package com.oifm.forum.admin;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIDBRegistry;

public class OIBOForumTopic extends OIBaseBo
{
    Logger logger = Logger.getLogger(OIBOForumTopic.class.getName());
    
    public OIResponseObject readTopic(int pBoardId,int pCategoryId,int pTopicId,String pUserId)
    {
        try
        {
            getConnection();
            if (pCategoryId!=0)
            {
                ArrayList arOIBAForumBoard = new OIDAOForumBoard().readAll(pUserId,pCategoryId,connection);
                responseObject.addResponseObject("arOIBAForumBoard",arOIBAForumBoard);
            }
            if (pTopicId!=0)
            {
                OIBAForumTopic aOIBAForumTopic = new OIDAOForumTopic().read(pTopicId,connection);
                OIBAForumBoard aOIBAForumBoard = null;
                if (pCategoryId==0)
                {
                    aOIBAForumBoard = new OIDAOForumBoard().read(aOIBAForumTopic.getBoardId(),connection);
                    responseObject.addResponseObject("aOIBAForumBoard",aOIBAForumBoard);
                }
                responseObject.addResponseObject("aOIBAForumTopic",aOIBAForumTopic);
                if (pCategoryId==0)
                    pCategoryId=aOIBAForumBoard.getCategoryId();
            }
            ArrayList arOIBAForumCategory = new OIDAOForumCategory().readAll(connection);
            responseObject.addResponseObject("arOIBAForumCategory",arOIBAForumCategory);
            
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
    public OIResponseObject saveTopic(OIBAForumTopic pOIBAForumTopic)
    {
        try
        {
            getConnection();
            connection.setAutoCommit(false);
            OIDAOForumTopic aOIDAOForumTopic = new OIDAOForumTopic();
            if (aOIDAOForumTopic.checkDuplicateTopic(pOIBAForumTopic.getTopicName(),pOIBAForumTopic.getTopicId(),pOIBAForumTopic.getBoardId(),connection))
            {
                boolean flag=aOIDAOForumTopic.save(pOIBAForumTopic,connection);
            }
            else
            {
                error = OIDBRegistry.getValues("OI.FORUM.DUPLICATE.TOPIC");
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
