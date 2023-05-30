package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.BoardVO;
import orm.DatabaseBuilder;

public class BoardDAOImpl implements BoardDAO {
	private SqlSession sql;
	private String NS = "BoardMapper.";
	
	private int isOk;
	
	public BoardDAOImpl() {
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public List<BoardVO> selectAllList() {
		return sql.selectList(NS + "selectAllList");
	}

	@Override
	public int insertList(BoardVO bvo) {
		isOk = sql.insert(NS + "insertList", bvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public BoardVO selectList(BoardVO bvo) {
		return sql.selectOne(NS + "selectList", bvo);
	}

	@Override
	public int updateList(BoardVO bvo) {
		isOk = sql.update(NS + "updateList", bvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

}
