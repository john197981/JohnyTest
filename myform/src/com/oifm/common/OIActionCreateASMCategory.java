package com.oifm.common;

/**
 * @author Rakesh 
 * Desc : This Action Class handles creation of ASM Categories.
 * Date : 14 Jan 2008
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
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.admin.OIActionConsultCreateCategory;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionCreateASMCategory extends OIBaseAction{
	Logger logger=Logger.getLogger(OIActionCreateASMCategory.class.getName());
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
    	//System.out.println("This is OIActionCreateASMCategory populate method.");
        request.setAttribute(OILoginConstants.PAGENAME,"ASMCategory");
        return mapping.findForward(POPULATE_ACTION);
        
    }
    
    /**
	 * This method will call the OIBOASMCategory.saveCategory() & pass OIBAASMCategory as the input parameter If save is unsuccessful then the error
	 * has to be shown in the same page
	 */
	
	public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		OIFormASMCategory aOIFormASMCategory = (OIFormASMCategory) form;
		OIBAASMCategory aOIBAASMCategory = new OIBAASMCategory();
		aOIBAASMCategory.setName(aOIFormASMCategory.getName());
		//logger.info("Name =" + aOIFormASMCategory.getName());
		//System.out.println("Category Name :" + aOIFormASMCategory.getName());
		aOIBAASMCategory.setDescription(aOIFormASMCategory.getDescription());
		//logger.info("Desc =" + aOIFormASMCategory.getDescription());
		//System.out.println("Cat Desc :" + aOIFormASMCategory.getDescription());
		aOIBAASMCategory.setCreatedBy((String) getSessionAttribute(request, OILoginConstants.USER_ID));
		//logger.info(Calendar.getInstance().getTime().toString());
		//System.out.println("User Id :" + (String) getSessionAttribute(request, OILoginConstants.USER_ID));
		aOIBAASMCategory.setCreatedOn(Calendar.getInstance().getTime());
		OIResponseObject oIResponseObject = new OIBOASMCategory().saveCategory(aOIBAASMCategory);
		ArrayList categoryList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_CATEGORY);
		String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
		if (error != null)
		{
			
			request.setAttribute(OILoginConstants.K_ERROR, error);
			request.setAttribute(OIASMCategoryConstant.ASM_CATEGORY_FORM, aOIFormASMCategory);
			return new ActionForward("/asmCreateCategoryAction.do?hiddenAction=populate");
		}
		
		return new ActionForward("/asmCategoryAction.do?hiddenAction=populate");
	}
    
}
