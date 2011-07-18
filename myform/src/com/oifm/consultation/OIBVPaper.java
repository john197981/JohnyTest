package com.oifm.consultation;

import java.util.Date;

public class OIBVPaper 
{
    private String categoryName;
    private int categoryId;
    private String paperName;
    private int paperId;
    private String description;
    private Date startDate;
    private String startStrDate;
    private Date expireDate;
    private String security;
    private String status;
    private String active;
    
    
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
     * @return Returns the expireDate.
     */
    public Date getExpireDate() {
        return expireDate;
    }
    /**
     * @param expireDate The expireDate to set.
     */
    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
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
     * @return Returns the paperName.
     */
    public String getPaperName() {
        return paperName;
    }
    /**
     * @param paperName The paperName to set.
     */
    public void setPaperName(String paperName) {
        this.paperName = paperName;
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
     * @return Returns the startDate.
     */
    public Date getStartDate() {
        return startDate;
    }
    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    /**
     * @return Returns the status.
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status The status to set.
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * @return Returns the startStrDate.
     */
    public String getStartStrDate() {
        return startStrDate;
    }
    /**
     * @param startStrDate The startStrDate to set.
     */
    public void setStartStrDate(String startStrDate) {
        this.startStrDate = startStrDate;
    }
}
