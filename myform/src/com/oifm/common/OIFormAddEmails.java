/*
 * File name	= OIFormAddEmails.java
 * Package		= com.oifm.common
 * Created on 	= Aug 22, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import com.oifm.base.OIBaseActionForm;


public class OIFormAddEmails extends OIBaseActionForm {
	private String strGroupID;
	private String strEmails;
	private String[] arMembers;
	
	/**
	 * @return Returns the strEmails.
	 */
	public String getStrEmails() {
		return strEmails;
	}
	/**
	 * @param strEmails The strEmails to set.
	 */
	public void setStrEmails(String strEmails) {
		this.strEmails = strEmails;
	}
	/**
	 * @return Returns the arMembers.
	 */
	public String[] getArMembers() {
		return arMembers;
	}
	/**
	 * @param arMembers The arMembers to set.
	 */
	public void setArMembers(String[] arMembers) {
		this.arMembers = arMembers;
	}
	/**
	 * @return Returns the strGroupID.
	 */
	public String getStrGroupID() {
		return strGroupID;
	}
	/**
	 * @param strGroupID The strGroupID to set.
	 */
	public void setStrGroupID(String strGroupID) {
		this.strGroupID = strGroupID;
	}
}
