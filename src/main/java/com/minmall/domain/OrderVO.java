package com.minmall.domain;

import java.util.Date;

public class OrderVO {

	private int    ord_code;
    private String mbs_id;
    private String ord_name;
    private String ord_zipcode;
    private String ord_addr;
    private String ord_deaddr;
    private String ord_phone;
    private int    ord_totalprice;
    private Date   ord_regdate;
	private String ord_delivery;
    
    public int getOrd_code() {
		return ord_code;
	}
	public void setOrd_code(int ord_code) {
		this.ord_code = ord_code;
	}
	public String getMbs_id() {
		return mbs_id;
	}
	public void setMbs_id(String mbs_id) {
		this.mbs_id = mbs_id;
	}
	public String getOrd_name() {
		return ord_name;
	}
	public void setOrd_name(String ord_name) {
		this.ord_name = ord_name;
	}
	public String getOrd_zipcode() {
		return ord_zipcode;
	}
	public void setOrd_zipcode(String ord_zipcode) {
		this.ord_zipcode = ord_zipcode;
	}
	public String getOrd_addr() {
		return ord_addr;
	}
	public void setOrd_addr(String ord_addr) {
		this.ord_addr = ord_addr;
	}
	public String getOrd_deaddr() {
		return ord_deaddr;
	}
	public void setOrd_deaddr(String ord_deaddr) {
		this.ord_deaddr = ord_deaddr;
	}
	public String getOrd_phone() {
		return ord_phone;
	}
	public void setOrd_phone(String ord_phone) {
		this.ord_phone = ord_phone;
	}
	public int getOrd_totalprice() {
		return ord_totalprice;
	}
	public void setOrd_totalprice(int ord_totalprice) {
		this.ord_totalprice = ord_totalprice;
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
		return "OrderVO [ord_code=" + ord_code + ", mbs_id=" + mbs_id + ", ord_name=" + ord_name + ", ord_zipcode="
				+ ord_zipcode + ", ord_addr=" + ord_addr + ", ord_deaddr=" + ord_deaddr + ", ord_phone=" + ord_phone
				+ ", ord_totalprice=" + ord_totalprice + ", ord_regdate=" + ord_regdate + ", ord_delivery="
				+ ord_delivery + "]";
	}

	
	
    
   
    
    
    
}
