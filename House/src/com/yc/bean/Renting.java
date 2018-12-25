package com.yc.bean;

//租房资料
public class Renting {
	private String hid;
	private String hname;// 房源名称
	private String rid;
	private String rname;// 租户姓名
	private String rarea;// 房源面积
	private String rrent;// 月租

	public String getHid() {
		return hid;
	}

	public void setHid(String hid) {
		this.hid = hid;
	}

	public String getHname() {
		return hname;
	}

	public void setHname(String hname) {
		this.hname = hname;
	}

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

	public String getRarea() {
		return rarea;
	}

	public void setRarea(String rarea) {
		this.rarea = rarea;
	}

	public String getRrent() {
		return rrent;
	}

	public void setRrent(String rrent) {
		this.rrent = rrent;
	}
}
