package service;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;
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
	public BoardVO selectDetail(BoardVO bvo) {
		return bdao.selectDetail(bvo);
	}

	@Override
	public int updateList(BoardVO bvo) {
		return bdao.updateList(bvo);
	}

	@Override
	public int selectAllCount(PagingVO pgvo) {
		return bdao.selectAllCount(pgvo);
	}

	@Override
	public List<BoardVO> selectPageList(PagingVO pgvo) {
		return bdao.selectPageList(pgvo);
	}

	@Override
	public int deleteBoard(int bno) {
		// TODO Auto-generated method stub
		return bdao.deleteBoard(bno);
	}

	@Override
	public String getFileName(int bno) {
		return bdao.selectFileName(bno);
	}

}
