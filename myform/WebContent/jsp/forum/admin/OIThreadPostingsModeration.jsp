<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.forum.OIForumConstants" %>


<script language="javascript">

	var contextRoot = '<bean:message key="OIFM.contextroot" />';

	function submitModThreadPostForm(submitUrl, actionName) 
	{
		var frm = document.ModerationForm;
		frm.target="_self";
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.hiddenAction.value = actionName;
		frm.submit();
		return;
	}

	function editModThreadPost(submitUrl, actionName, postId, threadId, strId) 
	{
		var frm = document.ModerationForm;
		var flag = confirm('<%= OIDBRegistry.getValues("OI.FORUM.MOD.CONFIRM") %>');
		if(flag) 
		{
			document.getElementById(strId).checked = true;
			frm.target="_self";
			frm.action= contextRoot+submitUrl;
			frm.hiddenAction.value = actionName;
			frm.strPostId.value = postId;
			frm.strThreadId.value = threadId;
			frm.submit();
		}
		return;
	}
</script>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/ForumThread.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/ForumMail.js'></script>

<html:form action="/ThreadPostMod.do" method="post" >
	<html:hidden property="hiddenAction" />
	<html:hidden property="strPostId" />
	<html:hidden property="strThreadId" />

	<% int i = 0; %>
  	
