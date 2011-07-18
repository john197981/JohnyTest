<%--
/**
 * FileName			: iofmSurveyInfo.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 10 jul 2005
 * Description 		: This page used to display the list of Survey
 * Version			: 1.0
 **/
--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIBASendMail" %>
<html:html>
<head>

<title>Survey Listing</title>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">

<style type="text/css">
<!--
 .style2 {font-size: 12px}
-->
</style>
</head>

<body bgcolor="white">

<html:form action="/SendMail" method="post">
 <table width="98%" border="0" align="center" cellpadding="1" cellspacing="1" bgcolor="white" class="box">
  <tr align="left" valign="top">
    <td colspan="4" class="sub_head">Surveys assigned to   <bean:write name="SendMailForm" property="id"/>
        
    </td>
  </tr>
  <tr align="left" valign="top">
    <td colspan="4" class="body_detail_text">
     </td>
  </tr>
  <tr>
    <td class="table_head">Survey</td>
    <td width="224" class="table_head"> Description </td>
    <td width="117" class="table_head" nowrap > Closing On </td>
    <td width="137" class="table_head"> Submitted  </td>
  </tr>
  
  
  <logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
   
	  <logic:iterate id="Paperinfo" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.common.OIBASendMail" scope="request" indexId="rowNum">
		<tr align="left" valign="top">
		    <td width="147" class="body_detail_text">
			    <div align="left">
				    <bean:write name="Paperinfo" property="name"/>
			    </div>
			 </td>
		    <td class="body_detail_text"><div align="left">	<bean:write name="Paperinfo" property="description"/></div></td>
		    <td class="body_detail_text" nowrap> <bean:write name="Paperinfo" property="startDate"/> </td>
		    <td class="body_detail_text" nowrap> <bean:write name="Paperinfo" property="endDate"/> </td>
		  </tr>  
  		</logic:iterate>
	  </logic:present>		
	
    <tr align="left" valign="top">
    <td height="19" colspan="4"><br><br><div align="center"><a href="Javascript:window.close()"><img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif' border="0" alt="OK"></a></div></td>
  </tr>


</table>
 <html:hidden property="id"/>
</body>
</html:form>
</html:html>
