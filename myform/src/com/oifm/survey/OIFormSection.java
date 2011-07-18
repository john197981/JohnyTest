
package com.oifm.survey;

import com.oifm.base.OIBaseActionForm;


public class OIFormSection extends OIBaseActionForm {

	private String strSectionId;
	private String strSurveyId;
	private String strParentId;
	private String strSectionName;
	private String strInstruction;
	private String strDescription;
	private String strDivisionCode;

	public OIFormSection() {
		super();
	}
	
	public String getStrDescription() {
		return strDescription;
	}
	public String getStrInstruction() {
		return strInstruction;
	}
	public String getStrParentId() {
		return strParentId;
	}
	public String getStrSectionId() {
		return strSectionId;
	}
	public String getStrSectionName() {
		return strSectionName;
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
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	public void setStrInstruction(String strInstruction) {
		this.strInstruction = strInstruction;
	}
	public void setStrParentId(String strParentId) {
		this.strParentId = strParentId;
	}
	public void setStrSectionId(String strSectionId) {
		this.strSectionId = strSectionId;
	}
	public void setStrSectionName(String strSectionName) {
		this.strSectionName = strSectionName;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
}
