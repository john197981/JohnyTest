<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.forum.OIForumConstants,com.oifm.utility.OIUtilities,java.util.ArrayList,com.oifm.forum.OIBAPosting,java.util.Date,java.util.Calendar" %>
<% String strOrder = "";
if(request.getAttribute("hidOrder")!=null){
	strOrder = String.valueOf(request.getAttribute("hidOrder"));
} 
%>
 
<%
 int iLatestPostId=0;
/*Added/Modified by Aik Mun @ Jan 16, 2009*/
String strPostsIdList = "";
String strReadedPost = "";
if(request.getAttribute("PostsList") !=null){
	ArrayList al= (ArrayList)request.getAttribute("PostsList");
	
 	for(int i=0;i<al.size();i++){
		try{
			OIBAPosting objVO = (OIBAPosting)al.get(i);
			strPostsIdList += objVO.getStrPostId()+",";
			
			if(objVO.getStrReadFlag()!=null && objVO.getStrReadFlag().equalsIgnoreCase("R")){
				strReadedPost += objVO.getStrPostId()+",";
			}
			if(i==0){
 				iLatestPostId=Integer.parseInt(objVO.getStrPostId());
			}else{
				int iNxtPostId = Integer.parseInt(objVO.getStrPostId());
   				if(iNxtPostId > iLatestPostId){
					iLatestPostId = iNxtPostId;
 				}
 			}
 		}catch(Exception e){
			System.out.println(e);
		}
		
	}
}
 
String strLatestPostId= ""+iLatestPostId;

%>

