
package com.oifm.survey.admin;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
import com.oifm.common.OIBAAddTag;
import com.oifm.common.OIBOAddTag;
import com.oifm.common.OIFunctionConstants;
import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.survey.OIBASurvey;
import com.oifm.survey.OIFormSurvey;
import com.oifm.survey.OISurveyConstants;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIFormUtilities;


public class OIActionSurvey extends OIBaseAction 
{
	private static Logger logger = Logger.getLogger(OIActionSurvey.class);
	
    public ActionForward doIt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response, String strAction)
    {
    	String strForward = "";
    	String strRedirect = "";
    	String strError = "";
    	String strUserId = "";
    	String strDivCode = "";
    	String tags = "";
    	String tagIds = "";
    	ArrayList alFunctions = null;
    	HttpSession session = null;
    	boolean isSurveyMaintain = false;
    	boolean isSurveyPublish = false;
    	OIBOSurveyAdmin objBOSurvey = null;
    	OIResponseObject objResponseObject = null;
    	OIBASurvey objSurveyVo = new OIBASurvey();
    	OIFormSurvey objSurvey = (OIFormSurvey)form;
    	OIResponseObject aOIResponseObject= null;
    	OIBOAddTag tagBO = new OIBOAddTag();
    	boolean isMainAdmin = false;
    	strAction = (strAction != null)? strAction: OISurveyConstants.SURVEY_ADMIN_LIST;
    	
    	try 
    	{
			session = request.getSession();
			strUserId = (String)getSessionAttribute(request, OILoginConstants.USER_ID);
			strDivCode = (String)getSessionAttribute(request, OILoginConstants.DIVCODE);
			alFunctions = (ArrayList)getSessionAttribute(request, OILoginConstants.FUNCTION_LIST);
			isMainAdmin = (alFunctions != null && alFunctions.contains(OIFunctionConstants.MAINTAIN_CATEGORY_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_BOARD) && alFunctions.contains(OIFunctionConstants.MAINTAIN_TOPIC) );
			isSurveyMaintain = alFunctions.contains(OIFunctionConstants.MAINTAIN_SURVEY);
			isSurveyPublish = alFunctions.contains(OIFunctionConstants.PUBLISH_SURVEY);
	    	objSurvey.setStrCreatedBy(strUserId);
//	    	objSurvey.setStrDivisionCode(strDivCode);
	    	
	    	//System.out.println("OIActionSurvey: doIt - action name : "+strAction);
			if(isSurveyMaintain) 
			{
				objBOSurvey = new OIBOSurveyAdmin();

				if(strAction.equals(OISurveyConstants.SURVEY_ADMIN_LIST) || strAction.equals(OISurveyConstants.SURVEY_CANCEL) || strAction.equals("Sort"))
				{
					if(!"all".equalsIgnoreCase(request.getParameter("viewMode")) && request.getParameter("sort")== null)
					{
						//System.out.println("OIActionSurvey: doIt - viewMode : "+request.getParameter("viewMode"));
						objResponseObject = objBOSurvey.getSurveyDivision();
						request.setAttribute("SurveyDivisionList", objResponseObject.getResponseObject("SurveyDivision"));
						request.setAttribute("pageName", "SurveyDivisionList");
					}
					else
					{
						//System.out.println("OIActionSurvey: doIt - var : "+request.getParameter("viewMode"));
						if(request.getParameter("sort")!= null){
						//System.out.println("OIActionSurvey: doIt - sort  : "+request.getParameter("sort"));
							objSurveyVo.setStrSort("sort");
						}
						else
						{
							//System.out.println("OIActionSurvey: doIt - sort : no");
							objSurveyVo.setStrAge("no");
						}
						PropertyUtils.copyProperties(objSurveyVo, objSurvey);
						objResponseObject = objBOSurvey.getSurveyList(objSurveyVo);
						request.setAttribute("SurveyList", objResponseObject.getResponseObject("SurveyList"));
						request.setAttribute("PageInfo", objResponseObject.getResponseObject("PageInfo"));
						request.setAttribute("pageName", "SurveyList");
						request.setAttribute("all", "all");
						//System.out.println("OIActionSurvey: doIt - var : view all page");
					}
					strForward = OISurveyConstants.LIST_PAGE; 

				}
				else if(strAction.equals(OISurveyConstants.DIVISION_SURVEY_VIEW_ALL))
				{
						if(request.getParameter("sort")!= null){
						//System.out.println("OIActionSurvey: doIt - sort  : "+request.getParameter("sort"));
							objSurveyVo.setStrSort("sort");
						}
						else
						{
							//System.out.println("OIActionSurvey: doIt - sort : no");
							objSurveyVo.setStrAge("no");
						}
						
						if("set".equalsIgnoreCase(request.getParameter("divCode")))
						{
							removeSessionAttribute(request, OILoginConstants.DIVCODE+"List");
							setSessionAttribute(request, OILoginConstants.DIVCODE+"List", objSurvey.getStrDivisionCode());
						}
						
						PropertyUtils.copyProperties(objSurveyVo, objSurvey);
						objResponseObject = objBOSurvey.getDivisionSurveyList(objSurveyVo);
						
						request.setAttribute("SurveyList", objResponseObject.getResponseObject("SurveyList"));
						request.setAttribute("PageInfo", objResponseObject.getResponseObject("PageInfo"));
						request.setAttribute("pageName", "SurveyList");
						
						strForward = OISurveyConstants.LIST_PAGE; 
				}
				else if(strAction.equals(OISurveyConstants.SURVEY_EDIT)) 
				{
					objSurvey.setStrDivisionCode(strDivCode);
					logger.info("start of survey edit page");
					// tag
					String strSurveyId = "";
					removeSessionAttribute(request, "alTagNames");
					removeSessionAttribute(request, "oiFormBlogTag");
					
					strSurveyId = objSurvey.getStrSurveyId();
					if(strSurveyId != null)
						aOIResponseObject = tagBO.getTags(strSurveyId,"SU");
					
					ArrayList tagList = (ArrayList)aOIResponseObject.getResponseObject("tagList");
					if(tagList != null){
						for (Iterator iter = tagList.iterator(); iter.hasNext();)
						{
							OIBAAddTag tag = (OIBAAddTag) iter.next();
							tags += tag.getTagName() + ",";
							tagIds += tag.getTagId() + ",";
						}
						setSessionAttribute(request, "alTagNames",tagList);
					}
					//end- tag
					
				    PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					objResponseObject = objBOSurvey.getSurveyDetails(objSurveyVo);
					objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
					try{
						if (objSurveyVo.getStrFromDate() == null || objSurveyVo.getStrFromDate().trim().length() == 0)
							objSurveyVo.setStrFromDate(DateUtility.getMMDDYYYYStringFromDate(new Date()));
					}
					catch (Exception e){}
					PropertyUtils.copyProperties(objSurvey, objSurveyVo);
					
					objSurvey.setStrTagWords(tags);
					objSurvey.setStrTagIdList(tagIds);
					//setSessionAttribute(request,"isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
					//setSessionAttribute(request,"isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					
					request.setAttribute("pageName", "SurveyEdit");
					request.setAttribute("isSurveyPublish", new Boolean(isSurveyPublish));
					request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
					if(!isMainAdmin)
						request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
					else 
					    request.setAttribute("isSurveyDivision", new Boolean(true));
					//request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
					logger.info("Before fetching externalEmailAddress");
					String externalEmailAddress=objBOSurvey.fetchExternalEmailAddress(objSurveyVo);
					objSurvey.setExternalMailAddress(externalEmailAddress);
					strForward = OISurveyConstants.EDIT_PAGE;

				} 
				else if(strAction.equals(OISurveyConstants.SURVEY_IMPORT)) 
				{
					objSurvey.setStrDivisionCode(strDivCode);
					
					String importFromId = (String)request.getParameter("importFromId");
					//System.out.println("OIActionSurvey: doIt - importFromId : "+importFromId);
					
					String copiedSurveyId = null;
					Integer importId = null;
					boolean cond =  false;
					
					try
					{
						importId = new Integer(importFromId);
					}
					catch (Exception e)
					{
						cond =  true;
					}
					
					if(importId!=null)
					{
						objResponseObject =  objBOSurvey.copyFrom(importId, strUserId);
						//System.out.println("OIActionSurvey: doIt - objResponseObject : "+objResponseObject);
						if(objResponseObject!=null)
						{
							//System.out.println("OIActionSurvey: doIt - error : "+objResponseObject.getResponseObject("error"));
							if(objResponseObject.getResponseObject("error") == null) 
							{
								copiedSurveyId = (String)objResponseObject.getResponseObject("destSurveyId");
							
								objSurvey.setStrSurveyId(copiedSurveyId);
							    PropertyUtils.copyProperties(objSurveyVo, objSurvey);
							    
							    // tag
								removeSessionAttribute(request, "alTagNames");
								removeSessionAttribute(request, "oiFormBlogTag");

								aOIResponseObject = tagBO.getTags(copiedSurveyId,"SU");
								
								ArrayList tagList = (ArrayList)aOIResponseObject.getResponseObject("tagList");
								if(tagList != null){
									for (Iterator iter = tagList.iterator(); iter.hasNext();)
									{
										OIBAAddTag tag = (OIBAAddTag) iter.next();
										tags += tag.getTagName() + ",";
										tagIds += tag.getTagId() + ",";
									}
									setSessionAttribute(request, "alTagNames",tagList);
								}
								//end- tag
								
								objResponseObject = objBOSurvey.getSurveyDetails(objSurveyVo);
								objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
								PropertyUtils.copyProperties(objSurvey, objSurveyVo);
								
								objSurvey.setStrTagWords(tags);
								objSurvey.setStrTagIdList(tagIds);
								
								request.setAttribute("pageName", "SurveyEdit");
								request.setAttribute("isSurveyPublish", new Boolean(isSurveyPublish));
								request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
								if(!isMainAdmin)
									request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
								else 
								    request.setAttribute("isSurveyDivision", new Boolean(true));
								//request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));	
								strForward = OISurveyConstants.EDIT_PAGE;
							}
							else
							{
								cond =  true;						
							}
						}
						else
						{
							cond =  true;						
						}
					}
					else
					{
						cond =  true;
						request.setAttribute("error", "OI.SURVEY.ID.INVALID");
					}
					
					if (cond)
					{
						PropertyUtils.copyProperties(objSurveyVo, objSurvey);
						objResponseObject = objBOSurvey.getSurveyDetails(objSurveyVo);
						objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
						PropertyUtils.copyProperties(objSurvey, objSurveyVo);
						request.setAttribute("pageName", "SurveyEdit");
						request.setAttribute("isSurveyPublish", new Boolean(isSurveyPublish));
						request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
						if(!isMainAdmin)
							request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
						else 
						    request.setAttribute("isSurveyDivision", new Boolean(true));
						//request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
						strForward = OISurveyConstants.EDIT_PAGE;
						request.setAttribute("pageName", "SurveyEdit");
						strForward = OISurveyConstants.EDIT_PAGE;
						//strRedirect = "/SurveyAdmin.do?hiddenAction="+OISurveyConstants.SURVEY_EDIT;
					}

				} 
				else if(isSurveyPublish && (strAction.equals(OISurveyConstants.SURVEY_PUBLISH_EDIT) || strAction.equals(OISurveyConstants.SURVEY_ATTACHMENT_REMOVE))) 
				{
					objSurvey.setStrDivisionCode(strDivCode);
					// try block is added by K.K.Kumaresan on Jan 15,2009
					try
					{
						String strRemoveFileName = "";
						PropertyUtils.copyProperties(objSurveyVo, objSurvey);
						if(strAction.equals(OISurveyConstants.SURVEY_ATTACHMENT_REMOVE))
							strRemoveFileName = OIFormUtilities.getFileName("SUR", Integer.parseInt(objSurveyVo.getStrSurveyId()));
						objResponseObject = objBOSurvey.getSurveyPublishDetails(objSurveyVo, strRemoveFileName);
						objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
						if(objSurveyVo != null) 
						    PropertyUtils.copyProperties(objSurvey, objSurveyVo);
						request.setAttribute("pageName", "SurveyPublishEdit");
						request.setAttribute("AckTypes", objResponseObject.getResponseObject("AckTypes"));
						if(!isMainAdmin)
							request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
						else 
						    request.setAttribute("isSurveyDivision", new Boolean(true));
					//	request.setAttribute("isOwner", objResponseObject.getResponseObject("isOwner"));
					}
					catch(Exception e)
					{
						logger.error("start of Exception"+e);
					}
					strForward = OISurveyConstants.EDIT_PAGE;

				} 
				else if(strAction.equals(OISurveyConstants.SURVEY_SAVE) || strAction.equals(OISurveyConstants.SURVEY_SAVE_NEXT)) 
				{
					objSurvey.setStrDivisionCode(strDivCode);

					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					//if(objSurveyVo.getStrSurveyName() != null && !objSurveyVo.getStrSurveyName().equals(""))
					objResponseObject = objBOSurvey.saveSurveyDetails(objSurveyVo);
					request.setAttribute("pageName", "SurveyEdit");
					
					String strSurveyId = "";
					strSurveyId = objSurveyVo.getStrSurveyId();
					if(strSurveyId==null)
					{
						strSurveyId = (String)objResponseObject.getResponseObject("strSurveyId");
					}
					
					if(objResponseObject.getResponseObject("error") != null) 
					{
						// tag
						removeSessionAttribute(request, "alTagNames");
						removeSessionAttribute(request, "oiFormBlogTag");

						if(strSurveyId != null)
							aOIResponseObject = tagBO.getTags(strSurveyId,"SU");
						
						ArrayList tagList = (ArrayList)aOIResponseObject.getResponseObject("tagList");
						if(tagList != null){
							for (Iterator iter = tagList.iterator(); iter.hasNext();)
							{
								OIBAAddTag tag = (OIBAAddTag) iter.next();
								tags += tag.getTagName() + ",";
								tagIds += tag.getTagId() + ",";
							}
							setSessionAttribute(request, "alTagNames",tagList);
						}
						//end- tag
						
						objResponseObject = objBOSurvey.getSurveyDetails(objSurveyVo);
						objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
						PropertyUtils.copyProperties(objSurvey, objSurveyVo);
						
						objSurvey.setStrTagWords(tags);
						objSurvey.setStrTagIdList(tagIds);
						
						request.setAttribute("isSurveyPublish", new Boolean(isSurveyPublish));
						request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
						if(!isMainAdmin)
							request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
						else 
						    request.setAttribute("isSurveyDivision", new Boolean(true));
						strForward = OISurveyConstants.EDIT_PAGE;
					} 
					else 
					{
						
						ArrayList alTagNames = convertToTagList(objSurveyVo.getStrTagIdList());
						//System.out.println("OIActionSurvey: doIt - alTagNames : "+alTagNames.size());
						if(strSurveyId!=null && strSurveyId!="")
							tagBO.updateTag(strSurveyId, alTagNames, "SU");
						
						if(strAction.equals(OISurveyConstants.SURVEY_SAVE))
							strRedirect = "/SurveyAdmin.do?hiddenAction="+OISurveyConstants.SURVEY_ADMIN_LIST;
						else  
						    strRedirect = "/SurveySection.do?hiddenAction="+OISurveyConstants.SECTION_EDIT+"&strSurveyId="+objSurveyVo.getStrSurveyId();
					}
				} 
				else if(strAction.equals(OISurveyConstants.SURVEY_DELETE)) 
				{
					objSurvey.setStrDivisionCode(strDivCode);
					
					
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					objResponseObject = objBOSurvey.deleteSurveyInfo(objSurveyVo);
					request.setAttribute("pageName", "SurveyEdit");
					if(objResponseObject.getResponseObject("error") != null)
					{
						// tag
						removeSessionAttribute(request, "alTagNames");
						removeSessionAttribute(request, "oiFormBlogTag");
						
						String strSurveyId = objSurvey.getStrSurveyId();
						if(strSurveyId != null)
							aOIResponseObject = tagBO.getTags(strSurveyId,"SU");
						
						ArrayList tagList = (ArrayList)aOIResponseObject.getResponseObject("tagList");
						if(tagList != null){
							for (Iterator iter = tagList.iterator(); iter.hasNext();)
							{
								OIBAAddTag tag = (OIBAAddTag) iter.next();
								tags += tag.getTagName() + ",";						
							}
							setSessionAttribute(request, "alTagNames",tagList);
						}
						//end- tag
						
						objResponseObject = objBOSurvey.getSurveyDetails(objSurveyVo);
						objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
						PropertyUtils.copyProperties(objSurvey, objSurveyVo);
						
						objSurvey.setStrTagWords(tags);
						objSurvey.setStrTagIdList(tagIds);
						
						request.setAttribute("isSurveyPublish", new Boolean(isSurveyPublish));
						request.setAttribute("isCurrentlyValid", objResponseObject.getResponseObject("isCurrentlyValid"));
						if(!isMainAdmin)
							request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
						else 
						    request.setAttribute("isSurveyDivision", new Boolean(true));
						strForward = OISurveyConstants.EDIT_PAGE;
					}
					else 
					    strRedirect = "/SurveyAdmin.do?hiddenAction="+OISurveyConstants.SURVEY_ADMIN_LIST;
				} 
				else if(strAction.equals(OISurveyConstants.SURVEY_ACTIVATE)) 
				{
					objSurvey.setStrDivisionCode(strDivCode);
					
					
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					objResponseObject = objBOSurvey.activateSurvey(objSurveyVo);
					request.setAttribute("pageName", "SurveyEdit");
					if(objResponseObject.getResponseObject("error") != null)
						strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");
					else 
					    strRedirect = "/SurveyAdmin.do?hiddenAction="+OISurveyConstants.SURVEY_ADMIN_LIST;
				} else if(strAction.equals(OISurveyConstants.SURVEY_PUBLISH)) 
				{
					objSurvey.setStrDivisionCode(strDivCode);
					
					if(isSurveyPublish) 
					{
						PropertyUtils.copyProperties(objSurveyVo, objSurvey);
						String strNewFileName = "";
						if(objSurvey.getAttachedFile() != null) 
						{
							strNewFileName = OIFormUtilities.getFileName("SUR", Integer.parseInt(objSurveyVo.getStrSurveyId()));
							objSurveyVo.setStrAttachedFile(strNewFileName);
						}
						objResponseObject = objBOSurvey.saveSurveyPublishInfo(objSurveyVo, objSurvey.getAttachedFile());
						request.setAttribute("pageName", "SurveyPublishEdit");
						if(objResponseObject.getResponseObject("error") != null) 
						{
							String strRemoveFileName = "";
							PropertyUtils.copyProperties(objSurveyVo, objSurvey);
							if(strAction.equals(OISurveyConstants.SURVEY_ATTACHMENT_REMOVE))
								strRemoveFileName = OIFormUtilities.getFileName("SUR", Integer.parseInt(objSurveyVo.getStrSurveyId()));
							objResponseObject = objBOSurvey.getSurveyPublishDetails(objSurveyVo, strRemoveFileName);
							objSurveyVo = (OIBASurvey)objResponseObject.getResponseObject("objSurveyVo");
							if(objSurveyVo != null) 
							    PropertyUtils.copyProperties(objSurvey, objSurveyVo);
							request.setAttribute("pageName", "SurveyPublishEdit");
							request.setAttribute("AckTypes", objResponseObject.getResponseObject("AckTypes"));
							if(!isMainAdmin)
								request.setAttribute("isSurveyDivision", objResponseObject.getResponseObject("isSurveyDivision"));
							else 
							    request.setAttribute("isSurveyDivision", new Boolean(true));
							objSurveyVo.setStrAttachedFile(null);
							objSurvey.setStrAttachedFile(null);
							objSurvey.setAttachedFile(null);
							
							strForward = OISurveyConstants.EDIT_PAGE;
						}
						else 
						    strRedirect = "/SurveyAdmin.do?hiddenAction="+OISurveyConstants.SURVEY_ADMIN_LIST;
				    } 
					else 
					{
				    	strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
				    }
				} 
				else if(strAction.equals(OISurveyConstants.SURVEY_EXPORT))
				{
					objSurvey.setStrDivisionCode(strDivCode);
					
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					PrintWriter out = (new OIActionHelper()).getWriter(response, "SurveyExport_"+objSurveyVo.getStrSurveyId()+".xls");
					objBOSurvey.exportSurveyDetails(objSurveyVo.getStrSurveyId(), out);
					/*if(objResponseObject.getResponseObject("error") != null) 
						strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");*/
				} 
				else if(strAction.equals(OISurveyConstants.SURVEY_EXPORT_HTML))
				{
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					//PrintWriter out = (new OIActionHelper()).getWriter(response, "SurveyExport_"+objSurveyVo.getStrSurveyId()+".htm");
					objBOSurvey.exportSurveyDetailsHtml(objSurveyVo.getStrSurveyId(), request);
					
					return (mapping.findForward( OISurveyConstants.EXPORT_HTML_PAGE));
					/*if(objResponseObject.getResponseObject("error") != null) 
						strRedirect = OISurveyConstants.ERROR_DO+"?error="+objResponseObject.getResponseObject("error");*/
				}
				//added by edmund for survey list of respondents report
				else if(strAction.equals(OISurveyConstants.RESULT_TYPE_RESPONDENTS))
				{
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					String strSurveyId = request.getParameter("surveyId");
					//String strSurveyTitle = request.getParameter("surveyTitle");
					String strExport = request.getParameter("export");
					//objResponseObject = objBOSurvey.getSurveyRespondentsList(objSurveyVo.getStrSurveyId());
					objResponseObject = objBOSurvey.getSurveyRespondentsList(strSurveyId);
					
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("SurveyTitle", objResponseObject.getResponseObject("SurveyTitle"));
					request.setAttribute("SurveyId", strSurveyId);
					request.setAttribute("SurveyRespondentsList", objResponseObject.getResponseObject("SurveyRespondentsList"));
					//request.setAttribute("pageName", "SurveyResultRespondents");
					
					if(strExport != null){
						strForward = "ExportResultRespondents";
					}
					else{
						strForward = "SurveyResultRespondents";
					}
				}
				//added by edmund for survey demographic report
				else if (strAction.equals(OISurveyConstants.RESULT_TYPE_SUMMARY)){
					String strSurveyId = request.getParameter("surveyId");
					//String strSurveyTitle = request.getParameter("surveyTitle");
					String strExport = request.getParameter("export");
					
					objResponseObject = objBOSurvey.getSurveySummary(strSurveyId);
					
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("SurveyDemographicSummary", objResponseObject.getResponseObject("SurveyDemographicSummary"));
					request.setAttribute("SurveyTitle", objResponseObject.getResponseObject("SurveyTitle"));
					request.setAttribute("SurveyId", strSurveyId);
					request.setAttribute("TotalUser", objResponseObject.getResponseObject("TotalUser"));
					
					request.setAttribute("pageName", "SurveyResultSummary");
					if(strExport!=null){
						strForward = "ExportResultSummary";
					}
					else{
						strForward = "SurveyResultSummary";
					}
					
				}//added by edmund for detail report
				else if (strAction.equals("selection")){
					String strSurveyId = (String)request.getParameter("surveyId");
					String strAge = (String)request.getParameter("strAge");
					String strLevel = (String)request.getParameter("strLevel");
					String strDesignation = (String)request.getParameter("strDesignation");
					String strYear = (String)request.getParameter("strYear");
					String strExport = request.getParameter("export");
					String tDesignation = "All";
					String tLevel	= "All";
					
					if(request.getParameter("tDesignation")!=null && !request.getParameter("tDesignation").equals(""))
						tDesignation = (String)request.getParameter("tDesignation");
					
					if(request.getParameter("tLevel")!=null && !request.getParameter("tLevel").equals(""))
						tLevel = (String)request.getParameter("tLevel");
					//String strSurveyTitle = request.getParameter("surveyTitle");
									
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					objResponseObject = objBOSurvey.getSurveyResultDetail(strSurveyId,strAge,strLevel,strDesignation,strYear);
					
					request.setAttribute("SurveyDemoDetail", objResponseObject.getResponseObject("SurveyDemoDetail"));
					request.setAttribute("TotalUser", objResponseObject.getResponseObject("TotalUser"));
					request.setAttribute("SurveyTitle", objResponseObject.getResponseObject("SurveyTitle"));
					request.setAttribute("QuestionRespondents", objResponseObject.getResponseObject("QuestionRespondents"));
					request.setAttribute("SurveyId", strSurveyId);
					request.setAttribute("strAge",strAge);
					request.setAttribute("strLevel",strLevel);
					request.setAttribute("strDesignation",strDesignation);
					request.setAttribute("strYear",strYear);
					request.setAttribute("tDesignation", tDesignation);
					request.setAttribute("tLevel", tLevel);
					
					if(strExport!=null){
						strForward = "ExportResultQuestion";
					}
					else{
						strForward = "SurveyResultQuestion";
					}
					
				}
//				added by edmund for other question
				else if (strAction.equals("otherQuestion")){
					String strQuestionId = (String)request.getParameter("questionId");
					objResponseObject = objBOSurvey.getOpenQuestion(strQuestionId);
					
					request.setAttribute("OpenEndQuestion", objResponseObject.getResponseObject("OpenEndQuestion"));
					request.setAttribute("strQuestion", objResponseObject.getResponseObject("strQuestionName"));
					//request.setAttribute("strQuestion", strQuestion);				
					strForward = "OpenEndQuestion";
				}
//				added by edmund for survey demographic detail report
				else if (strAction.equals(OISurveyConstants.RESULT_TYPE_DETAIL)){
					PropertyUtils.copyProperties(objSurveyVo, objSurvey);
					
					objResponseObject = objBOSurvey.getSurveyResultSelection();
					request.setAttribute("isSurveyDivision", new Boolean(true));
					request.setAttribute("SurveyDemographicSelection", objResponseObject.getResponseObject("SurveyDemographicSelection"));
					request.setAttribute("SurveyTitle", objSurveyVo.getStrSurveyName());
					request.setAttribute("SurveyId",objSurvey.getStrSurveyId());
					request.setAttribute("pageName", "SurveyResultDetail");
					strForward = OISurveyConstants.LIST_PAGE;
					
				}
				
		    } 
			else 
			{
		    	strRedirect = OISurveyConstants.ERROR_DO+"?error=NoAccess";
		    }
			
		} 
    	catch(Exception e) 
    	{
			e.printStackTrace();
	    	strRedirect = OISurveyConstants.ERROR_DO+"?error=OIDEFAULT";
		} 
    	finally 
    	{
			if(!strForward.equals("") && objResponseObject.getResponseObject("error") != null && !objResponseObject.getResponseObject("error").equals("null") ) 
				request.setAttribute("error", objResponseObject.getResponseObject("error"));
		}
    	//System.out.println("OIActionSurvey: doIt - strRedirect : "+strRedirect);
    	//System.out.println("OIActionSurvey: doIt - strForward : "+strForward);
    	
		if(strAction.equals(OISurveyConstants.SURVEY_EXPORT)) 
			return null;
		else if(!strRedirect.equals("")) 
			return new ActionForward(strRedirect);
		else
			return (mapping.findForward(strForward));
	}	
 
	public void copyPublishedInfo(OIFormSurvey objSurvey, OIBASurvey objSurveyVo) 
	{
		objSurvey.setStrSurveyId(objSurveyVo.getStrSurveyId());
		objSurvey.setStrSurveyName(objSurveyVo.getStrSurveyName());
		objSurvey.setStrSurveyType(objSurveyVo.getStrSurveyType());
		objSurvey.setStrFromDate(objSurveyVo.getStrFromDate());
		objSurvey.setStrToDate(objSurveyVo.getStrToDate());
		objSurvey.setStrDescription(objSurveyVo.getStrDescription());
		objSurvey.setStrInstruction(objSurveyVo.getStrInstruction());
		objSurvey.setStrAttachedFile(objSurveyVo.getStrAttachedFile());
	}
	
	private ArrayList convertToTagList(String strTagIdList){
		ArrayList tags = new ArrayList();
		String [] tagIds = strTagIdList.split(",");
		for(int i=0; i<tagIds.length; i++)
		{
			tags.add(tagIds[i]);
		}
		
		return tags;
	}
}
