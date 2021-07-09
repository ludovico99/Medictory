package logic.view;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GcOk {
	

	@FXML
	private Button ok;
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			primaryStage.setTitle("Medictory");
			primaryStage.setScene(new Scene(root, 314,209));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void mostrati() {
		Stage primaryStage = new Stage();
		setPrimaryStage(primaryStage, "Ok.fxml");
	}
	
	@FXML
	public void okPressed() {
		Stage stage = (Stage) ok.getScene().getWindow();
		stage.close();
	}
}
