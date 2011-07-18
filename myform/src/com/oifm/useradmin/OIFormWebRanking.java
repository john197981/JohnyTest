/*
 * File name	= OIFormRanking.java
 * Package		= com.oifm.useradmin
 * Created on 	= 
 * Created by	= 
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;
import java.util.Date;

import com.oifm.base.OIBaseActionForm;


public class OIFormWebRanking extends OIBaseActionForm {	
	
	private String title;
	private String typeList;
	private String fromDate;
	private String toDate;
	private String hidAction;
	private String hidUserId;
	private String hidType;
	private String hidUserName;
	
	/**
	 * @return Returns the title.
	 */
	public String getTitle() {		
		return title;
	}
	/**
	 * @param title The title to set.
	 */
	public void setTitle(String title) {		
		this.title = title;
	}
	/**
	 * @return Returns the typeList.
	 */
	public String getTypeList() {		
		return typeList;
	}
	/**
	 * @param title The typeList to set.
	 */
	public void setTypeList(String typeList) {
		this.typeList = typeList;
	}
	/**
	 * @return Returns the fromDate.
	 */
	public String getFromDate() {		
		return fromDate;
	}
	/**
	 * @param title The typeList to set.
	 */
	public void setFromDate(String fromDate) {		
		this.fromDate = fromDate;
	}
	/**
	 * @return Returns the toDate.
	 */
	public String getToDate() {
		return toDate;
	}
	/**
	 * @param title The typeList to set.
	 */
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	/**
	 * @return Returns the hidAction.
	 */
	public String getHidAction() {
		return hidAction;
	}
	/**
	 * @param hidAction The hidAction to set.
	 */
	public void setHidAction(String hidAction) {
		this.hidAction = hidAction;
	}
	/**
	 * @return Returns the hidAction.
	 */
	public String getHidUserId() {
		return hidUserId;
	}
	/**
	 * @param hidAction The hidAction to set.
	 */
	public void setHidUserId(String hidUserId) {
		this.hidUserId = hidUserId;
	}
	
	/**
	 * @return Returns the hidAction.
	 */
	public String getHidType() {
		return hidType;
	}
	/**
	 * @param hidAction The hidAction to set.
	 */
	public void setHidType(String hidType) {
		this.hidType = hidType;
	}
	/**
	 * @return Returns the hidUserName.
	 */
	public String getHidUserName() {
		return hidUserName;
	}
	/**
	 * @param hidAction The hidUserName to set.
	 */
	public void setHidUserName(String hidUserName) {
		this.hidUserName = hidUserName;
	}
}