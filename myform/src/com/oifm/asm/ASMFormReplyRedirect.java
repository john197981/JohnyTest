/*********************************ASMFormReplyReedirect.java******************* 
 * Title 		: ASMFormPrevRep
 * Description 	: This class is the Form Class for ASM Previous Replies. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 19 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import com.oifm.base.OIBaseActionForm;

public class ASMFormReplyRedirect extends OIBaseActionForm {
	private String strLetterID = null;
	private int iLink =0;
	private String strSortBy =null;
	
	/**
	 * @return Returns the strLetterID.
	 */
	
	public String getHidLetterID() {
		return strLetterID;
	}
	/**
	 * @param strLetterID The strLetterID to set.
	 */
	public void setHidLetterID(String strLetterID) {
		this.strLetterID = strLetterID;
	}
	/**
	 * @return Returns the iLink.
	 */
	public int getHidLink() {
		return iLink;
	}
	/**
	 * @param link The iLink to set.
	 */
	public void setHidLink(int link) {
		iLink = link;
	}
	/**
	 * @return Returns the strSortBy.
	 */
	public String getHidSortBy() {
		return strSortBy;
	}
	/**
	 * @param strSortBy The strSortBy to set.
	 */
	public void setHidSortBy(String strSortBy) {
		this.strSortBy = strSortBy;
	}
}
