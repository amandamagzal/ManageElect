package control;

import entity.Voter;
import util.Consts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * This class controls the voters functionality
 * It includes all the methods used to add and remove a voter's phone number
 *
 */

public class VoterControl {
	

	
	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	public static VoterControl instance;

	public static VoterControl getInstance() {
		if (instance == null)
			instance = new VoterControl();
		return instance;
	}

	

	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */

	
	/**
	 * Gets all the voters from the database
	 * @return ArrayList of Voter
	 */
	
	public ArrayList<Voter> getVoters() {
		ArrayList<Voter> voters = new ArrayList<Voter>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_VOTERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					voters.add(new Voter(rs.getString(i++), rs.getString(i++), rs.getString(i++), 
							rs.getDate(i++), rs.getString(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return voters;	}
	
	
	/**
	 * Adds a voter's phone num
	 * @param phoneNum
	 * @param voterID
	 * @return
	 */
	
	public boolean addPhoneNum(String phoneNum, String voterID) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_PHONENUM)) {
				int i = 1;

				stmt.setString(i++, phoneNum);
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
	 * Removes a voter's phone num
	 * @param voterID
	 * @return
	 */
	
	public boolean removePhoneNum(String voterID) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_UPD_PHONENUM)) {
				int i = 1;

				stmt.setNull(i++, java.sql.Types.CHAR);
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

	
}
