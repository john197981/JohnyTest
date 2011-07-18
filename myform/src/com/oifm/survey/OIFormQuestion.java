
package com.oifm.survey;

import com.oifm.base.OIBaseActionForm;


public class OIFormQuestion extends OIBaseActionForm {

	private String strSurveyId;
	private String strSectionId;
	private String strQuestionId;
	private String strQuestion;
	private String strInstruction;
	private String strQuestionType;
	private String strAnswer1;
	private String strAnswer2;
	private String strAnswer3;
	private String strAnswer4;
	private String strAnswer5;
	private String strAnswer6;
	private String strAnswer7;
	private String strAnswer8;
	private String strAnswer9;
	private String strAnswer10;
	private String strOtherRemarks;
	private String strOptional;
	private String strMakeTemplate;
	private String strDivisionCode;
	private String strUseSameLikert ;	
	private String canMoveUp;
	private String canMoveDown;

	public OIFormQuestion() {
		super();
	}
	
	public String getStrAnswer1() {
		return strAnswer1;
	}
	public String getStrAnswer10() {
		return strAnswer10;
	}
	public String getStrAnswer2() {
		return strAnswer2;
	}
	public String getStrAnswer3() {
		return strAnswer3;
	}
	public String getStrAnswer4() {
		return strAnswer4;
	}
	public String getStrAnswer5() {
		return strAnswer5;
	}
	public String getStrAnswer6() {
		return strAnswer6;
	}
	public String getStrAnswer7() {
		return strAnswer7;
	}
	public String getStrAnswer8() {
		return strAnswer8;
	}
	public String getStrAnswer9() {
		return strAnswer9;
	}
	public String getStrInstruction() {
		return strInstruction;
	}
	public String getStrMakeTemplate() {
		return strMakeTemplate;
	}
	public String getStrDivisionCode() {
		return strDivisionCode;
	}
	public String getStrUseSameLikert() {
		return strUseSameLikert;
	}
	public void setStrUseSameLikert(String strUseSameLikert) {
		this.strUseSameLikert = strUseSameLikert;
	}
	public void setStrDivisionCode(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
	}
	public void setStrMakeTemplate(String strMakeTemplate) {
		this.strMakeTemplate = strMakeTemplate;
	}
	public String getStrOptional() {
		return (strOptional != null && strOptional.equalsIgnoreCase("O"))?"O":"M";
	}
	public String getStrOtherRemarks() {
		return (strOtherRemarks != null && strOtherRemarks.equalsIgnoreCase("Y"))?"Y":"N";
	}
	public String getStrQuestion() {
		return strQuestion;
	}
	public String getStrQuestionId() {
		return strQuestionId;
	}
	public String getStrQuestionType() {
		return strQuestionType;
	}
	public String getStrSectionId() {
		return strSectionId;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public void setStrAnswer1(String strAnswer1) {
		this.strAnswer1 = strAnswer1;
	}
	public void setStrAnswer10(String strAnswer10) {
		this.strAnswer10 = strAnswer10;
	}
	public void setStrAnswer2(String strAnswer2) {
		this.strAnswer2 = strAnswer2;
	}
	public void setStrAnswer3(String strAnswer3) {
		this.strAnswer3 = strAnswer3;
	}
	public void setStrAnswer4(String strAnswer4) {
		this.strAnswer4 = strAnswer4;
	}
	public void setStrAnswer5(String strAnswer5) {
		this.strAnswer5 = strAnswer5;
	}
	public void setStrAnswer6(String strAnswer6) {
		this.strAnswer6 = strAnswer6;
	}
	public void setStrAnswer7(String strAnswer7) {
		this.strAnswer7 = strAnswer7;
	}
	public void setStrAnswer8(String strAnswer8) {
		this.strAnswer8 = strAnswer8;
	}
	public void setStrAnswer9(String strAnswer9) {
		this.strAnswer9 = strAnswer9;
	}
	public void setStrInstruction(String strInstruction) {
		this.strInstruction = strInstruction;
	}
	public void setStrOptional(String strOptional) {
		this.strOptional = strOptional;
	}
	public void setStrOtherRemarks(String strOtherRemarks) {
		this.strOtherRemarks = strOtherRemarks;
	}
	public void setStrQuestion(String strQuestion) {
		this.strQuestion = strQuestion;
	}
	public void setStrQuestionId(String strQuestionId) {
		this.strQuestionId = strQuestionId;
	}
	public void setStrQuestionType(String strQuestionType) {
		this.strQuestionType = strQuestionType;
	}
	public void setStrSectionId(String strSectionId) {
		this.strSectionId = strSectionId;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}

	public String getCanMoveUp() {
		return canMoveUp;
	}
	public void setCanMoveUp(String canMoveUp) {
		this.canMoveUp = canMoveUp;
	}

	public String getCanMoveDown() {
		return canMoveDown;
	}
	public void setCanMoveDown(String canMoveDown) {
		this.canMoveDown = canMoveDown;
	}
	
}
