package com.oifm.blog;

import java.io.Serializable;
import java.util.List;

public class OIBABlog implements Serializable  {
	
	private Integer blogId = null;
	private String title = null;
	private Integer totalAuthors = null;
	private Integer totalEntries = null;
	private Integer totalComments = null;
	private List authors;
	private Integer noOfPosts;
	
	
	/**
	 * @return the noOfPosts
	 */
	public Integer getNoOfPosts() {
		return noOfPosts;
	}
	/**
	 * @param noOfPosts the noOfPosts to set
	 */
	public void setNoOfPosts(Integer noOfPosts) {
		this.noOfPosts = noOfPosts;
	}
	/**
	 * @return the blogId
	 */
	public Integer getBlogId() {
		return blogId;
	}
	/**
	 * @param blogId the blogId to set
	 */
	public void setBlogId(Integer blogId) {
		this.blogId = blogId;
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
	 * @return the totalAuthors
	 */
	public Integer getTotalAuthors()
	{
		return totalAuthors;
	}
	/**
	 * @param totalAuthors the totalAuthors to set
	 */
	public void setTotalAuthors(Integer totalAuthors)
	{
		this.totalAuthors = totalAuthors;
	}
	/**
	 * @return the totalComments
	 */
	public Integer getTotalComments()
	{
		return totalComments;
	}
	/**
	 * @param totalComments the totalComments to set
	 */
	public void setTotalComments(Integer totalComments)
	{
		this.totalComments = totalComments;
	}
	/**
	 * @return the totalEntries
	 */
	public Integer getTotalEntries()
	{
		return totalEntries;
	}
	/**
	 * @param totalEntries the totalEntries to set
	 */
	public void setTotalEntries(Integer totalEntries)
	{
		this.totalEntries = totalEntries;
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
	

}
