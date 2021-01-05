package control;

import entity.BallotBox;
import entity.Branch;
import entity.ElectDayPosition;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.swing.JRViewer;
import util.Consts;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JFrame;


/**
 * This class controls the members functionality
 * It includes all the methods used to assign members to roles and issue badges for observers
 *
 */

public class MemberControl {
	
	

	/* -------------------------------------------------------------- */
	/* ------------------------- Attributes ------------------------- */
	/* -------------------------------------------------------------- */
	
	
	private static MemberControl instance;


	public static MemberControl getInstance() {
		if (instance == null)
			instance = new MemberControl();
		return instance;
	}


	
	/* ----------------------------------------------------------- */
	/* ------------------------- Methods ------------------------- */
	/* ----------------------------------------------------------- */
	

	/**
	 * Adds a new role
	 * @param roleName
	 * @param password
	 * @return
	 */
	
	public boolean addRole(String roleName, String password) {

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_INS_ROLE)) {
				int i = 1;
				stmt.setString(i++, roleName);			
				stmt.setString(i++, password);
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
	 * Deletes a role
	 * @param roleName
	 * @return
	 */
	
	public boolean removeRole(String roleName) {

		try {
			Class.forName(util.Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					CallableStatement stmt = conn.prepareCall(util.Consts.SQL_DEL_ROLE)) {
				stmt.setString(1, roleName);
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
	 * Adds a member's position for the election day
	 * @param p
	 * @return
	 */
	
	public boolean addPosition(ElectDayPosition p) {
		
		if (p.getBallotNum() == null) {
			
			try {
				Class.forName(Consts.JDBC_STR);
				try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(util.Consts.SQL_INS_POSITION)) {
					
					int i = 1;
					stmt.setString(i++, p.getMemberID());
					stmt.setString(i++, p.getRole().getRole());
					stmt.setTime(i++, p.getStartTime());
					stmt.setTime(i++, p.getFinishTime());
					stmt.setNull(i++, java.sql.Types.CHAR);
					
					stmt.executeUpdate();
					return true;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}	
		}
		else {
			try {
				Class.forName(Consts.JDBC_STR);
				try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
						CallableStatement stmt = conn.prepareCall(util.Consts.SQL_INS_POSITION)) {
					
					int i = 1;
					stmt.setString(i++, p.getMemberID());
					stmt.setString(i++, p.getRole().getRole());
					stmt.setTime(i++, p.getStartTime());
					stmt.setTime(i++, p.getFinishTime());
					stmt.setString(i++, p.getBallotNum());
					
					stmt.executeUpdate();
					return true;
					
				} catch (SQLException e) {
					e.printStackTrace();
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}
	
	
	/**
	 * Gets all the members assigned to be observers from the database
	 * @return ArrayList of ElectDayPosition
	 */
	
	public ArrayList<ElectDayPosition> getObservers() {
		ArrayList<ElectDayPosition> observers = new ArrayList<ElectDayPosition>();
				
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_OBSERVERS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					observers.add(new ElectDayPosition(rs.getString(i++), rs.getTime(i++), rs.getTime(i++), rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return observers;
	}
	
	
	/**
	 * Gets all the branches from the database
	 * @return ArrayList of Branch
	 */
	
	public ArrayList<BallotBox> getBallots() {
		ArrayList<BallotBox> ballots = new ArrayList<BallotBox>();

		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(util.Consts.CONN_STR);
					PreparedStatement stmt = conn.prepareStatement(util.Consts.SQL_SEL_BALLOTS);
					ResultSet rs = stmt.executeQuery()) {
				while (rs.next()) {
					int i = 1;
					ballots.add(new BallotBox(rs.getString(i++)));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return ballots;
	}
		
	
	/**
	 * Issues badges for the observers
	 * @param branchNum
	 * @return
	 */
	
	public JFrame issueBadges(Branch branchNum) {
		
		try {
			Class.forName(Consts.JDBC_STR);
			try (Connection conn = DriverManager.getConnection(Consts.CONN_STR)){
				HashMap<String, Object> params = new HashMap<>();
				params.put("branchNum", branchNum.toString());
				JasperPrint print = JasperFillManager.fillReport(
						getClass().getResourceAsStream("/boundary/Badges.jasper"),
						params, conn);
				JFrame frame = new JFrame("Badges for Observers in Branch " + branchNum);
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
