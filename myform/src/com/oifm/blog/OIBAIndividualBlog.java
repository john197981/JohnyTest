package com.oifm.blog;

import java.util.ArrayList;

import org.apache.struts.upload.FormFile;

import com.oifm.base.OIBaseBa;

public class OIBAIndividualBlog extends OIBaseBa
{
	private Integer blogId;
	private String blogTitle;
	private Integer entryId;
	private String title;
	private String date;
	private boolean commentAllowed;
	private String entryExcerpt;
	private String entryBody;
	private String picFileName;
	private String comment;
	private String commentDate;
	private String commentUser;
	private String authorUserId;
	private String authorUserName;
	private String authorNotifyEmail = "N";
	private String authorDescription;
	private String authorImageFileName;
	private FormFile fileUpload;
	
	private Integer commentCount;
	private ArrayList tagList;
	
	/**
	 * @return the commentDate
	 */
	public String getCommentDate()
	{
		return commentDate;
	}
	/**
	 * @param commentDate
	 *            the commentDate to set
	 */
	public void setCommentDate(String commentDate)
	{
		this.commentDate = commentDate;
	}
	/**
	 * @return the commentUser
	 */
	public String getCommentUser()
	{
		return commentUser;
	}
	/**
	 * @param commentUser
	 *            the commentUser to set
	 */
	public void setCommentUser(String commentUser)
	{
		this.commentUser = commentUser;
	}
	/**
	 * @return the comment
	 */
	public String getComment()
	{
		return comment;
	}
	/**
	 * @param comment
	 *            the comment to set
	 */
	public void setComment(String comment)
	{
		this.comment = comment;
	}
	/**
	 * @return the entryBody
	 */
	public String getEntryBody()
	{
		return entryBody;
	}
	/**
	 * @param entryBody
	 *            the entryBody to set
	 */
	public void setEntryBody(String entryBody)
	{
		this.entryBody = entryBody;
	}
	/**
	 * @return the commentAllowed
	 */
	public boolean isCommentAllowed()
	{
		return commentAllowed;
	}
	/**
	 * @param commentAllowed
	 *            the commentAllowed to set
	 */
	public void setCommentAllowed(boolean commentAllowed)
	{
		this.commentAllowed = commentAllowed;
	}
	/**
	 * @return the date
	 */
	public String getDate()
	{
		return date;
	}
	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date)
	{
		this.date = date;
	}
	/**
	 * @return the entryId
	 */
	public Integer getEntryId()
	{
		return entryId;
	}
	/**
	 * @param entryId
	 *            the entryId to set
	 */
	public void setEntryId(Integer entryId)
	{
		this.entryId = entryId;
	}
	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}
	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}
	/**
	 * @return the entryExcerpt
	 */
	public String getEntryExcerpt()
	{
		return entryExcerpt;
	}
	/**
	 * @param entryExcerpt
	 *            the entryExcerpt to set
	 */
	public void setEntryExcerpt(String entryExcerpt)
	{
		this.entryExcerpt = entryExcerpt;
	}
	/**
	 * @return the blogId
	 */
	public Integer getBlogId()
	{
		return blogId;
	}
	/**
	 * @param blogId
	 *            the blogId to set
	 */
	public void setBlogId(Integer blogId)
	{
		this.blogId = blogId;
	}
	/**
	 * @return the picFileName
	 */
	public String getPicFileName()
	{
		return picFileName;
	}
	/**
	 * @param picFileName
	 *            the picFileName to set
	 */
	public void setPicFileName(String picFileName)
	{
		this.picFileName = picFileName;
	}
	/**
	 * @return the authorDescription
	 */
	public String getAuthorDescription()
	{
		return authorDescription;
	}
	/**
	 * @param authorDescription
	 *            the authorDescription to set
	 */
	public void setAuthorDescription(String authorDescription)
	{
		this.authorDescription = authorDescription;
	}
	/**
	 * @return the authorImageFileName
	 */
	public String getAuthorImageFileName()
	{
		return authorImageFileName;
	}
	/**
	 * @param authorImageFileName
	 *            the authorImageFileName to set
	 */
	public void setAuthorImageFileName(String authorImageFileName)
	{
		this.authorImageFileName = authorImageFileName;
	}
	/**
	 * @return the authorNotifyEmail
	 */
	public String getAuthorNotifyEmail()
	{
		return authorNotifyEmail;
	}
	/**
	 * @param authorNotifyEmail
	 *            the authorNotifyEmail to set
	 */
	public void setAuthorNotifyEmail(String authorNotifyEmail)
	{
		this.authorNotifyEmail = (authorNotifyEmail != null) ? authorNotifyEmail : "N";
	}
	/**
	 * @return the authorUserId
	 */
	public String getAuthorUserId()
	{
		return authorUserId;
	}
	/**
	 * @param authorUserId
	 *            the authorUserId to set
	 */
	public void setAuthorUserId(String authorUserId)
	{
		this.authorUserId = authorUserId;
	}
	/**
	 * @return the authorUserName
	 */
	public String getAuthorUserName()
	{
		return authorUserName;
	}
	/**
	 * @param authorUserName
	 *            the authorUserName to set
	 */
	public void setAuthorUserName(String authorUserName)
	{
		this.authorUserName = authorUserName;
	}
	/**
	 * @return the fileUpload
	 */
	public FormFile getFileUpload()
	{
		return fileUpload;
	}
	/**
	 * @param fileUpload
	 *            the fileUpload to set
	 */
	public void setFileUpload(FormFile fileUpload)
	{
		this.fileUpload = fileUpload;
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
	
	public Integer getCommentCount(){
		return commentCount;
	}
	
	public void setCommentCount(Integer commentCount){
		this.commentCount = commentCount;
	}
	/**
	 * @return the tagList
	 */
	public ArrayList getTagList()
	{
		return tagList;
	}
	/**
	 * @param tagList the tagList to set
	 */
	public void setTagList(ArrayList tagList)
	{
		this.tagList = tagList;
	}
}
