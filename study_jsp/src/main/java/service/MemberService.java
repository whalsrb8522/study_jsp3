package service;

import java.util.List;

import domain.MemberVO;

public interface MemberService {
	
	int insertMember(MemberVO mvo);

	MemberVO selectMember(MemberVO mvo);

	int updateMember(MemberVO mvo);

	List<MemberVO> selectAllMember();

	int deleteMember(MemberVO mvo);
	
}
