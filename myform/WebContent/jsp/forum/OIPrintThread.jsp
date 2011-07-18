<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<br>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" class="Box">
<tr>
	<td height="25" colspan="3" class="Table_head" valign="top" bgcolor="white">
		<bean:define id="objThread" name="ThreadInfo" />
		<bean:write name="objThread" property="strTitle" />
		(<bean:write name="TotalRec" /> Postings)
 	</td>
</tr>
</table>      
 <table width="98%" align="center" border="0" cellpadding="0" cellspacing="0" class="Box">
<tr>
	<td>
	<table width="100%"  border="0" cellspacing="0" cellpadding="0">
<logic:present name="error" scope="request" >
	<tr>
		<td width="100%" class="BodyText" colspan="3">
		<b><bean:message name="error" scope="request"/></b>
		</td>
	</tr>
</logic:present>
<logic:notPresent name="error" scope="request" >
	<logic:present name="PostsList" scope="request" >
		<logic:greaterThan name="TotalRec" value="0">
	<tr>
		<td>
		<table class="Box" width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bgcolor="white">
 			<logic:iterate id="objPosting" name="PostsList" indexId="ind" type="com.oifm.forum.OIBAPosting">
		<tr>
			<td colspan="2"  class="heading_thread">
				<bean:write name="objPosting" property="strNickName" /> &nbsp&nbsp
				<I><bean:write name="objPosting" property="strSignature" /></I>
			</td>
			<td width="30%" class="heading_thread" align="right">
				<bean:write name="objPosting" property="strPostedOn" />
			</td>

		</tr>
        <tr>
			<logic:equal name="objPosting" property="strHQReply" value="Y">
			<td colspan="3" align="left" valign="top" class="body_detail_text">
				</logic:equal>
				<logic:notEqual name="objPosting" property="strHQReply" value="Y">
			<td colspan="3" align="left" valign="top" class="body_detail_text">
				</logic:notEqual>

				 <%= objPosting.getStrPosting() %> 
				 
    	</td>
	</tr>
		<tr>
		<td colspan="3">
		<hr noshade style="color:#CCCCCC" size=0>
		</td>
		</tr>
			</logic:iterate>
			</td>
		</tr>                
        </table>

		</logic:greaterThan>
		<logic:lessEqual name="TotalRec" value="0">
	<tr>
		<td width="100%" class="BodyText" >
			<b><bean:message key="NoRecordLoad"/></b>
		</td>
	</tr>
		</logic:lessEqual>

	</logic:present>
</logic:notPresent>
            
    </table>
	</td>
</tr>
</table>
<SCRIPT LANGUAGE="JavaScript">
<!--
	window.print();
//-->
</SCRIPT>
<br>
<br>
