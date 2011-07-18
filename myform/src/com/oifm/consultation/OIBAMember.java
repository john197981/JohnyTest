package com.oifm.consultation;

import java.util.Date;

public class OIBAMember 
{
    private int memberId;
    private String submitted;
    private int paperId;
    private String userId;
    private Date initiationDt;
    private Date submittedOn;
    
    /**
     * @return Returns the initiationDt.
     */
    public Date getInitiationDt() {
        return initiationDt;
    }
    /**
     * @param initiationDt The initiationDt to set.
     */
    public void setInitiationDt(Date initiationDt) {
        this.initiationDt = initiationDt;
    }
    /**
     * @return Returns the memberId.
     */
    public int getMemberId() {
        return memberId;
    }
    /**
     * @param memberId The memberId to set.
     */
    public void setMemberId(int memberId) {
        this.memberId = memberId;
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
     * @return Returns the submitted.
     */
    public String isSubmitted() {
        return submitted;
    }
    /**
     * @param submitted The submitted to set.
     */
    public void setSubmitted(String submitted) {
        this.submitted = submitted;
    }
    /**
     * @return Returns the submittedOn.
     */
    public Date getSubmittedOn() {
        return submittedOn;
    }
    /**
     * @param submittedOn The submittedOn to set.
     */
    public void setSubmittedOn(Date submittedOn) {
        this.submittedOn = submittedOn;
    }
    /**
     * @return Returns the userId.
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId The userId to set.
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