<script language="javascript">

	var contextRoot = '<bean:message key="OIFM.contextroot" />';

	var IE = document.all?true:false;
	if (!IE) 
	{
		document.captureEvents(Event.MOUSECLICK) //document.onmouseclick = getMouseXY;
	}

	var tempX = 0;
	var tempY = 0;
	var divPosting;
	var strPosting;

	function submitThreadPostsListForm(submitUrl, actionName, threadId, nPageNo) 
	{
		var frm = document.ThreadForm;		
		frm.target="_self";
		frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
		frm.hiddenAction.value = actionName;
		frm.strThreadId.value = threadId;
		frm.pageNo.value = nPageNo;
		frm.submit();
		return;
	}

	function submitThreadPostsForm(submitUrl, actionName, strPostId) 
	{
		var frm = document.ThreadForm;
		var flag = true;
		if(actionName == "<%= OIForumConstants.THREAD_LOCK %>") 
		{
			flag = confirm('<%= OIDBRegistry.getValues("OI.FORUM.POST.CONFIRM.LOCK") %>');
		} 
		else if(actionName == "<%= OIForumConstants.THREAD_DELETE %>") 
		{
			flag = confirm('<%= OIDBRegistry.getValues("OI.FORUM.POST.CONFIRM.THREAD.DELETE") %>');
		} 
		else if(actionName == "<%= OIForumConstants.POSTING_DELETE %>") 
		{
			flag = confirm('<%= OIDBRegistry.getValues("OI.FORUM.POST.CONFIRM.MESSAGE.DELETE") %>');
		}
		if(flag) 
		{
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.strPostId.value = strPostId;
			frm.submit();
		}
		return;
	}

	function getMouseXY(e) 
	{
		if (IE) 
		{ // grab the x-y pos.s if browser is IE
			tempX = event.clientX + document.body.scrollLeft;
			tempY = event.clientY + document.body.scrollTop;
		} 
		else 
		{  // grab the x-y pos.s if browser is NS
			tempX = e.pageX;
			tempY = e.pageY;
		}  
		if (tempX < 0)
		{
			tempX = 0;
		}		//document.Show.MouseX.value = tempX;
		if (tempY < 0)
		{
			tempY = 0;
		}		//document.Show.MouseY.value = tempY;

		window.status="tempX:"+tempX+"    tempY:"+tempY;
		return true;
	}

	function doPopup2() 
	{
 /*
		getMouseXY();
		cmdObj=document.getElementById("dropin");
		alert("2")
		cmdObj.style.top=tempY;
		cmdObj.style.left=tempX+2;
 
		if(cmdObj.style.visibility=="hidden")
		{
			cmdObj.style.visibility="visible";
		}
		else
		{
			cmdObj.style.visibility="hidden"	
		}
			*/
	}
	function listThread(hidMode){

		var url1='<bean:message key="OIFM.contextroot" />'+'/WebForumListingAction.do?hiddenAction=populate&hidMode='+hidMode;
 		document.ThreadForm.target="_self";
		document.ThreadForm.action=url1
		document.ThreadForm.submit();

	}

	function fnSort(sortKey,sortOrder){
		document.ThreadForm.pageNo.value = 1;
		var strDocRoot ='<bean:message key="OIFM.contextroot" />';
		var contextRoot = '<bean:message key="OIFM.contextroot" />';		
		document.ThreadForm.hidOrder.value = sortOrder;
		document.ThreadForm.action = strDocRoot+"/ThreadMaintain.do"
   	    document.ThreadForm.hiddenAction.value="PostingList";
		document.ThreadForm.target="_self";
		document.ThreadForm.submit();
	}
	//Added by Zhao Yu
 	function fnGetMember(userid)
 	{
 		 var strDocRoot ='<bean:message key="OIFM.contextroot" />';
	  	 var strUrl = '/MemberProfileAction.do?nric='+userid+'&hiddenAction=populate&isEncrpyt=yes';
		 var help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=890,height=280,left=0,top=0,resizable=no,scrollbars=yes');
  		 help_window.focus();
  	}
  	
  	/*Added/Modified by Aik Mun @ Jan 16, 2009*/
  	var markPostsId = "<%=strPostsIdList%>";
  	
  	//Ajax function to mark/upmark as read.
	function markAsRead()
	{
	var xmlHttp;
	var markURL;
	var markThreadId = document.ThreadForm.JsThread.value;
	//this is for checking broswer
	try
	  {
	  // Firefox, Opera 8.0+, Safari
	  xmlHttp=new XMLHttpRequest();
	  }
	catch (e)
	  {
	  // Internet Explorer
	  try
	    {
	    xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	    }
	  catch (e)
	    {
	    try
	      {
	      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	      }
	    catch (e)
	      {
	      alert("Your browser does not support AJAX!");
	      return false;
	      }
	    }
	  }
	 //end checking browser
	 
	 if(document.ThreadForm.chkMarkRead.checked){
	 	if(!confirm('<%=OIDBRegistry.getValues("OI.FORUM.POST.CONFIRM.MESSAGE.MARKREAD")%>')){
	 		document.ThreadForm.chkMarkRead.checked = false;
	 		return;
	 	}
	 	var vPostsId = "";
	 	
	 	//if initially all post already mark as readed
	 	if(null==document.ThreadForm.hidReadFlag){
	 		vPostsId = markPostsId;
	 	
	 	// if only one post going to be mark as read
	 	}else if(null==document.ThreadForm.hidReadFlag.length){
	 		vPostsId = document.ThreadForm.hidReadFlag.value;
	 		
	 	// if more than one post going to be mark as read
	 	}else{
	 		for(var k=0;k<document.ThreadForm.hidReadFlag.length;k++){
	 			vPostsId +=document.ThreadForm.hidReadFlag[k].value+",";
	 		}
	 	}
	 	
	 	markURL = "ThreadMaintain.do?hiddenAction=markRead&markThredId="+markThreadId+"&markId="+vPostsId+"&timeId="+new Date().getTime();
	 }else{
	 	if(!confirm('<%=OIDBRegistry.getValues("OI.FORUM.POST.CONFIRM.MESSAGE.MARKUNREAD")%>')){
	 		document.ThreadForm.chkMarkRead.checked = true;
	 		return;
	 	}
	 	markURL = "ThreadMaintain.do?hiddenAction=markUnRead&markThredId="+markThreadId+"&markId="+markPostsId+"&timeId="+new Date().getTime();
	 }
	
	  xmlHttp.onreadystatechange=function()
	    {
	    if(xmlHttp.readyState==4)
	      {
	      var ResTxt=document.ThreadForm.hidMarkFlag.value; 
	      ResTxt=xmlHttp.responseText;
	      if(ResTxt.match("Success")=="Success"){
	      	var pId = 0;
	      	var vTd = document.getElementById("PostDetail"+pId);
	      	var vBgColor = "#cccccc";
	      	
	      	if(ResTxt.match("markRead")!="markRead"){
	      		vBgColor = "#ffffff";
	      	}
	      	
	      	//change color according to mark as read or unread
	      	while(null!=vTd){
	      		vTd.style.backgroundColor =vBgColor;
	      		pId++;
	      		vTd = document.getElementById("PostDetail"+pId);
	      	}
	      	
	      }else{
	      	//alert('<%=OIDBRegistry.getValues("OI.FORUM.POST.CONFIRM.MESSAGE.MARKFAIL")%>');
	      }
	      
	      }
	    }
	    
	  xmlHttp.open("GET",markURL,true);
	  xmlHttp.send(null);
	  }
