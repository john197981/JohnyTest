<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIAddEmailsConstants" %>
<%@ page import="com.oifm.useradmin.OIGroupsConstants" %>


<html:html>
<head>
	<title>Add Users by Email</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<script language="javascript">
		function bodyLoad() {
			if (document.AddEmailsForm.hiddenAction.value == '<%= OIAddEmailsConstants.ACTION_ADD_MEMBERS %>') {
				opener.document.GroupsForm.action = "Groups.do?hiddenAction=<%= OIGroupsConstants.EDIT_GROUP %>&nextAction=";
				opener.document.GroupsForm.submit();
			}
		}
		function doVerify() {
			var emails = trim(document.AddEmailsForm.strEmails);
			if (emails.value.length != 0){			
				document.AddEmailsForm.hiddenAction.value = '<%= OIAddEmailsConstants.ACTION_VERIFY %>';
				document.AddEmailsForm.submit();
			} else alert('<bean:message key="OI.GROUPS.ADDEMAILS.NO_EMAIL"/>');
		}
	</script>
	<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
</head>

<body onload="bodyLoad()">
<html:form action ="/AddEmails.do"  method="post">

<html:hidden property="hiddenAction"/>
<html:hidden property="strGroupID"/>


<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="1" cellpadding="1">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td>Add Users by Email</td>
 					</tr>

 	<tr>
		<td>&nbsp;</td>
	</tr> 
 				<tr>
					<td class="heading_thread">&nbsp;Enter email addresses (separated by comma) : </td>
				</tr>
				<tr>
					<td class="body_detail_text"><html:textarea property="strEmails" styleClass="Text_aria" cols="65" rows="10"></html:textarea></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td class="Text">
			<a href="#" onclick="doVerify()"><img src="<bean:message key="OIFM.docroot"/>/images/verify.gif" border="0" alt="Verify"></a> <a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt="Cancel"></a>
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
</body>
</html:html>

