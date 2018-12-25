package com.yc.ui;

import java.awt.BorderLayout;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.BookTypebiz;
import com.yc.util.BeanUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class BookTypeDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	private BookTypebiz btbiz = new BookTypebiz();
	private Vector<Vector<Object>> data;

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public BookTypeDialog() throws SQLException {
		setTitle("\u7528\u6237\u67E5\u8BE2-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 988, 688);
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
				JLabel label = new JLabel("\u5206\u7C7B\uFF1A");
				panel.add(label);
			}
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u63CF\u8FF0\uFF1A");
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
					public void actionPerformed(ActionEvent e) {
						// 获取查询参数

						String name = textField.getText();
						String info = textField_1.getText();
						
						// 调用查询方法
						List<Map<String, Object>> list = btbiz.find(name, info);
						Vector<Vector<Object>> dbdate = BeanUtils.toVector(list);

						// 将查询结果更新到表格
						// 清空表格的集合
						data.clear();
						// 重新设置表格集合中的数据
						data.addAll(dbdate);
						// 更新表格控件
						table.updateUI();

					}
				});

				panel.add(button);
			}
			{
				JButton button = new JButton("\u5220\u9664");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						/**
						 * 删除用户、删除被选中的用户 获取选中表格中的记录
						 */
						int index = table.getSelectedRow();
						Vector<Object> row = data.get(index);
						if(index < 0 ) {
							JOptionPane.showMessageDialog(null, "请选择你要删除的用户");
							return;
						}
						// 提示确认是否删除
						int ret = JOptionPane.showConfirmDialog(null, "请确认是否删除该用户？");

						if (ret == JOptionPane.YES_OPTION) {
							// 获取用户 向下转型 object ==》Long
							// BigDecimal 大实数类 理论上说可以表示任意大小的数据
							BigDecimal userid = (BigDecimal) row.get(0);
							try {
								// 删除该条记录：1、删除数据库记录
								btbiz.remove(userid.longValue());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "系统繁忙，请稍后重试");
							}
							// 2、删除表格中的记录
							data.remove(index);
							// 更新表格实现的内容
							table.updateUI();
						}
					}
				});
				{
					JButton button_1 = new JButton("\u6DFB\u52A0");
					button_1.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent arg0) {
							BookTypeAddDialog eud = new BookTypeAddDialog();
							eud.setVisible(true);
							table.updateUI();
						}
					});
					panel.add(button_1);
				}
				panel.add(button);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				List<Map<String, Object>> list =btbiz.find(null, null);
				data = BeanUtils.toVector(list);
				Vector<String> head = new Vector<String>();

				head.add("ID");
				head.add("名称");
				head.add("描述");
				head.add("父ID");

				table = new JTable(data, head);
				scrollPane.setViewportView(table);
			}
		}
	}

}
