/*
 * File name	= OIFormAuditTrail.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 21, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import com.oifm.base.OIBaseActionForm;


public class OIFormAuditTrail extends OIBaseActionForm {
	private String strType;
	private String strFrom;
	private String strTo;
	
	/**
	 * @return Returns the strFrom.
	 */
	public String getStrFrom() {
		return strFrom;
	}
	/**
	 * @param strFrom The strFrom to set.
	 */
	public void setStrFrom(String strFrom) {
		this.strFrom = strFrom;
	}
	/**
	 * @return Returns the strTo.
	 */
	public String getStrTo() {
		return strTo;
	}
	/**
	 * @param strTo The strTo to set.
	 */
	public void setStrTo(String strTo) {
		this.strTo = strTo;
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
		this.strType = strType;
	}
}
