package com.oifm.blog;

import java.io.Serializable;

public class OIBABlogConfig implements Serializable
{
	private Integer noOfPosts = null;
	private Integer noOfFeatPosts = null;
	private String blogMessage = null;

	
	/**
	 * @return the noOfFeatPosts
	 */
	public Integer getNoOfFeatPosts()
	{
		return noOfFeatPosts;
	}
	/**
	 * @param noOfFeatPosts the noOfFeatPosts to set
	 */
	public void setNoOfFeatPosts(Integer noOfFeatPosts)
	{
		this.noOfFeatPosts = noOfFeatPosts;
	}
	/**
	 * @return the noOfPosts
	 */
	public Integer getNoOfPosts()
	{
		return noOfPosts;
	}
	/**
	 * @param noOfPosts the noOfPosts to set
	 */
	public void setNoOfPosts(Integer noOfPosts)
	{
		this.noOfPosts = noOfPosts;
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
	
}
