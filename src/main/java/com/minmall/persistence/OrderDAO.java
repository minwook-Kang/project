package com.minmall.persistence;

import java.util.List;

import com.minmall.domain.OrderDetailVO;
import com.minmall.domain.OrderListVO;
import com.minmall.domain.OrderReadDetailVO;
import com.minmall.domain.OrderVO;

public interface OrderDAO {

	//주문코드 시퀀스 가져오기
	public int readOrderCode() throws Exception;
	
	//주문목록
	public List<OrderListVO> orderList(String mbs_id) throws Exception;
	
	//주문내역 추가
	public void addOrderDetail(OrderDetailVO vo) throws Exception;
	
	//주문정보 추가
	public void addOrder(OrderVO vo) throws Exception;
	
	//주문 상세 정보
	public List<OrderReadDetailVO> readOrder(int ord_code) throws Exception;	
	
	//주문자 정보
	public OrderVO getOrderMbs(int ord_code) throws Exception;
	
	
}
