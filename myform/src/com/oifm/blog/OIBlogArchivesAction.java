package com.oifm.blog;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIFormUtilities;

public class OIBlogArchivesAction extends OIBaseAction
{
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		int noOfCount = 5;
		String strForward = "";
		String strRedirect = "";
		String strAction = (actionName != null) ? actionName : OIBlogConstants.ACTION_ARCHIVES;
		OIBOBlog oiBOBlog = new OIBOBlog();
		OIResponseObject objResponseObject = null;
		OIBlogArchivesForm oiForm = (OIBlogArchivesForm) form;	
		OIBABlogArchives oiBABlogArchives = new OIBABlogArchives();	
		
		try
		{
			Integer blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
			oiBABlogArchives.setBlogId(blogId);
			oiBABlogArchives.setRecPerPage(noOfCount);
			
			//System.out.println("OIBlogArchivesAction: doIt - pageNo : "+request.getParameter("pageNo"));
			if (request.getParameter("pageNo")==null)
    		{
				oiBABlogArchives.setRowId("1");
    		}
    		else
    		{
    			oiBABlogArchives.setRowId(OIFormUtilities.chkIsNull(request.getParameter("pageNo")));
    			if(oiBABlogArchives.getRowId().length()==0)
    			{
    				oiBABlogArchives.setRowId("0");
    			}
    		}
		
			if (strAction.equals(OIBlogConstants.ACTION_ARCHIVES))
			{		
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY);
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
			}
			else if (strAction.equals(OIBlogConstants.ACTION_ARCHIVES_CATEGORY))
			{
				//System.out.println("OIBlogArchivesAction: doIt - CategoryId : "+oiForm.getCategoryId());
				oiBABlogArchives.setRowId("1");
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY);
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
				oiBABlogArchives.setCategoryId(new Integer(oiForm.getCategoryId()));
				request.setAttribute("TYPE", "Category");
				if (oiForm.getCategoryName() != null && oiForm.getCategoryName().length() > 0)
					request.setAttribute("TYPE_VALUE", oiForm.getCategoryName());
			}
			else if (strAction.equals(OIBlogConstants.ACTION_ARCHIVES_AUTHOR))
			{
				//System.out.println("OIBlogArchivesAction: doIt - Author Id : "+oiForm.getAuthorId());
				oiBABlogArchives.setRowId("1");
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY);
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
				oiBABlogArchives.setAuthorId(oiForm.getAuthorId());
				request.setAttribute("TYPE", "Author");
				if (oiForm.getAuthorName() != null && oiForm.getAuthorName().length() > 0)
					request.setAttribute("TYPE_VALUE", oiForm.getAuthorName());
			}
			else if (strAction.equals(OIBlogConstants.ACTION_ARCHIVES_MONTH))
			{
				//System.out.println("OIBlogArchivesAction: doIt - Month : "+oiForm.getMonth());
				oiBABlogArchives.setRowId("1");
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY);
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
				oiBABlogArchives.setMonth(oiForm.getMonth());
				request.setAttribute("TYPE", "Date");
				if (oiForm.getMonth() != null && oiForm.getMonth().length() > 0)
					request.setAttribute("TYPE_VALUE", oiForm.getMonth());
			}
			else if (strAction.equals(OIBlogConstants.ACTION_ARCHIVES_TAGS))
			{
				//System.out.println("OIBlogArchivesAction: doIt - tag : "+oiForm.getTagId());
				oiBABlogArchives.setRowId("1");
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY);
				removeSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
				oiBABlogArchives.setTagId(new Integer(oiForm.getTagId()));
				request.setAttribute("TYPE", "Tag");
				if (oiForm.getTagName() != null && oiForm.getTagName().length() > 0)
				request.setAttribute("TYPE_VALUE", oiForm.getTagName());
			}
			
			String blogArchivesCountQuery = (String)getSessionAttribute(request,OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
			//System.out.println("OIBlogArchivesAction: doIt - blogArchivesCountQuery : "+blogArchivesCountQuery);
			oiBABlogArchives.setTotalCountQuery(blogArchivesCountQuery);
			
			String blogArchivesQuery = (String)getSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY);
			//System.out.println("OIBlogArchivesAction: doIt - blogArchivesQuery : "+blogArchivesQuery);
			oiBABlogArchives.setArchivesQuery(blogArchivesQuery);
			
			objResponseObject = oiBOBlog.getArchivesPosts(oiBABlogArchives);
			//System.out.println("OIBlogArchivesAction: doIt - objResponseObject : "+objResponseObject);
			if (objResponseObject!=null)
			{
				OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) objResponseObject.getResponseObject(OIBlogConstants.K_aOIPageInfoBean);
				if (aOIPageInfoBean!=null)
				{
			        ArrayList arPage = new ArrayList();
			        int start = aOIPageInfoBean.getCurrLinkStart();
			        for (int i=start;i<start + aOIPageInfoBean.getNoOfLinks();i++)
			        {
			            if (i<=aOIPageInfoBean.getNoOfPages())
			                arPage.add(i+"");
			        }
			        
			        if (aOIPageInfoBean.getNoOfPages()>=1){
			            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
			        }
			        else{
			            request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
			        }
			        request.setAttribute(OIBlogConstants.K_currentPage,aOIPageInfoBean.getPageNo() + "");
			        request.setAttribute(OIBlogConstants.K_pageNo,aOIPageInfoBean.getPageNo() + "");
			        request.setAttribute(OIBlogConstants.K_nextSet,aOIPageInfoBean.isNSet() + "");
			        request.setAttribute(OIBlogConstants.K_previousSet,aOIPageInfoBean.isPSet() + "");
			        request.setAttribute(OIBlogConstants.K_nextPage,aOIPageInfoBean.getNextSet() + "");
			        request.setAttribute(OIBlogConstants.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
			        request.setAttribute(OIBlogConstants.K_arPage,arPage);
				}
				
				blogArchivesCountQuery = (String)objResponseObject.getResponseObject(OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY);
				setSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_TOTALREC_QUERY, blogArchivesCountQuery);
				
				blogArchivesQuery = (String)objResponseObject.getResponseObject(OIBlogConstants.BLOG_ARCHIVES_QUERY);
				setSessionAttribute(request, OIBlogConstants.BLOG_ARCHIVES_QUERY, blogArchivesQuery);
			
				OIBABlogDetail oiBABlogDetail = (OIBABlogDetail)objResponseObject.getResponseObject(OIBlogConstants.BLOG_DETAIL);
				//System.out.println("OIBlogArchivesAction: doIt - oiBABlogDetail : "+oiBABlogDetail);
				//System.out.println("OIBlogArchivesAction: doIt - Authors : "+oiBABlogDetail.getAuthors());
				//System.out.println("OIBlogArchivesAction: doIt - Blogs : "+oiBABlogDetail.getBlogs());
				//System.out.println("OIBlogArchivesAction: doIt - Categories : "+oiBABlogDetail.getCategories());
				//System.out.println("OIBlogArchivesAction: doIt - Months : "+oiBABlogDetail.getMonths());
				request.setAttribute(OIBlogConstants.BLOG_DETAIL, oiBABlogDetail);
			}
			request.setAttribute("pageName", OIBlogConstants.ACTION_ARCHIVES);
			strForward = "success";			
			
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
