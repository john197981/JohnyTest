
package com.oifm.survey.admin;

import java.io.Serializable;

public class OIBATempUser implements Serializable {

	private String strTempUserId;
	private String strSurveyId;
	private String strPassword;
	private int noOfTries;
	private String strObsolete;
	private String strBatchNo;
	private int noOfUsers;
	private String strDivisionCode;

	public OIBATempUser() {	}
		
	public int getNoOfTries() {
		return noOfTries;
	}
	public int getNoOfUsers() {
		return noOfUsers;
	}
	public String getStrBatchNo() {
		return strBatchNo;
	}
	public String getStrObsolete() {
		return strObsolete;
	}
	public String getStrPassword() {
		return strPassword;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public String getStrTempUserId() {
		return strTempUserId;
	}
	public String getStrDivisionCode() {
		return strDivisionCode;
	}
	public void setStrDivisionCode(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
	}
	public void setNoOfTries(int noOfTries) {
		this.noOfTries = noOfTries;
	}
	public void setNoOfUsers(int noOfUsers) {
		this.noOfUsers = noOfUsers;
	}
	public void setStrBatchNo(String strBatchNo) {
		this.strBatchNo = strBatchNo;
	}
	public void setStrObsolete(String strObsolete) {
		this.strObsolete = strObsolete;
	}
	public void setStrPassword(String strPassword) {
		this.strPassword = strPassword;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
	public void setStrTempUserId(String strTempUserId) {
		this.strTempUserId = strTempUserId;
	}

}
