/*****************************************************************************
* Title 		: OIActionHome.java
* Description 	: This is the Action class for home page used to display the poll.   
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 01 - Aug -2005
* Copyright 	: Scandent Group
********************************************************************************/
package com.oifm.home;

import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.blog.OIBABlogAdminRecentEntries;
import com.oifm.blog.OIBABlogConfig;
import com.oifm.common.OIBAAddTag;
import com.oifm.common.OIBAConfiguration;
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.poll.OIBAPoll;
import com.oifm.poll.OIBVPoll;
import com.oifm.poll.OIFormPoll;
import com.oifm.utility.OIFormUtilities;

/**
 * @author Sureshkumar
 *
 * This is Home class to construct the home page.
 */
public class OIActionHome extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionHome.class.getName());
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		logger.debug(OILabelConstants.BEGIN_METHOD + "OIActionHome");
	 	String strForward = OIHomeConstants.POPULATE;
	 	OIBVHome objBV = null;
	 	OIBOHome oIBO = null;
	 	OIBOAddTag tagBO = new OIBOAddTag();
	 	OIResponseObject OIResponseObject= null;
	 	String tags = "";
	 	HttpSession session = request.getSession(false);
	 	try 
	 	{
	 	    objBV = new OIBVHome();
	 		OIFormHome objForm = (OIFormHome)form;
	 		
	 		setFormTOBV(objForm,objBV);
	 		if(session.getAttribute(OIHomeConstants.POLLVOTE)!=null){
	 			objBV.setPollVoted(session.getAttribute(OIHomeConstants.POLLVOTE).toString());
	 		}else{
	 			objBV.setPollVoted(OIHomeConstants.FLAG_N);
	 		}
	 		if(objBV.getPollVoted().equals(OIHomeConstants.FLAG_Y)){
	 			request.setAttribute(OIHomeConstants.ALREADYVOTED,OIHomeConstants.ALREADYVOTED);
	 		}
	 		/**Call to the BO's process method**/
	 		String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
		    OIResponseObject aOIResponseObject = new OIBOHome().processHome(userId,objBV);
	 		setResponse(aOIResponseObject,request,objForm);
	 		//objForm.setTags(OIFormUtilities.chkIsNull(objBV.getStrTag()));
	 		objForm.setPubId(OIFormUtilities.chkIsNull(objBV.getPubId()));
	 		objForm.setPublished(OIFormUtilities.chkIsNull(objBV.getPublished()));
	 		/** Check for the Poll Info. **/
	 		if(OIFormUtilities.chkIsNull(objForm.getHiddenAction()).equalsIgnoreCase(OIHomeConstants.POLL))
	 		{
	 		    if(OIFormUtilities.chkIsNull(objForm.getShowRes()).length()==0)
	 		    {
	 		        strForward =OIHomeConstants.THANKS;		 				
	 			}
	 		    else
	 		    {
	 		        request.setAttribute(OIHomeConstants.PAGENAME,OIHomeConstants.PUBLISH);
	 				strForward =OIHomeConstants.PUBLISH;
	 			}
	 		}
	 		else if(OIFormUtilities.chkIsNull(objForm.getHiddenAction()).equalsIgnoreCase(OIHomeConstants.PUBLISH))
	 		{
	 		    request.setAttribute(OIHomeConstants.PAGENAME,OIHomeConstants.PUBLISH);
				strForward =OIHomeConstants.PUBLISH;
			}
	 		
	 		
	 		if(OIFormUtilities.chkIsNull(objForm.getHiddenAction()).equalsIgnoreCase("saveTag"))
			{
				/*OIBVHome oIBVHome = new OIBVHome();
				OIBOHome oIBOHome = new OIBOHome();
				oIBVHome.setStrTag(objForm.getTags());
				
				//PropertyUtils.copyProperties(oIBVHome,objForm);
				//aOIResponseObject = oIBOHome.updateTag(userId, oIBVHome);
 		        //request.setAttribute(OIHomeConstants.PAGENAME,OIHomeConstants.POPULATE);
 				//strForward =OIHomeConstants.POPULATE;
				*/
	 			
	 			
	 			ArrayList alTagNames = (ArrayList) getSessionAttribute(request, "alTagNames");
	 			tagBO.updateTag(userId, alTagNames);
	 			
	 			removeSessionAttribute(request, "alTagNames");
				removeSessionAttribute(request, "oiFormBlogTag");
			}
	 		
	 		if(!"Home".equalsIgnoreCase(request.getParameter("Mode"))){
	 			OIResponseObject = tagBO.getTags(userId);
				ArrayList tagList = (ArrayList)OIResponseObject.getResponseObject("tagList");
				if(tagList != null){
					setSessionAttribute(request, "alTagNames",tagList);
					setSessionAttribute(request,"alTagSize", new Integer(tagList.size()));
				}
				
				for (Iterator iter = tagList.iterator(); iter.hasNext();)
				{
					OIBAAddTag tag = (OIBAAddTag) iter.next();
					tags += tag.getTagName() + ",";						
				}
	 		}
	 		else{
	 			ArrayList tagList = (ArrayList)getSessionAttribute(request, "alTagNames");
	 			for (Iterator iter = tagList.iterator(); iter.hasNext();)
				{
					OIBAAddTag tag = (OIBAAddTag) iter.next();
					tags += tag.getTagName() + ",";						
				}
	 		}
	 		
			
			//objForm.setTags(OIFormUtilities.chkIsNull(objBV.getStrTag()));
			objForm.setTags(OIFormUtilities.chkIsNull(tags));
			
			OIBAConfiguration aOIBAConfiguration = (OIBAConfiguration) aOIResponseObject.getResponseObject("aOIBAConfiguration");
	 		OIBAConfiguration aOISiteConfiguration = (OIBAConfiguration) aOIResponseObject.getResponseObject("aOISiteConfiguration");
			ArrayList arOIBVHottestThread = (ArrayList) aOIResponseObject.getResponseObject("arOIBVHottestThread");
			ArrayList arOIBVLatestThread = (ArrayList) aOIResponseObject.getResponseObject("arOIBVLatestThread");
			ArrayList arOIBVPaper = (ArrayList) aOIResponseObject.getResponseObject("arOIBVPaper");
			ArrayList arOIBASurvey = (ArrayList) aOIResponseObject.getResponseObject("arOIBASurvey");
			ArrayList arASMLetter = (ArrayList) aOIResponseObject.getResponseObject("arASMLetter");
			//Added to include Latest Blog display in home page
			ArrayList arOIBlog = (ArrayList)aOIResponseObject.getResponseObject("arOIBlog");
			
			
			OIBAConfiguration aHotTopic = (OIBAConfiguration) aOIResponseObject.getResponseObject("aHotTopic");
			OIBAConfiguration aLatTopic = (OIBAConfiguration) aOIResponseObject.getResponseObject("aLatTopic");
			
			request.setAttribute("bannerCnt",(String)aOIResponseObject.getResponseObject("bannerCnt"));
			
			request.setAttribute("arOIBASurvey",arOIBASurvey);
			request.setAttribute("arOIBVPaper",arOIBVPaper);
			request.setAttribute("aOIBAConfiguration",aOIBAConfiguration);
			request.setAttribute("aOISiteConfiguration",aOISiteConfiguration);
			request.setAttribute("arOIBVHottestThread",arOIBVHottestThread);
			request.setAttribute("arOIBVLatestThread",arOIBVLatestThread);
			request.setAttribute("arASMLetter",arASMLetter);
			
			request.setAttribute("aHotTopic",aHotTopic);
			request.setAttribute("aLatTopic",aLatTopic);
			request.setAttribute("arOIBlog",arOIBlog);
			request.setAttribute("tagWords",(String)aOIResponseObject.getResponseObject("tagWords"));
			session.setAttribute(OIHomeConstants.POLLVOTE,objBV.getPollVoted());
			
	 	}catch(Exception ex)
	 	{
	 	    /**Catching the Exception and redirect to error page **/	
			logger.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
			strForward= OILabelConstants.ERROR_FORWARD;
			return new ActionForward(strForward);
		}
	 	finally
	 	{				
			/** Releasing all objects **/ 	
			objBV = null;	
		}
		logger.debug(OILabelConstants.END_METHOD + "OIActionHome");
		/** Setting page Attribute for the loading the menu in jsp **/
		logger.debug(OILabelConstants.END_METHOD + strForward);
		return mapping.findForward(strForward);
	}//doit ends here
	 	
	 
	 /**
	  * 
	  * @param aOIResponseObject
	  * @param request
	  * @param objForm
	  */
	 
	private void setResponse(OIResponseObject aOIResponseObject,HttpServletRequest request,OIFormHome objForm)
	{
	    ArrayList alUsrLst = null;
	 	OIFormPoll objFormPoll = new OIFormPoll(); 
	    if(aOIResponseObject.containsKey(OIHomeConstants.POLLVIEW))
	    {
	    	alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OIHomeConstants.POLLVIEW);
	    	if(alUsrLst!= null && alUsrLst.size()>0)
	    	{
		    	OIBVPoll objBV = (OIBVPoll) alUsrLst.get(0);
		    	objForm.setPollId(OIFormUtilities.chkIsNull(objBV.getPollId()));
		    	objForm.setShowRes(OIFormUtilities.chkIsNull(objBV.getShowRes()));
		    	objForm.setMultiple(OIFormUtilities.chkIsNull(objBV.getMutAns()));
		    	objForm.setPubId(OIFormUtilities.chkIsNull(objBV.getPubId()));
		    	objForm.setPubTitle(OIFormUtilities.chkIsNull(objBV.getTitle()));
		    	logger.info(" OIFormUtilities.chkIsNull(objBV.getTitle()) "+OIFormUtilities.chkIsNull(objBV.getTitle()));
		    	logger.info(" objForm.getPubTitle "+objForm.getPubTitle());
				request.setAttribute(OIHomeConstants.POLLVIEW,alUsrLst);
	    	}	
	    }
	    if(aOIResponseObject.containsKey(OILabelConstants.OBJARBV))
	    {
	    	 alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OILabelConstants.OBJARBV);
				logger.info(" OILabelConstants.OBJARBV "+OILabelConstants.OBJARBV);
				logger.info(" alUsrLst "+alUsrLst);
	    	 OIBAPoll objBAPolls[] = new OIBAPoll[alUsrLst.size()];
			 objBAPolls = (OIBAPoll[]) alUsrLst.toArray(objBAPolls);
			/** setting the Array of VOAs in request Object **/ 				
			request.setAttribute(OILabelConstants.OBJARBV,objBAPolls);
	    } 
	    if(aOIResponseObject.containsKey(OIHomeConstants.BV))
	    {
	    	OIBVPoll objBVPoll = (OIBVPoll)  aOIResponseObject.getResponseObject(OIHomeConstants.BV);
	    	objFormPoll.setTitle(OIFormUtilities.chkIsNull(objBVPoll.getTitle()));
	    	objFormPoll.setStartDt(OIFormUtilities.chkIsNull(objBVPoll.getExpDt()));
	    	objFormPoll.setQuestion(OIFormUtilities.chkIsNull(objBVPoll.getQuestion()));
		  	objFormPoll.setAnswer1(OIFormUtilities.chkIsNull(objBVPoll.getAnswer1()));
			objFormPoll.setAnswer2(OIFormUtilities.chkIsNull(objBVPoll.getAnswer2()));
		  	objFormPoll.setAnswer3(OIFormUtilities.chkIsNull(objBVPoll.getAnswer3()));
		  	objFormPoll.setAnswer4(OIFormUtilities.chkIsNull(objBVPoll.getAnswer4()));
		  	objFormPoll.setAnswer5(OIFormUtilities.chkIsNull(objBVPoll.getAnswer5()));
		  	objFormPoll.setRes1(OIFormUtilities.chkIsNull(objBVPoll.getRes1()));
		  	objFormPoll.setRes2(OIFormUtilities.chkIsNull(objBVPoll.getRes2()));
		  	objFormPoll.setRes3(OIFormUtilities.chkIsNull(objBVPoll.getRes3()));
		  	objFormPoll.setRes4(OIFormUtilities.chkIsNull(objBVPoll.getRes4()));
		  	objFormPoll.setRes5(OIFormUtilities.chkIsNull(objBVPoll.getRes5()));
		  	objFormPoll.setTotal(OIFormUtilities.replaceToZero(objBVPoll.getTotal()));
		  	objFormPoll.setImgPer(OIFormUtilities.chkIsNull(objBVPoll.getImgPer())); 
		  	objFormPoll.setImgSize(OIFormUtilities.chkIsNull(objBVPoll.getImgSize()));
		  	objFormPoll.setPubTitle(OIFormUtilities.chkIsNull(objBVPoll.getPubTitle()));
		  	objFormPoll.setPubId(OIFormUtilities.chkIsNull(objBVPoll.getPubId()));
		  	objFormPoll.setHiddenAction(objForm.getHiddenAction());
	    	request.setAttribute(OIHomeConstants.POLLFORM,objFormPoll);
	    }
	 }  

	/**
	  * 
	  * @param objForm
	  * @param objBV
	  */
	private void setFormTOBV(OIFormHome objForm,OIBVHome objBV)
	{
	    objBV.setPollId(OIFormUtilities.chkIsNull(objForm.getPollId()));
		objBV.setStrRes1(OIFormUtilities.chkIsNull(objForm.getRes1()));
		objBV.setStrRes2(OIFormUtilities.chkIsNull(objForm.getRes2()));
		objBV.setStrRes3(OIFormUtilities.chkIsNull(objForm.getRes3()));
		objBV.setStrRes4(OIFormUtilities.chkIsNull(objForm.getRes4()));
		objBV.setStrRes5(OIFormUtilities.chkIsNull(objForm.getRes5()));
		objBV.setResponse(OIFormUtilities.chkIsNull(objForm.getResponse()));
		objBV.setShowRes(OIFormUtilities.chkIsNull(objForm.getShowRes()));
		objBV.setMultiple(OIFormUtilities.chkIsNull(objForm.getMultiple()));
		objBV.setAction(OIFormUtilities.chkIsNull(objForm.getHiddenAction()));
	}
}
