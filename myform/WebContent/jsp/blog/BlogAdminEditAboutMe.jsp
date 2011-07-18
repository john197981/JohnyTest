<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
 
<script language="javascript">
	function fnSubmit()
	{
		document.OIFormIndividualBlogAboutUs.hiddenAction.value = 'UPDATE_AUTHOR_DETAILS';
		document.OIFormIndividualBlogAboutUs.submit();
	}
	function doRemovePicture()
	{
		document.OIFormIndividualBlogAboutUs.hiddenAction.value = 'REMOVE_PICTURE';
		document.OIFormIndividualBlogAboutUs.submit();
	}
</script>
 
<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td class="Orange_fade">About Me</td>
  </tr>
</table>
<table width="857" border="0" cellspacing="0" cellpadding="0">

<tr>
	<td align="left" valign="top" bgcolor="#f7f8fc">
 		<table width="857" height="400" border="0" cellspacing="0" cellpadding="0">
       	<tr>
    		<td width="30" class="Grey_fade">&nbsp;</td>
        	<td width="648" class="Grey_fade" valign="top">
        		<html:form action="/IndividualBlogAboutUs.do" enctype="multipart/form-data">
        		<table border="0" cellspacing="0" cellpadding="0">
        		<tr>
        			<td class="body_detail_text">
        				<logic:present name="error" scope="request" >
		        			<b><bean:message name="error" scope="request"/></b>
		        		</logic:present>
		        		<logic:present name="success" scope="request" >
		        			<b><bean:write name="success" scope="request"/></b>
		        		</logic:present>
        				&nbsp;
        			</td>
        		</tr>
        		<tr>
        			<td bgcolor="#F0F8FF" class="Sub_head"><bean:write name="OIFormIndividualBlogAboutUs" property="authorUserName" /></td>
        		</tr>
        		<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td class="body_detail_text">About Me <br />
        				<html:textarea property="authorDescription" cols="60" rows="5"></html:textarea></td>
        		</tr>
        		<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td class="body_detail_text">
        				<html:checkbox property="authorNotifyEmail" value="Y" />
        				Notification emails
        			</td>
        		</tr>
        		<tr><td>&nbsp;</td></tr>
        		<tr>
        			<td class="body_detail_text">Photo <br />
        				<html:file property="fileUpload" ></html:file></td>
        		</tr>
        		<tr><td>&nbsp;</td></tr>
        		<logic:notEmpty name="OIFormIndividualBlogAboutUs" property="authorImageFileName">
        		<tr>
        			<td class="body_detail_text">Existing <br />
        				<img src="<bean:message key="OIFM.contextroot" />/IndividualBlogAboutUs.do?hiddenAction=READ_PICTURE&authorImageFileName=<bean:write name="OIFormIndividualBlogAboutUs" property="authorImageFileName" />"><br />
        				<a href="#" class="special_body_link" onclick="doRemovePicture()">Remove Photograph</a>
        				</td>
        		</tr>
        		<tr><td>&nbsp;</td></tr>
				</logic:notEmpty>
        		<tr>
        			<td class="body_detail_text"><a href="#" onclick="fnSubmit();"><img src="<bean:message key="OIFM.docroot"/>/images/but_submit.gif" border="0" alt = "Submit"></a></td>
        		</tr>
        		</table>
        		<html:hidden property="hiddenAction"/>
        		<html:hidden property="authorImageFileName"/>
        		</html:form>
        	</td>
            <td width="16" class="Blue">&nbsp;</td>
            <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				<jsp:include page="/jsp/blog/BlogAdminRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Home" /> </jsp:include>
			</td>
        </tr>
        </table>
	</td>
</tr>
</table>

