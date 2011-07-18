<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.useradmin.OIRankingConstants" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.ResourceBundle" %>
<%@ page import="java.util.Locale" %>
<html:html>
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript">
<%
	double iSize = 0;
	if(request.getAttribute("totalCount")!= null){
		iSize = Double.parseDouble((String)request.getAttribute("totalCount"));
	};
	Locale currentLocale = Locale.getDefault();	
	ResourceBundle myResources = ResourceBundle.getBundle("OIProperties", currentLocale);
	int iRecords = Integer.parseInt(myResources.getString("RANKING.RECORDS")); 
	String strMsg1 = myResources.getString("RANKING.MSG1");
	String strMsg2 = myResources.getString("RANKING.MSG2");
	
%>
function fnSubmit(actionName) 
	{
		document.WebRankingForm.target = "";
		document.WebRankingForm.hidAction.value ="";
		document.WebRankingForm.action= '<bean:message key="OIFM.contextroot" />'+actionName+'?id=<%= Math.random() %>';
		if(trim(document.WebRankingForm.title).value.length> 0){
			if(checkNumber(document.WebRankingForm.title,"Top N Users")== false)
			{return;}
			document.WebRankingForm.method="post";		
			document.WebRankingForm.submit();
		}else{
			document.WebRankingForm.method="post";		
			document.WebRankingForm.submit();
		}
	}
	
	function fnExport(actionName){
		document.WebRankingForm.target = "";
		document.WebRankingForm.reset();
		var frm = document.WebRankingForm;	
		document.WebRankingForm.hidAction.value = "WebRankExport";
		frm.action= '<bean:message key="OIFM.contextroot" />'+actionName+'?id=<%= Math.random() %>';
		frm.submit();
		return;	 		
 	}

	function fnDetails(actionName,count){

	var total = parseInt(document.WebRankingForm.hidTotal.value);
	document.WebRankingForm.reset();
	var frm = document.WebRankingForm;
	frm.target="help_window";
	document.WebRankingForm.hidAction.value = "WebRankDetails";
	if(!(help_window && help_window.open && !help_window.closed))
	{
		help_window = window.open("", "help_window", "width=680,height=500,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5, left=50" );
	}
	frm.action= '<bean:message key="OIFM.contextroot" />'+actionName+'?id=<%= Math.random() %>';
	document.WebRankingForm.winObj.value =  help_window;	
	if(total == 1){
		document.WebRankingForm.hidType.value = document.WebRankingForm.strHidTypeList.value;
		document.WebRankingForm.hidUserId.value = document.WebRankingForm.useridTemp.value;		
		document.WebRankingForm.hidUserName.value = document.WebRankingForm.userNameTemp.value;		
	}else{
		document.WebRankingForm.hidType.value = document.WebRankingForm.strHidTypeList[count].value;
		document.WebRankingForm.hidUserId.value = document.WebRankingForm.useridTemp[count].value;	
		document.WebRankingForm.hidUserName.value = document.WebRankingForm.userNameTemp[count].value;		
	}
	frm.submit();
	help_window.focus();
	return;	
}
function clearLog(actionName){
		document.WebRankingForm.target = "";
		document.WebRankingForm.reset();
		var resultVal1 = confirm("<%=strMsg1%>");
		if(resultVal1 == true){
		var resultVal2 = confirm("<%=strMsg2%>");
			if(resultVal2 == true){
				document.WebRankingForm.hidAction.value ="ClearLog";
				document.WebRankingForm.action= '<bean:message key="OIFM.contextroot" />'+actionName+'?id=<%= Math.random() %>';
				document.WebRankingForm.submit();
			}
		}
	}
</script>
<html:form action="/WebRanking.do" method="post">
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
<table width="98%" border="0" cellpadding="0"	cellspacing="0">
<tr>
<td class="TableHead">
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr>
<td class="Box">
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="85%" align="center" valign="top">
    <table width="100%"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td valign="bottom" class="Table_head"><div align="left">User Ranking </div></td>
      </tr>
    </table>    
      <br>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td class="body_extract_text">Top N User</TD>
		<td >
		<html:text  name="WebRankingForm" property="title" styleClass="Text_box" size="10" maxlength="10" /></TD>
	</tr>
	<tr>
	<td class="body_extract_text">Type</TD>
	<td >
	<html:select name="WebRankingForm" property="typeList"  size="1" styleClass="Text_box"> 		  
		 <html:option value=""></html:option>
		<html:options collection="typeList" property="value" labelProperty="label" /> 	 
	</html:select> </TD>
