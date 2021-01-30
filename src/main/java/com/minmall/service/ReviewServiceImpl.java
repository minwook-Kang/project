package com.minmall.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minmall.domain.ReviewVO;
import com.minmall.persistence.ReviewDAO;
import com.minmall.util.Criteria;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Autowired
	private ReviewDAO dao;
	
	//상품후기 총 개수
	@Override
	public int countReview(int pro_num) throws Exception {
		return dao.countReview(pro_num);
	}

	//상품후기 쓰기
	@Override
	public void writeReview(ReviewVO vo, String mbs_id) throws Exception {
		vo.setMbs_id(mbs_id);
		dao.writeReview(vo);
	}

	//상품후기 리스트(페이지포함)
	@Override
	public List<ReviewVO> listReview(int pro_num, Criteria cri) throws Exception {
		
		Map<String, Object> map = new HashMap<>();
		map.put("pro_num", pro_num);
		map.put("cri", cri);
		
		return dao.listReview(map);
	}

	//상품후기 삭제
	@Override
	public void deleteReview(int rew_num) throws Exception {
		dao.deleteReview(rew_num);
	}

	//상품후기 수정
	@Override
	public void modifyReview(ReviewVO vo) throws Exception {
		dao.modifyReview(vo);
	}

}
