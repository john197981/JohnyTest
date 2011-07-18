package com.oifm.consultation;

import java.util.Date;

public class OIBADraft 
{
    private int draftId;
    private int cpsuId;
    private String draftType;
    private String userId;
    private Date draftedOn;
    private String status;
    
    /**
     * @return Returns the cpsuId.
     */
    public int getCpsuId() {
        return cpsuId;
    }
    /**
     * @param cpsuId The cpsuId to set.
     */
    public void setCpsuId(int cpsuId) {
        this.cpsuId = cpsuId;
    }
    /**
     * @return Returns the draftedOn.
     */
    public Date getDraftedOn() {
        return draftedOn;
    }
    /**
     * @param draftedOn The draftedOn to set.
     */
    public void setDraftedOn(Date draftedOn) {
        this.draftedOn = draftedOn;
    }
    /**
     * @return Returns the draftId.
     */
    public int getDraftId() {
        return draftId;
    }
    /**
     * @param draftId The draftId to set.
     */
    public void setDraftId(int draftId) {
        this.draftId = draftId;
    }
    /**
     * @return Returns the draftType.
     */
    public String getDraftType() {
        return draftType;
    }
    /**
     * @param draftType The draftType to set.
     */
    public void setDraftType(String draftType) {
        this.draftType = draftType;
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
