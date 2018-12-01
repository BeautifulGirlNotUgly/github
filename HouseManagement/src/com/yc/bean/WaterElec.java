package com.yc.bean;

public class WaterElec {
	private Long elcquantity;
	private Long waterquantity;
	private Long eelcmoney;
	private Long waterwmoney;
	private String unit;
	public Long getElcquantity() {
		return elcquantity;
	}
	public void setElcquantity(Long elcquantity) {
		this.elcquantity = elcquantity;
	}
	public Long getWaterquantity() {
		return waterquantity;
	}
	public void setWaterquantity(Long waterquantity) {
		this.waterquantity = waterquantity;
	}
	public Long getEelcmoney() {
		return eelcmoney;
	}
	public void setEelcmoney(Long eelcmoney) {
		this.eelcmoney = eelcmoney;
	}
	public Long getWaterwmoney() {
		return waterwmoney;
	}
	public void setWaterwmoney(Long waterwmoney) {
		this.waterwmoney = waterwmoney;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public WaterElec(Long elcquantity, Long waterquantity, Long eelcmoney, Long waterwmoney, String unit) {
		super();
		this.elcquantity = elcquantity;
		this.waterquantity = waterquantity;
		this.eelcmoney = eelcmoney;
		this.waterwmoney = waterwmoney;
		this.unit = unit;
	}
	public WaterElec() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WaterElec [elcquantity=" + elcquantity + ", waterquantity=" + waterquantity + ", eelcmoney=" + eelcmoney
				+ ", waterwmoney=" + waterwmoney + ", unit=" + unit + "]";
	}

}
