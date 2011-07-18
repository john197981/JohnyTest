package com.oifm.forum.admin;

import java.util.ArrayList;

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

public class OIActionForumViewModifyCategory extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionForumViewModifyCategory.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String DELETE_ACTION = "delete";
    private static final String UPDATE_ACTION = "update";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(UPDATE_ACTION))
	        {
	            return update(mapping, form, request, response);
	        }
	        if (actionName.equals(DELETE_ACTION))
	        {
	            return delete(mapping, form, request, response);
	        }
        }
        String error=null;
        try
        {
            error = OIDBRegistry.getValues("OI.CONS.GEN"); //"Action not Available";
        }
        catch(Exception e){}
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String categoryId = request.getParameter(OIForumConstants.K_categoryId);
        OIResponseObject aOIResponseObject = new OIBOForumCategory().readCategory(Integer.parseInt(categoryId.trim()));
        OIFormForumCategory aOIFormForumCategory = new OIFormForumCategory();
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAForumCategory aOIBAForumCategory = (OIBAForumCategory) aOIResponseObject.getResponseObject(OIForumConstants.K_aOIBAForumCategory);
        aOIFormForumCategory.setCategoryId(aOIBAForumCategory.getCategoryID() + "");
        aOIFormForumCategory.setCategoryName(aOIBAForumCategory.getName());
        
        request.setAttribute(OIForumConstants.FORUM_CATEGORY_FORM,aOIFormForumCategory);

        request.setAttribute(OILoginConstants.PAGENAME,"ForumCategory");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormForumCategory aOIFormForumCategory = (OIFormForumCategory) form;
        OIBAForumCategory aOIBAForumCategory = new OIBAForumCategory();
        aOIBAForumCategory.setName(aOIFormForumCategory.getCategoryName());
        aOIBAForumCategory.setCategoryID(Integer.parseInt(aOIFormForumCategory.getCategoryId()));

        OIResponseObject oIResponseObject = new OIBOForumCategory().saveCategory(aOIBAForumCategory);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIForumConstants.FORUM_CATEGORY_FORM,form);
            //return new ActionForward("/adminForumCreateCategoryAction.do?hiddenAction=populate");
            request.setAttribute(OILoginConstants.PAGENAME,"ForumCategory");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        
        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }

    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormForumCategory aOIFormForumCategory = (OIFormForumCategory) form;
        OIBAForumCategory aOIBAForumCategory = new OIBAForumCategory();
        aOIBAForumCategory.setName(aOIFormForumCategory.getCategoryName());
        aOIBAForumCategory.setCategoryID(Integer.parseInt(aOIFormForumCategory.getCategoryId()));
        OIResponseObject oIResponseObject = new OIBOForumCategory().deleteCategory(aOIBAForumCategory);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            logger.info(error);
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,form);
            //return new ActionForward("/adminForumCreateCategoryAction.do?hiddenAction=populate");
            request.setAttribute(OILoginConstants.PAGENAME,"ForumCategory");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }
        if (messageList!=null)
        {
            request.setAttribute(OILoginConstants.K_MESSAGE,messageList);
            request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,form);
            //return new ActionForward("/adminForumCreateCategoryAction.do?hiddenAction=populate");
            request.setAttribute(OILoginConstants.PAGENAME,"ForumCategory");
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }

        return new ActionForward("/adminForumListing.do?hiddenAction=populate");
    }
}
