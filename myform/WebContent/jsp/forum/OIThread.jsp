<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.forum.OIForumConstants" %>
<%@ page import="com.oifm.useradmin.OIGroupsConstants" %>

<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditor.js'></script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<script language="Javascript">
var ctxRoot = '<bean:message key="OIFM.contextroot"/>'
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

	function submitThreadForm(submitUrl, actionName) {
		var frm = document.ThreadForm;
		var flag = false;
		flag = checkBlank(frm.strTitle, "title");
		if(actionName == "<%= OIForumConstants.THREAD_CREATE %>")  {
			if(flag) flag = checkBlank(frm.strPosting, "message");
			if(flag) flag = txtAreaMaxLen(frm.strPosting, 4000, "message (including RTF tags)");
		}
<logic:match name="createPrivateThread" value="true">
		if(flag && frm.strSecurity[1].checked) {
			flag = checkMandatoryGroups();
			if(!flag) alert("Please allocate atleast one group");
		}
</logic:match>
		if(flag) {
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
		}
		return;
	}

	function setGroupsOption(disableFlag) {
		var obj = document.ThreadForm.aryUserGroups;
		for(var i=0; i<obj.length; i++) {
			obj[i].disabled = disableFlag;		
		}
		return;
	}

	function checkMandatoryGroups() {
		var obj = document.ThreadForm.aryUserGroups;
		var flag = false;
		for(var i=0; i<obj.length; i++) {
			if(obj[i].checked) {
				flag = true; break;
			} else flag = false;
		}
		return flag;
	}

	function resetForm() {
		document.ThreadForm.reset();
	}

function openGroupMembers(gID){
	var strUrl = '/GroupMembers.do?hiddenAction=<%= OIGroupsConstants.LIST_MEMBERS %>&groupId='+gID;
	help_window  = window.open(ctxRoot+strUrl,'GroupMembers','width=800,height=550,left=0,top=0,resizable=yes,scrollbars=yes');
  	help_window.focus();
}


</script>

<BR>	
<%	int i = 0, j=0; %>

<html:form action="/ThreadMaintain.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strThreadId" />
<html:hidden property="strTopicId" />
<html:hidden property="strPostId" />
<html:hidden property="strTopicModerationReq" />
<html:hidden property="pageNo" />


  <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
 <logic:present name="error" scope="request" >
<tr>
	<td width="100%" class="BodyText" height="35" valign="middle" >
	<b><bean:message name="error" scope="request"/></b>
	</td>
</tr>
</logic:present>

  <tr><td class="Box"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td class="Table_head" colspan="3">
		<logic:greaterThan name="ThreadForm" property="strThreadId" value="0" >
				 Modify/View Thread 
		</logic:greaterThan>
		<logic:lessThan name="ThreadForm" property="strThreadId" value="1" >
				 Create Thread  
		</logic:lessThan>
	  
	  </td>
     </tr>
    <tr>
      <td width="17%" bgcolor="#E0EBFC">&nbsp;</td>
      <td width="10%">&nbsp;</td>
      <td width="72%">&nbsp;</td>
    </tr>
    <tr>
      <td valign="top" bgcolor="#E0EBFC" class="Table_Sub_head">Thread Title</td>
      <td colspan="2" valign="top">
	  <html:text styleClass="Text_box"  property="strTitle" size="70" maxlength="75"	/>
	  <label></label></td>
      </tr>
    <tr>
      <td valign="top" bgcolor="#E0EBFC" class="Table_Sub_head">Moderation Req </td>
      <td valign="top" ><span class="body_detail_text">
<logic:present name="ThreadForm" property="strTopicModerationReq" scope="request" >
	<logic:match name="ThreadForm" property="strTopicModerationReq" value="true">
 		<html:radio styleClass="Text" property="strModerationReq" value="Y" >Yes  </html:radio>
		</span></td> <td valign="top"><span class="body_detail_text">
		<html:radio  styleClass="Text" property="strModerationReq" value="N" disabled="true">No</html:radio>
	</logic:match>
	<logic:match name="ThreadForm" property="strTopicModerationReq" value="false">
		<html:radio styleClass="Text" property="strModerationReq" value="Y" >Yes  </html:radio>
		</span></td> <td valign="top"><span class="body_detail_text">
		<html:radio  styleClass="Text" property="strModerationReq" value="N" >No</html:radio>
	</logic:match>
</logic:present>

</span></td>
     </tr>

<logic:match name="createPrivateThread" value="true">

    <tr>
      <td valign="top" bgcolor="#E0EBFC" class="Table_Sub_head">Security</td>
      <td valign="top"  ><span class="body_detail_text">
			<html:radio  styleClass="Text" property="strSecurity" value="N" onclick="setGroupsOption(true);">Public
			</html:radio>
			</span>
		</td> 
		<td valign="top"><span class="body_detail_text">
			<html:radio  styleClass="Text" property="strSecurity" value="Y" onclick="setGroupsOption(false);">Private</html:radio>
			</span>
		</td>
     </tr>
 
    <tr>
      <td valign="top" bgcolor="#E0EBFC" class="Table_Sub_head">Invite Groups </td>
      <td colspan="2" valign="top" class="Heading_Thread">

