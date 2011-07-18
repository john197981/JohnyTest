
package com.oifm.survey;

import java.io.Serializable;

public class OIBVQuestion implements Serializable {

	private String strSurveyId;
	private OIBAQuestion[] objOIBAQuestion; 
	
	public OIBVQuestion() {	}
	
	public OIBAQuestion[] getObjOIBAQuestion() {
		return objOIBAQuestion;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public void setObjOIBAQuestion(OIBAQuestion[] objOIBAQuestion) {
		this.objOIBAQuestion = objOIBAQuestion;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
}
