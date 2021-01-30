package com.minmall.controller;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.MemberVO;
import com.minmall.service.MemberService;


@Controller
@RequestMapping("/member/*")
public class MemberController {

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);
	
	@Autowired
	private MemberService service;
	
	@Autowired
	private BCryptPasswordEncoder passwdEncrypt;
	
	//로그인 뷰
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public void login() {
	}
	
	//로그인 전송
	@RequestMapping(value = "loginOK",method = RequestMethod.POST)
	public String loginOK(MemberDTO dto,RedirectAttributes redirect, 
							HttpSession session, HttpServletResponse response) throws Exception {
		logger.info("===========login() 실행중....");
		//DB에서 암호화된 비밀번호 저장
		MemberDTO memDTO = service.login(dto);
		
		if(memDTO != null) { //로그인 성공
			
			session.setAttribute("user", memDTO);
			
			redirect.addFlashAttribute("msg", "LOGIN_SUCCESS");
			return "redirect:/";
		}else { 			//로그인 실패
			
			redirect.addFlashAttribute("msg", "LOGIN_FAIL");
			
			return "redirect:/member/login";
		}
	}
	
	//로그아웃
	@RequestMapping(value = "logout",method = RequestMethod.GET)
	public String logout(HttpSession session, RedirectAttributes redirect) {
		
		logger.info("===========logout() 실행중....");
		
		session.invalidate();
		redirect.addFlashAttribute("msg", "LOGOUT_SUCCESS");
		
		return "redirect:/";
	}
	
	//회원가입 뷰
	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public String join() {
		return "/member/join";
	}
	
	//회원가입 전송
	@RequestMapping(value = "/join",method = RequestMethod.POST)
	public String joinOK(MemberVO vo, RedirectAttributes redirect) throws Exception {
		
		logger.info("=========================입력데이타: " + vo.toString());
		
		//비밀번호 암호화 처리
		vo.setMbs_pw(passwdEncrypt.encode(vo.getMbs_pw()));
		
		service.join(vo);
		redirect.addFlashAttribute("msg", "REGISTER_SUCCESS");
		
		return "redirect:/";
	}
	
	//아이디 중복체크
	@ResponseBody
	@RequestMapping(value = "checkIdOverlap", method = RequestMethod.POST)
	public ResponseEntity<String> checkIdOverlap(@RequestParam("mbs_id") String mbs_id) throws Exception {
		
		ResponseEntity<String> entity =null;
		try {
			String ck = service.checkIdOverlap(mbs_id);
			
			if(ck != null) {
				entity = new ResponseEntity<String>("FAIL",HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			}
		}catch(Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//이메일 인증
	@ResponseBody
	@RequestMapping(value = "checkAuthcode", method = RequestMethod.POST)
	public ResponseEntity<String> checkAuthcode(@RequestParam("code") String code, HttpSession session){
		
		ResponseEntity<String> entity = null;
		
		try {
			if(code.equals(session.getAttribute("authcode"))) {
				entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
			} else {
				entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
			}
		}catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	//비밀번호 재확인
	//1)회원정보 수정 url=modify, 2)비밀번호 변경 url=changePw, 3)회원 탈퇴 url=delete
	@RequestMapping(value = "checkPw", method = RequestMethod.GET)
	public void checkPwGET(@ModelAttribute("url") String url) {
		
	}
	@RequestMapping(value = "checkPw", method = RequestMethod.POST)
	public String checkPwPOST(@RequestParam("url") String url, @RequestParam("mbs_pw") String pw,
								HttpSession session, Model model) throws Exception {
		
		logger.info("================checkPw() 실행중..."); 
		logger.info("================url: " + url + ", mem_pw: " + pw);
		
		MemberDTO dto = (MemberDTO) session.getAttribute("user");
		
		if(passwdEncrypt.matches(pw, dto.getMbs_pw())) {
			//비밀번호 일치시 url 확인
			if(url.equals("modify")) {
				model.addAttribute("vo", service.readMem(dto.getMbs_id()));
				return "/member/modify";
			} else if(url.equals("changePw")) {
				return "/member/changePw";
			} else if(url.equals("delete")) {
				return "/member/delete";
			}
		}
		//비밀번호가 일치하지 않거나, url이 정해진 url이 아닌 경우
		model.addAttribute("url", url);
		model.addAttribute("msg", "CHECK_PW_FAIL");
		return "/member/checkPw";
	}
	
	//비밀번호 확인(Ajax)
	@ResponseBody
	@RequestMapping("checkPwAjax")
	public ResponseEntity<String> checkPwAjax(@RequestParam("mbs_pw") String mbs_pw, HttpSession session) {
		
		ResponseEntity<String> entity = null;
		MemberDTO dto =(MemberDTO) session.getAttribute("user");

		if(passwdEncrypt.matches(mbs_pw, dto.getMbs_pw())) {
			entity = new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
		} else {
			entity = new ResponseEntity<String>("FAIL", HttpStatus.OK);
		}
		return entity;
	}
	
	//회원정보 수정
	@RequestMapping(value = "modify", method = RequestMethod.POST)
	public String modify(MemberVO vo,RedirectAttributes redirect, HttpSession session) throws Exception {
		
		logger.info("===========modify() 실행중...");
		
		MemberDTO dto = new MemberDTO();
		dto.setMbs_id(vo.getMbs_id());
		dto.setMbs_pw(vo.getMbs_pw());
		
		//비밀번호 암호화
		vo.setMbs_pw(passwdEncrypt.encode(vo.getMbs_pw()));
		service.modifyMemInfo(vo);
		
		session.setAttribute("user", service.login(dto));
		
		redirect.addFlashAttribute("msg", "MODIFY_USER_SUCCESS");
		
		return "redirect:/";
	}
	
	//비밀번호 변경
	@RequestMapping(value = "changePw", method = RequestMethod.POST)
	public String changePw(MemberDTO dto, RedirectAttributes redirect, HttpSession session) throws Exception {
		
		logger.info("===========changePw() 실행중...");
		
		//비밀번호 암호화 후 변경
		dto.setMbs_pw(passwdEncrypt.encode(dto.getMbs_pw()));
		service.changePw(dto);
		
		//세션의 비밀번호 재설정
		MemberDTO memDTO = (MemberDTO) session.getAttribute("user");
		memDTO.setMbs_pw(dto.getMbs_pw());
		session.setAttribute("msg", "CHANGE_PW_SUCCESS");
		return "redirect:/";
	}
	
	//회원탈퇴
	@RequestMapping(value="delete", method = RequestMethod.POST)
	public String delete(String mbs_id, HttpSession session, RedirectAttributes redirect) throws Exception {
		
		logger.info("==============delete() 실행중...");
		
		service.deleteMem(mbs_id);
		session.invalidate();
		redirect.addFlashAttribute("msg", "DELETE_USER_SUCCESS");
		
		return "redirect:/";
	}
}
