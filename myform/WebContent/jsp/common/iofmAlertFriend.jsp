<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<html>
	<title>
	<%if(request.getParameter("module")!=null && (request.getParameter("module").equals("ASM") || 
	request.getParameter("module").equals("ASMDRAFT"))){%>
		Email Friend 
	<%}else{%>
		Alert a Friend 
	<%}%>
	</title>

	<head>
	<base target=_self>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
		<style type="text/css">
			<!--
			.style6 {color: #000000}
			.style9 {font-family: verdana}
			.style10 {font-size: 12px}
			-->
		</style>
		<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
		<script language="javascript">
			strDocRoot = '<bean:message key="OIFM.contextroot"/>'
			function fnSubmit(actionName,ac)
			{
				if (Trim(document.AlertFriendForm.to.value)=="")
				{
					alert('<%= OIDBRegistry.getValues("OI.COMMON.ALERTFRIEND.EMAIL.EMPTY") %>');
					document.AlertFriendForm.to.focus();
				}
				else
				{
					document.AlertFriendForm.action=actionName;
					document.AlertFriendForm.hiddenAction.value=ac;
					document.AlertFriendForm.submit();
				}
			}
			function fnSubmit1(actionName,ac)
			{
				document.AlertFriendForm.action=actionName;
				document.AlertFriendForm.hiddenAction.value=ac;
				document.AlertFriendForm.submit();
			}
			function fnClear()
			{
				document.AlertFriendForm.reset();
			}

		/** This function is used to load select users window */
			function fnSelectUsers(){
		 		 var strUrl = '/SelectUsers.do?hiddenAction=populate&hidPage=addemails'
				 var help_win  = window.open(strDocRoot+strUrl,'SelectUsers','width=920,height=550,left=0,top=0,resizable=yes,scrollbars=yes');
  				 help_win.focus();
			}
		</script>
	</head>

	<body>
		<html:form action="/AlertFriendAction.do">
		<br>
		<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			<tr>
				<td height="25" class="Table_head">
				<%if(request.getParameter("module")!=null && (request.getParameter("module").equals("ASM") || 
				request.getParameter("module").equals("ASMDRAFT"))){%>
					Email a Friend 
				<%}else{%>
					Alert to a Friend 
				<%}%>
				</td>
			</tr>

					
		<logic:notPresent name="mailerror" scope="request">
			<tr>
				<td class="box">
					<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
						<tr align="left" valign="top"> 
							<td width="7%" height="21" bgcolor="#F0F8FF" class="Table_Sub_head"> 
								<strong>From</strong>
							</td>
							<td   class="Heading_Thread" bgcolor="#FFFFFF" colspan="2">
								<bean:write name="AlertFriendForm" property="emailId" />
								<html:hidden name="AlertFriendForm" property="emailId" />
							</td>
						</tr>
						<tr align="left" valign="top"> 
							<td height="29"  bgcolor="#F0F8FF" class="Table_Sub_head" width="7%"><strong>To</strong></td>
							<td width="29%" bgcolor="#FFFFFF" height="29"> 
							<html:text name="AlertFriendForm" property="to" size="80"></html:text>
							<br>
							<font size="1" name="arial">
								Separate multiple addresses with commas(,)
								</font>
							</td>
						 <!--	<td width="64%" class="BodyText" height="29">
								<a href="#" onclick="javascript:fnSelectUsers()" >
									<img src='<bean:message key="OIFM.docroot" />/images/button/btn_Select-Users.gif' width="93" height="19" border="0">
								</a>
							</td>
							-->
						</tr>
						<tr align="left" valign="top"> 
							<td height="21"  bgcolor="#F0F8FF" class="Table_Sub_head" width="7%">
								<strong>Subject</strong>
							</td>
							<td  bgcolor="#FFFFFF" colspan="2" class = "Heading_Thread">
								<%if(request.getParameter("module")!=null && (request.getParameter("module").equals("ASM") || 
								request.getParameter("module").equals("ASMDRAFT"))){%>
									<html:text name="AlertFriendForm" property="subject" size="80" />
								<%}else{%>
									<bean:write name="AlertFriendForm" property="subject" filter="false" /> 
									<html:hidden name="AlertFriendForm" property="subject" />
								<%}%>
								<html:hidden name="AlertFriendForm" property="url" />
							</td>
						</tr>
						<tr align="left" valign="top"> 
							<td height="73"  bgcolor="#F0F8FF" class="Table_Sub_head" width="7%"><strong>Content</strong></td>
							<td  bgcolor="#FFFFFF" colspan="2" class = "body_detail_text"> 
								<%if(request.getParameter("module")!=null && (request.getParameter("module").equals("ASM") || 
								request.getParameter("module").equals("ASMDRAFT"))){%>
									<html:textarea name="AlertFriendForm" property="content" cols="60" rows="5"/>
									<br><a href="#" onClick="javascript:fnClear();"  class="Heading_Attr_A">Undo all edits</a>
								<%}else{%>
									<bean:write name="AlertFriendForm" property="content" filter="false" />
									<html:hidden name="AlertFriendForm" property="content" />
								<%}%>
							</td>
						</tr>
						<tr align="left" valign="top"> 
							<td bgcolor="#FFFFFF"  class="Table_head"  width="7%">
							</td>
							<td  bgcolor="#FFFFFF"  class="Table_head" colspan="2"> 
								
								<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/AlertFriendAction.do','sendMail');">
									<img src='<bean:message key="OIFM.docroot" />/images/but_send.gif' border="0" alt="Send"></a> 
								<a href="#" onclick="javascript:self.close();">
									<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt="Close"></a> 
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<html:hidden name="AlertFriendForm" property="hiddenAction"/>
				<input type="hidden" name="module" value="<%= (String) request.getAttribute("module") %>" />
		</logic:notPresent>
		</table>
		</html:form>
	</body>
</html>