</script>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/ForumThread.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/ForumMail.js'></script>

        <table width="859" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td class="Orange_fade" colspan="3">
				<a name="#TOP"></a>View Postings
			</td>
          </tr>        
          <tr>
            <td valign="top" class="orange">&nbsp;</td>
            <td valign="top" class="orange"><br> 
				<p><span class="Highlight_body">  
				<bean:define id="objThread" name="ThreadInfo" />
				<logic:equal name="objThread" property="strLocked" value="Y" >
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/lock.gif' border=0 alt="Lock">
				</logic:equal>
				<logic:equal name="objThread" property="strSecurity" value="Y" >
					<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif' border=0 alt="Private">
				</logic:equal>
				<bean:write name="objThread" property="strTitle" />
				<br>&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; 
				<i>(<bean:write name="TotalRec" /> Posts)
				&nbsp; created by <bean:write name="objThread" property="strCreatedBy" />
				&nbsp; on <bean:write name="objThread" property="strCreatedOn" /></i>
				</span>
			</td>
            <td valign="top" class="orange">&nbsp;</td>
          </tr>          
          <tr>
            <td colspan="3" class="Grey_fade">
				<html:form action="/ThreadMaintain.do" method="post" >
				<html:hidden property="hiddenAction" />
				<html:hidden property="strThreadId" />
				<html:hidden property="strTopicId" />
				<html:hidden property="strPostId" />
				<html:hidden property="pageNo" />
				<!-- /*Added/Modified by Aik Mun @ Jan 16, 2009*/ -->
				<input type="hidden" name="JsThread" value='<bean:write name="ThreadInfo" property="strThreadId" />'/>
			</td>
          </tr>
          <tr>
            <td colspan="3" align="left" valign="top" bgcolor="white">			
				<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<tr>
						<td align="center">
							<table width="98%" border="0" cellspacing="0" cellpadding="2">
								<tr>
									<td width="29%" align="right" valign="top" nowrap>
										<table width="100%"  border="0" cellpadding="2" cellspacing="0" class="Box">
											<tr>                 
												<td width="29%" align="right" valign="top" nowrap>
													<table width="100%"  border="0" cellpadding="2" cellspacing="0">
														<tr>
															<td width="4%" align="center" nowrap class="Special_body_link">
																<bean:define id="objThread" name="ThreadInfo" />
																<logic:equal name="objThread" property="strLocked" value="N" >
																	<A HREF="#" onClick="javascript:submitThreadPostsPrintForm('<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>','<%= OIForumConstants.POSTING_EDIT %>', '')">
																	<img src='<bean:message key="OIFM.docroot" />/images/btn_PostReply.gif' border="0"  alt="Post a new Reply"></A>
																</logic:equal >
																<logic:equal name="objThread" property="strLocked" value="Y" >
																	<a href="#" onClick="alert('Currently thread is locked. You can not post the message.'); return false;">
																	<img src='<bean:message key="OIFM.docroot" />/images/btn_PostReply.gif' border="0" alt="Post a new Reply"></a>
																</logic:equal >
															</td>
															<td width="4%" align="center" nowrap class="Special_body_link">
																<logic:match name="accessInfo" property="editThread" value="true">
																	<A title="Edit this Thread" class="Special_body_link" HREF="#" onClick="javascript:submitThreadPostsPrintForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_EDIT %>', '')" >Edit
																</A> 
																</logic:match>
															</td>
															<td width="4%" nowrap class="Special_body_link">
																<logic:match name="accessInfo" property="deleteThread" value="true">
																	<A title="Delete this Thread" class="Special_body_link" HREF="#" onClick="javascript:submitThreadPostsForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_DELETE %>','')">Delete
																	</A> 
																</logic:match>
 															</td>
															<td width="4%" nowrap class="Special_body_link">
																<logic:match name="accessInfo" property="lockThread" value="true">
																	<logic:equal name="objThread" property="strLocked" value="N" >
																		<A title="Lock this Thread" class="Special_body_link" HREF="#" onClick="javascript:submitThreadPostsForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_LOCK %>','')">Lock</A>
																	</logic:equal>
																	<logic:equal name="objThread" property="strLocked" value="Y" >
																		<A title="Unlock this thread" class="Special_body_link" HREF="#" onClick="javascript:submitThreadPostsForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_UNLOCK %>','')">UnLock</A>
																	</logic:equal>
																</logic:match>
															</td>
															<td width="5%" nowrap class="Special_body_link">
																 <logic:equal name="roleID" scope="session" value="ADMIN">
																	<A title="Make this thread sticky for all My Forum Users" class="Special_body_link" HREF="#" onClick="javascript:submitThreadPostsStatusForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_ADMIN_STICKY %>')">Admin Sticky</A>
																</logic:equal>
															</td>
															<td nowrap>
																<logic:equal name="roleID" scope="session" value="ADMIN">
																	<A title="Make this thread sticky as speical thread" class="Special_body_link" HREF="#" onClick="javascript:submitThreadPostsStatusForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.SPL_STICKY_THREAD %>')">Mark as special thread</A>
																</logic:equal>
															</td>
														</tr>
													</table>                  
												</td>
												<td width="35%" align="right" valign="top" nowrap>
													<table width="132"  border="0" cellpadding="2" cellspacing="0" align="right">
														<tr>
															<td width="4%" valign="top">
																<A HREF="#" onClick="javascript:submitThreadPostsPrintForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_PRINT %>', '')">
																<img alt="Print all the postings in this thread" src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' border="0" style="CURSOR:hand" onClick="doPopup2();"  ></A> 
										 					</td >
															<td width="4%" valign="top">
																<a href="#" onClick="javascript:fnAlertEmail()">
																<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' border="0" alt="Email this thread link to your Friend"></a>
 															</td>
										                    <td width="5%" valign="top">
																<a href="#" onClick="javascript:submitThreadPostsStatusForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_STICKY %>')">
																<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sticky.gif' alt="Mark this thread as sticky"  border="0"></a>
															</td>
															<td width="4%" nowrap class="Special_body_link" valign="top">
																<A HREF="#" onClick="javascript:submitThreadPostsStatusForm('<%= OIForumConstants.THREAD_MAINTAIN_DO %>','<%= OIForumConstants.THREAD_TRACK %>')"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_bookmark.gif' border="0" alt="Bookmark this thread. Any new posting will be acknowledged by email"></A>
															</td>
															<td width="4%" valign="top">
																<a href="#" onclick="listThread('ViewUserPosting')"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_ownPosting.gif' alt="View all your Postings" border="0"></a>
															</td>
										                </tr>
													</table>
												</td>
												<td width="100%" align="left" valign="top" nowrap>
													<table width="100%"  align="left" border="0" cellpadding="2" cellspacing="0" class="Box">
													  <tr>
															<td width="55%" valign="top" nowrap  align="left" class="Special_body_text">Postings in Last </td>
															<td width="7%" class="Special_body_link" align="center">
																<a title="View postings in last 12 hrs" class="Special_body_link" href="#" onclick="listThread('12')">12</a>
															</td>
															<td width="5%" valign="top">
																<img src='<bean:message key="OIFM.docroot" />/images/break.gif'>
															</td>
															<td width="7%" class="Special_body_link" align="center">
																<a  title="View postings in last 24 hrs" class="Special_body_link" href="#" onclick="listThread('24')">24</a>
															</td>
															<td width="5%" valign="top">
																<img src='<bean:message key="OIFM.docroot" />/images/break.gif'>
															</td>
															<td width="7%" class="Special_body_link" align="center">
																<a  title="View postings in last 72 hrs" class="Special_body_link" href="#" onclick="listThread('72')">72</a>
															</td>
															<td width="17%" valign="top" align="right" class="Special_body_text">
																Hours 
															</td>
													  </tr>													  
												</table>
											</td>
										</tr>
										<tr>
											<td colspan="2" align="left" valign="middle" class="Special_body_link">
												&nbsp;&nbsp;&nbsp;
												<logic:present name="CatBoardTopic" scope="request" >
													<bean:define id="objCatBoardTopic" name="CatBoardTopic" />						
													<a title="Click to see list of discussion threads on this topic" class="Special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.FORUM_HOME_DO %>?hiddenAction=populate#C<bean:write name="objCatBoardTopic" property="strCategoryId" />' >
													<bean:write name="objCatBoardTopic" property="strCategory" /></a> 
													<font class="Special_body_link">-&gt;</font>
													<a title="Click to see list of discussion threads on this topic"  class="Special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.FORUM_HOME_DO %>?hiddenAction=populate#B<bean:write name="objCatBoardTopic" property="strBoardId" />' >
													<bean:write name="objCatBoardTopic" property="strBoard" /></a> 
													<font class="Special_body_link">-&gt;</font>
													<a  title="Click to see list of discussion threads on this topic" class="Special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_LIST_DO %>?strTopicId=<bean:write name="objCatBoardTopic" property="strTopicId" />' >
													<bean:write name="objCatBoardTopic" property="strTopic" /></a>
												</logic:present>
											</td>
											<td align="right" valign="bottom">
												<table align="right" cellpadding="2" cellspacing="2" border="0" height="8" bgcolor="#F4F0EA">						
													<tr>
														<logic:present name="PageBean" scope="request" >
														<bean:define id="objPageInfo" name="PageBean" type="com.oifm.common.OIPageInfoBean" />
														<bean:define id="selPageNo" name="objPageInfo" property="pageNo"/> 
														<logic:greaterThan name="objPageInfo" property="noOfPages" value="1">
														<td  valign="bottom" class="Page_text">Pages</td>
														<%
															for(int i=1, j=((java.lang.Integer)selPageNo).intValue(); i<=objPageInfo.getNoOfPages(); i++) 
															{
																if(i != j) 
																{
														%>
																	<td valign="bottom">
																		<a class="Page_link" href="#" onClick='javascript:submitThreadPostsListForm("<%= OIForumConstants.THREAD_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_LIST %>", "<bean:write name="ThreadInfo" property="strThreadId" />", "<%= i %>")'><%= i %></a>
																	</td>
														<%			
																} 
																else 
																{
														%>
														<td valign="bottom"><font color="red" size="2px"><b><%= i %></b></font></td>
														<%			
																}
															}
														%>
														</logic:greaterThan >
														</logic:present>
													</tr>
												</table>				 
											</td>
										</tr>
										<!-- /*Added/Modified by Aik Mun @ Jan 16, 2009*/ 
											For mark as read Checkbox, hidMarkFlag is use for store feed back from 
											Ajax. 
										-->
										<tr>
											<td colspan="3" align="right" class="Special_body_link">
											<% if( strPostsIdList.equalsIgnoreCase(strReadedPost)){%>
											<input type="checkbox" name="chkMarkRead" checked="checked" onClick="markAsRead();"/>
											<%}else{ %> 
											<input type="checkbox" name="chkMarkRead" onClick="markAsRead();"/>
											<%} %>Mark as Read
											<input type="hidden" name="hidMarkFlag" value="" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<logic:present name="error" scope="request" >
							<tr>
								<td class="BodyText" height="35" valign="middle" colspan="3">
									<b><bean:message name="error" scope="request"/></b>
								</td>
							</tr>
							</logic:present>
