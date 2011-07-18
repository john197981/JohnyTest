<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.forum.OIForumConstants,com.oifm.forum.OIBAPosting" %>



<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditor.js'></script>
<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>

<script language="Javascript">
<!-- 
	var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write('<scr'+'ipt>function editor_generate() { return false; }</scr'+'ipt>'); 
	}

	<logic:iterate id="objExpression" name="ExpressionsList" indexId="ind" type="org.apache.struts.util.LabelValueBean">
		aryCodes[<bean:write name="ind" />] = "<bean:write name="objExpression" property="value" />";
		aryIcons[<bean:write name="ind" />] = "<bean:write name="objExpression" property="label" />";
	</logic:iterate>

	function previewHtmlContent() 
	{
		var strImgRoot1 = '<img src=\"<bean:message key="OIFM.docroot" />';
		var strImgRoot2 = '\" border="0" >';
		var cont = document.PostingForm.strPostingNew.value;
		var ind = -1;
		var code = "";
		for(var j=0; j<aryCodes.length; j++) 
		{
			code = aryCodes[j];
			while((ind = cont.indexOf(code)) != -1)
			{
				cont = cont.substring(0, ind) + strImgRoot1+ aryIcons[j] + strImgRoot2 + cont.substring(ind+code.length, cont.length);
			}
		}
		document.getElementById("testView").innerHTML = cont;
		document.getElementById("displayCont").style.display = "";
		document.PostingForm.strPostingNew.select();
	}

	function insertEmotion(tag) 
	{
 		editor_insertHTML('taContentId1', tag);
	}

	function submitPostingForm(submitUrl, actionName) 
	{
		var frm = document.PostingForm;
		//var flag = false;
		var flag = true;	// changed by K.K.Kumaresan on Mar 18,2009(Ref. to user reported that she is not able to submit)
		var postingNewFlag = false;
		
		postingNewFlag = checkBlank(frm.strPostingNew, "message");
		if(postingNewFlag) 
		{
			//alert("1");
			postingNewFlag = txtAreaMaxLen(frm.strPostingNew, 4000, "message (including RTF tags)");
		}
		
		/*Added/Modified by Aik Mun @ Jan 15, 2009*/
		if(frm.strQuotePost!= null){
			flag = checkBlank(frm.strQuotePost, "message");
			if(flag) 
			{
				//alert("2");
				flag = txtAreaMaxLen(frm.strQuotePost, 4000, "message (including RTF tags)");
			}
		}
		//alert("flag is"+flag);
		
		if(postingNewFlag && flag) 
		{
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
		}
		return;
	}

	function resetForm() 
	{
		document.PostingForm.reset();
	}

// -->
</script>

<BR>	
<% int i = 0; %>

