package application.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.DTO.Board;

public class BoardDAO extends JDBConnection{
	
//	데이터 목록 조회
	public List<Board>selectList() { 
		List<Board>boardList = new ArrayList<Board>();
		
//		SQL 
		String sql = "SELECT *" + "FROM board ";
		try {
			stmt = con.createStatement();		
			rs = stmt.executeQuery(sql);		
			while(rs.next()) {
				Board board = new Board();
				
				board.setBoardNo(rs.getInt("board_no"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setUpdDate(rs.getTimestamp("upd_date"));
				
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		
		return boardList;
	}
	
//	데이터 조회
	public Board select(int no) {
		Board board = new Board();
		
		String sql = " SELECT * " + " FROM board "+" WHERE board_no = ?";
		
		try {
			psmt = con.prepareStatement(sql);		
			psmt.setInt(1, no);				
			rs = psmt.executeQuery();					
			
//			조회 결과 가져오기
			if(rs.next()) {
				board.setBoardNo(rs.getInt("board_No"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getTimestamp("reg_date"));
				board.setUpdDate(rs.getTimestamp("upd_date"));
			}
		} catch (SQLException e) {
			System.err.println("게시글 조회 시, 에러 발생");
			e.printStackTrace();
		}
		return board;
	}
	
//	데이터 등록
	public int insert(Board board) {
		int result = 0;
		
		String sql = " INSERT INTO board( title, writer, content ) "+ " VALUES ( ?, ?, ? )";		
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());		
			psmt.setString(2, board.getWriter());		
			psmt.setString(3, board.getContent());		
			
			result = psmt.executeUpdate();			
			
		} catch (SQLException e) {
			System.err.println("게시글 등록 시, 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
//	데이터 수정
	public int update(Board board) {
		int result = 0;
		
		String sql = " UPDATE board " + " SET title = ? " + "	,writer = ? "+ "	,content = ?"+"		,upd_date = now() " + " WHERE board_no = ? ";
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());;
			psmt.setString(2, board.getWriter());		
			psmt.setString(3, board.getContent());
			psmt.setInt(4, board.getBoardNo());
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("게시글 수정 시, 에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
//	데이터 삭제
	public int delete(int no) {
		int result = 0;
		
		String sql = " DELETE FROM board " + " WHERE board_no = ? ";
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, no);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("게시글 삭제 시, 에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}
}
