
package com.oifm.survey;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIActionHelper;
import com.oifm.utility.OIUtilities;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;

public class OIActionSurveyUser extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionSurveyUser.class);
	
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
    	OIBASurveySection objSurveySectionVo = new OIBASurveySection();
    	OIFormSurveySection objSurveySection = (OIFormSurveySection)form;
    	strAction = (strAction != null)? strAction: OISurveyConstants.SURVEY_USER_LIST;
    	System.out.println("strAction is"+strAction);
    	try {
			//session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
			isGDSUser = alFunctions.contains(OIFunctionConstants.GDS_USER);
	    	objSurveySection.setStrUserId(strUserId);
			objBOSurvey = new OIBOSurveyUser(isGDSUser);

			if(strAction.equals(OISurveyConstants.SURVEY_USER_LIST)){
				PropertyUtils.copyProperties(objSurveySectionVo, objSurveySection);
				objResponseObject = objBOSurvey.getSurveyList(objSurveySectionVo);
				request.setAttribute("CurrSurveyList", objResponseObject.getResponseObject("CurrSurveyList"));
				request.setAttribute("PastSurveyList", objResponseObject.getResponseObject("PastSurveyList"));
				request.setAttribute("pageName", "UserSurveyList");
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strForward = OISurveyConstants.USER_PAGE; 

			} else if(strAction.equals(OISurveyConstants.SURVEY_USER_SUBMIT)){
				PropertyUtils.copyProperties(objSurveySectionVo, objSurveySection);
				String emailUser = (String)getSessionAttribute(request, OILoginConstants.EMAIL_USER);
				objResponseObject = objBOSurvey.updateSurveyUserSubmit(objSurveySectionVo,emailUser);
				request.setAttribute("pageName", "ThankFeedBack");
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else {
					request.setAttribute("title", "Survey");
					request.setAttribute("content", "We value your views and suggestions. Thank you for your feedback");
					if(isGDSUser)
						request.setAttribute("URL", OIUtilities.getUploadFilesDir("OIFM.contextroot")+"/UserSurvey.do?hiddenAction="+OISurveyConstants.SURVEY_USER_LIST);
					strForward = OISurveyConstants.USER_PAGE; 
				}

			} else if(strAction.equals(OISurveyConstants.SURVEY_DETAILS)) {
				//System.out.println("Inside Survey details");
				
				PropertyUtils.copyProperties(objSurveySectionVo, objSurveySection);
				objResponseObject = objBOSurvey.getSurveyDetails(objSurveySectionVo);
				if(objResponseObject.getResponseObject("error") == null) {
					OIBASurvey objSurveyDetails = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
					request.setAttribute("objSurveyVo", objSurveyDetails);
					if(objSurveyDetails.getStrPublishedStatus() != null && objSurveyDetails.getStrPublishedStatus().equals("N")) {
						request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
						request.setAttribute("compPercent", objResponseObject.getResponseObject("compPercent"));
						request.setAttribute("pageName", "CurrSurveyDetails");
						request.setAttribute("isGDSUser", new Boolean(isGDSUser));
					} else {
						request.setAttribute("pageName", "PastSurveyDetails");
					}
					strForward = OISurveyConstants.USER_PAGE;
				} else
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");

			} else if(strAction.equals(OISurveyConstants.SURVEY_PREVIEW)) {
				PropertyUtils.copyProperties(objSurveySectionVo, objSurveySection);
				objResponseObject = objBOSurvey.getSurveyPreview(objSurveySectionVo);
				OIBASurvey objSurveyDetails = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
				request.setAttribute("objSurveyVo", objSurveyDetails);
				request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strForward = OISurveyConstants.PREVIEW_PAGE;

			}
			else if(strAction.equals(OISurveyConstants.SURVEY_PRINT_PREVIEW)) {
				
				PropertyUtils.copyProperties(objSurveySectionVo, objSurveySection);
				objResponseObject = objBOSurvey.getSurveyPreview(objSurveySectionVo);
				OIBASurvey objSurveyDetails = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
				request.setAttribute("objSurveyVo", objSurveyDetails);
				request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));

				OIBAQuestionResponse objQuestionResponseVo = new OIBAQuestionResponse();
				PropertyUtils.copyProperties(objQuestionResponseVo, objSurveySection);
				objResponseObject = objBOSurvey.getSectionPreviewDetails(objQuestionResponseVo);
				request.setAttribute("SectionDetails", objResponseObject.getResponseObject("SectionDetails"));
				request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
				request.setAttribute("ResponsesSet", objResponseObject.getResponseObject("ResponseList"));
				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");

				if(objResponseObject.getResponseObject("error") != null)
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				else strForward = OISurveyConstants.PRINT_PREVIEW_PAGE;

			} 
			else if(strAction.equals(OISurveyConstants.SURVEY_DOWNLOAD)){
				PropertyUtils.copyProperties(objSurveySectionVo, objSurveySection);
				objResponseObject = objBOSurvey.getDownloadFileInfo(objSurveySectionVo);
				String strFileName = "";
				boolean dnldFile = false;
				if(objResponseObject.getResponseObject("error") == null) {
					strFileName = (String)objResponseObject.getResponseObject("strFileName");
					if(strFileName != null && !strFileName.equals("")) { 
						dnldFile = (new OIActionHelper()).downloadFile(response, OISurveyConstants.SURVEY_UPLOAD_DIR , strFileName, "Survey_"+objSurveySection.getStrSurveyId()+".pdf");
						if(!dnldFile) 
							strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
					} else strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
				} else {
					//System.out.println("OIActionSurveyUser: doIt - var : "+123);
					strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
				}

			} else strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
		} catch(Exception e) {
			e.printStackTrace();
	    	strRedirect = OISurveyConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally {
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
		
//		if(strAction.equals(OISurveyConstants.SURVEY_DOWNLOAD))
//			return null;
//		else 
			if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}	
 
    
}
