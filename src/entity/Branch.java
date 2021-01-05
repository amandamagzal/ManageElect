package entity;


/**
 * This class represents a branch object.
 *
 */

public class Branch {
	
	
	/* -------------------- Attributes -------------------- */

	private String branchNum;
	private String managerID;
	private String transportManagerID;

	
	/* -------------------- Constructor -------------------- */

	public Branch(String branchNum, String managerID, String transportManagerID) {
		this.branchNum = branchNum;
		this.managerID = managerID;
		this.transportManagerID = transportManagerID;
	}
	
	
	public Branch(String branchNum) {
		this.branchNum = branchNum;
	}


	/* -------------------- Constructor -------------------- */


	public String getBranchNum() {
		return branchNum;
	}


	public void setBranchNum(String branchNum) {
		this.branchNum = branchNum;
	}
	
	
	public String getManagerID() {
		return managerID;
	}


	public void setManagerID(String managerID) {
		this.managerID = managerID;
	}
	

	public String getTransportManagerID() {
		return transportManagerID;
	}


	public void setTransportManagerID(String transportManagerID) {
		this.transportManagerID = transportManagerID;
	}
	
	
	/* -------------------- To String -------------------- */

	@Override
	public String toString() {
		return branchNum;
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
		Branch other = (Branch) obj;
		if (branchNum != other.branchNum)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((branchNum == null) ? 0 : branchNum.hashCode());
		return result;
	}
	
	
}
