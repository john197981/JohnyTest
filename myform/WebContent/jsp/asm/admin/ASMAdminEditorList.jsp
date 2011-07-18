<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script language="JavaScript" type="text/JavaScript">
<!--

function submitForm(id) {
	document.ASMFormEditor.hiddenAction.value = 'EDIT';
	document.ASMFormEditor.id.value = id;
	document.ASMFormEditor.submit();
}
//-->
</script>

<html:form action="/ASMEditor.do" method="post">

	<html:hidden property="hiddenAction" />
	<html:hidden property="id"/>
	
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
						<td><div align="left">Editor's Note</div></td>
						<td><div align="right"><a class="Table_head" href="#" onclick="submitForm('0')">Add New</a></div></td>
					</tr>
												<tr bgcolor="#F0F8FF" class="Sub_head">
													<td width="15%" align="left">Date</td>
													<td width="85%" align="left" nowrap>Title</td>
												</tr>
												<logic:iterate id="note" name="list" indexId="idx" type="com.oifm.asm.ASMBAEditor">
													<tr class="Heading_Thread" >
														<td align="center"><div align="left"><bean:write name="note" property="editon" /></div></td>
														<td><a class="Heading_Thread" href="#" onclick="submitForm('<bean:write name="note" property="id" />')"><bean:write name="note" property="topic" /></a></td>
													</tr>
												</logic:iterate>

				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</html:form>
