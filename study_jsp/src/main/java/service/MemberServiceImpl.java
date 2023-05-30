package service;

import java.util.List;

import domain.MemberVO;
import repository.MemberDAO;
import repository.MemberDAOImple;

public class MemberServiceImpl implements MemberService {
	MemberDAO mdao;
	
	public MemberServiceImpl() {
		mdao = new MemberDAOImple();
	}

	@Override
	public int insertMember(MemberVO mvo) {
		return mdao.insertMember(mvo);
	}

	@Override
	public MemberVO selectMember(MemberVO mvo) {
		return mdao.selectMember(mvo);
	}

	@Override
	public int updateMember(MemberVO mvo) {
		return mdao.updateMember(mvo);
	}

	@Override
	public List<MemberVO> selectAllMember() {
		return mdao.selectAllMember();
	}
}