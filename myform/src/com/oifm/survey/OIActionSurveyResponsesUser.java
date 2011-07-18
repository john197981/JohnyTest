
package com.oifm.survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionSurveyResponsesUser extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionSurveyResponsesUser.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)	{
    	
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	ArrayList alFunctions = null;
    	//HttpSession session = null;
    	boolean isSurveyMaintain = false;
    	boolean isGDSUser = false;
    	OIBOSurveyUser objBOSurvey = null;
    	OIResponseObject objResponseObject = null;
    	OIBAQuestionResponse objQuestionResponseVo = new OIBAQuestionResponse();
    	OIFormQuestionResponse objQuestionResponse = (OIFormQuestionResponse)form;
    	strAction = (strAction != null)? strAction: OISurveyConstants.SURVEY_USER_LIST;

    	try {
			//session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
			isGDSUser = alFunctions.contains(OIFunctionConstants.GDS_USER);
			objQuestionResponse.setStrUserId(strUserId);
			objBOSurvey = new OIBOSurveyUser(isGDSUser);

			if(strAction.equals(OISurveyConstants.SECTION_QST_RESP_DTLS)) {
				copyProperties(objQuestionResponseVo, objQuestionResponse);
logger.debug("VO getResponseList() : "+ objQuestionResponseVo.getResponseList());
				objResponseObject = objBOSurvey.getSectionQstRespDetails(objQuestionResponseVo);
				if(objResponseObject.getResponseObject("error") == null) {
					request.setAttribute("SectionDetails", objResponseObject.getResponseObject("SectionDetails"));
					request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
					request.setAttribute("ResponsesSet", objResponseObject.getResponseObject("ResponseList"));
					request.setAttribute("isUserSubmitted", objResponseObject.getResponseObject("isUserSubmitted"));
					request.setAttribute("pageName", "SectionQuestionsResponses");
					strForward = OISurveyConstants.USER_PAGE;
				} else strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");

			} else if(strAction.equals(OISurveyConstants.SURVEY_PREVIEW)) {
				
				copyProperties(objQuestionResponseVo, objQuestionResponse);
				objResponseObject = objBOSurvey.getSectionPreviewDetails(objQuestionResponseVo);
				request.setAttribute("SectionDetails", objResponseObject.getResponseObject("SectionDetails"));
				request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
				request.setAttribute("ResponsesSet", objResponseObject.getResponseObject("ResponseList"));
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strForward = OISurveyConstants.USER_PAGE; 

			} 
			else if(strAction.equals(OISurveyConstants.SURVEY_PRINT_PREVIEW)) {
				String strSurveyId = (String)request.getParameter("strSurveyId");
				logger.error("strSurveyId==="+strSurveyId);
				if(strSurveyId!=null && !strSurveyId.equals(""))
				{
					objResponseObject = objBOSurvey.getSurveyDetailPreview(strSurveyId);
					OIBASurvey objSurveyDetails = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
					request.setAttribute("objSurveyVo", objSurveyDetails);
					
					logger.error("pt1");
					ArrayList sectionList = objBOSurvey.getSections(strSurveyId);
					logger.error("pt2"+sectionList.size());
					request.setAttribute("sectionSize",String.valueOf(sectionList.size()));
					if(sectionList!=null){
						for(int i=0;i<sectionList.size();i++)
						{
							String strSectionId = (String)sectionList.get(i);
							objQuestionResponse.setStrSectionId(strSectionId);
							copyProperties(objQuestionResponseVo, objQuestionResponse);
							objResponseObject = objBOSurvey.getSectionPreviewDetails(objQuestionResponseVo);
							request.setAttribute("SectionDetails_"+i, objResponseObject.getResponseObject("SectionDetails"));
							request.setAttribute("QuestionList_"+i, objResponseObject.getResponseObject("QuestionList"));
							request.setAttribute("ResponsesSet_"+i, objResponseObject.getResponseObject("ResponseList"));
						}
					}
				}
				
				if (objResponseObject!=null)
				{
					if(objResponseObject.getResponseObject("error") != null)
					{
						strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
					}
					else
					{	
						strForward = OISurveyConstants.PRINT_PREVIEW_PAGE;
					}
				}
				else
				{	
					strForward = OISurveyConstants.PRINT_PREVIEW_PAGE;
				}
			} 
			else if(strAction.equals(OISurveyConstants.SECTION_QST_RESP_SAVE)) {
				copyProperties(objQuestionResponseVo, objQuestionResponse);
logger.debug("VO getResponseList() : "+ objQuestionResponseVo.getResponseList());
				objResponseObject = objBOSurvey.saveQstRespDetails(objQuestionResponseVo);
				request.setAttribute("SectionDetails", objResponseObject.getResponseObject("SectionDetails"));
				request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
				request.setAttribute("ResponsesSet", objResponseObject.getResponseObject("ResponseList"));
				request.setAttribute("isCloseScreen", objResponseObject.getResponseObject("isCloseScreen"));
				request.setAttribute("isUserSubmitted", objResponseObject.getResponseObject("isUserSubmitted"));
				setSectionInfo((OIBASection)objResponseObject.getResponseObject("SectionDetails"), objQuestionResponse);
				request.setAttribute("pageName", "SectionQuestionsResponses");
				strForward = OISurveyConstants.USER_PAGE;

			} 
		} catch(Exception e) {
			e.printStackTrace();
	    	strRedirect = OISurveyConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally 
		{
			if (strForward!=null && objResponseObject!=null)
			{
				if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
					request.setAttribute("error", objResponseObject.getResponseObject("error"));
			}
		}
		if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}	
 
	private void copyProperties(OIBAQuestionResponse objQuestionResponseVo, OIFormQuestionResponse objQuestionResponse) {
		objQuestionResponseVo.setStrSurveyId(objQuestionResponse.getStrSurveyId());
		objQuestionResponseVo.setStrUserId(objQuestionResponse.getStrUserId());
		objQuestionResponseVo.setStrSectionId(objQuestionResponse.getStrSectionId());
		objQuestionResponseVo.setResponseList(objQuestionResponse.getResponseList());
		objQuestionResponseVo.setStrNextSectionId(objQuestionResponse.getStrNextSectionId());
		objQuestionResponseVo.setStrUserId(objQuestionResponse.getStrUserId());
		
	}

	private void setSectionInfo(OIBASection objSection, OIFormQuestionResponse objQuestionResponse) {
		if(objSection != null) {
			objQuestionResponse.setStrSectionId(objSection.getStrSectionId());
			objQuestionResponse.setStrNextSectionId(objSection.getStrNextSecId());
		}
	}
	
}
