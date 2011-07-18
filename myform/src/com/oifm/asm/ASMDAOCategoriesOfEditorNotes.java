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


public class ASMDAOCategoriesOfEditorNotes extends OIBaseDao {
	private static final Logger logger = Logger.getLogger(ASMDAOCategoriesOfEditorNotes.class);
	
	public ASMDAOCategoriesOfEditorNotes() {}
	
	public ArrayList getEditorNoteList(Connection conn) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList notesList = new ArrayList();
		ASMBVEditorNotesDetails objASMBVEditorNotesDetails ;
		try {
			pst = conn.prepareStatement(ASMGlobals.QRY_GET_ALL_EDITOR_NOTES);
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objASMBVEditorNotesDetails = new ASMBVEditorNotesDetails();
				objASMBVEditorNotesDetails.setStrContentID(new Integer(rs.getInt("CONTENTID")));
				objASMBVEditorNotesDetails.setStrTitle(rs.getString("TOPIC"));
				logger.info("note's details b4 adding to the array :::: " + objASMBVEditorNotesDetails.getStrContentID());
				notesList.add(objASMBVEditorNotesDetails);
			}
		} catch (Exception e){
			logger.error("getList() - " + e);
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return notesList;
	}
	
	
	public ASMBVEditorNotesDetails getNotesContentByTitle(Connection conn,int contentId) throws Exception {
		PreparedStatement pst = null;
		ResultSet rs = null;
		ASMBVEditorNotesDetails objBA = null;
		try {
			System.out.println("inside DAO getNotesContentByTitle () " + contentId);
			//To be moved to the constant file later.....Nimitta
			String qry ="SELECT CONTENTID,TOPIC,CONTENT FROM OI_AM_CONTENT WHERE contenttype ='E' AND CONTENTID=? ";
			pst = conn.prepareStatement(qry);
			pst.setInt(1, contentId);
			//pst.setInt(2, letter.intValue());
			rs = pst.executeQuery();
			
			while (rs.next()) {
				objBA = new ASMBVEditorNotesDetails();
				objBA.setStrContentID(new Integer(contentId));
				objBA.setStrTitle(rs.getString("TOPIC"));
				objBA.setStrNoteContent(OIUtilities.clobToString(rs.getClob("CONTENT")));
				//list.add(objBA);
			}
		} catch (Exception e){
			logger.error("getList() - " + e);
			e.printStackTrace();
			throw e;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pst);
		}
		return objBA;
	}
	
		
}
