package application.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import application.DTO.Board;
import application.DTO.Text;
import application.Service.BoardService;
import application.Service.BoardServiceImpl;
import application.Util.SceneUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReadController {
	static List<Board> boardList = new ArrayList<Board>();				// 게시글 목록
	static BoardService boardService = new BoardServiceImpl();
	Text selectedBoard;
	@FXML private TableView<Board> boardTableView;
    @FXML private TableColumn<Board, Integer> colBoardNo;
    @FXML private TableColumn<Board, String> colTitle;
    @FXML private TableColumn<Board, String> colWriter;
    @FXML private TableColumn<Board, Date> colRegDate;
    @FXML private TableColumn<Board, Date> colUpdDate;
    
    @FXML private Button DeleteButton;
    @FXML private Button ListButton;
    @FXML private TextArea content;
    @FXML private TextField titleName;
    @FXML private Button updateButton;
    @FXML private TextField writerName;

    @FXML
    void Delete(ActionEvent event) throws IOException {
    	System.out.println("##### 게시글 삭제 #####");
		System.out.print("게시글 번호 : ");
		int boardNo = selectedBoard.getNo();
		int result = boardService.delete(boardNo);
		if( result > 0 ) {
			System.out.println("게시글이 삭제되었습니다.");
		}
		SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }
    
    @FXML
    void List(ActionEvent event) {

    }
    
    Board selectedItem;
	private Parent root;

	//게시글 읽기
	public void inputItemIndex(int index) {

		selectedBoard = boardService.select(index);
		System.out.println(selectedBoard);
		
		titleName.setText(selectedBoard.getTitle());
		writerName.setText(selectedBoard.getWriter());
 		content.setText(selectedBoard.getContent());
	}
	
	// 수정 버튼
	 @FXML void Update(ActionEvent event) throws IOException {
			
	    	int boardNo = selectedBoard.getNo();
	    	System.out.println("수정버튼 클릭 boardNo : " + boardNo);
	    	
	    	SceneUtil.getInstance().getController(UI.UPDATE.getPath());
	    	
			String title = selectedBoard.getTitle();
			String write = selectedBoard.getTitle();
			String content = selectedBoard.getTitle();
	    	
			if(SceneUtil.getInstance().getController(UI.UPDATE.getPath()) != null) {
				UpdateController updateController = new UpdateController();
				updateController.inputItemIndex(boardNo);
			}
			
			SceneUtil.getInstance().switchScene(event, UI.UPDATE.getPath(), root);
	    }
}
