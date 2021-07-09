package logic.view;

import java.io.IOException;

import logic.controller.ControllerCustomerAppuntamento;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneCliente;

public class GcCustomerAppuntamento implements GraphicController {
	
	@FXML
	private Button x;
	@FXML
	private Button eventi;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML
	private Button risorse;
	@FXML
	private Button annulla;
	@FXML
	private Button prenota;
	@FXML
	private Button riciclaggio;	
	@FXML
	private TextField nometf;
	@FXML
	private TextField cittatf;
	@FXML
	private TextField indirizzotf;
	@FXML
	private TextField farmaciatf;
	@FXML
	private TextField emailtf;
	
	@FXML
	private DatePicker datatf;
	
	private SessioneCliente sessione;
	
	private ControllerCustomerAppuntamento controller = new ControllerCustomerAppuntamento();

	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
			Parent root = loader.load();
			
			GraphicController controllerNext = loader.getController();
			controllerNext.setData(sessione);
			
			primaryStage.setTitle("Medictory");
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
			
		} catch(IOException e) {
			e.printStackTrace();
			}
		
	}
	
	
	@FXML
	public void xPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerRiciclaggio.fxml");
		
	}
	
	@FXML
	public void risorsePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "Risorse.fxml");
		
	}
	
	@FXML
	public void riciclaggioPressed(ActionEvent event) {
		this.xPressed(event);
		
	}

	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerMyEvent.fxml");
	}
	
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccount.fxml");
	}
	
	@FXML
	public void homePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepageClient.fxml");
	}
	
	@FXML
	public void annullaPressed(ActionEvent event) {
		this.xPressed(event);
	}

	@FXML
	public void prenotaPressed(ActionEvent event) {
		
		String n = nometf.getText();
		String c = cittatf.getText();
		String i = indirizzotf.getText();
		String d = "";
		if (datatf.getValue() != null)
			d = datatf.getValue().toString();
		String f = farmaciatf.getText();
		String e = emailtf.getText();
	
		
			try {
				controller.prenotaRitiro(n, c, i, d, f, e, this.sessione);
			} catch (InputException e1) {
				e1.printStackTrace();
				Feedback.mostraErrore(e1.getMessage());
			}finally {
				nometf.setText("");
				cittatf.setText("");
				indirizzotf.setText("");
				datatf.setValue(null); 
				farmaciatf.setText("");
				emailtf.setText("");
			}
	}


	@Override
	public void setData(Sessione cliente) {

		this.sessione = (SessioneCliente) cliente;
		
	}


}