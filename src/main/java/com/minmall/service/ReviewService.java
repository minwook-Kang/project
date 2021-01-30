package com.minmall.service;

import java.util.List;

import com.minmall.domain.ReviewVO;
import com.minmall.util.Criteria;

public interface ReviewService {

	//상품후기 총 개수
	public int countReview(int pro_num) throws Exception;
	
	//상품후기 쓰기
	public void writeReview(ReviewVO vo, String mbs_id) throws Exception;
	
	//상품후기 리스트(페이지포함)
	public List<ReviewVO> listReview(int pro_num, Criteria cri) throws Exception;
	
	//상품후기 삭제
	public void deleteReview(int rew_num) throws Exception;
	
	//상품후기 수정
	public void modifyReview(ReviewVO vo) throws Exception;
	
}
