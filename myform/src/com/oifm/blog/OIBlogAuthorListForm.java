package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;

public class OIBlogAuthorListForm extends OIBaseActionForm
{
	private String[] aryUsers=null;
	private String pageNo;
	private String searchString;
	private String lastSearchString;
	
	public OIBlogAuthorListForm()
	{
		super();
	}	

	/**
	 * @return the aryUsers
	 */
	public String[] getAryUsers()
	{
		return aryUsers;
	}

	/**
	 * @param aryUsers the aryUsers to set
	 */
	public void setAryUsers(String[] aryUsers)
	{
		this.aryUsers = aryUsers;
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
	 * @return the searchString
	 */
	public String getSearchString()
	{
		return searchString;
	}

	/**
	 * @param searchString the searchString to set
	 */
	public void setSearchString(String searchString)
	{
		this.searchString = searchString;
	}

	/**
	 * @return the lastSearchString
	 */
	public String getLastSearchString()
	{
		return lastSearchString;
	}

	/**
	 * @param lastSearchString the lastSearchString to set
	 */
	public void setLastSearchString(String lastSearchString)
	{
		this.lastSearchString = lastSearchString;
	}

	
}
