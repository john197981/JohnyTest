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

import java.util.Date;

public class OIBVConsultListing 
{
    private String categoryName;
    private int categoryId;
    private int paperId;
    private String paperTitle;
    private String description;
    private Date fromDt;
    private Date toDt;
    private String mailStatus;
    private String security;
    private String division;
    private String targetAudiance;
    
    private String divisionCode;
    public String publishedOn;
    private String noFeedBacks;
    
    
    public String getNoFeedBacks(){
    	return noFeedBacks;
    }
    
    public void setNoFeedBacks(String noFeedBacks){
    	this.noFeedBacks = noFeedBacks;
    }
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
    //added by edmund
    public String getDivisionCode(){
    	return divisionCode;
    }
    
    public void setDivisionCode(String divisionCode){
    	this.divisionCode = divisionCode;
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
     * @return Returns the categoryId.
     */
    public int getCategoryId() {
        return categoryId;
    }
    /**
     * @param categoryId The categoryId to set.
     */
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }
    /**
     * @return Returns the categoryName.
     */
    public String getCategoryName() {
        return categoryName;
    }
    /**
     * @param categoryName The categoryName to set.
     */
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
    public Date getFromDt() {
        return fromDt;
    }
    /**
     * @param fromDt The fromDt to set.
     */
    public void setFromDt(Date fromDt) {
        this.fromDt = fromDt;
    }
    /**
     * @return Returns the targetAudiance.
     */
    public String getTargetAudiance() {
        return targetAudiance;
    }
    /**
     * @param fromDt The fromDt to set.
     */
    public void setTargetAudiance(String targetAudiance) {
        this.targetAudiance = targetAudiance;
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
    public int getPaperId() {
        return paperId;
    }
    /**
     * @param paperId The paperId to set.
     */
    public void setPaperId(int paperId) {
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
    public Date getToDt() {
        return toDt;
    }
    /**
     * @param toDt The toDt to set.
     */
    public void setToDt(Date toDt) {
        this.toDt = toDt;
    }
    
    
}
