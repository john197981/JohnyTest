package com.oifm.useradmin;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOMemberProfile extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOMemberProfile.class.getName());
    
    public OIResponseObject readMemberProfile(String pUserId)
    {
        try
        {
            getConnection();
            OIBAProfile aOIBAProfile = new OIDAOMemberProfile().readMemberProfile(pUserId,connection);
            responseObject.addResponseObject("aOIBAProfile",aOIBAProfile);
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
        addToResponseObject();
        return responseObject;
    }
}
