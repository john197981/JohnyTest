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
import com.oifm.forum.admin.OIBAForumBoard;
import com.oifm.forum.admin.OIBAForumTopic;
import com.oifm.forum.admin.OIDAOForumBoard;
import com.oifm.forum.admin.OIDAOForumCategory;
import com.oifm.forum.admin.OIDAOForumTopic;


public class ASMBOCategoriesOfLetter extends OIBaseBo {
	private static final Logger logger = Logger.getLogger(ASMBOCategoriesOfLetter.class);
	
	public ASMBOCategoriesOfLetter() {}
	
	public int getCategorybyLetterID(int letterID) {
		int temp = 0;
		ASMDAOCategoriesOfLetter objDAO = new ASMDAOCategoriesOfLetter();
		try {
			getConnection();
			temp = objDAO.getCategorybyLetterID(connection,letterID);
			
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getList() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getList() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("id", new Integer(temp));
		}
		return temp;
	}
	
	
	
	
	public OIResponseObject readNotesDetail(int categoryID,int letterID)
    {
		//System.out.println("Start of readNotesDetail");
        try
        {
            getConnection();
            ASMBVLetterDetails objDetails = new ASMDAOCategoriesOfLetter().getLetterContentByLtrIDCtgryID(connection,new Integer(categoryID),new Integer(letterID));
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
	
	
	public ASMBVLetterDetails getLetterDetails(Integer categoryID,Integer letterID) {
		ASMBVLetterDetails obj = null;
		ASMDAOCategoriesOfLetter objDAO = new ASMDAOCategoriesOfLetter();
		try {
			getConnection();
			obj = objDAO.getLetterContentByLtrIDCtgryID(connection, categoryID,letterID);
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
	
	// By Kumaresan
	public OIResponseObject readCategory()
    {
		logger.info("Start of readCategory");
        try
        {
            getConnection();
            ArrayList arOIBAForumCategory = new ASMDAOCategoriesOfLetter().getCategoryList(connection);
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

	public OIResponseObject readLetters(String categoryID)
    {
		logger.info("Start of readCategory");
        try
        {
            getConnection();
            ArrayList objOIBAForumCategory = new ASMDAOCategoriesOfLetter().getLetterbyCategory(connection, categoryID);
            //logger.info("Size is"+objOIBAForumCategory.size());
            responseObject.addResponseObject("arOIBAForumLetter",objOIBAForumCategory);            
           
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

	
	
}
