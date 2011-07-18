
package com.oifm.survey;

import java.io.Serializable;

import com.oifm.utility.OIUtilities;

public class OIBAResponse implements Serializable {

	private String strResponseId;
	private String strQuestionId;
	private String strQstType;	
	private String strUserId;
	private String strResponse1;
	private String strResponse2;
	private String strResponse3;
	private String strResponse4;
	private String strResponse5;
	private String strResponse6;
	private String strResponse7;
	private String strResponse8;
	private String strResponse9;
	private String strResponse10;
	private String strOtherRemarks;
	private String strResponseOn;
	private String strRbResponse;

	public OIBAResponse() {	}
		
	public String getStrOtherRemarks() {
		return strOtherRemarks;
	}
	public String getStrQuestionId() {
		return strQuestionId;
	}
	public String getStrQstType() {
		return strQstType;
	}
	public String getStrResponse1() {
		return strResponse1;
	}
	public String getStrResponse10() {
		return strResponse10;
	}
	public String getStrResponse2() {
		return strResponse2;
	}
	public String getStrResponse3() {
		return strResponse3;
	}
	public String getStrResponse4() {
		return strResponse4;
	}
	public String getStrResponse5() {
		return strResponse5;
	}
	public String getStrResponse6() {
		return strResponse6;
	}
	public String getStrResponse7() {
		return strResponse7;
	}
	public String getStrResponse8() {
		return strResponse8;
	}
	public String getStrResponse9() {
		return strResponse9;
	}
	public String getStrResponseId() {
		return strResponseId;
	}
	public String getStrResponseOn() {
		return strResponseOn;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public String getStrRbResponse() {
		return strRbResponse;
	}
	public void setStrRbResponse(String strRbResponse) {
		this.strRbResponse = strRbResponse;
	}
	public void setStrOtherRemarks(String strOtherRemarks) {
		this.strOtherRemarks = strOtherRemarks;
	}
	public void setStrQuestionId(String strQuestionId) {
		this.strQuestionId = strQuestionId;
	}
	public void setStrQstType(String strQstType) {
		this.strQstType = strQstType;
	}
	public void setStrResponse1(String strResponse1) {
		this.strResponse1 = strResponse1;
	}
	public void setStrResponse10(String strResponse10) {
		this.strResponse10 = strResponse10;
	}
	public void setStrResponse2(String strResponse2) {
		this.strResponse2 = strResponse2;
	}
	public void setStrResponse3(String strResponse3) {
		this.strResponse3 = strResponse3;
	}
	public void setStrResponse4(String strResponse4) {
		this.strResponse4 = strResponse4;
	}
	public void setStrResponse5(String strResponse5) {
		this.strResponse5 = strResponse5;
	}
	public void setStrResponse6(String strResponse6) {
		this.strResponse6 = strResponse6;
	}
	public void setStrResponse7(String strResponse7) {
		this.strResponse7 = strResponse7;
	}
	public void setStrResponse8(String strResponse8) {
		this.strResponse8 = strResponse8;
	}
	public void setStrResponse9(String strResponse9) {
		this.strResponse9 = strResponse9;
	}
	public void setStrResponseId(String strResponseId) {
		this.strResponseId = strResponseId;
	}
	public void setStrResponseOn(String strResponseOn) {
		this.strResponseOn = strResponseOn;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}

	public void setRbValueFormToSystem() {
		if(strQstType != null &&
		   (strQstType.equals(OISurveyConstants.SINGLE_CHOICE) ||
			strQstType.equals(OISurveyConstants.LIKERT_SCALE))) {
			if(OIUtilities.isSameValue(this.strRbResponse, "R1"))
				this.strResponse1 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R2"))
				this.strResponse2 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R3"))
				this.strResponse3 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R4"))
				this.strResponse4 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R5"))
				this.strResponse5 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R6"))
				this.strResponse6 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R7"))
				this.strResponse7 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R8"))
				this.strResponse8 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R9"))
				this.strResponse9 = "Y";
			else if(OIUtilities.isSameValue(this.strRbResponse, "R10"))
				this.strResponse10 = "Y";
		}
	}

	public void setRbValueSystemToForm() {
		if(strQstType != null &&
		   (strQstType.equals(OISurveyConstants.SINGLE_CHOICE)) ||
		   (strQstType.equals(OISurveyConstants.LIKERT_SCALE))) {
			if(OIUtilities.isSameValue(this.strResponse1, "Y"))
				this.strRbResponse = "R1";
			else if(OIUtilities.isSameValue(this.strResponse2, "Y"))
				this.strRbResponse = "R2";
			else if(OIUtilities.isSameValue(this.strResponse3, "Y"))
				this.strRbResponse = "R3";
			else if(OIUtilities.isSameValue(this.strResponse4, "Y"))
				this.strRbResponse = "R4";
			else if(OIUtilities.isSameValue(this.strResponse5, "Y"))
				this.strRbResponse = "R5";
			else if(OIUtilities.isSameValue(this.strResponse6, "Y"))
				this.strRbResponse = "R6";
			else if(OIUtilities.isSameValue(this.strResponse7, "Y"))
				this.strRbResponse = "R7";
			else if(OIUtilities.isSameValue(this.strResponse8, "Y"))
				this.strRbResponse = "R8";
			else if(OIUtilities.isSameValue(this.strResponse9, "Y"))
				this.strRbResponse = "R9";
			else if(OIUtilities.isSameValue(this.strResponse10, "Y"))
				this.strRbResponse = "R10";
		}
	}
	
	public boolean isEmptyResponse() {
		boolean isEmpty = (!OIUtilities.checkBlank(this.strResponse1) && 
				!OIUtilities.checkBlank(this.strResponse2) &&
				!OIUtilities.checkBlank(this.strResponse3) &&
				!OIUtilities.checkBlank(this.strResponse4) &&
				!OIUtilities.checkBlank(this.strResponse5) &&
				!OIUtilities.checkBlank(this.strResponse6) &&
				!OIUtilities.checkBlank(this.strResponse7) &&
				!OIUtilities.checkBlank(this.strResponse8) &&
				!OIUtilities.checkBlank(this.strResponse9) &&
				!OIUtilities.checkBlank(this.strResponse10) &&
				!OIUtilities.checkBlank(this.strOtherRemarks));
		return isEmpty;
	}

}