<logic:notPresent name="error" scope="request" >
	<logic:present name="PostsList" scope="request" >
		<logic:greaterThan name="TotalRec" value="0">
		<%int count =0; %>
			<logic:iterate id="objPosting" name="PostsList" indexId="ind" type="com.oifm.forum.OIBAPosting">
			<% if(count == 0){%>
              <tr>
                <td>
				<table class="Box" width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  
                  <tr>
                    <td width="1%" class="Table_head">&nbsp;</td>
                    <td width="19%" class="Table_head">Posted By</td>
                    <td width="47%" valign="top" nowrap class="Table_head">Message					
						<a href="#"> 
						<logic:equal name="ThreadForm" property="hidOrder" value="ASC" scope="request">
							<img alt="Show New postings first" src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF'   border="0" onClick="javascript:fnSort('LASTPOST_ON','DESC');" width="15" height="15">
						</logic:equal>
						<logic:equal name="ThreadForm" property="hidOrder" value="DESC" scope="request" >
							<img alt="Show Old postings first" src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('LASTPOST_ON','ASC');">
						</logic:equal>
						</a>				

						</td>
						<td align="right" valign="top" nowrap class="Table_head">
					
				<logic:greaterThan name="objThread" property="strPrevThread" value="0">
					<font color="#CC3300">&lt;</font>
						<a title="Click to see postings in previous thread" class="Table_head" href="#" onClick='javascript:submitThreadPostsListForm("<%= OIForumConstants.THREAD_LIST_DO %>","<%= OIForumConstants.HITS %>","<bean:write name="objThread" property="strPrevThread" />", "")' >
						Previous
				</logic:greaterThan>
				<logic:greaterThan name="objThread" property="strNextThread" value="0">
					<a  title="Click to see postings in next thread" class="Table_head" href="#" onClick='javascript:submitThreadPostsListForm("<%= OIForumConstants.THREAD_LIST_DO %>","<%= OIForumConstants.HITS %>","<bean:write name="objThread" property="strNextThread" />", "")' >
						Next</a> 
					<font color="#CC3300">&gt;</font>
				</logic:greaterThan>
					 
					</td>
                    </tr>

			<% }%>	

                  <tr>
                    <td bgcolor="#E0EBFC">&nbsp;</td>
                    <td rowspan="2" align="left" valign="top" nowrap bgcolor="#E0EBFC"><table width="98%" border="0" cellspacing="0" cellpadding="0">
                        <tr>
                          <td colspan="2">
							<A title="Clcik to see the profile" class="Special_body_link" href="#" onClick='javascript:fnGetMember("<bean:write name="objPosting" property="strPostedBy" />");'><bean:write name="objPosting" property="strNickName" /></A>				
						</td>
                          </tr>
                        <tr>
                          <td class="Special_body_text">(<bean:write name="objPosting" property="totalPost" /> Posts) </td>
                          <td>
							<logic:match name="accessInfo" property="allowPM" value="true">
								<a href="#" onClick='javascript:getiFrame("<bean:write name="objPosting" property="strPostId" />","<bean:write name="objPosting" property="strNickName" />", "P");'><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_pm.gif' border="0" title="Send Personal Message to poster" ></a>
							</logic:match>
						   </td>
                          </tr>
                        <tr>
                          <td colspan="2" class="Heading_Attributes"> 
								<bean:write name="objPosting" property="strSignature" />						  
						  </td>
                          </tr>
                          </table>                      <BR></td>
                    <logic:equal name="objPosting" property="strHQReply" value="Y">
						<td bgcolor="#F4F0EA" class="small_text">
					</logic:equal>
					<logic:notEqual name="objPosting" property="strHQReply" value="Y">
						<td class="small_text">
					</logic:notEqual>
  					Posted on <bean:write name="objPosting" property="strPostedOn" /></td>
                    <logic:equal name="objPosting" property="strHQReply" value="Y">
						<td bgcolor="#F4F0EA" class="small_text">
					</logic:equal>
					<logic:notEqual name="objPosting" property="strHQReply" value="Y">
						<td class="small_text">
					</logic:notEqual>
                     
								<table width="98%" border="0" cellspacing="0" cellpadding="1" >
								  <tr>
									<td width="40%" align="right">&nbsp;</td>
									<td width="34%" align="right">
										<logic:match name="accessInfo" property="allowAM" value="true">
											<a href="#" onClick='javascript:getiFrame("<bean:write name="objPosting" property="strPostId" />","<bean:write name="objPosting" property="strNickName" />", "A");'>
												<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_admin.gif' border="0" alt="Alert to Admin on this posting"  ></a> 
										</logic:match>
									</td>
									<td width="8%" align="right"> 
									<A HREF="#" onClick='javascript:submitThreadPostsPrintForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_QUOTE %>", "<bean:write name="objPosting" property="strPostId" />")'>
										<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_quote.gif' border="0" alt="Quote on this post"></a>
									</td>
									<td width="10%" align="right"> 
									<a href="#TOP">
										<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_jump_up.gif' border="0" alt="Jump to top"></a>
									 </td>
									<td width="8%" align="right">
										<a href="#BOTTOM">
											<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_jump_down.gif'  border="0" alt="Jump to last post"></a>
								</td>
								  </tr>
								</table>
							
					</td>
                    </tr>
                  
                  <tr >
                    <td bgcolor="#E0EBFC">&nbsp;</td>
                    <logic:equal name="objPosting" property="strHQReply" value="Y">
						<td bgcolor="#F4F0EA" colspan="3" valign="top" class="body_detail_text" id="PostDetail<%=count %>">
					</logic:equal>
					<logic:notEqual name="objPosting" property="strHQReply" value="Y">
						<logic:equal name="objPosting" property="strReadFlag" value="R">
							<td colspan="3" valign="top" style="background-color: #cccccc" class="body_detail_text" id="PostDetail<%=count %>">
						</logic:equal>
						<!-- /*Added/Modified by Aik Mun @ Jan 19, 2009*/
							if post is not readed, then create a hiddent field that use when mark as read.
						 -->
						<logic:notEqual name="objPosting" property="strReadFlag" value="R">
							<input type="hidden" name="hidReadFlag" value="<%=OIUtilities.replaceNull(""+objPosting.getStrPostId()) %>"/>
							<td colspan="3" valign="top" class="body_detail_text" id="PostDetail<%=count %>">
						</logic:notEqual>
					</logic:notEqual>

					<hr noshade style="color:#CCCCCC" size=0>
                       <%= OIUtilities.replaceNull(""+objPosting.getStrPosting())%>
                       
  					   </td>
                    </tr>
                  <tr>
                    <td bgcolor="#E0EBFC">&nbsp;</td>
                    <td bgcolor="#E0EBFC">&nbsp;</td>

					<logic:equal name="objPosting" property="strHQReply" value="Y">
						<td bgcolor="#F4F0EA" colspan="2" align="right" class="body_detail_text" >
					</logic:equal>
					<logic:notEqual name="objPosting" property="strHQReply" value="Y">
						<td colspan="2" align="right" class="body_detail_text" >
					</logic:notEqual>
 
					<logic:match name="accessInfo" property="editPosting" value="true">
				 
						<A HREF="#" onClick='javascript:submitThreadPostsPrintForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_EDIT %>", "<bean:write name="objPosting" property="strPostId" />")'>
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_edit.gif' border="0" alt="Edit the current Posting"></A>&nbsp;
					</logic:match>
					<!-- If there is no other poset apart from the login user then he can edit the reply -->
					<logic:notMatch name="accessInfo" property="editPosting" value="true">
				   <%if(OIUtilities.replaceNull(""+session.getAttribute("userid")).equals(OIUtilities.replaceNull(objPosting.getStrPostedBy()))  &&   OIUtilities.replaceNull(""+objPosting.getStrPostId()).equals(strLatestPostId)){ %>
						<A HREF="#" onClick='javascript:submitThreadPostsPrintForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_EDIT %>", "<bean:write name="objPosting" property="strPostId" />")'>
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_edit.gif' border="0" alt="Edit the current posting"></A>&nbsp;														 
					<%}%>
					</logic:notMatch>
					
				<logic:match name="accessInfo" property="deletePosting" value="true">
					<A HREF="#" onClick='javascript:submitThreadPostsForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_DELETE %>", "<bean:write name="objPosting" property="strPostId" />")'>
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_delete.gif' border="0" alt="Delete the current posting"></a>
				</logic:match>
				<!-- If there is no other poset apart from the login user then he can delete the reply -->
				<logic:notMatch name="accessInfo" property="editPosting" value="true">
				<%if(OIUtilities.replaceNull(""+session.getAttribute("userid")).equals(OIUtilities.replaceNull(objPosting.getStrPostedBy()))  &&   OIUtilities.replaceNull(""+objPosting.getStrPostId()).equals(strLatestPostId)){ %>
					<A HREF="#" onClick='javascript:submitThreadPostsForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_DELETE %>", "<bean:write name="objPosting" property="strPostId" />")'>
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_delete.gif' border="0" alt="Delete the current posting"></a>
		 
				<%}%>
				</logic:notMatch> 
				
				</td>
                  </tr>
                  <tr>
                    <td colspan="4" bgcolor="#E0EBFC">&nbsp;</td>
                    </tr>
				<%count++;%> 
				</logic:iterate>
			</logic:greaterThan>
			<logic:lessEqual name="PageBean" property="totalRec" value="0">
				<tr>
					<td colspan="4" width="98%" align="center" class="body_detail_text" >
						 No posting available
					</td>
				</tr>
			</logic:lessEqual>
