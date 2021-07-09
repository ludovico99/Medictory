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
import logic.model.SessioneCliente;
import java.io.IOException;
import logic.controller.ControllerCustomerAccount;
import logic.controller.ControllerLogout;
import logic.ingegnerizzazione.CustomerAccountBean;
import logic.ingegnerizzazione.FarmaciaBean;
import logic.ingegnerizzazione.Observer;
import javafx.scene.Scene;

public class GcCustomerAccount  implements GraphicController, Observer {
	@FXML
	private Button farma;
	@FXML
	private Button dati;
	@FXML
	private Button home;
	@FXML
	private Button home2;
	@FXML
	private Button logout;
	@FXML
	private Button logout2;
	@FXML
	private Label usernameLabel;
	@FXML
	private Label emailLabel;
	@FXML
	private Label farmaciaLabel;
	@FXML
	private Label puntiLb;
	@FXML
	private Label livelloLb;
	@FXML
	private Label ptLabel;
	@FXML
	private Label lvLabel;
	@FXML
	private TableView<FarmaciaBean> farmacieTb;
	@FXML
	private TableColumn<FarmaciaBean, String> usernameCol;
	@FXML
	private TableColumn<FarmaciaBean, String> nomeCol;
	@FXML
	private TableColumn<FarmaciaBean, String> emailCol;
	@FXML
	private TableColumn<FarmaciaBean, String> indirizzoCol;
	
	private SessioneCliente sessione;
	private ControllerCustomerAccount controller = new ControllerCustomerAccount();
	
	
	
	private void setPrimaryStage(Stage primaryStage, String file, Button btn) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
			Parent root = loader.load();
			
			if(btn.getId().compareTo("logout") == 0 || btn.getId().compareTo("logout2") ==0) {
				ControllerLogout controllerLogout = new ControllerLogout();
				controllerLogout.makeDataClientPersistent(sessione);
			}
			else {
				GraphicController controllerNext = loader.getController();
				controllerNext.setData(sessione);
			}
			primaryStage.setTitle("Medictory");
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
			
			
		} catch(IOException e) {
			e.printStackTrace();
			}
		
	}
	
	
	
	public void setData(Sessione cliente) {
		this.sessione = (SessioneCliente) cliente;
		if(farmacieTb != null)
			this.showResourceBis();
		else this.showResource();
		
	}
	
	@Override
	public void update() {
		if (puntiLb!=null) this.showResource();
		else this.showResourceBis();
		
	}
	
	public void showResource() {
		
		
		if(sessione != null) {
			CustomerAccountBean bean = new CustomerAccountBean(sessione);
		
			usernameLabel.setText(bean.getUsername());
			emailLabel.setText(bean.getEmail());
			farmaciaLabel.setText(bean.getFarmaciaAssociata());
			livelloLb.setText(bean.getLivello());
		
		
			String punti = bean.getPunti();
			String[] parts = puntiLb.getText().split("/");
			parts[0] = punti;
			parts[1] = Integer.toString(sessione.getLivello()*100);
			puntiLb.setText(parts[0]+"/"+parts[1]);
		}
	}
	
	public void showResourceBis() {
		
		ObservableList<FarmaciaBean> list = FXCollections.observableArrayList();
		usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
		nomeCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		indirizzoCol.setCellValueFactory(new PropertyValueFactory<>("Indirizzo"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
		
		
		if(sessione != null) {
			list.addAll(controller.findListOfPharmacy());
			farmacieTb.setItems(list);
		
		
			lvLabel.setText(Integer.toString(sessione.getLivello()));
			String punti = Integer.toString(sessione.getPunteggio());
			String[] parts = ptLabel.getText().split("/");
			parts[0] = punti;
			parts[1] = Integer.toString(sessione.getLivello()*100);
			ptLabel.setText(parts[0]+"/"+parts[1]);
		}
		
	}
	
	@FXML
	public void datiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccount.fxml",(Button)event.getSource());
	}
	
	@FXML
	public void farmaPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "CustomerAccountPage2.fxml",(Button)event.getSource());
	}

	@FXML
	public void homePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepageClient.fxml",(Button)event.getSource());
	}
	@FXML
	public void home2Pressed(ActionEvent event) {
		this.homePressed(event);
	}
	
	@FXML
	public void logoutPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml",(Button)event.getSource());
	}
	
	@FXML
	public void logout2Pressed(ActionEvent event) {
		this.logoutPressed(event);
	}

}
