
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
import com.oifm.common.OIActionHelper;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OISurveyConstants;


public class OIActionTempUserAdmin extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionTempUserAdmin.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)	{
    	
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	String strDivCode = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	boolean isSurveyMaintain = false;
    	OIBOTempUserAdmin objBOTempUser = null;
    	OIResponseObject objResponseObject = null;
    	OIBATempUser objTempUserVo = new OIBATempUser();
    	OIFormTempUser objTempUser = (OIFormTempUser)form;
    	strAction = (strAction != null)? strAction: OISurveyConstants.TEMP_USER_LIST;

    	try {
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
			objTempUser.setStrDivisionCode(strDivCode);

			if(isSurveyMaintain) {
				objBOTempUser = new OIBOTempUserAdmin();

				if(strAction.equals(OISurveyConstants.TEMP_USER_LIST))	{
					ArrayList alUsersList = null;
					PropertyUtils.copyProperties(objTempUserVo, objTempUser);
					objResponseObject = objBOTempUser.getSurveyBatchUsersList(objTempUserVo);
					request.setAttribute("SurveyList", objResponseObject.getResponseObject("SurveyList"));
					request.setAttribute("SurveyBatchList", objResponseObject.getResponseObject("SurveyBatchList"));
					alUsersList = (ArrayList)objResponseObject.getResponseObject("SurveyBatchUsersList");
					request.setAttribute("SurveyBatchUsersList", alUsersList);
					if(alUsersList != null) objTempUser.setNoOfUsers(alUsersList.size());
					request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					request.setAttribute("pageName", "TempUserList");
					strForward = OISurveyConstants.LIST_PAGE;

				} else if(strAction.equals(OISurveyConstants.TEMP_USER_GENERATION)) {
					PropertyUtils.copyProperties(objTempUserVo, objTempUser);
					objResponseObject = objBOTempUser.generateSaveTempUserInfo(objTempUserVo);
					PropertyUtils.copyProperties(objTempUser, objTempUserVo);
					if(objResponseObject.getResponseObject("error") != null) {
logger.error((String)objResponseObject.getResponseObject("error"));
						strRedirect = OISurveyConstants.ERROR_DO+"?error="+(String)objResponseObject.getResponseObject("error");
					} else strRedirect = "/SurveyTempUser.do?hiddenAction="+OISurveyConstants.TEMP_USER_LIST+"&strSurveyId="+objTempUserVo.getStrSurveyId()+"&strBatchNo="+objTempUserVo.getStrBatchNo();

				} else if(strAction.equals(OISurveyConstants.TEMP_USER_REMOVE)) {
					PropertyUtils.copyProperties(objTempUserVo, objTempUser);
					objResponseObject = objBOTempUser.deleteTempUserInfo(objTempUserVo);
					if(objResponseObject.getResponseObject("error") != null)
				    	strRedirect = OISurveyConstants.ERROR_DO+"?error="+(String)objResponseObject.getResponseObject("error");
					else strRedirect = "/SurveyTempUser.do?hiddenAction="+OISurveyConstants.TEMP_USER_LIST+"&strSurveyId="+objTempUserVo.getStrSurveyId();

				} else if(strAction.equals(OISurveyConstants.TEMP_USER_EXPORT)) {
					PropertyUtils.copyProperties(objTempUserVo, objTempUser);
					String tempUserInfo = objBOTempUser.exportTempUserList(objTempUserVo);
					(new OIActionHelper()).writeContentToFile(response, tempUserInfo, "TempUserList_"+objTempUserVo.getStrSurveyId()+"_"+objTempUserVo.getStrBatchNo()+".XLS");
					request.setAttribute("pageName", "TempUserList");
					strForward = OISurveyConstants.LIST_PAGE;

				} 
		    } else {
		    	strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
		    }
		} catch(Exception e) {
			e.printStackTrace();
	    	strRedirect = OISurveyConstants.ERROR_DO+"?error=OIDEFAULT";
		} finally {
			if(!strForward.equals("") && objResponseObject!= null && objResponseObject.getResponseObject("error") != null ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
		if(strAction.equals(OISurveyConstants.TEMP_USER_EXPORT)) 
			return null;
		else if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}	
 
}
