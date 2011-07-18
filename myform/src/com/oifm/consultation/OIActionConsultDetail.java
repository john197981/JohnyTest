package com.oifm.consultation;

import java.util.ArrayList;
import java.util.Date;

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
import com.oifm.consultation.admin.OIBAConsultQuestion;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;
public class OIActionConsultDetail extends OIBaseAction 
{
    Logger logger = Logger.getLogger(OIActionConsultDetail.class.getName());
    
    private static final String POPULATE_ACTION = "populate";
    private static final String PRINT_ACTION = "print";
    private static final String SAVE_ACTION = "saveAsDraft";
    private static final String SUBMIT_ACTION = "submit";
    
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
	        if (actionName.equals(SAVE_ACTION))
	        {
	            return saveAsDraft(mapping, form, request, response);
	        }
	        if (actionName.equals(SUBMIT_ACTION))
	        {
	            return submit(mapping, form, request, response);
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

    /**
     * This method calls the readConsultPaperDetail of OIBOConsultWeb 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String paperId = (String) request.getParameter(OIConsultConstant.K_paperId);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIResponseObject aOIResponseObject = new OIBOConsultWeb().readConsultPaperDetail(Integer.parseInt(paperId),userId);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        OIBAConsultPaper aOIBAConsultPaper = (OIBAConsultPaper) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultPaper);
        OIBAConsultCategory aOIBAConsultCategory = (OIBAConsultCategory) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultCategory);
        ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
        ArrayList arOIBAResponse = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAResponse);
        OIBAFeedBack aOIBAFeedBack = (OIBAFeedBack) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAFeedBack);
        OIBADraft aOIBADraft = (OIBADraft) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBADraft);

        OIFormConsultPresentPaper aOIFormConsultPresentPaper = new OIFormConsultPresentPaper();
        aOIFormConsultPresentPaper.setActive(aOIBAConsultPaper.getActive());
        aOIFormConsultPresentPaper.setCategoryName(aOIBAConsultCategory.getName());
        aOIFormConsultPresentPaper.setAttachedFile(aOIBAConsultPaper.getAttachedFile());
        aOIFormConsultPresentPaper.setAttachedSum(aOIBAConsultPaper.getAttachedSum());
        aOIFormConsultPresentPaper.setCategoryID(aOIBAConsultPaper.getCategoryID() + "");
        aOIFormConsultPresentPaper.setCreatedBy(aOIBAConsultPaper.getCreatedBy());
        try
        {
            if (aOIBAConsultPaper.getCreatedOn()!=null)
                aOIFormConsultPresentPaper.setCreatedOn(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getCreatedOn()));
            if (aOIBAConsultPaper.getFromDt()!=null)
                aOIFormConsultPresentPaper.setFromDt(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getFromDt()));
            if (aOIBAConsultPaper.getPublishedOn()!=null)
            {
                if (aOIBAConsultPaper.getPublishedOn().before(new Date()))
                {
                    return new ActionForward("/webConsultPastPaperAction.do?paperId=" + aOIBAConsultPaper.getPaperId() + "&hiddenAction=populate");
                }
                aOIFormConsultPresentPaper.setPublishedOn(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getPublishedOn()));
            }
            if (aOIBAConsultPaper.getToDt()!=null)
            {
                aOIFormConsultPresentPaper.setToDt(DateUtility.getMMDDYYYYStringFromDate(aOIBAConsultPaper.getToDt()));
            }
        }
        catch(Exception e)
        {
            logger.info(e.getMessage());
        }
        if (aOIBAConsultPaper.getDescription()!=null)
        	aOIFormConsultPresentPaper.setDescription(aOIBAConsultPaper.getDescription().replaceAll("\n","<br>"));
        else
        	aOIFormConsultPresentPaper.setDescription(aOIBAConsultPaper.getDescription());
        //aOIFormConsultPresentPaper.setDescription(aOIBAConsultPaper.getDescription());
        aOIFormConsultPresentPaper.setMailStatus(aOIBAConsultPaper.getMailStatus());
        aOIFormConsultPresentPaper.setPaperId(aOIBAConsultPaper.getPaperId() + "");
        aOIFormConsultPresentPaper.setPublishedStatus(aOIBAConsultPaper.getPublishedStatus());
        aOIFormConsultPresentPaper.setReminderMode(aOIBAConsultPaper.getReminderMode());
        aOIFormConsultPresentPaper.setSecurity(aOIBAConsultPaper.getSecurity());
        aOIFormConsultPresentPaper.setSummary(aOIBAConsultPaper.getSummary());
        aOIFormConsultPresentPaper.setTargetAudiance(aOIBAConsultPaper.getTargetAudiance());
		aOIFormConsultPresentPaper.setContactPerson(aOIBAConsultPaper.getContactPerson());
		aOIFormConsultPresentPaper.setPhone(aOIBAConsultPaper.getPhone());
		aOIFormConsultPresentPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());

		//NEW FIELDS
		aOIFormConsultPresentPaper.setStrInstruction(aOIBAConsultPaper.getStrInstruction());
		aOIFormConsultPresentPaper.setStrCompletionTime(aOIBAConsultPaper.getStrCompletionTime());
		aOIFormConsultPresentPaper.setStrFindingsPlannedDt(aOIBAConsultPaper.getStrFindingsPlannedDt());
		aOIFormConsultPresentPaper.setStrViewFindingType(aOIBAConsultPaper.getStrViewFindingType());
		aOIFormConsultPresentPaper.setPublishStatus(aOIBAConsultPaper.getPublishStatus());
		aOIFormConsultPresentPaper.setStrOpenTag(aOIBAConsultPaper.getStrOpenTag());
		aOIFormConsultPresentPaper.setStrTagWords(aOIBAConsultPaper.getStrTagWords());
		aOIFormConsultPresentPaper.setEmailAddress(aOIBAConsultPaper.getEmailAddress());
		aOIFormConsultPresentPaper.setStrEmailDate(aOIBAConsultPaper.getStrEmailDate());
		aOIFormConsultPresentPaper.setStrEmailMessage(aOIBAConsultPaper.getStrEmailMessage());
		aOIFormConsultPresentPaper.setStrTargetGpIds(aOIBAConsultPaper.getStrTargetGpIds());
		
		aOIFormConsultPresentPaper.setTitle(aOIBAConsultPaper.getTitle());
        if (aOIBAFeedBack != null)
	        if (aOIBAFeedBack.getFeedBack2()!=null)
	            aOIFormConsultPresentPaper.setFeedBack1(aOIBAFeedBack.getFeedBack1()+aOIBAFeedBack.getFeedBack2());
	        else
	            aOIFormConsultPresentPaper.setFeedBack1(aOIBAFeedBack.getFeedBack1());
        OIBAConsultQuestion aOIBAConsultQuestion = null;
        OIFormConsultPresentPaperHelper aOIFormConsultPresentPaperHelper = null;
        OIBAResponse aOIBAResponse = null;
        if (arOIBAConsultQuestion!=null)
        {
            for (int i=0;i<arOIBAConsultQuestion.size();i++)
            {
                aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
                aOIFormConsultPresentPaperHelper = new OIFormConsultPresentPaperHelper();
                aOIFormConsultPresentPaperHelper.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
                aOIFormConsultPresentPaperHelper.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
                aOIFormConsultPresentPaperHelper.setQuestion(aOIBAConsultQuestion.getQuestion());
                aOIFormConsultPresentPaperHelper.setQuestionNo(i+1 + "");
                aOIFormConsultPresentPaperHelper.setAnswer1(aOIBAConsultQuestion.getAnswer1());
                aOIFormConsultPresentPaperHelper.setAnswer2(aOIBAConsultQuestion.getAnswer2());
                aOIFormConsultPresentPaperHelper.setAnswer3(aOIBAConsultQuestion.getAnswer3());
                aOIFormConsultPresentPaperHelper.setAnswer4(aOIBAConsultQuestion.getAnswer4());
                aOIFormConsultPresentPaperHelper.setAnswer5(aOIBAConsultQuestion.getAnswer5());
                aOIFormConsultPresentPaperHelper.setAnswerType(aOIBAConsultQuestion.getAnswerType());
				aOIFormConsultPresentPaperHelper.setLikertScale(aOIBAConsultQuestion.getLikertScale());
				aOIFormConsultPresentPaperHelper.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
                if (arOIBAResponse!=null)
                {
                    for (int j=0;j<arOIBAResponse.size();j++)
                    {
                        aOIBAResponse = (OIBAResponse) arOIBAResponse.get(j);
                        if (aOIBAResponse.getQuestionId() == aOIBAConsultQuestion.getQuestionId())
                        {
                            aOIFormConsultPresentPaperHelper.setResponse1(aOIBAResponse.getResponse1());
                            aOIFormConsultPresentPaperHelper.setResponse2(aOIBAResponse.getResponse2());
                            aOIFormConsultPresentPaperHelper.setResponse3(aOIBAResponse.getResponse3());
                            aOIFormConsultPresentPaperHelper.setResponse4(aOIBAResponse.getResponse4());
                            aOIFormConsultPresentPaperHelper.setResponse5(aOIBAResponse.getResponse5());
                            if (aOIBAConsultQuestion.getNeedOtherRemark()!=null && aOIBAConsultQuestion.getNeedOtherRemark().equals("1"))
                            {
                            	if (aOIBAResponse.getOtherRemarks()!=null)
                            		aOIFormConsultPresentPaperHelper.setOtherRemarks(aOIBAResponse.getOtherRemarks());
                            	else
                            		aOIFormConsultPresentPaperHelper.setOtherRemarks("");
                            }
                        }
                    }
                }
				else if (aOIBAConsultQuestion.getNeedOtherRemark()!=null && aOIBAConsultQuestion.getNeedOtherRemark().equals("1"))
				{
					aOIFormConsultPresentPaperHelper.setOtherRemarks("");
				}
                aOIFormConsultPresentPaper.addArOIFormConsultPresentPaperHelper(aOIFormConsultPresentPaperHelper);
            }
        }
        if (aOIBADraft!=null)
            aOIFormConsultPresentPaper.setStatus(aOIBADraft.getStatus());
        
        request.setAttribute(OIConsultConstant.CONSULT_PRESENT_PAPER_FORM_WEB,aOIFormConsultPresentPaper);

        request.setAttribute(OILoginConstants.PAGENAME,"ConsultOpenPaper");
        if (request.getAttribute(PRINT_ACTION)!=null)
            return mapping.findForward(PRINT_ACTION);
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    /**
     * This method calls the saveConsultPaperDetail of OIBOConsultWeb 
     */
    public ActionForward saveAsDraft(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String paperId = (String) request.getParameter(OIConsultConstant.K_paperId);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);

        OIFormConsultPresentPaper aOIFormConsultPresentPaper = (OIFormConsultPresentPaper) form;
        OIResponseObject aOIResponseObject = new OIBOConsultWeb().readQuetions(Integer.parseInt(paperId));
        
        ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
        OIBAResponse aOIBAResponse = null;
        ArrayList arOIBAResponse = new ArrayList();
        
        if (arOIBAConsultQuestion!=null)
        {
            for(int i=0;i<arOIBAConsultQuestion.size();i++)
            {
                OIBAConsultQuestion aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
                int questionId = aOIBAConsultQuestion.getQuestionId();
                String str[] = request.getParameterValues(questionId + "");
                String otherRemarks = request.getParameter("otherRemarks" + questionId);
                //logger.info(questionId + "");
                if (str!=null && str.length>0)
                {
                    aOIBAResponse = new OIBAResponse();
                    aOIBAResponse.setQuestionId(questionId);
                    aOIBAResponse.setUserId(userId);
//                    aOIBAResponse.setOtherRemarks(otherRemarks);
                    
                    for (int k=0;k<str.length;k++)
	                {
	                    if (str[k].equals("answer1"))
	                    {
	                        aOIBAResponse.setResponse1("1");
	                    }
	                    if (str[k].equals("answer2"))
	                    {
	                        aOIBAResponse.setResponse2("1");
	                    }
	                    if (str[k].equals("answer3"))
	                    {
	                        aOIBAResponse.setResponse3("1");
	                    }
	                    if (str[k].equals("answer4"))
	                    {
	                        aOIBAResponse.setResponse4("1");
	                    }
	                    if (str[k].equals("answer5"))
	                    {
	                        aOIBAResponse.setResponse5("1");
	                    }
	                }
                    
                    aOIBAResponse.setOtherRemarks((aOIBAResponse.getResponse5() == null || aOIBAResponse.getResponse5().trim().length() == 0)?"":otherRemarks);
                }
//                if (str!=null && str.length>0)
//	                for (int k=0;k<str.length;k++)
//	                {
//	                    if (str[k].equals("answer1"))
//	                    {
//	                        aOIBAResponse.setResponse1("1");
//	                    }
//	                    if (str[k].equals("answer2"))
//	                    {
//	                        aOIBAResponse.setResponse2("1");
//	                    }
//	                    if (str[k].equals("answer3"))
//	                    {
//	                        aOIBAResponse.setResponse3("1");
//	                    }
//	                    if (str[k].equals("answer4"))
//	                    {
//	                        aOIBAResponse.setResponse4("1");
//	                    }
//	                    if (str[k].equals("answer5"))
//	                    {
//	                        aOIBAResponse.setResponse5("1");
//	                    }
//	                }
                if (aOIBAResponse!=null)
                    arOIBAResponse.add(aOIBAResponse);
            }
        }
        
        OIBAFeedBack aOIBAFeedBack = new OIBAFeedBack();
        logger.info(aOIFormConsultPresentPaper.getFeedBack1().length() + "");
        if (aOIFormConsultPresentPaper.getFeedBack1().length()>4000)
        {
            aOIBAFeedBack.setFeedBack1(aOIFormConsultPresentPaper.getFeedBack1().substring(0,4000));
            aOIBAFeedBack.setFeedBack2(aOIFormConsultPresentPaper.getFeedBack1().substring(4000));
        }
        else
        {
            aOIBAFeedBack.setFeedBack1(aOIFormConsultPresentPaper.getFeedBack1());
        }
        aOIBAFeedBack.setPaperId(Integer.parseInt(paperId));
        aOIBAFeedBack.setUserId(userId);
        
        aOIResponseObject = new OIBOConsultWeb().saveConsultPaperDetail(arOIBAResponse,aOIBAFeedBack);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }

