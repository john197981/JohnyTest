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

public class OIActionBlogAdminRecentEntries extends OIBaseAction
{
	private static Logger logger = Logger.getLogger(OIActionBlog.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName)
	{
		
		String strAction = (actionName != null) ? actionName :"BlogAdminRecentEntries";
		//System.out.println("strAction"+strAction);
		String strForward = "";
		String strRedirect = "";
		OIBOBlogAdmin objBOBlog  = new OIBOBlogAdmin();
		OIBABlogAdminRecentEntries objBA = null;
		OIResponseObject objResponseObject = new OIResponseObject();
		OIBlogAdminRecentEntriesForm oiBlogAdminRecentEntriesForm = (OIBlogAdminRecentEntriesForm)form;
		
		try {
			String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			Integer blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
			if(strAction.equalsIgnoreCase("apply"))
			{
				OIBABlogAdminRecentEntries blog = new OIBABlogAdminRecentEntries();
				PropertyUtils.copyProperties(blog, oiBlogAdminRecentEntriesForm);
				//System.out.println("OIActionBlogAdminRecentEntries: doIt - noofpost : "+blog.getNoOfPosts());
				
				//blog.setNoOfposts(new Integer(oiBlogAdminRecentEntriesForm.getNoOfPosts()));
				objBOBlog.updateNoOfPosts(blogId , blog.getNoOfPosts());
				//strForward= "BlogAdminRecentEntries";
				
			}
			objResponseObject =objBOBlog.getNoOfPostsRecentEntriesComments(blogId);
			objBA = (OIBABlogAdminRecentEntries)objResponseObject.getResponseObject("objBA");
			PropertyUtils.copyProperties(oiBlogAdminRecentEntriesForm, objBA);
			request.setAttribute("recent_entries",objResponseObject.getResponseObject("entriesList"));
			request.setAttribute("recent_comments",objResponseObject.getResponseObject("commentsList"));
			
			request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.ACTION_INDIVIDUAL_BLOG_ADMIN);
			strForward = OIBlogConstants.BLOG_MODULE_HOME_PAGE;
			
			// Other authors for Blog Admin RHS menu
			ArrayList arList = (new OIBOBlogAdmin()).getOtherBlogAuthors(blogId, strUserId);
			if (arList != null && arList.size() > 0)
				request.setAttribute("OTHER_AUTHORS", arList);
			// End RHS menu
			
		} catch (Exception e) {
			logger.error("TRY - CATCH : " + e);
			strRedirect = OIIndividualBlogConstants.ERROR_DO + "?error=OIDEFAULT";
		}
		
		finally 
    	{
			
		}
		
		if(!strRedirect.equals(""))
		{
			return new ActionForward(strRedirect);
		}
		else
		{
			return (mapping.findForward(strForward));
		}
		
    	
	}

}
