package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			05/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseActionForm;

public class OIFormConsultPaper extends OIBaseActionForm 
{
    private String categoryID;
    private ArrayList arCategoryID;
    private String fromDt;
    private String description;
    private String security;
    private String mailStatus;
    private String attachedFile;
    private String summary;
    private String title;
    private String targetAudiance;
	private String contactPerson;
	private String phone;
	private String emailAddress;
    private String createdOn;
    private String createdBy;
    private String toDt;
    private String paperId;
    private String active;
    private FormFile fileName;
    private String reminderMode;
    private ArrayList arOIBAConsultQuestion;
    private String readOnlyFlag;

	private String strInstruction;
	private String strCompletionTime;
	private String strFindingsPlannedDt;
	private String strViewFindingType;
	private String publishStatus;
	private String strOpenTag;
	private String strTagWords;
	private String strEmailDate;
	private String strEmailMessage;
	private String strTargetGpIds;
	
	private String strTagIdList;
	
	private String strDefaultEmailMessage;


    /**
     * @return Returns the readOnlyFlag.
     */
    public String getReadOnlyFlag() {
        return readOnlyFlag;
    }
    /**
     * @param readOnlyFlag The readOnlyFlag to set.
     */
    public void setReadOnlyFlag(String readOnlyFlag) {
        this.readOnlyFlag = readOnlyFlag;
    }
    public OIFormConsultPaper()
    {
        arCategoryID = new ArrayList();
        arOIBAConsultQuestion = new ArrayList();
    }
    /**
     * @return Returns the active.
     */
    public String getActive() {
        return active;
    }
    /**
     * @param active The active to set.
     */
    public void setActive(String active) {
        this.active = active;
    }
    /**
     * @return Returns the attachedFile.
     */
    public String getAttachedFile() {
        return attachedFile;
    }
    /**
     * @param attachedFile The attachedFile to set.
     */
    public void setAttachedFile(String attachedFile) {
        this.attachedFile = attachedFile;
    }
    /**
     * @return Returns the categoryID.
     */
    public String getCategoryID() {
        return categoryID;
    }
    /**
     * @param categoryID The categoryID to set.
     */
    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
    /**
     * @return Returns the createdBy.
     */
    public String getCreatedBy() {
        return createdBy;
    }
    /**
     * @param createdBy The createdBy to set.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    /**
     * @return Returns the createdOn.
     */
    public String getCreatedOn() {
        return createdOn;
    }
    /**
     * @param createdOn The createdOn to set.
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }
    /**
     * @return Returns the description.
     */
    public String getDescription() {
        return description;
    }
    /**
     * @param description The description to set.
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * @return Returns the fromDt.
     */
    public String getFromDt() {
        return fromDt;
    }
    /**
     * @param fromDt The fromDt to set.
     */
    public void setFromDt(String fromDt) {
        this.fromDt = fromDt;
    }
    /**
     * @return Returns the mailStatus.
     */
    public String getMailStatus() {
        return mailStatus;
    }
    /**
     * @param mailStatus The mailStatus to set.
     */
    public void setMailStatus(String mailStatus) {
        this.mailStatus = mailStatus;
    }
    /**
     * @return Returns the paperId.
     */
    public String getPaperId() {
        return paperId;
    }
    /**
     * @param paperId The paperId to set.
     */
    public void setPaperId(String paperId) {
        this.paperId = paperId;
    }
    /**
     * @return Returns the security.
     */
    public String getSecurity() {
        return security;
    }
    /**
     * @param security The security to set.
     */
    public void setSecurity(String security) {
        this.security = security;
    }
    /**
     * @return Returns the summary.
     */
    public String getSummary() {
        return summary;
    }
    /**
     * @param summary The summary to set.
     */
    public void setSummary(String summary) {
        this.summary = summary;
    }
    /**
     * @return Returns the targetAudiance.
     */
    public String getTargetAudiance() {
        return targetAudiance;
    }
    /**
     * @param targetAudiance The targetAudiance to set.
     */
    public void setTargetAudiance(String targetAudiance) {
        this.targetAudiance = targetAudiance;
    }
	/**
     * @return Returns the contactPerson.
     */
    public String getContactPerson() {
        return contactPerson;
    }
    /**
     * @param contactPerson The contactPerson to set.
     */
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }
	/**
     * @return Returns the phone.
     */
    public String getPhone() {
        return phone;
    }
    /**
     * @param phone The phone to set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
	/**
     * @return Returns the emailAddress.
     */
    public String getEmailAddress() {
        return emailAddress;
    }
    /**
     * @param emailAddress The emailAddress to set.
     */
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    /**
     * @return Returns the title.
     */
    public String getTitle() {
        return title;
    }
    /**
     * @param title The title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * @return Returns the toDt.
     */
    public String getToDt() {
        return toDt;
    }
    /**
     * @param toDt The toDt to set.
     */
    public void setToDt(String toDt) {
        this.toDt = toDt;
    }
    /**
     * @return Returns the arCategoryID.
     */
    public ArrayList getArCategoryID() {
        return arCategoryID;
    }
    /**
     * @param arCategoryID The arCategoryID to set.
     */
    public void setArCategoryID(ArrayList arCategoryID) {
        this.arCategoryID = arCategoryID;
    }

    public void addArCategoryID(LabelValueBean labelObj) 
    {
        arCategoryID.add(labelObj);
    }
    /**
     * @return Returns the fileName.
     */
    public FormFile getFileName() {
        return fileName;
    }
    /**
     * @param fileName The fileName to set.
     */
    public void setFileName(FormFile fileName) {
        this.fileName = fileName;
    }
    /**
     * @return Returns the reminderMode.
     */
    public String getReminderMode() {
        return reminderMode;
    }
    /**
     * @param reminderMode The reminderMode to set.
     */
    public void setReminderMode(String reminderMode) {
        this.reminderMode = reminderMode;
    }
    /**
     * @return Returns the arOIBAConsultQuestion.
     */
    public ArrayList getArOIBAConsultQuestion() {
        return arOIBAConsultQuestion;
    }
    /**
     * @param arOIBAConsultQuestion The arOIBAConsultQuestion to set.
     */
    public void setArOIBAConsultQuestion(ArrayList arOIBAConsultQuestion) {
        this.arOIBAConsultQuestion = arOIBAConsultQuestion;
    }

    public void addArOIBAConsultQuestion(Object obj) {
        this.arOIBAConsultQuestion.add(obj); //= arOIBAConsultQuestion;
    }

		 /**
     * @return Returns the strInstruction.
     */
    public String getStrInstruction() {
        return strInstruction;
    }
    /**
     * @param strInstruction The strInstruction to set.
     */
    public void setStrInstruction(String strInstruction) {
        this.strInstruction = strInstruction;
    }

	 /**
     * @return Returns the strCompletionTime.
     */
    public String getStrCompletionTime() {
        return strCompletionTime;
    }
    /**
     * @param strCompletionTime The strCompletionTime to set.
     */
    public void setStrCompletionTime(String strCompletionTime) {
        this.strCompletionTime = strCompletionTime;
    }

	 /**
     * @return Returns the strFindingsPlannedDt.
     */
    public String getStrFindingsPlannedDt() {
        return strFindingsPlannedDt;
    }
    /**
     * @param strFindingsPlannedDt The strFindingsPlannedDt to set.
     */
    public void setStrFindingsPlannedDt(String strFindingsPlannedDt) {
        this.strFindingsPlannedDt = strFindingsPlannedDt;
    }
	 /**
     * @return Returns the strViewFindingType.
     */
    public String getStrViewFindingType() {
        return strViewFindingType;
    }
    /**
     * @param strViewFindingType The strViewFindingType to set.
     */
    public void setStrViewFindingType(String strViewFindingType) {
        this.strViewFindingType = strViewFindingType;
    }

	 /**
     * @return Returns the publishStatus.
     */
    public String getPublishStatus() {
        return publishStatus;
    }
    /**
     * @param publishStatus The publishStatus to set.
     */
    public void setPublishStatus(String publishStatus) {
        this.publishStatus = publishStatus;
    }

	 /**
     * @return Returns the strOpenTag.
     */
    public String getStrOpenTag() {
        return strOpenTag;
    }
    /**
     * @param strOpenTag The strOpenTag to set.
     */
    public void setStrOpenTag(String strOpenTag) {
        this.strOpenTag = strOpenTag;
    }
	 /**
     * @return Returns the strTagWords.
     */
    public String getStrTagWords() {
        return strTagWords;
    }
    /**
     * @param strTagWords The strTagWords to set.
     */
    public void setStrTagWords(String strTagWords) {
        this.strTagWords = strTagWords;
    }

	 /**
     * @return Returns the strEmailDate.
     */
    public String getStrEmailDate() {
        return strEmailDate;
    }
    /**
     * @param strEmailDate The strEmailDate to set.
     */
    public void setStrEmailDate(String strEmailDate) {
        this.strEmailDate = strEmailDate;
    }

	 /**
     * @return Returns the strEmailMessage.
     */
    public String getStrEmailMessage() {
        return strEmailMessage;
    }
    /**
     * @param strEmailMessage The strEmailMessage to set.
     */
    public void setStrEmailMessage(String strEmailMessage) {
        this.strEmailMessage = strEmailMessage;
    }

	 /**
     * @return Returns the strTargetGpIds.
     */
    public String getStrTargetGpIds() {
        return strTargetGpIds;
    }
    /**
     * @param strTargetGpIds The strTargetGpIds to set.
     */
    public void setStrTargetGpIds(String strTargetGpIds) {
        this.strTargetGpIds = strTargetGpIds;
    }
    
    public String getStrTagIdList(){
		return strTagIdList;
	}
	
	public void setStrTagIdList(String strTagIdList){
		this.strTagIdList = strTagIdList;
	}
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
}
