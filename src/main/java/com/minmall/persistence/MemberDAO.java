package com.minmall.persistence;

import java.util.Map;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.MemberVO;

public interface MemberDAO {

		//MemberVO 가져오기
		public MemberVO readMem(String mbs_id) throws Exception;
		
		//로그인
		public MemberDTO login(MemberDTO dto) throws Exception;
		
		// 로그인 시간 업데이트
		public void loginUpdate(String mbs_id) throws Exception;
		
		//회원가입
		public void join(MemberVO vo) throws Exception;

		//아이디 중복체크
		public String checkIdOverlap(String mbs_id) throws Exception;
		
		//회원정보 수정
		public void modifyMemInfo(MemberVO vo) throws Exception;
		
		//비밀번호 변경
		public void changePw(MemberDTO dto) throws Exception;
		
		//회원 탈퇴
		public void deleteMem(String mbs_id) throws Exception;
		
}