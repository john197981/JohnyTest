<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="com.oifm.login.OILoginConstants" %>

<script language="javascript" >
function submitSurveyListForm(submitUrl, actionName, surveyId, divcode) {
 	var frm = document.SurveyForm;
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>&viewMode=all&divCode=set';
	frm.hiddenAction.value = actionName;
	frm.strSurveyId.value = surveyId;
	frm.strDivisionCode.value = divcode;
	frm.submit();
}

</script>
<%
try {
	String strDivision = "";
	if (session.getAttribute(OILoginConstants.DIVCODE+"List")!=null)
		strDivision = (String) session.getAttribute(OILoginConstants.DIVCODE+"List");
%>

<html:form action="/SurveyAdmin.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="pageNo" />
<html:hidden property="strDivisionCode" />


<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"	cellspacing="1" bgcolor="white">					
 		  <logic:present name="error" scope="request" >
				  <tr>
					<td width="100%" class="body_detail_text" colspan="6">
					<b><bean:message name="error" scope="request"/></b>
					</td>
				  </tr>
		</logic:present>
		
<logic:notPresent name="error" scope="request" >
	
	<logic:present name="SurveyDivisionList" scope="request" >
          	 <tr>
              	<td valign="bottom" align="left" NOWRAP colspan="6">
					<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>', '', '')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_create_survey.gif'  border="0" alt = "Create Survey"></a>
					&nbsp;
					<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_LIST %>', '','')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_CreateUserID.gif' alt="Create Temporary User IDs"  border="0" alt = "Create User IDs"></a>
					&nbsp;
					<!--added by priyanka-->
                     <a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_ADMIN_LIST %>', '','')" ><img src='<bean:message key="OIFM.docroot" />/images/button_ViewAllSurveys.gif' alt="View All Survey"  border="0" alt = "View All"></a>

                    <!--ended by priyanka-->
					
				</td>
            </tr>

		   <tr>
            
            <td width="250" class="Table_Head""> Division </td>
            
          </tr>


			<logic:iterate id="objSurvey" name="SurveyDivisionList" indexId="ind" type="com.oifm.survey.OIBASurvey">
          <tr>
            <td width="70%" class="heading_thread"  valign="top">
			<a href="#" class="heading_thread" onClick='javascript:submitSurveyListForm(
            "<%= OISurveyConstants.SURVEY_ADMIN_DO %>",
            "<%= OISurveyConstants.DIVISION_SURVEY_VIEW_ALL %>",
			"<bean:write name="objSurvey" property="strDivisionName" />", "<bean:write name="objSurvey" property="strDivisionCode" />"
			)'><bean:write name="objSurvey" property="strDivisionCode" /> - <bean:write name="objSurvey" property="strDivisionName" /></a>

		<!--	<bean:write name="objSurvey" property="strDivisionName" />
			<bean:write name="objSurvey" property="strDivisionCode" /> -->
			</td>
			</tr>
			</logic:iterate>




	</logic:present>
</logic:notPresent>
<tr>
              	<td valign="bottom" align="left" NOWRAP colspan="3">
	            <a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>', '', '')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_create_survey.gif'  border="0" alt = "Create Survey"></a>
				&nbsp;
	            <a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_LIST %>', '','')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_CreateUserID.gif' alt="Create Temporary User IDs"  border="0" alt = "Create User IDs"></a>
				&nbsp;
				<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_ADMIN_LIST %>', '','')" ><img src='<bean:message key="OIFM.docroot" />/images/button_ViewAllSurveys.gif' alt="View All Survey"  border="0" alt = "View All"></a>
				</td>

				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</html:form>
<%
} catch(Exception e)	{
	e.printStackTrace();
}		
%>


