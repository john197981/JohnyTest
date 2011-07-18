/*******************************************************************************************
* Title 		: OIActionThreadList 
* Description 	: This class is the action Class for Listing the Threads in discussion forum
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 09 Aug 2005
* Copyright 	: Scandent Group
*******************************************************************************************/

package com.oifm.forum;

import java.util.ArrayList;
import org.apache.commons.beanutils.PropertyUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIFormUtilities;
import com.oifm.forum.OIForumAccessInfo;
import com.oifm.forum.OIForumConstants;
import com.oifm.forum.admin.OIBOModThreadPost;




public class OIActionThreadList extends OIBaseAction{
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{
		/** Logger Declaration **/		
		logger.info(OILabelConstants.BEGIN_METHOD + "OIActionThreadList");
		String strForward ="ThreadList";
		String strAction = null;
		HttpSession session = request.getSession(false);		
    	OIBOModThreadPost objBOModThreadPost = new OIBOModThreadPost();
    	OIResponseObject objResponseObject = null;
    	strAction = (strAction != null)? strAction: OIForumConstants.POSTS_MODERATION;
    	OIForumAccessInfo objAccessInfo = null;
    	
	    try
	    {
	    	
	    	String strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
	    	ArrayList alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
	    	objAccessInfo = new OIForumAccessInfo(alFunctions);
	    	objResponseObject = objBOModThreadPost.getModerateThreadPostsList(strUserId, true, objAccessInfo.isMainAdmin());
	        OIFormThread objForm = (OIFormThread) form;
	    	OIBAThread objThread = new OIBAThread();
	    	OIBAThread objThreads[] = null;
	    		    		
	    	/**Call to the BO's process method**/
    	 	if (request.getParameter("pageNo")==null)
    	 	{
    	 	    objThread.setRowId("1");
    		}
    	 	else
    	 	{
    	 	    objThread.setRowId(OIFormUtilities.chkIsNull(request.getParameter("pageNo")));
    			if(objThread.getRowId().length()==0)
    			{
    			    objThread.setRowId("0");
    			}
    	 	}	
	    	/** Form to BA **/
	    	objThread.setHidOrder(OIFormUtilities.chkIsNull(objForm.getHidOrder()));
	    	objThread.setHidSortKey(OIFormUtilities.chkIsNull(objForm.getHidSortKey()));
			objThread.setStrTopicId(OIFormUtilities.chkIsNull(objForm.getStrTopicId()));
			objThread.setStrThreadId(OIFormUtilities.chkIsNull(objForm.getStrThreadId()));
			objThread.setStrHiddenAction(OIFormUtilities.chkIsNull(objForm.getHiddenAction()));
			objThread.setStrUserId(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.USER_ID).toString()));

	    	strAction =  OIFormUtilities.chkIsNull(objForm.getHiddenAction());
	    	if(strAction.length()==0 )
	    	{
	    	    objThread.setHidOrder(OILabelConstants.DESC);
	    		objThread.setHidSortKey("LASTPOST_ON");
	    	}

	    	/**Call to the BO's process method**/
	    	OIResponseObject aOIResponseObject = new OIBOThreadList().processThreads(objThread, strUserId, alFunctions);

