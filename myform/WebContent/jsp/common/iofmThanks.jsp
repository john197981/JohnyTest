<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.poll.OIBVPoll" %>

<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
   <jsp:param name="pageName" value="Home" />
</jsp:include>

<html>
<head>

<title>Thanks For Polling</title>
<base target=_self>
<style type="text/css">
<!--
.style1 {
	font-size: 18px;
	font-weight: bold;
}
-->
</style>
</head>

<body>
<br>
<table width="95%" height="225"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="77%" align="left" valign="top" class="Boxoutline"><div align="center">
      <p><strong><br>
        </strong><br>
        <span class="bold">Thank you for voting</span> </p>
      <p> <a href='<bean:message key="OIFM.contextroot" />/home.do'><img src='<bean:message key="OIFM.docroot" />/images/button/btn_Back.gif' width="50" height="19" border="0" alt="Click to go back to previous screen"></a>
    </p>
      </div>
      </td>
  </tr>
</table>
</body>
</html>
