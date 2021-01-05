package entity;

import java.sql.Date;
import java.sql.Time;

import util.E_Answer;


/**
 * This class represents a call object.
 *
 */

public class CallInfo {
	
	
	/* -------------------- Attributes -------------------- */
	
	private String callNum;
	private String voterID;
	private String callerID;
	private Date callDate;
	private Time callTime;
	private boolean gotAnswer;
	private E_Answer willVote;
	private E_Answer supportParty;
	private E_Answer interestedInCourse;
	private E_Answer needRide;
	private Time pickupRangeStart;
	private Time pickupRangeEnd;
	
	
	/* -------------------- Constructors -------------------- */
	
	
	public CallInfo(String callNum, String voterID, String callerID, 
			Date callDate, Time callTime, boolean gotAnswer, 
			E_Answer willVote, E_Answer supportParty, E_Answer interestedInCourse, E_Answer needRide, 
			Time pickupRangeStart, Time pickupRangeEnd) {
		this.callNum = callNum;
		this.voterID = voterID;
		this.callerID = callerID;
		this.callDate = callDate;
		this.callTime = callTime;
		this.gotAnswer = gotAnswer;
		this.willVote = willVote;
		this.supportParty = supportParty;
		this.interestedInCourse = interestedInCourse;
		this.needRide = needRide;
		this.pickupRangeStart = pickupRangeStart;
		this.pickupRangeEnd = pickupRangeEnd;
	}
	
	
	public CallInfo(String callNum, String voterID, String callerID, 
			Date callDate, Time callTime, boolean gotAnswer, 
			String willVote, String supportParty, String interestedInCourse, String needRide, 
			Time pickupRangeStart, Time pickupRangeEnd) {
		
		this.callNum = callNum;
		this.voterID = voterID;
		this.callerID = callerID;
		this.callDate = callDate;
		this.callTime = callTime;
		this.gotAnswer = gotAnswer;
		
		if (willVote == null || (willVote != null && willVote.isEmpty())) 
			this.willVote = E_Answer.DidntAnswer;
		else if (willVote.toLowerCase().contains("decide"))
			this.willVote = E_Answer.DidntDecide;
		else
			this.willVote = E_Answer.valueOf(willVote);
		
		if (supportParty == null || (supportParty != null && supportParty.isEmpty())) 
			this.supportParty = E_Answer.DidntAnswer;
		else if (supportParty.toLowerCase().contains("decide"))
			this.supportParty = E_Answer.DidntDecide;
		else
			this.supportParty = E_Answer.valueOf(supportParty);
		
		if (interestedInCourse == null || (interestedInCourse != null && interestedInCourse.isEmpty())) 
			this.interestedInCourse = E_Answer.DidntAnswer;
		else if (interestedInCourse.toLowerCase().contains("decide"))
			this.interestedInCourse = E_Answer.DidntDecide;
		else
			this.interestedInCourse = E_Answer.valueOf(interestedInCourse);
		
		if (needRide == null || (needRide != null && needRide.isEmpty())) 
			this.needRide = E_Answer.DidntAnswer;
		else if (needRide.toLowerCase().contains("decide"))
			this.needRide = E_Answer.DidntDecide;
		else
			this.needRide = E_Answer.valueOf(needRide);
		
		this.pickupRangeStart = pickupRangeStart;
		this.pickupRangeEnd = pickupRangeEnd;
	}
	
	
	public CallInfo(String callNum, String voterID, String callerID, Date callDate, Time callTime, 
			boolean gotAnswer, String willVote, String supportParty, String interestedInCourse, String needRide) {
		this.callNum = callNum;
		this.voterID = voterID;
		this.callerID = callerID;
		this.callDate = callDate;
		this.callTime = callTime;
		this.gotAnswer = gotAnswer;
		
		if (willVote == null || (willVote != null && willVote.isEmpty())) 
			this.willVote = E_Answer.DidntAnswer;
		else if (willVote.toLowerCase().contains("decide"))
			this.willVote = E_Answer.DidntDecide;
		else
			this.willVote = E_Answer.valueOf(willVote);
		
		if (supportParty == null || (supportParty != null && supportParty.isEmpty())) 
			this.supportParty = E_Answer.DidntAnswer;
		else if (supportParty.toLowerCase().contains("decide"))
			this.supportParty = E_Answer.DidntDecide;
		else
			this.supportParty = E_Answer.valueOf(supportParty);
		
		if (interestedInCourse == null || (interestedInCourse != null && interestedInCourse.isEmpty())) 
			this.interestedInCourse = E_Answer.DidntAnswer;
		else if (interestedInCourse.toLowerCase().contains("decide"))
			this.interestedInCourse = E_Answer.DidntDecide;
		else
			this.interestedInCourse = E_Answer.valueOf(interestedInCourse);
		
		if (needRide == null || (needRide != null && needRide.isEmpty())) 
			this.needRide = E_Answer.DidntAnswer;
		else if (needRide.toLowerCase().contains("decide"))
			this.needRide = E_Answer.DidntDecide;
		else
			this.needRide = E_Answer.valueOf(needRide);
	}
	
	
	public CallInfo(String callNum, String voterID, String callerID, 
			Date callDate, Time callTime, boolean gotAnswer) {
		this.callNum = callNum;
		this.voterID = voterID;
		this.callerID = callerID;
		this.callDate = callDate;
		this.callTime = callTime;
		this.gotAnswer = gotAnswer;
	}
	
	
	public CallInfo(String voterID, Time pickupRangeStart, Time pickupRangeEnd) {
		this.voterID = voterID;
		this.pickupRangeStart = pickupRangeStart;
		this.pickupRangeEnd = pickupRangeEnd;
	}
	
	
	/* -------------------- Getters & Setters -------------------- */

