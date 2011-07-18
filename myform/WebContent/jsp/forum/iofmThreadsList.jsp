<%--
/**
 * FileName			: iofmThreadsList.jsp
 * Author      		: Suresh Kumar.R
 * Created Date 	: 9 Aug 2005
 * Description 		: This page used to display the List of Threads
 * Version			: 1.0
 **/
--%>
 

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.forum.OIForumConstants" %>

<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/ThreadList.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/ForumThread.js'></script>

<script>
	var strDocRoot ='<bean:message key="OIFM.contextroot" />';
	var contextRoot = '<bean:message key="OIFM.contextroot" />';

	function listThread(hidMode){

		var url1='<bean:message key="OIFM.contextroot" />'+'/WebForumListingAction.do?hiddenAction=populate&hidMode='+hidMode;
 		document.ThreadForm.target="_self";
		document.ThreadForm.action=url1
		document.ThreadForm.submit();

	} 
	function fnGetMember(userid)
	{
	  	 var strUrl = '/MemberProfileAction.do?nric='+userid+'&hiddenAction=populate&isEncrpyt=yes'
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=890,height=280,left=0,top=0,resizable=no,scrollbars=yes');
  		 help_window.focus();
   }
</script>	  


<body>
	
		
		
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">View discussion threads </td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="45" valign="top" class="orange">&nbsp;				
			</td>
            <td width="664" valign="top" class="orange"><p>&nbsp;</p>
              <span class="Highlight_body">
				<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_topic.gif'> 
				<bean:write name="ThreadForm" property="strTopicName"/><br>
			 	<logic:present name="ThreadForm" scope="request"> 
					<logic:notEqual name="ThreadForm" property="strTopicDesc" value="">
						(<bean:write name="ThreadForm" property="strTopicDesc"/>)
 					</logic:notEqual>
				</logic:present>
  
			 </span>
			 <br></p>
			</td>
            <td width="148" valign="top" class="orange">&nbsp;</td>
          </tr>
    <html:form action ="/ThreadList">      
          <tr>
            <td colspan="3" class="Grey_fade">&nbsp;</td>
          </tr>
          <tr>
            <td colspan="3" align="left" valign="top" >
			<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="5" bgcolor="white">
			<tr>
			<td class="Box" align="center">
			<table width="98%" border="0" cellspacing="0" cellpadding="2">
              <tr>
                <td width="14%" valign="top">
								<a href="#" onClick='javascript:submitNewThreadForm("<%= OIForumConstants.THREAD_MAINTAIN_DO %>","<%= OIForumConstants.THREAD_EDIT %>","<bean:write name="ThreadForm" property="strTopicId"/>");'>
									<img src='<bean:message key="OIFM.docroot" />/images/but_New_thread.gif' border="0" alt="Create a new Thread"  ></a>				
				 </td>
				  <td width="51%" align="right" valign="top"><a href="#" onclick="listThread('ViewUserPosting')"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_ownPosting.gif' alt="View all your book marked threads" border="0"></a>&nbsp;&nbsp;&nbsp;</td>
			<td width="10%" align="right" valign="top" nowrap><table width="250"  border="0" cellspacing="0" cellpadding="2" class="box">
                  <tr>
                     <td width="5%">&nbsp;</td>
                    <td width="38%" valign="top" align="right" class="Special_body_link">Postings in Last </td>
                    <td width="5%" class="Special_body_link">
						<a class="Special_body_link" href="#" onclick="listThread('12')">12</a>
				</td>
                    <td width="4%"><span class="Special_body_text"><img src='<bean:message key="OIFM.docroot" />/images/break.gif'></span></td>
                    <td width="4%" class="Special_body_link">
					<a class="Special_body_link" href="#" onclick="listThread('24')">24</a></td>
                    <td width="4%"><span class="Special_body_text"><img src='<bean:message key="OIFM.docroot" />/images/break.gif'></span></td>
                    <td width="5%" class="Special_body_link">
