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
 

public class ASMBVCategoryDetails implements Serializable  
{
	private Integer strCategoryID = null;
	private String strCategoryName = null;
	private Integer strLetterID = null;
	private String strLetterName = null;
	private String name;
	
	public Integer getStrLetterID() {
		return strLetterID;
	}
	public void setStrLetterID(Integer strLetterID) {
		this.strLetterID = strLetterID;
	}
	public String getStrLetterName() {
		return strLetterName;
	}
	public void setStrLetterName(String strLetterName) {
		this.strLetterName = strLetterName;
	}
	/**
	 * @return the strCategoryID
	 */
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the strCategoryName
	 */
	public String getStrCategoryName() {
		return strCategoryName;
	}
	/**
	 * @param strCategoryName the strCategoryName to set
	 */
	public void setStrCategoryName(String strCategoryName) {
		this.strCategoryName = strCategoryName;
	}
	/**
	 * @return the strCategoryID
	 */
	public Integer getStrCategoryID() {
		return strCategoryID;
	}
	/**
	 * @param strCategoryID the strCategoryID to set
	 */
	public void setStrCategoryID(Integer strCategoryID) {
		this.strCategoryID = strCategoryID;
	}
	
	
}