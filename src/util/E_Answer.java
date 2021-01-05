package util;


/**
 * This class represents an enumeration for answer.
 *
 */

public enum E_Answer {

	
	/* ------------------------- Values ------------------------- */
	
	Yes ("Yes"), No ("No"), DidntDecide ("DidntDecide"), DidntAnswer ("");

	
	/* ------------------------- Variable ------------------------- */
	
	private final String answer;

	
	/* ------------------------- Constructor ------------------------- */
	
	E_Answer(String answer) {
		this.answer = answer;
	}

	
	/* ------------------------- Methods ------------------------- */
	
	public String getAnswer() {
		return answer;
	}

	
	public static E_Answer returnAnswer(String val) {
		if (val == null)
			return E_Answer.DidntAnswer;
		
		switch (val) {
		case "Yes":
			return E_Answer.Yes;
		case "No":
			return E_Answer.No;
		case "Didn't Decide":
			return E_Answer.DidntDecide;
		default:
			return E_Answer.DidntAnswer;
		}
	}
	
	
}
