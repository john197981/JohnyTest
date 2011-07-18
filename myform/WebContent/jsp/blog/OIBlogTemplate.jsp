<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.blog.OIBlogConstants" %>
<html>
	<body>
		<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
			<jsp:param name="pageName" value="Blog" />
		</jsp:include>	
		<jsp:include page="/jsp/blog/BlogTopMenu.jsp" flush="true" />
		<logic:present name="pageName" scope="request">	
			<logic:equal name="pageName" value="<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>" scope="request">
				<jsp:include page="/jsp/blog/BlogModuleHomePage.jsp" />
			</logic:equal>		
			<logic:equal name="pageName" value="<%=OIBlogConstants.ACTION_INDIVIDUAL_BLOG_ADMIN%>" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminRecentEntries.jsp" />
			</logic:equal>	
			<logic:equal name="pageName" value="BlogAdminEntries" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminEntries.jsp" />
			</logic:equal>
			<!-- added by edmund-->
			<logic:equal name="pageName" value="BlogAdminEntriesComment" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminEntriesComment.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="BlogAdminNewEntry" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminCreateEntries.jsp" />
			</logic:equal>	
			<logic:equal name="pageName" value="BlogAdminEditEntry" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminCreateEntries.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="BlogAdminCategories" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminCategory.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%=OIBlogConstants.ACTION_ABOUT_US%>" scope="request">
				<jsp:include page="/jsp/blog/BlogAboutUs.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%=OIBlogConstants.ACTION_ARCHIVES%>" scope="request">
				<jsp:include page="/jsp/blog/BlogArchives.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%=OIBlogConstants.BLOG_HOME_PAGE%>" scope="request">
				<jsp:include page="/jsp/blog/IndividualBlogHome.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%=OIBlogConstants.BLOG_ENTRY%>" scope="request">
				<jsp:include page="/jsp/blog/IndividualBlogEntry.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%=OIBlogConstants.BLOG_ABOUT_US%>" scope="request">
				<jsp:include page="/jsp/blog/IndividualBlogAboutUs.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%=OIBlogConstants.BLOG_ARCHIVES%>" scope="request">
				<jsp:include page="/jsp/blog/IndividualBlogArchives.jsp" />
			</logic:equal>
			<logic:equal name="pageName" value="<%= OIBlogConstants.BLOG_EDIT_ABOUT_ME %>" scope="request">
				<jsp:include page="/jsp/blog/BlogAdminEditAboutMe.jsp" />
			</logic:equal>				
		</logic:present>		
		<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />	
	</body>
</html>
