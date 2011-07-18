<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/simpleTxtFormating.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript">
	function fnSubmit(actionName,ac)
	{
		//document.ConsultPublishForm.summary.value=Trim(document.ConsultPublishForm.summary.value);
		if(Trim(document.ConsultPublishForm.summary.value)=="")
		{
			alert('Please enter the Summary');
			document.ConsultPublishForm.summary.focus();
			return;
		}

		document.ConsultPublishForm.hiddenAction.value=ac;
		document.ConsultPublishForm.action=actionName;
		//document.loginForm.method="post";
		document.ConsultPublishForm.submit();
	}
	function fnClear() 
	{
		document.ConsultPublishForm.reset();
		fn_textCounter(this.ConsultPublishForm.summary,this.ConsultPublishForm.numleft);
	}
	var maxlimit = 4000;
	function fn_textCounter(field, countfield) 
	{
		if (field.value.length > maxlimit) 
		{
			field.value = field.value.substring(0, maxlimit);
			cmdObj=document.getElementById("exceed");
			cmdObj.value="You have exceeded the maximum allowed characters in this field";
		}
		else
		{
			countfield.value = maxlimit - field.value.length;
			cmdObj=document.getElementById("exceed");
			cmdObj.value="";
		}
	}

	function fnSubmitPage(actionName,hiddenAction,paperId,pageNo)
	{
		document.tempForm.paperId.value=paperId;
		document.tempForm.pageNo.value=pageNo;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function openNewWindow()
	{
		document.ConsultPublishForm.previewDescription.value = document.ConsultPublishForm.summary.value;
		window.open('<bean:message key="OIFM.docroot" />/html/preview.html','mywindow','left=20,top=20,width=400,height=250,toolbar=0,resizable=0');
	}
</script>

<logic:present name="error" scope="request">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable">
		<tr>
    		<td width="85%" align="center" valign="top" class="Mainheader">
				<bean:write name="error" scope="request" />
			</td>
		</tr>
	</table>    
</logic:present>

<logic:present name="message" scope="request">
	<table width="100%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable">
		<logic:iterate id="mList" name="message" scope="request">
			<tr>
    			<td width="85%" align="center" valign="top" class="Mainheader">
					<bean:write name="mList"/>
				</td>
			</tr>
		</logic:iterate>
	</table>    
</logic:present>
<html:form action="/consultPaperPublishAction.do" enctype="multipart/form-data">

	 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

	<table width="98%" border="0" cellpadding="0"
		cellspacing="0"  bgcolor="white">
		<tr>
			<td class="Box"> 
					<table width="100%" border="0" cellpadding="1"
						cellspacing="1"  bgcolor="white">
  				<tr align="left" valign="top" class="Table_head">
					<td colspan="2">Publish Consultation Results</td>
				</tr>
	  							<tr>
									<td width="130" align="left" valign="top" class="heading_thread">Paper Title</td>
									<td width="490" class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="title"/>
										<html:hidden name="ConsultPublishForm" property="paperId"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Target Audience </td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="targetAudiance"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Contact Person </td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="contactPerson"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Telephone Number </td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="phone"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Email address </td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="emailAddress"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Consultation Period </td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="fromDt"/>
										to 
										<bean:write name="ConsultPublishForm" property="toDt"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Status</td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="active"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Description</td>
									<td class="body_detail_text">
										<bean:write name="ConsultPublishForm" property="description" filter="false" />
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Attached Paper </td>
									<td class="body_detail_text">
										<logic:present name="ConsultPublishForm" property="attachedFile">
											<a href='<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?attachedFileName=<bean:write name="ConsultPublishForm" property="attachedFile"/>&paperId=<bean:write name="ConsultPublishForm" property="paperId"/>&hiddenAction=downLoad' />
												Consultation Paper Attachment
											</a>
										</logic:present>
										<html:hidden name="ConsultPublishForm" property="attachedFile"/>
									</td>
								</tr>
								<tr>
									<td align="left" valign="top" class="heading_thread">Summary<font color="red"> * </font></td>
									<td class="body_detail_text">
										<div id="toolbar" style="width: 565 px;height:15px">
											<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);" onclick="format_sel('b',document.ConsultPublishForm.summary);"  src='<bean:message key="OIFM.docroot" />/images/bold.gif'  align="middle" alt="click to make your selection bold">
											<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="format_sel('i',document.ConsultPublishForm.summary);"  src='<bean:message key="OIFM.docroot" />/images/italic.gif'  align="middle"  alt="click to make your selection italic">
											<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="format_sel('u',document.ConsultPublishForm.summary);"  src='<bean:message key="OIFM.docroot" />/images/underline.gif'  align="middle"  alt="click to make your selection underline">
											<img class="button"  onmouseover="mouseover(this);"  onmouseout="mouseout(this);"  onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="insert_link(document.ConsultPublishForm.summary);"  src='<bean:message key="OIFM.docroot" />/images/hyperlink.gif'  align="middle"  alt="click to add a link">
										</div>
										<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="false">
											<INPUT TYPE="hidden" name="previewDescription">
											<html:textarea name="ConsultPublishForm" property="summary" cols="70" rows="10" onkeydown="fn_textCounter(this.form.summary,this.form.numleft);" onkeyup="fn_textCounter(this.form.summary,this.form.numleft);" onmouseover="fn_textCounter(this.form.summary,this.form.numleft);" onmouseout="fn_textCounter(this.form.summary,this.form.numleft);">
											</html:textarea>
											<a class="special_body_link" href="#" onClick="javascript:openNewWindow();">Preview open window</a>
										</logic:equal>
										<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="true">
											<html:textarea name="ConsultPublishForm" property="summary" cols="70" rows="10" onkeydown="fn_textCounter(this.form.summary,this.form.numleft);" onkeyup="fn_textCounter(this.form.summary,this.form.numleft);" onmouseover="fn_textCounter(this.form.summary,this.form.numleft);" onmouseout="fn_textCounter(this.form.summary,this.form.numleft);" readonly="true">
											</html:textarea>
										</logic:equal>
										<br>
										<div align="left">
											<font color="#005BCC">
												No. of characters remaining: 
												<input name="numleft" type="text" size="10" size="5" maxlength="3" value="8000" disabled class="text_box">
												<input name="exceed" id="exceed" type="hidden" size="60" value="" style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 0px solid #7F9DB9;border-right: 0px solid #7F9DB9;border-left: 0px solid #7F9DB9;border-top:0px solid #7F9DB9; color:red;text-decoration:none">
											</font>
										</div>
									</td>
								</tr>
								<tr>
									<td height="21" class="heading_thread">Acknowledge Users </td>
									<td class="body_detail_text">
										<bean:define id="ar" name="ConsultPublishForm" property="arPublishStatus" /> 
										<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="false">
											<html:select property="publishStatus" styleClass="Text" >
												<html:options collection="ar" property="value" labelProperty="label"  />
											</html:select>
										</logic:equal>
										<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="true">
											<html:select property="publishStatus" styleClass="Text" disabled="true">
												<html:options collection="ar" property="value" labelProperty="label"  />
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<logic:notPresent name="ConsultPublishForm" property="summaryFile" >
									<tr>
										<td height="21" class="heading_thread">Attach Summary</td>
										<td colspan="2" class="heading_thread">
											<html:file  property="summaryFileName" styleClass="Text" ></html:file>
											(Max 2 MB) 
										</td>
									</tr>
								</logic:notPresent>
								<logic:present name="ConsultPublishForm" property="summaryFile" >
									<tr>
										<td height="21" class="heading_thread">Attach Summary</td>
										<td align="left" valign="middle" class="body_detail_text">
											<div align="left">
												<a href='<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?summaryFile=<bean:write name="ConsultPublishForm" property="summaryFile"/>&paperId=<bean:write name="ConsultPublishForm" property="paperId"/>&hiddenAction=downLoad'>
													Consultation Paper Summary
												</a>
												<html:hidden name="ConsultPublishForm" property="summaryFile"/>
												&nbsp;
												<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="false">
													<a href='<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?summaryFile=<bean:write name="ConsultPublishForm" property="summaryFile"/>&paperId=<bean:write name="ConsultPublishForm" property="paperId"/>&hiddenAction=removePublishAttachment'>
														<img src='<bean:message key="OIFM.docroot" />/images/remove.gif' alt="Remove Attachments" border="0" alt = "Remove">
													</a>
												</logic:equal>
												<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="true">
												</logic:equal>
											</div>
										</td>
									</tr>
								</logic:present>
								<tr>
									<td colspan="2" class="body_detail_text">&nbsp;</td>
								</tr>
								<tr>
									<td colspan="2" class="body_detail_text">
										<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="false">
											<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do','update');">
												<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif'  border="0" alt = "Save"></a> 
										</logic:equal>
										<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultListingAction.do','populate');">
											<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif'  border="0" alt = "Cancel"></a> 
										<logic:equal name="ConsultPublishForm" property="readOnlyFlag" value="false">
											<a href="#" onclick="javascript:fnClear()">
												<img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>
										</logic:equal>
										<a href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?paperId=<bean:write name="ConsultPublishForm" property="paperId"/>&hiddenAction=export'); ">
											<img src='<bean:message key="OIFM.docroot" />/images/btn_Export_consultation_pap.gif'  border="0" alt = "Export Consultation Paper"></a>
									</td>
									<html:hidden name="ConsultPublishForm" property="readOnlyFlag"/>
									<html:hidden name="ConsultPublishForm" property="hiddenAction"/>

 					</tr>
				</table>
			</td>
		</tr>
	</table> 
