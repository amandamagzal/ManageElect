package entity;

import java.sql.Time;


/**
 * This class represents a ride assignment for a voter.
 *
 */

public class RideToBallot {
	
	
	/* -------------------- Attributes -------------------- */
	
	private String voterID;
	private String driverID;
	private Time pickupTime;
	
	
	/* -------------------- Constructor -------------------- */

	public RideToBallot(String voterID, String driverID, Time pickupTime) {
		this.voterID = voterID;
		this.driverID = driverID;
		this.pickupTime = pickupTime;
	}

	
	/* -------------------- Getters & Setters -------------------- */
	
	public String getDriverID() {
		return driverID;
	}

	
	public void setDriverID(String driverID) {
		this.driverID = driverID;
	}

	
	public String getVoterID() {
		return voterID;
	}

	
	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	
	public Time getPickupTime() {
		return pickupTime;
	}

	
	public void setPickupTime(Time pickupTime) {
		this.pickupTime = pickupTime;
	}


	/* -------------------- To String -------------------- */
	
	@Override
	public String toString() {
		return " Driver ID = " + driverID + 
				", Voter ID = " + voterID + ", Pickup Time = " + pickupTime;
	}
	
	
}
