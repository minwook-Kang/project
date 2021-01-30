package com.minmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.CartVO;
import com.minmall.service.CartService;

@Controller
@RequestMapping(value = "/cart/*")
public class CartController {

	private static final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	@Inject
	private CartService service;
	
	//장바구니 목록
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void listCart(Model model, HttpSession session) throws Exception {
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		model.addAttribute("cartProductList", service.getCart(dto.getMbs_id()));
	}

	//장바구니 추가(list,index에서 추가)
	@ResponseBody
	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ResponseEntity<String> addCart(int pro_num, HttpSession session) {
		
		logger.info("===========addCart() excute...");
		logger.info("======================pro_num : " + pro_num);
		
		ResponseEntity<String> entity = null;
		
		
		try {
			MemberDTO dto = (MemberDTO) session.getAttribute("user");
			CartVO vo = new CartVO();
			
			if(dto == null) {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}else {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				vo.setMbs_id(dto.getMbs_id());
				vo.setPro_num(pro_num);
				vo.setCart_amount(1);
				service.addCart(vo);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
		
	}
	
	//장바구니 추가(read페이지 안에서 추가)
	@ResponseBody
	@RequestMapping(value = "addMany", method = RequestMethod.POST)
	public ResponseEntity<String> addManyCart(int pro_num, int pro_amount, HttpSession session) {
		
		logger.info("===========addManyCart() 실행중...");
		logger.info("======================pro_num : " + pro_num);
		
		ResponseEntity<String> entity = null;
		
		
		try {
			MemberDTO dto = (MemberDTO) session.getAttribute("user");
			CartVO vo = new CartVO();
			
			if(dto == null) {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}else {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
				vo.setMbs_id(dto.getMbs_id());
				vo.setPro_num(pro_num);
				vo.setCart_amount(pro_amount);
				service.addCart(vo);				
			}
			
		} catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//장바구니 삭제
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public ResponseEntity<String> deleteCart(int cart_code) throws Exception {
		
		logger.info("===========deleteCart() 실행중...");
		
		ResponseEntity<String> entity = null;
		
		try {
			service.deleteCart(cart_code);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//장바구니 선택 삭제
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteChecked(@RequestParam("checkArr[]") List<Integer> checkArr) throws Exception {
		
		logger.info("===========deleteChecked() 실행중...");
		
		 ResponseEntity<String> entity =null;
		 
		 try {
			 for(int cart_code : checkArr) {
				 service.deleteCart(cart_code);
			 }
			 entity = new ResponseEntity<String>(HttpStatus.OK);
			 
		 }catch(Exception e) {
			 e.printStackTrace();
			 entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		 }
		 return entity;
		 
	}
	
	//장바구니 수량 수정
	@ResponseBody
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> modifyCart(int cart_code, int cart_amount) {
		
		logger.info("===============modify() 실행중...");
		logger.info("======================cart_code : " + cart_code);
		logger.info("======================cart_amount : " + cart_amount);
		
		ResponseEntity<String> entity = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cart_code", cart_code);
		map.put("cart_amount", cart_amount);
		
		try {
			service.updateCart(map);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
}