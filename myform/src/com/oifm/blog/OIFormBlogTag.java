package com.oifm.blog;

import com.oifm.base.OIBaseActionForm;

public class OIFormBlogTag extends OIBaseActionForm {

	private String[] tagArrays = null;
	private String tagName = null;
	private String tagId = null;
	private String hHome;
	private String hModule;
	
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
	
	public String getTagId(){
		return tagId;
	}
	
	public void setTagId(String tagId){
		this.tagId = tagId;
	}

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
}
