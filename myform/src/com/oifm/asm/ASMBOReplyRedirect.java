/*********************************ASMBOReplyRedirect.java******************* 
 * Title 		: ASMBOReplyRedirect
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
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.utility.OIUtilities;


public class ASMBOReplyRedirect extends OIBaseBo {
	private static Logger objLogger = Logger.getLogger(ASMBOReplyRedirect.class);
	
	public OIResponseObject process(ASMBAReplyRedirect objBA)throws Exception{
		return getLetterDetails(objBA);
	}
//This method gets the connection and pass it to the DAO class to fetch the Announcement details	
	private OIResponseObject getLetterDetails(ASMBAReplyRedirect objBA) throws Exception{
		ASMDAOReplyRedirect objDAO = new ASMDAOReplyRedirect();
        try {
        	objLogger.debug("getLetterDetails");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		//Get the number of months
            int totalRecord = objDAO.getTotalReplyRedirect(connection);
            //Get the letter pagination size
    		OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(objBA.getHidLink(),getPaginationSize("ASMLETTERPAGESIZE"));
            //aOIPageInfoBean.setRecPerPage();
            aOIPageInfoBean.setTotalRec(totalRecord);
            // Set default sort by as TOPIC
            if(OIUtilities.replaceNull(objBA.getHidSortBy()).equalsIgnoreCase("")){
            	objBA.setHidSortBy("SUBMITTEDON DESC");
            }
            objDAO.getLetterDetails(connection,objBA,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),totalRecord);

            //responseObject.addResponseObject("ObjASMBAReplyRedirect",objBA);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);

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
	
//	This method gets the connection and pass it to the DAO class to fetch the Pagination Size for ASM 	
	public int getPaginationSize(String strModule) throws Exception{
		ASMDAOCommon objDAO = new ASMDAOCommon();
		int nRows =0;
        try {
        	objLogger.debug("getPaginationSize");
    		//Get the connection
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

}
