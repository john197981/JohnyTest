<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
  <%@ page language="java" import="com.oifm.blog.OIBlogConstants"%>

<%
try
{
%>
<jsp:include page="/jsp/blog/IndividualBlogMenu.jsp" flush="true" />	
<table width="98%"  border="0" cellspacing="0" cellpadding="0">
	  <tr>
		<td colspan="2" class="Poll_body">&nbsp;</td>
	  </tr>
	  <tr>
	  	<td colspan="2"  ><span class="yellow_text"><FONT size="2"><I> &nbsp;&nbsp;Blog Admin</I></FONT><br></span></td>
	  </tr>
	 	
		<tr class="Text">
			<td width="20%" height="25"></td>
			<logic:present name="pageName">
				<logic:match name="pageName" value="BlogAdminEntries">
					<td width="80%" class="Poll_body"><li>Entries</td>
				</logic:match>
				<logic:notMatch name="pageName" value="BlogAdminEntries">
					<td width="80%" class="Poll_body"><li><a href='<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?id=<%= Math.random() %>' class="Poll_body">Entries</a></td>
				</logic:notMatch>
			</logic:present>
			<logic:notPresent name="pageName">
				<td width="80%" class="Poll_body"><li><a href='<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?id=<%= Math.random() %>' class="Poll_body">Entries</a></td>

			</logic:notPresent>
		</tr>
		<tr align="left" valign="top" class="Text">
			<td height="25" ></td>
			<logic:present name="pageName">
				<logic:match name="pageName" value="BlogAdminNewEntry">
					<td class="Poll_body"><li>New Entry</td>
				</logic:match>
				<logic:notMatch name="pageName" value="BlogAdminNewEntry">
					<td class="Poll_body"><li>
					<a href='<bean:message key="OIFM.contextroot" />/BlogAdminNewEntry.do?id=<%= Math.random() %>' class="Poll_body">New Entry</a></td>
				</logic:notMatch>
			</logic:present>
			<logic:notPresent name="pageName">
				<td class="Poll_body"><li><a href='<bean:message key="OIFM.contextroot" />/BlogAdminNewEntry.do?id=<%= Math.random() %>' class="Poll_body">New Entry</a></td>
			</logic:notPresent>
		</tr>

		<tr align="left" valign="top" class="Text">
			<td height="25" ></td>
			<logic:present name="pageName">
				<logic:match name="pageName" value="BlogAdminCategories">
					<td class="Poll_body"><li>Categories</td>
				</logic:match>
				<logic:notMatch name="pageName" value="BlogAdminCategories">
					<td class="Poll_body"><li>
					<a href='<bean:message key="OIFM.contextroot" />/adminCategoryAction.do?id=<%= Math.random() %>&hiddenAction=BlogAdminCategories' class="Poll_body">Categories</a></td>
				</logic:notMatch>
			</logic:present>
			<logic:notPresent name="pageName">
				<td class="Poll_body"><li><a class="Poll_body" href='<bean:message key="OIFM.contextroot" />/adminCategoryAction.do?hiddenAction=BlogAdminCategories' class="Poll_body">Categories</a></td>
			</logic:notPresent>
		</tr>
		
		<tr align="left" valign="top" class="Text">
			<td height="25" ></td>
			<logic:present name="pageName">
				<logic:match name="pageName" value="EditAboutMe">
					<td class="Poll_body"><li>About Me</td>
				</logic:match>
				<logic:notMatch name="pageName" value="EditAboutMe">
					<td class="Poll_body"><li>
					<a href='<bean:message key="OIFM.contextroot" />/IndividualBlogAboutUs.do?hiddenAction=DISPLAY_AUTHOR_DETAILS' class="Poll_body">About Me</a></td>
				</logic:notMatch>
			</logic:present>
			<logic:notPresent name="pageName">
				<td class="Poll_body"><li><a href='<bean:message key="OIFM.contextroot" />/IndividualBlogAboutUs.do?hiddenAction=DISPLAY_AUTHOR_DETAILS' class="Poll_body">About Me</a></td>
			</logic:notPresent>
		</tr>

		 <tr align="left" valign="top">
			<td height="28" colspan="2">&nbsp;</td>
		</tr>
		<logic:present name="OTHER_AUTHORS" scope="request">
		<tr>
			<td height="25" colspan="2" class="Yellow_text"> Other Authors</td>
		</tr>
		<logic:iterate id="Author" name="OTHER_AUTHORS" type="java.lang.String">
		<tr align="left" valign="top" class="Text">
			<td height="13" colspan="2"><bean:write name="Author" /></td>
		</tr>
		</logic:iterate>
		</logic:present>
	</table>
<%
}
catch(Exception e)
{
	out.println(e.getMessage());
}
%>


 
