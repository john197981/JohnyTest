package com.oifm.blog;

//added by edmund
import org.apache.struts.upload.FormFile;

import com.oifm.base.OIBaseActionForm;

public class OIBlogAdminCreateEntryForm extends OIBaseActionForm
{
	private int entryId;
	private String entryTitle;
	private int category;
	//private String expiryDate;
	private String entryBody;
	private String entryExcept;
	private String tags;
	private String status;
	private String acceptComments;
	
	private String strTagIdList;
	
	
	// added by edmund
	private FormFile fileUpload;
	
	public String getAcceptComments()
	{
		return (category == 0) ? "Y" : acceptComments;
		//return acceptComments;
	}
	public void setAcceptComments(String acceptComments)
	{
		this.acceptComments = acceptComments;
	}
	/**
	 * @return the category
	 */
	public int getCategory()
	{
		return category;
	}
	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(int category)
	{
		this.category = category;
	}
	public String getEntryBody()
	{
		return entryBody;
	}
	public void setEntryBody(String entryBody)
	{
		this.entryBody = entryBody;
	}
	public String getEntryExcept()
	{
		return entryExcept;
	}
	public void setEntryExcept(String entryExcept)
	{
		this.entryExcept = entryExcept;
	}
	public String getEntryTitle()
	{
		return entryTitle;
	}
	public void setEntryTitle(String entryTitle)
	{
		this.entryTitle = entryTitle;
	}
	public String getStatus()
	{
		return status;
	}
	public void setStatus(String status)
	{
		this.status = status;
	}
	public String getTags()
	{
		return tags;
	}
	public void setTags(String tags)
	{
		this.tags = tags;
	}
	/*public String getExpiryDate()
	{
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate)
	{
		this.expiryDate = expiryDate;
	}*/
	/**
	 * @return the entryId
	 */
	public int getEntryId()
	{
		return entryId;
	}
	/**
	 * @param entryId
	 *            the entryId to set
	 */
	public void setEntryId(int entryId)
	{
		this.entryId = entryId;
	}
	
	
	// added by edmund
	public FormFile getFileUpload()
	{
		return fileUpload;
	}
	
	public void setFileUpload(FormFile fileUpload)
	{
		this.fileUpload = fileUpload;
	}
	
	public String getStrTagIdList()
	{
		return strTagIdList;
	}
	
	public void setStrTagIdList(String strTagIdList)
	{
		this.strTagIdList = strTagIdList;
	}
	
}
