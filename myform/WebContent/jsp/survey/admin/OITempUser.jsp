<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>

<SCRIPT LANGUAGE="JavaScript">
	<!--
	function submitTempUserForm() 
	{
		submitTempUserListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>', '<%= OISurveyConstants.TEMP_USER_LIST %>');
	}

	function submitTempUserListForm(submitUrl, actionName) 
	{
		var flag = true;
		var frm = document.TempUserForm;
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.hiddenAction.value = actionName;
		if(actionName == "<%= OISurveyConstants.TEMP_USER_GENERATION %>") 
		{
			if(flag) 
				flag = checkSelectedIndex(frm.strSurveyId, "Survey");
			if(flag) 
				flag = checkBlank(frm.noOfUsers, "Number of Users");
			if(flag) 
				flag = checkNumber(frm.noOfUsers, "Number of Users");
			if(flag) 
				flag = checkBlank(frm.noOfUsers, "Number of Users");
		} 
		else if(actionName == "<%= OISurveyConstants.SURVEY_EDIT %>") 
		{
			if(flag) 
				flag = checkSelectedIndex(frm.strSurveyId, "Survey");
		} 
		else if(actionName == "<%= OISurveyConstants.TEMP_USER_REMOVE %>" || actionName == "<%= OISurveyConstants.TEMP_USER_EXPORT %>") 
		{
			if(flag) 
				flag = checkSelectedIndex(frm.strSurveyId, "Survey");
			if(flag) 
				flag = checkSelectedIndex(frm.strBatchNo, "Batch Number");
		} 
		if(flag) 
			frm.submit();
		else 
			return;
	}
	
	function deleteTempUserListForm(submitUrl, actionName) 
	{
		var flag = confirm('<%= OIDBRegistry.getValues("OI.SU.TEMP.CONFIRM.DELETE") %>');
		if(flag) 
			submitTempUserListForm(submitUrl, actionName);
		else 
			return false;
	}

	function clearFormData()
	{
		document.TempUserForm.reset();
	}
	//-->
</script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/SurveyTempUser.do" method="post" >
 	<html:hidden property="hiddenAction" />

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
						<td colspan="2">Temp Users Creation </td>
 					</tr>

					<logic:present name="error" scope="request" >
						<tr>
							<td  class="body_detail_text" colspan="2">
								<b><bean:message name="error" scope="request"/></b>
							</td>
						</tr>
					</logic:present>
					
					<logic:present name="message" scope="request">
						<logic:iterate id="msg" name="message" scope="request">
							<tr>
								<td colspan="2" align="center" valign="top" class="body_detail_text">
									<b><bean:message key="msg"/></b>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
							
							<tr>
								<td width="17%" class="heading_thread">
									Survey <B><font color="#FF0000">*</font></B>
								</td>
								<td width="83%" class="body_detail_text">
									<html:select property="strSurveyId" styleClass="Text" onchange="javascript:submitTempUserForm()" >
										<html:option value="">Please select......</html:option>
										<logic:present name="SurveyList" scope="request" >
											<html:options collection="SurveyList" property="strSurveyId" labelProperty="strSurveyName" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<td width="17%" class="heading_thread">No. Of Users </td>
								<td class="body_detail_text">
									<html:text styleClass="Text_box" property="noOfUsers" size="10" maxlength="4" />
								</td>
							</tr>
							<tr>
								<td width="17%" class="heading_thread">Available Batches </td>
								<td class="body_detail_text">
									<html:select property="strBatchNo" styleClass="Text" onchange="javascript:submitTempUserForm()" >
										<html:option value="">Please select ......</html:option>
										<logic:present name="SurveyBatchList" scope="request" >
											<html:options name="SurveyBatchList" />
										</logic:present>
									</html:select>
								</td>
							</tr>
							<tr>
								<td height="35" align="left"  colspan="2">
									<a href="#" onClick="javascript:submitTempUserListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_GENERATION %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_GenerateIDs.gif' border="0" alt = "Generate IDs"></a> &nbsp;
									<a href="#" onClick="javascript:submitTempUserListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_ADMIN_LIST %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif'  border="0" alt = "Cancel"></a> &nbsp;
									<a href="#" onClick="javascript:deleteTempUserListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_REMOVE %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/remove.gif' alt="Remove users from the survey" border="0"></a> &nbsp;
									<a href="#" onClick="javascript:submitTempUserListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_EXPORT %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Export_UserID.gif'  border="0" alt = "Export User ID"></a> &nbsp;
									<a href="#" onClick="window.print();" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_print.gif'  border="0" alt = "Print"></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr><td colspan=2>&nbsp;</td></tr>
				<tr>
					<td height="35" align="left">
						<table width="60%"  border="0" cellpadding="2" cellspacing="1" class="box">
							<tr>
								<td colspan="3" height="9" class="table_head" >List of Users</td>
							</tr>
 
							<logic:present name="SurveyBatchUsersList" scope="request" >
								<bean:size id="secSize" name="SurveyBatchUsersList" scope="request" />
								<logic:greaterThan name="secSize" value="0">
									<tr class="subhead">
										<td width="140" class="sub_head">User ID </td>
										<td width="120" class="sub_head">Password</td>
										<td width="70" class="sub_head">Obsolete</td>
									</tr>
									<logic:iterate id="objTempUser" name="SurveyBatchUsersList" type="com.oifm.survey.admin.OIBATempUser" scope="request">
										<tr class="body_detail_text">
											<td class="body_detail_text"><bean:write name="objTempUser" property="strTempUserId" /></td>
											<td class="body_detail_text"><bean:write name="objTempUser" property="strPassword" /></td>
											<td class="body_detail_text"><bean:write name="objTempUser" property="strObsolete" /></td>
										</tr>
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
				</table>
			</td>
		</tr>
	</table>
 </html:form>