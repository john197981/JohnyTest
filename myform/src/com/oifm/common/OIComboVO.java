/*
 * Created on Dec 27, 2004
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package com.oifm.common;

import java.io.Serializable;


public class OIComboVO implements Serializable {
	
	private  String strDDLabelId;
	private String strDDLabelDesc;
	
	public OIComboVO()
	{
		strDDLabelId=null;
		strDDLabelDesc=null;
	}
	
	public OIComboVO(String strDDLabelId,String strDDLabelDesc) {
		this.strDDLabelId=strDDLabelId;
		this.strDDLabelDesc=strDDLabelDesc;
	}
	
	public String getStrDDLabelId() {
		return this.strDDLabelId;
	}
	
	public String getStrDDLabelDesc() {
		return this.strDDLabelDesc;
	}
	
	public void setStrDDLabelId(String strDDLabelId) {
		this.strDDLabelId=strDDLabelId;
	}
	
	public void setStrDDLabelDesc(String strDDLabelDesc) {
		this.strDDLabelDesc=strDDLabelDesc;
	}

}

