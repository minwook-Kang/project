package com.minmall.controller;

import java.text.DateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.minmall.domain.ProductVO;
import com.minmall.service.ProductService;
import com.minmall.util.Criteria;
import com.minmall.util.PageMaker;

@Controller
public class Maincontroller {

	private static final Logger logger = LoggerFactory.getLogger(Maincontroller.class);
	
	@Inject
	ProductService service;

	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, @ModelAttribute("cri") Criteria cri, Model model) throws Exception {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
		
		List<ProductVO> list = service.productList(cri);
		model.addAttribute("productListAll", list);
		
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCountAll();
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
		
		return "index";
	}	
}
