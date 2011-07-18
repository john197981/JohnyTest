/******************************************************************************
* Title 		: OIActionSendMail 
* Description 	: This class is the action Class for Sending Mail
* Author		: NirmalKumar.S 
* Version No 	: 1.0
* Date Created 	: 04 - Jul -2005
* Copyright 	: Scandent Group
******************************************************************************/
package com.oifm.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;

public class OIActionSendMail extends OIBaseAction
{
	transient private final Logger LOGGER = Logger.getLogger(this.getClass().getName());

	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response, String actionName)
	{
	 	/** LOGGER Declaration **/
		LOGGER.debug(OILabelConstants.BEGIN_METHOD + "OIActionSendMail");
	    
		/** Objects Creation Form,BA,BV,BO **/
		String strForward = OILabelConstants.SENDMAIL;
		OIFormSendMail objOIFormSendMail = null;
	    OIBASendMail objOIBASendMail[] = null;
		OIBVSendMail objOIBVSendMail = null;
		OIBOSendMail objOIBOSendMail =null;
		HttpSession session = request.getSession(false);
		OIResponseObject aOIResponseObject = null;
		String strAction = null;	
		try	
		{
		    objOIFormSendMail =(OIFormSendMail)form;
			objOIBVSendMail = new OIBVSendMail(); 
			objOIBOSendMail = new OIBOSendMail();
			objOIBVSendMail.setURL(request.getContextPath());
			strAction = OIFormUtilities.chkIsNull(objOIFormSendMail.getHiddenAction());
				
			if (session.getAttribute(OILoginConstants.USER_ID)!=null)
			{					
			    objOIBVSendMail.setUserId(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.USER_ID).toString()));
			}
			 
			if (session.getAttribute(OILoginConstants.NICKNAME)!=null)
			{  
			 objOIBVSendMail.setUserId(OIFormUtilities.chkIsNull(session.getAttribute(OILoginConstants.NICKNAME).toString()));
			 //objOIBVSendMail.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
			 objOIBVSendMail.setFrom(OIDBRegistry.getValues("sendmailMyForum"));
			} 
			if (session.getAttribute(OILoginConstants.NICKNAME)==null){
				String name = session.getAttribute(OILoginConstants.USER_ID).toString();
				//System.out.println("OIActionSendMail-doIt1 " + name);
				String nickName =objOIBOSendMail.readNickname(name);
				//System.out.println("OIActionSendMail-doIt2 " + nickName);
				
				 request.setAttribute("NickName", nickName);
				 objOIBVSendMail.setUserId(OIFormUtilities.chkIsNull(nickName));
				 objOIBVSendMail.setFrom(OIDBRegistry.getValues("sendmailMyForum"));
				 
		       }
			
			else
			{
				//System.out.println("OIActionSendMail-doIt- else");
			    objOIBVSendMail.setFrom("");
			}
			
			/** Null Value Check against the Form Elements **/
			if (objOIFormSendMail.getId()==null || objOIFormSendMail.getId().equals(""))
			{
			    String strId=request.getParameter("Id");
				String strModule=request.getParameter("module");
				String strFlag=request.getParameter("flag");
				String strAct=request.getParameter("hiddenAction");
				LOGGER.info(strAct);
				objOIBVSendMail.setSendOrRemain(OIFormUtilities.chkIsNull(strFlag));
				objOIBVSendMail.setSurveyOrCons(OIFormUtilities.chkIsNull(strModule));
				objOIBVSendMail.setId(OIFormUtilities.chkIsNull(strId));
				objOIBVSendMail.setHiddenAction(OIFormUtilities.chkIsNull(strAct));
			}
			else
			{
				/** Copying Form Values to BV objects **/
				setFormToBV(objOIFormSendMail,objOIBVSendMail);
			}			
					
			/**Call to the BO's process method**/
			if(!strAction.equals(OILabelConstants.MAIL))
			{
			    if(OIFormUtilities.chkIsNull(objOIBVSendMail.getHiddenAction()).equals(OILabelConstants.SENDMAIL))
			    {
			        if (session.getAttribute(OILoginConstants.EMAIL)!=null)
			        {
			            objOIBVSendMail.setFrom(OIDBRegistry.getValues("sendmailMyForum"));
			        }
			    }
			    aOIResponseObject = new OIBOSendMail().readUserInformation(objOIBVSendMail);
				/**Call the BV Method to set BV values to Form **/    
			    setBVToForm(objOIFormSendMail,objOIBVSendMail);
			}
	           
			/** Check for the Existenece of Key in response object **/
	        if(aOIResponseObject != null && aOIResponseObject.containsKey(OILabelConstants.OBJARBV))
	        {
	            ArrayList alUsrLst = (ArrayList) aOIResponseObject.getResponseObject(OILabelConstants.OBJARBV);
				objOIBASendMail = new OIBASendMail[alUsrLst.size()];
				objOIBASendMail = (OIBASendMail[]) alUsrLst.toArray(objOIBASendMail);
				/** setting the Array of VOAs in request Object **/ 				
				request.setAttribute(OILabelConstants.OBJARBV,objOIBASendMail);
	        }
				
			/** Changing the Mapping forward to ConsulationPaper/Survery after sending Mail **/
	        if(strAction.equals(OILabelConstants.SENDMAIL))
	        {
	            request.setAttribute("pageName","message");
	    	}
	        else
	        {
	            request.setAttribute("pageName","SendMail");
	        }
	        if(strAction.equals(OILabelConstants.PAPERSURVEY))
	        {
	            strForward = OILabelConstants.SURVEYLIST;
	            if(OIFormUtilities.chkIsNull(objOIFormSendMail.getSurveyOrCons()).equals(OILabelConstants.CONSULT_PAPER))
	            {
	                strForward = OILabelConstants.PAPERLIST;
	           	}
	        }
	        else if(strAction.equals(OILabelConstants.PMMAIL)||strAction.equals(OILabelConstants.ADMINMAIL))
	        {
	            request.setAttribute("Mail","Success");
	           	strForward = "AdminMail";
	        }
	        else if (strAction.equals(OILabelConstants.MAIL))
	        {
	            strForward = "AdminMail";
	        }
	        if(strAction.equals(OILabelConstants.REMOVE) && objOIBVSendMail.getDelCnt()!=null)
	        {
	            request.setAttribute(OILabelConstants.REMOVE,objOIBVSendMail.getDelCnt());		
	        }
	        if(aOIResponseObject!= null )
	        {
	        	System.out.println("OIActionSendMail-doIt object not null");
	            if(aOIResponseObject.containsKey(OILabelConstants.VALIDIDS) &&  aOIResponseObject.containsKey(OILabelConstants.INVALIDIDS))
	            {
	                addErrors(aOIResponseObject,request,OILabelConstants.INVALIDIDS);
	      	  	    request.removeAttribute("Mail");
	            }
	            else if(aOIResponseObject.containsKey(OILabelConstants.INVALIDIDS))
	            {
	                request.setAttribute(OILabelConstants.INVALIDALONE,aOIResponseObject.getResponseObject(OILabelConstants.INVALIDIDS));
	                request.removeAttribute("Mail");
	            }
	            else if( aOIResponseObject.containsKey(OILabelConstants.NODOMAINS))
	            {
	                addErrors(aOIResponseObject,request,OILabelConstants.NODOMAINS);
		      	  	request.removeAttribute("Mail");
	            }
	            else if( aOIResponseObject.containsKey(OILabelConstants.MAILERROR))
	            {
	                addErrors(aOIResponseObject,request,OILabelConstants.MAILERROR);
		      	  	request.removeAttribute("Mail");
	            }	  
	        }	
	        repURL(objOIFormSendMail,objOIBVSendMail);
		}
		/*catch(SendFailedException mailex )
		{      
			//LOGGER.error(OILabelConstants.EXCEPTION_IN_ACTION,mailex);
			addErrors(aOIResponseObject,request,OILabelConstants.MAILERROR);
			if(strAction.equals(OILabelConstants.SENDMAIL))
			{
				request.setAttribute("pageName","message");
			}	
			request.setAttribute(OILabelConstants.MAILERROR,OILabelConstants.MAILERROR);
		}*/
		catch(Exception ex)
		{
			/**Catching the Exception and redirect to error page **/	
			//LOGGER.error(OILabelConstants.EXCEPTION_IN_ACTION,ex);
		    addErrors(aOIResponseObject,request,OILabelConstants.ERROR);
		    strForward = OILabelConstants.ERROR_FORWARD;
		    System.out.println("OIActionSendMail-doIt exception");
			if(strAction.equals(OILabelConstants.PMMAIL)||strAction.equals(OILabelConstants.ADMINMAIL))
			{
			    //addErrors(aOIResponseObject,request,OILabelConstants.MAILERROR);
				//strForward = "AdminMail";
			    request.setAttribute("TopFlag","true");
			}
			/*else
			{
			    strForward = OILabelConstants.ERROR_FORWARD;
				return new ActionForward(strForward);
			}*/	
			System.out.println("OIActionSendMail-doIt strForward="+strForward);
			return new ActionForward(strForward);
		}
		finally
		{				
			/** Releasing all objects **/ 	
		    objOIFormSendMail = null;
			objOIBASendMail = null;
			objOIBVSendMail = null;
			objOIBOSendMail =null;
		}
		
		LOGGER.debug(OILabelConstants.END_METHOD + "OIActionSendMail");
		/** Setting page Attribute for the loading the menu in jsp **/
			
		LOGGER.debug(OILabelConstants.END_METHOD + strForward);
		System.out.println("OIActionSendMail-doIt strForward2="+strForward);
		return mapping.findForward(strForward);
	}//doit ends here
  
	/***
	 * This method is used to set BV values to Form bean
	 * @param  objOIFormSendMail
	 * @param  objOIBVSendMail
	 */
	private void setBVToForm(OIFormSendMail objOIFormSendMail,OIBVSendMail objOIBVSendMail)
	{
		/** Setting Subject,Message,Email values for BV to Form Bean **/
		objOIFormSendMail.setSubject(OIFormUtilities.chkIsNull(objOIBVSendMail.getSubject()));
		objOIFormSendMail.setMessage(OIFormUtilities.chkIsNull(objOIBVSendMail.getMessage()));
		objOIFormSendMail.setEmail(OIFormUtilities.chkIsNull(objOIBVSendMail.getEmail()));
		objOIFormSendMail.setSurveyOrCons(OIFormUtilities.chkIsNull(objOIBVSendMail.getSurveyOrCons()));
		objOIFormSendMail.setSendOrRemain(OIFormUtilities.chkIsNull(objOIBVSendMail.getSendOrRemain()));
		objOIFormSendMail.setId(OIFormUtilities.chkIsNull(objOIBVSendMail.getId()));
		objOIFormSendMail.setCaption(OIFormUtilities.chkIsNull(objOIBVSendMail.getCaption()));
		if(objOIFormSendMail.getHiddenAction().trim().equals(OILabelConstants.POPULATE) || objOIFormSendMail.getHiddenAction().trim().equals(OILabelConstants.REMOVE) )
		{
			objOIFormSendMail.setHidOrder(OILabelConstants.ASC.trim());
			objOIFormSendMail.setHidSortKey("1"); 
		}
	}
	
	/***
	 * This method is used to set Form Bean Values to BV.
	 * @param  objOIFormSendMail
	 * @param  objOIBVSendMail
	 */
	private void setFormToBV(OIFormSendMail objOIFormSendMail,OIBVSendMail objOIBVSendMail)
	{
		/** Setting Subject,Message,Email values for BV to Form Bean **/		
		objOIBVSendMail.setId(OIFormUtilities.chkIsNull(objOIFormSendMail.getId())); 
		objOIBVSendMail.setSubject(OIFormUtilities.chkIsNull(objOIFormSendMail.getSubject()));
		objOIBVSendMail.setMessage(OIFormUtilities.chkIsNull(objOIFormSendMail.getMessage()));
		objOIBVSendMail.setEmail(OIFormUtilities.chkIsNull(objOIFormSendMail.getEmail()));
		objOIBVSendMail.setSurveyOrCons(OIFormUtilities.chkIsNull(objOIFormSendMail.getSurveyOrCons()));
		objOIBVSendMail.setSendOrRemain(OIFormUtilities.chkIsNull(objOIFormSendMail.getSendOrRemain()));
		objOIBVSendMail.setHiddenAction(OIFormUtilities.chkIsNull(objOIFormSendMail.getHiddenAction()));
		objOIBVSendMail.setCaption(OIFormUtilities.chkIsNull(objOIFormSendMail.getCaption()));
		objOIBVSendMail.setRemoveId(objOIFormSendMail.getRemoveId());
		objOIBVSendMail.setHidOrder(objOIFormSendMail.getHidOrder()); 
		objOIBVSendMail.setHidSortKey(objOIFormSendMail.getHidSortKey());
	}
	
	/**
	 * This helper method set the error object in request scope
	 * @param aOIResponseObject
	 * @param request
	 * @param strKey
	 */
	private void addErrors(OIResponseObject aOIResponseObject,HttpServletRequest request,String strKey )
	{
		if(aOIResponseObject != null && aOIResponseObject.containsKey(strKey))
		{
			request.setAttribute(strKey,aOIResponseObject.getResponseObject(strKey));
			LOGGER.debug(request.getAttribute(strKey));
		}
	}
	
	private void repURL(OIFormSendMail objOIFormSendMail,OIBVSendMail objOIBVSendMail) throws Exception
	{
		String strMsg = objOIBVSendMail.getMessage();
		StringBuffer sbUrl = new StringBuffer(OIDBRegistry.getValues("AlertAFriend"));
		sbUrl.append(OILabelConstants.MODULE).append(objOIBVSendMail.getSurveyOrCons()).append(OILabelConstants.AMPID).append(objOIBVSendMail.getId());
		if(strMsg.trim().length()>0 && strMsg.indexOf("URL")>=0)
		{
		    strMsg = strMsg.replaceAll("URL",sbUrl.toString());
		    objOIFormSendMail.setMessage(strMsg);
		}
	}
	
} //Action class ends