package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import control.VoterControl;
import entity.ActiveMember;
import entity.Voter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.E_Gender;


/**
 * This class represent the screen used to add a voter's phone number
 * An active member adds the phone number of a voter
 *
 */

public class AddPhoneScreen implements Initializable {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;

	@FXML private TableView<Voter> tbl_Voters;
    @FXML private TableColumn<Voter, String> col_voterID;
    @FXML private TableColumn<Voter, String> col_firstName;
    @FXML private TableColumn<Voter, String> col_lastName;
    @FXML private TableColumn<Voter, Date> col_birthDate;
    @FXML private TableColumn<Voter, E_Gender> col_gender;
    @FXML private TableColumn<Voter, String> col_phoneNum;

    @FXML private JFXButton btn_add;
    @FXML private JFXButton btn_edit;
    @FXML private JFXButton btn_delete;
    
    @FXML private Pane pane_phoneNum;
    @FXML private JFXTextField phoneNum;
    @FXML private JFXButton btn_save;
    @FXML private JFXButton btn_cancel;
    
    static Voter selectedVoter;
    
    ActiveMember user;

    
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
    
    /**
     * Sets the voters table
     */
    
    protected void setVotersTable() {
		ObservableList<Voter> voters = FXCollections.observableArrayList();
		voters.setAll(ViewLogic.voterControl.getVoters());
		tbl_Voters.setItems(voters);
		tbl_Voters.refresh();
	}
    
    
    /**
     * Saves the selected voter's information
     * @param event
     */
    
	public void voterSelected(MouseEvent event) {
		if(tbl_Voters.getSelectionModel().getSelectedItem() instanceof Voter) {
			selectedVoter = tbl_Voters.getSelectionModel().getSelectedItem();
		}				
	}
	
	
	/**
     * Makes the voting information pane visible when the "add" or "edit" buttons are pressed
     * @param event
     */
	
	@FXML
	void showNum(ActionEvent event) {
		if (event.getSource() == btn_add || event.getSource() == btn_edit)
			if (selectedVoter != null) {
				pane_phoneNum.setVisible(true);
			}
			else{ 
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.initOwner(tbl_Voters.getScene().getWindow());
				alert.setTitle("New Phone Number Error");
				alert.setHeaderText(null);
				alert.setContentText("You did not select a voter!");
				alert.showAndWait();
			}	
	}
	
	
	/**
	 * Saves a voter's phone num when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {
		
		if (selectedVoter != null) {
			
			String voterID = selectedVoter.getVoterID();
			String num = phoneNum.getText();
			
			if (num != null) {
				if (isInputValid(num)) {
					if(VoterControl.getInstance().addPhoneNum(num, voterID)) {
						// show the success message
						Alert alert = new Alert(Alert.AlertType.INFORMATION);
						alert.setTitle("Add Phone Number");
						alert.setHeaderText(null);
						alert.setContentText("The number was successfully added!");
						alert.showAndWait();
						pane_phoneNum.setVisible(false);
						phoneNum.clear();
						setVotersTable();
					}
					else { // show the error message
						Alert alert = new Alert(Alert.AlertType.ERROR);
						alert.setTitle("Error");
						alert.setHeaderText(null);
						alert.setContentText("Failed to add number!");
						alert.showAndWait();
					}
				}	
			}
			else {
				Alert alert = new Alert(Alert.AlertType.WARNING);
				alert.initOwner(tbl_Voters.getScene().getWindow());
				alert.setTitle("Add Phone Number Error");
				alert.setHeaderText(null);
				alert.setContentText("You didn't insert a number!");
				alert.showAndWait();
			}		
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Add Phone Number Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a voter!");
			alert.showAndWait();
		}
	}
	
	
	/**
	 * Hides the phone num pane when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	void cancelClick(ActionEvent event) {
		phoneNum.clear();
		pane_phoneNum.setVisible(false);	
	}
	
	
	/**
	 * Deletes the voter's phone num when the "delete" button is pressed
	 * @param event
	 */
	
	@FXML
	void delete(ActionEvent event) {
		
		if (selectedVoter != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Delete Phone Number");
			String id = selectedVoter.getVoterID();
			alert.setHeaderText(id);
			alert.setContentText("Are you sure you want to delete this voter's phone number?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (VoterControl.getInstance().removePhoneNum(selectedVoter.getVoterID())){
					// show the success message
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setTitle("Delete Phone Number");
					alert2.setHeaderText(null);
					alert2.setContentText("The voter's phone num was successfully deleted!");
					alert2.showAndWait();
					setVotersTable();
				}
				else { // show the error message
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to delete phone num!");
					alert.showAndWait();
				}	
			}
			else {
				alert.close();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Delete Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a voter!");
			alert.showAndWait();
		}
	}
	
	
	/**
	 * Validates the phone number
	 * @param num
	 * @return
	 */
	
	@SuppressWarnings("unlikely-arg-type")
	private boolean isInputValid(String num) {
		String errorMessage = "";
		if (num.length() != 10)
			errorMessage += "Invalid Phone Number!\n";
		
		if (errorMessage.length() == 0)
			return true;		
		else { // Show the error message
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.initOwner(btn_save.getScene().getWindow());
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct the invalid fields.");
			alert.setContentText(errorMessage);
			alert.showAndWait();
			return false;
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
		
		if (user.getPassword().equals("Member"))
			ViewLogic.MenuWindowMember();
		else if (user.getPassword().equals("TransportM"))
			ViewLogic.MenuWindowTransportM();
		else if (user.getPassword().equals("BranchM"))
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
		
		/* ------------------------- Voters Table ------------------------- */

		col_voterID.setCellValueFactory(new PropertyValueFactory<>("voterID")); 
		col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		col_birthDate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));	
		tbl_Voters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setVotersTable();
		
		
		// hide pane
		pane_phoneNum.setVisible(false);
		
	}
    
    
}
