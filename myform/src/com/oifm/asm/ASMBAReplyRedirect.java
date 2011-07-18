/*********************************ASMBAReplyRedirect.java******************* 
 * Title 		: ASMBAReplyRedirect
 * Description 	: This class is the BA Class for ASM Previous Replies. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 20 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
 

public class ASMBAReplyRedirect extends ASMBVPrevRep   
{
	private ASMBVReplyRedirect objBV[] = null;
	private int iTotRecLtr = 0;
	private String strSortBy =null;
	
	private int iLink =0;
	private String strHiddenAction = null;
	
	/**
	 * @return Returns the objBV.
	 */
	public ASMBVReplyRedirect[] getObjBV() {
		return objBV;
	}
	/**
	 * @param objBV The objBV to set.
	 */
	public void setObjBV(ASMBVReplyRedirect[] objBV) {
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
	 * @return Returns the strHiddenAction.
	 */
	public String getHiddenAction() {
		return strHiddenAction;
	}
	/**
	 * @param strHiddenAction The strHiddenAction to set.
	 */
	public void setHiddenAction(String strHiddenAction) {
		this.strHiddenAction = strHiddenAction;
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
