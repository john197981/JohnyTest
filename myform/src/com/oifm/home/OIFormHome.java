/*****************************************************************************
* Title 		: OIFormHome.java
* Description 	: This Form stores the home page in information      
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 01 - Aug -2005
* Copyright 	: Scandent Group
********************************************************************************/


package com.oifm.home;

import com.oifm.base.OIBaseActionForm;


public class OIFormHome  extends OIBaseActionForm {
	
	
	private String strPollId;
	private String strPubId;
	private String strShowRes;
	private String strRes1;
	private String strRes2;
	private String strRes3;
	private String strRes4;
	private String strRes5;
	private String strResponse;  
	private String strMultiple; 
	private String strPollTitle;
	
	private String strPublished;
	private String tags;
	
	
	/**
	 * @return the tags
	 */
	public String getTags() {
		return tags;
	}
	/**
	 * @param tags the tags to set
	 */
	public void setTags(String tags) {
		this.tags = tags;
	}
	/**
	 * @return Returns the strPublished.
	 */
	public String getPublished() {
		return strPublished ;
	}
	/**
	 * @param strPublished The strPublished to set.
	 */
	public void setPublished(String strPublished) {
		this.strPublished = strPublished;
	}
	/**
	 * @return Returns the strPubId.
	 */
	public String getPubId() {
		return strPubId;
	}
	/**
	 * @param strPubId The strPubId to set.
	 */
	public void setPubId(String strPubId) {
		this.strPubId = strPubId;
	}
	/**
	 * @return Returns the strMultiple.
	 */
	public String getMultiple() {
		return strMultiple;
	}
	/**
	 * @param strMultiple The strMultiple to set.
	 */
	public void setMultiple(String strMultiple) {
		this.strMultiple = strMultiple;
	}
	/**
	 * @return Returns the strRes1.
	 */
	public String getRes1() {
		return strRes1;
	}
	/**
	 * @param strRes1 The strRes1 to set.
	 */
	public void setRes1(String strRes1) {
		this.strRes1 = strRes1;
	}
	/**
	 * @return Returns the strRes2.
	 */
	public String getRes2() {
		return strRes2;
	}
	/**
	 * @param strRes2 The strRes2 to set.
	 */
	public void setRes2(String strRes2) {
		this.strRes2 = strRes2;
	}
	/**
	 * @return Returns the strRes3.
	 */
	public String getRes3() {
		return strRes3;
	}
	/**
	 * @param strRes3 The strRes3 to set.
	 */
	public void setRes3(String strRes3) {
		this.strRes3 = strRes3;
	}
	/**
	 * @return Returns the strRes4.
	 */
	public String getRes4() {
		return strRes4;
	}
	/**
	 * @param strRes4 The strRes4 to set.
	 */
	public void setRes4(String strRes4) {
		this.strRes4 = strRes4;
	}
	/**
	 * @return Returns the strRes5.
	 */
	public String getRes5() {
		return strRes5;
	}
	/**
	 * @param strRes5 The strRes5 to set.
	 */
	public void setRes5(String strRes5) {
		this.strRes5 = strRes5;
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
		strResponse = response;
	}
	/**
	 * @return Returns the strPollId.
	 */
	public String getPollId() {
		return strPollId;
	}
	/**
	 * @param strPollId The strPollId to set.
	 */
	public void setPollId(String strPollId) {
		this.strPollId = strPollId;
	}
	
	/**
	 * @return Returns the strShowRes.
	 */
	public String getShowRes() {
		return strShowRes;
	}
	/**
	 * @param strShowRes The strShowRes to set.
	 */
	public void setShowRes(String strShowRes) {
		this.strShowRes = strShowRes;
	}
	/**
	 * @return Returns the strPollTitle.
	 */
	public String getPubTitle() {
		return strPollTitle;
	}
	/**
	 * @param strPollTitle The strPollTitle to set.
	 */
	public void setPubTitle(String strPollTitle) {
		this.strPollTitle = strPollTitle;
	}
}
