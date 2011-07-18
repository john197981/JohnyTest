/*
 * File name	= ASMActionAnnouncement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

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
//import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
//import com.oifm.utility.OIUtilities;


public class ASMActionAnnouncement extends OIBaseAction {
	
	//Constants
	private final String RETRIEVE = "RETRIEVE";
	private final String SAVE = "SAVE";
	
	//End of contants
	
	private static final Logger logger = Logger.getLogger(ASMActionAnnouncement.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String action = (actionName != null)? actionName:RETRIEVE;
		String forward = "";
        String redirect = "";
        String userID = "";
        ArrayList functions = null;
        boolean isASMRep = false;
        ASMBAAnnouncement objBA = new ASMBAAnnouncement();
        ASMFormAnnouncement objForm = (ASMFormAnnouncement) form;
        ASMBOAnnouncement objBO = null;
        OIResponseObject responseObject = null;
        HttpSession session = null;
        String strRedirect = "";
        try{
        	session = request.getSession();
        	functions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            userID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isASMRep = functions.contains(OIFunctionConstants.ASM_REPS);
            
             if(isASMRep) {
            	objBO = new ASMBOAnnouncement();
            	
            	if (action.equals(RETRIEVE)) {
            		responseObject = objBO.getAnnouncement();
            		objBA = (ASMBAAnnouncement)responseObject.getResponseObject("Announcement");
            		PropertyUtils.copyProperties(objForm, objBA);
            		//objForm.setMessage(OIUtilities.addSingleQuoteJS(objForm.getMessage()));
            		request.setAttribute("pageName", "ASMAnnouncement");
            		//request.setAttribute("reset", OIUtilities.addSingleQuoteJS(objForm.getMessage()));
            		forward = "AdminPage";
            	} else if (action.equals(SAVE)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.setAnnouncement(objBA, userID);
            		if(responseObject.getResponseObject("error") != null) {
            			responseObject = objBO.getAnnouncement();
                		objBA = (ASMBAAnnouncement)responseObject.getResponseObject("Announcement");
                		PropertyUtils.copyProperties(objForm, objBA);
                		request.setAttribute("pageName", "ASMAnnouncement");
                		//request.setAttribute("reset", OIUtilities.addSingleQuoteJS(objForm.getMessage()));
                		request.setAttribute("status", "error");
                		forward = "AdminPage";
            		} else {
            			request.setAttribute("pageName", "ASMAnnouncement");
            			//request.setAttribute("reset", OIUtilities.addSingleQuoteJS(objForm.getMessage()));
                		request.setAttribute("status", "ASM.Announce.Success");
                		forward = "AdminPage";
            		}
            	}
           } else {
                strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
            }
            
        } catch(Exception e) {
        	logger.error("Action : TRY - CATCH : " + e);
        	redirect = ASMGlobals.ERR_REDIRECT + "?error=OIDEFAULT";
        } finally {
            if(!forward.equals("") && responseObject.getResponseObject("error") != null && !responseObject.getResponseObject("error").equals("null") ) 
                request.setAttribute("error", responseObject.getResponseObject("error"));
        }
        
        if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
            return (mapping.findForward(forward));
	}

}
