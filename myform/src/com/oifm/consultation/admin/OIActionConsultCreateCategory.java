package com.oifm.consultation.admin;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			28/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

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
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionConsultCreateCategory extends OIBaseAction 
{
    Logger logger=Logger.getLogger(OIActionConsultCreateCategory.class.getName());
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
    
    /**
     * This method will populate the JSP 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
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
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultCategory aOIFormConsultCategory = (OIFormConsultCategory) form;
        OIBAConsultCategory aOIBAConsultCategory = new OIBAConsultCategory();
        aOIBAConsultCategory.setName(aOIFormConsultCategory.getCategoryName());
        aOIBAConsultCategory.setCreatedBy((String) getSessionAttribute(request,OILoginConstants.USER_ID));
        logger.info(Calendar.getInstance().getTime().toString());
        aOIBAConsultCategory.setCreatedOn(Calendar.getInstance().getTime());
        OIResponseObject oIResponseObject = new OIBOConsultCategory().saveCategory(aOIBAConsultCategory);
        ArrayList messageList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            //aOIFormConsultCategory.setCategoryId(null);
            request.setAttribute(OILoginConstants.K_ERROR,error);
            aOIFormConsultCategory.setCategoryId(null);
            request.setAttribute(OIConsultConstant.CONSULT_CATEGORY_FORM,aOIFormConsultCategory);
            return new ActionForward("/consultCreateCategoryAction.do?hiddenAction=populate");
        }
        
        return new ActionForward("/consultListingAction.do?hiddenAction=populate");
    }
}
