package application.Controller;

import java.io.IOException;
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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InsertController {
	private Stage stage;
	private Scene scene;
	
	static BoardService boardService = new BoardServiceImpl();
	
	static Scanner sc = new Scanner(System.in);
	
	@FXML    private TextField textcontent;
    @FXML    private TextArea texttitle;
    @FXML    private TextArea textwriter;
    
    @FXML
    void Write(ActionEvent event) throws IOException {
    	Board board = input();
    	
    	int result = boardService.insert( board );
    	
    	SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
    }
    
 	public Board input() {
 		
		String title = texttitle.getText();
		
		String writer = textwriter.getText();
		
		String content = textcontent.getText();
		
		Board board = new Board(title, writer, content);
		return board;
	}
 	
// 	SceneUtil.getInstance().switchScene(event, UI.MAIN.getPath());
	}

