package com.minmall.service;

import java.util.Map;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.MemberVO;
import com.minmall.persistence.MemberDAO;

@Service
public class MemberServiceImpl implements MemberService {

	@Autowired
	private MemberDAO dao;
	
	@Inject
	private BCryptPasswordEncoder passwdEncrypt;
	
	//MemberVO 가져오기
	@Override
	public MemberVO readMem(String mbs_id) throws Exception {
		return dao.readMem(mbs_id);
	}
	
	//로그인
	@Transactional
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		MemberDTO memDTO = dao.login(dto);
		
		if(memDTO != null) {
			//비밀번호가 암호화 된 비밀번호와 일치하는지 확인.
			if(passwdEncrypt.matches(dto.getMbs_pw(), memDTO.getMbs_pw())) {
				dao.loginUpdate(memDTO.getMbs_id());
			}else {
				memDTO = null;
		
			}
		}
		return memDTO;
	}

	//회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
			
		dao.join(vo);
			
	}

	//아이디 중복 체크
	@Override
	public String checkIdOverlap(String mbs_id) throws Exception {
		return dao.checkIdOverlap(mbs_id);
	}
	
	//회원 정보 수정
	@Override
	public void modifyMemInfo(MemberVO vo) throws Exception {
		dao.modifyMemInfo(vo);
	}

	//비밀번호 변경
	@Override
	public void changePw(MemberDTO dto) throws Exception {
		dao.changePw(dto);
	}
	
	//회원 탈퇴
	@Override
	public void deleteMem(String mbs_id) throws Exception {
		dao.deleteMem(mbs_id);
	}


}
