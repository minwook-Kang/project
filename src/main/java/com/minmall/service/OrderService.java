package com.minmall.service;

import java.util.List;

import com.minmall.domain.OrderDetailVOList;
import com.minmall.domain.OrderListVO;
import com.minmall.domain.OrderReadDetailVO;
import com.minmall.domain.OrderVO;

public interface OrderService {

	//주문목록
	public List<OrderListVO> orderList(String mbs_id) throws Exception;
	
	//주문정보 추가(상품 상세/바로구매)
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception;
	
	//주문정보 추가(장바구니)
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mbs_id) throws Exception;
	
	//주문 상세 정보
	public List<OrderReadDetailVO> readOrder(int ord_code) throws Exception;
	
	//주문자 정보
	public OrderVO getOrderMbs(int ord_code) throws Exception;
	
}
