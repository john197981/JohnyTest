package com.oifm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.blog.OIDAOBlog;
import com.oifm.utility.OISQLUtilities;

public class OIDAOAddTag extends OIBaseDao{
	Logger logger = Logger.getLogger(OIDAOBlog.class);
	
	public OIDAOAddTag() {
	}
	
	public List getTags(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		OIBAAddTag objBA = null;
		
		try {
			pst = conn.prepareStatement(OITagSqls.GET_TAG);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				objBA= new OIBAAddTag();
				objBA.setTagName(rs.getString("NAME"));	
				objBA.setTagId(new Integer(rs.getInt("TAG_ID")));
				list.add(objBA);
			}

			
		} catch (Exception e) {
			logger.error("getTags() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return list;
	}
	
	public boolean saveNewTag(Connection conn, String tagName) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			
			pst = conn.prepareStatement(OITagSqls.CHECK_TAG);
			pst.setString(1, tagName);
			rs = pst.executeQuery();

			if(!rs.next())
			{
				pst = conn.prepareStatement(OITagSqls.INSERT_NEW_TAG);
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
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return count ==1;
	}
	
	public boolean updateTag(Connection conn, String userId, ArrayList alTagNames) throws Exception {
		PreparedStatement pst = null;
		
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			//System.out.println("OIDAOAddTag: updateTag - userid : "+userId);
			pst = conn.prepareStatement(OITagSqls.DELETE_Profile_TAG);
			pst.setString(1, userId);
			count = pst.executeUpdate();
			OISQLUtilities.closePstatement(pst);
			//System.out.println("OIDAOAddTag: updateTag - count : "+count);
			
			//System.out.println("OIDAOAddTag: updateTag - alTagNames : "+alTagNames.size());
			
			if(alTagNames.size()>0)
			{
				pst = conn.prepareStatement(OITagSqls.INSERT_Profile_TAG);
				for (Iterator iter = alTagNames.iterator(); iter.hasNext();)
				{
					OIBAAddTag tag = (OIBAAddTag) iter.next();
					//System.out.println("OIDAOAddTag: updateTag - var : "+userId);
					//System.out.println("OIDAOAddTag: updateTag - var : "+tag.getTagId());
					pst.setInt(1, tag.getTagId().intValue());
					pst.setString(2, userId);
					count = pst.executeUpdate();
				}
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
	
	public boolean updateTag(Connection conn, String Id, ArrayList alTagNames, String strModule) throws Exception {
		PreparedStatement pst = null;
		
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			//System.out.println("OIDAOAddTag: updateTag - id : "+Id);
			pst = conn.prepareStatement(OITagSqls.DELETE_TAG);
			pst.setString(1, strModule);
			pst.setString(2, Id);
			count = pst.executeUpdate();
			//Added by Deva
			OISQLUtilities.closePstatement(pst);
			//System.out.println("OIDAOAddTag: updateTag - count : "+count);
			
			//System.out.println("OIDAOAddTag: updateTag - alTagNames : "+alTagNames.size());
			//System.out.println("OIDAOAddTag: updateTag - alTagNames : "+alTagNames.get(0));
			if(alTagNames.size()>0 && !alTagNames.get(0).equals(""))
			{
				pst = conn.prepareStatement(OITagSqls.INSERT_TAG);
				for (int k = 0; k<alTagNames.size(); k++)
				{
					
					//OIBAAddTag tag = (OIBAAddTag) iter.next();
					//System.out.println("OIDAOAddTag: updateTag - var : "+Id);
					//System.out.println("OIDAOAddTag: updateTag - class : "+alTagNames.get(k).getClass());
					pst.setInt(1, Integer.parseInt((String)alTagNames.get(k)));
					pst.setString(2,strModule);
					pst.setString(3, Id);
					count = pst.executeUpdate();
				}
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
	
	public List getTags(Connection conn, String id) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		OIBAAddTag objBA = null;
		
		try {
			pst = conn.prepareStatement(OITagSqls.GET_Profile_TAG);
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				objBA= new OIBAAddTag();
				objBA.setTagName(rs.getString("NAME"));	
				objBA.setTagId(new Integer(rs.getInt("TAGID")));
				list.add(objBA);
				//System.out.println("OIDAOAddTag: getTags - var : "+objBA.getTagName());
			}

			
		} catch (Exception e) {
			logger.error("getTags() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return list;
	}
	
	public List getTags(Connection conn, String id, String strQuery) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		List list = new ArrayList();
		OIBAAddTag objBA = null;
		
		try {
			pst = conn.prepareStatement(strQuery);
			pst.setString(1, id);
			rs = pst.executeQuery();
			
			while(rs.next())
			{
				objBA= new OIBAAddTag();
				objBA.setTagName(rs.getString("NAME"));	
				objBA.setTagId(new Integer(rs.getInt("TAGID")));
				list.add(objBA);
				//System.out.println("OIDAOAddTag: getTags - var : "+objBA.getTagName());
			}

			
		} catch (Exception e) {
			logger.error("getTags() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return list;
	}
}