<!-- pagination start-->
			<tr>
				<td colspan="4" valign="bottom" align="right">
					<table align="right" cellpadding="2" cellspacing="2" border="0" height="8" bgcolor="#F4F0EA">						
						<tr>
						<logic:present name="PageBean" scope="request" >
						<bean:define id="objPageInfo" name="PageBean" type="com.oifm.common.OIPageInfoBean" />
						<bean:define id="selPageNo" name="objPageInfo" property="pageNo"/> 
						<logic:greaterThan name="objPageInfo" property="noOfPages" value="1">						
						<td  valign="bottom" class="Page_text">Pages</td>
						<%
							for(int i=1, j=((java.lang.Integer)selPageNo).intValue(); i<=objPageInfo.getNoOfPages(); i++) 
							{
								if(i != j) 
								{
						%>
									<td valign="bottom">
										<a class="Page_link" href="#" onClick='javascript:submitThreadPostsListForm("<%= OIForumConstants.THREAD_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_LIST %>", "<bean:write name="ThreadInfo" property="strThreadId" />", "<%= i %>")'><%= i %></a>
									</td>
						<%			
								} 
								else 
								{
						%>
						<td valign="bottom"><font color="red" size="2px"><b><%= i %></b></font></td>
						<%			
								}
							}
						%>
						</logic:greaterThan >
					</logic:present>
					</tr></table>
				</td>
			</tr>
			<!-- pagination end-->
 
				 </table>
				 
				 </td>
              </tr>
				</logic:present>
			</logic:notPresent>
				<tr>
					<td colspan="7" align="right">				
						<logic:equal name="objThread" property="strLocked" value="N" >
							<A HREF="#" onClick="javascript:submitThreadPostsPrintForm('<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>','<%= OIForumConstants.POSTING_EDIT %>', '')">
							<img src='<bean:message key="OIFM.docroot" />/images/btn_PostReply.gif' border="0"  alt="Post a new Reply"></A>
						</logic:equal >
						<logic:equal name="objThread" property="strLocked" value="Y" >
							<a href="#" onClick="alert('Currently thread is locked. You can not post the message.'); return false;">
							<img src='<bean:message key="OIFM.docroot" />/images/btn_PostReply.gif' border="0" alt="Post a new Reply"></a>
						</logic:equal >
				</td>
				</tr>	
            </table>						
			</td>
          </tr>
		  <tr>
		  <td>&nbsp;</td>
		  </tr>
         </table>         
  <a name="#BOTTOM"></a>
 <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" /> 
 
 <input type="hidden" name="winObj" value="">
<input type="hidden" name="threadList" value="">
<input type="hidden" name="threadListType" value="">
<input type="hidden" name="hidSortKey" value="posting">
<input type="hidden" name="hidOrder" value="<%=strOrder%>">
</html:form>