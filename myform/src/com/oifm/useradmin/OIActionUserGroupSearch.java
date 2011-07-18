package com.oifm.useradmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
public class OIActionUserGroupSearch extends OIBaseAction 
{
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        return null;
	}

    /**
     * This method calls the readAllGroups of OIBOUserGroupSearch
     * 
     * This page expects ID & Module from the called page 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }
    
    /**
     * This method calls the addGroupUsers of OIBOUserGroupSearch
     * 
     * It passes ID, Module name, List of groupIds 
     */
    public ActionForward addGroupUsers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        return null;
    }
}
