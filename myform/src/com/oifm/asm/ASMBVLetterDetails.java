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
 

public class ASMBVLetterDetails implements Serializable  
{
	private Integer intCategoryID = null;
	private Integer  intLetterid = null;
	private String strLetterContent = null;
	private String strLetterReply = null;
	private String strTopic = null;
	
	/**
	 * @return the intCategoryID
	 */
	public Integer getIntCategoryID() {
		return intCategoryID;
	}
	/**
	 * @param intCategoryID the intCategoryID to set
	 */
	public void setIntCategoryID(Integer intCategoryID) {
		this.intCategoryID = intCategoryID;
	}
	/**
	 * @return the intLetterid
	 */
	public Integer getIntLetterid() {
		return intLetterid;
	}
	/**
	 * @param intLetterid the intLetterid to set
	 */
	public void setIntLetterid(Integer intLetterid) {
		this.intLetterid = intLetterid;
	}
	/**
	 * @return the strLetterContent
	 */
	public String getStrLetterContent() {
		return strLetterContent;
	}
	/**
	 * @param strLetterContent the strLetterContent to set
	 */
	public void setStrLetterContent(String strLetterContent) {
		this.strLetterContent = strLetterContent;
	}
	/**
	 * @return the strLetterReply
	 */
	public String getStrLetterReply() {
		return strLetterReply;
	}
	/**
	 * @param strLetterReply the strLetterReply to set
	 */
	public void setStrLetterReply(String strLetterReply) {
		this.strLetterReply = strLetterReply;
	}
	public String getStrTopic() {
		return strTopic;
	}
	public void setStrTopic(String strTopic) {
		this.strTopic = strTopic;
	}
	
	
	
}