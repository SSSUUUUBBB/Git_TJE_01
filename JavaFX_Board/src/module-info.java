module JavaFX_Board {
	requires javafx.base;
	requires javafx.controls;
	requires javafx.fxml;
	
	opens application to javafx.graphics, javafx.fxml;
	opens application.Controller to javafx.fxml;
	opens application.DTO to javafx.base;
}
