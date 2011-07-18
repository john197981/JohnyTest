/*
 * File name	= OIActionAuditTrail.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 21, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;


public class OIActionAuditTrail extends OIBaseAction {
	
	private static Logger logger = Logger.getLogger(OIActionAuditTrail.class);

	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName != null)? actionName:OIAuditTrailConstants.MAIN_PAGE;
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        ArrayList alFunctions = null;
        boolean isAllowAudit = false;
        OIBAAuditTrail objBAAuditTrail = new OIBAAuditTrail();
        OIFormAuditTrail objFormAuditTrail = (OIFormAuditTrail) form;
        OIBOAuditTrail objBOAuditTrail = null;
        OIResponseObject objResponseObject = null;
        HttpSession session = null;
        try{
            
        	session = request.getSession();
        	alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isAllowAudit = alFunctions.contains(OIFunctionConstants.AUDIT_TRAIL);
            
            if(isAllowAudit) {
            	objBOAuditTrail = new OIBOAuditTrail();
            	
            	if (strAction.equals(OIAuditTrailConstants.MAIN_PAGE)) {
            		PropertyUtils.copyProperties(objBAAuditTrail, objFormAuditTrail);
            		objResponseObject = new OIResponseObject();
            		request.setAttribute("pageName", "AuditTrail");
            		strForward = OIAuditTrailConstants.AUDIT_TRAIL_PAGE;
            		
            	} else if(strAction.equals(OIAuditTrailConstants.DO_SEARCH)) {
                    PropertyUtils.copyProperties(objBAAuditTrail, objFormAuditTrail);
                    objResponseObject = objBOAuditTrail.getTransactions(objBAAuditTrail);
                    request.setAttribute("Transactions", objResponseObject.getResponseObject("Transactions"));
                    request.setAttribute("pageName", "AuditTrail");
                    strForward = OIAuditTrailConstants.AUDIT_TRAIL_PAGE;
                    
            	}
            } else {
                strRedirect = OIAuditTrailConstants.ERROR_DO+"?error=NoAccess";
            }
        } catch(Exception e) {
        	logger.error("TRY - CATCH : " + e);
            strRedirect = OIAuditTrailConstants.ERROR_DO+"?error=OIDEFAULT";
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
