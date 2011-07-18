<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>

<%
/*response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition", "attachment;filename='SummaryReport'");
*/

ArrayList alList = new ArrayList();
int i = 0;
String strQuestion = "";

if(request.getAttribute("OpenEndQuestion")!= null){
	alList = (ArrayList)request.getAttribute("OpenEndQuestion");
	strQuestion = (String)request.getAttribute("strQuestion");
}


%>
<html>
<head>
		<title>My Forum, Ministry Of Education - Open End Question</title>

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

<table width="500" border="0" cellpadding="0"	cellspacing="0">
<tr>
	<td class="Boxoutline">
<table width="500" border="0" cellpadding="0"	cellspacing="0">
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr>
	<td colspan="2" class="Sub_head"><%=strQuestion%></td>
</tr>
<tr>
	<td colspan="2" align="left" valign="top" class="Boxoutline">
		<table border="1" cellpadding="0"	cellspacing="0" width="500">
			<tr>
				<td width="50" class="Table_head">S/N</td>
				<td class="Table_head">Answer</td>
			</tr>
			<% 
				for(i=0; i<alList.size(); i++)
				{
					if(alList.get(i)!= null)
					{

			%>
			<tr>
				<td class=body_detail_text vAlign=top><%=i+1%></td>
				<td class=body_detail_text vAlign=top><%=(String)alList.get(i)%></td>
			</tr>
			<%
					}
				}
			%>
		</table>
	</td>
</tr>
<tr>
	<td colspan="2"><a href="#" onClick="window.close()" >
	<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a></td>
</tr>
</table>
	</td>
</tr>
</table>


</html>