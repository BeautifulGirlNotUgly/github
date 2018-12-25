package com.yc.bean;
/*
 * ×â»§
 */
public class Renter {
	private String rid;
	private String rname;
	private String rpwd;
	
	public String getRid() {
		return rid;
	}
	public void setRid(String rid) {
		this.rid = rid;
	}
	public String getRname() {
		return rname;
	}
	public void setRname(String rname) {
		this.rname = rname;
	}
	public String getRpwd() {
		return rpwd;
	}
	public void setRpwd(String rpwd) {
		this.rpwd = rpwd;
	}
	
	
	public Renter(String rid,String rname,String rpwd) {
		super();
		this.rid = rid;
		this.rname = rname;
		this.rpwd = rpwd;
	}
	@Override
	public String toString() {
		return "Renter [rid=" + rid + ", rname=" + rname + ", rpwd=" + rpwd + "]";
	}
	public Renter() {
		super();
		// TODO Auto-generated constructor stub
	}
}
