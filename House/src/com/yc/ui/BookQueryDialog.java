package com.yc.ui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Bookbiz;
import com.yc.util.BeanUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BookQueryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private Bookbiz bookbiz = new Bookbiz();
	private Vector<Vector<Object>> data;

	/**
	 * Create the dialog.
	 * @param uname 
	 */
	public BookQueryDialog(String uname) {
		setTitle("\u56FE\u4E66\u67E5\u8BE2-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1009, 640);
		// 设置当前窗体为模态窗体
		this.setModal(true);
		// 设置居中
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
				panel.add(label);
			}
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
				panel.add(label);
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				JButton button = new JButton("\u67E5\u8BE2");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						String bname = textField.getText();
						String author = textField_1.getText();

						List<Map<String, Object>> list = bookbiz.find(bname, author);
						Vector<Vector<Object>> dbdata = BeanUtils.toVector(list);

						data.clear();
						data.addAll(dbdata);
						table.updateUI();

					}
				});
				panel.add(button);
			}
			{
				
				JButton button = new JButton("\u4FEE\u6539");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int index = table.getSelectedRow();
						if (index < 0) {
							JOptionPane.showMessageDialog(null, "请选择你要修改的图书");
							return;
						}
						Vector<Object> row = data.get(index);

						// 打开用户编辑的窗口，并且将要修改的用户传递给编辑窗口
						// 传递数据方式一：使用构造方法参数传入存放用户信息的集合对象（推荐）
						EditBookDialog ebd = new EditBookDialog(row);
						// 传递数据方式二：使用对象方法参数传入 存放用户信息的集合对象
						// eud.setRow(row);
						// 当编辑窗口显示的时候，当前方法会停止运行，知道窗口关闭
						ebd.setVisible(true);
						// 关闭窗口之后，更新表格
						table.updateUI();
					}
				});
				panel.add(button);
			}
			{
				JButton button = new JButton("\u5220\u9664");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						/**
						 * 删除用户、删除被选中的用户 获取选中表格中的记录
						 */
						int index = table.getSelectedRow();
						if (index < 0) {
							JOptionPane.showMessageDialog(null, "请选择你要删除的图书");
							return;
						}
						Vector<Object> row = data.get(index);
						// 提示确认是否删除
						int ret = JOptionPane.showConfirmDialog(null, "请确认是否删除该图书？");
						System.out.println("=======" + ret);

						if (ret == JOptionPane.YES_OPTION) {
							// 获取用户 向下转型 object ==》Long
							// BigDecimal 大实数类 理论上说可以表示任意大小的数据
							BigDecimal bookid = (BigDecimal) row.get(0);
							try {
								bookbiz.remove(bookid.longValue());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "系统繁忙，请稍后重试");
							}
							// 2、删除表格中的记录
							data.remove(index);
							// 更新表格实现的内容
							table.updateUI();
						}
					}
				});
				panel.add(button);
			}
			{
				JButton button = new JButton("\u501F\u4E66");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BorrowDialog dialog = new BorrowDialog();
						dialog.setVisible(true);
					}
				});
				panel.add(button);
			}
			{
				JButton button = new JButton("\u8FD8\u4E66");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ReturnDialog dialog = new ReturnDialog(uname);
						dialog.setVisible(true);
					}
				});
				panel.add(button);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				List<Map<String, Object>> list = bookbiz.find(null, null);
				data = BeanUtils.toVector(list);
				Vector<String> head = new Vector<String>();

				head.add("图书ID");
				head.add("图书名称");
				head.add("发行编号");
				head.add("图书作者");
				head.add("刊印日期");
				head.add("进货日期");
				head.add("借阅状态");

				table = new JTable(data, head);
				scrollPane.setViewportView(table);
			}
		}
	}

}
