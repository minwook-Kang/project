package com.minmall.domain;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class ProductVO {

	private int    pro_num;
    private String cate_code;
    private String cate_prtcode;
    private String pro_name;
    private int    pro_price;
    private int    pro_discount;
    private String pro_company;
    private String pro_contents;
    private String pro_img;
    private int    pro_amount;
    private String pro_buy;
    private Date   pro_date;
    private Date   pro_update;
	
    // 업로드 파일
 	private MultipartFile file1;

	
 	public int getPro_num() {
		return pro_num;
	}

	public void setPro_num(int pro_num) {
		this.pro_num = pro_num;
	}

	public String getCate_code() {
		return cate_code;
	}

	public void setCate_code(String cate_code) {
		this.cate_code = cate_code;
	}

	public String getCate_prtcode() {
		return cate_prtcode;
	}

	public void setCate_prtcode(String cate_prtcode) {
		this.cate_prtcode = cate_prtcode;
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

	public String getPro_company() {
		return pro_company;
	}

	public void setPro_company(String pro_company) {
		this.pro_company = pro_company;
	}

	public String getPro_contents() {
		return pro_contents;
	}

	public void setPro_contents(String pro_contents) {
		this.pro_contents = pro_contents;
	}

	public String getPro_img() {
		return pro_img;
	}

	public void setPro_img(String pro_img) {
		this.pro_img = pro_img;
	}

	public int getPro_amount() {
		return pro_amount;
	}

	public void setPro_amount(int pro_amount) {
		this.pro_amount = pro_amount;
	}

	public String getPro_buy() {
		return pro_buy;
	}

	public void setPro_buy(String pro_buy) {
		this.pro_buy = pro_buy;
	}

	public Date getPro_date() {
		return pro_date;
	}

	public void setPro_date(Date pro_date) {
		this.pro_date = pro_date;
	}

	public Date getPro_update() {
		return pro_update;
	}

	public void setPro_update(Date pro_update) {
		this.pro_update = pro_update;
	}

	public MultipartFile getFile1() {
		return file1;
	}

	public void setFile1(MultipartFile file1) {
		this.file1 = file1;
	}

	
	@Override
	public String toString() {
		return "ProductVO [pro_num=" + pro_num + ", cate_code=" + cate_code + ", cate_prtcode=" + cate_prtcode
				+ ", pro_name=" + pro_name + ", pro_price=" + pro_price + ", pro_discount=" + pro_discount
				+ ", pro_company=" + pro_company + ", pro_contents=" + pro_contents + ", pro_img=" + pro_img
				+ ", pro_amount=" + pro_amount + ", pro_buy=" + pro_buy + ", pro_date=" + pro_date + ", pro_update="
				+ pro_update + ", file1=" + file1 + "]";
	}
 
	
 	
}    