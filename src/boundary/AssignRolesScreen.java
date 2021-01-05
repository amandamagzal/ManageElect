package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTimePicker;

import control.MemberControl;
import entity.ActiveMember;
import entity.BallotBox;
import entity.Branch;
import entity.ElectDayPosition;
import entity.Voter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import util.E_Gender;
import util.E_Role;


/**
 * This class represent the screen used to assign a active member to a position
 * A branch manager adds the member to a branch and assigns them to a position
 *
 */

public class AssignRolesScreen implements Initializable {


	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;

	@FXML private JFXComboBox<Branch> combo_branchNum;
	
	@FXML private TableView<Voter> tbl_Voters;
    @FXML private TableColumn<Voter, String> col_voterID;
    @FXML private TableColumn<Voter, String> col_firstName;
    @FXML private TableColumn<Voter, String> col_lastName;
    @FXML private TableColumn<Voter, Date> col_birthDate;
    @FXML private TableColumn<Voter, E_Gender> col_gender;
    @FXML private TableColumn<Voter, String> col_phoneNum;
    
    static Voter selectedVoter;
    
    @FXML private JFXComboBox<String> combo_role;
    @FXML private JFXTimePicker picker_startTime;
    @FXML private JFXTimePicker picker_finishTime;

    @FXML private Pane pane_ballot;
    @FXML private JFXComboBox<BallotBox> combo_ballotNum;
    
    @FXML private JFXButton btn_save;
    @FXML private JFXButton btn_cancel;
    
    ActiveMember user;
    
    
    
    /* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
    
    /**
     * Sets the branch combo box
     */
    
    private void setBranchCombo() {
		combo_branchNum.getItems().setAll(ViewLogic.ridesControl.getBranches());
		combo_branchNum.getSelectionModel().select(0);

	}
    
    
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
	 * Sets the roles combo box
	 */
	
	private void setRoleCombo() {
		ArrayList<String> roles = new ArrayList<String>();
		for (E_Role r : E_Role.values()) {
			roles.add(r.toString());
		}
		combo_role.getItems().setAll(roles);
		combo_role.getSelectionModel().select(0);

	}
    
    
	/**
     * Makes the ballot pane visible when the "observer" role is selected
     * @param event
     */
	
	@FXML
	void showNum(ActionEvent event) {
		if (combo_role.getValue().equals("Observer"))
			pane_ballot.setVisible(true);
	}
	
	
	/**
     * Sets the branch combo box
     */
    
    private void setBallotCombo() {
		combo_ballotNum.getItems().setAll(ViewLogic.memberControl.getBallots());
		combo_ballotNum.getSelectionModel().select(0);

	}
	
	
	/**
	 * Saves the member's assignment information when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {
		
		if (selectedVoter != null) {
			
			String memberID = selectedVoter.getVoterID();
			String role = combo_role.getValue();
			Time startTime = Time.valueOf(picker_startTime.getValue());
			Time finishTime = Time.valueOf(picker_finishTime.getValue());
			String ballot = combo_ballotNum.getValue().toString();
	
			if(isInputValid(role, startTime, finishTime)) {
				
				ElectDayPosition p;
				
				if(ballot == null)
					p = new ElectDayPosition(memberID, role, startTime, finishTime);
				else
					p = new ElectDayPosition(memberID, role, startTime, finishTime, ballot);
				
				if(MemberControl.getInstance().addPosition(p)) {
					// show the success message
					Alert alert = new Alert(Alert.AlertType.INFORMATION);
					alert.setTitle("Add Position");
					alert.setHeaderText(null);
					alert.setContentText("The position assignment was successfully added!");
					alert.showAndWait();
				}
				else { // show the error message
					Alert alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to add position!");
					alert.showAndWait();
				} 
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Add Position Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a member!");
			alert.showAndWait();
		}
	}
	
	
	/**
	 * Hides the ballot pane when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	void cancelClick(ActionEvent event) {

		pane_ballot.setVisible(false);	
	}
	
	
	/**
	 * Validates the input
	 * @param role
	 * @param startTime
	 * @param finishTime
	 * @return
	 */
	
	@SuppressWarnings("unlikely-arg-type")
	private boolean isInputValid(String role, Time startTime, Time finishTime) {
		String errorMessage = "";
		
		if (role == null)
			errorMessage += "No Role selected!\n";
		if (startTime == null)
			errorMessage += "No Start Time selected!\n";
		if (finishTime == null)
			errorMessage += "No Finish Time selected!\n";
		
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
		setRoleCombo();
		setBallotCombo();
		
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
		pane_ballot.setVisible(false);
		
	}
    
     
}
