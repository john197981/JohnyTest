<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="org.apache.struts.util.LabelValueBean"%>
<%@ page import="com.oifm.useradmin.OIRolesConstants"%>

<script language="javascript" type="text/javascript">
function submitForm(roleID) {
	var frm = document.RolesForm;
	frm.strRoleID.value = roleID;
	frm.hiddenAction.value = '<%= OIRolesConstants.EDIT_ROLE %>';
	frm.submit();
}

</script>

<html:form action="/Roles.do" method="post">

	<html:hidden property="strRoleID" />
	<html:hidden property="hiddenAction" />

	<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="60%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="2">Roles</td>
 					</tr>
 						<tr class="SubHead">
							<td width="71%" class="Sub_Head">Role Name</td>
							<td width="29%" class="Sub_Head">No of Users</td>
						</tr>
						<logic:iterate id="objRoles" name="RolesList" indexId="idx"
							type="com.oifm.useradmin.OIBARoles">
							<tr>
								<td class="body_detail_text"><a class="special_body_link" href="#"
									onclick='submitForm("<bean:write name="objRoles" property="strRoleID" />")'><bean:write
									name="objRoles" property="strRoleName" /></a></td>
								<td class="heading_attributes"><bean:write name="objRoles"
									property="intNumOfUsers" /></td>
							</tr>
						</logic:iterate>
				<tr height="30">
					<td align="left" colspan="2">
					</tr>
 				<tr>
					<td align="left" colspan="2">
					<a href="#" onclick="javascript:submitForm('');"> <img
						src='<bean:message key="OIFM.docroot"/>/images/but_new_role.gif'
						border='0' alt="New Role" /></a> 
					</td>
				</tr>

				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</html:form>
