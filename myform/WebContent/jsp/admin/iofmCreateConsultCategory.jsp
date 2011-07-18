<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>

	<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
	<script language="javascript">
		function fnSubmit(url,ac)
		{
			if(Trim(document.ConsultCategoryForm.categoryName.value)=="")
			{
				alert('<%= OIDBRegistry.getValues("OI.CONS.CAT.EMPTY") %>');
				document.ConsultCategoryForm.categoryName.focus();
			}
			else
			{
				//document.ConsultCategoryForm.action="iofm_mainFrameHome.htm";
				//document.ConsultCategoryForm.method="post";
				document.ConsultCategoryForm.hiddenAction.value=ac;
				document.ConsultCategoryForm.action=url;
				document.ConsultCategoryForm.submit();
			}
		}
		function fnClear()
		{
			document.ConsultCategoryForm.reset();
		}
		function fnDelete(url,ac)
		{
			if (window.confirm('<%= OIDBRegistry.getValues("OI.CONS.CAT.CONFIRM") %>')==false)
			{
				return;
			}
			else if(Trim(document.ConsultCategoryForm.categoryName.value)=="")
			{
				alert('<%= OIDBRegistry.getValues("OI.CONS.CAT.EMPTY") %>');
				document.ConsultCategoryForm.categoryName.focus();
			}
			else
			{
				document.ConsultCategoryForm.hiddenAction.value=ac;
				document.ConsultCategoryForm.action=url;
				document.ConsultCategoryForm.submit();
			}	
		}
	</script>


<html:form action="/consultCreateCategoryAction.do">

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="5">Consultation Paper</td>
 					</tr>
					
				<logic:present name="message" scope="request">
 						<logic:iterate id="mList" name="message" scope="request">
							<tr>
								<td width="75%" align="center" valign="top" class="body_detail_text" colspan="2">
									<bean:write name="mList"/>
								</td>
							</tr>
						</logic:iterate>
 				</logic:present>
					
					<tr>
						<td colspan="2" class="Sub_head">
							<logic:notPresent name="ConsultCategoryForm" property="categoryId">
								Create Category 
							</logic:notPresent>
							<logic:present name="ConsultCategoryForm" property="categoryId">
								View / Modify Consultation Category 
							</logic:present>
						</td>
					</tr>
						<tr align="left" valign="middle">
							<td width="24%" class="heading_attributes"><p>Category Name <font color="red"> * </font> </p>
							</td>
							<td width="76%" class="Body_detail_text"  valign="middle"><p>
								<html:text name="ConsultCategoryForm" property="categoryName" styleClass="Text_box" size="35" maxlength="70"></html:text></p>
 							<logic:present name="ConsultCategoryForm" property="categoryId">
								<html:hidden name="ConsultCategoryForm" property="categoryId"/>
							</logic:present>
							<html:hidden name="ConsultCategoryForm" property="hiddenAction" value="populate"/>
							</td>
						</tr>
 					<tr>
					<tr height="20"><td colspan="2"></td></tr>
						
						<td height="35" align="left" class="body_detail_text" colspan="2"> 
							<logic:present name="ConsultCategoryForm" property="categoryId">
								<a href="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultViewModifyCategoryAction.do','update')"><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt = "Save"></a> 
							</logic:present>
							<logic:notPresent name="ConsultCategoryForm" property="categoryId">
								<a href="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultCreateCategoryAction.do','save')">
									<img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt = "Save"></a> 
							</logic:notPresent>
							<logic:present name="ConsultCategoryForm" property="categoryId">
								<a href="javascript:fnDelete('<bean:message key="OIFM.contextroot" />/consultViewModifyCategoryAction.do','delete')">
									<img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" alt = "Delete"></a>
								<a href="javascript:fnClear()"><img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" alt = "Reset"></a>
							</logic:present>

							<a href='<bean:message key="OIFM.contextroot" />/consultListingAction.do?hiddenAction=populate'><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt = "Cancel"></a></p>
							
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

<logic:present name="error" scope="request">
	<script language="javascript">
		window.alert('<bean:write name="error" scope="request" />');
	</script>
</logic:present>
