<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.forum.OIForumConstants,java.util.ArrayList,com.oifm.forum.OIBVForumListing,com.oifm.utility.OIUtilities" %>

<script>
 
	function listThread(hidMode){
  
		document.homePageDiscussion.hidMode.value=hidMode;
		var url1='<bean:message key="OIFM.contextroot" />'+'/WebForumListingAction.do?hiddenAction=populate&hidMode='+hidMode;
 		document.homePageDiscussion.target="_self";
		document.homePageDiscussion.action=url1
		document.homePageDiscussion.submit();
 	
	}
</script>	  
<% String strMode = "";
if(request.getAttribute("strMode")!=null){
	strMode = String.valueOf(request.getAttribute("strMode"));
} 
%>


		<table width="857" border="0" cellspacing="0" cellpadding="0">
<form name="homePageDiscussion" method="post">
          <tr>
            <td class="Orange_fade">What's Up</td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2" valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br></p>
					<logic:present name="aOIBAConfiguration" scope="request">
						<bean:define id="forumAnnouncement" name="aOIBAConfiguration" scope="request" type="com.oifm.common.OIBAConfiguration"></bean:define>
						<%= forumAnnouncement.getValue() %>
					</logic:present>
                </p>
                <%if(strMode!=null && strMode.equals("12")){%>
                <p>Postings in Last 12 hours</p>
                 <%}else if(strMode!=null && strMode.equals("24")){%>
                <p>Postings in Last 24 hours</p>
                <%}else if(strMode!=null && strMode.equals("72")){%>
                <p>Postings in Last 72 hours</p>
                 <%}else {%><p>We welcome your letters any time, any day. 
                  </p>
                 <%}%>
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td colspan="2" class="Grey_fade">&nbsp;</td>
          </tr>
          
          <tr>
            <td colspan="2" align="left" valign="top" bgcolor="white"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="Box">
				<table width="100%" border="0" cellspacing="0" cellpadding="2">
                  <tr>
                    <td width="50%" valign="top">
						<%if(request.getAttribute("strSplThreadID")!=null && !request.getAttribute("strSplThreadID").equals("")){%>
						<span class="Special_body_text"><font color='red'>Join special discussion : </font></span>&nbsp; <a class="special_body_link" href='<bean:message key="OIFM.contextroot" />
						<%= OIForumConstants.THREAD_MAINTAIN_DO %>?strThreadId=<%=request.getAttribute("strSplThreadID")%>'><%=request.getAttribute("strSplThreadName")%> </a>
						<%}%>
					</td>
                    <td width="50%" align="right" valign="top" nowrap>
                    <table width="100%"  border="0" cellspacing="0" cellpadding="2">
                      <tr>
                 
						 <td width="60%" align="right" valign="top" ><a href="#" onclick="listThread('ViewUserPosting')"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_ownPosting.gif' alt="View all your book marked threads" border="0"></a>&nbsp;&nbsp;&nbsp;</td>
						<td width="30%" align="right" valign="top" nowrap class="Box">
						<table width="250"  border="0" cellspacing="0" cellpadding="2" >
							  <tr>

                         <td width="38%" class="Special_body_text" valign="top" align="right">Postings in Last </td>
                        <td width="5%"   valign="top">
<a class="Special_body_link" href="#" onclick="listThread('12')">12</a>
</td>
                        <td width="4%"><span class="Special_body_text"><img src='<bean:message key="OIFM.docroot" />/images/break.gif' alt="." width="14" height="20"></span></td>
                        <td width="5%"   valign="top">
<a class="Special_body_link" href="#" onclick="listThread('24')">24</a>						
						</td>
                        <td width="4%"><span class="Special_body_text"><img src='<bean:message key="OIFM.docroot" />/images/break.gif' alt="." width="14" height="20"></span></td>
                        <td width="5%"   valign="top">