<html:form action="/PostingMaintain.do" method="post" >

	<html:hidden property="hiddenAction" />
	<html:hidden property="strThreadId" />
	<html:hidden property="strPostId" />
	<html:hidden property="pageNo" />

   <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">

		<logic:present name="error" scope="request" >
			<tr>
				<td width="100%" class="BodyText" height="35" valign="middle" colspan="3">
					<b><bean:message name="error" scope="request"/></b>
				</td>
			</tr>
		</logic:present>

  <tr><td class="Box"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
    <tr>
      <td width="17%" class="Table_head">Post Reply </td>
      <td width="10%" class="Table_head">&nbsp;</td>
      <td width="72%" class="Table_head">&nbsp;</td>
    </tr>
    <!--
    <tr>
      <td bgcolor="#E0EBFC" class="Table_Sub_head">
 		<logic:greaterThan name="PostingForm" property="strPostId" value="0" >
			 Modify/View Message  
		</logic:greaterThan>
		<logic:lessThan name="PostingForm" property="strPostId" value="1" >
			 Post Message 
		</logic:lessThan>
 </td>
      <td class="Table_Sub_head">&nbsp;</td>
      <td class="Table_Sub_head">&nbsp;</td>
    </tr>
	-->
	<!-- /*Added/Modified by Aik Mun @ Jan 15, 2009*/ -->
    <tr>
      <td align="right" valign="top" bgcolor="#E0EBFC" rowspan="3">
	      <table height="100%" align='right' cellpadding='4' cellspacing="0">
	      <tr>
	      <td colspan="3" class="Sub_head">
	 		<logic:greaterThan name="PostingForm" property="strPostId" value="0" >
				 Modify/View Message  
			</logic:greaterThan>
			<logic:lessThan name="PostingForm" property="strPostId" value="1" >
				 Post Message 
			</logic:lessThan>
	 		</td>
	     </tr>
	     <tr align='center'>
			<logic:iterate id="objExpression" name="ExpressionsList" indexId="ind" type="org.apache.struts.util.LabelValueBean">
				<td><a href="#" onClick='javascript:insertEmotion("<bean:write name="objExpression" property="value" />")'><img src='<bean:message key="OIFM.docroot" /><bean:write name="objExpression" property="label" />' border='0' /></a>&nbsp;</td>
				<%= (++i%3 == 0)?"</tr><tr align='center'>":"" %>
			</logic:iterate>
	     </tr>
	     <tr align='center'>
	        <td colspan="3"><b>Emotions</b></td>
	     </tr>
	     <tr align='left'>
	        <td colspan="3"  class="Special_body_link" > 
		  		<logic:lessThan name="PostingForm" property="strPostId" value="1" >
				
				<logic:equal name="accessInfo" property="divAdminOrModerator" value="true">
					 <html:checkbox property="strHQReply" value="Y" />HQ Reply
				</logic:equal>
				
				</logic:lessThan>
	 		</td>
	      </tr>
	      </table>
      </td>
      <td colspan="2" align="left" valign="top"> 
		<html:textarea property="strPostingNew" styleId="taContentId1" cols="65" rows="12" styleClass="bodytext" />
		<script language="javascript">
			var config = new Object();
			config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
			config.debug = 0;
			editor_generate('taContentId1',config);
		</script>
		</td>
	</tr>
	<%
		if(request.getAttribute("objPostingVo")!=null){
			OIBAPosting objPostingVo =  (OIBAPosting)request.getAttribute("objPostingVo"); 
		
			if(objPostingVo.getStrQuotePost()!=null){
		%>
	<tr>
		<td colspan="2" class="Table_head">Posting Information </td>
	</tr>
	<tr>
       <td colspan="2" align="left" valign="top">   
		
			<html:textarea property="strQuotePost" styleId="taContentId2" cols="65" rows="12" styleClass="bodytext" />
			<script language="javascript">
				editor_generate('taContentId2',config);
			</script>
		
		</p>
		</td>
     </tr>
     <%	}
		}%>
		<html:hidden property="strPosting" styleId="taContentId" />
		<html:hidden property="strPostingQuote" styleId="taContentId_2" />
     <tr>
      <td class="Table_head">&nbsp;</td>
      <td colspan="2" class="Table_head">
	  
		<logic:greaterThan name="PostingForm" property="strPostId" value="0">
			<logic:match name="PostingForm" property="hiddenAction" value="<%= OIForumConstants.POSTS_MODERATION_EDIT %>">
				<a href="#" onClick='javascript:submitPostingForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTS_MOD_MODIFY %>");'>
					<img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit"></a>
			</logic:match>
			<logic:match name="PostingForm" property="hiddenAction" value="<%= OIForumConstants.POSTING_EDIT %>">
				<a href="#" onClick='javascript:submitPostingForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POSTING_MODIFY %>");'>
					<img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit"></a>
			</logic:match>
		</logic:greaterThan>
		<logic:lessThan name="PostingForm" property="strPostId" value="1">
			<a href="#" onClick='javascript:submitPostingForm("<%= OIForumConstants.POSTINGS_MAINTAIN_DO %>","<%= OIForumConstants.POST_MESSAGE %>");'>
				<img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit"></a>
		</logic:lessThan>
	   
		<a href="#"  onClick="previewHtmlContent();">
			<img src='<bean:message key="OIFM.docroot" />/images/but_Preview.gif' border="0" alt="Preview" width="72" height="24" ></a>

		<a href="#" onClick="window.close();">
			<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt="Cancel" width="72" height="24" ></a>

	 
	   </td>
      </tr>
    
    
  </table></td></tr>
   </table> 
   
</html:form>

<div id="displayCont" style="display:none">	
	<TABLE width="650" cellpadding="3">
		<tr>
			<td class="Table_head">
				<B>Posting Preview</B> 
			</td>
		</tr>
		<tr>
			<td>
				<TABLE style="border: 1px solid #0099FF;" bgcolor="#FFFFFF" width="100%">
					<TR>
						<TD ><div  id="testView" class="bodytext" bgcolor="#FFFFFF"></div></TD>
					</TR>
				</TABLE>
			</td>
		</tr>
	</table>
</div>