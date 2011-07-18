<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.blog.OIBlogConstants" %>


<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function submitForm(hidAction)
{
	var frm = document.CreateBlogForm;
	frm.hiddenAction.value = hidAction;
	
	if(hidAction=='<%=OIBlogConstants.ACTION_SAVE%>')
	{
		if(validateForm())
		{
			frm.submit();
		}
		else 
		{
			return;
		}
	}
	else
	{
		frm.submit();
	}
	
}

function submitForm1(actionName)
{
	document.CreateBlogForm.hiddenAction.value="delete";
	document.CreateBlogForm.action=actionName;
	document.CreateBlogForm.submit();	
}

function validateForm()
{
	var frm = document.CreateBlogForm;
	var flag = true;
	if(flag) 
	{
		flag = checkBlank(frm.title, "Blog Name");

	}

	if(flag) 
	{
		var total = document.CreateBlogForm.totalAuthors.value ;
		//alert("Total Authors : "+total);
		if(total==0)
		{
			alert("Please add Authors for the Blog");
			flag = false;
		}

	}
	return flag;
}

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

<html:form action="/CreateBlog.do" method="post">

<html:hidden property="hiddenAction" />
<logic:present name="AuthorsList" scope="session">									
		<bean:size id='sizeAuthors' name='AuthorsList' scope='session' />
		<input type="hidden" name="totalAuthors" value="<bean:write name='sizeAuthors'  />">
</logic:present>

 <logic:notPresent name="AuthorsList" scope="session">							  
	<input type="hidden" name="totalAuthors" value="0">
</logic:notPresent>

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<%
							String createFlag = "create";
							if(request.getAttribute("forManage")==null)
							{
						%>
							<td colspan="3">Create Blog
						<%	}else
							{
							createFlag = "manage";
						%>
							<td colspan="3">Manage Blog
						<%	}
						%>
						</td>
 					</tr>
		 			<logic:present name="error" scope="request" >
						  <tr>
							<td width="100%" class="body_detail_text" colspan="3">
							<b><bean:message name="error" scope="request"/></b>
							</td>
						  </tr>
					</logic:present>
					<%
						if(request.getAttribute("forManage")!=null)
						{
					%>
					<tr>
						<td colspan="3" class="Sub_Head"><span class="activetab">Blog</span>&nbsp;&nbsp; 
						<span class="inactivetab"><a href="#" onClick="submitForm1('<bean:message key="OIFM.contextroot" />/BlogAdminEntries.do?hiddenAction=manageBlog&id=<%= Math.random() %>')">Entries</a></span>
						</td>
					</tr>
					<%
						}
					%>
 					<tr>
						<td colspan="3" class="Sub_head"></td>
					</tr>
			        <tr>
			          <td align="left" width="15%" class="heading_attributes">Name <font color="red">*</font> </td> 
			          <td align="left" width="*" class="body_detail_text">          	
			          		<html:text property="title" styleClass="Text_box" size="30" maxlength="70" />          	
			          </td>
			          <td align="left">&nbsp;</td>
			        </tr> 
					<tr>
						<td colspan="3" >&nbsp;</td>
					</tr>
			        <tr>
			          <td align="left" valign="top" width="15%" class="heading_attributes">Authors <font color="red">*</font> </td> 
			          <td align="left" width="40%" class="body_detail_text">          	
			          		<table width="100%" border="1" cellpadding="0" cellspacing="0" bgcolor="white">
								<tr class="SubHead">
									<td class="Sub_Head"  colspan="2">Authors added</td>
			 					</tr>
			 					<logic:present name="AuthorsList" scope="session">
									
									<bean:size id='sizeAuthors' name='AuthorsList' scope='session' />
				 					<logic:iterate id="objAuthors" name="AuthorsList" scope="session" indexId="ifdx" type="com.oifm.blog.OIBABlogAuthor">
										<tr >
											<td class="heading_attributes" align="center" width="30">
											<html:multibox property="deleteArthors" >
												<bean:write name="objAuthors" property="userId" />
											</html:multibox>										
											<td class="heading_attributes" align="left">
												&nbsp;&nbsp;<bean:write name="objAuthors" property="name" />
											</td>
										</tr>										
							        </logic:iterate>
									<logic:empty name="AuthorsList" scope="session">	
										 <tr align="center" >
											<td class="heading_attributes" colspan="2">No Authors</td>					          		
										</tr>
									</logic:empty>
							    </logic:present>
							    <logic:notPresent name="AuthorsList" scope="session">							  
								    <tr align="center" >
								    <td class="heading_attributes" colspan="2">No Authors</td>						          		
								    </tr>
							    </logic:notPresent>

			 				</table>      	
			          </td>
			          <td align="left" width="15%" class="body_detail_text">
 <a class="special_body_link" href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/BlogAuthorList.do?id=<%= Math.random()%>&hiddenAction=<%=OIBlogConstants.ACTION_AUTHORS_LIST%>','mywindow','width=800px, height=600px, scrollbars,screenX=25,screenY=25','true' );">
 <img src="<bean:message key="OIFM.docroot"/>/images/btn_AddAuthors.gif" border="0" alt = " Add Authors">
</a>    
<br>
<a href="#" onclick="submitForm('DeleteBlogAuthors<%=createFlag%>')"><img src="<bean:message key="OIFM.docroot"/>/images/delete-authors.gif" border="0" alt = "Delete Blog Authors"></a> 
</a>    
			   		   </td>

			        </tr>       
			      </table>
			        <br>        
			      	<br>
			      <table width="98%"  border="0" cellspacing="0" cellpadding="0" bgcolor="white">
			        <tr>
			          <td height="35" align="left">
			          <p>          	
			   			<a href="#" onclick="submitForm('<%=OIBlogConstants.ACTION_SAVE%>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save.gif" border="0" alt = "Save"></a>          			
			   			<a href="#" onclick="submitForm('<%=OIBlogConstants.ACTION_CANCEL%>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>          		          		
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