/*
 * File name	= OIDAOAuditTrail.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 21, 2005
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


public class OIDAOAuditTrail extends OIBaseDao {
	private static Logger logger = Logger.getLogger(OIDAOAuditTrail.class);
	
	public OIDAOAuditTrail() {}
	
	public ArrayList searchTransaction (Connection conn, String strType, String strFrom, String strTo) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alTransactions = new ArrayList();
		OIAuditTrailBean objAuditBean = null;
		
		try{
            pst = conn.prepareStatement(OIAuditTrailSqls.QRY_AUDIT_TRANSACTION);
            pst.setString(1, strType);
            pst.setString(2, strFrom);
            pst.setString(3, strTo);
            rs = pst.executeQuery();

            while (rs.next()) {
            	objAuditBean = new OIAuditTrailBean();
            	objAuditBean.setStrUserID(rs.getString("USERID"));
            	objAuditBean.setStrNickname(rs.getString("NICKNAME"));
            	objAuditBean.setStrType(rs.getString("TRANSACTION_TYPE"));
            	objAuditBean.setStrDateTime(rs.getString("DATETIME"));
            	objAuditBean.setStrPostUserID(rs.getString("POSTUSERID"));
            	objAuditBean.setStrPostNickname(rs.getString("POSTNICKNAME"));
            	objAuditBean.setStrPostDateTime(rs.getString("POSTDATETIME"));
            	objAuditBean.setStrThreadID(rs.getString("THREADID"));
            	objAuditBean.setStrPostMessage(rs.getString("POSTED_CONTENT"));
            	objAuditBean.setStrModMessage(rs.getString("MODERATED_CONTENT"));
            	alTransactions.add(objAuditBean);
            }
            
        } catch(SQLException e) {
            logger.error("searchTransaction() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        return alTransactions;
	}
}
