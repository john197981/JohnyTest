<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
try
{
 %>
<html>
<head>
	<title>My Forum, Ministry Of Education</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<style type="text/css">
		<!--
			.style4 
			{
				color: #000000; text-align: left; border-right-style: solid; border-bottom-style: solid; 
				border-top-width: thin; border-right-width: 1px; border-bottom-width: 1px; border-left-width: thin; 
				padding-right: 8px; padding-left: 8px; background-color: #EAF4FA; font-size: 12px; 
				border-right-color: #58A4DA; border-bottom-color: #58A4DA; font-family: Arial;
			}
		-->
	</style>
</head>
<body>

<logic:present name="error" scope="request">
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable">
		<tr>
    		<td width="75%" align="center" valign="top" class="Mainheader">
				<bean:write name="error" scope="request" />
			</td>
		</tr>
	</table>    
</logic:present>

<logic:present name="message" scope="request">
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable">
		<logic:iterate id="mList" name="message" scope="request">
			<tr>
    			<td width="75%" align="center" valign="top" class="Mainheader">
					<bean:write name="mList"/>
				</td>
			</tr>
		</logic:iterate>
	</table>    
</logic:present>

	<br>
	<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
		<tr>
			<td width="72%" align="left" valign="top">
				<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td  height="25" class="Table_head"> Member Profile of <bean:write name="MemberProfileForm" property="nickName"/></td>
 		            </tr>
				</table>
			</td>
		</tr>
    </table>      
	<table width="98%"  border="1" align="center" cellpadding="2" cellspacing="0"  bgcolor="white" >
		<tr>
			<td>
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td class="box">
							<table width="100%" height="88"  border="0" align="center" cellpadding="0" cellspacing="0" >
								<tr>
									<td height="88" align="left" valign="top" >
										<table width="100%" height="177"  border="0" align="center" cellpadding="0" cellspacing="0"  >
											<!--<tr> 
												<td width="19%" height="25" class="BodyText1">Nick Name </td>
												<td width="81%" height="25" class="style4">
													<bean:write name="MemberProfileForm" property="nickName"/>&nbsp
												</td>
											</tr>-->
											<tr>
												<td height="25" class="Heading_Thread" nowrap width="10%">Hobbies</td>
												<td height="25" class="body_detail_text">
													<bean:write name="MemberProfileForm" property="hobbies"/>&nbsp
												</td>
											</tr>
											<tr>
												<td height="25" class="Heading_Thread" nowrap >
													Areas Of Interest 
												</td>
												<td height="25" class="body_detail_text">
													<bean:write name="MemberProfileForm" property="interest"/>&nbsp
												</td>
											</tr>
											<tr> 
												<td height="25" class="Heading_Thread" nowrap >No Of Posts </td>
												<td height="25" class="body_detail_text">
														<bean:write name="MemberProfileForm" property="totalPosting"/>&nbsp
												</td>
											</tr>
											<tr> 
												<td height="22" class="Heading_Thread" nowrap >Signature</td>
												<td height="27" class="body_detail_text">
													<bean:write name="MemberProfileForm" property="signature"/>&nbsp																									
												</td>
											</tr>
											<tr>
												<td height="25" class="Heading_Thread" nowrap >&nbsp;</td>
												<td height="25" class="body_detail_text">
													<a href="#" onclick="javascript:self.close();">
														<img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif' border="0" alt = "OK">
													</a>
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
</body>
</html>

<%
}catch(Exception e)
{
out.println(e.getMessage());
}
%>