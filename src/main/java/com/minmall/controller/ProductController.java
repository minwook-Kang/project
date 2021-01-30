package com.minmall.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.service.ProductService;
import com.minmall.service.ReviewService;
import com.minmall.util.Criteria;
import com.minmall.util.FileUtils;
import com.minmall.util.PageMaker;
import com.minmall.util.SearchCriteria;

@Controller
@RequestMapping("/product/*")
public class ProductController {

	private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Inject
	ProductService service;
	
	@Inject
	ReviewService reviewService;
	
	@Resource(name="uploadPath")
	private String uploadPath;
	
	//1차 카테고리에 따른 2차 카테고리 출력
	@ResponseBody
	@RequestMapping(value = "subCGList/{cate_code}", method=RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCGList(@PathVariable("cate_code") String cate_code) {
		
		logger.info("======================subCGList() 실행중...");
		
		ResponseEntity<List<CategoryVO>> entity = null;
		
		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(cate_code), HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//카테고리에 해당하는 상품 리스트 출력
	@RequestMapping(value="list", method = RequestMethod.GET)
	public void list(@ModelAttribute("cri") Criteria cri,
					 @ModelAttribute("cate_code") String cate_code,
					 Model model) throws Exception {
		
		logger.info("===========list() 실행중...");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("cate_code", cate_code);
		map.put("rowStart", cri.getRowStart());
		map.put("rowEnd", cri.getRowEnd());
	
		List<ProductVO> list = service.productListCG(map);
		model.addAttribute("productList", list);
		model.addAttribute("cate_name", service.getCGName(cate_code));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		int count = service.productCount(cate_code);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	//검색조건에 해당하는 상품 리스트 출력
	@RequestMapping(value = "listSearch", method = RequestMethod.GET)
	public void listSearch(@ModelAttribute("scri") SearchCriteria scri,
						   Model model) throws Exception {
		
		logger.info("===========listSearch() 실행중...");
		logger.info("======================scri : " + scri.toString());
		
		List<ProductVO> list = service.productListSearch(scri);
		model.addAttribute("productList", list);
		
		PageMaker pm = new PageMaker();
		pm.setCri(scri);
		int count = service.productCountSearch(scri.getKeyword());
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	//파일 출력
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFile(uploadPath, fileName);
	}
	
	//카테고리 선택 -> 상품 상세정보 페이지 읽기
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void productRead(@ModelAttribute("cri") Criteria cri,
							@RequestParam("pro_num") int pro_num, Model model) throws Exception {
		
		logger.info("======================productRead() 실행중....");
		
		//선택한 상품 정보의 이미지를 썸네일에서 원본으로 변경
		ProductVO vo = service.readProduct(pro_num);
		vo.setPro_img(FileUtils.thumbToOriginName(vo.getPro_img()));
		
		model.addAttribute("vo", vo);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
		
		//해당 상품에 달린 상품 후기 개수
		model.addAttribute("totalReview", reviewService.countReview(vo.getPro_num()));
	}
	
		
	
}
