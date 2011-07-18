<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<table width="857" border="0" cellspacing="0" cellpadding="0" bgcolor="#6E86B6">
  	<tr height="34">
  		<logic:present name="pageName" scope="request">
			
  			<logic:present name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session">
				<td align="left" >
					<logic:match name="pageName" value="<%=OIBlogConstants.BLOG_HOME_PAGE%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/IndividualBlogHome.do?blogId=<bean:write name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session" />' class="Orange_fade" style="font-size: 14px;text-decoration:none">
						<bean:write name="SelectedBlogTitle" /> Home&nbsp;&nbsp;
						</a>
					</logic:match>
					<logic:notMatch name="pageName" value="<%=OIBlogConstants.BLOG_HOME_PAGE%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/IndividualBlogHome.do?blogId=<bean:write name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session" />'  class="Orange_fade" style="font-size: 14px;text-decoration:none"> 
						<bean:write name="SelectedBlogTitle" /> Home&nbsp;&nbsp;
						</a>
					</logic:notMatch>				
				</td>
				<td align="left" >&nbsp;
				</td>
				<td align="left" >&nbsp;
				</td>
				<td align="left" >&nbsp;
				</td>
	  		</logic:present>
	  		
	  		<logic:notPresent name="<%=OIBlogConstants.SELECTED_BLOG_ID%>" scope="session">		  		
	  			<td align="left" width="97" >
					<logic:match name="pageName" value="<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>' >
						<img src="<bean:message key="OIFM.docroot" />/images/Blog-Home1.gif" border="0" width="97" height="34"> 
						</a>
					</logic:match>
					<logic:notMatch name="pageName" value="<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>' >
						<img src="<bean:message key="OIFM.docroot" />/images/Blog-Home.gif" border="0" width="97" height="34"> 
						</a>
					</logic:notMatch>
				</td>
                <td align="left" width="97" >
					<logic:match name="pageName" value="<%=OIBlogConstants.ACTION_ABOUT_US%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.ACTION_ABOUT_US%>'><img src="<bean:message key="OIFM.docroot" />/images/About-Us1.gif" border="0" width="97" height="34"> </a>
					</logic:match>
					<logic:notMatch name="pageName" value="<%=OIBlogConstants.ACTION_ABOUT_US%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.ACTION_ABOUT_US%>'><img src="<bean:message key="OIFM.docroot" />/images/About-Us.gif" border="0" width="97" height="34"> </a>
					</logic:notMatch>
				</td>
				<td >  
					<logic:match name="pageName" value="<%=OIBlogConstants.ACTION_ARCHIVES%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/BlogArchives.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.ACTION_ARCHIVES%>'><img src="<bean:message key="OIFM.docroot" />/images/Archives1.gif" border="0" width="97" height="34"></a>
					</logic:match>
					<logic:notMatch name="pageName" value="<%=OIBlogConstants.ACTION_ARCHIVES%>" scope="request">
						<a href='<bean:message key="OIFM.contextroot" />/BlogArchives.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.ACTION_ARCHIVES%>'><img src="<bean:message key="OIFM.docroot" />/images/Archives.gif" border="0" width="97" height="34"></a>
					</logic:notMatch>
				</td>
				<td ></td>
	  		</logic:notPresent>
	  	</logic:present>  		
  	</tr>
</table>