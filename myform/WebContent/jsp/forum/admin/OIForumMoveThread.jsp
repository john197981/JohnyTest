<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %> 
<%@ page import="com.oifm.utility.OIDBRegistry" %>

<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript">
	function fnSubmit3(actionName,hiddenAction)
	{
		//document.ForumMoveThreadForm.flag.value="T";
		document.ForumMoveThreadForm.hiddenAction.value=hiddenAction;
		document.ForumMoveThreadForm.action='<%= OIDBRegistry.getValues("OIFM.contextroot") %>' + actionName;
		document.ForumMoveThreadForm.submit();
	}

	function fnClear()
	{
		document.ForumMoveThreadForm.reset();
		document.getElementById("ThreadOpt").innerHTML=defaultThread;
	}
	function fnSubmit(actionName,ac)
	{
		if (document.ForumMoveThreadForm.categoryId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.SAVE.CHECK.CATEGORY") %>');
			document.ForumMoveThreadForm.categoryId.focus();
			return;
		}
		else if (document.ForumMoveThreadForm.boardId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.SAVE.CHECK.BOARD") %>');
			document.ForumMoveThreadForm.boardId.focus();
			return;
		}
		else if(document.ForumMoveThreadForm.topicId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.MOVE.CHECK.TOPIC") %>');
			document.ForumMoveThreadForm.topicId.focus();
			return;
		}
		else if(document.ForumMoveThreadForm.threadId.selectedIndex==-1)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.MOVE.CHECK.THREAD") %>');
			document.ForumMoveThreadForm.threadId.focus();
			return;
		}
		else if (document.ForumMoveThreadForm.desCategoryId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.MOVE.CHECK.DES.CATEGORY") %>');
			document.ForumMoveThreadForm.desCategoryId.focus();
			return;
		}
		else if (document.ForumMoveThreadForm.desBoardId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.MOVE.CHECK.DES.BOARD") %>');
			document.ForumMoveThreadForm.desBoardId.focus();
			return;
		}
		else if(document.ForumMoveThreadForm.desTopicId.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.FORUM.MOVE.CHECK.DES.TOPIC") %>');
			document.ForumMoveThreadForm.desTopicId.focus();
			return;
		}
		else if(!confirm('<%= OIDBRegistry.getValues("OI.FORUM.MOVE.CHECK.CONFIRM") %>')){
			return;
		}
		else
		{
			document.ForumMoveThreadForm.hiddenAction.value=ac;
			document.ForumMoveThreadForm.action=actionName;
			document.ForumMoveThreadForm.submit();
		}
	}

	//Ajax default List Box
	var topicEnding = "<option value=\"\">Please Select...</option></select>";
	
	var defaultTopic = "<select name=\"topicId\" class=\"Text\">"+topicEnding;
	var defaultDesTopic = "<select name=\"desTopicId\" class=\"Text\">"+topicEnding;
	
	var defaultThread = "<select name=\"threadId\" class=\"Text\" size=\"4\"><option value=\"\"></option></select>";
	

	//Ajax function to load Board List box when category selected.
	function loadBoard(listType)
	{
	var xmlHttp;
	var boardType = "BoardOpt";
	var topicType = "topicOpt";
	var threadType = "ThreadOpt";
	var topicDefault = defaultTopic;
	var categoryValue = "";
	var boardURL = "";
	
	document.getElementById("MsgPanel").innerHTML="";
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
	 
	 if(listType!=null && listType=="des"){
	 	boardType = "desBoardOpt";
	 	topicType = "desTopicOpt";
	 	topicDefault = defaultDesTopic;
	 	categoryValue = document.ForumMoveThreadForm.desCategoryId.value;
	 }else{
	 	categoryValue = document.ForumMoveThreadForm.categoryId.value;
	 }
	 
	 if(categoryValue!=""){
	  	boardURL = "adminForumMoveThreadAction.do?hiddenAction=board&boardType="+boardType+"&categoryId="+categoryValue+"&tid="+new Date().getTime();
	  }else{
	  	boardURL = "adminForumMoveThreadAction.do?hiddenAction=board&boardType="+boardType+"&tid="+new Date().getTime();
	  }
	 
	  xmlHttp.onreadystatechange=function()
	    {
	    if(xmlHttp.readyState==4)
	      {
	      document.getElementById(boardType).innerHTML=xmlHttp.responseText;
	      
	      document.getElementById(topicType).innerHTML=topicDefault; 
	      
	      if(boardType=="BoardOpt"){
	      	document.getElementById(threadType).innerHTML=defaultThread;
	      }
	      
	      }
	    }
	    	  
	  xmlHttp.open("GET",boardURL,true);
	  xmlHttp.send(null);
	  }
	  
	//Ajax function to load Board List box when board selected.
	function loadTopic(listType)
	{
	var xmlHttp;
	var topicType = "TopicOpt";
	var threadType = "ThreadOpt";
	var boardValue = "";
	var topicURL = "";
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
	 
	 if(listType!=null && listType=="des"){
	 	topicType = "desTopicOpt";
	 	boardValue = document.ForumMoveThreadForm.desBoardId.value;
	 }else{
	 	boardValue = document.ForumMoveThreadForm.boardId.value;
	 }
	 
	 if(boardValue!=""){
	  	topicURL = "adminForumMoveThreadAction.do?hiddenAction=topic&topicType="+topicType+"&boardId="+boardValue+"&tid="+new Date().getTime();
	  }else{
	  	topicURL = "adminForumMoveThreadAction.do?hiddenAction=topic&topicType="+topicType+"&tid="+new Date().getTime();
	  }
	 
	  xmlHttp.onreadystatechange=function()
	    {
	    if(xmlHttp.readyState==4)
	      {
	      document.getElementById(topicType).innerHTML=xmlHttp.responseText;
	      
		      if(topicType=="TopicOpt"){
		      	document.getElementById(threadType).innerHTML=defaultThread;
		      }
	      }
	    }
	    	  
	  xmlHttp.open("GET",topicURL,true);
	  xmlHttp.send(null);
	  }
	  
	//Ajax function to load Thread List box when topic selected.
	function loadThread()
	{
	var xmlHttp;
	var topicValue = "";
	var threadURL = "";
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
	 
	 
	topicValue = document.ForumMoveThreadForm.topicId.value;
	 
	 if(topicValue!=""){
	  	threadURL = "adminForumMoveThreadAction.do?hiddenAction=thread&topicId="+topicValue+"&tid="+new Date().getTime();
	  }else{
	  	threadURL = "adminForumMoveThreadAction.do?hiddenAction=thread&tid="+new Date().getTime();
	  }
	 
	  xmlHttp.onreadystatechange=function()
	    {
	    if(xmlHttp.readyState==4)
	      {
	      document.getElementById("ThreadOpt").innerHTML=xmlHttp.responseText;
	      }
	    }
	    	  
	  xmlHttp.open("GET",threadURL,true);
	  xmlHttp.send(null);
	  }