<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td>
							<logic:equal name="ModerationForm" property="hiddenAction" value="<%= OIForumConstants.POSTS_MOD_LISTING %>">
						          Postings Moderation
							</logic:equal>
							<logic:equal name="ModerationForm" property="hiddenAction" value="<%= OIForumConstants.THREAD_MOD_LISTING %>">
						          Threads Moderation
							</logic:equal>

						</td>
 					</tr>

			<logic:present name="error" scope="request" >
			<tr>
				<td width="100%" class="BodyText" height="35" valign="middle" colspan="3">
					<b><bean:message name="error" scope="request"/></b>
				</td>
			</tr>
		</logic:present>

  					<logic:notPresent name="error" scope="request" >
						<logic:present name="TopicThreadPostsList" scope="request" >
							<bean:size id="TotalRec" name="TopicThreadPostsList"/>

							<logic:greaterThan name="TotalRec" value="0">
								<logic:iterate id="objModThreadPost" name="TopicThreadPostsList" type="com.oifm.forum.OIBAModThreadPosting">
									<logic:equal name="objModThreadPost" property="nextBoard" value="true">
 										<tr class="Subhead">
											<td colspan="3" class="Sub_head">
												<bean:write name="objModThreadPost" property="strBoardName" />
											</td>
										</tr>
									</logic:equal>
									<tr>
										<td colspan="3" class="sub_head">
											<bean:write name="objModThreadPost" property="strTopicName" />
										</td>
									</tr>
									<bean:define id="PostsList" name="objModThreadPost" property="alPostsList" type="java.util.ArrayList"/>
									<logic:iterate id="objPosting" name="PostsList" type="com.oifm.forum.OIBAPosting">
										<input type="hidden" name="objPosting[<%= i %>].strThreadId" value="<%= objPosting.getStrThreadId() %>" >
										<input type="hidden" name="objPosting[<%= i %>].strPostId"  value="<%= objPosting.getStrPostId() %>" >
										<tr>
											<td >
												<table width="100%" height="80"  border="0" align="center" cellpadding="2" cellspacing="0" bordercolor="#ffffff">
													<tr>
														<td  width="160" NOWRAP background='/oi/docroot/images/2boards-bl-lt.jpg' class="BodyText">
															<bean:write name="objPosting" property="strPostedOn" />
														</td>
														<td class="BodyText" colspan="2" valign="bottom" background='/oi/docroot/images/2boards-bl-lt.jpg' >
															<p><bean:write name="objPosting" property="strThread" /></p>
														</td>
													</tr>
												    <tr>
														<logic:equal name="objPosting" property="strHQReply" value="Y">
															<td bgcolor="#E0EBFC" width="150" height="30" align="left" valign="top" class="body_detail_text">
														</logic:equal>
														<logic:notEqual name="objPosting" property="strHQReply" value="Y">
															<td bgcolor="#E0EBFC" width="150" height="30" align="left" valign="top" class="body_detail_text">
														</logic:notEqual>
														<P><STRONG>
															<A class="special_body_link" href="#" onClick='javascript:fnMember("<bean:write name="objPosting" property="strPostedBy" />");'>
															<bean:write name="objPosting" property="strNickName" /></A>
														</STRONG>
														<a class="special_body_link" href="#" onClick='javascript:getiFrame("<bean:write name="objPosting" property="strPostId" />","<bean:write name="objPosting" property="strNickName" />", "P");'>
															<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_pm.gif' border="0" alt="PM"></a><BR><BR>
														<I><bean:write name="objPosting" property="strSignature" /></I>
														<br></P>
														</td>
				
														<logic:equal name="objPosting" property="strHQReply" value="Y">
															<td colspan="2" align="left" width="850" valign="top" class="body_detail_text" id='<bean:write name="objPosting" property="strPostId" />'>
														</logic:equal>
														<logic:notEqual name="objPosting" property="strHQReply" value="Y">
															<td colspan="2" align="left" width="850" valign="top" class="body_detail_text">
														</logic:notEqual>
															<p><%= objPosting.getStrPosting() %></p>
														<DIV Class="heading_attributes" align="right">
															<input type="radio" id="<%= i %>A" name="objPosting[<%= i %>].strApproveStatus" value="A">Approve &nbsp;
															<input type="radio" id="<%= i %>D" name="objPosting[<%= i %>].strApproveStatus" value="D" checked>Defer &nbsp;
															<input type="radio" id="<%= i %>R" name="objPosting[<%= i %>].strApproveStatus" value="R">Reject &nbsp;
															 &nbsp;
															<logic:equal name="ModerationForm" property="hiddenAction" value="<%= OIForumConstants.POSTS_MOD_LISTING %>">
																<A class="special_body_link" HREF="#" onClick='javascript:editModThreadPost("<%= OIForumConstants.THREAD_POST_MOD_DO %>","<%= OIForumConstants.POSTS_MODERATION_EDIT %>", "<bean:write name="objPosting" property="strPostId" />", "", "<%= i %>D")'><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_edit.gif' border="0" alt="Edit"></A>&nbsp;
															</logic:equal>
															<logic:equal name="ModerationForm" property="hiddenAction" value="<%= OIForumConstants.THREAD_MOD_LISTING %>">
																<A class="special_body_link" HREF="#" onClick='javascript:editModThreadPost("<%= OIForumConstants.THREAD_POST_MOD_DO %>","<%= OIForumConstants.THREAD_MODERATION_EDIT %>", "", "<bean:write name="objPosting" property="strThreadId" />", "<%= i %>D")'><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_edit.gif' border="0" alt="Edit"></A>&nbsp;
															</logic:equal>
															<% i++; %>
														</DIV>
													</td>
												</tr>    
									        </table>
								    	</td>
									</tr>
									<tr>
										<td valign="top" align="left" class="body_detail_text" height="1"><hr noshade style="color:#CCCCCC" size=0>

									</tr>
 								</logic:iterate>
							</logic:iterate>
							<tr>
								<td height="15"><p>
									<logic:equal name="ModerationForm" property="hiddenAction" value="<%= OIForumConstants.POSTS_MOD_LISTING %>">
									    <a href="#" onClick='submitModThreadPostForm("<%= OIForumConstants.THREAD_POST_MOD_DO %>", "<%= OIForumConstants.POSTS_MODERATION %>")'><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt="Save"></a>
									</logic:equal>
									<logic:equal name="ModerationForm" property="hiddenAction" value="<%= OIForumConstants.THREAD_MOD_LISTING %>">
										<a href="#" onClick='submitModThreadPostForm("<%= OIForumConstants.THREAD_POST_MOD_DO %>", "<%= OIForumConstants.THREAD_MODERATION %>")'><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt="Save"></a>
									</logic:equal>
									<!-- 					&nbsp;<a href="#"><img src='<bean:message key="OIFM.docroot" />/images/button/btn_Cancel.gif' border="0"></a>
									 -->					
									 </p>
								</td>
							</tr>
						</logic:greaterThan>
						<logic:lessEqual name="TotalRec"  value="0">
							<tr>
								<td width="100%" height="50" valign="middle" class="BodyText" >
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
	</td>
	</tr>
</table>

</html:form>

<logic:greaterThan name="strEditThreadId" value="0">
	<SCRIPT LANGUAGE="JavaScript">
		<!--
			submitModThreadPostEditForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_MODERATION_EDIT %>', '','<bean:write name="strEditThreadId" scope="request"/>');
		//-->
	</SCRIPT>
</logic:greaterThan >
<logic:greaterThan name="strEditPostId" value="0">
	<SCRIPT LANGUAGE="JavaScript">
		<!--
		submitModThreadPostEditForm('<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>','<%= OIForumConstants.POSTS_MODERATION_EDIT %>', '<bean:write name="strEditPostId" scope="request"/>', '');
		//-->
	</SCRIPT>
</logic:greaterThan >
<br>
<br>