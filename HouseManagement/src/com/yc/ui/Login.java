package com.yc.ui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.awt.MouseInfo;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.wb.swt.SWTResourceManager;
import com.yc.db.DBHelper;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Login {

	protected Shell shell;
	private Text text;
	protected boolean dragFrag;
	protected int x;
	protected int y;
	private Text text_1;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
		shell.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/aaaa.jpeg"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(602, 472);
		shell.setText("SWT Application");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		combo.setBounds(291, 168, 186, 28);
		
		combo.add("管理员");
		combo.add("用户");
		combo.select(1);
		
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		text.setBounds(291, 306, 186, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(combo.getText().equals("管理员")) {
					String mname=text_1.getText();
					String mpassword=text.getText();
					
					//String sql="select * from manager where mid = ? and mpassword = ?";
					if("admin".equals(mname) && "123456".equals(mpassword)) {
						Manager mg = new Manager(null);
						mg.open();
					}else {
						//弹窗提示“账号或密码错误，请重试！”
					}	
				}else {
					String rname=text_1.getText();
					String rpassword=text.getText();
					
					//String sql="select * from renter where rid = ? and rpassword = ?";
					if("admin".equals(rname) && "123456".equals(rpassword)) {
						/*User ur = new User(null);
						ur.open();*/
					}else {
						//弹窗提示“账号或密码错误，请重试！”
					}	
				}
			}
		});
		btnNewButton.setFont(SWTResourceManager.getFont("΢���ź� Light", 11, SWT.NORMAL));
		
		btnNewButton.setBounds(369, 377, 98, 27);
		btnNewButton.setText("登录");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel.setBounds(62, 235, 167, 25);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		lblNewLabel_1.setBounds(62, 309, 167, 27);
		lblNewLabel_1.setText("\u8BF7\u8F93\u5165\u5BC6\u7801");
		
		Label lblNewLabel_2 = new Label(shell, SWT.NONE);
		//关闭窗口
		lblNewLabel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.dispose();
			}
		});
		lblNewLabel_2.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_close_normal.png"));
		lblNewLabel_2.setBounds(534, 0, 33, 17);
		
		Label lblNewLabel_3 = new Label(shell, SWT.NONE);
		//窗口最小化
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
		lblNewLabel_3.setImage(SWTResourceManager.getImage(Login.class, "/images/btn_mini_normal.png"));
		lblNewLabel_3.setBounds(509, -6, 28, 27);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("宋体", 16, SWT.NORMAL));
		label.setBounds(62, 171, 124, 28);
		label.setText("请选择");
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("楷体", 26, SWT.NORMAL));
		label_1.setBounds(62, 48, 456, 51);
		label_1.setText("欢迎登录物业管理系统");
		
		Button button = new Button(shell, SWT.NONE);
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Register rg = new Register();
				rg.open();
			}
		});
		button.setBounds(154, 375, 98, 30);
		button.setText("注册");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(291, 239, 186, 26);
		
	}
}
