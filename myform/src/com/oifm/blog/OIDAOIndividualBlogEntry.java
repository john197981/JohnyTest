package com.oifm.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOIndividualBlogEntry extends OIBaseDao
{
	private static final Logger logger = Logger.getLogger(OIDAOIndividualBlogEntry.class);
	
	public OIDAOIndividualBlogEntry () {super();}
	
	public OIBAIndividualBlog getEntryDetails(Connection conn, Integer entryId) throws Exception
	{
		OIBAIndividualBlog objBA = new OIBAIndividualBlog();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			pst = conn.prepareStatement(OIIndividualBlogSqls.QRY_BLOG_ENTRY);
			pst.setInt(1, entryId.intValue());
			rs = pst.executeQuery();
			
			if (rs.next())
			{
				objBA.setBlogId(Integer.valueOf(rs.getString("BLOG_ID")));
				objBA.setBlogTitle(rs.getString("BLOG_TITLE"));
				objBA.setEntryId(Integer.valueOf(rs.getString("ENTRY_ID")));
				objBA.setTitle(rs.getString("TITLE"));
				objBA.setAuthorUserName(rs.getString("NAME"));
				objBA.setDate(rs.getString("CREATED_ON"));
				objBA.setCommentAllowed(rs.getString("ALLOW_COMMENTS").trim().equalsIgnoreCase("Y"));
				objBA.setEntryExcerpt(rs.getString("ENTRY_EXCEPT"));
				objBA.setEntryBody(OIUtilities.clobToString(rs.getClob("ENTRY_BODY")));
				objBA.setPicFileName(rs.getString("IMAGE_NAME"));
			}
			//added by Deva
			OISQLUtilities.closeRsetPstatement(rs, pst);
			pst = conn.prepareStatement(OIBlogSqls.GET_ENTRY_TAG);
			pst.setInt(1,objBA.getEntryId().intValue());
			rs = pst.executeQuery();
			
			ArrayList tagList = new ArrayList();
			
			while(rs.next()){
				tagList.add(rs.getString("NAME"));
			}
			objBA.setTagList(tagList);
		}
		catch (Exception e)
		{
			logger.error("getEntryDetails() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return objBA;
	}
	
	public ArrayList getEntryComments (Connection conn, Integer entryId) throws Exception
	{
		ArrayList alList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAIndividualBlog objBA = null;
		
		try
		{
			pst = conn.prepareStatement(OIIndividualBlogSqls.QRY_BLOG_ENTRY_COMMENTS);
			pst.setInt(1, entryId.intValue());
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				objBA = new OIBAIndividualBlog();
				
				objBA.setComment(rs.getString("MESSAGE"));
				objBA.setCommentUser(rs.getString("NICKNAME"));
				objBA.setCommentDate(rs.getString("CREATED_ON"));
				
				alList.add(objBA);
			}
		}
		catch (Exception e)
		{
			logger.error("getEntryComments() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return alList;
	}
	
	public void saveComment (Connection conn, Integer blogId, Integer entryId, String comment, String userId) throws Exception
	{
		PreparedStatement pst = null;
		
		try
		{
			pst = conn.prepareStatement(OIIndividualBlogSqls.QRY_SAVE_COMMENT);
			pst.setInt(1, entryId.intValue());
			pst.setInt(2, blogId.intValue());
			pst.setString(3, comment);
			pst.setString(4, userId);
			pst.executeUpdate();
		}
		catch (Exception e)
		{
			logger.error("saveComment() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closePstatement(pst);
		}
	}
	
	public LinkedHashMap getNotificationMailRecipient (Connection connection, Integer blogId) throws Exception
	{
		LinkedHashMap hashMap = new LinkedHashMap();
		ArrayList alList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		
		try
		{
			pst = connection.prepareStatement(OIIndividualBlogSqls.QRY_NOTIFY_EMAIL_RECIPIENTS);
			pst.setInt(1, blogId.intValue());
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				alList.add(rs.getString("EMAILID"));
				
				if (alList.size() == 10)
				{
					hashMap.put("SET" + count, alList);
					count++;
					alList = new ArrayList();
				}
			}
			
			if (alList.size() > 0)
				hashMap.put("SET" + count, alList);
		}
		catch (Exception e)
		{
			logger.error("getNotificationMailRecipient() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return hashMap;
	}
	
	public HashMap getNotificationMailSubjectBody (Connection connection) throws Exception
	{
		HashMap hashMap = new HashMap();
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			pst = connection.prepareStatement(OIIndividualBlogSqls.QRY_NOTIFY_EMAIL_SUBJECT_BODY);
			rs = pst.executeQuery();
			
			if (rs.next())
			{
				hashMap.put("SUBJECT", rs.getString("SUBJECT"));
				hashMap.put("MAIL_BODY", rs.getString("MAIL_BODY"));
			}
			else
			{
				hashMap.put("SUBJECT", "");
				hashMap.put("MAIL_BODY", "");
			}
		}
		catch (Exception e)
		{
			logger.error("getNotificationMailSubjectBody() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return hashMap;
	}
}
