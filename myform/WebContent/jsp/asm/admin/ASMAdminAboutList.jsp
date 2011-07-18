<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script language="JavaScript" type="text/JavaScript">
<!--
function submitForm(id) {
	document.ASMFormAbout.hiddenAction.value = 'EDIT';
	document.ASMFormAbout.id.value = id;
	document.ASMFormAbout.submit();
}
//-->
</script>
<html:form action="/ASMAbout.do" method="post">

<html:hidden property="hiddenAction"/>
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
													<td><div align="left">About ASM</div></td>
													<td><div align="right"><a class="Table_head" href="#" onclick="submitForm('0')">Add New</a></div></td>
												</tr>
												<tr bgcolor="#F0F8FF" class="Sub_head">
 													<td colspan="2" align="left" nowrap>Content</td>
												</tr>
												<logic:iterate id="about" name="list" indexId="idx" type="com.oifm.asm.ASMBAAbout">
													<tr class="Heading_Thread" >
 														<td colspan="2"><a href="#" class="Heading_Thread"  onclick="submitForm('<bean:write name="about" property="id" />')"><bean:write name="about" property="question" /></a></td>
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
