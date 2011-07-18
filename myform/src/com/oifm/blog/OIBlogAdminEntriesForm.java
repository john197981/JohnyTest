package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;

public class OIBlogAdminEntriesForm extends OIBaseActionForm
{
	public OIBlogAdminEntriesForm()
	{
		super();
	}
	private int entryId;
	private String entryTitle;
	private String[] entriesArray = null;
	private int noOfPosts;
	
	//added by edmund
	private String[] commentArray = null;

	/**
	 * @return the noOfPosts
	 */
	public int getNoOfPosts() {
		return noOfPosts;
	}

	/**
	 * @param noOfPosts the noOfPosts to set
	 */
	public void setNoOfPosts(int noOfPosts) {
		this.noOfPosts = noOfPosts;
	}

	/**
	 * @return the entriesArray
	 */
	public String[] getEntriesArray() {
		return entriesArray;
	}

	/**
	 * @param entriesArray the entriesArray to set
	 */
	public void setEntriesArray(String[] entriesArray) {
		this.entriesArray = entriesArray;
	}

	/**
	 * @return the entryId
	 */
	public int getEntryId() {
		return entryId;
	}

	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(int entryId) {
		this.entryId = entryId;
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
	
	//added by edmund
	
	public String[] getCommentArray() {
		return commentArray;
	}

	
	public void setCommentArray(String[] commentArray) {
		this.commentArray = commentArray;
	}
	

}
