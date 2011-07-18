package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			30/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

public class OIFormA2ConsultListing 
{
    private String paperId;
    private String paperTitle;
    private String description;
    private String fromDt;
    private String toDt;
    private String mailStatus;
    private String noOfFeedbacks;
	private String targetAudiance;
    private String security;
    private String division;

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
	
public String publishedOn;
	
	/**
	 * @return Returns the hidSortKey.
	 */
	public String getPublishedOn() {
		return publishedOn;
	}
	/**
	 * @param hidSortKey The hidSortKey to set.
	 */
	public void setPublishedOn(String publishedOn) {
		this.publishedOn = publishedOn;
	}


    /**
     * @return Returns the division.
     */
    public String getDivision() {
        return division;
    }
    /**
     * @param division The division to set.
     */
    public void setDivision(String division) {
        this.division = division;
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
     * @return Returns the noOfFeedbacks.
     */
    public String getNoOfFeedbacks() {
        return noOfFeedbacks;
    }
    /**
     * @param noOfFeedbacks The noOfFeedbacks to set.
     */
    public void setNoOfFeedbacks(String noOfFeedbacks) {
        this.noOfFeedbacks = noOfFeedbacks;
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
     * @return Returns the paperTitle.
     */
    public String getPaperTitle() {
        return paperTitle;
    }
    /**
     * @param paperTitle The paperTitle to set.
     */
    public void setPaperTitle(String paperTitle) {
        this.paperTitle = paperTitle;
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
}
