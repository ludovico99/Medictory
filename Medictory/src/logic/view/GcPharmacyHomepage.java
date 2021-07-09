package logic.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;

import java.io.IOException;
import javafx.scene.Scene;

public class GcPharmacyHomepage implements GraphicController{
	@FXML
	private Button account;
	@FXML
	private Button eventi;
	@FXML
	private Button gestione;
	@FXML
	private Button ritiro;
	@FXML
	private Button risorse;
	
	private SessioneFarmacia sessione;
	
	
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
	
	public void setData(Sessione farma) {
		this.sessione = (SessioneFarmacia) farma;
		
	}
	
	
	
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccount.fxml");
	}
	
	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyEvent.fxml");
	}
	
	@FXML
	public void gestionePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "GestioneFarmacie.fxml");
	}
	
	@FXML
	public void ritiroPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "FarmaciaAppuntamento.fxml");
		
	}
	
	@FXML
	public void risorsePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "RisorseFarmacia.fxml");
	}
}
