
package com.oifm.forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.regex.PatternSyntaxException;

import org.apache.log4j.Logger;
import org.apache.struts.util.LabelValueBean;

import com.oifm.base.OIBaseDao;
import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOPosting extends OIBaseDao 
{
	private static final Logger logger = Logger.getLogger(OIDAOPosting.class);

	public OIDAOPosting()	{	}

	public String getNewPostingId(Connection con) throws SQLException 
	{
		return OISQLUtilities.getString(con, OIForumThreadPostsSqls.NEW_POSTING_ID, "getNewPostingId", "OIDAOPosting");
	}

	public boolean createPosting(Connection con, OIBAPosting objPosting) throws SQLException 
	{
		boolean createFlag = false;
		ArrayList alPosting = new ArrayList();
		alPosting.add(objPosting.getStrPostId());
		alPosting.add(objPosting.getStrThreadId());
		alPosting.add(objPosting.getStrPosting());
		alPosting.add(objPosting.getStrModerationStat());
		alPosting.add(objPosting.getStrUserId());
		alPosting.add(objPosting.getStrHQReply());
		/* Added/Modified by Aik Mun @ Jan 19, 2009 */
		alPosting.add(OIUtilities.replaceNull(objPosting.getStrQuotePost()));
		
		alPosting.add(objPosting.getStrUserId());
		createFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.CREATE_POSTING, alPosting, "createPosting", "OIDAOPosting") == 1);
		
		try{
			String pUserId = objPosting.getStrUserId();
			OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
	        aOIBAWebSiteRanking.setActionId("PM");
	        aOIBAWebSiteRanking.setUserId(pUserId);
	        aOIBAWebSiteRanking.setItemId(Integer.parseInt(objPosting.getStrPostId()));
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

	public OIBAPosting fetchPostingInfo(Connection con, String strPostId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAPosting objPosting = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.POSTING_DETAILS);
			pstmt.setString(1, strPostId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objPosting = new OIBAPosting(); 
				objPosting.setStrPostId(strPostId);
				objPosting.setStrPosting(rs.getString("POSTING"));
				objPosting.setStrThreadId(rs.getString("THREADID"));
				objPosting.setStrNickName(rs.getString("NICKNAME"));
				objPosting.setStrSignature(rs.getString("SIGNATURE"));
				objPosting.setStrPostedBy(rs.getString("POSTED_BY"));
				objPosting.setStrPostedOn(rs.getString("POSTED_ON"));
				objPosting.setStrHQReply(rs.getString("HQREPLY"));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" fetchPostingInfo() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objPosting;
	}

	public OIBAPosting fetchModPostingInfo(Connection con, String strThreadId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAPosting objPosting = null;
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.POSTING_MOD_DETAILS);
			pstmt.setString(1, strThreadId);
			rs = pstmt.executeQuery();
			while(rs.next())	
			{
				objPosting = new OIBAPosting(); 
				objPosting.setStrPostId(rs.getString("POSTID"));
				objPosting.setStrPosting(rs.getString("POSTING"));
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" fetchModPostingInfo() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objPosting;
	}

	public boolean modifyPosting(Connection con, OIBAPosting objPosting) throws SQLException 
	{
		boolean modifyFlag = false;
		ArrayList alPosting = new ArrayList();
		alPosting.add(objPosting.getStrPosting());
		alPosting.add(objPosting.getStrPostId());
 		modifyFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.MODIFY_POSTING, alPosting, "modifyPosting", "OIDAOPosting") == 1);
 		return modifyFlag;
	}

	public boolean deletePosting(Connection con, String strPostId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alPosting = new ArrayList();
		alPosting.add(strPostId);
		deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_POSTING, alPosting, "deletePosting", "OIDAOPosting") == 1);
		return deleteFlag;
	}

	public boolean deletePostingAudit(Connection con, String strPostId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alPosting = new ArrayList();
		alPosting.add(strPostId);
		deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_AUDIT_POSTING, alPosting, "deletePostingAudit", "OIDAOPosting") >= 0);
		return deleteFlag;
	}

	public boolean modifyPostsModerationStatus(Connection con, String[] aryPostIds, String strUserId) throws SQLException 
	{
		boolean updateFlag = false;
		ArrayList alPosting = new ArrayList();
		String strPostIds = OIUtilities.arrayToString(aryPostIds);
		logger.debug(" modifyPostsModerationStatus  : "+strPostIds);
		alPosting.add(strUserId);
		updateFlag = (OISQLUtilities.executeSingle(con, (OIForumThreadPostsSqls.UPDATE_POSTING_MODERATION).replaceFirst(":POSTIDS:", strPostIds), alPosting, "modifyPostsModerationStatus", "OIDAOPosting") >= 1);
		return updateFlag;
	}

	public boolean setPostsAudit(Connection con, String strUserId, String strCont, String strPostId) throws SQLException 
	{
		boolean auditFlag = false;
		ArrayList alPosting = new ArrayList();
		alPosting.add(strUserId);
		alPosting.add(strCont);
		alPosting.add(strPostId);
		alPosting.add(strPostId);
		auditFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.AUDIT_MOD_POST_INFO, alPosting, "setPostsAudit", "OIDAOPosting") == 1);
		return auditFlag;
	}

	public boolean deleteRejectedPosts(Connection con, String[] aryPostIds, String strUserId) throws SQLException 
	{
		boolean updateFlag = false;
		String strPostIds = OIUtilities.arrayToString(aryPostIds);
		logger.debug(" deleteRejectedPosts  : "+strPostIds);
		updateFlag = (OISQLUtilities.execute(con, (OIForumThreadPostsSqls.REJECT_POSTING_AUDIT_MODERATION).replaceFirst(":POSTIDS:", strPostIds), "deleteRejectedPosts", "OIDAOPosting") >= 0);
		if(updateFlag) updateFlag = (OISQLUtilities.execute(con, (OIForumThreadPostsSqls.REJECT_POSTING_MODERATION).replaceFirst(":POSTIDS:", strPostIds), "deleteRejectedPosts", "OIDAOPosting") >= 1);
		return updateFlag;
	}

	public static ArrayList getExpressionsList()  
	{
		ArrayList alExpressions = new ArrayList();
		LabelValueBean objExpression = null;
		for (int i=0; i<OIForumConstants.SMILIES_SYMBOLS.length; i++)	
		{
			objExpression = new LabelValueBean();
			objExpression.setValue(OIForumConstants.SMILIES_SYMBOLS[i]);
			objExpression.setLabel(OIForumConstants.SMILIES_ICONS[i]);
			alExpressions.add(objExpression);
		}
		return alExpressions;
	}

	public static void assignExpressions(ArrayList alPostsList) 
	{
		OIBAPosting objPosting = null;
		String strImagesRoot = OIUtilities.getUploadFilesDir("OIFM.docroot");
		String strImgPart1 = "<img src='"+strImagesRoot;
		String strImgPart2 = "' border='0'>";
		String strPosting = "";
		if(alPostsList != null && alPostsList.size() > 0)	
		{
			for (int j=0; j<alPostsList.size(); j++)	
			{
				objPosting = (OIBAPosting) alPostsList.get(j);
				strPosting = allocateExpressions(strImgPart1, strImgPart2, objPosting.getStrPosting()); 
				objPosting.setStrPosting(strPosting);
			}
		}
	}

	public static String allocateExpressions(String strImgPart1, String strImgPart2, String strPosting) 
	{
		if(strPosting != null && strPosting.length() > 0)	
		{
			for (int i=0; i<OIForumConstants.SMILIES_SYMBOLS.length; i++)	
			{
				try 
				{
					strPosting = strPosting.replaceAll(OIForumConstants.SMILIES_SYMBOLS[i], strImgPart1+OIForumConstants.SMILIES_ICONS[i]+strImgPart2);
				}
				catch(PatternSyntaxException pe) {}
			}
		}
		return strPosting;
	}
	public OIBAThread fetchThreadInfo(Connection con, String strPostId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAThread objThreadVO = new OIBAThread();
		try 
		{
			pstmt = con.prepareStatement(OIForumThreadPostsSqls.THREADNPOST_DETAILS);
			pstmt.setString(1, strPostId);
			rs = pstmt.executeQuery();
			if(rs.next())	
			{
				objThreadVO.setStrCreatedBy(rs.getString("CREATED_BY"));
				objThreadVO.setStrCreatedOn(rs.getString("CREATED_ON"));				
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" fetchPostingInfo() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objThreadVO;
	}
	
//	 Writteb by K.K.Kumaresan on Feb 17,2008.
	public boolean deleteReadInformation(Connection con, String postId,String threadId,String userId) throws SQLException 
	{
		logger.debug("In deleteReadInformation");
		
		boolean deleteFlag = false;
		try 
		{
			ArrayList alPosting = new ArrayList();
			alPosting.add(userId);
			alPosting.add(threadId);
			alPosting.add(postId);
//			logger.info("userId="+userId+" threadId="+threadId+" postId="+postId);
//			logger.info(OIForumThreadPostsSqls.DELETE_MARK_READED);
			deleteFlag = (OISQLUtilities.executeSingle(con, OIForumThreadPostsSqls.DELETE_MARK_READED, alPosting, "deletePosting", "OIDAOPosting") == 1);
		}
		catch(SQLException se) 
		{
		    logger.error(" deleteReadInformation() is "+se.getMessage());
		    se.printStackTrace();
			throw se;
		} 
		return deleteFlag;
	}

}