<a class="Special_body_link" href="#" onclick="listThread('72')">72</a>
</td>
                        <td width="10%" align="right" valign="top" class="Special_body_text">Hours </td>
                      </tr>					
			</table></td></tr>


                    </table></td>
                  </tr>
                </table></td>
              </tr>
			  <tr><td height="5px"></td></tr>
               <tr>
                <td class="Box"><table width="100%"  border="0" align="center" cellpadding="1" cellspacing="1">
                  <tr>
                    <td colspan="4" class="Table_head" nowrap>Category/Topic</td>
                     <td width="34%" class="Table_head" nowrap>Last post </td>
                    <td width="10%" class="Table_head" nowrap>Last post by </td>
                    <td width="11%" align="left" nowrap class="Table_head">Last post on </td>
                    <td width="8%" align="left" class="Table_head">Threads</td>
                  </tr>
				  
				 <logic:present name="arOIBVUpToTopicListing" scope="request">
			
				<logic:iterate id="forumTopic" name="arOIBVUpToTopicListing" scope="request" type="com.oifm.forum.OIBVForumListing">
				<logic:equal name="forumTopic" property="level" value="1">
                  <tr>
                    <td colspan="8" class="Heading_Category">
							<A NAME='#<bean:write name="forumTopic" property="categoryId"/>' />
							 <font size="3"> 
								<bean:write name="forumTopic" property="category"/>
							 </font>
						</td>
                  </tr>
				  </logic:equal>
				  <logic:equal name="forumTopic" property="level" value="2">
                  <tr>
                    <td><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_folder.gif' ></td>
                    <td colspan="7" class="Heading_Board">
						<A NAME='#<bean:write name="forumTopic" property="boardId"/>' />
						<font size="2"><strong>
							<bean:write name="forumTopic" property="boardName"/>
						</strong></font>
						</td>
                  </tr>
				</logic:equal>
				<logic:equal name="forumTopic" property="level" value="3">
                  <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="2%" valign="top"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_topic.gif'  ></td>
                    <td width="30%" valign="top">
						<a class="Heading_Topic" href='<bean:message key="OIFM.contextroot" />/ThreadList.do?strTopicId=<bean:write name="forumTopic" property="topicId" />'>
							<u><bean:write name="forumTopic" property="topicName"/></u>					
 					 </a></td>
                    
					<td valign="top" align="right">
						<logic:equal name="forumTopic" property="icon" value="Sticky">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif'> 
						</logic:equal>
						<logic:equal name="forumTopic" property="icon" value="H">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/Hottest_thread.gif'> 
						</logic:equal>
						<logic:equal name="forumTopic" property="icon" value="L">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif'> 
						</logic:equal>
						<logic:equal name="forumTopic" property="icon" value="AlreadyPosted">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_Already_postmessages.gif'> 
						</logic:equal>
						<logic:equal name="forumTopic" property="icon" value="Locked">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/lock.gif'> 
						</logic:equal>
						<logic:equal name="forumTopic" property="icon" value="Security">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif'> 
						</logic:equal>
					
					 </td>
                    <td valign="top">
						<a class="Heading_Thread" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_MAINTAIN_DO %>?strThreadId=<bean:write name="forumTopic" property="threadId"/>'>
							<bean:write name="forumTopic" property="threadName"/>
						</a> 
					 </td>
                    <td align="left" valign="top" class="Heading_Attributes">
						<logic:present name="forumTopic" property="nickName">
 							<a class="Heading_Attr_A" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="forumTopic" property="postedBy"/>&hiddenAction=populate&isEncrpyt=yes','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );">
								<bean:write name="forumTopic" property="nickName"/>
							</a> 
							<br>
							(<bean:write name="forumTopic" property="totalPosts"/> Posts)
						</logic:present>
					 </td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="forumTopic" property="lastPostOn"/></td>
                    <td align="center" valign="top" class="Heading_Attributes"><bean:write name="forumTopic" property="noThreads"/></td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td height="3" colspan="6" valign="top" class="Heading_Topic">&nbsp;</td>
                  </tr>

					</logic:equal>
				</logic:iterate>
			</logic:present>

			<logic:notPresent name="arOIBVUpToTopicListing" scope="request">
			 <%if(OIUtilities.replaceNull(request.getParameter("hidMode")).equalsIgnoreCase("12") || 
			 OIUtilities.replaceNull(request.getParameter("hidMode")).equalsIgnoreCase("24") || 
			 OIUtilities.replaceNull(request.getParameter("hidMode")).equalsIgnoreCase("72")){%>
			 <tr><td colspan="8" class="Heading_Thread" align="center">
				There is no posting for the past <%=request.getParameter("hidMode")%> hours
				</td></tr>
			 <%}else if(OIUtilities.replaceNull(request.getParameter("hidMode")).equalsIgnoreCase("ViewUserPosting")){ %>
			 <tr><td colspan="8" class="Heading_Thread" align="center">
				There is no posting for this user.
				</td></tr>
			 <%}%>
			</logic:notPresent>


                    <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td valign="top" class="Heading_Topic">&nbsp;</td>
                    <td valign="top" class="Heading_Thread">&nbsp;</td>
                    <td valign="top" class="Heading_Thread">&nbsp;</td>
                    <td align="left" valign="top" class="Heading_Attributes">&nbsp;</td>
                    <td valign="top" class="Heading_Attributes">&nbsp;</td>
                    <td valign="top" class="Heading_Attributes">&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td colspan="2" align="left" valign="top" bgcolor="white">&nbsp;</td>
          </tr>
          <tr>
            <td width="49" align="left" valign="top" bgcolor="white">&nbsp;</td>
            <td width="808" align="left" valign="top" bgcolor="white">
			<logic:present name="arOIBVUpToTopicListing" scope="request">
			<TABLE cellSpacing=0 cellPadding=0 width="98%" align=center border=0>
              <TBODY>
                <TR>
                  <TD colspan="4" class="heading_thread"><u>Legend</u></TD>
                 </TR>
                <TR>
                  <TD><img src='<bean:message key="OIFM.docroot" />/images/Icons/Hottest_thread.gif' width="20" height="20"></TD>
                  <TD nowrap class=smalltext>Hottest messages</TD>
                  <TD><span class="Heading_Attributes"><IMG height=13 
            src='<bean:message key="OIFM.docroot" />/images\Icons\icon_Already_postmessages.gif' 
            width=13></span></TD>
                  <TD height=19 nowrap class=smalltext>You Have Posted in this Thread </TD>
                </TR>
                <TR>
                  <TD><img src='<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif' width="20" height="20"></TD>
                  <TD height=17 nowrap class=smalltext>Latest messages </TD>
                  <TD width="3%"><span class="Heading_Attributes"><IMG src='<bean:message key="OIFM.docroot" />/images\Icons\lock.gif' width="14" height="16"></span></TD>
                  <TD width="74%" height=17 nowrap class=smalltext>Locked Thread </TD>
                </TR>
                <TR>
                  <TD width="3%"><img src='<bean:message key="OIFM.docroot" />/images/Icons/HQ_Replies.gif' width="20" height="20"></TD>
                  <TD width="20%" nowrap class=smalltext>HQ Replies </TD>
                  <TD><span class="Heading_Attributes"><IMG height=17 
            src='<bean:message key="OIFM.docroot" />/images\Icons\icon_private.gif' width=18></span></TD>
                  <TD height=19 nowrap class=smalltext>Private thread</TD>
                </TR>
                </TR>
                <TR>
                  <TD width="3%"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif' width="20" height="20"></TD>
                  <TD colspan="3" width="20%" nowrap class=smalltext>Sticky Message </TD>
                   
                </TR>
                <TR>
                  <TD>&nbsp;</TD>
                  <TD nowrap class=smalltext>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                  <TD height=19 nowrap class=smalltext>&nbsp;</TD>
                </TR>
              </TBODY>
            </TABLE>
			</logic:present>
			</td>
          </tr>
          <tr><td colspan = 10><jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" /></td></tr>
        </table>
       </div></td>
    </tr>
  </table>

<!--
<input type="hidden" name="threadList" value="">
<input type="hidden" name="threadListType" value="">
-->
<input type="hidden" name="hidMode" value="<%=request.getParameter("hidMode")%>">


</form>