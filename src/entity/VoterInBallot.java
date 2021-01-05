package entity;


/**
 * This class represents an assignment of a voter to a ballot box.
 *
 */

public class VoterInBallot {

	
	/* -------------------- Attributes -------------------- */
	
	private String voterID;
	private String ballotNum;
	private int serialNum;	
	
	
	/* -------------------- Constructor -------------------- */
	
	public VoterInBallot(String voterID, String ballotNum, int serialNum) {
		this.voterID = voterID;
		this.ballotNum = ballotNum;
		this.serialNum = serialNum;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getVoterID() {
		return voterID;
	}


	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}


	public String getBallotNum() {
		return ballotNum;
	}


	public void setBallotNum(String ballotNum) {
		this.ballotNum = ballotNum;
	}


	public int getSerialNum() {
		return serialNum;
	}


	public void setSerialNum(int serialNum) {
		this.serialNum = serialNum;
	}
	
	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return "Voter voterID = " + voterID + ", Ballot Number = " + ballotNum + ", Serial Number = " + serialNum;
	}	
	
	
}
