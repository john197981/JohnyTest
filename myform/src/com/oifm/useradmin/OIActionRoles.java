/*
 * Roles module Action class
 * 
 * File name	= OIActionRoles.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 5, 2005
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


public class OIActionRoles extends OIBaseAction {
    
    private static Logger logger = Logger.getLogger(OIActionRoles.class);
    
    public ActionForward doIt(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response,
            String actionName) {
        String strAction = (actionName != null)? actionName:OIRolesConstants.LIST_ROLES;
        String strForward = "";
        String strRedirect = "";
        String strUserId = "";
        ArrayList alFunctions = null;
        boolean isRolesMaintain = false;
        OIBARoles objBARoles = new OIBARoles();
        OIFormRoles objFormRoles = (OIFormRoles) form;
        OIBORoles objBORoles = null;
        OIResponseObject objResponseObject = null;
        
        try{
            alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
            strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
            isRolesMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_ROLES);
            
            if(isRolesMaintain) {
                objBORoles = new OIBORoles();
                if(strAction.equals(OIRolesConstants.LIST_ROLES)) {
                    PropertyUtils.copyProperties(objBARoles, objFormRoles);
                    objResponseObject = objBORoles.getRolesList();
                    request.setAttribute("RolesList", objResponseObject.getResponseObject("RolesList"));
                    request.setAttribute("pageName", "RolesList");
                    strForward = OIRolesConstants.LIST_ROLES_PAGE;
                    
                } else if (strAction.equals(OIRolesConstants.EDIT_ROLE)) {
                    Boolean canDelete = new Boolean(false);
                    PropertyUtils.copyProperties(objBARoles, objFormRoles);
                    objResponseObject = objBORoles.getRoleDetails(objBARoles);
                    objBARoles = (OIBARoles)objResponseObject.getResponseObject("objBARoles");
                    PropertyUtils.copyProperties(objFormRoles, objBARoles);
                    objResponseObject = objBORoles.canDelete(objBARoles);
                    canDelete = (Boolean)objResponseObject.getResponseObject("canDelete");
                    objResponseObject = objBORoles.getFunctionsList(alFunctions);
                    request.setAttribute("pageName", "RolesEdit");
                    request.setAttribute("canDelete", canDelete);
                    request.setAttribute("ADMIN_DF", objResponseObject.getResponseObject("ADMIN_DF"));
                    request.setAttribute("ADMIN_CP", objResponseObject.getResponseObject("ADMIN_CP"));
                    request.setAttribute("ADMIN_SU", objResponseObject.getResponseObject("ADMIN_SU"));
                    request.setAttribute("ADMIN_OTHERS", objResponseObject.getResponseObject("ADMIN_OTHERS"));
                    request.setAttribute("WEBSITE_DF", objResponseObject.getResponseObject("WEBSITE_DF"));
                    request.setAttribute("ADMIN_ASM", objResponseObject.getResponseObject("ADMIN_ASM"));
                    request.setAttribute("ADMIN_BLOG", objResponseObject.getResponseObject("ADMIN_BLOG"));
                    strForward = OIRolesConstants.EDIT_ROLE_PAGE;
                    
                } else if (strAction.equals(OIRolesConstants.DO_CREATE)) {
                    PropertyUtils.copyProperties(objBARoles, objFormRoles);
                    objResponseObject = objBORoles.createRole(objBARoles, strUserId);
                    if(objResponseObject.getResponseObject("error") != null) {
                        Boolean canDelete = new Boolean(false);
                        objResponseObject = objBORoles.canDelete(objBARoles);
                        canDelete = (Boolean)objResponseObject.getResponseObject("canDelete");
                        objResponseObject = objBORoles.getFunctionsList(alFunctions);
                        request.setAttribute("pageName", "RolesEdit");
                        request.setAttribute("canDelete", canDelete);
                        request.setAttribute("ADMIN_DF", objResponseObject.getResponseObject("ADMIN_DF"));
                        request.setAttribute("ADMIN_CP", objResponseObject.getResponseObject("ADMIN_CP"));
                        request.setAttribute("ADMIN_SU", objResponseObject.getResponseObject("ADMIN_SU"));
                        request.setAttribute("ADMIN_OTHERS", objResponseObject.getResponseObject("ADMIN_OTHERS"));
                        request.setAttribute("WEBSITE_DF", objResponseObject.getResponseObject("WEBSITE_DF"));
                        request.setAttribute("ADMIN_ASM", objResponseObject.getResponseObject("ADMIN_ASM"));
                        request.setAttribute("ADMIN_BLOG", objResponseObject.getResponseObject("ADMIN_BLOG"));
                        strForward = OIRolesConstants.EDIT_ROLE_PAGE;
                    } else
                        strRedirect = "/Roles.do?hiddenAction=" + OIRolesConstants.LIST_ROLES;
                    
                } else if (strAction.equals(OIRolesConstants.DO_EDIT)) {
                    PropertyUtils.copyProperties(objBARoles, objFormRoles);
                    objResponseObject = objBORoles.saveRole(objBARoles);
                    if(objResponseObject.getResponseObject("error") != null) {
                        Boolean canDelete = new Boolean(false);
                        objResponseObject = objBORoles.canDelete(objBARoles);
                        canDelete = (Boolean)objResponseObject.getResponseObject("canDelete");
                        objResponseObject = objBORoles.getFunctionsList(alFunctions);
                        request.setAttribute("pageName", "RolesEdit");
                        request.setAttribute("canDelete", canDelete);
                        request.setAttribute("ADMIN_DF", objResponseObject.getResponseObject("ADMIN_DF"));
                        request.setAttribute("ADMIN_CP", objResponseObject.getResponseObject("ADMIN_CP"));
                        request.setAttribute("ADMIN_SU", objResponseObject.getResponseObject("ADMIN_SU"));
                        request.setAttribute("ADMIN_OTHERS", objResponseObject.getResponseObject("ADMIN_OTHERS"));
                        request.setAttribute("WEBSITE_DF", objResponseObject.getResponseObject("WEBSITE_DF"));
                        request.setAttribute("ADMIN_ASM", objResponseObject.getResponseObject("ADMIN_ASM"));
                        request.setAttribute("ADMIN_BLOG", objResponseObject.getResponseObject("ADMIN_BLOG"));
                        strForward = OIRolesConstants.EDIT_ROLE_PAGE;
                    } else
                        strRedirect = "/Roles.do?hiddenAction=" + OIRolesConstants.LIST_ROLES;
                    
                } else if (strAction.equals(OIRolesConstants.DO_DELETE)) {
                    PropertyUtils.copyProperties(objBARoles, objFormRoles);
                    objResponseObject = objBORoles.deleteRole(objBARoles);
                    if(objResponseObject.getResponseObject("error") != null) {
                        Boolean canDelete = new Boolean(false);
                        objResponseObject = objBORoles.canDelete(objBARoles);
                        canDelete = (Boolean)objResponseObject.getResponseObject("canDelete");
                        objResponseObject = objBORoles.getFunctionsList(alFunctions);
                        request.setAttribute("pageName", "RolesEdit");
                        request.setAttribute("canDelete", canDelete);
                        request.setAttribute("ADMIN_DF", objResponseObject.getResponseObject("ADMIN_DF"));
                        request.setAttribute("ADMIN_CP", objResponseObject.getResponseObject("ADMIN_CP"));
                        request.setAttribute("ADMIN_SU", objResponseObject.getResponseObject("ADMIN_SU"));
                        request.setAttribute("ADMIN_OTHERS", objResponseObject.getResponseObject("ADMIN_OTHERS"));
                        request.setAttribute("WEBSITE_DF", objResponseObject.getResponseObject("WEBSITE_DF"));
                        request.setAttribute("ADMIN_ASM", objResponseObject.getResponseObject("ADMIN_ASM"));
                        request.setAttribute("ADMIN_BLOG", objResponseObject.getResponseObject("ADMIN_BLOG"));
                        strForward = OIRolesConstants.EDIT_ROLE_PAGE;
                    } else
                        strRedirect = "/Roles.do?hiddenAction=" + OIRolesConstants.LIST_ROLES;
                    
                }
                
            } else {
                strRedirect = OIRolesConstants.ERROR_DO+"?error=NoAccess";
            }
        } catch(Exception e) {
            e.printStackTrace();
            strRedirect = OIRolesConstants.ERROR_DO+"?error=OIDEFAULT";
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
