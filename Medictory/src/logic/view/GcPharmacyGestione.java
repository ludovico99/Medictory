package logic.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;
import java.io.IOException;
import logic.controller.ControllerPharmacyGestione;
import logic.ingegnerizzazione.DataNotFoundException;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.PharmacyGestioneBean;
import javafx.scene.Scene;

public class GcPharmacyGestione implements GraphicController{
	
	@FXML
	private Button eventi;
	@FXML
	private Button eventi2;
	@FXML
	private Button ritiro;
	@FXML
	private Button ritiro2;
	@FXML
	private Button risorse;
	@FXML
	private Button risorse2;
	@FXML
	private Button account;
	@FXML
	private Button account2;
	@FXML
	private Button home;
	@FXML
	private Button home2;
	@FXML
	private Button riciclaggi;
	@FXML
	private Button premiazioni;
	@FXML
	private Button verifica;
	@FXML
	private Button premia;
	@FXML
	private TextField verificaUtenteTf;
	@FXML
	private TextField verificaFarmacoTf;
	@FXML
	private TextField verificaScadenzaTf;
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
		String scadenza = verificaScadenzaTf.getText();
		if(sessione == null) return;
		
		try {
			if(utente.compareTo("") == 0 || farmaco.compareTo("") == 0 || scadenza.compareTo("") == 0) throw new InputException("Non hai inserito tutti i parametri");
			else {
				for(PharmacyGestioneBean tm: autenticazioniTb.getItems()) {
					if(tm.getUtente().compareToIgnoreCase(utente) == 0 && tm.getFarmaco().compareToIgnoreCase(farmaco) == 0 && tm.getScadenza().compareToIgnoreCase(scadenza) == 0) {
						
						//this is the table's row that has to be verified
						controller.verifica(sessione, utente, farmaco, scadenza, tm.getQuantitativo());
						autenticazioniTb.getItems().remove(tm);
						return;
					}
				}
				throw new DataNotFoundException("Impossibile trovare l'elemento inserito");
			}
		}catch (InputException | DataNotFoundException ex) {
			Feedback.mostraErrore(ex.getMessage());
		}finally {
			verificaFarmacoTf.setText("");
			verificaUtenteTf.setText("");
			verificaScadenzaTf.setText("");
		}
	}
		
	
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccount.fxml");
	}
	
	@FXML
	public void account2Pressed(ActionEvent event) {
		this.accountPressed(event);
	}
	
	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyEvent.fxml");
	}
	
	@FXML
	public void eventi2Pressed(ActionEvent event) {
		this.eventiPressed(event);
	}
	
	@FXML
	public void risorsePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "RisorseFarmacia.fxml");
	}
	
	@FXML
	public void risorse2Pressed(ActionEvent event) {
		this.risorsePressed(event);
	}
	
	@FXML
	public void homePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepagePharmacy.fxml");
	}
	
	@FXML
	public void home2Pressed(ActionEvent event) {
		this.homePressed(event);
	}
	
	@FXML
	public void premiazioniPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "GestionePremiazioniFarmacia.fxml");
	}
	
	@FXML
	public void riciclaggiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "GestioneFarmacie.fxml");
	}
	
	@FXML
	public void ritiroPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "FarmaciaAppuntamento.fxml");
		
	}
	
	public void setData(Sessione farma) {
		this.sessione = (SessioneFarmacia) farma;
		if(autenticazioniTb != null) {
			this.showResource();
		}
	}
	
}
