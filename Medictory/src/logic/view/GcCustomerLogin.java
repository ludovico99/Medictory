package logic.view;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import logic.model.SessioneCliente;
import logic.controller.ControllerLogin;
import logic.ingegnerizzazione.Feedback;
import logic.ingegnerizzazione.InputException;
import logic.ingegnerizzazione.LoginBean;

import java.io.IOException;
import javafx.scene.Scene;

public class GcCustomerLogin{
	
	@FXML
	private Button backButton;
	@FXML
	private Button loginButton;
	@FXML
	private TextField username;
	@FXML
	private PasswordField password;
	
	private String title = "Medictory";
	private LoginBean loginBean = new LoginBean();
	
	private void setPrimaryStage(Stage primaryStage, String file) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource(file));
			primaryStage.setTitle(title);
			primaryStage.setScene(new Scene(root, 600,400));
			primaryStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	
		
	
	public void loginPressed(ActionEvent event){
	
		loginBean.setUsername(username.getText());
		loginBean.setPassword(password.getText());
			
		
		ControllerLogin controllerAttuale = new ControllerLogin();		
				try {
					SessioneCliente s = controllerAttuale.loginCliente(loginBean);

					Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
					primaryStage.setOnCloseRequest( ev -> {
						try {
								Stage stage = new Stage();	
								FXMLLoader loader = new FXMLLoader(getClass().getResource("Exit.fxml"));
								Parent root = loader.load();
								GcExit controllerNext = loader.getController();
								stage.setTitle(title);
								stage.initStyle(StageStyle.UNDECORATED);
								stage.setScene(new Scene(root, 314, 209));
								stage.show();
								controllerNext.setData(s,0);
							} catch(IOException e) {
								e.printStackTrace();
							}			
					
					});
					FXMLLoader loader = new FXMLLoader(getClass().getResource("HomepageClient.fxml"));
					Parent root = loader.load();
					GcCustomerHomepage controllerNext = loader.getController();
					controllerNext.setData(s);
					primaryStage.setTitle(title);
					primaryStage.setScene(new Scene(root, 600,400));
					primaryStage.show();
				} catch(IOException e) {
					e.printStackTrace();
				} catch (InputException e1) {
					e1.printStackTrace();
					Feedback.mostraErrore(e1.getMessage());
					username.setText("");
					password.setText("");
				}
	}

	
	public void backPressed(ActionEvent event) {
		Stage primaryStage=(Stage)((Node)event.getSource()).getScene().getWindow();
		setPrimaryStage(primaryStage, "progetto.fxml");
	}

}
