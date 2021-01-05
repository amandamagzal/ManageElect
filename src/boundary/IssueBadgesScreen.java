package boundary;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.jfoenix.controls.JFXButton;

import entity.ActiveMember;
import entity.Branch;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * This class represent the screen used to issue badges for observers
 * A branch manager issues badges for observers in his branch
 *
 */

public class IssueBadgesScreen implements Initializable{

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;
	
	@FXML private ComboBox<Branch> combo_branchNum;
	@FXML private JFXButton btn_issueBadges;
	
	ActiveMember user;
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	private void setBranchCombo() {
		combo_branchNum.getItems().setAll(ViewLogic.ridesControl.getBranches());
		combo_branchNum.getSelectionModel().select(0);

	}
	
	
	/**
	 * Issues badges for the observers
	 */
	
	@FXML
	private void issueBadges() {
		Branch branchNum = combo_branchNum.getValue();
		if (branchNum == null) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Branch Number Error");
			alert.setContentText("Please select branch.");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.showAndWait();
		}
		else {
			JFrame reportFrame = ViewLogic.memberControl.issueBadges(branchNum);
			reportFrame.setVisible(true);
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
		
		if (user.getPassword().equals("BranchM"))
			ViewLogic.MenuWindowBranchM();	
		else
			ViewLogic.MenuWindow();	
	}
	
	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		user = ViewLogic.user;
		
		setBranchCombo();
		
	}
	
	
}
