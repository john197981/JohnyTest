package com.oifm.forum;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIDAOConfiguration;
import com.oifm.common.OIResponseObject;
import com.oifm.home.OIDAOHome;

public class OIBOForumWebListing extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOForumWebListing.class.getName());
    
    public OIResponseObject readLists(String pUserId,String strMode)
    {
        try
        {
            getConnection();
            OIBAConfiguration aOIBAConfiguration = new OIDAOConfiguration().readDt("FORUMANNOUNCEMENT","FORUMANNOUNCEFROM","FORUMANNOUNCETO",connection);
            responseObject.addResponseObject("aOIBAConfiguration",aOIBAConfiguration);
            ArrayList arOIBVHottestThread = new OIDAOForumWebListing().readHottestThread(pUserId,connection);
            //ArrayList arOIBVLatestThread = new OIDAOForumWebListing().readLatestThread(pUserId,connection);
            ArrayList arOIBVLatestThread = new OIDAOHome().readLatestThread(pUserId,connection);
            ArrayList arOIBVHQReplies = new OIDAOForumWebListing().readHQReplies(pUserId,connection); 
            ArrayList arOIBVUpToTopicListing = new OIDAOForumWebListing().readUpToTopicListing(connection);
            
            arOIBVUpToTopicListing = new OIDAOForumWebListing().readPostListing(pUserId,arOIBVUpToTopicListing,connection,strMode);
            
            OIDAOThread objThread = new OIDAOThread();
//          String strSplThreadID=objThread.getThreadID(connection);
            ArrayList alThreadList=objThread.getThreadIDAndName(connection);
            String strSplThreadID="";
            String strSplThreadName="";
            if(alThreadList!=null && alThreadList.size()>1){
            	strSplThreadID =alThreadList.get(0).toString();
            	strSplThreadName =alThreadList.get(1).toString();
            }
            
            responseObject.addResponseObject("strSplThreadName",strSplThreadName);
            responseObject.addResponseObject("strSplThreadID",strSplThreadID);
            responseObject.addResponseObject("arOIBVUpToTopicListing",arOIBVUpToTopicListing);
            responseObject.addResponseObject("arOIBVHQReplies",arOIBVHQReplies);
            responseObject.addResponseObject("arOIBVLatestThread",arOIBVLatestThread);
            responseObject.addResponseObject("arOIBVHottestThread",arOIBVHottestThread);
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
