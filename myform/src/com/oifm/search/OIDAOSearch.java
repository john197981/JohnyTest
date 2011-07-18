/*
 * File name	= OIDAOSearch.java
 * Package		= com.oifm.search
 * Created on 	= Aug 14, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;


public class OIDAOSearch extends OIBaseDao {
	
	private static final Logger logger = Logger.getLogger(OIDAOSearch.class);
	
	public OIDAOSearch () {}
	
	public int getNumPerPage (Connection conn) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try{
            pst = conn.prepareStatement(OISearchSqls.QRY_NUM_PER_PAGE);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	ret = rs.getInt("VALUE");
            }
            
        } catch(SQLException e) {
            logger.error("getNumPerPage() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
	}
	
	public int getCount (Connection conn, String query, String userID) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try{
            pst = conn.prepareStatement(OISearchSqls.QRY_COUNT + query + ")");
            pst.setString(1, userID);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	ret = rs.getInt("COUNT");
            }
            
        } catch(SQLException e) {
            logger.error("getCount() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
	}
	
	public int getCount (Connection conn, String query) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try{
            pst = conn.prepareStatement(OISearchSqls.QRY_COUNT + query + ")");
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	ret = rs.getInt("COUNT");
            }
            
        } catch(SQLException e) {
            logger.error("getCount() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
	}
	
	public ArrayList getSearchResult (Connection conn, String query, String userID, int row1, int row2) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OISearchResultBean objResult = null;
		
		try{
            pst = conn.prepareStatement(OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2);
            pst.setString(1, userID);
            pst.setInt(2, row1);
            pst.setInt(3, row2);
            rs = pst.executeQuery();
            while (rs.next()) {
            	objResult = new OISearchResultBean();
            	objResult.setRowNum(rs.getString("NUM"));
            	objResult.setStrID(rs.getString("ID"));
            	objResult.setStrTitle(rs.getString("TITLE"));
            	objResult.setStrDescription(rs.getString("DESCRIPTION"));
            	objResult.setStrCreatedBy(rs.getString("CREATED_BY"));
            	objResult.setStrNickname(rs.getString("NICKNAME"));
            	objResult.setStrCreatedOn(rs.getString("CREATED_ON"));
            	alResult.add(objResult);
            }
            
        } catch(SQLException e) {
            logger.error("getSearchResult() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return alResult;
	}
	
	// Modified by K.K.Kumaresan on Aug 17,2009
	public ArrayList getSearchResult (Connection conn, String query, int row1, int row2) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OISearchResultBean objResult = null;
		
		try{
            pst = conn.prepareStatement(OISearchSqls.QRY_ROW1 + query + OISearchSqls.QRY_ROW2);
            pst.setInt(1, row1);
            pst.setInt(2, row2);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	objResult = new OISearchResultBean();
            	objResult.setRowNum(rs.getString("NUM"));
            	objResult.setStrID(rs.getString("letterid"));
            	objResult.setStrTitle(rs.getString("topic"));
            	objResult.setStrDescription(rs.getString("SUBJECT"));
            	objResult.setStrCreatedBy(rs.getString("CREATEDBY"));
            	objResult.setStrNickname(rs.getString("NICKNAME"));
            	objResult.setStrCreatedOn(rs.getString("CREATED_ON"));
            	alResult.add(objResult);
            }
            
        } catch(SQLException e) {
            logger.error("ASM getSearchResult() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return alResult;
	}
}
