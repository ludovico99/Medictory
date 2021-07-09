package logic.view;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Formatter;
import logic.controller.ControllerDisponibilita;
import logic.ingegnerizzazione.DisponibilitaBean;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.NoInternetException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class GcCustomerAvailability {
	
	@FXML
	private Button back;
	@FXML
	private Label numeroLb;
	@FXML
	private TextField siglaProvincia;
	@FXML
	private TextField comune;
	@FXML
	private TextField indirizzo;
	private String farmacia;
	private String farmaco;
	private ControllerDisponibilita controller = new ControllerDisponibilita();
	

	private void searchAvailability() {
		DisponibilitaBean bean = controller.cercaDisponibilitaInFarmacia(this.farmacia, this.farmaco);
		numeroLb.setText(bean.getNumero() + " unità");
	}

	
	@FXML
	public void backPressed() {
		Stage stage = (Stage) back.getScene().getWindow();
		stage.close();
	}
	

	@FXML
	public void linkPressed(ActionEvent event) {
		String p = siglaProvincia.getText();
		String c = comune.getText();
		String i = indirizzo.getText();
		if (p.compareTo("") != 0 && c.compareTo("") != 0 && i.compareTo("") != 0) {
			String[] address = i.split(" ");
			String request;
			Formatter formatter = new Formatter();
			if ( address.length == 2) 
				formatter.format("https://www.google.it/maps/search/farmacia+vicino+a+%s+%s,+%s,+%s/",address[0],address[1],c,p);
			else if (address.length == 3)
				formatter.format("https://www.google.it/maps/search/farmacia+vicino+a+%s+%s+%s,+%s,+%s/",address[0],address[1],address[2],c,p);
			request = formatter.toString();
			formatter.close();
			try {
				
				if(!Feedback.pingHost()) throw new NoInternetException();
				else java.awt.Desktop.getDesktop().browse(new URI(request));
			} catch(NoInternetException ex) { 
				Feedback.mostraErrore(ex.getMessage());
			} catch (IOException  | URISyntaxException e) {
				e.printStackTrace();
			}
		}
		
		else 
			Feedback.mostraErrore("Non hai inserito tutti i parametri");
		
			siglaProvincia.setText("");
			comune.setText("");
			indirizzo.setText("");

	}
	
	
	
	public void request(String farmacia, String farmaco) {
		this.farmacia = farmacia;
		this.farmaco = farmaco;
		this.searchAvailability();
	}
}
