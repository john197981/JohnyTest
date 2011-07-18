package com.oifm.blog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOBlogAdmin extends OIBaseDao {
	Logger logger = Logger.getLogger(OIDAOBlog.class);

	public OIDAOBlogAdmin() {
	}

	// modify by edmund
	public int insertAdminBlogNewEntry(Connection con,
			OIBABlogAdminCreateEntry admin, String userId, Integer blogId)
			throws SQLException {
		// boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CLOB clob = null;
		int id = blogId.intValue();
		int entryId = -1;

		//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - var : "	+ "DAO1");
		//System.out.println("DAO" + admin.getEntryBody());

		try {
			con.setAutoCommit(false);
			// remember to change the query in properties file
			pstmt = con.prepareStatement(OIBlogSqls.CREATE_BLOG_ENTRY);

			pstmt.setInt(1, id);
			pstmt.setInt(2, admin.getCategory().intValue());
			pstmt.setString(3, admin.getEntryTitle());
			pstmt.setString(4, admin.getStatus());
			pstmt.setString(5, admin.getEntryExcept());
			pstmt.setString(6, admin.getAcceptComments());
			pstmt.setString(7, userId);
			//pstmt.setString(8, admin.getExpiryDate());

			int i = pstmt.executeUpdate();

			if (i == 0) {
				logger.error("Save Failed");
				throw new SQLException("Save Failed");
			}

			pstmt = con
					.prepareStatement("SELECT SEQ_OI_BG_ENTRYID.CURRVAL FROM OI_BG_ENTRY");
			rs = pstmt.executeQuery();
			if (rs.next())
				entryId = rs.getInt("CURRVAL");

			//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - entryId : "+entryId);
			pstmt = con.prepareStatement(OIBlogSqls.INSERT_ENTRY_BODY
					+ " FOR UPDATE");
			pstmt.setInt(1, entryId);

			rs = pstmt.executeQuery();
			//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - clob1 : "+clob);
			if (rs.next()) {
				// temp = rs.getString("TITLE");
				//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - clobx1 : "+clob);
				clob = (oracle.sql.CLOB)rs.getClob("ENTRY_BODY");
				//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - clobx2 : "+clob);
			}
			//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - clob2 : "+clob);
			if (clob != null) {

				OIUtilities.writeClob(clob, admin.getEntryBody());
			}
			

			// status = true;
			/*System.out
					.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - var : "
							+ "DAO2");*/
		} catch (SQLException se) {
			logger.error(" insertAdminBlogNewEntry() " + se.getMessage());
			throw se;
		} catch (IOException ioe) {
			logger.error(" insertAdminBlogNewEntry() " + ioe.getMessage());
		} finally {
			con.commit();
			con.setAutoCommit(true);
			OISQLUtilities.closePstatement(pstmt);
		}
		return entryId;
	}

	// added by edmund
	public int updateAdminBlogNewEntry(Connection con,
			OIBABlogAdminCreateEntry admin, int entryId, Integer blogId)
			throws SQLException {
		// boolean status = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CLOB clob = null;
		int id = blogId.intValue();

		try {
			con.setAutoCommit(false);
			// remember to change the query in properties file
			pstmt = con.prepareStatement(OIBlogSqls.UPDATE_BLOG_ENTRY);
			
			//System.out.println("OIDAOBlogAdmin: updateAdminBlogNewEntry - admin.getEntryExcept() : "+ admin.getEntryExcept());
			
			pstmt.setInt(1, admin.getCategory().intValue());
			pstmt.setString(2, admin.getEntryTitle());
			pstmt.setString(3, admin.getStatus());
			pstmt.setString(4, admin.getEntryExcept());
			pstmt.setString(5, admin.getAcceptComments());
			//pstmt.setString(6, admin.getExpiryDate());
			pstmt.setInt(6, entryId);
			pstmt.setInt(7, id);

			//System.out.println("OIDAOBlogAdmin: updateAdminBlogNewEntry - var : "+"finish setting");
			int i = pstmt.executeUpdate();
			if (i == 0) {
				logger.error("Save Failed");
				throw new SQLException("Save Failed");
			}						
			//System.out.println("OIDAOBlogAdmin: updateAdminBlogNewEntry - var : "+entryId);
			
			 pstmt=con.prepareStatement(OIBlogSqls.INSERT_ENTRY_BODY+" FOR UPDATE"); 
			 pstmt.setInt(1,entryId);
			 
			 rs = pstmt.executeQuery();
				if (rs.next()) {
					clob = (oracle.sql.CLOB)rs.getClob("ENTRY_BODY");
				}
				if (clob != null) {
					OIUtilities.writeClob(clob, admin.getEntryBody());
				}
			 

			// status = true;
			//System.out.println("OIDAOBlogAdmin: insertAdminBlogNewEntry - var : "+ "DAO2");
		} catch (SQLException se) {
			logger.error(" updateAdminBlogNewEntry() " + se.getMessage());
			throw se;
		} catch (IOException e) {
			logger.error(" updateAdminBlogNewEntry() " + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			con.commit();
			con.setAutoCommit(true);
			OISQLUtilities.closePstatement(pstmt);
		}
		return entryId;
	}

	// added by edmund
	public int deleteEntry(Connection con, Integer blogId, String[] aEntryId)
			throws SQLException {
		// boolean status = false;
		PreparedStatement pstmt = null;

		int batch = 0;

		int id = blogId.intValue();

		try {
			con.setAutoCommit(false);
			
			pstmt = con.prepareStatement(OIBlogSqls.DELETE_BLOG_ENTRY_COMMENT);
			for (int i = 0; i < aEntryId.length; i++) {
				//System.out.println("selected " + aEntryId[i] + " " + id);
				pstmt.setInt(1, Integer.parseInt(aEntryId[i]));
				batch += pstmt.executeUpdate();
			}
			

			pstmt = con.prepareStatement(OIBlogSqls.DELETE_BLOG_ENTRY);
			for (int i = 0; i < aEntryId.length; i++) {
				//System.out.println("selected " + aEntryId[i] + " " + id);
				pstmt.setInt(1, Integer.parseInt(aEntryId[i]));
				pstmt.setInt(2, id);
				batch += pstmt.executeUpdate();
			}

			//System.out.println("OIDAOBlogAdmin: deleteEntry - var : "	+ "DAO delete exe");
		} catch (SQLException se) {
			logger.error(" insertAdminBlogNewEntry() " + se.getMessage());
			throw se;
		} finally {
			con.commit();
			con.setAutoCommit(true);
			OISQLUtilities.closePstatement(pstmt);
		}
		return batch;
	}
	
//	 added by edmund
	public int deleteComment(Connection con, Integer blogId, String[] aCommentId)
			throws SQLException {
		// boolean status = false;
		PreparedStatement pstmt = null;

		int batch = 0;

		int id = blogId.intValue();

		try {
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(OIBlogSqls.DELETE_BLOG_COMMENT);

			for (int i = 0; i < aCommentId.length; i++) {
				//System.out.println("selected " + aCommentId[i] + " " + id);
				pstmt.setInt(1, Integer.parseInt(aCommentId[i]));
				batch += pstmt.executeUpdate();
			}

			//System.out.println("OIDAOBlogAdmin: deleteComment - var : "+ "DAO delete exe" + batch);
		} catch (SQLException se) {
			logger.error(" deleteComment " + se.getMessage());
			throw se;
		} finally {
			con.commit();
			con.setAutoCommit(true);
			OISQLUtilities.closePstatement(pstmt);
		}
		return batch;
	}
	
//	 added by edmund
	public int setFeature(Connection con, Integer blogId, String[] aEntryId, String mode)
			throws SQLException {
		// boolean status = false;
		PreparedStatement pstmt = null;

		int batch = 0;
		String strQuery = "";
		int id = blogId.intValue();
		
		if(mode.equals("Y"))
		{
			strQuery = OIBlogSqls.SET_BLOG_FEATURE;
		}
		else
		{
			strQuery = OIBlogSqls.UNSET_BLOG_FEATURE;
		}

		try {
			con.setAutoCommit(false);

			pstmt = con.prepareStatement(strQuery);

			for (int i = 0; i < aEntryId.length; i++) {
				//System.out.println("selected " + aEntryId[i] + " blog-" + id);
				pstmt.setInt(1, Integer.parseInt(aEntryId[i]));
				pstmt.setInt(2, id);
				batch += pstmt.executeUpdate();
			}

			//System.out.println("OIDAOBlogAdmin: setFeature - var : "+ "DAO delete exe -" + batch);
		} catch (SQLException se) {
			logger.error(" setFeature " + se.getMessage());
			throw se;
		} finally {
			con.commit();
			con.setAutoCommit(true);
			OISQLUtilities.closePstatement(pstmt);
		}
		return batch;
	}

	// added by edmund
	public boolean setPhoto(Connection conn, String fileName, int entryId,
			int blogId) throws Exception {
		PreparedStatement pst = null;
		int count = 0;

		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(OIBlogSqls.INSERT_ENRTY_FILENAME);
			pst.setString(1, fileName);
			pst.setInt(2, entryId);
			pst.setInt(3, blogId);

			count = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("removePhoto() - " + e);
			throw e;
		} finally {
			if (count != 1)
				conn.rollback();
			else
				conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count == 1;
	}

	public List getRecentEntries(Connection con, Integer blogId) throws SQLException {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		OIBABlogAdminRecentEntries objBA = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(OIBlogSqls.GET_RECENT_ENTRIES);
			pstmt.setInt(1, blogId.intValue());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objBA = new OIBABlogAdminRecentEntries();
				objBA.setEntryTitle(rs.getString("TITLE"));
				objBA.setEntryDate(rs.getDate("CREATED_ON"));
				list.add(objBA);
			}
		} catch (SQLException se) {
			logger.error(" getRecentEntries() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return list;
	}

	public List getRecentComments(Connection con, Integer blogId) throws SQLException {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		OIBABlogAdminRecentEntries objBA = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(OIBlogSqls.GET_RECENT_COMMENTS);
			pstmt.setInt(1, blogId.intValue());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				objBA = new OIBABlogAdminRecentEntries();
				objBA.setCommentor(rs.getString("NICKNAME"));
				objBA.setCommentDate(rs.getDate("CREATED_ON"));
				list.add(objBA);
			}
		} catch (SQLException se) {
			logger.error(" getRecentComments() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return list;
	}

	// added by edmund
	public List getAllComments(Connection con, Integer blogId)
			throws SQLException {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		OIBABlogAdminComment objBA = null;
		ResultSet rs = null;

		try {
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_COMMENT);
			pstmt.setInt(1, blogId.intValue());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				objBA = new OIBABlogAdminComment();
				objBA.setComment(rs.getString("MESSAGE"));
				objBA.setEntryId(rs.getString("ENTRY_ID"));
				objBA.setCommentId(rs.getString("COMMENT_ID"));
				objBA.setCommentor(rs.getString("CREATED_BY"));

				list.add(objBA);
			}
		} catch (SQLException se) {
			logger.error(" getAllComments() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closePstatement(pstmt);
		}
		return list;
	}

	// modify by edmund
	public List getCategories(Connection con) throws SQLException {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		OIBABlogAdminCategory objBA = null;
		ResultSet rs = null;

		try {
			// remember to change the query in properties file
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_CATEGORIES);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				objBA= new OIBABlogAdminCategory();
				objBA.setCategoryId(new Integer(rs.getString("CATEGORY_ID")));	
				objBA.setCategory(rs.getString("NAME"));
				list.add(objBA);
			}
		} catch (SQLException se) {
			logger.error(" getCategories() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closePstatement(pstmt);
		}
		return list;

	}

	public boolean createCategory(Connection con, OIBABlogAdminCategory admin)
			throws SQLException {
		boolean status = false;
		PreparedStatement pstmt = null;

		try {
			// remember to change the query in properties file
			pstmt = con.prepareStatement(OIBlogSqls.CREATE_CATEGORIES);
			//System.out.println("getCategory " + admin.getCategory());
			pstmt.setString(1, admin.getCategory());
			int count = pstmt.executeUpdate();
			if (count == 1) {
				status = true;
			}
		} catch (SQLException se) {
			logger.error(" createCategory() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closePstatement(pstmt);
		}
		return status;

	}

	public List getEntriesList(Connection con) throws SQLException {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogAdminEntries blog = null;

		try {
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_ENTRIES);
			// pstmt.setInt(1, blogIc);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blog = new OIBABlogAdminEntries();
				blog.setEntryId(new Integer(rs.getInt("ENTRY_ID")));
				blog.setEntryTitle(rs.getString("TITLE"));
				list.add(blog);
			}
		} catch (SQLException se) {
			logger.error(" getEntriesList() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return list;
	}
	
	//added by edmund
	public List getBlogEntriesList(Connection con, Integer blogId) throws SQLException {
		List list = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogAdminEntries blog = null;

		try {
			pstmt = con.prepareStatement(OIBlogSqls.GET_BLOG_ENTRIES);
			 pstmt.setInt(1, blogId.intValue());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				blog = new OIBABlogAdminEntries();
				blog.setEntryId(new Integer(rs.getInt("ENTRY_ID")));
				blog.setEntryTitle(rs.getString("TITLE"));
				blog.setIsFeatured(rs.getString("IS_FEATURED"));
				blog.setStrStatus(rs.getString("STATUS"));
				blog.setCreatedBy(rs.getString("CREATED_BY"));
				list.add(blog);
			}
		} catch (SQLException se) {
			logger.error(" getBlogEntriesList() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return list;
	}

	// modify by edmund
	public OIBABlogAdminCreateEntry getEntry(Connection con, Integer entryId,
			Integer blogId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		// OIBABlogAdminEntries objBA = null;
		OIBABlogAdminCreateEntry objBACreate = null;

		try {
			//System.out.println("OIDAOBlogAdmin: getEntry - var : " + "DAO1");
			pstmt = con.prepareStatement(OIBlogSqls.GET_ENTRY);
			pstmt.setInt(1, entryId.intValue());
			pstmt.setInt(2, blogId.intValue());
			rs = pstmt.executeQuery();

			while (rs.next()) {
				if (objBACreate == null) {
					objBACreate = new OIBABlogAdminCreateEntry();
					objBACreate.setEntryId(entryId);
				}
				
				objBACreate = new OIBABlogAdminCreateEntry();
				objBACreate.setEntryTitle(rs.getString("TITLE"));
				objBACreate.setCategory(new Integer(rs.getInt("CATEGORY_ID")));
				//objBACreate.setExpiryDate(rs.getString("EXPIRY_DATE"));
				objBACreate.setPicFileName(rs.getString("IMAGE_NAME"));

				if (rs.getClob("ENTRY_BODY") == null)
				{
					//System.out.println("OIDAOBlogAdmin: getEntry - var : "+ "null ");
				}
					else
					{
					//System.out.println("OIDAOBlogAdmin: getEntry - var : "+ rs.getClob("ENTRY_BODY").length());
					}

				objBACreate.setEntryBody(OIUtilities.clobToString(rs.getClob("ENTRY_BODY")));
				objBACreate.setEntryExcept(rs.getString("ENTRY_EXCEPT"));
				//objBACreate.setTags(rs.getString("TAGS"));
				objBACreate.setStatus(rs.getString("STATUS"));
				objBACreate.setAcceptComments(rs.getString("ALLOW_COMMENTS"));
				//System.out.println("" + "DAO2-" + objBACreate.getEntryBody()+ "-DAO2");
			}
		} catch (SQLException se) {
			logger.error(" getEntry() " + se.getMessage());
			throw se;
		} catch (IOException ioe) {
			logger.error(" getEntry() " + ioe.getMessage());
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}

		return objBACreate;
	}
	
	//added by edmund
	public boolean removePhoto(Connection conn, int id) throws Exception {
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(OIBlogSqls.DELETE_IMAGE);
			pst.setInt(1, id);
			
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
	
//	added by edmund
	public boolean saveNewTag(Connection conn, String tagName) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pst = conn.prepareStatement(OIBlogSqls.CHECK_TAG);
			pst.setString(1, tagName);
			rs = pst.executeQuery();

			if(!rs.next())
			{
				pst = conn.prepareStatement(OIBlogSqls.INSERT_NEW_TAG);
				pst.setString(1, tagName);
				count = pst.executeUpdate();
			}
			else
			{
				throw new Exception("Blog.Tag.Existing");
			}
			
		} catch (Exception e) {
			logger.error("saveNewTag() - " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count ==1;
	}
	
//	added by edmund
	public boolean saveTag(Connection conn, Integer entryId, ArrayList alTagNames) throws Exception {
		PreparedStatement pst = null;
		
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pst = conn.prepareStatement(OIBlogSqls.INSERT_ENTRY_TAG);
			for (Iterator iter = alTagNames.iterator(); iter.hasNext();)
			{
				OIBABlogTag tag = (OIBABlogTag) iter.next();
				pst.setInt(1, tag.getTagId().intValue());
				pst.setInt(2, entryId.intValue());
				count = pst.executeUpdate();
			}
			
		} catch (Exception e) {
			logger.error("saveTag() - " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count ==1;
	}
	
//	added by edmund
	public boolean updateTag(Connection conn, Integer entryId, ArrayList alTagNames) throws Exception {
		PreparedStatement pst = null;
		
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			//System.out.println("OIDAOBlogAdmin: updateTag - var : "+entryId);
			pst = conn.prepareStatement(OIBlogSqls.DELETE_ENTRY_TAG);
			pst.setInt(1, entryId.intValue());
			count = pst.executeUpdate();
			
			//System.out.println("OIDAOBlogAdmin: updateTag - count : "+count);
			
			//System.out.println("OIDAOBlogAdmin: updateTag - alTagNames : "+alTagNames.size());
			
			pst = conn.prepareStatement(OIBlogSqls.INSERT_ENTRY_TAG);
			for (Iterator iter = alTagNames.iterator(); iter.hasNext();)
			{
				OIBABlogTag tag = (OIBABlogTag) iter.next();
				//System.out.println("OIDAOBlogAdmin: updateTag - var : "+entryId);
				//System.out.println("OIDAOBlogAdmin: updateTag - var : "+tag.getTagId());
				pst.setInt(1, tag.getTagId().intValue());
				pst.setInt(2, entryId.intValue());
				count = pst.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			logger.error("DAO updateTag() - " + e);
			conn.rollback();
			throw e;
		} finally {
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count ==1;
	}
	
//	added by edmund
	public List getTags(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		OIBABlogTag objBA = null;
		
		try {
			pst = conn.prepareStatement(OIBlogSqls.GET_TAG);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				objBA= new OIBABlogTag();
				objBA.setTagName(rs.getString("NAME"));	
				objBA.setTagId(new Integer(rs.getInt("TAG_ID")));
				list.add(objBA);
			}

			
		} catch (Exception e) {
			logger.error("getTags() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closePstatement(pst);
		}
		return list;
	}
	
//	added by edmund
	public List getTags(Connection conn, int entryId) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		OIBABlogTag objBA = null;
		
		try {
			pst = conn.prepareStatement(OIBlogSqls.GET_ENTRY_TAG);
			pst.setInt(1, entryId);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				objBA= new OIBABlogTag();
				objBA.setTagName(rs.getString("NAME"));	
				objBA.setTagId(new Integer(rs.getInt("TAGID")));
				list.add(objBA);
				//System.out.println("OIDAOBlogAdmin: getTags - var : "+objBA.getTagName());
			}

			
		} catch (Exception e) {
			logger.error("getTags() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closePstatement(pst);
		}
		return list;
	}
	
	//added by edmund
	public List getAllTags(Connection con,int nStartNum, int nEndNum) throws SQLException
	{
		List list = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBABlogTag objBA = null;
		
		try
		{
			pstmt = con.prepareStatement(OIBlogSqls.GET_ALL_TAG);
			pstmt.setInt(1,nStartNum);
			pstmt.setInt(2,nEndNum);
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				if (list==null)
				{
					list = new ArrayList();
				}
				
				objBA = new OIBABlogTag();
				objBA.setTagName(rs.getString("NAME"));
				objBA.setTagId(new Integer(Integer.parseInt(rs.getString("TAG_ID"))));
				list.add(objBA);
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
	
	/**
	 * Loads a Category details identified by the Category Id
	 * @param con
	 * @param categoryId
	 * @return
	 * @throws SQLException
	 */
	public OIBABlogAdminCategory getCategory(Connection con,Integer categoryId) throws SQLException 
	{
		PreparedStatement pstmt = null;
		OIBABlogAdminCategory objBA = null;
		ResultSet rs = null;

		try {

			pstmt = con.prepareStatement(OIBlogSqls.GET_CATEGORy);
			pstmt.setInt(1, categoryId.intValue());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				objBA= new OIBABlogAdminCategory();
				objBA.setCategoryId(new Integer(rs.getString("CATEGORY_ID")));	
				objBA.setCategory(rs.getString("NAME"));				
			}
			
		} catch (SQLException se) {
			logger.error(" getCategories() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closePstatement(pstmt);
		}
		return objBA;

	}
	
	public boolean updateCategory(Connection con,OIBABlogAdminCategory objBA) throws SQLException 
	{
		PreparedStatement pstmt = null;
		int count = 0;
		boolean updateStatus =  false;

		try {

			pstmt = con.prepareStatement(OIBlogSqls.UPDATE_CATEGORy);
			pstmt.setString(1, objBA.getCategory());
			pstmt.setInt(2, objBA.getCategoryId().intValue());
			count = pstmt.executeUpdate();
			
			if (count==1)
			{
				updateStatus = true;
			}
			
		} catch (SQLException se) {
			logger.error(" updateCategory() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closePstatement(pstmt);
		}
		return updateStatus;

	}
	
	public boolean checkEntryTitleExist(Connection connection, Integer blogId, String entryTitle) throws Exception
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		
		try
		{	
			//System.out.println("OIDAOBlogAdmin: checkEntryTitleExist - blogId : "+blogId);
			//System.out.println("OIDAOBlogAdmin: checkEntryTitleExist - entryTitle : "+entryTitle);
			pst = connection.prepareStatement(OIBlogSqls.CHECK_ENTRY_TITLE_EXISTS_1);
			pst.setInt(1, blogId.intValue());
			pst.setString(2, entryTitle);
			rs = pst.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
		}
		catch (Exception se)
		{
			logger.error("checkEntryTitleExist() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return count > 0;
	}
	
	public boolean checkEntryTitleExist(Connection connection, Integer blogId, String entryTitle, Integer entryId) throws Exception
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		
		try
		{	
			//System.out.println("OIDAOBlogAdmin: checkEntryTitleExist - blogId : "+blogId);
			//System.out.println("OIDAOBlogAdmin: checkEntryTitleExist - entryTitle : "+entryTitle);
			//System.out.println("OIDAOBlogAdmin: checkEntryTitleExist - entryId : "+entryId);
			pst = connection.prepareStatement(OIBlogSqls.CHECK_ENTRY_TITLE_EXISTS_2);
			pst.setInt(1, blogId.intValue());
			pst.setString(2, entryTitle);
			pst.setInt(3, entryId.intValue());
			rs = pst.executeQuery();
			
			rs.next();
			
			count = rs.getInt(1);
		}
		catch (Exception se)
		{
			logger.error("checkEntryTitleExist() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return count > 0;
	}
	
	public OIBABlogAdminRecentEntries getBlogNoOfPosts (Connection connection, Integer blogId) throws Exception
	{
		OIBABlogAdminRecentEntries objBA = new OIBABlogAdminRecentEntries();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			pst = connection.prepareStatement(OIBlogSqls.GET_BLOG_NO_OF_POSTS);
			pst.setInt(1, blogId.intValue());
			rs = pst.executeQuery();
			
			rs.next();
			
			objBA.setNoOfPosts(Integer.valueOf(rs.getString("NO_OF_POSTS_SHOW")));
		}
		catch (SQLException se) {
			logger.error("getBlogNoOfPosts() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return objBA;
	}
	
	public ArrayList getOtherBlogAuthors(Connection connection, Integer blogId, String userId) throws Exception
	{
		ArrayList arList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try
		{
			pst = connection.prepareStatement(OIBlogSqls.GET_OTHER_BLOG_AUTHORS);
			pst.setInt(1, blogId.intValue());
			pst.setString(2, userId);
			rs = pst.executeQuery();
			
			while (rs.next())
			{
				arList.add(rs.getString("NAME"));
			}
		}
		catch (SQLException se) {
			logger.error("getOtherBlogAuthors() " + se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return arList;
	}
}
