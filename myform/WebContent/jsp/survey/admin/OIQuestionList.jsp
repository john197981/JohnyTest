<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>

<script language="javascript" >

var QuestionEdit; 
var SurveyPreview;

function submitQuestionForm(submitUrl, actionName, sectionId, questionId) {
	
 	var frm = document.QuestionForm;
	frm.target="QuestionEdit";
	if(!(QuestionEdit && QuestionEdit.open && !QuestionEdit.closed))
		QuestionEdit = window.open("", "QuestionEdit", "width=600,height=600,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5, left=200" );
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.strSectionId.value = sectionId;
	frm.strQuestionId.value = questionId;
	frm.submit();
	QuestionEdit.focus();
	return;
}

function submitReorderQuestion(submitUrl, actionName, sectionId, questionId,qindex,sindex,qsize,ssize,canMoveUp, canMoveDown) {

	if(actionName=="<%= OISurveyConstants.QUESTION_MOVE_UP%>" && (sindex==1 && qindex==1))
	{
		alert('Sorry. The first question cannot be moved further up');
		return;
	}
	else if(actionName=="<%= OISurveyConstants.QUESTION_MOVE_DOWN%>" && sindex==ssize && qindex==qsize)
	{
		alert('Sorry. The last question cannot be moved further down');
		return;
	}
	else if(actionName=="<%= OISurveyConstants.QUESTION_MOVE_UP%>" && (canMoveUp!='' && canMoveUp!='null'))
	{
		alert(canMoveUp);
		return;
	}
	else if(actionName=="<%= OISurveyConstants.QUESTION_MOVE_DOWN%>" && (canMoveDown!='' && canMoveDown!='null'))
	{
		alert(canMoveDown);
		return;
	}
 	var frm = document.QuestionForm;
	frm.target="_self";
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.strSectionId.value = sectionId;
	frm.strQuestionId.value = questionId;
	frm.submit();
	return;
}
function getSurveyPreviewForm(submitUrl, actionName) {
 	var frm = document.QuestionForm;
	frm.target="SurveyPreview";
	if(!(SurveyPreview && SurveyPreview.open && !SurveyPreview.closed))
		SurveyPreview = window.open("", "SurveyPreview", "width=500,height=500,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5,left=5" );
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.submit();
	SurveyPreview.focus();
	return;
}

function submitQuestionListForm(submitUrl, actionName) {
 	var frm = document.QuestionForm;
	frm.target="_self";
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.submit();
	return;
}

function QuestionsDisplay(sectionId) {
	if(document.getElementById(sectionId).style.display == "")
		document.getElementById(sectionId).style.display = "none";
	else 
		document.getElementById(sectionId).style.display = "";
}

function submitSurveyListForm(submitUrl, actionName) 
		{
			var frm = document.QuestionForm;
			frm.action = '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			if(actionName == "<%= OISurveyConstants.SURVEY_PRINT_PREVIEW %>")
			{
				frm.target='_new';
				frm.submit();
				frm.target='_self';
				return;

			}
		}
</script>

<html:form action="/SurveyQuestion.do" method="post" > 

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />
<html:hidden property="strQuestionId" />

<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />

<bean:define id="qstAvailable" value="N" />
<%	
	java.util.ArrayList alQuestList = null;	
	java.util.Hashtable QuestionList = (java.util.Hashtable) request.getAttribute("QuestionList");
	java.util.ArrayList SectionList = (java.util.ArrayList) request.getAttribute("SectionList");
	

%>

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
					<td width="100%" colspan="3" align="center" valign="top" class="Sub_head">
						<b><bean:message key="msg"/></b>
					</td>
				</tr>
					</logic:iterate>
			</logic:present>
	<!-- modify by edmund-->
	<tr>
		<td colspan="3"><div align="left"><img src='<bean:message key="OIFM.docroot" />/images/tab_questio_answers.gif' height="27" border="0" usemap="#MapMap">
<logic:greaterThan name="QuestionForm" property="strSurveyId" value="0">
                <map name="MapMap">
                      <area shape="rect" coords="7,0,62,32" href="javascript:submitQuestionListForm(
						'<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
						'<%= OISurveyConstants.SURVEY_EDIT %>')" >
					  <area shape="rect" coords="76,3,141,27" href="javascript:submitQuestionListForm(
						'<%= OISurveyConstants.SURVEY_SECTION_DO %>',
						'<%= OISurveyConstants.SECTION_EDIT %>')" >
                      <area shape="rect" coords="150,0,284,29" href="javascript:submitQuestionListForm(
						'<%= OISurveyConstants.SURVEY_QUESTION_DO %>',
						'<%= OISurveyConstants.QUESTION_LIST %>')" >
					  <area shape="rect" coords="290,0,380,29" href="javascript:submitQuestionListForm(
						'<%= OISurveyConstants.SURVEY_RESULT_DO %>',
						'<%= OISurveyConstants.RESULT_TYPE %>')" >
				</map>
</logic:greaterThan>
		</div></td>
	</tr>
         <tr class="sub_head">
			<td colspan="3" class="sub_head">Section & Questions &nbsp;&nbsp;&nbsp; <I>(click on section to create questions)</I> </td>
		</tr>
		<tr>
			<td align="left" valign="top" width="100%" >
			<table width="100%"  border="0" cellpadding="3" cellspacing="0" bgcolor="white" > 
			<% int topS=0,topQ=0;
		
			%>
