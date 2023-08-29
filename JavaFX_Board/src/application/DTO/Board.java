package application.DTO;

import javafx.beans.property.SimpleIntegerProperty;

public class Board extends Text {
	
	private SimpleIntegerProperty boardNo;
		
	public Board() {
		this("제목없음", "글쓴이없음", "내용없음");
	}
	
	public Board(String title, String writer, String content) {
		super(title, writer, content);
	}

	public int getBoardNo() {
		return boardNo.get();
	}

	public void setBoardNo(int boardNo) {
		this.boardNo = new SimpleIntegerProperty(boardNo);
		setNo(boardNo);				
	}
}
