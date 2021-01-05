package boundary;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import control.RidesControl;
import entity.ActiveMember;
import entity.RideToBallot;
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
import javafx.stage.Stage;


/**
 * This class represent the screen used to assign rides
 * A transportation manager assigns rides to voters
 *
 */

public class RidesScreen implements Initializable {


	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */

	
	@FXML private JFXButton btn_menu;
	@FXML private JFXButton btn_new; 
	@FXML private JFXButton btn_edit;
	@FXML private JFXButton btn_delete; 

	/* ------------------------- Rides Table ------------------------- */

	@FXML private TableView<RideToBallot> tbl_Rides;
    @FXML private TableColumn<RideToBallot, String> col_voterID;
    @FXML private TableColumn<RideToBallot, String> col_driverID;
    @FXML private TableColumn<RideToBallot, Time> col_pickupTime;
	
    static RideToBallot selectedRide;

    ActiveMember user;
    

	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
    
    /**
     * Sets the rides table
     */
	
	protected void setRidesTable() {
		ObservableList<RideToBallot> rides = FXCollections.observableArrayList();
		rides.setAll(ViewLogic.ridesControl.getRides());
		tbl_Rides.setItems(rides);
		tbl_Rides.refresh();
	}
	
	
	/**
     * Saves the selected ride's information
     * @param event
     */

	public void rideSelected(MouseEvent event) {
		if(tbl_Rides.getSelectionModel().getSelectedItem() instanceof RideToBallot) {
			selectedRide = tbl_Rides.getSelectionModel().getSelectedItem();
		}				
	}
	
	
	/**
	 * Opens the ride info window to add a new ride when the "add" button is pressed
	 * @param event
	 * @throws IOException
	 */

	@FXML
	void add(ActionEvent event) throws IOException {
		ViewLogic.AssignRidesWindow();
	}

	
	/**
	 * Opens the ride info window to edit a ride's info when the "edit" button is pressed
	 * @param event
	 * @throws IOException
	 */

	@FXML
	void edit(ActionEvent event) throws IOException {
		RideToBallot selected = tbl_Rides.getSelectionModel().getSelectedItem();
		if(selected != null) {
			ViewLogic.AssignRidesWindow();
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Rides.getScene().getWindow());
			alert.setTitle("Edit Ride Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a ride to edit!");
			alert.showAndWait();
		}
	}
	
	
	/**
	 * Deletes a ride when the "delete" button is pressed
	 * @param event
	 */
	
	@FXML
	void delete(ActionEvent event) {
		RideToBallot selected = tbl_Rides.getSelectionModel().getSelectedItem();
		if (selected != null) {
			Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
			alert.initOwner(tbl_Rides.getScene().getWindow());
			alert.setTitle("Delete Ride");
			String id = selected.getVoterID();
			alert.setHeaderText(id);
			alert.setContentText("Are you sure you want to delete this voter's ride?");
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				if (RidesControl.getInstance().removeRide(selected.getVoterID())){
					// show the success message
					Alert alert2 = new Alert(Alert.AlertType.INFORMATION);
					alert2.setTitle("Delete Ride");
					alert2.setHeaderText(null);
					alert2.setContentText("The ride was successfully deleted!");
					alert2.showAndWait();
					setRidesTable();
				}
				else { // show the error message
					alert = new Alert(Alert.AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Failed to delete ride!");
					alert.showAndWait();
				}	
			}
			else {
				alert.close();
			}
		}
		else {
			Alert alert = new Alert(Alert.AlertType.WARNING);
			alert.initOwner(tbl_Rides.getScene().getWindow());
			alert.setTitle("Delete Error");
			alert.setHeaderText(null);
			alert.setContentText("You didn't select a ride!");
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
		
		if (user.getPassword().equals("TransportM"))
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
		
		ViewLogic.ridesScreen = this;
		
		/* ------------------------- Rides Table ------------------------- */

		col_voterID.setCellValueFactory(new PropertyValueFactory<>("voterID")); 
		col_driverID.setCellValueFactory(new PropertyValueFactory<>("driverID"));
		col_pickupTime.setCellValueFactory(new PropertyValueFactory<>("pickupTime"));	
		tbl_Rides.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
		setRidesTable();
	}
	

}
