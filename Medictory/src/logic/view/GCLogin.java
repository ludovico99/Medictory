package logic.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.Scene;

public class GCLogin{
		
		@FXML
		private Button userStart;
		@FXML
		private Button pharmacyStart;
		@FXML
		private Button registrazione;

		
		public void userPressed(ActionEvent event){
			try {
				Parent root = FXMLLoader.load(getClass().getResource("LoginCustomer.fxml"));
		        Scene scene = new Scene(root,600,400);
		        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		        window.setScene(scene);
				window.setTitle("Medictory Customer Login");
				window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		public void pharmacyPressed(ActionEvent event) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("LoginPharmacy.fxml"));
				 Scene scene = new Scene(root,600,400);
			     Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
			     window.setTitle("Medictory Pharmacy Login");
			     window.setScene(scene);
			     window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		

		public void signUpPressed(ActionEvent event) {
			try {
				Parent root = FXMLLoader.load(getClass().getResource("Registrazione.fxml"));
		        Scene scene = new Scene(root,600,400);
		        Stage window=(Stage)((Node)event.getSource()).getScene().getWindow();
		        
		    	window.setScene(scene);
		    	window.setTitle("Medictory Registrazione");
		    	window.show();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
}
