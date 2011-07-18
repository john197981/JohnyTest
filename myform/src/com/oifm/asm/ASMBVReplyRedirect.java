/*********************************ASMBVReplyRedirect.java******************* 
 * Title 		: ASMBVReplyRedirect
 * Description 	: This class is the VO Class for ASM Right side portal. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
import java.io.Serializable;
 

public class ASMBVReplyRedirect implements Serializable  
{
	private String strStatus = null;
	private String strLetterID = null;
	private String strTopic = null;
	private String strSubmBy = null;
	private String strSubmOn = null;
	private String strDivInCharge = null;
	private String strRedtOn = null;
	private String strReplOn = null;
	private String strPublOn = null;
	private String strCategoryName = null;
	
	public String getHidCategoryName()
	{
		return strCategoryName;
	}
	/**
	 * @param strCategoryName the strCategoryName to set
	 */
	public void setHidCategoryName(String strCategoryName)
	{
		this.strCategoryName = strCategoryName;
	}
	/**
	 * @return Returns the strDivInCharge.
	 */
	public String getHidDivInCharge() {
		return strDivInCharge;
	}
	/**
	 * @param strDivInCharge The strDivInCharge to set.
	 */
	public void setHidDivInCharge(String strDivInCharge) {
		this.strDivInCharge = strDivInCharge;
	}
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
	 * @return Returns the strPublOn.
	 */
	public String getHidPublOn() {
		return strPublOn;
	}
	/**
	 * @param strPublOn The strPublOn to set.
	 */
	public void setHidPublOn(String strPublOn) {
		this.strPublOn = strPublOn;
	}
	/**
	 * @return Returns the strRedtOn.
	 */
	public String getHidRedtOn() {
		return strRedtOn;
	}
	/**
	 * @param strRedtOn The strRedtOn to set.
	 */
	public void setHidRedtOn(String strRedtOn) {
		this.strRedtOn = strRedtOn;
	}
	/**
	 * @return Returns the strReplOn.
	 */
	public String getHidReplOn() {
		return strReplOn;
	}
	/**
	 * @param strReplOn The strReplOn to set.
	 */
	public void setHidReplOn(String strReplOn) {
		this.strReplOn = strReplOn;
	}
	/**
	 * @return Returns the strStatus.
	 */
	public String getHidStatus() {
		return strStatus;
	}
	/**
	 * @param strStatus The strStatus to set.
	 */
	public void setHidStatus(String strStatus) {
		this.strStatus = strStatus;
	}
	/**
	 * @return Returns the strSubmBy.
	 */
	public String getHidSubmBy() {
		return strSubmBy;
	}
	/**
	 * @param strSubmBy The strSubmBy to set.
	 */
	public void setHidSubmBy(String strSubmBy) {
		this.strSubmBy = strSubmBy;
	}
	/**
	 * @return Returns the strSubmOn.
	 */
	public String getHidSubmOn() {
		return strSubmOn;
	}
	/**
	 * @param strSubmOn The strSubmOn to set.
	 */
	public void setHidSubmOn(String strSubmOn) {
		this.strSubmOn = strSubmOn;
	}
	/**
	 * @return Returns the strTopic.
	 */
	public String getHidTopic() {
		return strTopic;
	}
	/**
	 * @param strTopic The strTopic to set.
	 */
	public void setHidTopic(String strTopic) {
		this.strTopic = strTopic;
	}
}
