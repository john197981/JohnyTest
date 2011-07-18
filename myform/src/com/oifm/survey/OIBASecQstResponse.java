
package com.oifm.survey;

import java.io.Serializable;

public class OIBASecQstResponse implements Serializable  {

	private String strSectionId;
	private String strSurveyId;
	private String strUserId;

	// set of questions & Responses will come as a common object
	
	public OIBASecQstResponse() {
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
	public void setStrSectionId(String strSectionId) {
		this.strSectionId = strSectionId;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

}
