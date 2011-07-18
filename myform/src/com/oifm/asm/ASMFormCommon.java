/*********************************ASMFormCommon.java******************* 
 * Title 		: ASMFormCommon
 * Description 	: This class is the Form Class for ASM Common modules 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 20 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import com.oifm.base.OIBaseActionForm;

public class ASMFormCommon extends OIBaseActionForm {
	private ASMBVCommon objBV[] = null;
	private int iTotRecLtr = 0;
	
	private int iLink =0;
	private String strDivisionCode = null;
	private String strEmailID = null;
	private String strRecLtrID =null;
	
	// This variables is used for User Detail screen
	private String strSelect = null;
	private boolean chkSelectAll =false;
	
	/**
	 * @return Returns the objBV.
	 */
	public ASMBVCommon[] getObjBV() {
		return objBV;
	}
	/**
	 * @param objBV The objBV to set.
	 */
	public void setObjBV(ASMBVCommon[] objBV) {
		this.objBV = objBV;
	}
	/**
	 * @return Returns the iTotRecLtr.
	 */
	public int getTotRecLtr() {
		return iTotRecLtr;
	}
	/**
	 * @param totRecLtr The iTotRecLtr to set.
	 */
	public void setTotRecLtr(int totRecLtr) {
		iTotRecLtr = totRecLtr;
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
	 * @return Returns the strDivisionCode.
	 */
	public String getCboDivision() {
		return strDivisionCode;
	}
	/**
	 * @param strDivisionCode The strDivisionCode to set.
	 */
	public void setCboDivision(String strDivisionCode) {
		this.strDivisionCode = strDivisionCode;
	}
	
	/**
	 * @return Returns the strSelect.
	 */
	public String getChkSelect() {
		return strSelect;
	}
	/**
	 * @param strSelect The strSelect to set.
	 */
	public void setChkSelect(String strSelect) {
		this.strSelect = strSelect;
	}
	/**
	 * @return Returns the strEmailID.
	 */
	public String getHidEmailID() {
		return strEmailID;
	}
	/**
	 * @param strEmailID The strEmailID to set.
	 */
	public void setHidEmailID(String strEmailID) {
		this.strEmailID = strEmailID;
	}
	/**
	 * @return Returns the strRecLtrID.
	 */
	public String getHidRecLtrID() {
		return strRecLtrID;
	}
	/**
	 * @param strRecLtrID The strRecLtrID to set.
	 */
	public void setHidRecLtrID(String strRecLtrID) {
		this.strRecLtrID = strRecLtrID;
	}
	/**
	 * @return Returns the chkSelectAll.
	 */
	public boolean getChkSelectAll() {
		return chkSelectAll;
	}
	/**
	 * @param chkSelectAll The chkSelectAll to set.
	 */
	public void setChkSelectAll(boolean chkSelectAll) {
		this.chkSelectAll = chkSelectAll;
	}
}