	public String getCallNum() {
		return callNum;
	}

	
	public void setCallNum(String callNum) {
		this.callNum = callNum;
	}

	
	public String getVoterID() {
		return voterID;
	}

	
	public void setVoterID(String voterID) {
		this.voterID = voterID;
	}

	
	public String getCallerID() {
		return callerID;
	}

	
	public void setCallerID(String callerID) {
		this.callerID = callerID;
	}

	
	public Date getCallDate() {
		return callDate;
	}

	
	public void setCallDate(Date callDate) {
		this.callDate = callDate;
	}
	
	
	public Time getCallTime() {
		return callTime;
	}

	
	public void setCallTime(Time callTime) {
		this.callTime = callTime;
	}

	
	public boolean getGotAnswer() {
		return gotAnswer;
	}

	
	public void setGotAnswer(boolean gotAnswer) {
		this.gotAnswer = gotAnswer;
	}

	
	public E_Answer getWillVote() {
		return willVote;
	}

	
	public void setWillVote(E_Answer willVote) {
		this.willVote = willVote;
	}

	
	public E_Answer getSupportParty() {
		return supportParty;
	}

	
	public void setSupportParty(E_Answer supportParty) {
		this.supportParty = supportParty;
	}

	
	public E_Answer getInterestedInCourse() {
		return interestedInCourse;
	}

	
	public void setInterestedInCourse(E_Answer interestedInCourse) {
		this.interestedInCourse = interestedInCourse;
	}

	
	public E_Answer getNeedRide() {
		return needRide;
	}

	
	public void setNeedRide(E_Answer needRide) {
		this.needRide = needRide;
	}

	
	public Time getPickupRangeStart() {
		return pickupRangeStart;
	}

	
	public void setPickupRangeStart(Time pickupRangeStart) {
		this.pickupRangeStart = pickupRangeStart;
	}

	
	public Time getPickupRangeEnd() {
		return pickupRangeEnd;
	}

	
	public void setPickupRangeEnd(Time pickupRangeEnd) {
		this.pickupRangeEnd = pickupRangeEnd;
	}


	/* -------------------- To String -------------------- */

	
	@Override
	public String toString() {
		return "Call Number = " + callNum + ", Voter ID = " + voterID + ", Caller ID = " + callerID +
				", Date = " + callDate + ", Time = " + callTime + ", Got Answer = " + gotAnswer + 
				", Will Vote = " + willVote + ", Supports Party = " + supportParty +
				", Interested In Course = " + interestedInCourse + ", Needs a Ride = " + needRide + 
				", Pickup Range: " + pickupRangeStart + " - " + pickupRangeEnd;
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
		CallInfo other = (CallInfo) obj;
		if (callDate == null) {
			if (other.callDate != null)
				return false;
		} else if (!callDate.equals(other.callDate))
			return false;
		if (callNum != other.callNum)
			return false;
		if (voterID != other.voterID)
			return false;
		return true;
	}
	
	
	/* -------------------- Hashing -------------------- */
	
	@Override
	public int hashCode() {
		
		final int prime = 31;
		int result = 1;
		result = prime * result + ((callNum == null) ? 0 : callNum.hashCode());
		return result;
	}

	
}
