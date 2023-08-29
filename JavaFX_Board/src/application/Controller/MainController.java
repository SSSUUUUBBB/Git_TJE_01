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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainController implements Initializable{
	static List<Board> boardList = new ArrayList<Board>();
	static BoardService boardService = new BoardServiceImpl();
    private Stage stage;
    private Scene scene;
    private Parent root;
    
	@FXML private TableView<Board> tableView;
    @FXML private TableColumn<Board, Integer> colNumber;
    @FXML private TableColumn<Board, String> colTitle;
    @FXML private TableColumn<Board, String> colWriter;
    @FXML private TableColumn<Board, Date> colRegdate;
    @FXML private TableColumn<Board, Date> colUdpdate;
    @FXML private Button btnWrite;
    @FXML private TextField title;
    @FXML private TextField wirter;
    Board selectedItem;

    @FXML void Write(ActionEvent event) throws IOException {
    	SceneUtil.getInstance().switchScene(event, UI.INSERT.getPath());
//    	switchScene(event, UI.INSERT.getPath());
    }
 
	@Override
	
	public void initialize(URL arg0, ResourceBundle arg1) {
			
		boardList = boardService.list();
		printAll(boardList);
		
		ObservableList<Board> list = FXCollections.observableArrayList(boardList);
		
		colNumber.setCellValueFactory(new PropertyValueFactory<>("BoardNo"));
		colTitle.setCellValueFactory(new PropertyValueFactory<>("Title"));
		colWriter.setCellValueFactory(new PropertyValueFactory<>("Writer"));
		colRegdate.setCellValueFactory(new PropertyValueFactory<>("RegDate"));
		colUdpdate.setCellValueFactory(new PropertyValueFactory<>("updDate"));
		
		tableView.setItems(list);
		tableView.setOnMouseClicked(new EventHandler<MouseEvent>() {
			
			@Override
			public void handle(MouseEvent event) {
				if (event.getClickCount() == 2) {
					selectedItem = tableView.getSelectionModel().getSelectedItem();
					
					stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
					
					int index = tableView.getSelectionModel().getSelectedItem().getBoardNo();
					//Parent root = FXMLLoader.load(getClass().getResource(UI.MAIN.getPath()));
					FXMLLoader loader = new FXMLLoader(getClass().getResource(UI.READ.getPath()));
					try {
						root = loader.load();				
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					ReadController readController = loader.getController();
					
					if(readController != null) {
						readController.inputItemIndex(index);
					}
					
					// 화면 이동
					switchScene(stage, "Read.fxml");
				}
	    }
		});
	}
		
		/**
		 * 게시글 목록
		 */
		public static void list() {
			System.out.println("##### 게시글 목록 #####");
			boardList = boardService.list();
			printAll(boardList);
		}
		
		/**
		 * 글 목록 전체 출력 메소드
		 * @param list
		 */
		public static void printAll(List<? extends Text> list) {
			if( list == null || list.isEmpty() ) {
				System.out.println("조회된 글이 없습니다.");
				return;
			}
			
			for (Text text : list) {
				print(text);
			}
		}
		
		/**
		 * 글 출력 메소드
		 * @param text
		 */
		public static void print(Text text) {
			
			if( text == null ) {
				System.out.println("(조회되지 않는 글)");
				return;
			}
			
			int no = text.getNo();
			String title = text.getTitle();
			String writer =  text.getWriter();
			String content = text.getContent();
			Date regDate = text.getRegDate();
			Date updDate = text.getUpdDate();
			
			System.out.println("# 글번호 : " + no + " ##########################");
			if( text instanceof Board )	// 제목은 게시글에서만, 댓글에는 제목 없음
				System.out.println("# 제목 : " + title);
			System.out.println("# 작성자 : " + writer);
			System.out.println("# " + content);
			System.out.println("# - 등록일자 : " + regDate);
			System.out.println("# - 수정일자 : " + updDate);
			System.out.println("######################################");
			System.out.println();
		}
		
		public void switchScene(Stage stage, String fxml) {
			scene = new Scene(root);
			stage.setScene(scene);
			stage.show();
	}	
}

