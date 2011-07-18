package com.oifm.forum;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIBOSendMail;
import com.oifm.common.OICommonMailBean;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.MailUtility;
import com.oifm.utility.OIDBRegistry;

public class OIBOPosting extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOPosting.class);
	
	public OIBOPosting()	
	{
		super();
	}
	
	public OIResponseObject createPosting(OIBAPosting objPostingVo)	
	{
		OIDAOThread objThread = new OIDAOThread();
		OIDAOPosting objPosting = new OIDAOPosting();
		String strPostId = "";
		boolean createFlag = false;
		boolean moderationReq = false;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			strPostId = objPosting.getNewPostingId(connection);
			objPostingVo.setStrPostId(strPostId);
			moderationReq = objThread.isThreadModerationReq(connection, objPostingVo.getStrThreadId());
			if(moderationReq)
			{
			    objPostingVo.setStrModerationStat("N");
			}
			else
			{
			    objPostingVo.setStrModerationStat("Y");
			}
			createFlag = objPosting.createPosting(connection, objPostingVo);
			if(createFlag && !moderationReq)
			{
				createFlag = objThread.updateLastpostInfo(connection, objPostingVo.getStrThreadId(), strPostId);
				ArrayList arBKMails = objThread.getMailIdsofBookmarkUsers(connection, objPostingVo.getStrThreadId());
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
						    if (objPostingVo.getStrNickName()!=null)
						    {
						        strEmail = objPostingVo.getStrNickName().replaceAll(" ","_");
						    }
						    else
						    {
						        strEmail = "";
						    }
						    //objCmnMailBean.setFrom(strEmail.trim().substring(1,strEmail.length() - 1));
						    objCmnMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
						    sbMail.append(OIDBRegistry.getValues("OI.FM.BOOKMARK.MESSAGE"))
								.append("\n\n")
								.append(OIDBRegistry.getValues("AlertAFriend"))
								.append("?module=F&id=")
								.append(objPostingVo.getStrThreadId())
								.append("\n\n")
								.append(OIDBRegistry.getValues("postedon"))
								.append(objPostingVo.getStrPostedOn());
						    objCmnMailBean.setMessage(sbMail.toString());
						    logger.info(objCmnMailBean.getMessage()+"<br>");
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
			}
		} 
		catch(SQLException se) 
		{
			createFlag = false;
			error = ""+se.getMessage();
			logger.error(" createPosting => "+se);
		} 
		catch(Exception be)
		{
			createFlag = false;
			error = "OIDEFAULT";
			logger.error(" createPosting => "+be);
		} 
		finally 
		{
			try 
			{
				if(createFlag) 
				{
					connection.commit();
					if(moderationReq)
					{
						addMessageList("SuccessfulPostModerationCreated");
					}
					else
					{
					    addMessageList("SuccessfulPosting");
					}
				}
				else
				{
				    connection.rollback();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getMessage();
				logger.error(" createPosting => "+se);
			}
			freeConnection();
			addToResponseObject();
			if(!createFlag)
			{
				responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList());
			}
			responseObject.addResponseObject("createFlag", new Boolean(createFlag));
		}
		return responseObject;
	}

	public OIResponseObject getPostingDetails(OIBAPosting objPostingVo)	
	{
		OIDAOPosting objPosting = new OIDAOPosting();
		OIBAThread objThreadVo = new OIBAThread();
		try 
		{
			getConnection();
			if(objPostingVo.getStrPostId() != null && !objPostingVo.getStrPostId().equals(""))
			{
				objPostingVo = objPosting.fetchPostingInfo(connection, objPostingVo.getStrPostId());
				objThreadVo = objPosting.fetchThreadInfo(connection, objPostingVo.getStrPostId());
			}
		} 
		catch(SQLException se) 
		{
			error = ""+se.getMessage();
			logger.error(" getPostingDetails => " + se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getPostingDetails => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
			responseObject.addResponseObject("objPostingVo", objPostingVo); 
			responseObject.addResponseObject("objThreadVo", objThreadVo);
		}
		return responseObject;
	}

	public OIResponseObject modifyPosting(OIBAPosting objPostingVo, boolean isMod)	
	{
		OIDAOPosting objPosting = new OIDAOPosting();
		boolean modifyPosting = true;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			if(isMod) modifyPosting = objPosting.setPostsAudit(connection, objPostingVo.getStrUserId(), objPostingVo.getStrPosting(), objPostingVo.getStrPostId());
			if(modifyPosting) modifyPosting = objPosting.modifyPosting(connection, objPostingVo);
		} 
		catch(SQLException se) 
		{
			modifyPosting = false;
			error = ""+se.getMessage();
			logger.error(" modifyPosting => " + se);
		} 
		catch(Exception be)	
		{
			modifyPosting = false;
			error = "OIDEFAULT";
			logger.error(" modifyPosting => "+be);
		} 
		finally 
		{
			try 
			{
				if(!modifyPosting) 
				{
					connection.rollback();
					responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
				} 
				else 
				{
					connection.commit();
					addMessageList("PostModifiedSuccessfull");
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getMessage();
				logger.error(" modifyModarateThread => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("modifyPosting", new Boolean(modifyPosting));
		}
		return responseObject;
	}

	public OIResponseObject deletePosting(OIBAPosting objPostingVo)	
	{
		OIDAOThread objThread = new OIDAOThread();
		OIDAOPosting objPosting = new OIDAOPosting();
		boolean deletePosting = false;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			deletePosting = objPosting.deletePostingAudit(connection, objPostingVo.getStrPostId());
			//	 Writteb by K.K.Kumaresan on Feb 17,2008.
			objPosting.deleteReadInformation(connection, objPostingVo.getStrPostId(),objPostingVo.getStrThreadId(),objPostingVo.getStrUserId());
			if(deletePosting) deletePosting = objPosting.deletePosting(connection, objPostingVo.getStrPostId());
			if(deletePosting) deletePosting = objThread.updateModerataionLastpostInfo(connection, objPostingVo.getStrThreadId());
			
		} 
		catch(SQLException se) 
		{
			deletePosting = false;
			error = ""+se.getMessage();
//			logger.error(" deletePosting => " + se);
			logger.error(" deletePosting",se);
		} 
		catch(Exception be)	
		{
			deletePosting = false;
			error = "OIDEFAULT";
//			logger.error(" deletePosting => "+be);
			logger.error(" deletePosting",be);
		} 
		finally 
		{
			try 
			{
				if(deletePosting) 
				{
					addMessageList("SuccessDeleteThread");
					connection.commit();
				}
				else
				{
				    connection.rollback();
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getMessage();
				logger.error(" deleteThread => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("deletePosting", new Boolean(deletePosting));
		}
		return responseObject;
	}
}