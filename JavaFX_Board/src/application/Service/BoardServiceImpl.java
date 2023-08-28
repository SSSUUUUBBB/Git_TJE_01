package application.Service;

import java.util.List;

import application.DAO.BoardDAO;
import application.DTO.Board;


//		비즈니스 로직 구현 클래스
//		............
/*		............
		............
		............
		............
		............
		............
		
*/

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
//		글 작성자 회원에게 퐁니트 10점을 부여한다.
		
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
