package com.oifm.consultation;

import java.util.Date;

public class OIBAFeedBack 
{
    private String feedBack1;
    private Date feedBackOn;
    private String userId;
    private String feedBack2;
    private int paperId;
    private int feedBackId;
    
    /**
     * @return Returns the feedBack1.
     */
    public String getFeedBack1() {
        return feedBack1;
    }
    /**
     * @param feedBack1 The feedBack1 to set.
     */
    public void setFeedBack1(String feedBack1) {
        this.feedBack1 = feedBack1;
    }
    /**
     * @return Returns the feedBack2.
     */
    public String getFeedBack2() {
        return feedBack2;
    }
    /**
     * @param feedBack2 The feedBack2 to set.
     */
    public void setFeedBack2(String feedBack2) {
        this.feedBack2 = feedBack2;
    }
    /**
     * @return Returns the feedBackId.
     */
    public int getFeedBackId() {
        return feedBackId;
    }
    /**
     * @param feedBackId The feedBackId to set.
     */
    public void setFeedBackId(int feedBackId) {
        this.feedBackId = feedBackId;
    }
    /**
     * @return Returns the feedBackOn.
     */
    public Date getFeedBackOn() {
        return feedBackOn;
    }
    /**
     * @param feedBackOn The feedBackOn to set.
     */
    public void setFeedBackOn(Date feedBackOn) {
        this.feedBackOn = feedBackOn;
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
