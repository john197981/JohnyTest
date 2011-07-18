package com.oifm.survey;

import java.io.Serializable;

public class OIBASurveyDivision implements Serializable  {

	
	private String strDivisionCode;
	private String strDivisionName;
	
	
	public OIBASurveyDivision() {	}
	
    
    
    
	
	public String getStrDivisionCode() {
		return strDivisionCode;
	}
	public String getStrDivisionName() {
		return strDivisionName;
	}
	public void setStrDivisionName(String strDivisionName) {
		this.strDivisionName = strDivisionName;
	}
	public void setStrDivisionCode(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
	}
	
	
		
}
