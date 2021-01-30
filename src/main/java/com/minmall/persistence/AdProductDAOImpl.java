package com.minmall.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.util.SearchCriteria;

@Repository
public class AdProductDAOImpl implements AdProductDAO {

	public final static String NS="com.minmall.mappers.AdProductMapper";
	
	@Autowired
	SqlSession session;
	
	//1차 카테고리
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return session.selectList(NS + ".mainCGList");
	}

	//1차 카테고리에 따른 2차 카테고리
	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		return session.selectList(NS + ".subCGList", cate_code);
	}
	
	//상품 등록
	@Override
	public void insertProduct(ProductVO vo) throws Exception {
		session.insert(NS + ".insertProduct", vo);
	}

	//상품 리스트
	@Override
	public List<ProductVO> searchListProduct(SearchCriteria cri) throws Exception {
		return session.selectList(NS + ".searchListProduct", cri);
	}

	//검색 조건에 맞는 상품 개수
	@Override
	public int searchListCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS + ".searchListCount", cri);
	}

	//상품 정보
	@Override
	public ProductVO readProduct(int pro_num) throws Exception {
		return session.selectOne(NS + ".readProduct", pro_num);
	}

	//상품 수정
	@Override
	public void editProduct(ProductVO vo) throws Exception {
		session.update(NS + ".editProduct", vo);
	}

	//상품 삭제
	@Override
	public void deleteProduct(int pro_num) throws Exception {
		session.delete(NS +".deleteProduct", pro_num);
	}

	//선택한 상품 수정
	@Override
	public void editChecked(Map<String, Object> map) throws Exception {
		session.update(NS + ".editChecked", map);
	}
	
}
