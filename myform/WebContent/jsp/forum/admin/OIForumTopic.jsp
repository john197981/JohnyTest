<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %> 
<%@ page import="com.oifm.utility.OIDBRegistry" %>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript">
	function fnSubmit3(actionName,hiddenAction)
	{
		document.ForumTopicForm.flag.value="T";
		document.ForumTopicForm.hiddenAction.value=hiddenAction;
		document.ForumTopicForm.action='<%= OIDBRegistry.getValues("OIFM.contextroot") %>' + actionName;
		document.ForumTopicForm.submit();
	}
	function fnSubmit2(actionName,hiddenAction)
	{
		if (document.ForumTopicForm.categoryId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.SAVE.CHECK.CATEGORY") %>');
			document.ForumTopicForm.categoryId.focus();
			return;
		}
		document.ForumTopicForm.flag.value="T";
		document.ForumTopicForm.hiddenAction.value=hiddenAction;
		document.ForumTopicForm.action='<%= OIDBRegistry.getValues("OIFM.contextroot") %>' + actionName;
		document.ForumTopicForm.submit();
	}
	function fnClear()
	{
		document.ForumTopicForm.reset();
	}
	function fnSubmit(actionName,ac)
	{
		if (document.ForumTopicForm.categoryId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.SAVE.CHECK.CATEGORY") %>');
			document.ForumTopicForm.categoryId.focus();
			return;
		}
		else if (document.ForumTopicForm.boardId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.SAVE.CHECK.BOARD") %>');
			document.ForumTopicForm.boardId.focus();
			return;
		}
		else if(Trim(document.ForumTopicForm.topicName.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.SAVE.CHECK.TOPIC") %>');
			document.ForumTopicForm.topicName.focus();
			return;
		}
		else
		{
			document.ForumTopicForm.hiddenAction.value=ac;
			document.ForumTopicForm.action=actionName;
			document.ForumTopicForm.submit();
		}
	}
	function fnDelete(url,ac)
	{
		if (window.confirm('<%= OIDBRegistry.getValues("OI.FORUM.DELETE.TOPIC") %>')==false)
		{
			return;
		}
		else
		{
			document.ForumTopicForm.hiddenAction.value=ac;
			document.ForumTopicForm.action=url;
			document.ForumTopicForm.submit();
		}
	}

</script>
<html:form action="/adminForumCreateTopicAction.do">

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
						<td colspan="2">Category / Boards / Topics Maintainance</td>
 					</tr>
					<tr  class="sub_head">
						<td colspan="2" class="sub_head">
							<logic:notPresent name="ForumTopicForm" property="topicId">
								Create Topic 
							</logic:notPresent>
							<logic:present name="ForumTopicForm" property="topicId">
								View / Modify Topic 
							</logic:present>
						</td>
					</tr>
							<tr>
								<td width="22%" class="heading_attributes">Category</td>
								<td width="78%" class="body_detail_text">
									<bean:define id="ar" name="ForumTopicForm" property="arCategoryID" />
									<logic:notPresent name="ForumTopicForm" property="topicId">
										<html:select name="ForumTopicForm" property="categoryId" onchange="javascript:fnSubmit2('/adminForumCreateTopicAction.do','populate');" styleClass="Text">
											<html:options collection="ar" property="value" labelProperty="label"  />
										</html:select>
									</logic:notPresent>
									<logic:present name="ForumTopicForm" property="topicId">
										<html:select name="ForumTopicForm" property="categoryId" onchange="javascript:fnSubmit2('/adminForumViewModifyTopicAction.do','populate');" styleClass="Text">
											<html:options collection="ar" property="value" labelProperty="label"  />
										</html:select>
									</logic:present>
								</td>
							</tr>
							<tr>
								<td class="heading_attributes">Board</td>
								<td class="body_detail_text">
									<bean:define id="ar1" name="ForumTopicForm" property="arBoardId" />
									<html:select name="ForumTopicForm" property="boardId" styleClass="Text">
										<html:options collection="ar1" property="value" labelProperty="label"  />
									</html:select>
								</td>
							</tr>
							<tr>
								<td class="heading_attributes">Topic Name </td>
								<td class="body_detail_text">
									<html:text name="ForumTopicForm" property="topicName" size="75" maxlength="75" styleClass="Text_box"></html:text>
								<html:hidden name="ForumTopicForm" property="topicId"/>
								<html:hidden name="ForumTopicForm" property="hiddenAction"/>
								<input type="hidden" name="flag" value="F">
								</td>
							</tr>							
							<tr>
								<td class="heading_attributes">Topic Description </td>
								<td class="body_detail_text">
									<html:text name="ForumTopicForm" property="topicDesc" size="100" maxlength="1000" styleClass="Text_box"></html:text>
								</td>
							</tr>
							<tr>
								<td class="heading_attributes">Moderation Required </td>
								<td class="body_detail_text">
									<html:checkbox name="ForumTopicForm" property="modRequired"></html:checkbox>
								</td>
							</tr>
<tr height="30"><td colspan="2"></td></tr>
				<tr>
					<td height="35" align="left" colspan="2">
						<logic:notPresent name="ForumTopicForm" property="topicId">
							<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminForumCreateTopicAction.do','save');">
								<img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt="Save"></a>
						</logic:notPresent>
						<logic:present name="ForumTopicForm" property="topicId">
							<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminForumViewModifyTopicAction.do','update');">
								<img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt="Save"></a>
							<a href="#" onClick="Javascript:fnDelete('<bean:message key="OIFM.contextroot" />/adminForumViewModifyTopicAction.do','delete')">
								<img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" alt="Delete"></a>
						</logic:present>
						<a href="#" onclick="javascript:fnSubmit3('/adminForumListing.do','populate');">
							<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt="Cancel"></a>
						<a href="javascript:fnClear()">
							<img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" alt="Reset"></a>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr height="30"><td colspan="2"></td></tr>
	</table>
	</td>
	</tr>
</table>
</html:form>

<logic:present name="error" scope="request">
	<script language="javascript">
		window.alert('<bean:write name="error" scope="request" />');
	</script>
</logic:present>
