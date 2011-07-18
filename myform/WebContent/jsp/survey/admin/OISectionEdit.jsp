<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>

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

		function openNewWindow(str)
		{
			document.SectionForm.previewDescription.value = str;
			window.open('<bean:message key="OIFM.docroot" />/html/preview.html','mywindow','left=20,top=20,width=400,height=275,toolbar=0,resizable=0');
		}

<!--
 
function submitSectionListForm(submitUrl, actionName, sectionId) {
	var frm = document.SectionForm;
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.strSectionId.value = sectionId;
	frm.submit();
}

function submitSectionForm(submitUrl, actionName) {
	var frm = document.SectionForm;
	var flag = true;
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	if(actionName == "<%= OISurveyConstants.SECTION_SAVE %>") {
//		if(flag) flag = checkSelectedIndex(frm.strParentId, "Parent Section");
		if(flag) flag = checkBlank(frm.strSectionName, "Section Name");
		if(flag) flag = checkBlank(frm.strDescription, "Description");
		if(flag) flag = txtAreaMaxLen(frm.strDescription, 1000, "Description");
		trim(frm.strInstruction);
		if(flag) flag = txtAreaMaxLen(frm.strInstruction, 1000, "Instruction");
	}
	if(flag) frm.submit();
	else return;
}

function deleteSectionForm(submitUrl, actionName) {
	var flag = confirm('<%= OIDBRegistry.getValues("OI.SU.SECTION.DELETE.CONFIRM") %>');
	if(flag) submitSectionForm(submitUrl, actionName);
	else return false;
}

function clearFormData()
{
	editor_setHTML('taContentId',hidStrDescription);
	editor_setHTML('taContentId1',hidStrInstruction);
	document.SectionForm.reset();
}
//-->
</script>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/SurveySection.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />

<bean:define id="alSectionList" name="SectionList" scope="request" type="java.util.ArrayList"/>
<bean:define id="alSectionParentsList" name="alSectionList"  type="java.util.ArrayList" />
<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />

<logic:present name="SectionParentsList" scope="request">
	<bean:define id="alSectionParentsList" name="SectionParentsList" scope="request" type="java.util.ArrayList" />
</logic:present>

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
						<td colspan="2">Survey</td>
 					</tr>

			 <logic:present name="error" scope="request" >
				<tr>
					<td width="100%" class="body_detail_text" colspan="2">
					<b><bean:message name="error" scope="request"/></b>
					</td>
				</tr>
			</logic:present>

		<logic:present name="message" scope="request">
				<logic:iterate id="msg" name="message" scope="request">
			<tr>
				<td width="100%" align="center" valign="top" class="Sub_head" colspan="2">
					<b><bean:message key="msg"/></b>
				</td>
			</tr>
				</logic:iterate>
		</logic:present>
		<!-- modify by edmund-->
    <tr>
		<td colspan="2"><div align="left"><img src='<bean:message key="OIFM.docroot" />/images/tab_sections.gif' width="356" height="27" border="0" usemap="#MapMap">
<logic:greaterThan name="SectionForm" property="strSurveyId" value="0">
                <map name="MapMap">
                      <area shape="rect" coords="7,0,62,32" href="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
						'<%= OISurveyConstants.SURVEY_EDIT %>')" >
					  <area shape="rect" coords="76,3,141,27" href="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_SECTION_DO %>',
						'<%= OISurveyConstants.SECTION_EDIT %>')" >
                      <area shape="rect" coords="150,0,284,29" href="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_QUESTION_DO %>',
						'<%= OISurveyConstants.QUESTION_LIST %>')" >
					  <area shape="rect" coords="290,0,380,29" href="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_RESULT_DO %>',
						'<%= OISurveyConstants.RESULT_TYPE %>')" >
				</map>
</logic:greaterThan>
		</div></td>
	</tr>
         <tr>
			<td colspan="2" class="Sub_head">Section </td>
		</tr>
        <tr>
			<td width="17%" class="heading_attributes" valign="top">Parent Section  <B><font color="#FF0000">*</font></B></td>
			<td width="83%" class="body_detail_text">
			<html:select property="strParentId" styleClass="Text_box" >
				<html:option value="">Root</html:option>
				<html:options collection="alSectionParentsList" property="strSectionId"
					labelProperty="strSectionName" />
				</html:select>
			</td>
		</tr>
        <tr>
			<td width="17%" class="heading_attributes" valign="top">Section Name <B><font color="#FF0000">*</font></B></td>
			<td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strSectionName" size="82" maxlength="150"	/>
			</td>
		</tr>
        <tr>
			<td class="heading_attributes"  valign="top">Description <B><font color="#FF0000">*</font></B></td>
            <td class="body_detail_text">
			<INPUT TYPE="hidden" name="previewDescription">
						<html:textarea property="strDescription" styleId="taContentId" cols="70" rows="6" styleClass="text_area" onkeydown="fn_textCounter(document.SectionForm.strDescription, document.SectionForm.numleft, 1000);" onkeyup="fn_textCounter(document.SectionForm.strDescription, document.SectionForm.numleft, 1000);" onmouseover="fn_textCounter(document.SectionForm.strDescription, document.SectionForm.numleft, 1000);" onmouseout="fn_textCounter(document.SectionForm.strDescription, document.SectionForm.numleft, 1000);"/>

						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId',config);
						</script>
						<br>
				<a class="special_body_link" href="#" onClick="javascript:openNewWindow(document.SectionForm.strDescription.value);">Preview open window</a>
				<div align="left">
					<font color="#005BCC" >
						<!--No. of characters remaining: -->
						<input name="numleft" type="hidden" class="text_box" size="5" value="1000" disabled style="taInfo">
					</font>
				</div>
			</td>
		</tr>
        <tr>
			<td class="heading_attributes"  valign="top">Instructions</td>
            <td class="body_detail_text">
 						<html:textarea property="strInstruction" styleId="taContentId1" cols="70" rows="6" styleClass="text_area" onkeydown="fn_textCounter(document.SectionForm.strInstruction, document.SectionForm.numleft1, 1000);" onkeyup="fn_textCounter(document.SectionForm.strInstruction, document.SectionForm.numleft1, 1000);" onmouseover="fn_textCounter(document.SectionForm.strInstruction, document.SectionForm.numleft1, 1000);" onmouseout="fn_textCounter(document.SectionForm.strInstruction, document.SectionForm.numleft1, 1000);"/>

						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId1',config);
						</script>
						<br>
				<a class="special_body_link" href="#" onClick="javascript:openNewWindow(document.SectionForm.strInstruction.value);">Preview open window</a>
				<div align="left">
					<font color="#005BCC" >
						<!--No. of characters remaining: -->
						<input name="numleft1" type="hidden" class="text_box" size="5" value="1000" disabled style="taInfo">
					</font>
				</div>
			</td>
		</tr>
     <tr>
		<td height="35" align="left" colspan="2"> 
