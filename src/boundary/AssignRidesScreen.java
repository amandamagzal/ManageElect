package boundary;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTimePicker;

import control.RidesControl;
import entity.ActiveMember;
import entity.CallInfo;
import entity.ElectDayPosition;
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
import javafx.stage.Stage;


/**
 * This class represent the screen used to assign rides
 * A transportation manager assigns rides to voters
 *
 */

public class AssignRidesScreen implements Initializable {


	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */

	
	/* ------------------------- Voters Table ------------------------- */

	@FXML private TableView<CallInfo> tbl_Voters;
	@FXML private TableColumn<CallInfo, String> col_voterID;
    @FXML private TableColumn<CallInfo, Time> col_pickupFrom;
    @FXML private TableColumn<CallInfo, Time> col_pickupTo;
	
    static CallInfo selectedVoter;
    
    /* ------------------------- Drivers Table ------------------------- */

	@FXML private TableView<ElectDayPosition> tbl_Drivers;
	@FXML private TableColumn<ElectDayPosition, String> col_driverID;
    @FXML private TableColumn<ElectDayPosition, Time> col_startTime;
    @FXML private TableColumn<ElectDayPosition, Time> col_finishTime;
	
    static ElectDayPosition selectedDriver;

    @FXML private JFXTimePicker picker_pickupTime;
    
    ActiveMember user;
    
    
    
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
    
    /**
     * Sets the voters table
     */
	
	protected void setVotersTable() {
		ObservableList<CallInfo> voters = FXCollections.observableArrayList();
		voters.setAll(ViewLogic.ridesControl.getVoters());
		tbl_Voters.setItems(voters);
		tbl_Voters.refresh();
	}
	
	
	/**
     * Saves the selected voter's information
     * @param event
     */

	public void voterSelected(MouseEvent event) {
		if(tbl_Voters.getSelectionModel().getSelectedItem() instanceof CallInfo) {
			selectedVoter = tbl_Voters.getSelectionModel().getSelectedItem();
		}				
	}
	
	
	/**
     * Sets the drivers table
     */
	
	protected void setDriversTable() {
		ObservableList<ElectDayPosition> drivers = FXCollections.observableArrayList();
		drivers.setAll(ViewLogic.ridesControl.getDrivers());
		tbl_Drivers.setItems(drivers);
		tbl_Drivers.refresh();
	}
	
	
	/**
     * Saves the selected driver's information
     * @param event
     */

	public void driverSelected(MouseEvent event) {
		if(tbl_Drivers.getSelectionModel().getSelectedItem() instanceof ElectDayPosition) {
			selectedDriver = tbl_Drivers.getSelectionModel().getSelectedItem();
		}				
	}
	
	
	/**
	 * Saves a ride's information when the "save" button is pressed
	 * @param event
	 */
	
	@FXML
	void saveClick(ActionEvent event) {

		if (selectedVoter != null && selectedDriver != null) {
			
			String voterID = selectedVoter.getVoterID();
			String driverID = selectedDriver.getMemberID();
			Time time = Time.valueOf(picker_pickupTime.getValue());

			if(RidesControl.getInstance().addRide(driverID, time, voterID)) {
				// show the success message
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setTitle("Add Ride");
				alert.setHeaderText(null);
				alert.setContentText("The ride was successfully added!");
				alert.showAndWait();
				Stage stage = (Stage) picker_pickupTime.getScene().getWindow();
				stage.close();
				ViewLogic.ridesScreen.setRidesTable();
			}
			else { // show the error message
				Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Failed to add ride!");
				alert.showAndWait();
			}
		}
		else if (selectedVoter == null) {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Add Ride Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a voter!");
			alert.showAndWait();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Voters.getScene().getWindow());
			alert.setTitle("Add Ride Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a driver!");
			alert.showAndWait();
		}
	}
	
	
	/**
	 * Closes the ride info window when the "cancel" button is pressed
	 * @param event
	 */
	
	@FXML
	private void cancelClick(ActionEvent event) {
		Stage stage = (Stage) picker_pickupTime.getScene().getWindow();
		stage.close();
	}

	
	
	/* ------------------------------------------------------------------ */
	/* ------------------------- Initialization ------------------------- */
	/* ------------------------------------------------------------------ */
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		user = ViewLogic.user;
		
		/* ------------------------- Voters Table ------------------------- */

		col_voterID.setCellValueFactory(new PropertyValueFactory<>("voterID")); 
		col_pickupFrom.setCellValueFactory(new PropertyValueFactory<>("pickupRangeStart")); 
		col_pickupTo.setCellValueFactory(new PropertyValueFactory<>("pickupRangeEnd"));
		tbl_Voters.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setVotersTable();
		
		/* ------------------------- Drivers Table ------------------------- */

		col_driverID.setCellValueFactory(new PropertyValueFactory<>("memberID")); 
		col_startTime.setCellValueFactory(new PropertyValueFactory<>("startTime")); 
		col_finishTime.setCellValueFactory(new PropertyValueFactory<>("finishTime"));
		tbl_Drivers.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setDriversTable();
		
	}
	
	
}
