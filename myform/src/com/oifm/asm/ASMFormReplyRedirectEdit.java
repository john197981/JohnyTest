/*********************************ASMFormReplyRedirectEdit.java******************* 
 * Title 		: ASMFormReplyRedirectEdit
 * Description 	: This class is the Form Class for ASM Reply Redirect Edit Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 20 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import com.oifm.base.OIBaseActionForm;

public class ASMFormReplyRedirectEdit extends OIBaseActionForm {
	private String strDivision =null;
	private String strTo =null;
	private String strCc =null;
	private String strBcc =null;
	private String strSubject =null;
	private String strMessage =null;
	private String strPostedBy =null;
	private String strPostedOn =null;
	private String strEmail =null;
	private String strContactNo =null;
	private String strTopic =null;
	private String strLetterID =null;
	private String strLetter =null;
	private String strReply =null;
	private String strRemainder =null;
	private String strPublishOn =null;
	private String strShowFrom =null;
	private String strShowTo =null;
	private String strStatus =null;
	private String strRepliedBy =null;
	private String strRepliedOn =null;
	private boolean bPublishWebSite =false;
	private String strUsrId = null;
	private String strUsrDesig = null;
	private String strUsrDivSch = null;
	private String strRedirectedOn = null;
	private String strCategory=null;
	private String fromCategory=null;
	private String categoryId=null;
	private String letterId=null;
	
	/**
	 * @return the strCategory
	 */
	public String getCboCategory()
	{
		return strCategory;
	}
	/**
	 * @param strCategory the strCategory to set
	 */
	public void setCboCategory(String strCategory)
	{
		this.strCategory = strCategory;
	}
	/**
	 * @return Returns the strBcc.
	 */
	public String getTxtBcc() {
		return strBcc;
	}
	/**
	 * @param strBcc The strBcc to set.
	 */
	public void setTxtBcc(String strBcc) {
		this.strBcc = strBcc;
	}
	/**
	 * @return Returns the strCc.
	 */
	public String getTxtCc() {
		return strCc;
	}
	/**
	 * @param strCc The strCc to set.
	 */
	public void setTxtCc(String strCc) {
		this.strCc = strCc;
	}
	/**
	 * @return Returns the strContactNo.
	 */
	public String getTxtContactNo() {
		return strContactNo;
	}
	/**
	 * @param strContactNo The strContactNo to set.
	 */
	public void setTxtContactNo(String strContactNo) {
		this.strContactNo = strContactNo;
	}
	/**
	 * @return Returns the strDivision.
	 */
	public String getCboDivision() {
		return strDivision;
	}
	/**
	 * @param strDivision The strDivision to set.
	 */
	public void setCboDivision(String strDivision) {
		this.strDivision = strDivision;
	}
	/**
	 * @return Returns the strEmail.
	 */
	public String getHidEmail() {
		return strEmail;
	}
	/**
	 * @param strEmail The strEmail to set.
	 */
	public void setHidEmail(String strEmail) {
		this.strEmail = strEmail;
	}
	/**
	 * @return Returns the strLetter.
	 */
	public String getTxtLetter() {
		return strLetter;
	}
	/**
	 * @param strLetter The strLetter to set.
	 */
	public void setTxtLetter(String strLetter) {
		this.strLetter = strLetter;
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
	 * @return Returns the strMessage.
	 */
	public String getTxtMessage() {
		return strMessage;
	}
	/**
	 * @param strMessage The strMessage to set.
	 */
	public void setTxtMessage(String strMessage) {
		this.strMessage = strMessage;
	}
	/**
	 * @return Returns the strPostedBy.
	 */
	public String getHidPostedBy() {
		return strPostedBy;
	}
	/**
	 * @param strPostedBy The strPostedBy to set.
	 */
	public void setHidPostedBy(String strPostedBy) {
		this.strPostedBy = strPostedBy;
	}
	/**
	 * @return Returns the strPostedOn.
	 */
	public String getHidPostedOn() {
		return strPostedOn;
	}
	/**
	 * @param strPostedOn The strPostedOn to set.
	 */
	public void setHidPostedOn(String strPostedOn) {
		this.strPostedOn = strPostedOn;
	}
	/**
	 * @return Returns the strPublishOn.
	 */
	public String getTxtPublishOn() {
		return strPublishOn;
	}
	/**
	 * @param strPublishOn The strPublishOn to set.
	 */
	public void setTxtPublishOn(String strPublishOn) {
		this.strPublishOn = strPublishOn;
	}
	/**
	 * @return Returns the bPublishWebSite.
	 */
	public boolean getChkPublishWebSite() {
		return bPublishWebSite;
	}
	/**
	 * @param bPublishWebSite The bPublishWebSite to set.
	 */
	public void setChkPublishWebSite(boolean bPublishWebSite) {
		this.bPublishWebSite = bPublishWebSite;
	}
	/**
	 * @return Returns the strRemainder.
	 */
	public String getRadRemainder() {
		return strRemainder;
	}
	/**
	 * @param strRemainder The strRemainder to set.
	 */
	public void setRadRemainder(String strRemainder) {
		this.strRemainder = strRemainder;
	}
	/**
	 * @return Returns the strReply.
	 */
	public String getTxtReply() {
		return strReply;
	}
	/**
	 * @param strReply The strReply to set.
	 */
	public void setTxtReply(String strReply) {
		this.strReply = strReply;
	}
	/**
	 * @return Returns the strShowFrom.
	 */
	public String getTxtShowFrom() {
		return strShowFrom;
	}
	/**
	 * @param strShowFrom The strShowFrom to set.
	 */
	public void setTxtShowFrom(String strShowFrom) {
		this.strShowFrom = strShowFrom;
	}
	/**
	 * @return Returns the strShowTo.
	 */
	public String getTxtShowTo() {
		return strShowTo;
	}
	/**
	 * @param strShowTo The strShowTo to set.
	 */
	public void setTxtShowTo(String strShowTo) {
		this.strShowTo = strShowTo;
	}
	/**
	 * @return Returns the strSubject.
	 */
	public String getTxtSubject() {
		return strSubject;
	}
	/**
	 * @param strSubject The strSubject to set.
	 */
	public void setTxtSubject(String strSubject) {
		this.strSubject = strSubject;
	}
	/**
	 * @return Returns the strTo.
	 */
	public String getTxtTo() {
		return strTo;
	}
	/**
	 * @param strTo The strTo to set.
	 */
	public void setTxtTo(String strTo) {
		this.strTo = strTo;
	}
	/**
	 * @return Returns the strTopic.
	 */
	public String getTxtTopic() {
		return strTopic;
	}
	/**
	 * @param strTopic The strTopic to set.
	 */
	public void setTxtTopic(String strTopic) {
		this.strTopic = strTopic;
	}
	
	/**
	 * @return Returns the strStatus.
	 */
	public String getHidStatus() {
		return strStatus;
	}
	/**
	 * @param strStatus The strStatus to set.
	 */
	public void setHidStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	/**
	 * @return Returns the strRepliedBy.
	 */
	public String getTxtRepliedBy() {
		return strRepliedBy;
	}
	/**
	 * @param strRepliedBy The strRepliedBy to set.
	 */
	public void setTxtRepliedBy(String strRepliedBy) {
		this.strRepliedBy = strRepliedBy;
	}
	/**
	 * @return Returns the strRepliedOn.
	 */
	public String getTxtRepliedOn() {
		return strRepliedOn;
	}
	/**
	 * @param strRepliedOn The strRepliedOn to set.
	 */
	public void setTxtRepliedOn(String strRepliedOn) {
		this.strRepliedOn = strRepliedOn;
	}
	/**
	 * @return Returns the strRedirectedOn.
	 */
	public String getStrRedirectedOn() {
		return strRedirectedOn;
	}
	/**
	 * @param strRedirectedOn The strRedirectedOn to set.
	 */
	public void setStrRedirectedOn(String strRedirectedOn) {
		this.strRedirectedOn = strRedirectedOn;
	}
	/**
	 * @return Returns the strUsrDesig.
	 */
	public String getStrUsrDesig() {
		return strUsrDesig;
	}
	/**
	 * @param strUsrDesig The strUsrDesig to set.
	 */
	public void setStrUsrDesig(String strUsrDesig) {
		this.strUsrDesig = strUsrDesig;
	}
	/**
	 * @return Returns the strUsrDivSch.
	 */
	public String getStrUsrDivSch() {
		return strUsrDivSch;
	}
	/**
	 * @param strUsrDivSch The strUsrDivSch to set.
	 */
	public void setStrUsrDivSch(String strUsrDivSch) {
		this.strUsrDivSch = strUsrDivSch;
	}
	/**
	 * @return Returns the strUsrId.
	 */
	public String getStrUsrId() {
		return strUsrId;
	}
	/**
	 * @param strUsrId The strUsrId to set.
	 */
	public void setStrUsrId(String strUsrId) {
		this.strUsrId = strUsrId;
	}
	public String getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
	public String getFromCategory() {
		return fromCategory;
	}
	public void setFromCategory(String fromCategory) {
		this.fromCategory = fromCategory;
	}
	public String getLetterId() {
		return letterId;
	}
	public void setLetterId(String letterId) {
		this.letterId = letterId;
	}
}

