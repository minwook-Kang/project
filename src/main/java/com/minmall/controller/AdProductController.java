package com.minmall.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minmall.domain.CategoryVO;
import com.minmall.domain.ProductVO;
import com.minmall.service.AdProductService;
import com.minmall.util.FileUtils;
import com.minmall.util.PageMaker;
import com.minmall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/product/*")
public class AdProductController {

	private static final Logger logger = LoggerFactory.getLogger(AdProductController.class);

	@Autowired
	private AdProductService service;
	
	@Resource(name="uploadPath")
	private String uploadPath; 
	
	//1차 카테고리에 따른 2차 카테고리 출력
	@ResponseBody
	@RequestMapping(value = "subCGList/{cate_code}", method = RequestMethod.GET)
	public ResponseEntity<List<CategoryVO>> subCGList(@PathVariable("cate_code") String cate_code){
		
		logger.info("===========subCGList() 실행중");

		//2차 카테고리 리스트
		ResponseEntity<List<CategoryVO>> entity = null;

		try {
			entity = new ResponseEntity<List<CategoryVO>>(service.subCGList(cate_code), HttpStatus.OK);
		} catch(Exception e) {
			entity = new ResponseEntity<List<CategoryVO>>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//상품 등록 페이지
	@RequestMapping(value = "insert",method = RequestMethod.GET)
	public void productInsert(Model model) throws Exception{
		
		//1차 카테고리 리스트 전송
		model.addAttribute("cateList", service.mainCGList());
	}
	
	//상품 등록
	@RequestMapping(value = "insert",method = RequestMethod.POST)
	public String productInsertOK(ProductVO vo, RedirectAttributes redirect) throws Exception {
		
		logger.info("===========productInsertOK() 실행중...");
		logger.info(vo.toString());
		
		vo.setPro_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		
		service.insertProduct(vo);
		redirect.addFlashAttribute("msg", "INSERT_SUCCESS");
		
		return "redirect:/admin/product/list";
		
	}

	//파일 출력(저장된 파일을 가져와 반환)
	@ResponseBody
	@RequestMapping(value = "displayFile", method = RequestMethod.GET)
	public ResponseEntity<byte[]> displayFile(String fileName) throws Exception {
		
		return FileUtils.getFile(uploadPath, fileName);
	}

	//이미지 파일 삭제
	public void deleteFile(String fileName) {
		
		logger.info("delete FileName : " + fileName);
		
		FileUtils.deleteFile(uploadPath, fileName);
	}
	
	//상품 상세(ckEditor) -파일 업로드
	@RequestMapping(value = "imgUpload", method = RequestMethod.POST)
	public void imUpload(HttpServletRequest req, HttpServletResponse res, MultipartFile upload) {
		
		logger.info("===========imgUpload() 실행중...");
		
		OutputStream out = null;
		PrintWriter printWriter = null;
		
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html);charset=utf-8");
		
		try {
			String fileName = upload.getOriginalFilename();
			byte[] bytes = upload.getBytes();
			
			String uploadPath = req.getSession().getServletContext().getRealPath("/");
			uploadPath = uploadPath + "resources\\upload\\" + fileName;
			
			logger.info("======================uploadPath : " + uploadPath);
			
			out = new FileOutputStream(new File(uploadPath));
			out.write(bytes);
			printWriter = res.getWriter();
			String fileUrl = "/upload/" + fileName;
			printWriter.println("{\"filename\":\"" + fileName + "\", \"uploaded\":1,\"url\":\"" + fileUrl + "\"}");
			printWriter.flush();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(out != null) {
				try {out.close();} catch(Exception e) {e.printStackTrace();}
			}
			if(printWriter != null) {
				try {printWriter.close();} catch(Exception e) {e.printStackTrace();}
			}
		}
	}
	
	//상품 리스트
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void productList(@ModelAttribute("cri") SearchCriteria cri, Model model) throws Exception {
		
		logger.info("===========productList() 실행중...");
		logger.info("======================cri : " + cri.toString());
		
		model.addAttribute("productList", service.searchListProduct(cri));
		
		//PageMaker 생성
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		int count = service.searchListCount(cri);
		
		logger.info("======================일치하는 상품개수  : " + count);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	//상품 상세정보 페이지 읽기
	@RequestMapping(value = "read", method = RequestMethod.GET)
	public void productRead(@ModelAttribute("cri") SearchCriteria cri,
							@RequestParam("pro_num") int pro_num, Model model) throws Exception {
		
		logger.info("===========productRead() 실행중...");
		
		ProductVO vo = service.readProduct(pro_num);
		
		logger.info("Product Contents : " + vo.toString());
		
		vo.setPro_img(vo.getPro_img().substring(vo.getPro_img().lastIndexOf("_") + 1));
		
		logger.info("======================changed Product contents : " + vo.toString());
		model.addAttribute("vo", vo);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);
	}
	
	//상품 수정 페이지
	@RequestMapping(value = "edit", method = RequestMethod.GET)
	public void productEdit(@ModelAttribute("cri") SearchCriteria cri,
							@RequestParam("pro_num") int pro_num, Model model) throws Exception {
		
		logger.info("===========productEdit() 실행중...");
		
		ProductVO vo = service.readProduct(pro_num);
	
		logger.info("======================Product Contents : " + vo.toString());
		
		String originFile = vo.getPro_img().substring(vo.getPro_img().lastIndexOf("_") + 1);
		
		model.addAttribute("vo", vo);
		model.addAttribute("originFile", originFile);
		model.addAttribute("cateList", service.mainCGList());
		model.addAttribute("subCateList", service.subCGList(vo.getCate_prtcode()));
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		model.addAttribute("pm", pm);		
	}
	
	//상품 수정 
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public String productEditOK(ProductVO vo, SearchCriteria cri, RedirectAttributes redirect) throws Exception {
		
		logger.info("===========productEditOK() 실행중...");
		logger.info("======================editted info : " + vo.toString());
		logger.info("======================cri info : " + cri.toString());
		
		if(vo.getFile1().getSize() > 0) {
			logger.info("file not zero size");
			vo.setPro_img(FileUtils.uploadFile(uploadPath, vo.getFile1().getOriginalFilename(), vo.getFile1().getBytes()));
		}
		logger.info("===========changed info : " + vo.toString());
		service.editProduct(vo);
		redirect.addFlashAttribute("cri", cri);
		redirect.addFlashAttribute("msg", "EDUT_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	//상품 삭제
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public String productDelete(SearchCriteria cri, @RequestParam("pro_num") int pro_num,
								@RequestParam("pro_img") String pro_img, RedirectAttributes redirect) throws Exception {
	
		logger.info("===========delete() 실행중...");
		
		deleteFile(pro_img);
		service.deleteProduct(pro_num);
		redirect.addFlashAttribute("cri", cri);
		redirect.addFlashAttribute("msg", "DELETE_SUCCESS");
		
		return "redirect:/admin/product/list";
	}
	
	//선택된 상품 수정
	@ResponseBody
	@RequestMapping(value = "editChecked", method = RequestMethod.POST)
	public ResponseEntity<String> editChecked(@RequestParam("checkArr[]") List<Integer> checkArr,
											  @RequestParam("amountArr[]") List<Integer> amountArr,
											  @RequestParam("buyArr[]") List<String> buyArr) {
		
		logger.info("===========editChecked() 실행중....");
		
		ResponseEntity<String> entity = null;
		
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			for(int i=0; i<checkArr.size(); i++) {
				map.put("pro_num", checkArr.get(i));
				map.put("pro_amount", amountArr.get(i));
				map.put("pro_buy", buyArr.get(i));
				
				service.editChecked(map);
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
		
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		entity = new ResponseEntity<String>(HttpStatus.OK);
		return entity;
	}
	
	//선택된 상품 삭제
	@ResponseBody
	@RequestMapping(value = "deleteChecked", method = RequestMethod.POST)
	public ResponseEntity<String> deleteChecked(@RequestParam("checkArr[]") List<Integer> checkArr,
												@RequestParam("imgArr[]") List<String> imgArr) {
	
		logger.info("===========deleteChecked() 실행중....");
		
		ResponseEntity<String> entity =null;
		
		try {
			for(int i=0; i<checkArr.size(); i++) {
				deleteFile(imgArr.get(i));
				service.deleteProduct(checkArr.get(i));
			}
			entity = new ResponseEntity<String>(HttpStatus.OK);
			
		}catch(Exception e) {
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	
}
