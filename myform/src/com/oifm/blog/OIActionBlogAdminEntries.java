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
import com.oifm.common.OIBAAddTag;
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionBlogAdminEntries extends OIBaseAction
{
	private static Logger logger = Logger.getLogger(OIActionBlog.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response,
			String actionName)
	{

		String strAction = (actionName != null) ? actionName :"BlogAdminEntries";
		//System.out.println("strAction->"+strAction);
		String strForward = "";
		String strRedirect = "";
		OIBOBlogAdmin objBOBlog  = new OIBOBlogAdmin();
		OIResponseObject objResponseObject = new OIResponseObject();
		OIResponseObject objResponseObject2 = new OIResponseObject();
		OIBlogAdminEntriesForm oiBlogAdminEntriesForm = new OIBlogAdminEntriesForm();
		
		OIResponseObject aOIResponseObject= null;
    	OIBOAddTag tagBO = new OIBOAddTag();
    	String tags = "";
    	String tagIds = "";
		
		try {
			
			String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			Integer blogId = (Integer)getSessionAttribute(request, OIBlogConstants.SELECTED_BLOG_ID);
			
			if(strAction.equalsIgnoreCase("BlogAdminEntries"))
			{
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				request.setAttribute("pageName", "BlogAdminEntries");
				removeSessionAttribute(request, "tagList");
				removeSessionAttribute(request, "alTagNames");
				removeSessionAttribute(request, "oiFormBlogTag");
				strForward = "BlogAdminEntry";
				
			}
			else if(strAction.equalsIgnoreCase("EditEntryAction"))
			{
				int entryId = Integer.parseInt(request.getParameter("entryId"));
				
		    	
				removeSessionAttribute(request, "alTagNames");
				removeSessionAttribute(request, "oiFormBlogTag");
				
				//System.out.println("OIActionBlogAdminEntries: doIt - entryId : "+entryId);
				
				if (entryId!=0)
				{
					//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"ACT 1");
					OIBOBlogAdminCategory blogBOCategory = new OIBOBlogAdminCategory();
					OIBABlogAdminCreateEntry oiBABlog = new OIBABlogAdminCreateEntry();
					objResponseObject = objBOBlog.getEntry(new Integer(entryId), blogId);
					
		
					aOIResponseObject = tagBO.getTags(String.valueOf(entryId),"BG");
					ArrayList tagList = (ArrayList)aOIResponseObject.getResponseObject("tagList");
					if(tagList != null){
						for (Iterator iter = tagList.iterator(); iter.hasNext();)
						{
							OIBAAddTag tag = (OIBAAddTag) iter.next();
							tags += tag.getTagName() + ",";
							tagIds += tag.getTagId() + ",";
						}
						setSessionAttribute(request, "alTagNames",tagList);
						//System.out.println("OIActionBlogAdminEntries: doIt - tags : "+tags);
					}
					

					oiBABlog = (OIBABlogAdminCreateEntry)objResponseObject.getResponseObject("ENTRY_DATA");
					
					if (oiBABlog!=null)
					{			

						OIBlogAdminCreateEntryForm oiBlogAdminCreateEntryForm = new OIBlogAdminCreateEntryForm();
						oiBlogAdminCreateEntryForm.setEntryId(entryId);
						oiBlogAdminCreateEntryForm.setEntryTitle(oiBABlog.getEntryTitle());
						oiBlogAdminCreateEntryForm.setCategory(oiBABlog.getCategory().intValue());
						//oiBlogAdminCreateEntryForm.setExpiryDate(oiBABlog.getExpiryDate());
						oiBlogAdminCreateEntryForm.setEntryBody(oiBABlog.getEntryBody());
						oiBlogAdminCreateEntryForm.setEntryExcept(oiBABlog.getEntryExcept());
						oiBlogAdminCreateEntryForm.setTags(tags);
						oiBlogAdminCreateEntryForm.setStrTagIdList(tagIds);
						oiBlogAdminCreateEntryForm.setStatus(oiBABlog.getStatus());
						oiBlogAdminCreateEntryForm.setAcceptComments(oiBABlog.getAcceptComments());

						//System.out.println("OIActionBlogAdminEntries: doIt - oiBABlog.getEntryExcept() : "+oiBABlog.getEntryExcept());

						objResponseObject = blogBOCategory.getCategories();
						ArrayList CategoryList = (ArrayList)objResponseObject.getResponseObject("categoriesList");
						
						/*objResponseObject = objBOBlog.getTags(entryId);
						ArrayList tagList = (ArrayList)objResponseObject.getResponseObject("tagList");
						if(tagList != null){
							setSessionAttribute(request, "alTagNames",tagList);
						}*/
						
						request.setAttribute("categoriesList",CategoryList);
						//request.setAttribute("entryForm",oiBlogAdminCreateEntryForm);
						
						removeSessionAttribute(request, "pictureName");
						removeSessionAttribute(request, "entryid");
						
						setSessionAttribute(request, "pictureName",oiBABlog.getPicFileName());
						setSessionAttribute(request, "entryid",String.valueOf(entryId));
						setSessionAttribute(request, "entryForm",oiBlogAdminCreateEntryForm);

						request.setAttribute("Mode", "update");
						request.setAttribute(OIBlogConstants.PAGE_NAME,"BlogAdminEditEntry");
						
						strForward = "BlogAdminEntry";
						
						//System.out.println("OIActionBlogAdminEntries: doIt - entry : "+"ACT 1");
					}
					strForward = "BlogAdminEntry";
				}
			}
			else if(strAction.equalsIgnoreCase("delete"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"Delete 1");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				
				
				OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				if (objForm.getEntriesArray() != null)
				{
					//System.out.println("OIActionBlogAdminEntries: doIt - var : " + objForm.getEntriesArray().length);
					objBOBlog.deleteEntry(blogId, objForm.getEntriesArray());
				}
				
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				
				request.setAttribute("pageName", "BlogAdminEntries");
				strForward = "BlogAdminEntry";
			}
			else if(strAction.equalsIgnoreCase("ManageDelete"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"Delete 1");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				OICreateBlogForm oiCreateBlogForm = (OICreateBlogForm)getSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				
				OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				if (objForm.getEntriesArray() != null)
				{
					//System.out.println("OIActionBlogAdminEntries: doIt - var : " + objForm.getEntriesArray().length);
					objBOBlog.deleteEntry(blogId, objForm.getEntriesArray());
				}
				
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				
				request.setAttribute("pageName", "BlogAdminEntries");
				request.setAttribute("blogId",blogId);
				strForward = "ManageAdminEntry";
			}
			else if(strAction.equalsIgnoreCase("comment"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"comment 1");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				//OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				objResponseObject2 = objBOBlog.getAllComments(blogId);
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				//request.setAttribute("commentList",objResponseObject2.getResponseObject("commentsList"));
				
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				ArrayList alComList = (ArrayList)objResponseObject2.getResponseObject("commentsList");
				if(alComList.size()>0)
					request.setAttribute("commentList", alComList);
				
				request.setAttribute("pageName", "BlogAdminEntriesComment");
				
				strForward = "BlogAdminEntry";
			}
			else if(strAction.equalsIgnoreCase("deleteComment"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"Delete Comment1");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"act1");
				OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				if (objForm.getCommentArray() != null)
				{
					//System.out.println("OIActionBlogAdminEntries: doIt - var : " + objForm.getCommentArray().length);
					objBOBlog.deleteComment(blogId, objForm.getCommentArray());
				}
				
				objResponseObject2 = objBOBlog.getAllComments(blogId);
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				//request.setAttribute("commentList",objResponseObject2.getResponseObject("commentsList"));
				
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				ArrayList alComList = (ArrayList)objResponseObject2.getResponseObject("commentsList");
				if(alComList.size()>0)
					request.setAttribute("commentList", alComList);
				
				request.setAttribute("pageName", "BlogAdminEntriesComment");
				request.setAttribute("blogId",blogId);
				strForward = "BlogAdminEntry";
			}
			else if(strAction.equalsIgnoreCase("manageBlog"))
			{
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				OICreateBlogForm oiCreateBlogForm = (OICreateBlogForm)getSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				//System.out.println("OIActionBlogAdminEntries: doIt - manageBlog : "+blogId);
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				
				if(objResponseObject!=null)
				{
					ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
					if (alList!=null)
					{
						if(alList.size()>0)
							request.setAttribute("entriesList",alList);
					}					
				}
				
				
				request.setAttribute("blogId",blogId);
				request.setAttribute("pageName", "BlogAdminEntries");
				strForward = "ManageAdminEntry";
				
			}
			else if(strAction.equalsIgnoreCase("manageComment"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"Manage comment 1");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				OICreateBlogForm oiCreateBlogForm = (OICreateBlogForm)getSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				objResponseObject2 = objBOBlog.getAllComments(blogId);
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				//request.setAttribute("commentList",objResponseObject2.getResponseObject("commentsList"));
				
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				ArrayList alComList = (ArrayList)objResponseObject2.getResponseObject("commentsList");
				if(alComList.size()>0)
					request.setAttribute("commentList", alComList);
				
				request.setAttribute("pageName", "BlogAdminEntriesComment");
				request.setAttribute("blogId",blogId);
				
				strForward = "ManageCommentEntry";
			}
			else if(strAction.equalsIgnoreCase("manageDeleteComment"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"Delete Comment1");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				OICreateBlogForm oiCreateBlogForm = (OICreateBlogForm)getSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"act1");
				OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				if (objForm.getCommentArray() != null)
				{
					//System.out.println("OIActionBlogAdminEntries: doIt - var : " + objForm.getCommentArray().length);
					objBOBlog.deleteComment(blogId, objForm.getCommentArray());
				}

				objResponseObject2 = objBOBlog.getAllComments(blogId);
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				//request.setAttribute("commentList",objResponseObject2.getResponseObject("commentsList"));
				
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				ArrayList alComList = (ArrayList)objResponseObject2.getResponseObject("commentsList");
				if(alComList.size()>0)
					request.setAttribute("commentList", alComList);
				
				request.setAttribute("pageName", "BlogAdminEntriesComment");
				request.setAttribute("blogId",blogId);
				strForward = "ManageCommentEntry";
			}
			else if(strAction.equalsIgnoreCase("setFeature"))
			{
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+"set feature");
				OIBABlogAdminEntries blog = new OIBABlogAdminEntries();
				OICreateBlogForm oiCreateBlogForm = (OICreateBlogForm)getSessionAttribute(request, OIBlogConstants.FORM_CREATE_BLOG);
				
				PropertyUtils.copyProperties(blog, oiBlogAdminEntriesForm);
				
				OIBlogAdminEntriesForm objForm = (OIBlogAdminEntriesForm)form;
				
				//System.out.println("OIActionBlogAdminEntries: doIt - var : "+objForm.getEntriesArray().length);
				if(request.getParameter("set_mode").equals("s"))
				{
					objBOBlog.setFeature(blogId,objForm.getEntriesArray(),"Y");
				}
				else
				{
					objBOBlog.setFeature(blogId,objForm.getEntriesArray(),"N");
				}
				
				objResponseObject = objBOBlog.getBlogEntriesList(blogId);
				//request.setAttribute("entriesList",objResponseObject.getResponseObject("entriesList"));
				ArrayList alList = (ArrayList)objResponseObject.getResponseObject("entriesList");
				if(alList.size()>0)
					request.setAttribute("entriesList",alList);
				
				
				request.setAttribute("pageName", "BlogAdminEntries");
				request.setAttribute("blogId",blogId);
				strForward = "ManageAdminEntry";
			}
			
			// Other authors for Blog Admin RHS menu
			ArrayList arList = (new OIBOBlogAdmin()).getOtherBlogAuthors(blogId, strUserId);
			if (arList != null && arList.size() > 0)
				request.setAttribute("OTHER_AUTHORS", arList);
			// End RHS menu
			
			
			} catch (Exception e) {
				e.printStackTrace();
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
