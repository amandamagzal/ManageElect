package boundary;

import entity.Voter;
import entity.ActiveMember;
import entity.CallInfo;
import util.E_Answer;
import util.E_Gender;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import control.CallsControl;
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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * This class represent the screen used to call voters
 * An active member calls a voter
 *
 */

public class CallsScreen implements Initializable {


	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */

	
	@FXML private AnchorPane main_pane;
	@FXML private JFXButton btn_menu;
	@FXML private JFXButton btn_new; 
	@FXML private JFXButton btn_edit;
	@FXML private JFXButton btn_delete; 

	/* ------------------------- Voters Table ------------------------- */

	@FXML private TableView<Voter> tbl_Voters;
    @FXML private TableColumn<Voter, String> col_id;
    @FXML private TableColumn<Voter, String> col_firstName;
    @FXML private TableColumn<Voter, String> col_lastName;
    @FXML private TableColumn<Voter, Date> col_birthDate;
    @FXML private TableColumn<Voter, E_Gender> col_gender;
    @FXML private TableColumn<Voter, String> col_phoneNum;

	/* ------------------------- Calls Table ------------------------- */

	@FXML private TableView<CallInfo> tbl_CallInfo;
	@FXML private TableColumn<CallInfo, String> col_callNum;
	@FXML private TableColumn<CallInfo, Date> col_date;
	@FXML private TableColumn<CallInfo, Time> col_time;
	@FXML private TableColumn<CallInfo, String> col_voterID;
	@FXML private TableColumn<CallInfo, String> col_callerID;
	@FXML private TableColumn<CallInfo, Boolean> col_answer;
	@FXML private TableColumn<CallInfo, E_Answer> col_voteAnswer;	
	@FXML private TableColumn<CallInfo, E_Answer> col_supportAnswer;	
	@FXML private TableColumn<CallInfo, E_Answer> col_courseAnswer;	
	@FXML private TableColumn<CallInfo, E_Answer> col_rideAnswer;
	@FXML private TableColumn<CallInfo, Time> col_pickupStart;
	@FXML private TableColumn<CallInfo, Time> col_pickupEnd;
	
	
	static Voter selectedVoter;
	static CallInfo selectedCall;

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
     * Sets the calls table
     */
	
	protected void setCallsTable() {
		ObservableList<CallInfo> calls = FXCollections.observableArrayList();
		calls.setAll(ViewLogic.callsControl.getCalls());
		tbl_CallInfo.setItems(calls);
		tbl_CallInfo.refresh();
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
     * Saves the selected call's information
     * @param event
     */

	public void callSelected(MouseEvent event) {
		if(tbl_CallInfo.getSelectionModel().getSelectedItem() instanceof CallInfo) {
			selectedCall = tbl_CallInfo.getSelectionModel().getSelectedItem();
		}				
	}

	
	/**
	 * Opens the call info window to add a new call's info when the "add" button is pressed
	 * @param event
	 * @throws IOException
	 */

	@FXML
	void add(ActionEvent event) throws IOException {
		if (selectedVoter != null) {
			ViewLogic.CallInfoWindow();
		}
		else{ 
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_CallInfo.getScene().getWindow());
			alert.setTitle("New Call Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a voter to call!");
			alert.showAndWait();
		}
	}

	
	/**
	 * Opens the call info window to edit a call's info when the "edit" button is pressed
	 * @param event
	 * @throws IOException
	 */

	@FXML
	void edit(ActionEvent event) throws IOException {
		if(selectedCall != null) {
			ViewLogic.CallInfoWindow();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_CallInfo.getScene().getWindow());
			alert.setTitle("Edit Call Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a call to edit!");
			alert.showAndWait();
		}
	}


	/**
	 * Deletes the call when the "delete" button is pressed
	 * @param event
	 */
	
	@FXML
	void delete(ActionEvent event) {
		CallInfo selected = tbl_CallInfo.getSelectionModel().getSelectedItem();
		if (selected != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(tbl_CallInfo.getScene().getWindow());
			alert.setTitle("Delete Call");
			String num = tbl_CallInfo.getSelectionModel().getSelectedItem().getVoterID();
			alert.setHeaderText(num);
			alert.setContentText("Are you sure you want to delete this call?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (CallsControl.getInstance().removeCall(selected.getVoterID())) {
					// show the success message
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setTitle("Delete Call");
					alert2.setHeaderText(null);
					alert2.setContentText("The call was successfully deleted!");
					alert2.showAndWait();
					tbl_CallInfo.getItems().remove(selected);
					ObservableList<CallInfo> calls = FXCollections.observableArrayList();
					calls.setAll(CallsControl.getInstance().getCalls());
				}
				else { // show the error message
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to delete call!");
					alert.showAndWait();
				}
			}
			else 
				alert.close();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_CallInfo.getScene().getWindow());
			alert.setTitle("Delete Call Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a call to delete!");
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
	public void initialize(URL location, ResourceBundle resources) {

		user = ViewLogic.user;
		
		ViewLogic.callsScreen = this;
		
		/* ------------------------- Voters Table ------------------------- */

		col_id.setCellValueFactory(new PropertyValueFactory<>("voterID")); 
		col_firstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		col_lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		col_birthDate.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		col_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		col_phoneNum.setCellValueFactory(new PropertyValueFactory<>("phoneNum"));	
		tbl_Voters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setVotersTable();
		

		/* ------------------------- Calls Table ------------------------- */

		col_callNum.setCellValueFactory(new PropertyValueFactory<>("callNum"));
		col_voterID.setCellValueFactory(new PropertyValueFactory<>("voterID"));
		col_callerID.setCellValueFactory(new PropertyValueFactory<>("callerID"));
		col_date.setCellValueFactory(new PropertyValueFactory<>("callDate"));
		col_time.setCellValueFactory(new PropertyValueFactory<>("callTime"));
		col_answer.setCellValueFactory(new PropertyValueFactory<>("gotAnswer"));
		col_voteAnswer.setCellValueFactory(new PropertyValueFactory<>("willVote"));
		col_supportAnswer.setCellValueFactory(new PropertyValueFactory<>("supportParty"));
		col_courseAnswer.setCellValueFactory(new PropertyValueFactory<>("interestedInCourse"));
		col_rideAnswer.setCellValueFactory(new PropertyValueFactory<>("needRide"));
		col_pickupStart.setCellValueFactory(new PropertyValueFactory<>("pickupRangeStart"));
		col_pickupEnd.setCellValueFactory(new PropertyValueFactory<>("pickupRangeEnd"));
		tbl_CallInfo.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setCallsTable();

	}
	
	
}
