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
import javafx.stage.StageStyle;
import logic.model.AbstractState;
import logic.model.EventoFarmacia;
import logic.model.Sessione;
import logic.model.SessioneFarmacia;
import logic.model.StatoIniziale;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import logic.controller.ControllerPharmacyEvent;
import logic.ingegnerizzazione.DateException;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.NoInternetException;
import logic.ingegnerizzazione.Observer;
import logic.ingegnerizzazione.PharmacyAllEventBean;
import logic.ingegnerizzazione.PrimaryStage;
import javafx.scene.Scene;

public class GcPharmacyEvent implements GraphicController, Observer, Runnable {
	
	@FXML
	private DatePicker start;
	@FXML
	private DatePicker end;
	@FXML
	private Button gestione;
	@FXML
	private Button gestione2;
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
	private Button allEvent;
	@FXML
	private Button creaEvento;
	@FXML
	private Button createEvent;
	@FXML
	private Button elimina;
	
	@FXML
	private TextField nomeTf;
	@FXML
	private TextField dettagliTf;
	@FXML
	private TextField livelloTf;
	@FXML
	private TextField inizioTf;
	@FXML
	private TextField fineTf;
	@FXML
	private ComboBox<String> premioCb;
	@FXML
	private TextField eliminaTf;
	
	@FXML
	private TableView<PharmacyAllEventBean> eventiTb;
	@FXML
	private TableColumn<PharmacyAllEventBean, String> eventoCol;
	@FXML
	private TableColumn<PharmacyAllEventBean, String> descrizioneCol;
	@FXML
	private TableColumn<PharmacyAllEventBean, String> requisitiCol;
	@FXML
	private TableColumn<PharmacyAllEventBean, String> inizioCol;
	@FXML
	private TableColumn<PharmacyAllEventBean, String> fineCol;
	@FXML
	private TableColumn<PharmacyAllEventBean, String> premioCol;
	
	private SessioneFarmacia sessione;
	private ControllerPharmacyEvent controller = new ControllerPharmacyEvent();
	private boolean first = true;
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		FXMLLoader loader = new FXMLLoader(getClass().getResource(file));
		PrimaryStage.setPrimaryStage(primaryStage, loader, sessione);
		
	}	
	
	
	private void completed() {
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
	
	public void setData(Sessione farma) {
		this.sessione = (SessioneFarmacia) farma;
		if (eventiTb != null) {
			if (first && sessione.getEventi() != null) {
				for (EventoFarmacia e : sessione.getEventi()) {
					AbstractState state = e.getState();
					if(state.getClass() == StatoIniziale.class){
						e.attach(this);
				}	
			}
			first = false;
			}
			
			this.showResource();	
		}
		if (premioCb != null) {
			String[] premi = {"Coupon","Sconto 50%","Analisi Gratis"};		
			premioCb.setItems(FXCollections.observableArrayList(premi));
		}
		
		try{
			if(!Feedback.pingHost()) {
				throw new NoInternetException();
			}
			else new Thread(this).start();
		}catch(NoInternetException ex) {
			Feedback.mostraErrore(ex.getMessage() + "\nImpossibile procedere con le premiazioni");
		}

	}

	@Override
	public void update() {
		this.showResource();
		
	}
	
	@FXML
	public void deletePressed(ActionEvent e) {
		String n = eliminaTf.getText();
		try {
				EventoFarmacia evento = controller.deleteEvent(sessione,n);
				if(evento != null) {
					evento.attach(this);
					evento.notifica();
					completed();
				}
			}catch (InputException ex) {
				Feedback.mostraErrore(ex.getMessage());
			}
		eliminaTf.setText("");
		
		
	}
	

	
	
	public void showResource() {
		if ( eventoCol != null) {
			ObservableList<PharmacyAllEventBean> list = FXCollections.observableArrayList();
			eventoCol.setCellValueFactory(new PropertyValueFactory<>("Evento"));
			descrizioneCol.setCellValueFactory(new PropertyValueFactory<>("Descrizione"));
			requisitiCol.setCellValueFactory(new PropertyValueFactory<>("Requisiti"));
			premioCol.setCellValueFactory(new PropertyValueFactory<>("Premio"));
			inizioCol.setCellValueFactory(new PropertyValueFactory<>("Inizio"));
			fineCol.setCellValueFactory(new PropertyValueFactory<>("Fine"));
	
			if(sessione != null) {
				list.addAll(controller.findEvent(sessione));
				eventiTb.setItems(list);
				}
			}
	}
	
	@FXML
	public void ritiroPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "FarmaciaAppuntamento.fxml");
		
	}
	
	@FXML
	public void gestionePressed(ActionEvent event) {
		
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "GestioneFarmacie.fxml");
	}
	
	@FXML
	public void gestione2Pressed(ActionEvent event) {
		this.gestionePressed(event);
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
	public void allEventPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAllEvent.fxml");
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
	public void accountPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyAccount.fxml");
	}
	
	@FXML
	public void account2Pressed(ActionEvent event) {
		this.accountPressed(event);
		}
	
	
	@FXML
	public void creaPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "PharmacyEvent.fxml");
	}
	
	
	@FXML
	public void createEventPressed() {
		String n = nomeTf.getText();
		String p = premioCb.getValue();
		String d = dettagliTf.getText();
		String l = livelloTf.getText();
		LocalDate in = start.getValue();
		LocalDate fin = end.getValue();
		
		try {
			if(in != null && in.isAfter(fin)) {
				throw new DateException("La data di inizio deve precedere quella di fine");
			} else if(n.compareTo("") == 0 || p==null || d.compareTo("") == 0 || l.compareTo("") == 0 || in == null || fin == null) {
				throw new InputException("Non hai inserito tutti i parametri");
			} else {
					List<String> informazioni = new ArrayList<>();
					informazioni.add(n);
					informazioni.add(d);
					informazioni.add(p);
					informazioni.add(in.toString());
					informazioni.add(fin.toString());
					
					EventoFarmacia ev;
					if ( (ev = controller.addEvent (sessione , informazioni, Integer.parseInt(l)))!= null) {
						ev.attach(this);
						ev.notifica();
						this.completed();
					}
				}
			
		}catch (InputException ex) {
			Feedback.mostraErrore(ex.getMessage());
			
		}finally {
			nomeTf.setText("");
			dettagliTf.setText("");
			livelloTf.setText("");
			start.setValue(null);
			end.setValue(null);
		}
		
	}

	@Override
	public void run() {
	 controller.premiazione(sessione);
	}
}
