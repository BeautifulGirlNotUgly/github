package com.yc.ui;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import java.awt.MouseInfo;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import com.yc.db.DBHelper;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;

public class Register {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	protected boolean dragFrag;
	protected int x;
	protected int y;
	private boolean button;
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
		lblNewLabel.setBounds(165, 93, 126, 27);
		lblNewLabel.setText("\u8BFB\u8005\u8EAB\u4EFD\u8BC1\u53F7\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(308, 90, 132, 23);
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label.setText("\u5BC6\u7801\uFF1A");
		label.setBounds(228, 146, 54, 23);
		
		text_1 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(308, 146, 132, 23);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("楷体", 13, SWT.NORMAL));
		label_1.setText("\u8F93\u5165\u59D3\u540D\uFF1A");
		label_1.setBounds(194, 209, 80, 17);
		
		text_2 = new Text(shell, SWT.BORDER);
		text_2.setBounds(304, 205, 132, 23);
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		
		btnNewButton.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton.setBounds(199, 325, 80, 27);
		btnNewButton.setText("\u786E\u8BA4");
		
		Button btnNewButton_1 = new Button(shell, SWT.NONE);
		
		btnNewButton_1.setFont(SWTResourceManager.getFont("微软雅黑", 12, SWT.NORMAL));
		btnNewButton_1.setBounds(392, 325, 80, 27);
		btnNewButton_1.setText("\u53D6\u6D88");
		
		Button btnRadioButton_1 = new Button(shell, SWT.RADIO);
		btnRadioButton_1.setBounds(373, 266, 49, 17);
		btnRadioButton_1.setText("女");
		
		Button btnRadioButton = new Button(shell, SWT.RADIO);
		
		btnRadioButton.setBounds(302, 266, 49, 17);
		btnRadioButton.setText("男");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("楷体", 11, SWT.NORMAL));
		lblNewLabel_1.setBounds(231, 266, 49, 17);
		lblNewLabel_1.setText("\u6027\u522B\uFF1A");
		
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
