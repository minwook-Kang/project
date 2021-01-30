package com.minmall.service;

import java.util.List;

import com.minmall.DTO.AdminDTO;
import com.minmall.DTO.MemberDTO;
import com.minmall.domain.AdminVO;
import com.minmall.util.SearchCriteria;

public interface AdminService {

		// 로그인
		public AdminVO login(AdminDTO dto) throws Exception;
		
		//회원 목록
		public List<MemberDTO> mbsList(SearchCriteria cri) throws Exception;
				
		//검색 조건에 해당하는 회원 목록
		public int searchListMbsCount(SearchCriteria cri) throws Exception;
				
				
}
