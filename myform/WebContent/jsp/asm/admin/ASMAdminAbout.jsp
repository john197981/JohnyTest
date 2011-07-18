<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<script language="JavaScript" type="text/JavaScript">
<!--
function doSave() {
	if (document.ASMFormAbout.id.value == 0)
		document.ASMFormAbout.hiddenAction.value = 'DO_CREATE';
	else
		document.ASMFormAbout.hiddenAction.value = 'DO_EDIT';

	var flag = true;
	if (flag) flag = checkBlank(document.ASMFormAbout.question, 'Topic');
	if (flag) flag = checkBlank(document.ASMFormAbout.answer, 'Explanation');
	if (flag)
		document.ASMFormAbout.submit();
}

function doDelete() {
	var flag = confirm('<bean:message key="ASM.DELETE.MSG" />');
	if (flag) {
		document.ASMFormAbout.hiddenAction.value='DO_DELETE';
		document.ASMFormAbout.submit();
	} else return false;
}
//-->
</script>

<style type="text/css">
<!--
.style1 {color: #FF0000}
-->
</style>

<html:form action="/ASMAbout.do" method="post">

<html:hidden property="hiddenAction"/>
<html:hidden property="id"/>
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

            <table width="98%"  border="0"  cellpadding="0" cellspacing="0" bgcolor="#f7f8fc">
                <tr>
                  <td class="Box"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
							<table width="100%"  border="0" cellpadding="3" cellspacing="0" bgcolor="#f7f8fc">
                                            <tr align="left" valign="top" class="Table_head">
                                              <td colspan="2">About ASM </td>
                                            </tr>
                                            <tr align="left" valign="top">
                                              <td width="13%" class="Heading_Thread">Topic <span class="style1">*</span> </td>
                                              <td width="87%" nowrap class="body_detail_text"><html:text property="question" size="105" maxlength="256" styleClass="text_box" /></td>
                                            </tr>
                                            <tr align="left" valign="top">
                                              <td width="13%" valign="top" nowrap class="Heading_Thread">Explanation <span class="style1">* </span></td>
                                              <td width="87%" nowrap class="body_detail_text"><label>
                                                <html:textarea property="answer" cols="70" rows="20"  />
                                                </td>
                                            </tr>
                                            <tr align="left" valign="top">
                                              <td class="body_detail_text" valign="top">&nbsp;</td>
                                              <td nowrap class="body_detail_text">&nbsp;</td>
                                            </tr>
                                            <tr align="left" valign="top">
                                              <td class="Heading_Thread" valign="top"><span class="style1">*</span> Mandatory </td>
                                              <td nowrap class="body_detail_text">
                                              	<a href="#" onclick="doSave()"><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" ALT="Save""></a>
                                              	<logic:greaterThan name="ASMFormAbout" property="id" value="0"><a href="#" onclick="doDelete()"><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" ALT="Delete"></a></logic:greaterThan>
                                              	<a href="<bean:message key="OIFM.contextroot" />/ASMAbout.do?hiddenAction=ADMIN"><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" ALT="Cancel"></a> 
					</td>
				</tr>				
			</table>
			</td>
 		</tr>
	</table>
</html:form>