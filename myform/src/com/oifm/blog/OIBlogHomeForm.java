
package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;


public class OIBlogHomeForm extends OIBaseActionForm 
{
	int blogId;
	String picFileName; // Added by Oscar
	
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
	public OIBlogHomeForm() {
		super();
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
	
}
