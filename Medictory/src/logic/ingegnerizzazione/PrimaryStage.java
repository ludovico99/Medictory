package logic.ingegnerizzazione;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.view.GraphicController;

public class PrimaryStage {
	

	private PrimaryStage() {
	    throw new IllegalStateException("Utility class");}
	
	public static void setPrimaryStage(Stage primaryStage,FXMLLoader loader,Sessione sessione) {
		try {
			Parent root = loader.load();
			
			GraphicController controllerNext= loader.getController();
			controllerNext.setData(sessione);
			
			primaryStage.setTitle("Medictory");
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
			
		} catch(IOException e) {
			e.printStackTrace();
			}
		
	}

}
