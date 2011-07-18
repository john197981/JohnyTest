package com.oifm.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.consultation.OIConsultConstant;

public class OIActionAdmin extends OIBaseAction
{
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);        
    }
}
