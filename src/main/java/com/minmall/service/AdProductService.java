package com.minmall.service;

import java.util.List;
import java.util.Map;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.util.SearchCriteria;

public interface AdProductService {

	//1차 카테고리
	public List<CategoryVO> mainCGList() throws Exception;
	
	//1차 카테고리에 따른 2차 카테고리
	public List<CategoryVO> subCGList(String cate_code) throws Exception;

	//상품 등록
	public void insertProduct(ProductVO vo) throws Exception;
	
	//상품 리스트
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception;
		
	//검색 조건에 해당하는 상품 개수
	public int searchListCount(SearchCriteria cri) throws Exception;

	//상품 정보
	public ProductVO readProduct(int pro_num) throws Exception;
		
	//상품 수정
	public void editProduct(ProductVO vo) throws Exception;
		
	//상품 삭제
	public void deleteProduct(int pro_num) throws Exception;
		
	//선택한 상품 수정
	public void editChecked(Map<String, Object> map) throws Exception;
	
}
