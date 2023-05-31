package repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import domain.CommentVO;
import orm.DatabaseBuilder;

public class CommentDAOImpl implements CommentDAO {
	private SqlSession sql;
	private String NS = "CommentMapper.";
	private int isOk;
	
	public CommentDAOImpl() {
		new DatabaseBuilder();
		sql = DatabaseBuilder.getFactory().openSession();
	}

	@Override
	public int insertComment(CommentVO cvo) {
		isOk = sql.insert(NS + "insertComment", cvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public List<CommentVO> selectComment(int bno) {
		return sql.selectList(NS + "selectComment", bno);
	}

	@Override
	public int deleteComment(int cno) {
		isOk = sql.delete(NS + "deleteComment", cno);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}

	@Override
	public int updateComment(CommentVO cvo) {
		isOk = sql.update(NS + "updateComment", cvo);
		if (isOk > 0) {
			sql.commit();
		}
		return isOk;
	}
}
	