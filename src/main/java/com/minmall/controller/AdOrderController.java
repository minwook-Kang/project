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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minmall.domain.OrderVO;
import com.minmall.service.AdOrderService;
import com.minmall.service.OrderService;
import com.minmall.util.PageMaker;
import com.minmall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/order/*")
public class AdOrderController {

	private static final Logger logger = LoggerFactory.getLogger(AdOrderController.class);
	
	@Inject
	private AdOrderService service;
	
	@Inject
	private OrderService orderService;
	
	//주문현황
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void orderList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info("===========adminList() 실행중...");
		
		List<OrderVO> list = service.orderListAdmin(cri);
		
		model.addAttribute("orderList", list);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		int count = service.searchListOrderCount(cri);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
		
	}
	
	//배송현황 수정
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public ResponseEntity<String> deliveryModify(int ord_code, String ord_delivery) {
		
		logger.info("===========modify() 실행중...");
		
		ResponseEntity<String> entity = null;
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("ord_code", ord_code);
		map.put("ord_delivery", ord_delivery);
		
		
		
		try {
			service.deliveryModify(map);
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//주문 상세 조회
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void orderRead(int ord_code, Model model) throws Exception {
		
		logger.info("=============readAdmin() 실행중...");
		
		model.addAttribute("orderList", orderService.readOrder(ord_code));
		model.addAttribute("order", orderService.getOrderMbs(ord_code));
	}
}
