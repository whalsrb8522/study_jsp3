package service;

import java.util.List;

import domain.CommentVO;
import repository.CommentDAO;
import repository.CommentDAOImpl;

public class CommentServiceImpl implements CommentService {
	private CommentDAO cdao;
	
	public CommentServiceImpl() {
		cdao = new CommentDAOImpl();
	}

	@Override
	public int post(CommentVO cvo) {
		return cdao.insertComment(cvo);
	}

	@Override
	public List<CommentVO> getList(int bno) {
		return cdao.selectComment(bno);
	}

	@Override
	public int remove(int cno) {
		return cdao.deleteComment(cno);
	}

	@Override
	public int modify(CommentVO cvo) {
		return cdao.updateComment(cvo);
	}
}
