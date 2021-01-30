package com.minmall.domain;

public class CategoryVO {

	private String cate_code;            
    private String cate_prtcode;
    private String cate_name;
	
    
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
	public String getCate_name() {
		return cate_name;
	}
	public void setCate_name(String cate_name) {
		this.cate_name = cate_name;
	}
	
	
	@Override
	public String toString() {
		return "CategoryVO [cate_code=" + cate_code + ", cate_prtcode=" + cate_prtcode + ", cate_name=" + cate_name
				+ "]";
	}
    
    
	
}
