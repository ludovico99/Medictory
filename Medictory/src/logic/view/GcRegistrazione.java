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
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.Scene;
import logic.controller.ControllerCustomerAccount;
import logic.controller.ControllerRegistrazione;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.FarmaciaBean;

public class GcRegistrazione {
	

	@FXML
	private Button x;
	@FXML
	private Button farma;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	@FXML
	private TextField email;
	@FXML
	private TextField scelta;
	@FXML
	private Button done;
	@FXML
	private TableView<FarmaciaBean> table;
	@FXML
	private TableColumn<FarmaciaBean, String> usernameCol;
	@FXML
	private TableColumn<FarmaciaBean, String> nameCol;
	@FXML
	private TableColumn<FarmaciaBean, String> indirizzoCol;
	@FXML
	private TableColumn<FarmaciaBean, String> emailCol;
	
	private ControllerRegistrazione controller = new ControllerRegistrazione();
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			primaryStage.setTitle("Medictory");
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void xPressed(ActionEvent event) {
		
        Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml");
	}
	
	
	@FXML
	public void farmaPressed(ActionEvent event) {
		
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "Registrazione_farma.fxml");
	}
	

	@FXML
	public void tableStart() {
		
		
		ObservableList<FarmaciaBean> list = FXCollections.observableArrayList();
		usernameCol.setCellValueFactory(new PropertyValueFactory<>("Username"));
		nameCol.setCellValueFactory(new PropertyValueFactory<>("Nome"));
		indirizzoCol.setCellValueFactory(new PropertyValueFactory<>("Indirizzo"));
		emailCol.setCellValueFactory(new PropertyValueFactory<>("Email"));
		
		ControllerCustomerAccount controllerTable = new ControllerCustomerAccount();
		ArrayList<FarmaciaBean> request = (ArrayList<FarmaciaBean>) controllerTable.findListOfPharmacy();
		if(request != null) list.addAll(request);
		table.setItems(list);	
		
	}
	
	
	@FXML
	public void donePressed(ActionEvent event) {
		String u;
		String p;
		String e;
		String s;
		
		u = username.getText();
		p = password.getText();
		e = email.getText();
		s = scelta.getText();
		
		if(controller.registraCliente(u, p, e, s)) {
			
			Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
			setPrimaryStage(primaryStage, "progetto.fxml");
			GcOk o = new GcOk();
			o.mostrati();
		}
		else {
			username.setText("");
			password.setText("");
			email.setText("");
			scelta.setText("");
			Feedback.mostraErrore("Dati inseriti scorretti, riprovare");
			
		}
		
	}
	
}
