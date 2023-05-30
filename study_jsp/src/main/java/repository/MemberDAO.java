package repository;

import java.util.List;

import domain.MemberVO;

public interface MemberDAO {

	int insertMember(MemberVO mvo);

	MemberVO selectMember(MemberVO mvo);

	int updateMember(MemberVO mvo);

	List<MemberVO> selectAllMember();

}
