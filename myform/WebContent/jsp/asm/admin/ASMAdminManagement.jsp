<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script language="Javascript"
	src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<SCRIPT LANGUAGE="JavaScript"
	src='<bean:message key="OIFM.docroot" />/js/Common.js'></SCRIPT>

<script language="JavaScript">
<!--

var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 
	}

function doSave() {
	if (document.ASMFormManagement.id.value == 0)
		document.ASMFormManagement.hiddenAction.value = 'DO_CREATE';
	else
		document.ASMFormManagement.hiddenAction.value = 'DO_EDIT';

	var flag = true;
	if (flag) flag = checkBlank(document.ASMFormManagement.heading, 'Heading');
	if (flag) flag = checkBlank(document.ASMFormManagement.subheading, 'Subheading');
	if (flag) flag = checkBlank(document.ASMFormManagement.name, 'Name');
	if (flag) flag = checkBlank(document.ASMFormManagement.designation, 'Designation');
	if (flag) flag = checkBlank(document.ASMFormManagement.division, 'Division');
	if (flag) flag = checkBlank(document.ASMFormManagement.profile, 'Profile');
	if (flag)
		document.ASMFormManagement.submit();
	
}

function doDelete() {
	var flag = confirm('<bean:message key="ASM.DELETE.MSG" />');
	if (flag) {
		document.ASMFormManagement.hiddenAction.value='DO_DELETE';
		document.ASMFormManagement.submit();
	} else return false;
}

function doRemovePhoto() {
	document.ASMFormManagement.hiddenAction.value='REM_PHOTO';
	document.ASMFormManagement.submit();
}

//-->
</script>

<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<html:form action="/ASMManagement.do" method="post"
	enctype="multipart/form-data">

	<html:hidden property="hiddenAction" />
	<html:hidden property="id" />
	<html:hidden property="photograph" />
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
	<table width="98%" border="0" cellpadding="0"
		cellspacing="0"  bgcolor="#f7f8fc">
		<tr>
			<td class="Box"> 
			 <table width="100%" border="0" cellpadding="0"
						cellspacing="0"  bgcolor="#f7f8fc">
					
						<logic:present name="error" scope="request">
							<tr align="left"  >
								<td>&nbsp;</td>
							</tr>
							<tr>
								<td width="100%" class="body_detail_text" colspan="3">
								<div align="center"><b><bean:message name="error"
									scope="request" /></b></div>
								</td>
							</tr>
							<tr>
								<td>&nbsp;</td>
							</tr>
						 
						</logic:present>
			<tr align="left" valign="top" class="Table_head">
			<td colspan="2">Senior Management Profile</td>
			</tr>
			<tr align="left" valign="top">
				<td width="15%" nowrap class="Heading_Thread">Heading <span
					class="style1">*</span></td>
				<td width="85%" nowrap class="body_detail_text"><html:text
					property="heading" size="70" maxlength="50" styleClass="text_box"/></td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">Subheading <span
					class="style1">*</span></td>
				<td nowrap class="body_detail_text"><html:text
					property="subheading" size="70" maxlength="70" styleClass="text_box"/></td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">Name<span class="style1"> *</span></td>
				<td nowrap class="body_detail_text"><html:text property="name"
					size="70" maxlength="66" styleClass="text_box"/></td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">Designation <span
					class="style1">*</span></td>
				<td nowrap class="body_detail_text"><html:text
					property="designation" size="70" maxlength="50" styleClass="text_box"/></td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">Division <span class="style1">*</span></td>
				<td nowrap class="body_detail_text"><html:text
					property="division" size="70" maxlength="70" styleClass="text_box"/></td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">Profile <span class="style1">*</span></td>
				<td nowrap class="body_detail_text"><html:textarea
					property="profile" styleId="taContentId" cols="65"
					rows="20" styleClass="text_box"/> <script language="javascript">
						var config = new Object();
						config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
						config.debug = 0;
						editor_generate('taContentId',config);
					</script></td>
			</tr>

			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">URL</td>
				<td nowrap class="body_detail_text">http://<html:text
					property="divisionurl" size="64" maxlength="70" styleClass="text_box"/></td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread">Photograph</td>
				<td nowrap class="body_detail_text"><html:file
					property="fileName" size="50" styleClass="text_box"></html:file> (Max 100 KB)</td>
			</tr>
			<logic:notEmpty name="ASMFormManagement"
				property="photograph">
				<tr align="left" valign="top">
					<td nowrap class="Heading_Thread">Existing</td>
					<td width="23%" nowrap class="body_detail_text"><img
						src="<bean:message key="OIFM.contextroot" />/ASMManagement.do?hiddenAction=PHOTO&photograph=<bean:write name="ASMFormManagement" property="photograph" />"></td>
				</tr>
				<tr align="left" valign="top">
					<td nowrap class="body_detail_text">&nbsp;</td>
					<td nowrap class="body_detail_text">
						<a href="#" class="special_body_link" onclick="doRemovePhoto()">Remove Photograph</a>
					</td>
				</tr>
			</logic:notEmpty>
			<tr align="left" valign="top">
				<td nowrap class="body_detail_text">&nbsp;</td>
				<td nowrap class="body_detail_text">&nbsp;</td>
			</tr>
			<tr align="left" valign="top">
				<td nowrap class="Heading_Thread"><span class="style1">*</span>
				Mandatory</td>
				<td nowrap class="body_detail_text"><a href="#"
					onclick="doSave()"><img
					src="<bean:message key="OIFM.docroot" />/images/but_save.gif" ALT="Save"
					border="0"></a> <logic:greaterThan
					name="ASMFormManagement" property="id" value="0">
					<a href="#" onclick="doDelete()"><img
						src="<bean:message key="OIFM.docroot" />/images/but_delete.gif" ALT="Delete"
						border="0"></a>
				</logic:greaterThan> <a
					href="<bean:message key="OIFM.contextroot" />/ASMManagement.do?hiddenAction=ADMIN"><img
					src="<bean:message key="OIFM.docroot" />/images/but_Cancel.gif" ALT="Cancel"
					border="0"></a>
					</td>
				</tr>				
			</table>
			</td>
 		</tr>
	</table>

</html:form>
