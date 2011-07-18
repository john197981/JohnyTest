/*************************************************************************************************
 * File Name		:	OIBVSendMail.java
 * Author			:	SureshKumar.R
 * Description		:   This BV is used to Information of Send Mail		
 * Created Date		:	Jul 10 2005
 * Version			:	1.0
 * Copyright 		: Scandent Group
 ************************************************************************************************/ 
package com.oifm.common;

import java.io.Serializable;



public class OIBVSendMail  implements Serializable
{
    
	
    private String id;
    private String SurveyOrCons;
    private String SendOrRemain;   
	private String hiddenAction;
	private String strSubject;
	private String strMessage;
	private String strEmail;
	private String[] strRemoveId; 
	private String strHidOrder; 
	private String strHidSortKey;
	private String strCaption;
	private String strPrincipal;
	
	private String strName;
	private String strDescription;
	private String strStartDate;
	private String strEndDate;
	private String strURL;
	private String strPostedBy;
	private String strUserId;
	private String strDelCnt;
	private String strFrom;
	
	private String nickname;
	/**
	 * @return Returns the strFrom.
	 */
	public String getFrom() {
		return strFrom;
	}
	/**
	 * @param strFrom The strFrom to set.
	 */
	public void setFrom(String strFrom) {
		this.strFrom = strFrom;
	}
	/**
	 * @return Returns the strDelCnt.
	 */
	public String getDelCnt() {
		return strDelCnt;
	}
	/**
	 * @param strDelCnt The strDelCnt to set.
	 */
	public void setDelCnt(String strDelCnt) {
		this.strDelCnt = strDelCnt;
	}
	/**
	 * @return Returns the strUserId.
	 */
	public String getUserId() {
		return strUserId;
	}
	/**
	 * @param strUserId The strUserId to set. 
	 */
	public void setUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	/**
	 * @return Returns the strPostedBy.
	 */
	public String getPostedOn() {
		return strPostedBy;
	}
	/**
	 * @param strPostedBy The strPostedBy to set.
	 */
	public void setPostedOn(String strPostedBy) {
		this.strPostedBy = strPostedBy;
	}
	/**
	 * @return Returns the strURL.
	 */
	public String getURL() {
		return strURL;
	}
	/**
	 * @param strURL The strURL to set.
	 */
	public void setURL(String strURL) {
		this.strURL = strURL;
	}
	/**
	 * @return Returns the strDescription.
	 */
	public String getDescription() {
		return strDescription;
	}
	/**
	 * @param strDescription The strDescription to set.
	 */
	public void setDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	/**
	 * @return Returns the strEndDate.
	 */
	public String getEndDate() {
		return strEndDate;
	}
	/**
	 * @param strEndDate The strEndDate to set.
	 */
	public void setEndDate(String strEndDate) {
		this.strEndDate = strEndDate;
	}
	/**
	 * @return Returns the strName.
	 */
	public String getName() {
		return strName;
	}
	/**
	 * @param strName The strName to set.
	 */
	public void setName(String strName) {
		this.strName = strName;
	}
	/**
	 * @return Returns the strStartDate.
	 */
	public String getStartDate() {
		return strStartDate;
	}
	/**
	 * @param strStartDate The strStartDate to set.
	 */
	public void setStartDate(String strStartDate) {
		this.strStartDate = strStartDate;
	}

	/**
	 * @return Returns the strCaption.
	 */
	public String getCaption() {
		return strCaption;
	}
	/**
	 * @param strCaption The strCaption to set.
	 */
	public void setCaption(String strCaption) {
		this.strCaption = strCaption;
	}
	/**
	 * @return Returns the strEmail.
	 */
	public String getEmail() {
		return strEmail;
	}
	/**
	 * @param strEmail The strEmail to set.
	 */
	public void setEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	/**
	 * @return Returns the strMessage.
	 */
	public String getMessage() {
		return strMessage;
	}
	/**
	 * @param strMessage The strMessage to set.
	 */
	public void setMessage(String strMessage) {
		this.strMessage = strMessage;
	}
	/**
	 * @return Returns the strSubject.
	 */
	public String getSubject() {
		return strSubject;
	}
	/**
	 * @param strSubject The strSubject to set.
	 */
	public void setSubject(String strSubject) {
		this.strSubject = strSubject;
	}
	//This variable is used to store the order of the readUsers whether It is Ascending or Desending
	private String readUsrsOrder;
	
//	This is used to store the ID's of the selected check box for removal 
	private String strHidIdvalues;
	
	private int NIndex = 0;
     
    
	  
   
    /**
     * @return Returns the id.
     */
    public String getId() {
        return id;
    }
    /**
     * @param id The id to set.
     */
    public void setId(String id) {
        this.id = id;
    }
    /**
     * @return Returns the sendOrRemain.
     */
    public String getSendOrRemain() {
        return SendOrRemain;
    }
    /**
     * @param sendOrRemain The sendOrRemain to set.
     */
    public void setSendOrRemain(String sendOrRemain) {
        SendOrRemain = sendOrRemain;
    }
    /**
     * @return Returns the surveyOrCons.
     */
    public String getSurveyOrCons() {
        return SurveyOrCons;
    }
    /**
     * @param surveyOrCons The surveyOrCons to set.
     */
    public void setSurveyOrCons(String surveyOrCons) {
        SurveyOrCons = surveyOrCons;
    }
	

	
	/**
	 * @return
	 */
	public String getHiddenAction() {
		return hiddenAction;
	}

	/**
	 * @param string
	 */
	public void setHiddenAction(String string) {
		hiddenAction = string;
	}



	/**
	 * @return
	 */
	public int getNIndex() {
		return NIndex;
	}

	/**
	 * @param i
	 */
	public void setNIndex(int i) {
		NIndex = i;
	}

/**
 * @return
 */
public String getStrHidIdvalues() {
	return strHidIdvalues;
}

/**
 * @param string
 */
public void setStrHidIdvalues(String string) {
	strHidIdvalues = string;
}

	/**
	 * @return
	 */
	public String getReadUsrsOrder() {
		return readUsrsOrder;
	}

	/**
	 * @param string
	 */
	public void setReadUsrsOrder(String string) {
		readUsrsOrder = string;
	}

	/**
	 * @return Returns the strRemoveId.
	 */
	public String[] getRemoveId() {
		return strRemoveId;
	}
	/**
	 * @param strRemoveId The strRemoveId to set.
	 */
	public void setRemoveId(String[] strRemoveId) {
		this.strRemoveId = strRemoveId;
	}
	public String getHidSortKey() {
		return strHidSortKey;
	}
	/**
	 * @param strHidSortKey The strHidSortKey to set.
	 */
	public void setHidSortKey(String strHidSortKey) {
		this.strHidSortKey = strHidSortKey; 
	}
	/**
	 * @return Returns the strHidOrder.
	 */
	public String getHidOrder() {
		return strHidOrder;
	}
	/**
	 * @param strHidOrder The strHidOrder to set.
	 */
	public void setHidOrder(String strHidOrder) {
		this.strHidOrder = strHidOrder;
	}
	/**
	 * @return the strPrincipal
	 */
	public String getPrincipal()
	{
		return strPrincipal;
	}
	/**
	 * @param strPrincipal the strPrincipal to set
	 */
	public void setPrincipal(String strPrincipal)
	{
		this.strPrincipal = strPrincipal;
	}
	/**
	 * @return the nickname
	 */
	public String getNickname()
	{
		return nickname;
	}
	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname)
	{
		this.nickname = nickname;
	}
}
