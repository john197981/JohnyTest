/*
 * File name	= OIAuditTrailBean.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 21, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;


public class OIAuditTrailBean {
	private String strUserID;
	private String strNickname;
	private String strType;
	private String strDateTime;
	private String strPostDateTime;
	private String strPostUserID;
	private String strPostNickname;
	private String strThreadID;
	private String strPostMessage;
	private String strModMessage;

	public OIAuditTrailBean() {}
	/**
	 * @return Returns the strDateTime.
	 */
	public String getStrDateTime() {
		return strDateTime;
	}
	/**
	 * @param strDateTime The strDateTime to set.
	 */
	public void setStrDateTime(String strDateTime) {
		this.strDateTime = strDateTime;
	}
	/**
	 * @return Returns the strModMessage.
	 */
	public String getStrModMessage() {
		return strModMessage;
	}
	/**
	 * @param strModMesage The strModMessage to set.
	 */
	public void setStrModMessage(String strModMessage) {
		this.strModMessage = strModMessage;
	}
	/**
	 * @return Returns the strPostDateTime.
	 */
	public String getStrPostDateTime() {
		return strPostDateTime;
	}
	/**
	 * @param strPostDateTime The strPostDateTime to set.
	 */
	public void setStrPostDateTime(String strPostDateTime) {
		this.strPostDateTime = strPostDateTime;
	}
	/**
	 * @return Returns the strPostMessage.
	 */
	public String getStrPostMessage() {
		return strPostMessage;
	}
	/**
	 * @param strPostMessage The strPostMessage to set.
	 */
	public void setStrPostMessage(String strPostMessage) {
		this.strPostMessage = strPostMessage;
	}
	/**
	 * @return Returns the strPostNickname.
	 */
	public String getStrPostNickname() {
		return strPostNickname;
	}
	/**
	 * @param strPostNickname The strPostNickname to set.
	 */
	public void setStrPostNickname(String strPostNickname) {
		this.strPostNickname = strPostNickname;
	}
	/**
	 * @return Returns the strPostUserID.
	 */
	public String getStrPostUserID() {
		return strPostUserID;
	}
	/**
	 * @param strPostUserID The strPostUserID to set.
	 */
	public void setStrPostUserID(String strPostUserID) {
		this.strPostUserID = strPostUserID;
	}
	/**
	 * @return Returns the strThreadID.
	 */
	public String getStrThreadID() {
		return strThreadID;
	}
	/**
	 * @param strThreadID The strThreadID to set.
	 */
	public void setStrThreadID(String strThreadID) {
		this.strThreadID = strThreadID;
	}
	/**
	 * @return Returns the strType.
	 */
	public String getStrType() {
		return strType;
	}
	/**
	 * @param strType The strType to set.
	 */
	public void setStrType(String strType) {
		if (strType.equalsIgnoreCase("MP")) this.strType = "Moderate Posting";
		else if (strType.equalsIgnoreCase("MT")) this.strType = "Moderate Thread";
	}
	/**
	 * @return Returns the strUserID.
	 */
	public String getStrUserID() {
		return strUserID;
	}
	/**
	 * @param strUserID The strUserID to set.
	 */
	public void setStrUserID(String strUserID) {
		this.strUserID = strUserID;
	}
	/**
	 * @return Returns the strNickname.
	 */
	public String getStrNickname() {
		return strNickname;
	}
	/**
	 * @param strUserName The strUserName to set.
	 */
	public void setStrNickname(String strNickname) {
		this.strNickname = strNickname;
	}
}
