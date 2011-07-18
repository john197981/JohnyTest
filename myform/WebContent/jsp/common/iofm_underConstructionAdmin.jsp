<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<html>
<body background='<bean:message key="OIFM.docroot" />/images/page_bk.gif'>
	<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
	   <jsp:param name="pageName" value="" />
	</jsp:include>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" >
		<tr>
			<td width="15%"  valign="top">
				<jsp:include page="/jsp/admin/iofm_AdminLeft.jsp" />
			</td>
			<td width="85%"  valign="top">
				<table width="60%"  border="1" align="center" cellpadding="0" cellspacing="2" bordercolor="#6B9FE3" bgcolor="#FFFFFF">
					<tr>
		    			<td><div align="center"><img src='<bean:message key="OIFM.docroot" />/images/under-construction.gif' width="391" height="296"></div></td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</body>
</html>
