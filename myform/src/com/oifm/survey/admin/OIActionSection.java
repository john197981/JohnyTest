
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
import com.oifm.survey.OIBASection;
import com.oifm.survey.OIFormSection;
import com.oifm.survey.OISurveyConstants;


public class OIActionSection extends OIBaseAction {

	private static Logger logger = Logger.getLogger(OIActionSection.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)	{
    	 
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	String strDivCode = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	boolean isSurveyMaintain = false;
    	OIBOSectionAdmin objBOSection = null;
    	OIResponseObject objResponseObject = null;
    	OIBASection objSectionVo = new OIBASection();
    	OIFormSection objSection = (OIFormSection)form;
    	boolean isMainAdmin = false;
    	strAction = (strAction != null)? strAction: OISurveyConstants.SECTION_EDIT;

    	try {
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isMainAdmin = (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && 
					alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) );
			isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
			objSection.setStrDivisionCode(strDivCode);
			
			if(isSurveyMaintain) {
				objBOSection = new OIBOSectionAdmin();

				if(strAction.equals(OISurveyConstants.SECTION_EDIT)) {
					if(objSection.getStrSectionId() == null || objSection.getStrSectionId().equals(""))
						initializeSection(objSection);
					PropertyUtils.copyProperties(objSectionVo, objSection);
					objResponseObject = objBOSection.getSectionDetails(objSectionVo);
					objSectionVo = (OIBASection)objResponseObject.getResponseObject("objSectionVo");
					PropertyUtils.copyProperties(objSection, objSectionVo);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("SectionParentsList", objResponseObject.getResponseObject("SectionParentsList"));
					request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
					if(!isMainAdmin)
						request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					else request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("pageName", "SectionEdit");
					strForward = OISurveyConstants.EDIT_PAGE;

				} else if(strAction.equals(OISurveyConstants.SECTION_SAVE)) {
					PropertyUtils.copyProperties(objSectionVo, objSection);
					objResponseObject = objBOSection.saveSectionDetails(objSectionVo);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("SectionParentsList", objResponseObject.getResponseObject("SectionParentsList"));
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("pageName", "SectionEdit");
					if(objResponseObject.getResponseObject("error") == null)
						initializeSection(objSection);
					strForward = OISurveyConstants.EDIT_PAGE;

				} else if(strAction.equals(OISurveyConstants.SECTION_DELETE)) {
					PropertyUtils.copyProperties(objSectionVo, objSection);
					objResponseObject = objBOSection.deleteSectionInfo(objSectionVo);
					request.setAttribute("SectionList", objResponseObject.getResponseObject("SectionList"));
					request.setAttribute("SectionParentsList", objResponseObject.getResponseObject("SectionParentsList"));
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("pageName", "SectionEdit");
					if(objResponseObject.getResponseObject("error") == null) {
						initializeSection(objSection);
					}
					strForward = OISurveyConstants.EDIT_PAGE;
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

	private void initializeSection(OIFormSection objSection)	{
		objSection.setStrDescription("");
		objSection.setStrInstruction("");
		objSection.setStrParentId("");
		objSection.setStrSectionId("");
		objSection.setStrSectionName("");
	}

}
 
    

