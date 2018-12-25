package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.bean.Renter;
import com.yc.biz.Userbiz;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class RegisterDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JPasswordField passwordField;
	private Userbiz userbiz = new Userbiz();
	private JPasswordField passwordField_1;

	/**
	 * Create the dialog.
	 */
	public RegisterDialog() {
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
			JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
			label.setBounds(159, 186, 72, 18);
			contentPanel.add(label);
		}
		{
			textField = new JTextField();
			textField.setBounds(297, 183, 265, 24);
			contentPanel.add(textField);
			textField.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
			label.setBounds(159, 240, 72, 18);
			contentPanel.add(label);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(297, 237, 265, 24);
			contentPanel.add(passwordField);
		}
		{
			JLabel label_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
			label_1.setBounds(159, 298, 83, 18);
			contentPanel.add(label_1);
		}

		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(296, 295, 266, 24);
		contentPanel.add(passwordField_1);
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
				JButton okButton = new JButton("\u6CE8\u518C");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Renter renter = new Renter();
						renter.setRname(textField.getText());
						renter.setRpwd(new String(passwordField.getPassword()));

						// 调用用户服务对象的注册方法
						String msg = userbiz.register(renter, new String(passwordField_1.getPassword()));
						if (msg == null) {
							JOptionPane.showMessageDialog(RegisterDialog.this, "注册成功");
						} else {
							JOptionPane.showMessageDialog(RegisterDialog.this, msg);
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
						RegisterDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
