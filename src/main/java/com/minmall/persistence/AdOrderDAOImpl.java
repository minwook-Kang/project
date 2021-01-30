package com.minmall.persistence;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minmall.domain.OrderVO;
import com.minmall.util.SearchCriteria;

@Repository
public class AdOrderDAOImpl implements AdOrderDAO {

	public final static String NS = "com.minmall.mappers.AdOrderMapper";
	
	@Autowired
	private SqlSession session;
	
	//주문현황
	@Override
	public List<OrderVO> orderListAdmin(SearchCriteria cri ) throws Exception {
		return session.selectList(NS + ".orderListAdmin", cri);
	}

	//검색 조건에 맞는 주문 개수
	@Override
	public int searchListOrderCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS + ".searchListOrderCount", cri);
	}
	
	//배송현황 수정
	@Override
	public void deliveryModify(Map map) throws Exception {
		session.update(NS + ".deliveryModify", map);
	}


	
	
}
