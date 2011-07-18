/*
 * Group Maintenance module Data Access Object
 * 
 * File name	= OIDAOGroups.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 11, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAOGroups extends OIBaseDao {

	private static final Logger logger = Logger.getLogger(OIDAOGroups.class);
	
	public OIDAOGroups() {}
	
	public ArrayList getGroupsList (Connection conn) throws SQLException{
		ArrayList alGroupsList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAGroups objBAGroups= null;
		
		try{
         //   System.out.println("OIDAOGroups-getGroupsList" + OIGroupsSqls.QRY_GROUP_LIST);
			pst = conn.prepareStatement(OIGroupsSqls.QRY_GROUP_LIST);
            
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	objBAGroups = new OIBAGroups();
            	objBAGroups.setGroupId(new Integer(rs.getInt("GROUPID")));
            	objBAGroups.setName(rs.getString("NAME"));
            	objBAGroups.setDescription(rs.getString("DESCRIPTION"));
            	objBAGroups.setCreatedBy(rs.getString("CREATED_BY"));
            	objBAGroups.setNickname(rs.getString("NICKNAME"));
            	objBAGroups.setDivName(rs.getString("DIVNAME"));
            	objBAGroups.setCreatedOnstr(rs.getString("CREATEDATE"));
            	objBAGroups.setNumOfUsers(new Integer(rs.getInt("NUM_OF_USERS")));
            	alGroupsList.add(objBAGroups);
            }
        } catch(SQLException e) {
            logger.error("getGroupsList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alGroupsList;
	}
	
	public OIBAGroups getGroupDetails (Connection conn, Integer groupID) throws SQLException {
		OIBAGroups objBAGroups = new OIBAGroups();
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
            pst = conn.prepareStatement(OIGroupsSqls.QRY_GROUP_DETAILS);
            pst.setInt(1, groupID.intValue());
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	objBAGroups.setGroupId(groupID);
            	objBAGroups.setName(rs.getString("NAME"));
            	objBAGroups.setDescription(rs.getString("DESCRIPTION"));
            	objBAGroups.setCreatedOn(rs.getDate("CREATED_ON"));
            	objBAGroups.setCreatedBy(rs.getString("CREATED_BY"));
            }
        } catch(SQLException e) {
            logger.error("getGroupDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return objBAGroups;
	}
	
	public ArrayList getGroupMembers (Connection conn, Integer groupID, String sortKey, String sortOrder) throws SQLException {
		ArrayList alMembersList = new ArrayList();
		OIGroupMembersBean objMember = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
			//System.out.println("query="+OIGroupsSqls.QRY_GROUP_MEMBERS);
            pst = conn.prepareStatement(OIGroupsSqls.QRY_GROUP_MEMBERS + OIGroupsConstants.SORT_COLUMNS[Integer.parseInt(sortKey)] + " " + OIGroupsConstants.SORT_ORDER[Integer.parseInt(sortOrder)]);
            pst.setInt(1, groupID.intValue());
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	objMember = new OIGroupMembersBean();
            	objMember.setUserID(rs.getString("USERID"));
            	objMember.setName(rs.getString("NAME"));
            	objMember.setDivision(rs.getString("DIVISION"));
            	objMember.setEmailID(rs.getString("EMAILID"));
            	objMember.setNumOfPostings(new Integer(rs.getInt("TOTALPOSTINGS")));
            	alMembersList.add(objMember);
            }
        } catch(SQLException e) {
            logger.error("getGroupMembers() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alMembersList;
	}
	
	public boolean createGroup(Connection conn, OIBAGroups objBAGroups, String strUserID) throws SQLException {
		PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIGroupsSqls.QRY_CREATE_GROUP);
            pst.setString(1, objBAGroups.getName());
            pst.setString(2, objBAGroups.getDescription());
            pst.setString(3, strUserID);
            if (pst.executeUpdate() <= 0) throw new SQLException();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("createGroup() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
	}
	
	public boolean editGroup(Connection conn, OIBAGroups objBAGroups) throws SQLException {
		PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIGroupsSqls.QRY_EDIT_GROUP);
            pst.setString(1, objBAGroups.getName());
            pst.setString(2, objBAGroups.getDescription());
            pst.setInt(3, objBAGroups.getGroupId().intValue());
            if (pst.executeUpdate() <= 0) throw new SQLException();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("editGroup() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
	}
	
	public boolean deleteGroup(Connection conn, OIBAGroups objBAGroups) throws SQLException {
		PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIGroupsSqls.QRY_DELETE_GROUP);
            pst.setInt(1, objBAGroups.getGroupId().intValue());
            if (pst.executeUpdate() <= 0) throw new SQLException();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("deleteGroup() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
	}
	
	public int removeMember(Connection conn, OIBAGroups objBAGroups, String strUserID) throws SQLException {
		PreparedStatement pst = null;
        int ret = 0;
        
        try {
            pst = conn.prepareStatement(OIGroupsSqls.QRY_REMOVE_MEMBER);
            pst.setInt(1, objBAGroups.getGroupId().intValue());
            pst.setString(2, strUserID);
            if ((ret = pst.executeUpdate()) < 0) throw new SQLException();
            
        } catch (SQLException e) {
            logger.error("removeMember() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
	}
	
	public boolean removeMembers(Connection conn, OIBAGroups objBAGroups) throws SQLException {
		PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIGroupsSqls.QRY_REMOVE_MEMBERS);
            pst.setInt(1, objBAGroups.getGroupId().intValue());
            pst.executeUpdate();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("removeMembers() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
	}
	
	public boolean removeThreadGroup(Connection conn, OIBAGroups objBAGroups) throws SQLException {
		PreparedStatement pst = null;
        boolean ret = false;
        
        try {
            pst = conn.prepareStatement(OIGroupsSqls.QRY_REMOVE_THREAD_GROUP);
            pst.setInt(1, objBAGroups.getGroupId().intValue());
            pst.executeUpdate();
            
            ret = true;
        } catch (SQLException e) {
            logger.error("removeThreadGroup() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closePstatement(pst);
        }
        
        return ret;
	}
	
	public boolean isOwner (Connection conn, Integer groupID, String userID) throws SQLException {
		boolean isOwner = false;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
            pst = conn.prepareStatement(OIGroupsSqls.QRY_CHECK_GROUP_OWNER);
            pst.setInt(1, groupID.intValue());
            pst.setString(2, userID);
            pst.setString(3, userID);
            rs = pst.executeQuery();
            
            isOwner = rs.next();
            
        } catch(SQLException e) {
            logger.error("isOwner() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return isOwner;
	}
	
	public boolean hasDuplicate (Connection conn, Integer groupID, String name) throws SQLException {
		boolean hasDuplicate = false;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try{
            pst = conn.prepareStatement(OIGroupsSqls.QRY_CHECK_DUPLICATE_NAME);
            pst.setInt(1, groupID.intValue());
            pst.setString(2, name);
            rs = pst.executeQuery();
            
            hasDuplicate = !rs.next();
            
        } catch(SQLException e) {
            logger.error("hasDuplicate() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("hasDuplicate() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return hasDuplicate;
	}
	
	public Integer getGroupID (Connection conn, String name) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try{
            pst = conn.prepareStatement(OIGroupsSqls.QRY_GROUPID);
            pst.setString(1, name);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	ret = rs.getInt("GROUPID");
            }
            
        } catch(SQLException e) {
            logger.error("getGroupID() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return new Integer(ret);
	}
	
	public ArrayList getFixedGroupsList (Connection conn) throws SQLException{
		ArrayList alGroupsList = new ArrayList();
		PreparedStatement pst = null;
		ResultSet rs = null;
		OIBAGroups objBAGroups= null;
		
		try{
            pst = conn.prepareStatement(OIGroupsSqls.QRY_FIXED_GROUP_LIST);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	objBAGroups = new OIBAGroups();
            	objBAGroups.setGroupId(new Integer(rs.getInt("GROUPID")));
            	objBAGroups.setName(rs.getString("NAME"));
            	objBAGroups.setDescription(rs.getString("DESCRIPTION"));
            	objBAGroups.setCreatedBy(rs.getString("CREATED_BY"));
               	objBAGroups.setCreatedOnstr(rs.getString("CREATEDATE"));
               	objBAGroups.setNumOfUsers(new Integer(rs.getInt("MEMBERSCOUNT")));
              	alGroupsList.add(objBAGroups);
            }
        } catch(SQLException e) {
            logger.error("getFixedGroupsList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
            
          //  System.out.println("OIDAOGroups: getFixedGroupsList - FixedGroupsList : "+alGroupsList.size());
        }
		
		return alGroupsList;
	}
}