<logic:match name="isSurveyOwnerDivision" value="true" >
		<a href="#" onClick="javascript:submitSectionListForm(
						'<%= OISurveyConstants.SURVEY_SECTION_DO %>',
						'<%= OISurveyConstants.SECTION_EDIT %>', '')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Add_new_selection.gif'  border="0" alt = "Add New Section"></a>&nbsp;
		<a href="#" onClick="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_SECTION_DO %>',
						'<%= OISurveyConstants.SECTION_SAVE %>', '')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif'  border="0" alt = "Save"></a>&nbsp;
		<a href="#" onClick="javascript:clearFormData()" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>&nbsp;
	<logic:greaterThan name="SectionForm" property="strSectionId" value="0">
		<logic:match name="isCurrentlyValid" value="true" scope="request">
		<a href="#" onClick="alert('You should not delete section, when survey period is valid')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Delete.gif'  border="0" alt = "Delete"></a>&nbsp;
		</logic:match>
		<logic:match name="isCurrentlyValid" value="false" scope="request">
		<a href="#" onClick="<%= ((alSectionParentsList.size() == alSectionList.size()-1)? "javascript:deleteSectionForm('"+ OISurveyConstants.SURVEY_SECTION_DO +"', '"+ OISurveyConstants.SECTION_DELETE +"')":"alert('This Section contains sub sections. You can not delete.')") %>" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Delete.gif' border="0"></a>&nbsp;
		</logic:match>
	</logic:greaterThan>
</logic:match>
		<a href="#" onClick="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_QUESTION_DO %>',
						'<%= OISurveyConstants.QUESTION_LIST %>')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_next.gif' border="0"></a>&nbsp;
		<a href="#" onClick="javascript:submitSectionForm(
						'<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
						'<%= OISurveyConstants.SURVEY_ADMIN_LIST %>')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0"></a>

 		</td>
	</tr>
	</table>
		</td>
	</tr>
			<tr>
			<td >
 
		<table width="100%"  border="0" cellpadding="3" cellspacing="0" bgcolor="white" class="box">
		<tr class="table_head">
			<td height="9" >Sections</td>
		</tr>
<logic:present name="SectionList" scope="request" >
	<bean:size id="secSize" name="SectionList" scope="request" />
	<logic:greaterThan name="secSize" value="0">
		<logic:iterate id="objSection" name="SectionList" indexId="ind" type="com.oifm.survey.OIBASection">
		<tr>
	<logic:equal name="objSection" property="level" value="1">
			<td width="75%" class="special_body_link" valign="top">
	</logic:equal>
	<logic:notEqual name="objSection" property="level" value="1">
			<td width="75%" class="special_body_link" valign="top">
	</logic:notEqual>
	<font class="special_body_link" >
		<bean:write name="objSection" property="strLevelLabel" />.&nbsp
		</font>
		<a class="special_body_link" href="#" onClick='javascript:submitSectionListForm(
			"<%= OISurveyConstants.SURVEY_SECTION_DO %>",
			"<%= OISurveyConstants.SECTION_EDIT %>",
			"<bean:write name="objSection" property="strSectionId" />")'
		><bean:write name="objSection" property="strSectionName" /></a>
			</td>
		</tr>
		</logic:iterate>
	</logic:greaterThan>
	<logic:lessThan name="secSize" value="1">
		<tr>
			<td width="100%" class="body_detail_text" >
			<b><bean:message key="NoRecordLoad"/></b>
			</td>
		</tr>
	</logic:lessThan>
</logic:present>

						</table>
			</td>
		</tr>
		<tr height="20"><td colspan="2"></td></tr>

	</table>
	</td>
	</tr>
</table>

<logic:notMatch name="isSurveyOwnerDivision" value="true" >
<SCRIPT LANGUAGE="JavaScript">
<!--
	disableElements(document.SectionForm);
//-->
</SCRIPT>
</logic:notMatch>
<script language="javascript">
	var hidStrDescription = document.SectionForm.strDescription.value;
	var hidStrInstruction = document.SectionForm.strInstruction.value;
</script>
</html:form>
