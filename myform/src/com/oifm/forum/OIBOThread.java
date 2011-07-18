package com.oifm.forum;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIApplConstants;
import com.oifm.common.OIDAOSendMail;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;

public class OIBOThread extends OIBaseBo 
{
	private static Logger logger = Logger.getLogger(OIBOThread.class);
	
	public OIBOThread()	
	{
		super();
	}

	public OIResponseObject getThreadPostingList(OIBAThread objThreadVo,String strSortOrder)
	{
		OIDAOThread objThread = new OIDAOThread();
		OIPageInfoBean objPageBean = null;
		ArrayList alPostsList = null;
		ArrayList alTopicHrcy = null;
		OIBAThread threadObj = null;
		OIBACatBoardTopic objCatBoardTopic = null;
		boolean isSameFlag = false;
		boolean allowPM = false;
		boolean allowAM = false;
		int recPerPage = OIApplConstants.RECORDS_PER_PAGE;
		int recTotal = 0;
		try 
		{
			logger.info(" strSortOrder "+strSortOrder);
			getConnection();
			recPerPage = OIDAOSendMail.recPerPage(connection);
			logger.debug("After invoke recPerPage() : "+recPerPage+" Page no : "+objThreadVo.getPageNo());			
			if(recPerPage > 0)
			{
				objPageBean = new OIPageInfoBean(objThreadVo.getPageNo(), recPerPage);
			}
			else
			{
			    objPageBean = new OIPageInfoBean(objThreadVo.getPageNo(), OIApplConstants.RECORDS_PER_PAGE);
			}
			recTotal = objThread.getThreadPostsCount(connection, objThreadVo.getStrThreadId());
			objPageBean.setTotalRec(recTotal);
			threadObj = objThread.fetchThreadInfo(connection, objThreadVo);
			threadObj.setStrUserId(objThreadVo.getStrUserId());
			objThread.setPrevNextThreadInfo(connection, threadObj);
			alPostsList = objThread.getThreadPostsList(connection, objThreadVo.getStrThreadId(), objPageBean,strSortOrder);
			
			/* Added/Modified by Aik Mun @ Jan 19, 2009 
			 * Get Information whether post is readed or not
			 * */
			alPostsList = getPostReadFlag(connection,alPostsList, objThreadVo.getStrUserId(), objThreadVo.getStrThreadId());
			
			objCatBoardTopic = objThread.getThreadNavigation(connection, objThreadVo.getStrThreadId());
			isSameFlag = objThread.isThreadUserBoardSame(connection, objThreadVo.getStrThreadId(), objThreadVo.getStrUserId());
			allowPM = objThread.isAllowPersonalMessage(connection);
			allowAM = objThread.isAllowAlertAdmin(connection);
			
             String strSplThreadID=objThread.getThreadID(connection);
             responseObject.addResponseObject("strSplThreadID",strSplThreadID);
			
		}
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
			se.printStackTrace();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getThreadPostingList => "+be);		
			be.printStackTrace();
		}
		finally 
		{
			freeConnection();
			addToResponseObject();
			OIDAOPosting.assignExpressions(alPostsList);
			responseObject.addResponseObject("ThreadInfo", threadObj);
			responseObject.addResponseObject("TotalRec", new Integer(recTotal));
			responseObject.addResponseObject("TopicHrcy", alTopicHrcy);
			responseObject.addResponseObject("PostsList", alPostsList);
			responseObject.addResponseObject("CatBoardTopic", objCatBoardTopic);
			responseObject.addResponseObject("PageBean", objPageBean);
			responseObject.addResponseObject("isSameFlag", new Boolean(isSameFlag));
			responseObject.addResponseObject("isAllowPM", new Boolean(allowPM));
			responseObject.addResponseObject("isAllowAM", new Boolean(allowAM));
			
			logger.info("%%%%%%%% "+responseObject.getResponseObject("ThreadInfo"));
			logger.info(responseObject.getResponseObject("TotalRec"));
			logger.info(responseObject.getResponseObject("TopicHrcy"));
			logger.info(responseObject.getResponseObject("PostsList"));
		}
		return responseObject;
	}
	
	public OIResponseObject getCategoryBoardTopicHrcy(OIBAThread objThreadVo)
	{
		OIDAOThread objThread = new OIDAOThread();
		ArrayList alTopicHrcy = null;
		try 
		{
			getConnection();
			logger.debug("Before invoke getCategoryBoardTopicHrcy");
			alTopicHrcy = objThread.getTopicHierarchy(connection);
			logger.debug("After invoke getCategoryBoardTopicHrcy");
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getCategoryBoardTopicHrcy => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("TopicHrcy", alTopicHrcy);
		}
		return responseObject;
	}
	
	public OIResponseObject createThread(OIBAThread objThreadVo)
	{
		OIDAOThread objThread = new OIDAOThread();
		OIDAOPosting objPosting = new OIDAOPosting();
		String strPostId = "";
		String strThreadId = "";
		ArrayList alGroupsList = null;
		boolean createFlag = false;

		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			strThreadId = objThread.getNewThreadId(connection);
			objThreadVo.setStrThreadId(strThreadId);
			strPostId = objPosting.getNewPostingId(connection);
			createFlag = objThread.createThread(connection, objThreadVo);
			if(createFlag) 
			{
				if(objThreadVo.getStrSecurity() != null && objThreadVo.getStrSecurity().equals("N"))
				{
					objThreadVo.setAryUserGroups(null);
				}
				createFlag = objThread.setGroupsToThread(connection, objThreadVo.getStrThreadId(), objThreadVo.getAryUserGroups());
			}
			if(createFlag) 
			{
			    logger.debug("Before create posting object () "+createFlag);
				OIBAPosting objPostingVo = new OIBAPosting();
				objPostingVo.setStrPostId(strPostId);
				objPostingVo.setStrThreadId(objThreadVo.getStrThreadId());
				objPostingVo.setStrPosting(objThreadVo.getStrPosting());
				objPostingVo.setStrModerationStat(objThreadVo.getStrModerationStat());
				objPostingVo.setStrUserId(objThreadVo.getStrUserId());
				objPostingVo.setStrHQReply(objThreadVo.getStrHQReply());
				createFlag = objPosting.createPosting(connection, objPostingVo);
				logger.debug("After executing create posting object () "+createFlag);
			}
			logger.debug("After executing create posting object () "+createFlag+ "  moderation req : "+objThreadVo.getStrModerationReq());
			if(createFlag && objThreadVo.getStrModerationReq().equals("N"))
			{
				createFlag = objThread.updateLastpostInfo(connection, objThreadVo.getStrThreadId(), strPostId);
			}
			logger.debug("After executing updateLastpostInfo object () "+createFlag);
		} 
		catch(SQLException se) 
		{
			createFlag = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			createFlag = false;
			error = "OIDEFAULT";
			logger.error(" createThread => "+be);
		} 
		finally 
		{
			try 
			{
				if(!createFlag) 
				{
					connection.rollback();
					alGroupsList = objThread.getGroupsList(connection);
					logger.debug("After rollback  () "+createFlag);
				} 
				else 
				{
					connection.commit();
					if(objThreadVo.getStrModerationReq().equals("N"))
					{
						addMessageList("SuccessfulThreadCreated");
					}
					else
					{
					    addMessageList("SuccessfulThreadCreatedModeration");
					}
					logger.debug("After commit () "+createFlag);
				}
				connection.setAutoCommit(true);
			} catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" createThread => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("GroupsList", alGroupsList);
			responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
			responseObject.addResponseObject("createFlag", new Boolean(createFlag));
		}
		return responseObject;
	}
	
	public OIResponseObject getThreadModInfo(OIBAThread objThreadVo) throws SQLException 
	{
		OIDAOThread objThread = new OIDAOThread();
		OIDAOPosting objPosting = new OIDAOPosting();
		ArrayList alGroupsList = null;
		String[] aryThreadGroups = null;
		OIBAPosting objPostingVo = null;
		boolean isTopicModReq = false;
		try 
		{
			getConnection();
			logger.debug(" Thread Id : "+objThreadVo.getStrThreadId());
			objThreadVo = objThread.fetchThreadInfo(connection, objThreadVo);
			objPostingVo = objPosting.fetchModPostingInfo(connection, objThreadVo.getStrThreadId());
			objThreadVo.setStrPostId(objPostingVo.getStrPostId());
			objThreadVo.setStrPosting(objPostingVo.getStrPosting());			
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getThreadModInfo => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("objThreadVo", objThreadVo);
			responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
		}
		return responseObject;
	}
	
	public OIResponseObject getThreadGroupsInfo(OIBAThread objThreadVo) throws SQLException 
	{
		OIDAOThread objThread = new OIDAOThread();
		ArrayList alGroupsList = null;
		String[] aryThreadGroups = null;
		boolean isTopicModReq = false;
		try 
		{
			getConnection();
			logger.debug(" Topic Id : "+ objThreadVo.getStrTopicId()+" Thread Id : "+objThreadVo.getStrThreadId());
			isTopicModReq = objThread.isThreadTopicModerationReq(connection, objThreadVo.getStrTopicId());
			if(objThreadVo.getStrThreadId() != null && !objThreadVo.getStrThreadId().equals("")) 
			{
				objThreadVo = objThread.fetchThreadInfo(connection, objThreadVo);
				if(objThreadVo.getStrSecurity() != null && objThreadVo.getStrSecurity().equals("Y")) 
				{
					aryThreadGroups = objThread.getThreadGroupsList(connection, objThreadVo.getStrThreadId());
					objThreadVo.setAryUserGroups(aryThreadGroups);
				}
			}
			objThreadVo.setStrTopicModerationReq(isTopicModReq);
			if(objThreadVo.getStrModerationReq() == null || objThreadVo.getStrModerationReq().equals(""))
			{
				objThreadVo.setStrModerationReq((isTopicModReq)?"Y":"N");
			}
			alGroupsList = objThread.getGroupsList(connection);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getThreadGroupsInfo => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("GroupsList", alGroupsList);
			responseObject.addResponseObject("ThreadGroups", aryThreadGroups);
			responseObject.addResponseObject("objThreadVo", objThreadVo);
			responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
		}
		return responseObject;
	}

	public OIResponseObject modifyThread(OIBAThread objThreadVo, boolean canCreatePrivate)	
	{
		OIDAOThread objThread = new OIDAOThread();
		OIDAOPosting objPosting = new OIDAOPosting();
		String strPostId = "";
		String strThreadId = "";
		ArrayList alGroupsList = null;
		boolean modifyThread = false;
		boolean isTopicModReq = false;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			modifyThread = objThread.modifyThread(connection, objThreadVo);
			if(modifyThread && objThreadVo.getStrPosting() != null && !objThreadVo.getStrPosting().equals("") && !objThreadVo.getStrLastpostId().equals("")) 
			{
				OIBAPosting objPostingVo = new OIBAPosting();
				objPostingVo.setStrPosting(objThreadVo.getStrPosting());
				modifyThread = objPosting.modifyPosting(connection, objPostingVo);
			}
			if(modifyThread && canCreatePrivate) 
			{
				if(objThreadVo.getStrSecurity() != null && objThreadVo.getStrSecurity().equals("N"))
				{
					objThreadVo.setAryUserGroups(null);
				}
				modifyThread = objThread.setGroupsToThread(connection, objThreadVo.getStrThreadId(), objThreadVo.getAryUserGroups());
			}
		} 
		catch(SQLException se) 
		{
			modifyThread = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			modifyThread = false;
			error = "OIDEFAULT";
			logger.error(" modifyThread => "+be);
		} 
		finally 
		{
			try 
			{
				if(!modifyThread) 
				{
					connection.rollback();
					alGroupsList = objThread.getGroupsList(connection);
					responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
				} 
				else 
				{
					connection.commit();
					addMessageList("SuccessfulThreadModified");
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" modifyThread => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("GroupsList", alGroupsList);
			responseObject.addResponseObject("modifyThread", new Boolean(modifyThread));
		}
		return responseObject;
	}

	public OIResponseObject modifyModarateThread(OIBAThread objThreadVo)
	{
		OIDAOThread objThread = new OIDAOThread();
		OIDAOPosting objPosting = new OIDAOPosting();
		String strPostId = "";
		String strThreadId = "";
		boolean modifyThread = false;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			modifyThread = objThread.setThreadsAudit(connection, objThreadVo.getStrUserId(), objThreadVo.getStrTitle(), objThreadVo.getStrThreadId());
			if(modifyThread)
			{
			    modifyThread = objThread.modifyModarateThread(connection, objThreadVo);
			}
			if(modifyThread && objThreadVo.getStrPosting() != null && !objThreadVo.getStrPosting().equals("") && !objThreadVo.getStrPostId().equals("")) 
			{
				OIBAPosting objPostingVo = new OIBAPosting();
				objPostingVo.setStrPostId(objThreadVo.getStrPostId());
				objPostingVo.setStrPosting(objThreadVo.getStrPosting());
				modifyThread = objPosting.setPostsAudit(connection, objThreadVo.getStrUserId(), objPostingVo.getStrPosting(), objPostingVo.getStrPostId());
				if(modifyThread)
				{
				    modifyThread = objPosting.modifyPosting(connection, objPostingVo);
				}
			}
		} 
		catch(SQLException se) 
		{
			modifyThread = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			modifyThread = false;
			error = "OIDEFAULT";
			logger.error(" modifyModarateThread => "+be);
		} 
		finally 
		{
			try 
			{
				if(!modifyThread) 
				{
					connection.rollback();
					responseObject.addResponseObject("ExpressionsList", OIDAOPosting.getExpressionsList()); 
				} 
				else 
				{
					connection.commit();
					addMessageList("SuccessfulThreadModified");
				}
				connection.setAutoCommit(true);
			} 
			catch(SQLException se) 
			{
				error = ""+se.getErrorCode();
				logger.error(" modifyModarateThread => "+se);
			}
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("modifyThread", new Boolean(modifyThread));
		}
		return responseObject;
	}

	public OIResponseObject deleteThread(OIBAThread objThreadVo)
	{
		OIDAOThread objThread = new OIDAOThread();
		String strPostId = "";
		String strThreadId = "";
		boolean deleteThread = false;
		try 
		{
			getConnection();
			connection.setAutoCommit(false);
			deleteThread = objThread.deleteThreadPosts(connection, objThreadVo.getStrThreadId());
			if(deleteThread) 
			{
				objThreadVo.setAryUserGroups(null);
				deleteThread = objThread.setGroupsToThread(connection, objThreadVo.getStrThreadId(), objThreadVo.getAryUserGroups());
			}
			if(deleteThread)
			{
			    deleteThread = objThread.deleteThreadBookMarks(connection, objThreadVo.getStrThreadId());
			}
			if(deleteThread)
			{
			    deleteThread = objThread.deleteThreadAudit(connection, objThreadVo.getStrThreadId());
			}
			if(deleteThread)
			{
			    deleteThread = objThread.deleteThread(connection, objThreadVo.getStrThreadId());
			}
		} 
		catch(SQLException se) 
		{
			deleteThread = false;
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			deleteThread = false;
			error = "OIDEFAULT";
			logger.error(" deleteThread => "+be);
		} 
		finally 
		{
			try 
			{
				if(deleteThread) 
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
				error = ""+se.getErrorCode();
				logger.error(" deleteThread => "+se);
			}
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}

	public OIResponseObject lockUnlockThread(OIBAThread objThreadVo)
	{
		OIDAOThread objThread = new OIDAOThread();
		boolean lockThread = false;
		try 
		{
			getConnection();
			lockThread = objThread.lockUnlockThread(connection, objThreadVo.getStrThreadId());
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" lockUnlockThread => "+be);
		} 
		finally 
		{
			freeConnection();
			if(lockThread)
			{
				addMessageList("SuccessLockUnlockThread");
			}
			addToResponseObject();
		}
		return responseObject;
	}

	public OIResponseObject setStickyThread(OIBAThread objThreadVo)	
	{
		OIDAOThread objThread = new OIDAOThread();
		boolean isStickyAllowed = false;
		boolean stickyFlag = false;
		try 
		{
			getConnection();
			stickyFlag = objThread.isStickyExists(connection, objThreadVo);
			if(!stickyFlag) 
			{ 
				isStickyAllowed = objThread.isStickyThreadAllowed(connection, objThreadVo.getStrUserId());
				if(isStickyAllowed)
				{
				    stickyFlag = objThread.updateStickyBmarkThread(connection, objThreadVo, OIForumConstants.STICKY_THREAD);
				}
				else
				{
				    error = "STICKY_THREAD_LIMIT_REACHED";
				}
			}
			
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" setStickyThread => "+be);
		} 
		finally 
		{
			freeConnection();
			if(stickyFlag)
			{
				addMessageList("SuccessStickyThread");
			}
			addToResponseObject();
		}
		return responseObject;
	}

