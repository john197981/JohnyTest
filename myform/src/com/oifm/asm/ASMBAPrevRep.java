/*********************************ASMBAPrevRep.java******************* 
 * Title 		: ASMBAPrevRep
 * Description 	: This class is the BA Class for ASM Previous Replies. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 19 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
 

public class ASMBAPrevRep extends ASMBVPrevRep   
{
	private ASMBVPrevRep objBV[] = null;
	private int iMonths =0;
	private int iTotRecLtr = 0;
	private String strPageDesc =null;
	private String strModuleDesc =null;
	
	private int iLink =0;
	
	/**
	 * @return Returns the objBV.
	 */
	public ASMBVPrevRep[] getObjBV() {
		return objBV;
	}
	/**
	 * @param objBV The objBV to set.
	 */
	public void setObjBV(ASMBVPrevRep[] objBV) {
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
	 * @return Returns the iMonths.
	 */
	public int getHidMonths() {
		return iMonths;
	}
	/**
	 * @param months The iMonths to set.
	 */
	public void setHidMonths(int months) {
		iMonths = months;
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
		
	/**
	 * @return Returns the strModuleDesc.
	 */
	public String getModuleDesc() {
		return strModuleDesc;
	}
	/**
	 * @param strModuleDesc The strModuleDesc to set.
	 */
	public void setModuleDesc(String strModuleDesc) {
		this.strModuleDesc = strModuleDesc;
	}
}
