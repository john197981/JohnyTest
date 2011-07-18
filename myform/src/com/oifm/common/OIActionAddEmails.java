/*
 * File name	= OIActionAddEmails.java
 * Package		= com.oifm.common
 * Created on 	= Aug 23, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;


public class OIActionAddEmails extends OIBaseAction {
	
	Logger logger = Logger.getLogger(OIActionAddEmails.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName != null)? actionName:OIAddEmailsConstants.ACTION_INPUT;
        String strForward = "";
        String strRedirect = "";
        OIResponseObject objResponseObject = null;
        OIFormAddEmails objForm = (OIFormAddEmails) form;
        OIBAAddEmails objBA = new OIBAAddEmails();
        OIBOAddEmails objBO = null;
        
        try {
        	if (objForm.getStrGroupID() != null && !objForm.getStrGroupID().equals("")){
	        	objBO = new OIBOAddEmails();
	        	if (strAction.equals(OIAddEmailsConstants.ACTION_INPUT)) {
	        		objResponseObject = new OIResponseObject();
	        		strForward = OIAddEmailsConstants.INPUT_PAGE;
	        		
	        	} else if (strAction.equals(OIAddEmailsConstants.ACTION_VERIFY)) {
	        		PropertyUtils.copyProperties(objBA, objForm);
	        		objResponseObject = objBO.verifyEmails(objBA);
	        		request.setAttribute("SuccessMails", objResponseObject.getResponseObject("SuccessMails"));
	        		request.setAttribute("FailedMails", objResponseObject.getResponseObject("FailedMails"));
	        		strForward = OIAddEmailsConstants.VERIFY_PAGE;
	        		
	        	} else if (strAction.equals(OIAddEmailsConstants.ACTION_ADD_MEMBERS)) {
	        		PropertyUtils.copyProperties(objBA, objForm);
	        		objResponseObject = objBO.addGroupMembers(objBA);
	        		request.setAttribute("Added", objResponseObject.getResponseObject("Added"));
	        		strForward = OIAddEmailsConstants.INPUT_PAGE;
	        		
	        	}
        	} else strRedirect = OIAddEmailsConstants.ERROR_DO+"?error=NoAccess";
        	
		} catch(Exception e) {
			logger.error("TRY - CATCH test : " + e);
			logger.info(e.getCause().toString());
			strRedirect = OIAddEmailsConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally {
			//logger.error("Inside finally");
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
    
		if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}

}
