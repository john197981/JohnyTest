package com.oifm.blog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIForumConstants;
import com.oifm.login.OILoginConstants;

public class OIBlogAdminAction extends OIBaseAction
{
	
	private static Logger logger = Logger.getLogger(OIBlogAdminAction.class);	
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{		
		String strAction = (actionName != null) ? actionName : OIBlogConstants.ACTION_MANAGE_BLOG;
		String strForward = "";
		String strRedirect = "";
		OIBOBlog boBlog = new OIBOBlog();
		OIResponseObject objResponseObject = null;
		OIBABlogAuthor oiBABlogAuthor =  null;
		OIBABlogConfig objBABlogConfig = null;
		OIBlogAdminForm oiBlogAdminForm = (OIBlogAdminForm)form;
		HttpSession session = null;
		String strUserId = "";
		boolean isASMRep = false;
    	ArrayList alFunctions = null;
		try
		{
			
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isASMRep = alFunctions.contains(OIFunctionConstants.ASM_REPS);
			if(isASMRep){
			//System.out.println("OIBlogAdminAction: doIt - strAction : "+strAction);
			if(strAction.equals(OIBlogConstants.ACTION_CREATE_BLOG))
			{
				setSessionAttribute(request, OIBlogConstants.BLOG_MODE, OIBlogConstants.CREATE_BLOG_MODE);
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.CREATE_BLOG_PAGE);
				removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
				removeSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				removeSessionAttribute(request, OIBlogConstants.ALL_USERS);
			}
			else if(strAction.equals(OIBlogConstants.ACTION_MODIFY_BLOG))
			{
				Integer blogId = null;
				if(request.getParameter("blogId")!=null)
				{
					blogId = new Integer(request.getParameter("blogId"));
					//System.out.println("OIBlogAdminAction: doIt -  from request blogId : "+blogId);
				}
				else
				{
					blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
					//System.out.println("OIBlogAdminAction: doIt -  from session blogId : "+blogId);
				}
				
				//System.out.println("OIBlogAdminAction: doIt -  modify blogId : "+blogId);
				
				if (blogId!=null)
				{
					setSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID,blogId);
					
					objResponseObject = boBlog.getBlog(blogId);
					OIBABlog oiBABlog = (OIBABlog)objResponseObject.getResponseObject(OIBlogConstants.BLOG_DATA);
					//System.out.println("OIBlogAdminAction: doIt - oiBABlog : "+oiBABlog);
					
					if (oiBABlog!=null)
					{			
						OICreateBlogForm oiCreateBlogForm = new OICreateBlogForm();
						oiCreateBlogForm.setBlogId(blogId.intValue());
						oiCreateBlogForm.setTitle(oiBABlog.getTitle());
						setSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG, oiCreateBlogForm);
						
						List authors = oiBABlog.getAuthors();
						if (authors!=null)
						{		
							//System.out.println("OIBlogAdminAction: doIt - authors.size() : "+authors.size());
							if (authors.size()>0)
							{								
								setSessionAttribute(request, OIBlogConstants.AUTHORS_LIST, authors);	
								OIBlogAuthorListForm oiBlogAuthorListForm = new OIBlogAuthorListForm();
								ArrayList selectedAuthors = new ArrayList();
								for (Iterator iter = authors.iterator(); iter.hasNext();)
								{
									oiBABlogAuthor = (OIBABlogAuthor) iter.next();
									selectedAuthors.add(oiBABlogAuthor.getUserId());					
								}
								oiBlogAuthorListForm.setAryUsers((String[])selectedAuthors.toArray(new String[0]));
								setSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST, oiBlogAuthorListForm);								
								
							}
						}
					}
					setSessionAttribute(request, OIBlogConstants.BLOG_MODE, OIBlogConstants.MODIFY_BLOG_MODE);
					request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.CREATE_BLOG_PAGE);
					request.setAttribute("forManage", "Manage");
				}
				
			}
			else if(strAction.equals(OIBlogConstants.ACTION_MANAGE_BLOG))
			{
				objResponseObject = boBlog.getBlogsList();
				request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.MANAGE_BLOG_PAGE);
				removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
				removeSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
				removeSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				removeSessionAttribute(request, OIBlogConstants.ALL_USERS);
				
				//System.out.println("OIBlogAdminAction: doIt - var : "+"manage");
			}
			else if(strAction.equals(OIBlogConstants.ACTION_DELETE_BLOG))
			{
				String[] blogs = oiBlogAdminForm.getAryBlogs();
				if (blogs != null)
				{
					ArrayList blogsList = new ArrayList();
					for (int i = 0; i < blogs.length; i++)
					{
						//System.out.println("OIBlogAdminAction: doIt - selected : " + blogs[i]);
						blogsList.add(new Integer(blogs[i]));
					}
					if (blogsList.size() > 0)
					{
						boBlog.deleteBlogs(blogsList);
						objResponseObject = boBlog.getBlogsList();
						request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
					}
				}
				
				objResponseObject = boBlog.getBlogsList();
				request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
				removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
				removeSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				removeSessionAttribute(request, OIBlogConstants.ALL_USERS);
				removeSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
				
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.MANAGE_BLOG_PAGE);
				request.setAttribute("forManage", "Manage");
			}			
			else if(strAction.equals(OIBlogConstants.ACTION_BLOG_SETTINGS))
			{			
				objResponseObject = boBlog.getBlogConfig();
				objBABlogConfig = (OIBABlogConfig) objResponseObject.getResponseObject(OIBlogConstants.BLOG_CONFIG);
				oiBlogAdminForm.setNoOfPosts(objBABlogConfig.getNoOfPosts().intValue());
				oiBlogAdminForm.setNoOfFeatPosts(objBABlogConfig.getNoOfFeatPosts().intValue());
				oiBlogAdminForm.setBlogMessage(objBABlogConfig.getBlogMessage());
				request.setAttribute(OIBlogConstants.FORM_BLOG_ADMIN,oiBlogAdminForm);
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.BLOG_SETTINGS_PAGE);
				
			}
			else if(strAction.equals("SaveBlogSettings"))
			{
				objBABlogConfig = new OIBABlogConfig();
				PropertyUtils.copyProperties(objBABlogConfig, oiBlogAdminForm);
				objResponseObject = boBlog.updateBlogConfig(objBABlogConfig);
				//System.out.println("objBABlogConfig.getNoOfPosts().intValue()=" + objBABlogConfig.getNoOfPosts().intValue());
				//System.out.println("objBABlogConfig.getNoOfFeatPosts().intValue()=" + objBABlogConfig.getNoOfFeatPosts().intValue());
				//System.out.println("objBABlogConfig.getBlogMessage()=" + objBABlogConfig.getBlogMessage());
				
				objResponseObject = boBlog.getBlogsList();
				request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.MANAGE_BLOG_PAGE);				
			}	
			else if(OIBlogConstants.ACTION_CANCEL.equals(strAction))
			{
				objResponseObject = boBlog.getBlogsList();
				request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
				removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
				removeSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				removeSessionAttribute(request, OIBlogConstants.ALL_USERS);
				removeSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.ACTION_MANAGE_BLOG);
				
			}
			strForward = OIBlogConstants.ADMIN_PAGE;
			
		}else
		{
		    strRedirect = OIForumConstants.ERROR_DO+"?error=NoAccess";
		}
		}catch (Exception e)
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
