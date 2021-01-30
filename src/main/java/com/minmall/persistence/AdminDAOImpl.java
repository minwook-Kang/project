package com.minmall.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.minmall.DTO.AdminDTO;
import com.minmall.DTO.MemberDTO;
import com.minmall.domain.AdminVO;
import com.minmall.util.SearchCriteria;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	SqlSession session;
	
	public final static String NS ="com.minmall.mappers.AdminMapper";
	
	//로그인
	@Override
	public AdminVO login(AdminDTO dto) throws Exception {
		return session.selectOne(NS + ".login", dto);
	}

	//최근 로그인 시간 업데이트
	@Override
	public void loginUpdate(String admin_id) throws Exception {
		session.update(NS+ ".loginUpdate", admin_id);
	}

	//회원 목록
	@Override
	public List<MemberDTO> mbsList(SearchCriteria cri) throws Exception {
		return session.selectList(NS + ".mbsList", cri);
	}

	//검색 조건에 따른 회원 목록
	@Override
	public int searchListMbsCount(SearchCriteria cri) throws Exception {
		return session.selectOne(NS + ".searchListMbsCount", cri);
	}

}
