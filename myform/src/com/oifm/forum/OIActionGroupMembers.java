/*
 * File name	= OIActionGroupMembers.java
 * Package		= com.oifm.forum
 * Created on 	= Oct 18, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.forum;

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
import com.oifm.useradmin.OIBAGroups;
import com.oifm.useradmin.OIBOGroups;
import com.oifm.useradmin.OIFormGroups;
import com.oifm.useradmin.OIGroupsConstants;


public class OIActionGroupMembers extends OIBaseAction {
	
	private static final Logger logger = Logger.getLogger(OIActionGroupMembers.class);

	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName != null)? actionName:OIGroupsConstants.LIST_MEMBERS;
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        ArrayList alFunctions = null;
        boolean canCreatePrivate = false;
        OIBAGroups objBAGroups = new OIBAGroups();
        OIFormGroups objFormGroups = (OIFormGroups) form;
        OIBOGroups objBOGroups = null;
        OIResponseObject objResponseObject = null;
        
        try{
            alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            canCreatePrivate = alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD);
            //logger.info("action");
            if(canCreatePrivate) {
                objBOGroups = new OIBOGroups();
                //logger.info("inside action");
                if(strAction.equals(OIGroupsConstants.LIST_MEMBERS)) {
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                	if (objResponseObject.containsKey("MembersList"))
                		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                	request.setAttribute("pageName", "GroupMembers");
                	strForward = OIGroupsConstants.MEMBER_LIST_PAGE;
                	
                }
            } else {
                strRedirect = OIGroupsConstants.ERROR_DO+"?error=NoAccess";
            }
        } catch(Exception e) {
        	logger.error("TRY - CATCH : " + e);
            strRedirect = OIGroupsConstants.ERROR_DO+"?error=OIDEFAULT";
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
