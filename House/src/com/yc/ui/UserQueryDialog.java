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

import com.yc.biz.Userbiz;
import com.yc.util.BeanUtils;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
//普通用户查询房源资料
public class UserQueryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;

	private Userbiz userbiz = new Userbiz();
	private Vector<Vector<Object>> data;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the dialog.
	 * 
	 * @throws SQLException
	 */
	public UserQueryDialog() throws SQLException {
		setTitle("\u7528\u6237\u67E5\u8BE2\u623F\u6E90\u8D44\u6599");
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
				JLabel label = new JLabel("\u540D\u5B57\uFF1A");
				panel.add(label);
			}
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u9762\u79EF\uFF1A");
				panel.add(label);
			}
			{
				textField_1 = new JTextField();
				panel.add(textField_1);
				textField_1.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u6708\u79DF\uFF1A");
				panel.add(label);
			}
			{
				textField_2 = new JTextField();
				panel.add(textField_2);
				textField_2.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u62BC\u91D1\uFF1A");
				panel.add(label);
			}
			{
				textField_3 = new JTextField();
				panel.add(textField_3);
				textField_3.setColumns(10);
			}
			{
				JButton button = new JButton("\u67E5\u8BE2");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						// 获取查询参数

						String hname = textField.getText();
						String area = textField_1.getText();
						String monthMoney = textField_2.getText();
						String rcash = textField_3.getText();

						// 调用查询方法
						List<Map<String, Object>> list = userbiz.find(hname, area, monthMoney, rcash);
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
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				List<Map<String, Object>> list = userbiz.find(null, null, null, null);
				data = BeanUtils.toVector(list);
				Vector<String> head = new Vector<String>();

				head.add("房源名称");
				head.add("房源面积");
				head.add("房源租金");
				head.add("房源物业");
				head.add("房源仓库");
				head.add("房源押金");
				head.add("剩余房源");
				
				table = new JTable(data, head);
				scrollPane.setViewportView(table);
			}
		}
	}

}
