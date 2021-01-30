package com.minmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.persistence.AdProductDAO;
import com.minmall.util.SearchCriteria;

@Service
public class AdProductServiceImpl implements AdProductService {

	@Autowired
	AdProductDAO dao;

	//1차 카테고리
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return dao.mainCGList();
	}

	//1차 카테고리에 따른 2차 카테고리
	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		return dao.subCGList(cate_code);
	}
	
	//상품 등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		dao.insertProduct(vo);
	}

	//상품 리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		return dao.searchListProduct(cri);
	}

	//검색 조건에 맞는 상품 개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		return dao.searchListCount(cri);
	}

	//상품 정보
	@Override
	public ProductVO readProduct(int pro_num) throws Exception {
		return dao.readProduct(pro_num);
	}

	//상품 수정
	@Override
	public void editProduct(ProductVO vo) throws Exception {
		dao.editProduct(vo);
	}

	//상품 삭제
	@Transactional
	@Override
	public void deleteProduct(int pro_num) throws Exception {
		dao.deleteProduct(pro_num);
	}
	
	//선택한 상품 수정
	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
		dao.editChecked(map);
	}

}
