package com.oifm.common;

import java.io.Serializable;

public class OIBAAddTag implements Serializable{

	private String[] tagArrays = null;
	private String tagName = null;
	private Integer tagId;
	private boolean hasPost =false;
	
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
