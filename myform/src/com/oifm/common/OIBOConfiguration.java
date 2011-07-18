package com.oifm.common;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;

public class OIBOConfiguration extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConfiguration.class.getName());
    
    public OIResponseObject readConfiguration()
    {
        try
        {
            getConnection();
            ArrayList arOIBAConfiguration = new OIDAOConfiguration().read(connection);
            responseObject.addResponseObject("arOIBAConfiguration",arOIBAConfiguration);
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

    public OIResponseObject saveConfiguration(ArrayList arOIBAConfiguration)
    {
        try
        {
            getConnection();
            boolean flag = new OIDAOConfiguration().save(arOIBAConfiguration,connection);
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
    
    public OIResponseObject readMessage(String messageTag)
    {
        try
        {
            getConnection();
            OIBAConfiguration aOIBAConfiguration = new OIDAOConfiguration().read(messageTag,connection);
            responseObject.addResponseObject("aOIBAConfiguration",aOIBAConfiguration);
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
