package com.minmall.domain;

public class CartVO {

	private int    cart_code;
    private int    pro_num;
    private String mbs_id;
    private int    cart_amount;
	
    
    public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public String getMbs_id() {
		return mbs_id;
	}
	public void setMbs_id(String mbs_id) {
		this.mbs_id = mbs_id;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	
	
	@Override
	public String toString() {
		return "CartVO [cart_code=" + cart_code + ", pro_num=" + pro_num + ", mbs_id=" + mbs_id + ", cart_amount="
				+ cart_amount + "]";
	}
	
}
