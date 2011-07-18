/*********************************ASMBVCommon.java******************* 
 * Title 		: ASMBVCommon
 * Description 	: This class is the VO Class for ASM Right side portal. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;
import java.io.Serializable;
import java.util.ArrayList;
 

public class ASMBVCommon implements Serializable  
{
	//This is for homepage right side menu
	private String strRecLtrID = null;
	private String strRecLtrTopic = null;
	private String strRecLtrPubOn = null;
	//This is for Select User Popup
	private String strEmailID = null;
	private String strName = null;
	private ArrayList alDivision =null;
	

	/**
	 * @return Returns the strRecLtrPubOn.
	 */
	public String getHidRecLtrPubOn() {
		return strRecLtrPubOn;
	}
	/**
	 * @param strRecLtrPubOn The strRecLtrPubOn to set.
	 */
	public void setHidRecLtrPubOn(String strRecLtrPubOn) {
		this.strRecLtrPubOn = strRecLtrPubOn;
	}
	/**
	 * @return Returns the strRecLtrTopic.
	 */
	public String getHidRecLtrTopic() {
		return strRecLtrTopic;
	}
	/**
	 * @param strRecLtrTopic The strRecLtrTopic to set.
	 */
	public void setHidRecLtrTopic(String strRecLtrTopic) {
		this.strRecLtrTopic = strRecLtrTopic;
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
	 * @return Returns the strName.
	 */
	public String getHidName() {
		return strName;
	}
	/**
	 * @param strName The strName to set.
	 */
	public void setHidName(String strName) {
		this.strName = strName;
	}
	/**
	 * @return Returns the alDivision.
	 */
	public ArrayList getAlDivision() {
		return alDivision;
	}
	/**
	 * @param alDivision The alDivision to set.
	 */
	public void setAlDivision(ArrayList alDivision) {
		this.alDivision = alDivision;
	}
}
