/*********************************ASMBOHomePage.java.java******************* 
 * Title 		: ASMBOHomePage
 * Description 	: This class is the BO Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;


public class ASMBOHomePage extends OIBaseBo {
	private static Logger objLogger = Logger.getLogger(ASMBOHomePage.class);
	
	public void process(ASMBVHomePage objBV)throws Exception{
		getAnnouncement(objBV);
		getLetterReplyDetails(objBV);
	}
//This method gets the connection and pass it to the DAO class to fetch the Announcement details	
	private void getAnnouncement (ASMBVHomePage objBV) throws Exception{
		ASMDAOHomePage objDAO = new ASMDAOHomePage();
        try {
        	objLogger.debug("getAnnouncement");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		objDAO.getAnnouncement(connection, objBV);
        	
        } catch (SQLException se) {
            objLogger.error("getAnnouncement - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getAnnouncement - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the Letter details	
	private void getLetterReplyDetails(ASMBVHomePage objBV) throws Exception{
		ASMDAOHomePage objDAO = new ASMDAOHomePage();
        try {
        	objLogger.debug("getLetterReplyDetails");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		objDAO.getLetterReplyDetails(connection, objBV);
        	
        } catch (SQLException se) {
            objLogger.error("getLetterReplyDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getLetterReplyDetails - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}
	
}
