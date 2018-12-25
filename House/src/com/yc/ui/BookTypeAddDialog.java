package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.BookTypebiz;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BookTypeAddDialog extends JDialog {

	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JComboBox combobox = new JComboBox();
	private BookTypebiz btbiz = new BookTypebiz();

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public BookTypeAddDialog() {
		setTitle("\u7528\u6237\u6CE8\u518C-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		// 设置当前窗体为模态窗体
		this.setModal(true);
		// 设置位置大小
		setBounds(100, 100, 774, 521);
		// 设置居中
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u5206\u7C7B\u540D\uFF1A");
			label.setBounds(164, 149, 72, 18);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(250, 146, 265, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u63CF\u8FF0\uFF1A");
			label.setBounds(164, 186, 72, 18);
			contentPanel.add(label);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(250, 183, 265, 24);
			contentPanel.add(textField_1);
		}
		{
			JLabel lblid = new JLabel("\u7236ID\uFF1A");
			lblid.setBounds(164, 233, 83, 18);
			contentPanel.add(lblid);
		}
		{
			JLabel label_1 = new JLabel("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
			label_1.setFont(new Font("宋体", Font.PLAIN, 60));
			label_1.setBounds(148, 31, 453, 63);
			contentPanel.add(label_1);
		}

		List<Map<String, Object>> list = btbiz.find(null, null);
		{
			String[] items = new String[list.size()];
			int i = 0;
			for (Map<String, Object> map : list) {
				String name = (String) map.get("NAME");
				items[i] = name;
				i++;
			}
			combobox.setModel(new DefaultComboBoxModel(items));
			combobox.setBounds(250, 229, 265, 24);
			contentPanel.add(combobox);

			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u6DFB\u52A0");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {

						String name = textField.getText();
						String info = textField_1.getText();
						/**
						 * 获取下拉框的当前选择的选项
						 */
						// 获取被选择的选项索引（编号）
						int index = combobox.getSelectedIndex();
						// 获取行记录map
						Map<String, Object> row = list.get(index);
						// 获取父ID
						String pid = "" + row.get("ID");
						// 调用用户服务对象的注册方法
						String msg = btbiz.add(name, info, pid);
						if (msg == null) {
							JOptionPane.showMessageDialog(BookTypeAddDialog.this, "成功添加");
							BookTypeAddDialog.this.dispose();

						} else {
							JOptionPane.showMessageDialog(BookTypeAddDialog.this, msg);
							BookTypeAddDialog.this.dispose();
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);

				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// 关闭窗口
						BookTypeAddDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);

			}
		}
	}
}
