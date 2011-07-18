<%--
/**
 * FileName			: ASMExcelReport.jsp
 * Author      		: Anbalagan
 * Created Date 	: 03/01/2006
 * Description 		: This page used to display the ASM Report in Excel sheet.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%@ page language="java" contentType="text/html"%>
   
<html>
<%try{%>
<%response.setContentType("application/vnd.ms-excel");
%>

<logic:notEqual name="TotRecExcel" value="0">

<table border="1" class="boxtable" >
<tr class="subhead1">
<td valign="top" align="left"><strong>S/N</strong>
</td>
<td valign="top" align="left"><strong>Title</strong>
</td>
<%if(request.getParameter("chkSubmitOn")!=null){%>
<td valign="top" align="left"><strong>Submitted On</strong>
</td>
<%}if(request.getParameter("chkWrittenBy")!=null){%>
<td valign="top" align="left"><strong>Written By</strong>
</td>
<%}if(request.getParameter("chkDesigLW")!=null){%>
<td valign="top" align="left"><strong>Designation</strong>
</td>
<%}if(request.getParameter("chkDivisionLW")!=null){%>
<td valign="top" align="left"><strong>Division</strong>
</td>
<%}if(request.getParameter("chkLetterContent")!=null){%>
<td valign="top" align="left"><strong>Letter Content</strong>
</td>
<%}if(request.getParameter("chkYIS")!=null){%>
<td valign="top" align="left"><strong>YIS</strong>
</td>
<%}if(request.getParameter("chkAge")!=null){%>
<td valign="top" align="left"><strong>Age</strong>
</td>
<%}if(request.getParameter("chkDivInCharge")!=null){%>
<td valign="top" align="left"><strong>Div-in-charge</strong>
</td>
<%}if(request.getParameter("chkCategory")!=null){%>
<td valign="top" align="left"><strong>Category</strong>
</td>
<%}if(request.getParameter("chkRedirectTo")!=null){%>
<td valign="top" align="left"><strong>Redirect To</strong>
</td>
<%}if(request.getParameter("chkRedirectOn")!=null){%>
<td valign="top" align="left"><strong>Redirect On</strong>
</td>
<%}if(request.getParameter("chkRepliedBy")!=null){%>
<td valign="top" align="left"><strong>Replied By</strong>
</td>
<%}if(request.getParameter("chkRepliedOn")!=null){%>
<td valign="top" align="left"><strong>Replied On</strong>
</td>
<%}if(request.getParameter("chkReplyContent")!=null){%>
<td valign="top" align="left"><strong>Reply Content</strong>
</td>
<%}%>
</tr>
<logic:iterate id="objBV" name="search_results_excel" type="com.oifm.asm.ASMBVReport" scope="request" indexId="rowNum" >
<tr class="BodyText">
	<td valign="top" align="left">
	<%= rowNum.intValue()+1 %></td>
	<td valign="top" align="left"><bean:write name="objBV" property="hidLetterTopic"/></td>
	<%if(request.getParameter("chkSubmitOn")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtSubOnFromDate"/></td>
	<%}if(request.getParameter("chkWrittenBy")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtWrittenBy"/></td>
	<%}if(request.getParameter("chkDesigLW")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtDesigLW"/></td>
	<%}if(request.getParameter("chkDivisionLW")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtDivisionLW"/></td>
	<%}if(request.getParameter("chkLetterContent")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtLetterContent" filter="false"/></td>
	<%}if(request.getParameter("chkYIS")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtYISFromDate"/></td>
	<%}if(request.getParameter("chkAge")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtAgeFromDate"/></td>
	<%}if(request.getParameter("chkDivInCharge")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="cboDivInCharge"/></td>
	<%}if(request.getParameter("chkCategory")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="cboCategory"/></td>
	<%}if(request.getParameter("chkRedirectTo")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtRedirectTo"/></td>
	<%}if(request.getParameter("chkRedirectOn")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtRedirectFromDate"/></td>
	<%}if(request.getParameter("chkRepliedBy")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtRepliedBy"/></td>
	<%}if(request.getParameter("chkRepliedOn")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtReplyFromDate"/></td>
	<%}if(request.getParameter("chkReplyContent")!=null){%>
	<td valign="top" align="left"><bean:write name="objBV" property="txtReplycontent" filter="false"/></td>
	<%}%>
</tr>
</logic:iterate>

</table>

</logic:notEqual>


</html>
<%}catch(Exception e){//out.println(e);

}%>
