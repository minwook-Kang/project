package com.minmall.controller;

import javax.inject.Inject;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.minmall.service.ProductService;

@ControllerAdvice(basePackages = {"com.minmall.controller"})
public class GlobalControllerAdvice {

	@Inject
	private ProductService service;
	
	//1차 카테고리 메뉴 표시
	@ModelAttribute
	public void categoryList(Model model) throws Exception {
		model.addAttribute("userCategoryList", service.mainCGList());
	}
}
