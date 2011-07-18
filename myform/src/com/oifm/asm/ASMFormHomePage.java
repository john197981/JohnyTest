/*********************************ASMFormHomePage.java******************* 
 * Title 		: ASMFormHomePage
 * Description 	: This class is the Form Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import com.oifm.base.OIBaseActionForm;

public class ASMFormHomePage extends OIBaseActionForm {
	private String strLetterID = null;
	private String strPageDesc = null;
	
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
	 * @return Returns the strPageDesc.
	 */
	public String getHidPageDesc() {
		return strPageDesc;
	}
	/**
	 * @param strPageDesc The strPageDesc to set.
	 */
	public void setHidPageDesc(String strPageDesc) {
		this.strPageDesc = strPageDesc;
	}
}
