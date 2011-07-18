package com.oifm.consultation;

import java.util.Date;

public class OIBAResponse 
{
    private int responseId;
    private int questionId;
    private int questionNo;
    private String response1;
    private String remarks;
    private String otherRemarks;
    private String userId;
    private Date responseOn;
    private String response2;
    private String response3;
	/**
	 * @return Returns the otherRemarks.
	 */
	public String getOtherRemarks() {
		return otherRemarks;
	}
	/**
	 * @param otherRemarks The otherRemarks to set.
	 */
	public void setOtherRemarks(String otherRemarks) {
		this.otherRemarks = otherRemarks;
	}
    private String response4;
    private String response5;
    
    
    /**
     * @return Returns the questionId.
     */
    public int getQuestionId() {
        return questionId;
    }
    /**
     * @param questionId The questionId to set.
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    /**
     * @return Returns the remarks.
     */
    public String getRemarks() {
        return remarks;
    }
    /**
     * @param remarks The remarks to set.
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    /**
     * @return Returns the response1.
     */
    public String getResponse1() {
        return response1;
    }
    /**
     * @param response1 The response1 to set.
     */
    public void setResponse1(String response1) {
        this.response1 = response1;
    }
    /**
     * @return Returns the response2.
     */
    public String getResponse2() {
        return response2;
    }
    /**
     * @param response2 The response2 to set.
     */
    public void setResponse2(String response2) {
        this.response2 = response2;
    }
    /**
     * @return Returns the response3.
     */
    public String getResponse3() {
        return response3;
    }
    /**
     * @param response3 The response3 to set.
     */
    public void setResponse3(String response3) {
        this.response3 = response3;
    }
    /**
     * @return Returns the response4.
     */
    public String getResponse4() {
        return response4;
    }
    /**
     * @param response4 The response4 to set.
     */
    public void setResponse4(String response4) {
        this.response4 = response4;
    }
    /**
     * @return Returns the response5.
     */
    public String getResponse5() {
        return response5;
    }
    /**
     * @param response5 The response5 to set.
     */
    public void setResponse5(String response5) {
        this.response5 = response5;
    }
    /**
     * @return Returns the responseId.
     */
    public int getResponseId() {
        return responseId;
    }
    /**
     * @param responseId The responseId to set.
     */
    public void setResponseId(int responseId) {
        this.responseId = responseId;
    }
    /**
     * @return Returns the responseOn.
     */
    public Date getResponseOn() {
        return responseOn;
    }
    /**
     * @param responseOn The responseOn to set.
     */
    public void setResponseOn(Date responseOn) {
        this.responseOn = responseOn;
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
	public int getQuestionNo() {
		return questionNo;
	}
	public void setQuestionNo(int questionNo) {
		this.questionNo = questionNo;
	}
}