<a class="Special_body_link" href="#" onclick="listThread('72')">72</a></td>
                    <td width="10%" valign="top" align="right" class="Special_body_link">Hours </td>
                  </tr>
					
                </table>                  </td>
                </tr>
              <tr>
                <td colspan="2" align="left" valign="middle" nowrap class="Special_body_text">
				<logic:present name="CatBoardTopic" scope="request" >
					<bean:define id="objCatBoardTopic" name="CatBoardTopic" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a class="Special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.FORUM_HOME_DO %>?hiddenAction=populate#C<bean:write name="objCatBoardTopic" property="strCategoryId" />' >
						<bean:write name="objCatBoardTopic" property="strCategory" /></a> 
					<font class="Special_body_link">-&gt;</font>
					<a class="Special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.FORUM_HOME_DO %>?hiddenAction=populate#B<bean:write name="objCatBoardTopic" property="strBoardId" />' >
						<bean:write name="objCatBoardTopic" property="strBoard" /></a> 
				</logic:present>
				 </td>
				 <td valign="bottom" align="right">
					 <logic:greaterThan name="totalPage" value="1">
					 <!--Pagination start -->
					 <logic:present name="currentPage" scope="request">
 					 <table  border="0" cellpadding="2" cellspacing="2" align="right" height="8" bgcolor="#F4F0EA">
						<tr>
							<td nowrap valign="bottom" class="Page_text">Pages</td>
							<logic:present name="previousSet" scope="request">
							<logic:equal name="previousSet" scope="request" value="true">
							<td nowrap valign="bottom"> 
								<a class="Page_link" href='javascript:fnPagination(<bean:write name="previousPage" scope="request"/>);'>&laquo;Previous</a>
							 </td>
							</logic:equal>  
							</logic:present>
							<logic:present name="arPage" scope="request">
							<logic:iterate id="no" name="arPage" scope="request">
							<%	
								String currentPage=(String) request.getAttribute("pageNo");
								String temp = (String) no;
								if (! currentPage.trim().equals(temp.trim()))
								   {
									%>
								 <td nowrap valign="bottom">
								<a class="Page_link" href='javascript:fnPagination(<bean:write name="no" />);'><bean:write name="no" />
								</a>
								</td>
								<%
								}	
								else
								{
								%>
								<td nowrap valign="bottom"><font color="red" size="2px"><b>
								<bean:write name="no" />
								<script>nPagNum='<bean:write name="no"/>'</script>
								</b></font></td>
								<%
								}
								%>
								</logic:iterate>
								</logic:present>
								<logic:present name="nextSet" scope="request">
								<logic:equal name="nextSet" scope="request" value="true">
								<td nowrap valign="bottom"> 
									<a class="Page_link" href='javascript:fnPagination(<bean:write name="nextPage" scope="request"/>);'>Next&raquo;</a>
								</td>
								</logic:equal>
								</logic:present>
								</td>
								</tr>
								</table>									
						</logic:present>							
						</logic:greaterThan > 
				  <!-- pagination ends here -->				 
				 </td>

				</tr>
				</table>
			</td>
			</tr>
			
              <tr>
                <td class="Box" colspan="3">
				
					
						<logic:notPresent name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
					<table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1">	
							<tr>
								<td align="center" colspan="7" class="Text">
									<br><font color="red"><bean:message key="NoRecordLoad"/></font>
								</td>
							</tr>
						</table>	 
						</logic:notPresent>
						
					<logic:present name="<%=com.oifm.common.OILabelConstants.OBJARBV%>" scope="request">
						 <table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1">	
                  
                  <tr>
                    <td class="Table_head">&nbsp;</td>
                    <td class="Table_head">&nbsp;</td>
                    <td valign="top" nowrap class="Table_head">Threads 
					
					<a href="#">
					<logic:notEqual name="ThreadForm" property="hidSortKey" value="TITLE" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('TITLE','ASC');" alt="Show in Ascending Order">
					</logic:notEqual>
					<logic:equal name="ThreadForm" property="hidSortKey" value="TITLE" scope="request">
						<logic:equal name="ThreadForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('TITLE','DESC');" alt="Show in Descending Order">
						</logic:equal>
						<logic:equal name="ThreadForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('TITLE','ASC');" alt="No Order">
						</logic:equal>
					</logic:equal></a>                       
					
					 
					</td>
                    <td width="13%" valign="top" nowrap class="Table_head">Last post by  
					<a href="#"> 
					<logic:equal name="ThreadForm" property="hidSortKey" value="POSTNAME" scope="request">
						<logic:equal name="ThreadForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF'  border="0" onClick="javascript:fnSort('POSTNAME','DESC');" alt="Show in Descending Order">
						</logic:equal>
						<logic:equal name="ThreadForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF'  border="0" onClick="javascript:fnSort('POSTNAME','ASC');" alt="Show in Ascending Order">
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="ThreadForm" property="hidSortKey" value="POSTNAME" scope="request">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF' border="0" onClick="javascript:fnSort('POSTNAME','ASC');" alt="No Sort">
					</logic:notEqual></a>
					
					
					</td>
                    <td width="17%" valign="top" nowrap class="Table_head">Last post on 
					<a href="#"> 
					<logic:equal name="ThreadForm" property="hidSortKey" value="LASTPOST_ON" scope="request">
						<logic:equal name="ThreadForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('LASTPOST_ON','DESC');" alt="Show in Descending Order">
						</logic:equal>
						<logic:equal name="ThreadForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('LASTPOST_ON','ASC');" alt="Show in Ascending Order">
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="ThreadForm" property="hidSortKey" value="LASTPOST_ON" scope="request">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF' border="0" onClick="javascript:fnSort('LASTPOST_ON','ASC');" alt="No Order">
					</logic:notEqual></a>

