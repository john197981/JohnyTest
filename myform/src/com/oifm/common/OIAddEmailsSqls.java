/*
 * File name	= OIAddEmailsSqls.java
 * Package		= com.oifm.common
 * Created on 	= Aug 22, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;


public class OIAddEmailsSqls {
	// SQL query to verify email id
	public static final String QRY_CHECK_EMAIL = "SELECT EMAILID, USERID, NAME FROM OI_AD_PROFILE WHERE UPPER(EMAILID) LIKE UPPER(?)";
	
	// SQL query to check whether a user is already member of the group
	public static final String QRY_CHECK_MEMBER = "SELECT * FROM OI_FM_GROUPMEMBERS WHERE GROUPID = ? AND USERID = ?";
	
	// SQL query to add user as group member
	public static final String QRY_INSERT_MEMBER ="INSERT INTO OI_FM_GROUPMEMBERS(GROUPMEMBERID, GROUPID, USERID) VALUES (SEQ_OI_FM_GROUPMEMBERID.NEXTVAL, ?, ?)";
}
