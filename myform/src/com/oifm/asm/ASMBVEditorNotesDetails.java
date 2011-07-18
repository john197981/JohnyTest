/*********************************ASMBVHomePage.java.java******************* 
 * Title 		: ASMBVHomePage
 * Description 	: This class is the VO Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
import java.io.Serializable;
 

public class ASMBVEditorNotesDetails implements Serializable  
{
	private Integer strContentID = null;
	private String strTitle = null;
	private String strNoteContent = null;
	/**
	 * @return the strContentID
	 */
	public Integer getStrContentID() {
		return strContentID;
	}
	/**
	 * @param strContentID the strContentID to set
	 */
	public void setStrContentID(Integer strContentID) {
		this.strContentID = strContentID;
	}
	/**
	 * @return the strNoteContent
	 */
	public String getStrNoteContent() {
		return strNoteContent;
	}
	/**
	 * @param strNoteContent the strNoteContent to set
	 */
	public void setStrNoteContent(String strNoteContent) {
		this.strNoteContent = strNoteContent;
	}
	/**
	 * @return the strTitle
	 */
	public String getStrTitle() {
		return strTitle;
	}
	/**
	 * @param strTitle the strTitle to set
	 */
	public void setStrTitle(String strTitle) {
		this.strTitle = strTitle;
	}
	
	
}