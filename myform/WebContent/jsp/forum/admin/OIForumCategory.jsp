<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript">
	function fnSubmit(url,ac)
	{
		if(Trim(document.ForumCategoryForm.categoryName.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.VALIDATE.CATEGORY") %>');
			document.ForumCategoryForm.categoryName.focus();
		}
		else
		{
			document.ForumCategoryForm.hiddenAction.value=ac;
			document.ForumCategoryForm.action=url;
			document.ForumCategoryForm.submit();
		}
	}
	function fnClear()
	{
		document.ForumCategoryForm.reset();
	}
	function fnDelete(url,ac)
	{
		if (window.confirm('<%= OIDBRegistry.getValues("OI.FORUM.DELETE.CATEGORY") %>')==false)
		{
			return;
		}
		else if(Trim(document.ForumCategoryForm.categoryName.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.VALIDATE.CATEGORY") %>');
			document.ForumCategoryForm.categoryName.focus();
		}
		else
		{
			document.ForumCategoryForm.hiddenAction.value=ac;
			document.ForumCategoryForm.action=url;
			document.ForumCategoryForm.submit();
		}
	}
</script>
<html:form action="/adminForumCreateCategoryAction.do">

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
						<td COLSPAN="2">Category / Boards / Topics Maintainance</td>
 					</tr>

						<tr>
							<td colspan="2" class="sub_head">
								<logic:notPresent name="ForumCategoryForm" property="categoryId">
									Create Category
								</logic:notPresent>
								<logic:present name="ForumCategoryForm" property="categoryId">
									View / Modify Category
								</logic:present>
							</td>
						</tr>
								<tr align="left" valign="middle">
									<td width="24%" class="heading_attributes">Category Name </td>
									<td width="76%" class="body_detail_text"  valign="middle">
										<html:text name="ForumCategoryForm" property="categoryName" styleClass="Text_box" size="50" maxlength="70"></html:text>
										<logic:present name="ForumCategoryForm" property="categoryId">
											<html:hidden name="ForumCategoryForm" property="categoryId"/>
										</logic:present>
		  
									</td>
								</tr>
					</tr>
					<tr height="50" >
						
						<td align="left" valign="middle" colspan="2">
						<html:hidden name="ForumCategoryForm" property="hiddenAction"/>
							<logic:present name="ForumCategoryForm" property="categoryId">					
								<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminForumViewModifyCategoryAction.do','update')">
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt="Save"></a>
							</logic:present>
							<logic:notPresent name="ForumCategoryForm" property="categoryId">					
								<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminForumCreateCategoryAction.do','save')">
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt="Save"></a>
							</logic:notPresent>
							<logic:present name="ForumCategoryForm" property="categoryId">
								<a href="javascript:fnDelete('<bean:message key="OIFM.contextroot" />/adminForumViewModifyCategoryAction.do','delete')">
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Delete.gif'  border="0" alt="Delete"></a>
							</logic:present>
	
							<a href='<bean:message key="OIFM.contextroot" />/adminForumListing.do?hiddenAction=populate'><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt="Cancel"></a>
							<a href="javascript:fnClear()"><img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt="Reset"></a>
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

<logic:present name="error" scope="request">
	<script language="javascript">
		window.alert('<bean:write name="error" scope="request" />');
	</script>
</logic:present>
