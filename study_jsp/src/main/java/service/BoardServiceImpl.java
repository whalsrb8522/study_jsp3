package service;

import java.util.List;

import domain.BoardVO;
import repository.BoardDAO;
import repository.BoardDAOImpl;

public class BoardServiceImpl implements BoardService {
	BoardDAO bdao;
	
	public BoardServiceImpl() {
		bdao = new BoardDAOImpl();
	}

	@Override
	public List<BoardVO> selectAllList() {
		return bdao.selectAllList();
	}

	@Override
	public int insertList(BoardVO bvo) {
		return bdao.insertList(bvo);
	}

	@Override
	public BoardVO selectList(BoardVO bvo) {
		return bdao.selectList(bvo);
	}

	@Override
	public int updateList(BoardVO bvo) {
		return bdao.updateList(bvo);
	}

}
