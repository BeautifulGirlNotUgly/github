package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.bean.Renting;
import com.yc.biz.Rentbiz;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.awt.event.ActionEvent;
//租房页面
public class RentingDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private Rentbiz rentbiz = new Rentbiz();
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;

	/**
	 * Create the dialog.
	 */
	public RentingDialog() {

		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setTitle("\u79DF\u623F\u7BA1\u7406-\u5C0F\u578B\u7269\u4E1A\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 755, 529);
		this.setModal(true);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);

		JLabel label = new JLabel("\u5C0F\u578B\u7269\u4E1A\u7BA1\u7406\u7CFB\u7EDF ");
		label.setFont(new Font("宋体", Font.PLAIN, 60));
		label.setBounds(130, 65, 544, 77);
		contentPanel.add(label);
		
		JLabel lblid = new JLabel("\u623F\u6E90ID");
		lblid.setBounds(76, 190, 72, 18);
		contentPanel.add(lblid);
		
		textField = new JTextField();
		textField.setBounds(153, 187, 167, 24);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel label_1 = new JLabel("\u623F\u6E90\u540D\u79F0");
		label_1.setBounds(363, 190, 72, 18);
		contentPanel.add(label_1);
		
		textField_1 = new JTextField();
		textField_1.setBounds(442, 187, 167, 24);
		contentPanel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblid_1 = new JLabel("\u79DF\u6237ID");
		lblid_1.setBounds(76, 256, 72, 18);
		contentPanel.add(lblid_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(153, 253, 167, 24);
		contentPanel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("\u79DF\u6237\u59D3\u540D");
		label_2.setBounds(363, 256, 72, 18);
		contentPanel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setBounds(442, 253, 167, 24);
		contentPanel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel label_3 = new JLabel("\u623F\u6E90\u9762\u79EF");
		label_3.setBounds(76, 313, 72, 18);
		contentPanel.add(label_3);
		
		textField_4 = new JTextField();
		textField_4.setBounds(153, 310, 167, 24);
		contentPanel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel label_4 = new JLabel("\u79DF\u91D1");
		label_4.setBounds(383, 313, 72, 18);
		contentPanel.add(label_4);
		
		textField_5 = new JTextField();
		textField_5.setBounds(442, 310, 167, 24);
		contentPanel.add(textField_5);
		textField_5.setColumns(10);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		String cdate = sdf.format(c.getTime());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u786E\u8BA4");
				okButton.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						Renting renting = new Renting();
						renting.setHid(textField.getText());
						renting.setHname(textField_1.getText());
						renting.setRid(textField_2.getText());
						renting.setRname(textField_3.getText());
						renting.setRarea(textField_4.getText());
						renting.setRrent(textField_5.getText());
						
						String msg = rentbiz.renthouse(renting);
						if (msg == null) {
							JOptionPane.showMessageDialog(RentingDialog.this, "租房成功");
							RentingDialog.this.dispose();
						} else {
							JOptionPane.showMessageDialog(RentingDialog.this, msg);
							RentingDialog.this.dispose();
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
					public void actionPerformed(ActionEvent e) {
						RentingDialog.this.dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
