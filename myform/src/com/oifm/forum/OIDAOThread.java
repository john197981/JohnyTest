package com.oifm.forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseDao;
import com.oifm.common.OIPageInfoBean;
import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.useradmin.OIDAOMemberProfile;
import com.oifm.utility.OIEncrypter;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOThread extends OIBaseDao 
{
	private static final Logger logger = Logger.getLogger(OIDAOThread.class);

	public OIDAOThread()	{	}

	public OIBAThread fetchThreadInfo(Connection con, OIBAThread objThreadVo) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAThread objThread = null;
		String strThreadId = objThreadVo.getStrThreadId();
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.THREAD_DETAILS);
			pstmt.setString(1, strThreadId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objThread = new OIBAThread(); 
				objThread.setStrThreadId(strThreadId);
				objThread.setStrTopicId(rs.getString("TOPICID"));
				objThread.setStrTitle(rs.getString("TITLE"));
				objThread.setStrSecurity(rs.getString("SECURITY"));
				objThread.setStrModerationReq(rs.getString("MODERATION_REQ"));
				objThread.setStrModerationStat(rs.getString("MODERATION_STAT"));
				objThread.setStrLocked(rs.getString("LOCKED"));
				objThread.setStrCreatedBy(rs.getString("NICKNAME"));
				objThread.setStrCreatedOn(rs.getString("CREATED_ON"));
				objThread.setStrReplies(rs.getString("REPLIES"));
			}
			String pUserId = objThreadVo.getStrUserId();
			OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            aOIBAWebSiteRanking.setActionId("VT");
            aOIBAWebSiteRanking.setUserId(pUserId);
            aOIBAWebSiteRanking.setItemId(Integer.parseInt(strThreadId));
            new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,con);
		} 
		catch(Exception e)
        {
            String error = e.getMessage();
            logger.error(e);
            try
            {
            	con.rollback();
            }catch (Exception ex){}
        }
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objThread;
	}

	public ArrayList getThreadPostsList(Connection con, String strThreadId, 
			OIPageInfoBean pageInfo,String strSortOrder) throws SQLException 
	{
		ArrayList alPostsList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAPosting objPosting = null;
		//Amended by Zhao Yu on 2008-Aug-21
		//Encrypt user's nric
		String eName = "";
		try 
		{
			logger.info(" strSortOrder "+strSortOrder);
		    logger.info(" No of records : "+pageInfo.getTotalRec() + " StartRec : "+pageInfo.getStartRec() + " EndRec : "+pageInfo.getEndRec()+ " Connection : "+con);
		    //String strQuery = OIForumThreadPostsSqls.THREAD_POSTS_LIST+" ORDER BY POSTED_ON "+strSortOrder;
		    String strQuery = OIForumThreadPostsSqls.THREAD_POSTS_LIST+strSortOrder +" ) OFP) OFP1 WHERE RN>=? AND RN <=?";
			pstmt = con.prepareStatement(strQuery);
			logger.info(" strQuery "+strQuery);
			pstmt.setString(1, strThreadId);
			//pstmt.setString(2, strSortOrder);
			pstmt.setInt(2, pageInfo.getStartRec());
			pstmt.setInt(3, pageInfo.getEndRec());
			rs = pstmt.executeQuery();
			Date d=null;
			while(rs.next())
			{
				objPosting = new OIBAPosting();
				objPosting.setStrPostId(rs.getString("POSTID"));
				
				/* Added/Modified by Aik Mun @ Jan 19, 2009 */
				StringBuffer sbPosting = new StringBuffer();
				sbPosting.append(rs.getString("POSTING"));
				if(rs.getString("QUOTE_POSTING")!=null && rs.getString("QUOTE_POSTING").trim().length()>0){
					sbPosting.append("<BR><BR>");
					sbPosting.append(rs.getString("QUOTE_POSTING"));
				}
				objPosting.setStrPosting(sbPosting.toString());
				
				objPosting.setStrNickName(rs.getString("NICKNAME"));
				objPosting.setStrSignature(rs.getString("SIGNATURE"));
//				objPosting.setStrModerationStat(rs.getString("MODERATION_STAT"));
//				objPosting.setStrModeratedBy(rs.getString("MODERATED_BY")); 
				eName = OIEncrypter.encrypt(rs.getString("POSTED_BY"));
				objPosting.setStrPostedBy(eName);
				objPosting.setDtPostedDate(rs.getTime("POSTED_ON_DATE"));
 				objPosting.setStrPostedOn(rs.getString("POSTED_ON_CHAR"));
				objPosting.setStrHQReply(rs.getString("HQREPLY"));
				OIDAOMemberProfile objDao = new OIDAOMemberProfile();
				//Amended by Zhao Yu on 2008-Sep-25
				//objPosting.setTotalPost(objDao.totalPotsing(objPosting.getStrPostedBy(),con));
				objPosting.setTotalPost(objDao.totalPotsing(rs.getString("POSTED_BY"),con));
				alPostsList.add(objPosting);
  			
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadPostsList() "+se.getMessage());
			throw se;
		} 
		catch(Exception se) 
		{
		    logger.error(" getThreadPostsList() "+se.getMessage());
			//throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alPostsList;
	}
	
	public OIBACatBoardTopic getThreadNavigation(Connection con, String strThreadId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBACatBoardTopic objCatBoardTopic = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.THREAD_CAT_BRD_TPC_INFO);
			pstmt.setString(1, strThreadId);
			logger.debug("^^^^^^^ strThreadId"+strThreadId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objCatBoardTopic = new OIBACatBoardTopic(); 
				objCatBoardTopic.setStrCategoryId(rs.getString("CATEGORYID"));
				objCatBoardTopic.setStrCategory(rs.getString("CATEGORY"));
				objCatBoardTopic.setStrBoardId(rs.getString("BOARDID"));
				objCatBoardTopic.setStrBoard(rs.getString("BOARD"));
				objCatBoardTopic.setStrTopicId(rs.getString("TOPICID"));
				objCatBoardTopic.setStrTopic(rs.getString("TOPIC"));
				objCatBoardTopic.setStrThreadId(rs.getString("THREADID"));
				objCatBoardTopic.setStrThread(rs.getString("THREAD"));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadNavigation() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objCatBoardTopic;
	}
	
	public ArrayList getThreadPostsListForModeration(Connection con, String strUserId, boolean isThreadMod, boolean isAdmin) throws SQLException 
	{
		ArrayList alTopicThreadPostList = new ArrayList();
		ArrayList alPostsList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAModThreadPosting objModThreadPost = null;
		OIBAPosting objPosting = null;
		String strBoardId = "";
		String strTopicId = "";
		
		try 
		{
			if(isAdmin)	
			{
				pstmt = con.prepareStatement(OIForumThreadPostsSqls.ADMIN_MOD_REQ_THREADS_POSTS_LIST);
				pstmt.setString(1, (isThreadMod)?"N":"Y");
			} 
			else 
			{
				pstmt = con.prepareStatement(OIForumThreadPostsSqls.MOD_REQ_THREADS_POSTS_LIST);
				pstmt.setString(1, strUserId);
				pstmt.setString(2, (isThreadMod)?"N":"Y");
			}
			rs = pstmt.executeQuery();
			
			logger.info("163 "+OIForumThreadPostsSqls.MOD_REQ_THREADS_POSTS_LIST);
			
			while(rs.next())	
			{
				logger.info("167 "+OIForumThreadPostsSqls.MOD_REQ_THREADS_POSTS_LIST);				
				
				if(!strTopicId.equals(rs.getString("TOPICID"))) 
				{
					logger.info("171 ");
					
					if(alPostsList != null) 
					{
						logger.info("175 ");
						OIDAOPosting.assignExpressions(alPostsList);
						objModThreadPost.setAlPostsList(alPostsList);
						alTopicThreadPostList.add(objModThreadPost);
					}
					objModThreadPost = new OIBAModThreadPosting();
					objModThreadPost.setStrTopicName(rs.getString("TOPICNAME"));
					objModThreadPost.setStrBoardName(rs.getString("BOARDNAME"));
					if(!strBoardId.equals(rs.getString("BOARDID"))) 
					{
						strBoardId = rs.getString("BOARDID");
						objModThreadPost.setNextBoard(true);
						logger.info("187 ");
					}
					alPostsList = new ArrayList();
					strTopicId = rs.getString("TOPICID");
				}
				objPosting = new OIBAPosting();
				objPosting.setStrThreadId(rs.getString("THREADID"));
				objPosting.setStrPostId(rs.getString("POSTID"));
				objPosting.setStrThread(rs.getString("THREADNAME"));
				objPosting.setStrPosting(rs.getString("POSTING"));
				objPosting.setStrNickName(rs.getString("NICKNAME"));
				objPosting.setStrSignature(rs.getString("SIGNATURE"));
//				objPosting.setStrModerationStat(rs.getString("MODERATION_STAT"));
//				objPosting.setStrModeratedBy(rs.getString("MODERATED_BY")); 
				objPosting.setStrPostedBy(rs.getString("POSTED_BY"));
				objPosting.setStrPostedOn(rs.getString("POSTED_ON"));
				objPosting.setStrHQReply(rs.getString("HQREPLY"));	
				alPostsList.add(objPosting);
			}
			//logger.info("alPostsList "+alPostsList);
			//logger.info("alPostsList "+alPostsList.size());
			if(alPostsList != null) 
			{
				OIDAOPosting.assignExpressions(alPostsList);
				objModThreadPost.setAlPostsList(alPostsList);
				alTopicThreadPostList.add(objModThreadPost);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadPostsList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alTopicThreadPostList;
	}
	
	public int getThreadPostsCount(Connection con, String strThreadId) throws SQLException 
	{
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		return OISQLUtilities.getNumber(con, OIForumThreadPostsSqls.THREAD_POSTS_CNT, alThread, "getThreadPostsCount", "OIDAOThread");
	}
	
	public ArrayList getTopicHierarchy(Connection con) throws SQLException 
	{
		ArrayList alTopicHrcy = new ArrayList();
		OIBATopicHrcy objTopicHrcy = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.CAT_BOARD_TOPIC_HRCY);
			rs = pstmt.executeQuery();
			logger.debug("inside getTopicHrcy");
			while(rs.next())	
			{
				objTopicHrcy = new OIBATopicHrcy(); 
				objTopicHrcy.setStrId(rs.getString("ID"));
				objTopicHrcy.setStrTitle(rs.getString("NAME"));
				objTopicHrcy.setLevel(rs.getInt("LEVEL"));
				alTopicHrcy.add(objTopicHrcy);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getTopicHierarchy() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alTopicHrcy;
	}

	public String getNewThreadId(Connection con) throws SQLException 
	{
		return OISQLUtilities.getString(con, OIForumThreadPostsSqls.NEW_THREAD_ID, "getNewThreadId", "OIDAOThread");
	}

	public boolean createThread(Connection con, OIBAThread objThread) throws SQLException 
	{
		boolean createFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThread.getStrThreadId());
		alThread.add(objThread.getStrTopicId());
		alThread.add(objThread.getStrTitle());
		alThread.add(objThread.getStrModerationReq());
		alThread.add(objThread.getStrModerationStat());
		alThread.add(objThread.getStrLastpostId());
		alThread.add(objThread.getStrSecurity());
		alThread.add(objThread.getStrUserId());
		createFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.CREATE_THREAD, alThread, "createThread", "OIDAOThread") == 1);
		
		try{
			String pUserId = objThread.getStrUserId();
			OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
	        aOIBAWebSiteRanking.setActionId("PT");
	        aOIBAWebSiteRanking.setUserId(pUserId);
	        aOIBAWebSiteRanking.setItemId(Integer.parseInt(objThread.getStrThreadId()));
	        new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,con);
		} 
		catch(Exception e)
	    {
	        String error = e.getMessage();
	        logger.error(e);
	        try
	        {
	        	con.rollback();
	        }catch (Exception ex){}
	    }
        
        return createFlag;
	}

	public boolean modifyThread(Connection con, OIBAThread objThread) throws SQLException 
	{
		boolean modifyFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThread.getStrTitle());
		alThread.add(objThread.getStrModerationReq());
		alThread.add(objThread.getStrSecurity());
		alThread.add(objThread.getStrThreadId());
		modifyFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.MODIFY_THREAD, alThread, "modifyThread", "OIDAOThread") == 1);
		return modifyFlag;
	}

	public boolean modifyModarateThread(Connection con, OIBAThread objThread) throws SQLException 
	{
		boolean modifyFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThread.getStrTitle());
		alThread.add(objThread.getStrThreadId());
		modifyFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.MODIFY_MOD_THREAD, alThread, "modifyModarateThread", "OIDAOThread") == 1);
		return modifyFlag;
	}
	
	public boolean updateLastpostInfo(Connection con, String strThreadId, String strPostId) throws SQLException 
	{
		boolean updateFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strPostId);
		alThread.add(strThreadId);
		updateFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.MODIFY_LAST_POST_INFO, alThread, "updateLastpostInfo", "OIDAOThread") == 1);
		return updateFlag;
	}

	public boolean updateThreadStatistics(Connection con, String[] aryModifyThreadIds, String strUserId) throws SQLException 
	{
		boolean updateFlag = false;
		ArrayList alThreadList = new ArrayList();
		ArrayList alThread = null;
		for(int i=0; i<aryModifyThreadIds.length; i++) 
		{
			alThread = new ArrayList();
			alThread.add(aryModifyThreadIds[i]);
			alThread.add(aryModifyThreadIds[i]);
			alThread.add(aryModifyThreadIds[i]);
			alThread.add(aryModifyThreadIds[i]);
			alThreadList.add(alThread);
		}
		logger.debug("updateThreadStatistics  :  "+alThreadList);		
		updateFlag = (OISQLUtilities.executeMultiple(con, OIForumThreadPostsSqls.MODIFY_MODERATION_LAST_POST_INFO, alThreadList, "updateThreadStatistics", "OIDAOThread") >= 0);
		logger.debug("updateFlag  :  "+updateFlag);		
		return updateFlag;
	}
	
	public boolean updateModerataionLastpostInfo(Connection con, String strThreadId) throws SQLException 
	{
		boolean updateFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		alThread.add(strThreadId);
		alThread.add(strThreadId);
		alThread.add(strThreadId);
		updateFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.MODIFY_MODERATION_LAST_POST_INFO, alThread, "updateModerataionLastpostInfo", "OIDAOThread") == 1);
		return updateFlag;
	}

	public boolean isThreadModerationReq(Connection con, String strThreadId) throws SQLException 
	{
		boolean modReqFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		modReqFlag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.THREAD_MODERATION_REQ, alThread, "isThreadModerationReq", "OIDAOThread");
		return modReqFlag;
	}

	public boolean isThreadTopicModerationReq(Connection con, String strTopicId) throws SQLException 
	{
		boolean modReqFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strTopicId);
		modReqFlag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.THREAD_TOPIC_MODERATION_REQ, alThread, "isThreadTopicModerationReq", "OIDAOThread");
		return modReqFlag;
	}
	
	public ArrayList getGroupsList(Connection con) throws SQLException 
	{
		ArrayList alGroups = new ArrayList();
		LabelValueBean objLabelValue = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.GROUPS_LIST);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objLabelValue = new LabelValueBean(); 
				objLabelValue.setValue(rs.getString("GROUPID"));
				objLabelValue.setLabel(rs.getString("NAME"));
				alGroups.add(objLabelValue);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getGroupsList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alGroups;	
	}
	
	public String[] getThreadGroupsList(Connection con, String strThreadId) throws SQLException 
	{
		ArrayList alGroupIds = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.THREAD_GROUPS_LIST);
			pstmt.setString(1, strThreadId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				alGroupIds.add(rs.getString("GROUPID"));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadGroupsList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return (String[])alGroupIds.toArray(new String[alGroupIds.size()]);	
	}
	
	public boolean setGroupsToThread(Connection con, String strThreadId, String[] aryGroups) throws SQLException 
	{
		boolean setFlag = true;
		String strGroups = OIUtilities.arrayToString(aryGroups);
		String strFinalGroups = "";
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		if(strGroups.length() <= 0) strFinalGroups = "0";
		else strFinalGroups = strGroups;
		setFlag = (OISQLUtilities.executeSingle(con, (OIForumThreadPostsSqls.DELETE_THREAD_GROUPS).replaceFirst(":GRPLST:", strFinalGroups), alThread, "setGroupsToThread(delete)", "OIDAOThread") >= 0);
		logger.debug("setGroupsToThread() after deletion of groups : Parameters : "+alThread);
		alThread.add(strThreadId);
		if(setFlag && strGroups.length() > 0) 
			setFlag = (OISQLUtilities.executeSingle(con, (OIForumThreadPostsSqls.INSERT_THREAD_GROUPS).replaceFirst(":GRPLST:", strFinalGroups), alThread, "setGroupsToThread(create)", "OIDAOThread") >= 1);
		logger.debug("setGroupsToThread() after insertion of groups : Parameters : "+alThread);
		return setFlag;
	}

	public boolean deleteThread(Connection con, String strThreadId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_THREAD, alThread, "deleteThread", "OIDAOThread") == 1);
		return deleteFlag;
	}	
	
	public boolean deleteThreadAudit(Connection con, String strThreadId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_AUDIT_THREAD, alThread, "deleteThreadAudit", "OIDAOThread") >= 0);
		return deleteFlag;
	}	

	public boolean deleteThreadBookMarks(Connection con, String strThreadId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_THREAD_BOOKMARKS_STICKY, alThread, "deleteThreadBookmarks", "OIDAOThread") >= 0);
		return deleteFlag;
	}
	
	public boolean deleteThreadPosts(Connection con, String strThreadId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_THREAD_POSTS, alThread, "deleteThreadPosts", "OIDAOThread") >= 0);
		return deleteFlag;
	}	
	
	public boolean lockUnlockThread(Connection con, String strThreadId) throws SQLException 
	{
		boolean lockFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		lockFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.UPDATE_THREAD_LOCK, alThread, "lockUnlockThread", "OIDAOThread") == 1);
		return lockFlag;
	}

	public boolean isStickyThreadAllowed(Connection con, String strUserId) throws SQLException 
	{
		boolean flag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strUserId);
		flag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.STICKY_THREAD_ALLOWED, alThread, "isStickyThreadAllowed", "OIDAOThread");
		return flag;
	}
	
	public boolean isBookMarkExists(Connection con, OIBAThread objThreadVo) throws SQLException 
	{
		boolean flag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThreadVo.getStrUserId());
		alThread.add(objThreadVo.getStrThreadId());
		alThread.add(OIForumConstants.BOOKMARK_THREAD);
		flag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.BOOKMARK_THREAD_EXISTS, alThread, "isBookMarkExists", "OIDAOThread");
		return flag;
	}
	
	public boolean isStickyExists(Connection con, OIBAThread objThreadVo) throws SQLException 
	{
		boolean flag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThreadVo.getStrUserId());
		alThread.add(objThreadVo.getStrThreadId());
		alThread.add(OIForumConstants.STICKY_THREAD);
		flag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.STICKY_THREAD_EXISTS, alThread, "isStickyExists", "OIDAOThread");
		return flag;
	}

	public boolean isAdminStickyExists(Connection con, OIBAThread objThreadVo) throws SQLException 
	{
		boolean flag = false;
 		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.ADMIN_STICKY_THREAD_EXISTS);
 			pstmt.setString(1, OIForumConstants.ADMIN_STICKY_THREAD);
 			rs = pstmt.executeQuery();
			if(rs.next())	
			{
				flag = true;
				objThreadVo.setStrBookMarkId(rs.getString(2));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" isAdminStickyExists() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		
		
 		return flag;
	}

