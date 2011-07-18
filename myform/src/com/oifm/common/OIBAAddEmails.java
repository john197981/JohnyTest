/*
 * File name	= OIBAAddEmails.java
 * Package		= com.oifm.common
 * Created on 	= Aug 22, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import java.util.ArrayList;
import java.util.StringTokenizer;

import com.oifm.base.OIBaseBa;


public class OIBAAddEmails extends OIBaseBa {
	private String strGroupID;
	private String strEmails;
	private ArrayList alEmails;
	private String[] arMembers;
	
	private String strEmailID;
	private String strUserID;
	private String strName;
	private boolean alreadyMember;
	
	/**
	 * @return Returns the alEmails.
	 */
	public ArrayList getAlEmails() {
		return alEmails;
	}
	/**
	 * @param alEmails The alEmails to set.
	 */
	public void setAlEmails(ArrayList alEmails) {
		this.alEmails = alEmails;
	}
	/**
	 * @return Returns the strEmails.
	 */
	public String getStrEmails() {
		return strEmails;
	}
	/**
	 * @param strEmails The strEmails to set.
	 */
	public void setStrEmails(String strEmails) {
		this.strEmails = strEmails;
		this.setAlEmails(this.tokenizeEmails(this.strEmails));
	}
	
	private ArrayList tokenizeEmails(String strEmails){
		ArrayList alTokens = new ArrayList();
		StringTokenizer tokenizer = new StringTokenizer(strEmails, "\t\n\r\f,;");
		
		while (tokenizer.hasMoreTokens()) {
			String temp = tokenizer.nextToken().toLowerCase().trim();
			if (!alTokens.contains(temp))
				alTokens.add(temp);
		}
		return alTokens;
	}
	/**
	 * @return Returns the strEmailID.
	 */
	public String getStrEmailID() {
		return strEmailID;
	}
	/**
	 * @param strEmailID The strEmailID to set.
	 */
	public void setStrEmailID(String strEmailID) {
		this.strEmailID = strEmailID;
	}
	/**
	 * @return Returns the strUserID.
	 */
	public String getStrUserID() {
		return strUserID;
	}
	/**
	 * @param strUserID The strUserID to set.
	 */
	public void setStrUserID(String strUserID) {
		this.strUserID = strUserID;
	}
	/**
	 * @return Returns the strName.
	 */
	public String getStrName() {
		return strName;
	}
	/**
	 * @param strName The strName to set.
	 */
	public void setStrName(String strName) {
		this.strName = strName;
	}
	public String[] getArMembers() {
		return arMembers;
	}
	/**
	 * @param arMembers The arMembers to set.
	 */
	public void setArMembers(String[] arMembers) {
		this.arMembers = arMembers;
	}
	/**
	 * @return Returns the strGroupID.
	 */
	public String getStrGroupID() {
		return strGroupID;
	}
	/**
	 * @param strGroupID The strGroupID to set.
	 */
	public void setStrGroupID(String strGroupID) {
		this.strGroupID = strGroupID;
	}
	/**
	 * @return Returns the alreadyMember.
	 */
	public boolean isAlreadyMember() {
		return alreadyMember;
	}
	/**
	 * @param alreadyMember The alreadyMember to set.
	 */
	public void setAlreadyMember(boolean alreadyMember) {
		this.alreadyMember = alreadyMember;
	}
}
