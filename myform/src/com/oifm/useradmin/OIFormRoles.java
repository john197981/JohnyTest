/*
 * Roles module Action Form class
 * 
 * File name	= OIFormRoles.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 4, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */
package com.oifm.useradmin;

import com.oifm.base.OIBaseActionForm;

public class OIFormRoles extends OIBaseActionForm {
    private String strRoleID;
    private String strRoleName;
    private String[] strFunctions;
    private boolean boolDefault;
    private int intNumOfUsers;
    
    public OIFormRoles() {
        super();
    }
    
    /**
     * @return Returns the strRoleID.
     */
    public String getStrRoleID() {
        return strRoleID;
    }
    /**
     * @param strRoleID The strRoleID to set.
     */
    public void setStrRoleID(String strRoleID) {
        this.strRoleID = strRoleID;
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
