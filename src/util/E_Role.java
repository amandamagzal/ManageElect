package util;

/**
 * This class represents an enumeration for role.
 *
 */

public enum E_Role {

	
	/* ------------------------- Values ------------------------- */
	
	Driver ("Driver"), Caller ("Caller"), Observer ("Observer"),
	CampaignManager ("Campaign Manager"), BranchManager ("Branch Manager"), TransportManager ("Transport Manager") ;

	
	/* ------------------------- Variable ------------------------- */
	
	private final String role;

	
	/* ------------------------- Constructor ------------------------- */
	
	E_Role(String role) {
		this.role = role;
	}

	
	/* ------------------------- Methods ------------------------- */
	
	public String getRole() {
		return role;
	}

	
	public static E_Role returnRole(String val) {
		switch (val) {
		case "Driver":
			return E_Role.Driver;
		case "Caller":
			return E_Role.Caller;
		case "Observer":
			return E_Role.Observer;
		case "Campaign Manager":
			return E_Role.CampaignManager;
		case "Branch Manager":
			return E_Role.BranchManager;
		case "Transport Manager":
			return E_Role.TransportManager;	
		default:
			return E_Role.Caller;
		}
	}
	
	
}
