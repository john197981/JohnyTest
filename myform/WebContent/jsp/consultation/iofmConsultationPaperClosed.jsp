<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<script language="javascript">
	var strDocroot ='<bean:message key="OIFM.contextroot" />';

	function fnAlertSubmit(actionName,hiddenAction,id,module)
	{
		document.tempForm.iD.value=id;
		document.tempForm.module.value=module;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmit(actionName,hiddenAction)
	{
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnAlertEmail(){
		 var strUrl = "/AlertFriendAction.do?hiddenAction=populate&module=CP&iD="+<bean:write name="ConsultPastPaperForm" property="paperId"/>
		 help_window  = window.open(strDocroot+strUrl,'AlertFriend','width=900,height=260,left=0,top=0,resizable=no,scrollbars=yes');
		 help_window.focus();
	}

</script>
<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">View Consultation Paper  </td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
                  <bean:write name="ConsultPastPaperForm" property="categoryName"/></a>
					<html:hidden name="ConsultPastPaperForm" property="paperId"/>
                  <br>
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
            <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td >
                <table width="98%"  border="0" cellspacing="0" cellpadding="0" class="Box" align = "center">
                  <tr>
                    <td colspan="2" valign="top" class="Table_head">Past Consultation Paper(s) </td>
                    <td width="5%" class="Table_head"><a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');" class="Table_head">Back</a></td>
                    <td width="3%" class="Table_head">
	                    <logic:present name="ConsultPastPaperForm" property="security">
							<logic:equal name="ConsultPastPaperForm" property="security" value="U">
								<a href="#" onclick="javascript:fnAlertEmail();"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Alert a friend on this paper" border="0"></a>
							</logic:equal>
							<logic:equal name="ConsultPastPaperForm" property="security" value="R">
								<a href="javascript:alert('<%= OIDBRegistry.getValues("OI.CONS.OPEN.ALERTFRIEND") %>');" ><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Alert a friend on this paper" border="0"></a>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="ConsultPastPaperForm" property="security">a
							<a href="#" onclick="javascript:fnAlertEmail();"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Alert a friend on this paper" border="0"></a>
						</logic:notPresent>
					</td>
                    <td width="3%" class="Table_head"><a href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?hiddenAction=print&paperId=<bean:write name="ConsultPastPaperForm" property="paperId"/>','mywindow','menubar=no,toolbar=no,copyhistory=no,location=no,directories=no,resizable=1,scrollbars=yes');"><img src="<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif"  border="0" alt="Print"></a></td>
                  </tr>
            <tr>
                <td width="16%" valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Paper Title</td>
                <td colspan="4" valign="top" class="body_extract_text">
						<bean:write name="ConsultPastPaperForm" property="title"/>
					<html:hidden name="ConsultPastPaperForm" property="title"/>
                </td>
                </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" valign="top" class="Heading_Topic"><hr style="height:1px; color=#E4E4E9"></td>
                </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Target Audience </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPastPaperForm" property="targetAudiance"/>
			</span>
				 </td>
                </tr>
              <tr>
			  <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Contact Person </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPastPaperForm" property="contactPerson"/>
				 </td>
                </tr>
              <tr>
			  <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Telephone </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPastPaperForm" property="phone"/>
				 </td>
                </tr>
              <tr>
			  <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Email Address </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPastPaperForm" property="emailAddress"/>
				 </td>
                </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" valign="top" class="body_extract_text">
                <hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Period</td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPastPaperForm" property="fromDt"/>
				 to 
				<bean:write name="ConsultPastPaperForm" property="toDt"/>
			</span>
                </td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" valign="top" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Description </td>
                <td colspan="4" valign="top" class="body_extract_text"><p align="justify"><bean:write name="ConsultPastPaperForm" property="description" filter="false" /> </p></td>
              </tr>
              <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>&nbsp;
              	<logic:present name="ConsultPastPaperForm" property="attachedFile">
	              <td bgcolor="#DEEAF3" class="Heading_Topic">View Attachment </td>
	              <td colspan="4" class="body_extract_text">	
	            <a href='<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?attachedFileName=<bean:write name="ConsultPastPaperForm" property="attachedFile"/>&paperId=<bean:write name="ConsultPastPaperForm" property="paperId"/>&hiddenAction=downLoad' />
								Consultation Paper Attachment 
				</a></td>
	           </logic:present>
              </tr>
               <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Summary </td>
                <td colspan="4" valign="top" class="body_extract_text">
                <p align="justify"><p class="style12">
				<bean:write name="ConsultPastPaperForm" property="summary" filter="false"/> 
				</p></p></td>
              </tr>
              <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Summary Document </td>
                <td colspan="4" valign="top" class="body_extract_text">
               	<a href='<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?summaryFile=<bean:write name="ConsultPastPaperForm" property="attachedSum"/>&paperId=<bean:write name="ConsultPastPaperForm" property="paperId"/>&hiddenAction=downLoad'>
					Consultation Paper Summary
				</a></td>
              </tr>
              <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
				<td >
					<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');" >
						<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' border="0" alt="Back"></a>
				</td>
              </tr>
              
	</table>  
<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
 <form name="tempForm" method="post">
	<input type="hidden" name="iD">
	<input type="hidden" name="module">
	<input type="hidden" name="hiddenAction">
</form>