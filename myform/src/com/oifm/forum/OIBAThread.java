
package com.oifm.forum;

import java.io.Serializable;

public class OIBAThread implements Serializable,Comparable {

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
	private String strModeratedOn;
	private String strLastpostId;
	private String strLastpostOn;
	private String strSecurity;
	private String strReplies;
	private String strHits;
	private String strLocked;
	private String strCreatedBy;
	private String strCreatedOn;
	private String strPrevThread;
	private String strNextThread;
	private String strHQReply;
	private String[] aryUserGroups;
	private String strTopicDesc;
	private String strBookMarkId="";
	private String strSplThreadID="";
	
	/**
	 * @return Returns the strBookMarkId.
	 */
	public String getStrBookMarkId() {
		return strBookMarkId;
	}
	/**
	 * @param strBookMarkId The strBookMarkId to set.
	 */
	public void setStrBookMarkId(String strBookMarkId) {
		this.strBookMarkId = strBookMarkId;
	}
	/** Thread List **/
	private String strModName;
	private String strPostedName;
	private String strPostedBy;
	private String strHidSortKey;
	private String strHidOrder;
	private String strTopicName;
	private String strHiddenAction;
	private String strRowId;
	private int nRowCount;
	private String strImgMsg;
	private String strImgThread;
	private int totalPosts=0;
		
		
	public OIBAThread() {	}
	
	/**
	 * @return Returns the strImgMsg.
	 */
	public String getStrImgMsg() {
		return strImgMsg;
	}
	/**
	 * @param strImgMsg The strImgMsg to set.
	 */
	public void setStrImgMsg(String strImgMsg) {
		this.strImgMsg = strImgMsg;
	}
	/**
	 * @return Returns the strImgThread.
	 */
	public String getStrImgThread() {
		return strImgThread;
	}
	/**
	 * @param strImgThread The strImgThread to set.
	 */
	public void setStrImgThread(String strImgThread) {
		this.strImgThread = strImgThread;
	}
		
	/**
	 * @return Returns the strRowCount.
	 */
	public int getRowCount() {
		return nRowCount;
	}
	/**
	 * @param strRowCount The strRowCount to set.
	 */
	public void setRowCount(int nRowCount) {
		this.nRowCount = nRowCount;
	}
		
	/**
	 * @return Returns the setRowId.
	 */
	public String getRowId() {
		return strRowId;
	}
	/**
	 * @param setRowId The setRowId to set.
	 */
	public void setRowId(String setRowId) {
		this.strRowId = setRowId;
	}
	
	
	/**
	 * @return Returns the strHiddenAction.
	 */
	public String getStrHiddenAction() {
		return strHiddenAction;
	}
	/**
	 * @param strHiddenAction The strHiddenAction to set.
	 */
	public void setStrHiddenAction(String strHiddenAction) {
		this.strHiddenAction = strHiddenAction;
	}
	
	
	/**
	 * @return Returns the modName.
	 */
	public String getStrModName() {
		return strModName;
	}
	/**
	 * @param modName The modName to set.
	 */
	public void setStrModName(String modName) {
		strModName = modName;
	}
	/**
	 * @return Returns the postedBy.
	 */
	public String getStrPostedBy() {
		return strPostedBy;
	}
	/**
	 * @param postedBy The postedBy to set.
	 */
	public void setStrPostedBy(String postedBy) {
		strPostedBy = postedBy;
	}
	/**
	 * @return Returns the postedName.
	 */
	public String getStrPostedName() {
		return strPostedName;
	}
	/**
	 * @param postedName The postedName to set.
	 */
	public void setStrPostedName(String postedName) {
		strPostedName = postedName;
	}
	

	public int getPageNo() {
		return (pageNo < 1)?1:pageNo;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	public String[] getAryUserGroups() {
		return aryUserGroups;
	}
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	public String getStrCreatedOn() {
		return strCreatedOn;
	}
	public String getStrHits() {
		return strHits;
	}
	public String getStrLastpostId() {
		return strLastpostId;
	}
	public String getStrLastpostOn() {
		return strLastpostOn;
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
		return (strModerationReq == null)? ((strTopicModerationReq)?"Y":"N"):strModerationReq;
	}
	public String getStrModerationStat() {
		return (strModerationStat == null)? ((getStrModerationReq().equals("Y"))?"N":"Y"):strModerationStat;
	}
	public String getStrReplies() {
		return strReplies;
	}
	public String getStrSecurity() {
		return (strSecurity != null && strSecurity.equals("Y"))?"Y":"N";
	}
	public String getStrThreadId() {
		return strThreadId;
	}
	public String getStrTitle() {
		return strTitle;
	}
	public String getStrPosting() {
		return strPosting;
	}	
	public String getStrTopicId() {
		return strTopicId;
	}
	public String getStrPostId() {
		return strPostId;
	}
	public String getStrNextThread() {
		return strNextThread;
	}
	public String getStrPrevThread() {
		return strPrevThread;
	}
	public String getStrHQReply() {
		return strHQReply;
	}
	public void setStrHQReply(String strHQReply) {
		this.strHQReply = strHQReply;
	}
	public void setStrNextThread(String strNextThread) {
		this.strNextThread = strNextThread;
	}
	public void setStrPrevThread(String strPrevThread) {
		this.strPrevThread = strPrevThread;
	}
	public void setStrPostId(String strPostId) {
		this.strPostId = strPostId;
	}
	public void setAryUserGroups(String[] aryUserGroups) {
		this.aryUserGroups = aryUserGroups;
	}
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}
	public void setStrHits(String strHits) {
		this.strHits = strHits;
	}
	public void setStrLastpostId(String strLastpostId) {
		this.strLastpostId = strLastpostId;
	}
	public void setStrLastpostOn(String strLastpostOn) {
		this.strLastpostOn = strLastpostOn;
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
	public void setStrPosting(String strPosting) {
		this.strPosting = strPosting;
	}
	public void setStrTopicId(String strTopicId) {
		this.strTopicId = strTopicId;
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
	public int compareTo(Object obj) {
		   // This automatically throws exception if wrong type
	  OIBAThread objExpVO = (OIBAThread)obj;
	  int returnValue = strImgMsg.compareTo(objExpVO.strImgMsg);
	  return returnValue;
	}
	/**
	 * @return Returns the totalPosts.
	 */
	public int getTotalPosts() {
		return totalPosts;
	}
	/**
	 * @param totalPosts The totalPosts to set.
	 */
	public void setTotalPosts(int totalPosts) {
		this.totalPosts = totalPosts;
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
	/**
	 * @return Returns the strSplThreadID.
	 */
	public String getStrSplThreadID() {
		return strSplThreadID;
	}
	/**
	 * @param strSplThreadID The strSplThreadID to set.
	 */
	public void setStrSplThreadID(String strSplThreadID) {
		this.strSplThreadID = strSplThreadID;
	}
}
