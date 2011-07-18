<!--
Displaying List of Respondent report for survey
Created by Edmund Choo
on 22 NOV 2006

-->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oifm.survey.OIBASurveyResponse" %>

<%
ArrayList alList = new ArrayList();
String strSurveyName = (String)request.getAttribute("SurveyTitle");
String strSurveyId = (String)request.getAttribute("SurveyId");
OIBASurveyResponse objSurveyResponse = new OIBASurveyResponse();
int i = 0;

if(request.getAttribute("SurveyRespondentsList")!= null){
	alList = (ArrayList)request.getAttribute("SurveyRespondentsList");
}


%>

<html>
<head>
		<title>My Forum, Ministry Of Education</title>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
</head>

<script language="javascript" >

</script>

<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />
<html:form action="/SurveyResult.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />
<html:hidden property="strResultType" />
<html:hidden property="strSurveyName" />

<table width="98%" border="0" cellpadding="0"	cellspacing="0">
<tr>
<td class="TableHead">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td class="Box">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="85%" align="center" valign="top">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="bottom" class="Table_head"><div align="left">List of Respondents</div></td>                               
      </tr>
	  <tr>
		<td>&nbsp;</td>
	  </tr>
    </table>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
         <td colspan="7">
         <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td height="25" class=body_detail_text vAlign=top><b>Survey Title :<%=strSurveyName%></b></td>                
            </tr>
         </table>
		 </td>
    </tr>
	<tr>
		<td>&nbsp;</td>
	  </tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" valign="top" class="Boxoutline">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="25" class="Table_head">List of Respondents</td>                
              </tr>
            </table>
			</td>
          </tr>
		  <tr>
			<td colspan="2" class="Boxoutline">
				<TABLE cellSpacing=0 cellPadding=0 border=0 width="100%">
					<tr bgcolor="#F0F8FF">
						<td >&nbsp;</td>
						<td class="Sub_head">Name</td>
						<td class="Sub_head" align="center">Email</td>
					</tr>
				<% if(!(alList.size()<= 0))
					{
					for (i = 0; i < alList.size() ; i++){
						objSurveyResponse = (OIBASurveyResponse)alList.get(i);
						String strName = objSurveyResponse.getStrNickName();
						String strEmail = objSurveyResponse.getEmail();
						strName = (strName==null)?"":strName;
						strEmail = (strEmail==null)?"":strEmail;
				%>
					<tr bgcolor="white">
						<td>&nbsp;</td>
						<TD class=body_detail_text vAlign=top><%=strName %></td>
						<td class=body_detail_text vAlign=top align="center"><%=strEmail %></td>
					</tr>
				<% 		} 
					} else {%>
					<tr>
						<TD class=body_detail_text vAlign=top colspan="3">No Record</td>
					</tr>
				<%	}
					%>
				<tr bgcolor="#F0F8FF">
					<td>&nbsp;</td>
		 			<td class="Sub_head" align="right">Total</td>
		 			<td class="Sub_head" align="center"><%=i%></td>
				</tr>
				</table>
				<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left">
					<a href='<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?surveyId=<%=strSurveyId %>&surveyTitle=<%=strSurveyName%>&export=yes&hiddenAction=<%= OISurveyConstants.RESULT_TYPE_RESPONDENTS %>'><img src='<bean:message key="OIFM.docroot" />/images/btn_export.gif' alt="Export the Survey" border="0" ></a>
					&nbsp;&nbsp;
					<a href="#" onClick="window.print()"><img src='<bean:message key="OIFM.docroot"/>/images/btn_print.gif' border="0" alt = "Print Page"></a>
					</td>
				</tr>
				</table>
			</td>
			</tr>
		  </table>
		  </td>
      </tr>
   </table>
    </td>
  </tr>
</table>
</html:form>
</html>