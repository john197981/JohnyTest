package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;

public class OIBlogAdminRecentEntriesForm extends OIBaseActionForm
{
	public OIBlogAdminRecentEntriesForm()
	{
		super();
	}
	
	private Integer noOfPosts;

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

}
