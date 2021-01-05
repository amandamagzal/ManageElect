package entity;

import java.sql.Time;

import util.E_Role;


/**
 * This class represents an assignment of an active member to a role.
 *
 */

public class ElectDayPosition {
	
	
	/* -------------------- Attributes -------------------- */
	
	private String memberID;
	private E_Role role;
	private Time startTime;
	private Time finishTime;
	private String ballotNum;
	
	
	/* -------------------- Constructors -------------------- */
	
	public ElectDayPosition(String memberID, E_Role role, Time startTime, Time finishTime, String ballotNum) {
		this.memberID = memberID;
		this.role = role;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.ballotNum = ballotNum;
	}
	
	
	public ElectDayPosition(String memberID, String role, Time startTime, Time finishTime, String ballotNum) {
		this.memberID = memberID;
		this.role = E_Role.valueOf(role);
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.ballotNum = ballotNum;
	}
	
	
	public ElectDayPosition(String memberID, E_Role role, Time startTime, Time finishTime) {
		this.memberID = memberID;
		this.role = role;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}
	
	
	public ElectDayPosition(String memberID, String role, Time startTime, Time finishTime) {
		this.memberID = memberID;
		this.role = E_Role.valueOf(role);
		this.startTime = startTime;
		this.finishTime = finishTime;
	}
	
	
	public ElectDayPosition(String memberID, Time startTime, Time finishTime) {
		this.memberID = memberID;
		this.startTime = startTime;
		this.finishTime = finishTime;
	}
	
	
	public ElectDayPosition(String memberID, Time startTime, Time finishTime, String ballotNum) {
		this.memberID = memberID;
		this.startTime = startTime;
		this.finishTime = finishTime;
		this.ballotNum = ballotNum;
	}

	
	/* -------------------- Getters & Setters -------------------- */
	
	public String getMemberID() {
		return memberID;
	}


	public void setMemberID(String memberID) {
		this.memberID = memberID;
	}


	public E_Role getRole() {
		return role;
	}


	public void setRole(E_Role role) {
		this.role = role;
	}


	public Time getStartTime() {
		return startTime;
	}


	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}


	public Time getFinishTime() {
		return finishTime;
	}


	public void setFinishTime(Time finishTime) {
		this.finishTime = finishTime;
	}


	public String getBallotNum() {
		return ballotNum;
	}


	public void setBallotNum(String ballotNum) {
		this.ballotNum = ballotNum;
	}


	/* -------------------- To String -------------------- */
	
	@Override
	public String toString() {
		
		return "Member ID = " + memberID + ", Role = " + role + ", Start Time = " + startTime + 
				", Finish Time = " + finishTime + ", Ballot Box = " + ballotNum;
	}

	
	/* -------------------- Equals -------------------- */

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ElectDayPosition other = (ElectDayPosition) obj;
		if (memberID != other.memberID)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((memberID == null) ? 0 : memberID.hashCode());
		return result;
	}

	
}
