/*
 * File name	= ASMBOAbout.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;


public class ASMBOCategoriesOfEditorNotes extends OIBaseBo {
	private static final Logger logger = Logger.getLogger(ASMBOCategoriesOfEditorNotes.class);
	
	public ASMBOCategoriesOfEditorNotes() {}
	
	public ArrayList getEditorNotesList() {
		ArrayList temp = null;
		ASMDAOCategoriesOfEditorNotes objDAO = new ASMDAOCategoriesOfEditorNotes();
		try {
			getConnection();
			temp = objDAO.getEditorNoteList(connection);
			
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getList() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getList() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("List", temp);
		}
		return temp;
	}
	
	public OIResponseObject readNotes()
    {
		logger.info("Start of readCategory");
        try
        {
            getConnection();
            ArrayList arOIBAForumCategory = new ASMDAOCategoriesOfEditorNotes().getEditorNoteList(connection);
            logger.info("Size is"+arOIBAForumCategory.size());
            responseObject.addResponseObject("arOIBAForumCategory",arOIBAForumCategory);            
           
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        logger.info("End of readCategory");
        return responseObject;
    }
	
	public OIResponseObject readNotesDetail(int contentID)
    {
		System.out.println("Start of readNotesDetail");
        try
        {
            getConnection();
            ASMBVEditorNotesDetails objDetails = new ASMDAOCategoriesOfEditorNotes().getNotesContentByTitle(connection,contentID);
            responseObject.addResponseObject("objDetails",objDetails);            
           
        }
        catch(Exception e)
        {
            error = e.getMessage();
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        logger.info("End of readCategory");
        return responseObject;
    }
		
	public ASMBVEditorNotesDetails getEditorNotesDetails(int ContentID) {
		ASMBVEditorNotesDetails obj = null;
		ASMDAOCategoriesOfEditorNotes objDAO = new ASMDAOCategoriesOfEditorNotes();
		try {
			getConnection();
			obj = objDAO.getNotesContentByTitle(connection, ContentID);
			
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getDetail() - SQLException - " + e);
			e.printStackTrace();
		} catch (Exception e) {
			error = "OIDEFAULT";
			e.printStackTrace();
			logger.error("getDetail() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("LetterContentDetail", obj);
		}
		return obj;
	}

	
}