</td>
                    <td width="9%" align="left" valign="top" nowrap class="Table_head">Replies 
												<a href="#">
					<logic:equal name="ThreadForm" property="hidSortKey" value="REPLIES" scope="request">
						<logic:equal name="ThreadForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('REPLIES','DESC');" alt="Show in Descending Order">
						</logic:equal>
						<logic:equal name="ThreadForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('REPLIES','ASC');" alt="Show in Ascending Order">
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="ThreadForm" property="hidSortKey" value="REPLIES" scope="request">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF' border="0" onClick="javascript:fnSort('REPLIES','ASC');" alt="No Order">
					</logic:notEqual></a>	 	

</td>
                    <td width="8%" align="left" valign="top" nowrap class="Table_head">Views  
												<a href="#">
					<logic:equal name="ThreadForm" property="hidSortKey" value="HITS" scope="request">
						<logic:equal name="ThreadForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('HITS','DESC');" alt="Show in Descending Order">
						</logic:equal>
						<logic:equal name="ThreadForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('HITS','ASC');" alt="Show in Ascending Order">
						</logic:equal>
					</logic:equal>
					<logic:notEqual name="ThreadForm" property="hidSortKey" value="HITS" scope="request">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF' border="0" onClick="javascript:fnSort('HITS','ASC');" alt="No Order">
					</logic:notEqual></a>
					
					
					</td>
                  </tr>

	<!-- Admin Sticky Thread Start-->
	<logic:iterate id="Thread" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.forum.OIBAThread" scope="request" indexId="rowNum">
	 
		<logic:equal name="Thread" property="strImgMsg" value="ADMIN">				   
                  <tr>
                    <td width="2%" valign="top" class="Heading_Attributes">
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif'> 
					 </td>

                    <td width="4%" valign="top" class="Heading_Attributes">
					
					<logic:equal name="Thread" property="strImgThread" value="A">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_Already_postmessages.gif' alt="Already Post Messages">
					</logic:equal>
					<logic:equal name="Thread" property="strImgThread" value="B">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/lock.gif' alt="Lock">
					</logic:equal>
					<logic:equal name="Thread" property="strImgThread" value="C">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif' alt="Private">
					</logic:equal>
					 </td>
                    <td width="47%" valign="top"> 
					<a class="Heading_Thread" href="#" onClick="javascript:fnHits('<%=Thread.getStrThreadId()%>')">
						<bean:write name="Thread" property="strTitle"/></a> 
					<logic:present name="Thread" property="strModerationStat">
						<logic:equal name="Thread" property="strModerationStat" value="Y">
							<br><span class="smalltext"> Moderated by  <a href="#" onClick="javascript:fnGetMember('<%=Thread.getStrModeratedBy()%>')">
							<bean:write name="Thread" property="strModName"/></a></span> <br><br>
						</logic:equal>
					</logic:present>
					 </td>

                    <td valign="top" class="Heading_Attributes">
					<a href="#" class="Heading_Attr_A" onClick="javascript:fnGetMember('<%=Thread.getStrPostedBy()%>')">
					 <bean:write name="Thread" property="strPostedName"/></a>
					 <br>(<bean:write name="Thread" property="totalPosts"/> Posts)
					 </td>
                    <td align="left" valign="top" class="Heading_Attributes" nowrap><bean:write name="Thread" property="strLastpostOn"/>
					</td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strReplies"/></td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strHits"/></td>
                  </tr>
                   
		</logic:equal>
	</logic:iterate>
	<!-- Admin Sticky Thread end-->

	<!-- Sticky Thread Start-->
		<logic:iterate id="Thread" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.forum.OIBAThread" scope="request" indexId="rowNum">

			<logic:equal name="Thread" property="strImgMsg" value="A">
                  <tr>
                    <td width="2%" valign="top" class="Heading_Attributes">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif'> 			 
					 </td>

                    <td width="4%" valign="top" class="Heading_Attributes">
					
					<logic:equal name="Thread" property="strImgThread" value="A">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_Already_postmessages.gif' alt="Already Post Messages">
					</logic:equal>
					<logic:equal name="Thread" property="strImgThread" value="B">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/lock.gif' alt="Lock">
					</logic:equal>
					<logic:equal name="Thread" property="strImgThread" value="C">
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif' alt="Private">
					</logic:equal>
				 </td>
                    <td width="50%" valign="top"> 
					<a href="#" class="Heading_Thread" onClick="javascript:fnHits('<%=Thread.getStrThreadId()%>')">
						<bean:write name="Thread" property="strTitle"/></a> 
					<logic:present name="Thread" property="strModerationStat">
						<logic:equal name="Thread" property="strModerationStat" value="Y">
							<br><span class="smalltext"> Moderated by  <a href="#" onClick="javascript:fnGetMember('<%=Thread.getStrModeratedBy()%>')">
							<bean:write name="Thread" property="strModName"/></a></span> <br><br>
						</logic:equal>
					</logic:present>
					 
					 </td>

                    <td valign="top" class="Heading_Attributes">
   					<a href="#" class="Heading_Attr_A" onClick="javascript:fnGetMember('<%=Thread.getStrPostedBy()%>')">
					 <bean:write name="Thread" property="strPostedName"/></a>
					 <br>(<bean:write name="Thread" property="totalPosts"/> Posts)
					 </td>
                    <td align="left" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strLastpostOn"/></td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strReplies"/></td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strHits"/></td>
                  </tr>

				</logic:equal>
			</logic:iterate>
			<!-- Sticky Thread end-->

			<!-- Not a Sticky Thread Start-->
			<logic:iterate id="Thread" name="<%= com.oifm.common.OILabelConstants.OBJARBV %>" type="com.oifm.forum.OIBAThread" scope="request" indexId="rowNum">     
				<logic:notEqual name="Thread" property="strImgMsg" value="A">
				<logic:notEqual name="Thread" property="strImgMsg" value="ADMIN">
                  <tr>
						<!--Column 1 Messages -->

                    <td width="2%" valign="top" class="Heading_Attributes">
						
 				<logic:equal name="Thread" property="strImgMsg" value="A">
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif'> 
				</logic:equal>
				<logic:equal name="Thread" property="strImgMsg" value="HL">	 
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/Hottest_thread.gif' alt="Hot New Messages">
				</logic:equal>
				<logic:equal name="Thread" property="strImgMsg" value="H">
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/Hottest_thread.gif' alt="Hot New Messages">
				</logic:equal>
				<logic:equal name="Thread" property="strImgMsg" value="L">
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif' alt="New Messages">
				</logic:equal>

					 </td>
		
		<!--Column 2 Threads -->	
                    <td width="4%" valign="top" class="Heading_Attributes">
					
			<logic:equal name="Thread" property="strImgThread" value="A">    		          	 
				<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_Already_postmessages.gif' alt="Already Post Messages">
			</logic:equal>
			<logic:equal name="Thread" property="strImgThread" value="B">
				<img src='<bean:message key="OIFM.docroot" />/images/Icons/lock.gif' alt="Lock">
			</logic:equal>
			<logic:equal name="Thread" property="strImgThread" value="C">
				<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif' alt="Private">
			</logic:equal>

				 </td>
                    <td width="50%" valign="top"> 
					<a href="#" class="Heading_Thread" onClick="javascript:fnHits('<%=Thread.getStrThreadId()%>')">
						<bean:write name="Thread" property="strTitle"/></a> 
					<logic:present name="Thread" property="strModerationStat">
						<logic:equal name="Thread" property="strModerationStat" value="Y">
							<br><span class="smalltext"> Moderated by  <a href="#" onClick="javascript:fnGetMember('<%=Thread.getStrModeratedBy()%>')">
							<bean:write name="Thread" property="strModName"/></a></span> <br><br>
						</logic:equal>
					</logic:present>
					 
					 </td>

                    <td valign="top" class="Heading_Attributes">
   					<a href="#" class="Heading_Attr_A" onClick="javascript:fnGetMember('<%=Thread.getStrPostedBy()%>')">
					 <bean:write name="Thread" property="strPostedName"/></a>
					 <br>(<bean:write name="Thread" property="totalPosts"/> Posts)
					 </td>
                    <td align="left" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strLastpostOn"/></td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strReplies"/></td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="Thread" property="strHits"/></td>
                  </tr>

			</logic:notEqual>
			</logic:notEqual>
		</logic:iterate> 


                  <tr>
                    <td colspan="7">&nbsp;</td>
                    </tr>
				<logic:greaterThan name="totalPage" value="1">
				<tr>
                    <td colspan="7" valign="bottom" align="right" >&nbsp;
                  <!--Pagination start -->
							<logic:present name="currentPage" scope="request">
								<table  border="0" cellpadding="2" cellspacing="2" align="right" bgcolor="#F4F0EA" height="8">
											<tr>
												<td nowrap valign="bottom" class="Page_text">Pages</td>
												<logic:present name="previousSet" scope="request">
													<logic:equal name="previousSet" scope="request" value="true">
														<td nowrap valign="bottom"> 
															<a class="Page_link" href='javascript:fnPagination(<bean:write name="previousPage" scope="request"/>);'>&laquo;Previous</a>
														</td>
													</logic:equal>  
												</logic:present>
												<logic:present name="arPage" scope="request">
													<logic:iterate id="no" name="arPage" scope="request">
														<%	
															String currentPage=(String) request.getAttribute("pageNo");
															String temp = (String) no;
															if (! currentPage.trim().equals(temp.trim()))
															{
														%>
																<td nowrap valign="bottom">
																	<a class="Page_link" href='javascript:fnPagination(<bean:write name="no" />);'><bean:write name="no" />
																	</a>
																</td>
														<%
															}	
															else
															{
														%>
																<td nowrap valign="bottom"><font color="red" size="2px"><b>
																	<bean:write name="no"/>
																	<script>nPagNum='<bean:write name="no"/>'</script>
																</b></font></td>
														<%
															}
														%>
													</logic:iterate>
												</logic:present>
												<logic:present name="nextSet" scope="request">
													<logic:equal name="nextSet" scope="request" value="true">
														<td nowrap valign="bottom"> 
															<a class="Page_link" href='javascript:fnPagination(<bean:write name="nextPage" scope="request"/>);'>Next&raquo;</a>
														</td>
													</logic:equal>
												</logic:present>
											</td>
										</tr>
									</table>
									<!--input type="hidden" name="pageNo"-->
								</logic:present>							
				  <!-- pagination ends here -->
				</td>
					</tr>
					</logic:greaterThan >                  
                </table>
				</td>
              </tr>
            </table>
			</td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="white">&nbsp;<input type="hidden" name="pageNo"></td>
            <td align="left" valign="top" bgcolor="white" colspan = 2>
			      <TABLE cellSpacing=0 cellPadding=0 width="98%" align=center border=0 >
        <TBODY>
        <TR>
			<TD colspan="4" class="heading_thread"><u>Legend</u></TD>
			</TR>
        <TR>
          <TD><img src='<bean:message key="OIFM.docroot" />/images/Icons/Hottest_thread.gif' width="20" height="20"></TD>
          <TD nowrap class=smalltext>Hottest messages</TD>
          <TD><span class="Heading_Attributes"><IMG height=13 
            src='<bean:message key="OIFM.docroot" />/images/Icons/icon_Already_postmessages.gif' 
            width=13></span></TD>
          <TD height=19 nowrap class=smalltext>You Have Posted in this Thread </TD>
        </TR>
        <TR>
          <TD><img src='<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif' width="20" height="20"></TD>
          <TD height=17 nowrap class=smalltext>Latest messages </TD>
          <TD width="3%"><span class="Heading_Attributes"><IMG src='<bean:message key="OIFM.docroot" />/images/Icons/lock.gif' width="14" height="16"></span></TD>
          <TD width="74%" height=17 nowrap class=smalltext>Locked Thread </TD>
        </TR>
        <TR>
          <TD width="3%"><img src='<bean:message key="OIFM.docroot" />/images/Icons/HQ_Replies.gif' width="20" height="20"></TD>
          <TD width="20%" nowrap class=smalltext>HQ Replies </TD>
          <TD><span class="Heading_Attributes"><IMG height=17 
            src='<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif' width=18></span></TD>
          <TD height=19 nowrap class=smalltext>Private thread</TD>
        </TR>
		<TR>
		  <TD width="3%"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif' width="20" height="20"></TD>
		  <TD colspan="3" width="20%" nowrap class=smalltext>Sticky Message </TD>
		   
		</TR>        
        </TBODY></TABLE>			
		</td>
 <!--
            <td align="left" valign="top" bgcolor="#f7f8fc">&nbsp;</td>
          </tr>          
        </table>
		</div>
		</td>
		-->
          
    </tr>
	</logic:present>
  </table>
  <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
<html:hidden property="hidSortKey"/>
<html:hidden property="hidOrder"/>
<html:hidden property="hiddenAction"/>
<html:hidden property="strTopicId"/>
<html:hidden property="strTopicName"/>
<html:hidden property="strThreadId"/>
<input type="hidden" name="threadList" value="">
<input type="hidden" name="threadListType" value="">
<input type="hidden" name="winObj" value="">
</html:form>