package logic.view;

import logic.controller.ControllerLogout;
import logic.ingegnerizzazione.ConnectionClose;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import logic.model.Connector;
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
	private static Connector connector = Connector.getConnectorInstance();

	
	@FXML
	public void pressed(ActionEvent event) {
		if(((Button)event.getSource()).getId().compareTo("btnSi")==0) {
			ControllerLogout controller = new ControllerLogout();
			if (discriminante == 0)
				controller.makeDataClientPersistent((SessioneCliente)sessione);
			else controller.makeDataPharmacyPersistent((SessioneFarmacia)sessione);
			ConnectionClose.closeConn(connector.getConnection());
		}
		Stage stage =(Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
		
	}

	public void setData(Sessione s,int i) {
			this.sessione = s;
			this.discriminante = i;
		
	}
		
}

