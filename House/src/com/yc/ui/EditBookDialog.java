package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Bookbiz;
import com.yc.util.FileUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class EditBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Bookbiz bookbiz = new Bookbiz();
	private JTextField textField_5;

	/**
	 * Create the dialog.
	 * 
	 * @param row
	 */
	public EditBookDialog(Vector<Object> book) {
		setTitle("\u56FE\u4E66\u4FEE\u6539-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
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
			JLabel label = new JLabel("\u4E66\u540D\uFF1A");
			label.setBounds(164, 127, 72, 18);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(250, 124, 265, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblNewLabel = new JLabel("ISBN\uFF1A");
			lblNewLabel.setBounds(164, 169, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(250, 166, 265, 24);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4F5C\u8005\uFF1A");
			label.setBounds(164, 214, 72, 18);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(250, 211, 265, 24);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u520A\u5370\u65E5\u671F\uFF1A");
			label.setBounds(164, 259, 98, 18);
			contentPanel.add(label);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(250, 256, 265, 24);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}

		JLabel label = new JLabel("\u5165\u9986\u65E5\u671F\uFF1A");
		label.setBounds(164, 304, 83, 18);
		contentPanel.add(label);

		textField_4 = new JTextField();
		textField_4.setBounds(250, 301, 265, 24);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		{
			JLabel label_1 = new JLabel("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
			label_1.setFont(new Font("宋体", Font.PLAIN, 60));
			label_1.setBounds(148, 31, 453, 63);
			contentPanel.add(label_1);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u4FDD\u5B58");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						String str = "" + textField_3.getText();
						String strr = "" + textField_4.getText();

						BigDecimal bookid = (BigDecimal) book.get(0);
						// 清空原有的用户数据
						book.clear();
						// 从界面读取用户数据，写入到users
						book.add(bookid);
						book.add(textField.getText());
						book.add(textField_1.getText());
						book.add(textField_2.getText());
						book.add(FileUtils.change(str));
						book.add(FileUtils.change(strr));
						book.add(textField_5.getText());

						try {
							// 调用用户服务对象的更新方法
							bookbiz.modify(book);
							// 更新成功之后，关闭窗口
							EditBookDialog.this.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(EditBookDialog.this, e1.getMessage());
						}
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {

						// 关闭窗口
						EditBookDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}

		JLabel label_1 = new JLabel("\u5907\u6CE8\uFF1A");
		label_1.setBounds(164, 343, 72, 18);
		contentPanel.add(label_1);

		textField_5 = new JTextField();
		textField_5.setBounds(250, 341, 265, 21);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);

		/**
		 * 将传入的用户信息，写到控件中
		 */

		// 书名
		textField.setText((String) book.get(1));
		// isbn
		textField_1.setText((String) book.get(2));
		// 作者
		textField_2.setText((String) book.get(3));
		// 备注
		textField_5.setText((String) book.get(6));

	}
}