        return new ActionForward("/webConsultListingAction.do?hiddenAction=populate");
    }
    
    /**
     * This method calls the submitConsultPaperDetail of OIBOConsultWeb 
     */
    public ActionForward submit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String paperId = (String) request.getParameter(OIConsultConstant.K_paperId);
        String userId = (String) getSessionAttribute(request,OILoginConstants.USER_ID);
        OIFormConsultPresentPaper aOIFormConsultPresentPaper = (OIFormConsultPresentPaper) form;
        OIResponseObject aOIResponseObject = new OIBOConsultWeb().readQuetions(Integer.parseInt(paperId));
        
        ArrayList arOIBAConsultQuestion = (ArrayList) aOIResponseObject.getResponseObject(OIConsultConstant.K_arOIBAConsultQuestion);
        OIBAResponse aOIBAResponse = null;
        ArrayList arOIBAResponse = new ArrayList();
        
        if (arOIBAConsultQuestion!=null)
        {
            for(int i=0;i<arOIBAConsultQuestion.size();i++)
            {
                OIBAConsultQuestion aOIBAConsultQuestion = (OIBAConsultQuestion) arOIBAConsultQuestion.get(i);
                int questionId = aOIBAConsultQuestion.getQuestionId();
                String str[] = request.getParameterValues(questionId + "");
                String otherRemarks = request.getParameter("otherRemarks" + questionId);
                //logger.info(questionId + "");
                if (str!=null && str.length>0)
                {
                    aOIBAResponse = new OIBAResponse();
                    aOIBAResponse.setQuestionId(questionId);
                    aOIBAResponse.setUserId(userId);
//                    aOIBAResponse.setOtherRemarks(otherRemarks);
                    
                    for (int k=0;k<str.length;k++)
	                {
	                    if (str[k].equals("answer1"))
	                    {
	                        aOIBAResponse.setResponse1("1");
	                    }
	                    if (str[k].equals("answer2"))
	                    {
	                        aOIBAResponse.setResponse2("1");
	                    }
	                    if (str[k].equals("answer3"))
	                    {
	                        aOIBAResponse.setResponse3("1");
	                    }
	                    if (str[k].equals("answer4"))
	                    {
	                        aOIBAResponse.setResponse4("1");
	                    }
	                    if (str[k].equals("answer5"))
	                    {
	                        aOIBAResponse.setResponse5("1");
	                    }
	                }
                    
                    aOIBAResponse.setOtherRemarks((aOIBAResponse.getResponse5() == null || aOIBAResponse.getResponse5().trim().length() == 0)?"":otherRemarks);
                }
//                if (str!=null && str.length>0)
//	                for (int k=0;k<str.length;k++)
//	                {
//	                    if (str[k].equals("answer1"))
//	                    {
//	                        aOIBAResponse.setResponse1("1");
//	                    }
//	                    if (str[k].equals("answer2"))
//	                    {
//	                        aOIBAResponse.setResponse2("1");
//	                    }
//	                    if (str[k].equals("answer3"))
//	                    {
//	                        aOIBAResponse.setResponse3("1");
//	                    }
//	                    if (str[k].equals("answer4"))
//	                    {
//	                        aOIBAResponse.setResponse4("1");
//	                    }
//	                    if (str[k].equals("answer5"))
//	                    {
//	                        aOIBAResponse.setResponse5("1");
//	                    }
//	                }
                if (aOIBAResponse!=null)
                    arOIBAResponse.add(aOIBAResponse);
            }
        }
        
        OIBAFeedBack aOIBAFeedBack = new OIBAFeedBack();
        if (aOIFormConsultPresentPaper.getFeedBack1().length()>4000)
        {
            aOIBAFeedBack.setFeedBack1(aOIFormConsultPresentPaper.getFeedBack1().substring(0,4000));
            aOIBAFeedBack.setFeedBack2(aOIFormConsultPresentPaper.getFeedBack1().substring(4000));
        }
        else
        {
            aOIBAFeedBack.setFeedBack1(aOIFormConsultPresentPaper.getFeedBack1());
        }
        aOIBAFeedBack.setPaperId(Integer.parseInt(paperId));
        aOIBAFeedBack.setUserId(userId);
        
        aOIResponseObject = new OIBOConsultWeb().submitConsultPaperDetail(arOIBAResponse,aOIBAFeedBack);
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            return new ActionForward(OILoginConstants.ERRORPAGE);
        }
        String message=null,contextroot=null;
        try
        {
            message = OIDBRegistry.getValues("OI.CONS.THANKS");
        }catch(Exception e){}
        try
        {
            contextroot = OIDBRegistry.getValues("OIFM.contextroot");
        }catch(Exception e){}
        request.setAttribute(OILoginConstants.PAGENAME,"ThankFeedBack");
        request.setAttribute("title",aOIFormConsultPresentPaper.getTitle());
        request.setAttribute("content",message);
        request.setAttribute("URL", contextroot + "/webConsultListingAction.do?hiddenAction=populate");
        //return new ActionForward("/webConsultListingAction.do?hiddenAction=populate");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
