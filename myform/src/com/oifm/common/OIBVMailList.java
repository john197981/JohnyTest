/*************************************************************************************************
 * File Name		:	OIBVMailList.java
 * Author			:	SureshKumar.R
 * Description		:   This BV is used to store user details		
 * Created Date		:	Jul 10 2005
 * Version			:	1.0
 * Copyright : Scandent Group
 *************************************************************************************************/

package com.oifm.common;

public class OIBVMailList {
    private String strResponse;
    private String strEmail;
    private String strSurveys;
    private String strUserId;
    private String strPapers;
    private String strName;
    private String strSubmitted;
    
   
	/**
	 * @return Returns the eMail.
	 */
	public String getEMail() {
		return strEmail;
	}
	/**
	 * @param mail The strEmail to set.
	 */
	public void setstrEmail(String mail) {
		strEmail = mail;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return strName;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.strName = name;
	}
	/**
	 * @return Returns the papers.
	 */
	public String getPapers() {
		return strPapers;
	}
	/**
	 * @param papers The papers to set.
	 */
	public void setPapers(String papers) {
		this.strPapers = papers;
	}
	/**
	 * @return Returns the response.
	 */
	public String getResponse() {
		return strResponse;
	}
	/**
	 * @param response The response to set.
	 */
	public void setResponse(String response) {
		this.strResponse = response;
	}
	/**
	 * @return Returns the surveys.
	 */
	public String getSurveys() {
		return strSurveys;
		
	}
	/**
	 * @param surveys The surveys to set.
	 */
	public void setSurveys(String surveys) {
		this.strSurveys = surveys;
	}
	/**
	 * @return Returns the userId.
	 */
	public String getUserId() {
		return strUserId;
	}
	/**
	 * @param userId The userId to set.
	 */
	public void setUserId(String userId) {
		this.strUserId = userId;
	}
	/**
	 * @return Returns the strSubmitted.
	 */
	public String getSubmitted() {
		return strSubmitted;
	}
	/**
	 * @param strSubmitted The strSubmitted to set.
	 */
	public void setSubmitted(String strSubmitted) {
		this.strSubmitted = strSubmitted;
	}
	/**
	 * @return Returns the strHidSortKey.
	 */
	
}
