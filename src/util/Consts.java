package util;

import java.net.URLDecoder;


public class Consts {

	protected static final String DB_FILEPATH = getDBPath();
	public static final String CONN_STR =
			//"jdbc:ucanaccess://src/entity/Transfer_Database.accdb;COLUMNORDER=DISPLAY";
			"jdbc:ucanaccess://" + DB_FILEPATH;
	public static final String JDBC_STR = "net.ucanaccess.jdbc.UcanaccessDriver";

	
	
	
	/* ------------------------------ VOTERS QUERIES ------------------------------ */
	
	public static final String SQL_SEL_VOTERS = "SELECT tblVoter.[Voter ID], tblVoter.[First Name], tblVoter.[Last Name], tblVoter.[Birth Date], tblVoter.[Gender] , tblVoter.[Phone Num] FROM tblVoter";
	public static final String SQL_UPD_PHONENUM = "{ call qryUpdPhoneNum(?,?) }";
	
	
	/* ------------------------------ MEMBERS QUERIES ------------------------------ */
	
	public static final String SQL_SEL_ROLES = "SELECT tblRole.[Role Name] FROM tblRole";
	public static final String SQL_INS_ROLE = "{ call qryInsRole(?,?) }";
	public static final String SQL_DEL_ROLE = "{ call qryDelRole(?) }";
	public static final String SQL_INS_POSITION = "{ call qryInsPosition(?,?,?,?,?) }";
	public static final String SQL_SEL_OBSERVERS = "SELECT tblPosition.[Member ID], tblPosition.[Start Time], tblPosition.[Finish Time], tblPosition.[Ballot ID] FROM tblPosition WHERE (((tblPosition.Role)=\"Observer\"))";
	public static final String SQL_SEL_BALLOTS = "SELECT tblBallot.[Ballot ID] FROM tblBallot";
	
	
	/* ------------------------------ CALLS QUERIES ------------------------------ */
	
	public static final String SQL_SEL_CALLS = "SELECT tblCall.[Call ID], tblCall.[Voter ID], tblCall.[Caller ID], tblCall.[Call Date], tblCall.[Call Time], tblCall.[Voter Answered], tblCall.[Will Vote], tblCall.[Supports Party], tblCall.[Interested in Course], tblCall.[Needs Ride], tblCall.[Pickup From], tblCall.[Pickup To] FROM tblCall";
	public static final String SQL_INS_CALL = "{ call qryInsCall(?,?,?,?,?,?,?,?,?,?,?,?) }";
	public static final String SQL_UPD_CALL = "{ call qryUpdCall(?,?,?,?,?,?,?,?,?,?,?,?) }";
	public static final String SQL_DEL_CALL = "{ call qryDelCall(?) }";
	
	
	/* ------------------------------ RIDES QUERIES ------------------------------ */
	
	public static final String SQL_SEL_BRANCHES = "SELECT tblBranch.[Branch ID] FROM tblBranch";
	public static final String SQL_SEL_RIDES = "SELECT tblVoter.[Voter ID], tblVoter.[Driver ID], tblVoter.[Pickup Time] FROM tblVoter WHERE tblVoter.[Driver ID] IS NOT NULL";
	public static final String SQL_SEL_VOTERSFORRIDES = "SELECT tblCall.[Voter ID], tblCall.[Pickup From], tblCall.[Pickup To] FROM tblCall WHERE (((tblCall.[Needs Ride])=\"Yes\"))";
	public static final String SQL_SEL_DRIVERS = "SELECT tblPosition.[Member ID], tblPosition.[Start Time], tblPosition.[Finish Time] FROM tblPosition WHERE (((tblPosition.Role)=\"Driver\"))";
	public static final String SQL_UPD_RIDE = "{ call qryUpdRide(?,?,?) }";
	
	
	/* ------------------------------ INFO QUERIES ------------------------------ */

	public static final String SQL_SELAll_VOTERS="SELECT tblVoter.* FROM tblVoter;";
	public static final String SQL_SELAll_BALLOTS="SELECT tblBallot.* FROM tblBallot;";
	
	

	/**
	 * find the correct path of the DB file
	 * @return the path of the DB file (from eclipse or with runnable file)
	 */
	
	private static String getDBPath() {
		try {
			String path = Consts.class.getProtectionDomain().getCodeSource().getLocation().getPath();
			String decoded = URLDecoder.decode(path, "UTF-8");
			if (decoded.contains(".jar")) {
				decoded = decoded.substring(0, decoded.lastIndexOf('/'));
				return decoded + "/database/ManageElectDB.accdb";
			} else {
				decoded = decoded.substring(0, decoded.lastIndexOf("bin/"));
				return decoded + "src/entity/ManageElectDB.accdb";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
}
