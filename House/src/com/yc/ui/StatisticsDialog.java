package com.yc.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.yc.biz.Bookbiz;
import com.yc.util.BeanUtils;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.WindowConstants;

public class StatisticsDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTable table;
	Bookbiz bookbiz = new Bookbiz();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			StatisticsDialog dialog = new StatisticsDialog();
			dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings("unchecked")
	public StatisticsDialog() {
		setTitle("\u56FE\u4E66\u7EDF\u8BA1-\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setBounds(100, 100, 791, 531);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panel = new JPanel();
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel label = new JLabel("\u8BF7\u9009\u62E9\u6708\u4EFD");
				panel.add(label);
			}
			{
				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "1\u6708", "2\u6708", "3\u6708", "4\u6708",
						"5\u6708", "6\u6708", "7\u6708", "8\u6708", "9\u6708", "10\u6708", "11\u6708", "12\u6708" }));
				panel.add(comboBox);
			}
		}
		{
			Vector data = new Vector();
			List<Map<String, Object>> list = bookbiz.find(null, null);
			data = BeanUtils.toVector(list);
			Vector<String> head = new Vector<String>();

			head.add("图书名称");
			head.add("发行编号");
			head.add("借阅次数");
			head.add("图书排名");

			table = new JTable(data, head);
			contentPanel.add(table, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("\u67E5\u8BE2");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("\u53D6\u6D88");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

}
