package com.yc.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import com.yc.db.DBHelper;


import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Combo;

public class Register {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	protected boolean dragFrag;
	protected int x;
	protected int y;
	private DBHelper db=new DBHelper();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Register window = new Register();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NO_TRIM);
		shell.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if( dragFrag ) {
					shell.setLocation(MouseInfo.getPointerInfo().getLocation().x-x,
							MouseInfo.getPointerInfo().getLocation().y-y);
				}
			}
		});
		shell.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				//鼠标移动，代表可以移动
				dragFrag=true;
				//获取到当前的坐标
				x=e.x;
				y=e.y;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				//鼠标松开，就不能移动了
				dragFrag=false;
			}
		});
		shell.setBackgroundImage(SWTResourceManager.getImage(Register.class, "/images/aaaa.jpeg"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(647, 407);
		shell.setText("SWT Application");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		lblNewLabel.setBounds(147, 185, 80, 27);
		lblNewLabel.setText("账号：");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(260, 185, 176, 23);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label.setText("\u5BC6\u7801\uFF1A");
		label.setBounds(147, 236, 54, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(260, 236, 176, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label_1.setText("姓名：");
		label_1.setBounds(147, 290, 80, 27);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(260, 290, 176, 23);
		
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label_2.setBounds(147, 128, 76, 20);
		label_2.setText("请选择：");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(260, 128, 168, 28);
		combo.setFont(SWTResourceManager.getFont("宋体",13, SWT.NORMAL));

		combo.add("管理员");
		combo.add("用户");
		combo.select(0);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				if(combo.getText().equals("管理员")) {
					String mid=text.getText();
					String mpassword=text_1.getText();
					String mname=text_2.getText();
					
					String sql="insert into manager values(seq_rid.nextval,?,?,?)";
					List<Object> params=new ArrayList<Object>();
					params.add(mid);
					params.add(db.string2MD5(mpassword));
					params.add(mname);
				}else {
					String uid=text.getText();
					String upassword=text_1.getText();
					String uname=text_2.getText();
					
					String sql="insert into user values(seq_rid.nextval,?,?,?)";
					List<Object> params=new ArrayList<Object>();
					params.add(uid);
					params.add(db.string2MD5(upassword));
					params.add(uname);
					db.doUpdate(sql, params);
				}
				Login lg = new Login();
				lg.open();
			}
		});
		
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton.setBounds(197, 370, 80, 27);
		btnNewButton.setText("\u786E\u8BA4");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		btnNewButton_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				shell.close();
			}
		});
		
		btnNewButton_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton_1.setBounds(386, 370, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		//关闭窗口
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		lblNewLabel_2.setImage(SWTResourceManager.getImage(Register.class, "/images/btn_close_normal.png"));
		lblNewLabel_2.setBounds(610, -2, 39, 17);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		//最小化窗口
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
		lblNewLabel_3.setImage(SWTResourceManager.getImage(Register.class, "/images/btn_mini_normal.png"));
		lblNewLabel_3.setBounds(581, -6, 28, 27);
		
	}
}
