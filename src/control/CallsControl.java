package control;

import entity.CallInfo;
import util.Consts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * This class controls the calls functionality
 * It includes all the methods used to manage the calls to the voters
 *
 */

public class CallsControl {

	
	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	private static CallsControl instance;

	
	public static CallsControl getInstance() {
		if (instance == null)
			instance = new CallsControl();
		return instance;
	}



	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */

	
	/**
	 * Gets all the calls from the database
	 * @return ArrayList of CallInfo
	 */

	public ArrayList<CallInfo>  getCalls() {
		ArrayList<CallInfo> calls = new ArrayList<CallInfo>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_CALLS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					calls.add(new CallInfo(rs.getString(i++), 
							rs.getString(i++), 
							rs.getString(i++), 
							rs.getDate(i++), 
							rs.getTime(i++),
							rs.getBoolean(i++), 
							rs.getString(i++), 
							rs.getString(i++),
							rs.getString(i++), 
							rs.getString(i++), 
							rs.getTime(i++), 
							rs.getTime(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return calls;
	}

	
	/**
	 * Adds a call of a voter
	 * @param c
	 * @return
	 */
	
	public boolean addCall(CallInfo c) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_INS_CALL)) {
				int i = 1;
				stmt.setString(i++, c.getCallNum()); 
				stmt.setString(i++, c.getVoterID());
				stmt.setString(i++, c.getCallerID());
				stmt.setDate(i++, c.getCallDate());
				stmt.setTime(i++, c.getCallTime());
				stmt.setBoolean(i++, c.getGotAnswer());
				
				if(c.getGotAnswer() == true) {
					stmt.setString(i++, c.getWillVote().toString());
					stmt.setString(i++, c.getSupportParty().toString());
					stmt.setString(i++, c.getInterestedInCourse().toString());
					stmt.setString(i++, c.getNeedRide().toString());
					
					if(c.getNeedRide().getAnswer().equals("Yes")) {
						stmt.setTime(i++, c.getPickupRangeStart());
						stmt.setTime(i++, c.getPickupRangeEnd());
					}
					else {
						stmt.setNull(i++, java.sql.Types.TIME);
						stmt.setNull(i++, java.sql.Types.TIME);
					}
				}
				else {
					stmt.setNull(i++, java.sql.Types.CHAR);
					stmt.setNull(i++, java.sql.Types.CHAR);
					stmt.setNull(i++, java.sql.Types.CHAR);
					stmt.setNull(i++, java.sql.Types.CHAR);
					stmt.setNull(i++, java.sql.Types.TIME);
					stmt.setNull(i++, java.sql.Types.TIME);
				}
				
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
	 * Deletes a call for a voter
	 * @param callNum
	 * @return
	 */

	public boolean removeCall(String voterID) {
		
		try {
			Class.forName(util.Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_DEL_CALL)) {
				stmt.setString(1, voterID);
				stmt.execute();
				return true;
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	/**
	 * Edits a call for a voter
	 * @param c
	 * @return
	 */
	
	public boolean editCall(CallInfo c) {
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_CALL)) {
				int i = 1;
				 
				stmt.setString(i++, c.getCallNum());
				stmt.setString(i++, c.getVoterID());
				stmt.setString(i++, c.getCallerID());
				stmt.setDate(i++, c.getCallDate());
				stmt.setTime(i++, c.getCallTime());
				stmt.setBoolean(i++, c.getGotAnswer());
				stmt.setString(i++, c.getWillVote().toString());
				stmt.setString(i++, c.getSupportParty().toString());
				stmt.setString(i++, c.getInterestedInCourse().toString());
				stmt.setString(i++, c.getNeedRide().toString());
				stmt.setTime(i++, c.getPickupRangeStart());
				stmt.setTime(i++, c.getPickupRangeEnd());
				
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
	 * Gets the number of the last call from the database
	 * @return
	 */

	public int getLastCallNum() {
		ArrayList<CallInfo> calls = new ArrayList<CallInfo>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(Consts.SQL_SEL_CALLS);
					ResultSet rs = stmt.executeQuery())
			{
				while (rs.next()) {
					int i = 1;
					calls.add(new CallInfo(rs.getString(i++), 
							rs.getString(i++), 
							rs.getString(i++), 
							rs.getDate(i++), 
							rs.getTime(i++),
							rs.getBoolean(i++), 
							rs.getString(i++), 
							rs.getString(i++),
							rs.getString(i++), 
							rs.getString(i++), 
							rs.getTime(i++), 
							rs.getTime(i++)));
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return calls.size();
	}
	

}
