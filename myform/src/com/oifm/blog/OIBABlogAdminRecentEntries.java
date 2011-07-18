package com.oifm.blog;

import java.io.Serializable;
import java.util.Date;

public class OIBABlogAdminRecentEntries implements Serializable 
{
	private String commentor = null;
	private String entryTitle = null;
	private Date commentDate = null;
	private Date entryDate = null;
	private Integer noOfPosts = null;
	/**
	 * @return the commentor
	 */
	public String getCommentor() {
		return commentor;
	}
	/**
	 * @param commentor the commentor to set
	 */
	public void setCommentor(String commentor) {
		this.commentor = commentor;
	}
	/**
	 * @return the entryTitle
	 */
	public String getEntryTitle() {
		return entryTitle;
	}
	/**
	 * @param entryTitle the entryTitle to set
	 */
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	/**
	 * @return the commentDate
	 */
	public Date getCommentDate() {
		return commentDate;
	}
	/**
	 * @param commentDate the commentDate to set
	 */
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}
	/**
	 * @return the entryDate
	 */
	public Date getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return the noOfPosts
	 */
	public Integer getNoOfPosts()
	{
		return noOfPosts;
	}
	/**
	 * @param noOfPosts the noOfPosts to set
	 */
	public void setNoOfPosts(Integer noOfPosts)
	{
		this.noOfPosts = noOfPosts;
	}
}
