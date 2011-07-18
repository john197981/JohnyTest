/******************************************************************************
* Title 		: OIActionCodeMaster.java
* Description 	: This action class is to populate the code master data
* Author		: Suresh Kuamr.R 
* Version No 	: 1.0
* Date Created 	: 23 - Jul -2005
* Copyright 	: Scandent Group
******************************************************************************/

package com.oifm.codemaster;

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
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OISurveyConstants;
import com.oifm.useradmin.admin.OIAdminConstants;
import com.oifm.utility.OIFormUtilities;

public class OIActionCodeMaster extends OIBaseAction {
	
	Logger logger = Logger.getLogger(this.getClass().getName());
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction){
      	
    	logger.debug(OILabelConstants.BEGIN_METHOD + "OIActionCodeMaster");    	
    	String strForward = "CodeMaster";    	
    	OIBACodeMaster objBV = null ;
    	OIBACodeMaster objBVs[] = null;
    	OIFormCodeMaster objform = null; 	
    	HttpSession session = request.getSession(true);
    	
    	String strUserId = "";
    	String strDivCode = "";
    	String strRedirect = "";
    	ArrayList alFunctions = null;
    	boolean isAdmin = false;
    	boolean isCodeMaintain = false;
    	
    	
    	try{
    		session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isAdmin = (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) );
			isCodeMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_CODES);
//			isSurveyPublish = alFunctions.contains(OIFunctionConstants.PUBLISH_SURVEY);
			
			
			if(isCodeMaintain){
				
				objBV =  new OIBACodeMaster();
	    		objform = (OIFormCodeMaster)form;
	    		String strHidAction = OIFormUtilities.chkIsNull(objform.getHiddenAction());
	    		if(strHidAction.equals(OIAdminConstants.SEARCH)){
	    				//hidData(objform);
	    	  	}		
	    		PropertyUtils.copyProperties(objBV, objform);
	    		if(!strHidAction.equals(OIAdminConstants.SEARCH) && session.getAttribute(OILabelConstants.QUERY)!=null){
	      			objBV.setQuery(session.getAttribute(OILabelConstants.QUERY).toString());
	    		}
	    		OIResponseObject aOIResponseObject = new OIBOCodeMaster().processCdeMaster(objBV);
	    		 /** Check for the Existenece of Key in response object **/
		         if(aOIResponseObject != null && aOIResponseObject.containsKey(OILabelConstants.OBJARBV)){
						ArrayList alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OILabelConstants.OBJARBV);
						objBVs = new OIBACodeMaster[alUsrLst.size()];
						objBVs = (OIBACodeMaster[]) alUsrLst.toArray(objBVs);
						/** setting the Array of VOAs in request Object **/ 				
						request.setAttribute(OILabelConstants.OBJARBV,objBVs);
		         }		
	    		PropertyUtils.copyProperties(objform, objBV);
	    		chkKey( aOIResponseObject,request,OICodeMasterSqls.CODETYPE);  		
	    		if(strHidAction.equals(OIAdminConstants.SEARCH)){
	    			session.setAttribute(OILabelConstants.QUERY,objBV.getQuery());
	    			session.setAttribute(OILabelConstants.OBJFORM,objform);
	    		}else if(strHidAction.length()==0){
	    			session.removeAttribute(OILabelConstants.QUERY);
	    		}else if(strHidAction.equals(OIAdminConstants.UPDATE)){
	    			request.setAttribute("CodeMasterForm",session.getAttribute(OILabelConstants.OBJFORM));
	    		}	
	    		
			}else{
				System.out.println("OIActionCodeMaster-doIt no access");
				strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
			}
			
    	}catch(Exception ex){
			logger.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
			strForward= "/error.do";
			return new ActionForward(strForward);
		}finally{				
			/** Releasing all objects **/ 	
			objBV = null;
		}	
    	request.setAttribute(OILoginConstants.PAGENAME,"CodeMaster");
    	logger.debug(OILabelConstants.END_METHOD + "OIActionCodeMaster");
//   Added for Session Expiry 	
//		return mapping.findForward(strForward);
//		
		if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
    }

    	
   	/**
	 * This is the helper method to check dropdown key available in responseobject 
	 * @param aOIResponseObject
	 * @param strKey
	 * @param request
	 */

	private void chkKey(OIResponseObject aOIResponseObject,HttpServletRequest request,String strKey){
		if(aOIResponseObject.containsKey(strKey)){
		 	request.setAttribute(strKey,aOIResponseObject.getResponseObject(strKey));
		}
	}	  
    
	/**
	 * This method converts the hidden data textboxes
	 * @param objform
	 */
	 private void hidData(OIFormCodeMaster objform){
       	objform.setHidType(objform.getType());
		objform.setHidCode(objform.getValue());
		objform.setHidDesc(objform.getDescription());
		if(objform.getObsolete()==null){
			objform.setHidObs(OILabelConstants.FLAG_N);
		}else{
			objform.setHidObs(OILabelConstants.FLAG_Y);
		}
		
    }
	 /**
	  * 
	  * @param objform
	  * @param objBV
	  */
	 private void hidDataToForm(OIFormCodeMaster objform,OIBACodeMaster objBV){
		objform.setType(objform.getHidType());
		objform.setValue(objform.getHidCode());
		objform.setDescription(objform.getHidDesc());
		if(objform.getHidObs()==null)
		objform.setObsolete(objform.getHidObs());
    }
}
