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
import logic.model.FarmacoFarmacia;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;
import java.time.LocalDate;
import logic.controller.ControllerPharmacyResource;
import logic.ingegnerizzazione.DataNotFoundException;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.PrimaryStage;
import logic.ingegnerizzazione.RisorseFarmaciaBean;

public class GcPharmacyRisorse implements GraphicController, Observer{
	
	@FXML
	private Button eventi;
	@FXML
	private Button ritiro;
	@FXML
	private Button gestione;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML
	private Button submit;
	@FXML
	private DatePicker scadenzaDp;
	@FXML
	private TableView<RisorseFarmaciaBean> risorseTb;
	@FXML
	private TableColumn<RisorseFarmaciaBean, String> farmacoCol;
	@FXML
	private TableColumn<RisorseFarmaciaBean, String> quantitativoCol;
	@FXML
	private TableColumn<RisorseFarmaciaBean, String> descrizioneCol;
	@FXML
	private TextField farmacoLb;
	@FXML
	private TextField quantitativoLb;
	@FXML
	private TextField scadenzaLb;
	@FXML
	private TextField descrizioneLb;
	
	
	private SessioneFarmacia sessione;
	
	private ControllerPharmacyResource controller = new ControllerPharmacyResource();
	
	
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
	}
	
	
	public void showResource() {
		
		ObservableList<RisorseFarmaciaBean> list = FXCollections.observableArrayList();
		farmacoCol.setCellValueFactory(new PropertyValueFactory<>("Farmaco"));
		quantitativoCol.setCellValueFactory(new PropertyValueFactory<>("Quantitativo"));
		descrizioneCol.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
		
		
	
		if(sessione != null) {
			list.addAll(controller.findResources(sessione));
			risorseTb.setItems(list);
		
		}
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
	public void homePressed(ActionEvent event) {
		
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepagePharmacy.fxml");
	}
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccount.fxml");
	}
	
	@FXML
	public void submitPressed(ActionEvent event) {
		
		String f = farmacoLb.getText();
		String q = quantitativoLb.getText();
		String d = descrizioneLb.getText();
		LocalDate scade = scadenzaDp.getValue();
		try {
			if(f.compareTo("") != 0 && q.compareTo("") != 0 &&  d.compareTo("") == 0 &&  scade == null) {
				controller.cambiaQuantita(sessione, f, Integer.parseInt(q));
			}
			else if(f.compareTo("") == 0 || q.compareTo("") == 0 ||  d.compareTo("") == 0 ||  scade == null)  {
				throw new InputException("Non hai inserito tutti i parametri");
			}
			else {
				FarmacoFarmacia newF =controller.addMedicine(sessione, f, Integer.parseInt(q) , d, scade.toString());
				newF.attach(this);
				newF.notifica();
			}
		}catch(InputException | DataNotFoundException ex) {
			Feedback.mostraErrore(ex.getMessage());
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		
		farmacoLb.setText("");
		quantitativoLb.setText("");
		descrizioneLb.setText("");
		scadenzaDp.setValue(null);
	}

	@Override
	public void setData(Sessione farmacia) {
		this.sessione = (SessioneFarmacia) farmacia;
		if(sessione.getFarmaci() != null) {
			for (FarmacoFarmacia f : sessione.getFarmaci()) {
				f.attach(this);
			}
		}
		this.showResource();
		
	}

	@Override
	public void update() {
		showResource();
		
	}
	
}
