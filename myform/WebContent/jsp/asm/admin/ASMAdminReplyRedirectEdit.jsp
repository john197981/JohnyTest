<%--
/**
 * FileName			: ASMAdminReplyRedirectEdit.jsp
 * Author      		: Anbalagan / Rakesh
 * Created Date 	: 20/12/2005
 * Modified Date	: 24/01/2008 {$"Added New Options 'Category' and 'Delete'"}
 * Description 		: This page used to display the ASM Reply Redirect Edit Page.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page language="java" import="com.oifm.asm.ASMBVReplyRedirect,com.oifm.utility.OIUtilities,java.text.SimpleDateFormat,java.util.Date"%>
   
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmReplyRedirectEdit.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>

<script>
var popUpWin;
var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 
	}
	var help_window;
	function fnModal()
	{ 
		if(!(!help_window ||  help_window.closed))
		{ 
			inside = 1;
			help_window.focus(); 
		}
	}

	function fnCallModal()
	{
		if(help_window)
		{
			window.setTimeout("fnModal()",1);
		}
	}

	function fnClosePopup()
	{
		if(help_window!=null)
		{
			help_window.close();
		}
	}

	var maxlimitMessage = 1000;
	function fn_textCounter(field, countfield,maxlimit) 
	{
		if (field.value.length > maxlimit) 
		{
			field.value = field.value.substring(0, maxlimit);
		}
		else
		{
			countfield.value = maxlimit - field.value.length;
		}
	}


</script>
<%try{%>
<html:form action="ASMReplyRedirectEdit.do" method="post" >
<body onfocus="fnModalWindow()" onload="fnRadioRemainder();">
        
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
 	<table width="98%" border="0" cellpadding="1"
		cellspacing="1">
		<tr>
			<td class="TableHead">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				<td class="Box">
					<table width="100%" border="0" cellpadding="1"
						cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="3">Reply/Redirect letters</td>
 					</tr>
					<tr >
						<td colspan="3" bgcolor="#F0F8FF" class="Sub_head"><bean:write name="letter_title" scope="request" /></td>
 					</tr>
  						    <tr class="Heading_Thread" height="25">
						    <td width="22%" nowrap>Division</td>
						    <td>
								<html:select property="cboDivision" size="1" styleClass="Text" onchange="fnDivision()"> 	
									 <html:option value="">Select a Division</html:option>
									 <html:options collection="DivisionList" property="strDDLabelId" labelProperty="strDDLabelDesc"/> 
								</html:select>		 

						    <td></td>
						    </tr>

						    <tr class="Heading_Thread" height="25">
						    <td>To</td>
						    <td ><html:text property="txtTo" size="95" styleClass="text_box" maxlength="256"/></td>
						    <td align="left"><a href="#" style="cursor:hand" onclick="fnSelectUser('TO')"><img src='<bean:message key="OIFM.docroot" />/images/lookup.jpg' border="0"></a></td>

						    </tr>

						    <tr class="Heading_Thread" height="25">
						    <td>Cc</td>
						    <td ><html:text property="txtCc" size="95" styleClass="text_box" maxlength="256"/></td>
						    <td align="left"><a href="#" style="cursor:hand" onclick="fnSelectUser('CC')"><img src='<bean:message key="OIFM.docroot" />/images/lookup.jpg' border="0"></a></td>
						    </tr>

						    <tr class="Heading_Thread" height="25">
						    <td>Bcc</td>
						    <td><html:text property="txtBcc" size="95" styleClass="text_box" maxlength="256"/></td>
						    <td align="left"><a href="#" style="cursor:hand" onclick="fnSelectUser('BCC')"><img src='<bean:message key="OIFM.docroot" />/images/lookup.jpg' border="0"></a></td>
						    </tr>

						    <tr class="Heading_Thread" height="25">
						    <td>Subject</td>
						    <td><html:text property="txtSubject" size="95" styleClass="text_box" maxlength="256"/></td>
						    <td></td>
						    </tr>
						    
						    <tr class="Heading_Thread" height="25">
						    <td width="22%" nowrap>Category</td>
						    <td>
								<html:select property="cboCategory" size="1" styleClass="Text"> 	
									 <html:option value="">Select a Category</html:option>
									 <html:options collection="CategoryList" property="strDDLabelId" labelProperty="strDDLabelDesc"/> 
								</html:select>		 

						    <td></td>
						    </tr>

						    <tr class="Heading_Thread">
						    <td valign="top">Message</td>
						    <td><html:textarea property="txtMessage" cols="60" rows="7"  onkeydown="fn_textCounter(document.ASMFormReplyRedirectEdit.txtMessage,document.ASMFormReplyRedirectEdit.numleft,maxlimitMessage);" onkeyup="fn_textCounter(document.ASMFormReplyRedirectEdit.txtMessage,document.ASMFormReplyRedirectEdit.numleft,maxlimitMessage);" onmouseover="fn_textCounter(document.ASMFormReplyRedirectEdit.txtMessage,document.ASMFormReplyRedirectEdit.numleft,maxlimitMessage);" onmouseout="fn_textCounter(document.ASMFormReplyRedirectEdit.txtMessage,document.ASMFormReplyRedirectEdit.numleft,maxlimitMessage);"></html:textarea></td>
						    <td>
							</td>
						    </tr>

						    <tr class="Heading_Thread">
						    <td> </td>
						    <td>
									<input name="numleft" type="text" size="5" size="5" maxlength="3" value="500" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">							
									</td>
						    <td align="left">
							<logic:present name="letter_status" scope="request">
							<logic:notEqual name="letter_status" scope="request" value="P">
								<a href="#" style="cursor:hand" onclick="fnRedirect()">
									<img src='<bean:message key="OIFM.docroot" />/images/btn_redirect.gif' border="0" ALT = "Redirect Letters" >
								</a>
							</logic:notEqual>
							</logic:present>
							</td>
						    </tr>

						    <tr class="Heading_Thread">
						    <td></td>
						    <td></td>
						    <td></td>
						    </tr>

						    <tr class="Heading_Thread" height="20">
						    <td>Submt By</td>
						    <td><bean:write name="posted_by" scope="request" /></td>
						    <td></td>
						    </tr>

						    <tr class="Heading_Thread" height="20">
						    <td>Submt On</td>
						    <td><bean:write name="posted_on" scope="request" /></td>
						    <td></td>
						    </tr>

						    <tr class="Heading_Thread" height="20">
						    <td>Email ID</td>
						    <td><bean:write name="email" scope="request" /></td>
						    <td></td>
						    </tr>

						    <tr class="Heading_Thread" height="25">
						    <td>Cont No</td>
						    <td>
							<html:text property="txtContactNo" size="17" styleClass="text_box" maxlength="15" />
							</td>
						    <td></td>
						    </tr>

						    <tr class="Heading_Thread" height="25">
						    <td >Title</td>
						    <td><html:text property="txtTopic" size="95" styleClass="text_box" maxlength="256"/></td>
						    <td></td>
						    </tr>

							<tr class="Heading_Thread" height="10"><td colspan="3"></td></tr>

						    <tr class="Heading_Thread">
						    <td valign="top"><br><br>Letter</td>
						    <td><html:textarea
								property="txtLetter" styleId="txtLetter" cols="60" rows="10" styleClass="Text"/>
							<script language="javascript">
								var config = new Object();
								config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
								config.debug = 0;
								editor_generate('txtLetter',config);
							</script></td>
						    <td></td>
						    </tr>
							
						    <tr class="Heading_Thread" height="35">
						    <td >Replied By</td>
						    <td><html:text property="txtRepliedBy" size="95" styleClass="text_box" maxlength="66"/></td>
						    <td align="left"><a href="#" style="cursor:hand" onclick="fnSelectUser('RepliedBy')"><img src='<bean:message key="OIFM.docroot" />/images/lookup.jpg' border="0"></a></td>
						    
						    </tr>
						    <tr class="Heading_Thread" HEIGHT="25">
						    <td >Replied On</td>
						    <td ><html:text property="txtRepliedOn" size="14" styleClass="text_box" readonly="true"/>
							<a href="#" onClick="cal.select(document.forms['ASMFormReplyRedirectEdit'].txtRepliedOn,'txtRepliedOn','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
								<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a>
								</td>
						    <td></td>
						    </tr>
						    <tr class="Heading_Thread" HEIGHT="10">
						    <td colspan="3"></td>
							</tr>


						    <tr class="Heading_Thread">
						    <td valign="top"><br><br>Reply</td>
						    <td><html:textarea
								property="txtReply" styleId="txtReply" cols="60" rows="10" styleClass="text_box"/>
							<script language="javascript">
								var config = new Object();
								config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
								config.debug = 0;
								editor_generate('txtReply',config);
							</script></td>

						    <td></td>
						    </tr>
							
							<tr class="Heading_Thread" height="25">
							<td colspan="3"></td>
							</tr>

						    <tr height="30" >
							
						    <td class="Heading_Thread"  valign="center"><div id ="12" >Reminder</div></td>
						    <td valign="center" nowrap>
							<table border="0">
							<tr class="body_detail_text">
							<td width="30%">
							<div id ="11" >
							<html:radio property="radRemainder" value="A" onclick="fnRadioRemainder()"></html:radio>Automatic
							<html:radio property="radRemainder" value="M" onclick="fnRadioRemainder()"></html:radio>Manual &nbsp;&nbsp;
							</div>
							</td><td width="25%">

							<div id ="10" >
							<a href="#" style="cursor:hand" onclick="fnSendReminder()"><img src='<bean:message key="OIFM.docroot" />/images/btn_send_reminder.gif' border="0" ALT = "Send Reminder"></a>
							</div>
							
							</td>
							<td width="*">
								<div id = "20" >System will automatically send a reminder message</div>
								<div id = "21" >Click on the button to manually send a reminder message</div>
							</td>
							</tr>
							</table>
							
							</td>
						    <td></td>
						    </tr>
							<%if(OIUtilities.replaceNull(""+request.getAttribute("letter_status")).equalsIgnoreCase("S")){%>
							
							<html:hidden property="chkPublishWebSite" value=""/>
							<html:hidden property="txtPublishOn" value=""/>
							<html:hidden property="txtShowFrom" value=""/>
							<html:hidden property="txtShowTo" value=""/>
							<%}else{%>

						    <tr  height="30">
						    <td colspan="2">
							<table width="60%"><tr class="body_detail_text" height="30"><td class="Heading_Thread" >
							<html:checkbox property="chkPublishWebSite" onclick="fnClickPublish()"/>
							Publish on Website&nbsp;&nbsp;&nbsp;
							</td><td class="Heading_Thread" >
							<div id="13">
							
						    Publish on <html:text property="txtPublishOn" size="14" styleClass="text_box" readonly="true"/>
							<a href="#" onClick="cal.select(document.forms['ASMFormReplyRedirectEdit'].txtPublishOn,'txtPublishOn','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
								<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a>
							</div>
							</td></tr></table>
								</td>
						    <td></td>
						    </tr>

						    <tr height="30">
						    <td colspan="2" class="Heading_Thread" >
							<div id="14">
							Show on website &nbsp;&nbsp;&nbsp;
						    From <html:text property="txtShowFrom" size="14" styleClass="text_box" readonly="true"/>&nbsp;&nbsp;
							<a href="#" onClick="cal.select(document.forms['ASMFormReplyRedirectEdit'].txtShowFrom,'txtShowFrom','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor"><img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a>
							To <html:text property="txtShowTo" size="14" styleClass="text_box" readonly="true"/>
							<a href="#" onClick="cal.select(document.forms['ASMFormReplyRedirectEdit'].txtShowTo,'txtShowTo','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor"><img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' border="0" alt="Calendar"></a>
							</div>
							</td>
						    <td></td>
						    </tr>
							<%}%>

						    <tr class="body_detail_text" height="70">
						    <td></td>
						    <td><a href="#" onclick="fnSave1()" style="cursor:hand"><img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" ALT = "Save"></a>&nbsp;<a href="#" onclick="fnReset()" style="cursor:hand"><img src='<bean:message key="OIFM.docroot" />/images/Undo_all_edits.gif' border="0" ALT = "Undo All Edits"></a>&nbsp;<a href="#" onclick="fnCancel()" style="cursor:hand"><img src='<bean:message key="OIFM.docroot" />/images/back_to_inbox.gif' border="0" ALT = "Back to Inbox"></a>&nbsp
						    <a href="#" onclick="fnDelete()" style="cursor:hand"><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" alt = "Delete"></a></td>
						   <td></td>
						    </tr>

											</table>
 									</td>
								</tr>
							</table>
 			</td>
		</tr>
	</table>

  

<!-- This hidden variable holds the letter id-->
<html:hidden property="hidLetterID"/>	
<html:hidden property="hiddenAction"/>
<html:hidden property="hidPostedBy"/>
<html:hidden property="hidPostedOn"/>
<html:hidden property="hidEmail"/>
<html:hidden property="hidStatus"/>
<html:hidden property="strRedirectedOn"/>
<html:hidden property="strUsrId"/>
<html:hidden property="strUsrDesig"/>
<html:hidden property="strUsrDivSch"/>
<html:hidden property="fromCategory"/>
<html:hidden property="letterId"/>

<!-- -->
 <!-- This is for calendar -->
 <DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>

</body>
</html:form> 
<%}catch(Exception e){out.println(e);}%>

<script>
	//This function is to show the Send Raminder button based on Mode
	fnRadioRemainder();
	//This function is to display the publish on date depends upon the checkbox of Publish on Website
	fnClickPublish();

var varLetter= document.ASMFormReplyRedirectEdit.txtLetter.value
var varReply= document.ASMFormReplyRedirectEdit.txtReply.value
function fnReset(){
	try{
		editor_setHTML('txtLetter', varLetter);
		editor_setHTML('txtReply', varReply);
		document.ASMFormReplyRedirectEdit.reset();
		fnRadioRemainder();
	}
	catch(err)
	{
		alert('Function Name : fnReset() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}
</script>

<%
// Get the server current date 
Date todayDate= new Date();
SimpleDateFormat sdfInput = new SimpleDateFormat("dd-MMM-yyyy");
String textDate = sdfInput.format(todayDate);
%>
<script>
function fnSave1(){
	try{
	
		if(!fnValidation()){
		
			return false;
		}
		document.ASMFormReplyRedirectEdit.hiddenAction.value ="Save" ;
		document.ASMFormReplyRedirectEdit.action ="ASMReplyRedirectEdit.do" ;
		document.ASMFormReplyRedirectEdit.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSave() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnDelete()
		{
		
		if (window.confirm('Are you sure you want to delete the Letter')==false)
			{
				return;
			} 
		else{	
				document.ASMFormReplyRedirectEdit.hiddenAction.value="Delete";
				document.ASMFormReplyRedirectEdit.action="ASMReplyRedirectEdit.do";
				document.ASMFormReplyRedirectEdit.submit();
			}	
		}
var currentDate ="<%=textDate%>"
var publishOnDate = document.ASMFormReplyRedirectEdit.txtPublishOn.value
var showFrom = document.ASMFormReplyRedirectEdit.txtShowFrom.value
var showTo = document.ASMFormReplyRedirectEdit.txtShowTo.value


fn_textCounter(document.ASMFormReplyRedirectEdit.txtMessage,document.ASMFormReplyRedirectEdit.numleft,maxlimitMessage);


</script>
