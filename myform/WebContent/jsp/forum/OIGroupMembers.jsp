<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.common.OIAddEmailsConstants" %>
<%@ page import="com.oifm.useradmin.OIGroupsConstants" %>

 
<html:html>
<head>
	<title>Group Member List</title>
	<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
	<script language="javascript">
		function sort(sortKey, sortOrder) {
			document.GroupsForm.sortKey.value = sortKey;
			document.GroupsForm.sortOrder.value = sortOrder;
			document.GroupsForm.hiddenAction.value = '<%= OIGroupsConstants.LIST_MEMBERS %>';
			document.GroupsForm.submit();
		}
	</script> 
	<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
</head>

<body>
<html:form action ="/GroupMembers.do"  method="post">

<html:hidden property="hiddenAction"/>
<html:hidden property="groupId" />
<html:hidden property="sortKey" />
<html:hidden property="sortOrder" />
<br>
<table width="98%"  border="0" cellspacing="0" cellpadding="0" align="center">
  	<tr>
		<td>
			<table width="100%"  border="0" cellspacing="0" cellpadding="0" bgcolor="white" class="Box">

      			<tr>
        			<td colspan="7"  class="Table_Sub_head"> Group Member List </td>
        		</tr>
      			<tr>
					<td colspan="7"   > &nbsp;</td>
         		</tr>

        <logic:present name="MembersList" scope="request">
        	<tr>
          		<td width="10%" class="Table_Head">
          			NRIC
          			<logic:equal name="GroupsForm" property="sortKey" value="0" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(0,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt="Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(0,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt="Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="0" scope="request">
						<a href="#" onClick="sort(0,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt="No sort"></a>
					</logic:notEqual>
          		</td>
          		<td width="30%" class="Table_Head">
          			Name
          			<logic:equal name="GroupsForm" property="sortKey" value="1" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(1,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt="Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(1,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt="Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="1" scope="request">
						<a href="#" onClick="sort(1,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt="No sort"></a>
					</logic:notEqual>
          		</td>
          		<td width="20%" class="Table_Head">
          			Division
          			<logic:equal name="GroupsForm" property="sortKey" value="2" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(2,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt="Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(2,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt="Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="2" scope="request">
						<a href="#" onClick="sort(2,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt="No sort"></a>
					</logic:notEqual>
          		</td>
          		<td width="20%" class="Table_Head">
          			Email ID
          			<logic:equal name="GroupsForm" property="sortKey" value="3" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(3,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt="Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(3,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt="Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="3" scope="request">
						<a href="#" onClick="sort(3,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt="No sort"></a>
					</logic:notEqual>
          		</td>
          		<td width="20%" class="Table_Head">
          			No Of Posting
          			<logic:equal name="GroupsForm" property="sortKey" value="4" scope="request">
						<logic:equal name="GroupsForm" property="sortOrder" value="0" scope="request">
							<a href="#" onClick="sort(4,1);"><img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif' width="17" height="16" border="0" alt="Down"></a>
						</logic:equal>
						<logic:equal name="GroupsForm" property="sortOrder" value="1" scope="request" >
							<a href="#" onClick="sort(4,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_uparrow.gif' width="17" height="16" border="0" alt="Up"></a>
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="GroupsForm" property="sortKey" value="4" scope="request">
						<a href="#" onClick="sort(4,0);"><img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' width="17" height="16" border="0" alt="No sort">
					</logic:notEqual>
          		</td>
        	</tr>
        	<logic:iterate id="objMembers" name="MembersList" indexId="idx" type="com.oifm.useradmin.OIGroupMembersBean">
        		<tr>
          			<td valign="top" class="Special_body_link"><a class="Special_body_link" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="objMembers" property="userID" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="objMembers" property="userID" /></a></td>
          			<td valign="top" class="body_detail_text"><bean:write name="objMembers" property="name" /></td>
          			<td valign="top" class="body_detail_text"><bean:write name="objMembers" property="division" /></td>
          			<td valign="top" class="body_detail_text"><bean:write name="objMembers" property="emailID" /></td>
          			<td valign="top" class="body_detail_text"><bean:write name="objMembers" property="numOfPostings" /></td>
        		</tr>
        	</logic:iterate>
        </logic:present>
        <logic:notPresent name="MembersList" scope="request">
        	<tr>
          		<td class="body_detail_text">
          			<p>&nbsp;</p>
          			<p>No members</p>
          			<p>&nbsp;</p>
          		</td>
        </tr>
        </logic:notPresent>
      </table>
		</td>
	</tr>
	<tr>
		<td>&nbsp;</td>
	</tr>
	<tr>
		<td class="Text" align="center">
			<a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/but_ok.gif" border="0" alt="Close"></a>
 		</td>
		
	</tr>
</table>    
</html:form>
</body>
</html:html>

