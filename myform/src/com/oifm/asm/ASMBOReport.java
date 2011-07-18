/*********************************ASMBOReport.java******************* 
 * Title 		: ASMBOReport
 * Description 	: This class is the BO Class for ASM Report. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 23 - Dec -2005
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
import com.oifm.utility.OIUtilities;


public class ASMBOReport extends OIBaseBo {
	private static Logger objLogger = Logger.getLogger(ASMBOReport.class);
	
	public OIResponseObject process(ASMBAReport objBA1,ASMBAReport objBA2)throws Exception{
		//System.out.println("ASMBOReport-process--BEGIN");
		if(OIUtilities.replaceNull(objBA1.getHiddenAction()).equalsIgnoreCase("SearchResult")){
		//	System.out.println("ASMBOReport-process--IFPART");
			return searchDetails(objBA1,"SearchResult");	
		}
		else if(OIUtilities.replaceNull(objBA1.getHiddenAction()).equalsIgnoreCase("ExportExcel")){
			//System.out.println("ASMBOReport-process--ElsePART-- Export");
			searchDetails(objBA2,"ExportExcel");
			return searchDetails(objBA1,"SearchResult");
			
		}
		return null;
	}
	
//	This method gets the connection and pass it to the DAO class to fetch the Search Result Details
	public OIResponseObject searchDetails(ASMBAReport objBA,String strAction) throws Exception{
		ASMDAOReport objDAO = new ASMDAOReport();
        try {
        	objLogger.debug("searchDetails");
    		//Get the connection
    		getConnection();
    		if(OIUtilities.replaceNull(objBA.getHidSortBy()).equalsIgnoreCase("")){
    			objBA.setHidSortBy("TOPIC");
    		}
    		//System.out.println("ASMBOReport-searchDetails--BEGIN" );
    		//getDivisionDetails
    		//objDAO.getDivisionDetails(connection,objBA);
    		//Call the DAO
            int totalRecord = objDAO.getTotalDetails(connection,objBA);
           // System.out.println("ASMBOReport-searchDetails-- total size" + totalRecord);
            //Get the letter pagination size
    		OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(objBA.getHidLink(),getPaginationSize(("ASMLETTERPAGESIZE")));
            aOIPageInfoBean.setTotalRec(totalRecord);
            objDAO.searchDetails(connection,objBA,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),totalRecord,strAction);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);

        } catch (SQLException se) {
            objLogger.error("searchDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("searchDetails - Exception" + e);
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
		ASMDAOReport objDAO = new ASMDAOReport();
		ASMDAOCommon objCommonDAO = new ASMDAOCommon();
		int nRows =0;
        try {
        	objLogger.debug("getPaginationSize");
    		//Get the connection
    		//Call the DAO
    		nRows = objCommonDAO.getPaginationSize(connection,strModule);
        	
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

//Get division details
//	This method gets the connection and pass it to the DAO class to fetch the Division Details
	public ArrayList getDivisionDetails(ASMBAReport objBA) throws Exception{
		ASMDAOReplyRedirectEdit objDAO = new ASMDAOReplyRedirectEdit();
        try {
    		//Get the connection
    		getConnection();
    		//getDivisionDetails
    		return objDAO.getDivisionDetails(connection);
        } catch (SQLException se) {
            objLogger.error("getDivisionDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getDivisionDetails - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}

//	Get division details
//	This method gets the connection and pass it to the DAO class to fetch the School Details
	public ArrayList getSchoolDetails(ASMBAReport objBA,ArrayList alList) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
        try {
    		//Get the connection
    		getConnection();
    		//getDivisionDetails
    		return objDAO.getSchoolDetails(connection,alList);
        } catch (SQLException se) {
            objLogger.error("getDivisionDetails - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getDivisionDetails - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}
//	Get Category List.
//	This method gets the connection and pass it to the DAO class to fetch the Category List.
	public ArrayList getCategoryList(ASMBAReport objBA) throws Exception{
		ASMDAOReplyRedirectEdit objDAO = new ASMDAOReplyRedirectEdit();
        try {
    		//Get the connection
    		getConnection();
    		//getCategoryList
    		return objDAO.getCategoryList(connection);
        } catch (SQLException se) {
            objLogger.error("getCategoryList - SQLException" + se);
            throw new Exception(se);
        } catch (Exception e) {
            objLogger.error("getCategoryList - Exception" + e);
            throw new Exception(e);
        } finally {
            freeConnection();
            objDAO = null;
        }
	}
	
}
