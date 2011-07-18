
package com.oifm.survey;

import java.util.ArrayList;

import org.apache.log4j.Logger;

public class OIFormQuestionResponse extends OIFormSurveySection {

	private transient static Logger logger = Logger.getLogger(OIFormQuestionResponse.class);

	private ArrayList responseList;
	private String strNextSectionId;
		
	public OIFormQuestionResponse()	{
		responseList = new ArrayList();
	}

	public ArrayList getResponseList() {
		return responseList;
	}
	public OIBAResponse getObjResponse(int ind) {
		if (responseList.size() <= ind) {
			for (int i = responseList.size(); i <= ind; i++) {
				  responseList.add(new OIBAResponse());
			}
		}
		return (OIBAResponse)(this.responseList.get(ind));
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
		if (responseList.size() <= ind) {
			for (int i = responseList.size(); i <= ind; i++) {
				  responseList.add(new OIBAResponse());
			}
		}
		responseList.set(ind, objResponse);
	}

}
