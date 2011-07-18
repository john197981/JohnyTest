<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.login.OILoginConstants" %>
<%
	String temp=(String)session.getAttribute(OILoginConstants.EMAIL_USER);
	request.setAttribute(OILoginConstants.EMAIL_USER,temp);
%>
<script language="javascript" >

var QuestionEdit;

function submitSurveyDetailsForm(submitUrl, actionName, sectionId) {
 	var frm = document.UserSurveyForm;
	frm.target="QuestionEdit";
	if(!(QuestionEdit && QuestionEdit.open && !QuestionEdit.closed))
		QuestionEdit = window.open("", "QuestionEdit", "width=600,height=600,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5,left=200" );
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.strSectionId.value = sectionId;
	frm.submit();
	QuestionEdit.focus();
	return;
}

function submitForm(submitUrl, actionName) {
 	var frm = document.UserSurveyForm;
	frm.target="_self";
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.submit();
}

function DetailssDisplay(sectionId) {
	if(document.getElementById(sectionId).style.display == "")
		document.getElementById(sectionId).style.display = "none";
	else 
		document.getElementById(sectionId).style.display = "";
}

</script>

<logic:present name="objSurveyVo" scope="request">
<bean:define id="objSurvey" name="objSurveyVo" type="com.oifm.survey.OIBASurvey" />
<table width="857" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td class="Orange_fade"><bean:write name="objSurvey" property="strSurveyName" /></td>
  </tr>
</table>
  </logic:present>        
<table width="857" border="0" cellspacing="0" cellpadding="0" bgcolor="#f7f8fc">
  <tr>
    <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
      <blockquote>
        <p><br>
          <bean:write name="objSurvey" property="strDescription" filter="false" />
          <br>
          </p>
        </blockquote>
    </div></td>
    </tr>


<html:form action="/UserSurvey.do" method="post" >
<logic:present name="error" scope="request" >
<tr>
	<td width="98%"  colspan="3">
	<b><bean:message name="error" scope="request"/></b>
	</td>
</tr>
</logic:present>


<tr>
	<td width="98%" align="center" valign="top">

<logic:present name="objSurveyVo" scope="request">
<bean:define id="objSurvey" name="objSurveyVo" type="com.oifm.survey.OIBASurvey" />
	<table width="98%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
    <td class="Box">
    <table width="100%"  border="0" cellspacing="0" cellpadding="0">
	 <tr>
	    <td width="84%" class="Table_head">Instructions : </td>
	    <td width="20%" class="Table_head">
	    	
	    	<logic:notPresent name="emailUser" scope="request">
	    		<a href='<bean:message key="OIFM.contextroot" />/UserSurvey.do?id=<%= Math.random() %>' class="Poll_body"> Back to Survey List</a> 
	    	</logic:notPresent>
	    </td>
	</tr>
	<tr>
	<td class="Heading_Thread">
	<logic:notEqual name="objSurvey" property="strInstruction" value="">
		<bean:write name="objSurvey" property="strInstruction"  filter="false"/>
	</logic:notEqual>	
	</td>
	</tr>
	<tr>
		<logic:present name="compPercent" scope="request" >
		<bean:define id="nPercent" name="compPercent" type="java.lang.Integer" />
		<logic:greaterThan name="nPercent" value="0">
		<td>&nbsp;</td>
		<td bgcolor="#CCCCCC" >
		<img src='<bean:message key="OIFM.docroot" />/images/surveyBar.gif' height="15" border="0" width='<bean:write name="nPercent" />%' >
		</td>
		</logic:greaterThan>
	</logic:present>
	</tr>
	<tr>
	<td>&nbsp;</td>
	<td valign="bottom"  align="right"  class="Table_Sub_Topic">
		<bean:write name="nPercent" />% Completed
	</td>
	</tr>
	</table>
	</td>
	</tr>
	<tr>
	<td class="Box">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	 <tr>
	    <td  class="Table_head">Contact Person Details : </td>
	    <td class="Table_head">
	     </td>
	</tr>
	<tr>
	<td colspan=2>&nbsp;</td>
	</tr>
	<tr>
		<td class="body_extract_text" width="30%"><b>Name : </b>
		</td>
		<td class="body_extract_text">
		<bean:write name="objSurvey" property="strContactPerson" />
		</td>
	</tr>
	<tr>
	<td colspan=2>&nbsp;</td>
	</tr>
	<tr>
		<td class="body_extract_text" width="30%"><b>Telephone : </b>
		</td>
		<td class="body_extract_text">
		<bean:write name="objSurvey" property="strPhone" />
		</td>
	</tr>
	<tr>
	<td colspan=2>&nbsp;</td>
	</tr>
	<tr>
		<td class="body_extract_text" width="30%"><b>Email Address : </b>
		</td>
		<td class="body_extract_text">
		<bean:write name="objSurvey" property="strEmailAddress" />
		</td>
	</tr>
	<tr>
	<td colspan=2>&nbsp;</td>
	</tr>
	<tr>
	<td>
	</td>
	</tr>
	</table>
	</td>
	</tr>
	<br>
	<tr >
		<td align="left" valign="top" >
		<table width="98%"  border="0" cellspacing="0" cellpadding="0" >
		<tr >
		<br>
		<td><span class="Table_Sub_head">Click on the section hyperlinks to answer the questions</span></td>
		</tr >
		</table>
		</td>
	</tr>
