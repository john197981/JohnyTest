package com.oifm.utility;

import java.util.Locale;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

import sg.gov.moe.regservice.RegistryService;
import sg.gov.moe.util.RegistryException;

public class OIDBRegistry 
{
    private static Logger logger = Logger.getLogger(OIDBRegistry.class);
    public static String keyCodeUser;
    public static String keyCodePwd;
    public static String level1Code;
    public static String level2Code;
    public static String dataSourceName;
    public static String dbUserName;
    public static String appName;
    public static String appCode;
    public static String dbPwd;

    public static void setValues() throws Exception
    {
        try
        {
			Locale currentLocale = Locale.getDefault();
			ResourceBundle myResources = ResourceBundle.getBundle("OIProperties", currentLocale);
	        RegistryService regService = RegistryService.getInstance();
			keyCodeUser = myResources.getString("OI.OIKeyCode.User");
			keyCodePwd = myResources.getString("OI.OIKeyCode.Pwd");
			level1Code = myResources.getString("OI.OILevel1Code");
			//level2Code = myResources.getString("OI.OILevel2Code");
			dataSourceName = myResources.getString("dataSourceName");
			//logger.info(OIDBRegistry.dataSourceName);
			appName = myResources.getString("appName");
			appCode = myResources.getString("appCode");
			//logger.info("before initialize");
			regService.initialize(appName,appCode);
			//logger.info("after initialize");
			
			dbUserName =regService.getRegistryValue(keyCodeUser,level1Code);
			logger.info("before retrieve pass");
			dbPwd =regService.getRegistryValue(keyCodePwd,level1Code); 
			//logger.info("dbUserName - " + dbUserName);
			//logger.info("dbPwd - " + dbPwd);
        }
        catch (RegistryException re) {
        	logger.error("Registry access error - " + re);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
    }
    
    public static String getValues(String pKey) throws Exception
    {
        String value=null;
        try
        {
			Locale currentLocale = Locale.getDefault();
			ResourceBundle myResources = ResourceBundle.getBundle("OIProperties", currentLocale);
			value = myResources.getString(pKey);
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
            throw new Exception(e.getMessage());
        }
        return value;
    }
}
