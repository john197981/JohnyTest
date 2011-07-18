/*
 * File name	= OIActionSurveyPaperEmailEdit.java
 * Package		= com.oifm.common
 * Created on 	= Oct 18, 2006
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
//import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
//import com.oifm.utility.OIUtilities;


public class OIActionSurveyPaperEmailEdit extends OIBaseAction {
	
	//Constants
	private final String RETRIEVE = "RETRIEVE";
	private final String SAVE = "SAVE";
	
	//End of contants
	
	private static final Logger logger = Logger.getLogger(OIActionSurveyPaperEmailEdit.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String action = (actionName != null)? actionName:RETRIEVE;
		String forward = "";
        String redirect = "";
        String userID = "";
        //ArrayList functions = null;
        OIBASurveyPaperEmailEdit objBA = new OIBASurveyPaperEmailEdit();
        OIFormSurveyPaperEmailEdit objForm = (OIFormSurveyPaperEmailEdit) form;
        OIBOSurveyPaperEmailEdit objBO = null;
        OIResponseObject responseObject = null;
		String module = request.getParameter("module");
		String id = request.getParameter("id");
        
        try{
           // functions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            userID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            //isASMRep = functions.contains(OIFunctionConstants....);
            System.out.println("debug st 1");
            // if(isASMRep) {
            	objBO = new OIBOSurveyPaperEmailEdit();
            	
            	if (action.equals(RETRIEVE)) {
					 System.out.println("debug st 2");
            		responseObject = objBO.getEmail(module,id);
					 System.out.println("debug st 3");
            		objBA = (OIBASurveyPaperEmailEdit)responseObject.getResponseObject("EmailText");
            		PropertyUtils.copyProperties(objForm, objBA);
            		//objForm.setMessage(OIUtilities.addSingleQuoteJS(objForm.getMessage()));
            		request.setAttribute("pageName", "EmailEdit");
            		//request.setAttribute("reset", OIUtilities.addSingleQuoteJS(objForm.getMessage()));
            		forward = "AdminPage";
            	} else if (action.equals(SAVE)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.setEmail(objBA, module,id);
            		if(responseObject.getResponseObject("error") != null) {
            			responseObject = objBO.getEmail(module,id);
                		objBA = (OIBASurveyPaperEmailEdit)responseObject.getResponseObject("EmailText");
                		PropertyUtils.copyProperties(objForm, objBA);
                		request.setAttribute("pageName", "EmailEdit");
                		//request.setAttribute("reset", OIUtilities.addSingleQuoteJS(objForm.getMessage()));
                		request.setAttribute("status", "error");
                		forward = "AdminPage";
            		} else {
            			request.setAttribute("pageName", "EmailEdit");
            			forward = "AdminPage";
            		}
            	}
				request.setAttribute("callingModule",module);
            /* } else {
                strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
            }*/
            
        } catch(Exception e) {
        	logger.error("Action : TRY - CATCH : " + e);
        	//redirect = ASMGlobals.ERR_REDIRECT + "?error=OIDEFAULT";
        } finally {
            if(!forward.equals("") && responseObject.getResponseObject("error") != null && !responseObject.getResponseObject("error").equals("null") ) 
                request.setAttribute("error", responseObject.getResponseObject("error"));
        }
        
        if(!redirect.equals("")) 
            return new ActionForward(redirect);
        else
            return (mapping.findForward(forward));
	}

}
