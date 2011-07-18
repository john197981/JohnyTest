<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%
try
{
 %>
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/simpleTxtFormating.js'></script>

<script language="JavaScript" type="text/JavaScript">
<!--
	var maxlimit = 1000;
	function fn_textCounter(field, countfield) 
	{
		if (field.value.length > maxlimit) 
		{
			field.value = field.value.substring(0, maxlimit);
		}
		else
		{
			countfield.value = maxlimit - field.value.length;
		}
	}
	function openNewWindow()
	{
		document.ConfigurationForm.previewDescription.value = document.ConfigurationForm.message.value;
		window.open('<bean:message key="OIFM.docroot" />/html/preview.html','mywindow','left=20,top=20,width=400,height=250,toolbar=0,resizable=0');
	}
	function fnClear()
	{
		document.ConfigurationForm.reset();
	}
	function fnsaveConfMessage(actionName,hiddenAction)
	{
		document.ConfigurationForm.hiddenAction.value=hiddenAction;
		document.ConfigurationForm.action=actionName;
		document.ConfigurationForm.submit();
	}
//-->
</script>

<html:form action="/adminConfiguration.do" method="post">
	<html:hidden property="hiddenAction" />
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
						<td colspan="2"><bean:write name="messageCaption" scope="request" /></td>
 					</tr>
					<tr height="1">
					</tr>
 						<tr>
							<td width="10%" valign="top" nowrap class="heading_attributes">Message</td>
							<td class="body_detail_text">
								<div id="toolbar" style="width: 600 px">
									<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);" onclick="format_sel('b',document.ConfigurationForm.message);"  src='<bean:message key="OIFM.docroot" />/images/bold.gif'  align="middle" alt="click to make your selection bold">
									<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="format_sel('i',document.ConfigurationForm.message);"  src='<bean:message key="OIFM.docroot" />/images/italic.gif'  align="middle"  alt="click to make your selection italic">
									<img class="button" onmouseover="mouseover(this);" onmouseout="mouseout(this);" onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="format_sel('u',document.ConfigurationForm.message);"  src='<bean:message key="OIFM.docroot" />/images/underline.gif'  align="middle"  alt="click to make your selection underline">
									<img class="button"  onmouseover="mouseover(this);"  onmouseout="mouseout(this);"  onmousedown="mousedown(this);"  onmouseup="mouseup(this);"  onclick="insert_link(document.ConfigurationForm.message);"  src='<bean:message key="OIFM.docroot" />/images/hyperlink.gif'  align="middle"  alt="click to add a link">
								</div>
								<INPUT TYPE="hidden" name="previewDescription">
								<!--html:textarea name="message"  scope="request" styleClass="Text" cols="80" rows="10"  onkeydown="fn_textCounter(this.form.message,this.form.numleft);" onkeyup="fn_textCounter(this.form.message,this.form.numleft);" onmouseover="fn_textCounter(this.form.message,this.form.numleft);" onmouseout="fn_textCounter(this.form.message,this.form.numleft);"/-->
								<textarea name="message" style="Text" cols="72" rows="10"  onkeydown="fn_textCounter(this.form.message,this.form.numleft);" onkeyup="fn_textCounter(this.form.message,this.form.numleft);" onmouseover="fn_textCounter(this.form.message,this.form.numleft);" onmouseout="fn_textCounter(this.form.message,this.form.numleft);"><bean:write name="message" scope="request" /></textarea>
								<div align="left">
									<font color="#005BCC">
										No. of characters remaining: 
										<input name="numleft" type="text" size="10" size="5" maxlength="3" value="500" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">
										<input name="exceed" id="exceed" type="hidden" size="60" value="" style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 0px solid #7F9DB9;border-right: 0px solid #7F9DB9;border-left: 0px solid #7F9DB9;border-top:0px solid #7F9DB9; color:red;text-decoration:none">
									</font>
									<a class="special_body_link" href="#" onClick="javascript:openNewWindow();">Preview open window</a>
								</div>
								<input type="hidden" name="messageTag" value='<bean:write name="messageTag" scope="request" />' >
							</td>
						</tr>
						<tr>
							<td colspan="2">&nbsp;</td>
						</tr>
						<tr >

							<td>&nbsp;</td>
							<td>
								<a href="#" onclick="fnsaveConfMessage('<bean:message key="OIFM.contextroot" />/adminConfiguration.do','saveMessage')"><img src="<bean:message key="OIFM.docroot" />/images/but_save.gif" alt="Save"  border="0" ALT = "Save"></a> 
								<a href="#" onclick="fnClear()"><img src="<bean:message key="OIFM.docroot" />/images/but_reset.gif" alt="Reset" border="0" ALT = "Reset"></a>
								<a href="#" onclick="fnsaveConfMessage('<bean:message key="OIFM.contextroot" />/adminConfiguration.do','populate')" >
									<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt = "Cancel"></a>  
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
		fn_textCounter(this.ConfigurationForm.message,this.ConfigurationForm.numleft);
	</script>
</html:form>
<%
}catch(Exception e)
{
	out.println(e.getMessage());
}
%>