package logic.view;

import logic.controller.ControllerPharmacyAppuntamenti;
import logic.ingegnerizzazione.PharmacyAppuntamentiBean;
import logic.ingegnerizzazione.PrimaryStage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;

public class GcPharmacyAppuntamenti implements GraphicController {
	
	@FXML
	private Button eventi;
	@FXML
	private Button verifica;
	@FXML
	private Button ritiro;
	@FXML
	private Button risorse;
	@FXML
	private Button account;
	@FXML
	private Button home;
	@FXML 
	private Button refresh;
	
	@FXML
	private TableView<PharmacyAppuntamentiBean> richiesteTv;
	@FXML
	private TableColumn<PharmacyAppuntamentiBean, String> utenteCol;
	@FXML
	private TableColumn<PharmacyAppuntamentiBean, String> emailCol;
	@FXML
	private TableColumn<PharmacyAppuntamentiBean, String> cittaCol;
	@FXML
	private TableColumn<PharmacyAppuntamentiBean, String> indirizzoCol;
	@FXML
	private TableColumn<PharmacyAppuntamentiBean, String> dataCol;
	
	private SessioneFarmacia sessione;

	private ControllerPharmacyAppuntamenti controller = new ControllerPharmacyAppuntamenti();
	
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
			
		
	}
	
	@FXML
	public void eventiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyEvent.fxml");
	}
	
	@FXML
    public void refreshPressed(ActionEvent event) {
        if(richiesteTv != null) {
            this.showResource();
        }
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
	public void risorsePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "RisorseFarmacia.fxml");
	}
	
	@FXML
	public void verificaPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "GestioneFarmacie.fxml");
	}

	@Override
	public void setData(Sessione f) {
		
		this.sessione = (SessioneFarmacia) f;
		  if(richiesteTv != null) {
			this.showResource();
		}
		
	}
	
    public void showResource() {
		
		ObservableList<PharmacyAppuntamentiBean> list = FXCollections.observableArrayList();
		utenteCol.setCellValueFactory(new PropertyValueFactory<>("Utente"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
		cittaCol.setCellValueFactory(new PropertyValueFactory<>("City"));
		indirizzoCol.setCellValueFactory(new PropertyValueFactory<>("Address"));
		dataCol.setCellValueFactory(new PropertyValueFactory<>("Data"));
		
		
		if(sessione != null && sessione.getClienti() != null) {		
				list.addAll(controller.findListOfRitiri(sessione)); 
				richiesteTv.setItems(list);		
		}
   }
}
	
	
	