</script>
<html:form action="/adminForumMoveThreadAction.do">
<html:hidden name="ForumMoveThreadForm" property="hiddenAction"/>

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
						<td colspan="5">Category / Boards / Topics / Thread Maintainance</td>
 					</tr>
					<tr  class="sub_head">
						<td colspan="5" class="sub_head">
							Move Thread
						</td>
					</tr>
					
					<span id="MsgPanel">
					<logic:present name="saveFlag">
					<logic:equal name="saveFlag" value="true">
					<tr>
						<td colspan="5" class="heading_attributes" align="center">Thread/Threads Successfully Moved</td>
					</tr>
					</logic:equal>
					<logic:notEqual name="saveFlag" value="true">
					<tr>
						<td colspan="5" class="heading_attributes" align="center">Move Thread/Threads Failed</td>
					</tr>
					</logic:notEqual>
					</logic:present>
					</span>
					<tr>
						<td colspan="2" class="heading_attributes" align="center">Source</td>
						<td>&nbsp;</td>
						<td colspan="2" class="heading_attributes" align="center">Destination</td>
					</tr>
					<tr>
						<td width="20%" class="heading_attributes">Category</td>
						<td class="body_detail_text">
							<bean:define id="ar" name="ForumMoveThreadForm" property="arCategoryID" />
							<html:select name="ForumMoveThreadForm" property="categoryId" onchange="javascript:loadBoard();" styleClass="Text">
								<html:options collection="ar" property="value" labelProperty="label"  />
							</html:select>
						</td>
						<td>&nbsp;</td>
						<td width="20%" class="heading_attributes">Category</td>
						<td class="body_detail_text">
							<html:select name="ForumMoveThreadForm" property="desCategoryId" onchange="javascript:loadBoard('des');" styleClass="Text">
								<html:options collection="ar" property="value" labelProperty="label"  />
							</html:select>
						</td>
					</tr>
					<tr>
						<td width="20%" class="heading_attributes">Board</td>
						<td class="body_detail_text">
						<span id="BoardOpt">
							<select name="boardId" class="Text" onchange="javascript:loadTopic();">
								<option value="">Please Select...</option>
							</select>
						</span>
						</td>
						<td>&nbsp;</td>
						<td width="20%" class="heading_attributes">Board</td>
						<td class="body_detail_text">
						<span id="desBoardOpt">
							<select name="desBoardId" class="Text" onchange="javascript:loadTopic('des');">
								<option value="">Please Select...</option>
							</select>
						</span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="heading_attributes">Topic</td>
						<td class="body_detail_text">
						<span id="TopicOpt">
							<select name="topicId" class="Text">
								<option value="">Please Select...</option>
							</select>
						</span>
						</td>
						<td>&nbsp;</td>
						<td width="20%" class="heading_attributes">Topic</td>
						<td class="body_detail_text">
						<span id="desTopicOpt">
							<select name="desTopicId" class="Text">
								<option value="">Please Select...</option>
							</select>
						</span>
						</td>
					</tr>
					<tr>
						<td width="20%" class="heading_attributes">Thread</td>
						<td class="body_detail_text">
						<span id="ThreadOpt">
							<select name="threadId" class="Text" size="4">
								<option value=""></option>
							</select>
						</span>
						</td>
						<td>&nbsp;</td>
						<td width="20%" class="heading_attributes">&nbsp;</td>
						<td class="body_detail_text">
						&nbsp;
						</td>
					</tr>
							
				<tr height="30"><td colspan="5"></td></tr>
				<tr>
					<td height="35" align="left" colspan="5">
						<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/adminForumMoveThreadAction.do','save');">
							<img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt="Save"></a>
						
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
