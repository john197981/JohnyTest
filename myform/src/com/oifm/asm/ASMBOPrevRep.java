/*********************************ASMBOPrevRep.java******************* 
 * Title 		: ASMBOPrevRep
 * Description 	: This class is the BO Class for ASM Previous Replies. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 19 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;


public class ASMBOPrevRep extends OIBaseBo {
	private static Logger objLogger = Logger.getLogger(ASMBOHomePage.class);
	
	public OIResponseObject process(ASMBAPrevRep objBA)throws Exception{
		return getRecentLetters(objBA);
	}
//This method gets the connection and pass it to the DAO class to fetch the Announcement details	
	private OIResponseObject getRecentLetters(ASMBAPrevRep objBA) throws Exception{
		ASMDAOPrevRep objDAO = new ASMDAOPrevRep();
        try {
        	objLogger.debug("getRecentLetters");
    		//Get the connection
    		getConnection();
    		//Call the DAO
    		//get the short description
    		ASMDAOCommon objCommonDAO =new ASMDAOCommon(); 
    		objBA.setModuleDesc(objCommonDAO.getModuleDescription(connection,"ASMPREVREPWELCOME"));
    		
    		//Get the number of months
    		objBA.setHidMonths(getPaginationSize("ASMMONTHS"));
            int totalRecord = objDAO.getTotalPrevRep(connection,objBA.getHiddenAction());
            //Get the letter pagination size
    		OIPageInfoBean aOIPageInfoBean = new OIPageInfoBean(objBA.getHidLink(),getPaginationSize("ASMLETTERPAGESIZE"));
            //aOIPageInfoBean.setRecPerPage();
            aOIPageInfoBean.setTotalRec(totalRecord);
            objDAO.getRecentLetters(connection,objBA,aOIPageInfoBean.getStartRec(),aOIPageInfoBean.getEndRec(),totalRecord);

            //responseObject.addResponseObject("ObjASMBAPrevRep",objBA);
            responseObject.addResponseObject(OIConsultConstant.K_aOIPageInfoBean,aOIPageInfoBean);

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
            //freeConnection();
            objDAO = null;
        }
        return nRows;
	}

}
