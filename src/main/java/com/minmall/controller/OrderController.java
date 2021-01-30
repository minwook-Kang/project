package com.minmall.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.OrderDetailVOList;
import com.minmall.domain.OrderListVO;
import com.minmall.domain.OrderVO;
import com.minmall.domain.ProductVO;
import com.minmall.service.MemberService;
import com.minmall.service.OrderService;
import com.minmall.service.ProductService;

@Controller
@RequestMapping(value = "/order/*")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	
	@Inject
	private OrderService service;
	
	@Inject
	private ProductService productService;
	
	@Inject
	private MemberService memberService;
	
	//주문목록
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void list(Model model, HttpSession session) throws Exception {
		
		logger.info("===========list() 실행중...");
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		List<OrderListVO> list = service.orderList(dto.getMbs_id());
		
		model.addAttribute("orderList", list);
	}
	
	//상품 상세 -> 바로구매
	@RequestMapping(value = "buy", method = RequestMethod.GET)
	public void buy(@RequestParam int ord_amount, @RequestParam int pro_num,
					Model model, HttpSession session) throws Exception {
		
		logger.info("===========buy() 실행중...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(pro_num));
		amountList.add(ord_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readMem(dto.getMbs_id()));
	}
	
	//구매하기 -> 결제
	@RequestMapping(value = "order", method = RequestMethod.POST)
	public String order(OrderVO order, OrderDetailVOList orderDetailList,
						Model model, HttpSession session) throws Exception {
		
		logger.info("===========order() 실행중...");
		logger.info("=====================OrderVO(주문자 정보) : " + order.toString());
		logger.info("=======================OrderDetail(주문 내역) : " + orderDetailList.toString());
		
		service.addOrder(order, orderDetailList);
		
		return "/order/orderComplete";
	}
	
	
	//장바구니 -> 구매(단일 상품)
	@RequestMapping(value = "buyFromCart", method = RequestMethod.GET)
	public String buyFromCart(@RequestParam int ord_amount, @RequestParam int pro_num,
							  Model model, HttpSession session) throws Exception {
		
		logger.info("===========buyFromCart() 실행중...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		productList.add(productService.readProduct(pro_num));
		amountList.add(ord_amount);
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readMem(dto.getMbs_id()));
		
		return "/order/buyFromCart";
		
	}
	
	//장바구니 -> 체크상품 구매
	@RequestMapping(value = "buyFromCart", method = RequestMethod.POST)
	public void buyFromCartChecked(@RequestParam("check") List<Integer> checkList,
								   @RequestParam("pro_num") List<Integer> pro_numList,
								   @RequestParam("cart_amount") List<Integer> cart_amountList,
								   @RequestParam("cart_code") List<Integer> cart_codeList,
								   Model model, HttpSession session) throws Exception {
		
		logger.info("======================buyFromCartChecked() 실행중...");
		
		List<ProductVO> productList = new ArrayList<ProductVO>();
		List<Integer> amountList = new ArrayList<Integer>();
		
		for(int i=0; i<cart_codeList.size(); i++) {
			for(int j=0; j<checkList.size(); j++) {
				if((int)cart_codeList.get(i) == (int)checkList.get(j)) {
					productList.add(productService.readProduct((int)pro_numList.get(i)));
					amountList.add(cart_amountList.get(i));
					continue;
				}else {
					continue;
				}
			}
		}
		
		model.addAttribute("productList", productList);
		model.addAttribute("amountList", amountList);
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		model.addAttribute("user", memberService.readMem(dto.getMbs_id()));
		
	}
	
	//장바구니 구매 -> 결제
	@RequestMapping(value = "orderFromCart", method = RequestMethod.POST)
	public String orderFromCart(OrderVO order, OrderDetailVOList orderDetailList,
								Model model, HttpSession session) throws Exception {
		
		logger.info("===========orderFromCart() 실행중...");
		logger.info("======================OrderVO(주문자 정보) : " + order.toString());
		logger.info("======================OrderDetail(주문 내역) : " + orderDetailList.toString());
		
		MemberDTO dto = (MemberDTO)session.getAttribute("user");
		service.addOrderCart(order, orderDetailList, dto.getMbs_id());
		
		return "/order/orderComplete";
	}
	
	//주문 상세 조회
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void read(int ord_code, Model model, HttpSession session) throws Exception {
		
		logger.info("===========read() execute...");
		
		model.addAttribute("orderList", service.readOrder(ord_code));
		model.addAttribute("order",service.getOrderMbs(ord_code));
	}
	
	
}
