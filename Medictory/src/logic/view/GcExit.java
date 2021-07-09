package logic.view;

import logic.controller.ControllerLogout;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneCliente;
import logic.model.SessioneFarmacia;



public class GcExit{
	
	@FXML
	private Button btnSi;
	@FXML
	private Button btnNo;
	
	private Sessione sessione;
	private int discriminante;

	
	@FXML
	public void pressed(ActionEvent event) {
		if(((Button)event.getSource()).getId().compareTo("btnSi")==0) {
			ControllerLogout controller = new ControllerLogout();
			if (discriminante == 0)
				controller.makeDataClientPersistent((SessioneCliente)sessione);
			else controller.makeDataPharmacyPersistent((SessioneFarmacia)sessione);
		}
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		
	}

	public void setData(Sessione s,int i) {
			this.sessione = s;
			this.discriminante = i;
		
	}
		
}

