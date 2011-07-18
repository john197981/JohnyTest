<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.blog.OIBlogConstants" %>
<head>
	<link href="<bean:message key='OIFM.docroot' />/css/iofs.css" type="text/css" rel="stylesheet"/>
	<script language="javascript" >
	var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
	function submitForm(hidAction)
	{
		var frm = document.BlogAuthorListForm;
		frm.hiddenAction.value = hidAction;
		frm.submit();
		
		var openerForm = opener.document.forms.CreateBlogForm;
		openerForm.hiddenAction.value = '<%=OIBlogConstants.ACTION_REFRESH%>';
		openerForm.action = "<bean:message key="OIFM.contextroot"/>/CreateBlog.do";
		openerForm.method = "post";
		openerForm.submit();	
		
		//opener.document.forms.CreateBlogForm.
		//opener.location.reload(true); 
		self.close();
	}

	function fnPagination(pageNum)
	{
		 document.BlogAuthorListForm.pageNo.value = pageNum;
		 document.BlogAuthorListForm.hiddenAction.value="pagination";
		 document.BlogAuthorListForm.submit();
	}
	
	function submitSearch()
	{
		document.BlogAuthorListForm.pageNo.value = '1';
		document.BlogAuthorListForm.hiddenAction.value = 'AuthorsListAction';
		document.BlogAuthorListForm.submit();
	}
	
	</script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

</head>
<div align="center">
<br>
<br>
<html:form action="/BlogAuthorList.do" method="post">
<html:hidden property="hiddenAction" />
<html:hidden property="lastSearchString" />
		<table width="80%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td class="heading_attributes">Search</td>
				<td class="heading_attributes"><html:text property="searchString"></html:text> <a href="#" onclick="submitSearch();"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Search.gif" border="0" alt="Search" /></a></td>
			</tr>
		</table>
		<br />
		<table width="80%" border="1" cellpadding="0" cellspacing="0" bgcolor="white">
					<tr class="SubHead">
						<td width="15%"  class="Sub_Head" >&nbsp;</td>
						<td width="70%" class="Sub_Head" >Author Names</td>						
 					</tr>
 					<logic:present name="AllUsers" scope="session">
 						<logic:iterate id="objUser" name="AllUsers" scope="session" indexId="ifdx" type="com.oifm.blog.OIBABlogAuthor">
		 					<tr>
		 						<td align="center" width="7%"  class="heading_attributes" > 	
									<logic:equal name="objUser" property="addedToEntry" value="true">
										<html:multibox property="aryUsers" disabled="true">
											<bean:write name="objUser" property="userId" />
										</html:multibox>
									</logic:equal>
									<logic:notEqual name="objUser" property="addedToEntry" value="true">
										<html:multibox property="aryUsers" >
											<bean:write name="objUser" property="userId" />
										</html:multibox>
									</logic:notEqual>
		 						</td>
								<td align="left" width="70%" class="heading_attributes" ><bean:write name="objUser" property="name" /></td>								
		 					</tr> 
	 					</logic:iterate> 
	 				</logic:present> 					
 				</table>
				 <br>
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
 				 <br>        
			     <br>
			      <table width="80%"  border="0" cellspacing="0" cellpadding="0" >
			        <tr>
			          <td height="35" align="left">
			          <p>          	
			   			<a href="#" onclick="submitForm('SelectUsersAction')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Select-Users.gif" border="0" alt = "Select Users"></a>          			
			   			<a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>          		          		
			          </p>		  
					  </td>
			        </tr>
			      </table>   

</html:form> 				  
</div>