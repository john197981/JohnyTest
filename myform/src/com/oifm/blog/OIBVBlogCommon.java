package com.oifm.blog;

import java.util.Date;

public class OIBVBlogCommon {
	
	
	private Integer entryId = null;
	private String entryTitle = null;
	private Integer blogId = null;
	private String blogTitle =  null;
	private String createdOn = null;
	/**
	 * @return the blogId
	 */
	public Integer getBlogId() {
		return blogId;
	}
	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
	}
	/**
	 * @return the blogTitle
	 */
	public String getBlogTitle() {
		return blogTitle;
	}
	/**
	 * @param blogTitle the blogTitle to set
	 */
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	/**
	 * @return the createdOn
	 */
	public String getCreatedOn() {
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(String createdOn) {
		this.createdOn = createdOn;
	}
	/**
	 * @return the entryId
	 */
	public Integer getEntryId() {
		return entryId;
	}
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(Integer entryId) {
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


}
