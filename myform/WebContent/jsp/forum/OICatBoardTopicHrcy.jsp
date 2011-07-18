<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.forum.OIForumConstants" %>

<style type="text/css">
.BoxTablee {
	border: opx solid #0099FF;
}
</style>
 
<SCRIPT LANGUAGE="JavaScript">
<!--
function refreshParent(topicId) {
	var frm = document.ThreadForm;

	if(frm.hiddenAction.value == '<%= OIForumConstants.GOTO_TOPIC %>') {
		window.opener.location.href = '<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_LIST_DO %>?strTopicId='+topicId;
		self.close();
	} else if(frm.hiddenAction.value == '<%= OIForumConstants.MOVE_THREAD_TO_TOPIC %>') {
		frm.strTopicId.value = topicId;
		frm.submit();
	}
}
//-->
</SCRIPT>

<html:form action="/ThreadMaintain.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strThreadId" />
<html:hidden property="strTopicId" />

<table width="250"  align="center" border="0" cellpadding="0" cellspacing="0" >
<logic:present name="error" scope="request" >
<tr>
	<td width="100%" class="BodyText" colspan="3">
	<b><bean:message name="error" scope="request"/></b>
	</td>
</tr>
</logic:present>
<logic:notPresent name="error" scope="request" >
	<logic:present name="TopicHrcy" scope="request" >
	<bean:size id="hrcySize" name="TopicHrcy" />
		<logic:greaterThan name="hrcySize" value="0">
			<logic:iterate id="objTopicHrcy" name="TopicHrcy" indexId="ind" type="com.oifm.forum.OIBATopicHrcy">
<tr>
	<td>
	<table class="BoxTablee" width="100%" border="0" align="center" cellpadding="2" cellspacing="0" bordercolor="#ffffff">
				<logic:equal name="objTopicHrcy" property="level" value="1">
	<tr>
		<td class="Text"> 
				<b><bean:write name="objTopicHrcy" property="strTitle" /></b>
		</td>
	</tr>
			</logic:equal>
			<logic:equal name="objTopicHrcy" property="level" value="2">
	<tr>
		<td class="BodyText1">
			<font size="1"><b><bean:write name="objTopicHrcy" property="strTitle"/></b> </font>
		</td>
	</tr>
			</logic:equal>
			<logic:equal name="objTopicHrcy" property="level" value="3">
	<tr>
		<td class="BodyText">
			<a href="#" onClick='refreshParent("<bean:write name="objTopicHrcy" property="strId" />")'><bean:write name="objTopicHrcy" property="strTitle" /></a>
		</td>
	</tr>
			</logic:equal>
	</table>
	</td>
</tr>
			</logic:iterate>
		</logic:greaterThan>
	</logic:present>
</logic:notPresent>
            
</table>

</html:form>