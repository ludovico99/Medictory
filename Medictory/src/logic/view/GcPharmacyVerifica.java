package logic.view;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;
import java.time.LocalDate;
import logic.controller.ControllerPharmacyGestione;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.PharmacyGestioneBean;
import logic.ingegnerizzazione.PrimaryStage;

public class GcPharmacyVerifica implements GraphicController{
	
	@FXML
	private Button eventi;
	@FXML
	private Button ritiro;
	@FXML
	private Button risorse;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML
	private Button verifica;
	@FXML
	private Button refresh;
	@FXML
	private TextField verificaUtenteTf;
	@FXML
	private TextField verificaFarmacoTf;
	@FXML
	private DatePicker verificaScadenzaDp;
	@FXML
	private TableView<PharmacyGestioneBean> autenticazioniTb;
	@FXML
	private TableColumn<PharmacyGestioneBean, String> farmacoCol;
	@FXML
	private TableColumn<PharmacyGestioneBean, String> utenteCol;
	@FXML
	private TableColumn<PharmacyGestioneBean, String> scadenzaCol;
	@FXML
	private TableColumn<PharmacyGestioneBean, String> quantitativoCol;
	
	private SessioneFarmacia sessione;
	private ControllerPharmacyGestione controller = new ControllerPharmacyGestione();


	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
		
	}
	
	
	public void showResource() {
		
		ObservableList<PharmacyGestioneBean> list = FXCollections.observableArrayList();
		farmacoCol.setCellValueFactory(new PropertyValueFactory<>("Farmaco"));
		utenteCol.setCellValueFactory(new PropertyValueFactory<>("Utente"));
		scadenzaCol.setCellValueFactory(new PropertyValueFactory<>("Scadenza"));
		quantitativoCol.setCellValueFactory(new PropertyValueFactory<>("Quantitativo"));
		
		if(sessione != null && sessione.getClienti() != null) {			// altrimenti significa che la farmacia non ha clienti quindi non deve inizializzare la tabella

				list.addAll(controller.findResources(sessione));
				autenticazioniTb.setItems(list);		
		}
	}
	
	@FXML
	public void verificaPressed(ActionEvent event) {
		
		String utente = verificaUtenteTf.getText();
		String farmaco = verificaFarmacoTf.getText();
		LocalDate scade = verificaScadenzaDp.getValue();
		verificaFarmacoTf.setText("");
		verificaUtenteTf.setText("");
		verificaScadenzaDp.setValue(null);
		
		if(sessione == null) return;
		
		try {
			if(utente.compareTo("") == 0 || farmaco.compareTo("") == 0 || scade == null) {
				throw new InputException("Inserire i parametri mancanti");
			}
			else {
				for(PharmacyGestioneBean tm: autenticazioniTb.getItems()) {
					if(tm.getUtente().compareToIgnoreCase(utente) == 0 && tm.getFarmaco().compareToIgnoreCase(farmaco) == 0 && tm.getScadenza().compareToIgnoreCase(scade.toString()) == 0) {
						
						//this is the table's row that has to be verified
						controller.verifica(sessione, utente, farmaco, scade.toString(), tm.getQuantitativo());
						autenticazioniTb.getItems().remove(tm);
						return;
					}
				}
				throw new InputException("Impossibile trovare l'elemento inserito");
			}
		}catch (InputException e) {
			Feedback.mostraErrore(e.getMessage());
		}
	}
		
	
	@FXML
	public void refreshPressed(ActionEvent event) {
		if(autenticazioniTb != null) {
			this.showResource();
		}
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
	public void risorsePressedA(ActionEvent e) {
		Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "RisorseFarmacia.fxml");
	}
	
	@FXML
	public void ritiroPressedA(ActionEvent e) {
		Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "FarmaciaAppuntamento.fxml");
		
	}
	
	@FXML
	public void homePressedA(ActionEvent e) {
		Stage primaryStage=(Stage)((Node)e.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepagePharmacy.fxml");
	}
	
	

	public void setData(Sessione farma) {
		this.sessione = (SessioneFarmacia) farma;
		if(autenticazioniTb != null) {
			this.showResource();
		}
	}
	
}
