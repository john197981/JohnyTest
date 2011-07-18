<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.blog.OIBlogConstants,com.oifm.login.OILoginConstants" %>


<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function submitForm(actionName)
{
	document.OIBlogAdminEntriesForm.hiddenAction.value="delete";
		document.OIBlogAdminEntriesForm.action=actionName;
		document.OIBlogAdminEntriesForm.submit();
}
function populateBlog(hidAction,blogId)
{	
	var frm = document.OIBlogAdminEntriesForm;
	frm.hiddenAction.value = hidAction;	
	alert("entryId : "+entryId);
	frm.entryId.value = entryId;
	frm.submit();
}

function populateEntry(actionName)
	{
		
		document.OIBlogAdminEntriesForm.hiddenAction.value="EditEntryAction";
		document.OIBlogAdminEntriesForm.action=actionName;
		document.OIBlogAdminEntriesForm.submit();
	}

</script>

<%
int i = 0;
%>

 
 
<body>  
<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td class="Orange_fade">List of Comments</td>
  </tr>
</table>
<table width="857" border="0" cellspacing="0" cellpadding="0">
<html:form action="BlogAdminEntries" method="post">
<html:hidden property="hiddenAction" />
<html:hidden property="entryId" />
<tr>
	<td align="left" valign="top" bgcolor="#f7f8fc" valign="top">
	<table width="100%" height="400px" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td class="TableHead" valign="top">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box" valign="top">
				<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
					<logic:present name="error" scope="request">
						  <tr>
							<td width="100%" class="body_detail_text" colspan="3">
							<b><bean:message name="error" scope="request"/></b>
							</td>
						  </tr>
					</logic:present>
 					<tr>
						<td colspan="3" class="Sub_head"></td>
					</tr>			       
			        <tr>
						<td colspan="3" >&nbsp;</td>
					</tr>
			        <tr>
			             <td align="center" width="70%" class="body_detail_text">          	
			          		<table width="90%" border="1" cellpadding="0" cellspacing="0" bgcolor="white">
								<tr class="SubHead">								
									<td class="Sub_Head" colspan="2">
									<a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?hiddenAction=BlogAdminEntries&id=<%= Math.random() %>')"><img src="<bean:message key="OIFM.docroot"/>/images/Entries-tab.gif" border="0" alt = "entires"></a> 
									<img src="<bean:message key="OIFM.docroot"/>/images/Comments-tab1.gif" border="0" alt = "comment">
									</td>									
			 					</tr>
			 					<logic:present name="entriesList" >
				 					<logic:iterate id="objEntry" name="entriesList" indexId="ifdx" type="com.oifm.blog.OIBABlogAdminEntries">
							        	<tr align="left">
							          		<td align="center" class="heading_attributes">
							          			
											</td>						          		
							          		<td class="Boxoutline" align="left">
												<table border="0" width="100%">
												<tr>
													<td class="body_detail_text" width="100%" align="left" colspan="2" bgcolor="#F1F9Fe"><%=++i%>. &nbsp;&nbsp;
								          			<bean:write name="objEntry" property="entryTitle" />
													</td>
												</tr>
												<logic:present name="commentList">
												<logic:iterate id="objComment" name="commentList" indexId="ifdx" type="com.oifm.blog.OIBABlogAdminComment">
													<%
														String enId = String.valueOf(objEntry.getEntryId());
													%>
													<logic:equal name="objComment" property="entryId" value="<%=enId%>" >
												<tr>
													<td align="left">&nbsp;&nbsp;
													</td>
													<td class="body_extract_text">
														<blockquote>
														<logic:match name="objComment" property="commentor"  value='<%=(String)session.getAttribute(OILoginConstants.USER_ID)%>'>
															<html:multibox property="commentArray" >
																<bean:write name="objComment" property="commentId" />
															</html:multibox>
														</logic:match>
														<logic:notMatch name="objComment" property="commentor"  value='<%=(String)session.getAttribute(OILoginConstants.USER_ID)%>'>
														<li>
														</logic:notMatch>

														<bean:write name="objComment" property="comment" />
														</blockquote>
													</td>
												</tr>
													</logic:equal>
												</logic:iterate>
												</logic:present>
												</table>
							          		</td>						          		
							        	</tr>
							        </logic:iterate>
							    </logic:present>
							    <logic:notPresent name="entriesList">							  
								    <tr align="center" >
								    <td colspan="2" class="heading_attributes">No Blogs</td>						          		
								    </tr>
							    </logic:notPresent>
			 				</table>      	
			          </td>			         
			        </tr>       
			      </table>
			      <table width="98%"  border="0" cellspacing="0" cellpadding="0" bgcolor="white">
			        <tr>
			          <td height="35" align="left">
			          <p> 
					  <logic:present name="commentList">
			   			<a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?hiddenAction=deleteComment&id=<%= Math.random() %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Delete.gif" border="0" alt = "Delete"></a>         
					 </logic:present>				   			
			          </p>		  
					  </td>
			        </tr>
			      </table>
 			</td>
		</tr>
	</table>
	</td>
	<td width="16px" class="Blue">&nbsp;</td>
    <td width="193px"  align="left" valign="top" class="Blue">
		<jsp:include page="/jsp/blog/BlogAdminRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Home" /> </jsp:include>
	<p class="Address_body">&nbsp;</p></td>
	
	</tr>
	</table>
	</td>
</tr>

</html:form>
</table>  
</body>
</HTML>

