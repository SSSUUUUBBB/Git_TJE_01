module JavaFX_Board {
	requires javafx.controls;
	requires javafx.fxml;
	requires javafx.graphics;
	requires javafx.base;
	requires java.sql;

	opens application to java.sql, javafx.base, javafx.graphics, javafx.fxml, javafx.controls;
	opens application.DTO to java.sql, javafx.base, javafx.graphics, javafx.fxml, javafx.controls;
	opens application.DAO to java.sql, javafx.base, javafx.graphics, javafx.fxml, javafx.controls;
	opens application.Service to java.sql, javafx.base, javafx.graphics, javafx.fxml, javafx.controls;
	opens application.Controller to java.sql, javafx.base, javafx.graphics, javafx.fxml, javafx.controls;
	
} 