<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html>
<body background='<bean:message key="OIFM.docroot" />/images/page_bk.gif'>
<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
   <jsp:param name="pageName" value="Admin" />
</jsp:include>        
<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="10%" valign="top">
			<jsp:include page="/jsp/admin/iofm_AdminLeft.jsp" />
		</td>
		<td width="90%" valign="top">
			<logic:present name="pageName" scope="request">

				<logic:equal name="pageName" value="SendMail" scope="request">
					<jsp:include page="/jsp/common/iofmSendMail.jsp" />
				</logic:equal>
				
				<logic:equal name="pageName" value="message" scope="request">
					<jsp:include page="/jsp/common/iofmMessage.jsp" />
				</logic:equal>
				<logic:equal name="pageName" value="publish" scope="request">
				  	<jsp:include page="/jsp/poll/iofmPollResult.jsp" />
				</logic:equal>
				

			</logic:present>
		</td>
	</tr>
</table>
</body>
</html>