<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIAddEmailsConstants" %>

<html:html>
<head>
	<title>Add Users by Email</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<script language="javascript">
		function back() {
			document.AddEmailsForm.hiddenAction.value = '<%= OIAddEmailsConstants.ACTION_INPUT %>';
			document.AddEmailsForm.submit();
		}
		function checkAll() {
			if(document.AddEmailsForm.arMembers.length != undefined) {
				for (i = 0; i < document.AddEmailsForm.arMembers.length; i++)
					if (document.AddEmailsForm.arMembers[i].disabled != true)
						document.AddEmailsForm.arMembers[i].checked = document.AddEmailsForm.selectAll.checked;
			} else {
				if (document.AddEmailsForm.arMembers.disabled != true) {
					document.AddEmailsForm.arMembers.checked = document.AddEmailsForm.selectAll.checked;
				}
			}
		}

		function checkSelectAll(chkbx) {
			if (!chkbx.checked) document.AddEmailsForm.selectAll.checked = false;
		}

		function addUsers() {
			var flag = false;
			if(document.AddEmailsForm.arMembers.length != undefined) {
				for (i = 0; i < document.AddEmailsForm.arMembers.length; i++) {
					if (flag = document.AddEmailsForm.arMembers[i].checked) break;
				}
			} else {
				flag = document.AddEmailsForm.arMembers.checked;
			}

			if (!flag) alert('<bean:message key="OI.GROUPS.ADDEMAILS.NO_USER"/>');
			else {
				document.AddEmailsForm.hiddenAction.value = '<%= OIAddEmailsConstants.ACTION_ADD_MEMBERS %>';
				document.AddEmailsForm.submit();
			}
		}
	</script>
</head>

<body>
<html:form action ="/AddEmails.do"  method="post">

<html:hidden property="hiddenAction"/>
<html:hidden property="strGroupID"/>
<html:hidden property="strEmails"/>


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
						<td colspan="5">Add Users by Email</td>
 					</tr>
 	<tr class="subhead">
		<td class="sub_head"><input type="checkbox" name="selectAll" value="selectAll" onclick="checkAll()"></td>
		<td class="sub_head">Email ID</td>
		<td class="sub_head">User ID</td>
		<td class="sub_head">Name</td>
		<td class="sub_head">Status</td>
		</tr>
		<logic:iterate id="objEmails" name="SuccessMails" indexId="idx" type="com.oifm.common.OIBAAddEmails">
			<tr>
				<td width="2%" class="body_detail_text">
						<logic:match name="objEmails" property="alreadyMember" value="true">
							<html:multibox property="arMembers" disabled="true">
								<bean:write name="objEmails" property="strUserID" />
							</html:multibox>
						</logic:match>
						<logic:notMatch name="objEmails" property="alreadyMember" value="true">
							<html:multibox property="arMembers" onclick="checkSelectAll(this)">
								<bean:write name="objEmails" property="strUserID" />
							</html:multibox>
						</logic:notMatch>
					</td>
				<td width="20%" class="body_detail_text"><bean:write name="objEmails" property="strEmailID" /></td>
				<td width="15%" class="body_detail_text"><a class="special_body_link" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="objEmails" property="strUserID" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="objEmails" property="strUserID" /></a></td>
				<td width="44%" class="body_detail_text"><bean:write name="objEmails" property="strName" /></td>
				<td width="15%" class="body_detail_text"><div align="center"><font color="green">OK</font></div></td>
			</tr>
		</logic:iterate>
				<logic:iterate id="objEmails" name="FailedMails" indexId="idx" type="com.oifm.common.OIBAAddEmails">
					<tr>
						<td width="2%" class="body_detail_text">&nbsp</td>
						<td width="20%" class="body_detail_text"><bean:write name="objEmails" property="strEmailID" /></td>
						<td width="15%" class="body_detail_text">N/A</td>
						<td width="44%" class="body_detail_text">N/A</td>
						<td width="15%" class="body_detail_text">Invalid</td>
					</tr>
				</logic:iterate>
				<tr>
					<td colspan="5">&nbsp;</td>
				</tr>
  	<tr>
		<bean:size id="arrSize" name="SuccessMails" scope="request" />
		<td class="body_detail_text" colspan="5">
			<% if (arrSize.intValue() > 0) { %><a href="#" onclick="addUsers()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_AddUsers.gif" border="0" alt="Add Users"></a> <% } %><a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt="Cancel"></a> <a href="#" onclick="back()"><img src="<bean:message key="OIFM.docroot"/>/images/but_back.gif" border="0" alt="Back"></a>
		</td>
	</tr>
	<tr>
		<td colspan="5">&nbsp;</td>
	</tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>

</html:form>
</body>
</html:html>

