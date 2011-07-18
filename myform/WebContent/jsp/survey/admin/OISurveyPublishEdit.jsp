<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
 
<SCRIPT LANGUAGE="JavaScript">
	<!--
	function submitSurveyListForm(submitUrl, actionName) 
	{
		var flag = true;
		var frm = document.SurveyForm;
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.hiddenAction.value = actionName;
		if(actionName == "<%= OISurveyConstants.SURVEY_PUBLISH %>") 
		{
			if(flag) 
				flag = checkBlank(frm.strSummary, "Summary");
			if(flag) 
				flag = txtAreaMaxLen(frm.strSummary, 4000, "Summary");
			if(flag) 
				flag = checkSelectedIndex(frm.strAcknowledgeMode, " Acknowledge Mode");
			if(flag) 
				flag = checkFileType(frm.attachedFile);
		}
		if(flag) 
			frm.submit();
		else 
			return;
	}

	function checkFileType(upFile) 
	{
		var str = upFile.value;
		if(str.length > 0) 
		{
			var ext = str.substring(str.length-3, str.length);
			ext = ext.toLowerCase();
			if(ext != 'pdf') 
			{
				alert('<%= OIDBRegistry.getValues("OI.SU.SURVEY.PDF.SELECT") %>');
				return false; 
			} 
			else 
				return true; 
		} 
		else 
			return true;
	}

	function clearFormData()
	{
		document.SurveyForm.reset();
	}

	//-->
</script>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/SurveyAdmin.do" method="post"  enctype="multipart/form-data">

	<html:hidden property="hiddenAction" />
	<html:hidden property="strSurveyId" />

	<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />
	<bean:define id="SurveyForm" name="SurveyForm" scope="request" type="com.oifm.survey.OIFormSurvey"/>

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="2">Publish Survey </td>
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
									<td width="100%" align="center" colspan="2" valign="top" class="body_detail_text">
										<b><bean:message key="msg"/></b>
									</td>
								</tr>
							</logic:iterate>
						</logic:present>
  								<tr>
									<td width="13%" valign="top" class="heading_thread">Survey Name </td>
									<td class="body_detail_text">
										<bean:write name="SurveyForm" property="strSurveyName"	/>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread">Period</td>
									<td class="body_detail_text">
										<bean:write name="SurveyForm" property="strFromDate" 	/> To 
										<bean:write name="SurveyForm" property="strToDate"	/>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread" valign="top">Description</td>
									<td class="body_detail_text">
										<bean:write name="SurveyForm" property="strDescription" filter="false" /><br>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread"  valign="top">Instructions</td>
									<td class="body_detail_text">
										<bean:write name="SurveyForm" property="strInstruction"	filter="false" /><br>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread">Survey Type </td>
									<td class="body_detail_text">
										<logic:match name="SurveyForm" property="strSurveyType" value="N">Public  </logic:match>
										<logic:match name="SurveyForm" property="strSurveyType" value="Y">Private  </logic:match>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread"  valign="top">Summary  <B><font color="#FF0000">*</font></B></td>
									<td class="body_detail_text">
										<html:textarea styleClass="Text" property="strSummary" cols="80" rows="4" onkeydown="fn_textCounter(document.SurveyForm.strSummary, document.SurveyForm.numleft1, 4000);" onkeyup="fn_textCounter(document.SurveyForm.strSummary, document.SurveyForm.numleft1, 4000);" onmouseover="fn_textCounter(document.SurveyForm.strSummary, document.SurveyForm.numleft1, 4000);" onmouseout="fn_textCounter(document.SurveyForm.strSummary, document.SurveyForm.numleft1, 4000);"/>
										<div align="left">
											<font color="#005BCC" >
												No. of characters remaining: 
												<input name="numleft1" type="text" size="5" value="4000" disabled style="taInfo">
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread">Acknowledge Users <B><font color="#FF0000">*</font></B></td>
									<td class="body_detail_text"> 
										<html:select property="strAcknowledgeMode" styleClass="Text" >
											<option >Please Select...</option>
											<logic:present name="AckTypes" scope="request" >
												<html:options collection="AckTypes" property="value" labelProperty="label"	/>
											</logic:present>
										</html:select>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_thread">Attach Survey Result </td>
									<td class="heading_attributes">	
										<html:file  property="attachedFile" styleClass="Text" /> (Max 2MB) 
									</td>
								</tr>
								<%
									String attachFileName = SurveyForm.getStrAttachedFile();
									if(attachFileName != null && !attachFileName.equals("")) 
									{
								%>
										<tr>
											<td valign="top" class="body_detail_text" colspan="2">
 												<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_USER_DO %>','<%= OISurveyConstants.SURVEY_DOWNLOAD %>')" >
													<bean:write name="SurveyForm" property="strAttachedFile" /></a>
												&nbsp;&nbsp;
												<logic:match name="isSurveyOwnerDivision" value="true">
													<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_ATTACHMENT_REMOVE %>')" >
														<img src='<bean:message key="OIFM.docroot" />/images/remove.gif' alt="Remove Attachment"  border="0"></a>
												</logic:match>
											</td>
										</tr>
								<%
									}
								%>
 					<tr>
						<td height="35" align="left" colspan="2">
							<logic:match name="isSurveyOwnerDivision" value="true">
								<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_PUBLISH %>')" >
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt = "Save"></a>&nbsp;
								<a href="#" onClick="javascript:clearFormData()" >
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>&nbsp;
							</logic:match>
								<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_ADMIN_LIST %>')" >
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a>&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
	<logic:match name="isSurveyOwnerDivision" value="false" >
		<SCRIPT LANGUAGE="JavaScript">
			<!--
				disableElements(document.SurveyForm);
			//-->
		</SCRIPT>
	</logic:match>
</html:form >