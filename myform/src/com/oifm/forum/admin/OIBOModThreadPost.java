package com.oifm.forum.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIBOSendMail;
import com.oifm.common.OICommonMailBean;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIBAPosting;
import com.oifm.forum.OIDAOPosting;
import com.oifm.forum.OIDAOThread;
import com.oifm.utility.MailUtility;
import com.oifm.utility.OIDBRegistry;

public class OIBOModThreadPost extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOModThreadPost.class);
	private String[] aryAppPostIds = null;
	private String[] aryRejPostIds = null;
	private String[] aryAppThreadIds = null;
	private String[] aryRejThreadIds = null;
	private String[] aryModifyThreadIds = null;
	
	public OIBOModThreadPost()	
	{
		super();
	}

	public OIResponseObject getModerateThreadPostsList(String strUserId, boolean isThreadMod, boolean isAdmin)	
	{
		OIDAOThread objThread = new OIDAOThread();
		ArrayList alTopicThreadPostsList = null;		
		logger.info(("getModerateThreadPostsList"));
		logger.info(("getConnection"));
		try 
		{
			getConnection();
			alTopicThreadPostsList = objThread.getThreadPostsListForModeration(connection, strUserId, isThreadMod, isAdmin);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getThreadPostingList => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("TopicThreadPostsList", alTopicThreadPostsList);
		}
		logger.debug(("responseObject "+responseObject));
		return responseObject;
	}
	
	public OIResponseObject setPostingModeration(ArrayList alModStatus, String strUserId) 
	{
		OIDAOPosting objPosting = new OIDAOPosting();
		OIDAOThread objThread = new OIDAOThread();
		boolean modifyFlag = true;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			setThreadPostModeationInfo(alModStatus, false);
			if(aryRejPostIds != null && aryRejPostIds.length > 0)
			{
				modifyFlag = objPosting.deleteRejectedPosts(connection, aryRejPostIds, strUserId);
			}
			if(modifyFlag && aryAppPostIds != null && aryAppPostIds.length > 0)
			{
				modifyFlag = objPosting.modifyPostsModerationStatus(connection, aryAppPostIds, strUserId);
			}
			if(modifyFlag && aryModifyThreadIds != null && aryModifyThreadIds.length > 0)
			{
				modifyFlag = objThread.updateThreadStatistics(connection, aryModifyThreadIds, strUserId);
				if (modifyFlag)
				{
					for(int i=0; i<aryModifyThreadIds.length; i++) 
					{
					    //---------
						ArrayList arBKMails = objThread.getMailIdsofBookmarkUsers(connection, aryModifyThreadIds[i]);
						if (arBKMails!=null)
						{
						    String strEmail = arBKMails.toString();
						    HashMap hmValidIds =  new OIBOSendMail().chkDomains(connection,strEmail.trim().substring(1,strEmail.length() - 1));
						    if(hmValidIds == null)
						    {
						        responseObject.addResponseObject(OILabelConstants.MAILERROR,OILabelConstants.MAILERROR);
						    }
						    else
						    {
						        if(hmValidIds.containsKey(OILabelConstants.VALIDIDS))
						        {
						            OICommonMailBean objCmnMailBean = new OICommonMailBean();
						            MailUtility objMailUtil = new MailUtility();
						            StringBuffer sbMail = new StringBuffer(0);
						            objCmnMailBean.setBcc(hmValidIds.get(OILabelConstants.VALIDIDS).toString());	  	  	
								    objCmnMailBean.setType(OIDBRegistry.getValues("type"));	    // Type of format
						 		    objCmnMailBean.setSmtpHost(OIDBRegistry.getValues("smtp")); // Mail Server
								    objCmnMailBean.setSubject(OIDBRegistry.getValues("OI.FM.BOOKMARK.SUBJECT"));
								    //strEmail = alTemp.toString();
								    //strEmail = objPostingVo.getStrNickName().replaceAll(" ","_");
								    strEmail = "Moderator";
								    //objCmnMailBean.setFrom(strEmail.trim().substring(1,strEmail.length() - 1));
								    objCmnMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
								    sbMail.append(OIDBRegistry.getValues("OI.FM.BOOKMARK.MESSAGE"))
										.append("\n\n")
										.append(OIDBRegistry.getValues("AlertAFriend"))
										.append("?module=F&id=")
										.append(aryModifyThreadIds[i])
										.append("\n\n");
										/*.append(OIDBRegistry.getValues("postedon"))
										.append(aryModifyThreadIds[i]);*/
								    objCmnMailBean.setMessage(sbMail.toString()+"<br>");
								    logger.info(objCmnMailBean.getMessage());
								    objMailUtil.sendMessage(objCmnMailBean);
								    //responseObject.addResponseObject(OILabelConstants.VALIDIDS,hmValidIds.get(OILabelConstants.VALIDIDS));
						        }
						        /*if(hmValidIds.containsKey(OILabelConstants.INVALIDIDS))
						        {
						            responseObject.addResponseObject(OILabelConstants.INVALIDIDS,hmValidIds.get(OILabelConstants.INVALIDIDS));
						        }
						        if(hmValidIds.containsKey(OILabelConstants.NODOMAINS))
						        {
						            responseObject.addResponseObject(OILabelConstants.NODOMAINS,OILabelConstants.NODOMAINS);
						        }*/
						    }
						}

					    //---------
					}
				}
			}
		} 
		catch(SQLException se) 
		{
			modifyFlag = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			modifyFlag = false;
			error = "OIDEFAULT";
			logger.error(" setPostingModeration => "+be);
		} 
		finally 
		{
			try 
			{
				if(!modifyFlag)
				{
					connection.rollback();
				}
				else 
				{
				    connection.commit();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" setPostingModeration => "+se);
			}
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject setThreadModeration(ArrayList alModStatus, String strUserId) 
	{
		OIDAOPosting objPosting = new OIDAOPosting();
		OIDAOThread objThread = new OIDAOThread();
		boolean modifyFlag = true;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			setThreadPostModeationInfo(alModStatus, true);
			if(aryRejPostIds != null && aryRejPostIds.length > 0)
			{
				modifyFlag = objPosting.deleteRejectedPosts(connection, aryRejPostIds, strUserId);
			}
			if(aryRejThreadIds != null && aryRejThreadIds.length > 0)
			{
				modifyFlag = objThread.deleteRejectedThreads(connection, aryRejThreadIds, strUserId);
			}
			if(modifyFlag && aryAppPostIds != null && aryAppPostIds.length > 0)
			{
				modifyFlag = objPosting.modifyPostsModerationStatus(connection, aryAppPostIds, strUserId);
			}
			if(modifyFlag && aryAppThreadIds != null && aryAppThreadIds.length > 0)
			{
				modifyFlag = objThread.modifyThreadsModerationStatus(connection, aryAppThreadIds, strUserId);
			}
			if(modifyFlag && aryModifyThreadIds != null && aryModifyThreadIds.length > 0)
			{
				modifyFlag = objThread.updateThreadStatistics(connection, aryModifyThreadIds, strUserId);
			}
		} 
		catch(SQLException se) 
		{
			modifyFlag = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			modifyFlag = false;
			error = "OIDEFAULT";
			logger.error(" setThreadModeration => "+be);
		} 
		finally 
		{
			try 
			{
				if(!modifyFlag)
				{
					connection.rollback();
				}
				else
				{
				    connection.commit();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" setThreadModeration => "+se);
			}
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	private void setThreadPostModeationInfo(ArrayList alModStatus, boolean isThreadMod) 
	{
		ArrayList alAppPostIds = new ArrayList();
		ArrayList alRejPostIds = new ArrayList();
		ArrayList alAppThreadIds = new ArrayList();
		ArrayList alRejThreadIds = new ArrayList();
		HashSet hsThreads = new HashSet(); 
		OIBAPosting objPosting = null;
		for(int i=0; i<alModStatus.size(); i++) 
		{
			objPosting = (OIBAPosting)alModStatus.get(i);
			if(objPosting.getStrApproveStatus() != null && objPosting.getStrApproveStatus().equals("A")) 
			{
				alAppPostIds.add(objPosting.getStrPostId());
				if(!alAppThreadIds.contains(objPosting.getStrThreadId()))
				{
					alAppThreadIds.add(objPosting.getStrThreadId());
				}
			} 
			else if(objPosting.getStrApproveStatus() != null && objPosting.getStrApproveStatus().equals("R")) 
			{
				alRejPostIds.add(objPosting.getStrPostId());
				if(!alRejThreadIds.contains(objPosting.getStrThreadId()))
				{
					alRejThreadIds.add(objPosting.getStrThreadId());
				}
			}
			logger.debug("alAppPostIds = "+alAppPostIds +"  alRejPostIds = "+alRejPostIds+" alAppThreadIds = "+alAppThreadIds+" alRejThreadIds = "+alRejThreadIds);			
			aryAppPostIds = (String[])alAppPostIds.toArray(new String[alAppPostIds.size()]);
			aryRejPostIds = (String[])alRejPostIds.toArray(new String[alRejPostIds.size()]);
			aryAppThreadIds = (String[])alAppThreadIds.toArray(new String[alAppThreadIds.size()]);
			aryRejThreadIds = (String[])alRejThreadIds.toArray(new String[alRejThreadIds.size()]);
			hsThreads.addAll(alAppThreadIds);
			if(isThreadMod) hsThreads.addAll(alRejThreadIds);
			aryModifyThreadIds = (String[])hsThreads.toArray(new String[hsThreads.size()]);
			
		}
	}	
}