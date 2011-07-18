/*
 * File name	= ASMActionManagement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 22, 2005
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


public class ASMActionManagement extends OIBaseAction {

	//Constants
	
	private final String WEB_DISPLAY = "WEB";
	private final String ADMIN_LIST = "ADMIN";
	private final String ADMIN_EDIT = "EDIT";
	private final String ADMIN_DO_CREATE = "DO_CREATE";
	private final String ADMIN_DO_EDIT = "DO_EDIT";
	private final String ADMIN_DO_DELETE = "DO_DELETE";
	private final String ADMIN_REMOVE_PHOTO = "REM_PHOTO";
	private final String DISPLAY_PHOTO = "PHOTO";
	
	// End of constants

	private static final Logger logger = Logger.getLogger(ASMActionManagement.class);
	
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String action = (actionName != null && actionName.length() != 0)? actionName:WEB_DISPLAY;
		String forward = "";
        String redirect = "";
        String userID = "";
        String strRedirect = "";
        ArrayList functions = null;
        boolean isASMRep = false;
        ASMBAManagement objBA = new ASMBAManagement();
        ASMFormManagement objForm = (ASMFormManagement) form;
        ASMBOManagement objBO = null;
        OIResponseObject responseObject = null;
        HttpSession session = null;
        try{
        	session = request.getSession();
        	functions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            userID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isASMRep = functions.contains(OIFunctionConstants.ASM_REPS);
            
        // if(isASMRep) {
            	objBO = new ASMBOManagement();
            	
            	
            	if (action.equals(WEB_DISPLAY)) {
            		responseObject = objBO.getWelcomeMessage();
            		request.setAttribute("welcomeMessage", responseObject.getResponseObject("WelcomeMessage"));
            		responseObject = objBO.getList();
            		request.setAttribute("list", responseObject.getResponseObject("List"));
            		request.setAttribute("total", responseObject.getResponseObject("Total"));
            		request.setAttribute("pageName", "ASMManagement");
            		forward = "WebDisplay";
            		
            		//For right menu
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
            		request.setAttribute("total", responseObject.getResponseObject("Total"));
            		request.setAttribute("pageName", "ASMManagementList");
            		forward = "AdminPage";
            		
            	} else if (action.equals(ADMIN_EDIT)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.getDetail(objBA);
            		objBA = (ASMBAManagement) responseObject.getResponseObject("Detail");
            		PropertyUtils.copyProperties(objForm, objBA);
            		request.setAttribute("pageName", "ASMManagementEdit");
            		forward = "AdminPage";
            		
            	} else if (action.equals(ADMIN_DO_CREATE)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.create(objBA, userID, objForm.getFileName());
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMManagementEdit");
                		forward = "AdminPage";
            		} else {
            			redirect = "/ASMManagement.do?hiddenAction=" + ADMIN_LIST;
            		}
            		
            	} else if (action.equals(ADMIN_DO_EDIT)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.modify(objBA, userID, objForm.getFileName());
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMManagementEdit");
                		forward = "AdminPage";
            		} else {
            			
            			//redirect = "/ASMManagement.do?hiddenAction=" + ADMIN_LIST;
                		responseObject = objBO.getList();
                		request.setAttribute("list", responseObject.getResponseObject("List"));
                		request.setAttribute("total", responseObject.getResponseObject("Total"));
                		request.setAttribute("pageName", "ASMManagementList");
                		forward = "AdminPage";
            		}
            		
            	} else if (action.equals(ADMIN_DO_DELETE)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.delete(objBA);
            		if(responseObject.getResponseObject("error") != null) {
            			request.setAttribute("pageName", "ASMManagementEdit");
                		forward = "AdminPage";
            		} else {
            			redirect = "/ASMManagement.do?hiddenAction=" + ADMIN_LIST;
            		}
            		
            	} else if (action.equals(ADMIN_REMOVE_PHOTO)) {
            		PropertyUtils.copyProperties(objBA, objForm);
            		responseObject = objBO.removePhoto(objBA);
            		//if(responseObject.getResponseObject("error") != null) {
            			//request.setAttribute("pageName", "ASMManagementEdit");
                		//forward = "AdminPage";
            		//} else {
            			redirect = "/ASMManagement.do?hiddenAction=" + ADMIN_EDIT;
            		//}
            		
            	} else if (action.equals(DISPLAY_PHOTO)) {
            		responseObject = objBO.readPhoto(response, objForm.getPhotograph());
            	}
//            	 } else {
//                 	strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
//                }
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
