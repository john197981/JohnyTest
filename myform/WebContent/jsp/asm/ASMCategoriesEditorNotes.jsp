<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<script>
function fnSubmit(actionName,ac)
	{
		if(document.ASMFormCategoriesOfEditorNotes.noteId.value=="")
		{
			alert("Please select a Note");
			document.ASMFormCategoriesOfEditorNotes.noteId.focus();
			return false;
		}
		document.ASMFormCategoriesOfEditorNotes.action=actionName+"?hiddenAction="+ac;
		document.ASMFormCategoriesOfEditorNotes.submit();
		
	}
	

</script>

<SCRIPT LANGUAGE="JavaScript"
	src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></SCRIPT>
<html:form action="/ASMCategoriesOfEditorNotes.do">

	<table width="857" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td class="Orange_fade"><a name="top">Categories of Editor's
			Notes</a></td>
		</tr>
	</table>
	<table width="857" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td valign="top" class="orange">
			<div align="justify" class="Highlight_body">
			<blockquote>
			<p><br>
			<logic:present name="ModuleDesc" scope="request">
				<bean:write name="ModuleDesc" scope="request" filter="false" />
			</logic:present> <br>
			<br>
			</p>

			</blockquote>
			</div>
			</td>
		</tr>
		<tr>
			<td align="left" valign="top" bgcolor="#f7f8fc">
			<table width="857" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="648" class="Grey_fade">&nbsp;</td>
					<td width="16" class="Blue">&nbsp;</td>
					<td width="193" rowspan="2" align="left" valign="top" class="Blue">


					<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true">
						<jsp:param name="pageName" value="About" />
					</jsp:include>


					<p class="Address_body">&nbsp;</p>
					</td>
				</tr>
				<tr>
					<td align="left" valign="top" bgcolor="#f7f8fc">
					<table width="98%" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<tr>
							<td class="Box">


							<table width="100%" cellpadding="0" cellspacing="0"
								bgcolor="white">
								<tr>

									<td>
									<table width="100%" cellpadding="5" cellspacing="0"
										bgcolor="white">

										<tr>
											<td colspan="2" valign="top" class="Table_head">
											Categories of Editor's Notes</td>
										</tr>


										<tr class="BodyText">
											<td nowrap class="heading_attributes">Editor's Notes</td>
											<td class="BodyText"><bean:define id="ar"
												name="ASMFormCategoriesOfEditorNotes" property="arnoteId" />
											<html:select name="ASMFormCategoriesOfEditorNotes"
												property="noteId" 
												styleClass="Text">
												<html:options collection="ar" property="value"
													labelProperty="label" />
											</html:select></td>
										</tr>
										<tr>
											<td class="BodyText" height="25" align="right" colspan="5">
											<a href="#"
												onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMCategoriesOfEditorNotes.do','search');">
											<img
												src='<bean:message key="OIFM.docroot" />/images/btn_Search.gif'
												border="0" alt="Search"></a></td>
										</tr>
										<tr height="20" class="BodyText">
											<td colspan="2"></td>
										</tr>
										<tr>
											<td nowrap colspan="2" valign="top" class="Table_head">Latest
											Letter(s)</td>
										</tr>
										<tr height="20" class="BodyText">
											<logic:present name="ASMFormCategoriesOfEditorNotes"
												property="noteId">
												<td width="5%">&nbsp;</td>
												<td colspan="4" align="justify" class="body_detail_text">
												<br>
												<bean:write name="ASMFormCategoriesOfEditorNotes"
													property="noteDetail" filter="false" />
												</td>
											</logic:present>
											

										</tr>
									</table>
									</td>
								</tr>
							</table>
							</td>
						</tr>
					</table>
					</td>
					<td class="Blue">&nbsp;</td>
				</tr>
			</table>
			</td>
		</tr>
	</table>
	<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
</html:form>
<a name="bottom"></a>
<script language="javascript">
	function fnRightPanelSubmit(actionName,hiddenAction)
	{

 		document.ASMFormHome.hiddenAction.value=hiddenAction;

		document.ASMFormHome.action=actionName+"?hiddenAction="+hiddenAction;

		document.ASMFormHome.submit();
	}
</script>



