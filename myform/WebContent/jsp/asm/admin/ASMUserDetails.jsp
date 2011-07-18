<%--
/**
 * FileName			: ASMPrevRep.jsp
 * Author      		: Anbalagan
 * Created Date 	: 09/12/2005
 * Description 		: This page used to display the ASM home page.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="com.oifm.asm.ASMBVCommon,com.oifm.utility.OIUtilities"%>
   
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmReplyRedirectEdit.js'></script>

<title>Select User</title>
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

<%try{%>
<html:form action="ASMUserDetails.do" method="post" >

<table width="95%"  border="0" cellpadding="0" cellspacing="0" class="boxtable">
	<tr class="Titleheade1">
	<td colspan="3">Select Users</td>
	</tr>

	<logic:equal name="hiddenAction" value="DivisionUserList">
		<tr class="BodyText" height="25">
		<td nowrap colspan="3">Division &nbsp;&nbsp;
		<html:select property="cboDivision" size="1" styleClass="Text" onchange="fnChangeDivision()"> 	
  			  <html:option value="">Select a Division</html:option>
			  <html:options collection="DivisionList" property="strDDLabelId" labelProperty="strDDLabelDesc"/> 
		</html:select>		 

		</td>
		</tr>
	</logic:equal>

	<logic:notEqual name="TotRec" value="0">
	<tr class="SubHead1">
	<td width="20%"><html:checkbox property="chkSelectAll" onclick="fnSelectAll()"/>Select All </td>
	<td width="30%">User Name</td>
	<td width="50%">Email ID</td>
	</tr>

	<logic:iterate id="objBV" name="UserList" type="com.oifm.asm.ASMBVCommon" scope="request" indexId="rowNum" >
		<tr class="BodyText">
		<td><html:checkbox property="chkSelect" value="false"/></td>
		<td><bean:write name="objBV" property="hidName"/></td>
		<td><bean:write name="objBV" property="hidEmailID"/>
		<!-- For senior management assign the User name in the hidEmail-->
		<logic:equal name="hiddenAction" value="SeniorManagementUserList">
			<html:hidden property="hidEmailID" value="<%=objBV.getHidName()%>" />
		</logic:equal>
		<!-- For Others,assign the Email Address in the hidEmail-->
		<logic:notEqual name="hiddenAction" value="SeniorManagementUserList">
			<html:hidden property="hidEmailID" value="<%=objBV.getHidEmailID()%>" />
		</logic:notEqual>

		</td>
		</tr>
	</logic:iterate>
	</logic:notEqual>
</table>
		<!--pagination start  -->
		<logic:equal name="TotRec" value="0">

			<br><br>
				<p align="center"><strong>
				<logic:equal name="hiddenAction" value="DivisionUserList">
					<logic:notEqual name="cboDivison" value="">
						No users available for the selected division.
					</logic:notEqual>
				</logic:equal>
				<logic:equal name="hiddenAction" value="AllUserList">
					No users available.
				</logic:equal>

				</strong></p>
		</logic:equal>

		<logic:greaterThan name="totalPage" value="1">

		<logic:notEqual name="TotRec" value="0">
		<br>
			<table border="0" cellpadding="2" cellspacing="0" class="BodyText" align="right">
				<tr>
					<td nowrap class="Boxinside_text"> 
						Page 
							<bean:write name="pageNo" scope="request" /> 
						of 
							<bean:write name="totalPage" scope="request" />
					</td>
					<logic:present name="previousSet" scope="request">
						<logic:equal name="previousSet" scope="request" value="true">
							<td nowrap class="BD_2">&lt;</td>
							<td nowrap class="BD_3"> 
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="previousPage" scope="request"/>');">
									&laquo;Previous</a>
							</td>
						</logic:equal>
					</logic:present>


					<!--<td nowrap class="BD_1">1</td>-->
					<logic:present name="arPage" scope="request">
						<logic:iterate id="no" name="arPage" scope="request">
							<%
								String currentPage=(String) request.getAttribute("pageNo");
								String temp = (String) no;
								if (! currentPage.trim().equals(temp.trim()))
								{
							%>
							<td nowrap class="BD_2">
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="no" />');">		
									<bean:write name="no" /></a>
							</td>
							<%
								}
								else
								{
							%>
							<td nowrap class="BD_1">
									<bean:write name="no" />
							</td>
							<%
								}
							%>
						</logic:iterate>
					</logic:present>
					<logic:present name="nextSet" scope="request">
						<logic:equal name="nextSet" scope="request" value="true">
							<td nowrap class="BD_2">&gt;</td>
							<td nowrap class="BD_3"> 
								<a href='#' onclick="javascript:fnSubmitPage('<bean:write name="nextPage" scope="request"/>');">
									Next&raquo;</a>
							</td>
						</logic:equal>
					</logic:present>

		</tr>
			</table>
			<br><br>
			</logic:notEqual>
			</logic:greaterThan>
		<!-- Pagination end-->
<br>
<logic:notEqual name="TotRec" value="0">
<p align="center">
<a href="#" style="cursor:hand" onclick="fnAddUser()"><img src='<bean:message key="OIFM.docroot" />/images/btn_AddUsers.gif' border="0" alt="Add Users"></a> &nbsp;
<a href="#" style="cursor:hand" onclick="fnClosewindow()"><img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif' border="0" alt="Close"></a> 
</p>
</logic:notEqual>

<!-- This hidden variable holds the letter id-->
<html:hidden property="hiddenAction"/>
<!-- -->
<html:hidden property="hidLink" />

<!-- -->
</html:form> 
<%}catch(Exception e){out.println(e);}%>

<script>
var totalRec = <bean:write name="TotRec" scope="request" />
/*
if(opener.objName=="TO"){
	document.ASMFormCommon.cboDivision.options.length=1
	document.ASMFormCommon.cboDivision.options[0].value =  window.opener.document.ASMFormReplyRedirectEdit.cboDivision.value
	document.ASMFormCommon.cboDivision.options[0].text = window.opener.document.ASMFormReplyRedirectEdit.cboDivision.options[window.opener.document.ASMFormReplyRedirectEdit.cboDivision.selectedIndex].text
}
*/

</script>

