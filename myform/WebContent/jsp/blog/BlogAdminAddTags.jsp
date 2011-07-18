<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oifm.blog.OIBlogConstants" %>
<%@ page language="java" import="com.oifm.blog.OIBABlogAdminCategory"%>
<%@ page language="java" import="com.oifm.blog.OIBlogAdminCreateEntryForm"%>

<%
	OIBlogAdminCreateEntryForm entryForm = new OIBlogAdminCreateEntryForm();
	if(request.getAttribute("entryForm")!= null){
		entryForm = (OIBlogAdminCreateEntryForm)request.getAttribute("entryForm");
	}

%>

<script language="JavaScript" type="text/JavaScript">
	var docRoot = '<bean:message key="OIFM.docroot" />';
</script>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/MySay.css");
 			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->

   		</style>
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script type="text/javascript">
function noenter() {
	//if(document.OIFormBlogTag.tagName.value == "" && window.event.keyCode == 13)
		return !(window.event && window.event.keyCode == 13); 
  }
</script>
<script language="javascript">
	
	function fnSubmit(actionName)
	{
			if(document.OIFormBlogTag.tagName.value == "")
			{
				alert("Please fill in tag name");
				return;
			}
			document.OIFormBlogTag.action=actionName;
			document.OIFormBlogTag.submit();
	}

	var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
	function submitForm(actionName)
	{
		var frm = document.OIFormBlogTag;
		document.OIFormBlogTag.action=actionName;
		frm.submit();
		
		var openerForm = opener.document.forms.OIBlogAdminCreateEntryForm;
		openerForm.hiddenAction.value = '<%=OIBlogConstants.ACTION_REFRESH%>';
		openerForm.action = "<bean:message key="OIFM.contextroot"/>/BlogAdminNewEntry.do?Mode=Edit";
		openerForm.method = "post";
		openerForm.submit();	
		
		//opener.document.forms.CreateBlogForm.
		//opener.location.reload(true); 
		self.close();
	}

	function fnPagination(pageNum)
	{
		 document.OIFormBlogTag.pageNo.value = pageNum;
		 document.OIFormBlogTag.hiddenAction.value="pagination";
		 document.OIFormBlogTag.submit();
	}

	
</script>
<html>
<head>
	<title>My Forum, Ministry Of Education - Add Tag</title>
	<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
</head>
<body>
<html:form action="BlogTags" method="post">
<table>
	<tr>
		<td class="Boxoutline">
			<table width="500">
				<tr>
					<td class="Orange_fade" colspan="3">Create Tag</td>
				</tr>
				<logic:present name="error" scope="request">
				<tr>
					<td width="100%" class="body_detail_text" colspan="3">
						<b><bean:message name="error" scope="request"/></b>
					</td>
				</tr>
				</logic:present>
				<tr>
					<td colspan="3">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td colspan="3">
						<table>
							<tr>
								<td class="body_extract_text">Create new tag :</td>
								<td><html:text property="tagName" styleClass="text_box" size="50" maxlength="249" onkeypress="return noenter()" > </html:text></td>
								<td>
								<a href='#' onclick="fnSubmit('<bean:message key="OIFM.contextroot" />/BlogTags.do?hiddenAction=newTags')" ><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt="Save" /></a>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td colspan="3" class="body_extract_text bold">List of tags</td>
				</tr>
				<tr>
					<td colspan="1" class="Table_head" width="100" align="center" ><a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogTags.do?hiddenAction=selected')" ><img src='<bean:message key="OIFM.docroot"/>/images/add-tags.gif' border="0" alt = "Add" /></a></td>
					<td colspan="2" class="Table_head" width="400">Tag Name</td>
				</tr>
				<logic:present name="tagList" >
					<logic:iterate id="objTag" name="tagList" indexId="ifdx" type="com.oifm.blog.OIBABlogTag">
				<tr class="Boxoutline">
					<td align="center">
						<html:multibox property="tagArrays" >
							<bean:write name="objTag" property="tagId" />
						</html:multibox>
					</td>
					<td colspan="2" class="body_extract_text">
						<bean:write name="objTag" property="tagName" />
					</td>
				</tr>
					</logic:iterate>
				</logic:present>
				<tr>
					<td colspan="3">&nbsp;&nbsp;</td>
				</tr>
				<tr>
					<td>
						<logic:present name="currentPage" scope="request">
							<table  border="0" cellspacing="0" cellpadding="0" align="right"> 
							<tr>		
								<td align="right">								
									<table  border="0" cellpadding="3" cellspacing="0" class="BodyText" align="right">
										<tr>
											<td nowrap class="Boxinside_text"> 
												Page 
												<bean:write name="currentPage" scope="request" /> 
											of 
												<bean:write name="totalPage" scope="request" />
											</td>
										<logic:present name="previousSet" scope="request">
											<logic:equal name="previousSet" scope="request" value="true">
												<td nowrap class="BD_3">
												   <a href='javascript:fnPagination(<bean:write name="previousPage" scope="request"/>);'>&laquo;Previous</a>
												</td>
											</logic:equal>  
										</logic:present>
										<!--<td nowrap class="BD_1">1</td>-->
										<logic:present name="arPage" scope="request">
											<logic:iterate id="no" name="arPage" scope="request">
											
													<%	String currentPage=(String) request.getAttribute("pageNo");
														String temp = (String) no;
														if (! currentPage.trim().equals(temp.trim())){
													%>
															<td nowrap class="BD_2">
																<a href='javascript:fnPagination(<bean:write name="no" />);'><bean:write name="no" />
				
															</a>
															</td>
													<%}	else{%>
															<td nowrap class="BD_1">
																	<bean:write name="no" />
																	<script>nPagNum='<bean:write name="no"/>'</script>
															</td>
													<%}%>
											</logic:iterate>
										</logic:present>
										<logic:present name="nextSet" scope="request">
											<logic:equal name="nextSet" scope="request" value="true">
												<td nowrap class="BD_3"> 
													<a href='javascript:fnPagination(<bean:write name="nextPage" scope="request"/>);'>Next&raquo;</a>
												</td>
											</logic:equal>
										</logic:present>
									</td>
								</tr>
							</table>
							</td>
							<td>&nbsp;</td> 
							<td>&nbsp;</td> 		
							</tr>
						</table>		
						<input type="hidden" name="pageNo">	
					</logic:present>
					</td>
				</tr>
				<tr>
					<td colspan="3">
						<a href="#" onclick="submitForm('<bean:message key="OIFM.contextroot" />/BlogTags.do?hiddenAction=selected')" ><img src='<bean:message key="OIFM.docroot"/>/images/add-tags.gif' border="0" alt = "Add" /></a>
						<a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</html:form>
</body>
</html>

