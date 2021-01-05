package util;


/**
 * This class represents an enumeration for status.
 *
 */

public enum E_Status {


	/* ------------------------- Values ------------------------- */
	
	Single ("Single"), Married ("Married"), Divorced ("Divorced"), Widowed ("Widowed");

	
	/* ------------------------- Variable ------------------------- */
	
	private final String status;

	
	/* ------------------------- Constructor ------------------------- */
	
	E_Status(String status) {
		this.status = status;
	}

	
	/* ------------------------- Methods ------------------------- */
	
	public String getStatus() {
		return status;
	}

	
	public static E_Status returnStatus(String val) {
		switch (val) {
		case "Single":
			return E_Status.Single;
		case "Married":
			return E_Status.Married;
		case "Divorced":
			return E_Status.Divorced;
		case "Widowed":
			return E_Status.Widowed;
		default:
			return E_Status.Single;
		}
	}
	
	
}
