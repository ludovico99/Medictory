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
import logic.model.FarmacoCliente;
import logic.model.FarmacoClienteDAO;
import logic.model.Sessione;
import logic.model.SessioneCliente;
import java.util.List;

import logic.controller.ControllerCustomerRiciclaggio;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.PrimaryStage;
import logic.ingegnerizzazione.RiciclaggioUtenteBean;
import logic.ingegnerizzazione.StoricoUtenteBean;

public class GcCustomerRiciclaggio implements GraphicController, Observer {
	
	@FXML
	private Button risorse;
	@FXML
	private Button risorse2;
	@FXML
	private Button eventi;
	@FXML
	private Button eventi2;
	@FXML
	private Button account;
	@FXML
	private Button account2;
	@FXML
	private Button home;
	@FXML
	private Button home2;
	@FXML
	private Button storico;
	@FXML
	private Button ricicla2;
	@FXML
	private Button ricicla;
	@FXML
	private Button ritiro;
	@FXML
	private Button refresh;
	@FXML
	private TextField riciclaTf;
	@FXML
	private TableView<RiciclaggioUtenteBean> riciclaggiotb;
	@FXML
	private TableColumn<RiciclaggioUtenteBean, String> riciclaggioCol1;
	@FXML
	private TableColumn<RiciclaggioUtenteBean, String> riciclaggioCol2;
	@FXML
	private TableColumn<RiciclaggioUtenteBean, String> riciclaggioCol3;
	
	
	@FXML
	private TableView<StoricoUtenteBean> storicotb;
	@FXML
	private TableColumn<StoricoUtenteBean, String> storicoCol1;
	@FXML
	private TableColumn<StoricoUtenteBean, String> storicoCol2;
	@FXML
	private TableColumn<StoricoUtenteBean, String> storicoCol3;
	
	
	private SessioneCliente sessione;
	private ControllerCustomerRiciclaggio controller = new ControllerCustomerRiciclaggio();
	
	

	
	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
		
	}
	
	
	public void setData(Sessione cliente) {
		this.sessione = (SessioneCliente) cliente;
		if (sessione.getFarmaci() !=null) {
			for (FarmacoCliente f : sessione.getFarmaci()) {
				if(f.getStato().compareTo("utilizzabile") != 0) f.attach(this);
			}
		
		
			if(riciclaggiotb != null) this.showResource();
			else this.showResourceBis();
		}
	}
	
	public void showResource() {
		
		ObservableList<RiciclaggioUtenteBean> list = FXCollections.observableArrayList();
		riciclaggioCol1.setCellValueFactory(new PropertyValueFactory<>("Drug"));
		riciclaggioCol2.setCellValueFactory(new PropertyValueFactory<>("Detail"));
		riciclaggioCol3.setCellValueFactory(new PropertyValueFactory<>( "Amount"));
		
		if(sessione != null) {
			 list.addAll(controller.findResources(sessione));	
			riciclaggiotb.setItems(list);
		}
	}
	
	public void showResourceBis() {
		
		ObservableList<StoricoUtenteBean> list = FXCollections.observableArrayList();
		storicoCol1.setCellValueFactory(new PropertyValueFactory<>("Farmaco"));
		storicoCol2.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
		storicoCol3.setCellValueFactory(new PropertyValueFactory<>("Verifica"));
		
		
		if(sessione != null) {
			 list.addAll(controller.findResourcesBis(sessione));
			 storicotb.setItems(list);
		}
	}
	
	
	
	@FXML
	public void riciclaPressed(ActionEvent event) {
		
		String farmaco = riciclaTf.getText();
		riciclaTf.setText("");	
		List<FarmacoCliente> farmaci = sessione.getFarmaci();
		if(farmaci == null) Feedback.mostraErrore("Non possiedi nessun farmaco");
		else {
				try {
					controller.ricicla(sessione, farmaco);
				} catch (InputException e) {
					Feedback.mostraErrore(e.getMessage());
				}				
			}
	}

	
	
	@FXML
	public void refreshPressed(ActionEvent event) {
		
		
		sessione.setFarmaci(FarmacoClienteDAO.myFarmaciCliente(sessione.getUsername()));
		for(FarmacoCliente f: sessione.getFarmaci()) {
			f.attach(this);
			f.notifica();
		}
		controller.incrementaPunteggio(sessione);
	}
	
	
	
	@FXML
	public void ritiroPressed(ActionEvent event) {

		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "Appuntamento.fxml");

		
		
	}
	
	

	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerMyEvent.fxml");
	}
	
	@FXML
	public void eventi2Pressed(ActionEvent event) {
		this.eventiPressed(event);
	}
	
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccount.fxml");
	}
	
	@FXML
	public void account2Pressed(ActionEvent event) {
		this.accountPressed(event);
		}
	
	@FXML
	public void risorsePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "Risorse.fxml");
		
	}
	
	@FXML
	public void risorse2Pressed(ActionEvent event) {
		this.risorsePressed(event);
	}
	
	@FXML
	public void homePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepageClient.fxml");
	}
	
	@FXML
	public void home2Pressed(ActionEvent event) {
		this.homePressed(event);
		}
	
	@FXML
	public void ricicla2Pressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerRiciclaggio.fxml");
		
	}
	
	@FXML
	public void storicoPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerStoricoRicicli.fxml");
		
	}


	@Override
	public void update() {
		if(riciclaggiotb != null) this.showResource();
		else this.showResourceBis();		
	}
}
