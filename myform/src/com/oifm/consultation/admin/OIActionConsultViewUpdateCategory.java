package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			01/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

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
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionConsultViewUpdateCategory extends OIBaseAction 
{
    Logger logger=Logger.getLogger(OIActionConsultCreateCategory.class.getName());
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
    
    /**
     * This method calls the readCategory of OIBOConsultCategory, which passes category ID as parameter 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String categoryId = request.getParameter(OIConsultConstant.K_categoryId);
       //System.out.println("OIActionConsultViewUpdateCategory-populate" + categoryId);
        OIResponseObject aOIResponseObject = new OIBOConsultCategory().readCategory(Integer.parseInt(categoryId.trim()));
       // System.out.println("OIActionConsultViewUpdateCategory-populate1" + categoryId);
        OIFormConsultCategory aOIFormConsultCategory = new OIFormConsultCategory();
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultCategory);
        aOIFormConsultCategory.setCategoryId(aOIBAConsultCategory.getCategoryID() + "");
        aOIFormConsultCategory.setCategoryName(aOIBAConsultCategory.getName());
        
        request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,aOIFormConsultCategory);
        request.setAttribute(OILoginConstants.PAGENAME,"ConsultCategory");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    /**
     * This method will call the OIBOConsultCategory.saveCategory() & pass OIBAConsultCategory as the input parameter
     * 
     * If save is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the Consultation Paper Listing page. 
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultCategory aOIFormConsultCategory = (OIFormConsultCategory) form;
        OIBAConsultCategory aOIBAConsultCategory = new OIBAConsultCategory();
        aOIBAConsultCategory.setName(aOIFormConsultCategory.getCategoryName());
        aOIBAConsultCategory.setCategoryID(Integer.parseInt(aOIFormConsultCategory.getCategoryId()));

        OIResponseObject oIResponseObject = new OIBOConsultCategory().saveCategory(aOIBAConsultCategory);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,form);
            return new ActionForward("/consultCreateCategoryAction.do?hiddenAction=populate");
        }
        
        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
    
    /**
     * This method will call the OIBOConsultCategory.deleteCategory() & pass OIBAConsultCategory as the input parameter
     * 
     * If delete is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the Consultation Paper Listing page. 
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultCategory aOIFormConsultCategory = (OIFormConsultCategory) form;
        OIBAConsultCategory aOIBAConsultCategory = new OIBAConsultCategory();
        aOIBAConsultCategory.setName(aOIFormConsultCategory.getCategoryName());
        aOIBAConsultCategory.setCategoryID(Integer.parseInt(aOIFormConsultCategory.getCategoryId()));
        OIResponseObject oIResponseObject = new OIBOConsultCategory().deleteCategory(aOIBAConsultCategory);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,form);
            return new ActionForward("/consultCreateCategoryAction.do?hiddenAction=populate");
        }
        if (messageList!=null)
        {
            request.setAttribute(OILoginConstants.K_MESSAGE,messageList);
            request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,form);
            return new ActionForward("/consultCreateCategoryAction.do?hiddenAction=populate");            
        }

        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
}
