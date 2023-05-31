package repository;

import java.util.List;

import domain.BoardVO;
import domain.PagingVO;

public interface BoardDAO {

	List<BoardVO> selectAllList();

	int insertList(BoardVO bvo);

	BoardVO selectDetail(BoardVO bvo);

	int updateList(BoardVO bvo);

	int selectAllCount(PagingVO pgvo);

	List<BoardVO> selectPageList(PagingVO pgvo);

	int deleteBoard(int bno);

	String selectFileName(int bno);

}
