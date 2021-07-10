package logic.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.model.EventoCliente;
import logic.model.EventoDAO;
import logic.model.Sessione;
import logic.model.SessioneCliente;
import java.io.IOException;
import logic.controller.ControllerCustomerEvents;
import logic.ingegnerizzazione.EventiUtenteBean;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.PrimaryStage;
import logic.ingegnerizzazione.RequirementException;
import javafx.scene.Scene;

public class GcCustomerEvent implements GraphicController, Observer {
	@FXML
	private Button risorse;
	@FXML
	private Button risorse2;
	@FXML
	private Button riciclaggio;
	@FXML
	private Button riciclaggio2;
	@FXML
	private Button account;
	@FXML
	private Button account2;
	@FXML
	private Button home;
	@FXML
	private Button home2;
	@FXML
	private Button myEvent;
	@FXML
	private Button eventiAttivi;
	@FXML
	private Button refresh;
	@FXML
	private Button join;
	@FXML
	private TextField tfEvento;
	
	
	@FXML
	private TableView<EventiUtenteBean> myEventoTb;
	@FXML
	private TableColumn<EventiUtenteBean, String> risorseCol1;
	@FXML
	private TableColumn<EventiUtenteBean, String> risorseCol2;
	@FXML
	private TableColumn<EventiUtenteBean, String> risorseCol3;
	@FXML
	private TableColumn<EventiUtenteBean, String> risorseCol4;
	@FXML
	private TableColumn<EventiUtenteBean, String> risorseCol5;
	@FXML
	private TableColumn<EventiUtenteBean, String> extra;
	
	private SessioneCliente sessione;
	private ControllerCustomerEvents controller = new ControllerCustomerEvents();
	
	
	
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
		
	}
	
	public void setData(Sessione cliente) {
		this.sessione = (SessioneCliente) cliente;
		sessione.setEventiAttiviFarmaciaAssociata(EventoDAO.allActiveEvents(sessione.getFarmaciaAssociata()));
		this.showResource();

	}
	

	public void joined() {
		try {
			Stage primaryStage = new Stage();
			Parent root = FXMLLoader.load(getClass().getResource("OK.fxml"));
			primaryStage.setTitle("Medictory");
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(new Scene(root, 314,209));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void joinEvent(ActionEvent event) {
		String n = tfEvento.getText();
		try {
				EventoCliente e = controller.addMyEvent(sessione, n);
				sessione.getEventi().add(e);
				if(e.setJoined(true)) {
					e.attach(this);
					e.notifica();
					this.joined();
				
				} else {
					Feedback.mostraErrore("Evento non disponibile");
				}
			}catch (InputException | RequirementException e) {
				Feedback.mostraErrore(e.getMessage());
		
			}finally {
				tfEvento.setText("");
			}

	}
	
	public void showResource() {
		
		ObservableList<EventiUtenteBean> list = FXCollections.observableArrayList();
		risorseCol1.setCellValueFactory(new PropertyValueFactory<>("Evento"));
		risorseCol2.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
		risorseCol3.setCellValueFactory(new PropertyValueFactory<>("Premio"));
		risorseCol4.setCellValueFactory(new PropertyValueFactory<>("DataInizio"));
		risorseCol5.setCellValueFactory(new PropertyValueFactory<>("DataFine"));
		
		
		
		
		if(extra != null) {
			extra.setCellValueFactory(new PropertyValueFactory<>("Requisiti"));
			 list.addAll(controller.findAllActiveEvents(sessione));
		}
		if(sessione != null && extra ==null) {
			 list.addAll(controller.findEvents(sessione));	
			
		}
		
		myEventoTb.setItems(list);
		

	}
	
	@FXML
	public void refreshPressed(ActionEvent event) {
		sessione.setEventiAttiviFarmaciaAssociata(EventoDAO.allActiveEvents(sessione.getFarmaciaAssociata()));
		for(EventoCliente e: sessione.getEventiAttiviFarmaciaAssociata()) {
			e.attach(this);
			e.notifica();
		}		
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
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccount.fxml");
	}
	
	@FXML
	public void account2Pressed(ActionEvent event) {
		this.accountPressed(event);
	}
	
	@FXML
	public void riciclaggioPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerRiciclaggio.fxml");
	}
	
	@FXML
	public void riciclaggio2Pressed(ActionEvent event) {
		this.riciclaggioPressed(event);
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
	public void eventiAttiviPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerEventi.fxml");
	}
	
	@FXML
	public void myEventPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerMyEvent.fxml");
	}

	@Override
	public void update() {
		this.showResource();
	}
}
