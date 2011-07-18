package com.oifm.consultation.admin;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseActionForm;

public class OIFormConsultPublish extends OIBaseActionForm 
{
    private String paperId;
    private String title;
    private String targetAudiance;
	private String contactPerson;
	private String phone;
	private String emailAddress;
    private String fromDt;
    private String toDt;
    private String active;
    private String description;
    private String attachedFile;
    private String summaryFile;
    private String summary;
    private String publishStatus;
    private ArrayList arPublishStatus;
    private ArrayList arOIFormConsultPublishHelper;
    private FormFile summaryFileName;
    private ArrayList arOIBAConsultQuestion;
    private String readOnlyFlag;

	private String strInstruction;
	private String strCompletionTime;
	private String strFindingsPlannedDt;
	private String strViewFindingType;
	private String strOpenTag;
	private String strTagWords;
	private String strEmailDate;
	private String strEmailMessage;
	private String strTargetGpIds;
    
    public OIFormConsultPublish()
    {
        arPublishStatus = new ArrayList();
        arOIFormConsultPublishHelper = new ArrayList();
        arOIBAConsultQuestion = new ArrayList();
    }

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
        this.arOIBAConsultQuestion.add(obj);
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
     * @return Returns the arOIFormConsultPublishHelper.
     */
    public ArrayList getArOIFormConsultPublishHelper() {
        return arOIFormConsultPublishHelper;
    }
    /**
     * @param arOIFormConsultPublishHelper The arOIFormConsultPublishHelper to set.
     */
    public void setArOIFormConsultPublishHelper(
            ArrayList arOIFormConsultPublishHelper) {
        this.arOIFormConsultPublishHelper = arOIFormConsultPublishHelper;
    }
    
    public void addArOIFormConsultPublishHelper(Object obj)
    {
        this.arOIFormConsultPublishHelper.add(obj);
    }
    
    /**
     * @return Returns the arPublishStatus.
     */
    public ArrayList getArPublishStatus() {
        return arPublishStatus;
    }
    /**
     * @param arPublishStatus The arPublishStatus to set.
     */
    public void setArPublishStatus(ArrayList arPublishStatus) {
        this.arPublishStatus = arPublishStatus;
    }
    
    public void addArPublishStatus(LabelValueBean obj)
    {
        this.arPublishStatus.add(obj);
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
     * @return Returns the summaryFileName.
     */
    public FormFile getSummaryFileName() {
        return summaryFileName;
    }
    /**
     * @param summaryFileName The summaryFileName to set.
     */
    public void setSummaryFileName(FormFile summaryFileName) {
        this.summaryFileName = summaryFileName;
    }
    /**
     * @return Returns the summaryFile.
     */
    public String getSummaryFile() {
        return summaryFile;
    }
    /**
     * @param summaryFile The summaryFile to set.
     */
    public void setSummaryFile(String summaryFile) {
        this.summaryFile = summaryFile;
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
}
