package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.bean.Book;
import com.yc.biz.Bookbiz;
import com.yc.util.BeanUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.sql.Date;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class AddBookDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	private Bookbiz bookbiz = new Bookbiz();

	/**
	 * Create the dialog.
	 */
	public AddBookDialog() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u56FE\u4E66\u5F55\u5165-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		// 设置当前窗体为模态窗体
		this.setModal(true);
		// 设置居中
		setLocationRelativeTo(null);
		setBounds(100, 100, 742, 529);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u4E66\u540D\uFF1A");
			label.setBounds(189, 154, 72, 18);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(275, 151, 265, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel lblIsbn = new JLabel("ISBN\uFF1A");
			lblIsbn.setBounds(189, 191, 72, 18);
			contentPanel.add(lblIsbn);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(275, 188, 265, 24);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u4F5C\u8005\uFF1A");
			label.setBounds(189, 228, 72, 18);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(275, 225, 265, 24);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u520A\u5370\u65E5\u671F\uFF1A");
			label.setBounds(189, 275, 90, 18);
			contentPanel.add(label);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(275, 272, 265, 24);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5165\u9986\u65E5\u671F\uFF1A");
			label.setBounds(189, 312, 81, 18);
			contentPanel.add(label);
		}
		{
			textField_4 = new JTextField();
			textField_4.setBounds(275, 309, 265, 24);
			contentPanel.add(textField_4);
			textField_4.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5907\u6CE8\uFF1A");
			label.setBounds(189, 344, 72, 18);
			contentPanel.add(label);
		}
		{
			textField_5 = new JTextField();
			textField_5.setBounds(275, 341, 265, 24);
			contentPanel.add(textField_5);
			textField_5.setColumns(10);
		}

		JLabel label = new JLabel("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		label.setFont(new Font("宋体", Font.PLAIN, 60));
		label.setBounds(161, 43, 451, 64);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u8BA4\u5F55\u5165");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						Book book = new Book();
						Date d = BeanUtils.cast(textField_3.getText(), Date.class);
						Date d1 = BeanUtils.cast(textField_4.getText(), Date.class);

						book.setBname(textField.getText());
						book.setIsbn(textField_1.getText());
						book.setAuthor(textField_2.getText());
						book.setPubdate(d);
						book.setIndate(d1);
						book.setStatus(textField_5.getText());

						String msg = bookbiz.AddBook(book);
						if (msg == null) {
							JOptionPane.showMessageDialog(AddBookDialog.this, "添加成功");
							AddBookDialog.this.dispose();
						} else {
							JOptionPane.showMessageDialog(AddBookDialog.this, msg);
							AddBookDialog.this.dispose();
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
						AddBookDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
