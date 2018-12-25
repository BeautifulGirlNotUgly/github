package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Borrowbiz;
import com.yc.util.DBUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;

public class ReturnDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField_1;
	private JTextField textField_2;
	private Borrowbiz borrowbiz = new Borrowbiz();

	/**
	 * Create the dialog.
	 */
	public ReturnDialog(String uname) {
		System.out.println("==========" + uname);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u501F\u9605\u7BA1\u7406-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 755, 529);
		// 设置当前窗体为模态窗体
		this.setModal(true);
		// 设置居中
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF ");
		label.setFont(new Font("宋体", Font.PLAIN, 60));
		label.setBounds(158, 68, 491, 77);
		contentPanel.add(label);

		JLabel lblid_1 = new JLabel("\u5F52\u8FD8\u65E5\u671F:");
		lblid_1.setBounds(191, 174, 72, 18);
		contentPanel.add(lblid_1);

		textField_1 = new JTextField();
		textField_1.setEditable(false);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();

		String cdate = sdf.format(c.getTime());
		textField_1.setText("" + cdate);
		textField_1.setBounds(288, 171, 252, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblid_2 = new JLabel("\u56FE\u4E66\u540D\u79F0:");
		lblid_2.setBounds(191, 224, 83, 18);
		contentPanel.add(lblid_2);

		textField_2 = new JTextField();
		textField_2.setBounds(288, 221, 252, 24);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u8BA4\u5F52\u8FD8");
				okButton.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent e) {
						String sql = "update borrow set backtime=sysdate where uname=?";
						// 清空原有的用户数据
						try {
							DBUtils.update(sql, uname);
							JOptionPane.showMessageDialog(ReturnDialog.this, "还书成功");
							ReturnDialog.this.dispose();
						} catch (SQLException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							JOptionPane.showMessageDialog(ReturnDialog.this, "系统繁忙，请稍后重试");
						}
						ReturnDialog.this.dispose();
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
					public void actionPerformed(ActionEvent e) {
						ReturnDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
