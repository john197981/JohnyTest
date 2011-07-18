/*******************************************************************************************
* Title 		: OIBOThreadList 
* Description 	: This is the Business Object for Listing the Threads in discussion forum
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 09 Aug 2005
* Copyright 	: Scandent Group
*******************************************************************************************/
package com.oifm.forum;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;

/**
 * @author Administrator
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
public class OIBOThreadList extends OIBaseBo 
{
//	Create instance for LOGGER and objects  used
	transient private final Log LOGGER = LogFactory.getLog(this.getClass());
	
	public OIBOThreadList()
	{
		LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + this.getClass().getName());
	}
	
	
	public OIResponseObject processThreads(OIBAThread objBAThread, String userId, ArrayList alFunctions) throws Exception
	{
		LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "processThreads");
		String strAction = objBAThread.getStrHiddenAction(); 
    	
		if(strAction.equals("hits"))
		{
		    updateHits(objBAThread);
    	}
		else
		{  		
		    responseObject =  listThreads(objBAThread, userId, alFunctions);
    	}	
    	    			
    	LOGGER.info(OILabelConstants.END_METHOD_BO + "processThreads");
    	return responseObject;
	}
	
	/**
	 * This method returns list of threads
	 * @param objOIBVSendMail
	 * @return
	 * @throws Exception
	 */
	 private OIResponseObject listThreads(OIBAThread objBAThread, String userId, ArrayList alFunctions)throws Exception
	 {
	     LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "listThreads");
	     OIDAOThreadList objDAO = new OIDAOThreadList(); 
	     ArrayList alThread = new ArrayList();
	     ArrayList alSticky = new ArrayList();
	     OIPageInfoBean aOIPageInfoBean = null;
	     LinkedHashMap hmTemp = null;
	     LinkedHashMap hmSticky = null;
	     LinkedHashMap hmAdminSticky = null;
	     OIBAThread  objThreads[] =null;
	     boolean bFlag = false;
		 OIBACatBoardTopic objCatBoardTopic = null;
		 OIDAOThread objThread = new OIDAOThread();
		 boolean isBoardAdmin = false;
	     try
	     {
	         getConnection();
	         OIForumAccessInfo objForumAccess = new OIForumAccessInfo(alFunctions);
	         
	         isBoardAdmin = objForumAccess.isMainAdmin() || (objForumAccess.isDivAdminOrModerator() && objDAO.isTopicUserBoardSame(connection, objBAThread.getStrTopicId(), userId));
	         
	         objDAO.getTopicName(connection,objBAThread);
	         aOIPageInfoBean = new OIPageInfoBean(Integer.parseInt(objBAThread.getRowId()),OIDAOSendMail.recPerPage(connection));
	         bFlag =  objDAO.srchRowCntQry(connection,objBAThread, isBoardAdmin);
	         OIBAThread objThreadVo = new OIBAThread();
			 objCatBoardTopic = objThread.getThreadNavigation(connection, objThreadVo.getStrThreadId());
			 
			 //Get the thread id for the special thread for the selected topic id
			 objBAThread.setStrSplThreadID(objThread.getThreadID(connection));
 			 LOGGER.info(" objCatBoardTopic ######## "+objCatBoardTopic);
	         if(bFlag)
	         {
	             aOIPageInfoBean.setTotalRec(objBAThread.getRowCount());
		    	 LOGGER.info(""+aOIPageInfoBean.getTotalRec());  
		    	 hmTemp = objDAO.getUsers(connection,objBAThread);
		    	 alThread.add(hmTemp);
		    	 hmTemp	= objDAO.getMsgs(connection,objBAThread);
		    	 alThread.add(hmTemp);
		    	 hmSticky	= objDAO.getStickyThreads(connection,objBAThread);
		    	 alThread.add(hmSticky);
		    	 //Admin Sticky
		    	 hmAdminSticky	= objDAO.getAdminStickyThreads(connection,objBAThread);
		    	 alThread.add(hmAdminSticky);
		    	 
		    	 alSticky = objDAO.getThreadLst(connection,objBAThread,alThread,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(), isBoardAdmin);
		         if(alSticky != null && hmSticky != null)
		         {
		             objThreads = new OIBAThread[alSticky.size()];
		             objThreads = (OIBAThread[]) alSticky.toArray(objThreads);
		             /// Arrays.sort(objThreads);
		         }		            	            
	         }   
	         
	         if(alSticky != null)
	         {
	             responseObject.addResponseObject(OILabelConstants.OBJARBV,alSticky);
	         }
 	         responseObject.addResponseObject("aOIPageInfoBean",aOIPageInfoBean);
			 responseObject.addResponseObject("CatBoardTopic", objCatBoardTopic);
			 responseObject.addResponseObject("strSplThreadID",objBAThread.getStrSplThreadID());
	     }
	     catch (Exception ex)
	     {
	         LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
	         responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
	     }
	     finally
	     {
	         freeConnection();	
	         hmTemp = null;
	     }

	     LOGGER.info(OILabelConstants.END_METHOD_BO + "listThreads");
	     return responseObject;
	 }
	 
	 /**
	  * This method updates the hits count of thread listing
	  * @param objBAThread
	  * @return
	  * @throws Exception
	  */
	 private boolean updateHits(OIBAThread objBAThread)throws Exception
	 {
	 	LOGGER.info(OILabelConstants.BEGIN_METHOD_BO + "updateHits");
	 	OIDAOThreadList objDAO = new OIDAOThreadList(); 
		boolean bUpdate = false;
		try
		{
		    /**Create the Connection Object **/
		    getConnection();	
			/**DAO call to search the List of Users **/
			connection.setAutoCommit(false);
			bUpdate = objDAO.updateHits(connection,objBAThread);
			connection.commit();
		}
		catch (Exception ex)
		{
		    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,ex);
		    responseObject.addResponseObject(OILabelConstants.ERROR,ex.getMessage());
			try
			{
				connection.rollback();
			}
			catch(SQLException sqlEx)
			{	
			    LOGGER.error(OILabelConstants.EXCEPTION_IN_BO,sqlEx);
			}
		}
		finally
		{
		    freeConnection();
			objDAO = null;
		}
		LOGGER.info(OILabelConstants.END_METHOD_BO + "updateHits");		 
		return bUpdate;
	 }
	 
	 private void SortBV()
	 {
	 } 
	 
	 private class StringComparator implements Comparator 
	 {
	     /*
		 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
		 */

	     public int compare(Object obj1, Object obj2) 
	     {
	         OIBAThread objBAThread1 = (OIBAThread) obj1;
	         OIBAThread objBAThread2 = (OIBAThread)obj2;
	         String str1 = objBAThread1.getStrImgMsg();
	         String str2 = objBAThread1.getStrImgMsg();
	         Integer nRow1 = null;
	         Integer nRow2 = null;
	         int nTemp=0;
	         if(str1 != null && str2 != null)
	         {
	             nTemp = str1.compareTo(str2); 
	         }
	         return (nTemp);
	     }
	 }
}
