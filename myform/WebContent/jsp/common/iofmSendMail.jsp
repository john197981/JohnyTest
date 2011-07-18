<%--
/**
 * FileName			: iofmSendMail.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 10 jul 2005
 * Description 		: This page used to display the list of Send Mail Users  
 * Version			: 1.0
 **/
--%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIBASendMail" %>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
	<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/simpleTxtFormating.js'></script>
	<script>
		var strDocRoot ='<bean:message key="OIFM.contextroot" />';
		window.name="sendmail"
		var xWin;
		function openNewWindow()
		{
			document.SendMailForm.previewDescription.value = document.SendMailForm.message.value;
			window.open('<bean:message key="OIFM.docroot" />/html/preview.html','mywindow','left=20,top=20,width=400,height=250,toolbar=0,resizable=0');
		}
	</script>	    
	<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/SendMail.js'></script>
	 
	<html:form action="/SendMail" method="post">
	
	<html:hidden property="hidSortKey"/>
	<html:hidden property="hidOrder"/>
	
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
 						<td colspan="2">
 									<logic:equal name="SendMailForm" property="surveyOrCons" value="C" scope="request">
										Consultation Paper
									</logic:equal >
									<logic:equal name="SendMailForm" property="surveyOrCons" value="S" scope="request">
										Survey
									</logic:equal >
						</td>
					</tr>
			
					<tr align="left" valign="top">
						<td colspan="2" class="sub_head">
							<logic:equal name="SendMailForm" property="surveyOrCons" value="C" scope="request">
								<logic:equal name="SendMailForm" property="sendOrRemain" value="S" scope="request">
									Send Invitation for Consultation Paper - &nbsp;<bean:write name="SendMailForm" property="caption"/>  
								</logic:equal>
								<logic:equal name="SendMailForm" property="sendOrRemain" value="R" scope="request">
									Send Reminder for Consultation Paper - &nbsp;<bean:write name="SendMailForm" property="caption"/> 
								</logic:equal>
							</logic:equal>
							<logic:equal name="SendMailForm" property="surveyOrCons" value="S" scope="request">
								<logic:equal name="SendMailForm" property="sendOrRemain" value="S" scope="request">
									Send Invitation for Survey - &nbsp;<bean:write name="SendMailForm" property="caption"/> 
								</logic:equal>
								<logic:equal name="SendMailForm" property="sendOrRemain" value="R" scope="request">
									Send Reminder for Survey - &nbsp;<bean:write name="SendMailForm" property="caption"/> 
								</logic:equal>	 
							</logic:equal>
						</td>
					</tr>
									<tr align="left" valign="top">
										<td width="4%" class="heading_thread">Subject</td>
										<td width="96%" class="body_detail_text">
										<input type="text" name="subject" value ='<bean:write name="SendMailForm" property="subject"/>' class="Text_box" size="102" tabindex="1" maxlength="300">
									</tr>
									<tr>
										<td valign="top" class="heading_thread">Message</td>
										<td colspan="4" class="body_detail_text">
											<div id="toolbar" style="width: 520 px">
												<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);" onclick="format_sel('b',document.SendMailForm.message);"  src='<bean:message key="OIFM.docroot" />/images/bold.gif'  align="middle" alt="click to make your selection bold">
												<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="format_sel('i',document.SendMailForm.message);"  src='<bean:message key="OIFM.docroot" />/images/italic.gif'  align="middle"  alt="click to make your selection italic">
												<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="format_sel('u',document.SendMailForm.message);"  src='<bean:message key="OIFM.docroot" />/images/underline.gif'  align="middle"  alt="click to make your selection underline">
												<img class="button"  onmouseover="mouseover(this);"  onmouseout="mouseout(this);"  onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="insert_link(document.SendMailForm.message);"  src='<bean:message key="OIFM.docroot" />/images/hyperlink.gif'  align="middle"  alt="click to add a link">
											</div>
											<INPUT TYPE="hidden" name="previewDescription">
											<html:textarea  property="message" styleClass="Text_aria" cols="120" rows="6" onkeydown="fn_SndMailtextCounter(this.form.message,this.form.numleft);" onkeyup="fn_SndMailtextCounter(this.form.message,this.form.numleft);"></html:textarea>
											<br>
											<a class="special_body_link" href="#" onClick="javascript:openNewWindow();">Preview open window</a>
											<br>
											<div align="left">
												<font color="#005BCC">
													No. of characters remaining: 
													<input name="numleft" type="text" size="10" size="5" maxlength="3" value="500" disabled class="text_box">
												</font>
											</div>
										</td>
									</tr>
									<tr align="left" valign="top">
										<td class="body_detail_text">&nbsp;</td>
										<td class="body_detail_text">&nbsp;</td>
									</tr>
 						<tr>
							<td height="35" align="left" colspan="2">
 								<a href="#" onClick="javascript:fnSendMail();"><img src='<bean:message key="OIFM.docroot" />/images/btn_send_mail.gif' border="0" alt="Send Mail"></a> 
								<a href="#" onClick="javascript:fnClear();"><img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt="Clear Values"></a>
								<a href="#" onClick="javascript:fnCancel();"><img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt="Cancel"></a> 
								<a href="javascript:;" onClick="javascript:fnSelectUsers();" ><img src='<bean:message key="OIFM.docroot" />/images/btn_Select-Users.gif' border="0" alt="Select Users"></a> 
								<a href="javascript:;" onClick="javascript:fnSelectGroups();"><img src='<bean:message key="OIFM.docroot" />/images/but_select_groups.gif' border="0" alt="Select Group"></a> 
 							</td>
						</tr>
						<tr align="left" valign="top">
							<td class="body_detail_text">&nbsp;</td>
							<td class="body_detail_text">&nbsp;</td>
						</tr>
						<logic:present name="Remove" scope="request">
							<tr>
								<td align="left">
									<table width="90%"  border="0" cellpadding="1" cellspacing="1" class="Box" align="center">		
										<tr align="center">
											<td class="Text">
												<font color="red"><%=request.getAttribute("Remove")%> <bean:message key="SndMail.Remove"/></font>
											</td>
										</tr>
									</table>			
								</td>
							</tr>
						</logic:present>	
						<logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
							<tr>
 								<td align="left" colspan="2">
									<table width="100%"  border="0" cellpadding="1" cellspacing="1" class="Box" >
										<tr>
											<td width="36" class="table_head">
												<html:checkbox property="removeId" styleClass="Text" tabindex="3" value="userid" onclick="fnCheckAll();"/>    
											</td>
											<td width="109" class="table_head">
												NRIC
												<a href="#">
													<logic:equal name="SendMailForm" property="hidSortKey" value="1" scope="request">
														<logic:equal name="SendMailForm" property="hidOrder" value="ASC" scope="request">
															<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(1,'DESC');" alt="Sort in descending order">
														</logic:equal>
														<logic:equal name="SendMailForm" property="hidOrder" value="DESC" scope="request" >
															<img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(1,'ASC');" alt="Sort in ascending order">
														</logic:equal>
													</logic:equal>
													<logic:notEqual name="SendMailForm" property="hidSortKey" value="1" scope="request">
														<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(1,'ASC');" alt="No Sort">
													</logic:notEqual>
												</a>  	
											</td>
											<td width="109" class="table_head">
												Name  
												<a href="#"> 
													<logic:equal name="SendMailForm" property="hidSortKey" value="2" scope="request">
														<logic:equal name="SendMailForm" property="hidOrder" value="ASC" scope="request">
															<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(2,'DESC');" alt="Sort in descending order">
														</logic:equal>
														<logic:equal name="SendMailForm" property="hidOrder" value="DESC" scope="request" >
															<img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(2,'ASC');" alt="Sort in ascending order">
														</logic:equal>
													</logic:equal>
													<logic:notEqual name="SendMailForm" property="hidSortKey" value="2" scope="request">
														<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(2,'ASC');" alt="No Sort">
													</logic:notEqual>
												</a> 
											</td>
											<td width="109" class="table_head">
												Response
												<a href="#"> 
													<logic:equal name="SendMailForm" property="hidSortKey" value="3" scope="request">
														<logic:equal name="SendMailForm" property="hidOrder" value="ASC" scope="request">
															<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(3,'DESC');" alt="Sort in descending order">
														</logic:equal>
														<logic:equal name="SendMailForm" property="hidOrder" value="DESC" scope="request" >
															<img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(3,'ASC');" alt="Sort in ascending order">
														</logic:equal>
													</logic:equal>
													<logic:notEqual name="SendMailForm" property="hidSortKey" value="3" scope="request">
														<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(3,'ASC');" alt="No Sort">
													</logic:notEqual>
												</a>  	
											</td>
											<td width="109" class="table_head">
												Papers
												<a href="#">
													<logic:equal name="SendMailForm" property="hidSortKey" value="4" scope="request">
														<logic:equal name="SendMailForm" property="hidOrder" value="ASC" scope="request">
															<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(4,'DESC');" alt="Sort in descending order">
														</logic:equal>
														<logic:equal name="SendMailForm" property="hidOrder" value="DESC" scope="request" >
															<img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(4,'ASC');" alt="Sort in ascending order">
														</logic:equal>
													</logic:equal>
													<logic:notEqual name="SendMailForm" property="hidSortKey" value="4" scope="request">
														<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(4,'ASC');" alt="No Sort">
													</logic:notEqual>
												</a>  	
											</td>
											<td width="109" class="table_head">
												Surveys
												<a href="#">
													<logic:equal name="SendMailForm" property="hidSortKey" value="5" scope="request">
														<logic:equal name="SendMailForm" property="hidOrder" value="ASC" scope="request">
															<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(5,'DESC');" alt="Sort in descending order">
														</logic:equal>
														<logic:equal name="SendMailForm" property="hidOrder" value="DESC" scope="request" >
															<img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(5,'ASC');" alt="Sort in ascending order">
														</logic:equal>
													</logic:equal>
													<logic:notEqual name="SendMailForm" property="hidSortKey" value="5" scope="request">
														<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" onClick="javascript:fnSort(5,'ASC');" alt="No Sort">
													</logic:notEqual>
												</a>  	
											</td>
										</tr>
										<logic:iterate id="objSndMail" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.common.OIBASendMail" scope="request" indexId="rowNum">
											<tr>
												<td class="body_detail_text">
													<% 
														if (objSndMail.getChk().trim().length()==0)
														{ 
													%>
														<html:checkbox property="removeId" styleClass="Text" onclick="fnRemoveIdChk();" value = "<%=objSndMail.getNric()%>"/>
													<%
														}
														else
														{
													%>
														<input type="checkbox" name="disable" disabled=true>
													<%
														}
													%>
												</td>
												<td class="body_detail_text">
													<a class="special_body_link" href="#" onClick="javascript:fnMember('<%=objSndMail.getNric()%>')">
														<bean:write name="objSndMail" property="nric"/>
													</a>
												</td>
												<td class="heading_attributes">
													<bean:write name="objSndMail" property="name"/>
												</td>
												<td class="heading_attributes" align="center" width="105">
													<logic:equal name="objSndMail" property="response" value="Y">
														<img border="0" src='<bean:message key="OIFM.docroot" />/images/icon_tick.gif' width="13" height="13">
													</logic:equal>
													<logic:equal name="objSndMail" property="response" value="y">
														<img border="0" src='<bean:message key="OIFM.docroot" />/images/icon_tick.gif' width="13" height="13">
													</logic:equal>
												</td>
												<td class="body_detail_text">
													<a class="special_body_link" href="javascript:;" onClick='javascript:fnPaperList("<%=objSndMail.getNric()%>")'>
														<bean:write name="objSndMail" property="papers"/>
													</a>
												</td>
												<td class="body_detail_text">
													<a class="special_body_link" href="javascript:;" onClick='javascript:fnSurveyList("<%=objSndMail.getNric()%>")'>
														<bean:write name="objSndMail" property="surveys"/>
													</a>
												</td>
											</tr>
										</logic:iterate>
									</table>
								</td>
							</tr>
							<tr>
								<td height="35" align="left" colspan="2">
									<a href="#" onClick="javascript:fnRemove();">
										<img src='<bean:message key="OIFM.docroot" />/images/remove.gif' alt="Remove User from the Paper" border="0">
									</a>
								</td>
							</tr>
							<!-- Pagination -->
							<tr>
								<td height="35" align="left" colspan="2">
									<logic:present name="currentPage" scope="request">
										<table  border="0" cellspacing="0" cellpadding="0" align="right"> 
											<tr>
												<td align="right">
													<table  border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td align="right">
																<table  border="0" cellpadding="2" cellspacing="0" class="BodyText" align="right">
																	<tr>
																		<td nowrap class="Boxinside_text"> 
																			Page 
																			<bean:write name="currentPage" scope="request" /> 
																			of 
																			<bean:write name="totalPage" scope="request" />
																		</td>
																		<logic:present name="previousSet" scope="request">
																			<logic:equal name="previousSet" scope="request" value="true">
																				<td nowrap class="BD_3"> 
																				   <a href='javascript:fnPagination(<bean:write name="previousPage" scope="request"/>);'>&laquo;Previous</a>
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
																						<a href='javascript:fnPagination(<bean:write name="no" />);'><bean:write name="no" />
																						</a>
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
																				<td nowrap class="BD_3"> 
																					<a href='javascript:fnPagination(<bean:write name="nextPage" scope="request"/>);'>Next&raquo;</a>
																				</td>
																			</logic:equal>
																		</logic:present>
																	</tr>
																</table>
															</td>
														</tr>
													</table>
													<input type="hidden" name="pageNo">
												</td>
											</tr>
										</table>
									</logic:present>
									<!--pagination ends -->
								</td>
							</tr>
						</logic:present>
							<tr>
								<td height="35" align="left" colspan="2">
 								</td>
							</tr>

				</table>
			</td>
		</tr>

	</table>
	</td>
	</tr>
</table>
		<!-- List of Hidden Variables -->
		<html:hidden property="id"/>
		<html:hidden property="surveyOrCons"/>
		<html:hidden property="sendOrRemain"/> 
		<html:hidden property="email"/>
		<html:hidden property="hiddenAction"/>
		<input type="hidden" name="tempsubject"/>	
		<html:hidden property="caption"/>	 
		<html:hidden property="winObj"/>	 
			
		<script language="javascript">
			fn_SndMailtextCounter(this.SendMailForm.message,this.SendMailForm.numleft);
		</script>
	</html:form>