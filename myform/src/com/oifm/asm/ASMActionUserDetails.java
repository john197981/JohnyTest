/*********************************ASMActionUserDetails.java******************* 
 * Title 		: ASMActionUserDetails
 * Description 	: This class is the Action Class for ASM User List. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 21 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;
 

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIPageInfoBean;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.utility.OIUtilities;

public class ASMActionUserDetails extends OIBaseAction 
{
	private static Logger objLogger = Logger.getLogger(ASMActionUserDetails.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{	
		
		ASMBACommon objBA=null;
		ASMBOCommon objBO=null;
		OIUtilities objUtility =null;
		
		try {
			ASMFormCommon objForm = (ASMFormCommon) form;
			//Create the instance for BO and VO and rkUtility class
			objBO = new ASMBOCommon();
			objBA = new ASMBACommon();
			objUtility =new OIUtilities();
			//Set the Form to VO using PropertyUtils class
			PropertyUtils.copyProperties(objBA, objForm);
			//Call the ASM Home page BO class to get the details of User List
			OIResponseObject aOIResponseObject = null;
			if(OIUtilities.replaceNull(actionName).equalsIgnoreCase("DivisionUserList")){
				aOIResponseObject = objBO.getUserDetails(objBA);
				request.setAttribute("DivisionList",objBA.getAlDivision());
			//}else{
			}else if(OIUtilities.replaceNull(actionName).equalsIgnoreCase("SeniorManagementUserList")){			
				
				aOIResponseObject = objBO.getSnrMgmtUserList(objBA);
			
			}else{			
				aOIResponseObject = objBO.getUserList(objBA);
			}
			PropertyUtils.copyProperties(objForm, objBA);
			
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
			
		}
		catch(Exception e)
		{
			objLogger.info("Reply redirect action Edit Error ================");
		}
		finally{
			objBO=null;
			objUtility =null;
		}
		
		request.setAttribute("TotRec",""+objBA.getTotRecLtr());
		request.setAttribute("UserList",objBA.getObjBV());
		request.setAttribute("hiddenAction",actionName);
		request.setAttribute("cboDivison",objBA.getCboDivision());
		
		
		return mapping.findForward(actionName);
	}
}
