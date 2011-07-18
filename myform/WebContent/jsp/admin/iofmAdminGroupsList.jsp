<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.oifm.useradmin.OIGroupsConstants" %>
<%@ page import="com.oifm.utility.DateUtility" %>

<script language="javascript">
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function editGroup(groupID) {
	var frm = document.GroupsForm;
	frm.groupId.value = groupID;
	frm.hiddenAction.value = '<%= OIGroupsConstants.EDIT_GROUP %>';
	frm.submit();
}
</script>

<html:form action="/Groups.do" method="post">

<html:hidden property="groupId" />
<html:hidden property="hiddenAction" />
 
	<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0"> 
	<tr>
	<td class="TableHead">
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="white">
					<tr class="Table_head" ><td colspan="6">Fixed Group</td></tr>
		        	<tr align="left" valign="top" class="SubHead">
						 <td width="17%" class="Sub_Head">Group Name</td>
						 <td class="Sub_Head">Description</td>
						 <td class="Sub_Head">Members</td>
			        </tr>
			        <logic:iterate id="objFixedGroups" name="FixedGroupsList" indexId="ifdx" type="com.oifm.useradmin.OIBAGroups">
			        	<tr align="left" valign="top">
			          		<td width="30%" class="heading_attributes"><bean:write name="objFixedGroups" property="name" /></td>
			          		<td width="50%" class="heading_attributes"><bean:write name="objFixedGroups" property="description" /></td>
			          		<td width="20%" class="heading_attributes"><bean:write name="objFixedGroups" property="numOfUsers" /></td>
			        	</tr>
			        </logic:iterate>
			        <tr height="30">
						<td colspan="6"></td>
					</tr>
	 			</table>
			</td>
		</tr>		
		<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="white">
					<tr class="Table_head" ><td colspan="6">Private Group</td></tr>
			         <tr align="left" valign="top" class="SubHead">
						 <td width="17%" class="Sub_Head">Group Name</td>
						 <td class="Sub_Head">Description</td>
						 <td class="Sub_Head">Created By</td>
						 <td class="Sub_Head">Division</td>
						 <td class="Sub_Head">Created On</td>
						 <td class="Sub_Head">Members</td>
			        </tr>
			        <logic:iterate id="objGroups" name="GroupsList" indexId="idx" type="com.oifm.useradmin.OIBAGroups">
			        	<tr align="left" valign="top">
			          		<td class="body_detail_text"><a class="special_body_link" href="#" onclick="editGroup('<bean:write name="objGroups" property="groupId" />')"><bean:write name="objGroups" property="name" /></a></td>
			          		<td width="40%" class="heading_attributes"><bean:write name="objGroups" property="description" /></td>
			          		<td width="12%" class="heading_attributes"><a class="special_body_link" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="objGroups" property="createdBy" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="objGroups" property="nickname" /></a></td>
			          		<td width="13%" class="heading_attributes"><bean:write name="objGroups" property="divName" /></td>
			          		<td width="14%" class="heading_attributes"><bean:write name="objGroups" property="createdOnstr" /></td>
			          		<td width="8%" class="heading_attributes"><bean:write name="objGroups" property="numOfUsers" /></td>
			        	</tr>
			        </logic:iterate>
					<tr height="30">
						<td colspan="6"></td>
					</tr>
					<tr height="30">
						<td colspan="6"><a href="#" onclick="editGroup('')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_NewGroup.gif" border="0" alt = "Create Group"></a></td>
					</tr>
	 			</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</html:form>