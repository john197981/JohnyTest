package com.oifm.blog;

import java.util.ArrayList;

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

public class OIActionIndividualBlogAboutUs extends OIBaseAction
{
	private static final Logger logger = Logger.getLogger(OIActionIndividualBlogAboutUs.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		String strAction = (actionName != null) ? actionName : OIIndividualBlogConstants.DISPLAY_AUTHOR_DETAILS_LIST;
		String strForward = "";
		String strRedirect = "";
		String strUserId = "";
		OIBAIndividualBlog objBA = new OIBAIndividualBlog();
		OIFormIndividualBlog objForm = (OIFormIndividualBlog) form;
		OIBOIndividualBlogAboutUs objBO = null;
		OIResponseObject objResponseObject = null;
		
		try
		{
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			Integer blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
			objBO = new OIBOIndividualBlogAboutUs();
			
			objForm.setBlogId(blogId);
			objForm.setAuthorUserId(strUserId);
			
			if (strAction.equals(OIIndividualBlogConstants.DISPLAY_AUTHOR_DETAILS_LIST))
			{
				PropertyUtils.copyProperties(objBA, objForm);
				objResponseObject = objBO.getAuthorDetailsList(objBA);
				request.setAttribute("AuthorDetailsList", objResponseObject.getResponseObject("AuthorDetailsList"));
				
				// START RHS MENU
				Object error = objResponseObject.getResponseObject("error");
				
				objResponseObject = objBO.getAuthorList(objBA);
				request.setAttribute("AuthorList", objResponseObject.getResponseObject("AuthorList"));
				
				if (error != null && !error.equals("null"))
					objResponseObject.addResponseObject("error", error);
				// END RHS MENU
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_ABOUT_US);
				strForward = OIIndividualBlogConstants.BLOG_PAGE;
			}
			else if (strAction.equals(OIIndividualBlogConstants.DISPLAY_AUTHOR_DETAILS))
			{
				PropertyUtils.copyProperties(objBA, objForm);
				objResponseObject = objBO.getAuthorDetails(objBA);
				
				PropertyUtils.copyProperties(objForm, (OIBAIndividualBlog)objResponseObject.getResponseObject("AuthorDetails"));
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_EDIT_ABOUT_ME);
				strForward = OIIndividualBlogConstants.BLOG_PAGE;
			}
			else if (strAction.equals(OIIndividualBlogConstants.UPDATE_AUTHOR_DETAILS))
			{
				PropertyUtils.copyProperties(objBA, objForm);
				objResponseObject = objBO.updateAuthorDetails(objBA);
				
				Object error = objResponseObject.getResponseObject("error");
				
				objResponseObject = objBO.getAuthorDetails(objBA);
				PropertyUtils.copyProperties(objForm, (OIBAIndividualBlog)objResponseObject.getResponseObject("AuthorDetails"));
				
				if (error != null && !error.equals("null"))
					objResponseObject.addResponseObject("error", error);
				
				if (objResponseObject.getResponseObject("error") == null
					|| objResponseObject.getResponseObject("error").equals("null"))
					request.setAttribute("success", OIIndividualBlogConstants.SUCCESS_SAVE);
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_EDIT_ABOUT_ME);
				strForward = OIIndividualBlogConstants.BLOG_PAGE;
			}
			else if (strAction.equals(OIIndividualBlogConstants.READ_PICTURE))
			{
				objResponseObject = objBO.readPicture(response, objForm.getAuthorImageFileName());
			}
			else if (strAction.equals(OIIndividualBlogConstants.REMOVE_PICTURE))
			{
				PropertyUtils.copyProperties(objBA, objForm);
        		objResponseObject = objBO.removePhoto(objBA, strUserId);
        		
        		Object error = objResponseObject.getResponseObject("error");
        		
        		objResponseObject = objBO.getAuthorDetails(objBA);
				PropertyUtils.copyProperties(objForm, (OIBAIndividualBlog)objResponseObject.getResponseObject("AuthorDetails"));
				
				if (error != null && !error.equals("null"))
					objResponseObject.addResponseObject("error", error);
				
				if (objResponseObject.getResponseObject("error") == null
						|| objResponseObject.getResponseObject("error").equals("null"))
						request.setAttribute("success", OIIndividualBlogConstants.SUCCESS_REMOVE_PHOTO);
        		
				request.setAttribute("pageName", OIBlogConstants.BLOG_EDIT_ABOUT_ME);
				strForward = OIIndividualBlogConstants.BLOG_PAGE;
			}
			
			// Other authors for Blog Admin RHS menu
			ArrayList arList = (new OIBOBlogAdmin()).getOtherBlogAuthors(blogId, strUserId);
			if (arList != null && arList.size() > 0)
				request.setAttribute("OTHER_AUTHORS", arList);
			// End RHS menu
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
