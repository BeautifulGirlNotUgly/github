package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Userbiz;

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

public class EditUserDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private Userbiz userbiz = new Userbiz();

	/**
	 * Create the dialog.
	 * 
	 * @param row
	 */
	public EditUserDialog(Vector<Object> users) {
		setTitle("\u7528\u6237\u4FEE\u6539-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		// ���õ�ǰ����Ϊģ̬����
		this.setModal(true);
		// ����λ�ô�С
		setBounds(100, 100, 774, 521);
		// ���þ���
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
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
			JLabel lblNewLabel = new JLabel("\u5730\u5740\uFF1A");
			lblNewLabel.setBounds(164, 195, 72, 18);
			contentPanel.add(lblNewLabel);
		}
		{
			textField_1 = new JTextField();
			textField_1.setBounds(250, 192, 265, 24);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u7535\u8BDD\uFF1A");
			label.setBounds(164, 243, 72, 18);
			contentPanel.add(label);
		}
		{
			textField_2 = new JTextField();
			textField_2.setBounds(250, 240, 265, 24);
			contentPanel.add(textField_2);
			textField_2.setColumns(10);
		}
		{
			JLabel label = new JLabel("\u8EAB\u4EFD\u8BC1\u53F7\u7801\uFF1A");
			label.setBounds(164, 293, 98, 18);
			contentPanel.add(label);
		}
		{
			textField_3 = new JTextField();
			textField_3.setBounds(250, 290, 265, 24);
			contentPanel.add(textField_3);
			textField_3.setColumns(10);
		}

		JLabel label = new JLabel("\u90AE\u7BB1\uFF1A");
		label.setBounds(164, 334, 72, 18);
		contentPanel.add(label);

		textField_4 = new JTextField();
		textField_4.setBounds(250, 331, 265, 24);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		{
			JLabel label_1 = new JLabel("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
			label_1.setFont(new Font("����", Font.PLAIN, 60));
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
						// ���û���Ϣд�ص�users��ȥ
						// ��ʱ�����û�id������
						BigDecimal userid = (BigDecimal) users.get(0);
						String pwd = (String) users.get(6);
						// ���ԭ�е��û�����
						users.clear();
						// �ӽ����ȡ�û����ݣ�д�뵽users
						users.add(userid);
						users.add(textField.getText());
						users.add(textField_1.getText());
						users.add(textField_2.getText());
						users.add(textField_3.getText());
						users.add(textField_4.getText());
						users.add(pwd);

						try {
							
							// �����û��������ĸ��·���
							userbiz.modify(users);
							// ���³ɹ�֮�󣬹رմ���
							EditUserDialog.this.dispose();
						} catch (SQLException e1) {
							e1.printStackTrace();
							JOptionPane.showMessageDialog(EditUserDialog.this, e1.getMessage());
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

						// �رմ���
						EditUserDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		/**
		 * ��������û���Ϣ��д���ؼ���
		 */
		// �����û���
		textField.setText((String) users.get(1));
		// �绰
		textField_1.setText((String) users.get(3));
		// ���֤
		textField_2.setText((String) users.get(4));
		// ����
		textField_3.setText((String) users.get(5));
		// ��ַ
		textField_4.setText((String) users.get(6));
	}
}
