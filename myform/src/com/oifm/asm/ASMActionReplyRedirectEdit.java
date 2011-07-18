/*********************************ASMActionReplyRedirectEdit.java******************* 
 * Title 		: ASMActionReplyRedirectEdit
 * Description 	: This class is the Action Class for ASM Reply Redirect Edit Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 20 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/
package com.oifm.asm;
 

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIBOSendMail;
import com.oifm.common.OICommonMailBean;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OILabelConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.MailUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OIUtilities;

public class ASMActionReplyRedirectEdit extends OIBaseAction 
{
	private static Logger objLogger = Logger.getLogger(ASMActionReplyRedirectEdit.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
	{	
		
		ASMBVReplyRedirectEdit objBV=null;
		ASMBOReplyRedirectEdit objBO=null;
		OIUtilities objUtility =null;
		
		HttpSession session = null;
		String strUserId = "";
    	String strDivCode = "";
    	String strRedirect = "";
    	ArrayList alFunctions = null;
    	boolean isAdmin = false;
    	boolean isASMRep = false;
    	String fromCategory=request.getParameter("fromCategory");
    	String id = request.getParameter("letterId");
    	//String id = request.getParameter("categoryId");
    	String catid = request.getParameter("hidLetterID");
    	System.out.println("doIt >>>>>id ::::" + id +"status  " + fromCategory + "category id" + catid);
		try {
			
			session = request.getSession();
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			if(alFunctions!=null)
				isASMRep = alFunctions.contains(OIFunctionConstants.ASM_REPS);
			
			objBO = new ASMBOReplyRedirectEdit();
			objBV = new ASMBVReplyRedirectEdit();
			
         if(isASMRep) {
			
			ASMFormReplyRedirectEdit objForm = (ASMFormReplyRedirectEdit) form;
			System.out.println("ASMActionReplyRedirectEdit-doIt1*****" + actionName);
			//Create the instance for BO and VO and rkUtility class
//			objBO = new ASMBOReplyRedirectEdit();
//			objBV = new ASMBVReplyRedirectEdit();
			objUtility =new OIUtilities();
			//Set the Form to VO using PropertyUtils class
			String strUserID = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			//System.out.println("ASMActionReplyRedirectEdit-doIt" + strUserID);
			PropertyUtils.copyProperties(objBV, objForm);
			objBV.setHidUserID(strUserID);
			//System.out.println("ASMActionReplyRedirectEdit-doIt" + objBV);
			//Call the ASM Home page BO class to get the details of announcement, letter and reply
			if(objBV.getHiddenAction().equalsIgnoreCase("Redirect") || 
					objBV.getHiddenAction().equalsIgnoreCase("SendRemainder")){
				String strMailIds =OIUtilities.replaceNull(objBV.getTxtTo());
				/*if(!strMailIds.equals("")){
					if(!OIUtilities.replaceNull(objBV.getTxtCc()).equals("")){
						strMailIds = strMailIds+","+objBV.getTxtCc();
					}
					if(!OIUtilities.replaceNull(objBV.getTxtBcc()).equals("")){
						strMailIds = strMailIds+","+objBV.getTxtBcc();
					}
				}
				else if(!OIUtilities.replaceNull(objBV.getTxtCc()).equals("")){
					strMailIds =OIUtilities.replaceNull(objBV.getTxtCc());
					if(!OIUtilities.replaceNull(objBV.getTxtBcc()).equals("")){
						strMailIds = strMailIds+","+objBV.getTxtBcc();
					}
				}else{
					strMailIds =OIUtilities.replaceNull(objBV.getTxtBcc());
				}*/
				String thanksMsg ="";
				int reminderDays = objBO.getReminderDays();
				//lSystem.out.println("ASMActionReplyRedirectEdit-doIt"+ reminderDays );
				//String strMailIds = objBV.getTxtTo()+","+objBV.getTxtCc()+","+objBV.getTxtBcc();
				//objLogger.info("strMailIds--" + strMailIds);
				HashMap hmValidIdTo = new OIBOSendMail().chkDomains(null,OIFormUtilities.chkIsNull(strMailIds));
				HashMap hmValidIdCc = new OIBOSendMail().chkDomains(null,OIFormUtilities.chkIsNull(objBV.getTxtCc()));
				HashMap hmValidIdBcc = new OIBOSendMail().chkDomains(null,OIFormUtilities.chkIsNull(objBV.getTxtBcc()));
				OICommonMailBean aOICommonMailBean = new OICommonMailBean();
				//objLogger.info("strMailIds--1" );
	        	if(hmValidIdTo == null && hmValidIdCc==null && hmValidIdBcc==null)
	        	{
	      	  		request.setAttribute(OILabelConstants.MAILERROR, OILabelConstants.MAILERROR);
	                request.setAttribute(OILoginConstants.K_ERROR,"Invalid Mail Id");
	                request.setAttribute(OILoginConstants.TOPFLAG,OILoginConstants.TOPFLAG);
	                return new ActionForward(OILoginConstants.ERRORPAGE);
	      	  	}
	        	else
	        	{
					//objLogger.info("strMailIds--2" );
	        	    if( (hmValidIdTo!= null && hmValidIdTo.containsKey(OILabelConstants.VALIDIDS)) ||
	        	    		(hmValidIdCc!= null && hmValidIdCc.containsKey(OILabelConstants.VALIDIDS)) ||
							(hmValidIdBcc!= null && hmValidIdBcc.containsKey(OILabelConstants.VALIDIDS))
	        	    		)
	        	    {
	    				//objLogger.info("strMailIds--3" );
				        aOICommonMailBean.setSmtpHost(OIDBRegistry.getValues("smtp"));
				        aOICommonMailBean.setType(OIDBRegistry.getValues("type_html"));
						aOICommonMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
						//System.out.println("ASMActionReplyRedirectEdit-doIt" + aOICommonMailBean);
						if (hmValidIdTo!= null && hmValidIdTo.containsKey(OILabelConstants.VALIDIDS))
						{
							aOICommonMailBean.setTo(hmValidIdTo.get(OILabelConstants.VALIDIDS).toString());
						}
						//objLogger.info("strMailIds--4" );
						if (hmValidIdCc!= null && hmValidIdCc.containsKey(OILabelConstants.VALIDIDS))
						{
							aOICommonMailBean.setCc(hmValidIdCc.get(OILabelConstants.VALIDIDS).toString());
						}
						//objLogger.info("strMailIds--5" );
						if (hmValidIdBcc!= null && hmValidIdBcc.containsKey(OILabelConstants.VALIDIDS))
						{
							aOICommonMailBean.setBcc(hmValidIdBcc.get(OILabelConstants.VALIDIDS).toString());
						}
						//objLogger.info("strMailIds--6" );
						//Date reminderDate = new Date(System.currentTimeMillis() + (((long)reminderDays)*24*3600*1000) );
						Calendar cal = Calendar.getInstance();
						cal.setTime(new Date());
						cal.add(Calendar.DATE, reminderDays);
						Date reminderDate = cal.getTime();
						aOICommonMailBean.setSubject(objBV.getTxtSubject());
						//System.out.println("ASMActionReplyRedirectEdit-doIt" + objBV.getTxtSubject());
						aOICommonMailBean.setMessage("<font face=\"Arial\">" + objBV.getTxtMessage().replaceAll("<DATE>", DateUtility.getDDMMMYYYYStringFromDate(reminderDate)).replaceAll("\n", "<br>")+"<br><br><hr><br><br>Author : "+objBV.getHidPostedBy()+"<br>Designation : "+objBV.getStrUsrDesig()+"<br>Division / School : "+objBV.getStrUsrDivSch()+"<br>Contact No : "+objBV.getTxtContactNo()+"<br>Email : "+objBV.getHidEmail() +"<br><br><hr><br><br>"+objBV.getTxtLetter()+ "</font><br>");
						//objLogger.info("strMailIds--7" );
						if(objBV.getHiddenAction().equalsIgnoreCase("SendRemainder")){
							SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
							cal.setTime(sdf.parse(objBV.getStrRedirectedOn()));
							cal.add(Calendar.DATE, reminderDays);
							reminderDate = cal.getTime();
							ASMBOCommon objCommonBo= new  ASMBOCommon();
							//Get message for remainder note
							String strMsg =objCommonBo.getMessageConfig("ASMREMAINDERMESSAGE");
							aOICommonMailBean.setMessage("<font face=\"Arial\">" + strMsg.replaceAll("<DATE>", DateUtility.getDDMMMYYYYStringFromDate(reminderDate)).replaceAll("\n", "<br>") +"<br><br><hr><br><br>Author : "+objBV.getHidPostedBy()+"<br>Designation : "+objBV.getStrUsrDesig()+"<br>Division / School : "+objBV.getStrUsrDivSch()+"<br>Contact No : "+objBV.getTxtContactNo()+"<br>Email : "+objBV.getHidEmail() +"<br><br><hr><BR><BR>"+objBV.getTxtLetter()+ "</font><br>");
						}
		        	    MailUtility mailUtility = new MailUtility();
						//objLogger.info("strMailIds--8" );
						mailUtility.sendMessage(aOICommonMailBean);
						//objLogger.info("strMailIds--9" );
				        thanksMsg = OIDBRegistry.getValues("OI.ASM.REDIRECTED") + " " + hmValidIdTo.get(OILabelConstants.VALIDIDS).toString();
				        request.setAttribute(OILabelConstants.VALIDIDS, hmValidIdTo.get(OILabelConstants.VALIDIDS));    
	        	    }
		      	 	String invalidId =null;
		      	 	String invalidIdOnly =null;
		      	 	String noDomain = null;
					//objLogger.info("strMailIds--10" );
			        if(hmValidIdTo!=null && hmValidIdTo.containsKey(OILabelConstants.VALIDIDS) &&  hmValidIdTo.containsKey(OILabelConstants.INVALIDIDS))
		      	 	{
			        	if (hmValidIdTo.get(OILabelConstants.INVALIDIDS)!=null)
			        		invalidId = (String) hmValidIdTo.get(OILabelConstants.INVALIDIDS);
		      	 	}
		      	 	else if(hmValidIdTo!=null && hmValidIdTo.containsKey(OILabelConstants.INVALIDIDS))
		      	 	{
						//objLogger.info("strMailIds--11" );
			        	if (hmValidIdTo.get(OILabelConstants.INVALIDIDS)!=null)
			        		invalidIdOnly = (String) hmValidIdTo.get(OILabelConstants.INVALIDIDS);
		      	 	}
		      	 	else if(hmValidIdTo!=null && hmValidIdTo.containsKey(OILabelConstants.NODOMAINS))
		      	 	{
		      	 		noDomain = OILabelConstants.NODOMAINS;
		  	  		}
			        if(hmValidIdCc!=null && hmValidIdCc.containsKey(OILabelConstants.VALIDIDS) &&  hmValidIdCc.containsKey(OILabelConstants.INVALIDIDS))
		      	 	{
			        	if (invalidId==null)
			        	{
				        	if (hmValidIdCc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidId = (String) hmValidIdCc.get(OILabelConstants.INVALIDIDS);
			        	}
			        	else
			        	{
				        	if (hmValidIdCc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidId = invalidId + "," + (String) hmValidIdCc.get(OILabelConstants.INVALIDIDS);
			        	}
		      	 	}
		      	 	else if(hmValidIdCc!=null && hmValidIdCc.containsKey(OILabelConstants.INVALIDIDS))
		      	 	{
			        	if (invalidIdOnly==null)
			        	{
				        	if (hmValidIdCc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidIdOnly = (String) hmValidIdCc.get(OILabelConstants.INVALIDIDS);
			        	}
			        	else
			        	{
				        	if (hmValidIdCc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidIdOnly = invalidIdOnly + "," + (String) hmValidIdCc.get(OILabelConstants.INVALIDIDS);
			        	}
		      	 	}
		      	 	else if(hmValidIdCc!=null && hmValidIdCc.containsKey(OILabelConstants.NODOMAINS))
		      	 	{
		      	 		noDomain = OILabelConstants.NODOMAINS;
		  	  		}
			        if(hmValidIdBcc!=null && hmValidIdBcc.containsKey(OILabelConstants.VALIDIDS) &&  hmValidIdBcc.containsKey(OILabelConstants.INVALIDIDS))
		      	 	{
			        	if (invalidId==null)
			        	{
				        	if (hmValidIdBcc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidId = (String) hmValidIdBcc.get(OILabelConstants.INVALIDIDS);
			        	}
			        	else
			        	{
				        	if (hmValidIdBcc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidId = invalidId + "," + (String) hmValidIdBcc.get(OILabelConstants.INVALIDIDS);
			        	}
		      	 	}
		      	 	else if(hmValidIdBcc!=null && hmValidIdBcc.containsKey(OILabelConstants.INVALIDIDS))
		      	 	{
			        	if (invalidIdOnly==null)
			        	{
				        	if (hmValidIdBcc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidIdOnly = (String) hmValidIdBcc.get(OILabelConstants.INVALIDIDS);
			        	}
			        	else
			        	{
				        	if (hmValidIdBcc.get(OILabelConstants.INVALIDIDS)!=null)
				        		invalidIdOnly = invalidIdOnly + "," + (String) hmValidIdBcc.get(OILabelConstants.INVALIDIDS);
			        	}
		      	 	}
		      	 	else if(hmValidIdBcc!=null && hmValidIdBcc.containsKey(OILabelConstants.NODOMAINS))
		      	 	{
		      	 		noDomain = OILabelConstants.NODOMAINS;
		  	  		}
			        if (invalidId!=null)
						request.setAttribute("valid",hmValidIdTo.get(OILabelConstants.VALIDIDS).toString());
			        	request.setAttribute(OILabelConstants.INVALIDIDS,invalidId);
			        if (invalidIdOnly!=null)
			        	thanksMsg = "";
		      	 	    request.setAttribute(OILabelConstants.INVALIDALONE, invalidIdOnly);
			        if (noDomain!=null)
			        	thanksMsg = "";
		      	 	    request.setAttribute(OILabelConstants.NODOMAINS,noDomain);
	        	}
	        	objLogger.info("hmValidIdTo--" + hmValidIdTo);
	      	 	request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/ASMReplyRedirect.do?hiddenAction=AdminPage");
	            request.setAttribute(OILoginConstants.PAGENAME,"ThankFeedBack");
	            request.setAttribute("ASM", "ASM");
	            request.setAttribute("title","");
	            request.setAttribute("content",thanksMsg);
	            
	            if(objBV.getHiddenAction().equalsIgnoreCase("Redirect")){
	            	OIResponseObject aOIResponseObject = objBO.process(objBV);
	            }
	         //   System.out.println("ASMActionReplyRedirectEdit-doIt" + actionName);
	            return mapping.findForward(actionName);
			}
			
			OIResponseObject aOIResponseObject = objBO.process(objBV);
			PropertyUtils.copyProperties(objForm, objBV);	
		}else {
         	strRedirect = ASMGlobals.ERR_REDIRECT + "?error=NoAccess";
        	}
		}catch(Exception e)
		{
			objLogger.info("Reply redirect action Edit Error ================"+e);
            request.setAttribute(OILoginConstants.K_ERROR,e.getMessage());
            //request.setAttribute(OILoginConstants.TOPFLAG,OILoginConstants.TOPFLAG);
            return new ActionForward(OILoginConstants.ERRORPAGE);
		}
		finally{
			objBO=null;
			objUtility =null;
		}
		request.setAttribute("DivisionList",objBV.getAlDivision());
		request.setAttribute("CategoryList",objBV.getAlCategoryList());
		try{
			if(null!=actionName && actionName.equalsIgnoreCase("Save")){
				//System.out.println("inside save >>>>>id ::::" + id +"status  " + fromCategory + "category id" + catid);
				if(null!= fromCategory && fromCategory.equals("Y")){
					//System.out.println("inside if loop ");
					return new ActionForward("/ASMActionCategoriesOfLetter.do?topicId="+id+"&hiddenAction=Adminsearch");
				}else{
					//System.out.println("inside else :::::: ");
					return new ActionForward("/ASMReplyRedirect.do?hiddenAction=AdminPage");
				}



			}else{
				request.setAttribute("pageName","ASMReplyRedirectEdit");	
			}	
		}catch(Exception e){
			e.printStackTrace();
		}
		
//Added by Rakesh. This is to Delete the Letter form the The OI_AM_LETTERS.
		if(null!=actionName && actionName.equalsIgnoreCase("Delete")){
			System.out.println("inside delete >>>>>id ::::" + id +"status  " + fromCategory + "category id" + catid);
			if(null!= fromCategory && fromCategory.equals("Y")){
				System.out.println("inside if loop ");
				return new ActionForward("/ASMActionCategoriesOfLetter.do?topicId="+id+"&hiddenAction=ADMIN");
			}else{
				return new ActionForward("/ASMReplyRedirect.do?hiddenAction=AdminPage");
			}
			
		}else{
			request.setAttribute("pageName","ASMReplyRedirectEdit");	
		}
		
		request.setAttribute("posted_by",objBV.getHidPostedBy());
		request.setAttribute("posted_on",objBV.getHidPostedOn());
		request.setAttribute("email",objBV.getHidEmail());
		request.setAttribute("letter_status",objBV.getHidStatus());
		request.setAttribute("resetLetter", objBV.getTxtLetter());
		request.setAttribute("resetReply", objBV.getTxtReply());
		
		//this is for setting the letter heading
		if(OIUtilities.replaceNull(objBV.getHidStatus()).equals("P")){
			request.setAttribute("letter_title","Published Letter");
		}
		else if(OIUtilities.replaceNull(objBV.getHidStatus()).equals("D")){
			request.setAttribute("letter_title","Drafted Letter");
		}
		else if(OIUtilities.replaceNull(objBV.getHidStatus()).equals("R")){
			request.setAttribute("letter_title","Replied Letter");
		}
		else if(OIUtilities.replaceNull(objBV.getHidStatus()).equals("T")){
			request.setAttribute("letter_title","Redirected Letter");
		}
		else{
			request.setAttribute("letter_title","Submitted Letter");
		}
		
		if(!strRedirect.equals("")) 
            return new ActionForward(strRedirect);
        else
            
		return mapping.findForward(actionName);
	}
}
