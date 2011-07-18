package com.oifm.blog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionBlogAdminCategory extends OIBaseAction 
{

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		//System.out.println("OIActionBlogAdminCategory: doIt - actionName : "+actionName);
		String strAction = (actionName!=null)?actionName:"BlogAdminCategories";
		//System.out.println("OIActionBlogAdminCategory: doIt - strAction : "+strAction);
		String strForward = "";
		String strRedirect = "";
		OIBOBlogAdminCategory blogBO= new OIBOBlogAdminCategory();
		OIResponseObject objResponseObject = null;
		Integer categoryId = null;
		
		OIBlogAdminCategoryForm oiBlogAdminCategoryForm = (OIBlogAdminCategoryForm)form;
		oiBlogAdminCategoryForm = (oiBlogAdminCategoryForm!=null)?oiBlogAdminCategoryForm:(new OIBlogAdminCategoryForm());
		
		if(oiBlogAdminCategoryForm!=null)
		{
			//System.out.println("OIActionBlogAdminCategory: doIt - HiddenAction : "+oiBlogAdminCategoryForm.getHiddenAction());
			//System.out.println("OIActionBlogAdminCategory: doIt - Category : "+oiBlogAdminCategoryForm.getCategory());
			//System.out.println("OIActionBlogAdminCategory: doIt - Category Id : "+oiBlogAdminCategoryForm.getCategoryId());
			categoryId = new Integer(oiBlogAdminCategoryForm.getCategoryId());
		}
		
		try
		{
			String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			Integer blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
			
			if(strAction.equalsIgnoreCase("CreateCategoryAction"))
			{
				boolean doesCategoryExists = blogBO.doesCategoryExists(oiBlogAdminCategoryForm.getCategory(),categoryId);
				if (!doesCategoryExists)
				{	
					OIBABlogAdminCategory blog = new OIBABlogAdminCategory();
					PropertyUtils.copyProperties(blog, oiBlogAdminCategoryForm);				
					objResponseObject = blogBO.createCategory(blog);				
					oiBlogAdminCategoryForm.setCategory(null);
					oiBlogAdminCategoryForm.setHiddenAction("CreateCategoryAction");			
					request.setAttribute("success", "BLOG.INDIVIDUAL.ADMIN.CATEGORIES.SUCCESS_CREATE");
				}
				else
				{	
					objResponseObject = blogBO.getCategories();
					request.setAttribute("error", "BLOG.ADMIN.BLOG.CATEGORY.EXISTS");					
				}
				oiBlogAdminCategoryForm.setMode("createMode");
			}
			else if(strAction.equalsIgnoreCase("BlogAdminCategories")) 
			{
				objResponseObject = blogBO.getCategories();
				
				oiBlogAdminCategoryForm.setCategory(null);
				oiBlogAdminCategoryForm.setHiddenAction("CreateCategoryAction");
				oiBlogAdminCategoryForm.setMode("createMode");
				
			}
			else if(strAction.equalsIgnoreCase("GetCategoryAction")) 
			{
				//System.out.println("OIActionBlogAdminCategory: doIt - CategoryId : "+oiBlogAdminCategoryForm.getCategoryId());
				objResponseObject = blogBO.getCategory(categoryId);
				
				if (objResponseObject!=null)
				{
					OIBABlogAdminCategory objBA = (OIBABlogAdminCategory)objResponseObject.getResponseObject("GetCategory");
					if(objBA!=null)
					{
						oiBlogAdminCategoryForm.setCategory(objBA.getCategory());
						oiBlogAdminCategoryForm.setCategoryId(objBA.getCategoryId().intValue());
						oiBlogAdminCategoryForm.setHiddenAction("ModifyCategoryAction");	
						oiBlogAdminCategoryForm.setMode("modifyMode");
					}
				}
			}
			else if(strAction.equalsIgnoreCase("ModifyCategoryAction")) 
			{
				boolean doesCategoryExists = blogBO.doesCategoryExists(oiBlogAdminCategoryForm.getCategory(),categoryId);
				if (!doesCategoryExists)
				{	
					OIBABlogAdminCategory blog = new OIBABlogAdminCategory();
					PropertyUtils.copyProperties(blog, oiBlogAdminCategoryForm);
					objResponseObject = blogBO.updateCategory(blog);
					
					oiBlogAdminCategoryForm.setCategory(null);
					oiBlogAdminCategoryForm.setCategoryId(0);
					oiBlogAdminCategoryForm.setHiddenAction("CreateCategoryAction");
					oiBlogAdminCategoryForm.setMode("CreateCategoryAction");
					
					request.setAttribute("success", "BLOG.INDIVIDUAL.ADMIN.CATEGORIES.SUCCESS_EDIT");
					oiBlogAdminCategoryForm.setMode("createMode");
				}
				else
				{	
					objResponseObject = blogBO.getCategories();
					request.setAttribute("error", "BLOG.ADMIN.BLOG.CATEGORY.EXISTS");	
					oiBlogAdminCategoryForm.setMode("modifyMode");
				}			
			}
			
			// Other authors for Blog Admin RHS menu
			ArrayList arList = (new OIBOBlogAdmin()).getOtherBlogAuthors(blogId, strUserId);
			if (arList != null && arList.size() > 0)
				request.setAttribute("OTHER_AUTHORS", arList);
			// End RHS menu
			
			if (objResponseObject!=null)
			{
				request.setAttribute("categoriesList",objResponseObject.getResponseObject("categoriesList"));
			}			
			
			request.setAttribute("OIBlogAdminCategoryForm",oiBlogAdminCategoryForm);			
			request.setAttribute("pageName", "BlogAdminCategories");
			strForward = "success";
			return mapping.findForward(strForward);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		finally
		{
			
		}
		
		if (!strRedirect.equals(""))
		{
			return new ActionForward(strRedirect);
		}
		else
		{
			return (mapping.findForward(strForward));
		}
	
	}	
}
