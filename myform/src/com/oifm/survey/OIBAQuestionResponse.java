
package com.oifm.survey;

import java.util.ArrayList;

import org.apache.log4j.Logger;


public class OIBAQuestionResponse extends OIBASurveySection  {

	private transient static Logger logger = Logger.getLogger(OIBAQuestionResponse.class);
	private ArrayList responseList;
	private String strNextSectionId;
		
	public OIBAQuestionResponse() {
		super();
	}

	public ArrayList getResponseList() {
		return responseList;
	}
	public OIBAResponse getObjResponse(int ind) {
		return (OIBAResponse)this.responseList.get(ind);
	}
	public String getStrNextSectionId() {
		return this.strNextSectionId;
	}
	public void setStrNextSectionId(String strNextSectionId) {
		this.strNextSectionId = strNextSectionId;
	}
	public void setResponseList(ArrayList responseList) {
		this.responseList = responseList;
	}	 
	public void setObjResponse(int ind, OIBAResponse objResponse) {
		responseList.set(ind, objResponse);
	}

}
