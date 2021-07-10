package logic.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.model.FarmacoCliente;
import logic.model.Sessione;
import logic.model.SessioneCliente;
import java.io.IOException;
import java.time.LocalDate;
import logic.controller.ControllerCustomerResource;
import logic.ingegnerizzazione.RisorseUtenteBean;
import javafx.scene.Scene;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.PrimaryStage;

public class GcCustomerRisorse implements GraphicController, Observer {
	
	@FXML
	private DatePicker scad;
	@FXML
	private Button riciclaggio;
	@FXML
	private Button eventi;
	@FXML
	private Button account;
	@FXML
	private Button submit;
	@FXML
	private Button home;
	@FXML
	private Button search;
	@FXML
	private TextField farmaco;
	@FXML
	private TextField descrizione;
	@FXML
	private TextField quantitativo;
	@FXML
	private TextField scadenza;
	@FXML
	private TextField richiesta;
	@FXML
	private Hyperlink link;

	@FXML
	private TableView<RisorseUtenteBean> risorsetb;
	@FXML
	private TableColumn<RisorseUtenteBean, String> risorseCol1;
	@FXML
	private TableColumn<RisorseUtenteBean, String> risorseCol2;
	@FXML
	private TableColumn<RisorseUtenteBean, String> risorseCol3;
	@FXML
	private TableColumn<RisorseUtenteBean, String> risorseCol4;
	

	
	private SessioneCliente sessione;
	private ControllerCustomerResource controller = new ControllerCustomerResource();
	

	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
	}
	
	
	
	public void setData(Sessione cliente) {
		this.sessione = (SessioneCliente)cliente;
		if(sessione.getFarmaci() != null) {
			for (FarmacoCliente f : sessione.getFarmaci()) {
				if(f.getStato().compareTo("utilizzabile") == 0) f.attach(this);
			}
			this.showResource();
		}
	}
	
	
	@FXML
	public void submitPressed(ActionEvent event) {
		
		String f = farmaco.getText();
		String q = quantitativo.getText();
		String d = descrizione.getText();
		LocalDate scade = scad.getValue();
		
		try {
			if (f.compareTo("") != 0 && q.compareTo("") != 0 && scade != null) {
				FarmacoCliente newF = controller.addMedicine(sessione, f, d, scade.toString(), Integer.parseInt(q));
				newF.attach(this);
				newF.notifica();
				
			}
			else throw new InputException("Non hai inserito tutti i parametri");
		}catch (InputException ev){
			Feedback.mostraErrore(ev.getMessage());
		}finally {
			farmaco.setText("");
			quantitativo.setText("");
			descrizione.setText("");
			scad.setValue(null);
		}
	}
	
		@FXML
		public void searchPressed(ActionEvent event) {
		String farmacoRichiesto = richiesta.getText();
		richiesta.setText("");
		
		if(farmacoRichiesto.compareTo("") == 0 ) {
			Feedback.mostraErrore("Devi inserire un farmaco per poterlo cercare");
			return;
		}
		
		
		try {
			
			Stage primaryStage = new Stage();
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Disponibilita.fxml"));
			Parent root = loader.load();
			GcCustomerAvailability controllerNext = loader.getController();
			controllerNext.request(sessione.getFarmaciaAssociata(), farmacoRichiesto);
			primaryStage.initStyle(StageStyle.UNDECORATED);
			primaryStage.setScene(new Scene(root, 314, 209));
			primaryStage.show();
			
		} catch(IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public void showResource() {
		
		ObservableList<RisorseUtenteBean> list = FXCollections.observableArrayList();
		risorseCol1.setCellValueFactory(new PropertyValueFactory<>("Farmaco"));
		risorseCol2.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
		risorseCol3.setCellValueFactory(new PropertyValueFactory<>("Quantitativo"));
		risorseCol4.setCellValueFactory(new PropertyValueFactory<>("Scadenza"));
	
		if(sessione != null) {
			 list.addAll(controller.findResources(sessione));
			 risorsetb.setItems(list);
		
		}
	}
	
	@FXML
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccount.fxml");
	}
	
	@FXML
	public void homePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepageClient.fxml");
	}
	
	@FXML
	public void riciclaggioPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerRiciclaggio.fxml");
		
	}
	
	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerMyEvent.fxml");
	}


	@Override
	public void update () {
		showResource();
	}

}
