/*********************************ASMBOReplyRedirectEdit.java******************* 
 * Title 		: ASMBOReplyRedirectEdit
 * Description 	: This class is the BO Class for ASM Reply/Redirect Admin page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 20 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIUtilities;


public class ASMBOReplyRedirectEdit extends OIBaseBo {
	private static Logger objLogger = Logger.getLogger(ASMBOReplyRedirectEdit.class);
	
	public OIResponseObject process(ASMBVReplyRedirectEdit objBV)throws Exception{
		if(OIUtilities.replaceNull(objBV.getHiddenAction()).equalsIgnoreCase("AdminPage")){
			
			OIResponseObject objResp=getLetterDetails(objBV);
			if(OIUtilities.replaceNull(objBV.getTxtMessage()).equals("")){
				ASMBOCommon objCommonBo =new ASMBOCommon();
				objBV.setTxtMessage(objCommonBo.getMessageConfig("ASMREDIRECTMESSAGE"));
			}
			return 	objResp;
		}
		// save the page
		else if(OIUtilities.replaceNull(objBV.getHiddenAction()).equalsIgnoreCase("Save") || 
				OIUtilities.replaceNull(objBV.getHiddenAction()).equalsIgnoreCase("Redirect")){
			//System.out.println("ASMBOReplyRedirectEdit-process calls save method");	
			saveLetterDetails(objBV);
				
				OIResponseObject objResp=getLetterDetails(objBV);
				return 	objResp;
				
		}
		//This is to delete Added by Rakesh 
		else if(objBV.getHiddenAction().equalsIgnoreCase("Delete")){
			//System.out.println("The delete method has called the delete in process ");
			deleteLetter(objBV);
			
			OIResponseObject objResp=deleteLetter(objBV);
			return 	objResp;
	}		
		return null;
	}
//This method gets the connection and pass it to the DAO class to fetch the Letter information	
	private OIResponseObject getLetterDetails(ASMBVReplyRedirectEdit objBV) throws Exception{
		ASMDAOReplyRedirectEdit objDAO = new ASMDAOReplyRedirectEdit();
        try {
        	objLogger.debug("getLetterDetails");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		//getDivisionDetails
    		objBV.setAlDivision(objDAO.getDivisionDetails(connection));
    		//getCategoryList
    		objBV.setAlCategoryList(objDAO.getCategoryList(connection));
    		//Get the letter details
    		//Populating the page
   			objDAO.getLetterDetails(connection,objBV);	
    	
        } catch (SQLException se) {
            objLogger.error("getLetterDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getLetterDetails - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        addToResponseObject();
        return responseObject;
	}
	
//	This method gets the connection and pass it to the DAO class to save the Letter information	
	private OIResponseObject saveLetterDetails(ASMBVReplyRedirectEdit objBV) throws Exception{
		ASMDAOReplyRedirectEdit objDAO = new ASMDAOReplyRedirectEdit();
        try {
        	objLogger.debug("saveLetterDetails");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		//objLogger.info("before division");
    		//getDivisionDetails
    		objBV.setAlDivision(objDAO.getDivisionDetails(connection));
    	//getCategoryList
    		objBV.setAlCategoryList(objDAO.getCategoryList(connection));
    		//	objBV.setAlCategoryList(objDAO.)
    		//objLogger.info("before Reminder");
    		if(OIUtilities.replaceNull(objBV.getRadRemainder()).equals("")){
    			objBV.setRadRemainder("A");
    		}
    		
    		objLogger.info("before DAO");
//    		//Save the letter details
   			objDAO.saveLetterDetails(connection,objBV);	
    	
        } catch (SQLException se) {
            objLogger.error("saveLetterDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("saveLetterDetails - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        addToResponseObject();
        return responseObject;
	}
		
	//This method gets the connection and pass the DAO to delete the Letter
	//Added by Rakesh--Created on 23 Jan 2008.
	public OIResponseObject deleteLetter(ASMBVReplyRedirectEdit objBV) throws Exception {
		ASMDAOReplyRedirectEdit objDAO = new ASMDAOReplyRedirectEdit();
		try {
			objLogger.info("The Path for delete in BO ");
			//System.out.println("ASMBOReplyRedirectEdit-deleteLetter");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		connection.setAutoCommit(false);
			//System.out.println("The before");
			objDAO.deleteLetter(connection, objBV);
			
			connection.commit();
			connection.setAutoCommit(true);
		} catch (Exception e) {
			error = e.getMessage();
			//logger.error(e);
			try {
				connection.rollback();
			} catch (Exception ex) {
			}
		} finally {
			freeConnection();
		}
		addToResponseObject();
		return responseObject;
	}
	
	public int getReminderDays() {
		ASMDAOReplyRedirectEdit objDAO = new ASMDAOReplyRedirectEdit();
		int ret = 0;
		try {
			getConnection();
			ret = objDAO.getReminderDays(connection);
		} catch (SQLException e) {
			objLogger.error("getReminderDays() - SQLException - " + e);
		} finally {
			freeConnection();
		}
		return ret;
	}

}
