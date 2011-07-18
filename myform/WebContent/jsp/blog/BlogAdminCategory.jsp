<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<script language="javascript">
	
	function fnSubmit(hidAction)
	{
		if (document.OIBlogAdminCategoryForm.category.value.length > 0)
		{
			var str = trim(document.OIBlogAdminCategoryForm.category.value);
			if(str.length >0)
			{
				var frm = document.OIBlogAdminCategoryForm;
				frm.hiddenAction.value = hidAction;
				//alert("testing : "+frm.hiddenAction.value);
				frm.submit();
			}
			else
			{
				alert("Please enter a Category Name !");
			}
		}
		else
		{
			alert("Please enter a Category Name !");
		}
		
	}

	function fnModifySubmit(hidAction,catId)
	{
		var frm = document.OIBlogAdminCategoryForm;
		frm.hiddenAction.value = hidAction;
		frm.categoryId.value = catId;
		//alert("testing : "+frm.hiddenAction.value);
		frm.submit();		
	}
	
	function trim(str)
	{
	   return str.replace(/^\s*|\s*$/g,"");
	}

</script>


<body>
  
<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td class="Orange_fade">Blog Categories</td>
  </tr>
</table>

<table width="857" border="0" cellspacing="0" cellpadding="0">
<html:form action="/adminCategoryAction.do" method="post">
<html:hidden property="hiddenAction" />
<html:hidden property="categoryId" />
<html:hidden property="mode" />
<tr>
	<td align="left" valign="top" bgcolor="#f7f8fc">
 		<table width="857" height="400" border="0" cellspacing="0" cellpadding="0">
       	<tr>
    		<td width="30" class="Grey_fade">&nbsp;</td>
        	<td width="648" class="Grey_fade" valign="top">
        		<table border="0" cellspacing="0" cellpadding="0">
				<tr>
        			<td colspan="2">&nbsp;</td>
        		</tr>
				<logic:present name="error" scope="request" >
        		<tr>
        			<td colspan="2" class="body_detail_text">
						<b><bean:message name="error" scope="request"/></b>
        			</td>
        		</tr>
				<tr>
        			<td colspan="2" >&nbsp;</td>
        		</tr>
				</logic:present>
				<logic:present name="success" scope="request" >
					<tr>
						<td colspan="2" class="body_detail_text">
							<b><bean:message name="success" /></b>
						</td>
					</tr>
					<tr>
        			<td  colspan="2">&nbsp;</td>
        		</tr>
				</logic:present>
				
        		<tr>
        			<td bgcolor="#F0F8FF" class="Sub_head" colspan="2" align="left">
						<logic:equal name="OIBlogAdminCategoryForm" property="mode" value="createMode">
							Create Category
						</logic:equal>
						<logic:equal name="OIBlogAdminCategoryForm" property="mode" value="modifyMode">
							Modify Category
						</logic:equal>
					</td>
        		</tr>
				
        		<tr>
        			<td colspan="2" >&nbsp;</td>
        		</tr>
        		<tr>
        			<td width="100" class="Table_Sub_head">Category Name*  </td>
        			<td align="left" width="200" >
        				<html:text property="category" styleClass="text_box" size="30" maxlength="15"></html:text>
 					</td> 					
        		</tr>
        		<tr>
        			<td colspan="4">&nbsp;</td>
        		</tr>
        		<tr>
        			<td  >&nbsp;</td>
        			<td align="left">
        				<a href="#" class="Menu_text" onclick="javascript:fnSubmit('<bean:write name="OIBlogAdminCategoryForm" property="hiddenAction" />');"><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit" /></a>
 					</td>
        		</tr>
        		<tr>
        			<td colspan="4">&nbsp;</td>
        		</tr>
        		<tr>
        			<td class="Table_head"  colspan="4">List of Categories</td>
        		</tr>
				<tr>
        			<td colspan="4">
						<table width="100%" border="1" cellpadding="0" cellspacing="0" bgcolor="white">
								<tr class="SubHead">
									<td class="Sub_Head"  colspan="2">Click the following links to edit categories</td>
			 					</tr>
								<logic:present name="categoriesList">
									<logic:iterate id="objBA" name="categoriesList" type="com.oifm.blog.OIBABlogAdminCategory" scope="request" indexId="rowNum" >
										<tr>
											<td align="left" class="Heading_Attributes">&nbsp;&nbsp;&nbsp;
												<a href="#" class="Menu_text" onclick="javascript:fnModifySubmit('GetCategoryAction','<bean:write name="objBA" property="categoryId" />');"><bean:write name="objBA" property="category" /></a>
											</td>
										</tr>

									</logic:iterate>
								</logic:present>
								<logic:notPresent name="categoriesList">
									<tr>
										<td align="center" class="Heading_Attributes" >
											No Categories
										</td>
									</tr>			
								</logic:notPresent >
        				</table>  
					</td>
        		</tr>
        		<tr>
        			<td colspan="4">&nbsp;</td>
        		</tr>
				<tr>
        			<td colspan="4">&nbsp;</td>
        		</tr>
        		</table>
        	</td>
            <td width="16" class="Blue">&nbsp;</td>
            <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				<jsp:include page="/jsp/blog/BlogAdminRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Home" /> </jsp:include>
			</td>
        </tr>
      

        </table>
	</td>
</tr>
</html:form>
</table>

</body>
</HTML>
  