<bean:size id="groupSize" name="GroupsList" />
<logic:greaterThan name="groupSize" value="0" >

		  <table width="348" border="0" cellpadding="0" cellspacing="0" class="Box">
	  
			<tr>
	<logic:iterate name="GroupsList" id="objGroup" type="org.apache.struts.util.LabelValueBean">
			  
		<td width="20" class="Special_body_link">
			<html:multibox property="aryUserGroups"><bean:write name="objGroup" property="value" /></html:multibox>
		</td>
		<td width="142" class="Special_body_link"> 
			<a class="Special_body_link" href="#" onclick="openGroupMembers('<bean:write name="objGroup" property="value" />')">
			<bean:write name="objGroup" property = "label" />
			</a>
			</td>
		<%= (++j%2 == 0)?"</tr><tr align='left' class='Special_body_link'>":"" %>
 		
	</logic:iterate>
		</tr>
		</table>
	<logic:match name="ThreadForm" property="strSecurity" value="N">
	<SCRIPT LANGUAGE="JavaScript">
	<!--
		setGroupsOption(true);
	//-->
	</SCRIPT>
	</logic:match>
</logic:greaterThan> 
<logic:lessThan name="groupSize" value="1"> No Available Groups</logic:lessThan>
	  </td>
      </tr>
  
</logic:match>
<logic:match name="createPrivateThread" value="false">
	<html:hidden property="strSecurity" />
</logic:match>
<logic:lessThan name="ThreadForm" property="strThreadId" value="1">
	
    <tr>
      <td bgcolor="#E0EBFC" class="Table_Sub_head">Post Message </td>
      <td class="Table_Sub_head">&nbsp;</td>
      <td class="Table_Sub_head">&nbsp;</td>
    </tr>
    <tr>
      <td align="right" valign="top" bgcolor="#E0EBFC"><table height="100%" align='right' cellpadding='4' cellspacing="0">
        
        <tr align='center'>
			<logic:iterate id="objExpression" name="ExpressionsList" indexId="ind" type="org.apache.struts.util.LabelValueBean">
				<td>
					<a href="#" onClick='javascript:insertEmotion("<bean:write name="objExpression" property="value" />")'><img src='<bean:message key="OIFM.docroot" /><bean:write name="objExpression" property="label" />' border='0' /></a>&nbsp;</td>
				<%= (++i%3 == 0)?"</tr><tr align='center'>":"" %>
			</logic:iterate>
        </tr>
          <tr align='center'>
          <td colspan="3"><b>Emotions</b></td>
          </tr>

         <tr align='left'>
          <td colspan="3" class="Special_body_link" >
 <logic:lessThan name="ThreadForm" property="strThreadId" value="1">

	<logic:equal name="accessInfo" property="divAdminOrModerator" value="true">
	<html:checkbox property="strHQReply" value="Y" />HQ Reply
	</logic:equal>
</logic:lessThan>

 </td>
          </tr>

        
      </table></td>
      <td colspan="2" align="left" valign="top"> 
	  		<html:textarea property="strPosting" styleId="taContentId" cols="65" rows="11" styleClass="Text_Area" />
		<script language="javascript">
			var config = new Object();
			config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
			config.debug = 0;
			editor_generate('taContentId',config);
		</script>

	  </td>
      </tr>

	</logic:lessThan>

	<tr>
      <td bgcolor="#E0EBFC">&nbsp;</td>
      <td class="body_detail_text">&nbsp;</td>
      <td>&nbsp;</td>
    </tr>
    <tr>
      <td class="Table_head">&nbsp;</td>
      <td colspan="2" class="Table_head">
			<logic:greaterThan name="ThreadForm" property="strThreadId" value="0">
				<a href="#" onClick='javascript:submitThreadForm(
									"<%= OIForumConstants.THREAD_MAINTAIN_DO %>",
									"<%= OIForumConstants.THREAD_MODIFY %>");'><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit" width="72" height="24" ></a>
			</logic:greaterThan>

	<logic:lessThan name="ThreadForm" property="strThreadId" value="1">
		<a href="#" onClick='javascript:submitThreadForm(
							"<%= OIForumConstants.THREAD_MAINTAIN_DO %>",
							"<%= OIForumConstants.THREAD_CREATE %>");'><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit"></a>
		<a href="#"  onClick="previewHtmlContent();"><img src='<bean:message key="OIFM.docroot" />/images/but_Preview.gif' border="0" alt="Preview" width="72" height="24" ></a>
	</logic:lessThan>

		<a href="#" onClick="window.close();"><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt="Cancel" width="72" height="24" ></a>
		   
	   </td>
      </tr>
    
    
  </table></td></tr>
   </table> 
   
 
</html:form>

<div id="displayCont" style="display:none">	
	<TABLE width="650" cellpadding="3">
	<tr>
		<td class="Table_head">
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
