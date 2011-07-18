package com.oifm.blog;

import java.io.Serializable;

public class OIBABlogTag implements Serializable {

	private String[] tagArrays = null;
	private String tagName = null;
	private Integer tagId;
	private boolean hasPost =false;
	private String hHome;
	private String hModule;
	
	/**
	 * @return the hHome
	 */
	public String getHHome()
	{
		return hHome;
	}

	/**
	 * @param home the hHome to set
	 */
	public void setHHome(String home)
	{
		hHome = home;
	}

	/**
	 * @return the hModule
	 */
	public String getHModule()
	{
		return hModule;
	}

	/**
	 * @param module the hModule to set
	 */
	public void setHModule(String module)
	{
		hModule = module;
	}

	public String[] getTagArrays(){
		return tagArrays;
	}
	
	public void setTagArrays(String[] tagArrays){
		this.tagArrays = tagArrays;
	}
	
	public String getTagName(){
		return tagName;
	}
	
	public void setTagName(String tagName){
		this.tagName = tagName;
	}
	
	public Integer getTagId(){
		return tagId;
	}
	
	public void setTagId(Integer tagId){
		this.tagId = tagId;
	}

	/**
	 * @return the hasPost
	 */
	public boolean isHasPost()
	{
		return hasPost;
	}

	/**
	 * @param hasPost the hasPost to set
	 */
	public void setHasPost(boolean hasPost)
	{
		this.hasPost = hasPost;
	}
	
}
