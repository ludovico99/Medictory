package logic.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneCliente;

import java.io.IOException;
import javafx.scene.Scene;

public class GcCustomerHomepage implements GraphicController {
	@FXML
	private Button account;
	@FXML
	private Button eventi;
	@FXML
	private Button risorse;
	@FXML
	private Button riciclaggio;
	@FXML
	private Label score;
	
	private SessioneCliente sessione;
	
	
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
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
	
	
	private void initPage() {
		
		int punti = sessione.getPunteggio();
		String[] parts = score.getText().split("/");
		parts[0] = Integer.toString(punti);
		parts[1] = Integer.toString(sessione.getLivello()*100);
		score.setText(parts[0]+"/"+parts[1]);
	}
	
	
	
	public void setData(Sessione cliente) {
		this.sessione = (SessioneCliente)cliente;
		this.initPage();
	}
	
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccount.fxml");
	}
	
	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerMyEvent.fxml");
	}
	
	@FXML
	public void riciclaggioPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerRiciclaggio.fxml");
	}
	
	@FXML
	public void risorsePressed(ActionEvent event)  {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "Risorse.fxml");
	}

}
