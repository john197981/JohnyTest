
package com.oifm.forum;

import java.io.Serializable;
import java.util.Date;

public class OIBAPosting implements Serializable  {

	private String strUserId;
	private int pageNo;
	private String strPostId;
	private String strThreadId;
	private String strThread;
	private String strPosting;
	private String strNickName;
	private String strSignature;
	private String strModerationStat;
	private String strModeratedBy;
	private String strModeratedOn;
	private String strPostedBy;
	private String strPostedOn;
	private Date dtPostedDate;
	private String strHQReply;
	private String strApproveStatus;
	private String strPostingNew;
	private String strLatestFlag;
	private int totalPost=0;
	
	/* Added/Modified by Aik Mun @ Jan 15, 2009 */
	private String strQuotePost;
	private String strPostingQuote;
	private String strReadFlag = "";
	
	/**
	 * @return the strReadFlag
	 */
	public String getStrReadFlag() {
		return strReadFlag;
	}
	/**
	 * @param strReadFlag the strReadFlag to set
	 */
	public void setStrReadFlag(String strReadFlag) {
		this.strReadFlag = strReadFlag;
	}
	/**
	 * @return the strPostingQuote
	 */
	public String getStrPostingQuote() {
		return strPostingQuote;
	}
	/**
	 * @param strPostingQuote the strPostingQuote to set
	 */
	public void setStrPostingQuote(String strPostingQuote) {
		this.strPostingQuote = strPostingQuote;
	}
	/**
	 * @return the strQuotePost
	 */
	public String getStrQuotePost() {
		return strQuotePost;
	}
	/**
	 * @param strQuotePost the strQuotePost to set
	 */
	public void setStrQuotePost(String strQuotePost) {
		this.strQuotePost = strQuotePost;
	}
	
	/**
	 * @return Returns the strPostingNew.
	 */
	public String getStrPostingNew() {
		return strPostingNew;
	}
	/**
	 * @param strPostingNew The strPostingNew to set.
	 */
	public void setStrPostingNew(String strPostingNew) {
		this.strPostingNew = strPostingNew;
	}
	
	public OIBAPosting() {	}

	public int getPageNo() {
		return pageNo;
	}
	public String getStrHQReply() {
		return strHQReply;
	}
	public String getStrModeratedBy() {
		return strModeratedBy;
	}
	public String getStrModeratedOn() {
		return strModeratedOn;
	}
	public String getStrModerationStat() {
		return strModerationStat;
	}
	public String getStrNickName() {
		return strNickName;
	}
	public String getStrPostedBy() {
		return strPostedBy;
	}
	public String getStrPostedOn() {
		return strPostedOn;
	}
	public String getStrPostId() {
		return strPostId;
	}
	public String getStrPosting() {
		return strPosting;
	}
	public String getStrSignature() {
		return strSignature;
	}
	public String getStrThreadId() {
		return strThreadId;
	}
	public String getStrUserId() {
		return strUserId;
	}
	public String getStrThread() {
		return strThread;
	}
	public String getStrApproveStatus() {
		return (strApproveStatus != null)?strApproveStatus:"D";
	}
	public void setStrApproveStatus(String strApproveStatus) {
		this.strApproveStatus = strApproveStatus;
	}
	public void setStrThread(String strThread) {
		this.strThread = strThread;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public void setStrHQReply(String strHQReply) {
		this.strHQReply = strHQReply;
	}
	public void setStrModeratedBy(String strModeratedBy) {
		this.strModeratedBy = strModeratedBy;
	}
	public void setStrModeratedOn(String strModeratedOn) {
		this.strModeratedOn = strModeratedOn;
	}
	public void setStrModerationStat(String strModerationStat) {
		this.strModerationStat = strModerationStat;
	}
	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
	}
	public void setStrPostedBy(String strPostedBy) {
		this.strPostedBy = strPostedBy;
	}
	public void setStrPostedOn(String strPostedOn) {
		this.strPostedOn = strPostedOn;
	}
	public void setStrPostId(String strPostId) {
		this.strPostId = strPostId;
	}
	public void setStrPosting(String strPosting) {
		this.strPosting = strPosting;
	}
	public void setStrSignature(String strSignature) {
		this.strSignature = strSignature;
	}
	public void setStrThreadId(String strThreadId) {
		this.strThreadId = strThreadId;
	}
	public void setStrUserId(String strUserId) {
		this.strUserId = strUserId;
	}
	/**
	 * @return Returns the totalPost.
	 */
	public int getTotalPost() {
		return totalPost;
	}
	/**
	 * @param totalPost The totalPost to set.
	 */
	public void setTotalPost(int totalPost) {
		this.totalPost = totalPost;
	}

	/**
	 * @return Returns the strLatestFlag.
	 */
	public String getStrLatestFlag() {
		return strLatestFlag;
	}
	/**
	 * @param strLatestFlag The strLatestFlag to set.
	 */
	public void setStrLatestFlag(String strLatestFlag) {
		this.strLatestFlag = strLatestFlag;
	}
	/**
	 * @return Returns the dtPostedDate.
	 */
	public Date getDtPostedDate() {
		return dtPostedDate;
	}
	/**
	 * @param dtPostedDate The dtPostedDate to set.
	 */
	public void setDtPostedDate(Date dtPostedDate) {
		this.dtPostedDate = dtPostedDate;
	}
	
}
