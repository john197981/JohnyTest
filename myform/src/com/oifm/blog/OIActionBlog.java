package com.oifm.blog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;

public class OIActionBlog extends OIBaseAction
{
	private static Logger logger = Logger.getLogger(OIActionBlog.class);
	
	// Added by Oscar
	private static final String READ_PICTURE = "READ_PICTURE";
	
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		String strAction = (actionName != null) ? actionName : OIBlogConstants.BLOG_MODULE_HOME_PAGE;
		String strForward = "";
		String strRedirect = "";
		String strUserId = "";
		OIBOBlog oiBOBlog = null;
		OIResponseObject objResponseObject = null;
		OIBlogHomeForm oiBlogHomeForm = (OIBlogHomeForm)form;
		
		try
		{
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			
			// Added by Oscar
			if (strAction.equals(READ_PICTURE))
			{
				oiBOBlog = new OIBOBlog();
				objResponseObject = oiBOBlog.readPicture(response, oiBlogHomeForm.getPicFileName());
				return null;
			}
			// End added by Oscar
			else if (strAction.equals(OIBlogConstants.ACTION_INDIVIDUAL_BLOG_ADMIN))
			{
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.ACTION_INDIVIDUAL_BLOG_ADMIN);
			}
			else if (strAction.equals(OIBlogConstants.ACTION_ABOUT_US))
			{
				oiBOBlog = new OIBOBlog();
				objResponseObject = oiBOBlog.getAllBlogsAuthors();
				List authors = (List) objResponseObject.getResponseObject(OIBlogConstants.ALL_BLOGS_AUTHORS);
				request.setAttribute("ALL_BLOGS_AUTHORS", authors);
				request.setAttribute("pageName", OIBlogConstants.ACTION_ABOUT_US);
			}
			else if (strAction.equals(OIBlogConstants.BLOG_HOME_PAGE))
			{		
				int blogId = oiBlogHomeForm.getBlogId();
				logger.info("OIActionBlog: doIt - blogId : "+blogId);
				setSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID, new Integer(blogId));
				
				oiBOBlog = new OIBOBlog();
				boolean isAuthor = oiBOBlog.isAuthor(strUserId, new Integer(blogId));
				setSessionAttribute(request, OIBlogConstants.IS_BLOG_AUTHOR, Boolean.valueOf(isAuthor));
				
				request.setAttribute("pageName", OIBlogConstants.BLOG_HOME_PAGE);
			}			
			else
			{
				oiBOBlog = new OIBOBlog();
				objResponseObject = oiBOBlog.getBlogDetail(null);
				OIBABlogDetail oiBABlogDetail = (OIBABlogDetail) objResponseObject.getResponseObject(OIBlogConstants.BLOG_DETAIL);
			
				List allTagCloud = (List) objResponseObject.getResponseObject("ALL_TAGS");
				request.setAttribute("ALL_TAGS", allTagCloud);
				request.setAttribute(OIBlogConstants.BLOG_DETAIL, oiBABlogDetail);
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.BLOG_MODULE_HOME_PAGE);
				removeSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
				removeSessionAttribute(request, OIBlogConstants.IS_BLOG_AUTHOR);

			}
			strForward = OIBlogConstants.BLOG_MODULE_HOME_PAGE;
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
