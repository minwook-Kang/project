package com.minmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.ReviewVO;
import com.minmall.service.ReviewService;
import com.minmall.util.Criteria;
import com.minmall.util.PageMaker;

@Controller
@RequestMapping("/review/*")
public class ReviewController {

	private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

	@Inject
	private ReviewService service;
	
	//상품 후기 쓰기
	@ResponseBody
	@RequestMapping(value = "write", method = RequestMethod.POST)
	public ResponseEntity<String> write(ReviewVO vo,HttpSession session) throws Exception {
		
		logger.info("======================write() 실행중...");
		logger.info("=================================vo : " + vo.toString());
		
		ResponseEntity<String> entity = null;
		try {
			MemberDTO dto = (MemberDTO)session.getAttribute("user");
		
		
			if(dto == null) {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}else {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);	
				service.writeReview(vo, dto.getMbs_id());
			}
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>("FAIL", HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//상품후기 리스트(페이지포함)
	@RequestMapping(value = "{pro_num}/{page}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> listReview(@PathVariable("pro_num") Integer pro_num,
														  @PathVariable("page") Integer page) {
		
		logger.info("======================listReview() 실행중...");
		
		ResponseEntity<Map<String, Object>> entity = null;
		
		try {
			Criteria cri = new Criteria();
			cri.setPage(page);
			
			PageMaker pageMaker = new PageMaker();
			pageMaker.setCri(cri);
			
			Map<String, Object> map = new HashMap<String, Object>();
			List<ReviewVO> list = service.listReview(pro_num, cri);
			
			map.put("list", list);
			
			int replyCount = service.countReview(pro_num);
			pageMaker.setTotalCount(replyCount);
			
			map.put("pageMaker", pageMaker);
			
			entity = new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<Map<String,Object>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//상품 후기 삭제
	@ResponseBody
	@RequestMapping(value = "{rew_num}", method = RequestMethod.DELETE)
	public ResponseEntity<String> delete(@PathVariable("rew_num") int rew_num) {
		
		logger.info("======================deldete() 실행중...");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteReview(rew_num);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		} catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;
	}
	
	
	//상품 후기 수정
	@ResponseBody
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> modify(ReviewVO vo){
		
		logger.info("===========modify() 실행중...");
		logger.info("======================vo : " + vo.toString());
		
		ResponseEntity<String> entity = null;
		
		try {
			service.modifyReview(vo);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			e.printStackTrace();
		}
		return entity;
	}
	
}
