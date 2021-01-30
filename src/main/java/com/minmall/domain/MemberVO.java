package com.minmall.domain;

import java.util.Date;

public class MemberVO {
	
	private String 	mbs_id;          
	private String  mbs_name;        
	private String	mbs_pw;		    
	private String	mbs_email;       
	private String	mbs_zipcode;          
	private String	mbs_addr;        
	private String	mbs_deaddr;      
	private String	mbs_phone;      
	private String	mbs_nick;       
	private String	mbs_accept_e;          
	private int 	mbs_point;             
	private Date	mbs_datesub;             
	private Date	mbs_update;          
	private Date	mbs_lastlogin;   
	private String  mbs_authcode;
	
	public String getMbs_id() {
		return mbs_id;
	}
	public void setMbs_id(String mbs_id) {
		this.mbs_id = mbs_id;
	}
	public String getMbs_name() {
		return mbs_name;
	}
	public void setMbs_name(String mbs_name) {
		this.mbs_name = mbs_name;
	}
	public String getMbs_email() {
		return mbs_email;
	}
	public void setMbs_email(String mbs_email) {
		this.mbs_email = mbs_email;
	}
	public String getMbs_pw() {
		return mbs_pw;
	}
	public void setMbs_pw(String mbs_pw) {
		this.mbs_pw = mbs_pw;
	}
	public String getMbs_zipcode() {
		return mbs_zipcode;
	}
	public void setMbs_zipcode(String mbs_zipcode) {
		this.mbs_zipcode = mbs_zipcode;
	}
	public String getMbs_addr() {
		return mbs_addr;
	}
	public void setMbs_addr(String mbs_addr) {
		this.mbs_addr = mbs_addr;
	}
	public String getMbs_deaddr() {
		return mbs_deaddr;
	}
	public void setMbs_deaddr(String mbs_deaddr) {
		this.mbs_deaddr = mbs_deaddr;
	}
	public String getMbs_phone() {
		return mbs_phone;
	}
	public void setMbs_phone(String mbs_phone) {
		this.mbs_phone = mbs_phone;
	}
	public String getMbs_nick() {
		return mbs_nick;
	}
	public void setMbs_nick(String mbs_nick) {
		this.mbs_nick = mbs_nick;
	}
	public String getmbs_accept_e() {
		return mbs_accept_e;
	}
	public void setmbs_accept_e(String mbs_accept_e) {
		this.mbs_accept_e = mbs_accept_e;
	}
	public int getMbs_point() {
		return mbs_point;
	}
	public void setMbs_point(int mbs_point) {
		this.mbs_point = mbs_point;
	}
	public Date getMbs_datesub() {
		return mbs_datesub;
	}
	public void setMbs_datesub(Date mbs_datesub) {
		this.mbs_datesub = mbs_datesub;
	}
	public Date getMbs_update() {
		return mbs_update;
	}
	public void setMbs_update(Date mbs_update) {
		this.mbs_update = mbs_update;
	}
	public Date getMbs_lastlogin() {
		return mbs_lastlogin;
	}
	public void setMbs_lastlogin(Date mbs_lastlogin) {
		this.mbs_lastlogin = mbs_lastlogin;
	}
	public String getMbs_Authcode() {
		return mbs_authcode;
	}
	public void setMbs_Authcode(String mbs_authcode) {
		this.mbs_authcode = mbs_authcode;
	}
	
	
	@Override
	public String toString() {
		return "MemberVO [mbs_id=" + mbs_id + ", mbs_name=" + mbs_name + ", mbs_email=" + mbs_email + ", mbs_pw=" + mbs_pw
				+ ", mbs_zipcode=" + mbs_zipcode + ", mbs_addr=" + mbs_addr + ", mbs_deaddr=" + mbs_deaddr + ", mbs_phone="
				+ mbs_phone + ", mbs_nick=" + mbs_nick + ", mbs_accept_e=" + mbs_accept_e + ", mbs_point=" + mbs_point
				+ ", mbs_datesub=" + mbs_datesub + ", mbs_updatedate=" + mbs_update + ", mbs_lastlogin=" + mbs_lastlogin
				+ ", mbs_authcode=" + mbs_authcode + "]";
	}	 
	
		
}
