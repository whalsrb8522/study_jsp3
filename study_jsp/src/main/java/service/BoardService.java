package service;

import java.util.List;

import domain.BoardVO;

public interface BoardService {

	List<BoardVO> selectAllList();

	int insertList(BoardVO bvo);

	BoardVO selectList(BoardVO bvo);

	int updateList(BoardVO bvo);

}