<br>
		<table width="98%"  border="0" cellspacing="1" cellpadding="1" >
		<tr>
			<td align="center" valign="top" class="box">
				<logic:notEmpty name="ConsultPublishForm" property="arOIFormConsultPublishHelper" scope="request">
				<logic:present name="ConsultPublishForm" property="arOIFormConsultPublishHelper" scope="request">
					<table width="100%"  border="0" cellspacing="1" cellpadding="1" >
						<tr>
							<td width="151" class="Table_head">Name</td>
							<td width="103" class="Table_head">School</td>
							<td width="103" class="Table_head">Division</td>
							<td width="91" class="Table_head">Date</td>
							<!--<td width="581" class="Mainheader">Feedback</td>-->
						</tr>
						<logic:present name="ConsultPublishForm" property="arOIFormConsultPublishHelper" scope="request">
							<logic:iterate id="feedBackListing" name="ConsultPublishForm" property="arOIFormConsultPublishHelper" type="com.oifm.consultation.admin.OIFormConsultPublishHelper">
								<tr>
									<td height="25" class="body_detail_text" valign="top">
										<a class="heading_thread" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="feedBackListing" property="nric" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );" >
											<bean:write name="feedBackListing" property="name" />
										</a>
									</td>
									<td class="heading_attributes" valign="top">
										<bean:write name="feedBackListing" property="school" />
									</td>
									<td class="heading_attributes" valign="top">
										<bean:write name="feedBackListing" property="division" />
									</td>
									<td class="heading_attributes" valign="top">
										<bean:write name="feedBackListing" property="feedBackDate" />
									</td>
								</tr>
								<tr>
									<td class="body_detail_text" colspan="4" valign="top">
										<!--<bean:write name="feedBackListing" property="feedBack" filter="false" />-->
										<html:textarea  name="feedBackListing" property="feedBack" styleClass="text_aria" cols="80" rows="5"  readonly="true"></html:textarea>
									</td>
								</tr>
								<tr>
									<td height="5" colspan="4" class="body_detail_text">&nbsp;</td>
								</tr>
							</logic:iterate>
						</logic:present>
					</table>
					<br>
					<br>
					<table border="0" cellspacing="0" cellpadding="0" align="right">
						<tr>
							<td>
								<table border="0" cellpadding="2" cellspacing="0" class="BodyText">
									<tr>
										<td nowrap class="Boxinside_text"> 
											Page 
												<bean:write name="pageNo" scope="request" /> 
											of 
												<bean:write name="totalPage" scope="request" />
										</td>
										<logic:present name="previousSet" scope="request">
											<logic:equal name="previousSet" scope="request" value="true">
												<td nowrap class="BD_2">&lt;</td>
												<td nowrap class="BD_3"> 
													<a href='#' onclick="javascript:fnSubmitPage('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do','populate',<bean:write name="ConsultPublishForm" property="paperId"/>,<bean:write name="previousPage" scope="request"/>);">
														&laquo;Previous</a>
												</td>
											</logic:equal>
										</logic:present>
										<!--<td nowrap class="BD_1">1</td>-->
										<logic:present name="arPage" scope="request">
											<logic:iterate id="no" name="arPage" scope="request">
												<%
													String currentPage=(String) request.getAttribute("pageNo");
													String temp = (String) no;
													if (! currentPage.trim().equals(temp.trim()))
													{
												%>
												<td nowrap class="BD_2">
													<a href='#' onclick="javascript:fnSubmitPage('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do','populate',<bean:write name="ConsultPublishForm" property="paperId"/>,<bean:write name="no" />);">		
														<bean:write name="no" /></a>
												</td>
												<%
													}
													else
													{
												%>
												<td nowrap class="BD_1">
														<bean:write name="no" />
												</td>
												<%
													}
												%>
											</logic:iterate>
										</logic:present>
										<logic:present name="nextSet" scope="request">
											<logic:equal name="nextSet" scope="request" value="true">
												<td nowrap class="BD_2">&gt;</td>
												<td nowrap class="BD_3"> 
													<a href='#' onclick="javascript:fnSubmitPage('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do','populate',<bean:write name="ConsultPublishForm" property="paperId"/>,<bean:write name="nextPage" scope="request"/>);">
														Next&raquo;</a>
												</td>
											</logic:equal>
										</logic:present>
	                  				</tr>
								</table>
							</td>
							<td width="10">&nbsp;</td>
						</tr>
					</table>
				</logic:present>
				</logic:notEmpty>
				<br>
				<br>
			</td>
		</tr>
	</table>
	<br>
	<script language="javascript">
		fn_textCounter(this.ConsultPublishForm.summary,this.ConsultPublishForm.numleft);
	</script>
</html:form>
<form name="tempForm" method="post">
	<input type="hidden" name="categoryId">
	<input type="hidden" name="pageNo">
	<input type="hidden" name="paperId">
	<input type="hidden" name="hiddenAction">
</form>
</body>
</html>