<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<% 
try
{
%>
<head>
<style type="text/css"> 
<!-- .style2 {font-size: 11px}
a:link {
	text-decoration: none;
}
.style3 {color: #FFFFCC}
	--> 
	</style>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/MySay.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->

   		</style>

<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
	<jsp:param name="pageName" value="ASM" />
</jsp:include>

<script language="javascript">
	function fnSubmit(actionName,letterId,hiddenAction)
	{
		document.tempForm.letterId.value=letterId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmitSimple(actionName,hiddenAction)
	{
		document.tempForm.letterId.value="";
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}

	function fnAlertEmail(strUrl)
	{
		window.showModalDialog(strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
	}

	function fnPrintPreview()
	{
		window.open('asmHome.do?hiddenAction=PrintPreview&hidLetterID='+<bean:write name="ASMFormLetter" property="letterId" />,'printPreview','left=1,top=1,width=750,height=500,scrollbars=yes');
	}

</script>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr><td valign="top" align="center">
		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Submit/View Your Letter(s)</td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>&nbsp;
                 <br>
                </p>
                 
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td class="Grey_fade">&nbsp;</td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
 			<table width="857" border="0" cellspacing="0" cellpadding="0">   
		  <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc">
                <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
				<tr><td ><table width="98%" border="0" cellspacing="0" cellpadding="0">
                  
                </table></td>
				</tr>
              <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc">
                <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="Box">
					<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
				     <tr>
                        <td colspan="4" class="Table_head"> 
						
						<logic:equal name="ASMFormLetter" property="status" value="P">
							Published Letter
						</logic:equal>
						<logic:equal name="ASMFormLetter" property="status" value="S">
							Submitted Letter
						</logic:equal>
						<logic:equal name="ASMFormLetter" property="status" value="R">
							Replied Letter
						</logic:equal>
						<logic:equal name="ASMFormLetter" property="status" value="T">
							Redirected Letter
						</logic:equal>
						
						</td>
						<td align="right" width="45%"  class="Table_head" >


<logic:present name="ASMFormLetter" property="previousLetterId">
<font color="#CC3300">&lt;</font>
	<logic:notEqual name="ASMFormLetter" property="previousStatus" value="D">
		<a class="Table_head" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="ASMFormLetter" property="previousLetterId" />','detailLetter');">
		Previous  

</a>
	</logic:notEqual>
	<logic:equal name="ASMFormLetter" property="previousStatus" value="D">
		<a class="Table_head" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="ASMFormLetter" property="previousLetterId" />','newOrEditLetter');">
			 Previous  

</a>
	</logic:equal>
	&nbsp;
</logic:present>
<logic:present name="ASMFormLetter" property="nextLetterId">
	<logic:notEqual name="ASMFormLetter" property="nextStatus" value="D">
		<a class="Table_head" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="ASMFormLetter" property="nextLetterId" />','detailLetter');">
			Next 
			</a>
	</logic:notEqual>
	<logic:equal name="ASMFormLetter" property="nextStatus" value="D">
		<a class="Table_head" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="ASMFormLetter" property="nextLetterId" />','newOrEditLetter');">
		Next  
</a>
	</logic:equal>
	<font color="#CC3300">&gt;</font> 
</logic:present>
&nbsp;
						<a class="Table_head" href="#" onclick="javascript:fnSubmitSimple('<bean:message key="OIFM.contextroot" />/asmHome.do','populate');">
							 Back to ASM </a>
&nbsp;
<logic:equal name="ASMFormLetter" property="status" value="P">
	<a href="#" style="cursor:hand" onclick="javascript:fnAlertEmail('<bean:message key="OIFM.contextroot" />/AlertFriendAction.do?hiddenAction=populate&module=ASM&iD='+<bean:write name="ASMFormLetter" property="letterId" />);">
		<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Email letter link to your friend" width="16" height="16" border="0"></a>
</logic:equal>
<logic:equal name="ASMFormLetter" property="status" value="S">
	<a href="#" style="cursor:hand" onclick="javascript:fnAlertEmail('<bean:message key="OIFM.contextroot" />/AlertFriendAction.do?hiddenAction=populate&module=ASMDRAFT&iD='+<bean:write name="ASMFormLetter" property="letterId" />);">
		<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Email letter link to your friend" width="16" height="16" border="0"></a>
</logic:equal>

<a href="#" onclick="javascript:fnPrintPreview();" style="cursor:hand" >
	<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' alt="Print the letter content as shown" width="16" height="16" border="0"></a>
&nbsp;

						</td>
						</tr>
                      <tr>
                        <td colspan="5" align="justify" class="body_detail_text">
						</tr>


                      <tr>
                        <td colspan="5" bgcolor="#E0EBFC" class="Sub_head"> 
							<bean:write name="ASMFormLetter" property="topic" filter="false" />
						</td>
                      </tr>
                      <tr>
                        <td width="5%">&nbsp;</td>
                        <td colspan="4" align="justify" class="body_detail_text"><p>
						
						<br>
						<bean:write name="ASMFormLetter" property="letter" filter="false" />
						 
						  <br>
                          <span class="user_online">
							<bean:write name="ASMFormLetter" property="authorName"/>
						  </span> <br></p>
						  </td>
                      </tr>
                      <tr>
                        <td colspan="5">&nbsp;</td>
                      </tr>
					  <logic:equal name="ASMFormLetter" property="status" value="P">
						  <tr>
							<td colspan="5" bgcolor="#E0EBFC"  class="Sub_head">Reply</td>
						  </tr>
						  <tr>
							<td>&nbsp;</td>
							<td colspan="4" align="justify" class="body_detail_text">
							
							<p align="justify">
							<bean:write name="ASMFormLetter" property="reply" filter="false" />
							 <br>
							<blockquote><br>
									<br>
									<bean:write name="ASMFormLetter" property="repliedBy"/>
									</blockquote>
							  </div>
							  </td>
							</tr>
					</logic:equal>

					  <logic:equal name="ASMFormLetter" property="status" value="R">
						  <tr>
							<td colspan="5" bgcolor="#F0F8FF" class="Sub_head">Reply</td>
						  </tr>
						  <tr>
							<td>&nbsp;</td>
							<td colspan="4" align="justify" class="body_detail_text">
							
							<p align="justify">
							<bean:write name="ASMFormLetter" property="reply" filter="false" />
							 <br>
							<blockquote><br>
									<br>
									<bean:write name="ASMFormLetter" property="repliedBy"/>
									</blockquote>
							  </div>
							  </td>
							</tr>
					</logic:equal>


                      <tr>
                        <td>&nbsp;</td>
                        <td colspan="4">&nbsp;</td>
                      </tr>

                      <tr>
                        <td colspan="5" align="center">

</td>


                      </tr>

                    </table>

					
					
					</td>
                  </tr>
                </table>
                  <br></td>
                <!--<td class="Blue">&nbsp;</td> -->
              </tr>
            </table>

			</td>
 		  </tr>
        </table>
			</td>
 		  </tr>
        </table>
 <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
 <form name="tempForm" method="post">
	<input type="hidden" name="letterId">
	<input type="hidden" name="hiddenAction">
</form>

<% 
}catch(Exception e)
{
	out.println(e.getMessage());
}
%>
