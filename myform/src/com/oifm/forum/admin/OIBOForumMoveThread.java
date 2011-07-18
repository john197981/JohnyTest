package com.oifm.forum.admin;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIDBRegistry;

public class OIBOForumMoveThread extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOForumMoveThread.class.getName());
    
    public OIResponseObject readCategory()
    {
        try
        {
            getConnection();
           
            ArrayList arOIBAForumCategory = new OIDAOForumCategory().readAll(connection);
            responseObject.addResponseObject("arOIBAForumCategory",arOIBAForumCategory);

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
    
    public OIResponseObject readBoard(int pCategoryId, String pUserId)
    {
        try
        {
            getConnection();
            if (pCategoryId!=0)
            {
                ArrayList arOIBAForumBoard = new OIDAOForumBoard().readAll(pUserId,pCategoryId,connection);
                responseObject.addResponseObject("arOIBAForumBoard",arOIBAForumBoard);
            }
            
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
    
    public OIResponseObject readTopic(int pBoardId)
    {
        try
        {
            getConnection();
            if (pBoardId!=0)
            {
            	ArrayList arOIBAForumTopic = new OIDAOForumMoveThread().readTopic(pBoardId,connection);
                responseObject.addResponseObject("arOIBAForumTopic",arOIBAForumTopic);
            }
            
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
    
    public OIResponseObject readThread(int pTopicId)
    {
        try
        {
            getConnection();
            if (pTopicId!=0)
            {
            	ArrayList arOIBAForumThread = new OIDAOForumMoveThread().readThread(pTopicId,connection);
                responseObject.addResponseObject("arOIBAForumThread",arOIBAForumThread);
            }
            
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
    
    public OIResponseObject saveThread(OIBAForumMoveThread pOIBAMoveThread)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumMoveThread aOIDAOMoveThread = new OIDAOForumMoveThread();
            Boolean flag=new Boolean(aOIDAOMoveThread.saveThread(pOIBAMoveThread,connection));
            
            responseObject.addResponseObject("saveFlag",flag.toString());
            connection.commit();
            connection.setAutoCommit(true);
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

    public OIResponseObject deleteTopic(OIBAForumTopic pOIBAForumTopic)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            
            OIDAOForumTopic aOIDAOForumTopic = new OIDAOForumTopic();
            //Start for loop
            	// call method to delete the bookmarks
            	// call method to delete the posts
            	// call method to delete the threadgroups
            //end for loop
            ArrayList arOIBAThread = aOIDAOForumTopic.readThread(pOIBAForumTopic,connection);
            if (arOIBAThread!=null)
            {
	            for(int i=0;i<arOIBAThread.size();i++)
	            {
	                //Delete thread method has to be called here
	            }
            }
            
            if (aOIDAOForumTopic.checkTopic(pOIBAForumTopic.getTopicId(),connection)==0)
            {
                boolean flag = aOIDAOForumTopic.deleteTopic(pOIBAForumTopic,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.FORUM.TOPIC.CHECK");
                logger.info(error);
            }
            
            connection.commit();
            connection.setAutoCommit(true);
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
