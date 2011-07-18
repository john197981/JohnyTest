package com.oifm.common;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.codemaster.OIBACodeMaster;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.useradmin.OIBAProfile;
import com.oifm.utility.MailUtility;
import com.oifm.utility.OIDBRegistry;
import com.oifm.utility.OIFormUtilities;
import com.oifm.utility.OIUtilities;

public class OIActionAlertFriends extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionAlertFriends.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SEND_MAIL = "sendMail";
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(SEND_MAIL))
	        {
	            return sendMail(mapping, form, request, response);
	        }
        }
        String error=null;
        try
        {
            error = OIDBRegistry.getValues("OI.CONS.GEN"); //"Action not Available";
        }
        catch(Exception e){}
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        /*logger.info("getRequestURI-" + request.getRequestURI());
        logger.info("getContextPath-" + request.getContextPath());
        logger.info("getServletPath-" + request.getServletPath());
        logger.info("getPathInfo-" + request.getPathInfo());
        logger.info("getRequestURL-" + request.getRequestURL());
        logger.info("request.getHeaderNames() -" + request.getHeaderNames() );
        logger.info("request.getHeader('Referer')-" + request.getHeader("Referer"));*/
        String url = null;
        if (request.getParameter("url")==null)
            url = request.getHeader("Referer");
        else
            url = request.getParameter("url");
        String Id = request.getParameter("iD");
        String module = request.getParameter("module");
        String email="";
        try
		{
        	url = OIDBRegistry.getValues("AlertAFriend") + OILabelConstants.MODULE + module + OILabelConstants.AMPID + Id;
        	/*
        	if (getSessionAttribute(request,OILoginConstants.NICKNAME)!=null)
        	{
        	    email = (String) getSessionAttribute(request,OILoginConstants.NICKNAME);
        	}
        	*/
        	email = (String) getSessionAttribute(request,OILoginConstants.USER_NAME);
        	if(OIUtilities.replaceNull(module).equals("ASM") || 
        			OIUtilities.replaceNull(module).equals("ASMDRAFT")){
        		logger.info("ASM || ASMDRAFT");
        		email = (String) getSessionAttribute(request,OILoginConstants.USER_NAME);
        	}
        	//For ASM module the email name should be the full name and not the nickname
		}catch(Exception e){logger.info(e.getMessage());}
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject = new OIBOAlertFriends().formulateMessage(Integer.parseInt(Id),module,url,userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute("URL","/webConsultListingAction.do?hiddenAction=populate");
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBACodeMaster aOIBACodeMasterSubject = (OIBACodeMaster) aOIResponseObject.getResponseObject("aOIBACodeMasterSubject");
        OIBACodeMaster aOIBACodeMasterBody = (OIBACodeMaster) aOIResponseObject.getResponseObject("aOIBACodeMasterBody");
        OIBAProfile aOIBAProfile = (OIBAProfile) aOIResponseObject.getResponseObject("aOIBAProfile");
        OIFormAlertFriends aOIFormAlertFriends = new OIFormAlertFriends();
        aOIFormAlertFriends.setContent(aOIBACodeMasterBody.getDescription());
        aOIFormAlertFriends.setSubject(aOIBACodeMasterSubject.getDescription());
        //aOIFormAlertFriends.setEmailId(aOIBAProfile.getEmailId());
        aOIFormAlertFriends.setEmailId(email);
        aOIFormAlertFriends.setID(Id);
        aOIFormAlertFriends.setUrl(url);
        
        request.setAttribute(OILoginConstants.PAGENAME,"AlertFriend");
        request.setAttribute("AlertFriendForm",aOIFormAlertFriends);
        
        /** Added by Suresh to avoid inclusion of top jsp in Alert a Friend**/ 
        if(module.equals("F"))
        {
            request.setAttribute("module","Forum");
        }//14th March 2011, Ramesh added new condition for the ASMEditorNote module for addressing the Editor Note issue.
        if(module.equals("ASMEditorNote"))
        {
            request.setAttribute("module","ASMEditorNote");
        }
        if(module.equals("ASM"))
        {
            request.setAttribute("module","ASM");
        }        
        if(module.equals("ASMDRAFT"))
        {
            request.setAttribute("module","ASMDRAFT");
        }
        return mapping.findForward(POPULATE_ACTION);
    }
    
    public ActionForward sendMail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormAlertFriends aOIFormAlertFriends = (OIFormAlertFriends) form;
        OICommonMailBean aOICommonMailBean = new OICommonMailBean();
        String module = request.getParameter("module");
        
        String thanksMsg=null;
        /** SendMail **/
        HashMap hmValidIds = null;
        OIBOSendMail objSendMail = new OIBOSendMail();
        try
        {
        	/** Get the Valid and Invalid Email ids **/
        	hmValidIds = objSendMail.chkDomains(null,OIFormUtilities.chkIsNull(aOIFormAlertFriends.getTo()));
        	if(hmValidIds == null)
        	{
      	  		request.setAttribute(OILabelConstants.MAILERROR, OILabelConstants.MAILERROR);
                request.setAttribute(OILoginConstants.K_ERROR,"Invalid Mail Id");
                request.setAttribute(OILoginConstants.TOPFLAG,OILoginConstants.TOPFLAG);
                return new ActionForward(OILoginConstants.ERRORPAGE);
      	  	}
        	else
        	{
	        	/** If hashmap contains Valid Emailids then mail has been sent **/
        	    if(hmValidIds!= null &&   hmValidIds.containsKey(OILabelConstants.VALIDIDS))
        	    {
        	        //aOICommonMailBean.setBcc(hmValidIds.get(OILabelConstants.VALIDIDS).toString());
	      	  		aOICommonMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
	      	  		if(module!=null && (module.equalsIgnoreCase("ASM") || module.equalsIgnoreCase("ASMDRAFT")))
	      	  		{
	      	  			aOICommonMailBean.setFrom(""+getSessionAttribute(request,OILoginConstants.EMAIL));
	      	  		}
			        aOICommonMailBean.setTo(hmValidIds.get(OILabelConstants.VALIDIDS).toString());
	      	  		//aOICommonMailBean.setFrom(OIDBRegistry.getValues("sendmailtoaddress"));
			        //aOICommonMailBean.setTo(OIDBRegistry.getValues("sendmailtoaddress"));
			        //aOICommonMailBean.setBcc(aOIFormAlertFriends.getTo());
			        aOICommonMailBean.setSmtpHost(OIDBRegistry.getValues("smtp"));
			        //aOICommonMailBean.setType(OIDBRegistry.getValues("type_html"));
			        aOICommonMailBean.setType(OIDBRegistry.getValues("type_html"));
			        aOICommonMailBean.setSubject(aOIFormAlertFriends.getSubject());
			        aOICommonMailBean.setMessage("<font face=\"Arial\">" + aOIFormAlertFriends.getContent().replaceAll("\n", "<br>") + "</font><br>");
			        MailUtility aMailUtility = new MailUtility();
			        aMailUtility.sendMessage(aOICommonMailBean);
			        thanksMsg = OIDBRegistry.getValues("OI.CONS.THANKS.ALERTFRIEND");
			        request.setAttribute(OILabelConstants.VALIDIDS, hmValidIds.get(OILabelConstants.VALIDIDS));    
	      	  	}
        	    logger.info("coming in friend==============1");
        	    /** Get the List of Invalid Email Ids and displayed in page **/  
	      	 	if(hmValidIds.containsKey(OILabelConstants.VALIDIDS) &&  hmValidIds.containsKey(OILabelConstants.INVALIDIDS))
	      	 	{
	      	 	    request.setAttribute(OILabelConstants.INVALIDIDS, hmValidIds.get(OILabelConstants.INVALIDIDS));
	      	 	}
	      	 	else if(hmValidIds.containsKey(OILabelConstants.INVALIDIDS))
	      	 	{
	      	 	    request.setAttribute(OILabelConstants.INVALIDALONE, hmValidIds.get(OILabelConstants.INVALIDIDS));
	      	 	}
	      	 	else if( hmValidIds.containsKey(OILabelConstants.NODOMAINS))
	      	 	{
	      	 	    request.setAttribute(OILabelConstants.NODOMAINS,OILabelConstants.NODOMAINS);
	  	  		}
        	}	  	
      	  	/** If HashMap is null Mail has not been sent to any users **/
      	  	logger.info("hmValidIds" +hmValidIds );
      	  	
      	  	//request.setAttribute("URL",OIDBRegistry.getValues("OIFM.contextroot") + "/webConsultListingAction.do?hiddenAction=populate");
      	  	request.setAttribute("URL","javascript:window.close();");
      	    if(module!=null && module.equalsIgnoreCase("Forum"))
      	    {
               request.setAttribute("Forum","Forum");
               thanksMsg = OIDBRegistry.getValues("OI.FORUM.THANKS.ALERTFRIEND");
            }
      	    
      	    else if(module!=null && (module.equalsIgnoreCase("ASM") || module.equalsIgnoreCase("ASMDRAFT")))
      	    {
               request.setAttribute("ASM","ASM");
               thanksMsg = OIDBRegistry.getValues("OI.ASM.THANKS.ALERTFRIEND");
            }
      	  logger.info("coming in friend==============aa"+module);
        }catch(Exception e)
        {
            request.setAttribute(OILoginConstants.K_ERROR,e.getMessage());
            request.setAttribute(OILoginConstants.TOPFLAG,OILoginConstants.TOPFLAG);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        finally
        {
        	objSendMail = null;
        }

        /** Added by Suresh to avoid inclusion of top jsp in Alert a Friend**/ 
        if(module!=null && module.equals("Forum"))
        {
            request.setAttribute("Forum","Forum");
            request.setAttribute("URL","javascript:window.close();");
        }
        request.setAttribute(OILoginConstants.PAGENAME,"ThankFeedBack");
        request.setAttribute("title","");
        request.setAttribute("content",thanksMsg);

        //return new ActionForward("/webConsultListingAction.do?hiddenAction=populate");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
