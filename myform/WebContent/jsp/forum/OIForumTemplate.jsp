<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html>

	<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
		<jsp:param name="pageName" value="Discussion" />
	</jsp:include>

	<logic:present name="pageName" scope="request">

		<logic:equal name="pageName" value="ThreadsList" scope="request">
			<jsp:include page="/jsp/forum/iofmThreadsList.jsp" />			
		</logic:equal>

		<logic:equal name="pageName" value="ThreadPostsList" scope="request">
			<jsp:include page="/jsp/forum/OIThreadPostingsList.jsp" />			
		</logic:equal>

		<logic:equal name="pageName" value="ForumWebListing" scope="request">
			<jsp:include page="/jsp/forum/OIForumWebListing.jsp" />			
		</logic:equal>
		<logic:equal name="pageName" value="oifmThreadsSearch" scope="request">
			<jsp:include page="/jsp/forum/oifmThreadsSearch.jsp" />
		</logic:equal>	
	</logic:present>
	</body>
</html>