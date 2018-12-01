package com.yc.ui;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import java.awt.MouseInfo;
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

public class Login {

	protected Shell shell;
	private Text text;
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
		//鼠标移动
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
				//鼠标松开，就不能移动了
				dragFrag=false;
			}
			@Override
			public void mouseUp(MouseEvent e) {
				//鼠标松开，就不能移动了
				dragFrag=false;
			}
		});
		shell.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/aaaa.jpeg"));
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setSize(567, 472);
		shell.setText("SWT Application");
		
		Combo combo = new Combo(shell, SWT.NONE);
		combo.setBounds(203, 93, 114, 25);
		
		combo.add("管理员");
		combo.add("用户");
		combo.select(1);
		
		Combo combo_1 = new Combo(shell, SWT.NONE);
		combo_1.setBounds(203, 159, 114, 25);
		
		
		text = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text.setBounds(203, 223, 111, 23);
		
		Button btnCheckButton = new Button(shell, SWT.CHECK);
		btnCheckButton.setBounds(203, 269, 98, 17);
		btnCheckButton.setText("\u8BB0\u4F4F\u5BC6\u7801");
		
		Button btnNewButton = new Button(shell, SWT.NONE);
		btnNewButton.setFont(SWTResourceManager.getFont("΢���ź� Light", 11, SWT.NORMAL));
		
		btnNewButton.setBounds(220, 324, 80, 27);
		btnNewButton.setText("\u767B\u9646");
		
		Label lblNewLabel = new Label(shell, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("΢���ź�", 10, SWT.NORMAL));
		lblNewLabel.setBounds(347, 163, 71, 25);
		lblNewLabel.setText("\u8BF7\u8F93\u5165\u8D26\u53F7");
		
		Label lblNewLabel_1 = new Label(shell, SWT.NONE);
		lblNewLabel_1.setFont(SWTResourceManager.getFont("΢���ź�", 10, SWT.NORMAL));
		lblNewLabel_1.setBounds(347, 226, 71, 23);
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
		
		Label lblNewLabel_4 = new Label(shell, SWT.NONE);
		lblNewLabel_4.setBounds(47, 93, 61, 17);
		lblNewLabel_4.setText("New Label");
		
	}
}
