package repository;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.MemberVO;
import orm.DatabaseBuilder;

public class MemberDAOImpl implements MemberDAO {
	private SqlSession sql;
	private String NS = "MemberMapper.";
	
	private int isOk;
	private MemberVO mvo;
	
	public MemberDAOImpl() {
		sql = DatabaseBuilder.getFactory().openSession();
	}
	
	@Override
	public int insertMember(MemberVO mvo) {
		isOk = sql.insert(NS + "insertMember", mvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public MemberVO selectMember(MemberVO mvo) {
		mvo = sql.selectOne(NS + "selectMember", mvo);
		return mvo;
	}

	@Override
	public int updateMember(MemberVO mvo) {
		isOk = sql.update(NS + "updateMember", mvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<MemberVO> selectAllMember() {
		List<MemberVO> listMvo = new ArrayList<MemberVO>();
		listMvo = sql.selectList(NS + "selectAllMember");
		return listMvo;
	}

	@Override
	public int deleteMember(MemberVO mvo) {
		isOk = sql.delete(NS + "deleteMember", mvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
}
