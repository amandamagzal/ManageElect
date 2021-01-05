package boundary;

import javafx.scene.image.Image;

import java.io.IOException;
import java.net.URL;

import control.CallsControl;
import control.MemberControl;
import control.RidesControl;
import control.VoterControl;
import entity.ActiveMember;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;


/**
 * This class controls the window properties and navigation between the different windows
 *
 */

public class ViewLogic {
	
	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	protected static final Rectangle2D FULL_SCREEN = Screen.getPrimary().getBounds();
	protected static final Rectangle2D VISIBLE_SCREEN = Screen.getPrimary().getVisualBounds();
	
	protected static MenuScreen menuScreen;
	protected static CallsScreen callsScreen;
	protected static RidesScreen ridesScreen;
	protected static VoterControl voterControl;
	protected static MemberControl memberControl;
	protected static CallsControl callsControl;
	protected static RidesControl ridesControl;
	
	protected static ActiveMember user;

	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/* -------------------- Start Window -------------------- */
	
	public static void initUI() {
		
		voterControl = VoterControl.getInstance();
		memberControl = MemberControl.getInstance();
		callsControl = CallsControl.getInstance();
		ridesControl = RidesControl.getInstance();
		
		LoginWindow();
	}
	
	
	/* -------------------- Window Properties -------------------- */
	
	protected static void newWindow(URL fxmlLocation, Stage stage, Double prefWidth,
			Double prefHeight, Double minWidth, Double minHeight, Double maxWidth,
			Double maxHeight, boolean resizable, String title, boolean waitFor) {
		
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene;

					if (prefWidth == null || prefHeight == null)
						scene = new Scene(root);
					else
						scene = new Scene(root, prefWidth, prefHeight);

					Image icon = new Image(getClass().getResourceAsStream("/images/login.jpg"));
					stage.getIcons().setAll(icon);
					
					stage.setScene(scene);

					if (minWidth != null)
						stage.setMinWidth(minWidth);
					if (minHeight != null)
						stage.setMinHeight(minHeight);
					if (maxWidth != null)
						stage.setMaxWidth(maxWidth);
					if (maxHeight != null)
						stage.setMaxHeight(maxHeight);

					stage.setResizable(resizable);

					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();
				}
				catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	
	/* -------------------- Login Screen -------------------- */

	
	protected static void LoginWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("LoginScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Login Screen",
				false);
	}
	
	
	/* -------------------- Menu Screen -------------------- */
	
	
	protected static void MenuWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MenuScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Menu Screen",
				false);
	}
	
	
	protected static void MenuWindowBranchM() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MenuScreenBranchM.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Menu Screen",
				false);
	}
	
	
	protected static void MenuWindowTransportM() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MenuScreenTransportM.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Menu Screen",
				false);
	}
	
	
	protected static void MenuWindowMember() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MenuScreenMember.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Menu Screen",
				false);
	}
	
	
	/* -------------------- Other Screens -------------------- */
	
	
	protected static void CallsWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CallsScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Calls Screen",
				false);
	}
	
	
	protected static void CallInfoWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("CallInfoScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Call Info Screen",
				false);
	}
	
		
	protected static void RidesReportWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("RidesReportScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Rides Report Screen",
				false);
	}
	
	
	protected static void DefineRolesWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("DefineRoleScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Define Roles Screen",
				false);
	}
	
	
	protected static void InfoWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("InfoScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Import and Export Screen",
				false);
	}
	
	
	protected static void AddPhoneNumWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AddPhoneNumScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Add Voter's Phone Number Screen",
				false);
	}
	
	
	protected static void AssignRolesWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AssignRolesScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Assign Roles Screen",
				false);
	}
	
	
	protected static void RidesWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("RidesScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Rides Screen",
				false);
	}
	
	
	protected static void AssignRidesWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("AssignRidesScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Assign Rides Screen",
				false);
	}
	
	
	protected static void IssueBadgesWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("IssueBadgesScreen.fxml"),
				stage,
				null, null, null, null, null, null,
				true,
				"Issue Badges Screen",
				false);
	}
	
	
}
