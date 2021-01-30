package com.minmall.persistence;

import java.util.List;
import java.util.Map;

import com.minmall.domain.OrderVO;
import com.minmall.util.SearchCriteria;

public interface AdOrderDAO {

	//주문현황
	public List<OrderVO> orderListAdmin(SearchCriteria cri) throws Exception;
	
	//검색 조건에 해당하는 주문개수
	public int searchListOrderCount(SearchCriteria cri) throws Exception;
	
	//배송현황 수정
	public void deliveryModify(Map map) throws Exception;
	
}
