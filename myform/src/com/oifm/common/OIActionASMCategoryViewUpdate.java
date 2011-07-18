package com.oifm.common;
/**
 * author Rakesh
 * mail@ : Rakesh.Chagallu@cambridge-asia.com
 * Created on 18 Jan 2008
 * This Class provide edit, delete operations for ASM Categories.
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
public class OIActionASMCategoryViewUpdate extends OIBaseAction
{
	Logger logger=Logger.getLogger(OIActionASMCategoryViewUpdate.class.getName());
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
     * This method calls the readCategory of OIBOASMCategory, which passes category ID as parameter 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        int categoryID = Integer.parseInt(request.getParameter(OIASMCategoryConstant.K_categoryID));
        logger.info(categoryID + "");
        OIResponseObject aOIResponseObject = new OIBOASMCategory().readCategory(categoryID);
        OIFormASMCategory aOIFormASMCategory = new OIFormASMCategory();
        ArrayList categoryList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_CATEGORY);
        
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAASMCategory aOIBAASMCategory = (OIBAASMCategory) aOIResponseObject.getResponseObject(OIASMCategoryConstant.K_aOIBAASMCategory);
        aOIFormASMCategory.setCategoryID(aOIBAASMCategory.getCategoryID());
        logger.info("categoru Id =" + aOIBAASMCategory.getCategoryID());
       // System.out.println("Cat Id :" + aOIBAASMCategory.getCategoryID());
        aOIFormASMCategory.setName(aOIBAASMCategory.getName());
        //System.out.println("Cat Name :" + aOIBAASMCategory.getName());
        logger.info("categoru Name =" + aOIBAASMCategory.getName());
        aOIFormASMCategory.setDescription(aOIBAASMCategory.getDescription());
        logger.info("categoru Desc =" + aOIBAASMCategory.getDescription());
       // System.out.println("categoru Desc =" + aOIBAASMCategory.getDescription());
        request.setAttribute(OIASMCategoryConstant.ASM_CATEGORY_FORM,aOIFormASMCategory);
        request.setAttribute(OILoginConstants.PAGENAME,"ASMViewUpdate");
        return mapping.findForward(POPULATE_ACTION);
    }
     /**
     * This method will call the OIBOASMCategory.saveCategory() & pass OIBAASMCategory as the input parameter
     * 
     * If save is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the ASM Category Listing page. 
     */
    public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	OIFormASMCategory aOIFormASMCategory = (OIFormASMCategory) form;
    	OIBAASMCategory aOIBAASMCategory = new OIBAASMCategory();
    	aOIBAASMCategory.setName(aOIFormASMCategory.getName());
    	logger.info("Category name"+ aOIFormASMCategory.getName());
    	
    	aOIBAASMCategory.setDescription(aOIFormASMCategory.getDescription());
    	logger.info("Category Desc"+ aOIFormASMCategory.getDescription());
    	
    	aOIBAASMCategory.setCategoryID(aOIFormASMCategory.getCategoryID());

        OIResponseObject oIResponseObject = new OIBOASMCategory().saveCategory(aOIBAASMCategory);
        ArrayList categoryList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_CATEGORY);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIASMCategoryConstant.ASM_CATEGORY_FORM,aOIFormASMCategory);
            return new ActionForward("/asmViewModifyCategoryAction.do?hiddenAction=populate");
        }
        
        return new ActionForward("/asmCategoryAction.do?hiddenAction=populate");
    
     }
    
    /** Yet To be Modified. As Category is deleted the corresponding category Id in OI_AM_Letter tab must b deleted.
     * This method will call the OIBOASMCategory.deleteCategory() & pass OIBAASMCategory as the input parameter
     * 
     * If delete is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the ASM Category Listing page. 
     */
    public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	OIFormASMCategory aOIFormASMCategory = (OIFormASMCategory) form;
    	OIBAASMCategory aOIBAASMCategory = new OIBAASMCategory();
    	aOIBAASMCategory.setName(aOIFormASMCategory.getName());
    	aOIBAASMCategory.setCategoryID(aOIFormASMCategory.getCategoryID());
    	//System.out.println("OIActionASMCategoryViewUpdate-delete=1");
        OIResponseObject oIResponseObject = new OIBOASMCategory().deleteCategory(aOIBAASMCategory);
        ArrayList categoryList = (ArrayList) oIResponseObject.getResponseObject(OILoginConstants.K_CATEGORY);
        String error = (String) oIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIASMCategoryConstant.ASM_CATEGORY_FORM,form);
            return new ActionForward("/asmViewModifyCategoryAction.do?hiddenAction=populate");
        }
        if (categoryList!=null)
        {
            request.setAttribute(OILoginConstants.K_CATEGORY,categoryList);
            request.setAttribute(OIASMCategoryConstant.ASM_CATEGORY_FORM,form);
            return new ActionForward("/asmViewModifyCategoryAction.do?hiddenAction=populate");            
        }
        return new ActionForward("/asmCategoryAction.do?hiddenAction=populate");
    }
    
}
