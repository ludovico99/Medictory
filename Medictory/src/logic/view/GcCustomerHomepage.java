package logic.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.stage.Stage;
import logic.ingegnerizzazione.PrimaryStage;
import logic.model.Sessione;
import logic.model.SessioneCliente;

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
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
		
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
