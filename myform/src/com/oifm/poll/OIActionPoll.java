/*****************************************************************************
* Title 		: OIActionPoll.java
* Description 	: This class is the action Class for Sending Mail
* Author		: Suresh Kumar.R 
* Version No 	: 1.0
* Date Created 	: 04 - Jul -2005
* Copyright 	: Scandent Group
******************************************************************************/
package com.oifm.poll;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OISurveyConstants;
import com.oifm.utility.OIFormUtilities;


public class OIActionPoll extends OIBaseAction{
	
	Logger logger = Logger.getLogger(this.getClass().getName());

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, 
						HttpServletResponse response, String actionName){
		
		/** Logger Declaration **/		
		logger.debug(OILabelConstants.BEGIN_METHOD + "OIActionPoll");
		
		OIFormPoll objFormPoll = null;
		OIBOPoll objBOPoll = new OIBOPoll();
		OIBVPoll objBVPoll = new OIBVPoll();
		OIBVPoll objBVPolls[] = null;
		OIBAPoll objBAPolls[] = null;
		String strForward = OIPollConstants.POLLLIST;
		ActionErrors objErrors = new ActionErrors(); 
		ArrayList alUsrLst = null;
		// Added for Session 
		HttpSession session = null;
		String strUserId = "";
    	String strDivCode = "";
    	String strRedirect = "";
    	ArrayList alFunctions = null;
    	boolean isAdmin = false;
    	boolean isPollMaintain = false;
		
		
		try{
			//step 1 : get the session info
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isAdmin = (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) );
			
			//step 2 : check whether access right variable is in the alFunction arraylist
			isPollMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_POLL);
				
			for(int kk=0;kk<alFunctions.size();kk++){
				System.out.println("Function list alFunctions("+kk+")"+alFunctions.get(kk).toString());
			}
			//step 3 : If statement to determince have access right or not.
			if(isPollMaintain){
				
				objFormPoll = (OIFormPoll) form;
				/** Call the setBVToForm Method to store form values to BV. **/	
				setFormToBV(objFormPoll,objBVPoll );
				String strAction = OIFormUtilities.chkIsNull(objFormPoll.getHiddenAction());
			     
			   /**Call to the BO's process method**/
	           OIResponseObject aOIResponseObject = objBOPoll.processPoll(objBVPoll);
	           
	           /**Call the BV Method to set BV values to Form **/    
	           setBVToForm(objFormPoll,objBVPoll); 
	           
	           /** Check for the Existenece of Key in response object **/
	           if(aOIResponseObject.containsKey(OILabelConstants.OBJARBV)){
	           	
					 alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OILabelConstants.OBJARBV);
				
					if(strAction.equals(OIPollConstants.VIEWPOLL)||strAction.equals(OIPollConstants.EXPORT)||
							strAction.equals(OIPollConstants.PRINT)||Integer.parseInt(OIFormUtilities.replaceToZero(objBVPoll.getDtCnt()))>0){
						objBAPolls = new OIBAPoll[alUsrLst.size()];
						objBAPolls = (OIBAPoll[]) alUsrLst.toArray(objBAPolls);
						/** setting the Array of VOAs in request Object **/ 				
						request.setAttribute(OILabelConstants.OBJARBV,objBAPolls);
						objFormPoll.setRes1(objBVPoll.getRes1());
						objFormPoll.setRes2(objBVPoll.getRes2());
						objFormPoll.setRes3(objBVPoll.getRes3());
						objFormPoll.setRes4(objBVPoll.getRes4()); 
												
					}else{
						objBVPolls = new OIBVPoll[alUsrLst.size()];
						objBVPolls = (OIBVPoll[]) alUsrLst.toArray(objBVPolls);
						/** setting the Array of VOAs in request Object **/ 				
						request.setAttribute(OILabelConstants.OBJARBV,objBVPolls);
					}	
											
				}else if(aOIResponseObject.containsKey(OILabelConstants.ERROR)){
					/** Setting error object in request object **/
		        	request.setAttribute(OILabelConstants.ERROR,OILabelConstants.ERROR);
		        	strForward = OILabelConstants.ERROR_FORWARD;
		        	return new ActionForward(strForward);
		        }
			 	
			 	request.setAttribute(OIPollConstants.PAGENAME,"ListPoll");
				if(strAction.equals(OIPollConstants.VIEWPOLL)||strAction.equals(OIPollConstants.LOADPOLL)){
					request.setAttribute(OIPollConstants.PAGENAME,"CreatePoll");
				 }
				if(Integer.parseInt(OIFormUtilities.replaceToZero(objBVPoll.getDtCnt()))>0){
						
					request.setAttribute("Duplicate","Poll Already Exist");
					objFormPoll.setHiddenAction(OIPollConstants.VIEWPOLL);
					request.setAttribute(OIPollConstants.PAGENAME,"CreatePoll");
					if(strAction.equals(OIPollConstants.CREATEPOLL)){
						objFormPoll.setHiddenAction(OIPollConstants.LOADPOLL);
					}		
				}
				
				
				if(strAction.equals(OIPollConstants.EXPORT) ){
					request.setAttribute(OIPollConstants.PAGENAME,OIPollConstants.EXPORT);
					strForward ="print";
				}else if(strAction.equals(OIPollConstants.PRINT)){
					strForward ="print";	
				}
				
				
				logger.debug(request.getAttribute(OIPollConstants.PAGENAME));
			//step 4 : if user have no right, display error msg.
			}else{
				strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
			}
		}catch(Exception ex){
			/**Catching the Exception and redirect to error page **/	
			logger.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
			strForward= OILabelConstants.ERROR_FORWARD;
			return new ActionForward(strForward);
		}finally{				
			/** Releasing all objects **/ 	
				objFormPoll = null;
				objBOPoll = null;
				objBVPoll = null;
				
		}
			logger.debug(OILabelConstants.END_METHOD + "OIActionPoll");
			/** Setting page Attribute for the loading the menu in jsp **/
			logger.debug(OILabelConstants.END_METHOD + strForward);
			
			//step 5 :  if user have no right, forward to the error.do action
			//else forward to the respective jsp
			if(!strRedirect.equals("")) 
				return new ActionForward(strRedirect);
			else
				return mapping.findForward(strForward);
		}//doit ends here
		

	
	/**
	 * This is the helper method to store the form details to Business View Object.
	 * @param objFormPoll
	 * @param objBVPoll
	 */
	  private void setFormToBV(OIFormPoll objFormPoll,OIBVPoll objBVPoll ){
	  	
	  	objBVPoll.setTitle(OIFormUtilities.chkIsNull(objFormPoll.getTitle()));
	  	objBVPoll.setStartDt(OIFormUtilities.chkIsNull(objFormPoll.getStartDt()));
	  	objBVPoll.setExpDt(OIFormUtilities.chkIsNull(objFormPoll.getExpDt()));
	  	objBVPoll.setQuestion(OIFormUtilities.chkIsNull(objFormPoll.getQuestion()));
	  	objBVPoll.setAnswer1(OIFormUtilities.chkIsNull(objFormPoll.getAnswer1()));
		objBVPoll.setAnswer2(OIFormUtilities.chkIsNull(objFormPoll.getAnswer2()));
	  	objBVPoll.setAnswer3(OIFormUtilities.chkIsNull(objFormPoll.getAnswer3()));
	  	objBVPoll.setAnswer4(OIFormUtilities.chkIsNull(objFormPoll.getAnswer4()));
	  	objBVPoll.setAnswer5(OIFormUtilities.chkIsNull(objFormPoll.getAnswer5()));
	  	objBVPoll.setMutAns(OIFormUtilities.chkIsNull(objFormPoll.getMutAns()));
	  	objBVPoll.setPollId(OIFormUtilities.chkIsNull(objFormPoll.getPollId()));
	  	objBVPoll.setPublished(OIFormUtilities.chkIsNull(objFormPoll.getPublished()));
	  	objBVPoll.setShowRes(OIFormUtilities.chkIsNull(objFormPoll.getShowRes()));
	  	objBVPoll.setAction(OIFormUtilities.chkIsNull(objFormPoll.getHiddenAction()));
	  	
	  	
	  	
	  }
	  
	   
	  /**
	   * This is the helper method to set the Business View details to Form Object.
	   * @param OIFormUtilities.chkIsNull(objFormPoll
	   * @param objBVPoll
	   */
	  private void setBVToForm(OIFormPoll objFormPoll,OIBVPoll objBVPoll ){
	  	
	  	objFormPoll.setPollId(OIFormUtilities.chkIsNull(objBVPoll.getPollId()));
	  	objFormPoll.setTitle(OIFormUtilities.chkIsNull(objBVPoll.getTitle()));
	  	objFormPoll.setStartDt(OIFormUtilities.chkIsNull(objBVPoll.getStartDt()));
	  	objFormPoll.setExpDt(OIFormUtilities.chkIsNull(objBVPoll.getExpDt()));
	  	objFormPoll.setQuestion(OIFormUtilities.chkIsNull(objBVPoll.getQuestion()));
	  	objFormPoll.setAnswer1(OIFormUtilities.chkIsNull(objBVPoll.getAnswer1()));
		objFormPoll.setAnswer2(OIFormUtilities.chkIsNull(objBVPoll.getAnswer2()));
	  	objFormPoll.setAnswer3(OIFormUtilities.chkIsNull(objBVPoll.getAnswer3()));
	  	objFormPoll.setAnswer4(OIFormUtilities.chkIsNull(objBVPoll.getAnswer4()));
	  	objFormPoll.setAnswer5(OIFormUtilities.chkIsNull(objBVPoll.getAnswer5()));
	  	objFormPoll.setMutAns(OIFormUtilities.chkIsNull(objBVPoll.getMutAns()));
	  	objFormPoll.setPublished(OIFormUtilities.chkIsNull(objBVPoll.getPublished()));
	  	objFormPoll.setShowRes(OIFormUtilities.chkIsNull(objBVPoll.getShowRes()));
	  	objFormPoll.setHiddenAction(OIFormUtilities.chkIsNull(objBVPoll.getAction()));
	  	objFormPoll.setResId(OIFormUtilities.chkIsNull(objBVPoll.getResId()));
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
	  }
	   
	
	
	
	
	private void setResponse(OIFormPoll objFormPoll,OIBVPoll objBVPoll,HttpServletRequest request,ArrayList alUsrLst ){
		OIBAPoll objBAPolls[] = null;
		if(alUsrLst!=null){
			
		}	
	}	
	
	
	
	
	
	
	
	
	
}