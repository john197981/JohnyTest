<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.consultation.OIConsultConstant" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="com.oifm.login.OILoginConstants" %>

<script language="javascript">
	function fnSubmit(actionName,categoryId,hiddenAction)
	{
		document.tempForm.categoryId.value=categoryId;
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

	function fnSort(sortKey,sortOrder,divCode)
	{
		//alert("fnSort:" + sortKey + "," + sortOrder + "," + divCode);
		var frm = document.ConsultListingForm;
		frm.hidSortKey.value = sortKey;
		frm.hidOrder.value = sortOrder;
		frm.divisionCode.value=divCode;
		//if(divCode=='')
			//frm.hiddenAction.value='<%= OIConsultConstant.POPULATE_CONSULTLISTING%>';
		//else
			frm.hiddenAction.value='<%= OIConsultConstant.POPULATE_PAPER_CONSULTLISTING%>';
		frm.action= '<bean:message key="OIFM.contextroot" />'+'<%= OIConsultConstant.CONSULT_LISTING_DO %>'+'?id=<%= Math.random() %>';
		frm.submit();
	}

	function submitConsultListingForm(submitUrl, actionName, nPageNo)
	{
		var frm = document.ConsultListingForm;
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.hiddenAction.value = actionName;
		frm.pageNo.value = nPageNo;
		frm.submit();
	}
	
	function submitSurveyDivisionListForm(submitUrl, actionName, nPageNo,divCode)
	{
		var frm = document.ConsultListingForm;
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.hiddenAction.value = actionName;
		frm.divisionCode.value=divCode;
		frm.pageNo.value = nPageNo;
		frm.submit();
    }


</script>
<form name="tempForm1" method="post">
	<input type="hidden" name="hiddenAction">
</form>
<form name="tempForm" method="post">
	<input type="hidden" name="categoryId">
	<input type="hidden" name="paperId">
	<input type="hidden" name="hiddenAction">
	<input type="hidden" name="Id">
	<input type="hidden" name="module">
	<input type="hidden" name="flag">
	<input type="hidden" name="hidSortKey">
    <input type="hidden" name="hidOrder">
	<input type="hidden" name="pageNo">
</form>
<form name="ConsultListingForm" method="post">
	<input type="hidden" name="hiddenAction">
	<input type="hidden" name="hidSortKey">
    <input type="hidden" name="hidOrder">
	<input type="hidden" name="divisionCode">
	<input type="hidden" name="pageNo">
	
</form>


<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
<%
	String divisionCode = "";
    if(request.getParameter("divisionCode") != null)
		divisionCode = request.getParameter("divisionCode");
    //System.out.println("divisionCode in jsp:" +request.getParameter("divisionCode") );
%>
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
							<td colspan="6" width="75%" align="center" valign="top" class="body_detail_text">
								<bean:write name="error" scope="request" />
							</td>
						</tr>
 				</logic:present>

				<logic:present name="message" scope="request">
 						<logic:iterate id="mList" name="message" scope="request">
							<tr>
								<td colspan="6" width="75%" align="center" valign="top" class="body_detail_text">
									<bean:write name="mList"/>
								</td>
							</tr>
						</logic:iterate>
 				</logic:present>

 				<tr>  
					<td colspan="6" class="sub_head">
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreateCategoryAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/new_Category.gif' border="0" alt = "Create Consultation Category"></a> 
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreatePageAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/newpapr.gif' border="0" alt = "Create Consultation Paper"></a> 
						</td>
				</tr>
				<tr align="left" valign="top" class="Table_head">
				<td width="35%" class="Table_head">Consultation Paper List
						<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="aPaperId" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('aPaperId','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="aPaperId" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('aPaperId','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('aPaperId','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a>  
					</td>
					<td width="20%" class="Table_head">Division / Author
					<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="DESCRIPTION" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('DESCRIPTION','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="DESCRIPTION" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('DESCRIPTION','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('DESCRIPTION','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a>  
					</td>
					<td width="20%" class="Table_head"><center>Period
					<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="AFROMDT" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('AFROMDT','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="AFROMDT" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('AFROMDT','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('AFROMDT','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a></center>
					</td>
					<td width="12%" class="Table_head">Target Audience
					<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="TARGETAUDIENCE" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('TARGETAUDIENCE','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="TARGETAUDIENCE" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('TARGETAUDIENCE','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('TARGETAUDIENCE','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a>
					</td>
					<td width="4%" class="Table_head">Feedbacks
					<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="aNoFeedBacks" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('aNoFeedBacks','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="aNoFeedBacks" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('aNoFeedBacks','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('aNoFeedBacks','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a>
					</td>
					<td width="4%" class="Table_head">Published Finding On
					<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="a.aPublishedOn" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('a.aPublishedOn','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="a.aPublishedOn" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('a.aPublishedOn','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('a.aPublishedOn','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a>
					</td>
					<td width="9%" class="Table_head">Mail
					<a href="#">
						<logic:notEqual name="ConsultListingForm" property="hidSortKey" value="a.aMailStatus" scope="request"> 
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('a.aMailStatus','ASC','<%=divisionCode%>');" alt="No Order">
						</logic:notEqual>
						<logic:equal name="ConsultListingForm" property="hidSortKey" value="a.aMailStatus" scope="request">
							<logic:equal name="ConsultListingForm" property="hidOrder" value="ASC" scope="request">
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('a.aMailStatus','DESC','<%=divisionCode%>');" alt="Show in Ascending Order">
							</logic:equal>
							<logic:equal name="ConsultListingForm" property="hidOrder" value="DESC" scope="request" >
								<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('a.aMailStatus','ASC','<%=divisionCode%>');" alt="Show in Descending Order">
							</logic:equal>
						</logic:equal></a>
					</td>
				</tr>
						<logic:present name="ConsultListingForm" property="arOIFormA1ConsultListing" scope="request">
								<logic:iterate id="categoryListing" name="ConsultListingForm" property="arOIFormA1ConsultListing" type="com.oifm.consultation.admin.OIFormA1ConsultListing">
									<tr align="left" valign="top">
										<td colspan="7" bgcolor="#E0EBFC" class="Heading_Topic">Consultation Category - 
											<a  class="Heading_Topic" title="Click to change the Consultation Category Name" href='#' onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultViewModifyCategoryAction.do','<bean:write name="categoryListing" property="categoryId" />','populate');" >
												<bean:write name="categoryListing" property="categoryName" /> 
											</a>
										</td>
									</tr>
									<logic:present name="categoryListing" property="arOIFormA2ConsultListing">
										<logic:iterate id="categoryListing2" name="categoryListing" property="arOIFormA2ConsultListing" type="com.oifm.consultation.admin.OIFormA2ConsultListing">
											<tr align="left" valign="top">
												<td  class="heading_thread">
													<a title="Click to view Consultation Paper Detail" class="heading_thread" href='#' onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','<bean:write name="categoryListing2" property="paperId" />','populate');">
														<bean:write name="categoryListing2" property="paperId" />&nbsp;-&nbsp;<bean:write name="categoryListing2" property="paperTitle" /> 
													</a>
												</td>
												<td  class="heading_attributes">
													<bean:write name="categoryListing2" property="division" filter="false" />
												</td>
												<td  class="heading_attributes" align="center">
													<center>
													<bean:write name="categoryListing2" property="fromDt" /><br>
													  to <br>
													<bean:write name="categoryListing2" property="toDt" />
													</center>															
												</td>
												<td  class="heading_attributes">
													<bean:write name="categoryListing2" property="targetAudiance" />
												</td>
												<td  class="heading_attributes" align="center">
													<bean:write name="categoryListing2" property="noOfFeedbacks" />		
												</td>
												<td  class="heading_attributes">
													<bean:write name="categoryListing2" property="publishedOn" />
												</td>

												<td  class="heading_attributes">
													<logic:present name="categoryListing2" property="security">
													<logic:match name="categoryListing2" property="security" value="R">
														<logic:present name="categoryListing2" property="mailStatus">
														<logic:match name="categoryListing2" property="mailStatus" value="S">
															<a title="Send email" class="special_body_link" href="#" onclick="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/SendMail.do','<bean:write name="categoryListing2" property="paperId" />','C','S','populate');" target="_self">
																Send Mail 
															</a>
														</logic:match>
														</logic:present>
														<logic:present name="categoryListing2" property="mailStatus">
														<logic:match name="categoryListing2" property="mailStatus" value="R">
															<a title="Send email" class="special_body_link" href="#" onclick="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/SendMail.do','<bean:write name="categoryListing2" property="paperId" />','C','R','populate');" target="_self">
																Send Reminder 
															</a>
														</logic:match>
														</logic:present>
													</logic:match>
													</logic:present>
													</td>
													
											</tr>
 											<tr align="left" valign="top">
												<td  class="body_detail_text" colspan="7">
												<hr style="color:#CCCCCC;height=1px;">
												</td>
											</tr>
											<tr height="6" class="body_detail_text"><td>&nbsp;</td></tr>
										</logic:iterate>
									</logic:present>
								</logic:iterate>
 						</logic:present>
					
				<tr>  
					<td colspan="7" class="sub_head">
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreateCategoryAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/new_Category.gif' border="0" alt = "Create Consultation Category"></a> 
							<a class="Special_body_link" href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultCreatePageAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/newpapr.gif' border="0" alt = "Create Consultation Paper"></a> 
					</td>
							
				</tr>
				</table>
			</td>
		</tr>
		<tr height="20" ><td colspan="7"></td></tr>

	</table>
	</td>
	</tr>
</table>
