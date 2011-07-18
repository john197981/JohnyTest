package com.oifm.forum.admin;

import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.forum.OIForumConstants;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionForumCreateCategory extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionForumCreateCategory.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SAVE_ACTION = "save";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(SAVE_ACTION))
	        {
	            return save(mapping, form, request, response);
	        }
        }
        String error=null;
        try
        {
            error = OIDBRegistry.getValues("OI.CONS.GEN"); //"Action not Available";
        }
        catch(Exception e)
        {}

        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }
    
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        request.setAttribute(OILoginConstants.PAGENAME,"ForumCategory");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormForumCategory aOIFormForumCategory = (OIFormForumCategory) form;
        OIBAForumCategory aOIBAForumCategory = new OIBAForumCategory();
        aOIBAForumCategory.setName(aOIFormForumCategory.getCategoryName());
        aOIBAForumCategory.setCreatedBy((String) getSessionAttribute(request,OILoginConstants.USER_ID));
        
        aOIBAForumCategory.setCreatedOn(Calendar.getInstance().getTime());
        OIResponseObject oIResponseObject = new OIBOForumCategory().saveCategory(aOIBAForumCategory);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            //aOIFormConsultCategory.setCategoryId(null);
            request.setAttribute(OILoginConstants.K_ERROR,error);
            aOIFormForumCategory.setCategoryId(null);
            request.setAttribute(OIForumConstants.FORUM_CATEGORY_FORM,aOIFormForumCategory);
            return new ActionForward("/adminForumCreateCategoryAction.do?hiddenAction=populate");
        }
        
        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }

}
