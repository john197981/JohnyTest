/*
 * File name	= ASMDAOAbout.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 16, 2005
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


public class ASMDAOAbout extends OIBaseDao {
	private static final Logger logger = Logger.getLogger(ASMDAOAbout.class);
	
	public ASMDAOAbout() {}
	
	public ArrayList getList(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();
		ASMBAAbout objBA = null;
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_ABOUT_RETRIEVE_LIST);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objBA = new ASMBAAbout();
				objBA.setRownum(rs.getInt("NUM"));
				objBA.setId(rs.getInt("CONTENTID"));
				objBA.setQuestion(rs.getString("TOPIC"));
				objBA.setAnswer(OIUtilities.clobToString(rs.getClob("CONTENT")));
				
				list.add(objBA);
			}
		} catch (Exception e){
			logger.error("getList() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return list;
	}
	
	public ASMBAAbout getDetail(Connection conn, int id) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ASMBAAbout objBA = new ASMBAAbout();
		
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_ABOUT_RETRIEVE);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				objBA.setId(id);
				objBA.setQuestion(rs.getString("TOPIC"));
				objBA.setAnswer(OIUtilities.clobToString(rs.getClob("CONTENT")));
			}
		} catch (Exception e) {
			logger.error("getDetail - " + e);
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return objBA;
	}
	
	public boolean create(Connection conn, String question, String answer, String userID) throws Exception{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_ABOUT_CREATE);
			pst.setString(1, question);
			pst.setString(2, answer);
			pst.setString(3, userID);
			pst.setString(4, userID);
			
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
		return (count == 1);
	}
	
	public boolean modify(Connection conn, int id, String question, String answer, String userID) throws Exception{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_ABOUT_MODIFY);
			pst.setString(1, question);
			pst.setString(2, answer);
			pst.setString(3, userID);
			pst.setInt(4, id);
			
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
		return (count == 1);
	}
	
	public boolean delete(Connection conn, int id) throws Exception{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_ABOUT_DELETE);
			pst.setInt(1, id);
			
			count = pst.executeUpdate();
		} catch (Exception e) {
			logger.error("delete - " + e);
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
