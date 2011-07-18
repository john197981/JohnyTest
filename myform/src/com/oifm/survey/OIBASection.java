
package com.oifm.survey;

import java.io.Serializable;

public class OIBASection implements Serializable {

	private String strSectionId;
	private String strSurveyId;
	private String strParentId;
	private String strSectionName;
	private String strInstruction;
	private String strDescription;
	private int level;
	private String strLevelLabel;
	private int noOfQuestions;
	private int noOfResponses;
	private String strPrevSecId;
	private String strNextSecId;
	private String strDivisionCode;

	public OIBASection() {	}
		
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
	public String getStrLevelLabel() {
		return strLevelLabel;
	}
	public int getLevel() {
		return level;
	}
	public int getNoOfQuestions() {
		return noOfQuestions;
	}	
	public int getNoOfResponses() {
		return noOfResponses;
	}
	public String getStrNextSecId() {
		return strNextSecId;
	}
	public String getStrPrevSecId() {
		return strPrevSecId;
	}
	public String getStrDivisionCode() {
		return strDivisionCode;
	}
	public void setStrDivisionCode(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
	}
	public void setStrNextSecId(String strNextSecId) {
		this.strNextSecId = strNextSecId;
	}
	public void setStrPrevSecId(String strPrevSecId) {
		this.strPrevSecId = strPrevSecId;
	}
	public void setNoOfResponses(int noOfResponses) {
		this.noOfResponses = noOfResponses;
	}
	public void setNoOfQuestions(int noOfQuestions) {
		this.noOfQuestions = noOfQuestions;
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
	public void setStrLevelLabel(String strLevelLabel) {
		this.strLevelLabel = strLevelLabel;
	}
	public void setLevel(int level) {
		this.level = level;
	}
}
