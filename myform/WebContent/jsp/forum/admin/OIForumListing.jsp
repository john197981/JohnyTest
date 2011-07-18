<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<script type="text/javascript">
<!-- //-->
	function fnSubmit2(actionName,hiddenAction)
	{
		document.tempForm1.hiddenAction.value=hiddenAction;
		document.tempForm1.action=actionName;
		document.tempForm1.submit();
	}
	function fnSubmit(actionName,categoryId,hiddenAction)
	{
		document.tempForm.categoryId.value=categoryId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmitBoard(actionName,boardId,hiddenAction)
	{
		document.tempForm.boardId.value=boardId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
	function fnSubmitTopic(actionName,topicId,hiddenAction)
	{
		document.tempForm.topicId.value=topicId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}

</script>
<form name="tempForm1" method="post">
	<input type="hidden" name="hiddenAction">
</form>
<form name="tempForm" method="post">
	<input type="hidden" name="categoryId">
	<input type="hidden" name="boardId">
	<input type="hidden" name="topicId">
	<input type="hidden" name="hiddenAction">
</form>

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
						<td colspan="5">Category / Boards / Topics</td>
 					</tr>
  				
  				<tr class="subhead">
					<td width="20%" class="sub_head">Category</td>
					<td width="11%" class="sub_head">Board</td>
					<td width="16%" class="sub_head">Division</td>
					<td width="35%" class="sub_head">Topic Name </td>
					<td width="18%" class="sub_head">Date Created </td>
				</tr>
				<logic:present name="ForumListing" property="arOIFormForumListingHelper1" scope="request">
					<logic:iterate id="forum1" name="ForumListing" property="arOIFormForumListingHelper1" scope="request" type="com.oifm.forum.admin.OIFormForumListingHelper1">
						<tr>
							  <td align="left" valign="top" class="Heading_Topic" colspan="5">
							  <% boolean flagCategory=false; %>
								<logic:present name="functionList" scope="session">
									<logic:iterate id="fnList" name="functionList" scope="session">
										<% 
											if (flagCategory == false && (fnList.equals("MTNCTBD"))) 
											{
												flagCategory=true;
										%>
											<a class="Heading_Topic" href='#' onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminForumViewModifyCategoryAction.do?','<bean:write name="forum1" property="categoryId"/>','populate');" >
												<bean:write name="forum1" property="categoryName"/>
											</a>
										<%
											}
										%>
									</logic:iterate>
								</logic:present>
											<%
												if (flagCategory == false) 
												{
											%>
													<bean:write name="forum1" property="categoryName"/>
											<%
												}
											%>
										  </td>
  									</tr>
									<logic:present name="forum1" property="arOIFormForumListingHelper2">
										<logic:iterate id="forum2" name="forum1" property="arOIFormForumListingHelper2" type="com.oifm.forum.admin.OIFormForumListingHelper2">
											<tr>
												<td align="left" valign="top" class="body_detail_text"></td>
												<td align="left" valign="top" class="body_detail_text">
								                  <% boolean flagBoard=false; %>
													<logic:present name="functionList" scope="session">
														<logic:iterate id="fnList" name="functionList" scope="session">
															<% 
																if (flagBoard == false && flagCategory == false && (fnList.equals("MTNCTBD") || fnList.equals("MNTBORD"))) 
																{
																	flagBoard=true;
															%>
																<logic:present name="forum2" property="linkFlag">
																	<logic:equal name="forum2" property="linkFlag" value="true">
																		<a class="special_body_link" href='#' onclick="javascript:fnSubmitBoard('<bean:message key="OIFM.contextroot" />/adminForumViewModifyBoardAction.do?','<bean:write name="forum2" property="boardId"/>','populate');" >
																			<bean:write name="forum2" property="boardName"/>
																		</a>
																	</logic:equal>
																	<logic:notEqual name="forum2" property="linkFlag" value="true">
																		<bean:write name="forum2" property="boardName"/>
																	</logic:notEqual>
																</logic:present>
																<logic:notPresent name="forum2" property="linkFlag">
																	<a class="special_body_link" href='#' onclick="javascript:fnSubmitBoard('<bean:message key="OIFM.contextroot" />/adminForumViewModifyBoardAction.do?','<bean:write name="forum2" property="boardId"/>','populate');" >
																		<bean:write name="forum2" property="boardName"/>
																	</a>
																</logic:notPresent>
															<%
																}
																if (flagBoard == false && flagCategory == true && (fnList.equals("MTNCTBD") || fnList.equals("MNTBORD"))) 
																{
																	flagBoard=true;
															%>
																	<a class="special_body_link" href='#' onclick="javascript:fnSubmitBoard('<bean:message key="OIFM.contextroot" />/adminForumViewModifyBoardAction.do?','<bean:write name="forum2" property="boardId"/>','populate');" >
																		<bean:write name="forum2" property="boardName"/>
																	</a>
															<%
																}
															%>
														</logic:iterate>
													</logic:present>
													<%
														if (flagBoard == false) 
														{
													%>
															<bean:write name="forum2" property="boardName"/>
													<%
														}
													%>
												</td>
												<td align="left" valign="top" class="heading_attributes">
													<bean:write name="forum2" property="division"/>
												</td>
												<td align="left" valign="top" class="body_detail_text"></td>
												<td align="left" valign="top" class="body_detail_text"></td>
											</tr>
											<logic:present name="forum2" property="arOIFormForumListingHelper3">
												<logic:iterate id="forum3" name="forum2" property="arOIFormForumListingHelper3" type="com.oifm.forum.admin.OIFormForumListingHelper3">
													<tr>
														<td align="left" valign="top" class="body_detail_text"></td>
														<td align="left" valign="top" class="body_detail_text"></td>
														<td align="left" valign="top" class="body_detail_text"></td>
														<td class="heading_attributes">
									                  <% boolean flagTopic=false; %>
														<logic:present name="functionList" scope="session">
															<logic:iterate id="fnList" name="functionList" scope="session">
																<% 
																	if (flagTopic == false && flagCategory == false && (fnList.equals("MTNTOPI"))) 
																	{
																		flagTopic=true;
																%>
																	<logic:present name="forum2" property="linkFlag">
																		<logic:equal name="forum2" property="linkFlag" value="true">
																			<a class="special_body_link" href='#' onclick="javascript:fnSubmitTopic('<bean:message key="OIFM.contextroot" />/adminForumViewModifyTopicAction.do?','<bean:write name="forum3" property="topicId"/>','populate');" >
																				<bean:write name="forum3" property="topicName"/>
																			</a>
																		</logic:equal>
																		<logic:notEqual name="forum2" property="linkFlag" value="true">
																			<bean:write name="forum3" property="topicName"/>
																		</logic:notEqual>
																	</logic:present>
																	<logic:notPresent name="forum2" property="linkFlag">
																		<a class="special_body_link" href='#' onclick="javascript:fnSubmitTopic('<bean:message key="OIFM.contextroot" />/adminForumViewModifyTopicAction.do?','<bean:write name="forum3" property="topicId"/>','populate');" >
																			<bean:write name="forum3" property="topicName"/>
																		</a>
																	</logic:notPresent>
																<%
																	}
																	if (flagTopic == false && flagCategory == true && (fnList.equals("MTNTOPI"))) 
																	{
																		flagTopic=true;
																%>
																		<a class="special_body_link" href='#' onclick="javascript:fnSubmitTopic('<bean:message key="OIFM.contextroot" />/adminForumViewModifyTopicAction.do?','<bean:write name="forum3" property="topicId"/>','populate');" >
																			<bean:write name="forum3" property="topicName"/>
																		</a>
																<%
																	}
																%>
															</logic:iterate>
														</logic:present>
														<%
															if (flagTopic == false) 
															{
														%>
																<bean:write name="forum3" property="topicName"/>
														<%
															}
														%>
														</td>
														<td class="heading_attributes">
															<bean:write name="forum3" property="createdOn"/>
														</td>
													</tr>
													<tr height="6" class="tbdvr"><td colspan="5">&nbsp;</td></tr>
												</logic:iterate>
											</logic:present>
										</logic:iterate>
									</logic:present>
								</logic:iterate>
							</logic:present>
							<tr>
								<td colspan="5">&nbsp;</td>
							</tr>
						
 				<tr>
					<td align="left" colspan="5">
						<logic:present name="functionList" scope="session">
							<logic:iterate id="fnList" name="functionList" scope="session">
								<logic:match name="fnList" value="MTNCTBD">
									<a href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/adminForumCreateCategoryAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/new_Category.gif' border="0" alt="Create Category"></a> 
									<a href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/adminForumCreateBoardAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/btn_create_board.gif' border="0" alt="Create Board"></a>
								</logic:match>
								<logic:match name="fnList" value="MTNTOPI">
									<a href='#' onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/adminForumCreateTopicAction.do','populate');"><img src='<bean:message key="OIFM.docroot" />/images/btn_create_topic.gif' border="0" alt="Create Topic"></a>
								</logic:match>
							</logic:iterate>
						</logic:present>
					</td>
				</tr>
				</table>
			</td>
		</tr>
		<tr height="30"><td colspan="5"></td></tr>
	</table>
	</td>
	</tr>
</table>
