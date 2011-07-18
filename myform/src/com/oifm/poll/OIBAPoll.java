/*
 * Created on Jul 27, 2005
 *
 To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.poll;

import java.io.Serializable;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIBAPoll implements Serializable {

	/** These variables for to store the response of Poll **/
	String strResId;
	String strRes;
	String strImgSize;
	String strImgPer;
	/**
	 * @return Returns the strImgPer.
	 */
	public String getImgPer() {
		return strImgPer;
	}
	/**
	 * @param strImgPer The strImgPer to set.
	 */
	public void setImgPer(String strImgPer) {
		this.strImgPer = strImgPer;
	}
	/**
	 * @return Returns the strImgSize.
	 */
	public String getImgSize() {
		return strImgSize;
	}
	/**
	 * @param strImgSize The strImgSize to set.
	 */
	public void setImgSize(String strImgSize) {
		this.strImgSize = strImgSize;
	}
	/**
	 * @return Returns the strRes.
	 */
	public String getRes() {
		return strRes;
	}
	/**
	 * @param strRes The strRes to set.
	 */
	public void setRes(String strRes) {
		this.strRes = strRes;
	}
	/**
	 * @return Returns the strResId.
	 */
	public String getResId() {
		return strResId;
	}
	/**
	 * @param strResId The strResId to set.
	 */
	public void setResId(String strResId) {
		this.strResId = strResId;
	}

}
