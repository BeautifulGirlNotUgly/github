package com.yc.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.StackLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseListener;
import java.util.function.Consumer;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Manager extends Shell {
	private StackLayout stackLayout = new StackLayout();

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Manager shell = new Manager(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Manager(Display display) {
		super(display, SWT.NO_TRIM);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm = new SashForm(this, SWT.VERTICAL);
		sashForm.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		
		Label lblNewLabel = new Label(composite, SWT.NONE);
		lblNewLabel.setFont(SWTResourceManager.getFont("楷体", 15, SWT.BOLD));
		lblNewLabel.setBounds(0, 10, 259, 26);
		lblNewLabel.setText("欢迎登陆小区物业管理系统");
		
		Label lblNewLabel_1 = new Label(composite, SWT.NONE);
		lblNewLabel_1.setOrientation(SWT.RIGHT_TO_LEFT);
		lblNewLabel_1.setImage(SWTResourceManager.getImage(Manager.class, "/images/btn_mini_normal.png"));
		lblNewLabel_1.setText("1");
		lblNewLabel_1.setBounds(526, 10, 61, 17);
		
		Label lblNewLabel_2 = new Label(composite, SWT.NONE);
		lblNewLabel_2.setText("2");
		lblNewLabel_2.setBounds(679, 10, 61, 17);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		SashForm sashForm_1 = new SashForm(composite_1, SWT.NONE);
		
		Composite composite_2 = new Composite(sashForm_1, SWT.NONE);
		composite_2.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Tree tree = new Tree(composite_2, SWT.BORDER);
		
		
		tree.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 10, SWT.NORMAL));
		
		TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem.setText("个人信息");
		
		TreeItem trtmNewTreeitem_4 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_4.setText("修改密码");
		
		TreeItem trtmNewTreeitem_5 = new TreeItem(trtmNewTreeitem, SWT.NONE);
		trtmNewTreeitem_5.setText("个人信息查看");
		trtmNewTreeitem.setExpanded(true);
		
		TreeItem trtmNewTreeitem_1 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_1.setText("房源信息");
		
		TreeItem trtmNewTreeitem_2 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_2.setText("收费信息");
		
		TreeItem trtmNewTreeitem_3 = new TreeItem(tree, SWT.NONE);
		trtmNewTreeitem_3.setText("意见反馈");
		
		Composite composite_3 = new Composite(sashForm_1, SWT.NONE);
		composite_3.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Composite composite_5 = new Composite(composite_3, SWT.NONE);
		composite_5.setLayout(stackLayout);
		
		Composite composite_4 = new Composite(composite_5, SWT.NONE);
		composite_4.setLayout(new FillLayout(SWT.HORIZONTAL));
		//设置顶层
		stackLayout.topControl = composite_4;
		
		Label lblNewLabel_3 = new Label(composite_4, SWT.NONE);
		lblNewLabel_3.setText("1");
		
		Composite composite_6 = new Composite(composite_5, SWT.NONE);
		composite_6.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_4 = new Label(composite_6, SWT.NONE);
		lblNewLabel_4.setText("2");
		
		Composite composite_7 = new Composite(composite_5, SWT.NONE);
		composite_7.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_5 = new Label(composite_7, SWT.NONE);
		lblNewLabel_5.setText("3");
		
		Composite composite_8 = new Composite(composite_5, SWT.NONE);
		composite_8.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		Label lblNewLabel_6 = new Label(composite_8, SWT.NONE);
		lblNewLabel_6.setText("4");
		
		Composite composite_9 = new Composite(composite_5, SWT.NONE);
		composite_9.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		sashForm_1.setWeights(new int[] {136, 601});
		sashForm.setWeights(new int[] {46, 432});
		createContents();
		
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

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		
		setText("SWT Application");
		setSize(740, 481);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	
}
