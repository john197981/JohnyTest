package com.oifm.common;

/**
 * @author Rakesh 
 * Desc : This Action Class list the ASM Categories of ASM Categories.
 * Date : 14 Jan 2008
 */
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;

public class OIActionASMCategory extends OIBaseAction
{
	Logger logger = Logger.getLogger(OIActionASMCategory.class.getName());
	private static final String POPULATE_ACTION = "populate";
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		if (actionName != null)
		{
			if (actionName.equals(POPULATE_ACTION))
			{
				return populate(mapping, form, request, response);
			}
		}
		String error = null;
		try
		{
			error = OIDBRegistry.getValues("OI.CONS.GEN"); // "Action not Available";
		}
		catch (Exception e)
		{}
		request.setAttribute(OILoginConstants.K_ERROR, error);
		return new ActionForward(OILoginConstants.ERRORPAGE);
	}
	
	public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{		
		
		OIResponseObject aOIResponseObject = new OIBOASMCategory().readAllCategory();
		OIBAASMCategory aOIBAASMCategory = null;
		ArrayList categoryList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_CATEGORY);
		String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
		if (error != null)
		{
			request.setAttribute(OILoginConstants.K_ERROR, error);
			return new ActionForward(OILoginConstants.ERRORPAGE);
		}
		ArrayList arOIBAASMCategory = (ArrayList) aOIResponseObject.getResponseObject("arOIBAASMCategory");
		logger.info(arOIBAASMCategory + " aaaaa");
		OIFormASMCategory aOIFormASMCategory = new OIFormASMCategory();
		
		aOIBAASMCategory = (OIBAASMCategory) aOIResponseObject.getResponseObject("aOIBAASMCategory");
		if (arOIBAASMCategory != null)
		{
			OIBAASMCategoryList  aOIBAASMCategoryList = null;
			for (int i = 0; i < arOIBAASMCategory.size(); i++){
				aOIBAASMCategoryList= new OIBAASMCategoryList();
				
				aOIBAASMCategory = (OIBAASMCategory) arOIBAASMCategory.get(i);
				aOIBAASMCategoryList.setCategoryList(aOIBAASMCategory.getName());
				aOIBAASMCategoryList.setCategoryId(aOIBAASMCategory.getCategoryID());
				aOIFormASMCategory.addArCategoryList(aOIBAASMCategoryList);
			}
			request.setAttribute("ASMCategoryForm", aOIFormASMCategory);
			//logger.info(arOIBAASMCategory + "the Values"+ "");

			
			//int flagCategoryId = 0;
			//for (int i = 0; i < arOIBAASMCategory.size(); i++)
			//{
				//logger.info(i + "");
				//aOIBAASMCategory = (OIBAASMCategory) arOIBAASMCategory.get(i);
				
				//if (aOIBAASMCategory.getCategoryID() == flagCategoryId)
				//{
					//flagCategoryId = aOIBAASMCategory.getCategoryID();
				//}
				//else
				//{
				//	aOIFormASMCategory.setCategoryId(aOIBAASMCategory.getCategoryID() + "");
				//	aOIFormASMCategory.setCategoryName(aOIBAASMCategory.getName());
				//	logger.info(aOIBAASMCategory.getCategoryID() + "");
				//	flagCategoryId = aOIBAASMCategory.getCategoryID();
				//	System.out.println("flagCategoryId" + flagCategoryId);
				//	logger.info(flagCategoryId + "flagCategoryId");
				//	logger.info("this is"+ aOIBAASMCategory.getName() +  "");
				//	request.setAttribute("CategoryList", aOIBAASMCategory);
				//}
			//}
		}
		//request.setAttribute("CategoryList", aOIBAASMCategory);
		//logger.info(aOIBAASMCategory.getName() + "this is"+ "");
		request.setAttribute(OILoginConstants.PAGENAME, "arOIBAASMCategoryList");
		return mapping.findForward(POPULATE_ACTION); 
	}
	
	
}
