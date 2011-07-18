/*****************************************************************************
* Title 		: OIBOHome.java
* Description 	: This is the Business Object for the construction of home page.   
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 01 - Aug -2005
* Copyright 	: Scandent Group
********************************************************************************/
package com.oifm.home;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import com.oifm.base.OIBaseBo;
import com.oifm.blog.OIBABlogConfig;
import com.oifm.blog.OIDAOBlog;
import com.oifm.blog.OIDAOBlogAdmin;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIDAOConfiguration;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIDAOForumWebListing;
import com.oifm.poll.OIBOPoll;
import com.oifm.poll.OIBVPoll;
import com.oifm.poll.OIDAOPoll;
 

public class OIBOHome extends OIBaseBo 
{
	/**	Create instance for logger and objects  used **/
	transient private final  Logger logger = Logger.getLogger(OIActionHome.class.getName());
	
	public OIBOHome()
	{
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	/**
	 * This is public method to delegate to various operations Create,modify,view,publish the poll information.
	 * @param objBVPoll
	 * @return
	 */
	 public OIResponseObject processHome(String pUserId,OIBVHome objBV )
	 {
		logger.debug(OILabelConstants.BEGIN_METHOD_BO + "processHome");
		try
		{
				/** Get the Poll Details **/
				responseObject = poll(objBV);
				getConnection();
				OIBAConfiguration aOIBAConfiguration = new OIDAOConfiguration().readDt("HOMEANNOUNCEMENT","HOMEANNOUNCEFROM","HOMEANNOUNCETO",connection);
				OIBAConfiguration aOISiteConfiguration = new OIDAOConfiguration().read("SITEDISCLAIMER",connection);
				OIBAConfiguration aHotTopic = new OIDAOConfiguration().read("HOTTOPIC",connection);
				OIBAConfiguration aLatTopic = new OIDAOConfiguration().read("LATESTTOPIC",connection);
	            //ArrayList arOIBVHottestThread = new OIDAOHome().readHottestThread(pUserId,connection);
				ArrayList arOIBVHottestThread = new OIDAOForumWebListing().readHottestThread(pUserId,connection);
	            ArrayList arOIBVLatestThread = new OIDAOHome().readLatestThread(pUserId,connection);
	            ArrayList arOIBVPaper = new OIDAOHome().readLatestPapers(pUserId,connection);
	            ArrayList arOIBASurvey = new OIDAOHome().readLatestSurvey(pUserId,connection);
	            ArrayList arASMLetter = new OIDAOHome().readASMLetters(pUserId,connection);
	            // Added to include Latest Blog display in home page
	            ArrayList arOIBlog = new OIDAOHome().readLatestBlogs(pUserId, connection);
	            
	            new OIDAOHome().getBannerCount(connection,objBV);
	            responseObject.addResponseObject("bannerCnt",""+objBV.getBannerCnt());
	            
	            new OIDAOHome().getTag(pUserId, objBV, connection);
	            responseObject.addResponseObject("tagWords",""+objBV.getStrTag());

	            responseObject.addResponseObject("arOIBASurvey",arOIBASurvey);
	            responseObject.addResponseObject("arOIBVPaper",arOIBVPaper);
	            responseObject.addResponseObject("aOIBAConfiguration",aOIBAConfiguration);
	            responseObject.addResponseObject("aOISiteConfiguration",aOISiteConfiguration);
	            
	            responseObject.addResponseObject("aHotTopic",aHotTopic);
	            responseObject.addResponseObject("aLatTopic",aLatTopic);
	            
	            responseObject.addResponseObject("arOIBVHottestThread",arOIBVHottestThread);
	            responseObject.addResponseObject("arOIBVLatestThread",arOIBVLatestThread);
	            responseObject.addResponseObject("arASMLetter",arASMLetter);
	            //Added to include Latest Blog display in home page
	            responseObject.addResponseObject("arOIBlog",arOIBlog);
		}
		catch (Exception ex)
		{
		    logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
    		responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
    	}
	 	finally
	 	{
			freeConnection();
	 	}
    	logger.debug(OILabelConstants.END_METHOD_BO + "processHome");
		return responseObject;
	 }	
	 
	 /**
	  * This is Poll Bo Method to Display the poll and publish the poll results also. 
	  * @param objBV
	  * @return
	  */
	 private OIResponseObject poll(OIBVHome objBV)
	 {
	 	logger.debug(OILabelConstants.BEGIN_METHOD_BO + "poll");
	 	ArrayList alResult = null;
		String strAction = objBV.getAction();
		OIDAOPoll objDAOPoll= new OIDAOPoll();
		boolean bFlag = false;
	 	OIDAOHome objOIDAO = new OIDAOHome();
	 	
	 	try
	 	{
		 	getConnection();
			
		 	if(strAction.equals(OIHomeConstants.POLL))
		 	{
		 	    if(objOIDAO.selectPoll(connection , objBV )) {
		 	    	if(objBV.getPollVoted().equals(OIHomeConstants.FLAG_N)){
			 			 connection.setAutoCommit(false);
			 		     objOIDAO.updatetPoll(connection , objBV );
			 		     connection.commit();
			 		     objBV.setPollVoted(OIHomeConstants.FLAG_Y);
			 		} 	        
				} else{
					if(objBV.getPollVoted().equals(OIHomeConstants.FLAG_N)){
						connection.setAutoCommit(false);
			 	        objOIDAO.insertPoll(connection , objBV );
			 	        connection.commit();
			 	       objBV.setPollVoted(OIHomeConstants.FLAG_Y);
					}  
				}
		 	   pollResults(objBV);
		 	}else if( strAction.equals(OIHomeConstants.PUBLISH)){
				pollResults(objBV);
			}else{ 
		 	    alResult = objOIDAO.viewPoll(connection);
				objOIDAO.getPublishCnt(connection ,alResult,objBV);
				if(alResult!=null)
				{    responseObject.addResponseObject(OIHomeConstants.POLLVIEW,alResult);
				}
		 	}
	 	}
	 	catch (Exception ex)
	 	{
	 		logger.error(OILabelConstants.EXCEPTION_IN_BO,ex);
			responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	 	}
	 	finally
	 	{
			freeConnection();
	 	}
		
	 	logger.debug(OILabelConstants.END_METHOD_BO + "poll");
	 	return responseObject;
	}
	 
	 /**
	  * This private invokes poll BO method to get the poll results
	  * @param objBV
	  * @throws Exception
	  */
	 private void pollResults(OIBVHome objBV) throws Exception{
	 	
	 	OIBVPoll objBVPoll = new OIBVPoll();
		objBVPoll.setAction(OIHomeConstants.VIEW);
		objBVPoll.setPollId(objBV.getPollId());
		OIBOPoll objBOPoll = new OIBOPoll();
		responseObject = objBOPoll.processPoll(objBVPoll);
		responseObject.addResponseObject(OIHomeConstants.BV,objBVPoll);
	 		 
	 }
	 
		public OIResponseObject updateTag(String userId,OIBVHome objBV) 
	    {
	        OIDAOHome oiHome = new OIDAOHome(); 
	        try 
	        {
	            getConnection();
	            oiHome.updateTag(userId, objBV, connection);
	        } 
	        catch (SQLException se) 
	        {
	            error = ""+se.getErrorCode();
	            logger.error("updateTag - SQLException->" + se);
	        } 
	        catch (Exception e) 
	        {
	            error = "OIDEFAULT";
	            logger.error("updateTag->" + e);
	        } 
	        finally 
	        {
	            freeConnection();
	            addToResponseObject();            
	        }
	        return responseObject;
	    }

	 

}	 