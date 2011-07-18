<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.blog.OIBlogConstants" %>


<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function submitForm(actionName)
{
	if(actionName == '<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?hiddenAction=manageDeleteComment' && !confirm("<bean:message key="BLOG.ADMIN.MANAGE_BLOG.COMMENTS.DELETE_CONFIRMATION" />"))
	{
		return;
	}
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

Integer blogId = new Integer(-1);
	if(request.getAttribute("blogId")!=null)
	{
		blogId = (Integer)request.getAttribute("blogId");
	}

%>

</script>

<style type=text/css>
	.activetab 
		{
		font-family: Verdana;
		font-weight: normal;
		BORDER: silver 1px solid; 
		MARGIN-TOP: 1px;  
		MARGIN-LEFT: 1px; 
		WIDTH: 120px; 
		font-size: 11px;
		text-align: center;
		background-color: #ffcc66;
		}
	.inactivetab
		{
		font-family: Verdana;
		font-weight: normal;
		BORDER: silver 1px solid; 
		MARGIN-TOP: 1px;  
		MARGIN-LEFT: 1px; 
		WIDTH: 120px; 
		font-size: 11px;
		text-align: center;
		background-color: #cccccc;
		}
</style>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				
				<html:form action="BlogAdminEntries" method="post">
				<html:hidden property="hiddenAction" />
				<html:hidden property="entryId" />
				
					<table width="100%" height="400px" border="0" cellpadding="0" cellspacing="0">
					<tr>
						<td class="TableHead" valign="top">
						<table width="100%" border="0" cellspacing="0" cellpadding="0">
							<tr>
							<td class="Box" valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="white">
									<tr class="Table_head" >
										<td colspan="3">Manage Blog </td>
									</tr>
									<logic:present name="error" scope="request">
										  <tr>
											<td width="100%" class="body_detail_text" colspan="3">
											<b><bean:message name="error" scope="request"/></b>
											</td>
										  </tr>
									</logic:present>
									<tr>
										<td colspan="3" class="Sub_Head">
										<span class="inactivetab"><a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogAdmin.do?hiddenAction=ModifyBlogAction')" >Blog</a></span>&nbsp;&nbsp; 
										<span class="activetab">Entries</span>
										</td>
									</tr>			       
									<tr>
										<td colspan="3" >&nbsp;</td>
									</tr>
									<tr>
										 <td align="center" width="70%" class="body_detail_text">          	
											<table width="90%" border="1" cellpadding="0" cellspacing="0" bgcolor="white">
												<tr class="SubHead">								
													<td class="Sub_Head" colspan="2">
													<a href='<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?hiddenAction=manageBlog&id=<%= Math.random() %>' class="Menu_highlight"><img src="<bean:message key="OIFM.docroot"/>/images/Entries-tab.gif" border="0" alt = "entires"></a> 
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
																		<html:multibox property="commentArray" >
																			<bean:write name="objComment" property="commentId" />
																		</html:multibox>
																		
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
										<a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?hiddenAction=manageDeleteComment')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Delete.gif" border="0" alt = "Delete"></a> 
										</logic:present>
										&nbsp;&nbsp;
										<a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogAdmin.do?hiddenAction=ModifyBlogAction&blogId=<%=blogId%>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>		
									  </p>		  
									  </td>
									</tr>
								  </table>
							</td>
						</tr>
					</table>
					</td>
					</tr>
					</table>
				</html:form>
 			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
