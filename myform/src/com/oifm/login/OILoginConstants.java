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

public interface OILoginConstants 
{
    //Query variables
    public static final String	Q_afunctionName = "afunctionName";
    public static final String	Q_afunctionId = "afunctionId";
    public static final String	Q_aroleId = "aroleId";
    public static final String	Q_aroleName = "aroleName";
    public static final String	Q_aUserCount = "aUserCount";
    public static final String  Q_divisionCode = "divisionCode";
    
    
    //Keys
    public static final String	K_arOIBVRole = "arOIBVRole";
    public static final String  K_MESSAGE = "message";
    public static final String  K_ERROR = "error";
    public static final String  K_CATEGORY = "category";
     
    //Struts Mappings
    public static final String FORWARD_SUCCESS = "success";
    public static final String ERRORPAGE = "/error.do";
    public static final String PAGENAME = "pageName";
    
    //Session Keys
    public static final String USER_ID = "userid";
    public static final String USER_NAME = "username";
    public static final String ROLE_ID = "roleID";
    public static final String ROLE_NAME = "roleName";
    public static final String FUNCTION_LIST = "functionList";
    public static final String DIVCODE = "divCode";
    public static final String ROLE = "role";
    public static final String USER = "user";
    public static final String TOPFLAG ="TopFlag"; 
    public static final String EMAIL ="email";
    public static final String NICKNAME ="nickname";
    public static final String CATEGORY_LIST ="categoryList";
    
    // added by K.K.Kumaresan on Jan 20,2009
    public static final String EMAIL_USER ="emailUser";

}