</tr>
<tr>
	<td class="body_extract_text">Date Range</TD>
	<td class="body_extract_text">
	From &nbsp;<html:text  name="WebRankingForm" property="fromDate" styleClass="Text_box" size="12" maxlength="11" readonly = "true"/><a href="#" onClick="cal.select(document.forms['WebRankingForm'].fromDate,'fromDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor"><img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt = "Calendar"></a>
	&nbsp;To &nbsp;<html:text  name="WebRankingForm" property="toDate" styleClass="Text_box" size="12" maxlength="11" readonly = "true" />
	<a href="#" onClick="cal.select(document.forms['WebRankingForm'].toDate,'toDate','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
	<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt = "Calendar"></a></TD>
</tr>
<tr><td colspan = 2>&nbsp;</td></tr>
<tr>
	<td align="left" colspan = 2><p>
	<a href="javascript:fnSubmit('/WebRanking.do')">
	<img src="<bean:message key="OIFM.docroot"/>/images/but_submit.gif" border="0" alt="Submit"></a>
	</td>
	
</tr>
</table>
<br>
<%
int count = 0; %>
<logic:present name="Result" scope="request">
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" valign="top" class="Boxoutline"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="7">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="25" class="Table_head">The following are most active users in the website</td>                
              </tr>
            </table></td>
            </tr>
          <tr>
            <td width="3%" class="Sub_head">&nbsp;</td>
            <td width="20%" class="Sub_head">Name</td>
            <td width="18%" class="Sub_head">Designation</td>
            <td width="8%" class="Sub_head">Age</td>
            <td width="20%" class="Sub_head">School Level</td>
            <td width="16%" class="Sub_head">Yrs in Svc</td>
            <td width="15%" class="Sub_head">No. of Hits</td>
          </tr>
          <logic:iterate id="objRanking" name="Result" indexId="idx" type="com.oifm.useradmin.OIRankingBean">
          	<tr>
            	<td class="body_extract_text"><bean:write name="objRanking" property="strRowNum" /></td>
            	<td class="body_extract_text"><bean:write name="objRanking" property="strName" /></td>
            	<td class="body_extract_text"><bean:write name="objRanking" property="strDesignation" /></td>
            	<td class="body_extract_text"><bean:write name="objRanking" property="strAge" /></td>
            	<td class="body_extract_text"><bean:write name="objRanking" property="strSchoolLevel" /></td>
            	<td class="body_extract_text"><bean:write name="objRanking" property="strYIS" />
            	<td class="body_extract_text"><a href="#" onclick="javascript:fnDetails('/WebRanking.do','<%=count%>');">
            	<bean:write name="objRanking" property="strHits" /></a></td>
            	<input type = "hidden" name = "useridTemp" value = '<bean:write name="objRanking" property="strUserID"/>'>
            	<input type = "hidden" name = "strHidTypeList" value = '<bean:write name="objRanking" property="strHidTypeList"/>'>
            	<input type = "hidden" name = "userNameTemp" value = '<bean:write name="objRanking" property="strName"/>'>
          	</tr>
          <% count++; %>
          </logic:iterate>
        <tr><td>&nbsp;</td></tr></table></td>
      </tr>
   </table>
   <table width="100%" height="35"  border="0" cellpadding="0" cellspacing="0">
   <tr>
    <% if(iSize >= iRecords){%>
        <td height="25" align = "right">
        <a href="#" class="special_body_link" onclick="clearLog('/WebRanking.do')">The number of records in the log are <%=iRecords%>. Click here to clear the log</a></td>
    <% }%>
	</tr></table>            
     <table width="100%" height="35"  border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left">
        <p><a href="<bean:message key="OIFM.contextroot"/>/Ranking.do"><img src="<bean:message key="OIFM.docroot"/>/images/but_ok.gif" border="0" alt="OK"></a> 
        <a href="#" onclick="window.print()"><img src="<bean:message key="OIFM.docroot"/>/images/but_print.gif" border="0" alt="Print"></a>
        <a href="javascript:fnExport('/WebRanking.do')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_export.gif" border="0" alt="Export"></a></p>
          </td>
      </tr>
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
</logic:present>
<logic:notPresent name="Result" scope="request">
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
     <table width="100%" height="35"  border="0" cellpadding="0" cellspacing="0">
      <tr> <td height="25">&nbsp;</td>
	  </tr>
    </table>
</table>
</logic:notPresent>
<html:hidden name="WebRankingForm" property="hidUserId" />
<html:hidden name="WebRankingForm" property="hidUserName" />
<html:hidden name="WebRankingForm" property="hidType" />
 <input type="hidden" name="hidTotal" value = "<%=count%>" />
 <input type="hidden" name="winObj" value="">
 <html:hidden name="WebRankingForm" property="hidAction" />
</html:form>
<DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>
</html:html>
