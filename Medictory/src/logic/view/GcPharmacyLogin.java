package logic.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.model.SessioneFarmacia;
import logic.controller.ControllerLogin;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.LoginBean;

import java.io.IOException;
import javafx.scene.Scene;

public class GcPharmacyLogin {
	
	@FXML
	private Button backButton1;
	@FXML
	private Button pharmacyLogin;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	private static final String TITOLO = "Medictory";
	private LoginBean loginBean = new LoginBean();

	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			primaryStage.setTitle(TITOLO);
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	@FXML
	public void loginPressed(ActionEvent event){


		loginBean.setUsername(username.getText());
		loginBean.setPassword(password.getText());
		
		
		
		
		
		ControllerLogin controllerAttuale = new ControllerLogin();
		SessioneFarmacia sf;								
			try {
				sf = controllerAttuale.loginFarmacia(loginBean);		
				Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
				primaryStage.setOnCloseRequest(ev -> {
							try {
									Stage stage = new Stage();	
									FXMLLoader loader = new FXMLLoader(getClass().getResource("Exit.fxml"));
									Parent root = loader.load();
									GcExit controllerNext = loader.getController();
									stage.setTitle(TITOLO);
									stage.initStyle(StageStyle.UNDECORATED);
									stage.setScene(new Scene(root, 314, 209));
									stage.show();
									controllerNext.setData(sf,1);
								} catch(IOException e) {
									e.printStackTrace();
								}			
						
						});
				FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepagePharmacy.fxml"));
				Parent root = loader.load();
				GcPharmacyHomepage controllerNext = loader.getController();
				controllerNext.setData(sf);
				primaryStage.setTitle(TITOLO);
				primaryStage.setScene(new Scene(root, 600,400));
				primaryStage.show();
			} catch(IOException e) {
				e.printStackTrace();
			}catch (InputException e1) {
				e1.printStackTrace();
				Feedback.mostraErrore(e1.getMessage());	
				username.setText("");
				password.setText("");
			}
	}
	
	@FXML
	public void back1Pressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml");
	}

}
