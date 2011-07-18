<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

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
	document.ASMFormAnnouncement.hiddenAction.value = 'SAVE';
	document.ASMFormAnnouncement.submit();
}

var reset;

function resetForm() {
	editor_setHTML('taContentId', reset);
}
//-->
</script>

<html:form action="/ASMAnnouncement.do" method="post">

	<html:hidden property="hiddenAction" />
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
					<td>&nbsp;</td>
				</tr>
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

 				<tr class="Table_head">
					<td colspan="2" height="24">Announcement</td>
				</tr>
 						<tr height="1"></tr>
						<tr>
							<td width="10%" valign="top" nowrap class="Heading_Thread">Message</td>
							<td width="90%" class="body_detail_text"><html:textarea
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
		reset = document.ASMFormAnnouncement.message.value;
	</script>
</html:form>
