<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<script language="JavaScript" type="text/JavaScript">
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
	if (document.ASMFormEditor.id.value == 0)
		document.ASMFormEditor.hiddenAction.value = 'DO_CREATE';
	else
		document.ASMFormEditor.hiddenAction.value = 'DO_EDIT';

	var flag = true;
	if (flag) flag = checkBlank(document.ASMFormEditor.editon, "Date");
	if (flag) flag = checkBlank(document.ASMFormEditor.topic, "Topic");
	if (flag) flag = checkBlank(document.ASMFormEditor.content, "Content");
	if (flag) 
		document.ASMFormEditor.submit();
}

function doDelete() {
	var flag = confirm('<bean:message key="ASM.DELETE.MSG" />');
	if (flag) {
		document.ASMFormEditor.hiddenAction.value='DO_DELETE';
		document.ASMFormEditor.submit();
	} else return false;
}

function doCancel() {
	if (document.ASMFormEditor.fromCategory.value == 'Y') {
		document.ASMFormEditor.action = 'ASMCategoriesOfEditorNotes.do?hiddenAction=Adminsearch&noteId='+document.ASMFormEditor.id.value ;
		document.ASMFormEditor.submit();
	} else{
		document.ASMFormEditor.hiddenAction.value='ADMIN';
		document.ASMFormEditor.submit();
	}
}
//-->
</script>
<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<html:form action="/ASMEditor.do" method="post">

	<html:hidden property="hiddenAction" />
	<html:hidden property="id"/>
	<html:hidden property="fromCategory"/>
	 
	 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

	<table width="98%" border="0" cellpadding="0"
		cellspacing="0"  bgcolor="white">
		<tr>
			<td class="Box"> 
					<table width="100%" border="0" cellpadding="0"
						cellspacing="0"  bgcolor="white">
  				<tr align="left" valign="top" class="Table_head">
					<td colspan="2">Editor's Note</td>
				</tr>
				<tr align="left" valign="top">
					<td width="15%" class="Heading_Thread">Date <span
						class="style1">*</span></td>
					<td width="85%" nowrap class="body_detail_text"><html:text property="editon" size="13" maxlength="15" readonly="true" styleClass="text_box" />
						<a href="#" onClick="cal.select(document.ASMFormEditor.editon,'editon','dd-NNN-yyyy'); return false;"><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a>
					</td>
				</tr>
				<tr align="left" >
					<td width="15%" class="Heading_Thread">Title<span
						class="style1"> *</span></td>
					<td width="85%" nowrap class="body_detail_text"><html:text property="topic" size="105" maxlength="256" styleClass="text_box" /><br></td>
					
				</tr>
				<tr align="left" valign="top">
					<td width="15%" nowrap class="Heading_Thread">
					<p><br>
					Message<span class="style1"> *</span></p>
					</td>
					<td width="85%" nowrap class="body_detail_text"><html:textarea property="content" styleId="taContentId" cols="70" rows="20"  />
						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId',config);
						</script>
					</td>
				</tr>
				<tr align="left" valign="top">
					<td nowrap class="body_detail_text">&nbsp;</td>
					<td nowrap class="body_detail_text">&nbsp;</td>
				</tr>
				<tr align="left" valign="top">
					<td nowrap class="Heading_Thread"><span class="style1">*</span>
					Mandatory</td>
					<td nowrap class="body_detail_text">
						<a href="#" onclick="doSave()"><img src="<bean:message key="OIFM.docroot" />/images/but_save.gif" border="0" ALT="Save"></a>
						<logic:greaterThan name="ASMFormEditor" property="id" value="0"><a href="#" onclick="doDelete()"><img src="<bean:message key="OIFM.docroot" />/images/but_delete.gif" border="0" ALT="Delete"></a></logic:greaterThan>
						<a href="#" onclick="doCancel()"><img src="<bean:message key="OIFM.docroot" />/images/but_Cancel.gif" border="0" ALT="Cancel"></a>
						
					</td>
				</tr>				
			</table>
			</td>
 		</tr>
	</table>
	<DIV id="divCalendar" style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>

</html:form>
