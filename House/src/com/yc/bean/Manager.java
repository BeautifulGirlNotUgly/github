package com.yc.bean;

public class Manager {
	private  String aname;
	private  int aid;
	private  String apassword;
	public String getAname() {
		return aname;
	}
	public void setAname(String aname) {
		this.aname = aname;
	}
	public int getAid() {
		return aid;
	}
	public void setAid(int aid) {
		this.aid = aid;
	}
	public String getApassword() {
		return apassword;
	}
	public void setApassword(String apassword) {
		this.apassword = apassword;
	}
	@Override
	public String toString() {
		return "Manager [aname=" + aname + ", aid=" + aid + ", apassword=" + apassword + "]";
	}
	public Manager(String aname, int aid, String apassword) {
		super();
		this.aname = aname;
		this.aid = aid;
		this.apassword = apassword;
	}
	public Manager() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
