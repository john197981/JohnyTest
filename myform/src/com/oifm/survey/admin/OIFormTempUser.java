
package com.oifm.survey.admin;

import com.oifm.base.OIBaseActionForm;

public class OIFormTempUser extends OIBaseActionForm {

	private String strSurveyId;
	private String strObsolete;
	private String strBatchNo;
	private int noOfUsers;
	private String strDivisionCode;

	public OIFormTempUser() {	}
		

	public int getNoOfUsers() {
		return noOfUsers;
	}
	public String getStrBatchNo() {
		return strBatchNo;
	}
	public String getStrObsolete() {
		return strObsolete;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public String getStrDivisionCode() {
		return strDivisionCode;
	}
	public void setStrDivisionCode(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
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
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
}
