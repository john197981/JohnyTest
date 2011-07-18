<%--
/**
 * FileName			: ASMPrevRep.jsp
 * Author      		: Anbalagan /Rakesh
 * Created Date 	: 09/12/2005
 * Modified Date	: 25/01/2008
 * Description 		: This page used to display the ASM home page.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="com.oifm.asm.ASMBVReplyRedirect,com.oifm.utility.OIUtilities"%>
   
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmReplyRedirect.js'></script>

<%try{%>
<html:form action="ASMReplyRedirect.do" method="post" >
	 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

 	<table width="100%" border="0" cellpadding="0"
		cellspacing="0" bgcolor="white">
		<tr>
			<td class="TableHead">
			<table width="50%" border="0" cellspacing="0" cellpadding="0" bgcolor="white">
				<tr>
				<td class="Box">
					<table width="100%" border="0" cellpadding="1"
						cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="9">Reply/Redirect letters</td>
 					</tr>
					<tr >
						<td colspan="9" bgcolor="#F0F8FF" class="Sub_head">Inbox</td>
 					</tr>
                           <!--
								<tr>
					  <td class="Boxoutline1">
						<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
						-->
		                                      <tr class="Subhead1" >
							<td width="8%"  valign="top" nowrap class="Subhead">Sts
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("STATUS")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('STATUS')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"/></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('STATUS')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0" /></a>
							<%}%>
							</td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Title
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("UPPER(TOPIC)") ){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('UPPER(TOPIC)')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('UPPER(TOPIC)')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong>
							</td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Sub By 
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("CREATEDBY")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('CREATEDBY')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('CREATEDBY')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Sub On 
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("SUBMITTEDON DESC")||
									OIUtilities.replaceNull(request.getParameter("hidSortBy")).equals("")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('SUBMITTEDON DESC')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('SUBMITTEDON DESC')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Div in chg.
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("B.DESCRIPTION")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('B.DESCRIPTION')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('B.DESCRIPTION')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Redir On 
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("REDIRECTEDON")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('REDIRECTEDON')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('REDIRECTEDON')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Repl On 
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("REPLIEDON")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('REPLIEDON')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('REPLIEDON')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td>
							<td width="8%" valign="top" nowrap class="Subhead"><strong>Category 
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("d.NAME")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('d.NAME')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('d.NAME')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td>
						<td width="8%" valign="top" nowrap class="Subhead"><strong>Publ On 
							<%if(OIUtilities.replaceNull(request.getParameter("hidSortBy")).equalsIgnoreCase("PUBLISHEDON")){%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('PUBLISHEDON')"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}else{%>
								<a href="#" style="cursor:hand" onclick="fnSortBy('PUBLISHEDON')"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Sort" width="12" height="15" border="0"></a>
							<%}%>
							</strong></td> 
						    </tr>
						      <tr>
							<td height="10" colspan="9">&nbsp;</td>
						      </tr>
<!-- start-->
								<logic:notEqual name="TotRec" value="0">
								<logic:iterate id="objBV" name="reply_redirect_letter" type="com.oifm.asm.ASMBVReplyRedirect" scope="request" indexId="rowNum" >
						      <tr>
							<td height="29">
							<%if(OIUtilities.replaceNull(objBV.getHidStatus()).equalsIgnoreCase("P")){%> <!-- Publish-->
								<img src='<bean:message key="OIFM.docroot" />/images/Publish.gif' alt="Publish" width="19" >
							<%}else if(OIUtilities.replaceNull(objBV.getHidStatus()).equalsIgnoreCase("S")){%> <!-- Submit-->
								<img src='<bean:message key="OIFM.docroot" />/images/published.gif' alt="Submit" width="19" >
							<%}else if(OIUtilities.replaceNull(objBV.getHidStatus()).equalsIgnoreCase("R")){%> <!-- Reply-->
								<img src='<bean:message key="OIFM.docroot" />/images/replied_to.gif' alt="Reply" width="19" >
							<%}else if(OIUtilities.replaceNull(objBV.getHidStatus()).equalsIgnoreCase("T")){%> <!-- Redirect-->
								<img src='<bean:message key="OIFM.docroot" />/images/redirect.gif' alt="Redirect" width="19" >
							<%}%>

							</td>
							<td colspan="7" class="heading_attributes"><a href="#" class="Special_body_link" style="cursor:hand" onclick="fnClickTopic('<bean:write name="objBV" property="hidLetterID"/>')"><bean:write name="objBV" property="hidTopic"  /></a></td>
						      </tr>
						      <tr class="Text">
							<td> </td>
							<td></td>
							<td class="heading_attributes"><bean:write name="objBV" property="hidSubmBy"/></td>
							<td class="heading_attributes"><bean:write name="objBV" property="hidSubmOn"/></td>
							<td class="heading_attributes"><bean:write name="objBV" property="hidDivInCharge"/></td>
							<td class="heading_attributes"><bean:write name="objBV" property="hidRedtOn"/></td>
							<td class="heading_attributes"><bean:write name="objBV" property="hidReplOn"/></td>
							<td class="heading_attributes"><bean:write name="objBV" property="hidCategoryName"/></td> 
							<td class="heading_attributes"><bean:write name="objBV" property="hidPublOn"/></td>
						    </tr>
					  
						  <tr>
							<td>&nbsp;</td>
							<td height="1" colspan="8"><hr size="1" style="color:#CCCCCC"></td>
						    </tr>
									</logic:iterate>
								</logic:notEqual>

<!---end -->

	                              			</table>
 									</td>
								</tr>
							</table>

		<logic:greaterThan name="totalPage" value="1">
		<!--pagination start  -->
		<br>
 			<table border="0" cellpadding="2"  cellspacing="0" class="BodyText" align="right">
				<tr align="left">
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
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="previousPage" scope="request"/>');">
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
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="no" />');">		
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
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="nextPage" scope="request"/>');">
									Next&raquo;</a>
							</td>
						</logic:equal>
					</logic:present>

		</tr>
			</table>
 
 			</td>
		</tr>
	</table>
			<br><br>
			</logic:greaterThan>
		<!-- Pagination end-->
		<table width="100">
		<tr><td width="30"><img src='<bean:message key="OIFM.docroot" />/images/published.gif' alt="Submit" width="19" ></td>
		<td class="Heading_Thread">Submitted</td></tr>

		<tr><td ><img src='<bean:message key="OIFM.docroot" />/images/redirect.gif' alt="Redirect" width="19" ></td>
		<td class="Heading_Thread">Redirected</td></tr>
		
		<tr><td ><img src='<bean:message key="OIFM.docroot" />/images/replied_to.gif' alt="Reply" width="19" ></td>
		<td class="Heading_Thread">Replied</td>		</tr>
		
		<tr><td ><img src='<bean:message key="OIFM.docroot" />/images/Publish.gif' alt="Publish" width="19" ></td>
		<td class="Heading_Thread">Published</td></tr>


		</table>
			<br><br>		

<!-- This hidden variable holds the letter id-->
<html:hidden property="hidLetterID"/>	
<html:hidden property="hiddenAction"/>
<html:hidden property="hidSortBy"/>
<html:hidden property="hidLink" />
<html:hidden property="letterId"/>
<html:hidden property="fromCategory"/>

</html:form> 
<%}catch(Exception e){out.println(e);}%>

