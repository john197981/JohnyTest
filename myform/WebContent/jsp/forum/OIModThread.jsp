<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.forum.OIForumConstants" %>

<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditor.js'></script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<script language="Javascript">
<!-- 
	var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5) 
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 

<logic:iterate id="objExpression" name="ExpressionsList" indexId="ind" type="org.apache.struts.util.LabelValueBean">
	aryCodes[<bean:write name="ind" />] = "<bean:write name="objExpression" property="value" />";
	aryIcons[<bean:write name="ind" />] = "<bean:write name="objExpression" property="label" />";
</logic:iterate>

	function previewHtmlContent() {
		var strImgRoot1 = '<img src=\"<bean:message key="OIFM.docroot" />';
		var strImgRoot2 = '\" border="0" >';
		var cont = document.ThreadForm.strPosting.value;
		var ind = -1;
		var code = "";
		for(var j=0; j<aryCodes.length; j++) {
			code = aryCodes[j];
			while((ind = cont.indexOf(code)) != -1)
				cont = cont.substring(0, ind) + strImgRoot1+ aryIcons[j] + strImgRoot2 + cont.substring(ind+code.length, cont.length);
		}
		document.getElementById("testView").innerHTML = cont;
		document.getElementById("displayCont").style.display = "";
		document.ThreadForm.strPosting.select();
	}

	function insertEmotion(tag) {
		editor_insertHTML('taContentId', tag);
	}

	function submitModThreadForm(submitUrl, actionName) {
		var frm = document.ThreadForm;
		var flag = false;
		flag = checkBlank(frm.strTitle, "title");
		if(actionName == "<%= OIForumConstants.THREAD_CREATE %>")  {
			if(flag) flag = checkBlank(frm.strPosting, "message");
			if(flag) flag = txtAreaMaxLen(frm.strPosting, 4000, "message (including RTF tags)");
		}
		if(flag) {
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
		}
		return;
	}

// -->
</script>

<BR>	

<% int i=0; %>

<html:form action="/ThreadMaintain.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strThreadId" />
<html:hidden property="strTopicId" />
<html:hidden property="strPostId" />

<p>
	<div  class="Text">
		<logic:present name="CatBoardTopic" scope="request" >
			<bean:define id="objCatBoardTopic" name="CatBoardTopic" />
			&nbsp;&nbsp;&nbsp;&nbsp;
			<a href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.FORUM_HOME_DO %>?hiddenAction=populate#C<bean:write name="objCatBoardTopic" property="strCategoryId" />' >
				<bean:write name="objCatBoardTopic" property="strCategory" /></a> 
			<img src='<bean:message key="OIFM.docroot" />/images/sidearrow.gif' width="12" height="12" border=0 > 
			<a href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.FORUM_HOME_DO %>?hiddenAction=populate#B<bean:write name="objCatBoardTopic" property="strBoardId" />' >
				<bean:write name="objCatBoardTopic" property="strBoard" /></a> 
			<img src='<bean:message key="OIFM.docroot" />/images/sidearrow.gif' width="12" height="12" border=0> 
			<a href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_LIST_DO %>?strTopicId=<bean:write name="objCatBoardTopic" property="strTopicId" />' >
				<bean:write name="objCatBoardTopic" property="strTopic" /></a>
		</logic:present>
	</div>
</p>

<table width="650" border="0" cellspacing="0" cellpadding="0">
<logic:present name="error" scope="request" >
<tr>
	<td width="100%" class="BodyText" height="35" valign="middle" >
	<b><bean:message name="error" scope="request"/></b>
	</td>
</tr>
</logic:present>

<tr>
	<td bgcolor="white" class="bodyoutline" align="left" valign="top">

	<table width="100%" border="0" cellspacing="0" cellpadding="3">
	<tr class="Titleheader">
		<td height="20" colspan="4">Modify/View Thread </td>
	</tr>

	<tr class="BodyText">
		<td width="140">Thread Title</td>
		<td colspan="2" >
			<html:text styleClass="Text" property="strTitle" size="50" maxlength="75"	/>
		</td>
		<td>&nbsp;</td>
	</tr>

	<tr class="Titleheader">
		<td colspan="4">Post Message</td>
	</tr>
	
	<tr class="BodyText">
		<td colspan="3" align="middle" valign="top">
		<html:textarea property="strPosting" styleId="taContentId" cols="65" rows="9" styleClass="bodytext" /> 
		<script language="javascript">
			var config = new Object();
			config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
			config.debug = 0;
			editor_generate('taContentId',config);
		</script>
		</td>
		<td>
		<table align='center' cellpadding='4' cellspacing="0"  style="border: 1px solid #0099FF;">
		<tr>
			<td align="center" colspan="3" class="Titleheader"><b>Emotions</b></td>
		</tr>
		
		<tr align='center'>
<logic:iterate id="objExpression" name="ExpressionsList" indexId="ind" type="org.apache.struts.util.LabelValueBean">
	<td>
		<a href="#" onClick='javascript:insertEmotion("<bean:write name="objExpression" property="value" />")'><img src='<bean:message key="OIFM.docroot" /><bean:write name="objExpression" property="label" />' border='0' /></a>&nbsp;</td>
	<%= (++i%3 == 0)?"</tr><tr align='center'>":"" %>
</logic:iterate>
		</tr>
		</table>

		</td>
	</tr>
	
	<tr>
		<td colspan="4" align="right" class="bodytext" valign="top">

	<a href="#" onClick='javascript:submitModThreadForm(
						"<%= OIForumConstants.THREAD_MAINTAIN_DO %>",
						"<%= OIForumConstants.THREAD_MOD_MODIFY %>");'><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit"></a>

	<a href="#"  onClick="previewHtmlContent();"><img src='<bean:message key="OIFM.docroot" />/images/but_Preview.gif' border="0" alt="Preview"></a>

	<a href="#" onClick="window.close();"><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt="Cancel"></a>
		</td>
	</tr>
	</table>
	</TD>
</TR>
</TABLE>
</html:form>

<DIV id="displayCont" style="display:none">	
	<TABLE width="650" cellpadding="3">
	<tr>
		<td class="Titleheader">
		<B>Posting Preview</B> 
		</td>
	</tr>
	<tr>
		<td class="">
		<TABLE style="border: 1px solid #0099FF;" bgcolor="#FFFFFF" width="100%">
		<TR class="bodytext" >
			<TD ><div  id="testView" bgcolor="#FFFFFF"></div></TD>
		</TR>
		</TABLE>
		</TD>
	</TR>
	</TABLE>
</DIV>	
