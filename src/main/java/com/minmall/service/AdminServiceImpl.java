package com.minmall.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minmall.DTO.AdminDTO;
import com.minmall.DTO.MemberDTO;
import com.minmall.domain.AdminVO;
import com.minmall.persistence.AdminDAO;
import com.minmall.util.SearchCriteria;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO dao;
	/*/
	@Inject
	private BCryptPasswordEncoder passwdEncrypt;
	*/
	//관리자 로그인
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		
		AdminVO vo = dao.login(dto);
		
		// 로그인 정보와 일치하는 값이 존재하면,
		if(vo != null) {
			// 비밀번호가 암호화 된 비밀번호와 일치하는지 확인
			/*
			if(passwdEncrypt.matches(dto.getAdmin_pw(), vo.getAdmin_pw())) {
				dao.loginUpdate(dto.getAdmin_id());
			}else {
			vo = null;
		  	}
		  	*/
			dao.loginUpdate(dto.getAdmin_id());
		}
		return vo;
	}
	
	//회원 목록
	@Override
	public List<MemberDTO> mbsList(SearchCriteria cri) throws Exception {
		return dao.mbsList(cri);
	}
	
	//검색 조건에 따른 회원 목록
	@Override
	public int searchListMbsCount(SearchCriteria cri) throws Exception {
		return dao.searchListMbsCount(cri);
	}

}
