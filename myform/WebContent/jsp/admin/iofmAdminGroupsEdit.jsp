<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%> 
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.useradmin.OIGroupsConstants" %>
<%@ page import="com.oifm.common.OIAddEmailsConstants" %>

<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function submitForm(hidAction, nxtAction) {
	var flag = true;
	if (hidAction == '<%= OIGroupsConstants.DO_CREATE %>' || hidAction == '<%= OIGroupsConstants.DO_EDIT %>') {
		if (flag) flag = checkBlank(document.GroupsForm.name, "Group Name");
	}
	var frm = document.GroupsForm;
	frm.hiddenAction.value = hidAction;
	frm.nextAction.value = nxtAction;
	if (flag) frm.submit();
	else return;
}

function noDelete() {
	alert("<bean:message key="OI.GROUPS.NO_DELETE" scope="request"/>");
}

function doDelete(hidAction, nxtAction) {
	var flag = confirm("<bean:message key="OI.GROUPS.CONFIRM_DELETE" scope="request"/>");
	if (flag) submitForm(hidAction, nxtAction);
	else return false;
}

function doRemoveUsers(hidAction, nxtAction) {
	var flag = false;
	if(document.GroupsForm.toBeRemoved.length != undefined)
		for (i = 0; i < document.GroupsForm.toBeRemoved.length; i++) {
			if (flag = document.GroupsForm.toBeRemoved[i].checked) break;
		}
	else flag = document.GroupsForm.toBeRemoved.checked;
	
	if (!flag) alert('<bean:message key="OI.GROUPS.NO_MEMBER" scope="request"/>');
	else submitForm(hidAction, nxtAction);
}

function fnSelectUsers(){
	var strUrl = '/SelectUsers.do?hiddenAction=populate&hidPage=addGroups&flag=G&id='+document.GroupsForm.groupId.value;
	help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=920,height=550,left=0,top=0,resizable=yes,scrollbars=yes');
  	help_window.focus();
}

function openAddEmails(){
	var strUrl = '/AddEmails.do?hiddenAction=<%= OIAddEmailsConstants.ACTION_INPUT %>&strGroupID='+document.GroupsForm.groupId.value;
	help_window  = window.open(strDocRoot+strUrl,'AddEmails','width=600,height=550,left=0,top=0,resizable=yes,scrollbars=yes');
  	help_window.focus();
}

function checkAll() {
	if(document.GroupsForm.toBeRemoved.length != undefined)
		for (i = 0; i < document.GroupsForm.toBeRemoved.length; i++)
			document.GroupsForm.toBeRemoved[i].checked = document.GroupsForm.selectAll.checked;
	else document.GroupsForm.toBeRemoved.checked = document.GroupsForm.selectAll.checked;
}

function checkSelectAll(chkbx) {
	if (!chkbx.checked) document.GroupsForm.selectAll.checked = false;
}

function sort(sortKey, sortOrder) {
	document.GroupsForm.sortKey.value = sortKey;
	document.GroupsForm.sortOrder.value = sortOrder;
	document.GroupsForm.hiddenAction.value = '<%= OIGroupsConstants.SORT %>';
	document.GroupsForm.submit();
}

</script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/Groups.do" method="post">

<html:hidden property="hiddenAction" />
<html:hidden property="nextAction" />
<html:hidden property="groupId" />
<html:hidden property="sortKey" />
<html:hidden property="sortOrder" />


