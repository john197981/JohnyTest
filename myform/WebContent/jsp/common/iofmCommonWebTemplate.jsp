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
 <!-- This logic:notpresent is for to avoid top.jsp to be included in Forum Alert a Friend -->	
 <logic:notPresent name="Forum" scope="request">
	<logic:notEqual name="pageName" value="AlertFriend" scope="request">
	<logic:notEqual name="pageName" value="ThankFeedBack" scope="request">
		<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
			<jsp:param name="pageName" value="Consultation" />
		</jsp:include>
	 </logic:notEqual>	
	 </logic:notEqual>	
 </logic:notPresent>	

	<logic:present name="pageName" scope="request">
		<logic:equal name="pageName" value="AlertFriend" scope="request">
			<jsp:include page="/jsp/common/iofmAlertFriend.jsp" />
		</logic:equal>

		<logic:equal name="pageName" value="ThankFeedBack" scope="request">
			<jsp:include page="/jsp/consultation/iofmConsultationThankYou.jsp" />
		</logic:equal>
		
		<logic:equal name="pageName" value="SearchPage" scope="request">
			<jsp:include page="/jsp/search/iofmSimpleSearch.jsp" />
		</logic:equal>
	</logic:present>
</html>
