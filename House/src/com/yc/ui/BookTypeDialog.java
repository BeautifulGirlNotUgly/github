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
		// ���õ�ǰ����Ϊģ̬����
		this.setModal(true);
		// ���þ���
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
						// ��ȡ��ѯ����

						String name = textField.getText();
						String info = textField_1.getText();
						
						// ���ò�ѯ����
						List<Map<String, Object>> list = btbiz.find(name, info);
						Vector<Vector<Object>> dbdate = BeanUtils.toVector(list);

						// ����ѯ������µ����
						// ��ձ��ļ���
						data.clear();
						// �������ñ�񼯺��е�����
						data.addAll(dbdate);
						// ���±��ؼ�
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
						 * ɾ���û���ɾ����ѡ�е��û� ��ȡѡ�б���еļ�¼
						 */
						int index = table.getSelectedRow();
						Vector<Object> row = data.get(index);
						if(index < 0 ) {
							JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ�����û�");
							return;
						}
						// ��ʾȷ���Ƿ�ɾ��
						int ret = JOptionPane.showConfirmDialog(null, "��ȷ���Ƿ�ɾ�����û���");

						if (ret == JOptionPane.YES_OPTION) {
							// ��ȡ�û� ����ת�� object ==��Long
							// BigDecimal ��ʵ���� ������˵���Ա�ʾ�����С������
							BigDecimal userid = (BigDecimal) row.get(0);
							try {
								// ɾ��������¼��1��ɾ�����ݿ��¼
								btbiz.remove(userid.longValue());
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
								JOptionPane.showMessageDialog(null, "ϵͳ��æ�����Ժ�����");
							}
							// 2��ɾ������еļ�¼
							data.remove(index);
							// ���±��ʵ�ֵ�����
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
				head.add("����");
				head.add("����");
				head.add("��ID");

				table = new JTable(data, head);
				scrollPane.setViewportView(table);
			}
		}
	}

}