	    	OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIPageInfoBean);
	    	
	    	String strSplThreadID = (String) aOIResponseObject.getResponseObject("strSplThreadID");
 	    	request.setAttribute("strSplThreadID",strSplThreadID);
	    	
		    if (aOIPageInfoBean!=null)
		    {
		        ArrayList arPage = new ArrayList();

		        int start = aOIPageInfoBean.getCurrLinkStart();
			    for (int i=start;i<start + aOIPageInfoBean.getNoOfLinks();i++)
			    {
			        if (i<=aOIPageInfoBean.getNoOfPages())
			        {
			            arPage.add(i+"");
			        }
			    }
			    
			    request.setAttribute("totalPage",aOIPageInfoBean.getNoOfPages() + "");
			    request.setAttribute(OIConsultConstant.K_currentPage,aOIPageInfoBean.getPageNo() + "");
			    request.setAttribute(OIConsultConstant.K_pageNo,aOIPageInfoBean.getPageNo() + "");
			    request.setAttribute(OIConsultConstant.K_nextSet,aOIPageInfoBean.isNSet() + "");
			    request.setAttribute(OIConsultConstant.K_previousSet,aOIPageInfoBean.isPSet() + "");
			    request.setAttribute(OIConsultConstant.K_nextPage,aOIPageInfoBean.getNextSet() + "");
			    request.setAttribute(OIConsultConstant.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
			    request.setAttribute(OIConsultConstant.K_arPage,arPage);				
		    }	    		
	    				    	
	    	/** Check for the Existenece of Key in response object **/
	        if(aOIResponseObject.containsKey(OILabelConstants.OBJARBV))
	        {
	            ArrayList alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OILabelConstants.OBJARBV);
				objThreads = new OIBAThread[alUsrLst.size()];
				objThreads = (OIBAThread[]) alUsrLst.toArray(objThreads);
				request.setAttribute(OILabelConstants.OBJARBV,objThreads);
	        }
	        else if(aOIResponseObject.containsKey(OILabelConstants.ERROR))
	        {
	            /** Setting error object in request object **/
		        request.setAttribute(OILabelConstants.ERROR,aOIResponseObject.getResponseObject(OILabelConstants.ERROR));
		        strForward = OILabelConstants.ERROR_FORWARD;
		        return new ActionForward(strForward);
	        }
			
	        /** BA to Form **/
	        objForm.setHidOrder(OIFormUtilities.chkIsNull(objThread.getHidOrder()));
	        objForm.setHidSortKey(OIFormUtilities.chkIsNull(objThread.getHidSortKey()));
	        objForm.setStrTopicId(OIFormUtilities.chkIsNull(objThread.getStrTopicId()));
	        objForm.setStrTopicName(OIFormUtilities.chkIsNull(objThread.getStrTopicName()));
	        objForm.setStrTopicDesc(OIFormUtilities.chkIsNull(objThread.getStrTopicDesc()));

	        if(strAction.equals("hits"))
	        {
	            strForward = OIForumConstants.THREAD_MAINTAIN_DO+OIForumConstants.THREADFWD+objThread.getStrThreadId();
	           	logger.info("***********8"+strForward);
	           	return new ActionForward(strForward);
	        }
	        
	        /* ADDED BY NANDHINI */
	        
	        OIBOThread objBOThread = new OIBOThread();
	        OIBAThread objThreadVo = new OIBAThread();
	        PropertyUtils.copyProperties(objThreadVo, objThread);
	        objResponseObject = objBOThread.getList(objThreadVo);			
	        objThreadVo.setStrThreadId(OIFormUtilities.chkIsNull(objForm.getStrThreadId()));
	        objThreadVo.setStrTopicId(OIFormUtilities.chkIsNull(objForm.getStrTopicId()));
	        logger.info("objForm.getStrThreadId()"+objForm.getStrThreadId());
	        logger.info("objForm.getStrTopicId()"+objForm.getStrTopicId());
			request.setAttribute("CatBoardTopic", objResponseObject.getResponseObject("CatBoardTopic"));
			logger.info(" objResponseObject.getResponseObject CatBoardTopic"+objResponseObject.getResponseObject("CatBoardTopic"));
	    }
	    catch(Exception ex)
	    {
	        /**Catching the Exception and redirect to error page **/	
			logger.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
			request.setAttribute(OILabelConstants.ERROR,ex.getMessage());
			strForward= OILabelConstants.ERROR_FORWARD;
			return new ActionForward(strForward);
		}
	    finally
	    {				
	        /** Releasing all objects **/ 	
		}
	    logger.info(OILabelConstants.END_METHOD + "OIActionThreadList");
		/** Setting page Attribute for the loading the menu in jsp **/
		request.setAttribute("pageName","ThreadsList");
		request.setAttribute("CatBoardTopic", objResponseObject.getResponseObject("CatBoardTopic"));				
		logger.info("@@@@@@@@@@@@@ "+objResponseObject.getResponseObject("CatBoardTopic"));
		logger.info(OILabelConstants.END_METHOD + strForward);
		return mapping.findForward(strForward);
	}//doit ends here
}