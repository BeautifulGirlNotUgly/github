package com.yc.ui;

import java.awt.BorderLayout;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Bookbiz;
import com.yc.util.BeanUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class BookQueryDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private JTextField textField_1;
	private JTable table;
	private Bookbiz bookbiz = new Bookbiz();
	private Vector<Vector<Object>> data;

	/**
	 * Create the dialog.
	 * @param uname 
	 */
	public BookQueryDialog(String uname) {
		setTitle("\u56FE\u4E66\u67E5\u8BE2-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1009, 640);
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
				JLabel label = new JLabel("\u56FE\u4E66\u540D\u79F0\uFF1A");
				panel.add(label);
			}
			{
				textField = new JTextField();
				panel.add(textField);
				textField.setColumns(10);
			}
			{
				JLabel label = new JLabel("\u56FE\u4E66\u4F5C\u8005\uFF1A");
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
					public void actionPerformed(ActionEvent arg0) {

						String bname = textField.getText();
						String author = textField_1.getText();

						List<Map<String, Object>> list = bookbiz.find(bname, author);
						Vector<Vector<Object>> dbdata = BeanUtils.toVector(list);

						data.clear();
						data.addAll(dbdata);
						table.updateUI();

					}
				});
				panel.add(button);
			}
			{
				
				JButton button = new JButton("\u4FEE\u6539");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						int index = table.getSelectedRow();
						if (index < 0) {
							JOptionPane.showMessageDialog(null, "��ѡ����Ҫ�޸ĵ�ͼ��");
							return;
						}
						Vector<Object> row = data.get(index);

						// ���û��༭�Ĵ��ڣ����ҽ�Ҫ�޸ĵ��û����ݸ��༭����
						// �������ݷ�ʽһ��ʹ�ù��췽�������������û���Ϣ�ļ��϶����Ƽ���
						EditBookDialog ebd = new EditBookDialog(row);
						// �������ݷ�ʽ����ʹ�ö��󷽷��������� ����û���Ϣ�ļ��϶���
						// eud.setRow(row);
						// ���༭������ʾ��ʱ�򣬵�ǰ������ֹͣ���У�֪�����ڹر�
						ebd.setVisible(true);
						// �رմ���֮�󣬸��±��
						table.updateUI();
					}
				});
				panel.add(button);
			}
			{
				JButton button = new JButton("\u5220\u9664");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						/**
						 * ɾ���û���ɾ����ѡ�е��û� ��ȡѡ�б���еļ�¼
						 */
						int index = table.getSelectedRow();
						if (index < 0) {
							JOptionPane.showMessageDialog(null, "��ѡ����Ҫɾ����ͼ��");
							return;
						}
						Vector<Object> row = data.get(index);
						// ��ʾȷ���Ƿ�ɾ��
						int ret = JOptionPane.showConfirmDialog(null, "��ȷ���Ƿ�ɾ����ͼ�飿");
						System.out.println("=======" + ret);

						if (ret == JOptionPane.YES_OPTION) {
							// ��ȡ�û� ����ת�� object ==��Long
							// BigDecimal ��ʵ���� ������˵���Ա�ʾ�����С������
							BigDecimal bookid = (BigDecimal) row.get(0);
							try {
								bookbiz.remove(bookid.longValue());
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
								JOptionPane.showMessageDialog(null, "ϵͳ��æ�����Ժ�����");
							}
							// 2��ɾ������еļ�¼
							data.remove(index);
							// ���±��ʵ�ֵ�����
							table.updateUI();
						}
					}
				});
				panel.add(button);
			}
			{
				JButton button = new JButton("\u501F\u4E66");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						BorrowDialog dialog = new BorrowDialog();
						dialog.setVisible(true);
					}
				});
				panel.add(button);
			}
			{
				JButton button = new JButton("\u8FD8\u4E66");
				button.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						ReturnDialog dialog = new ReturnDialog(uname);
						dialog.setVisible(true);
					}
				});
				panel.add(button);
			}
		}
		{
			JScrollPane scrollPane = new JScrollPane();
			contentPanel.add(scrollPane, BorderLayout.CENTER);
			{
				List<Map<String, Object>> list = bookbiz.find(null, null);
				data = BeanUtils.toVector(list);
				Vector<String> head = new Vector<String>();

				head.add("ͼ��ID");
				head.add("ͼ������");
				head.add("���б��");
				head.add("ͼ������");
				head.add("��ӡ����");
				head.add("��������");
				head.add("����״̬");

				table = new JTable(data, head);
				scrollPane.setViewportView(table);
			}
		}
	}

}
