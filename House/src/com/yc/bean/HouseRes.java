package com.yc.bean;

public class HouseRes {
	private int hid;
	private String location;//名称
	private int housesize;//面积
	private int monthmoney;//月租金
	private int wuye;//物业
	private int warehouse;//仓库
	private int yajin;//押金
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getHousesize() {
		return housesize;
	}
	public void setHousesize(int housesize) {
		this.housesize = housesize;
	}
	public int getMonthmoney() {
		return monthmoney;
	}
	public void setMonthmoney(int monthmoney) {
		this.monthmoney = monthmoney;
	}
	public int getWuye() {
		return wuye;
	}
	public void setWuye(int wuye) {
		this.wuye = wuye;
	}
	public int getWarehouse() {
		return warehouse;
	}
	public void setWarehouse(int warehouse) {
		this.warehouse = warehouse;
	}
	public int getYajin() {
		return yajin;
	}
	public void setYajin(int yajin) {
		this.yajin = yajin;
	}
	@Override
	public String toString() {
		return "HouseRes [hid=" + hid + ", location=" + location + ", housesize=" + housesize + ", monthmoney="
				+ monthmoney + ", wuye=" + wuye + ", warehouse=" + warehouse + ", yajin=" + yajin + "]";
	}
	public HouseRes() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HouseRes(int hid, String location, int housesize, int monthmoney, int wuye, int warehouse, int yajin) {
		super();
		this.hid = hid;
		this.location = location;
		this.housesize = housesize;
		this.monthmoney = monthmoney;
		this.wuye = wuye;
		this.warehouse = warehouse;
		this.yajin = yajin;
	}
	
	

}
