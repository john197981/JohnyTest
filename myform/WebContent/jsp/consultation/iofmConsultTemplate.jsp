<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
		<style type="text/css">
			<!--
			.style1 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			.style2 {font-size: 12px}
			.style9 {font-family: verdana}
			-->
		</style>
	</head>

			<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
				<jsp:param name="pageName" value="Consultation" />
			</jsp:include>

	<logic:present name="pageName" scope="request">
		<logic:equal name="pageName" value="ConsultListing" scope="request">
			<jsp:include page="/jsp/consultation/iofmConsultationPaperList.jsp" />
		</logic:equal>
	
		<logic:equal name="pageName" value="ConsultPastPaper" scope="request">
			<jsp:include page="/jsp/consultation/iofmConsultationPaperClosed.jsp" />
		</logic:equal>
		
		<logic:equal name="pageName" value="ConsultOpenPaper" scope="request">
			<jsp:include page="/jsp/consultation/iofmConsultationPaperOpen.jsp" />
		</logic:equal>
		
		<logic:equal name="pageName" value="ThankFeedBack" scope="request">
			<jsp:include page="/jsp/consultation/iofmConsultationThankYou.jsp" />
		</logic:equal>
	</logic:present>
</html>
