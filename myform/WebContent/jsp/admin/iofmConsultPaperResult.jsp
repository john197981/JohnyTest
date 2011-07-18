<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
<script language="javascript">
	/*function submitConsultListForm1(submitUrl, actionName) {
		 	var frm = document.ConsultPageForm;
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
			return;
		}*/
		
	/*function submitConsultListForm2(submitUrl,paperId,actionName)
	{
		document.ConsultPageForm.paperId.value=paperId;
		document.ConsultPageForm.hiddenAction.value=actionName;
		document.ConsultPageForm.action='<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		document.ConsultPageForm.submit();
	}*/

	function fnSubmit3(actionName,paperId,hiddenAction)
	{
		document.ConsultPageForm.paperId.value=paperId;
		document.ConsultPageForm.hiddenAction.value=hiddenAction;
		document.ConsultPageForm.action=actionName;
		document.ConsultPageForm.submit();
	}
</script>

<form name="ConsultPageForm" method="post">
	<input type="hidden" name="categoryId">
	<input type="hidden" name="paperId">
	<input type="hidden" name="hiddenAction">
	<input type="hidden" name="Id">
	<input type="hidden" name="module">
	<input type="hidden" name="flag">
	<input type="hidden" name="title" value='<bean:write name="ConsultPageForm" property="title" />'>
</form>


<logic:present name="error" scope="request">
	<br><br>
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable" align="center">
		<tr>
    		<td width="75%" align="center" valign="top" class="Mainheader">
				<bean:write name="error" scope="request" />
			</td>
		</tr>
	</table>    
</logic:present>

<logic:present name="message" scope="request">
	<br><br>
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable" align="center">
		<logic:iterate id="mList" name="message" scope="request">
			<tr>
    			<td width="75%" align="center" valign="top" class="Mainheader">
					<bean:write name="mList"/>
				</td>
			</tr>
		</logic:iterate>
	</table>    
</logic:present>

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
						<td colspan="3">Consultation Paper</td>
 					</tr>
 					<tr>
						<td colspan="3">
							<div align="left">
								<img src='<bean:message key="OIFM.docroot" />/images/tab_cp1.gif' height="27" border="0" usemap="#MapMap">
									<map name="MapMap">
										<area shape="rect" coords="7,0,150,32" href="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','<bean:write name="ConsultPageForm" property="paperId" />','populate');" >
										<area shape="rect" coords="170,3,230,27" href="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','<bean:write name="ConsultPageForm" property="paperId" />','result');" >
									</map>
							</div>
						</td>
 					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"><a class="special_body_link" href="#" 
							onClick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId"/>&hiddenAction=export');" >Export raw data to excel</a>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3">
							<a class="special_body_link" href="#" 
							onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId" />&hiddenAction=summary');">Profile of Respondents</a>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3">
						<a class="special_body_link" href="#" 
							onclick="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','<bean:write name="ConsultPageForm" property="paperId" />','detail');">Responses by Demographics</a>
						</td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3"></td>
					</tr>
					<tr>
						<td width="100%" class="body_detail_text" colspan="3">
							<a class="special_body_link" href="#" 
							onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId" />&hiddenAction=respondents');">List of Respondents</a>
						</td>
					</tr>
					<tr>
						<td width="100%" height="60" colspan="3"></td>
					</tr>
				</table>
			</td>
		</tr>
		</table>
	</td>
	</tr>
</table>