<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="2">Group</td>
 					</tr>
 			<logic:present name="error" scope="request" >
				  <tr>
					<td width="100%" class="body_detail_text" colspan="2">
					<b><bean:message name="error" scope="request"/></b>
					</td>
				  </tr>
			</logic:present>
 		<tr>
			<td colspan="2" class="Sub_head">
        	<logic:lessThan name="GroupsForm" property="groupId" value="1">
        		Create Group
        	</logic:lessThan>
        	<logic:greaterThan name="GroupsForm" property="groupId" value="0">
        		View / Modify Group
        	</logic:greaterThan>
			</td>
		</tr>
        <tr>
          <td align="left" width="15%" class="heading_attributes">Group Name <font color="red">*</font> </td> 
          <td align="left" width="*" class="body_detail_text">
          	<logic:present name="isOwner" scope="request">
          		<logic:match name="isOwner" scope="request" value="true">
          			<html:text property="name" styleClass="Text_box" size="30" maxlength="20" />
          		</logic:match>
          		<logic:notMatch name="isOwner" scope="request" value="true">
          			<logic:lessThan name="GroupsForm" property="groupId" value="1">
          				<html:text property="name" styleClass="Text_box" size="30" maxlength="20" />
          			</logic:lessThan>
          			<logic:greaterThan name="GroupsForm" property="groupId" value="0">
          				<html:text property="name" styleClass="Text_box" size="30" maxlength="20" readonly="true" />
          			</logic:greaterThan>
          		</logic:notMatch>
          	</logic:present>
          	<logic:notPresent name="isOwner" scope="request">
          		<logic:lessThan name="GroupsForm" property="groupId" value="1">
          			<html:text property="name" styleClass="Text_box" size="30" maxlength="20" />
          		</logic:lessThan>
          		<logic:greaterThan name="GroupsForm" property="groupId" value="0">
          			<html:text property="name" styleClass="Text_box" size="30" maxlength="20" readonly="true" />
          		</logic:greaterThan>
          	</logic:notPresent>
          </td>
        </tr>
        <tr>
          <td align="left" width="15%" class="heading_attributes">Description</td> 
          <td align="left" width="*" class="body_detail_text">
          	<logic:present name="isOwner" scope="request">
          		<logic:match name="isOwner" scope="request" value="true">
          			<html:text property="description" styleClass="Text_box" size="90" maxlength="50" />
          		</logic:match>
          		<logic:notMatch name="isOwner" scope="request" value="true">
          			<logic:lessThan name="GroupsForm" property="groupId" value="1">
          				<html:text property="description" styleClass="Text_box" size="90" maxlength="50" />
          			</logic:lessThan>
          			<logic:greaterThan name="GroupsForm" property="groupId" value="0">
          				<html:text property="description" styleClass="Text_box" size="90" maxlength="50" readonly="true" />
          			</logic:greaterThan>
          		</logic:notMatch>
          	</logic:present>
          	<logic:notPresent name="isOwner" scope="request">
          		<logic:lessThan name="GroupsForm" property="groupId" value="1">
          			<html:text property="description" styleClass="Text_box" size="90" maxlength="50" />
          		</logic:lessThan>
          		<logic:greaterThan name="GroupsForm" property="groupId" value="0">
          			<html:text property="description" styleClass="Text_box" size="90" maxlength="50" readonly="true" />
          		</logic:greaterThan>
          	</logic:notPresent>
          </td>
        </tr>
      </table>
        <br>
        <logic:present name="Remove" scope="request">
        <div align="center">
			<p  class="Text"> <font color="red"><bean:write name="Remove" scope="request" /> selected user(s) deleted successfully</font></p> 
		</div>
      	</logic:present>
      	<br>
      <table width="100%"  border="0" cellspacing="1" cellpadding="1" bgcolor="white">
        <tr>
          <td colspan="6" align="left" valign="top" class="Table_head">Group Members</td>
        </tr>
        <logic:present name="MembersList" scope="request">
        	<tr class="SubHead">
          		<td width="2%" class="Sub_Head">
          			<logic:present name="isOwner" scope="request">
          				<logic:match name="isOwner" scope="request" value="true">
          					<input type="checkbox" name="selectAll" value="selectAll" onclick="checkAll()">
          				</logic:match>
          				<logic:notMatch name="isOwner" scope="request" value="true">
          					&nbsp;
          				</logic:notMatch>
          			</logic:present>
          			<logic:notPresent name="isOwner" scope="request">
          				&nbsp;
          			</logic:notPresent>
          		</td>
          		<td width="10%" class="Sub_Head">
          			NRIC
          			<logic:equal name="GroupsForm" property="sortKey" value="0" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(0,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt = "Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(0,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt = "Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="0" scope="request">
						<a href="#" onClick="sort(0,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt = "Right"></a>
					</logic:notEqual>
          		</td>
          		<td width="30%" class="Sub_Head">
          			Name
          			<logic:equal name="GroupsForm" property="sortKey" value="1" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(1,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt = "Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(1,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt = "Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="1" scope="request">
						<a href="#" onClick="sort(1,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt = "Right"></a>
					</logic:notEqual>
          		</td>
          		<td width="20%" class="Sub_Head">
          			Division
          			<logic:equal name="GroupsForm" property="sortKey" value="2" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(2,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt = "Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(2,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt = "Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="2" scope="request">
						<a href="#" onClick="sort(2,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt = "Right"></a>
					</logic:notEqual>
          		</td>
          		<td width="20%" class="Sub_Head">
          			Email ID
          			<logic:equal name="GroupsForm" property="sortKey" value="3" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(3,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt = "Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(3,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt = "Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="3" scope="request">
						<a href="#" onClick="sort(3,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt = "Right"></a>
					</logic:notEqual>
          		</td>
          		<td width="23%" class="Sub_Head">
          			No Of Posting
          			<logic:equal name="GroupsForm" property="sortKey" value="4" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(4,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt = "Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(4,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt = "Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="4" scope="request">
						<a href="#" onClick="sort(4,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt = "Right">
					</logic:notEqual>
          		</td>
        	</tr>
        	<logic:iterate id="objMembers" name="MembersList" indexId="idx" type="com.oifm.useradmin.OIGroupMembersBean">
        		<tr>
          			<td class="body_detail_text">
          				<logic:present name="isOwner" scope="request">
          					<logic:match name="isOwner" scope="request" value="true">
          						<html:multibox property="toBeRemoved" onclick="checkSelectAll(this)">
            						<bean:write name="objMembers" property="userID" />
            					</html:multibox>
          					</logic:match>
          					<logic:notMatch name="isOwner" scope="request" value="true">
          						&nbsp;
          					</logic:notMatch>
          				</logic:present>
          				<logic:notPresent name="isOwner" scope="request">
          					&nbsp;
          				</logic:notPresent>
          			</td>
          			<td class="body_detail_Text"><a class="special_body_link" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="objMembers" property="userID" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="objMembers" property="userID" /></a></td>
          			<td class="heading_attributes"><bean:write name="objMembers" property="name" /></td>
          			<td class="heading_attributes"><bean:write name="objMembers" property="division" /></td>
          			<td class="heading_attributes"><bean:write name="objMembers" property="emailID" /></td>
          			<td class="heading_attributes"><bean:write name="objMembers" property="numOfPostings" /></td>
        		</tr>
        	</logic:iterate>
        </logic:present>
        <logic:notPresent name="MembersList" scope="request">
        	<tr>
          		<td colspan="5" class="body_detail_text">
          			<p>&nbsp;</p>
          			<p>No members</p>
          			<p>&nbsp;</p>
          		</td>
        </tr>
        </logic:notPresent>
      </table>
      <br>
      <table width="98%"  border="0" cellspacing="0" cellpadding="0" bgcolor="white">
        <tr>
          <td height="35" align="left"><p>
          	<logic:present name="isOwner" scope="request">
          		<logic:match name="isOwner" scope="request" value="true">
          			<a href="#" onclick="submitForm('<%= OIGroupsConstants.DO_EDIT %>','<%= OIGroupsConstants.LIST_GROUPS %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save.gif" border="0" alt = "Save"></a>
          			<a href="#" onclick="doDelete('<%= OIGroupsConstants.DO_DELETE %>','<%= OIGroupsConstants.LIST_GROUPS %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Delete.gif" border="0" alt = "Delete"></a>
          			<a href="#" onclick="fnSelectUsers()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save_Add_User.gif" border="0" alt = "Add Users"></a>
          			<logic:present name="MembersList" scope="request">
          				<a href="#" onclick="doRemoveUsers('<%= OIGroupsConstants.DO_REMOVE_USER %>','<%= OIGroupsConstants.EDIT_GROUP %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_RemoveUsers.gif" border="0" alt = "Remove Users"></a>
          			</logic:present>
          			<a href="#" onclick="openAddEmails()"><img src="<bean:message key="OIFM.docroot"/>/images/Add_Email_IDs.gif" border="0" alt = "Add Email IDs"></a>
          			<a href="<bean:message key="OIFM.contextroot"/>/Groups.do"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>
          			<a href="#" onclick="document.GroupsForm.reset()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif" border="0" alt = "Reset">
          		</logic:match>
          		<logic:notMatch name="isOwner" scope="request" value="true">
          			<logic:lessThan name="GroupsForm" property="groupId" value="1">
          				<a href="#" onclick="submitForm('<%= OIGroupsConstants.DO_CREATE %>','<%= OIGroupsConstants.LIST_GROUPS %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save.gif" border="0" alt = "Save"></a>
          				<a href="#" onclick="submitForm('<%= OIGroupsConstants.DO_CREATE %>','<%= OIGroupsConstants.EDIT_GROUP %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save_Add_User.gif" border="0" alt = "Save and Add Users"></a>
          			</logic:lessThan>
          			<a href="<bean:message key="OIFM.contextroot"/>/Groups.do"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Cancel.gif" border="0" alt = "Cancel"></a>
          		</logic:notMatch>
          	</logic:present>
          	<logic:notPresent name="isOwner" scope="request">
          		<logic:lessThan name="GroupsForm" property="groupId" value="1">
          			<a href="#" onclick="submitForm('<%= OIGroupsConstants.DO_CREATE %>','<%= OIGroupsConstants.LIST_GROUPS %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save.gif" border="0" alt = "Save"></a>
          			<a href="#" onclick="submitForm('<%= OIGroupsConstants.DO_CREATE %>','<%= OIGroupsConstants.EDIT_GROUP %>')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save_Add_User.gif" border="0" alt = "Save and Add Users"></a>
          		</logic:lessThan>
          		<a href="<bean:message key="OIFM.contextroot"/>/Groups.do"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Close.gif" border="0" alt = "Close"></a>
          	</logic:notPresent>
          </p>
		  
		  </td>
        </tr>
      </table>


 			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>

<script language="javascript">
	if (document.GroupsForm.hiddenAction.value == '<%= OIGroupsConstants.EDIT_GROUP %>' && document.GroupsForm.nextAction.value == '<%= OIGroupsConstants.DO_CREATE %>') {fnSelectUsers();}
</script>
</html:form>