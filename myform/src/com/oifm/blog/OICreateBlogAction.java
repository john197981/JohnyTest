/**
 * 
 */
package com.oifm.blog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;

/**
 * @author Admin
 * 
 */
public class OICreateBlogAction extends OIBaseAction 
{
	private static Logger logger = Logger.getLogger(OICreateBlogAction.class);	
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		String strForward = "";
		String strRedirect = "";
		OIBOBlog blogBO= new OIBOBlog();
		OIResponseObject objResponseObject = null;
		OIBABlogAuthor author = null;
		Integer blogId = null;
		
		OICreateBlogForm oiCreateBlogForm = (OICreateBlogForm)form;
		//System.out.println("OICreateBlogAction: doIt - oiCreateBlogForm : "+oiCreateBlogForm);
		//System.out.println("OICreateBlogAction: doIt - actionName : "+actionName);
		
		try
		{
			if(OIBlogConstants.ACTION_SAVE.equals(actionName))
			{						
				OIBABlog blog = new OIBABlog();
				PropertyUtils.copyProperties(blog, oiCreateBlogForm);
				
				List authors = (List)getSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				if(authors!=null)
				{
					blog.setAuthors(authors);
				}			
				String mode = (String)getSessionAttribute(request, OIBlogConstants.BLOG_MODE);
				//System.out.println("OICreateBlogAction: doIt - mode : "+mode);
				
				//System.out.println("OICreateBlogAction: doIt - blogId From form : "+oiCreateBlogForm.getBlogId());
				if (oiCreateBlogForm.getBlogId()!=0)
				{
					blogId = new Integer(oiCreateBlogForm.getBlogId());
				}				
				
				boolean doesBlogTitleExists = blogBO.doesBlogTitleExists(oiCreateBlogForm.getTitle(),blogId);
				if (!doesBlogTitleExists)
				{					
					if ((OIBlogConstants.CREATE_BLOG_MODE).equals(mode))
					{
						blogBO.createBlog(blog);									
					}
					else if ((OIBlogConstants.MODIFY_BLOG_MODE).equals(mode))
					{											
						blog.setBlogId(blogId);
						blogBO.updateBlog(blog);
					}	
				}
				else
				{	
					request.setAttribute("error", "BLOG.ADMIN.BLOG.EXISTS");
					request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.CREATE_BLOG_PAGE);
					strForward = OIBlogConstants.ADMIN_PAGE;
					return (mapping.findForward(strForward));
				}	
				
				objResponseObject = blogBO.getBlogsList();
				request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
				removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
				removeSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				removeSessionAttribute(request, OIBlogConstants.ALL_USERS);
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.ACTION_MANAGE_BLOG);
				strForward = OIBlogConstants.ADMIN_PAGE;
			}			
			else if(OIBlogConstants.ACTION_REFRESH.equals(actionName))
			{
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.CREATE_BLOG_PAGE);
				strForward = OIBlogConstants.ADMIN_PAGE;
			}
			else if(OIBlogConstants.ACTION_CANCEL.equals(actionName))
			{
				objResponseObject = blogBO.getBlogsList();
				request.setAttribute(OIBlogConstants.BLOGS_LIST, objResponseObject.getResponseObject(OIBlogConstants.BLOGS_LIST));
				removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
				removeSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
				removeSessionAttribute(request, OIBlogConstants.ALL_USERS);
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.ACTION_MANAGE_BLOG);
				strForward = OIBlogConstants.ADMIN_PAGE;
			}
			else if("DeleteBlogAuthorscreate".equals(actionName) || "DeleteBlogAuthorsmanage".equals(actionName))
			{
				if("DeleteBlogAuthorsmanage".equals(actionName))
					request.setAttribute("forManage", "Manage");

				String[] authors = (oiCreateBlogForm).getDeleteArthors();
				if (authors != null)
				{					
					ArrayList authorsList = (ArrayList) getSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
					if(authorsList!=null)
					{			
						boolean removedItem = false;
						for (int i = 0; i < authors.length; i++)
						{
							//System.out.println("OICreateBlogAction: doIt - selected : " + authors[i]);
							
							for (int j = 0; j < authorsList.size(); j++)
							{
								author = (OIBABlogAuthor) authorsList.get(j);
								//System.out.println("OICreateBlogAction: doIt - author.getUserId() : " + author.getUserId());
								if ((author.getUserId()).equals(authors[i]))
								{
									authorsList.remove(j);
									removedItem = true;
									author.setAddedToEntry(false);						
								}
							}							
						}
						if (removedItem)
						{
//							removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
							//removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
							setSessionAttribute(request, OIBlogConstants.AUTHORS_LIST, authorsList);
						}
					}
					/*
					 * if (blogsList.size()>0) { boBlog.deleteBlogs(blogsList); objResponseObject = boBlog.getBlogsList();
					 * request.setAttribute("BlogsList", objResponseObject.getResponseObject("BlogsList")); }
					 */
				}
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.CREATE_BLOG_PAGE);
				setSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST, form);
				
				strForward = OIBlogConstants.ADMIN_PAGE;
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}		
		finally
		{
			//request.setAttribute("error", objResponseObject.getResponseObject("error"));
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
