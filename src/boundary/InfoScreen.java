package boundary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.json.simple.JsonArray;
import org.json.simple.JsonObject;
import org.json.simple.Jsoner;

import com.jfoenix.controls.JFXButton;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;


/**
 * This class represent the screen used to import and export information
 *
 */

public class InfoScreen implements Initializable{
	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	@FXML private JFXButton btn_menu;
	@FXML private JFXButton btn_voters;
	@FXML private JFXButton btn_ballots;
	@FXML private JFXButton btn_export;
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
		
	/**
	 * Imports voters info
	 */
	
	public void importVoters() {
		
		 try {
             Class.forName(util.Consts.JDBC_STR);
             try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(
	                    		util.Consts.SQL_SELAll_VOTERS);
	                    ResultSet rs = stmt.executeQuery()){
          	   JsonArray voters = new JsonArray();
               while (rs.next()) {
              	  JsonObject v = new JsonObject();
              	   
              	  for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
              		  v.put(rs.getMetaData().getColumnName(i), rs.getString(i));
              	   
              	voters.add(v);
               }
                 
          	   JsonObject doc = new JsonObject();
          	   doc.put("Ballots_info", voters);
                 
                 File file = new File("json/voters.json");
                 file.getParentFile().mkdir();
                 
                 try (FileWriter writer = new FileWriter(file)) {
              	   writer.write(Jsoner.prettyPrint(doc.toJson()));
              	   writer.flush();
                     System.out.println("Voters data exported successfully!");
                 } catch (IOException e) {
              	   e.printStackTrace();
                 }
             } catch (SQLException | NullPointerException e) {
                 e.printStackTrace();
             }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
    }
	
	
	/**
	 * Imports ballots info
	 */
	
	public void importBallots() {
		
		 try {
             Class.forName(util.Consts.JDBC_STR);
             try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(
	                    		util.Consts.SQL_SELAll_BALLOTS);
	                    ResultSet rs = stmt.executeQuery()){
          	   JsonArray ballots = new JsonArray();
               while (rs.next()) {
              	  JsonObject ballot = new JsonObject();
              	   
              	  for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
              		  ballot.put(rs.getMetaData().getColumnName(i), rs.getString(i));
              	   
              	  ballots.add(ballot);
               }
                 
          	   JsonObject doc = new JsonObject();
          	   doc.put("Ballots_info", ballots);
                 
                 File file = new File("json/ballots.json");
                 file.getParentFile().mkdir();
                 
                 try (FileWriter writer = new FileWriter(file)) {
              	   writer.write(Jsoner.prettyPrint(doc.toJson()));
              	   writer.flush();
                     System.out.println("Ballots data exported successfully!");
                 } catch (IOException e) {
              	   e.printStackTrace();
                 }
             } catch (SQLException | NullPointerException e) {
                 e.printStackTrace();
             }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
         }
	}
		
	
	/**
	 * Exports voters info to ElectDaySystem
	 */
	
	public void exportToElectDay() {
		 try {
             Class.forName(util.Consts.JDBC_STR);
             try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
	                    PreparedStatement stmt = conn.prepareStatement(
	                    		util.Consts.SQL_SELAll_VOTERS);
	                    ResultSet rs = stmt.executeQuery()){
          	   JsonArray voters = new JsonArray();
                 while (rs.next()) {
              	   JsonObject voter = new JsonObject();
              	   
              	   for (int i = 1; i < rs.getMetaData().getColumnCount(); i++)
              		   voter.put(rs.getMetaData().getColumnName(i), rs.getString(i));
              	   
              	   voters.add(voter);
                 }
                 
          	   JsonObject doc = new JsonObject();
          	   doc.put("Voters_info", voters);
                 
                 File file = new File("json/voters.json");
                 file.getParentFile().mkdir();
                 
                 try (FileWriter writer = new FileWriter(file)) {
              	   writer.write(Jsoner.prettyPrint(doc.toJson()));
              	   writer.flush();
                     System.out.println("Voters data exported successfully!");
                 } catch (IOException e) {
              	   e.printStackTrace();
                 }
             } catch (SQLException | NullPointerException e) {
                 e.printStackTrace();
             }
         } catch (ClassNotFoundException e) {
             e.printStackTrace();
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
		
		
	}
	
	
}
