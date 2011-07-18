/*
Created by edmund

*/
package com.oifm.survey.admin;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oifm.base.OIBaseAction;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OIBAResult;
import com.oifm.survey.OIFormResult;
import com.oifm.survey.OISurveyConstants;

public class OIActionResult extends OIBaseAction{

	private static Logger logger = Logger.getLogger(OIActionQuestion.class);
	
	public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)	{
		
		String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	String strDivCode = "";
    	ArrayList alFunctions = null;
    	boolean isSurveyMaintain = false;
    	OIBOResultAdmin objBOResult = null;
    	OIResponseObject objResponseObject = null;
    	OIBAResult objResultVo = new OIBAResult();
    	OIFormResult objResult = (OIFormResult)form;
    	boolean isMainAdmin = false;
    	strAction = (strAction != null)? strAction: OISurveyConstants.RESULT_TYPE;
    	//System.out.println("OIActionResult: doIt - var2 : "+OISurveyConstants.RESULT_TYPE);
		
    	try {
    		strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
    		strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
    		alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
    		isMainAdmin = (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && 
    				alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) );
    		isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
    		objResult.setStrDivisionCode(strDivCode);
    		
    		if(isSurveyMaintain) {
				objBOResult = new OIBOResultAdmin();
				if(strAction.equals(OISurveyConstants.RESULT_TYPE))	{
					//System.out.println("OIActionResult: doIt - var : "+OISurveyConstants.RESULT_TYPE);
					/*PropertyUtils.copyProperties(objResultVo, objResult);
					System.out.println("OIActionResult: doIt - var : "+objResultVo.getStrSurveyId());
					System.out.println("OIActionResult: doIt - var : "+objResult.getStrSurveyId());*/
					objResponseObject = objBOResult.getResultList(objResultVo);
				/*request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("QuestionList", objResponseObject.getResponseObject("QuestionList"));
					if(!isMainAdmin)
						request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					else request.setAttribute("isSurveyDivision", new Boolean(true));*/
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("pageName", "ResultType");
					strForward = OISurveyConstants.LIST_PAGE;
					
				/*} else if (strAction.equals(OISurveyConstants.RESULT_TYPE_SUMMARY)){
					PropertyUtils.copyProperties(objResultVo, objResult);
					
					request.setAttribute("pageName", "SurveyResultSummary");
					strForward = OISurveyConstants.LIST_PAGE;
					
				} else if (strAction.equals(OISurveyConstants.RESULT_TYPE_DETAIL)){
					PropertyUtils.copyProperties(objResultVo, objResult);
					objResponseObject = objBOResult.getResultList(objResultVo);
					
					request.setAttribute("pageName", "SurveyResultDetail");
					strForward = OISurveyConstants.LIST_PAGE;
					
				} else if (strAction.equals("selection")){
					PropertyUtils.copyProperties(objResultVo, objResult);
					String strSurveyId = objResult.getStrSurveyId();
					System.out.println("OIActionResult: doIt - var : "+strSurveyId);
					System.out.println("OIActionResult: doIt - var : "+objResult.getStrAge());
					objResponseObject = objBOResult.getSurveyResultDetail(objResultVo);
					request.setAttribute("pageName", "SurveyResultDetail");
					strForward = OISurveyConstants.LIST_PAGE;
					
				} else if (strAction.equals(OISurveyConstants.RESULT_TYPE_RESPONDENTS)){
					PropertyUtils.copyProperties(objResultVo, objResult);
					objResponseObject = objBOResult.getSurveyRespondentsList(objResultVo);
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("SurveyRespondentsList", objResponseObject.getResponseObject("SurveyRespondentsList"));
					request.setAttribute("pageName", "SurveyResultRespondents");
					strForward = OISurveyConstants.LIST_PAGE;*/
				}
				
    		}else {
			    strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
			}
    		
		} catch(Exception e) {
			e.printStackTrace();
		    strRedirect = OISurveyConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally {
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
		
		if(!strRedirect.equals("")){
			return new ActionForward(strRedirect);
		}
		else {
			return (mapping.findForward(strForward));
		}
    }
		
}
