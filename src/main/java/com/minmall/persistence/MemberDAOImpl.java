package com.minmall.persistence;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minmall.DTO.MemberDTO;
import com.minmall.domain.MemberVO;

@Repository
public class MemberDAOImpl implements MemberDAO {

	private final static String NS = "com.minmall.mappers.MemberMapper";
	
	@Autowired
	private SqlSession session;
	
	//MemberVO 가져오기
	@Override
	public MemberVO readMem(String mbs_id) throws Exception {
		return session.selectOne(NS + ".readMem", mbs_id);
	}

	//로그인
	@Override
	public MemberDTO login(MemberDTO dto) throws Exception {
		return session.selectOne(NS + ".login", dto);
	}

	//로그인 시간 업데이트
	@Override
	public void loginUpdate(String mbs_id) throws Exception {
		session.update(NS + ".loginUpdate", mbs_id);
	}

	//회원가입
	@Override
	public void join(MemberVO vo) throws Exception {
		session.insert(NS + ".join", vo);
	}
	
	//아이디 중복체크
	@Override
	public String checkIdOverlap(String mbs_id) throws Exception {
		return session.selectOne(NS + ".checkIdOverlap", mbs_id);
	}

	//회원정보 수정
	@Override
	public void modifyMemInfo(MemberVO vo) throws Exception {
		session.update(NS + ".modifyMemInfo", vo);
	}
	
	//비밀번호 변경
	@Override
	public void changePw(MemberDTO dto) throws Exception {
		session.update(NS + ".changePw", dto);
	}

	//회원 탈퇴
	@Override
	public void deleteMem(String mbs_id) throws Exception {
		session.delete(NS + ".deleteMem", mbs_id);
	}

	
}
