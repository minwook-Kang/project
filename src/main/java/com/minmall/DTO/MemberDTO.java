package com.minmall.DTO;

import java.util.Date;

public class MemberDTO {

	private String  mbs_id;
	private String  mbs_pw;
	private String  mbs_nick;
	private String  mbs_name;
	private String  mbs_point;
	private Date    mbs_lastlogin;
	
	
	public String getMbs_id() {
		return mbs_id;
	}
	public void setMbs_id(String mbs_id) {
		this.mbs_id = mbs_id;
	}
	public String getMbs_pw() {
		return mbs_pw;
	}
	public void setMbs_pw(String mbs_pw) {
		this.mbs_pw = mbs_pw;
	}
	public String getMbs_nick() {
		return mbs_nick;
	}
	public void setMbs_nick(String mbs_nick) {
		this.mbs_nick = mbs_nick;
	}
	public String getMbs_name() {
		return mbs_name;
	}
	public void setMbs_name(String mbs_name) {
		this.mbs_name = mbs_name;
	}
	public String getMbs_point() {
		return mbs_point;
	}
	public void setMbs_point(String mbs_point) {
		this.mbs_point = mbs_point;
	}
	public Date getMbs_lastlogin() {
		return mbs_lastlogin;
	}
	public void setMbs_lastlogin(Date mbs_lastlogin) {
		this.mbs_lastlogin = mbs_lastlogin;
	}
	
	
	@Override
	public String toString() {
		return "LoginDTO [mbs_id=" + mbs_id + ", mbs_pw=" + mbs_pw + ", mbs_nick=" + mbs_nick + ", mbs_name=" + mbs_name
				+ ", mbs_point=" + mbs_point + ", mbs_lastlogin=" + mbs_lastlogin + "]";
	}
	
	
	
}
