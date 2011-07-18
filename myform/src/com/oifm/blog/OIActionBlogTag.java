package com.oifm.blog;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;

public class OIActionBlogTag extends OIBaseAction {

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,String actionName)
	{
		String strForward = "";
		String strRedirect = "";
		OIBOBlogAdmin blogBO= new OIBOBlogAdmin();
		OIResponseObject objResponseObject = new OIResponseObject();
		OIResponseObject objResponseObject2 = new OIResponseObject();
		OIBABlogTag objBA = new OIBABlogTag();
		OIPageInfoBean aOIPageInfoBean = null;
		int noOfCount = 15;
		int rowId = 0;
		ArrayList allTags = null;
		
		OIFormBlogTag oiFormBlogTag = (OIFormBlogTag)form;

		//System.out.println("OIActionBlogTag: doIt - actionName : "+actionName);
		try
		{
			if("newTags".equals(actionName)){
				//System.out.println("OIActionBlogTag: newTags - var : "+oiFormBlogTag.getTagName());
				PropertyUtils.copyProperties(objBA, oiFormBlogTag);
				if (objBA.getTagName().trim().length() > 0)
					objResponseObject2 = blogBO.saveNewTag(objBA.getTagName());
				strForward = "BlogEntry";
			}
			else if("selected".equals(actionName))
			{
				//System.out.println("OIActionBlogTag: doIt - Action blog tag : "+actionName);
				String [] aTags = oiFormBlogTag.getTagArrays();
				ArrayList alTagNames = new ArrayList();
				if(aTags != null){
					
					ArrayList alTagList = (ArrayList)getSessionAttribute(request, "tagList");
					
					for (int i = 0; i < aTags.length; i++)
					{
						
						for (Iterator iter = alTagList.iterator(); iter.hasNext();)
						{
							OIBABlogTag tag = (OIBABlogTag) iter.next();
							
							if((String.valueOf(tag.getTagId())).equals(aTags[i]))
							{
								alTagNames.add(tag);
							}							
						}	
					}
					
				}
				removeSessionAttribute(request, "alTagNames");
				removeSessionAttribute(request, "oiFormBlogTag");
				removeSessionAttribute(request, "tagList");
				setSessionAttribute(request, "alTagNames",alTagNames);
				setSessionAttribute(request, "oiFormBlogTag",form);
				
			}
			else if ("addTags".equals(actionName) || "updateTags".equals(actionName) || "renewTags".equals(actionName)) 
			{
				//String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
				objResponseObject2 = new OIResponseObject();
				objResponseObject2 = blogBO.getTags();

				ArrayList tagList = (ArrayList)objResponseObject2.getResponseObject("tagList");
				oiFormBlogTag = new OIFormBlogTag();
				int count = 0;

				if(getSessionAttribute(request, "alTagNames")!=null)
				{
					ArrayList alTagNames = (ArrayList)getSessionAttribute(request, "alTagNames");
					String [] arTag = new String[alTagNames.size()];
					//System.out.println("OIActionBlogTag: doIt - alTagNames : "+alTagNames.size());
					if(alTagNames != null)
					{
						for (Iterator iter = alTagNames.iterator(); iter.hasNext();)
						{			
							OIBABlogTag tag = (OIBABlogTag) iter.next();
							arTag[count++] = String.valueOf(tag.getTagId());								
						}	
					
						oiFormBlogTag.setTagArrays(arTag);
					}
				}
				
				if(!"renewTags".equals(actionName))
				{
					removeSessionAttribute(request, "FromCreateTag");
					
					if(request.getParameter("create")!=null){
						setSessionAttribute(request, "FromCreateTag", "yes");
					}
				}
					


				request.setAttribute("OIFormBlogTag", oiFormBlogTag);
				setSessionAttribute(request, "tagList",tagList);
				strForward = "BlogTags";
        	}
			else if ("tagList".equals(actionName))
			{				
				allTags = (ArrayList) getSessionAttribute(request, "AllTags");
				// //System.out.println("OICreateBlogAction: doIt - allUsers from session : "+allUsers);

				aOIPageInfoBean = (OIPageInfoBean) getSessionAttribute(request, OIBlogConstants.K_aOIPageInfoBean);
				
				if (allTags == null)
				{
					objResponseObject = blogBO.getTags(rowId, noOfCount);					
				}
				strForward = "BlogTags";
			}
			else if ("pagination".equals(actionName))
			{				
				objResponseObject = blogBO.getTags(rowId, noOfCount);				
				strForward = "BlogTags";
			}
			
			if (objResponseObject != null)
			{				
				allTags = (ArrayList) objResponseObject.getResponseObject("AllTags");
				// //System.out.println("OICreateBlogAction: doIt - allUsers from DB : "+allUsers);
				setSessionAttribute(request, "AllTags", allTags);				
				
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
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
			strRedirect = "/error.do?error=OIDEFAULT";
		}		
		finally
		{
			if(!strForward.equals("") && objResponseObject2.getResponseObject("error") != null && !objResponseObject2.getResponseObject("error").equals("null") ) 
	        	request.setAttribute("error", objResponseObject2.getResponseObject("error"));			
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
