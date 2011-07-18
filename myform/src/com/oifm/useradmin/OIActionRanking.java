/*
 * File name	= OIActionRanking.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;


public class OIActionRanking extends OIBaseAction {
	
	Logger logger = Logger.getLogger(OIActionRanking.class);

	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName != null)? actionName:OIRankingConstants.RANK_MAIN;		        
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        ArrayList alFunctions = null;
        boolean isAllowRanking = false;
        OIBARanking objBARanking = new OIBARanking();
        OIFormRanking objFormRanking = objFormRanking = (OIFormRanking) form;
        OIBORanking objBORanking = null;
        OIResponseObject objResponseObject = null;
        try{
            alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isAllowRanking = alFunctions.contains(OIFunctionConstants.USER_RANKING);
            logger.info(" strAction "+strAction);
            if(isAllowRanking) {
            	objBORanking = new OIBORanking();
            	
            	if (strAction.equals(OIRankingConstants.RANK_MAIN)) {
            		PropertyUtils.copyProperties(objBARanking, objFormRanking);
            		objResponseObject = new OIResponseObject();
            		request.setAttribute("pageName", "RankMain");
            		strForward = OIRankingConstants.RANK_PAGE;
            	} else if(strAction.equals(OIRankingConstants.WEB_RANK)) {
            		PropertyUtils.copyProperties(objBARanking, objFormRanking);            		
            		objResponseObject = objBORanking.getWebsiteRankingList();
                    request.setAttribute("typeList", objResponseObject.getResponseObject("typeList"));
                    request.setAttribute("pageName", "WebRank");
                    strForward = OIRankingConstants.RANK_PAGE;
            	} else if (strAction.equals(OIRankingConstants.MOD_RANK)) {
            		PropertyUtils.copyProperties(objBARanking, objFormRanking);
            		objResponseObject = objBORanking.getPostModuleRanking(objBARanking);
            		request.setAttribute("PostResult", objResponseObject.getResponseObject("Result"));
            		objResponseObject = objBORanking.getThreadModuleRanking(objBARanking);
            		request.setAttribute("ThreadResult", objResponseObject.getResponseObject("Result"));
            		objResponseObject = objBORanking.getSurveyModuleRanking(objBARanking);
            		request.setAttribute("SurveyResult", objResponseObject.getResponseObject("Result"));
            		objResponseObject = objBORanking.getPaperModuleRanking(objBARanking);
            		request.setAttribute("PaperResult", objResponseObject.getResponseObject("Result"));
            		request.setAttribute("pageName", "ModRank");
            		strForward = OIRankingConstants.RANK_PAGE;
            	}
            } else {
                strRedirect = OIRankingConstants.ERROR_DO+"?error=NoAccess";
            }
        } catch(Exception e) {
        	logger.error("TRY - CATCH : " + e);
            strRedirect = OIRankingConstants.ERROR_DO+"?error=OIDEFAULT";
        } finally {
            if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
                request.setAttribute("error", objResponseObject.getResponseObject("error"));
        }
        
        if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
            return (mapping.findForward(strForward));
    }

}
