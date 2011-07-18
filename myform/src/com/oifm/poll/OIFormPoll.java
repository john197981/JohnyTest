/*************************************************************************************
* Title 		: OIFormPoll.java
* Description 	: This is the Form object to populate the view details.  				    
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 26 - Jul -2005
* Copyright 	: Scandent Group
***************************************************************************************/

package com.oifm.poll;

import com.oifm.base.OIBaseActionForm;


public class OIFormPoll extends OIBaseActionForm{
	
	String strPollId;
	String strTitle;
	String strQuestion;
	String strStartDt;
	String strExpDt;
	String strAnswer1;
	String strAnswer2;
	String strAnswer3;
	String strAnswer4;
	String strAnswer5;
	String strMutAns;
	String strShowRes;
	String strPublished;
	String strPubTitle;
	String strPubId;
	
	String strResId;
	String strRes1;
	String strRes2;
	String strRes3;
	String strRes4;
	String strRes5;
	String strTotal;
	String strAvg;
	String strWidth;
	
	String strImgSize;
	String strImgPer;
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
	 * @return Returns the strPubTitle.
	 */
	public String getPubTitle() {
		return strPubTitle;
	}
	/**
	 * @param strPubTitle The strPubTitle to set.
	 */
	public void setPubTitle(String strPubTitle) {
		this.strPubTitle = strPubTitle;
	}
	/**
	 * @return Returns the strTotal.
	 */
	public String getTotal() {
		return strTotal;
	}
	/**
	 * @param strTotal The strTotal to set.
	 */
	public void setTotal(String strTotal) {
		this.strTotal = strTotal;
	}
	/**
	 * @return Returns the strImgPer.
	 */
	public String getImgPer() {
		return strImgPer;
	}
	/**
	 * @param strImgPer The strImgPer to set.
	 */
	public void setImgPer(String strImgPer) {
		this.strImgPer = strImgPer;
	}
	/**
	 * @return Returns the strAvg.
	 */
	public String getAvg() {
		return strAvg;
	}
	/**
	 * @param strAvg The strAvg to set.
	 */
	public void setAvg(String strAvg) {
		this.strAvg = strAvg;
	}
	/**
	 * @return Returns the strImgSize.
	 */
	public String getImgSize() {
		return strImgSize;
	}
	/**
	 * @param strImgSize The strImgSize to set.
	 */
	public void setImgSize(String strImgSize) {
		this.strImgSize = strImgSize;
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
	 * @return Returns the strResId.
	 */
	public String getResId() {
		return strResId;
	}
	/**
	 * @param strResId The strResId to set.
	 */
	public void setResId(String strResId) {
		this.strResId = strResId;
	}
	/**
	 * @return Returns the strAnswer1.
	 */
	public String getAnswer1() {
		return strAnswer1;
	}
	/**
	 * @param strAnswer1 The strAnswer1 to set.
	 */
	public void setAnswer1(String strAnswer1) {
		this.strAnswer1 = strAnswer1;
	}
	/**
	 * @return Returns the strAnswer2.
	 */
	public String getAnswer2() {
		return strAnswer2;
	}
	/**
	 * @param strAnswer2 The strAnswer2 to set.
	 */
	public void setAnswer2(String strAnswer2) {
		this.strAnswer2 = strAnswer2;
	}
	/**
	 * @return Returns the strAnswer3.
	 */
	public String getAnswer3() {
		return strAnswer3;
	}
	/**
	 * @param strAnswer3 The strAnswer3 to set.
	 */
	public void setAnswer3(String strAnswer3) {
		this.strAnswer3 = strAnswer3;
	}
	/**
	 * @return Returns the strAnswer4.
	 */
	public String getAnswer4() {
		return strAnswer4;
	}
	/**
	 * @param strAnswer4 The strAnswer4 to set.
	 */
	public void setAnswer4(String strAnswer4) {
		this.strAnswer4 = strAnswer4;
	}
	/**
	 * @return Returns the strAnswer5.
	 */
	public String getAnswer5() {
		return strAnswer5;
	}
	/**
	 * @param strAnswer5 The strAnswer5 to set.
	 */
	public void setAnswer5(String strAnswer5) {
		this.strAnswer5 = strAnswer5;
	}
	/**
	 * @return Returns the strExpDt.
	 */
	public String getExpDt() {
		return strExpDt;
	}
	/**
	 * @param strExpDt The strExpDt to set.
	 */
	public void setExpDt(String strExpDt) {
		this.strExpDt = strExpDt;
	}
	/**
	 * @return Returns the strMutAns.
	 */
	public String getMutAns() {
		return strMutAns;
	}
	/**
	 * @param strMutAns The strMutAns to set.
	 */
	public void setMutAns(String strMutAns) {
		this.strMutAns = strMutAns;
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
	 * @return Returns the strPublished.
	 */
	public String getPublished() {
		return strPublished;
	}
	/**
	 * @param strPublished The strPublished to set.
	 */
	public void setPublished(String strPublished) {
		this.strPublished = strPublished;
	}
	/**
	 * @return Returns the strQuestion.
	 */
	public String getQuestion() {
		return strQuestion;
	}
	/**
	 * @param strQuestion The strQuestion to set.
	 */
	public void setQuestion(String strQuestion) {
		this.strQuestion = strQuestion;
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
	 * @return Returns the strStartDt.
	 */
	public String getStartDt() {
		return strStartDt;
	}
	/**
	 * @param strStartDt The strStartDt to set.
	 */
	public void setStartDt(String strStartDt) {
		this.strStartDt = strStartDt;
	}
	/**
	 * @return Returns the strTitle.
	 */
	public String getTitle() {
		return strTitle;
	}
	/**
	 * @param strTitle The strTitle to set.
	 */
	public void setTitle(String strTitle) {
		this.strTitle = strTitle;
	}
}