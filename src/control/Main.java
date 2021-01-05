package control;

import org.apache.log4j.BasicConfigurator;

import boundary.ViewLogic;
import javafx.application.Application;
import javafx.stage.Stage;


/**
 * This is the main class that launches the application
 *
 */

public class Main extends Application{

	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		launch(args);
	}


	@Override
	public void start(Stage arg0){
		try {
		ViewLogic.initUI();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
