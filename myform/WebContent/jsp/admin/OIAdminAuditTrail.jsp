<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.useradmin.OIAuditTrailConstants" %>
<%@ page import="com.oifm.forum.OIForumConstants" %>

<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
<script language="javascript">
function doSearch() {
	var flag = true;
	if (flag) flag = checkBlank(document.AuditTrailForm.strFrom, "From date");
	if (flag) flag = checkBlank(document.AuditTrailForm.strTo, "To date");
	if (flag) flag = compareTwoDates(document.AuditTrailForm.strFrom.value, document.AuditTrailForm.strTo.value, "From date", "To date", true);
	if (flag) {
		var frm = document.AuditTrailForm;
		frm.hiddenAction.value = '<%= OIAuditTrailConstants.DO_SEARCH %>';
		frm.submit();
	}
}
</script>

<html:form action="/AuditTrail.do" method="post">

<html:hidden property="hiddenAction" />

	 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>


 	<table width="98%" border="0" cellpadding="0"
		cellspacing="0" bgcolor="white">
		<tr>
 				<td class="Box">
					<table width="100%" border="0" cellpadding="0"
						cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="7">Audit Trail</td>
 					</tr>
  					<logic:present name="error" scope="request" >
						  <tr>
							<td width="100%" class="BodyText" colspan="7">
							<b><bean:message name="error" scope="request"/></b>
							</td>
						  </tr>
					</logic:present>
               <tr>
                <td colspan="7" class="Sub_head">Search Audit Trail Transactions </td>
              </tr>
              <tr>
                <td width="15%" nowrap class="Heading_attributes">Transaction Type</td>
                <td colspan="6"  ><html:select property="strType" styleClass="Text_box">
              		<html:option value="%">All</html:option>
              		<html:option value="MT">Moderate Threads</html:option>
              		<html:option value="MP">Moderate Postings</html:option>
                        </html:select></td>
              </tr>
              <tr>
                <td nowrap class="Heading_attributes">Transaction Date <font color="red">*</font>  </td>
                <td width="10%" ><html:text property="strFrom" styleClass="text_box" size="14" readonly="true" /></td>
                <td width="6%" ><a href="#" onClick="cal.select(document.forms['AuditTrailForm'].strFrom,'strFrom','dd-NNN-yyyy');return false;"><img src="<bean:message key="OIFM.docroot"/>/images/img_calendar.gif" border="0" alt="Calendar"></a></td>
                <td width="8%" class="Heading_Thread">To <font color="red">*</font> </td>
                <td width="50%" ><html:text property="strTo" styleClass="text_box" size="14" readonly="true" />
                	<a href="#" onClick="cal.select(document.forms['AuditTrailForm'].strTo,'strTo','dd-NNN-yyyy');return false;"><img src="<bean:message key="OIFM.docroot"/>/images/img_calendar.gif" border="0" alt="Calendar"></a></td>
                <td width="8%" >&nbsp;</td>
                <td width="7%" >&nbsp;</td>
              </tr>
  
        <tr>
          <td height="35" align="left" valign="middle" colspan="7">&nbsp;&nbsp;<a href="#" onclick="doSearch()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Search.gif" border="0" alt="Search"></a> <a href="#" onclick="document.AuditTrailForm.reset()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif" border="0" alt="Reset"></a></td>
        </tr>
        <logic:present name="Transactions" scope="request" >
        <bean:size id="arraySize" name="Transactions" scope="request" />
        <tr>
          <td height="35" align="left" valign="middle" colspan="7" >
		  <table width="98%"  border="0" align="center" cellpadding="2" cellspacing="1" bgcolor="white" class="Box">
            <tr>
              <td colspan="8" class="Sub_head">Audit Trail Transactions</td>
            </tr>
            <tr align="center" class="Subhead">
              <td height="25" colspan="3" class="Heading_Thread">Transaction</td>
              <td height="25" colspan="3" class="Subhead">Posted</td>
              <td rowspan="2" class="Subhead">Posted Content </td>
              <td rowspan="2" class="Subhead">Modified Content </td>
            </tr>
            <tr align="center" class="Subhead">
              <td height="25" class="Subhead"> By</td>
              <td class="Subhead"> Type </td>
              <td class="Subhead">On</td>
              <td height="25" class="Subhead"> By</td>
              <td class="Subhead">On</td>
              <td class="Subhead">Thread</td>
              </tr>
            <logic:greaterThan name="arraySize" scope="page" value="0">
            <logic:iterate id="objTransactions" name="Transactions" indexId="idx" type="com.oifm.useradmin.OIAuditTrailBean">
            	<tr align="left" valign="top" class="BodyText">
              		<td width="8%" class="BodyText"><a href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="objTransactions" property="strUserID" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="objTransactions" property="strNickname" /></a></td>
              		<td width="7%" class="BodyText"><bean:write name="objTransactions" property="strType" /></td>
              		<td width="12%" class="BodyText"><bean:write name="objTransactions" property="strDateTime" /></td>
              		<td width="8%" class="BodyText"><a href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="objTransactions" property="strPostUserID" />&hiddenAction=populate','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="objTransactions" property="strPostNickname" /></a></td>
              		<td width="12%" class="BodyText"><bean:write name="objTransactions" property="strPostDateTime" /></td>
              		<td width="3%"class="BodyText"><a href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_MAINTAIN_DO %>?strThreadId=<bean:write name="objTransactions" property="strThreadID" />'><bean:write name="objTransactions" property="strThreadID" /></td>
              		<td width="25%" class="BodyText"><bean:write name="objTransactions" property="strPostMessage" filter="false" /></td>
              		<td width="25%" class="BodyText"><bean:write name="objTransactions" property="strModMessage" filter="false" /></td>
            	</tr>
            </logic:iterate>
            </logic:greaterThan>
            <logic:lessThan name="arraySize" scope="page" value="1">
            	<tr align="left" valign="top" class="BodyText">
            		<td colspan="8" class="BodyText"><div align="center">No Result</div></td>
            	</tr>
            </logic:lessThan>
          </table></td>
        </tr>
        </logic:present>
		
		<tr height="20" >
		<td colspan="7" ></td>
		</tr>

			</table>
 
 			</td>
		</tr>
	</table>
 </html:form>
 