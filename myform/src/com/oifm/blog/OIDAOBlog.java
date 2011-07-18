package com.oifm.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOBlog extends OIBaseDao
{
	
	Logger logger = Logger.getLogger(OIDAOBlog.class);
			
	public OIDAOBlog()
	{
	}
	
	public List getBlogsList(Connection con) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlog blog = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_BLOGLIST);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				blog = new OIBABlog();
				blog.setTitle(rs.getString("TITLE"));
				blog.setBlogId(new Integer(rs.getInt("BLOG_ID")));
				blog.setTotalAuthors(new Integer(rs.getInt("TOTAL_AUTHORS")));
				blog.setTotalEntries(new Integer(rs.getInt("TOTAL_ENTRIES")));
				blog.setTotalComments(new Integer(rs.getInt("TOTAL_COMMENTS")));
				list.add(blog);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getBlogsList() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}
	
	public boolean deleteBlogs(Connection con, List blogIds) throws SQLException
	{
		boolean status = false;
		PreparedStatement pstmt = null;
		
		try
		{
			con.setAutoCommit(false); // If some fail, we want to rollback the rest
			
			pstmt = con.prepareStatement(OIBlogSqls.DELETE_BLOG);
			if (blogIds != null && blogIds.size() > 0)
			{
				int batchCount = 0;
				int executeCount = 0;
				int[] counts = null;
				
				for (Iterator iter = blogIds.iterator(); iter.hasNext();)
				{
					Integer blogId = (Integer) iter.next();
					pstmt.setInt(1, blogId.intValue());
					pstmt.addBatch();
					batchCount++;
					
					if (batchCount == 5000)
					{
						counts = pstmt.executeBatch();
						executeCount += counts.length;
						batchCount = 0;
						//pstmt = con.prepareStatement(OIBlogSqls.DELETE_BLOG);
					}
				}
				
				if (batchCount > 0)
	        	{
					counts = pstmt.executeBatch();
					executeCount += counts.length;
	        	}
				if (executeCount == blogIds.size())
				{
					status = true;
					con.commit();
				}
				else
				{
					con.rollback();
				}
			}			
		}
		catch (SQLException se)
		{
			logger.error(" deleteBlogs() " + se.getMessage());
			throw se;
		}
		finally
		{
			con.setAutoCommit(true);
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}	

	public boolean updateBlogConfig(Connection con, OIBABlogConfig config) throws SQLException
	{
		boolean status = false;
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.UPDATE_BLOG_CONFIG);
			pstmt.setInt(1, config.getNoOfPosts().intValue());
			pstmt.setInt(2, config.getNoOfFeatPosts().intValue());
			pstmt.setString(3, config.getBlogMessage());
			int count = pstmt.executeUpdate();
			if (count == 1)
			{
				status = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" updateBlogConfig() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}
	
	
	public OIBABlogConfig getBlogConfig(Connection con) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogConfig config = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_BLOG_CONFIG);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				config = new OIBABlogConfig();				
				config.setNoOfPosts(new Integer(rs.getInt("NO_OF_POSTS")));
				config.setNoOfFeatPosts(new Integer(rs.getInt("NO_OF_FEAT_POSTS")));
				config.setBlogMessage(rs.getString("BLOG_MESSAGE"));
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getBlogList() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return config;
	}
		
	public boolean createBlog(Connection con, OIBABlog blog) throws SQLException
	{
		boolean status = false;		
		PreparedStatement pstmt = null;
		PreparedStatement authPstmt = null;
		
		try
		{
			Integer blogId = getNextVal(con,OIBlogSqls.CREATE_BLOG_SEQ);
			
			pstmt = con.prepareStatement(OIBlogSqls.CREATE_BLOG);
			pstmt.setInt(1,blogId.intValue());		
			pstmt.setString(2, blog.getTitle());
			
			int count = pstmt.executeUpdate();
			if (count == 1)
			{				
				status = createBlogAuthors(con, blogId, blog.getAuthors());
				//System.out.println("OIDAOBlog: createBlog - createBlogAuthors status : "+status);
			}
			
			if(!status)
			{
				con.rollback();
			}
		}
		catch (SQLException se)
		{
			logger.error(" createBlog() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}
	
	public boolean createBlogAuthors(Connection con, Integer blogId,List authors) throws SQLException
	{
		boolean status = false;		
		PreparedStatement pstmt = null;
		
		try
		{		
			if(authors!=null && authors.size()>0)
			{			
				pstmt = con.prepareStatement(OIBlogSqls.CREATE_BLOG_AUTHOR);
				
				int batchCount = 0;
				int executeCount = 0;
				int[] counts = null;
				
				for (Iterator iter = authors.iterator(); iter.hasNext();)
				{
					OIBABlogAuthor author = (OIBABlogAuthor) iter.next();
					
					pstmt.setInt(1,blogId.intValue());		
					pstmt.setString(2, author.getUserId());
					pstmt.setString(3, author.getNotifyEmail());
					pstmt.addBatch();
					batchCount++;
					
					if (batchCount == 5000)
					{
						counts = pstmt.executeBatch();
						executeCount += counts.length;
						batchCount = 0;
						//pstmt = con.prepareStatement(OIBlogSqls.CREATE_BLOG_AUTHOR);
					}
				}
				
				if (batchCount > 0)
	        	{
					counts = pstmt.executeBatch();
					executeCount += counts.length;
	        	}
				////System.out.println("OIDAOBlog: createBlogAuthors - counts : "+counts.length);
				
				if (executeCount == authors.size())
				{
					status = true;				
				}	
			}
		}
		catch (SQLException se)
		{
			logger.error(" createBlogAuthors() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}
	
	
	
	public boolean updateBlog(Connection con, OIBABlog blog) throws SQLException
	{
		boolean status = false;
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.UPDATE_BLOG);
			pstmt.setString(1, blog.getTitle());
			pstmt.setInt(2, blog.getBlogId().intValue());
			int count = pstmt.executeUpdate();
			if (count == 1)
			{				
				//status = deleteBlogAuthor(con,blog.getBlogId());
				//status = createBlogAuthors(con, blog.getBlogId(), blog.getAuthors());
				status = updateBlogAuthors(con, blog.getBlogId(), blog.getAuthors());
			}			
		}
		catch (SQLException se)
		{
			logger.error(" updateBlog() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}
	
	public boolean updateBlogAuthors(Connection con, Integer blogId,List authors ) throws SQLException
	{
		boolean status = false;	
		OIBABlogAuthor currentAuthor = null;
		OIBABlogAuthor author = null;
		List deletedblogAuthors = new ArrayList();
		List newBlogAuthors= new ArrayList();
		
		List currentblogAuthors = getBlogAuthors(con, blogId);
		for (Iterator iter = currentblogAuthors.iterator(); iter.hasNext();)
		{			
			currentAuthor = (OIBABlogAuthor) iter.next();			
			if (!authors.contains(currentAuthor))
			{
				deletedblogAuthors.add(currentAuthor);
			}
		}
		//System.out.println("OIDAOBlog: updateBlogAuthors - deletedblogAuthors : "+deletedblogAuthors.size());
		if (deletedblogAuthors.size()>0)
		{
			status = deleteBlogAuthors(con,blogId,deletedblogAuthors);	
		}	
		
		for (Iterator iter = authors.iterator(); iter.hasNext();)
		{		
			author = (OIBABlogAuthor) iter.next();			
			if (!currentblogAuthors.contains(author))
			{
				newBlogAuthors.add(author);
			}
		}
		//System.out.println("OIDAOBlog: updateBlogAuthors - newBlogAuthors : "+newBlogAuthors.size());
		
		if (newBlogAuthors.size()>0)
		{
			status = createBlogAuthors(con,blogId,newBlogAuthors);
		}
			
		
		return status;
	}
	
	public boolean deleteBlogAuthors(Connection con, Integer blogId,List authors) throws SQLException
	{
		boolean status = false;		
		PreparedStatement pstmt = null;
		StringBuffer deletedUsers = new StringBuffer();
		OIBABlogAuthor author = null;		
		try
		{
			for (Iterator iter = authors.iterator(); iter.hasNext();)
			{
				author = (OIBABlogAuthor) iter.next();
				
				if (deletedUsers.length()>0)
				{
					deletedUsers.append(",");
				}
				deletedUsers.append(author.getUserId());
			}
			//System.out.println("OIDAOBlog: deleteBlogAuthors - users : "+deletedUsers.toString());
			
			pstmt = con.prepareStatement(OIBlogSqls.DELETE_BLOG_AUTHOR);
			pstmt.setInt(1,blogId.intValue());	
			pstmt.setString(2,deletedUsers.toString());
			
			int count = pstmt.executeUpdate();
			if (count == 1)
			{				
				status = true;		
			}			
		}
		catch (SQLException se)
		{
			logger.error(" deleteBlogAuthor() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}
	
	
	public boolean updateBlogNoOfPostShow(Connection con, Integer blogId, Integer noOfPostsToShow) throws SQLException
	{
		boolean status = false;
		PreparedStatement pstmt = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.UPDATE_BLOG_NO_POSTS);
			pstmt.setInt(1, noOfPostsToShow.intValue());
			pstmt.setInt(2, blogId.intValue());
			int count = pstmt.executeUpdate();
			if (count == 1) 
			{
				status = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" updateBlogNoOfPostShow() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;
	}
	

	private Integer getNextVal(Connection con,String seqName) throws SQLException
	{
		Integer nextVal = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = con.prepareStatement("SELECT "+seqName+".NEXTVAL IDvALUE FROM DUAL");
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				nextVal = new Integer(rs.getInt("IDvALUE"));
			}
		}
		catch (SQLException se)
		{
			logger.error(" getNextVal() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return nextVal;
		
	}
	
	public List getAllUsers(Connection con,String searchString, int nStartNum, int nEndNum) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogAuthor author = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_USERS);
		//	System.out.println("OIDAOBlog: getAllUsers - var : "+"%" + OIUtilities.replaceNull(searchString) + "%");
			pstmt.setString(1, "%" + OIUtilities.replaceNull(searchString) + "%");
			pstmt.setInt(2,nStartNum);
			pstmt.setInt(3,nEndNum);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				
				author = new OIBABlogAuthor();
				author.setName(rs.getString("NAME"));
				author.setUserId(rs.getString("USERID"));						
				author.setNotifyEmail("N");
				list.add(author);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getAllUsers() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}	
	
	public OIBABlog getBlog(Connection con,Integer blogId) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlog blog = null;		
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_BLOG);			
			pstmt.setInt(1, blogId.intValue());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				blog = new OIBABlog();				
				blog.setBlogId(blogId);
				blog.setTitle(rs.getString("TITLE"));				
			}	
			
			if (blog!=null)
			{
				blog.setAuthors(getBlogAuthors(con, blogId));
			}
		}
		catch (SQLException se)
		{
			logger.error(" getBlog() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return blog;
	}
	
	public List getBlogAuthors(Connection con,Integer blogId) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogAuthor author = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_BLOG_AUTHORS);
			pstmt.setInt(1, blogId.intValue());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				
				author = new OIBABlogAuthor();
				author.setUserId(rs.getString("USERID"));
				author.setNotifyEmail(rs.getString("NOTIFY_EMAIL"));
				author.setName(rs.getString("NAME"));
				author.setNickName(rs.getString("NICKNAME"));
				list.add(author);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getBlogAuthors() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}
	
	public List getAllBlogsAuthors(Connection con) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogAuthor author = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_BLOGS_AUTHORS);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				author = new OIBABlogAuthor();
				author.setUserId(rs.getString("USERID"));
				author.setEmailId(rs.getString("EMAILID"));
				author.setName(rs.getString("NAME"));
				author.setNickName(rs.getString("NICKNAME"));			
				list.add(author);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getAllBlogsAuthors() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}
	
	public List getAllBlogsAuthorsDetail(Connection con) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		//PreparedStatement pstmt2 = null;
		ResultSet rs = null;
		//ResultSet rs2 = null;
		OIBABlogAuthor author = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_BLOGS_AUTHORS);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				author = new OIBABlogAuthor();
				author.setUserId(rs.getString("USERID"));
				author.setEmailId(rs.getString("EMAILID"));
				author.setName(rs.getString("NAME"));
				//author.setAuthorImageFileName(rs.getString("ABOUT_IMAGE"));
				
				/*pstmt2 = con.prepareStatement(OIBlogSqls.GET_ABOUT_ME);
				pstmt2.setString(1,author.getUserId());
				rs2 = pstmt2.executeQuery();
				while (rs2.next())
				{
					author.setAuthorDescription(OIUtilities.clobToString(rs2.getClob("ABOUT_ME")));
				}*/
				
				list.add(author);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getAllBlogsAuthors() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
			//OISQLUtilities.closeRsetPstatement(rs2, pstmt2);
		}		
		return list;
	}
	
	public List getAllLatestPosts(Connection con,Integer latestNoOfPosts,boolean getFeatured,Integer blogId) throws Exception
	{
		List list = null;
		PreparedStatement pstmt = null,pstmtEntry = null;
		ResultSet rs = null,rsEntry = null;
		OIBABlogAdminCreateEntry entry = null;
		StringBuffer query =  null;
		List tagList = null;
		//System.out.println("OIDAOBlog: getAllLatestPosts - latestNoOfPosts : "+latestNoOfPosts+" - getFeatured : "+getFeatured+" - blogId : "+blogId);
		
		try
		{		
	
			query =  new StringBuffer("SELECT * FROM ( ");
			query.append(" SELECT ENTRY1.*, ENTRY2.ENTRY_EXCEPT, ENTRY2.ENTRY_BODY, ENTRY2.TITLE ENTRY_TITLE,ENTRY2.CREATED_ON,ADP.NAME CREATED_BY, ENTRY2.IMAGE_NAME FROM ");
			query.append(" (SELECT ENTRY.ENTRY_ID,ENTRY.BLOG_ID,BLOG.TITLE BLOG_TITLE,COUNT(COMMENTS.COMMENT_ID) TOTAL_COMMENTS FROM OI_BG_ENTRY ENTRY ");
			query.append(" LEFT OUTER JOIN OI_BG_BLOG BLOG ON BLOG.BLOG_ID = ENTRY.BLOG_ID ");
			query.append(" LEFT OUTER JOIN OI_BG_ENTRY_COMMENTS COMMENTS ON COMMENTS.BLOG_ID=ENTRY.BLOG_ID AND COMMENTS.ENTRY_ID=ENTRY.ENTRY_ID ");
			query.append(" WHERE UPPER(ENTRY.STATUS)='P' AND ");
			
			if(blogId!=null)
			{	
				query.append(" ENTRY.BLOG_ID=");
				query.append(blogId.toString());
				query.append(" AND ");
			}
			
			if (getFeatured)
			{
				query.append(" ENTRY.IS_FEATURED='Y' ");
			}
			else
			{
				query.append(" ENTRY.IS_FEATURED='N' ");
			}		
			query.append(" GROUP BY ENTRY.ENTRY_ID,ENTRY.BLOG_ID,BLOG.TITLE) ENTRY1,OI_BG_ENTRY ENTRY2, OI_AD_PROFILE ADP ");
			query.append(" WHERE ENTRY2.BLOG_ID = ENTRY1.BLOG_ID AND ENTRY2.ENTRY_ID = ENTRY1.ENTRY_ID AND ENTRY2.CREATED_BY = ADP.USERID ");
			query.append(" ORDER BY ENTRY2.CREATED_ON DESC ");
			query.append(" ) WHERE ROWNUM<=");
			query.append(latestNoOfPosts.toString());
			
			logger.info("getBlogDetail - query->" + query.toString());
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			boolean first = true;
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				
				entry = new OIBABlogAdminCreateEntry();
				entry.setEntryId(new Integer(rs.getString("ENTRY_ID")));
				entry.setEntryTitle(rs.getString("ENTRY_TITLE"));
				entry.setBlogId(new Integer(rs.getString("BLOG_ID")));
				entry.setCreatedOn(rs.getDate("CREATED_ON"));
				
				if (getFeatured && first)
				{
//					entry.setEntryBody(OIUtilities.clobToString(rs.getClob("ENTRY_BODY")));
//					first = false;
					
					if (rs.getString("ENTRY_EXCEPT") != null && rs.getString("ENTRY_EXCEPT").trim().length() > 0)
						entry.setEntryBody(rs.getString("ENTRY_EXCEPT"));
					else
					{
						String temp = OIUtilities.clobToString(rs.getClob("ENTRY_BODY"));
	
						int idx = 0;
						for (int i = 0; i < 200 && idx > -1; i++)
						{
							idx = temp.indexOf(" ", idx+1);
						}
						
						if (idx > 0)
							temp = temp.substring(0, idx);
						
						entry.setEntryBody(temp);
					}
					
					first = false;
				}
				else
				{
					if (rs.getString("ENTRY_EXCEPT") != null && rs.getString("ENTRY_EXCEPT").trim().length() > 0)
						entry.setEntryBody(rs.getString("ENTRY_EXCEPT"));
					else
					{
						String temp = OIUtilities.clobToString(rs.getClob("ENTRY_BODY"));
	
						int idx = 0;
						for (int i = 0; i < 100 && idx > -1; i++)
						{
							idx = temp.indexOf(" ", idx+1);
						}
						
						if (idx > 0)
							temp = temp.substring(0, idx);
						
						entry.setEntryBody(temp);
					}
				}
				entry.setBlogTitle(rs.getString("BLOG_TITLE"));	
				entry.setTotalComments(new Integer(rs.getInt("TOTAL_COMMENTS")));
				entry.setPicFileName(rs.getString("IMAGE_NAME"));
				entry.setCreatedBy(rs.getString("CREATED_BY"));
				
				list.add(entry);
			}
			//System.out.println("OIDAOBlog: getAllLatestPosts - list : "+list);
			
			if(list!=null)
			{
				pstmtEntry = con.prepareStatement(OIBlogSqls.GET_ENTRY_TAG);
				for (int i = 0; i < list.size(); i++)
				{
					entry = (OIBABlogAdminCreateEntry)list.get(i);
					
					
					pstmtEntry.setInt(1, entry.getEntryId().intValue());
					rsEntry = pstmtEntry.executeQuery();
					
					tagList = new ArrayList();
					
					while(rsEntry.next())
					{
						tagList.add(rsEntry.getString("NAME"));
					}
					
					entry.setTagList(tagList);
					OISQLUtilities.closeRsetPstatement(rsEntry, null);
				}
			}
		}
		catch (Exception se)
		{
			logger.error(" getAllLatestPosts() " + se.getMessage());
			throw se;			
		}
		finally
		{
			//Changed by Deva to fix ERROR -- ORA-01000: maximum open cursors exceeded
			OISQLUtilities.closeRsetPstatement(rsEntry, pstmtEntry);
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}
	
	
	public List getAllPostMonths(Connection con,Integer blogId) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		StringBuffer query =  null;
		
		try
		{
			query =  new StringBuffer(OIBlogSqls.GET_ENTRY_MONTHS);	
			query.append(" WHERE ");
			if(blogId!=null)
			{	
				query.append(" BLOG_ID=");				
				query.append(blogId.toString());
				query.append(" AND ");
			}
			query.append(" UPPER(STATUS)='P' ");
			query.append("  ORDER BY YEARNO, MONTHNO ");
			
			pstmt = con.prepareStatement(query.toString());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{		
				if (list==null)
				{
					list = new ArrayList();
				}
				list.add(rs.getString("MONTHS"));
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getAllPostMonths() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}

	public List getArchivesPosts(Connection con,OIBABlogArchives oiBABlogArchives,int nStartNum, int nEndNum) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		PreparedStatement pstmt2 = null;
		ResultSet rs2 = null;
		OIBABlogAdminCreateEntry entry = null;
		StringBuffer query =  null; 
		List tagList = null;
		
		try
		{
			if (oiBABlogArchives.getArchivesQuery() == null)
			{
				query =  new StringBuffer("SELECT * FROM ( ");
				query.append(" SELECT ROWNUM AS NUM,ENTRY1.*,NVL(ENTRY2.ENTRY_EXCEPT,SUBSTR(ENTRY2.ENTRY_BODY,1,500)) ENTRYBODY,ENTRY2.TITLE ENTRY_TITLE, ENTRY2.CREATED_BY CREATED_BY ,ENTRY2.IMAGE_NAME FROM ");
				query.append(" (SELECT ENTRY.BLOG_ID,BLOG.TITLE BLOG_TITLE,ENTRY.ENTRY_ID,ENTRY.CREATED_ON,COUNT(COMMENTS.COMMENT_ID) TOTAL_COMMENTS FROM OI_BG_ENTRY ENTRY ");
				
				if(oiBABlogArchives.getTagId()!=null)
				{
					query.append(" JOIN OI_TAGS_MAP TMAP ON TMAP.MODULE_TYPE='BG' AND TMAP.TAG_ID=");
					query.append(oiBABlogArchives.getTagId().toString());
					query.append(" AND TMAP.MODULE_ID=ENTRY.ENTRY_ID ");
				}
				
				query.append(" LEFT OUTER JOIN OI_BG_BLOG BLOG ON BLOG.BLOG_ID = ENTRY.BLOG_ID ");
				query.append(" LEFT OUTER JOIN OI_BG_ENTRY_COMMENTS COMMENTS ON COMMENTS.BLOG_ID=ENTRY.BLOG_ID AND COMMENTS.ENTRY_ID=ENTRY.ENTRY_ID ");
				query.append(" WHERE ");
				
				if(oiBABlogArchives.getBlogId()!=null)
				{	
					query.append(" ENTRY.BLOG_ID=");
					query.append(oiBABlogArchives.getBlogId().toString());
					query.append(" AND ");
				}
				query.append(" UPPER(ENTRY.STATUS)='P' ");
				
				if((oiBABlogArchives.getCategoryId()!=null) || (oiBABlogArchives.getMonth()!=null)|| (oiBABlogArchives.getAuthorId()!=null))
				{
					query.append(" AND ");
				}	
				
				if(oiBABlogArchives.getCategoryId()!=null)
				{	
					query.append(" ENTRY.CATEGORY_ID=");
					query.append(oiBABlogArchives.getCategoryId().toString());				
				}
				else if(oiBABlogArchives.getMonth()!=null)
				{	
					//CREATED_ON between TO_DATE('20 NOVEMBER 2006', 'DD MONTH YYYY') and LAST_DAY(TO_DATE('01 NOVEMBER 2006', 'DD MONTH YYYY'))
					query.append(" ENTRY.CREATED_ON BETWEEN TO_DATE('01 ");
					query.append(oiBABlogArchives.getMonth());
					query.append("', 'DD MONTH YYYY') AND LAST_DAY(TO_DATE('01 ");
					query.append(oiBABlogArchives.getMonth());
					query.append("', 'DD MONTH YYYY')) ");
				}
				else if(oiBABlogArchives.getAuthorId()!=null)
				{	
					query.append(" ENTRY.CREATED_BY='");
					query.append(oiBABlogArchives.getAuthorId());
					query.append("' ");
				}	
				query.append(" GROUP BY ENTRY.BLOG_ID,BLOG.TITLE,ENTRY.ENTRY_ID,ENTRY.CREATED_ON ");
				query.append(" ORDER BY ENTRY.BLOG_ID,ENTRY.CREATED_ON DESC) ENTRY1 JOIN OI_BG_ENTRY ENTRY2 ");
				query.append(" ON ENTRY2.BLOG_ID = ENTRY1.BLOG_ID AND ENTRY2.ENTRY_ID = ENTRY1.ENTRY_ID ");
				query.append(" ) WHERE NUM >= ? AND NUM <= ? ");	  			
				
				//System.out.println("OIDAOBlog: getArchivesPosts - Query : "+query.toString());
				oiBABlogArchives.setArchivesQuery(query.toString());
			}			
			
			pstmt = con.prepareStatement(oiBABlogArchives.getArchivesQuery());
			pstmt.setInt(1,nStartNum);
			pstmt.setInt(2,nEndNum);
	         
			rs = pstmt.executeQuery();
			pstmt2 = con.prepareStatement("select NAME from OI_AD_PROFILE where userid = ?");
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				entry = new OIBABlogAdminCreateEntry();
				entry.setEntryId(new Integer(rs.getString("ENTRY_ID")));
				entry.setEntryTitle(rs.getString("ENTRY_TITLE"));
				entry.setBlogId(new Integer(rs.getString("BLOG_ID")));
				entry.setCreatedOn(rs.getDate("CREATED_ON"));
				entry.setEntryBody(rs.getString("ENTRYBODY"));	
				entry.setBlogTitle(rs.getString("BLOG_TITLE"));	
				entry.setTotalComments(new Integer(rs.getInt("TOTAL_COMMENTS")));
				entry.setPicFileName(rs.getString("IMAGE_NAME"));
				
				
				pstmt2.setString(1, rs.getString("CREATED_BY"));
				rs2 = pstmt2.executeQuery();
				
				while(rs2.next())
				{
					entry.setCreatedBy(rs2.getString("NAME"));
				}
				OISQLUtilities.closeRsetPstatement(rs2, null);
				list.add(entry);
			}
			OISQLUtilities.closeRsetPstatement(rs2, pstmt2);
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
			
			if(list!=null)
			{		
				pstmt = con.prepareStatement(OIBlogSqls.GET_ENTRY_TAG);
				for (int i = 0; i < list.size(); i++)
				{
					entry = (OIBABlogAdminCreateEntry)list.get(i);
					
					
					pstmt.setInt(1, entry.getEntryId().intValue());
					rs = pstmt.executeQuery();
					
					tagList = new ArrayList();
					
					while(rs.next())
					{
						tagList.add(rs.getString("NAME"));
					}
					
					entry.setTagList(tagList);
					OISQLUtilities.closeRsetPstatement(rs, null);
				}
				OISQLUtilities.closeRsetPstatement(null, pstmt);
			}
		}
		catch (SQLException se)
		{
			logger.error(" getArchivesPosts() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
			OISQLUtilities.closeRsetPstatement(rs2, pstmt2);
		}		
		return list;
	}
	

	public OIBABlogArchives getTotalArchivesPostsCount(Connection con, OIBABlogArchives oiBABlogArchives) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			/*
SELECT COUNT(*) TOTAL FROM 	
(SELECT ENTRY.ENTRY_ID,ENTRY.BLOG_ID FROM OI_BG_ENTRY ENTRY 
JOIN OI_TAGS_MAP TMAP ON TMAP.MODULE_TYPE='BG' AND TMAP.MODULE_ID=ENTRY.ENTRY_ID AND TMAP.TAG_ID=939 	 
  WHERE  ENTRY.BLOG_ID=  AND  UPPER(ENTRY.STATUS)='P'
   AND  ENTRY.CATEGORY_ID=
    ENTRY.CREATED_ON BETWEEN TO_DATE('01 ', 'DD MONTH YYYY') AND LAST_DAY(TO_DATE('01 ', 'DD MONTH YYYY')) 
			 ENTRY.CREATED_BY=''  GROUP BY ENTRY.ENTRY_ID,ENTRY.BLOG_ID) 
			 */
			if (oiBABlogArchives.getArchivesQuery() == null)
			{
				StringBuffer query =  new StringBuffer("SELECT COUNT(*) TOTAL FROM ");
				query.append(" (SELECT ENTRY.ENTRY_ID,ENTRY.BLOG_ID FROM OI_BG_ENTRY ENTRY ");
				
				if(oiBABlogArchives.getTagId()!=null)
				{
					query.append(" JOIN OI_TAGS_MAP TMAP ON TMAP.MODULE_TYPE='BG' AND TMAP.TAG_ID=");
					query.append(oiBABlogArchives.getTagId().toString());
					query.append(" AND TMAP.MODULE_ID=ENTRY.ENTRY_ID ");
				}
				
				query.append(" WHERE ");
				
				if(oiBABlogArchives.getBlogId()!=null)
				{	
					query.append(" ENTRY.BLOG_ID=");
					query.append(oiBABlogArchives.getBlogId().toString());
					query.append(" AND ");
				}
				query.append(" UPPER(ENTRY.STATUS)='P' ");
				
				if((oiBABlogArchives.getCategoryId()!=null) || (oiBABlogArchives.getMonth()!=null)|| (oiBABlogArchives.getAuthorId()!=null))
				{
					query.append(" AND ");
				}	
				
				if(oiBABlogArchives.getCategoryId()!=null)
				{	
					query.append(" ENTRY.CATEGORY_ID=");
					query.append(oiBABlogArchives.getCategoryId().toString());				
				}
				else if(oiBABlogArchives.getMonth()!=null)
				{	
					//CREATED_ON between TO_DATE('20 NOVEMBER 2006', 'DD MONTH YYYY') and LAST_DAY(TO_DATE('01 NOVEMBER 2006', 'DD MONTH YYYY'))
					query.append(" ENTRY.CREATED_ON BETWEEN TO_DATE('01 ");
					query.append(oiBABlogArchives.getMonth());
					query.append("', 'DD MONTH YYYY') AND LAST_DAY(TO_DATE('01 ");
					query.append(oiBABlogArchives.getMonth());
					query.append("', 'DD MONTH YYYY')) ");
				}
				else if(oiBABlogArchives.getAuthorId()!=null)
				{	
					query.append(" ENTRY.CREATED_BY='");
					query.append(oiBABlogArchives.getAuthorId());
					query.append("' ");
				}	
				
				query.append(" GROUP BY ENTRY.ENTRY_ID,ENTRY.BLOG_ID) ");
				oiBABlogArchives.setTotalCountQuery(query.toString());
			}
			//System.out.println("OIDAOBlog: getTotalArchivesPostsCount - TotalCountQuery : "+oiBABlogArchives.getTotalCountQuery());
	
			pstmt = con.prepareStatement(oiBABlogArchives.getTotalCountQuery());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				oiBABlogArchives.setTotalRecords(rs.getInt("TOTAL"));				
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getTotalArchivesPostsCount() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}	

		
		return oiBABlogArchives;
	}

	public boolean isAuthor(Connection con, String userId, Integer blogId) throws SQLException
	{
		boolean isAuthor = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.CHECK_IS_BLOG_AUTHOR);
			pstmt.setInt(1, blogId.intValue());
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{		
				isAuthor = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" isAuthor() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return isAuthor;
	}
	
	
	public int getTotalRecCount(Connection con, String mainQuery) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		try
		{
			pstmt = con.prepareStatement(mainQuery);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				totalCount = rs.getInt("TOTAL");				
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getTotalRecCount() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}	

		
		return totalCount;
	}
	
	public int getTotalRecCount(Connection con, String mainQuery, String searchString) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalCount = 0;
		
		try
		{
			pstmt = con.prepareStatement(mainQuery);
			pstmt.setString(1, "%" + OIUtilities.replaceNull(searchString) + "%");
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				totalCount = rs.getInt("TOTAL");				
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getTotalRecCount() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}	

		
		return totalCount;
	}
	
	public List getAllTags(Connection con) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogTag oiBABlogTag = null;
		int blogEntriesCount = 0;
		
		try
		{	
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_TAG_WITH_ENTRIES);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				
				oiBABlogTag = new OIBABlogTag();
				oiBABlogTag.setTagName(rs.getString("NAME"));	
				oiBABlogTag.setTagId(new Integer(rs.getInt("TAG_ID")));
				
				blogEntriesCount = rs.getInt("ENTRIESCOUNT");
				if (blogEntriesCount>0)
				{
					oiBABlogTag.setHasPost(true);
				}
				else
				{	
					oiBABlogTag.setHasPost(false);
				}				
				list.add(oiBABlogTag);
			}			
		}
		catch (SQLException se)
		{
			logger.error(" getAllTags() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return list;
	}
	
	public boolean doesBlogTitleExists(Connection con, String blogTitle,Integer blogId) throws SQLException
	{
		boolean doesBlogTitleExists = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			StringBuffer query = new StringBuffer("SELECT BLOG_ID FROM OI_BG_BLOG WHERE UPPER(TITLE)=? ");
			if (blogId!=null)
			{
				query.append(" AND BLOG_ID<>");
				query.append(blogId.intValue());
			}
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, blogTitle.toUpperCase());
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{		
				doesBlogTitleExists = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" doesBlogTitleExists() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return doesBlogTitleExists;
	}
	
	public boolean doesCategoryExists(Connection con, String category,Integer categoryId) throws SQLException
	{
		boolean doesCategoryExists = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			StringBuffer query = new StringBuffer("SELECT CATEGORY_ID FROM OI_BG_CATEGORY WHERE UPPER(NAME)=? ");
			if (categoryId!=null)
			{
				query.append(" AND CATEGORY_ID<>");
				query.append(categoryId.intValue());
			}
			pstmt = con.prepareStatement(query.toString());
			pstmt.setString(1, category.toUpperCase());
			
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{		
				doesCategoryExists = true;
			}			
		}
		catch (SQLException se)
		{
			logger.error(" doesBlogTitleExists() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return doesCategoryExists;
	}
	
	public String getBlogTitle(Connection conn, Integer blogId) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String blogTitle = "";
		
		try
		{
			pstmt = conn.prepareStatement(OIBlogSqls.QRY_GET_BLOG_TITLE);
			pstmt.setInt(1, blogId.intValue());
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			blogTitle = rs.getString("TITLE");
		}
		catch (SQLException se)
		{
			logger.error("getBlogTitle() " + se.getMessage());
			throw se;			
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}		
		return blogTitle;
	}
}
