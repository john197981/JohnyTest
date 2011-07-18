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
	function listThread(submitUrl,submitValue,submitType){

		var frm = document.oifmThreadsSearch;
		frm.target="_self";
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.threadList.value = submitValue;
		frm.threadListType.value = submitType;
		frm.submit();

	}
</script>	  

<html:html>
<head>
	<title>Threads Listing</title>
	<style type="text/css">
	<!--
		.style1 {font-size: 9pt}
		.style3 {font-size: 8pt}
		.style6 {font-size: 9px}
	-->
	</style>
</head>

<body>		 
		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Thread List</td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td colspan="2" valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
				Search Result
				<br>
                </p>
                <p> 
                  <br>
                  </p>
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td colspan="2" class="Grey_fade">&nbsp;</td>
          </tr>
 			  <logic:present name="arOIBVUpToTopicListing" scope="request">
 			  <tr>
            <td colspan="2" align="left" valign="top" bgcolor="#f7f8fc">
            <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0" class="Box">
              <td width="50%">&nbsp;</td>
               <td width="50%" align="right" valign="top" nowrap >
 			   <table width="98%"  border="0" cellspacing="0" cellpadding="2">
                  <tr>
                        <td width="34%" class="Special_body_text"  valign="top">Join a special discussion</td>
                        <td width="5%"><a href="javascript:listThread('/oifmThreadsSearch.do','PO','Ots')"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_ownPosting.gif' alt="View all your Postings" width="16" height="16" border="0"></a></td>
                         <td width="5%"> &nbsp;</td>
                        <td width="18%" class="Special_body_text" valign="top" align="right">Last </td>
                        <td width="5%"   valign="top">
						<a class="Special_body_link" href="javascript:listThread('/oifmThreadsSearch.do',12,'Hrs')">12</a>
						</td>
                        <td width="4%"><span class="Special_body_text"><img src='<bean:message key="OIFM.docroot" />/images/break.gif'  width="14" height="20"></span></td>
                        <td width="5%"   valign="top">
						<a class="Special_body_link" href="javascript:listThread('/oifmThreadsSearch.do',24,'Hrs')">24</a>						
						</td>
                        <td width="4%"><span class="Special_body_text"><img src='<bean:message key="OIFM.docroot" />/images/break.gif'  width="14" height="20"></span></td>
                        <td width="5%"   valign="top">
						<a class="Special_body_link" href="javascript:listThread('/oifmThreadsSearch.do',72,'Hrs')">72</a>
						</td>
                        <td width="10%" align="right" valign="top" class="Special_body_text">Hours </td>
			         </tr>
                </table>
              </tr></td></table>
              </tr></td>
              <tr>
                <td  colspan = 10 bgcolor="#f7f8fc">&nbsp;</td>
              </tr>
 			  <tr>
	          <td colspan="2" align="left" valign="top" bgcolor="#f7f8fc">
 			  <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="Box">
                <table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" height = 400>
                  <tr>
						<td colspan="3" width="30%" height="22" class="Table_head">Category/Topic</td>
						<td width="20%" height="22" class="Table_head">Thread</td>
						<td width="20%" height="22" class="Table_head">Last posted by</td>
						<td width="10%" height="22" class="Table_head">Last post on</td>
						<td width="10%" height="22" class="Table_head">Replies</td>
						<td width="10%" height="22" class="Table_head">View</td>
						
			   		</tr>

				<logic:iterate id="forumTopic" name="arOIBVUpToTopicListing" scope="request" type="com.oifm.forum.OIBVThreadsSearch">
   					<logic:equal name="forumTopic" property="level" value="1">
						<tr align="left" >
							<td height="22" colspan="8" class="Heading_Category"  >
								<A NAME='#<bean:write name="forumTopic" property="categoryId"/>' />
									<font size="3">
									<bean:write name="forumTopic" property="category"/>
									</font>
							</td>
						</tr>
					</logic:equal>
					<logic:equal name="forumTopic" property="level" value="2">
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
						<tr >
                    <td width="2%">&nbsp;</td>
                    <td width="2%" valign="top"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_topic.gif'  ></td>
                    <td width="30%" valign="top">
 									<a class="Heading_Topic" href='<bean:message key="OIFM.contextroot" />/ThreadList.do?strTopicId=<bean:write name="forumTopic" property="topicId" />'>
										<bean:write name="forumTopic" property="topicName"/>
									</a>
 							</td>
							<td class="Heading_Thread"  valign="top">
								<a class="Heading_Thread" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_MAINTAIN_DO %>?strThreadId=<bean:write name="forumTopic" property="threadId"/>'>
									<bean:write name="forumTopic" property="threadName"/>
								</a> 
							</td>
							<td class="Heading_Thread"  valign="top">
								<logic:present name="forumTopic" property="nickName">
 										<a class="Heading_Thread" href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="forumTopic" property="postedBy"/>&hiddenAction=populate','mywindow','width=650,height=250,menubar=no,toolbar=no,copyhistory=no,location=no,directories=no' );">
											<bean:write name="forumTopic" property="nickName"/>
										</a> 
								</logic:present>
							</td>
							<td class="Heading_Thread"  valign="top">
								<bean:write name="forumTopic" property="lastPostOn"/>
							</td>
							<td class="Heading_Thread"  valign="top">
							<logic:present name="forumTopic" property="hits">
 									<bean:write name="forumTopic" property="hits"/>
 							</logic:present></td>
							<td class="Heading_Thread"  valign="top">
							<logic:present name="forumTopic" property="replies">
 									<bean:write name="forumTopic" property="replies"/>
 							</logic:present>
							</td>											
						</tr>
						<tr height="6" class="tbdvr"><td colspan="8">&nbsp;</td></tr>
					</logic:equal>

	 			</logic:iterate>
                    <tr>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                    <td colspan="7" valign="top" class="Heading_Topic">&nbsp;</td>
                  </tr>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td width="49" align="left" valign="top" bgcolor="#f7f8fc">&nbsp;</td>
            <td width="808" align="left" valign="top" bgcolor="#f7f8fc"><TABLE cellSpacing=0 cellPadding=0 width="98%" align=center border=0>
              <TBODY>
                <TR>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                </TR>
                <TR>
                  <TD><img src='<bean:message key="OIFM.docroot" />/images/Icons/Hottest_thread.gif' width="20" height="20"></TD>
                  <TD nowrap class=body_extract_text>Hottest messages</TD>
                  <TD><span class="Heading_Attributes"><IMG height=13 
            src='<bean:message key="OIFM.docroot" />/images\Icons\icon_Already_postmessages.gif' 
            width=13></span></TD>
                  <TD height=19 nowrap class=body_extract_text>You Have Posted in this Thread </TD>
                </TR>
                <TR>
                  <TD><img src='<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif' width="20" height="20"></TD>
                  <TD height=17 nowrap class=body_extract_text>Latest messages </TD>
                  <TD width="3%"><span class="Heading_Attributes"><IMG src='<bean:message key="OIFM.docroot" />/images\Icons\lock.gif' width="14" height="16"></span></TD>
                  <TD width="74%" height=17 nowrap class=body_extract_text>Locked Thread </TD>
                </TR>
                <TR>
                  <TD width="3%"><img src='<bean:message key="OIFM.docroot" />/images/Icons/HQ_Replies.gif' width="20" height="20"></TD>
                  <TD width="20%" nowrap class=body_extract_text>HQ Replies </TD>
                  <TD><span class="Heading_Attributes"><IMG height=17 
            src='<bean:message key="OIFM.docroot" />/images\Icons\icon_private.gif' width=18></span></TD>
                  <TD height=19 nowrap class=body_extract_text>Private thread</TD>
                </TR>
                </TR>
                <TR>
                  <TD width="3%"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif' width="20" height="20"></TD>
                  <TD colspan="3" width="20%" nowrap class=body_extract_text>Sticy Message </TD>
                   
                </TR>
                <TR>
                  <TD>&nbsp;</TD>
                  <TD nowrap class=body_extract_text>&nbsp;</TD>
                  <TD>&nbsp;</TD>
                  <TD height=19 nowrap class=body_extract_text>&nbsp;</TD>
                </TR>
              </TBODY>
            </TABLE></td>
          </tr>
         </table>
       </div></td>
    </tr>
    <tr><td align = "center"><jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" /></td></tr>
    </table>
     </logic:present>
     <logic:notPresent name="arOIBVUpToTopicListing" scope="request">
		<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0" height = 400>				
		<tr>
			<td align="center" colspan="7" class="Text" valign = "top">
				<br><font color="red"><bean:message key="NoRecordLoad"/></font>
				<br>
				<br>
				<br>
				<a href='<%= request.getHeader("Referer") %>'>
						<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' width="60" height="22" border="0" alt="Back"><a>
			</td>
		</tr>
		</table>
	</logic:notPresent>
<html:form action ="/oifmThreadsSearch">
	<input type = hidden name = threadList>
	<input type = hidden name = threadListType>
	</html:form>
</body>
</html:html>