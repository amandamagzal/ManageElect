package util;


/**
 * This class represents an enumeration for gender.
 *
 */

public enum E_Gender {

	
	/* ------------------------- Values ------------------------- */
	
	Male ("Male"), Female ("Female"), Other ("Other");

	
	/* ------------------------- Variable ------------------------- */
	
	private final String gender;

	
	/* ------------------------- Constructor ------------------------- */
	
	E_Gender(String gender) {
		this.gender = gender;
	}

	
	/* ------------------------- Methods ------------------------- */
	
	public String getGender() {
		return gender;
	}

	
	public static E_Gender returnGender(String val) {
		switch (val) {
		case "Male":
			return E_Gender.Male;
		case "Female":
			return E_Gender.Female;
		case "Other":
			return E_Gender.Other;
		default:
			return E_Gender.Other;
		}
	}

	
}
