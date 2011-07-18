<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<tr>
	<td width="85%" align="center" valign="top">

    <table width="100%" height="35" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td align="left" class="Boxoutline">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
        <tr>
			<td >&nbsp;</td>
		</tr>
        <tr>
			<td class="Mainheader"> Discussion Forum </td>
		</tr>
        <tr>
			<td >&nbsp;</td>
		</tr>
        <tr>
			<td >
			<table width="60%"  border="1" align="center" cellpadding="0" cellspacing="2" bordercolor="#6B9FE3" bgcolor="#FFFFFF">
			<tr>
				<td align="center">
				<table width="100%"  border="1" cellpadding="0" cellspacing="0" bordercolor="#FF0000">
				<tr>
					<td height="35" bgcolor="#FCECEB">
					<div align="center" class="bold">
						<logic:notPresent name="error" scope="request">
							<logic:present parameter="error" scope="request">
								<bean:parameter id="errorId" name="error"/>
								<bean:write name="errorId" />
							</logic:present>
						</logic:notPresent>
						<logic:present name="error" scope="request">
							<bean:write name="error" scope="request" />
						</logic:present>
						<logic:present name="message" scope="request">
							<logic:present name="refreshUrl" scope="request">
							<SCRIPT LANGUAGE="JavaScript">
							<!--
								window.opener.location.href = '<bean:message key="OIFM.contextroot" /><bean:write name="refreshUrl" />';
							//-->
							</SCRIPT>
							</logic:present>
							<logic:iterate id="msg" name="message" scope="request" type="java.lang.String">
								<b><bean:message key="<%= msg %>"/></b><br><br>
							</logic:iterate>
						</logic:present>
					</div>
					</td>
				</tr>
				</table>
				</td>
			</tr>
			</table>
			</td>
		</tr>
		</table>
		</td>
	</tr>
	</table>
	</td>
</tr>
</table>

<div align="center"><br>
		<a href='#' onClick="window.close()"><img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif' border="0" alt="Close"><a>
</div>
