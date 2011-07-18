
package com.oifm.survey;

import org.apache.struts.upload.FormFile;

import com.oifm.base.OIBaseActionForm;


public class OIFormSurvey extends OIBaseActionForm 
{
	private String strSurveyId;
	private String strSurveyName;
	private String strFromDate;
	private String strToDate;
	private String strDescription;
	private String strInstruction;
	private String strSurveyType;
	private String strReminderMode;
	private String strSummary;
	private FormFile attachedFile;
	private String strAttachedFile;
	private String strPublishedStatus;
	private String strMailStatus;
	private String strIsActive;
	private String strCreatedBy;
	private String strDivisionCode;
	private String strAcknowledgeMode;
	private int pageNo;
	private String strDivisionName;
	private String strTargetAudience;
	private String strTargetGpIds;
	private String strContactPerson;
	private String strPhone;
	private String strEmailAddress;
	private String strUserId;

	private String strCompletionTime;
	private String strFindingsPlannedDt;
	private String strViewFindingType;
	private String strOpenTag;
	private String strTagWords;
	private String strEmailDate;
	private String strEmailMessage;
	// Added by Oscar
	private String strDefaultEmailMessage;
	
	//added by edmund
	private String strAge;
	private String strLevel;
	private String strYear;
	private String strDesignation;
	private String strTagIdList;
	
	//added by edmund
	private String hidOrder;
	private String hidSortKey;
	
	// Added by K.K.Kumaresan on Jan 16,2008
	private String outsideMOEChecked;
	private String externalMailAddress;

	public OIFormSurvey() {
		super();
	}
	
