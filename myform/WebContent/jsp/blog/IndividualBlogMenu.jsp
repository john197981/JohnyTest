<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<table width="98%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
	<td colspan="2" class="Poll_body">&nbsp;</td>
	</tr>
		
		<tr class="Text">
			<td width="20%" align="right"  class="Poll_body">
				<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
			</td>
			<td  align="left" width="80%" class="Poll_body">
				<a href='<bean:message key="OIFM.contextroot" />/IndividualBlogAboutUs.do' class="Highlight_body" style="text-decoration:none">About Us 
				</a>				
			</td>
		</tr>
		<tr  align="left" class="Text">
			<td width="20%" align="right"   class="Poll_body">
				<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
			</td>
			<td  align="left" width="80%" class="Poll_body">
				<a href='<bean:message key="OIFM.contextroot" />/BlogArchives.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.ACTION_ARCHIVES%>' class="Highlight_body" style="text-decoration:none">Archives</a>
			</td>
		</tr>
		<logic:present name="<%=OIBlogConstants.IS_BLOG_AUTHOR%>" scope="session">
			<logic:match name="<%=OIBlogConstants.IS_BLOG_AUTHOR%>" scope="session" value="true">
				<tr class="Text">
					<td width="20%" align="right"   class="Poll_body">
						<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
					</td>
					<td  align="left" width="80%" class="Poll_body">		
						<a href='<bean:message key="OIFM.contextroot" />/BlogAdminRecentEntries.do' class="Highlight_body" style="text-decoration:none">Blog Admin</a>
					</td>
				</tr>
			</logic:match>
	    </logic:present>
		<tr  align="left" class="Text">
			<td width="20%" align="right"   class="Poll_body">
				<img src='<bean:message key="OIFM.docroot" />/images/Bullet_round.gif' width="22" height="22">
			</td>
			<td  align="left" width="80%" class="Poll_body">
				<a href='<bean:message key="OIFM.contextroot" />/BlogHome.do?id=<%= Math.random() %>&hiddenAction=<%=OIBlogConstants.BLOG_MODULE_HOME_PAGE%>' alt="Click to go back Main Blog Home" class="Highlight_body" style="text-decoration:none">Blogs Home</a>
			</td>
		</tr>	
		
</table>