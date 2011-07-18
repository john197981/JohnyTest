package com.oifm.blog;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OIUtilities;

public class OIBlogAuthorListAction extends OIBaseAction
{
	private static Logger logger = Logger.getLogger(OIBlogAuthorListAction.class);
	
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		String strForward = "";
		String strRedirect = "";
		OIBOBlog blogBO = new OIBOBlog();
		OIResponseObject objResponseObject = null;
		int noOfCount = 15;
		int rowId = 0;
		ArrayList allUsers = null;
		OIBlogAuthorListForm oiBlogAuthorListForm = (OIBlogAuthorListForm) form;
		OIPageInfoBean aOIPageInfoBean = null;
		ArrayList authorsList = null;
		OIBABlogAuthor author = null;
		
		try
		{
			authorsList = (ArrayList) getSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
			if (authorsList==null)
			{
				authorsList = new ArrayList();
			}
			
			
			String pageNo = request.getParameter("pageNo");
			//System.out.println("OIBlogAuthorListAction: doIt - pageNo : "+pageNo);
			
			if (pageNo==null)
    		{
				rowId = 1;
    		}
    		else
    		{
    			if (!"".equals(pageNo))
				{		    				
    				rowId = Integer.parseInt(pageNo);
				}
    			
    		}
			
			//System.out.println("OIBlogAuthorListAction: doIt - actionName : "+actionName);
			if (OIBlogConstants.ACTION_SELECT_USERS.equals(actionName))
			{
				String[] users = oiBlogAuthorListForm.getAryUsers();
				if (users != null)
				{					
					
					ArrayList usersList = (ArrayList) getSessionAttribute(request, OIBlogConstants.ALL_USERS);
					boolean newItem = false;
					for (int i = 0; i < users.length; i++)
					{
						//System.out.println("OIBlogAuthorListAction: doIt - selected : " + users[i]);
						
						for (int j = 0; j < usersList.size(); j++)
						{
							author = (OIBABlogAuthor) usersList.get(j);
							if ((author.getUserId()).equals(users[i]))
							{
								if(!authorsList.contains(author))
								{
									authorsList.add(author);
									newItem = true;
									author.setAddedToEntry(true);
									usersList.set(j,author);
								}
							}
						}						
					}
					if (newItem)
					{
						removeSessionAttribute(request, OIBlogConstants.AUTHORS_LIST);
						removeSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST);
						setSessionAttribute(request, OIBlogConstants.AUTHORS_LIST, authorsList);
					}
					
					/*
					 * if (blogsList.size()>0) { boBlog.deleteBlogs(blogsList); objResponseObject = boBlog.getBlogsList();
					 * request.setAttribute("BlogsList", objResponseObject.getResponseObject("BlogsList")); }
					 */
				}
				request.setAttribute(OIBlogConstants.PAGE_NAME, OIBlogConstants.ACTION_MANAGE_BLOG);
				setSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST, oiBlogAuthorListForm);
			}
			else if (OIBlogConstants.ACTION_AUTHORS_LIST.equals(actionName))
			{				
				allUsers = (ArrayList) getSessionAttribute(request, OIBlogConstants.ALL_USERS);
				// //System.out.println("OICreateBlogAction: doIt - allUsers from session : "+allUsers);

				aOIPageInfoBean = (OIPageInfoBean) getSessionAttribute(request, OIBlogConstants.K_aOIPageInfoBean);
				
//				if (allUsers == null)
//				{
					System.out.println("OIBlogAuthorListAction: doIt - var : "+"ok");
					objResponseObject = blogBO.getAllUsers(oiBlogAuthorListForm.getSearchString(), rowId, noOfCount);					
//				}
				oiBlogAuthorListForm.setLastSearchString(oiBlogAuthorListForm.getSearchString());
				strForward = OIBlogConstants.AUTHOR_PAGE;
			}
			else if ("pagination".equals(actionName))
			{	
				oiBlogAuthorListForm.setSearchString(OIUtilities.replaceNull(oiBlogAuthorListForm.getLastSearchString()));
				objResponseObject = blogBO.getAllUsers(oiBlogAuthorListForm.getSearchString(), rowId, noOfCount);				
				strForward = OIBlogConstants.AUTHOR_PAGE;
			}
			
			if (objResponseObject != null)
			{				
				allUsers = (ArrayList) objResponseObject.getResponseObject(OIBlogConstants.ALL_USERS);
				// //System.out.println("OICreateBlogAction: doIt - allUsers from DB : "+allUsers);
				OIBABlogAuthor user = null;
				
				for (int i = 0; i < authorsList.size(); i++)
				{
					author = (OIBABlogAuthor) authorsList.get(i);
					for (int j = 0; j < allUsers.size(); j++)
					{
						user = (OIBABlogAuthor) allUsers.get(j);
						if ((author.getUserId()).equals(user.getUserId()))
						{
							user.setAddedToEntry(true);
							allUsers.set(j,user);
							break;
						}
					}
				}
				setSessionAttribute(request, OIBlogConstants.ALL_USERS, allUsers);				
				
				aOIPageInfoBean = (OIPageInfoBean) objResponseObject.getResponseObject(OIBlogConstants.K_aOIPageInfoBean);
				setSessionAttribute(request, OIBlogConstants.K_aOIPageInfoBean, aOIPageInfoBean);
			}
			
			if (aOIPageInfoBean != null)
			{
				ArrayList arPage = new ArrayList();
				int start = aOIPageInfoBean.getCurrLinkStart();
				for (int i = start; i < start + aOIPageInfoBean.getNoOfLinks(); i++)
				{
					if (i <= aOIPageInfoBean.getNoOfPages())
						arPage.add(i + "");
				}
				
				if (aOIPageInfoBean.getNoOfPages() >= 1)
				{
					request.setAttribute("totalPage", aOIPageInfoBean.getNoOfPages() + "");
				}
				else
				{
					request.setAttribute("totalPage", aOIPageInfoBean.getNoOfPages() + "");
				}
				request.setAttribute(OIBlogConstants.K_currentPage, aOIPageInfoBean.getPageNo() + "");
				request.setAttribute(OIBlogConstants.K_pageNo, aOIPageInfoBean.getPageNo() + "");
				request.setAttribute(OIBlogConstants.K_nextSet, aOIPageInfoBean.isNSet() + "");
				request.setAttribute(OIBlogConstants.K_previousSet, aOIPageInfoBean.isPSet() + "");
				request.setAttribute(OIBlogConstants.K_nextPage, aOIPageInfoBean.getNextSet() + "");
				request.setAttribute(OIBlogConstants.K_previousPage, aOIPageInfoBean.getPrevSet() + "");
				request.setAttribute(OIBlogConstants.K_arPage, arPage);
			}			
			
			if (authorsList!=null)
			{
				ArrayList arys = new ArrayList();
				for (Iterator iter = authorsList.iterator(); iter.hasNext();)
				{
					author = (OIBABlogAuthor) iter.next();
					arys.add(author.getUserId());
				}
				oiBlogAuthorListForm.setAryUsers((String[])arys.toArray(new String[0]));
				setSessionAttribute(request, OIBlogConstants.FORM_BLOG_AUTHOR_LIST, oiBlogAuthorListForm);
			}
			
			String mode = (String)getSessionAttribute(request, OIBlogConstants.BLOG_MODE);
			//System.out.println("OIBlogAuthorListAction: doIt - mode : "+mode);
			if ((OIBlogConstants.MODIFY_BLOG_MODE).equals(mode))
			{
				request.setAttribute("forManage", "Manage");
			}
			
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
