
package com.oifm.forum;

import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIActionHelper;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionThread extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionThread.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)	{
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
		String strRoleId = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	OIBOThread objBOThread = new OIBOThread();
    	OIFormThread objThread = (OIFormThread)form;
    	OIBAThread objThreadVo = new OIBAThread();
    	OIResponseObject objResponseObject = null;
    	OIForumAccessInfo objAccessInfo = null;
    	strAction = (strAction != null)? strAction: OIForumConstants.POSTING_LIST;
    	boolean canCreatePrivate = false;
    	boolean isThreadUserBoard = false;	
    	try {
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strRoleId = (String)getSessionAttribute(request, OILoginConstants.ROLE_ID);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			canCreatePrivate = (alFunctions != null && alFunctions.contains(OIFunctionConstants.CREATE_PRIVATE_THREAD))?true:false;
			if (objThread==null)
				objThread = new OIFormThread();
	    	objThread.setStrUserId(strUserId);
			objAccessInfo = new OIForumAccessInfo(alFunctions);
			if(strAction.equals(OIForumConstants.POSTING_LIST)){
				logger.info(objThread.getStrThreadId());
				if (objThread.getStrThreadId()==null || objThread.getStrThreadId().trim().equals(""))
				{
					if (request.getParameter("strThreadId")!=null)
						objThread.setStrThreadId((String) request.getParameter("strThreadId"));
				}
				String strSortKey = request.getParameter("hidSortKey");
				String strSortOrder = request.getParameter("hidOrder");
				if(strSortKey ==null ||!(strSortKey.equals("posting"))){
					strSortOrder = "ASC";
				}
				logger.info(" hidOrder "+strSortOrder);
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.getThreadPostingList(objThreadVo,strSortOrder);
				objThreadVo = (OIBAThread)objResponseObject.getResponseObject("ThreadInfo");
				objThread.setStrTopicId(objThreadVo.getStrTopicId());
				request.setAttribute("isThreadUserBoard", objResponseObject.getResponseObject("isSameFlag"));
				request.setAttribute("ThreadInfo", objResponseObject.getResponseObject("ThreadInfo"));
				request.setAttribute("TotalRec", objResponseObject.getResponseObject("TotalRec"));
				request.setAttribute("PostsList", objResponseObject.getResponseObject("PostsList"));
				request.setAttribute("CatBoardTopic", objResponseObject.getResponseObject("CatBoardTopic"));
				request.setAttribute("PageBean", objResponseObject.getResponseObject("PageBean"));
				objAccessInfo.setSameBoard(((Boolean)objResponseObject.getResponseObject("isSameFlag")).booleanValue());
				objAccessInfo.setAllowPM(((Boolean)objResponseObject.getResponseObject("isAllowPM")).booleanValue());
				objAccessInfo.setAllowAM(((Boolean)objResponseObject.getResponseObject("isAllowAM")).booleanValue());
				request.setAttribute("pageName", "ThreadPostsList");
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strForward = OIForumConstants.USER_PAGE;
				objThread.setHidOrder(strSortOrder);
				request.setAttribute("hidOrder", strSortOrder);

		    	String strSplThreadID = (String) objResponseObject.getResponseObject("strSplThreadID");
		    	request.setAttribute("strSplThreadID",strSplThreadID);
		    	/*		 Added by K.K.Kumaresan on Sep 16,2009 for the direct link issue
		    	String directLink=request.getParameter("directLink");
		    	if(directLink!=null && directLink.equals("Y"))
		    	{
		    		// Control comes here only from the direct link
		    		logger.info(" Comes from the direct link ");
			    	boolean validUser=objBOThread.isValidUser(objThreadVo); // If the user is valid,it returns true.
			    	logger.info(" validUser "+validUser);
			    	if(!validUser)
						strRedirect = OIForumConstants.ERROR_DO+"?error=OIDEFAULT";
					else strForward = OIForumConstants.USER_PAGE;
		    	}
		    	else
		    	{
		    		logger.info(" Not from the direct link ");
		    	}*/

			} else if(strAction.equals(OIForumConstants.GOTO_TOPIC_HRCY) || strAction.equals(OIForumConstants.MOVETO_TOPIC_HRCY)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.getCategoryBoardTopicHrcy(objThreadVo);
				request.setAttribute("TopicHrcy", objResponseObject.getResponseObject("TopicHrcy"));

				if(strAction.equals(OIForumConstants.MOVETO_TOPIC_HRCY))
					objThread.setHiddenAction(OIForumConstants.MOVE_THREAD_TO_TOPIC);
				else	objThread.setHiddenAction(OIForumConstants.GOTO_TOPIC);
				request.setAttribute("pageName", "TopicHrcy");

				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.GOTO_TOPIC)){
					strRedirect = OIForumConstants.THREAD_LIST_DO+"?strTopicId="+objThread.getStrTopicId();

			} else if(strAction.equals(OIForumConstants.MOVE_THREAD_TO_TOPIC)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.moveThreadToAnotherTopic(objThreadVo);
				request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				request.setAttribute("pageName", "StatusPage");
				strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.THREAD_CREATE)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.createThread(objThreadVo);
				request.setAttribute("GroupsList", objResponseObject.getResponseObject("GroupsList"));
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
					request.setAttribute("pageName", "Thread");
				else {
					request.setAttribute("pageName", "StatusPage");
					request.setAttribute("refreshUrl", OIForumConstants.THREAD_LIST_DO+"?strTopicId="+objThreadVo.getStrTopicId());
				}
				strForward = OIForumConstants.POPUP_PAGE;
				
			} else if(strAction.equals(OIForumConstants.THREAD_EDIT)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.getThreadGroupsInfo(objThreadVo);
				objThreadVo = (OIBAThread)objResponseObject.getResponseObject("objThreadVo");
				PropertyUtils.copyProperties(objThread, objThreadVo);
				request.setAttribute("GroupsList", objResponseObject.getResponseObject("GroupsList"));
				request.setAttribute("ThreadGroups", objResponseObject.getResponseObject("ThreadGroups"));
				request.setAttribute("objThreadVo", objThreadVo);
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				if(objResponseObject.getResponseObject("error") == null)
					request.setAttribute("pageName", "Thread");
				else {
					request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
					request.setAttribute("pageName", "StatusPage");
				}
				strForward = OIForumConstants.POPUP_PAGE;
				
			} else if(strAction.equals(OIForumConstants.THREAD_MODERATION_EDIT) ){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.getThreadModInfo(objThreadVo);
				objThreadVo = (OIBAThread)objResponseObject.getResponseObject("objThreadVo");
				PropertyUtils.copyProperties(objThread, objThreadVo);
				request.setAttribute("objThreadVo", objThreadVo);
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				if(objResponseObject.getResponseObject("error") == null)
					request.setAttribute("pageName", "ModThread");
				else {
					request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
					request.setAttribute("pageName", "StatusPage");
				}
				strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.THREAD_MODIFY)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.modifyThread(objThreadVo, canCreatePrivate);
				request.setAttribute("GroupsList", objResponseObject.getResponseObject("GroupsList"));
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
					request.setAttribute("pageName", "Thread");
				else {
					request.setAttribute("pageName", "StatusPage");
					if(strAction.equals(OIForumConstants.THREAD_MODIFY))
						request.setAttribute("refreshUrl", OIForumConstants.THREAD_MAINTAIN_DO+"?strThreadId="+objThreadVo.getStrThreadId());
					else if(strAction.equals(OIForumConstants.THREAD_MOD_MODIFY))
						request.setAttribute("refreshUrl", OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.THREAD_MOD_LISTING);
				}
				strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.THREAD_MOD_MODIFY)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.modifyModarateThread(objThreadVo);
				request.setAttribute("ExpressionsList", objResponseObject.getResponseObject("ExpressionsList"));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
					request.setAttribute("pageName", "ModThread");
				else {
					request.setAttribute("pageName", "StatusPage");
					request.setAttribute("refreshUrl", OIForumConstants.THREAD_POST_MOD_DO+"?hiddenAction="+OIForumConstants.THREAD_MOD_LISTING);
				}
				strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.THREAD_DELETE)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.deleteThread(objThreadVo);
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OIForumConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strRedirect = OIForumConstants.THREAD_LIST_DO+"?strTopicId="+objThreadVo.getStrTopicId()+"&hidSortKey=2";

			} else if(strAction.equals(OIForumConstants.THREAD_STICKY)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				//anbu added to separate logic for admin sticky thread
				//if(OIUtilities.replaceNull(strRoleId).equalsIgnoreCase("ADMIN")){
					//objResponseObject  = objBOThread.setStickyThread(objThreadVo); objBOThread.setAdminStickyThread(objThreadVo);
				//}else{
					objResponseObject = objBOThread.setStickyThread(objThreadVo);	 
				//}
				request.setAttribute("pageName", "StatusPage");
				request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				strForward = OIForumConstants.POPUP_PAGE;
			} else if(strAction.equals(OIForumConstants.THREAD_ADMIN_STICKY)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				//anbu added to separate logic for admin sticky thread
				objResponseObject  =  objBOThread.setAdminStickyThread(objThreadVo);
 				request.setAttribute("pageName", "StatusPage");
				request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.SPL_STICKY_THREAD)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				//anbu added to separate logic for admin sticky thread
				objResponseObject  =  objBOThread.setSplStickyThread(objThreadVo);
 				request.setAttribute("pageName", "StatusPage");
				request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				strForward = OIForumConstants.POPUP_PAGE;

			} else if(strAction.equals(OIForumConstants.THREAD_LOCK) || strAction.equals(OIForumConstants.THREAD_UNLOCK)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.lockUnlockThread(objThreadVo);
				strRedirect = OIForumConstants.THREAD_MAINTAIN_DO+"?hiddenAction="+OIForumConstants.POSTING_LIST;

			} else if(strAction.equals(OIForumConstants.THREAD_TRACK)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.setBookMarkThread(objThreadVo);
				request.setAttribute("pageName", "StatusPage");
				request.setAttribute("error", objResponseObject.getResponseObject(OILoginConstants.K_ERROR));
				request.setAttribute("message", objResponseObject.getResponseObject(OILoginConstants.K_MESSAGE));
				strForward = OIForumConstants.POPUP_PAGE;
				
			} else if(strAction.equals(OIForumConstants.THREAD_PRINT)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.getPrintThreadPostingList(objThreadVo);
				request.setAttribute("ThreadInfo", objResponseObject.getResponseObject("ThreadInfo"));
				request.setAttribute("TotalRec", objResponseObject.getResponseObject("TotalRec"));
				request.setAttribute("PostsList", objResponseObject.getResponseObject("PostsList"));
				request.setAttribute("pageName", "PrintThread");
				strForward = OIForumConstants.POPUP_PAGE;
				
			} else if(strAction.equals(OIForumConstants.THREAD_EXPORT)){
				PropertyUtils.copyProperties(objThreadVo, objThread);
				objResponseObject = objBOThread.getExportThreadPostingsList(objThreadVo);
				String strThreadExportInfo = (String)objResponseObject.getResponseObject("ThreadExportInfo"); 
				(new OIActionHelper()).writeContentToFile(response, strThreadExportInfo, "ThreadPostingsList_"+objThread.getStrThreadId()+".XLS");
				
				if(objResponseObject.getResponseObject("error") != null) {
					request.setAttribute("pageName", "StatusPage");
					strForward = OIForumConstants.POPUP_PAGE;
				}
				
				/* Added/Modified by Aik Mun @ Jan 16, 2009 */
			} else if(strAction.equals(OIForumConstants.MARK_AS_READ) || strAction.equals(OIForumConstants.MARK_AS_UNREAD)){
				String strMarkStatus = "Fail";
				if(request.getParameter("markId")!=null && request.getParameter("markThredId")!=null){
					
					String strThreadId = request.getParameter("markThredId");					
					StringBuffer sbMarkId = new StringBuffer(request.getParameter("markId"));
					
					if(strAction.equals(OIForumConstants.MARK_AS_READ))
						objResponseObject = objBOThread.markReadStatus(sbMarkId.toString(),"R",strThreadId,strUserId);
					else
						objResponseObject = objBOThread.markReadStatus(sbMarkId.toString(),"U",strThreadId,strUserId);
					
					strMarkStatus = (String)objResponseObject.getResponseObject("MarkStatus");
				}
				
				try{
			        response.setContentType("text/html");
			        PrintWriter out = response.getWriter();
			        out.print(strMarkStatus.toString()+strAction);
			        out.flush();
			        }catch(Exception e){
			        	e.printStackTrace();
			        }
			        return null;
								
			}
			else strRedirect = OIForumConstants.ERROR_DO+"?error=NoAccess";
		} catch(Exception e) {
			logger.info(e.getMessage());
	    	strRedirect = OIForumConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally {
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
			request.setAttribute("createPrivateThread", new Boolean(canCreatePrivate));
			request.setAttribute("accessInfo", objAccessInfo);
		}

		if(strAction.equals(OIForumConstants.THREAD_EXPORT) && objResponseObject.getResponseObject("error") == null)
			return null;
		else if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}	
 
}
