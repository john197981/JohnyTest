package com.oifm.common;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;

public class OIActionConfiguration extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionConfiguration.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String POPULATE_MESSAGE_ACTION = "populateMessage";
    private static final String SAVE_MESSAGE_ACTION = "saveMessage";
    private static final String SAVE_ACTION = "save";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(POPULATE_MESSAGE_ACTION))
	        {
	            return populateMessage(mapping, form, request, response);
	        }
	        if (actionName.equals(SAVE_MESSAGE_ACTION))
	        {
	            return saveMessage(mapping, form, request, response);
	        }
	        if (actionName.equals(SAVE_ACTION))
	        {
	            return save(mapping, form, request, response);
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

    public ActionForward populateMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	String messageCaption = request.getParameter("messageCaption");
    	String messageTag = request.getParameter("messageTag");
    	
    	OIResponseObject aOIResponseObject = new OIBOConfiguration().readMessage(messageTag);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAConfiguration aOIBAConfiguration = (OIBAConfiguration) aOIResponseObject.getResponseObject("aOIBAConfiguration");

    	String message = null;
    	if (aOIBAConfiguration.getValue()!=null)
    		message = aOIBAConfiguration.getValue();
    	else
    		message = "";
    	
    	request.setAttribute("messageTag",messageTag);
    	request.setAttribute("messageCaption",messageCaption);
    	request.setAttribute("message",message);
    	request.setAttribute(OILoginConstants.PAGENAME,"MessageConfiguration");
    	//return null;
    	return mapping.findForward(POPULATE_ACTION);
    }

    public ActionForward saveMessage(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
    	String messageCaption = request.getParameter("messageCaption");
    	String messageTag = request.getParameter("messageTag");
    	String message = request.getParameter("message");
    	OIBAConfiguration aOIBAConfiguration = new OIBAConfiguration();
    	ArrayList arOIBAConfiguration = null;
    	if (messageTag!=null && message!=null)
    	{
    		arOIBAConfiguration=new ArrayList();
    		aOIBAConfiguration.setValue(message);
    		aOIBAConfiguration.setTag(messageTag);
    		arOIBAConfiguration.add(aOIBAConfiguration);
    	}
    	OIResponseObject aOIResponseObject = new OIBOConfiguration().saveConfiguration(arOIBAConfiguration);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
    	
    	return new ActionForward("/adminConfiguration.do?hiddenAction=populate");
    	//return mapping.findForward(POPULATE_ACTION);
    }

    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIResponseObject aOIResponseObject = new OIBOConfiguration().readConfiguration();
        OIBAMessageConfiguration aOIBAMessageConfiguration=null;
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        ArrayList arOIBAConfiguration = (ArrayList) aOIResponseObject.getResponseObject("arOIBAConfiguration");
        OIFormConfiguration aOIFormConfiguration = new OIFormConfiguration();
        if (arOIBAConfiguration!=null)
        {
            for(int i=0;i<arOIBAConfiguration.size();i++)
            {
                OIBAConfiguration aOIBAConfiguration = (OIBAConfiguration) arOIBAConfiguration.get(i);
                if (aOIBAConfiguration.getTag()!=null)
                {
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("SESSIONTIMEOUTWARN"))
	                {
	                    aOIFormConfiguration.setSessionTimeout(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("FORUMARCHIVE"))
	                {
	                    aOIFormConfiguration.setForumArchivePeriod(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("SURVEYARCHIVE"))
	                {
	                    aOIFormConfiguration.setSurveyArchivePeriod(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("CONSULTPAPERARCHIVE"))
	                {
	                    aOIFormConfiguration.setPaperArchivePeriod(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ATTACHMENTSIZE"))
	                {
	                    aOIFormConfiguration.setAttachmentSize(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("CATEGORISETHREADS"))
	                {
	                    aOIFormConfiguration.setCategorizeThread(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("HOTTOPIC"))
	                {
	                    aOIFormConfiguration.setNoPostingForHotTopics(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("BOOKMARKTHREADS"))
	                {
	                    aOIFormConfiguration.setNoTopicToShowBookmark(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("LATESTTOPIC"))
	                {
	                    aOIFormConfiguration.setNoOfDaysToConsiderLatestTopics(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("PERSONALMESSAGE"))
	                {
	                    if (aOIBAConfiguration.getValue().equalsIgnoreCase("Y"))
	                        aOIFormConfiguration.setPersonalMessage("on");
	                    else
	                        aOIFormConfiguration.setPersonalMessage("off");
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ALERTADMIN"))
	                {
	                    if (aOIBAConfiguration.getValue().equalsIgnoreCase("Y"))
	                        aOIFormConfiguration.setAlertAdmin("on");
	                    else
	                        aOIFormConfiguration.setAlertAdmin("off");
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("SENDREMINDERSBEFORE"))
	                {
	                    aOIFormConfiguration.setSendRemiders(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("HOMEANNOUNCEFROM"))
	                {
	                    aOIFormConfiguration.setHomeFrmDt(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("HOMEANNOUNCETO"))
	                {
	                    aOIFormConfiguration.setHomeToDt(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("FORUMANNOUNCEFROM"))
	                {
	                    aOIFormConfiguration.setForumFrmDt(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("FORUMANNOUNCETO"))
	                {
	                    aOIFormConfiguration.setForumToDt(aOIBAConfiguration.getValue());
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("HOMEANNOUNCEMENT"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("HOMEANNOUNCEMENT");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("FORUMANNOUNCEMENT"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("FORUMANNOUNCEMENT");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("SITEDISCLAIMER"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("SITEDISCLAIMER");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMMGMNTWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMMGMNTWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMSUBVIEWWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMSUBVIEWWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMPREVREPWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMPREVREPWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMABOUTWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMABOUTWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMEDITORWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMEDITORWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMREDIRECTMESSAGE"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMREDIRECTMESSAGE");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("ASMREMAINDERMESSAGE"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("ASMREMAINDERMESSAGE");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("CONSULTATIONWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("CONSULTATIONWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
	                if (aOIBAConfiguration.getTag().trim().equalsIgnoreCase("SURVEYWELCOME"))
	                {
	                	aOIBAMessageConfiguration = new OIBAMessageConfiguration();
	                	aOIBAMessageConfiguration.setMessageTag("SURVEYWELCOME");
	                	aOIBAMessageConfiguration.setMessage(aOIBAConfiguration.getValue());
	                	aOIBAMessageConfiguration.setMessageCaption(aOIBAConfiguration.getCaption());
	                	aOIFormConfiguration.addArMessages(aOIBAMessageConfiguration);
	                }
                }
            }
        }
        logger.info(aOIFormConfiguration.getArMessages().size() + "");
        request.setAttribute("ConfigurationForm",aOIFormConfiguration);
        request.setAttribute(OILoginConstants.PAGENAME,"adminConfiguration");
        return mapping.findForward(POPULATE_ACTION);
    }
    
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConfiguration aOIFormConfiguration = (OIFormConfiguration) form;
        ArrayList arOIBAConfiguration = new ArrayList();
        OIBAConfiguration aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("SESSIONTIMEOUTWARN");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getSessionTimeout());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("FORUMARCHIVE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getForumArchivePeriod());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("SURVEYARCHIVE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getSurveyArchivePeriod());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("CONSULTPAPERARCHIVE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getPaperArchivePeriod());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ATTACHMENTSIZE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getAttachmentSize());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("CATEGORISETHREADS");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getCategorizeThread());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("HOTTOPIC");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getNoPostingForHotTopics());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("BOOKMARKTHREADS");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getNoTopicToShowBookmark());
        arOIBAConfiguration.add(aOIBAConfiguration);

        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("LATESTTOPIC");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getNoOfDaysToConsiderLatestTopics());
        arOIBAConfiguration.add(aOIBAConfiguration);

        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("PERSONALMESSAGE");
        if (aOIFormConfiguration.getPersonalMessage()!=null)
	        if (aOIFormConfiguration.getPersonalMessage().equalsIgnoreCase("on"))
	            aOIBAConfiguration.setValue("Y");
	        else
	            aOIBAConfiguration.setValue("N");
        else
            aOIBAConfiguration.setValue("N");
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ALERTADMIN");
        
        if (aOIFormConfiguration.getAlertAdmin()!=null)
	        if (aOIFormConfiguration.getAlertAdmin().equalsIgnoreCase("on"))
	            aOIBAConfiguration.setValue("Y");
	        else
	            aOIBAConfiguration.setValue("N");
        else
            aOIBAConfiguration.setValue("N");
        arOIBAConfiguration.add(aOIBAConfiguration);
        
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("SENDREMINDERSBEFORE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getSendRemiders());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("HOMEANNOUNCEFROM");
        try
        {
            if (aOIFormConfiguration.getHomeFrmDt()!=null)
                aOIBAConfiguration.setValue(DateUtility.getMMDDYYYYStringFromDate(DateUtility.getDateFromMMDDYYYYString(aOIFormConfiguration.getHomeFrmDt())));
        }
        catch(Exception e){}
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("HOMEANNOUNCETO");
        try
        {
            if (aOIFormConfiguration.getHomeToDt()!=null)
                aOIBAConfiguration.setValue(DateUtility.getMMDDYYYYStringFromDate(DateUtility.getDateFromMMDDYYYYString(aOIFormConfiguration.getHomeToDt())));
        }
        catch(Exception e){}
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("FORUMANNOUNCEFROM");
        try
        {
            if (aOIFormConfiguration.getForumFrmDt()!=null)
                aOIBAConfiguration.setValue(DateUtility.getMMDDYYYYStringFromDate(DateUtility.getDateFromMMDDYYYYString(aOIFormConfiguration.getForumFrmDt())));
        }
        catch(Exception e){}
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("FORUMANNOUNCETO");
        try
        {
            if (aOIFormConfiguration.getForumToDt()!=null)
                aOIBAConfiguration.setValue(DateUtility.getMMDDYYYYStringFromDate(DateUtility.getDateFromMMDDYYYYString(aOIFormConfiguration.getForumToDt())));
        }
        catch(Exception e){}
        arOIBAConfiguration.add(aOIBAConfiguration);

        /*aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("HOMEANNOUNCEMENT");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getHomeAnnouncement());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("FORUMANNOUNCEMENT");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getForumAnnouncement());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("SITEDISCLAIMER");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getSiteDisclaimer());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMMGMNTWELCOME");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getSeniorMgmntWelcome());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMSUBVIEWWELCOME");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getSubmitViewWelcome());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMPREVREPWELCOME");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getPrevRepWelcome());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMABOUTWELCOME");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getAboutWelcome());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMEDITORWELCOME");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getEditorWelcome());
        arOIBAConfiguration.add(aOIBAConfiguration);
        
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMREDIRECTMESSAGE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getNoteMessage());
        arOIBAConfiguration.add(aOIBAConfiguration);
        aOIBAConfiguration = new OIBAConfiguration();
        aOIBAConfiguration.setTag("ASMREMAINDERMESSAGE");
        aOIBAConfiguration.setValue(aOIFormConfiguration.getRemainderMessage());
        arOIBAConfiguration.add(aOIBAConfiguration);*/
        
        
        OIResponseObject aOIResponseObject = new OIBOConfiguration().saveConfiguration(arOIBAConfiguration);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        request.setAttribute("ConfigurationForm",aOIFormConfiguration);
        request.setAttribute(OILoginConstants.PAGENAME,"adminConfiguration");
        return mapping.findForward(POPULATE_ACTION);
    }
}