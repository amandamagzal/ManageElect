package boundary;

import control.CallsControl;
import entity.CallInfo;
import entity.ActiveMember;
import entity.Voter;

import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTimePicker;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;


/**
 * This class represent the screen used fill the call info
 * An active member adds the voter's answers
 */

public class CallInfoScreen implements Initializable{


	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */

	
	private CallInfo call;

	@FXML private Pane pane;
	@FXML private GridPane hourRange;
	@FXML private JFXTextField callNum;
	@FXML private JFXDatePicker picker_date;
	@FXML private JFXTimePicker picker_time;
	@FXML private JFXTextField voterID;
	@FXML private JFXTextField callerID;
	@FXML private JFXCheckBox check_answered;

	ToggleGroup voteQ = new ToggleGroup();
	ToggleGroup supportQ = new ToggleGroup();
	ToggleGroup courseQ = new ToggleGroup();
	ToggleGroup rideQ = new ToggleGroup();

	@FXML private RadioButton btn_voteYes; 
	@FXML private RadioButton btn_voteNo;
	@FXML private RadioButton btn_voteDidntDecide;
	@FXML private RadioButton btn_supportYes;
	@FXML private RadioButton btn_supportNo;
	@FXML private RadioButton btn_supportDidntDecide;   
	@FXML private RadioButton btn_courseYes;
	@FXML private RadioButton btn_courseNo;
	@FXML private RadioButton btn_courseDidntDecide;
	@FXML private RadioButton btn_rideYes;
	@FXML private RadioButton btn_rideNo;
	@FXML private RadioButton btn_rideDidntDecide;
	@FXML private JFXTimePicker picker_rangeFrom;
	@FXML private JFXTimePicker picker_rangeTo;

	@FXML private JFXButton btn_save;
	@FXML private JFXButton btn_cancel;

	ActiveMember user;
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */

	
	/**
	 * Gets the basic info of the call
	 */

	void setBasicInfo(){
		Date date = Date.valueOf(LocalDate.now());
		Time time = Time.valueOf(LocalTime.now());
		Voter v = CallsScreen.selectedVoter;
		Integer callNum = new Integer(ViewLogic.callsControl.getLastCallNum() + 1);
		call = new CallInfo(callNum.toString(), v.getVoterID(), user.getVoterID(), date, time, false);
		setCall(call);
	}
	
	
	/**
	 * Sets the call information
	 * @param call
	 */
	
	public void setCall(CallInfo call) {
		if(call != null) {
			callNum.setText(call.getCallNum());
			callNum.setDisable(true);
			voterID.setText(call.getVoterID());
			callerID.setText(call.getCallerID());
			//picker_date.setText(call.getCallDate());
			//picker_time.setText(call.getCallTime());
		}
		this.call = call;
	}
	
	
	/**
     * Makes the questions information pane visible when the "answered" check box is checked
     * @param event
     */
	
	
	@FXML
	void showAnswers(ActionEvent event) {
		if (check_answered.isSelected())
			pane.setVisible(true);
		else {
			pane.setVisible(false);
			hourRange.setVisible(false);
		}
	}

	
	/**
     * Makes the ride information pane visible when the "yes" answer to a ride is pressed 
     * @param event
     */
	
	@FXML
	void showRide(ActionEvent event) {
		if (btn_rideYes.isSelected())
			hourRange.setVisible(true);
		if (btn_rideNo.isSelected() || btn_rideDidntDecide.isSelected())
			hourRange.setVisible(false);
	}

	
	/**
	 * Saves the call info when the "save" button is pressed
	 * @param event
	 */

