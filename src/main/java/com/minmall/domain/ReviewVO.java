package com.minmall.domain;

import java.util.Date;

public class ReviewVO {

	private int    rew_num;
    private String mbs_id;
    private int    pro_num;
    private String rew_contents;
    private int    rew_score;
    private Date   rew_regdate;
	
    
    public int getRew_num() {
		return rew_num;
	}
	public void setRew_num(int rew_num) {
		this.rew_num = rew_num;
	}
	public String getMbs_id() {
		return mbs_id;
	}
	public void setMbs_id(String mbs_id) {
		this.mbs_id = mbs_id;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public String getRew_contents() {
		return rew_contents;
	}
	public void setRew_contents(String rew_contents) {
		this.rew_contents = rew_contents;
	}
	public int getRew_score() {
		return rew_score;
	}
	public void setRew_score(int rew_score) {
		this.rew_score = rew_score;
	}
	public Date getRew_regdate() {
		return rew_regdate;
	}
	public void setRew_regdate(Date rew_regdate) {
		this.rew_regdate = rew_regdate;
	}
	
	
	@Override
	public String toString() {
		return "ReviewVO [rew_num=" + rew_num + ", mbs_id=" + mbs_id + ", pro_num=" + pro_num + ", rew_contents="
				+ rew_contents + ", rew_score=" + rew_score + ", rew_regdate=" + rew_regdate + "]";
	}
    
    
	
}
