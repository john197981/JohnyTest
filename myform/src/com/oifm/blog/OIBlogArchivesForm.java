package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;

public class OIBlogArchivesForm extends OIBaseActionForm
{
	private int categoryId;
	private String month;
	private String authorId;
	private String pageNo;
	private int tagId;
	private String authorName;
	private String categoryName;
	private String tagName;
	
	
	public OIBlogArchivesForm()
	{
		super();
	}

	/**
	 * @return the authorId
	 */
	public String getAuthorId()
	{
		return authorId;
	}

	/**
	 * @param authorId the authorId to set
	 */
	public void setAuthorId(String authorId)
	{
		this.authorId = authorId;
	}

	/**
	 * @return the categoryId
	 */
	public int getCategoryId()
	{
		return categoryId;
	}

	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(int categoryId)
	{
		this.categoryId = categoryId;
	}

	/**
	 * @return the month
	 */
	public String getMonth()
	{
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month)
	{
		this.month = month;
	}

	/**
	 * @return the pageNo
	 */
	public String getPageNo()
	{
		return pageNo;
	}

	/**
	 * @param pageNo the pageNo to set
	 */
	public void setPageNo(String pageNo)
	{
		this.pageNo = pageNo;
	}

	/**
	 * @return the tagId
	 */
	public int getTagId()
	{
		return tagId;
	}

	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(int tagId)
	{
		this.tagId = tagId;
	}

	/**
	 * @return the authorName
	 */
	public String getAuthorName()
	{
		return authorName;
	}

	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName)
	{
		this.authorName = authorName;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName()
	{
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName)
	{
		this.categoryName = categoryName;
	}

	/**
	 * @return the tagName
	 */
	public String getTagName()
	{
		return tagName;
	}

	/**
	 * @param tagName the tagName to set
	 */
	public void setTagName(String tagName)
	{
		this.tagName = tagName;
	}
	
	
}
