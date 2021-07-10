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
	private Button datiF;
	@FXML
	private Button homeF;
	@FXML
	private Button home2F;
	@FXML
	private Button logoutF;
	@FXML
	private Button logout2F;
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
	private Label usernameLabelF;
	@FXML
	private Label emailLabelF;
	@FXML
	private Label farmaciaLabelF;
	@FXML
	private Label puntiLbF;
	@FXML
	private Label livelloLbF;
	@FXML
	private Label ptLabelF;
	@FXML
	private Label lvLabelF;
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
			
			if(btn.getId().compareTo("logoutF") == 0 || btn.getId().compareTo("logout2F") ==0) {
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
	public void clientiPressedF(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccountPage2.fxml", (Button)event.getSource());
	}

	@FXML
	public void homePressedF(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "HomepagePharmacy.fxml", (Button)event.getSource());
	}
	@FXML
	public void home2PressedF(ActionEvent event) {
		this.homePressedF(event);
	}
	
	@FXML
	public void logoutPressedF(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml", (Button)event.getSource());
	}
	
	@FXML
	public void logout2PressedF(ActionEvent event) {
		this.logoutPressedF(event);
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
