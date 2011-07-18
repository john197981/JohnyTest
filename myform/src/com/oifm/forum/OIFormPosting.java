
package com.oifm.forum;

import com.oifm.base.OIBaseActionForm;

public class OIFormPosting extends OIBaseActionForm  {

	private String strUserId;
	private int pageNo;
	private String strPostId;
	private String strThreadId;
	private String strPosting;
	private String strPostingNew;
	private String strNickName;
	private String strSignature;
	private String strModerationReq;
	private String strModerationStat;
	private String strModeratedBy;
	private String strModeratedOn;
	private String strHQReply;
	
	public OIFormPosting() {	}

	/* Added/Modified by Aik Mun @ Jan 15, 2009 */
	private String strQuotePost;
	private String strPostingQuote;
	
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
	public int getPageNo() {
		return pageNo;
	}
	public String getStrHQReply() {
		return (strHQReply != null)?strHQReply:"N";
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
	public String getStrModerationReq() {
		return strModerationReq;
	}
	public String getStrNickName() {
		return strNickName;
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
	public void setStrModerationReq(String strModerationReq) {
		this.strModerationReq = strModerationReq;
	}
	public void setStrNickName(String strNickName) {
		this.strNickName = strNickName;
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

}
