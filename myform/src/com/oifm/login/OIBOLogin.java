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

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIDAOConfiguration;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIEncrypter;

public class OIBOLogin extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOLogin.class.getName());
    public OIResponseObject readRoles(String userId,String password)
    {
        ArrayList arOIBVRole=null;
        try
        {
            OIDAOLogin aOIDAOLogin = new OIDAOLogin();
            String userName=null;
            String pwd = null;
            if (password!=null)
                pwd = OIEncrypter.encrypt(password);
            logger.info(userId);
            logger.info(pwd);
            String strRoleId = null;
            getConnection();
            
            if (userId != null && !userId.equals("") && (userId.length() <= 9))
            {
	            boolean flag = aOIDAOLogin.checkOIFMUser(userId,connection);
	            if (flag)
	            {
		            arOIBVRole = aOIDAOLogin.readRoles(userId,connection);
		            if (arOIBVRole==null)
		            {
		                userName = aOIDAOLogin.readUserName(userId,connection);
		                responseObject.addResponseObject("userName",userName);
		            }
		            
		            /** This DAO method Checks for the ADMIN Role **/
	                strRoleId = aOIDAOLogin.getRoleId(connection,userId,OILoginSqls.QRY_CHK_ADMIN);
	                if(strRoleId == null){
	                	/** This DAO method Checks for the DIVADMIN Role **/
	                	strRoleId = aOIDAOLogin.getRoleId(connection,userId,OILoginSqls.QRY_CHK_DIVADMIN);
	                }if(strRoleId == null){
	                	/** This DAO method Checks for the MODERATOR Role **/
	                	strRoleId = aOIDAOLogin.getRoleId(connection,userId,OILoginSqls.QRY_CHK_MODERATOR);
	                }
	                if(strRoleId ==  null){
	                	/** When Roleid is null the login user is end user**/
	                	responseObject.addResponseObject(OILoginConstants.ROLE,OILoginConstants.USER);
	                }else{
	                	responseObject.addResponseObject(OILoginConstants.ROLE,strRoleId);
	                }
	                OIDAOConfiguration aOIDAOConfiguration = new OIDAOConfiguration();
	                OIBAConfiguration aOIBAConfigurationTimeOut = aOIDAOConfiguration.read("SESSIONTIMEOUTWARN",connection);
	                OIBAConfiguration aOIBAConfigurationAttachFile = aOIDAOConfiguration.read("ATTACHMENTSIZE",connection);
		            responseObject.addResponseObject("aOIBAConfigurationTimeOut",aOIBAConfigurationTimeOut);
		            responseObject.addResponseObject("aOIBAConfigurationAttachFile",aOIBAConfigurationAttachFile);
		            responseObject.addResponseObject(OILoginConstants.K_arOIBVRole,arOIBVRole);
	            }
	            else
	            {
	                addError("User not Registered in My Forum");
	            }
            }
            else
            {
                String surveyTag = aOIDAOLogin.checkTempUser(userId,pwd,connection);
                logger.info(surveyTag);
                if (surveyTag==null)
                {
                    addError("Inavlid user name / password");
                }
                else
                {
	                OIDAOConfiguration aOIDAOConfiguration = new OIDAOConfiguration();
	                OIBAConfiguration aOIBAConfigurationTimeOut = aOIDAOConfiguration.read("SESSIONTIMEOUTWARN",connection);
	                OIBAConfiguration aOIBAConfigurationAttachFile = aOIDAOConfiguration.read("ATTACHMENTSIZE",connection);
		            responseObject.addResponseObject("aOIBAConfigurationTimeOut",aOIBAConfigurationTimeOut);
		            responseObject.addResponseObject("aOIBAConfigurationAttachFile",aOIBAConfigurationAttachFile);
                    responseObject.addResponseObject("surveyTag",surveyTag);
                }
            }
        }
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
			logger.error(se);
		} 
         catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        responseObject.addResponseObject(OILoginConstants.K_ERROR,getError());
        responseObject.addResponseObject(OILoginConstants.K_MESSAGE,getMessageList());
        return responseObject;
    }
}
