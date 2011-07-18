/*
 * File name	= OIActionGroups.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 11, 2005
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


public class OIActionGroups extends OIBaseAction {
	
	private static final Logger logger = Logger.getLogger(OIActionGroups.class);

	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName) {
		String strAction = (actionName != null)? actionName:OIGroupsConstants.LIST_GROUPS;
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        ArrayList alFunctions = null;
        boolean isGroupsMaintain = false;
        OIBAGroups objBAGroups = new OIBAGroups();
        OIFormGroups objFormGroups = (OIFormGroups) form;
        OIBOGroups objBOGroups = null;
        OIResponseObject objResponseObject = null;
        
        try{
            alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isGroupsMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_GROUPS);
        	
            if(isGroupsMaintain) {
                objBOGroups = new OIBOGroups();
                if(strAction.equals(OIGroupsConstants.LIST_GROUPS)) {
                    PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                    objResponseObject = objBOGroups.getGroupsList();
                    request.setAttribute("GroupsList", objResponseObject.getResponseObject("GroupsList"));
                   
                    OIResponseObject objFixedResponseObject = objBOGroups.getFixedGroupsList();
                    //System.out.println("OIActionGroups: doIt - objFixedResponseObject : "+(java.util.List)objFixedResponseObject.getResponseObject("FixedGroupsList"));
                    request.setAttribute("FixedGroupsList", (java.util.List)objFixedResponseObject.getResponseObject("FixedGroupsList"));
                    //System.out.println("OIActionGroups: doIt - FixedGroupsList : "+request.getAttribute("FixedGroupsList"));
                    request.setAttribute("pageName", "GroupsList");
                    strForward = OIGroupsConstants.LIST_PAGE;
                    
                } else if(strAction.equals(OIGroupsConstants.EDIT_GROUP)) {
                	//System.out.println("OIActionGroups-edit group");
                	Boolean isOwner = new Boolean(false);
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.getGroupDetails(objBAGroups);
                	objBAGroups = (OIBAGroups)objResponseObject.getResponseObject("objBAGroups");
                	PropertyUtils.copyProperties(objFormGroups, objBAGroups);
                	objResponseObject = objBOGroups.isOwner(objBAGroups, strUserId);
                	isOwner = (Boolean)objResponseObject.getResponseObject("isOwner");
                	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                	request.setAttribute("isAssigned", objResponseObject.getResponseObject("isAssigned"));
                	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
                	if (objResponseObject.containsKey("MembersList"))
                		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                	request.setAttribute("pageName", "GroupsEdit");
                	strForward = OIGroupsConstants.EDIT_PAGE;
                	
                } else if(strAction.equals(OIGroupsConstants.SORT)) {
                	Boolean isOwner = new Boolean(false);
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.isOwner(objBAGroups, strUserId);
                	isOwner = (Boolean)objResponseObject.getResponseObject("isOwner");
                	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                	request.setAttribute("isAssigned", objResponseObject.getResponseObject("isAssigned"));
                	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
                	if (objResponseObject.containsKey("MembersList"))
                		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                	request.setAttribute("pageName", "GroupsEdit");
                	strForward = OIGroupsConstants.EDIT_PAGE;
                	
                } else if (strAction.equals(OIGroupsConstants.DO_CREATE)) {
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.createGroup(objBAGroups, strUserId);
                	if(objResponseObject.getResponseObject("error") != null) {
                    	Boolean isOwner = new Boolean(false);
                    	objResponseObject = objBOGroups.isOwner(objBAGroups, strUserId);
                    	isOwner = (Boolean)objResponseObject.getResponseObject("isOwner");
                    	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                    	request.setAttribute("isAssigned", objResponseObject.getResponseObject("isAssigned"));
                    	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
                    	if (objResponseObject.containsKey("MembersList"))
                    		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                    	request.setAttribute("pageName", "GroupsEdit");
                        strForward = OIGroupsConstants.EDIT_PAGE;
                    } else {
                    	if (objFormGroups.getNextAction().equalsIgnoreCase(OIGroupsConstants.EDIT_GROUP)) {
                    		objResponseObject = objBOGroups.getGroupID(objFormGroups.getName());
                    		objBAGroups.setGroupId((Integer)objResponseObject.getResponseObject("groupID"));
                    		strRedirect = "/Groups.do?hiddenAction=" + objFormGroups.getNextAction() + "&groupId=" + objBAGroups.getGroupId() + "&nextAction=" + objFormGroups.getHiddenAction();
                    	} else
                    		strRedirect = "/Groups.do?hiddenAction=" + objFormGroups.getNextAction();
                    }
                	
                } else if (strAction.equals(OIGroupsConstants.DO_EDIT)) {
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.editGroup(objBAGroups, strUserId);
                	if(objResponseObject.getResponseObject("error") != null) {
                    	Boolean isOwner = new Boolean(false);
                    	objResponseObject = objBOGroups.isOwner(objBAGroups, strUserId);
                    	isOwner = (Boolean)objResponseObject.getResponseObject("isOwner");
                    	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                    	request.setAttribute("isAssigned", objResponseObject.getResponseObject("isAssigned"));
                    	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
                    	if (objResponseObject.containsKey("MembersList"))
                    		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                    	request.setAttribute("pageName", "GroupsEdit");
                        strForward = OIGroupsConstants.EDIT_PAGE;
                    } else {
                        strRedirect = "/Groups.do?hiddenAction=" + objFormGroups.getNextAction();
                    }
                	
                } else if (strAction.equals(OIGroupsConstants.DO_DELETE)) {
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.deleteGroup(objBAGroups, strUserId);
                	if(objResponseObject.getResponseObject("error") != null) {
                    	Boolean isOwner = new Boolean(false);
                    	objResponseObject = objBOGroups.isOwner(objBAGroups, strUserId);
                    	isOwner = (Boolean)objResponseObject.getResponseObject("isOwner");
                    	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                    	request.setAttribute("isAssigned", objResponseObject.getResponseObject("isAssigned"));
                    	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
                    	if (objResponseObject.containsKey("MembersList"))
                    		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                    	request.setAttribute("pageName", "GroupsEdit");
                        strForward = OIGroupsConstants.EDIT_PAGE;
                    } else {
                        strRedirect = "/Groups.do?hiddenAction=" + objFormGroups.getNextAction();
                    }
                	
                } else if (strAction.equals(OIGroupsConstants.DO_REMOVE_USER)) {
                	PropertyUtils.copyProperties(objBAGroups, objFormGroups);
                	objResponseObject = objBOGroups.removeMember(objBAGroups, objFormGroups.getToBeRemoved());
                	if(objResponseObject.getResponseObject("error") != null) {
                    	Boolean isOwner = new Boolean(false);
                    	objResponseObject = objBOGroups.isOwner(objBAGroups, strUserId);
                    	isOwner = (Boolean)objResponseObject.getResponseObject("isOwner");
                    	objResponseObject = objBOGroups.getGroupMembers(objBAGroups);
                    	request.setAttribute("isAssigned", objResponseObject.getResponseObject("isAssigned"));
                    	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
                    	if (objResponseObject.containsKey("MembersList"))
                    		request.setAttribute("MembersList", objResponseObject.getResponseObject("MembersList"));
                    	request.setAttribute("pageName", "GroupsEdit");
                        strForward = OIGroupsConstants.EDIT_PAGE;
                    } else {
                    	request.setAttribute("Remove", objResponseObject.getResponseObject("Remove"));
                        strRedirect = "/Groups.do?hiddenAction=" + objFormGroups.getNextAction();
                    }
                	
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
