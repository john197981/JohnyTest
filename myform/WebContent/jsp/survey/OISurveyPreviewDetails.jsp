<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>

<script language="javascript" >

var QuestionEdit;

function submitSurveyDetailsForm(submitUrl, actionName, sectionId) {
 	var frm = document.UserSurveyForm;
	frm.target="QuestionEdit";
	if(!(QuestionEdit && QuestionEdit.open && !QuestionEdit.closed))
		QuestionEdit = window.open("", "QuestionEdit", "width=600,height=600,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5,left=200" );
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl;
	frm.hiddenAction.value = actionName;
	frm.strSectionId.value = sectionId;
	frm.submit();
	QuestionEdit.focus();
	//?id=<%= Math.random() %>';
	//alert('<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>&hiddenAction='+actionName+'&strSectionId='+sectionId);
	//window.open('<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>&hiddenAction='+actionName+'&strSectionId='+sectionId, "QuestionEdit", "width=600,height=600,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5,left=200" );
	return;
}

function DetailssDisplay(sectionId) {
	if(document.getElementById(sectionId).style.display == "")
		document.getElementById(sectionId).style.display = "none";
	else 
		document.getElementById(sectionId).style.display = "";
}

</script>


<jsp:include page="/jsp/common/iofmSimpleTop.jsp" flush="true" />	


<html:form action="/UserSurvey.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />


<table width="100%"  border="0" cellpadding="0" cellspacing="0" class="Box">
<logic:present name="error" scope="request" >
<tr>
	<td width="100%" class="BodyText" colspan="3">
	<b><bean:message name="error" scope="request"/></b>
	</td>
</tr>
</logic:present>

<logic:present name="message" scope="request">
		<logic:iterate id="msg" name="message" scope="request">
<tr>
	<td width="100%" align="center" valign="top" class="Mainheader">
		<b><bean:message key="msg"/></b>
	</td>
</tr>
		</logic:iterate>
</logic:present>


<tr>
	<td width="98%" align="center" valign="top">

<logic:present name="objSurveyVo" scope="request">
<bean:define id="objSurvey" name="objSurveyVo" type="com.oifm.survey.OIBASurvey" />

	<table width="100%"  border="0" cellpadding="0" cellspacing="0" >
	<tr>
		<td valign="bottom" class="table_head" align="left">
		<bean:write name="objSurvey" property="strSurveyName" />
		</td>
	</tr>
	</table><br>
	<table width="98%"  border="0" cellspacing="0" cellpadding="0" >
	<tr>
		<td class="sub_head" colspan="2">
			<bean:write name="objSurvey" property="strDescription" filter="false" /><BR> 
			<logic:notEqual name="objSurvey" property="strInstruction" value="">
				<I><bean:write name="objSurvey" property="strInstruction" filter="false" /></I>
			</logic:notEqual>			
		</td>
	</tr>
	</table><br>
</logic:present>

	<table width="100%"  border="0" cellspacing="0" cellpadding="0" bgcolor="white">
	<tr>
		<td align="left" valign="top" class="Box">
		<table width="100%"  border="0" cellpadding="3" cellspacing="0">
<logic:present name="SectionList" scope="request" >
	<bean:size id="secSize" name="SectionList" scope="request" />
	<logic:greaterThan name="secSize" value="0">
		<logic:iterate id="objSection" name="SectionList" type="com.oifm.survey.OIBASection">
			<bean:define id="secId" name="objSection" property="strSectionId"/>
		<tr valign="absmiddle"> 
			<td height="9" class="heading_attributes" width="1%">
			<bean:write name="objSection" property="strLevelLabel" />  
			</td>
			<td width="99%" colspan="2" NOWRAP class="Heading_Thread" valign="top">

			<a href="javascript:DetailssDisplay('<%= secId %>')"><img src='<bean:message key="OIFM.docroot" />/images/expand.gif' WIDTH="12" HEIGHT="11" BORDER="0" ALT="Section Details"></a>
			<logic:greaterThan name="objSection" property="noOfQuestions" value="0">	
				<a class="Heading_Thread" href="#" onClick='javascript:submitSurveyDetailsForm(
						"<%= OISurveyConstants.SURVEY_USER_RSP_DO %>",
						"<%= OISurveyConstants.SURVEY_PREVIEW %>",
						"<bean:write name="objSection" property="strSectionId" />")'
				><bean:write name="objSection" property="strSectionName" /></a> 
			</logic:greaterThan>
			<logic:equal name="objSection" property="noOfQuestions" value="0">	
				<bean:write name="objSection" property="strSectionName" />
			</logic:equal>
				&nbsp;
			<logic:greaterThan name="objSection" property="noOfQuestions" value="0">
					(0 of <bean:write name="objSection" property="noOfQuestions" />) &nbsp; 
			</logic:greaterThan>

			</td>
		</tr>
		<tr id="<%= secId %>" class="BodyText" >
			<td  >&nbsp; </td>
			<td  width="1%">&nbsp;&nbsp;&nbsp;</td>
			<td width="98%" align="left" class="heading_attributes">
					<bean:write name="objSection" property="strDescription" filter="false" /><BR>
			<logic:notEqual name="objSection" property="strInstruction" value="">
					<I><bean:write name="objSection" property="strInstruction"  filter="false"/></I>
			</logic:notEqual>
			</td>
		</tr>
<SCRIPT LANGUAGE="JavaScript">
<!--
	DetailssDisplay('<bean:write name="objSection" property="strSectionId" />');
//-->
	</SCRIPT>
		</logic:iterate>
	</logic:greaterThan>
	<logic:lessThan name="secSize" value="1">
		<tr>
			<td width="100%" class="BodyText" colspan="3">
				<b><bean:message key="NoRecordLoad"/></b>
			</td>
		</tr>
	</logic:lessThan>
</logic:present>
			
		</table>
		</td>
	</tr>
	</table>
	</td>
</tr>
<tr>
	<td height="9" >
		<br>
		<a href="#" onClick="javascript:window.close()" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif'  border="0" alt = "Cancel"></a>&nbsp;
	</td>
</tr>
</table>
</html:form>
</body>
</html>