	@FXML
	void saveClick(ActionEvent event) {

		if(isInputValid()) {

			CallInfo c = null;
			
			Date date = Date.valueOf(picker_date.getValue());
			Time time = Time.valueOf(picker_time.getValue());
			
			if(check_answered.isSelected()) {

				String voteAns;
				if (btn_voteYes.isSelected())
					voteAns = "Yes";
				else if (btn_voteNo.isSelected())
					voteAns = "No";
				else
					voteAns = "Didn't Decide";

				String supportAns;
				if (btn_supportYes.isSelected())
					supportAns = "Yes";
				else if (btn_supportNo.isSelected())
					supportAns = "No";
				else
					supportAns = "Didn't Decide";

				String courseAns;
				if (btn_courseYes.isSelected())
					courseAns = "Yes";
				else if (btn_courseNo.isSelected())
					courseAns = "No";
				else
					courseAns = "Didn't Decide";

				String rideAns;
				Time rangeStart = null;
				Time rangeEnd = null;
				
				if (btn_rideYes.isSelected()) {
					rideAns = "Yes";
					rangeStart = Time.valueOf(picker_rangeFrom.getValue());
					rangeEnd = Time.valueOf(picker_rangeTo.getValue());
					
					c = new CallInfo (callNum.getText(), voterID.getText(), callerID.getText(), date, time, 
							check_answered.isSelected(), voteAns, supportAns, courseAns, rideAns, rangeStart, rangeEnd);
				}
				else if (btn_rideNo.isSelected()) {
					rideAns = "No";
					c = new CallInfo (callNum.getText(), voterID.getText(), callerID.getText(), date, time, 
							check_answered.isSelected(), voteAns, supportAns, courseAns, rideAns);
				}
				else {
					rideAns = "Didn't Decide";
					c = new CallInfo (callNum.getText(), voterID.getText(), callerID.getText(), date, time, 
							check_answered.isSelected(), voteAns, supportAns, courseAns, rideAns);
				}
			}
			
			else {
				c = new CallInfo (callNum.getText(), 
						voterID.getText(), callerID.getText(), 
						date, time, check_answered.isSelected());
			}
			
			if(CallsControl.getInstance().addCall(c)) {
				// show the success message
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Add Call");
				alert.setHeaderText(null);
				alert.setContentText("The call was successfully added!");
				alert.showAndWait();
				ViewLogic.callsScreen.setCallsTable();
			}
			else { // show the error message
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Failed to add call!");
				alert.showAndWait();
			}		
		}
		Stage stage = (Stage) callNum.getScene().getWindow();
		stage.close();

	}


	/**
	 * Closes the call info window when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	private void cancelClick(ActionEvent event) {
		Stage stage = (Stage) callNum.getScene().getWindow();
		stage.close();
	}

	
	/**
	 * Validates the input
	 * @return true if valid, false otherwise
	 */

	@SuppressWarnings("unlikely-arg-type")
	private boolean isInputValid() {
		String errorMessage = "";
		if (callNum.getText() == null || callNum.getText().length() == 0)
			errorMessage += "Invalid Call Number!\n";
		else if(CallsControl.getInstance().getCalls().contains(callNum.getText()) && call == null)
			errorMessage += "This Call Number already exists!\n";           
		if(picker_date == null)
			errorMessage += "No Call Date selected!\n";
		if(picker_time == null)
			errorMessage += "No Call Time selected!\n";
		if (voterID.getText() == null || voterID.getText().length() == 0 || voterID.getText().length() != 9)
			errorMessage += "Invalid Voter ID entered!\n";
		if (callerID.getText() == null || callerID.getText().length() == 0 || callerID.getText().length() != 9)
			errorMessage += "Invalid Caller ID entered!\n";
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


	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		user = ViewLogic.user;

		// hide panes
		pane.setVisible(false);
		hourRange.setVisible(false);

		// assign radio buttons to toggle groups
		btn_voteYes.setToggleGroup(voteQ);
		btn_voteNo.setToggleGroup(voteQ);
		btn_voteDidntDecide.setToggleGroup(voteQ);
		btn_supportYes.setToggleGroup(supportQ);
		btn_supportNo.setToggleGroup(supportQ);
		btn_supportDidntDecide.setToggleGroup(supportQ);
		btn_courseYes.setToggleGroup(courseQ);
		btn_courseNo.setToggleGroup(courseQ);
		btn_courseDidntDecide.setToggleGroup(courseQ);
		btn_rideYes.setToggleGroup(rideQ);
		btn_rideNo.setToggleGroup(rideQ);
		btn_rideDidntDecide.setToggleGroup(rideQ);

		
		
		if(CallsScreen.selectedCall != null) {
			this.call = CallsScreen.selectedCall;
			setCall(call);
		}
		else
			setBasicInfo();

	}

	
}
