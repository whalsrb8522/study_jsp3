package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.BoardVO;
import domain.PagingVO;
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
	public BoardVO selectDetail(BoardVO bvo) {
		isOk = sql.update(NS + "updateReadCount", bvo);
		if (isOk > 0) {
			sql.commit();
			return sql.selectOne(NS + "selectDetail", bvo);
		} else {
			return null;
		}
	}

	@Override
	public int updateList(BoardVO bvo) {
		isOk = sql.update(NS + "updateList", bvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int selectAllCount(PagingVO pgvo) {
		return sql.selectOne(NS + "selectAllCount", pgvo);
	}

	@Override
	public List<BoardVO> selectPageList(PagingVO pgvo) {
		return sql.selectList(NS + "selectPageList", pgvo);
	}

	@Override
	public int deleteBoard(int bno) {
		isOk = sql.delete(NS + "deleteBoard", bno);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public String selectFileName(int bno) {
		return sql.selectOne(NS + "selectFileName", bno);
	}

}
