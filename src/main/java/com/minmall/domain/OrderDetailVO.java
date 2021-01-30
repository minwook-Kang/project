package com.minmall.domain;

public class OrderDetailVO {

	private int ord_code;
    private int pro_num;
    private int ord_amount;
    private int ord_price;
	
    
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
	
	
	@Override
	public String toString() {
		return "OrderDetailVO [ord_code=" + ord_code + ", pro_num=" + pro_num + ", ord_amount=" + ord_amount
				+ ", ord_price=" + ord_price + "]";
	}
	
    
    
}