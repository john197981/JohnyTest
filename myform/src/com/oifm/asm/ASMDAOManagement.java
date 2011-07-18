/*
 * File name	= ASMDAOManagement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 21, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;


public class ASMDAOManagement extends OIBaseDao {
	
	private static final Logger logger = Logger.getLogger(ASMDAOManagement.class);
	
	public ASMDAOManagement() {}
	
	public ArrayList getList(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList list = null;
		ASMBAManagement objBA = null;
		
		try {
			list = new ArrayList();
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_LIST);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objBA = new ASMBAManagement();
				objBA.setId(rs.getInt("MGMTID"));
				objBA.setHeading(rs.getString("HEADING"));
				objBA.setSubheading(rs.getString("SUBHEADING"));
				objBA.setName(rs.getString("NAME"));
				objBA.setDesignation(rs.getString("DESIGNATION"));
				objBA.setDivision(rs.getString("DIVISION"));
				objBA.setProfile(OIUtilities.clobToString(rs.getClob("PROFILE")));
				objBA.setDivisionurl(rs.getString("DIVISIONURL"));
				objBA.setPhotograph(rs.getString("PHOTOGRAPH"));
				
				list.add(objBA);
			}
			
		} catch (Exception e) {
			logger.error("getList() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return list;
	}
	
	public ASMBAManagement getDetail(Connection conn, int id) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ASMBAManagement objBA = null;
		
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_DETAIL);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objBA = new ASMBAManagement();
				objBA.setId(rs.getInt("MGMTID"));
				objBA.setHeading(rs.getString("HEADING"));
				objBA.setSubheading(rs.getString("SUBHEADING"));
				objBA.setName(rs.getString("NAME"));
				objBA.setDesignation(rs.getString("DESIGNATION"));
				objBA.setDivision(rs.getString("DIVISION"));
				objBA.setProfile(OIUtilities.clobToString(rs.getClob("PROFILE")));
				objBA.setDivisionurl(rs.getString("DIVISIONURL"));
				objBA.setPhotograph(rs.getString("PHOTOGRAPH"));
				
			}
			
		} catch (Exception e) {
			logger.error("getDetail() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return objBA;
	}
	
	public boolean create(Connection conn, String heading, String subheading, String name, String designation, String division, String profile, String divisionurl, String userID) throws Exception {
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_CREATE);
			pst.setString(1, heading);
			pst.setString(2, subheading);
			pst.setString(3, name);
			pst.setString(4, designation);
			pst.setString(5, division);
			pst.setString(6, profile);
			pst.setString(7, divisionurl);
			pst.setString(8, userID);
			pst.setString(9, userID);
			
			count = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("create() - " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count == 1;
	}
	
	public boolean modify(Connection conn, int id, String heading, String subheading, String name, String designation, String division, String profile, String divisionurl, String photograph, String userID) throws Exception{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_MODIFY);
			pst.setString(1, heading);
			pst.setString(2, subheading);
			pst.setString(3, name);
			pst.setString(4, designation);
			pst.setString(5, division);
			pst.setString(6, profile);
			pst.setString(7, divisionurl);
			pst.setString(8, photograph);
			pst.setString(9, userID);
			pst.setInt(10, id);
			
			count = pst.executeUpdate();
			
		} catch (Exception e) {
			logger.error("modify() - " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count == 1;
	}
	
	public boolean delete(Connection conn, int id) throws Exception {
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_DELETE);
			pst.setInt(1, id);
			
			count = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("delete() - " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return count == 1;
	}
	
	public int getID(Connection conn, String name) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_GET_ID);
			pst.setString(1, name);
			
			rs = pst.executeQuery();
			
			if (rs.next()) ret = rs.getInt("MGMTID");
			
		} catch (Exception e) {
			logger.error("getId() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return ret;
	}
	
	public boolean setPhoto(Connection conn, String fileName, int id) throws Exception {
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_SET_PHOTO);
			pst.setString(1, fileName);
			pst.setInt(2, id);
			
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
	
	public boolean removePhoto(Connection conn, int id) throws Exception {
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_REM_PHOTO);
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
	
	public String getWelcomeMessage(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		String ret = null;
		
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_MGMNT_WELCOME_MSG);
			rs = pst.executeQuery();
			
			if (rs.next()) ret = rs.getString("VALUE");
			else ret = new String();
			
		} catch (Exception e) {
			logger.error("getWelcomeMessage() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return ret;
	}
}
