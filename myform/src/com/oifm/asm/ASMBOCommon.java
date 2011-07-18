/*********************************ASMBOCommon.java******************* 
 * Title 		: ASMBOCommon
 * Description 	: This class is the BO Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;


public class ASMBOCommon extends OIBaseBo {
	private static Logger objLogger = Logger.getLogger(ASMBOCommon.class);
	
	public void process(ASMBACommon objBA,String contentId)throws Exception{
		getEditorsNote(objBA,contentId);
		getRecentLetters(objBA);
	}
	
//This method gets the connection and pass it to the DAO class to fetch the Editor's Note details	
	//14th March 2011, Added new parameter to the getEditorNote method for addressing the Editor note issue.
	private void getEditorsNote(ASMBACommon objBA, String contentId) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
        try {
        	objLogger.debug("getEditorsNote");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		objDAO.getEditorsNote(connection, objBA,contentId);
        	
        } catch (SQLException se) {
            objLogger.error("getEditorsNote - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getEditorsNote - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the Recent Letter details	
	private void getRecentLetters(ASMBACommon objBA) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
        try {
        	objLogger.debug("getRecentLetters");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		objDAO.getRecentLetters(connection, objBA);
        } catch (SQLException se) {
            objLogger.error("getRecentLetters - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getRecentLetters - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the Topic with the Letter ID
	public String getTopic(String strLetterID) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
		String strTopic ="";
        try {
        	objLogger.debug("getTopic");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		strTopic= objDAO.getTopic(connection, strLetterID);
        } catch (SQLException se) {
            objLogger.error("getTopic - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getTopic - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        return strTopic;
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the User details correspoding to the selected division list	
	public OIResponseObject getUserDetails(ASMBACommon objBA) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
        try {
        	objLogger.debug("getUserDetails");
    		//Get the connection
    		getConnection();
    		//getDivisionDetails
    		ArrayList alDiv = objDAO.getDivisionDetails(connection);
    		objBA.setAlDivision(alDiv);
    		//Call the DAO
            int totalRecord = objDAO.getTotalUserMail(connection,objBA.getCboDivision());
            //Get the letter pagination size
    		OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(objBA.getHidLink(),getPaginationSize(("RECORDS_PER_PAGE")));
            aOIPageInfoBean.setTotalRec(totalRecord);
            objDAO.getUserDetails(connection,objBA,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),totalRecord);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);

        } catch (SQLException se) {
            objLogger.error("getUserDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getUserDetails - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        addToResponseObject();
        return responseObject;
	}

//	This method gets the connection and pass it to the DAO class to fetch the Pagination Size for ASM 	
	private int getPaginationSize(String strModule) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
		int nRows =0;
        try {
        	objLogger.debug("getPaginationSize");
    		//Call the DAO
    		nRows = objDAO.getPaginationSize(connection,strModule);
        	
        } catch (SQLException se) {
            objLogger.error("getPaginationSize - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getPaginationSize - Exception" + e);
            throw new Exception(e);
        } finally {
            objDAO = null;
        }
        return nRows;
	}


//	This method gets the connection and pass it to the DAO class to fetch the code master descripion	
	public String getMessage(String strInput) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
		String strOutput="";
        try {
        	objLogger.debug("getMessage");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		strOutput = objDAO.getMessage(connection, strInput);
        	
        } catch (SQLException se) {
            objLogger.error("getMessage - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getMessage - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        return strOutput;
	}

//	This method gets the connection and pass it to the DAO class to fetch the User details 	
	public OIResponseObject getUserList(ASMBACommon objBA) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
        try {
        	objLogger.debug("getUserList");
    		//Get the connection
    		getConnection();
    		//Call the DAO
            int totalRecord = objDAO.getTotalUserList(connection);
            //Get the letter pagination size
    		OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(objBA.getHidLink(),getPaginationSize("RECORDS_PER_PAGE"));
            aOIPageInfoBean.setTotalRec(totalRecord);
            objDAO.getUserList(connection,objBA,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),totalRecord);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);

        } catch (SQLException se) {
            objLogger.error("getUserList - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getUserList - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        addToResponseObject();
        return responseObject;
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the configuration descripion	
	public String getMessageConfig(String strInput) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
		String strOutput="";
        try {
        	objLogger.debug("getMessageConfig");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		
    		strOutput = objDAO.getModuleDescription(connection, strInput);
        	
        } catch (SQLException se) {
            objLogger.error("getMessageConfig - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getMessageConfig - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        return strOutput;
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the User details 	
	public OIResponseObject getSnrMgmtUserList(ASMBACommon objBA) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
        try {
        	objLogger.debug("getSnrMgmtList");
    		//Get the connection
    		getConnection();
    		//Call the DAO
            int totalRecord = objDAO.getTotalSnrMgmtUserList(connection);
            //Get the letter pagination size
    		OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(objBA.getHidLink(),getPaginationSize("RECORDS_PER_PAGE"));
            aOIPageInfoBean.setTotalRec(totalRecord);
            objDAO.getSnrMgmtUserList(connection,objBA,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),totalRecord);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);

        } catch (SQLException se) {
            objLogger.error("getSnrMgmtList - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getSnrMgmtList - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
        addToResponseObject();
        return responseObject;
	}
	
}
