package com.oifm.blog;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionIndividualBlogHome extends OIBaseAction
{
	private static final Logger logger = Logger.getLogger(OIActionIndividualBlogHome.class);
	
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		String strAction = (actionName != null) ? actionName : OIIndividualBlogConstants.LIST_BLOG_ENTRIES;
		String strForward = "";
		String strRedirect = "";
		String strUserId = "";
		OIBAIndividualBlog objBAIndividualBlogHome = new OIBAIndividualBlog();
		OIFormIndividualBlog objFormIndividualBlogHome = (OIFormIndividualBlog) form;
		OIBOIndividualBlogHome objBOIndividualBlogHome = null;
		OIResponseObject objResponseObject = null;
		
		try
		{
			objBOIndividualBlogHome = new OIBOIndividualBlogHome();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			
			OIBOBlog objBOBlog = new OIBOBlog();
			boolean isAuthor = objBOBlog.isAuthor(strUserId, objFormIndividualBlogHome.getBlogId());
			setSessionAttribute(request, OIBlogConstants.IS_BLOG_AUTHOR, Boolean.valueOf(isAuthor));
			
			if (strAction.equals(OIIndividualBlogConstants.LIST_BLOG_ENTRIES))
			{
				PropertyUtils.copyProperties(objBAIndividualBlogHome, objFormIndividualBlogHome);
				
				setSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID, objBAIndividualBlogHome.getBlogId());
				setSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_TITLE, (new OIBOBlog()).getBlogTitle(objBAIndividualBlogHome.getBlogId()));
				
				objResponseObject = objBOIndividualBlogHome.getIndividualBlogEntries(objBAIndividualBlogHome);
				request.setAttribute("BlogEntries", objResponseObject.getResponseObject("BlogEntries"));
				
				
				// START RHS MENU
				Object error = objResponseObject.getResponseObject("error");
				
				objBOBlog = new OIBOBlog();
				objResponseObject = objBOBlog.getArchivesList(objBAIndividualBlogHome);
				request.setAttribute(OIBlogConstants.BLOG_DETAIL, objResponseObject.getResponseObject(OIBlogConstants.BLOG_DETAIL));
				
				if (error != null && !error.equals("null"))
					objResponseObject.addResponseObject("error", error);
				// END RHS MENU
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_HOME_PAGE);
				strForward = OIIndividualBlogConstants.BLOG_PAGE;
			}
		}
		catch (Exception e)
		{
			logger.error("TRY - CATCH : " + e);
			strRedirect = OIIndividualBlogConstants.ERROR_DO + "?error=OIDEFAULT";
		}
		finally
		{
			if (!strForward.equals("") && objResponseObject.getResponseObject("error") != null
					&& !objResponseObject.getResponseObject("error").equals("null"))
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
		
		if (!strRedirect.equals(""))
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}
	
}
