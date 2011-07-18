package com.oifm.blog;

import java.io.Serializable;

public class OIBABlogAdmin implements Serializable
{
	private String entryTitle;
	private String category;
	private String entryDate;
	private String entryBody;
	private String entryExcept;
	private String tags;
	private String status;
	private String acceptComments;
	
	/**
	 * @return the acceptComments
	 */
	public String getAcceptComments() {
		return acceptComments;
	}
	/**
	 * @param acceptComments the acceptComments to set
	 */
	public void setAcceptComments(String acceptComments) {
		this.acceptComments = acceptComments;
	}
	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
	/**
	 * @return the entryBody
	 */
	public String getEntryBody() {
		return entryBody;
	}
	/**
	 * @param entryBody the entryBody to set
	 */
	public void setEntryBody(String entryBody) {
		this.entryBody = entryBody;
	}
	/**
	 * @return the entryDate
	 */
	public String getEntryDate() {
		return entryDate;
	}
	/**
	 * @param entryDate the entryDate to set
	 */
	public void setEntryDate(String entryDate) {
		this.entryDate = entryDate;
	}
	/**
	 * @return the entryExcept
	 */
	public String getEntryExcept() {
		return entryExcept;
	}
	/**
	 * @param entryExcept the entryExcept to set
	 */
	public void setEntryExcept(String entryExcept) {
		this.entryExcept = entryExcept;
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
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}
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
}
