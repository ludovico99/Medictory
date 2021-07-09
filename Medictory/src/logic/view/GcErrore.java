package logic.view;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


public class GcErrore {
		
		@FXML
		private Button back;
		@FXML
		private Label messaggio;

		
		@FXML
		public void backPressed() {
			Stage stage = (Stage) back.getScene().getWindow();
			stage.close();
		}

		
		public void setError(String err) {
			this.messaggio.setText(err);
		}

}
