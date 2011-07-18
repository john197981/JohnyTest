/*
 * Group Maintenance module SQL query string constants
 * 
 * File name	= OIGroupsSqls.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 11, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import com.oifm.common.OILabelConstants;

public class OIGroupsSqls {

	// SQL query to retrieve the list of user groups
	public static final String QRY_GROUP_LIST = "SELECT g.groupid,g.NAME,g.description,g.created_by,p.NAME AS nickname,cm.description AS divname,TO_CHAR (g.created_on,'"+ OILabelConstants.DISPLAY_DATE_FORMAT +"') AS createdate,(SELECT COUNT(1) FROM OI_FM_GROUPMEMBERS D, OI_AD_PROFILE E WHERE g.GROUPID = D.GROUPID AND D.USERID = E.USERID AND (E.OBSOLETE IS NULL OR (E.OBSOLETE IS NOT NULL AND E.OBSOLETE NOT IN ('E','e')))) AS NUM_OF_USERS FROM OI_FM_GROUPS g, OI_AD_PROFILE p LEFT JOIN OI_AD_CODE_MASTER cm ON ( cm.TYPE = 'DIVISION_CODE' AND cm.VALUE =p.divisioncode) WHERE g.group_type = 'A' AND g.created_by = p.userid AND g.group_type = 'A' ORDER BY g.NAME ASC";
	
	// SQL query to retrieve details of a group
	public static final String QRY_GROUP_DETAILS = "SELECT * FROM OI_FM_GROUPS WHERE OI_FM_GROUPS.GROUPID = ?";
	
	// SQL query to retrieve list of group members
	public static final String QRY_GROUP_MEMBERS = "SELECT PR.USERID, PR.NAME, CM.DESCRIPTION DIVISION, PR.EMAILID, PR.TOTALPOSTINGS FROM OI_FM_GROUPMEMBERS GM, OI_AD_PROFILE PR LEFT JOIN OI_AD_CODE_MASTER CM ON (CM.TYPE = 'DIVISION_CODE' AND CM.VALUE = PR.DIVISIONCODE) WHERE GM.USERID = PR.USERID AND (PR.OBSOLETE NOT IN ('E','e') OR PR.OBSOLETE IS NULL) AND GM.GROUPID = ? ORDER BY ";
	
	// SQL query to remove link between thread and group
	public static final String QRY_REMOVE_THREAD_GROUP = "DELETE FROM OI_FM_THREAD_GROUPS WHERE GROUPID = ?";
	
	// SQL query to check whether a user is the owner of the group or belong to the same division as the owner
	public static final String QRY_CHECK_GROUP_OWNER = "SELECT * FROM OI_FM_GROUPS WHERE (GROUPID = ? AND CREATED_BY IN (SELECT USERID FROM OI_AD_PROFILE WHERE DIVISIONCODE IN (SELECT DIVISIONCODE FROM OI_AD_PROFILE WHERE USERID = ?))) OR ? IN (SELECT USERID FROM OI_AD_PROFILE WHERE ROLEID='ADMIN')";
	
	// SQL query to create new group
	public static final String QRY_CREATE_GROUP = "INSERT INTO OI_FM_GROUPS(GROUPID, NAME, DESCRIPTION, CREATED_ON, CREATED_BY,GROUP_TYPE) VALUES (SEQ_OI_FM_GROUPID.NEXTVAL, ?, ?, SYSDATE, ?,'"+ OIGroupsConstants.ADHOC_GROUP_TYPE+"')";
	
	// SQL query to edit a group
	public static final String QRY_EDIT_GROUP = "UPDATE OI_FM_GROUPS SET NAME = ?, DESCRIPTION = ? WHERE GROUPID = ?";
	
	// SQL query to delete a group
	public static final String QRY_DELETE_GROUP = "DELETE FROM OI_FM_GROUPS WHERE GROUPID = ?";
	
	// SQL query to remove a member from a group
	public static final String QRY_REMOVE_MEMBER = "DELETE FROM OI_FM_GROUPMEMBERS WHERE GROUPID = ? AND USERID = ?";
	
	// SQL query to remove all members from a group
	public static final String QRY_REMOVE_MEMBERS = "DELETE FROM OI_FM_GROUPMEMBERS WHERE GROUPID = ?";
	
	// SQL query to check for duplicate group name
	public static final String QRY_CHECK_DUPLICATE_NAME = "SELECT * FROM OI_FM_GROUPS WHERE GROUPID <> ? AND UPPER(NAME) = UPPER(?)";
	
	// SQL query to retrieve the GROUPID using Name
	public static final String QRY_GROUPID = "SELECT GROUPID FROM OI_FM_GROUPS WHERE NAME = ?";
	
//	 SQL query to retrieve the list of Fixed User groups
	public static final String QRY_FIXED_GROUP_LIST = "SELECT GROUPID, NAME, DESCRIPTION, CREATED_BY, TO_CHAR(CREATED_ON,'"+ OILabelConstants.DISPLAY_DATE_FORMAT +"') AS CREATEDATE, OI_GET_GROUPS.FN_GET_GROUPS_COUNT(GROUPID) MEMBERSCOUNT FROM OI_FM_GROUPS WHERE GROUP_TYPE = '"+ OIGroupsConstants.FIXED_GROUP_TYPE+"' ORDER BY NAME ASC";
	//"SELECT GROUPID, NAME, DESCRIPTION, CREATED_BY, TO_CHAR(CREATED_ON,'"+ OILabelConstants.DISPLAY_DATE_FORMAT +"') AS CREATEDATE FROM OI_FM_GROUPS WHERE GROUP_TYPE ='"+ OIGroupsConstants.FIXED_GROUP_TYPE+"' GROUP BY GROUPID, NAME, DESCRIPTION, CREATED_BY, CREATED_ON ORDER BY NAME ASC";
}
