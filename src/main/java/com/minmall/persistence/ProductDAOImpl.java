package com.minmall.persistence;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.util.Criteria;
import com.minmall.util.SearchCriteria;

@Repository
public class ProductDAOImpl implements ProductDAO {

	public final static String NS ="com.minmall.mappers.ProductMapper";
	
	@Inject
	private SqlSession session;
	
	//1차 카테고리 출력
	@Override
	public List<CategoryVO> mainCGList() throws Exception {
		return session.selectList(NS + ".mainCGList");
	}

	//2차 카테고리 출력
	@Override
	public List<CategoryVO> subCGList(String cate_code) throws Exception {
		return session.selectList(NS + ".subCGList", cate_code);
	}

	//카테고리 코드에 해당하는 카테고리명
	@Override
	public String getCGName(String cate_code) throws Exception {
		return session.selectOne(NS + ".getCGName", cate_code);
	}
	
	//상품 전체리스트(홈)
	@Override
	public List<ProductVO> productList(Criteria cri) throws Exception {
		return session.selectList(NS + ".productList", cri);
	}

	//상품 전체개수
	@Override
	public int productCountAll() throws Exception {
		return session.selectOne(NS + ".productCountAll");
	}

	//해당 카테고리에 해당하는 상품리스트(페이지에 맞춰서)
	@Override
	public List<ProductVO> productListCG(Map map) throws Exception {
		return session.selectList(NS + ".productListCG", map);
	}

	//카테고리에 해당하는 상품개수
	@Override
	public int productCount(String cate_code) throws Exception {
		return session.selectOne(NS + ".productCount", cate_code);
	}

	//해당 검색조건에 해당하는 상품리스트(페이지에 맞춰서)
	@Override
	public List<ProductVO> productListSearch(SearchCriteria cri) throws Exception {
		return session.selectList(NS + ".productListSearch", cri);
	}

	//해당 검색조건에 해당하는 상품 개수
	@Override
	public int productCountSearch(String keyword) throws Exception {
		return session.selectOne(NS + ".productSearchCount", keyword);
	}

	//상품 상세 정보 읽기
	@Override
	public ProductVO readProduct(int pro_num) throws Exception {
		return session.selectOne(NS + ".readProduct", pro_num);
	}



}
