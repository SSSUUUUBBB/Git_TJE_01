package application.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import application.DTO.Board;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertController {
	static List<Board> boardList = new ArrayList<Board>();				// 게시글 목록
	static BoardService boardService = new BoardServiceImpl();
	private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML private TextArea contentField;
    @FXML private Button goListButton;
    @FXML private Button postButton;
    @FXML private TextField titleField;
    @FXML private TextField writerField;

    @FXML
    void List(ActionEvent event) throws IOException {
      	SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }

    @FXML
    void Write(ActionEvent event) throws IOException {
		String writer1 = writerField.getText();
		String title1 = titleField.getText();
		String content1 = contentField.getText();
		Board board = new Board(title1, writer1, content1);
		int result = boardService.insert( board );
		
//		FXMLLoader loader = new FXMLLoader(getClass().getResource(UI.MAIN.getPath()));
//		try {
//			root = loader.load();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		SceneUtil.getInstance().getController(UI.MAIN.getPath());
		
		if( result > 0 ) {
			System.out.println("게시글이 작성되었습니다.");
		} 
		
      	SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }
        
	}

