
<!-- This Jsp populates the category list of ASM
	author@ : Rakesh			mail@ : Rakesh.Chagallu@cambridge-asia.com
	Created On 22 Jan 2008 -->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="java.util.ArrayList" %>
<script language="javascript">
function fnSubmit1(actionName,hiddenAction)
	{
		document.ASMCategoryForm.hiddenAction.value=hiddenAction;
		document.ASMCategoryForm.action=actionName;
		document.ASMCategoryForm.submit();
	}
	
function fnSubmit(url,ac)
		{	
			if(document.ASMCategoryForm.name.value=="")
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


</script>

<form name="ASMCategoryForm" method="post">
	<input type="hidden" name="hiddenAction">
	<input type="hidden" name="categoryID">

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="60%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="1">ASM Categories </td>
 					</tr>
 						<tr class="SubHead">
							<td width="100%" class="Sub_Head">Category Name </td>
						</tr>
						<logic:present name="ASMCategoryForm" property="arOIBAASMCategoryList" scope="request">
						<logic:iterate id="cL" name="ASMCategoryForm" property="arOIBAASMCategoryList" scope="request" type="com.oifm.common.OIBAASMCategoryList">
						<tr>
						<td class="heading_thread" align="left">
						<a class="heading_thread" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/asmViewModifyCategoryAction.do?categoryID=<bean:write name="cL" property="categoryId"/>','populate')"><bean:write name="cL" property="categoryList"/> </a>
						</tr></logic:iterate></logic:present> 

					<tr>
					<td colspan="4" height="10" align="left" valign="left">
					<a href="#" onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/asmCreateCategoryAction.do','populate')" ><img src='<bean:message key="OIFM.docroot"/>/images/new_Category.gif' border="0" alt = "Create ASM Category">
					</a></td></tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</form>