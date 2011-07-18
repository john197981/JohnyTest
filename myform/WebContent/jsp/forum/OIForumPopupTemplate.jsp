<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<html>

	<jsp:include page="/jsp/common/iofmSimpleTop.jsp" flush="true" />	

	<logic:present name="pageName" scope="request">

		<logic:equal name="pageName" value="StatusPage" scope="request">
			<jsp:include page="/jsp/forum/OIStatusPage.jsp" />
		</logic:equal>
	
		<logic:equal name="pageName" value="TopicHrcy" scope="request">
			<jsp:include page="/jsp/forum/OICatBoardTopicHrcy.jsp" />
		</logic:equal>
	
		<logic:equal name="pageName" value="PrintThread" scope="request">
			<jsp:include page="/jsp/forum/OIPrintThread.jsp" />
		</logic:equal>

		<logic:equal name="pageName" value="Posting" scope="request">
			<jsp:include page="/jsp/forum/OIPosting.jsp" />
		</logic:equal>
	
		<logic:equal name="pageName" value="Thread" scope="request">
			<jsp:include page="/jsp/forum/OIThread.jsp" />
		</logic:equal>

		<logic:equal name="pageName" value="ModThread" scope="request">
			<jsp:include page="/jsp/forum/OIModThread.jsp" />
		</logic:equal>	

	</logic:present>
</body>
</html>
