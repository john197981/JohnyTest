package com.oifm.survey;

import com.oifm.base.OIBaseActionForm;

public class OIFormResult extends OIBaseActionForm {

	public OIFormResult() {
		super();
	}
	
	private String strSurveyId;
	private String strSectionId;
	private String strResultType;
	private String strDivisionCode;
	private String strSurveyName;
	private String hiddenAction;
	
	private String strAge;
	private String strLevel;
	private String strYear;
	private String strDesignation;
	private String strPopulate;
	
	public String getStrSectionId() {
		return strSectionId;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public String getStrResultType(){
		return strResultType;
	}
	public String getStrDivisionCode() {
		return strDivisionCode;
	}
	public String getStrSurveyName() {
		return strSurveyName;
	}
	public String getStrAge(){
		return strAge;
	}
	public String getStrLevel(){
		return strLevel;
	}
	public String getStrYear(){
		return strYear;
	}
	public String getStrDesignation(){
		return strDesignation;
	}
	public String getStrPopulate(){
		return strPopulate;
	}
	public String getHiddenAction(){
		return hiddenAction;
	}
	
	public void setStrSectionId(String strSectionId) {
		this.strSectionId = strSectionId;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
	public void setStrResultType(String strResultType){
		this.strResultType = strResultType;
	}
	public void setStrDivisionCode(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
	}
	public void setStrSurveyName(String strSurveyName){
		this.strSurveyName = strSurveyName;
	}
	public void setStrAge(String strAge){
		this.strAge = strAge;
	}
	public void setStrLevel(String strLevel){
		this.strLevel = strLevel;
	}
	public void setStrYear(String strYear){
		this.strYear = strYear;
	}
	public void setStrDesignation(String strDesignation){
		this.strDesignation = strDesignation;
	}
	public void setStrPopulate(String strPopulate){
		this.strPopulate = strPopulate;
	}

	public void setHiddenAction(String hiddenAction){
		this.hiddenAction = hiddenAction;
	}
}
