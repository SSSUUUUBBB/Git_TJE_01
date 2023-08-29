package application.Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import application.DTO.Board;
import application.DTO.Text;
import application.Service.BoardService;
import application.Service.BoardServiceImpl;
import application.Util.SceneUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Initializable {
	static List<Board> boardList = new ArrayList<Board>();
	static BoardService boardService = new BoardServiceImpl();
	
	
	public void list() {
		boardList = boardService.list();
		printAll(boardList);
	}
	
	public void printAll(List<? extends Text> list) {
		if( list == null || list.isEmpty() ) {
			return;
		}
		
		for (Text text : list) {
			print(text);
		}
	}
	
	public void print(Text text) {
		
		if( text == null ) {
			return;
		}
		
		int no = text.getNo();
		String title = text.getTitle();
		String writer =  text.getWriter();
		String content = text.getContent();
		Date regDate = text.getRegDate();
		Date updDate = text.getUpdDate();
		
	}
	
	@FXML private TableView<Board> boardTableView;
	@FXML private TableColumn<Board, Integer> colBoardNo;
	@FXML private TableColumn<Board, Date> colRegDate;
	@FXML private TableColumn<Board, String> colTitle;
	@FXML private TableColumn<Board, Date> colUpdDate;
	@FXML private TableColumn<Board, String> colWriter;
	
	 Stage stage;
	 Scene scene;
	 Parent root;
	 Board selectedItem;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {		 
		list();
	
		ObservableList<Board> list = FXCollections.observableArrayList(
			boardList
		);
	
		colBoardNo.setCellValueFactory(new PropertyValueFactory<>("BoardNo"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
		colRegDate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
		colUpdDate.setCellValueFactory(new PropertyValueFactory<>("UpdDate"));
	
		boardTableView.setItems(list);
		
		//클릭 시 화면 전환
		
		 boardTableView.setOnMouseClicked(new EventHandler<MouseEvent>(){
			 
			@Override
			public void handle(MouseEvent event) {
				
				if( event.getClickCount() == 2) {
					
					selectedItem = boardTableView.getSelectionModel().getSelectedItem();
					
					stage = (Stage)	((Node) event.getSource()).getScene().getWindow();
					
					int index = boardTableView.getSelectionModel().getSelectedIndex();
					FXMLLoader loader = new FXMLLoader(getClass().getResource("READ.fxml"));
					try {
						root = loader.load();	
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					ReadController subController = loader.getController();
					
					if( subController != null) {
						// inputItemIndex 메소드 정의
						subController.inputItemIndex(index);
					}
					
					try {
						SceneUtil.getInstance().switchScene(event, UI.READ.getPath());
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
			}
		
			
		});
		 
		 
//			//글쓰기화면 이동
//			
//			//프로그램종료
//			SceneUtil.getInstance().close(event);
	}
	@FXML
	void writePost(ActionEvent event) throws IOException {
		SceneUtil.getInstance().switchScene(event, UI.INSERT.getPath());
	}
}
