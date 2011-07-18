<!--
Displaying link to different survey result report
Created by Edmund Choo
on 22 NOV 2006

-->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
 
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>

<script language="JavaScript" type="text/JavaScript">
	var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write("<scr"+"ipt>function editor_generate() { return false; }</scr"+"ipt>"); 
	}
</script>

 	<SCRIPT LANGUAGE="JavaScript">
		<!--
		function submitSurveyResultForm(submitUrl, actionName) {
		 	var frm = document.ResultForm;
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
			return;
		}
		
		function submitSurveyListForm(submitUrl, actionName) 
		{
			var frm = document.ResultForm;
			frm.action = '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
			
		}
	-->
	</script>

<html:form action="/SurveyResult.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />
<html:hidden property="strResultType" />
<html:hidden property="strSurveyName" />

<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="3">Survey</td>
 					</tr>
					<tr>
						<td colspan="2">
							<div align="left">
								<img src='<bean:message key="OIFM.docroot" />/images/tab_survey_results.gif' height="27" border="0" usemap="#MapMap">
									<map name="MapMap">
										<area shape="rect" coords="7,0,62,32" href="javascript:submitSurveyResultForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>')" >
										<area shape="rect" coords="76,3,141,27" href="javascript:submitSurveyResultForm('<%= OISurveyConstants.SURVEY_SECTION_DO %>','<%= OISurveyConstants.SECTION_EDIT %>')" >
										<area shape="rect" coords="150,0,284,29" href="javascript:submitSurveyResultForm('<%= OISurveyConstants.SURVEY_QUESTION_DO %>','<%= OISurveyConstants.QUESTION_LIST %>')" >
										<area shape="rect" coords="230,0,380,29" href="javascript:submitSurveyResultForm('<%= OISurveyConstants.SURVEY_RESULT_DO %>','<%= OISurveyConstants.RESULT_TYPE %>')" >
									</map>
							</div>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"><a class="special_body_link" href="#" 
							onClick="javascript:submitSurveyListForm('/SurveyAdmin.do','SurveyExport')" >Export raw data to excel</a>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"><a class="special_body_link" href="#" 
							onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?surveyId=<bean:write name="ResultForm" property="strSurveyId"/>&hiddenAction=<%= OISurveyConstants.RESULT_TYPE_SUMMARY %>');">Profile of Respondents</a>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"><a class="special_body_link" href="#" 
							onclick="javascript:submitSurveyListForm('/SurveyAdmin.do?surveyId=<bean:write name="ResultForm" property="strSurveyId"/>','<%= OISurveyConstants.RESULT_TYPE_DETAIL %>')">Responses by Demographics</a>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"><a class="special_body_link" href="#" 
							onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?surveyId=<bean:write name="ResultForm" property="strSurveyId"/>&hiddenAction=<%= OISurveyConstants.RESULT_TYPE_RESPONDENTS %>');">List of Respondents</a>
				
						</td>
					</tr>
					<tr>
						<td width="100%" height="60" colspan="3"></td>
					</tr>			
				</table>
			</td>
			</tr>
			
		</table>
		</td>
	</tr>
</table>
</html:form>