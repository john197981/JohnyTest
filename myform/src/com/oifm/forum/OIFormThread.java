
package com.oifm.forum;

import java.util.ArrayList;

import com.oifm.base.OIBaseActionForm;


public class OIFormThread extends OIBaseActionForm {

	private String strUserId;
	private int pageNo;
	private String strPostId;
	private String strThreadId;
	private String strTopicId;
	private String strTitle;
	private String strPosting; 
	private boolean strTopicModerationReq;
	private String strModerationReq;
	private String strModerationStat;
	private String strModeratedBy;
	private String strHQReply;
	private String strModeratedOn;
	private String strLastpostId;
	private String strSecurity;
	private String strReplies;
	private String strHits;
	private String strLocked;
	private String strCreatedBy;
	private String[] aryUserGroups;
	private ArrayList alFunctions;
	
	private String strHidOrder;
	private String strHidSortKey;
	private String strTopicName;
	private String strTopicDesc;
	
	
	public OIFormThread() {
		super();
	}
	public String[] getAryUserGroups() {
		return aryUserGroups;
	}
	public int getPageNo() {
		return (pageNo < 1)?1:pageNo;
	}
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	public String getStrHits() {
		return strHits;
	}
	public String getStrLastpostId() {
		return strLastpostId;
	}
	public String getStrLocked() {
		return strLocked;
	}
	public String getStrModeratedBy() {
		return strModeratedBy;
	}
	public String getStrModeratedOn() {
		return strModeratedOn;
	}
	public boolean getStrTopicModerationReq() {
		return strTopicModerationReq;
	}
	public String getStrModerationReq() {
		return strModerationReq;
	}
	public String getStrModerationStat() {
		return strModerationStat;
	}
	public String getStrReplies() {
		return strReplies;
	}
	public String getStrSecurity() {
		return (strSecurity != null)?strSecurity:"N";
	}
	public String getStrThreadId() {
		return strThreadId;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public String getStrTopicId() {
		return strTopicId;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public String getStrPostId() {
		return strPostId;
	}
	public String getStrPosting() {
		return strPosting;
	}
	public String getStrHQReply() {
		return strHQReply;
	}
	public void setStrHQReply(String strHQReply) {
		this.strHQReply = strHQReply;
	}
	public void setStrPosting(String strPosting) {
		this.strPosting = strPosting;
	}
	public void setStrPostId(String strPostId) {
		this.strPostId = strPostId;
	}
	public void setAryUserGroups(String[] aryUserGroups) {
		this.aryUserGroups = aryUserGroups;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	public void setStrHits(String strHits) {
		this.strHits = strHits;
	}
	public void setStrLastpostId(String strLastpostId) {
		this.strLastpostId = strLastpostId;
	}
	public void setStrLocked(String strLocked) {
		this.strLocked = strLocked;
	}
	public void setStrModeratedBy(String strModeratedBy) {
		this.strModeratedBy = strModeratedBy;
	}
	public void setStrModeratedOn(String strModeratedOn) {
		this.strModeratedOn = strModeratedOn;
	}
	public void setStrTopicModerationReq(boolean strTopicModerationReq) {
		this.strTopicModerationReq = strTopicModerationReq;
	}
	public void setStrModerationReq(String strModerationReq) {
		this.strModerationReq = strModerationReq;
	}
	public void setStrModerationStat(String strModerationStat) {
		this.strModerationStat = strModerationStat;
	}
	public void setStrReplies(String strReplies) {
		this.strReplies = strReplies;
	}
	public void setStrSecurity(String strSecurity) {
		this.strSecurity = strSecurity;
	}
	public void setStrThreadId(String strThreadId) {
		this.strThreadId = strThreadId;
	}
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	public void setStrTopicId(String strTopicId) {
		this.strTopicId = strTopicId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	
	/**
	 * @return Returns the hidSortKey.
	 */
	public String getHidSortKey() {
		return strHidSortKey;
	}
	/**
	 * @param hidSortKey The hidSortKey to set.
	 */
	public void setHidSortKey(String hidSortKey) {
		this.strHidSortKey = hidSortKey;
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
	 * @return Returns the strTopicName.
	 */
	public String getStrTopicName() {
		return strTopicName;
	}
	/**
	 * @param strTopicName The strTopicName to set.
	 */
	public void setStrTopicName(String strTopicName) {
		this.strTopicName = strTopicName;
	}
	
	public void setFunctions(ArrayList alFunctions) {
		this.alFunctions = alFunctions; 		
	} 
	/**
	 * @return Returns the strTopicDesc.
	 */
	public String getStrTopicDesc() {
		return strTopicDesc;
	}
	/**
	 * @param strTopicDesc The strTopicDesc to set.
	 */
	public void setStrTopicDesc(String strTopicDesc) {
		this.strTopicDesc = strTopicDesc;
	}
}

