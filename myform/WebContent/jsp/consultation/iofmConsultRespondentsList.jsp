<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.consultation.OIBVFeedBack" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>

<%
ArrayList alList = new ArrayList();
OIBVFeedBack aOIBVFeedBack = new OIBVFeedBack();
String strTitle = "title";
int i = 0;

if(request.getAttribute("arOIBAConsultNameEmail")!= null){
	alList = (ArrayList)request.getAttribute("arOIBAConsultNameEmail");
}

if(request.getAttribute("paperTitle") != null){
	strTitle = (String)request.getAttribute("paperTitle");
}

%>

<html>
<head>
		<title>My Forum, Ministry Of Education</title>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
</head>

<script language="javascript" >

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
        <td valign="bottom" class="Table_head"><div align="left">List of Respondents</div></td>                               
      </tr>
    </table>
    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
         <td colspan="7">
         <table width="100%"  border="0" cellspacing="0" cellpadding="0">
            <tr>
               <td vAlign=top><font size='2' >Paper Title : <%=strTitle%></font></td>                
            </tr>
         </table>
		 </td>
    </tr>
	</table>
	<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" valign="top" class="Boxoutline">
		<table width="100%"  border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2">
            <table width="100%"  border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="25" class="Table_head">List of Respondents</td>                
              </tr>
            </table>
			</td>
            </tr>
          <tr>
            <td class="Sub_head">Name</td>
            <td class="Sub_head" align="center">Email</td>
          </tr>
		  <tr>
			<td colspan="2">
				<TABLE class=Box cellSpacing=0 cellPadding=0 border=0 width="100%"> 
				<% if(!(alList.size()<= 0))
					{
					for (i = 0; i < alList.size() ; i++){
						aOIBVFeedBack = (OIBVFeedBack)alList.get(i);
						String strName = aOIBVFeedBack.getUserName();
						String strEmail =  aOIBVFeedBack.getEmail();
						strName = (strName==null)?"":strName;
						strEmail = (strEmail==null)?"":strEmail;
				%>
					<tr bgcolor="white">
						<TD class=body_detail_text vAlign=top><%=strName %></td>
						<td class=body_detail_text vAlign=top align="center"><%=strEmail %></td>
					</tr>
				<% 		} 
					} else {%>
					<tr bgcolor="white">
						<TD class=body_detail_text vAlign=top colspan="2">No Record</td>
					</tr>
				<%	} %>
				</table>
			</td>
		 </tr>
		 <tr>
		 	<td class="Sub_head" align="right">Total</td>
		 	<td class="Sub_head" align="center"><%=i%></td>
		 </tr>
		 </table>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left">
					<a href='<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId" />&paperTitle=<%=strTitle%>&hiddenAction=respondents&export=yes'>
					<img src='<bean:message key="OIFM.docroot" />/images/btn_export.gif' alt="Export the Survey" border="0" ></a>
					&nbsp;&nbsp;
					<a href="#" onClick="window.print()"><img src='<bean:message key="OIFM.docroot"/>/images/btn_print.gif' border="0" alt = "Print Page"></a>
					</td>
				</tr>
				</table>
		  </td>
      </tr>
   </table>
    </td>
  </tr>
</table>

</html>