//Check for spl thread exists
	public boolean isSplStickyExists(Connection con, OIBAThread objThreadVo) throws SQLException 
	{
		boolean flag = false;
 		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.ADMIN_STICKY_THREAD_EXISTS);
 			pstmt.setString(1, OIForumConstants.SPL_STICKY_THREAD);
 			rs = pstmt.executeQuery();
			if(rs.next())	
			{
				flag = true;
				objThreadVo.setStrBookMarkId(rs.getString(2));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" isSplStickyExists() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		} 
 		return flag;
	}
	
	
	public boolean isBookMarkAllowed(Connection con, String strUserId) throws SQLException 
	{
		boolean flag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strUserId);
		flag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.BOOKMARK_THREAD_ALLOWED, alThread, "isBookMarkAllowed", "OIDAOThread");
		return flag;
	}
	
	public boolean updateStickyBmarkThread(Connection con, OIBAThread objThreadVo, String strType) throws SQLException 
	{
		boolean stkBmkFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThreadVo.getStrUserId());
		alThread.add(objThreadVo.getStrThreadId());
		alThread.add(strType);
		stkBmkFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.STICKY_BOOKMARK_THREAD, alThread, "updateStickyBmarkThread", "OIDAOThread") == 1);
		return stkBmkFlag;
	}
	public boolean insertAdminStickyBmarkThread(Connection con, OIBAThread objThreadVo, String strType) throws SQLException 
	{
		boolean stkBmkFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThreadVo.getStrUserId());
		alThread.add(objThreadVo.getStrThreadId());
		alThread.add(strType);
		stkBmkFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.INSERT_STICKY_ADMIN_BOOKMARK_THREAD, alThread, "insertAdminStickyBmarkThread", "OIDAOThread") == 1);
		return stkBmkFlag;
	}

	public boolean updateAdminStickyBmarkThread(Connection con, OIBAThread objThreadVo, String strType) throws SQLException 
	{
		boolean stkBmkFlag = false;
		ArrayList alThread = new ArrayList();
 		alThread.add(objThreadVo.getStrThreadId());
 		alThread.add(objThreadVo.getStrBookMarkId());
 		stkBmkFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.UPDATE_STICKY_ADMIN_BOOKMARK_THREAD, alThread, "updateAdminStickyBmarkThread", "OIDAOThread") == 1);
		return stkBmkFlag;
	}
