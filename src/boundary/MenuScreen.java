package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import entity.ActiveMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class represent the menu screen used to navigate
 *
 */

public class MenuScreen implements Initializable {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private AnchorPane mainPane;

    @FXML private JFXButton btn_callVoter;
    @FXML private JFXButton btn_ridesReport;    
    @FXML private JFXButton btn_defineRoles;
    @FXML private JFXButton btn_info;
    @FXML private JFXButton btn_addNum;
    @FXML private JFXButton btn_assignRoles;
    @FXML private JFXButton btn_assignRides;
    @FXML private JFXButton btn_issueBadges;

    @FXML private JFXButton btn_logout;
	private double x , y;

	ActiveMember user;
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
		
	@FXML
	void close() {
		Stage stage = (Stage) btn_logout.getScene().getWindow();
		stage.close();
	}

	
	@FXML
	void minimize(MouseEvent event) {
		Stage stage = (Stage) btn_logout.getScene().getWindow();
		stage.setIconified(true);
	}

	
	@FXML
	void mousepressed(MouseEvent event) {
		x = event.getSceneX();
		y = event.getSceneY();
	}

	
	@FXML
	void dragged(MouseEvent event) {
		Stage stage = (Stage) btn_logout.getScene().getWindow();;
		stage.setX(event.getScreenX() - x);
		stage.setY(event.getScreenY() - y);
	}
	

	/**
	 * Opens the window according to the pressed button
	 * @param event
	 * @throws IOException
	 */
	
	@FXML
	void menu(ActionEvent event) throws IOException {

		if (event.getSource() == btn_callVoter) {
			close();
			ViewLogic.CallsWindow();
		}
		
		
		if (event.getSource() == btn_ridesReport) {
			close();
			ViewLogic.RidesReportWindow();
		}
		
		
		if (event.getSource() == btn_defineRoles) {
			close();
			ViewLogic.DefineRolesWindow();
		}
		
		
		if (event.getSource() == btn_info) {
			close();
			ViewLogic.InfoWindow();
		}
		
		
		if (event.getSource() == btn_addNum) {
			close();
			ViewLogic.AddPhoneNumWindow();
		}
		

		if (event.getSource() == btn_assignRoles) {
			close();
			ViewLogic.AssignRolesWindow();
		}
		
		
		if (event.getSource() == btn_assignRides) {
			close();
			ViewLogic.RidesWindow();
		}
		
		
		if (event.getSource() == btn_issueBadges) {
			close();
			ViewLogic.IssueBadgesWindow();
		}
		

		if (event.getSource() == btn_logout) {
			close();
			ViewLogic.LoginWindow();
		}
	}
	
	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */

    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
	
		user = ViewLogic.user;
		
		ViewLogic.menuScreen = this;
		
	}

	
}
