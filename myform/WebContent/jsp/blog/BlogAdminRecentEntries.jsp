<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.blog.OIBlogConstants" %>

<script language="javascript" >
var strDocRoot = '<bean:message key="OIFM.contextroot"/>'
function submitForm(hidAction)
{
	var frm = document.OIBlogAdminRecentEntriesForm;
	frm.hiddenAction.value = hidAction;
	frm.submit();
}

</script>

<table width="857" border="0" cellspacing="0" cellpadding="0">
  <tr>
     <td class="Orange_fade">Authors Page</td>
  </tr>
</table>
<table width="857" border="0" cellspacing="0" cellpadding="0">
<tr>
	<td align="left" valign="top" bgcolor="#f7f8fc">
 		<table width="857" height="400" border="0" cellspacing="0" cellpadding="0">
          	<tr>
    		<td width="30"  class="Grey_fade">&nbsp;</td>
        	<td width="324" height="300px" class="Grey_fade" valign="top">
	        	<table border="0" width="324" height="300px" cellspacing="0" cellpadding="0">
	        	<tr valign="top" height="100px">
	        		<td class="Sub_head" height="100px">&nbsp;</td>
	        	</tr>

	        	<tr valign="top" height="100px">
	        		<td class="Sub_head" height="100px">Recent Entries</td>
	        	</tr>
				<logic:present name="recent_entries">
				<logic:iterate id="objBA" name="recent_entries" type="com.oifm.blog.OIBABlogAdminRecentEntries" scope="request" indexId="rowNum" >
					<tr>
                        <td align="left" class="Heading_Attributes" valign="top" style="word-wrap:break-word;width:350px">
		          			<bean:write name="objBA" property="entryTitle" />&nbsp;&nbsp;
							<bean:write name="objBA" property="entryDate"/> 
						</td>
                    </tr>

				</logic:iterate>
				</logic:present>
				<logic:notPresent name="recent_entries">
				<tr>
                    <td align="left" class="Heading_Attributes" valign="top">
						No Recent Entries
					</td>
                </tr>
				
				
				</logic:notPresent >
	        	
	        	<tr>
	        		<td class="Sub_head" height="100px">Recent Comments</td>
	        	</tr>
				<logic:present name="recent_comments">
				<logic:iterate id="objBA" name="recent_comments" type="com.oifm.blog.OIBABlogAdminRecentEntries" scope="request" indexId="rowNum" >
					<tr>
                        <td align="left" class="Heading_Attributes" valign="top" style="word-wrap:break-word;width:350px">
		          			<bean:write name="objBA" property="commentor" />&nbsp;&nbsp;
							<bean:write name="objBA" property="commentDate"/> 
						</td>
                    </tr>

				</logic:iterate>
				</logic:present>
				<logic:notPresent name="recent_comments">
				<tr>
                    <td align="left" class="Heading_Attributes" valign="top">
						No Recent Comments
					</td>
                </tr>
				
				
				</logic:notPresent >

	        	</table>
        			
        	</td>
        	<td width="324"  class="Grey_fade" valign="top">
        		<html:form action="BlogAdminRecentEntries" method="post">
				<html:hidden property="hiddenAction" />
	        	<table border="0" width="324" height="100px" cellspacing="0" cellpadding="0">
	        	<tr>
	        		<td class="Heading_Attributes" valign="bottom">No of Post to show on Individual Home Page</td>	
	        	</tr>
	        	<tr>
	        		<td class="Heading_Attributes" valign="top">
	        			<html:text property="noOfPosts" styleClass="text_box" size="5" maxlength="15"></html:text>
					</td>	
	        	</tr>
	        	<tr>
	        		<td class="Heading_Attributes" valign="top">
				   		<a href="#" onclick="submitForm('apply')"><img src="<bean:message key="OIFM.docroot"/>/images/btn_Save.gif" border="0" alt ="Save"></a>          			
					</td>	
	        	</tr>
	
	        	</table>
	        	</html:form>
        	</td>
            <td width="16" class="Blue">&nbsp;</td>
            <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				<jsp:include page="/jsp/blog/BlogAdminRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Home" /> </jsp:include>
				<p class="Address_body">&nbsp;</p></td>
        </tr>
        	
        

        </table>
	</td>
</tr>
</table>  

