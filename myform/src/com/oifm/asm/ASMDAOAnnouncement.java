/*
 * File name	= ASMDAOAnnouncement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;


public class ASMDAOAnnouncement extends OIBaseDao {
	private static final Logger logger = Logger.getLogger(ASMDAOAnnouncement.class);
	
	public ASMDAOAnnouncement() {}
	
	/**
	 * Method to retrieve the ASM Main Page announcement from the database
	 * 
	 * @param conn SQL connection
	 * @return String containing the announcement message
	 * @throws SQLException
	 */
	public String getAnnouncement(Connection conn) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String message = new String();
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_ANNOUNCE_RETRIEVE);
			rs = pst.executeQuery();
			while (rs.next()) {
				message = OIUtilities.clobToString(rs.getClob("CONTENT"));
			}
		} catch (Exception e) {
			logger.error("getAnnouncement() : " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return message;
	}
	
	
	/**
	 * Method to save the ASM main page announcement to the database
	 * 
	 * @param conn SQL Connection
	 * @param objBA ASMBAAnnouncement containing information from the jsp
	 * @param userID The user ID of the currently logged in user
	 * @return Result of the operation
	 * @throws SQLException
	 */
	public boolean setAnnouncement(Connection conn, String message, String userID) throws SQLException{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_ANNOUNCE_SAVE);
			pst.setString(1, message);
			pst.setString(2, userID);
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("setAnnouncement() : " + e);
			throw e;
		} finally {
			if (count != 1) conn.rollback();
			else conn.commit();
			conn.setAutoCommit(true);
			OISQLUtilities.closePstatement(pst);
		}
		return (count == 1);
	}

}