//Anbu added for Admin  sticky thread
	public OIResponseObject setAdminStickyThread(OIBAThread objThreadVo)	
	{
		OIDAOThread objThread = new OIDAOThread();
 		boolean stickyFlag = false;
		try 
		{
			getConnection();
			stickyFlag = objThread.isAdminStickyExists(connection, objThreadVo);
			//System.out.println("here=======1"+stickyFlag);
			//if not exists insert else update
			if(!stickyFlag) 
			{ 
				//System.out.println("here=======insert 1"+stickyFlag);
 			    stickyFlag = objThread.insertAdminStickyBmarkThread(connection, objThreadVo, OIForumConstants.ADMIN_STICKY_THREAD);
 			}else{
 				//System.out.println("here=======update1"+stickyFlag);
 				stickyFlag = objThread.updateAdminStickyBmarkThread(connection, objThreadVo, OIForumConstants.ADMIN_STICKY_THREAD);
 			}
			
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" setAdminStickyThread => "+be);
		} 
		finally 
		{
			freeConnection();
			if(stickyFlag)
			{
				addMessageList("SuccessStickyThread");
			}
			addToResponseObject();
		}
		return responseObject;
	}

//	Anbu added for Admin  sticky thread
	public OIResponseObject setSplStickyThread(OIBAThread objThreadVo)	
	{
		OIDAOThread objThread = new OIDAOThread();
 		boolean stickyFlag = false;
		try 
		{
			getConnection();
			stickyFlag = objThread.isSplStickyExists(connection, objThreadVo);
			//System.out.println("here=======1"+stickyFlag);
			//if not exists insert else update
			if(!stickyFlag) 
			{ 
				//System.out.println("here=======insert 1"+stickyFlag);
 			    stickyFlag = objThread.insertSplStickyBmarkThread(connection, objThreadVo, OIForumConstants.SPL_STICKY_THREAD);
 			}else{
 				//System.out.println("here=======update1"+stickyFlag);
 				stickyFlag = objThread.updateSplStickyBmarkThread(connection, objThreadVo, OIForumConstants.SPL_STICKY_THREAD);
 			}
			
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" setSplStickyThread => "+be);
		} 
		finally 
		{
			freeConnection();
			if(stickyFlag)
			{
				addMessageList("SuccessStickyThread");
			}
			addToResponseObject();
		}
		return responseObject;
	}


	public OIResponseObject setBookMarkThread(OIBAThread objThreadVo)
	{
		OIDAOThread objThread = new OIDAOThread();
		boolean isBookMarkAllowed = false;
		boolean bookMarkFlag = false;
		try 
		{
			getConnection();
			bookMarkFlag = objThread.isBookMarkExists(connection, objThreadVo);
			if(!bookMarkFlag) 
			{ 
				isBookMarkAllowed = objThread.isBookMarkAllowed(connection, objThreadVo.getStrUserId());
				if(isBookMarkAllowed)
				{
				    bookMarkFlag = objThread.updateStickyBmarkThread(connection, objThreadVo, OIForumConstants.BOOKMARK_THREAD);
				}
				else
				{
				    error = "BOOKMARK_THREAD_LIMIT_REACHED";
				}
			}
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" setBookMarkThread => "+be);
		} 
		finally 
		{
			freeConnection();
			if(bookMarkFlag)
			{
				addMessageList("SuccessBookmarkThread");
			}
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject moveThreadToAnotherTopic(OIBAThread objThreadVo) 
	{
		OIDAOThread objThread = new OIDAOThread();
		boolean moveFlag = false;
		try 
		{
			getConnection();
			moveFlag = objThread.moveThreadToAnotherTopic(connection, objThreadVo);
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" moveThreadToAnotherTopic => "+be);
		} 
		finally 
		{
			freeConnection();
			if(moveFlag)
			{
				addMessageList("SuccessMoveThread");
			}
			addToResponseObject();
		}
		return responseObject;
	}	

	public OIResponseObject getPrintThreadPostingList(OIBAThread objThreadVo) 
	{
		OIDAOThread objThread = new OIDAOThread();
		ArrayList alPostsList = null;
		int recTotal = 0;
		try 
		{
			getConnection();
			objThreadVo = objThread.fetchThreadInfo(connection, objThreadVo);
			recTotal = objThread.getThreadPostsCount(connection, objThreadVo.getStrThreadId());
			alPostsList = objThread.getThreadPostsListForView(connection, objThreadVo.getStrThreadId());
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getPrintThreadPostingList => "+be);
		}
		finally 
		{
			freeConnection();
			addToResponseObject();
			OIDAOPosting.assignExpressions(alPostsList);
			responseObject.addResponseObject("ThreadInfo", objThreadVo);
			responseObject.addResponseObject("PostsList", alPostsList);
			responseObject.addResponseObject("TotalRec", new Integer(recTotal));
		}
		return responseObject;
	}
	
	public OIResponseObject getExportThreadPostingsList(OIBAThread objThreadVo)	
	{
		OIDAOThread objThread = new OIDAOThread();
		ArrayList alPostsList = null;
		String strThreadExportInfo = "";
		int recTotal = 0;
		try 
		{
			getConnection();
			objThreadVo = objThread.fetchThreadInfo(connection, objThreadVo);
			alPostsList = objThread.getThreadPostsListForView(connection, objThreadVo.getStrThreadId());
			recTotal = objThread.getThreadPostsCount(connection, objThreadVo.getStrThreadId());
			strThreadExportInfo = getStrThreadInfo(objThreadVo, alPostsList, recTotal);
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
			responseObject.addResponseObject("ThreadExportInfo", strThreadExportInfo);
		}
		return responseObject;
	}
	
	private String getStrThreadInfo(OIBAThread objThreadVo, ArrayList alPostingList, int recTotal) throws RuntimeException  
	{
		OIBAPosting objPosting = null;
		int cnt = 0;
		StringBuffer stbrOut = new StringBuffer();
		stbrOut.append(OILabelConstants.LINE);
		stbrOut.append("Thread Title :"+OILabelConstants.TAB+objThreadVo.getStrTitle()+OILabelConstants.LINE);
		stbrOut.append("Created By :"+OILabelConstants.TAB+objThreadVo.getStrCreatedBy()+OILabelConstants.LINE);
		stbrOut.append("Created On :"+OILabelConstants.TAB+objThreadVo.getStrCreatedOn()+OILabelConstants.LINE);
		stbrOut.append("Total Posts :"+OILabelConstants.TAB+recTotal+OILabelConstants.LINE);
		stbrOut.append(OILabelConstants.LINE);
		stbrOut.append("Created By "+OILabelConstants.TAB+" Created On "+OILabelConstants.TAB+"Total Posts "+OILabelConstants.LINE);

		for(int i=0; i<alPostingList.size(); i++) 
		{
			objPosting = (OIBAPosting)alPostingList.get(i);
			logger.info(i + "--" + removeExpressions(objPosting.getStrPosting()));
			stbrOut.append(objPosting.getStrNickName()+OILabelConstants.TAB+"'"+objPosting.getStrPostedOn()+OILabelConstants.TAB+removeExpressions(objPosting.getStrPosting())+OILabelConstants.LINE);
		}
		return stbrOut.toString();
	}

	private String removeExpressions(String strInput)  
	{
		try 
		{
		    for (int i=0; i<OIForumConstants.SMILIES_SYMBOLS.length; i++)	
		    {
				strInput = strInput.replaceAll(OIForumConstants.SMILIES_SYMBOLS[i], "");
			}
			strInput = strInput.replaceAll(OILabelConstants.LINE,"");
			strInput = strInput.replaceAll("\r","");
			strInput = strInput.replaceAll("&nbsp;","");
		}
		catch(PatternSyntaxException pe) 
		{}
		return strInput;
	}
	public OIResponseObject getList(OIBAThread objThreadVo)
	{	try
		{
			OIBACatBoardTopic objCatBoardTopic = null;
			OIDAOThread objThread = new OIDAOThread();
			getConnection();
			logger.info(" connection "+connection);
			logger.info(" objThreadVo.getStrTopicId() "+objThreadVo.getStrTopicId());			
			objCatBoardTopic = objThread.getNavigation(connection, objThreadVo.getStrTopicId());
			responseObject.addResponseObject("CatBoardTopic", objCatBoardTopic);		
		}catch(Exception pe){
		logger.info(pe);
		}finally{freeConnection();}
		return  responseObject ;		
	}	
	
	/* Added/Modified by Aik Mun @ Jan 16, 2009 */
	public OIResponseObject markReadStatus(String strPostsIdList, String strStatus, String strThreadId, String strUserId)
	{	try
		{
			OIDAOThread objThread = new OIDAOThread();
			boolean flag = false;
			ArrayList alPostList = new ArrayList();
			getConnection();
			
			
			String [] arPosts = strPostsIdList.split(",");
			if(arPosts!=null && arPosts.length>0){
				for(int i=0;i<arPosts.length;i++){
					if(arPosts[i]!=null && arPosts[i].trim().length()>0){
						ArrayList alPostDetail = new ArrayList();
						alPostDetail.add(strUserId);
						alPostDetail.add(strThreadId);
						alPostDetail.add(arPosts[i]);
						
						alPostList.add(alPostDetail);
					}
				}
				flag = objThread.markReadStatus(connection, alPostList, strStatus);
			}
			
			if(flag)
				responseObject.addResponseObject("MarkStatus", "Success");
			else
				responseObject.addResponseObject("MarkStatus", "Fail");
		}catch(Exception pe){
			logger.error(pe);
		}finally{freeConnection();}
		
		return  responseObject ;		
	}
	
	/* Added/Modified by Aik Mun @ Jan 19, 2009 */
	public ArrayList getPostReadFlag (Connection con,ArrayList alPosting, String userId, String threadId){

		StringBuffer sbPostId = new StringBuffer();
		StringBuffer sbReadPostId = new StringBuffer();
		OIDAOThread objThread = new OIDAOThread();
		
		try{
			if(alPosting!=null && alPosting.size()!=0){
				
				for(int i=0;i<alPosting.size();i++){
					OIBAPosting oiBAPost = (OIBAPosting)alPosting.get(i);
					if(oiBAPost!=null){
						sbPostId.append("'"+oiBAPost.getStrPostId());
					}
					
					if(i!=alPosting.size()-1){
						sbPostId.append("',");
					}else{
						sbPostId.append("'");
					}
				}
				
				sbReadPostId = objThread.checkReadStatus(con, sbPostId.toString(), threadId, userId);
				
				if(sbReadPostId!=null && sbReadPostId.length()>0){
					for(int i=0;i<alPosting.size();i++){
						OIBAPosting oiBAPost = (OIBAPosting)alPosting.get(i);
						if(oiBAPost!=null && oiBAPost.getStrPostId()!=null){
							if(sbReadPostId.indexOf(oiBAPost.getStrPostId())!=-1){
								oiBAPost.setStrReadFlag("R");
								alPosting.remove(i);
								alPosting.add(i, oiBAPost);
							}
						}
						
					}
				}
				
			}
		}catch(Exception pe){
			logger.error(pe);
			pe.printStackTrace();
		}
		
		return alPosting;
	}
	
	// Added by K.K.Kumaresan on Sep 16,2009
	/**
	 * is Valid User
	 * @param objThreadVo
	 * @return
	 */
	public boolean isValidUser(String threadId,String userId)
	{
		OIDAOThread objThread = new OIDAOThread();
		boolean validUser=true;
		try 
		{
			logger.info(" Start of isValidUser ");
			getConnection();
			validUser=objThread.isValidUser(connection,threadId,userId);
		}
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getThreadPostingList => "+be);		
			be.printStackTrace();
		}
		finally 
		{
			freeConnection();
		}
		logger.info(" End of isValidUser ");
		return validUser;
	}
}	
