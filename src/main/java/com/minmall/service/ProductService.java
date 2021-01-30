package com.minmall.service;

import java.util.List;
import java.util.Map;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.util.Criteria;
import com.minmall.util.SearchCriteria;

public interface ProductService {

	//1차 카테고리 출력
	public List<CategoryVO> mainCGList() throws Exception;
		
	//2차 카테고리 출력
	public List<CategoryVO> subCGList(String cate_code) throws Exception;
		
	//카테고리 코드에 해당하는 카테고리 명
	public String getCGName(String cate_code) throws Exception;	
	
	//상품 전체리스트(홈)
	public List<ProductVO> productList(Criteria cri) throws Exception;
		
	//상품 전체개수
	public int productCountAll() throws Exception;
	
	//해당 카테고리에 해당하는 상품리스트(페이지에 맞춰서)
	public List<ProductVO> productListCG(Map map) throws Exception;
	
	//해당 카테고리의 상품 개수
	public int productCount(String cate_code) throws Exception;
	
	//해당 검색조건에 해당하는 상품리스트(페이지에 맞춰서)
	public List<ProductVO> productListSearch(SearchCriteria cri) throws Exception;
		
	//해당 검색조건에 해당하는 상품 개수
	public int productCountSearch(String keyword) throws Exception;
		
	//상품 상세 정보 읽기
	public ProductVO readProduct(int pro_num) throws Exception;
	
}
