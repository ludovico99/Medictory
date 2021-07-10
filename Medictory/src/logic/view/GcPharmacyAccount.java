package logic.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;
import java.io.IOException;
import logic.controller.ControllerLogout;
import logic.controller.ControllerPharmacyAccount;
import logic.ingegnerizzazione.ListaClientiBean;
import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.PharmacyAccountBean;
import javafx.scene.Scene;

public class GcPharmacyAccount implements GraphicController, Observer{
	@FXML
	private Button clienti;
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
	private Label username;
	@FXML
	private Label count;
	@FXML
	private Label countBis;
	@FXML
	private Label usernameLb;
	@FXML
	private Label emailLb;
	@FXML
	private Label nomeLb;
	@FXML
	private Label indirizzoLb;
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
	private TableView<ListaClientiBean> clientiTb;
	@FXML
	private TableColumn<ListaClientiBean, String> usernameCol;
	@FXML
	private TableColumn<ListaClientiBean, String> emailCol;
	@FXML
	private TableColumn<ListaClientiBean, String> livelloCol;
	@FXML
	private TableColumn<ListaClientiBean, String> puntiCol;
	
	
	private SessioneFarmacia sessione;
	private ControllerPharmacyAccount controller = new ControllerPharmacyAccount();
	


	private void setPrimaryStage(Stage primaryStage, String file, Button btn) {
		try {
			
			FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
			Parent root = loader.load();
			
			if(btn.getId().compareTo("logout") == 0 || btn.getId().compareTo("logout2") ==0) {
				ControllerLogout controllerLogout = new ControllerLogout();
				controllerLogout.makeDataPharmacyPersistent(sessione);
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
	
	
	public void showResource() {
		
		
		if(sessione != null) {
			PharmacyAccountBean bean = new PharmacyAccountBean(sessione);
			usernameLb.setText(bean.getUsername());
			emailLb.setText(bean.getEmailFarmacia());
			nomeLb.setText(bean.getNamePharmacy());
			indirizzoLb.setText(bean.getAddress());
			count.setText(bean.getNumeroClienti());
		}
	}
	
	public void showResourceBis() {
		
		ObservableList<ListaClientiBean> list=FXCollections.observableArrayList();
		usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
		livelloCol.setCellValueFactory(new PropertyValueFactory<>("Livello"));
		puntiCol.setCellValueFactory(new PropertyValueFactory<>("Punti"));
		
		
		if(sessione != null) {
			 list.addAll( controller.findListOfCustomers(sessione/*, this*/));
			 clientiTb.setItems(list);
			 countBis.setText(Integer.toString(sessione.getClienti().size()));
		}
		
	}

	
	@FXML
	public void datiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccount.fxml", (Button)event.getSource());
	}
	
	@FXML
	public void clientiPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccountPage2.fxml", (Button)event.getSource());
	}

	@FXML
	public void homePressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepagePharmacy.fxml", (Button)event.getSource());
	}
	@FXML
	public void home2Pressed(ActionEvent event) {
		this.homePressed(event);
	}
	
	@FXML
	public void logoutPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml", (Button)event.getSource());
	}
	
	@FXML
	public void logout2Pressed(ActionEvent event) {
		this.logoutPressed(event);
	}
	
	public void setData(Sessione farma) {
		this.sessione = (SessioneFarmacia) farma;
		if(clientiTb != null) this.showResourceBis();
		else this.showResource();
	}

	@Override
	public void update() {
		if (count != null) this.showResource();
		else this.showResourceBis();
		
	}

}
