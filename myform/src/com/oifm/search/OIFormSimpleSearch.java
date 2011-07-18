/*
 * File name	= OIFormSimpleSearch.java
 * Package		= com.oifm.search
 * Created on 	= Aug 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.search;

import com.oifm.base.OIBaseActionForm;


public class OIFormSimpleSearch extends OIBaseActionForm {
	private String strSearchString;
	private String pageNo;
	/**
	 * @return Returns the strSearchString.
	 */
	public String getStrSearchString() {
		return strSearchString;
	}
	/**
	 * @param strSearchString The strSearchString to set.
	 */
	public void setStrSearchString(String strSearchString) {
		this.strSearchString = strSearchString;
	}
	/**
	 * @return Returns the pageNo.
	 */
	public String getPageNo() {
		return pageNo;
	}
	/**
	 * @param pageNo The pageNo to set.
	 */
	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}
}
