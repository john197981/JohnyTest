/*********************************ASMBVHomePage.java.java******************* 
 * Title 		: ASMBVHomePage
 * Description 	: This class is the VO Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
import java.io.Serializable;
 

public class ASMBVHomePage implements Serializable  
{
	private String strAnnouncemnet = null;
	private String strLetterTopic = null;
	private String strLetterContent = null;
	private String strReplyContent = null;
	private String strPublishOn = null;
	private String strLetterID = null;
	private String strPageDesc = null;
	private String strHiddenAction = null;
	private String hidCreatedBy = null;
	private String hidRepliedBy = null;
	private String userId = null;
	
	/**
	 * @return Returns the strAnnouncemnet.
	 */
	public String getHidAnnouncemnet() {
		return strAnnouncemnet;
	}
	/**
	 * @param strAnnouncemnet The strAnnouncemnet to set.
	 */
	public void setHidAnnouncemnet(String strAnnouncemnet) {
		this.strAnnouncemnet = strAnnouncemnet;
	}
	/**
	 * @return Returns the strLetterContent.
	 */
	public String getHidLetterContent() {
		return strLetterContent;
	}
	/**
	 * @param strLetterContent The strLetterContent to set.
	 */
	public void setHidLetterContent(String strLetterContent) {
		this.strLetterContent = strLetterContent;
	}
	/**
	 * @return Returns the strLetterTopic.
	 */
	public String getHidLetterTopic() {
		return strLetterTopic;
	}
	/**
	 * @param strLetterTopic The strLetterTopic to set.
	 */
	public void setHidLetterTopic(String strLetterTopic) {
		this.strLetterTopic = strLetterTopic;
	}
	/**
	 * @return Returns the strReplyContent.
	 */
	public String getHidReplyContent() {
		return strReplyContent;
	}
	/**
	 * @param strReplyContent The strReplyContent to set.
	 */
	public void setHidReplyContent(String strReplyContent) {
		this.strReplyContent = strReplyContent;
	}
	/**
	 * @return Returns the strPublishOn.
	 */
	public String getHidPublishOn() {
		return strPublishOn;
	}
	/**
	 * @param strPublishOn The strPublishOn to set.
	 */
	public void setHidPublishOn(String strPublishOn) {
		this.strPublishOn = strPublishOn;
	}
	/**
	 * @return Returns the strLetterID.
	 */
	
	public String getHidLetterID() {
		return strLetterID;
	}
	/**
	 * @param strLetterID The strLetterID to set.
	 */
	public void setHidLetterID(String strLetterID) {
		this.strLetterID = strLetterID;
	}
	/**
	 * @return Returns the strPageDesc.
	 */
	public String getHidPageDesc() {
		return strPageDesc;
	}
	/**
	 * @param strPageDesc The strPageDesc to set.
	 */
	public void setHidPageDesc(String strPageDesc) {
		this.strPageDesc = strPageDesc;
	}
	

	/**
	 * @return Returns the strHiddenAction.
	 */
	public String getHiddenAction() {
		return strHiddenAction;
	}
	/**
	 * @param strHiddenAction The strHiddenAction to set.
	 */
	public void setHiddenAction(String strHiddenAction) {
		this.strHiddenAction = strHiddenAction;
	}
	/**
	 * @return Returns the hidCreatedBy.
	 */
	public String getHidCreatedBy() {
		return hidCreatedBy;
	}
	/**
	 * @param hidCreatedBy The hidCreatedBy to set.
	 */
	public void setHidCreatedBy(String hidCreatedBy) {
		this.hidCreatedBy = hidCreatedBy;
	}
	/**
	 * @return Returns the hidRepliedBy.
	 */
	public String getHidRepliedBy() {
		return hidRepliedBy;
	}
	/**
	 * @param hidRepliedBy The hidRepliedBy to set.
	 */
	public void setHidRepliedBy(String hidRepliedBy) {
		this.hidRepliedBy = hidRepliedBy;
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
