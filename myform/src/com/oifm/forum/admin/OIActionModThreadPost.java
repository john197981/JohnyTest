package com.oifm.forum.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.forum.OIForumAccessInfo;
import com.oifm.forum.OIForumConstants;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OISurveyConstants;

public class OIActionModThreadPost extends OIBaseAction 
{
	private static Logger logger = Logger.getLogger(OIActionModThreadPost.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)
    {
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	OIBOModThreadPost objBOModThreadPost = new OIBOModThreadPost();
    	OIFormModeration objModeration = (OIFormModeration)form;
    	OIResponseObject objResponseObject = null;
    	strAction = (strAction != null)? strAction: OIForumConstants.POSTS_MODERATION;
    	OIForumAccessInfo objAccessInfo = null;
    	boolean isASMRep = false;
    	
    	try
    	{
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isASMRep = alFunctions.contains(OIFunctionConstants.ASM_REPS);
			if(isASMRep){
			logger.debug("After extract functions list");			
	    	objModeration.setStrUserId(strUserId);
	    	logger.debug("After set user id  :- strUserId = "+strUserId + "	strAction = "+strAction);
			objAccessInfo = new OIForumAccessInfo(alFunctions);

			if(strAction.equals(OIForumConstants.POSTS_MOD_LISTING))
			{
				objResponseObject = objBOModThreadPost.getModerateThreadPostsList(strUserId, false, objAccessInfo.isMainAdmin());
				request.setAttribute("TopicThreadPostsList", objResponseObject.getResponseObject("TopicThreadPostsList"));
				
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else 
				{
					request.setAttribute("pageName", "ModerationPage");
					strForward = OIForumConstants.ADMIN_PAGE;
				}
			} 
			else if(strAction.equals(OIForumConstants.THREAD_MOD_LISTING))
			{
			    logger.debug("Entered into thread moderation listing ");
				objResponseObject = objBOModThreadPost.getModerateThreadPostsList(strUserId, true, objAccessInfo.isMainAdmin());
				request.setAttribute("TopicThreadPostsList", objResponseObject.getResponseObject("TopicThreadPostsList"));
				logger.debug("After set the Topic Thread Posts List ");				
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else 
				{
					request.setAttribute("pageName", "ModerationPage");
					strForward = OIForumConstants.ADMIN_PAGE;
				}
			} 
			else if(strAction.equals(OIForumConstants.POSTS_MODERATION_EDIT))
			{
				objResponseObject = objBOModThreadPost.setPostingModeration(objModeration.getModStatus(), strUserId);
				request.setAttribute("strEditPostId", objModeration.getStrPostId());
				
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else 
				{
				    strRedirect = OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.POSTS_MOD_LISTING;
				}
			} 
			else if(strAction.equals(OIForumConstants.THREAD_MODERATION_EDIT))
			{
			    logger.debug("Entered into thread moderation edit screen");
				objResponseObject = objBOModThreadPost.setThreadModeration(objModeration.getModStatus(), strUserId);
				request.setAttribute("strEditThreadId", objModeration.getStrThreadId());
				logger.debug("After set the moderation edit thread Number ");
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else
				{
				    strRedirect = OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.THREAD_MOD_LISTING;
				}
			} 
			else if(strAction.equals(OIForumConstants.POSTS_MODERATION))
			{
				objResponseObject = objBOModThreadPost.setPostingModeration(objModeration.getModStatus(), strUserId);
				
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else
				{
				    strRedirect = OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.POSTS_MOD_LISTING;
				}
			} 
			else if(strAction.equals(OIForumConstants.THREAD_MODERATION))
			{
				objResponseObject = objBOModThreadPost.setThreadModeration(objModeration.getModStatus(), strUserId);
				
				if(objResponseObject.getResponseObject("error") != null)
				{
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}
				else 
				{
				    strRedirect = OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.THREAD_MOD_LISTING;
				}
			} 
			}else
			{
			    strRedirect = OIForumConstants.ERROR_DO+"?error=NoAccess";
			}
		
	} 
    	catch(Exception e) 
    	{
    	    logger.error(e);
	    	strRedirect = OIForumConstants.ERROR_DO+"?error=OIDEFAULT";
		} 
    	finally 
    	{
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") )
			{
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
			}
			//request.setAttribute("accessInfo", new OIForumAccessInfo(alFunctions));
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