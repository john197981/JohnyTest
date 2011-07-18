/*********************************ASMActionReplyRedirect.java******************* 
 * Title 		: ASMActionReplyRedirect
 * Description 	: This class is the Action Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;
 

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
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIUtilities;

public class ASMActionReplyRedirect extends OIBaseAction 
{
	private static Logger objLogger = Logger.getLogger(ASMActionReplyRedirect.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{	
		
		ASMBVReplyRedirect objBV=null;
		ASMBAReplyRedirect objBA=null;
		ASMBOReplyRedirect objReplyRedirectBO=null;
		OIUtilities objUtility =null;
		
		HttpSession session = null;
		String strUserId = "";
    	String strDivCode = "";
    	String strRedirect = "";
    	ArrayList alFunctions = null;
    	boolean isASMReplyRedirect = false;
		
    	
    	try {
			
    		session = request.getSession();
    		alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
    		strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
    		isASMReplyRedirect = alFunctions.contains(OIFunctionConstants.ASM_REPS);
    		
    		
			//Create the instance for BO and VO and rkUtility class
			objReplyRedirectBO = new ASMBOReplyRedirect();
			objBV = new ASMBVReplyRedirect();
			objBA = new ASMBAReplyRedirect();
			
         if(isASMReplyRedirect) {
    		
        	ASMFormReplyRedirect objForm = (ASMFormReplyRedirect) form;
			objUtility =new OIUtilities();
			//If the page is coming from Reply redirect Edit page (ie when it is saved)
			
			PropertyUtils.copyProperties(objBA, objForm);
			//Call the ASM Home page BO class to get the details of announcement, letter and reply
			OIResponseObject aOIResponseObject = objReplyRedirectBO.process(objBA);
			//############################# Pagination Start############################
			
	        OIPageInfoBean aOIPageInfoBean = (OIPageInfoBean) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIPageInfoBean);
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
	        request.setAttribute(OIConsultConstant.K_currentPage,aOIPageInfoBean.getPageNo() + "");
	        request.setAttribute(OIConsultConstant.K_pageNo,aOIPageInfoBean.getPageNo() + "");
	        request.setAttribute(OIConsultConstant.K_nextSet,aOIPageInfoBean.isNSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousSet,aOIPageInfoBean.isPSet() + "");
	        request.setAttribute(OIConsultConstant.K_nextPage,aOIPageInfoBean.getNextSet() + "");
	        request.setAttribute(OIConsultConstant.K_previousPage,aOIPageInfoBean.getPrevSet() + "");
	        request.setAttribute(OIConsultConstant.K_arPage,arPage);
	        
	        //############################# Pagination End############################
		
		
    	}else {
         	strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
    	}
      }catch(Exception e)
		{
			objLogger.info("Error Occured"+e);
		}
		finally{
			objReplyRedirectBO=null;
			objUtility =null;
		}
		
		request.setAttribute("TotRec",""+objBA.getTotRecLtr());
		request.setAttribute("reply_redirect_letter",objBA.getObjBV());
		request.setAttribute("pageName","ASMReplyRedirect");
		
		if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
		return mapping.findForward(actionName);
	}
}
