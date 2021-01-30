package com.minmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minmall.domain.OrderDetailVO;
import com.minmall.domain.OrderDetailVOList;
import com.minmall.domain.OrderListVO;
import com.minmall.domain.OrderReadDetailVO;
import com.minmall.domain.OrderVO;
import com.minmall.persistence.OrderDAO;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO dao;
	
	@Autowired
	private CartService cartService;

	//주문목록
	@Override
	public List<OrderListVO> orderList(String mbs_id) throws Exception {
		return dao.orderList(mbs_id);
	}
	
	//상품구매(상품상세/바로구매)
	@Transactional
	@Override
	public void addOrder(OrderVO order, OrderDetailVOList orderDetailList) throws Exception {
		
		//주문코드 가져오기
		int ord_code = dao.readOrderCode();
		
		//주문 정보 추가
		order.setOrd_code(ord_code);
		dao.addOrder(order);
		
		//주문 내역 추가
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOrd_code(ord_code);
			dao.addOrderDetail(orderDetail);
		}
	}

	//상품구매(장바구니)
	@Transactional
	@Override
	public void addOrderCart(OrderVO order, OrderDetailVOList orderDetailList, String mbs_id) throws Exception {
		
		int ord_code = dao.readOrderCode();
		
		order.setOrd_code(ord_code);
		dao.addOrder(order);
		
		List<OrderDetailVO> list = orderDetailList.getOrderDetailList();
		for(int i=0; i<list.size(); i++) {
			
			OrderDetailVO orderDetail = list.get(i);
			orderDetail.setOrd_code(ord_code);
			dao.addOrderDetail(orderDetail);
			
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("mbs_id", mbs_id);
			map.put("pro_num", orderDetail.getPro_num());
			cartService.deleteCartOrder(map);
					
		}
	}

	//주문 상세 정보
	@Override
	public List<OrderReadDetailVO> readOrder(int ord_code) throws Exception {
		return dao.readOrder(ord_code);
	}

	//주문자 정보
	@Override
	public OrderVO getOrderMbs(int ord_code) throws Exception {
		return dao.getOrderMbs(ord_code);
	}
	
	
	
}
