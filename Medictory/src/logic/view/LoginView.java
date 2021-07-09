package logic.view;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class LoginView extends Application {
	
	public static void main(String[] args){
		launch(args);
	}
	Stage window;
	Scene startPage;
	Scene loginPage;
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		Parent root = FXMLLoader.load(getClass().getResource("progetto.fxml"));
		primaryStage.setTitle("Medictory");
		primaryStage.setScene(new Scene(root, 600,400));
		Image i = new Image("file:icon3.png");
		primaryStage.getIcons().add(i);
		primaryStage.show();
		
		
	}
	
}
