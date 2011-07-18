package com.oifm.forum;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;

public class OIBOThreadsSearch extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOForumWebListing.class.getName());
    
    public OIResponseObject readLists(String pUserId,int iHours)
    {
        try
        {
            getConnection();   
            ArrayList arOIBVUpToTopicListing = new OIDAOThreadsSearch().readPostListing(pUserId,connection,iHours);
            arOIBVUpToTopicListing = new OIDAOThreadsSearch().readUpToTopicListing(connection,arOIBVUpToTopicListing);
            responseObject.addResponseObject("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    public OIResponseObject readBMLists(String pUserId)
    {
        try
        {
        	int iHours = 1;
            getConnection();             
            ArrayList arOIBVUpToTopicListing = new OIDAOThreadsSearch().readPostListing(pUserId,connection,iHours);
            arOIBVUpToTopicListing = new OIDAOThreadsSearch().readUpToTopicListing(connection,arOIBVUpToTopicListing);
            responseObject.addResponseObject("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
        
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    public OIResponseObject readPOLists(String pUserId)
    {
        try
        {
        	int iHours = 1;
            getConnection();             
            ArrayList arOIBVUpToTopicListing = new OIDAOThreadsSearch().readPOPostListing(pUserId,connection);
            arOIBVUpToTopicListing = new OIDAOThreadsSearch().readUpToTopicListing(connection,arOIBVUpToTopicListing);
            responseObject.addResponseObject("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}