package com.yc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.wb.swt.SWTResourceManager;

public class Users {

	protected Shell shell;
	private StackLayout stackLayout = new StackLayout();
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private Text text_4;
	private Text text_5;
	private Text text_6;
	private Text text_7;
	private Text text_8;
	private Text text_9;
	private Table table;
	private Text text_10;
	private Table table_1;
	private Text text_11;
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Users window = new Users();
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
		shell.setBackgroundMode(SWT.INHERIT_FORCE);
		shell.setBackgroundImage(SWTResourceManager.getImage(Users.class, "/images/aa.jpeg"));
		shell.setSize(883, 587);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

	SashForm sashForm = new SashForm(shell, SWT.VERTICAL);
	sashForm.setBackgroundMode(SWT.INHERIT_DEFAULT);
	
	Composite composite = new Composite(sashForm, SWT.NONE);
	
	Label lblNewLabel = new Label(composite, SWT.NONE);
	lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 15, SWT.BOLD));
	lblNewLabel.setBounds(0, 10, 337, 26);
	lblNewLabel.setText("欢迎登陆小区物业管理系统");
	
	Label lblNewLabel_1 = new Label(composite, SWT.NONE);
	lblNewLabel_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseDown(MouseEvent e) {
			shell.setMinimized(true);
		}
	});
	lblNewLabel_1.setOrientation(SWT.RIGHT_TO_LEFT);
	lblNewLabel_1.setImage(SWTResourceManager.getImage(Users.class, "/images/btn_mini_normal.png"));
	lblNewLabel_1.setBounds(843, 0, 28, 21);
	
	Label label = new Label(composite, SWT.NONE);
	label.setImage(SWTResourceManager.getImage(Users.class, "/images/btn_close_normal.png"));
	label.setBounds(809, 0, 39, 17);
	
	/*Label lblNewLabel_1 = new Label(shell, SWT.NONE);
	//关闭窗口
	lblNewLabel_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseDown(MouseEvent e) {
			shell.dispose();
		}
	});*/
	
	Composite composite_1 = new Composite(sashForm, SWT.NONE);
	composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
	
	SashForm sashForm_1 = new SashForm(composite_1, SWT.VERTICAL);
	
	Composite composite_11 = new Composite(sashForm_1, SWT.NONE);
	composite_11.setLayout(new FillLayout(SWT.HORIZONTAL));
	
	SashForm sashForm_2 = new SashForm(composite_11, SWT.NONE);
	
	Composite composite_2 = new Composite(sashForm_2, SWT.NONE);
	composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
	
	Tree tree = new Tree(composite_2, SWT.BORDER);
	
	
	tree.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
	
	TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
	trtmNewTreeitem.setText("个人信息");
	
	TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem, SWT.NONE);
	trtmNewTreeitem_4.setText("修改密码");
	trtmNewTreeitem_4.setExpanded(true);
	
	TreeItem trtmNewTreeitem_5 = new TreeItem(trtmNewTreeitem, SWT.NONE);
	trtmNewTreeitem_5.setText("个人信息查看");
	
	TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
	trtmNewTreeitem_1.setText("房源信息");
	trtmNewTreeitem_1.setExpanded(true);
	
	TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
	trtmNewTreeitem_2.setText("收费信息");
	trtmNewTreeitem_2.setExpanded(true);
	
	TreeItem trtmNewTreeitem_3 =  new TreeItem(tree, SWT.NONE);
	trtmNewTreeitem_3.setText("意见反馈");
	trtmNewTreeitem_3.setExpanded(true);
	
	Composite composite_3 = new Composite(sashForm_2, SWT.NONE);
	composite_3.setLayout(new StackLayout());
	
	Composite composite_5 = new Composite(composite_3, SWT.NONE);
	composite_5.setLayout(stackLayout);
	
	Composite composite_4 = new Composite(composite_5, SWT.NONE);
	//设置顶层
	stackLayout.topControl = composite_4;
	composite_4.setLayout(null);
	
	Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
	lblNewLabel_3.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 27, SWT.NORMAL));
	lblNewLabel_3.setBounds(75, 115, 600, 145);
	lblNewLabel_3.setText("欢迎使用小区物业管理系统");
	
	Composite composite_6 = new Composite(composite_5, SWT.NONE);
	composite_6.setLayout(null);
	
	Label lblNewLabel_4 = new Label(composite_6, SWT.NONE);
	lblNewLabel_4.setFont(SWTResourceManager.getFont("楷体", 11, SWT.NORMAL));
	lblNewLabel_4.setBounds(179, 122, 80, 17);
	lblNewLabel_4.setText("新密码：");
	
	Label lblNewLabel_7 = new Label(composite_6, SWT.NONE);
	lblNewLabel_7.setFont(SWTResourceManager.getFont("楷体", 11, SWT.NORMAL));
	lblNewLabel_7.setBounds(179, 175, 80, 17);
	lblNewLabel_7.setText("确认密码：");
	
	Label lblNewLabel_8 = new Label(composite_6, SWT.NONE);
	lblNewLabel_8.setFont(SWTResourceManager.getFont("楷体", 11, SWT.NORMAL));
	lblNewLabel_8.setBounds(179, 70, 90, 20);
	lblNewLabel_8.setText("原密码：");
	
	text = new Text(composite_6, SWT.BORDER |SWT.PASSWORD);
	text.setBounds(275, 70, 123, 23);
	
	text_1 = new Text(composite_6, SWT.BORDER |SWT.PASSWORD);
	text_1.setText("");
	text_1.setBounds(275, 172, 123, 23);
	
	text_2 = new Text(composite_6, SWT.BORDER |SWT.PASSWORD);
	text_2.setBounds(275, 119, 123, 23);
	
	Button btnNewButton = new Button(composite_6, SWT.NONE);
	btnNewButton.setBounds(210, 276, 80, 27);
	btnNewButton.setText("重置");
	
	Button btnNewButton_1 = new Button(composite_6, SWT.NONE);
	btnNewButton_1.setBounds(348, 276, 80, 27);
	btnNewButton_1.setText("提交");
	
	Composite composite_7 = new Composite(composite_5, SWT.NONE);
	composite_7.setLayout(null);
	
	Label lblNewLabel_2 = new Label(composite_7, SWT.NONE);
	lblNewLabel_2.setBounds(55, 67, 42, 17);
	lblNewLabel_2.setText("姓名：");
	
	text_3 = new Text(composite_7, SWT.BORDER);
	text_3.setBounds(106, 64, 105, 23);
	
	Label lblNewLabel_5 = new Label(composite_7, SWT.NONE);
	lblNewLabel_5.setBounds(260, 68, 61, 17);
	lblNewLabel_5.setText("身份证号：");
	
	Label lblNewLabel_9 = new Label(composite_7, SWT.NONE);
	lblNewLabel_9.setBounds(485, 71, 61, 17);
	lblNewLabel_9.setText("联系电话：");
	
	Label lblNewLabel_10 = new Label(composite_7, SWT.NONE);
	lblNewLabel_10.setBounds(31, 145, 61, 17);
	lblNewLabel_10.setText("房屋信息：");
	
	text_4 = new Text(composite_7, SWT.BORDER);
	text_4.setBounds(108, 142, 103, 23);
	
	Label lblNewLabel_11 = new Label(composite_7, SWT.NONE);
	lblNewLabel_11.setBounds(260, 145, 61, 17);
	lblNewLabel_11.setText("房屋面积：");
	
	text_5 = new Text(composite_7, SWT.BORDER);
	text_5.setBounds(327, 142, 99, 23);
	
	Label lblNewLabel_12 = new Label(composite_7, SWT.NONE);
	lblNewLabel_12.setBounds(483, 145, 61, 17);
	lblNewLabel_12.setText("入住日期：");
	
	text_6 = new Text(composite_7, SWT.BORDER);
	text_6.setBounds(585, 142, 73, 23);
	
	Label lblNewLabel_13 = new Label(composite_7, SWT.NONE);
	lblNewLabel_13.setBounds(49, 227, 43, 17);
	lblNewLabel_13.setText("押金：");
	
	text_7 = new Text(composite_7, SWT.BORDER);
	text_7.setBounds(108, 224, 103, 23);
	
	text_8 = new Text(composite_7, SWT.BORDER);
	text_8.setBounds(327, 65, 99, 23);
	
	text_9 = new Text(composite_7, SWT.BORDER);
	text_9.setBounds(585, 68, 73, 23);
	
	Composite composite_8 = new Composite(composite_5, SWT.NONE);
	composite_8.setLayout(null);
	
	table = new Table(composite_8, SWT.BORDER | SWT.FULL_SELECTION);
	table.setBounds(0, 0, 603, 315);
	table.setHeaderVisible(true);
	table.setLinesVisible(true);
	
	TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
	tblclmnNewColumn.setWidth(100);
	tblclmnNewColumn.setText("房屋信息");
	
	TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
	tblclmnNewColumn_1.setWidth(100);
	tblclmnNewColumn_1.setText("房屋面积");
	
	TableColumn tblclmnNewColumn_2 = new TableColumn(table, SWT.NONE);
	tblclmnNewColumn_2.setWidth(100);
	tblclmnNewColumn_2.setText("月租");
	
	TableColumn tblclmnNewColumn_3 = new TableColumn(table, SWT.NONE);
	tblclmnNewColumn_3.setWidth(100);
	tblclmnNewColumn_3.setText("物业");
	
	TableColumn tblclmnNewColumn_4 = new TableColumn(table, SWT.NONE);
	tblclmnNewColumn_4.setWidth(100);
	tblclmnNewColumn_4.setText("仓库");
	
	TableColumn tblclmnNewColumn_5 = new TableColumn(table, SWT.NONE);
	tblclmnNewColumn_5.setWidth(100);
	tblclmnNewColumn_5.setText("是否已出租");
	
	Label lblNewLabel_6 = new Label(composite_8, SWT.NONE);
	lblNewLabel_6.setBounds(0, 357, 72, 17);
	lblNewLabel_6.setText("联系人电话：");
	
	text_10 = new Text(composite_8, SWT.BORDER);
	text_10.setBounds(78, 357, 126, 23);
	
	Composite composite_9 = new Composite(composite_5, SWT.NONE);
	
	table_1 = new Table(composite_9, SWT.BORDER | SWT.FULL_SELECTION);
	table_1.setBounds(20, 39, 492, 267);
	table_1.setHeaderVisible(true);
	table_1.setLinesVisible(true);
	
	TableColumn tblclmnNewColumn_6 = new TableColumn(table_1, SWT.NONE);
	tblclmnNewColumn_6.setWidth(126);
	tblclmnNewColumn_6.setText("收费项目");
	
	TableColumn tblclmnNewColumn_7 = new TableColumn(table_1, SWT.NONE);
	tblclmnNewColumn_7.setWidth(100);
	tblclmnNewColumn_7.setText("金额");
	
	TableColumn tblclmnNewColumn_8 = new TableColumn(table_1, SWT.NONE);
	tblclmnNewColumn_8.setWidth(127);
	tblclmnNewColumn_8.setText("收费日期");
	
	TableColumn tblclmnNewColumn_9 = new TableColumn(table_1, SWT.NONE);
	tblclmnNewColumn_9.setWidth(136);
	tblclmnNewColumn_9.setText("记录人");
	
	Composite composite_10 = new Composite(composite_3, SWT.NONE);
	
	text_11 = new Text(composite_10, SWT.BORDER);
	text_11.setBounds(133, 68, 415, 123);
	
	Button btnNewButton_2 = new Button(composite_10, SWT.NONE);
	btnNewButton_2.setBounds(312, 255, 80, 27);
	btnNewButton_2.setText("提交");
	sashForm_2.setWeights(new int[] {153, 715});
	sashForm.setWeights(new int[] {54, 530});
	
	tree.addSelectionListener(new SelectionAdapter() {
		@Override
		public void widgetSelected(SelectionEvent e) {
			TreeItem ti=(TreeItem) e.item;
			if("修改密码".equals(ti.getText())) {
				stackLayout.topControl = composite_4;
				composite_6.setVisible(false);
				composite_7.setVisible(false);
				composite_8.setVisible(false);
				composite_9.setVisible(false);
				
			}else if("个人信息查看".equals(ti.getText())) {
				stackLayout.topControl = composite_6;
				composite_4.setVisible(false);
				composite_7.setVisible(false);
				composite_8.setVisible(false);
				composite_9.setVisible(false);
				
			}else if("房源信息".equals(ti.getText())) {
				stackLayout.topControl = composite_7;
				composite_4.setVisible(false);
				composite_6.setVisible(false);
				composite_8.setVisible(false);
				composite_9.setVisible(false);
				
			}else if("收费信息".equals(ti.getText())) {
				stackLayout.topControl = composite_8;
				composite_4.setVisible(false);
				composite_6.setVisible(false);
				composite_7.setVisible(false);
				composite_9.setVisible(false);
				
			}else if("意见反馈".equals(ti.getText())) {
				stackLayout.topControl = composite_9;
				composite_4.setVisible(false);
				composite_6.setVisible(false);
				composite_7.setVisible(false);
				composite_8.setVisible(false);
			}
		}
	});
}
}
