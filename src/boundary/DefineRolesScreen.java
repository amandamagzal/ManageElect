package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import control.MemberControl;
import entity.ActiveMember;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;


/**
 * This class represent the screen used to define the members' roles
 * The campaign manager defines the role name and the password used to sign into the system
 *
 */

public class DefineRolesScreen implements Initializable {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;

    @FXML private JFXTextField roleName;
    @FXML private JFXTextField password;
    @FXML private JFXButton btn_save;
    @FXML private JFXButton btn_cancel;
    @FXML private JFXButton btn_delete;
    
    ActiveMember user;
    
    
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
	 * Saves a role when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {
		
		String role = roleName.getText();
		String pass = password.getText();
		
		if (role != null) {
			if(MemberControl.getInstance().addRole(role, pass)) {
				// show the success message
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Add Role");
				alert.setHeaderText(null);
				alert.setContentText("The role was successfully added!");
				alert.showAndWait();
			}
			else { // show the error message
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Failed to add role!");
				alert.showAndWait();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(roleName.getScene().getWindow());
			alert.setTitle("Add Role Error");
			alert.setHeaderText(null);
			alert.setContentText("You did not insert a role!");
			alert.showAndWait();
		}	
	}
	
	
	/**
	 * Clears the text fields when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	void cancelClick(ActionEvent event) {

		roleName.clear();
		password.clear();
	}
	
	
	/**
	 * Deletes the role when the "delete" button is pressed
	 * @param event
	 */
	
	@FXML
	void delete(ActionEvent event) {
		
		String selected = roleName.getText();
		
		if (selected != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(roleName.getScene().getWindow());
			alert.setTitle("Delete Call");
			String name = roleName.getText();
			alert.setHeaderText(name);
			alert.setContentText("Are you sure you want to delete this role?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (MemberControl.getInstance().removeRole(selected)){
					// show the success message
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setTitle("Delete Role");
					alert2.setHeaderText(null);
					alert2.setContentText("The role was successfully deleted!");
					alert2.showAndWait();
				}
				else { // show the error message
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to delete role!");
					alert.showAndWait();
				}	
			}
			else {
				alert.close();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(roleName.getScene().getWindow());
			alert.setTitle("Delete Role Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't enter a role to delete!");
			alert.showAndWait();
		}
	}
	
	
	/* ------------------------- Back To Main Menu ------------------------- */
	
	@FXML
	void close() {
		Stage stage = (Stage) btn_menu.getScene().getWindow();
		stage.close();
	}
	
	
	@FXML
	void back(ActionEvent event) throws IOException {
		close();
		ViewLogic.MenuWindow();	
	}	
	
	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		user = ViewLogic.user;
		
	}
    
 
}
