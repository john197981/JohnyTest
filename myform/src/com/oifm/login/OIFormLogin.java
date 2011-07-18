package com.oifm.login;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			28/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import com.oifm.base.OIBaseActionForm;

public class OIFormLogin extends OIBaseActionForm 
{
    private String userid;
    private String password;
    
    /**
     * @return Returns the userid.
     */
    public String getUserid() {
        return userid;
    }
    /**
     * @param userid The userid to set.
     */
    public void setUserid(String userid) {
        this.userid = userid;
    }
    /**
     * @return Returns the userPassword.
     */
    public String getPassword() {
        return password;
    }
    /**
     * @param userPassword The userPassword to set.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
