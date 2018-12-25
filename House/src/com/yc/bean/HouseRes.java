package com.yc.bean;

public class HouseRes {
	private String hid;
	private String unit;//单位
	private String hname;//名称
	private String area;//面积
	private Long monthMoney;//月租金
	private Long hproperty;//物业
	private Long hware;//仓库
	private Long rcash;//押金
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
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Long getMonthMoney() {
		return monthMoney;
	}
	public void setMonthMoney(Long monthMoney) {
		this.monthMoney = monthMoney;
	}
	public Long getHproperty() {
		return hproperty;
	}
	public void setHproperty(Long hproperty) {
		this.hproperty = hproperty;
	}
	public Long getHware() {
		return hware;
	}
	public void setHware(Long hware) {
		this.hware = hware;
	}
	public Long getRcash() {
		return rcash;
	}
	public void setRcash(Long rcash) {
		this.rcash = rcash;
	}
	
	public HouseRes() {
		super();
	}
	
	@Override
	public String toString() {
		return "HouseRes [unit=" + unit + ", hname=" + hname + ", area=" + area + ", monthMoney=" + monthMoney
				+ ", hproperty=" + hproperty + ", hware=" + hware + ", rcash=" + rcash + "]";
	}
	public HouseRes(String unit, String hname, String area, Long monthMoney, Long hproperty, Long hware, Long rcash) {
		super();
		this.unit = unit;
		this.hname = hname;
		this.area = area;
		this.monthMoney = monthMoney;
		this.hproperty = hproperty;
		this.hware = hware;
		this.rcash = rcash;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	

}
