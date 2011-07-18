/*
 * Roles module Business Attribute Object
 * 
 * File name	= OIBARoles.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 4, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */
package com.oifm.useradmin;

public class OIBARoles {
    
    private String strRoleID;
    private String strRoleName;
    private String[] strFunctions;
    private boolean boolDefault;
    private String strCreatedBy;
    private int intNumOfUsers;
    
    public OIBARoles() {}
    
    /**
     * @return Returns the strUserID.
     */
    public String getCreatedBy() {
        return strCreatedBy;
    }
    /**
     * @param strUserID The strUserID to set.
     */
    public void setCreatedBy(String strCreatedBy) {
        this.strCreatedBy = strCreatedBy;
    }	
    /**
     * @return Returns the strRoleID.
     */
    public String getStrRoleID() {
        return (strRoleID == null)?strRoleID:strRoleID.toUpperCase();
    }
    /**
     * @param strRoleID The strRoleID to set.
     */	
    public void setStrRoleID(String strRoleID) {
        this.strRoleID = strRoleID;
        if (strRoleID != null) strRoleID = strRoleID.toUpperCase();
    }
    /**
     * @return Returns the boolDefault.
     */
    public boolean isBoolDefault() {
        return boolDefault;
    }
    /**
     * @param boolDefault The boolDefault to set.
     */
    public void setBoolDefault(boolean boolDefault) {
        this.boolDefault = boolDefault;
    }
    /**
     * @return Returns the strFunctions.
     */
    public String[] getStrFunctions() {
        return strFunctions;
    }
    /**
     * @param strFunctions The strFunctions to set.
     */
    public void setStrFunctions(String[] strFunctions) {
        this.strFunctions = strFunctions;
    }
    /**
     * @return Returns the strRoleName.
     */
    public String getStrRoleName() {
        return strRoleName;
    }
    /**
     * @param strRoleName The strRoleName to set.
     */
    public void setStrRoleName(String strRoleName) {
        this.strRoleName = strRoleName;
    }
    /**
     * @return Returns the intNumOfUsers.
     */
    public int getIntNumOfUsers() {
        return intNumOfUsers;
    }
    /**
     * @param intNumOfUsers The intNumOfUsers to set.
     */
    public void setIntNumOfUsers(int intNumOfUsers) {
        this.intNumOfUsers = intNumOfUsers;
    }
}
