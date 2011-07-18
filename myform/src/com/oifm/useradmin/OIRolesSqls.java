/*
 * Roles module SQL Constants
 * 
 * File name	= OIRolesSqls.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 4, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */
package com.oifm.useradmin;

public class OIRolesSqls {
    
    // SQL query to retrieve list of roles and number of users
    public static final String QRY_LIST = "SELECT OI_AD_ROLE.ROLEID, OI_AD_ROLE.NAME, COUNT(OI_AD_PROFILE.ROLEID) AS NUM_OF_USERS FROM OI_AD_ROLE LEFT JOIN OI_AD_PROFILE ON OI_AD_ROLE.ROLEID = OI_AD_PROFILE.ROLEID GROUP BY OI_AD_PROFILE.ROLEID, OI_AD_ROLE.NAME, OI_AD_ROLE.ROLEID ORDER BY NUM_OF_USERS ASC";
    
    // SQL query to add new role
    public static final String QRY_ADD_ROLE = "INSERT INTO OI_AD_ROLE(ROLEID, NAME, CREATED_ON, CREATED_BY) VALUES (?, ?, SYSDATE, ?)";
    
    // SQL query to modify role
    public static final String QRY_MODIFY_ROLE = "UPDATE OI_AD_ROLE SET NAME = ?, DEFAULT_ROLE = ? WHERE ROLEID = ?";
    
    // SQL query to retrieve role details
    public static final String QRY_RETRIEVE_ROLE = "SELECT * FROM OI_AD_ROLE WHERE ROLEID = ?";
    
    // SQL query to remove role
    public static final String QRY_DELETE_ROLE = "DELETE FROM OI_AD_ROLE WHERE ROLEID = ?";
    
    // SQL query to retrieve list of functions
    public static final String QRY_GET_ROLE_FUNCTIONS = "SELECT OI_AD_FUNCTIONS.FUNCTIONID, OI_AD_FUNCTIONS.NAME FROM OI_AD_FUNCTIONS, OI_AD_FUNCTIONROLE WHERE OI_AD_FUNCTIONS.FUNCTIONID = OI_AD_FUNCTIONROLE.FUNCTIONID AND OI_AD_FUNCTIONROLE.ROLEID = ?";
    
    // SQL query to assign a function to a role
    public static final String QRY_SET_FUNCTION = "INSERT INTO OI_AD_FUNCTIONROLE(FUNCTIONROLEID, FUNCTIONID, ROLEID) VALUES (SEQ_OI_AD_FUNCTIONROLEID.NEXTVAL, ?, ?)";
    
    // SQL query to remove all assigned functions for a role
    public static final String QRY_DELETE_FUNCTIONS = "DELETE FROM OI_AD_FUNCTIONROLE WHERE ROLEID = ?";
    
    // SQL query to disable default flag for all roles
    public static final String QRY_REMOVE_DEFAULT = "UPDATE OI_AD_ROLE SET DEFAULT_ROLE = '' WHERE DEFAULT_ROLE = 'Y'";
    
    // SQL query to set the default flag for a role
    public static final String QRY_SET_DEFAULT = "UPDATE OI_AD_ROLE SET DEFAULT_ROLE = 'Y' WHERE ROLEID = ?";
    
    // SQL query to check the number of assigned users of a role
    public static final String QRY_CHECK_NUM_OF_USERS = "SELECT OI_AD_PROFILE.ROLEID, COUNT(OI_AD_PROFILE.ROLEID) AS NUM_OF_USERS FROM OI_AD_ROLE LEFT JOIN OI_AD_PROFILE ON OI_AD_ROLE.ROLEID = OI_AD_PROFILE.ROLEID WHERE OI_AD_ROLE.ROLEID = ? GROUP BY OI_AD_PROFILE.ROLEID";
    
    // SQL query to retrieve the list of available functions in the system
    public static final String QRY_FUNCTIONS_LIST = "SELECT FUNCTIONID, NAME FROM OI_AD_FUNCTIONS";
}
