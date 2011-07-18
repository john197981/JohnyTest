<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script language="javascript">
<!--
function showAll() {
	
	var right = "TR";
	var down = "TD";
	var expand = "TE";
	var collapse = "TC";

	for(var i=0; i < <bean:write name="total" scope="request" />;i++){
		show(i);
	}
	
	document.getElementById(right).style.display = "none";
	document.getElementById(down).style.display = "";
	document.getElementById(expand).style.display = "none";
	document.getElementById(collapse).style.display = "";
}

function hideAll() {
	
	var right = "TR";
	var down = "TD";
	var expand = "TE";
	var collapse = "TC";

	for(var i=0 ; i < <bean:write name="total" scope="request" />;i++){
		hide(i);
	}
	
	document.getElementById(right).style.display = "";
	document.getElementById(down).style.display = "none";
	document.getElementById(expand).style.display = "";
	document.getElementById(collapse).style.display = "none";
}

function show(sectionId) {

	var right = sectionId + "R";
	var down = sectionId + "D";
	var profile = sectionId + "P";

	document.getElementById(right).style.display = "none";
	document.getElementById(down).style.display = "";
	document.getElementById(profile).style.display = "";
}

function hide(sectionId) {

	var right = sectionId + "R";
	var down = sectionId + "D";
	var profile = sectionId + "P";

	document.getElementById(right).style.display = "";
	document.getElementById(down).style.display = "none";
	document.getElementById(profile).style.display = "none";
}

function submitForm(id) {
	document.ASMFormManagement.hiddenAction.value = 'EDIT';
	document.ASMFormManagement.id.value = id;
	document.ASMFormManagement.submit();
}

//-->
</script>

<html:form action="/ASMManagement.do" method="post">

	<html:hidden property="hiddenAction" />
	<html:hidden property="id" />

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
													<td><div align="left">Senior Management Profile</div></td>
													<td><div align="right"><a class="Table_head" href="#" onclick="submitForm('0')">Add New</a></div></td>
												</tr>
   												<tr>
													<td colspan="2">
													<table width="100%" border=0 cellpadding="0"
														cellspacing="0"  bgcolor="white">
														<tr>
															<td width="3%" align="left" valign="middle"
																class="BodyText"><a href="javascript:showAll()"
																class="BodyText_profile">
															<div id='TR'><img
																src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif'
																alt="expand" width="15" heigth="15" border="0"></div>
															</a> <a href="javascript:hideAll()"
																class="BodyText_profile">
															<div id='TD' style="display:none"><img
																src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif'
																width="15" heigth="15" border="0" alt="collapse"
																border="0"></div>
															</a></td>
															<td width="95%" valign="middle" class="BodyText">
															<div id='TE'>Expand All</div>
															<div id='TC' style="display:none">Collapse All</div>
															</td>
														</tr>

														<% String currHeading = null; %>
														<logic:iterate id="management" name="list" indexId="idx"
															type="com.oifm.asm.ASMBAManagement">

															<% if (currHeading == null || !currHeading.equals(management.getHeading())) { %>
															<% 		currHeading = management.getHeading(); %>
															<tr>
																<td class="Heading_Mgmt">&nbsp;</td>
																<td align="left" valign="top" class="Heading_Mgmt"><bean:write
																	name="management" property="heading" /></td>
															</tr>
															<% } %>

															<tr>
																<td class="Subhead1">&nbsp;</td>
																<td align="left" valign="top" class="Subhead1"><bean:write
																	name="management" property="subheading" /></td>
															</tr>
															<tr class="BodyText">
																<td align="left" valign="top"><a
																	href="javascript:show('<bean:write name="idx" />')"
																	class="BodyText_profile">
																<div id='<bean:write name="idx" />R'><img
																	src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif'
																	ALT="expand" WIDTH="15" HEIGHT="15" BORDER="0"></div>
																</a> <a
																	href="javascript:hide('<bean:write name="idx" />')"
																	class="BodyText_profile">
																<div id='<bean:write name="idx" />D'
																	style="display:none"><img
																	src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif'
																	WIDTH="15" HEIGHT="15" BORDER="0" ALT="collapse"></div>
																</a></td>
																<td align="left" valign="top">
																<blockquote>
																<p><a href="#" onclick="submitForm('<bean:write name="management" property="id" />')"><strong><bean:write name="management" property="name" /></strong></a>
																<em class="BodyText_profile"><bean:write
																	name="management" property="designation" />, <bean:write
																	name="management" property="division" /></em></p>
																</blockquote>
																</td>
															</tr>
															<tr class="BodyText" id='<bean:write name="idx" />P'
																style="display:none">
																<td align="left" valign="top">&nbsp;</td>
																<td>
																<table width="100%" border="0" cellpadding="0"
																	cellspacing="0" bgcolor="white">

																	<tr valign="top">
																		<td width="105">
																			<logic:notEmpty name="management" property="photograph">
																				<img src='<bean:message key="OIFM.contextroot" />/ASMManagement.do?hiddenAction=PHOTO&photograph=<bean:write name="management" property="photograph" />' alt='<bean:write name="management" property="name" />'>
																			</logic:notEmpty>
																			<logic:empty name="management" property="photograph">
																				&nbsp;
																			</logic:empty>
																		</td>
																		<td class="body_detail_text"><bean:write name="management"
																			property="profile" filter="false"/> <br>
																		<br>
																		<logic:notEmpty name="management" property="divisionurl">
																		<a href='http://<bean:write name="management" property="divisionurl" />' target="_blank">
																			<bean:write name="management" property="division" /> Division Homepage</a> (available only in MOE intranet)
																		</logic:notEmpty>
																		</li>

																		</td>
																	</tr>
																	<tr
																		background='<bean:message key="OIFM.docroot" />/images/profile_end.gif' alt="New Letter">
 																		<td colspan="2" height="20"
																			background='<bean:message key="OIFM.docroot" />/images/profile_end.gif'>&nbsp;</td>
																	</tr>
																</table>
																</td>
															</tr>
														</logic:iterate>
												</table>
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
