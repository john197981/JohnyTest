<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js' ></SCRIPT>

<script language="javascript">

var contextroot='<bean:message key="OIFM.docroot" />'

function pagination(pageno) {
	document.ASMFormEditor.pageNo.value = pageno;
	document.ASMFormEditor.hiddenAction.value='WEB';
	document.ASMFormEditor.submit();
}
function fnEditorNotesAlertEmail(){
	 var strUrl = '/AlertFriendAction.do?hiddenAction=populate&module=ASMEditorNote&iD='+document.ASMFormHome.hidLetterID.value;
	 window.showModalDialog(contextRoot+strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
}

</script>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr><td valign="top" align="center">

<html:form action="/ASMEditor.do" method="post">

<html:hidden property="hiddenAction"/>
<html:hidden property="pageNo"/>



 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade"></td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
						Latest Editors Note / ASM Letters
 						<!-- <logic:present name="ModuleDesc" scope="request">
							<bean:write name="ModuleDesc" scope="request" filter="false" />
						</logic:present> -->

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
				  
<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true" />
					   

					   <p class="Address_body">&nbsp;</p></td>
              </tr>
              <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="Box">
					
					<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="white">
                      <tr>
                        <td colspan="5" class="Table_head">  </td>
 						</tr>
 					<logic:iterate id="editor" name="list" indexId="idx" type="com.oifm.asm.ASMBAEditor">
				
                  <!--    <tr bgcolor="#E0EBFC">
                        <td colspan="5" bgcolor="#E0EBFC" class="Sub_head"> 
						<bean:write name="editor" property="editon"/> - <bean:write name="editor" property="topic" />
						 &nbsp;&nbsp;
						</td>

                      </tr>  -->
                      
                     <tr bgcolor="#E0EBFC">
                        <td colspan="2" class="Sub_head">  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<bean:write name="editor" property="topic" />
						</td>
						<td colspan="3" width="32%" align="right" nowrap class="Heading_Thread">
						<bean:write name="editor" property="editon"/>
						 &nbsp;
						<a href="#" style="cursor:hand" onClick="javascript:fnEditorNotesAlertEmail()"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Email link to your friend" width="16" height="16" border="0"></a> 
						&nbsp;
						<a href="#" style="cursor:hand" onclick="fnPrintPreview()"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' alt="Print the content as shown" width="16" height="16" border="0"></a>
						</td>						
                      </tr>  
                      <tr>
                        <td width="5%">&nbsp;</td>
                        <td colspan="4" align="justify" class="body_detail_text">
						<br>
						<bean:write name="editor" property="content" filter="false"/> 
						
						  </td>
                      </tr>

					  <!-- Pagination starts here -->
			<logic:present name="PageInfo" scope="request" >
				<bean:define id="pageInfo" name="PageInfo" scope="request" type="com.oifm.common.OIPageInfoBean" />
				<% int linkStart = pageInfo.getCurrLinkStart(); %>
				<logic:greaterThan name="pageInfo" property="noOfPages" value="1">
					<tr>
						<td align="left">
							<table border="0" cellpadding="2" cellspacing="0" class="BodyText" align="left">
								<tr>
									<td nowrap class="Boxinside_text"> 
										Page 
										<bean:write name="pageInfo" property="pageNo" /> 
										of 
										<bean:write name="pageInfo" property="noOfPages" />
									</td>
									<%if (pageInfo.isPSet()) { %>
										<td nowrap class="BD_3"> 
											<a href='#' onclick="pagination('<bean:write name="pageInfo" property="prevSet" />')">&laquo;Previous</a>
										</td>
									<%}%>
									<% for (int a = linkStart; a < linkStart + pageInfo.getNoOfLinks(); a++) {
											if (a <= pageInfo.getNoOfPages()) {
												if (String.valueOf(a).trim().equals(String.valueOf(pageInfo.getPageNo()).trim())) {
									%>
													<td nowrap class="BD_1">
														<%= a %>
													</td>
									<% 			} else { %>
													<td nowrap class="BD_2">
														<a href='#' onclick="pagination('<%= a %>')"><%= a %></a>
													</td>
									<% 			}
											}
										}
									%>
									<%if (pageInfo.isNSet()) { %>
										<td nowrap class="BD_3"> 
											<a href='#' onclick="pagination('<bean:write name="pageInfo" property="nextSet" />')">Next&raquo;</a>
										</td>
									<%}%>
								</tr>
							</table>
						</td>
 			    	</tr>
				</logic:greaterThan >
			</logic:present>

					  <!-- Pagination Ends here -->

                    
					</logic:iterate>
					</table>
					</td>
                  </tr>
                </table>
                  <br></td>
                <td class="Blue">&nbsp;</td>
              </tr>
            </table>
			

			</td>
 		  </tr>
        </table>
 
			</td>
 		  </tr>
        </table>
<% System.out.println("from ramesh");%>
</html:form>

<script language="javascript">

	document.ASMFormHome.hidLetterID.value='<bean:write name="editor" property="id" filter="false"/>';
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
 		document.ASMFormHome.hiddenAction.value=hiddenAction;
		document.ASMFormHome.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormHome.submit();
	}
</script>