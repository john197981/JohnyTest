package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;

public class OICreateBlogForm extends OIBaseActionForm
{
	private String title;
	private int blogId ;
	private String[] deleteArthors=null;

	
	public OICreateBlogForm()
	{
		super();
	}

	/**
	 * @return the title
	 */
	public String getTitle()
	{
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title)
	{
		this.title = title;
	}

	/**
	 * @return the blogId
	 */
	public int getBlogId()
	{
		return blogId;
	}

	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(int blogId)
	{
		this.blogId = blogId;
	}

	/**
	 * @return the deleteArthors
	 */
	public String[] getDeleteArthors()
	{
		return deleteArthors;
	}

	/**
	 * @param deleteArthors the deleteArthors to set
	 */
	public void setDeleteArthors(String[] deleteArthors)
	{
		this.deleteArthors = deleteArthors;
	}

}
