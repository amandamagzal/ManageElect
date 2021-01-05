package entity;


/**
 * This class represents a ballot box object.
 *
 */

public class BallotBox {
	

	/* -------------------- Attributes -------------------- */

	private String ballotNum;
	private String branchNum;
	private String address;
	private String location;
	private String phoneNum;
	private boolean isAccessible;

	
	/* -------------------- Constructor -------------------- */

	public BallotBox(String ballotNum, String branchNum, String address, 
			String location, String phoneNum, boolean isAccessible) {
		this.ballotNum = ballotNum;
		this.branchNum = branchNum;
		this.address = address;
		this.location = location;
		this.phoneNum = phoneNum;
		this.isAccessible = isAccessible;
	}
	
	
	public BallotBox(String ballotNum) {
		this.ballotNum = ballotNum;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	public String getBallotNum() {
		return ballotNum;
	}


	public void setBallotNum(String ballotNum) {
		this.ballotNum = ballotNum;
	}

	
	public String getBranchNum() {
		return branchNum;
	}


	public void setBranchNum(String branchNum) {
		this.branchNum = branchNum;
	}
	

	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getPhoneNum() {
		return phoneNum;
	}


	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}


	public boolean isAccessible() {
		return isAccessible;
	}


	public void setIsAccessible(boolean isAccessible) {
		this.isAccessible = isAccessible;
	}

	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return ballotNum;
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
		BallotBox other = (BallotBox) obj;
		if (ballotNum != other.ballotNum)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ballotNum == null) ? 0 : ballotNum.hashCode());
		return result;
	}
	
	
}
