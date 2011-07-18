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
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionBlogAdminCreateEntry extends OIBaseAction
{

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response,String actionName)
	{
		String strForward = "";
		String strRedirect = "";
		OIBOBlogAdmin blogBO= new OIBOBlogAdmin();
		OIResponseObject objResponseObject = new OIResponseObject();
		
		OIBlogAdminCreateEntryForm oiBlogAdminCreateEntryForm = (OIBlogAdminCreateEntryForm)form;
		
		//System.out.println("OIActionBlogAdminCreateEntry: doIt - first : "+actionName);
		
		//modify by edmund
		try
		{
			String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			Integer blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
			
			if("create".equals(actionName))
			{						
				OIBABlogAdminCreateEntry blog = new OIBABlogAdminCreateEntry();
				OIBOAddTag tagBO = new OIBOAddTag();

				//System.out.println("OIActionBlogAdminCreateEntry: doIt - oiBlogAdminCreateEntryForm.getAcceptComments() : "+oiBlogAdminCreateEntryForm.getAcceptComments());
				if(oiBlogAdminCreateEntryForm.getAcceptComments()== null){
					oiBlogAdminCreateEntryForm.setAcceptComments("N");
				}else
				{
					oiBlogAdminCreateEntryForm.setAcceptComments("Y");
				}
				
				PropertyUtils.copyProperties(blog, oiBlogAdminCreateEntryForm);
				//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+blog.getAcceptComments()+"act1");
				
				blog.setBlogId(blogId);

				if (!blogBO.checkEntryTitleExists(blog))
				{
					objResponseObject = blogBO.insertAdminBlogNewEntry(blog, strUserId, blogId);
					Integer entryId = (Integer) objResponseObject.getResponseObject("entryId");
					//ArrayList alTagNames = (ArrayList) getSessionAttribute(request, "alTagNames");
					
					String error = (String) objResponseObject.getResponseObject("error");
					if(error != null){
						request.setAttribute("error", error);
						
						removeSessionAttribute(request, "alTagNames");
						removeSessionAttribute(request, "oiFormBlogTag");
						removeSessionAttribute(request, "entryForm");
						removeSessionAttribute(request, "pictureName");
						
						//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+"new entry create");
						OIBOBlogAdminCategory blogBOCategory = new OIBOBlogAdminCategory();
						objResponseObject = new OIResponseObject();
						objResponseObject = blogBOCategory.getCategories();
						ArrayList CategoryList = (ArrayList)objResponseObject.getResponseObject("categoriesList");
						
						request.setAttribute("categoriesList",CategoryList);
						request.setAttribute(OIBlogConstants.PAGE_NAME,"BlogAdminNewEntry");
						
						strForward = "BlogAdminNewEntry";
						return (mapping.findForward(strForward));
					}
					
					ArrayList alTagNames = convertToTagList(blog.getStrTagIdList());
					tagBO.updateTag(String.valueOf(entryId), alTagNames, "BG");
					
					strForward = "entries";
					actionName = "BlogAdminEntries";
					oiBlogAdminCreateEntryForm.setHiddenAction(actionName);
					//System.out.println("" + strForward + "  " + actionName);
					
					removeSessionAttribute(request, "alTagNames");
					removeSessionAttribute(request, "oiFormBlogTag");
					removeSessionAttribute(request, "tagList");
					request.setAttribute(OIBlogConstants.PAGE_NAME, "BlogAdminEntries");
					
					return (mapping.findForward(strForward));
				}
				else
				{
					request.setAttribute("error", "BLOG.INDIVIDUAL.ADMIN.ENTRIES.TITLE_EXISTS");
					
					removeSessionAttribute(request, "alTagNames");
					removeSessionAttribute(request, "oiFormBlogTag");
					removeSessionAttribute(request, "entryForm");
					removeSessionAttribute(request, "pictureName");
					
					//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+"new entry create");
					OIBOBlogAdminCategory blogBOCategory = new OIBOBlogAdminCategory();
					objResponseObject = new OIResponseObject();
					objResponseObject = blogBOCategory.getCategories();
					ArrayList CategoryList = (ArrayList)objResponseObject.getResponseObject("categoriesList");
					
					request.setAttribute("categoriesList",CategoryList);
					request.setAttribute(OIBlogConstants.PAGE_NAME,"BlogAdminNewEntry");
					
					strForward = "BlogAdminNewEntry";
				}
			}
			else if("update".equals(actionName))
			{						
				String strEntryId = request.getParameter("entryId");
				OIBABlogAdminCreateEntry blog = new OIBABlogAdminCreateEntry();
				OIBOAddTag tagBO = new OIBOAddTag();
				//System.out.println("OIActionBlogAdminCreateEntry: doIt - oiBlogAdminCreateEntryForm.getAcceptComments() : "+oiBlogAdminCreateEntryForm.getAcceptComments());
				if(oiBlogAdminCreateEntryForm.getAcceptComments()== null){
					oiBlogAdminCreateEntryForm.setAcceptComments("N");
				}else
				{
					oiBlogAdminCreateEntryForm.setAcceptComments("Y");
				}
				
				PropertyUtils.copyProperties(blog, oiBlogAdminCreateEntryForm);
				//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : " + blog.getAcceptComments() + "act1");
				
				blog.setBlogId(blogId);
				
				if (!blogBO.checkEntryTitleExists(blog))
				{
					objResponseObject = blogBO.updateAdminBlogNewEntry(blog, strUserId, Integer.parseInt(strEntryId), blogId);
					Integer entryId = (Integer) objResponseObject.getResponseObject("entryId");
					//ArrayList alTagNames = (ArrayList) getSessionAttribute(request, "alTagNames");
					
					String error = (String) objResponseObject.getResponseObject("error");
					if(error != null){
						request.setAttribute("error", error);
						
						request.setAttribute("Mode", "update");
						
						//removeSessionAttribute(request, "alTagNames");
						//removeSessionAttribute(request, "oiFormBlogTag");
						//removeSessionAttribute(request, "entryForm");
						//removeSessionAttribute(request, "pictureName");
						
						//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+"new entry create");
						OIBOBlogAdminCategory blogBOCategory = new OIBOBlogAdminCategory();
						objResponseObject = new OIResponseObject();
						objResponseObject = blogBOCategory.getCategories();
						ArrayList CategoryList = (ArrayList)objResponseObject.getResponseObject("categoriesList");
						
						request.setAttribute("categoriesList",CategoryList);
						request.setAttribute(OIBlogConstants.PAGE_NAME,"BlogAdminNewEntry");
						
						strForward = "BlogAdminNewEntry";
						return (mapping.findForward(strForward));
					}
					
					
					
					//blogBO.updateTag(entryId, alTagNames);
					ArrayList alTagNames = convertToTagList(blog.getStrTagIdList());
					tagBO.updateTag(String.valueOf(entryId), alTagNames, "BG");
					
					strForward = "entries";
					actionName = "BlogAdminEntries";
					oiBlogAdminCreateEntryForm.setHiddenAction(actionName);
					//System.out.println("" + strForward + "  " + actionName);
					
					removeSessionAttribute(request, "alTagNames");
					removeSessionAttribute(request, "oiFormBlogTag");
					removeSessionAttribute(request, "pictureName");
					removeSessionAttribute(request, "entryid");
					removeSessionAttribute(request, "tagList");
					request.setAttribute(OIBlogConstants.PAGE_NAME, "BlogAdminEntries");
					
					return (mapping.findForward(strForward));
				}
				else
				{
					request.setAttribute("error", "BLOG.INDIVIDUAL.ADMIN.ENTRIES.TITLE_EXISTS");
					
					request.setAttribute("Mode", "update");
					
					removeSessionAttribute(request, "alTagNames");
					removeSessionAttribute(request, "oiFormBlogTag");
					removeSessionAttribute(request, "entryForm");
					removeSessionAttribute(request, "pictureName");
					
					//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+"new entry create");
					OIBOBlogAdminCategory blogBOCategory = new OIBOBlogAdminCategory();
					objResponseObject = new OIResponseObject();
					objResponseObject = blogBOCategory.getCategories();
					ArrayList CategoryList = (ArrayList)objResponseObject.getResponseObject("categoriesList");
					
					request.setAttribute("categoriesList",CategoryList);
					request.setAttribute(OIBlogConstants.PAGE_NAME,"BlogAdminNewEntry");
					
					strForward = "BlogAdminNewEntry";
				}
			}
			else if ("removePhoto".equals(actionName)) 
			{
				String strEntryId = request.getParameter("entryId");
				String strPicName = request.getParameter("picFileName");
				
				//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+strEntryId+ "fg"+strPicName);
				OIBABlogAdminCreateEntry blog = new OIBABlogAdminCreateEntry();
        		PropertyUtils.copyProperties(blog, oiBlogAdminCreateEntryForm);
        		
        		objResponseObject = blogBO.removePhoto(strPicName,strEntryId);
        		removeSessionAttribute(request, "pictureName");
        		
        		strRedirect = "/BlogAdminEntries.do?hiddenAction=EditEntryAction&entryId=" + strEntryId;;
        		
        	}
			else
			{
				//added by edmund to get the category list
				if("Edit".equals(request.getParameter("Mode")))
				{
					if(getSessionAttribute(request, "FromCreateTag")==null)
					{
						request.setAttribute("Mode", "Edit");
						request.setAttribute("Mode", "update");
					}
				}
				else
				{
					removeSessionAttribute(request, "alTagNames");
					removeSessionAttribute(request, "oiFormBlogTag");
					removeSessionAttribute(request, "entryForm");
					removeSessionAttribute(request, "pictureName");
				}
				
				//System.out.println("OIActionBlogAdminCreateEntry: doIt - var : "+"new entry create");
				OIBOBlogAdminCategory blogBOCategory = new OIBOBlogAdminCategory();
				objResponseObject = new OIResponseObject();
				objResponseObject = blogBOCategory.getCategories();
				ArrayList CategoryList = (ArrayList)objResponseObject.getResponseObject("categoriesList");
				
				request.setAttribute("categoriesList",CategoryList);
				request.setAttribute(OIBlogConstants.PAGE_NAME,"BlogAdminNewEntry");
				
				request.setAttribute("OIBlogAdminCreateEntryForm", oiBlogAdminCreateEntryForm);
				
				strForward = "BlogAdminNewEntry";
			}
			
			// Other authors for Blog Admin RHS menu
			ArrayList arList = (new OIBOBlogAdmin()).getOtherBlogAuthors(blogId, strUserId);
			if (arList != null && arList.size() > 0)
				request.setAttribute("OTHER_AUTHORS", arList);
			// End RHS menu
		}
		catch (Exception e)
		{
			e.printStackTrace();
			strRedirect = "/error.do?error=OIDEFAULT";
		}		
		finally
		{
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
	        	request.setAttribute("error", objResponseObject.getResponseObject("error"));			
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
	
	private ArrayList convertToTagList(String strTagIdList){
		ArrayList tags = new ArrayList();
		String [] tagIds = strTagIdList.split(",");
		for(int i=0; i<tagIds.length; i++)
		{
			tags.add(tagIds[i]);
		}
		
		return tags;
	}
	
}
