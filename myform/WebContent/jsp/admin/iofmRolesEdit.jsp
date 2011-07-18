<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.useradmin.OIRolesConstants" %>
<%@ page import="com.oifm.common.OIFunctionConstants" %>

<script language="javascript" >
function submitForm(action) {
	var flag = true;
	if (action == '<%= OIRolesConstants.DO_CREATE %>' || action == '<%= OIRolesConstants.DO_EDIT %>') {
		if (flag) flag = checkBlank(document.RolesForm.strRoleID, "Role ID");
		if (flag) flag = checkBlank(document.RolesForm.strRoleName, "Role Name");
	}
	var frm = document.RolesForm;
	frm.hiddenAction.value = action;
	if (flag) frm.submit();
	else return;
}

function noDelete() {
	alert("<bean:message key="OI.ROLE.DELETE" scope="request"/>");
}

function doDelete(action) {
	var flag = confirm("<bean:message key="OI.ROLE.CONFIRM_DELETE" scope="request"/>");
	if (flag) submitForm(action);
	else return false;
}

</script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<html:form action="/Roles.do" method="post">
<html:hidden property="hiddenAction" />
 
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="3">Roles</td>
 					</tr>
          </tr>
	<logic:present name="error" scope="request" >
          <tr>
            <td width="100%" class="body_detail_text" colspan="3">
			<b><bean:message name="error" scope="request"/></b>
			</td>
          </tr>
	</logic:present>
           <tr>
            <td colspan="3" class="Sub_head">
            <logic:empty name="RolesForm" property="strRoleID">Create Role</logic:empty>
            <logic:notEmpty name="RolesForm" property="strRoleID">View / Modify Role</logic:notEmpty>
            </td>
          </tr>
          <tr>
            <td width="20%" nowrap class="heading_attributes">Role ID <font color="red">*</font> </td>
            <td colspan="2" class="body_detail_text">
            	<logic:empty name="RolesForm" property="strRoleID">
            		<html:text property="strRoleID" styleClass="Text_box" size="30" maxlength="20" onkeypress="this.value=this.value.toUpperCase()" onkeyup="this.value=this.value.toUpperCase()" />
            	</logic:empty>
            	<logic:notEmpty name="RolesForm" property="strRoleID">
            		<logic:present name="error" scope="request" >
          				<html:text property="strRoleID" styleClass="Text_box" size="30" maxlength="20" onkeypress="this.value=this.value.toUpperCase()" onkeyup="this.value=this.value.toUpperCase()" />
          			</logic:present>
          			<logic:notPresent name="error" scope="request" >
          				<html:text property="strRoleID" styleClass="Text_box" size="30" maxlength="20" readonly="true" />
          			</logic:notPresent>
            	</logic:notEmpty>
            </td>
          </tr>
          <tr>
            <td width="20%" nowrap class="heading_attributes">Role Name <font color="red">*</font> </td>
            <td colspan="2" class="body_detail_text">
            	<html:text property="strRoleName" styleClass="Text_box" size="50" maxlength="30" />
            </td>
          </tr>
          <tr>
            <td rowspan="2" align="left" valign="top" nowrap class="heading_attributes">Allocate Modules </td>
            <bean:size id="arraySize_ADF" name="ADMIN_DF" scope="request" />
            <bean:size id="arraySize_ASU" name="ADMIN_SU" scope="request" />
            <bean:size id="arraySize_ACP" name="ADMIN_CP" scope="request" />
            <bean:size id="arraySize_AO" name="ADMIN_OTHERS" scope="request" />
            <bean:size id="arraySize_WDF" name="WEBSITE_DF" scope="request" />
            <bean:size id="arraySize_ASM" name="ADMIN_ASM" scope="request" />
            <bean:size id="arraySize_BLOG" name="ADMIN_BLOG" scope="request" />
            <% if (arraySize_ADF.intValue() > 0 || arraySize_ASU.intValue() > 0 || arraySize_ACP.intValue() > 0 || arraySize_AO.intValue() > 0 || arraySize_ASM.intValue() > 0 || arraySize_BLOG.intValue() > 0) { %>
            <td colspan="2" class="body_detail_text">
            	<table width="100%" bgcolor="white">
            		<tr><td>&nbsp;</td></tr>
            		<tr>
            			<td align="left" class="heading_thread" width="100%"><strong>Administration</strong></td>
            		</tr>
            		<% if (arraySize_ADF.intValue() > 0) { %>
            		<tr>
            			<td align="left" width="100%">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead">
            						<td colspan="2" align="left"  class="Sub_head" width="100%">Discussion Forum</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="ADMIN_DF" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<logic:equal name="RolesForm" property="strRoleID" value="DIVADMIN">
            											<logic:equal name="objFuncBean" property="label" value="MTNCTBD">
            												<html:multibox property="strFunctions" disabled="true">
            													<bean:write name="objFuncBean" property="label" />
            												</html:multibox><bean:write name="objFuncBean" property="value" />
            											</logic:equal>
            											<logic:notEqual name="objFuncBean" property="label" value="MTNCTBD">
            												<html:multibox property="strFunctions">
            													<bean:write name="objFuncBean" property="label" />
            												</html:multibox><bean:write name="objFuncBean" property="value" />
            											</logic:notEqual>
            										</logic:equal>
            										<logic:notEqual name="RolesForm" property="strRoleID" value="DIVADMIN">
            											<logic:equal name="RolesForm" property="strRoleID" value="MODERATOR">
            												<logic:equal name="objFuncBean" property="label" value="MTNCTBD">
            													<html:multibox property="strFunctions" disabled="true">
            														<bean:write name="objFuncBean" property="label" />
            													</html:multibox><bean:write name="objFuncBean" property="value" />
            												</logic:equal>
            												<logic:notEqual name="objFuncBean" property="label" value="MTNCTBD">
            													<html:multibox property="strFunctions">
            														<bean:write name="objFuncBean" property="label" />
            													</html:multibox><bean:write name="objFuncBean" property="value" />
            												</logic:notEqual>
            											</logic:equal>
            											<logic:notEqual name="RolesForm" property="strRoleID" value="MODERATOR">
            												<html:multibox property="strFunctions" disabled="true">
            													<bean:write name="objFuncBean" property="label" />
            												</html:multibox><bean:write name="objFuncBean" property="value" />
            											</logic:notEqual>
            										</logic:notEqual>
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_ADF.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            		<% } %>
            		<% if (arraySize_ASU.intValue() > 0) { %>
            		<tr>
            			<td align="left">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead">
            						<td colspan="2" align="left" class="Sub_head" width="100%">Survey</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="ADMIN_SU" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_ASU.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            		<% } %>
            		<% if (arraySize_ACP.intValue() > 0) { %>
            		<tr>
            			<td align="left">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead">
            						<td colspan="2" align="left" class="Sub_head" width="100%">Consultation Paper</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="ADMIN_CP" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_ACP.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            		<% } %>
            		<% if (arraySize_AO.intValue() > 0) { %>
            		<tr>
            			<td align="left">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead">
            						<td colspan="2" align="left" class="Sub_head" width="100%">Others</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="ADMIN_OTHERS" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<logic:equal name="RolesForm" property="strRoleID" value="DIVADMIN">
            											<html:multibox property="strFunctions">
            												<bean:write name="objFuncBean" property="label" />
            											</html:multibox><bean:write name="objFuncBean" property="value" />
            										</logic:equal>
            										<logic:notEqual name="RolesForm" property="strRoleID" value="DIVADMIN">
            											<logic:equal name="objFuncBean" property="label" value="MNTPROF">
            												<html:multibox property="strFunctions" disabled="true">
            													<bean:write name="objFuncBean" property="label" />
            												</html:multibox><bean:write name="objFuncBean" property="value" />
            											</logic:equal>
            											<logic:notEqual name="objFuncBean" property="label" value="MNTPROF">
            												<html:multibox property="strFunctions">
            													<bean:write name="objFuncBean" property="label" />
            												</html:multibox><bean:write name="objFuncBean" property="value" />
            											</logic:notEqual>
            										</logic:notEqual>
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_AO.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            		<% } %>
            		<% if (arraySize_ASM.intValue() > 0) { %>
            		<tr>
            			<td align="left">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead" >
            						<td colspan="2" align="left" class="Sub_head" width="100%">ASM</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="ADMIN_ASM" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_ASU.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            		<% } %>  
            		
            		<!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module										                    		
            		<% if (arraySize_BLOG.intValue() > 0) { %>
            		<tr>
            			<td align="left">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead" >
            						<td colspan="2" align="left" class="Sub_head" width="100%">Blog</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="ADMIN_BLOG" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_BLOG.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            		<% } %>
            		
            		-->
            	</table></td> <% } %>
          <% if ((arraySize_ADF.intValue() > 0 || arraySize_ASU.intValue() > 0 || arraySize_ACP.intValue() > 0 || arraySize_AO.intValue() > 0 || arraySize_ASM.intValue() > 0 ||arraySize_BLOG.intValue() > 0) && arraySize_WDF.intValue() > 0) { %>
          </tr>
          <tr>
          <% } %>
          <% if (arraySize_WDF.intValue() > 0) { %>
          	<td colspan="2" class="body_detail_text">
          		<table width="100%" bgcolor="white">
          			<tr><td>&nbsp;</td></tr>
            		<tr>
            			<td align="left" class="heading_thread" width="100%"><strong>Website</strong></td>
            		</tr>
            		<tr>
            			<td align="left">
            				<table width="100%" class="Box" bgcolor="white">
            					<tr class="Subhead">
            						<td colspan="2" align="left" class="Sub_head" width="100%">Discussion Forum</td>
            					</tr>
            					<logic:iterate id="objFuncBean" indexId="idx" name="WEBSITE_DF" scope="request" type="org.apache.struts.util.LabelValueBean">
            							<% if (idx.intValue()%2==0){ %><tr><% } %>
            								<td align="left" class="heading_attributes" width="50%" nowrap>
            									<logic:equal name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions" onclick="this.checked=true">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:equal>
            									<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
            										<html:multibox property="strFunctions">
            											<bean:write name="objFuncBean" property="label" />
            										</html:multibox><bean:write name="objFuncBean" property="value" />
            									</logic:notEqual>
            								</td>
            							<% if (idx.intValue()%2==1){ %></tr><% } %>
            							<% if (idx.intValue()%2==0 && idx.intValue() == arraySize_WDF.intValue() - 1) { %>
            								<td align="left" class="body_detail_text" width="50%">
            									&nbsp;
            								</td>
            								</tr>
            							<% } %>
            					</logic:iterate>
            				</table>
            			</td>
            		</tr>
            	</table></td><% } %>
          </tr>
          <logic:notEmpty name="RolesForm" property="strRoleID">
          <logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
          <tr>
            <td align="left" valign="top" nowrap class="heading_attributes">Default Role </td>
            <td class="body_detail_text">
            	<html:checkbox property="boolDefault" disabled="true" />
            </td>
            <td class="body_detail_text">&nbsp;</td>
          </tr>
          </logic:notEqual>
          </logic:notEmpty>


      <tr>
        <td colspan="3" height="76" align="left"><p><br>
          <logic:empty name="RolesForm" property="strRoleID">
          	<a href="#" onclick="submitForm('<%= OIRolesConstants.DO_CREATE %>')">
          </logic:empty>
          <logic:notEmpty name="RolesForm" property="strRoleID">
          	<logic:present name="error" scope="request" >
          		<a href="#" onclick="submitForm('<%= OIRolesConstants.DO_CREATE %>')">
          	</logic:present>
          	<logic:notPresent name="error" scope="request" >
          		<a href="#" onclick="submitForm('<%= OIRolesConstants.DO_EDIT %>')">
          	</logic:notPresent>
          </logic:notEmpty>
          <img src="<bean:message key="OIFM.docroot"/>/images/but_save.gif" border="0" alt="Save"></a> 
          	<logic:notEmpty name="RolesForm" property="strRoleID">
          		<logic:notEqual name="RolesForm" property="strRoleID" value="ADMIN">
          			<logic:match name="canDelete" scope="request" value="true">
          				<a href="#" onclick="doDelete('<%= OIRolesConstants.DO_DELETE %>')">
          			</logic:match>
          			<logic:notMatch name="canDelete" scope="request" value="true">
          				<a href="#" onclick="noDelete()">
          			</logic:notMatch>
          			<img src="<bean:message key="OIFM.docroot"/>/images/but_delete.gif" border="0" alt="Delete"></a>
          		</logic:notEqual>
          	</logic:notEmpty>
          	<a href="<bean:message key="OIFM.contextroot" />/Roles.do"><img src="<bean:message key="OIFM.docroot"/>/images/but_Cancel.gif" border="0" alt="Cancel"></a> </p>
          <p>&nbsp;</p></td>
      </tr>

        </table></td>
      </tr>
    </table>
 	</td>
	</tr>
</table>

</html:form>
