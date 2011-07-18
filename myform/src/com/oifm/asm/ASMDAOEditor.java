/*
 * File name	= ASMDAOEditor.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 19, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;


public class ASMDAOEditor extends OIBaseDao {
	private static final Logger logger = Logger.getLogger(ASMDAOEditor.class);
	
	public ASMDAOEditor() {}
	
	public ArrayList getList(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList list = null;
		ASMBAEditor objBA = null;
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_EDITORS_LIST_FULL);
			rs = pst.executeQuery();
			list = new ArrayList();
			
			while (rs.next()) {
				objBA = new ASMBAEditor();
				objBA.setRownum(rs.getInt("NUM"));
				objBA.setId(rs.getInt("CONTENTID"));
				objBA.setEditon(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("EDITON")));
				objBA.setTopic(rs.getString("TOPIC"));
				objBA.setContent(OIUtilities.clobToString(rs.getClob("CONTENT")));
				
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
	
	// Modified by K.K.Kumaresan
	public ArrayList getListPage(Connection conn, int row1, int row2) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList editorList = null;
		ArrayList letterList = null;
		ArrayList toSend = new ArrayList();
		ASMBAEditor objBA = null;
		String editorDate=null;
		String letterDate=null;
		try {
			
			// For getting editor's notes
			pst = conn.prepareStatement(ASMGlobals.QRY_ROW1 + ASMGlobals.QRY_EDITORS_LIST + ASMGlobals.QRY_ROW2);
			logger.info("**********ASMGlobals.QRY_ROW1 "+ASMGlobals.QRY_ROW1);
			logger.info("ASMGlobals.QRY_EDITORS_LIST "+ASMGlobals.QRY_EDITORS_LIST);
			logger.info("ASMGlobals.QRY_ROW2 "+ASMGlobals.QRY_ROW2);
			pst.setInt(1, row1);
			pst.setInt(2, row2);
			rs = pst.executeQuery();
			editorList = new ArrayList();
			
			//while (rs.next()) 
			if(rs.next())			// Changed by K.K.Kumaresan on Sep 25,2009 for showing the latest record only
			{
			
				objBA = new ASMBAEditor();
				objBA.setRownum(rs.getInt("NUM"));
				objBA.setId(rs.getInt("CONTENTID"));
				objBA.setEditon(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("EDITON")));
				editorDate=DateUtility.getDDMMYYYYStringFromDate(rs.getDate("EDITON"));
				objBA.setTopic(rs.getString("TOPIC"));
				objBA.setContent(OIUtilities.clobToString(rs.getClob("CONTENT")));
				objBA.setFlag("EDITORNOTE");
				editorList.add(objBA);
			}
			
			//			 For getting ASM Letters
			//  Changed by K.K.Kumaresan on Oct 29,2009 
			String query=" select ROWNUM NUM,LETTERID,PUBLISHEDON,TOPIC,REPLY,letter from OI_AM_LETTERS where status='P' " +
					" AND to_date(SYSDATE,'dd/mm/yyyy') >= to_date(PUBLISHEDFROM,'dd/mm/yyyy') AND to_date(SYSDATE,'dd/mm/yyyy')<=to_date(PUBLISHEDTO,'dd/mm/yyyy') " +
					" AND PUBLISHONSITE='Y' order by publishedon desc ";
			logger.info("getting ASM Letters query is "+query);
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			letterList = new ArrayList();
			
			//while (rs.next()) 
			if(rs.next())			// Changed by K.K.Kumaresan on Sep 25,2009 for showing the latest record only
			{
			
				objBA = new ASMBAEditor();
				objBA.setRownum(rs.getInt("NUM"));
				objBA.setId(rs.getInt("LETTERID"));
				objBA.setEditon(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("PUBLISHEDON")));
				letterDate=DateUtility.getDDMMYYYYStringFromDate(rs.getDate("PUBLISHEDON"));
				objBA.setTopic(rs.getString("TOPIC"));
				objBA.setReply(OIUtilities.clobToString(rs.getClob("REPLY")));
				objBA.setContent(OIUtilities.clobToString(rs.getClob("letter")));
				objBA.setFlag("ASMLETTER");
				
				letterList.add(objBA);
			}
			
			if(editorDate!=null && letterDate!=null)
			{
				if (CheckDate(editorDate,letterDate))		//if EditorDate is after letterDate,it returns true
				{
					toSend=editorList;
				}
				else
				{
					toSend=letterList;
				}
			}
			else
			{
				// If any one of the date is null,control comes here.
				if(editorDate==null)
				{
					toSend=letterList;
				}
				else if(letterDate==null)
				{
					toSend=editorList;
				}
			}	
			
			
		} catch (Exception e){
			logger.error("getList() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return toSend;
	}
	
	private boolean CheckDate(String editorDate,String letterDate) throws Exception
	{
		logger.info("Start of CheckDate");
		boolean flag=true;
		try
		{
			logger.info("editorDate is*******"+editorDate);    
			logger.info("letterDate is*******"+letterDate);    
			Date formatEditorDate = new SimpleDateFormat("dd/MM/yyyy").parse(editorDate);
			Date formatLetterDate=new SimpleDateFormat("dd/MM/yyyy").parse(letterDate);
			//if (formatTodayDate.after(formatStartDate)|| formatTodayDate.equals(formatStartDate))
			if (formatEditorDate.after(formatLetterDate))  
		    {
		    	logger.info("Inside IF");
		    	flag=true;
		    }
		    else
		    {
		    	logger.info("Inside else");
		    	flag=false;
		    }	
		}
		catch(Exception ie)
		{
			logger.info("The Exception in date check:"+ie.getMessage());
		}
		logger.info("End of CheckDate"+flag);
		return flag;
	}
	
	public ASMBAEditor getDetail(Connection conn, int id) throws Exception{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ASMBAEditor objBA = null;
		
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_EDITORS_DETAILS);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			objBA = new ASMBAEditor();
			
			while(rs.next()) {
				objBA.setId(id);
				objBA.setEditon(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("EDITON")));
				objBA.setTopic(rs.getString("TOPIC"));
				objBA.setContent(OIUtilities.clobToString(rs.getClob("CONTENT")));
			}
		} catch (Exception e) {
			logger.error("getDetail - " + e);
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		
		return objBA;
	}
	
	public boolean create(Connection conn, String editon, String topic, String content, String userID) throws Exception{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_EDITORS_CREATE);
			pst.setString(1, editon);
			pst.setString(2, topic);
			pst.setString(3, content);
			pst.setString(4, userID);
			pst.setString(5, userID);
			
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
	
	public boolean modify(Connection conn, int id, String editon, String topic, String content, String userID) throws Exception{
		PreparedStatement pst = null;
		int count = 0;
		
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(ASMGlobals.QRY_EDITORS_MODIFY);
			pst.setString(1, editon);
			pst.setString(2, topic);
			pst.setString(3, content);
			pst.setString(4, userID);
			pst.setInt(5, id);
			
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
			pst = conn.prepareStatement(ASMGlobals.QRY_EDITORS_DELETE);
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
	
	public int getCount (Connection conn) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try{
            pst = conn.prepareStatement(ASMGlobals.QRY_COUNT + ASMGlobals.QRY_EDITORS_LIST + ")");
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
	
	public int getNumPerPage (Connection conn) throws SQLException {
		PreparedStatement pst = null;
		ResultSet rs = null;
		int ret = 0;
		
		try{
            pst = conn.prepareStatement(ASMGlobals.QRY_EDITORS_NOTE_PAGE_SIZE);
            rs = pst.executeQuery();
            
            while (rs.next()) {
            	ret = rs.getInt("DESCRIPTION");
            }
            
        } catch(SQLException e) {
            logger.error("getNumPerPage() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        
        return ret;
	}
}
