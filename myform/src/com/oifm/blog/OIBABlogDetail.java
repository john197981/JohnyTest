package com.oifm.blog;

import java.io.Serializable;
import java.util.List;

public class OIBABlogDetail implements Serializable
{	
	private OIBABlogAdminCreateEntry latestFeaturedPost =  null;
	private OIBABlogConfig blogConfig = null;
	private List authors =  null;
	private List categories =  null;
	private List months =  null;
	private List blogs =  null;
	private List latestPosts =  null;
	private List previousFeaturedPosts =  null;	
	
	/**
	 * @return the blogs
	 */
	public List getBlogs()
	{
		return blogs;
	}
	/**
	 * @param blogs the blogs to set
	 */
	public void setBlogs(List blogs)
	{
		this.blogs = blogs;
	}	
	/**
	 * @return the latestPosts
	 */
	public List getLatestPosts()
	{
		return latestPosts;
	}
	/**
	 * @param latestPosts the latestPosts to set
	 */
	public void setLatestPosts(List latestPosts)
	{
		this.latestPosts = latestPosts;
	}
	/**
	 * @return the blogConfig
	 */
	public OIBABlogConfig getBlogConfig()
	{
		return blogConfig;
	}
	/**
	 * @param blogConfig the blogConfig to set
	 */
	public void setBlogConfig(OIBABlogConfig blogConfig)
	{
		this.blogConfig = blogConfig;
	}
	/**
	 * @return the latestFeaturedPost
	 */
	public OIBABlogAdminCreateEntry getLatestFeaturedPost()
	{
		return latestFeaturedPost;
	}
	/**
	 * @param latestFeaturedPost the latestFeaturedPost to set
	 */
	public void setLatestFeaturedPost(OIBABlogAdminCreateEntry latestFeaturedPost)
	{
		this.latestFeaturedPost = latestFeaturedPost;
	}
	/**
	 * @return the previousFeaturedPosts
	 */
	public List getPreviousFeaturedPosts()
	{
		return previousFeaturedPosts;
	}
	/**
	 * @param previousFeaturedPosts the previousFeaturedPosts to set
	 */
	public void setPreviousFeaturedPosts(List previousFeaturedPosts)
	{
		this.previousFeaturedPosts = previousFeaturedPosts;
	}
	/**
	 * @return the authors
	 */
	public List getAuthors()
	{
		return authors;
	}
	/**
	 * @param authors the authors to set
	 */
	public void setAuthors(List authors)
	{
		this.authors = authors;
	}
	/**
	 * @return the categories
	 */
	public List getCategories()
	{
		return categories;
	}
	/**
	 * @param categories the categories to set
	 */
	public void setCategories(List categories)
	{
		this.categories = categories;
	}
	/**
	 * @return the months
	 */
	public List getMonths()
	{
		return months;
	}
	/**
	 * @param months the months to set
	 */
	public void setMonths(List months)
	{
		this.months = months;
	}
	
}
