package com.minmall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minmall.domain.OrderDetailVO;
import com.minmall.domain.OrderListVO;
import com.minmall.domain.OrderReadDetailVO;
import com.minmall.domain.OrderVO;

@Repository
public class OrderDAOImpl implements OrderDAO {

	public final static String NS = "com.minmall.mappers.OrderMapper";
	
	@Autowired
	private SqlSession session;

	//주문코드 시퀀스 가져오기
	@Override
	public int readOrderCode() throws Exception {
		return session.selectOne(NS + ".readOrderCode");
	}

	//주문목록
	@Override
	public List<OrderListVO> orderList(String mbs_id) throws Exception {
		return session.selectList(NS + ".orderList", mbs_id);
	}

	//주문내역 추가
	@Override
	public void addOrderDetail(OrderDetailVO vo) throws Exception {
		session.insert(NS + ".addOrderDetail", vo);
	}

	//주문정보 추가
	@Override
	public void addOrder(OrderVO vo) throws Exception {
		session.insert(NS + ".addOrder", vo);
	}
	
	//주문 상세 정보
	@Override
	public List<OrderReadDetailVO> readOrder(int ord_code) throws Exception {
		return session.selectList(NS + ".readOrder", ord_code);
	}
	
	//주문자 정보
	@Override
	public OrderVO getOrderMbs(int ord_code) throws Exception {
		return session.selectOne(NS + ".getOrderMbs", ord_code);
	}


	
}