</logic:present>
	<table width="98%"  border="0" cellspacing="0" cellpadding="0" >
	<tr >
		<td align="left" valign="top" class="Box">
		<table width="98%"  border="0" cellpadding="3" cellspacing="0">
<logic:present name="SectionList" scope="request" >
	<bean:size id="secSize" name="SectionList" scope="request" />
	<logic:greaterThan name="secSize" value="0">
		<logic:iterate id="objSection" name="SectionList" type="com.oifm.survey.OIBASection">
			<bean:define id="secId" name="objSection" property="strSectionId"/>
		<tr  valign="absmiddle" class="Heading_Thread" valign = bottom> 
			<td height="9" width="1%" valign = bottom>
			<bean:write name="objSection" property="strLevelLabel" />  
			</td>
			<td NOWRAP class="Heading_Thread" valign = bottom>
				<a href="javascript:DetailssDisplay('<%= secId %>')"><img src='<bean:message key="OIFM.docroot" />/images/expand.gif' WIDTH="12" HEIGHT="11" BORDER="0" ALT="Section Details" ></a>
			</td>
			<td NOWRAP class="Heading_Thread" valign = bottom>
				<logic:greaterThan name="objSection" property="noOfQuestions" value="0">	
				<a href="#" onClick='javascript:submitSurveyDetailsForm(
						"<%= OISurveyConstants.SURVEY_USER_RSP_DO %>",
						"<%= OISurveyConstants.SECTION_QST_RESP_DTLS %>",
						"<bean:write name="objSection" property="strSectionId" />")'
				 class="Heading_Thread" ><bean:write name="objSection" property="strSectionName" /></a> 
				</logic:greaterThan>
				<logic:equal name="objSection" property="noOfQuestions" value="0">	
					<bean:write name="objSection" property="strSectionName" />
				</logic:equal>
			</td>
			<td NOWRAP class="Heading_Thread" valign = bottom>
				<logic:greaterThan name="objSection" property="noOfQuestions" value="0">
						(<bean:write name="objSection" property="noOfResponses" /> of 
						<bean:write name="objSection" property="noOfQuestions" />) &nbsp; 
				</logic:greaterThan>
			</td>
		</tr>
		<tr id="<%= secId %>"  class="Heading_Thread" valign = bottom>
			<td  >&nbsp; </td>
			<td  width="1%">&nbsp;&nbsp;&nbsp;</td>
			<td colspan="2" width="98%" align="left" class="body_detail_text" valign = bottom>
					<bean:write name="objSection" property="strDescription" filter="false"/><BR>
			<logic:notEqual name="objSection" property="strInstruction" value="">
					<I><bean:write name="objSection" property="strInstruction" filter="false"/></I>
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
			<td width="98%"  colspan="3">
				<b><bean:message key="NoRecordLoad"/></b>
			</td>
		</tr>
	</logic:lessThan>
</logic:present>
		<tr>
	<td height="9" colspan="3">
		<br>
<logic:present name="isGDSUser" scope="request">
	<logic:equal name="isGDSUser" value="true">
		<a href="#" onClick="javascript:submitForm(
						'<%= OISurveyConstants.SURVEY_USER_DO %>',
						'<%= OISurveyConstants.SURVEY_USER_LIST %>')" ><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt = "Cancel"></a>&nbsp;
	</logic:equal>
	
</logic:present >

<logic:present name="emailUser" scope="request">
	<logic:equal name="emailUser" value="true">
		<a href="#" onClick="window.close();" ><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt = "Cancel"></a>&nbsp;
	</logic:equal>
	
</logic:present >

<logic:present name="objSurveyVo" scope="request">
	<logic:equal name="objSurveyVo" property="userSubmitted" value="false">
		<logic:equal name="objSurveyVo" property="userCanSubmit" value="true">
			<logic:equal name="compPercent" scope="request" value="0">
				<a href="#" onClick="alert('<%= OIDBRegistry.getValues("OI.SU.CURR.RES.CHECK") %>'); return false;" ><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt = "Submit"></a>
			</logic:equal>
			<logic:greaterThan name="compPercent" scope="request" value="0">
			<a href="#" onClick="javascript:submitForm(
							'<%= OISurveyConstants.SURVEY_USER_DO %>',
							'<%= OISurveyConstants.SURVEY_USER_SUBMIT %>', '')" ><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt = "Submit"></a>
			</logic:greaterThan>
		</logic:equal>
		<logic:equal name="objSurveyVo" property="userCanSubmit" value="false">
			<a href="#" onClick="alert('<%= OIDBRegistry.getValues("OI.SU.CURR.RES.MAN") %>'); return false;" ><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif'  border="0" alt = "Submit"></a>
		</logic:equal>
	</logic:equal>
</logic:present >
	</td>
</tr>
		</table>
		</td>
	</tr>
	</table>
	</td>
</tr>
<tr>
    <td align="left" valign="top" bgcolor="#f7f8fc">&nbsp;</td>
  </tr>
  <tr>
    <td align="left" valign="top" bgcolor="#f7f8fc"><br></td></tr>
</table>
<logic:notPresent name="emailUser" scope="request">
	<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
</logic:notPresent>
<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />
</html:form>
