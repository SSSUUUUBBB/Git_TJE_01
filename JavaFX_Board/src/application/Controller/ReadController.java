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
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class ReadController {
	static List<Board> boardList = new ArrayList<Board>();			
	static BoardService boardService = new BoardServiceImpl();
	
	Text selectedBoard;
	@FXML private TableView<Board> tableView;
    @FXML private TableColumn<Board, Integer> colNumber;
    @FXML private TableColumn<Board, String> col;
    @FXML private TableColumn<Board, String> colWriter;
    @FXML private TableColumn<Board, Date> colRegDate;
    @FXML private TableColumn<Board, Date> colUpdDate;
    
    @FXML private Button DeleteButton;
    @FXML private Button ListButton;
    @FXML private TextArea content;
    @FXML private TextField titleName;
    @FXML private Button updateButton;
    @FXML private TextField writerName;
    
    Board selectedItem;
	private Parent root;
	private Stage stage;
    private Scene scene;

    @FXML
    void List(ActionEvent event) throws IOException{
//    	root = FXMLLoader.load(getClass().getResource(UI.MAIN.getPath()));
//    	switchScene(event, root, UI.MAIN.getPath());
      	SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }
    
	//게시글 읽기
	public void inputItemIndex(int index) {
		selectedBoard = boardService.select(index);
		
		titleName.setText(selectedBoard.getTitle());
		writerName.setText(selectedBoard.getWriter());
 		content.setText(selectedBoard.getContent());
	}
	
	public void Delete(ActionEvent event) throws IOException {
			int boardNo = selectedBoard.getNo();
			int result = boardService.delete(boardNo);
			if( result > 0 ) {
				System.out.println("게시글이 삭제되었습니다.");
			}
			SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
	    }
	   
	// 수정 버튼
	 @FXML void Update(ActionEvent event) throws IOException {
			
	    	int boardNo = selectedBoard.getNo();
	    	System.out.println("수정버튼 클릭 boardNo : " + boardNo);
			FXMLLoader loader = new FXMLLoader(getClass().getResource(UI.UPDATE.getPath()));
			String title = selectedBoard.getTitle();
			String write = selectedBoard.getTitle();
			String content = selectedBoard.getTitle();
			
			try {
				
				root = loader.load();				// load() 메소드를 호출해야 loader에 Sub 씬 인스턴스를 가져온다.
											// 호출해야 Controller도 null 이 되지 않는다.
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			UpdateController updateController = loader.getController();
			if(updateController != null) {
				updateController.inputItemIndex(boardNo);
			}
			// 화면 이동
			//switchScene(event, root, UI.UPDATE.getPath());
			SceneUtil.getInstance().switchScene(event, UI.UPDATE.getPath(), root);
	    }
	 
//		public void switchScene(ActionEvent event, Parent root, String fxml) throws IOException {
//			scene = new Scene(root);
//			stage = (Stage)((Node) event.getSource()).getScene().getWindow();
//			stage.setScene(scene);		// 메인 씬으로 지정
//			stage.show();
//		}	
//		
}
