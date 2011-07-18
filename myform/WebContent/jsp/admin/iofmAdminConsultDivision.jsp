<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIUtilities" %>

<script language="javascript">
	function fnSubmit(actionName,categoryId,divisionCode,hiddenAction)
	{
		//alert("divisionCode" + divisionCode);
		document.tempForm.categoryId.value=categoryId;
		document.tempForm.divisionCode.value=divisionCode;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmit1(actionName,paperId,hiddenAction)
	{
		document.tempForm.paperId.value=paperId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmit2(actionName,hiddenAction)
	{
		if(document.tempForm1.CategoryFlat.value!="true")
		{
			alert("Please Create Category");
			return;
		}
		document.tempForm1.hiddenAction.value=hiddenAction;
		document.tempForm1.action=actionName;
		document.tempForm1.submit();
	}
	function fnSubmit3(actionName,id,module,flag,hiddenAction)
	{
		document.tempForm.Id.value=id;
		document.tempForm.module.value=module;
		document.tempForm.flag.value=flag;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmit4(actionName,hiddenAction)
	{
		document.tempForm1.hiddenAction.value=hiddenAction;
		document.tempForm1.action=actionName;
		document.tempForm1.submit();
	}
</script>

<%
	String flag = "false";
	if(request.getAttribute("CategoryFlag")!=null){
			flag =(String) request.getAttribute("CategoryFlag");
	}

%>
<form name="tempForm1" method="post">
	<input type="hidden" name="hiddenAction">
	<input type="hidden" name="CategoryFlat" value="<%=flag%>">
</form>
<form name="tempForm" method="post">
	<input type="hidden" name="categoryId">
	<input type="hidden" name="divisionCode">
	<input type="hidden" name="paperId">
	<input type="hidden" name="hiddenAction">
	<input type="hidden" name="Id">
	<input type="hidden" name="module">
	<input type="hidden" name="flag">
</form>



<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="white">
					 

				<logic:present name="error" scope="request">
 						<tr>
							<td colspan="3" width="75%" align="center" valign="top" class="body_detail_text">
								<bean:write name="error" scope="request" />
							</td>
						</tr>
 				</logic:present>

				<logic:present name="message" scope="request">
 						<logic:iterate id="mList" name="message" scope="request">
							<tr>
								<td colspan="3" width="75%" align="center" valign="top" class="body_detail_text">
									<bean:write name="mList"/>
								</td>
							</tr>
						</logic:iterate>
 				</logic:present>

 				<tr>  
					<td colspan="3" class="sub_head">
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit4('<bean:message key="OIFM.contextroot" />/consultCreateCategoryAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/new_Category.gif' border="0" alt = "Create Consultation Category"></a> 
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreatePageAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/newpapr.gif' border="0" alt = "Create Consultation Paper"></a> 
							 <!--added by priyanka to have view all button at top-->
							<a href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultListingAction.do','populate');" ><img src='<bean:message key="OIFM.docroot" />/images/btn_ViewConsultationPapers.gif' border="0" alt = "view all"></a>
							<!--ended by priyanka-->
						</td>
				</tr>
				<tr align="left" valign="top" class="Table_head">
					
					
					<td width="20%" class="Table_head">Division</td>
					
					
				</tr>
						<logic:iterate id="objConsult" name="ConsultListingForm" indexId="ind" type="com.oifm.consultation.admin.OIBVConsultListing">		<tr align="left" valign="top">
													<td  class="heading_thread"">
														<a  class="heading_thread"  href='#' onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultListingAction.do','<%=OIUtilities.addSingleQuoteJS(objConsult.getDivision())%>','<bean:write name="objConsult" property="divisionCode" />','populatePaper');" >
														<bean:write name="objConsult" property="division" />
														</a>
													</td>
												 </tr>
									</logic:iterate>
					
								
				     <tr>  
					<td colspan="3" class="sub_head">
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreateCategoryAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/new_Category.gif' border="0" alt = "Create Consultation Category"></a> 
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreatePageAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/newpapr.gif' border="0" alt = "Create Consultation Paper"></a> 
                           <!--added by priyanka to have view all button at bottom-->
							<a href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultListingAction.do','populate');" ><img src='<bean:message key="OIFM.docroot" />/images/btn_ViewConsultationPapers.gif' border="0" alt = "view all"></a>
							<!--ended by priyanka-->

						</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr height="20" ><td colspan="3"></td></tr>

	</table>
	</td>
	</tr>
</table>
