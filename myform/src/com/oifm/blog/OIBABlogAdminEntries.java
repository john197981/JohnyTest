package com.oifm.blog;

import java.io.Serializable;
import java.util.Date;

public class OIBABlogAdminEntries implements Serializable 
{
	private Integer entryId = null;
	private String entryTitle = null;
	//added by edmund
	private String isFeatured = null;
	private String strStatus = null;
	private String createdBy = null;
	/**
	 * @return the entryId
	 */
	public Integer getEntryId() {
		return entryId;
	}
	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(Integer entryId) {
		this.entryId = entryId;
	}
	/**
	 * @return the entryTitle
	 */
	public String getEntryTitle() {
		return entryTitle;
	}
	/**
	 * @param entryTitle the entryTitle to set
	 */
	public void setEntryTitle(String entryTitle) {
		this.entryTitle = entryTitle;
	}
	
	//added by edmund
	public String getIsFeatured (){
		return isFeatured;
	}

	public void setIsFeatured (String isFeatured){
		this.isFeatured = isFeatured;
	}
	
	public String getStrStatus(){
		return strStatus;
	}
	
	public void setStrStatus(String strStatus){
		this.strStatus = strStatus;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy()
	{
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy)
	{
		this.createdBy = createdBy;
	}


}