<logic:present name="SectionList" scope="request" >
	<bean:size id="secSize" name="SectionList" scope="request" />
	<logic:greaterThan name="secSize" value="0">
		<logic:iterate id="objSection" name="SectionList" indexId="ind" type="com.oifm.survey.OIBASection">
			<bean:define id="secId" name="objSection" property="strSectionId"/>
			<logic:equal name="objSection" property="level" value="1">
			<tr class="heading_thread"> 
			</logic:equal>
			<logic:notEqual name="objSection" property="level" value="1">
			<tr class="heading_thread" valign="absmiddle"> 
			</logic:notEqual>
				<td height="9" width="1%">
					<bean:write name="objSection" property="strLevelLabel" />) 
					<%topS++;
					
					%>
				</td>
				<td width="99%" colspan="2" class="heading_attributes">
			<logic:match name="isSurveyOwnerDivision" value="true" >
					<a class="special_body_link" href="#" onClick='javascript:submitQuestionForm(
					"<%= OISurveyConstants.SURVEY_QUESTION_DO %>",
					"<%= OISurveyConstants.QUESTION_EDIT %>",
					"<bean:write name="objSection" property="strSectionId" />", "")'
					><bean:write name="objSection" property="strSectionName" /></a> &nbsp; 
			</logic:match>
			<logic:match name="isSurveyOwnerDivision" value="false" >
					<B><bean:write name="objSection" property="strSectionName" /></B> &nbsp; 
			</logic:match>

			<logic:present name="QuestionList" scope="request" >
				<%	
					alQuestList = (java.util.ArrayList) QuestionList.get(secId);	
					com.oifm.survey.OIBAQuestion objQuestion = null;
					if(alQuestList != null && alQuestList.size() > 0) {
						
				%>
				<bean:define id="qstAvailable" value="Y" />
				<a class="special_body_link" href="javascript:QuestionsDisplay('<%= secId %>')"><img src='<bean:message key="OIFM.docroot" />/images/expand.gif' WIDTH="12" HEIGHT="11" BORDER="0" ALT="Questions List"></a>
				</td>
			</tr>
			<tr id="<%= secId %>" class="body_detail_text" >
				<td  >&nbsp; </td>
				<td  width="1%">&nbsp;&nbsp;&nbsp;&nbsp;</td>
				<td width="98%" align="left">
				<table width="100%" cellPadding="3" border="0">
				<%
					topQ=0;
						for (int i=0; i<alQuestList.size(); i++) {
							objQuestion = (com.oifm.survey.OIBAQuestion) alQuestList.get(i);
							topQ++;
				%>
				<tr class="body_detail_text" >
					<td width="90%" >
						<a class="special_body_link" href="#" onClick='javascript:submitQuestionForm(
						"<%= OISurveyConstants.SURVEY_QUESTION_DO %>",
						"<%= OISurveyConstants.QUESTION_EDIT %>", "",
						"<%= objQuestion.getStrQuestionId() %>")'
						><%= objQuestion.getStrQuestion() %></a> 
					</td>
					<td width="10%">
					<a href="#" onClick='javascript:submitReorderQuestion(
						"<%= OISurveyConstants.SURVEY_QUESTION_DO %>",
						"<%= OISurveyConstants.QUESTION_MOVE_UP %>", "",
						"<%= objQuestion.getStrQuestionId() %>","<%= topQ %>","<%= topS %>","<%=alQuestList.size()%>","<%= SectionList.size()%>" ,"<%= objQuestion.getCanMoveUp() %>", "<%= objQuestion.getCanMoveDown() %>")'
						><img src='<bean:message key="OIFM.docroot" />/images/up_arrow_3.gif' border=0></a> 

					<a href="#" onClick='javascript:submitReorderQuestion(
						"<%= OISurveyConstants.SURVEY_QUESTION_DO %>",
						"<%= OISurveyConstants.QUESTION_MOVE_DOWN %>", "",
						"<%= objQuestion.getStrQuestionId() %>","<%= topQ %>","<%= topS %>","<%= alQuestList.size()%>","<%= SectionList.size()%>","<%= objQuestion.getCanMoveUp() %>", "<%= objQuestion.getCanMoveDown() %>")'
						><img src='<bean:message key="OIFM.docroot" />/images/down_arrow_3.gif' border=0></a> 
					</td>
				</tr>
				<%
						}
				%>
				</table>
				<%
					}
				%>
				</td>
			</tr>
			</logic:present>
		</logic:iterate>
	</logic:greaterThan>
	<logic:lessThan name="secSize" value="1">
			<tr>
				<td width="100%" class="body_detail_text" colspan="3">
				<b><bean:message key="NoRecordLoad"/></b>
				</td>
			</tr>
	</logic:lessThan>
</logic:present>
			
            </table>
			</td>
		</tr>
	<tr>
		<td height="9" >
			<br>

			<a href="#" onClick="javascript:submitQuestionListForm(
						'<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
						'<%= OISurveyConstants.SURVEY_ADMIN_LIST %>')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif'  border="0" alt = "Cancel"></a>&nbsp;

<logic:equal name="qstAvailable" value="Y" >
			<a href="#" onClick="javascript:getSurveyPreviewForm(
						'<%= OISurveyConstants.SURVEY_USER_DO %>',
						'<%= OISurveyConstants.SURVEY_PREVIEW %>')" ><img src='<bean:message key="OIFM.docroot" />/images/but_Preview.gif' border="0" alt = "Preview"></a>&nbsp;
	<logic:match name="isSurveyOwnerDivision" value="true" >
			<a href="#" onClick="javascript:submitQuestionListForm(
						'<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
						'<%= OISurveyConstants.SURVEY_ACTIVATE %>')" ><img src='<bean:message key="OIFM.docroot" />/images/but_Publish_Website.gif'  border="0" alt = "Publish in Website"></a>&nbsp;
	</logic:match>
			<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_USER_RSP_DO %>','<%= OISurveyConstants.SURVEY_PRINT_PREVIEW %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/print-preview.gif' alt="Print the Survey" border="0" ></a>
</logic:equal >
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
