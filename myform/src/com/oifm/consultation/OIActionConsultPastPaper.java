package com.oifm.consultation;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.admin.OIBAConsultCategory;
import com.oifm.consultation.admin.OIBAConsultPaper;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;

public class OIActionConsultPastPaper extends OIBaseAction
{
    Logger logger = Logger.getLogger(OIActionConsultPastPaper.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String PRINT_ACTION = "print";
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
	        }
	        if (actionName.equals(PRINT_ACTION))
	        {
	            request.setAttribute(PRINT_ACTION,PRINT_ACTION);
	            return populate(mapping, form, request, response);
	        }
        }
        String error=null;
        try
        {
            error = OIDBRegistry.getValues("OI.CONS.GEN"); //"Action not Available";
        }
        catch(Exception e)
        {}
        request.setAttribute(OILoginConstants.K_ERROR,error);
        return new ActionForward(OILoginConstants.ERRORPAGE);
    }
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String paperId = request.getParameter(OIConsultConstant.K_paperId);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject = new OIBOConsultWeb().readPastPaper(Integer.parseInt(paperId),userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
        OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultCategory);
        OIFormConsultPastPaper aOIFormConsultPastPaper = new OIFormConsultPastPaper();
        if (aOIBAConsultPaper!=null)
        {
	        aOIFormConsultPastPaper.setActive(aOIBAConsultPaper.getActive());
	        aOIFormConsultPastPaper.setCategoryName(aOIBAConsultCategory.getName());
	        aOIFormConsultPastPaper.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
	        aOIFormConsultPastPaper.setAttachedSum(aOIBAConsultPaper.getAttachedSum());
	        aOIFormConsultPastPaper.setCategoryID(aOIBAConsultPaper.getCategoryID() + "");
	        aOIFormConsultPastPaper.setCreatedBy(aOIBAConsultPaper.getCreatedBy());
	        try
	        {
	            if (aOIBAConsultPaper.getCreatedOn()!= null)
	                aOIFormConsultPastPaper.setCreatedOn(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getCreatedOn()));
	            if (aOIBAConsultPaper.getFromDt()!= null)
	                aOIFormConsultPastPaper.setFromDt(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt()));
	            if (aOIBAConsultPaper.getPublishedOn()!= null)
	                aOIFormConsultPastPaper.setPublishedOn(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getPublishedOn()));
	            if (aOIBAConsultPaper.getToDt()!= null)
	                aOIFormConsultPastPaper.setToDt(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt()));
	        }
	        catch(Exception e)
	        {
	            logger.info(e.getMessage());
	        }
	        if (aOIBAConsultPaper.getDescription()!=null)
	        	aOIFormConsultPastPaper.setDescription(aOIBAConsultPaper.getDescription().replaceAll("\n","<br>"));
	        else
	        	aOIFormConsultPastPaper.setDescription(aOIBAConsultPaper.getDescription());
	        aOIFormConsultPastPaper.setMailStatus(aOIBAConsultPaper.getMailStatus());
	        aOIFormConsultPastPaper.setPaperId(aOIBAConsultPaper.getPaperId() + "");
	        aOIFormConsultPastPaper.setPublishedStatus(aOIBAConsultPaper.getPublishedStatus());
	        aOIFormConsultPastPaper.setReminderMode(aOIBAConsultPaper.getReminderMode());
	        aOIFormConsultPastPaper.setSecurity(aOIBAConsultPaper.getSecurity());
	        if (aOIBAConsultPaper.getSummary()!=null)
	        	aOIFormConsultPastPaper.setSummary(aOIBAConsultPaper.getSummary().replaceAll("\n","<br>"));
	        else
	        	aOIFormConsultPastPaper.setSummary(aOIBAConsultPaper.getSummary());
	        aOIFormConsultPastPaper.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
			aOIFormConsultPastPaper.setContactPerson(aOIBAConsultPaper.getContactPerson());
			aOIFormConsultPastPaper.setPhone(aOIBAConsultPaper.getPhone());
			aOIFormConsultPastPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
	        aOIFormConsultPastPaper.setTitle(aOIBAConsultPaper.getTitle());

			//NEW FIELDS
			aOIFormConsultPastPaper.setStrInstruction(aOIBAConsultPaper.getStrInstruction());
			aOIFormConsultPastPaper.setStrCompletionTime(aOIBAConsultPaper.getStrCompletionTime());
			aOIFormConsultPastPaper.setStrFindingsPlannedDt(aOIBAConsultPaper.getStrFindingsPlannedDt());
			aOIFormConsultPastPaper.setStrViewFindingType(aOIBAConsultPaper.getStrViewFindingType());
			aOIFormConsultPastPaper.setPublishStatus(aOIBAConsultPaper.getPublishStatus());
			aOIFormConsultPastPaper.setStrOpenTag(aOIBAConsultPaper.getStrOpenTag());
			aOIFormConsultPastPaper.setStrTagWords(aOIBAConsultPaper.getStrTagWords());
			aOIFormConsultPastPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
			aOIFormConsultPastPaper.setStrEmailDate(aOIBAConsultPaper.getStrEmailDate());
			aOIFormConsultPastPaper.setStrEmailMessage(aOIBAConsultPaper.getStrEmailMessage());
			aOIFormConsultPastPaper.setStrTargetGpIds(aOIBAConsultPaper.getStrTargetGpIds());
			
        }
        
        request.setAttribute(OIConsultConstant.CONSULT_PAST_PAPER_FORM_WEB,aOIFormConsultPastPaper);
        request.setAttribute(OILoginConstants.PAGENAME,"ConsultPastPaper");
        if (request.getAttribute(PRINT_ACTION)!=null)
            return mapping.findForward(PRINT_ACTION);
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
