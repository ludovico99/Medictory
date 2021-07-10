package logic.view;

import java.io.IOException;
import logic.controller.ControllerRegistrazione;
import logic.ingegnerizzazione.Feedback;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class GcRegistrazioneFarma {
	@FXML
	private Button x;
	@FXML
	private Button cliente;
	@FXML
	private Button fatto;
	@FXML
	private TextField username;
	@FXML
	private PasswordField pwd;
	@FXML
	private TextField email;
	@FXML
	private TextField nome;
	@FXML
	private TextField indirizzo;
	
	private ControllerRegistrazione controller = new ControllerRegistrazione();
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			primaryStage.setTitle("Medictory");
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void xPressedF(ActionEvent event) {
		
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml");
	}
	@FXML
	public void clientePressed(ActionEvent event) {
		
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "Registrazione.fxml");
	}
	@FXML
	public void fattoPressed(ActionEvent event) {
		String u;
		String p;
		String e;
		String n; 
		String i;
		
		u = username.getText();
		p = pwd.getText();
		e = email.getText();
		n = nome.getText();
		i = indirizzo.getText();
		
		if(controller.registraFarma(u, p, n, e, i)) {
			Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
			setPrimaryStage(primaryStage, "progetto.fxml");
			GcOk o = new GcOk();
			o.mostrati();
		}
		else {
			
			username.setText("");
			pwd.setText("");
			email.setText("");
			nome.setText("");
			indirizzo.setText("");
			Feedback.mostraErrore("Dati inseriti scorretti, riprovare");
			
		}
	}
}
