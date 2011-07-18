/*********************************ASMBACommon.java******************* 
 * Title 		: ASMBACommon
 * Description 	: This class is the BA Class for ASM Right side portal. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
 

public class ASMBACommon extends ASMBVCommon   
{
	private ASMBVCommon objBV[] = null;
	private String strEditorsNoteID = null;
	private String strEditorsNote = null;
	private String strEditorsNotePostedOn = null;
	private int iTotRecLtr = 0;
	
	private int iLink =0;
	private String strDivisionCode = null;
	

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
	 * @return Returns the strEditorsNote.
	 */
	public String getHidEditorsNote() {
		return strEditorsNote;
	}
	/**
	 * @param strEditorsNote The strEditorsNote to set.
	 */
	public void setHidEditorsNote(String strEditorsNote) {
		this.strEditorsNote = strEditorsNote;
	}
	/**
	 * @return Returns the strEditorsNotePostedOn.
	 */
	public String getHidEditorsNotePostedOn() {
		return strEditorsNotePostedOn;
	}
	/**
	 * @param strEditorsNotePostedOn The strEditorsNotePostedOn to set.
	 */
	public void setHidEditorsNotePostedOn(String strEditorsNotePostedOn) {
		this.strEditorsNotePostedOn = strEditorsNotePostedOn;
	}

	/**
	 * @return Returns the strEditorsNoteID.
	 */
	public String getHidEditorsNoteID() {
		return strEditorsNoteID;
	}
	/**
	 * @param strEditorsNoteID The strEditorsNoteID to set.
	 */
	public void setHidEditorsNoteID(String strEditorsNoteID) {
		this.strEditorsNoteID = strEditorsNoteID;
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
}
