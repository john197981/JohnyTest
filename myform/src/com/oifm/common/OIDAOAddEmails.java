/*
 * File name	= OIDAOAddEmails.java
 * Package		= com.oifm.common
 * Created on 	= Aug 22, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;


public class OIDAOAddEmails extends OIBaseDao {
	Logger logger = Logger.getLogger(OIDAOAddEmails.class);
	
	public OIDAOAddEmails () {}
	
	public ArrayList verifyEmail(Connection conn, String strGroupID, String strEmail) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alEmails = new ArrayList();
		OIBAAddEmails objEmail = null;
		try {
			pst = conn.prepareStatement(OIAddEmailsSqls.QRY_CHECK_EMAIL);
			pst.setString(1, strEmail);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objEmail = new OIBAAddEmails();
				objEmail.setStrEmailID(rs.getString("EMAILID"));
				objEmail.setStrUserID(rs.getString("USERID"));
				objEmail.setStrName(rs.getString("NAME"));
				objEmail.setAlreadyMember(checkGroupMember(conn, strGroupID, rs.getString("USERID")));
				alEmails.add(objEmail);
			}
		} catch(SQLException e) {
			logger.error("verifyEmail() : " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return alEmails;
	}
	
	private boolean checkGroupMember(Connection conn, String strGroupID, String strUserID) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		boolean ret = false;
		
		try {
			pst = conn.prepareStatement(OIAddEmailsSqls.QRY_CHECK_MEMBER);
			pst.setString(1, strGroupID);
			pst.setString(2, strUserID);
			rs = pst.executeQuery();
			
			ret = rs.next();
			
		} catch(SQLException e) {
			logger.error("checkGroupMember() : " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return ret;
	}
	
	public int addGroupMember(Connection conn, String strGroupID, String strUserID) throws SQLException {
		PreparedStatement pst = null;
		int ret = 0;
		
		try {
			pst = conn.prepareStatement(OIAddEmailsSqls.QRY_INSERT_MEMBER);
			pst.setString(1, strGroupID);
			pst.setString(2, strUserID);
			ret = pst.executeUpdate();
			
		} catch(SQLException e) {
			logger.error("addGroupMember() : " + e);
			throw e;
		} finally {
			OISQLUtilities.closePstatement(pst);
		}
		return 0;
	}
}
