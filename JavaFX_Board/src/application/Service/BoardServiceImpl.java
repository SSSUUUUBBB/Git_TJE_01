package application.Service;

import java.util.List;

import application.DAO.BoardDAO;
import application.DTO.Board;

public class BoardServiceImpl implements BoardService{

	private BoardDAO boardDAO = new BoardDAO();
	
	@Override
	public List<Board> list() {
		List<Board> boardList = (List<Board>) boardDAO.selectList();
		return boardList;
	}

	@Override
	public Board select(int boardNo) {
		Board board = (Board)boardDAO.select(boardNo);
		return board;
	}

	@Override
	public int insert(Board board) {
		int result = boardDAO.insert(board);
		return result;
	}

	@Override
	public int update(Board board) {
		int result = boardDAO.update(board);
		return result;
	}

	@Override
	public int delete(int boardNo) {
		int result = boardDAO.delete(boardNo);
		return result;
	}
}
