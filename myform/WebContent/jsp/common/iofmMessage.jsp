<%--
/**
 * FileName			: iofmMessage.jsp 
 * Author      		: Suresh Kumar.R
 * Created Date 	: 10 jul 2005
 * Description 		: This page used to display the list of Papers
 * Version			: 1.0
 **/
--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIBASendMail" %>
<html:html>
<head>

<title>Consultation Paper Listing</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/SendMail.js'></script>

<style type="text/css">
<!--
.style1 {
	color: #FFFFFF;
	font-weight: bold;
}
.style2 {font-size: 12px}
-->
</style>
</head>

<body>

<html:form action="/SendMail" method="post">


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
 						<td colspan="2">Email
 									


						</td>
					</tr>
			
					<tr align="left" valign="top">
						<td colspan="2" class="sub_head">

  <logic:notPresent name="mailerror" scope="request">  <div align="center">Email has been succesfully sent
<br><br>

  		 <a href="#" onClick="javascript:fnMsgCancel();"><img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' border="0" alt="Back to Main Page"></a> 
</div>
</logic:notPresent>
<logic:present name="mailerror" scope="request"><div align="center">Error occured in Sending mail
<br><br>
<a href="#" onClick="javascript:fnMsgCancel();"><img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' border="0" alt="Back to Main Page"></a> 		
</div>

	</logic:present>

<tr>
								<td height="35" align="left" colspan="2">
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

	<html:hidden property="id"/>
	<html:hidden property="surveyOrCons"/>
	<html:hidden property="sendOrRemain"/> 
	<html:hidden property="email"/>
	<html:hidden property="hiddenAction"/>
</html:form>
</html:html>
