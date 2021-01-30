package com.minmall.domain;

import java.util.Date;

public class BoardVO {

	private int    brd_num;
    private String mbs_id;
    private String brd_title;
    private String brd_contents;
    private Date   brd_regdate;
	
    
    public int getBrd_num() {
		return brd_num;
	}
	public void setBrd_num(int brd_num) {
		this.brd_num = brd_num;
	}
	public String getMbs_id() {
		return mbs_id;
	}
	public void setMbs_id(String mbs_id) {
		this.mbs_id = mbs_id;
	}
	public String getBrd_title() {
		return brd_title;
	}
	public void setBrd_title(String brd_title) {
		this.brd_title = brd_title;
	}
	public String getBrd_contents() {
		return brd_contents;
	}
	public void setBrd_contents(String brd_contents) {
		this.brd_contents = brd_contents;
	}
	public Date getBrd_regdate() {
		return brd_regdate;
	}
	public void setBrd_regdate(Date brd_regdate) {
		this.brd_regdate = brd_regdate;
	}
	
	
	@Override
	public String toString() {
		return "BoardVO [brd_num=" + brd_num + ", mbs_id=" + mbs_id + ", brd_title=" + brd_title + ", brd_contents="
				+ brd_contents + ", brd_regdate=" + brd_regdate + "]";
	}
    
	
	
	
}
