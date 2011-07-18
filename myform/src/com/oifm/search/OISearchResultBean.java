/*
 * File name	= OISearchResultBean.java
 * Package		= com.oifm.search
 * Created on 	= Aug 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.search;


public class OISearchResultBean {
	private String rowNum;
	private String strID;
	private String strTitle;
	private String strDescription;
	private String strCreatedBy;
	private String strNickname;
	private String strCreatedOn;
	
	public OISearchResultBean() {}
	/**
	 * @return Returns the strCreatedBy.
	 */
	public String getStrCreatedBy() {
		return strCreatedBy;
	}
	/**
	 * @param strCreatedBy The strCreatedBy to set.
	 */
	public void setStrCreatedBy(String strCreatedBy) {
		this.strCreatedBy = strCreatedBy;
	}
	/**
	 * @return Returns the strCreatedOn.
	 */
	public String getStrCreatedOn() {
		return strCreatedOn;
	}
	/**
	 * @param strCreatedOn The strCreatedOn to set.
	 */
	public void setStrCreatedOn(String strCreatedOn) {
		this.strCreatedOn = strCreatedOn;
	}
	/**
	 * @return Returns the strDescription.
	 */
	public String getStrDescription() {
		return strDescription;
	}
	/**
	 * @param strDescription The strDescription to set.
	 */
	public void setStrDescription(String strDescription) {
		this.strDescription = strDescription;
	}
	/**
	 * @return Returns the strID.
	 */
	public String getStrID() {
		return strID;
	}
	/**
	 * @param strID The strID to set.
	 */
	public void setStrID(String strID) {
		this.strID = strID;
	}
	/**
	 * @return Returns the strNickname.
	 */
	public String getStrNickname() {
		return strNickname;
	}
	/**
	 * @param strNickname The strNickname to set.
	 */
	public void setStrNickname(String strNickname) {
		this.strNickname = strNickname;
	}
	/**
	 * @return Returns the strTitle.
	 */
	public String getStrTitle() {
		return strTitle;
	}
	/**
	 * @param strTitle The strTitle to set.
	 */
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	/**
	 * @return Returns the rowNum.
	 */
	public String getRowNum() {
		return rowNum;
	}
	/**
	 * @param rowNum The rowNum to set.
	 */
	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
}
