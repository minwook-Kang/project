package com.minmall.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minmall.DTO.AdminDTO;
import com.minmall.DTO.MemberDTO;
import com.minmall.domain.AdminVO;
import com.minmall.service.AdminService;
import com.minmall.service.MemberService;
import com.minmall.util.PageMaker;
import com.minmall.util.SearchCriteria;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private AdminService service;
	
	@Inject
	private MemberService mbsService;
	
	//관리자 메인페이지
	@RequestMapping(value = "/main",method = RequestMethod.GET)
	public String adminMain() {
		return "admin/main";
	}
	
	//관리자 로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String adminlogin(AdminDTO dto, RedirectAttributes redirect,HttpSession session) throws Exception {
		
		logger.info("===========login() 실행중...");
		logger.info("======================AdminDTO : " + dto.toString());
		
		AdminVO vo = service.login(dto);
		String msg = "";

		if(vo!= null) {
			session.setAttribute("admin", vo);
			msg = "LOGIN_SUCCESS";
		}else {
			msg = "LOGIN_FAIL";
		}
		redirect.addFlashAttribute("msg", msg);
		return "redirect:/admin/main";
	}
	
	//관리자 로그아웃
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes redirect) {
		
		logger.info("===========logout() 실행중...");
		
		session.invalidate();
		redirect.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		return "redirect:/admin/main";
	}
	
	//회원 목록
	@RequestMapping(value = "member/list", method = RequestMethod.GET)
	public void mbsList(@ModelAttribute("cri") SearchCriteria cri,Model model) throws Exception {
		
		logger.info("===========mbsList() 실행중...");
		
		List<MemberDTO> list = service.mbsList(cri);
		
		model.addAttribute("mbsList", list);
		
		PageMaker pm = new PageMaker();
		pm.setCri(cri);
		
		int count = service.searchListMbsCount(cri);
		pm.setTotalCount(count);
		
		model.addAttribute("pm", pm);
	}
	
	//회원 목록(상세보기)
	@RequestMapping(value = "member/read", method = RequestMethod.GET)
	public void mbsRead(String mbs_id, Model model) throws Exception {
		
		logger.info("===========mbsRead() 실행중...");
		
		model.addAttribute("mbsread", mbsService.readMem(mbs_id));
	}
	
}
