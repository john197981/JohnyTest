
package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;


public class OIBlogAdminForm extends OIBaseActionForm 
{
	private String[] aryBlogs = null;
	private int blogId;
	private int noOfPosts ;
	private int noOfFeatPosts ;
	private String blogMessage =null;
	
	public OIBlogAdminForm() {
		super();
	}

	/**
	 * @return the aryBlogs
	 */
	public String[] getAryBlogs()
	{
		return aryBlogs;
	}

	/**
	 * @param aryBlogs the aryBlogs to set
	 */
	public void setAryBlogs(String[] aryBlogs)
	{
		this.aryBlogs = aryBlogs;
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
	 * @return the blogMessage
	 */
	public String getBlogMessage()
	{
		return blogMessage;
	}

	/**
	 * @param blogMessage the blogMessage to set
	 */
	public void setBlogMessage(String blogMessage)
	{
		this.blogMessage = blogMessage;
	}

	/**
	 * @return the noOfFeatPosts
	 */
	public int getNoOfFeatPosts()
	{
		return noOfFeatPosts;
	}

	/**
	 * @param noOfFeatPosts the noOfFeatPosts to set
	 */
	public void setNoOfFeatPosts(int noOfFeatPosts)
	{
		this.noOfFeatPosts = noOfFeatPosts;
	}

	/**
	 * @return the noOfPosts
	 */
	public int getNoOfPosts()
	{
		return noOfPosts;
	}

	/**
	 * @param noOfPosts the noOfPosts to set
	 */
	public void setNoOfPosts(int noOfPosts)
	{
		this.noOfPosts = noOfPosts;
	}	
}
