
package com.oifm.survey;

import com.oifm.base.OIBaseActionForm;

public class OIFormSurveySection extends OIBaseActionForm {

	private String strSectionId;
	private String strSurveyId;
	private String strUserId;
	private String strUserType;

	public OIFormSurveySection() {
		super();
	}

	public String getStrSectionId() {
		return strSectionId;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public String getStrUserType() {
		return strUserType;
	}
	public void setStrSectionId(String strSectionId) {
		this.strSectionId = strSectionId;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	public void setStrUserType(String strUserType) {
		this.strUserType = strUserType;
	}
}
