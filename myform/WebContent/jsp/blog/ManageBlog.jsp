<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>


<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'


function submitForm(hidAction)
{
	if(!confirm("<bean:message key="BLOG.ADMIN.MANAGE_BLOG.DELETE_CONFIRMATION" />"))
	{
		return;
	}
	var frm = document.BlogAdminForm;
	frm.hiddenAction.value = hidAction;
	frm.submit();
}

function populateBlog(hidAction,blogId)
{	
	var frm = document.BlogAdminForm;
	frm.hiddenAction.value = hidAction;	
	//alert("blogId : "+blogId);
	frm.blogId.value = blogId;
	frm.submit();
}

</script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/BlogAdmin.do" method="post">

<html:hidden property="hiddenAction" />
<html:hidden property="blogId" />

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
						<td colspan="3">Manage Blog</td>
 					</tr>
		 			<logic:present name="error" scope="request" >
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
									<td class="Sub_Head" colspan="5" width="5%">Blogs</td>									
			 					</tr>
			 					<logic:present name="BlogsList" >
				 					<logic:iterate id="objBlog" name="BlogsList" indexId="ifdx" type="com.oifm.blog.OIBABlog">
							        	<tr align="left">
							          		<td align="center" class="heading_attributes" width="5%">
							          			<html:multibox property="aryBlogs" >
								          			<bean:write name="objBlog" property="blogId" />
							          			</html:multibox>
											</td>						          		
							          		<td class="heading_attributes" width="55%">&nbsp;&nbsp;
								          		<a href="#" onclick="populateBlog('ModifyBlogAction','<bean:write name="objBlog" property="blogId" />')">
								          			<bean:write name="objBlog" property="title" />
							          			</a>
							          		</td>						          		
							          		<td align="center" class="heading_attributes" width="13%">Authors(<bean:write name="objBlog" property="totalAuthors" />)</td>						          		
							          		<td align="center" class="heading_attributes" width="13%">Entries(<bean:write name="objBlog" property="totalEntries" />)</td>						          		
							          		<td align="center" class="heading_attributes" width="14%">Comments(<bean:write name="objBlog" property="totalComments" />)</td>						          		
							        	</tr>
							        </logic:iterate>
							    </logic:present>
							    <logic:notPresent name="BlogsList">							  
								    <tr align="center" >
								    <td colspan="5" class="heading_attributes">No Blogs</td>						          		
								    </tr>
							    </logic:notPresent>
			 				</table>      	
			          </td>			         
			        </tr>       
			      </table>
			        <br>        
			      	<br>
			      <table width="98%"  border="0" cellspacing="0" cellpadding="0" bgcolor="white">
			        <tr>
			          <td height="35" align="left">
			          <p>          	
			   			<a href="#" onclick="submitForm('DeleteAction')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Delete.gif" border="0" alt = "Delete"></a>          						   			
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