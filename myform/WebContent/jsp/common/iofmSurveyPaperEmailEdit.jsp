<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<%
if(request.getParameter("module")==null || request.getParameter("id")==null)
{
	%>
	<script>window.close();</script>
	<%
}
%>
<script language="Javascript"
	src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>

<script language="JavaScript" type="text/JavaScript">
<!--

var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 
	}

function submitForm() {
	editor_updateOutput('taContentId');
	if('S'=='<%= request.getAttribute("callingModule") %>')
	{
		window.opener.document.SurveyForm.strEmailMessage.value=document.OIFormSurveyPaperEmailEdit.message.value;
	}
	else if('C'=='<%= request.getAttribute("callingModule") %>')
	{
		window.opener.document.ConsultPageForm.strEmailMessage.value=document.OIFormSurveyPaperEmailEdit.message.value;
	}
	window.close();
}


function fnPopEmail()
{
	if('S'=='<%= request.getAttribute("callingModule") %>')
	{
		document.OIFormSurveyPaperEmailEdit.message.value=window.opener.document.SurveyForm.strEmailMessage.value;
		document.OIFormSurveyPaperEmailEdit.beforeReset.value=window.opener.document.SurveyForm.strEmailMessage.value;
	}
	else if('C'=='<%= request.getAttribute("callingModule") %>')
	{
		document.OIFormSurveyPaperEmailEdit.message.value=window.opener.document.ConsultPageForm.strEmailMessage.value;
		document.OIFormSurveyPaperEmailEdit.beforeReset.value=window.opener.document.ConsultPageForm.strEmailMessage.value;
	}
}

var reset;

function resetForm() {
	editor_setHTML('taContentId', document.OIFormSurveyPaperEmailEdit.beforeReset.value);
	//fnPopEmail();
}
//-->
</script>
<body onLoad="fnPopEmail();">
<html:form action="/OIEmailEdit.do" method="post">
	<input type="hidden" name="beforeReset">
	<html:hidden property="hiddenAction" value='RETRIEVE' />
	 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

	<table width="98%" border="0" cellpadding="0"
		cellspacing="0">
		<tr>
			<td class="TableHead">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="Box">
		<logic:present name="status" scope="request">
		<table width="100%" border="0" cellpadding="0"
			cellspacing="0" bgcolor="white">
			<logic:match name="status" scope="request" value="error">
			
				<tr>
					<td width="100%" class="BodyText" colspan="3">
					<div align="center"><b><bean:message name="error" scope="request" /></b></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</logic:match>
			<logic:notMatch name="status" scope="request" value="error">
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td width="100%" class="BodyText">
					<div align="center"><b><bean:message name="status" scope="request" /></b></div>
					</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
			</logic:notMatch>
			</table>
		</logic:present>
		<table width="100%" border="0" cellpadding="0"
			cellspacing="0" bgcolor="white">

 				
 						<tr height="1"></tr>
						<tr>
						<td colspan=2 valign="top" nowrap class="sub_head" ><b>Edit Email</b></td>
						</tr>
						<tr>
							
							<td width="100%" class="body_detail_text" colspan=2><html:textarea 
								property="message" styleId="taContentId" cols="70" rows="15" />
							<script language="javascript">
								var config = new Object();
								config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
								config.debug = 0;
								editor_generate('taContentId',config);
							</script></td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr >

							<td>&nbsp;</td>
							<td><a href="#" onclick="submitForm()"><img
								src="<bean:message key="OIFM.docroot" />/images/but_save.gif"
								alt="Save"  border="0" ALT = "Save"></a> <a href="#"
								onclick="resetForm()"><img
								src="<bean:message key="OIFM.docroot" />/images/but_reset.gif"
								alt="Reset"  border="0" ALT = "Reset"></a>
				</td>
				</tr>				
			</table>
			</td>
 		</tr>
	</table>	

				</td>
 		</tr>
	</table>	

	
	<script language="JavaScript">
		//reset = document.OIFormSurveyPaperEmailEdit.message.value;
	</script>
</html:form>
