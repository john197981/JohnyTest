<%--
/**
 * FileName			: iofmPaperInfo.jsp
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
<br>
<table width="90%" height="159"  border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF">
  <tr align="left" valign="top">
    <td colspan="4" class="Titleheader">Consultation Papers allocated to  <bean:write name="SendMailForm" property="id"/>
        
    </td>
  </tr>
  <tr>
    <td class="Mainheader" nowrap>Papers</td>
    <td width="224" class="Mainheader style2" nowrap> Description </td>
    <td width="120" class="Mainheader style2" nowrap> Expiry Date </td>
    <td width="137" class="Mainheader style2" nowrap> Feedback Date</td>
  </tr>
  
  
  <logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
   
	  <logic:iterate id="Paperinfo" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.common.OIBASendMail" scope="request" indexId="rowNum">
		<tr align="left" valign="top">
		    <td width="170" class="BodyText_link" nowrap>
			    <div align="left">
					    <bean:write name="Paperinfo" property="name"/>
				 </div>
			 </td>
		    <td class="BodyText"><div align="left">	<bean:write name="Paperinfo" property="description"/></div></td>
		    <td class="BodyText" nowarp> <bean:write name="Paperinfo" property="startDate"/> </td>
		    <td class="BodyText" nowarp> <bean:write name="Paperinfo" property="endDate"/> </td>
		  </tr>  

  		</logic:iterate>
	  </logic:present>		
	
    <tr align="left" valign="top">
    <td height="19" colspan="4"><br><br><div align="center"><a href="Javascript:window.close()"><img src='<bean:message key="OIFM.docroot" />/images/button/btn_OK.gif' width="37" height="19" border="0" alt="OK"></a></div></td>
  </tr>
</table>
</body>
<html:hidden property="id"/>
</html:form>
</html:html>