//Special Sticky Thread
	public boolean insertSplStickyBmarkThread(Connection con, OIBAThread objThreadVo, String strType) throws SQLException 
	{
		boolean stkBmkFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThreadVo.getStrUserId());
		alThread.add(objThreadVo.getStrThreadId());
		alThread.add(strType);
		stkBmkFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.INSERT_STICKY_ADMIN_BOOKMARK_THREAD, alThread, "insertSplStickyBmarkThread", "OIDAOThread") == 1);
		return stkBmkFlag;
	}

	public boolean updateSplStickyBmarkThread(Connection con, OIBAThread objThreadVo, String strType) throws SQLException 
	{
		boolean stkBmkFlag = false;
		ArrayList alThread = new ArrayList();
 		alThread.add(objThreadVo.getStrThreadId());
 		alThread.add(objThreadVo.getStrBookMarkId());
 		stkBmkFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.UPDATE_STICKY_ADMIN_BOOKMARK_THREAD, alThread, "updateSplStickyBmarkThread", "OIDAOThread") == 1);
		return stkBmkFlag;
	}
	
	
	
	public boolean moveThreadToAnotherTopic(Connection con, OIBAThread objThreadVo) throws SQLException 
	{
		boolean moveFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(objThreadVo.getStrTopicId());
		alThread.add(objThreadVo.getStrThreadId());
		moveFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.MOVE_THREAD_TO_TOPIC, alThread, "moveThreadToAnotherTopic", "OIDAOThread") == 1);
		return moveFlag;
	}
	
	public ArrayList getThreadPostsListForView(Connection con, String strThreadId) throws SQLException 
	{
		ArrayList alPostsList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAPosting objPosting = null;
		
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.PRINT_THREAD_POSTS_LIST);
			pstmt.setString(1, strThreadId);
			logger.debug(" getThreadPostsListForView : Thread Id : "+strThreadId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objPosting = new OIBAPosting();
				objPosting.setStrPosting(rs.getString("POSTING"));
				objPosting.setStrNickName(rs.getString("NICKNAME"));
				objPosting.setStrSignature(rs.getString("SIGNATURE"));
				objPosting.setStrPostedOn(rs.getString("POSTED_ON"));
				objPosting.setStrHQReply(rs.getString("HQREPLY"));	
				alPostsList.add(objPosting);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadPostsListForView() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alPostsList;
	}

	public boolean modifyThreadsModerationStatus(Connection con, String[] aryThreadIds, String strUserId) throws SQLException 
	{
		boolean updateFlag = false;
		ArrayList alThread = new ArrayList();
		String strThreadIds = OIUtilities.arrayToString(aryThreadIds);
		logger.debug(" modifyThreadsModerationStatus  : "+strThreadIds);
		alThread.add(strUserId);
		updateFlag = (OISQLUtilities.executeSingle(con, (OIForumThreadPostsSqls.UPDATE_THREAD_MODERATION).replaceFirst(":THREADIDS:", strThreadIds), alThread, "modifyThreadsModerationStatus", "OIDAOThread") >= 1);
		return updateFlag;
	}

	public boolean deleteRejectedThreads(Connection con, String[] aryThreadIds, String strUserId) throws SQLException 
	{
		boolean updateFlag = false;
		String strThreadIds = OIUtilities.arrayToString(aryThreadIds);
		logger.debug(" deleteRejectedThreads : "+strThreadIds);
		updateFlag = (OISQLUtilities.execute(con, (OIForumThreadPostsSqls.REJECT_THREAD_GROUPS_MODERATION).replaceFirst(":THREADIDS:", strThreadIds), "deleteRejectedThreads", "OIDAOThread") >= 0);
		if(updateFlag) updateFlag = (OISQLUtilities.execute(con, (OIForumThreadPostsSqls.REJECT_THREAD_AUDIT_MODERATION).replaceFirst(":THREADIDS:", strThreadIds), "deleteRejectedThreads", "OIDAOThread") >= 0);
		if(updateFlag) updateFlag = (OISQLUtilities.execute(con, (OIForumThreadPostsSqls.REJECT_THREAD_MODERATION).replaceFirst(":THREADIDS:", strThreadIds), "deleteRejectedThreads", "OIDAOThread") >= 1);
		return updateFlag;
	}

	public boolean setThreadsAudit(Connection con, String strUserId, String strTitle, String strThreadId) throws SQLException 
	{
		boolean auditFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strUserId);
		alThread.add(strTitle);
		alThread.add(strThreadId);
		alThread.add(strThreadId);
		auditFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.AUDIT_MOD_THREAD_INFO, alThread, "setThreadsAudit", "OIDAOThread") == 1);
		return auditFlag;
	}

	public boolean isThreadUserBoardSame(Connection con, String strThreadId, String strUserId) throws SQLException 
	{
		boolean isSameFlag = false;
		ArrayList alThread = new ArrayList();
		alThread.add(strThreadId);
		alThread.add(strUserId);
		isSameFlag = OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.THREAD_BOARD_SAME, alThread, "isThreadUserBoardSame", "OIDAOThread");
		return isSameFlag;
	}

	public boolean isAllowPersonalMessage(Connection con) throws SQLException 
	{
		return OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.ALLOW_PERSONAL_MESSAGE, "isAllowPersonalMessage", "OIDAOThread");
	}

	public boolean isAllowAlertAdmin(Connection con) throws SQLException 
	{
		return OISQLUtilities.checkExists(con, OIForumThreadPostsSqls.ALLOW_ALERT_ADMIN, "isAllowAlertAdmin", "OIDAOThread");
	}

	public void setPrevNextThreadInfo(Connection con, OIBAThread objThread) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strTempThread = null;
		boolean isSetPrevThread = false;
		boolean isSetNextThread = false;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.PREV_NEXT_THREAD);
			pstmt.setString(1, objThread.getStrUserId());
			pstmt.setString(2, objThread.getStrUserId());
			pstmt.setString(3, objThread.getStrUserId());
			pstmt.setString(4, objThread.getStrTopicId());
			rs = pstmt.executeQuery();

			while(!isSetNextThread && rs.next())	
			{
				if(!isSetPrevThread && rs.getString("THREADID").equals(objThread.getStrThreadId())) 
				{
					objThread.setStrPrevThread(strTempThread);
					isSetPrevThread = true;
				} 
				else if(isSetPrevThread) 
				{
					objThread.setStrNextThread(rs.getString("THREADID"));
					isSetNextThread = true;
				}
				strTempThread = rs.getString("THREADID");
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" setPrevNextThreadInfo() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
	}

	public ArrayList getMailIdsofBookmarkUsers(Connection con, String strThreadId) throws Exception 
	{
		ArrayList arBKMails = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.BOOKMARK_MAILIDS);
			pstmt.setString(1, strThreadId);
			logger.info("getMailIdsofBookmarkUsers : Thread Id : "+strThreadId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
			    if(rs.getString("EMAILID")!=null)
			    {
			        arBKMails.add(rs.getString("EMAILID"));
			    }
			}
		} 
		catch(Exception se) 
		{
		    logger.error("getMailIdsofBookmarkUsers() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		if (arBKMails.size()==0)
		{
		    arBKMails=null;
		}
		
		return arBKMails;
	}
	public OIBACatBoardTopic getNavigation(Connection con, String strTopicId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBACatBoardTopic objCatBoardTopic = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.THREAD_CAT_BRD_INFO);
			pstmt.setString(1, strTopicId);
			logger.debug("^^^^^^^ strTopicId"+strTopicId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objCatBoardTopic = new OIBACatBoardTopic(); 
				objCatBoardTopic.setStrCategoryId(rs.getString("CATEGORYID"));
				objCatBoardTopic.setStrCategory(rs.getString("CATEGORY"));
				objCatBoardTopic.setStrBoardId(rs.getString("BOARDID"));
				objCatBoardTopic.setStrBoard(rs.getString("BOARD"));
				objCatBoardTopic.setStrTopicId(rs.getString("TOPICID"));
				objCatBoardTopic.setStrTopic(rs.getString("TOPIC"));
				objCatBoardTopic.setStrThreadId("");
				objCatBoardTopic.setStrThread("");
				objCatBoardTopic.setStrTopicDesc(rs.getString("TOPICDESC"));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadNavigation() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objCatBoardTopic;
	}
	//Get the spl stick thread
	public ArrayList getThreadIDAndName(Connection con) throws SQLException 
	{
		ArrayList al=new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strThreadID = "";
		String strThreadName = "";
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.GET_THRD_ID);
			pstmt.setString(1, OIForumConstants.SPL_STICKY_THREAD);
  			rs = pstmt.executeQuery();
  			
			if(rs.next())	
			{
				 strThreadID = rs.getString("THREADID");
				 strThreadName = rs.getString("THREADNAME");
			}
		} 
		catch(SQLException se) 
		{
			//System.out.println("no eeerr");
		    logger.error(" getThreadIDAndName() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		al.add(strThreadID);
		al.add(strThreadName);
		return al;
	}
	
	//Get the spl stick thread
	public String getThreadID(Connection con) throws SQLException 
	{
		ArrayList al=new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strThreadID = "";
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.GET_THRD_ID);
			pstmt.setString(1, OIForumConstants.SPL_STICKY_THREAD);
  			rs = pstmt.executeQuery();
  			
			if(rs.next())	
			{
				 strThreadID = rs.getString("THREADID");
			}
		} 
		catch(SQLException se) 
		{
			//System.out.println("no eeerr");
		    logger.error(" getThreadID() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return strThreadID;
	}

	/* Added/Modified by Aik Mun @ Jan 16, 2009 
	 * Mark Post as Read or Unread
	 * */
	public boolean markReadStatus(Connection con, ArrayList alPostList, String strStatus) throws SQLException 
	{
		boolean markFlag = false;
		if(strStatus.equalsIgnoreCase("R")){
			markFlag = (OISQLUtilities.executeMultiple(con, OIForumThreadPostsSqls.INSERT_MARK_READED, alPostList, "markReadStatus", "OIDAOThread") != 0);
		}else{
			markFlag = (OISQLUtilities.executeMultiple(con, OIForumThreadPostsSqls.DELETE_MARK_READED, alPostList, "markReadStatus", "OIDAOThread") != 0);
		}
		        
        return markFlag;
	}
	
	/* Added/Modified by Aik Mun @ Jan 19, 2009
	 * Check Post is readed or not
	 *  */
	public StringBuffer checkReadStatus(Connection con, String strPostList, String strThread, String strUserId) throws SQLException{
		StringBuffer sbPost = new StringBuffer();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			StringBuffer sbQuery = new StringBuffer();
			sbQuery.append(OIForumThreadPostsSqls.CHECK_READED).append(strPostList).append(")");
			pstmt = con.prepareStatement(sbQuery.toString());
			pstmt.setString(1, strUserId);
			pstmt.setString(2, strThread);
			
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				sbPost.append(rs.getString("POSTID")).append(",");
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" checkReadStatus() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return sbPost;
	}
	
	//  Added by K.K.Kumaresan on Sep 16,2009
	/**
	 * 
	 * @param con
	 * @param strThreadId
	 * @param userId
	 * @return
	 * @throws SQLException
	 */
	public boolean isValidUser(Connection con, String strThreadId,String userId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean validUser=true;
		try 
		{
			String strQuery = "SELECT count(*) count FROM OI_FM_GROUPMEMBERS MEMBERS,OI_FM_THREAD_GROUPS GRP " +
					" WHERE  GRP.groupid=MEMBERS.groupid AND threadid=? AND USERID=? ";
			pstmt = con.prepareStatement(strQuery);
			logger.info(" strQuery "+strQuery);
			pstmt.setString(1, strThreadId);
			pstmt.setString(2, userId);
			logger.info(" strThreadId "+strThreadId);
			logger.info(" userId "+userId);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				String count=rs.getString("count");
				if(count!=null && count.equals("0"))		// If the count is zero. User is not a valid user
					validUser=false;
				else
					validUser=true;
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getThreadPostsList() "+se.getMessage());
			throw se;
		} 
		catch(Exception se) 
		{
		    logger.error(" getThreadPostsList() "+se.getMessage());
			//throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return validUser;
	}
}