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
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UpdateController {
	static List<Board> boardList = new ArrayList<Board>();				// 게시글 목록
	static BoardService boardService = new BoardServiceImpl();
	
	private Stage stage;
    private Scene scene;
    private Parent root;
	 Text selectedBoard;
    @FXML private Button Delete;
    @FXML private Button UpdateCompl;
  //  @FXML private Button Upload;
    @FXML private Button boardBack1;
    @FXML private TextArea content;
    @FXML private TextField title;
    @FXML private TextField writer;
    Board selectedItem;
	@FXML private TableView<Board> boardTableView;
   
    
  public void inputItemIndex(int boardNo) { 
	  
 		System.out.println("넘어온 boardNo : " + boardNo);
 		
 		selectedBoard = boardService.select(boardNo);
 		System.out.println("##########################################");
 		System.out.println(selectedBoard == null);
 		System.out.println(selectedBoard);
 		
 		title.setText(selectedBoard.getTitle());
 		writer.setText(selectedBoard.getWriter());
 		content.setText(selectedBoard.getContent());
 	}
    
    public void UpdateCompl(ActionEvent event) throws IOException {
    	System.out.println("##### 게시글 수정 #####");
		System.out.print("게시글 번호 : "); // 여기까지만 정상
    	int boardNo = selectedBoard.getNo(); // null값임
    	
		System.out.print("작성자 : ");
		String writer1 = writer.getText();
		
		System.out.print("제목 : ");
		String title1 = title.getText();
		
		System.out.print("내용 : ");
		String content1 = content.getText();
		
		Board board = new Board(title1, writer1, content1);
		board.setBoardNo(boardNo);
		int result = boardService.update(board);
		
		if( result > 0 ) {
			System.out.println("게시글이 수정되었습니다.");
		}
		
		SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
		
    }

    @FXML void boardBack1(ActionEvent event) throws IOException {
    	root = FXMLLoader.load(getClass().getResource("Main.fxml"));
    	 
		SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
	}
    
}