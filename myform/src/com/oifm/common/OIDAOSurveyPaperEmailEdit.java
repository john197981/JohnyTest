/*
 * File name	= OIDAOSurveyPaperEmailEdit.java
 * Package		= com.oifm.common
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;


public class OIDAOSurveyPaperEmailEdit extends OIBaseDao {
	private static final Logger logger = Logger.getLogger(OIDAOSurveyPaperEmailEdit.class);
	
	public OIDAOSurveyPaperEmailEdit() {}
	
	public String getEmail(Connection conn, String module, String id) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String message = new String();
		try {
			if("S".equals(module))
			{
				pst = conn.prepareStatement(OISendMailSqls.QRY_SU_EMAIL_RETRIEVE);
				pst.setString(1, id);
			}
			else if ("C".equals(module))
			{
				pst = conn.prepareStatement(OISendMailSqls.QRY_CP_EMAIL_RETRIEVE);
				pst.setString(1, id);
			}
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
	
	
	
	public boolean setEmail(Connection conn, String message, String module,String id) throws SQLException{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			if("S".equals(module))
			{
				pst = conn.prepareStatement(OISendMailSqls.QRY_SU_EMAIL_SAVE);
				pst.setString(1, message);
				pst.setString(2, id);;
			}
			else if ("C".equals(module))
			{
				pst = conn.prepareStatement(OISendMailSqls.QRY_CP_EMAIL_SAVE);
				pst.setString(1, message);
				pst.setString(2, id);
			}
			
			count = pst.executeUpdate();
		} catch (SQLException e) {
			logger.error("setEmail() : " + e);
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
