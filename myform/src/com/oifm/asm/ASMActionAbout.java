/*
 * File name	= ASMActionAbout.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 16, 2005
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
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;


public class ASMActionAbout extends OIBaseAction {
	
	// Constants
	
	private final String WEB_DISPLAY = "WEB";
	private final String ADMIN_LIST = "ADMIN";
	private final String ADMIN_EDIT = "EDIT";
	private final String ADMIN_DO_CREATE = "DO_CREATE";
	private final String ADMIN_DO_EDIT = "DO_EDIT";
	private final String ADMIN_DO_DELETE = "DO_DELETE";
	
	// End of constants

	private static final Logger logger = Logger.getLogger(ASMActionAbout.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String action = (actionName != null)? actionName:WEB_DISPLAY;
		String forward = "";
        String redirect = "";
        String userID = "";
        ArrayList functions = null;
        boolean isASMRep = false;
        ASMBAAbout objBA = new ASMBAAbout();
        ASMFormAbout objForm = (ASMFormAbout) form;
        ASMBOAbout objBO = null;
        OIResponseObject responseObject = null;
        HttpSession session = null;
        String strRedirect = "";
        try{
        	session = request.getSession();
        	functions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            userID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isASMRep = functions.contains(OIFunctionConstants.ASM_REPS);
            
         if(isASMRep) {
            	objBO = new ASMBOAbout();
            	logger.info("Inside action"+action);
            	if (action.equals(WEB_DISPLAY)) {
            		responseObject = objBO.getList();
            		request.setAttribute("list", responseObject.getResponseObject("List"));
            		responseObject = objBO.getModuleDescription();
            		request.setAttribute("ModuleDesc", responseObject.getResponseObject("ModuleDesc"));
            		
            		request.setAttribute("pageName", "ASMAbout");
            		forward = "WebDisplay";
            		
            		// For right menu
            		ASMBACommon objCommonBA = new ASMBACommon();
            		ASMBOCommon objCommonBO = new ASMBOCommon();
            		objCommonBO.process(objCommonBA,null);
            		request.setAttribute("TotRecSizRecLtr",""+objCommonBA.getTotRecLtr());
            		request.setAttribute("recent_letter",objCommonBA.getObjBV());
            		request.setAttribute("editors_note_id",objCommonBA.getHidEditorsNoteID());
            		request.setAttribute("editors_note",objCommonBA.getHidEditorsNote());
            		request.setAttribute("editors_note_posted_on",objCommonBA.getHidEditorsNotePostedOn());
            		
            	} else if (action.equals(ADMIN_LIST)) {
            		responseObject = objBO.getList();
            		request.setAttribute("list", responseObject.getResponseObject("List"));
            		request.setAttribute("pageName", "ASMAboutList");
            		forward = "AdminPage";
            		
            	} else if (action.equals(ADMIN_EDIT)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.getDetail(objBA);
            		objBA = (ASMBAAbout) responseObject.getResponseObject("Detail");
            		PropertyUtils.copyProperties(objForm, objBA);
            		request.setAttribute("pageName", "ASMAboutEdit");
            		forward = "AdminPage";
            		
            	} else if (action.equals(ADMIN_DO_CREATE)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.create(objBA, userID);
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMAboutEdit");
                		forward = "AdminPage";
            		} else {
            			redirect = "/ASMAbout.do?hiddenAction=" + ADMIN_LIST;
            		}
            		
            	} else if (action.equals(ADMIN_DO_EDIT)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.modify(objBA, userID);
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMAboutEdit");
                		forward = "AdminPage";
                		logger.info("Inside ADMIN_DO_EDIT IF");
            		} else {
            			logger.info("Inside ADMIN_DO_EDIT else");
            			redirect = "/ASMAbout.do?hiddenAction=" + ADMIN_LIST;
            		}
            		
            	} else if (action.equals(ADMIN_DO_DELETE)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.delete(objBA);
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMAboutEdit");
                		forward = "AdminPage";
            		} else {
            			redirect = "/ASMAbout.do?hiddenAction=" + ADMIN_LIST;
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
        
       //if(!strRedirect.equals(""))
        if(!redirect.equals(""))
         {
        	logger.info("Inside last IF");
            return new ActionForward(redirect);
        } 
        else
        {
        	logger.info("Inside last else"+forward);
            return (mapping.findForward(forward));
        }
	}

}
