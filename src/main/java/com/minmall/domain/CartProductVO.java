package com.minmall.domain;

//장바구니에 담긴 상품목록을 위한 VO 객체
public class CartProductVO {

	private int    cart_code;
	private int    cart_amount;
	private int    pro_num;
	private String pro_name;
	private int    pro_price;
	private int    pro_discount;
	private String pro_img;
	
	
	public int getCart_code() {
		return cart_code;
	}
	public void setCart_code(int cart_code) {
		this.cart_code = cart_code;
	}
	public int getCart_amount() {
		return cart_amount;
	}
	public void setCart_amount(int cart_amount) {
		this.cart_amount = cart_amount;
	}
	public int getPro_num() {
		return pro_num;
	}
	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}
	public String getPro_name() {
		return pro_name;
	}
	public void setPro_name(String pro_name) {
		this.pro_name = pro_name;
	}
	public int getPro_price() {
		return pro_price;
	}
	public void setPro_price(int pro_price) {
		this.pro_price = pro_price;
	}
	public int getPro_discount() {
		return pro_discount;
	}
	public void setPro_discount(int pro_discount) {
		this.pro_discount = pro_discount;
	}
	public String getPro_img() {
		return pro_img;
	}
	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}
	
	
	@Override
	public String toString() {
		return "CartProductVO [cart_code=" + cart_code + ", cart_amount=" + cart_amount + ", pro_num=" + pro_num
				+ ", pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_discount=" + pro_discount
				+ ", pro_img=" + pro_img + "]";
	}
	
	
}
