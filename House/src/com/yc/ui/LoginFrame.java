package com.yc.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Userbiz;

import java.awt.Font;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	// 创建用户服务对象
	private Userbiz userbiz = new Userbiz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setTitle("\u7528\u6237\u767B\u5F55-\u5C0F\u578B\u7269\u4E1A\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 711, 495);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setBounds(152, 198, 72, 18);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setBounds(152, 258, 72, 18);
		contentPane.add(label_1);

		textField = new JTextField();
		textField.setBounds(238, 195, 235, 24);
		contentPane.add(textField);
		textField.setColumns(10);

		passwordField = new JPasswordField();
		passwordField.setBounds(238, 255, 235, 24);
		contentPane.add(passwordField);

		JButton button = new JButton("\u767B\u5F55");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String rname = textField.getText();
				String rpwd = new String(passwordField.getPassword());
				String msg = userbiz.login(rname, rpwd);

				if (msg == null) {
					MainFrame frame = new MainFrame(rname);
					// 让窗口自动适应内部控件大小，最适大小
					frame.pack();
					// 显示窗体
					frame.setVisible(true);
					// 关闭登录窗口
					LoginFrame.this.dispose();
				} else {
					JOptionPane.showMessageDialog(LoginFrame.this, msg);
				}
			}
		});
		button.setBounds(207, 324, 113, 27);
		contentPane.add(button);

		JButton button_1 = new JButton("\u6CE8\u518C");
		button_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				RegisterDialog dialog = new RegisterDialog();
				dialog.setVisible(true);
			}
		});
		button_1.setBounds(360, 324, 113, 27);
		contentPane.add(button_1);

		JLabel label_2 = new JLabel("\u5C0F\u578B\u7269\u4E1A\u7BA1\u7406\u7CFB\u7EDF");
		label_2.setFont(new Font("宋体", Font.PLAIN, 60));
		label_2.setBounds(98, 62, 497, 73);
		contentPane.add(label_2);
	}
}
