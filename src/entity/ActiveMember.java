package entity;

import java.sql.Date;

import util.E_Gender;
import util.E_Status;


/**
 * This class represents an active member object.
 *
 */

public class ActiveMember extends Voter {

	
	/* -------------------- Attributes -------------------- */
	
	private String nationality;
	private boolean ownsCar;
	private int numOfChildren;
	private String branchNum;
	private String password;
	
	
	/* -------------------- Constructor -------------------- */
	
	public ActiveMember(String voterID, String firstName, String lastName, Date dateOfBirth, E_Gender gender,
			E_Status familyStatus, String phoneNum, String address, String nationality, boolean ownsCar,
			int numOfChildren, String branchNum, String password) {
		super(voterID, firstName, lastName, dateOfBirth, gender, familyStatus, phoneNum, address);
		this.nationality = nationality;
		this.ownsCar = ownsCar;
		this.numOfChildren = numOfChildren;
		this.branchNum = branchNum;
		this.password = password;
	}
	
	
	public ActiveMember(String voterID, String firstName, String lastName, Date dateOfBirth, String gender,
			String familyStatus, String phoneNum, String address, String nationality, boolean ownsCar,
			int numOfChildren, String branchNum, String password) {
		super(voterID, firstName, lastName, dateOfBirth, gender, familyStatus, phoneNum, address);
		this.nationality = nationality;
		this.ownsCar = ownsCar;
		this.numOfChildren = numOfChildren;
		this.branchNum = branchNum;
		this.password = password;
	}
	
	
	public ActiveMember(String voterID, String password) {
		super(voterID);
		this.password = password;
	}

	
	/* -------------------- Getters & Setters -------------------- */

	
	public String getNationality() {
		return nationality;
	}


	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	
	public boolean ownsCar() {
		return ownsCar;
	}

	
	public void setOwnsCar(boolean ownsCar) {
		this.ownsCar = ownsCar;
	}

	
	public int getNumOfChildren() {
		return numOfChildren;
	}

	
	public void setNumOfChildren(int numOfChildren) {
		this.numOfChildren = numOfChildren;
	}
	
	
	public String getBranchNum() {
		return branchNum;
	}


	public void setBranchNum(String branchNum) {
		this.branchNum = branchNum;
	}

	
	public String getPassword() {
		return password;
	}
	
	
	public void setPassword(String password) {
		this.password = password;
	}


	/* -------------------- To String -------------------- */
	
	@Override
	public String toString() {
		return "ActiveMember [nationality=" + nationality + ", ownsCar=" + ownsCar + ", numOfChildren=" + numOfChildren
				+ ", branchNum=" + branchNum + ", password=" + password + "]";
	}
	
	
}
