/*
 * File name	= OIGroupMembersBean.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 11, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;


public class OIGroupMembersBean {
	
	private String userID;
	private String name;
	private String division;
	private String emailID;
	private Integer numOfPostings;
	
	public OIGroupMembersBean(){}

	/**
	 * @return Returns the division.
	 */
	public String getDivision() {
		return division;
	}
	/**
	 * @param division The division to set.
	 */
	public void setDivision(String division) {
		this.division = division;
	}
	/**
	 * @return Returns the emailID.
	 */
	public String getEmailID() {
		return emailID;
	}
	/**
	 * @param emailID The emailID to set.
	 */
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	/**
	 * @return Returns the name.
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return Returns the numOfPostings.
	 */
	public Integer getNumOfPostings() {
		return numOfPostings;
	}
	/**
	 * @param numOfPostings The numOfPostings to set.
	 */
	public void setNumOfPostings(Integer numOfPostings) {
		this.numOfPostings = numOfPostings;
	}
	/**
	 * @return Returns the userID.
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID The userID to set.
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
}
