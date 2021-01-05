package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import entity.ActiveMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


/**
 * This class represent the screen used to login
 *
 */

public class LoginScreen implements Initializable {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private Label errorLabel;
	@FXML private JFXTextField username;
	@FXML private JFXPasswordField password;
	@FXML private JFXButton btn_login;
	private double x , y;

	ActiveMember user;
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	@FXML
	void close() {
		Stage stage = (Stage) btn_login.getScene().getWindow();
		stage.close();
	}

	
	@FXML
	void mousepressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}

	
	@FXML
	void dragged(MouseEvent event) {
		Stage stage = (Stage) btn_login.getScene().getWindow();;
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}


	/**
	 * Opens the menu window when the "login" button is pressed
	 * @param event
	 * @throws IOException
	 */
	
	@FXML
	void login(ActionEvent event) throws IOException { 
		if(event.getSource() == btn_login)
			try {
				String user = username.getText();
				String pass = password.getText();
				ViewLogic.user = new ActiveMember(user, pass);
				
				if(user != null && pass.equals("CampaignM")) {
					close();
					ViewLogic.MenuWindow();
				}
				else if(user != null && pass.equals("BranchM")) {
					close();
					ViewLogic.MenuWindowBranchM();
				}
				else if(user != null && pass.equals("TransportM")) {
					close();
					ViewLogic.MenuWindowTransportM();
				}
				else if(user != null && pass.equals("Member")) {
					close();
					ViewLogic.MenuWindowMember();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username or password.");
					alert.showAndWait();
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Opens the menu window when "enter" is pressed
	 * @param event
	 */
	
    @FXML
    void loginPressed(KeyEvent event) {
    	if(event.getCode() == KeyCode.ENTER)
			try {
				String user = username.getText();
				String pass = password.getText();
				ViewLogic.user = new ActiveMember(user, pass);
				
				if(user != null && pass.equals("CampaignM")) {
					close();
					ViewLogic.MenuWindow();
				}
				else if(user != null && pass.equals("BranchM")) {
					close();
					ViewLogic.MenuWindowBranchM();
				}
				else if(user != null && pass.equals("TransportM")) {
					close();
					ViewLogic.MenuWindowTransportM();
				}
				else if(user != null && pass.equals("Member")) {
					close();
					ViewLogic.MenuWindowMember();
				}
				else {
					Alert alert = new Alert(Alert.AlertType.WARNING);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username or password.");
					alert.showAndWait();
				}
			} 
		catch (Exception e) {
			e.printStackTrace();
		}
    }


    
    /* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		user = ViewLogic.user;
		
	}


}
