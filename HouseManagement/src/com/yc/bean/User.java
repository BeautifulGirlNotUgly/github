package com.yc.bean;

public class User {
	private int uid;
	private String account;
	private String upwd;
	private String usex;
	private String uname;
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getUpwd() {
		return upwd;
	}
	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}
	public String getUsex() {
		return usex;
	}
	public void setUsex(String usex) {
		this.usex = usex;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public User(int uid, String account, String upwd, String usex, String uname) {
		super();
		this.uid = uid;
		this.account = account;
		this.upwd = upwd;
		this.usex = usex;
		this.uname = uname;
	}
	@Override
	public String toString() {
		return "User [uid=" + uid + ", account=" + account + ", upwd=" + upwd + ", usex=" + usex + ", uname=" + uname
				+ "]";
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
}
