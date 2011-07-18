/*
 * File name	= OIBARanking.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import com.oifm.base.OIBaseBa;

public class OIBARanking extends OIBaseBa {
	
	private String strNumToShow;
	private String strPeriod;
	
	/**
	 * @return Returns the strNumToShow.
	 */
	public String getStrNumToShow() {
		return strNumToShow;
	}
	/**
	 * @param strNumToShow The strNumToShow to set.
	 */
	public void setStrNumToShow(String strNumToDisplay) {
		this.strNumToShow = strNumToDisplay;
	}
	/**
	 * @return Returns the strPeriod.
	 */
	public String getStrPeriod() {
		return strPeriod;
	}
	/**
	 * @param strPeriod The strPeriod to set.
	 */
	public void setStrPeriod(String strPeriod) {
		this.strPeriod = strPeriod;
	}
}
