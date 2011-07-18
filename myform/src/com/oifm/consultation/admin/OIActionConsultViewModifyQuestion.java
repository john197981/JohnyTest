package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			05/07/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBRegistry;
public class OIActionConsultViewModifyQuestion extends OIBaseAction 
{
    Logger logger=Logger.getLogger(OIActionConsultViewModifyQuestion.class.getName());
    private static final String POPULATE_ACTION = "populate";
    private static final String SAVE_ACTION = "save";

    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String actionName)
    {
        if (actionName!=null)
        {
	        if (actionName.equals(POPULATE_ACTION))
	        {
	            return populate(mapping, form, request, response);
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
    
    /**
     * This method calls the readQuestion of OIBOConsultQuestion, which passes QuestionID as parameter 
     */
    public ActionForward populate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        String questionId = (String) request.getParameter(OIConsultConstant.K_questionId);

        OIResponseObject aOIResponseObject = new OIBOConsultQuestion().readQuestion(Integer.parseInt(questionId));
        OIBAConsultQuestion aOIBAConsultQuestion = (OIBAConsultQuestion) aOIResponseObject.getResponseObject(OIConsultConstant.K_aOIBAConsultQuestion);
        
        OIFormConsultQuestion aOIFormConsultQuestion = new OIFormConsultQuestion();
        aOIFormConsultQuestion.setPaperId(aOIBAConsultQuestion.getPaperId() + "");
        aOIFormConsultQuestion.setQuestionId(aOIBAConsultQuestion.getQuestionId() + "");
        aOIFormConsultQuestion.setQuestion(aOIBAConsultQuestion.getQuestion());
        aOIFormConsultQuestion.setAnswerType(aOIBAConsultQuestion.getAnswerType());
        aOIFormConsultQuestion.setAnswer1(aOIBAConsultQuestion.getAnswer1());
        aOIFormConsultQuestion.setAnswer2(aOIBAConsultQuestion.getAnswer2());
        aOIFormConsultQuestion.setAnswer3(aOIBAConsultQuestion.getAnswer3());
        aOIFormConsultQuestion.setAnswer4(aOIBAConsultQuestion.getAnswer4());
        aOIFormConsultQuestion.setAnswer5(aOIBAConsultQuestion.getAnswer5());
        if (aOIBAConsultQuestion.getAnswerType()!= null && aOIBAConsultQuestion.getAnswerType().equals("1"))
        {
            aOIFormConsultQuestion.setAnswerType("on");
        }
        else
        {
            aOIFormConsultQuestion.setAnswerType("off");
        }
        if (aOIBAConsultQuestion.getNeedOtherRemark()!= null && aOIBAConsultQuestion.getNeedOtherRemark().equals("1"))
        {
            aOIFormConsultQuestion.setNeedOtherRemark("on");
        }
        else
        {
            aOIFormConsultQuestion.setNeedOtherRemark("off");
        }
		if (aOIBAConsultQuestion.getLikertScale()!= null && aOIBAConsultQuestion.getLikertScale().equals("1"))
        {
            aOIFormConsultQuestion.setLikertScale("on");
        }
        else
        {
            aOIFormConsultQuestion.setLikertScale("off");
        }
		if (aOIBAConsultQuestion.getUseSameLikert()== null )
        {
            aOIFormConsultQuestion.setUseSameLikert("");
        }
        else 
        {
            aOIFormConsultQuestion.setUseSameLikert(aOIBAConsultQuestion.getUseSameLikert());
        }
		request.setAttribute("objPreviousQuestion", new OIBOConsultQuestion().getPreviousQuestion(questionId,aOIBAConsultQuestion.getPaperId() + ""));
        request.setAttribute(OIConsultConstant.CONSULT_QUESTION_FORM,aOIFormConsultQuestion);
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
    
    /**
     * This method will call the OIBOConsultQuestion.save() & pass OIBAConsultQuestion as the input parameter
     * 
     * If save is unsuccessful then the error has to be shown in the same page
     * 
     * Otherwise it should display the Consultation Paper. 
     */
    public ActionForward save(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        OIFormConsultQuestion aOIFormConsultQuestion = (OIFormConsultQuestion) form;
        OIBAConsultQuestion aOIBAConsultQuestion = new OIBAConsultQuestion();
        aOIBAConsultQuestion.setPaperId(Integer.parseInt(aOIFormConsultQuestion.getPaperId()));
        aOIBAConsultQuestion.setQuestionId(Integer.parseInt(aOIFormConsultQuestion.getQuestionId()));
        aOIBAConsultQuestion.setQuestion(aOIFormConsultQuestion.getQuestion());
        logger.info(aOIFormConsultQuestion.getAnswerType());
        if (aOIFormConsultQuestion.getAnswerType()!= null && aOIFormConsultQuestion.getAnswerType().equals("on"))
        {
            aOIBAConsultQuestion.setAnswerType("1");
        }
        else
        {
            aOIBAConsultQuestion.setAnswerType("0");
        }

        aOIBAConsultQuestion.setAnswer1(aOIFormConsultQuestion.getAnswer1());
        aOIBAConsultQuestion.setAnswer2(aOIFormConsultQuestion.getAnswer2());
        aOIBAConsultQuestion.setAnswer3(aOIFormConsultQuestion.getAnswer3());
        aOIBAConsultQuestion.setAnswer4(aOIFormConsultQuestion.getAnswer4());
        aOIBAConsultQuestion.setAnswer5(aOIFormConsultQuestion.getAnswer5());
        if (aOIFormConsultQuestion.getNeedOtherRemark()!= null && aOIFormConsultQuestion.getNeedOtherRemark().equals("on"))
        {
            aOIBAConsultQuestion.setNeedOtherRemark("1");
        }
        else
        {
            aOIBAConsultQuestion.setNeedOtherRemark("0");
        }
		if (aOIFormConsultQuestion.getLikertScale()!= null && aOIFormConsultQuestion.getLikertScale().equals("on"))
        {
            aOIBAConsultQuestion.setLikertScale("1");
        }
        else
        {
            aOIBAConsultQuestion.setLikertScale("0");
        }
		if (aOIFormConsultQuestion.getUseSameLikert()== null)
        {
            aOIBAConsultQuestion.setUseSameLikert("");
        }
		else
		{
			aOIBAConsultQuestion.setUseSameLikert(aOIFormConsultQuestion.getUseSameLikert());
		}
      
        OIResponseObject aOIResponseObject = new OIBOConsultQuestion().saveQuestion(aOIBAConsultQuestion);
        
        ArrayList messageList = (ArrayList) aOIResponseObject.getResponseObject(OILoginConstants.K_MESSAGE);
        String error = (String) aOIResponseObject.getResponseObject(OILoginConstants.K_ERROR);
        if (error != null)
        {
            request.setAttribute(OILoginConstants.K_ERROR,error);
            request.setAttribute(OIConsultConstant.CONSULT_QUESTION_FORM,aOIFormConsultQuestion);
            return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
        }

        request.setAttribute(OIConsultConstant.CONSULT_QUESTION_FORM,aOIFormConsultQuestion);
        request.setAttribute(OIConsultConstant.K_refresh,"ok");
        return mapping.findForward(OIConsultConstant.POPULATE_CONSULTLISTING);
    }
}
