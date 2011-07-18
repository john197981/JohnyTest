<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<head>
	<title>My Forum, Ministry Of Education</title>
</head>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="BoxTable">
	<tr bordercolor="58A4DA">
		<td height="25" class="Titleheader">
			<bean:write name="ConsultPastPaperForm" property="categoryName"/>
		</td>
	</tr>
	<tr bordercolor="58A4DA">
		<td class="bodyoutline">
			<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>Paper Title
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9">
							<bean:write name="ConsultPastPaperForm" property="title"/>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>
						Target Audience
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9"><bean:write name="ConsultPastPaperForm" property="targetAudiance"/>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>
						Contact Person
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9"><bean:write name="ConsultPastPaperForm" property="contactPerson"/>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>
						Phone
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9"><bean:write name="ConsultPastPaperForm" property="phone"/>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>
						Email Address
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9"><bean:write name="ConsultPastPaperForm" property="emailAddress"/>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>
						Period
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9">
						<bean:write name="ConsultPastPaperForm" property="fromDt"/> to 
						<bean:write name="ConsultPastPaperForm" property="toDt"/>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td width="19%" class="BodyText1">
						<strong>
						Status
						</strong>
					</td>
					<td width="81%" class="BodyText">
						<span class="style9">
							<logic:present name="ConsultPastPaperForm" property="active">
								<logic:equal name="ConsultPastPaperForm" property="active" value="1">
									Open
								</logic:equal>
								<logic:equal name="ConsultPastPaperForm" property="active" value="0">
									Closed
								</logic:equal>
								<logic:notEqual name="ConsultPastPaperForm" property="active" value="1">
									<logic:notEqual name="ConsultPastPaperForm" property="active" value="0">
										Not Defined
									</logic:notEqual>
								</logic:notEqual>
							</logic:present>
						</span>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td height="21" colspan="2">&nbsp;</td>
				</tr>
				<logic:present name="ConsultPastPaperForm" property="description">
					<tr align="left" valign="top">
						<td height="73" class="BodyText1">
							<strong>Description</strong>
						</td>
						<td class="BodyText">
							<bean:write name="ConsultPastPaperForm" property="description" filter="false" />
						</td>
					</tr>
					<tr align="left" valign="top">
						<td height="20" colspan="2">&nbsp;</td>
					</tr>
				</logic:present>
				<logic:present name="ConsultPastPaperForm" property="summary">
					<tr align="left" valign="top">
						<td height="73" class="BodyText1">
							<strong>Summary</strong>
						</td>
						<td class="BodyText">
							<p class="style12">
								<bean:write name="ConsultPastPaperForm" property="summary" filter="false"/> 
							</p>
						</td>
					</tr>
					<tr align="left" valign="top">
						<td height="20" colspan="2">&nbsp;</td>
					</tr>
				</logic:present>
				<tr align="left" valign="top">
					<td height="25" colspan="2">&nbsp;</td>
				</tr>
				<tr align="left" valign="top">
					<td height="22" class="BodyText">&nbsp;</td>
					<td class="BodyText">
						<a href="javascript:window.print()" >
							<img src='<bean:message key="OIFM.docroot" />/images/button/btn_print.gif' alt="Print the consultation Paper" width="46" height="19" border="0"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td width="72%" align="left" valign="top"> 
		</td>
</table>