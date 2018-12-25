package com.yc.bean;

public class Manager {
	private  String mname;
	private  int mid;
	private  String mpassword;
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMid() {
		return mid;
	}
	public void setMid(int mid) {
		this.mid = mid;
	}
	public String getMpassword() {
		return mpassword;
	}
	public void setMpassword(String mpassword) {
		this.mpassword = mpassword;
	}
	public Manager(String mname, int mid, String mpassword) {
		super();
		this.mname = mname;
		this.mid = mid;
		this.mpassword = mpassword;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Manager [mname=" + mname + ", mid=" + mid + ", mpassword=" + mpassword + "]";
	}
	
}
