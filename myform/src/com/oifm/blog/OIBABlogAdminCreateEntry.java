package com.oifm.blog;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.struts.upload.FormFile;

public class OIBABlogAdminCreateEntry implements Serializable
{	
	private String entryTitle = null;
	private Integer category = null;
	//private String expiryDate = null;
	private String entryBody= null;
	private String entryExcept= null;
	private String tags= null;
	private String status= null;
	private String acceptComments= null;
	private Integer blogId = null;
	private String blogTitle =  null;
	private Date createdOn = null;
	private Integer entryId = null;
	private Integer totalComments = null;
	private String picFileName = null;
	private List tagList = null;
	
	//	added by edmund
	private FormFile fileUpload;
	private String createdBy = null;
	private String strTagIdList;
	
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
	public Integer getCategory() {
		return category;
	}
	/**
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
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
	 * @return the expiryDate
	 */
	/*public String getExpiryDate() {
		return expiryDate;
	}
	*//**
	 * @param expiryDate the expiryDate to set
	 *//*
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}*/
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
	/**
	 * @return the blogId
	 */
	public Integer getBlogId()
	{
		return blogId;
	}
	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Integer blogId)
	{
		this.blogId = blogId;
	}
	/**
	 * @return the blogTitle
	 */
	public String getBlogTitle()
	{
		return blogTitle;
	}
	/**
	 * @param blogTitle the blogTitle to set
	 */
	public void setBlogTitle(String blogTitle)
	{
		this.blogTitle = blogTitle;
	}
	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn()
	{
		return createdOn;
	}
	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn)
	{
		this.createdOn = createdOn;
	}
	
	/**
	 * @return the totalComments
	 */
	public Integer getTotalComments()
	{
		return totalComments;
	}
	/**
	 * @param totalComments the totalComments to set
	 */
	public void setTotalComments(Integer totalComments)
	{
		this.totalComments = totalComments;
	}

//	added by edmund
	public FormFile getFileUpload() {
	    return fileUpload;
	  }

	public void setFileUpload(FormFile fileUpload) {
	    this.fileUpload = fileUpload;
	  }
	/**
	 * @return the entryId
	 */
	public Integer getEntryId()
	{
		return entryId;
	}
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(Integer entryId)
	{
		this.entryId = entryId;
	}
	/**
	 * @return the picFileName
	 */
	public String getPicFileName()
	{
		return picFileName;
	}
	/**
	 * @param picFileName the picFileName to set
	 */
	public void setPicFileName(String picFileName)
	{
		this.picFileName = picFileName;
	}
	
	public String getCreatedBy(){
		return createdBy;
	}
	
	public void setCreatedBy(String createdBy){
		this.createdBy = createdBy;
	}
	/**
	 * @return the tagList
	 */
	public List getTagList()
	{
		return tagList;
	}
	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(List tagList)
	{
		this.tagList = tagList;
	}
	
	public String getStrTagIdList(){
		return strTagIdList;
	}
	
	public void setStrTagIdList(String strTagIdList){
		this.strTagIdList = strTagIdList;
	}

}
