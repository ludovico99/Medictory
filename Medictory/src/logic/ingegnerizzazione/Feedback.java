package logic.ingegnerizzazione;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.view.GcErrore;

public class Feedback {
	private static String title = "Medictory";
	
	private Feedback() {
	    throw new IllegalStateException("Utility class");}
	
	public static void mostraErrore(String err) {
		try {
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(GcErrore.class.getResource("Errore.fxml"));
			Parent root = loader.load();
			GcErrore controllerNext = loader.getController();
			controllerNext.setError(err);
			primaryStage.setTitle(title);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(new Scene(root, 314, 209));
			primaryStage.toFront();
			primaryStage.show();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean pingHost() {	
		URL url;
		try {
			url = new URL("http://www.google.com");
			URLConnection uc = url.openConnection();
			BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
			
			return (!br.readLine().isEmpty());
		
		}catch (Exception e) {
			return false;
		}
	}
}
