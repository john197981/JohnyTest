
<%--
/**
 * FileName			: ASMHomePage.jsp
 * Author      		: Anbalagan
 * Created Date 	: 09/12/2005
 * Description 		: This page used to display the ASM home page.
						This page is called from a. on click of right side menu topic link
												b. on click of ASM in the top menu
												c. on click of topic link in the ASM report search details
												d. on click of topic link in the Previous Replies search details
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>   
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></script>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr><td valign="top" align="center">
<html:form action="asmHome.do" method="post" >

 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">
<%if(request.getParameter("hiddenAction")!=null &&  request.getParameter("hiddenAction").equalsIgnoreCase("PrevRepSearch")){%>
	Previous Published Replies		
 <%}else{%>
	What's Up
 <%}%>
			
			</td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>

				<%if(request.getParameter("hidPageDesc")!=null && 
					request.getParameter("hidPageDesc").equals("RecentLetters")){
					}else{ %>
				
 						<logic:present name="announcement" scope="request">
							<bean:write name="announcement" scope="request" filter="false" />
						</logic:present>
				<%}%>

                 <br>
                </p>
                 
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
 			<table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="648" class="Grey_fade">&nbsp;</td>
                <td width="16" class="Blue">&nbsp;</td>
                <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				  

<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Home" /> </jsp:include>
					   

					   <p class="Address_body">&nbsp;</p></td>
              </tr>
              <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="Box">
					<logic:notEqual name="letter_topic" value="">
					<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
				
				<%if(request.getParameter("hiddenAction").equalsIgnoreCase("ReportSearch")){ %>

                      <tr>
                        <td colspan="5" class="Table_head"> 
							<a href="#" onClick="javascript:window.history.back()" scope="request"><img src='<bean:message key="OIFM.docroot" />/images/button/btn_Back.gif' alt="Back To Report" border="0" alt="Back"></a> 
						</td>
                      </tr>
						<%}%>
						
					<%if(!request.getParameter("hiddenAction").equalsIgnoreCase("ReportSearch") && !request.getParameter("hiddenAction").equalsIgnoreCase("PrevRepSearch")){ %>

                      <tr class="Table_head">
                        <td colspan="3" class="Table_head"> Latest Letter(s)</td>

				<%if(!request.getParameter("hiddenAction").equalsIgnoreCase("ReportSearch") &&
				!request.getParameter("hiddenAction").equalsIgnoreCase("PrevRepSearch")){ 
				if(request.getParameter("hidPageDesc")!=null && 
					request.getParameter("hidPageDesc").equals("RecentLetters")){ %>
					<td  width="16%">
						<div id="5">
							<font color="#CC3300">&lt;</font>
							<a href="#" class="Table_head" onClick="javascript:fnPrevious('<bean:write name="TotRecSizRecLtr" scope="request"/>')">Previous
							</a> &nbsp;
							
							</div>  
							</td><td align="right"  width="10%">
							
							<div id="6"><a href="#" class="Table_head" style="cursor:hand" onclick="fnNext('<bean:write name="TotRecSizRecLtr" scope="request"/>')" >Next</a>&nbsp;<font color="#CC3300">&gt;</font></div>
							
					<%}
					else{ %>
						<td colspan="2"></td>
						
					<%}%>
					
					<%}else{%>                        
					<td colspan="2"></td>
					<%}%>
					</tr>
					  <%}else{


					   %>
					
                      <tr class="Table_head">
                        <td colspan="3" class="Table_head"> Replied Letter</td>

<td width="10" class="Table_head">
				<%if(!request.getParameter("hiddenAction").equalsIgnoreCase("ReportSearch") &&
				!request.getParameter("hiddenAction").equalsIgnoreCase("PrevRepSearch")){ 
				if(request.getParameter("hidPageDesc")!=null && 
					request.getParameter("hidPageDesc").equals("RecentLetters")){ %>
					
						<div id="5">
							<font color="#CC3300">&lt;</font>
							<a href="#" class="Table_head" onClick="javascript:fnPrevious('<bean:write name="TotRecSizRecLtr" scope="request"/>')">Previous
							</a> &nbsp;
							
							</div>  
							</td><td align="right" width="1">
							
							<div id="6"><a href="#" class="Table_head" style="cursor:hand" onclick="fnNext('<bean:write name="TotRecSizRecLtr" scope="request"/>')" >Next</a>&nbsp;<font color="#CC3300">&gt;</font></div>
							
					<%}
					}%>
</td>

 						<td width="4%" class="Table_head"><a href="#" onClick="javascript:window.history.back()" scope="request" class="Table_head">Back </a>
						
						</td>

                      </tr>

					  <%}%>
                      <tr bgcolor="#E0EBFC">
                        <td colspan="2" class="Sub_head">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<bean:write name="letter_topic" scope="request" filter="false" />
							 
						</td>
                        <td colspan="3" width="32%" align="right" nowrap class="Heading_Thread">
						<bean:write name="published_on" scope="request"/>
						 &nbsp;
						
						<a href="#" style="cursor:hand" onClick="javascript:fnAlertEmail()"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Email letter link to your friend" width="16" height="16" border="0"></a> 
                        
						&nbsp;
						<a href="#" style="cursor:hand" onclick="fnPrintPreview()"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' alt="Print the letter content as shown" width="16" height="16" border="0"></a>
						</td>						

                      </tr>
                      <tr>
                        <td width="5%">&nbsp;</td>
                        <td colspan="4" align="justify" class="body_detail_text"><p>
						
						<br>
						<bean:write name="letter_content" scope="request" filter="false" />
						 
						  <br>
                          <span class="body_detail_text">
							<!--bean:write name="created_by" scope="request" filter="false" /-->
						  </span> <br></p>
						  </td>
                      </tr>
                      <tr>
                        <td colspan="5">&nbsp;</td>
                      </tr>
                      <tr>
                        <td colspan="5" bgcolor="#E0EBFC" class="Sub_head">
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						Reply<br></td>
                      </tr>
                      <tr>
                        <td>&nbsp;</td>
                        <td colspan="4" align="justify" class="body_detail_text">						
						<p >
						<bean:write name="reply_content" scope="request" filter="false" />
 						 <br><br>
						 <span class="body_detail_text">
                                 <!--bean:write name="replied_by" scope="request" filter="false" /-->
								 </span> 
								 <br></p>
						   </div></td></tr>
                     </table>
					</logic:notEqual>
					
					</td>
                  </tr>
                </table>
                  </td>
                <td class="Blue">&nbsp;</td>
              </tr>
            </table>
			

			</td>
 		  </tr>
        </table>
	</td>
  </tr>
   </table>
   <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
<!-- This hidden variable holds the letter id-->
<html:hidden property="hidLetterID"/>	
<!-- This hidden variable holds the Page Description (ie) ASM Home Page or Recent Replies-->
<html:hidden property="hidPageDesc"/>
<html:hidden property="hiddenAction"/>
<script language="javascript">
	var contextRoot ='<bean:message key="OIFM.contextroot" />'
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
 		document.ASMFormHome.hiddenAction.value=hiddenAction;
		document.ASMFormHome.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormHome.submit();
	}
</script>
<%
	if(request.getParameter("hidPageDesc")!=null && request.getParameter("hidPageDesc").equals("RecentLetters")
	&& !request.getParameter("hiddenAction").equals("PrevRepSearch") && !request.getParameter("hiddenAction").equals("ReportSearch"))
	{ 
%>
		<script>
			fnDisplayHidePrevNext(<%=request.getAttribute("TotRecSizRecLtr")%>)
		</script>
<%
	}
%>
</html:form>