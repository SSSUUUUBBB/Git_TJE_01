package application.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.DTO.Board;
import application.DTO.Text;
import application.Service.BoardService;
import application.Service.BoardServiceImpl;
import application.Util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

	public class UpdateController {
		
	static List<Board> boardList = new ArrayList<Board>();
	static BoardService boardService = new BoardServiceImpl();
	Text selectedBoard;
    Board selectedItem;
    
    @FXML private Button applyButton;
    @FXML private TextArea content;
    @FXML private Button listButton;
    @FXML private TextField titleName;
    @FXML private TextField writerName;

    @FXML void List(ActionEvent event) throws IOException {
    	SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }

    public void inputItemIndex(int boardNo) { 
    	
 		selectedBoard = boardService.select(boardNo);
 		
 		titleName.setText(selectedBoard.getTitle());
 		writerName.setText(selectedBoard.getWriter());
 		content.setText(selectedBoard.getContent());
 	}
    
    public void UpdateCompl(ActionEvent event) throws IOException {

    	int boardNo = selectedBoard.getNo();
    	
		String writer1 = writerName.getText();
		String title1 = titleName.getText();
		String content1 = content.getText();
		
		Board board = new Board(title1, writer1, content1);
		board.setBoardNo(boardNo);
		int result = boardService.update(board);
		
		if( result > 0 ) {
			System.out.println("게시글이 수정되었습니다.");
		}
		
		SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }
}