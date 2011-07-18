<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.blog.OIBlogConstants" %>
 
<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
   <jsp:param name="pageName" value="Admin" />
</jsp:include>

  
<html>
<body  
	onload="fnModal();"
	onFocus="fnCallModal();">
	 
<div align="center">
  <table width="902" border="0" cellpadding="0" cellspacing="0" background="<bean:message key="OIFM.docroot" />/images/Top_Fade.gif">
    <tr>
      <td>
		<div align="center"> 
        <table width="0" border="0" cellspacing="0" cellpadding="0">
          <tr valign="top">
            <td valign="top">  
				<table width="857" border="0" valign="top" cellspacing="0" cellpadding="0" bgcolor="white">
					<tr valign="top">
						<td width="15%"  valign="top" class="Blue">
							<jsp:include page="/jsp/admin/iofm_AdminLeft.jsp" />
						</td>
						<td width="1" bgcolor="white" >
							<table valign="top" border="0" cellspacing="0" cellpadding="0">
								<tr valign="top">
									<td valign="top" class="orange">&nbsp;</td>
								</tr>
								<tr valign="top">
									<td  valign="top" >
										<table width="98%" border="0" cellpadding="0" cellspacing="0">
											<tr>
												<td>
													<table width="100%" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td class="Box">
																<table width="100%" border="0" cellpadding="0"	cellspacing="0" bgcolor="red">
																	<tr bgcolor="red">
																		<td></td>
 																	</tr>
																</table>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
						<td width="85%"  valign="top" border="0">
							<logic:present name="pageName" scope="request">
								<logic:equal name="pageName" value="<%= OIBlogConstants.CREATE_BLOG_PAGE %>" scope="request">
									<jsp:include page="/jsp/blog/CreateBlog.jsp" />
								</logic:equal>
								<logic:equal name="pageName" value="<%= OIBlogConstants.MANAGE_BLOG_PAGE %>" scope="request">
									<jsp:include page="/jsp/blog/ManageBlog.jsp" />
								</logic:equal>
								<logic:equal name="pageName" value="<%= OIBlogConstants.BLOG_SETTINGS_PAGE %>" scope="request">
									<jsp:include page="/jsp/blog/BlogSetting.jsp" />
								</logic:equal>
								<!-- added by edmund -->
								<logic:equal name="pageName" value="BlogAdminEntries" scope="request">
									<jsp:include page="/jsp/blog/ManageBlogEntries.jsp" />
								</logic:equal>		
								<logic:equal name="pageName" value="BlogAdminEntriesComment" scope="request">
									<jsp:include page="/jsp/blog/ManageBlogEntriesComment.jsp" />
								</logic:equal>				
							</logic:present>												
						</td>						
					</tr>					
				</table>
			</tr>			
		</table>
		</div>
	</td>
</tr>
</table>
</div>

<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />

</body>
</html>