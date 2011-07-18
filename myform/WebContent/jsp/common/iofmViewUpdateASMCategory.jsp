
<!-- This JSP is used to create the ASM Categories.
author @: Rakesh			mail@: Rakesh.Chagallu@cambridge-asia.com
Created on : 22 Jan 2008
 -->


<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.utility.OIDBRegistry"%>

<script language="javascript"
	src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript">
		
		function fnSubmit(url,ac)
		{
			if(Trim(document.ASMCategoryForm.name.value)=="")
			{
				alert('<%= OIDBRegistry.getValues("OI.CONS.CAT.EMPTY") %>');
				document.ASMCategoryForm.name.focus();
			}
			else
			{
				document.ASMCategoryForm.hiddenAction.value=ac;
				document.ASMCategoryForm.action=url;
				document.ASMCategoryForm.submit();
			}
		}
		function fnDelete(url,ac)
		{
			if (window.confirm('<%= OIDBRegistry.getValues("OI.CONS.CAT.CONFIRM") %>')==false)
			{
				return;
			} 
			else
			{
				document.ASMCategoryForm.hiddenAction.value=ac;
				document.ASMCategoryForm.action=url;
				document.ASMCategoryForm.submit();
			}	
		}
</script>

 <html:form action="/asmViewModifyCategoryAction">

	<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

	<table width="98%" border="0" cellpadding="0" cellspacing="0">

		<tr>
			<td class="TableHead">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class="Box">
					<table width="100%" border="0" cellpadding="1" cellspacing="1"
						bgcolor="white">
						<tr class="Table_head">
							<td colspan="5">ASM Categories</td>
						</tr>

						<tr>
							<td colspan="2" class="Sub_head">
							View/Modify ASM Category
						</tr>
						<tr align="left" valign="middle">
							<td width="24%" class="heading_attributes">
							<p>Category Name <font color="red"> * </font></p>
							</td>
							<td width="76%" class="Body_detail_text" valign="middle">
							<p><html:text name="ASMCategoryForm"
								property="name" styleClass="Text_box" size="60"
								maxlength="30"></html:text></p>
							<logic:present name="ASMCategoryForm" property="categoryID">
								<html:hidden name="ASMCategoryForm" property="categoryID" />
							</logic:present> <html:hidden name="ASMCategoryForm" property="hiddenAction"
								value="populate" /></td>
						</tr>
						<tr align="left" valign="top">
							<td width="24%" class="heading_attributes">
							<p>Description </p>
							</td>
							<td width="76%" class="Body_detail_text" valign="middle">
							<html:textarea  property="description" 
	                    				styleClass="Text" 
	                    				 cols="50" rows="5"
	                    				tabindex="3" 
	                    				>
	                  		</html:textarea><font class="heading_attributes"> &nbsp (Max 500 Characters)</font>
	                  		<logic:present name="ASMCategoryForm" property="categoryID">
								<html:hidden name="ASMCategoryForm" property="categoryID" />
							</logic:present> 
							<html:hidden name="ASMCategoryForm" property="hiddenAction" value="populate" /></td>
						</tr>
						<tr>
						<tr height="20"><td colspan="2"></td></tr>
						<td height="35" align="left" class="body_detail_text" colspan="2">
						<a href='<bean:message key="OIFM.contextroot" />/asmCategoryAction.do?hiddenAction=populate'><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif'
							border="0" alt = "Cancel"></a>
						
						<a href="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/asmViewModifyCategoryAction.do?hiddenAction=update')"><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt = "Save"></a>
						<a href="javascript:fnDelete('<bean:message key="OIFM.contextroot" />/asmViewModifyCategoryAction.do?hiddenAction=delete')"><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" alt = "Delete"></a>
						
						</p>

						</td>
						</tr>
					</table>
					</html:form></td>
				</tr>
			</table>
			</td>
		</tr>
	</table>

	<logic:present name="error" scope="request">
		<script language="javascript">
	/*	window.alert('<bean:write name="error" scope="request" />');*/
	</script>
	</logic:present>