    public String getStrTargetAudience() {
        return strTargetAudience;
    }
    public void setStrTargetAudience(String strTargetAudience) {
        this.strTargetAudience = strTargetAudience;
    }
	public String getStrContactPerson() {
        return strContactPerson;
    }
    public void setStrContactPerson(String strContactPerson) {
        this.strContactPerson = strContactPerson;
    }
	public String getStrPhone() {
        return strPhone;
    }
    public void setStrPhone(String strPhone) {
        this.strPhone = strPhone;
    }
	public String getStrEmailAddress() {
        return strEmailAddress;
    }
    public void setStrEmailAddress(String strEmailAddress) {
        this.strEmailAddress = strEmailAddress;
    }
	public String getStrAttachedFile() {
		return (attachedFile != null)?attachedFile.getFileName():strAttachedFile;
	}
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	public String getStrDescription() {
		return strDescription;
	}
	public String getStrFromDate() {
		return strFromDate;
	}
	public String getStrInstruction() {
		return strInstruction;
	}
	public String getStrIsActive() {
		return (strIsActive != null && strIsActive.equalsIgnoreCase("Y"))?"Y":"N";
	}
	public String getStrMailStatus() {
		return strMailStatus;
	}
	public String getStrPublishedStatus() {
		return strPublishedStatus;
	}
	public String getStrReminderMode() {
		return strReminderMode;
	}
	public String getStrSummary() {
		return strSummary;
	}
	public String getStrSurveyId() {
		return strSurveyId;
	}
	public String getStrSurveyName() {
		return strSurveyName;
	}
	public String getStrSurveyType() {
		return strSurveyType;
	}
	public String getStrToDate() {
		return strToDate;
	}
	public FormFile getAttachedFile() {
		return attachedFile;
	}
	public int getPageNo() {
		return pageNo;
	}
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
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getStrAcknowledgeMode() {
		return strAcknowledgeMode;
	}
	public void setStrAcknowledgeMode(String strAcknowledgeMode) {
		this.strAcknowledgeMode = strAcknowledgeMode;
	}
	public void setAttachedFile(FormFile attachedFile) {
		this.attachedFile = attachedFile;
	}
	public void setStrAttachedFile(String strAttachedFile) {
		this.strAttachedFile = strAttachedFile;
	}
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	public void setStrFromDate(String strFromDate) {
		this.strFromDate = strFromDate;
	}
	public void setStrInstruction(String strInstruction) {
		this.strInstruction = strInstruction;
	}
	public void setStrIsActive(String strIsActive) {
		this.strIsActive = strIsActive;
	}
	public void setStrMailStatus(String strMailStatus) {
		this.strMailStatus = strMailStatus;
	}
	public void setStrPublishedStatus(String strPublishedStatus) {
		this.strPublishedStatus = strPublishedStatus;
	}
	public void setStrReminderMode(String strReminderMode) {
		this.strReminderMode = strReminderMode;
	}
	public void setStrSummary(String strSummary) {
		this.strSummary = strSummary;
	}
	public void setStrSurveyId(String strSurveyId) {
		this.strSurveyId = strSurveyId;
	}
	public void setStrSurveyName(String strSurveyName) {
		this.strSurveyName = strSurveyName;
	}
	public void setStrSurveyType(String strSurveyType) {
		this.strSurveyType = strSurveyType;
	}
	public void setStrToDate(String strToDate) {
		this.strToDate = strToDate;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
		//added new for SR starts
	public String getStrCompletionTime() {
		return strCompletionTime;
	}
	public void setStrCompletionTime(String strCompletionTime) {
		this.strCompletionTime = strCompletionTime;
	}

	public String getStrFindingsPlannedDt() {
		return strFindingsPlannedDt;
	}
	public void setStrFindingsPlannedDt(String strFindingsPlannedDt) {
		this.strFindingsPlannedDt = strFindingsPlannedDt;
	}
	public String getStrViewFindingType() {
		return strViewFindingType;
	}
	public void setStrViewFindingType(String strViewFindingType) {
		this.strViewFindingType = strViewFindingType;
	}
	public String getStrOpenTag() {
		return strOpenTag;
	}
	public void setStrOpenTag(String strOpenTag) {
		this.strOpenTag = strOpenTag;
	}
	public String getStrTagWords() {
		return strTagWords;
	}
	public void setStrTagWords(String strTagWords) {
		this.strTagWords = strTagWords;
	}
	public String getStrEmailDate() {
		return strEmailDate;
	}
	public void setStrEmailDate(String strEmailDate) {
		this.strEmailDate = strEmailDate;
	}
	
	public String getStrEmailMessage() {
		return strEmailMessage;
	}
	public void setStrEmailMessage(String strEmailMessage) {
		this.strEmailMessage = strEmailMessage;
	}

	public String getStrTargetGpIds() {
        return strTargetGpIds;
    }
    public void setStrTargetGpIds(String strTargetGpIds) {
        this.strTargetGpIds = strTargetGpIds;
    }

	//added new for SR ends
	
	//added by edmund
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
	
	/**
	 * @return Returns the hidSortKey.
	 */
	public String getHidSortKey() {
		return hidSortKey;
	}
	/**
	 * @param hidSortKey The hidSortKey to set.
	 */
	public void setHidSortKey(String hidSortKey) {
		this.hidSortKey = hidSortKey;
	}
    
	/**
	 * @return Returns the strHidOrder.
	 */
	public String getHidOrder() {
		return hidOrder;
	}
	/**
	 * @param strHidOrder The strHidOrder to set.
	 */
	public void setHidOrder(String hidOrder) {
		this.hidOrder = hidOrder;
	}
	
	public String getStrTagIdList(){
		return strTagIdList;
	}
	
	public void setStrTagIdList(String strTagIdList){
		this.strTagIdList = strTagIdList;
	}
	//end

	/**
	 * @return the strDefaultEmailMessage
	 */
	public String getStrDefaultEmailMessage()
	{
		return strDefaultEmailMessage;
	}

	/**
	 * @param strDefaultEmailMessage the strDefaultEmailMessage to set
	 */
	public void setStrDefaultEmailMessage(String strDefaultEmailMessage)
	{
		this.strDefaultEmailMessage = strDefaultEmailMessage;
	}

	public String getExternalMailAddress() {
		return externalMailAddress;
	}

	public void setExternalMailAddress(String externalMailAddress) {
		this.externalMailAddress = externalMailAddress;
	}

	public String getOutsideMOEChecked() {
		return outsideMOEChecked;
	}

	public void setOutsideMOEChecked(String outsideMOEChecked) {
		this.outsideMOEChecked = outsideMOEChecked;
	}
	
	
}
