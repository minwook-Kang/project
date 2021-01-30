package com.minmall.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minmall.domain.OrderVO;
import com.minmall.persistence.AdOrderDAO;
import com.minmall.util.SearchCriteria;

@Service
public class AdOrderServiceImpl implements AdOrderService {

	@Autowired
	private AdOrderDAO dao;
	
	//주문현황
	@Override
	public List<OrderVO> orderListAdmin(SearchCriteria cri) throws Exception {
		return dao.orderListAdmin(cri);
	}

	//배송현황 수정
	@Override
	public void deliveryModify(Map map) throws Exception {
		dao.deliveryModify(map);
	}
	
	//검색 조건에 맞는 주문 개수
	@Override
	public int searchListOrderCount(SearchCriteria cri) throws Exception {
		return dao.searchListOrderCount(cri);
	}

}
