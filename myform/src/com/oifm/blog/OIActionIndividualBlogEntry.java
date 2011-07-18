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

public class OIActionIndividualBlogEntry extends OIBaseAction
{
	private static final Logger logger = Logger.getLogger(OIActionIndividualBlogEntry.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		String strAction = (actionName != null) ? actionName : OIIndividualBlogConstants.DISPLAY_BLOG_ENTRY;
		String strForward = "";
		String strRedirect = "";
		String strUserId = "";
		OIBAIndividualBlog objBA = new OIBAIndividualBlog();
		OIFormIndividualBlog objForm = (OIFormIndividualBlog) form;
		OIBOIndividualBlogEntry objBO = null;
		OIResponseObject objResponseObject = null;
		
		try
		{
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			objBO = new OIBOIndividualBlogEntry();
			
			if (objForm.getBlogId() == null)
				objForm.setBlogId((Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID));
			
			OIBOBlog objBOBlog = new OIBOBlog();
			boolean isAuthor = objBOBlog.isAuthor(strUserId, objForm.getBlogId());
			setSessionAttribute(request, OIBlogConstants.IS_BLOG_AUTHOR, Boolean.valueOf(isAuthor));
			
			if (strAction.equals(OIIndividualBlogConstants.DISPLAY_BLOG_ENTRY))
			{
				PropertyUtils.copyProperties(objBA, objForm);
				
				objResponseObject = objBO.getBlogEntryDetails(objBA);
				
				setSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID, ((OIBAIndividualBlog)objResponseObject.getResponseObject("EntryDetails")).getBlogId());
				setSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_TITLE, (new OIBOBlog()).getBlogTitle(((OIBAIndividualBlog)objResponseObject.getResponseObject("EntryDetails")).getBlogId()));
				
				request.setAttribute("EntryDetails", objResponseObject.getResponseObject("EntryDetails"));
				request.setAttribute("Comments", objResponseObject.getResponseObject("Comments"));
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_ENTRY);
				strForward = OIIndividualBlogConstants.BLOG_PAGE;
			}
			else if (strAction.equals(OIIndividualBlogConstants.DO_SUBMIT_COMMENT))
			{
				PropertyUtils.copyProperties(objBA, objForm);
				objResponseObject = objBO.saveComment(objBA, strUserId);
				Object error = objResponseObject.getResponseObject("error");
				
				if (error == null || error.equals("null"))
					objBO.sendCommentNotificationMail(objBA);
				
				objForm.setComment("");
				
				objResponseObject = objBO.getBlogEntryDetails(objBA);
				request.setAttribute("EntryDetails", objResponseObject.getResponseObject("EntryDetails"));
				request.setAttribute("Comments", objResponseObject.getResponseObject("Comments"));
				
				if (error != null && !error.equals("null"))
					objResponseObject.addResponseObject("error", error);
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_ENTRY);
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
