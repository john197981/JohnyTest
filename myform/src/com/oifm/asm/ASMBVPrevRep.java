/*********************************ASMBVPrevRep.java******************* 
 * Title 		: ASMBVPrevRep
 * Description 	: This class is the VO Class for ASM Previous REplies. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 19 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
import java.io.Serializable;
 

public class ASMBVPrevRep implements Serializable  
{
	
	private String strLetterTopic = null;
	private String strSubmittedBy = null;
	private String strSubmittedOn = null;	
	private String strPublishOn = null;
	private String strLetterID = null;
	private String strHiddenAction = null;
	

	/**
	 * @return Returns the strLetterTopic.
	 */
	public String getHidLetterTopic() {
		return strLetterTopic;
	}
	/**
	 * @param strLetterTopic The strLetterTopic to set.
	 */
	public void setHidLetterTopic(String strLetterTopic) {
		this.strLetterTopic = strLetterTopic;
	}
	/**
	 * @return Returns the strPublishOn.
	 */
	public String getHidPublishOn() {
		return strPublishOn;
	}
	/**
	 * @param strPublishOn The strPublishOn to set.
	 */
	public void setHidPublishOn(String strPublishOn) {
		this.strPublishOn = strPublishOn;
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
	 * @return Returns the strPageDesc.
	 */

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
	 * @return Returns the strSubmittedBy.
	 */
	public String getHidSubmittedBy() {
		return strSubmittedBy;
	}
	/**
	 * @param strSubmittedBy The strSubmittedBy to set.
	 */
	public void setHidSubmittedBy(String strSubmittedBy) {
		this.strSubmittedBy = strSubmittedBy;
	}
	/**
	 * @return Returns the strSubmittedOn.
	 */
	public String getHidSubmittedOn() {
		return strSubmittedOn;
	}
	/**
	 * @param strSubmittedOn The strSubmittedOn to set.
	 */
	public void setHidSubmittedOn(String strSubmittedOn) {
		this.strSubmittedOn = strSubmittedOn;
	}
}
