package application.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import application.DTO.Board;

public class BoardDAO extends JDBConnection{
	
//	데이터 목록 조회
	
	public List<Board>selectList() {		List<Board>boardList = new ArrayList<Board>();
		
//		SQL 
		String sql = "SELECT *" + "FROM board ";
		try {
			stmt = con.createStatement();		//<--쿼리 실행 객체 생성
			rs = stmt.executeQuery(sql);		//<--쿼리 실행 - 결과 --> rs(ResultSet)
			while(rs.next()) {
				Board board = new Board();
				
//				결과 데이터 가져오기
//				rs.getXXX("컬럼명") --> 해당 컬럼의 데이터를 가져온다.
//				- 실행 결과에서, "컬럼명"의 값을 특정 타입으로 변환
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
//			~어디에 매핑을 할 것이냐. 대상을 괄호 안에
			psmt = con.prepareStatement(sql);		//<--쿼리 실행 객체 생성
//		psmt.setXXX(순서번호, 매핑할 값);
//			매핑할 자료형과 (순번,값)을 입힌다
			psmt.setInt(1, no);				//<--?(1) <--boardNo(글번호)
			rs = psmt.executeQuery();					//쿼리 실행
//			excuteQuery()
//			: SQL (SELECT)를 실행하고 결과를 resultSet 객체로 변환
			
//			=>rs = con.prepareStatement(sql).setInt(1,no).executeQuery; 와 같은 형태
			
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
		
		String sql = " INSERT INTO board( title, writer, content ) "+ " VALUES ( ?, ?, ? )";		//<--띄어쓰기 주의!!!
		try {
			psmt = con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());		//<--제목 매핑
			psmt.setString(2, board.getWriter());		//<--작성자 매핑
			psmt.setString(3, board.getContent());		//<--내용 매핑
			
			result = psmt.executeUpdate();				// SQL 실행 요청, 적용된 데이터 개수를 받아옴			조회 시엔 executeQuery, 수정 시엔 executeUpdate
			
//			executeUpdate()
//			:	SQL(INSERT, UPDATE, DELETE)을 실행하고 적용된 데이터 개수를 int 타입으로 반환
														//게시글 1개 쓰기 성공시, result : 1
														//				실패 시, result : 0
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("게시글 등록 시, 에러 발생");
			e.printStackTrace();
		}
		return result;
	}
	
//	데이터 수정
	
	public int update(Board board) {
		int result = 0;
		
		String sql = " UPDATE board " + " SET title = ? " + "	,writer = ? "+ "	,content = ?"+"		,upd_date = now() " + " WHERE board_no = ? ";
		
//		- now() : 현재 날짜 / 시간을 반환하는 MySQL 함수
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setString(1, board.getTitle());;
			psmt.setString(2, board.getWriter());		
			psmt.setString(3, board.getContent());
			psmt.setInt(4, board.getBoardNo());
			
			result = psmt.executeUpdate();
//			executeUpdate()
//			:	SQL(INSERT, UPDATE, DELETE)을 실행하고 적용된 데이터 개수를 int 타입으로 반환
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("게시글 수정 시, 에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
	
//	데이터 삭제
	
	public int delete(int no) {
		int result = 0;
		
		String sql = " DELETE FROM board " + " WHERE board_no = ? ";
		
//		- now() : 현재 날짜 / 시간을 반환하는 MySQL 함수
		
		try {
			psmt=con.prepareStatement(sql);
			psmt.setInt(1, no);
			
			result = psmt.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.err.println("게시글 삭제 시, 에러 발생");
			e.printStackTrace();
		}
		
		return result;
	}

}
