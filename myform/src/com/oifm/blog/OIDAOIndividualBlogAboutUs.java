package com.oifm.blog;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOIndividualBlogAboutUs extends OIBaseDao
{
	private static final Logger logger = Logger.getLogger(OIDAOIndividualBlogAboutUs.class);
	
	public OIDAOIndividualBlogAboutUs () {super();}
	
	public ArrayList getAuthorDetailsList(Connection connection, Integer blogId) throws Exception
	{
		ArrayList alList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAIndividualBlog objBA = null;
		
		try
		{
			pst = connection.prepareStatement(OIIndividualBlogSqls.QRY_AUTHOR_DETAILS_LIST);
			pst.setInt(1, blogId.intValue());
			rs = pst.executeQuery();
			while (rs.next())
			{
				objBA = new OIBAIndividualBlog();
				objBA.setBlogId(Integer.valueOf(rs.getString("BLOG_ID")));
				objBA.setAuthorUserId(rs.getString("USERID"));
				objBA.setAuthorUserName(rs.getString("NAME"));
				objBA.setAuthorNotifyEmail(rs.getString("NOTIFY_EMAIL"));
				objBA.setAuthorDescription(OIUtilities.clobToString(rs.getClob("ABOUT_ME")));
				objBA.setAuthorDescription(objBA.getAuthorDescription().replaceAll("\n", "<br />"));
				objBA.setAuthorImageFileName(rs.getString("ABOUT_IMAGE"));
				
				alList.add(objBA);
			}
		}
		catch (Exception e)
		{
			logger.error("getAuthorDetailsList() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return alList;
	}
	
	public ArrayList getAuthorList (Connection connection, Integer blogId) throws Exception
	{
		ArrayList alList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAIndividualBlog objBA = null;
		
		try
		{
			pst = connection.prepareStatement(OIBlogSqls.GET_BLOG_AUTHORS);
			pst.setInt(1, blogId.intValue());
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				objBA = new OIBAIndividualBlog();
				
				objBA.setAuthorUserName(rs.getString("NAME"));
				
				alList.add(objBA);
			}
		}
		catch (Exception e)
		{
			connection.rollback();
			logger.error("getAuthorList() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return alList;
	}
	
	public OIBAIndividualBlog getAuthorDetails(Connection connection, Integer blogId, String userId) throws Exception
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAIndividualBlog objBA = new OIBAIndividualBlog();
		
		try
		{
			pst = connection.prepareStatement(OIIndividualBlogSqls.QRY_AUTHOR_DETAILS);
			pst.setInt(1, blogId.intValue());
			pst.setString(2, userId);
			rs = pst.executeQuery();
			if (rs.next())
			{
				objBA.setBlogId(Integer.valueOf(rs.getString("BLOG_ID")));
				objBA.setAuthorUserId(rs.getString("USERID"));
				objBA.setAuthorUserName(rs.getString("NAME"));
				objBA.setAuthorNotifyEmail(rs.getString("NOTIFY_EMAIL"));
				objBA.setAuthorDescription(OIUtilities.clobToString(rs.getClob("ABOUT_ME")));
				objBA.setAuthorImageFileName(rs.getString("ABOUT_IMAGE"));
			}
		}
		catch (Exception e)
		{
			logger.error("getAuthorDetails() : " + e);
			throw e;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return objBA;
	}
	
	public void updateAuthorDetails(Connection connection, Integer blogId, String userId, String notifyEmail, String description, String photoFileName) throws Exception
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		CLOB clob = null;
		
		try
		{
			connection.setAutoCommit(false);
			
			pst = connection.prepareStatement(OIIndividualBlogSqls.QRY_AUTHOR_EDIT_DETAILS);
			pst.setString(1, notifyEmail);
			pst.setString(2, photoFileName);
			pst.setInt(3, blogId.intValue());
			pst.setString(4, userId);
			pst.executeUpdate();
			
			pst = connection.prepareStatement(OIIndividualBlogSqls.QRY_AUTHOR_GET_ABOUT_ME_FOR_UPDATE);
			pst.setInt(1, blogId.intValue());
			pst.setString(2, userId);
			rs = pst.executeQuery();
			
			if (rs.next())
			{
				clob = (CLOB) rs.getClob("ABOUT_ME");
				
				if (clob != null)
					OIUtilities.writeClob(clob, description);
			}
			
			connection.commit();
			
		}
		catch (Exception e)
		{
			connection.rollback();
			logger.error("updateAuthorDetails() : " + e);
			throw e;
		}
		finally
		{
			connection.setAutoCommit(true);
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
	}
	
	public boolean removePhoto(Connection conn, Integer blogId, String userId) throws Exception {
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(OIIndividualBlogSqls.QRY_AUTHOR_REMOVE_PICTURE);
			pst.setInt(1, blogId.intValue());
			pst.setString(2, userId);
			
			count = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("removePhoto() - " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count == 1;
	}
}
