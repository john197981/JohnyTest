package com.oifm.blog;

import java.io.Serializable;

public class OIBABlogArchives implements Serializable
{
	private Integer blogId = null;
	private Integer CategoryId = null;
	private String month = null;
	private String authorId = null;
	private String totalCountQuery = null;
	private String archivesQuery = null;
	private int totalRecords;
	private int recPerPage;
	private String rowId = null;
	
	private Integer tagId = null;
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
	 * @return the categoryId
	 */
	public Integer getCategoryId()
	{
		return CategoryId;
	}
	/**
	 * @param categoryId the categoryId to set
	 */
	public void setCategoryId(Integer categoryId)
	{
		CategoryId = categoryId;
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
	 * @return the totalCountQuery
	 */
	public String getTotalCountQuery()
	{
		return totalCountQuery;
	}
	/**
	 * @param totalCountQuery the totalCountQuery to set
	 */
	public void setTotalCountQuery(String totalCountQuery)
	{
		this.totalCountQuery = totalCountQuery;
	}
	/**
	 * @return the totalRecords
	 */
	public int getTotalRecords()
	{
		return totalRecords;
	}
	/**
	 * @param totalRecords the totalRecords to set
	 */
	public void setTotalRecords(int totalRecords)
	{
		this.totalRecords = totalRecords;
	}
	/**
	 * @return the rowId
	 */
	public String getRowId()
	{
		return rowId;
	}
	/**
	 * @param rowId the rowId to set
	 */
	public void setRowId(String rowId)
	{
		this.rowId = rowId;
	}
	/**
	 * @return the recPerPage
	 */
	public int getRecPerPage()
	{
		return recPerPage;
	}
	/**
	 * @param recPerPage the recPerPage to set
	 */
	public void setRecPerPage(int recPerPage)
	{
		this.recPerPage = recPerPage;
	}
	/**
	 * @return the archivesQuery
	 */
	public String getArchivesQuery()
	{
		return archivesQuery;
	}
	/**
	 * @param archivesQuery the archivesQuery to set
	 */
	public void setArchivesQuery(String archivesQuery)
	{
		this.archivesQuery = archivesQuery;
	}
	/**
	 * @return the tagId
	 */
	public Integer getTagId()
	{
		return tagId;
	}
	/**
	 * @param tagId the tagId to set
	 */
	public void setTagId(Integer tagId)
	{
		this.tagId = tagId;
	}
}
