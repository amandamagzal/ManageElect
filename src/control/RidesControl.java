package control;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;

import entity.Branch;
import entity.CallInfo;
import entity.ElectDayPosition;
import entity.RideToBallot;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import util.Consts;


/**
 * This class controls the rides functionality
 * It includes all the methods used to manage the rides and produce the needed rides report
 *
 */

public class RidesControl {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	public static RidesControl instance;


	public static RidesControl getInstance() {
		if (instance == null)
			instance = new RidesControl();
		return instance;
	}
	
	
	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	
	
	/**
	 * Gets all the branches from the database
	 * @return ArrayList of Branch
	 */
	
	public ArrayList<Branch> getBranches() {
		ArrayList<Branch> branches = new ArrayList<Branch>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_BRANCHES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					branches.add(new Branch(rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return branches;
	}
		
	
	/**
	 * Gets all the assigned rides from the database
	 * @return ArrayList of RideToBallot
	 */
		
	public ArrayList<RideToBallot> getRides() {
		ArrayList<RideToBallot> rides = new ArrayList<RideToBallot>();
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_RIDES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					rides.add(new RideToBallot(rs.getString(i++), rs.getString(i++), rs.getTime(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return rides;
	}
	
	
	/**
	 * Gets all the voters that need a ride from the database
	 * @return ArrayList of CallInfo
	 */
	
	public ArrayList<CallInfo> getVoters() {
		ArrayList<CallInfo> voters = new ArrayList<CallInfo>();
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_VOTERSFORRIDES);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					voters.add(new CallInfo(rs.getString(i++), rs.getTime(i++), rs.getTime(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return voters;
	}
	
	
	/**
	 * Gets all the members assigned to be drivers from the database
	 * @return ArrayList of ElectDayPosition
	 */
	
	public ArrayList<ElectDayPosition> getDrivers() {
		ArrayList<ElectDayPosition> drivers = new ArrayList<ElectDayPosition>();
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_DRIVERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					drivers.add(new ElectDayPosition(rs.getString(i++), rs.getTime(i++), rs.getTime(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return drivers;
	}
	
	
	/**
	 * Adds a ride assignment for a voter
	 * @param driverID
	 * @param pickupTime
	 * @param voterID
	 * @return
	 */
	
	public boolean addRide(String driverID, Time pickupTime, String voterID) {

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_RIDE)) {
				int i = 1;

				stmt.setString(i++, driverID);
				stmt.setTime(i++, pickupTime);
				stmt.setString(i++, voterID);

				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * Removes a voter's ride info
	 * @param voterID
	 * @return
	 */
	
	public boolean removeRide(String voterID) {

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_RIDE)) {
				int i = 1;

				stmt.setNull(i++, java.sql.Types.CHAR);
				stmt.setNull(i++, java.sql.Types.TIME);
				stmt.setString(i++, voterID);

				stmt.executeUpdate();
				return true;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	/**
	 * Produces a report of voters that need a ride for a branch
	 * @param branchNum
	 * @return
	 */
	
	public JFrame produceReport(Branch branchNum) {

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				params.put("branchNum", branchNum.toString());
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/RidesReport.jasper"),
						params, conn);
				JFrame frame = new JFrame("Rides Report for Branch " + branchNum);
				frame.getContentPane().add(new JRViewer(print));
				frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
				frame.pack();
				return frame;
			}
			catch (Exception e) {
				e.printStackTrace();
			}

		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return null;
	}
	
	
}
