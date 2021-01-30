package com.minmall.domain;

import java.util.Date;

public class OrderListVO {

	private String pro_img;
	private String pro_name;
	private int    ord_code;
	private int    pro_num;
	private int    ord_amount;
	private int    ord_price;
	private Date   ord_regdate;
	private String ord_delivery;
	
	public String getPro_img() {
		return pro_img;
	}
	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getOrd_code() {
		return ord_code;
	}
	public void setOrd_code(int ord_code) {
		this.ord_code = ord_code;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public int getOrd_amount() {
		return ord_amount;
	}
	public void setOrd_amount(int ord_amount) {
		this.ord_amount = ord_amount;
	}
	public int getOrd_price() {
		return ord_price;
	}
	public void setOrd_price(int ord_price) {
		this.ord_price = ord_price;
	}
	public Date getOrd_regdate() {
		return ord_regdate;
	}
	public void setOrd_regdate(Date ord_regdate) {
		this.ord_regdate = ord_regdate;
	}
	public String getOrd_delivery() {
		return ord_delivery;
	}
	public void setOrd_delivery(String ord_delivery) {
		this.ord_delivery = ord_delivery;
	}
	
	
	@Override
	public String toString() {
		return "OrderListVO [pro_img=" + pro_img + ", pro_name=" + pro_name + ", ord_code=" + ord_code + ", pro_num="
				+ pro_num + ", ord_amount=" + ord_amount + ", ord_price=" + ord_price + ", ord_regdate=" + ord_regdate
				+ ", ord_delivery=" + ord_delivery + "]";
	}
	
	
	
}
