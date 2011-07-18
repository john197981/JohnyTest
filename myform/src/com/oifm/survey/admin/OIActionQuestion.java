
package com.oifm.survey.admin;

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
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OIBAQuestion;
import com.oifm.survey.OIFormQuestion;
import com.oifm.survey.OISurveyConstants;


public class OIActionQuestion extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionQuestion.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)	{
    	 
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	String strDivCode = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	boolean isSurveyMaintain = false;
    	OIBOQuestionAdmin objBOQuestion = null;
    	OIResponseObject objResponseObject = null;
    	OIBAQuestion objQuestionVo = new OIBAQuestion();
		OIBAQuestion objPrevQuestionVo = new OIBAQuestion();
    	OIFormQuestion objQuestion = (OIFormQuestion)form;
    	boolean isMainAdmin = false;
    	strAction = (strAction != null)? strAction: OISurveyConstants.QUESTION_LIST;

    	try {
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isMainAdmin = (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && 
					alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) );
			isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
			objQuestion.setStrDivisionCode(strDivCode);

			if(isSurveyMaintain) {
				objBOQuestion = new OIBOQuestionAdmin();

				if(strAction.equals(OISurveyConstants.QUESTION_LIST))	{
					PropertyUtils.copyProperties(objQuestionVo, objQuestion);
					objResponseObject = objBOQuestion.getQuestionList(objQuestionVo);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
					if(!isMainAdmin)
						request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					else request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("pageName", "QuestionList");
					strForward = OISurveyConstants.LIST_PAGE;

				} else if(strAction.equals(OISurveyConstants.QUESTION_EDIT)) {
					PropertyUtils.copyProperties(objQuestionVo, objQuestion);
					objResponseObject = objBOQuestion.getQuestionDetails(objQuestionVo, objPrevQuestionVo);
					objQuestionVo = (OIBAQuestion)objResponseObject.getResponseObject("objQuestionVo");
					if(objQuestionVo != null) PropertyUtils.copyProperties(objQuestion, objQuestionVo);
					request.setAttribute("pageName", "QuestionEdit");
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("QuestionTypeIds", OISurveyConstants.QUESTION_TYPE_ID);
					request.setAttribute("QuestionTypeText", OISurveyConstants.QUESTION_TYPE_TEXT);
					request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
					request.setAttribute("objPrevQuestion",objResponseObject.getResponseObject("objPrevQuestionVo"));
					if(objQuestionVo!=null)
					{
						request.setAttribute("strSectionId",objQuestionVo.getStrSectionId());
					}
					if(!isMainAdmin)
						request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					else request.setAttribute("isSurveyDivision", new Boolean(true));
					logger.error("before edit page");
					strForward = OISurveyConstants.QST_EDIT_PAGE;

				} else if(strAction.equals(OISurveyConstants.QUESTION_SAVE)) {
					PropertyUtils.copyProperties(objQuestionVo, objQuestion);
					objResponseObject = objBOQuestion.saveQuestionDetails(objQuestionVo, objPrevQuestionVo);
					if((objQuestion.getStrQuestionId() == null || objQuestion.getStrQuestionId().equals("")) &&
						(objResponseObject.getResponseObject("error") == null || ((String)objResponseObject.getResponseObject("error")).equals(""))) {
						if(objQuestion.getStrMakeTemplate() != null && objQuestion.getStrMakeTemplate().equals("Y")) 
							initializeTemplateQuestion(objQuestion);
						else initializeQuestion(objQuestion);
					}
					request.setAttribute("QuestionTypeIds", OISurveyConstants.QUESTION_TYPE_ID);
					request.setAttribute("QuestionTypeText", OISurveyConstants.QUESTION_TYPE_TEXT);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("isCurrentlyValid", new Boolean(true));
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("objPrevQuestion",objResponseObject.getResponseObject("objPrevQuestionVo"));
					if(objQuestionVo!=null)
					{
						request.setAttribute("strSectionId",objQuestionVo.getStrSectionId());
					}
					request.setAttribute("pageName", "QuestionEdit");
					strForward = OISurveyConstants.QST_EDIT_PAGE;
	
				} else if(strAction.equals(OISurveyConstants.QUESTION_DELETE)) {
					PropertyUtils.copyProperties(objQuestionVo, objQuestion);
					objResponseObject = objBOQuestion.deleteQuestionInfo(objQuestionVo, objPrevQuestionVo);
					request.setAttribute("QuestionTypeIds", OISurveyConstants.QUESTION_TYPE_ID);
					request.setAttribute("QuestionTypeText", OISurveyConstants.QUESTION_TYPE_TEXT);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("isCurrentlyValid", new Boolean(true));
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("objPrevQuestion",objResponseObject.getResponseObject("objPrevQuestionVo"));
					request.setAttribute("strSectionId",objQuestionVo.getStrSectionId());
					request.setAttribute("pageName", "QuestionEdit");
					strForward = OISurveyConstants.QST_EDIT_PAGE;

				} 
				 else if(strAction.equals(OISurveyConstants.QUESTION_MOVE_UP) || strAction.equals(OISurveyConstants.QUESTION_MOVE_DOWN)) {
					PropertyUtils.copyProperties(objQuestionVo, objQuestion);
logger.error("question id == "+objQuestionVo.getStrQuestionId());
					objResponseObject = objBOQuestion.reorderQuestion(objQuestionVo,strAction);
					objResponseObject = objBOQuestion.getQuestionList(objQuestionVo);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
					if(!isMainAdmin)
						request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					else request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("pageName", "QuestionList");
					strForward = OISurveyConstants.LIST_PAGE;

				}
		    } else {
		    	strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
		    }
		} catch(Exception e) {
			e.printStackTrace();
	    	strRedirect = OISurveyConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally {
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
		if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else 
			return (mapping.findForward(strForward));
	}	
 
	private void initializeTemplateQuestion(OIFormQuestion objQuestion)	{
logger.debug(" in method initializeTemplateQuestion() ");
		objQuestion.setStrQuestionId("");
		objQuestion.setStrQuestion("");
		objQuestion.setStrInstruction("");
	}

	private void initializeQuestion(OIFormQuestion objQuestion)	{
logger.debug(" in method initializeQuestion() ");
		objQuestion.setStrQuestionId("");
		objQuestion.setStrQuestion("");
		objQuestion.setStrInstruction("");
		objQuestion.setStrMakeTemplate("");
		objQuestion.setStrQuestionType("");
		objQuestion.setStrOtherRemarks("");
		objQuestion.setStrOptional("");
		objQuestion.setStrAnswer1("");
		objQuestion.setStrAnswer2("");
		objQuestion.setStrAnswer3("");
		objQuestion.setStrAnswer4("");
		objQuestion.setStrAnswer5("");
		objQuestion.setStrAnswer6("");
		objQuestion.setStrAnswer7("");
		objQuestion.setStrAnswer8("");
		objQuestion.setStrAnswer9("");
		objQuestion.setStrAnswer10("");
		objQuestion.setStrUseSameLikert("");
	}
	
}
