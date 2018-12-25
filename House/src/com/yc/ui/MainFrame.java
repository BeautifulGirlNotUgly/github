package com.yc.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.Color;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 * @param uname 
	 */
	public MainFrame(String uname) {
		
		setBackground(new Color(51, 102, 255));
		setTitle("\u56FE\u4E66\u9986\u7BA1\u7406\u7CFB\u7EDF");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 670, 498);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("\u7528\u6237\u7BA1\u7406");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("\u7528\u6237\u67E5\u8BE2");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				UserQueryDialog dialog = null;
				try {
					dialog = new UserQueryDialog();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				dialog.setVisible(true);
			}
		});
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("\u56FE\u4E66\u7BA1\u7406");
		menuBar.add(mnNewMenu_1);

		JMenuItem menuItem = new JMenuItem("\u56FE\u4E66\u5F55\u5165 ");
		menuItem.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				AddBookDialog dialog = new AddBookDialog();
				dialog.setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem);

		JMenuItem menuItem_1 = new JMenuItem("\u56FE\u4E66\u67E5\u8BE2");
		menuItem_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BookQueryDialog dialog = new BookQueryDialog(uname);
				dialog.setVisible(true);
			}
		});
		mnNewMenu_1.add(menuItem_1);
		
		JMenu menu = new JMenu("\u5176\u4ED6\u7BA1\u7406");
		menuBar.add(menu);
		
		JMenuItem menuItem_6 = new JMenuItem("\u5206\u7C7B\u7BA1\u7406");
		menuItem_6.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				BookTypeDialog eud = null;
				try {
					eud = new BookTypeDialog();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				eud.setVisible(true);
			}
		});
		menu.add(menuItem_6);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		// 创建图片对象
		ImageIcon imageIcon = new ImageIcon("pic.jpg");

		// 创建展示图片的文本框
		JLabel lbBg = new JLabel(imageIcon);
		// 设置满屏
		lbBg.setBounds(5, 5, -1, 415);
		// 添加图片到内容面板
		contentPane.add(lbBg);
	}
}
