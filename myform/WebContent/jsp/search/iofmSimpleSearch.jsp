<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.search.OISearchConstants" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.forum.OIForumConstants" %>

<style type=text/css>
	.activetab 
		{
		 font-family: Verdana;
		 font-weight: normal;
		 BORDER-RIGHT: red 1px solid;
		 BORDER-TOP: red 1px solid; 
		 MARGIN-TOP: 1px; 
		 BORDER-BOTTOM-WIDTH: 1px; 
		 BORDER-BOTTOM-COLOR: red; 
		 MARGIN-LEFT: 1px; 
		 BORDER-LEFT: red 1px solid; 
		 WIDTH: 120px; 
		 MARGIN-RIGHT: 1px;
		 font-size: 11px;
		 text-align: center;
		}
	.inactivetab
		{
		font-family: Verdana;
		font-weight: normal;
		BORDER-RIGHT: silver 1px solid; 
		BORDER-TOP: silver 1px solid; 
		MARGIN-TOP: 1px; 
		BORDER-BOTTOM-WIDTH: 1px; 
		BORDER-BOTTOM-COLOR: red; 
		MARGIN-LEFT: 1px; 
		BORDER-LEFT: silver 1px solid; 
		WIDTH: 120px; 
		MARGIN-RIGHT: 1px;
		font-size: 11px;
		text-align: center;
		}
	
	.bodytopborder
	{
		BORDER-TOP: red 1px solid; 		
	}
	.bodyleftborder
	{
		BORDER-LEFT: red 1px solid; 
	}				
	.bodyrightborder
	{
		BORDER-RIGHT: red 1px solid; 		
	}
	.bodybottomborder
	{
		BORDER-BOTTOM: red 1px solid; 		
	}
	.bodytoprightborder
	{
		BORDER-RIGHT: red 1px solid; 
		BORDER-TOP: red 1px solid;
	}
	.bodytopleftborder
	{
		BORDER-LEFT: red 1px solid; 
		BORDER-TOP: red 1px solid;
	}
	.bodyleftbtmrightborder
	{
		BORDER-LEFT: red 1px solid; 
		BORDER-BOTTOM: red 1px solid;
		BORDER-RIGHT: red 1px solid;
	}
	
</style>
 
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Search</td>
          </tr>
        </table>

         <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange">
            <div align="justify" class="Highlight_body">
              <p>&nbsp;</p>
              <p> 
              </p>
            </div></td>
            <td width="664" valign="top" class="orange"><p>&nbsp;</p>
              <p><span class="Highlight_body">  
 
                  <br>
                </p></td>
            <td width="148" valign="top" class="orange">&nbsp;</td>
          </tr>
          
          <tr>
            <td colspan="3" class="Grey_fade">&nbsp;</td>
          </tr>
          <tr>
</table>

<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="85%" align="center" valign="top">
       <table width="100%"  border="0" cellspacing="0" cellpadding="0">
         <tr>
          <td colspan="3" width="100%">
          	<table cellSpacing=0 cellPadding=0 width="100%" border=0 >
        		<tr>
        			<logic:equal name="Type" value="Forum" scope="request"><td class="activetab" bgColor="#ffcc66"></logic:equal><logic:notEqual name="Type" value="Forum" scope="request"><td class="inactivetab" bgColor="#cccccc"></logic:notEqual>
        				<a href="#" onclick="fnSearchSubmitType('<%= OISearchConstants.SEARCH_FORUM %>')">Discussion Forum</a></td>
        			<td style="WIDTH: 1px"></TD>
        			<logic:equal name="Type" value="Survey" scope="request"><td class="activetab" bgColor="#ffcc66"></logic:equal><logic:notEqual name="Type" value="Survey" scope="request"><td class="inactivetab" bgColor="#cccccc"></logic:notEqual>
        				<a href="#" onclick="fnSearchSubmitType('<%= OISearchConstants.SEARCH_SURVEY %>')">Survey</a></td>
        			<td style="WIDTH: 1px"></TD>
        			<logic:equal name="Type" value="Paper" scope="request"><td class="activetab" bgColor="#ffcc66"></logic:equal><logic:notEqual name="Type" value="Paper" scope="request"><td class="inactivetab" bgColor="#cccccc"></logic:notEqual>
        				<a href="#" onclick="fnSearchSubmitType('<%= OISearchConstants.SEARCH_PAPER %>')">Consultation Paper</a></td>
        			
        			<!-- commented by K.K.Kumaresan on June 29,2009 to hide the blog module										          
        			<td style="WIDTH: 1px"></TD>
        			<logic:equal name="Type" value="Blog" scope="request"><td class="activetab" bgColor="#ffcc66"></logic:equal><logic:notEqual name="Type" value="Blog" scope="request"><td class="inactivetab" bgColor="#cccccc"></logic:notEqual>
        				<a href="#" onclick="fnSearchSubmitType('<%= OISearchConstants.SEARCH_BLOG %>')">Blog</a></td>
        			<td>&nbsp;</TD>
        			-->
        			
        			<logic:equal name="Type" value="ASM" scope="request"><td class="activetab" bgColor="#ffcc66"></logic:equal><logic:notEqual name="Type" value="ASM" scope="request"><td class="inactivetab" bgColor="#cccccc"></logic:notEqual>
        				<a href="#" onclick="fnSearchSubmitType('<%= OISearchConstants.SEARCH_ASM %>')">ASM</a></td>
        			<td style="WIDTH: 1px"></TD>
        			
        		</TR>
        	
        	
        		<tr>
          			<logic:equal name="Type" value="Forum" scope="request"><td class=bodyleftborder></logic:equal><logic:notEqual name="Type" value="Forum" scope="request"><td class="bodytopleftborder"></logic:notEqual>&nbsp;</TD>
          			<td class=bodytopborder style="WIDTH: 1px">&nbsp;</TD>
          			<logic:equal name="Type" value="Survey" scope="request"><td></logic:equal><logic:notEqual name="Type" value="Survey" scope="request"><td class="bodytopborder"></logic:notEqual>&nbsp;</TD>
          			<td class=bodytopborder style="WIDTH: 1px">&nbsp;</TD>
          			<logic:equal name="Type" value="Paper" scope="request"><td></logic:equal><logic:notEqual name="Type" value="Paper" scope="request"><td class="bodytopborder"></logic:notEqual>&nbsp;</TD>
          			<td class=bodytopborder style="WIDTH: 1px">&nbsp;</TD>
          			<logic:equal name="Type" value="ASM" scope="request"><td></logic:equal><logic:notEqual name="Type" value="ASM" scope="request"><td class="bodytopborder"></logic:notEqual>&nbsp;</TD>
          			<td class=bodytopborder style="WIDTH: 1px">&nbsp;</TD>
          		</TR>
          		</table>
          		<table cellSpacing=0 cellPadding=0 width="100%" border=0 >
        <tr>
          <td class="bodyleftbtmrightborder" colSpan=6>
            <table cellSpacing=0 cellPadding=0 width="100%" border=0>
        <tr>
        	<bean:size id="arraySize" name="SearchTokens" scope="request" />
        	<td class="sub_head">&nbsp;</td>
        	<td colspan="2" class="sub_head">Searching
        		<logic:equal name="Type" value="Forum" scope="request"><b>Discussion Forum</b></logic:equal>
        		<logic:equal name="Type" value="Survey" scope="request"><b>Survey</b></logic:equal>
        		<logic:equal name="Type" value="Paper" scope="request"><b>Consultation Paper</b></logic:equal>
        		<logic:equal name="Type" value="ASM" scope="request"><b>ASM</b></logic:equal>
        		for <logic:iterate id="strToken" name="SearchTokens" indexId="idx" type="java.lang.String"><b><%= strToken %></b><% if (idx.intValue() < (arraySize.intValue() - 1)) { %>,<% } %> </logic:iterate></td>
        </tr>
        <tr>
        	<td class="heading_thread">&nbsp;</td>
        	<td colspan="2" class="heading_thread"><bean:write name="TotalResult" scope="request" /> records found</td>
        </tr>
        <tr>
          <td colspan="3">&nbsp;</td>
        </tr>
        <tr>
                <td colspan="3" width="98%" align="right" valign="top" nowrap>
                <table width="98%" align="center" border="0" cellpadding="2" cellspacing="0" class="Box">
				<tr>
				  <td colspan="2" class="table_head">
				  Search Result</td>
				  <td class="table_head">&nbsp;</td>
				</tr>
				<bean:size id="arraySize" name="SearchResult" scope="request" />
				<% if (arraySize.intValue() > 0) { %>
				<logic:iterate id="result" name="SearchResult" indexId="idx" type="com.oifm.search.OISearchResultBean">
				<tr>
				  <td width="8%" align="left" valign="top" class="body_detail_text"><bean:write name="result" property="rowNum" />.</td>
				  <td width="63%" align="left" valign="top" class="body_detail_text">
					<logic:equal name="Type" value="Forum" scope="request"><a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OIForumConstants.THREAD_MAINTAIN_DO %>?strThreadId=<bean:write name="result" property="strID" />'></logic:equal>
					<logic:equal name="Type" value="Survey" scope="request"><a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_USER_DO %>?hiddenAction=SurveyDetails&strSurveyId=<bean:write name="result" property="strID" />'></logic:equal>
					<logic:equal name="Type" value="Paper" scope="request"><a class="special_body_link" href="<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?hiddenAction=populate&paperId=<bean:write name="result" property="strID" />"></logic:equal>
					<logic:equal name="Type" value="ASM" scope="request"><a class="special_body_link" href='<bean:message key="OIFM.contextroot" />/asmHome.do?hidPageDesc=RecentLetters&hiddenAction=populate&hidLetterID=<bean:write name="result" property="strID" />'></logic:equal>
					<bean:write name="result" property="strTitle" /></a><br>
					  <bean:write name="result" property="strDescription" filter="false" /><br>&nbsp;</td>
				  <td width="29%" align="left" valign="top" class="body_detail_text">
					<logic:equal name="Type" value="Forum" scope="request">Posted by <a class="special_body_link" href="#" onclick="javascript:window.showModalDialog('<bean:message key="OIFM.contextroot" />/MemberProfileAction.do?nric=<bean:write name="result" property="strCreatedBy" />&hiddenAction=populate','mywindow','dialogWidth:900px;dialogHeight:260px;dialogLeft:50px;dialogRight:0px;resizable:yes,scrollbars:yes;help:no;status:off;' );"><bean:write name="result" property="strNickname" /></a><br></logic:equal>
					<logic:notEqual name="Type" value="Forum" scope="request">Created by <bean:write name="result" property="strNickname" /><br></logic:notEqual>
			  on <bean:write name="result" property="strCreatedOn" /> <br>
				  </td>
				</tr>
				</logic:iterate>
				<% } else { %>
				<tr><td colspan="3" class="body_detail_text">&nbsp;</td></tr>
				<tr>
					<td width="8%" align="left" valign="top" class="body_detail_text">&nbsp;</td>
					<td width="73%" class="body_detail_text" align="center">No Result</td>
					<td width="19%" align="left" valign="top" class="body_detail_text">&nbsp;</td>
				</tr>

        <tr><td colspan="3" class="body_detail_text">&nbsp;</td></tr>
        <% } %>
				</td></table></tr>
				<tr><td height="30" colspan="3"></td></tr>

        </TABLE></TD></TR></TABLE></TD></TR>
      <tr><td colspan="3" align="right" valign="top">
      <table width="100%" height="35"  border="0" cellpadding="0" cellspacing="0">
      <tr>
<logic:present name="PageInfo" scope="request" >
	<bean:define id="objPageInfo" name="PageInfo" scope="request" type="com.oifm.common.OIPageInfoBean" />
	<bean:define id="selPageNo" name="objPageInfo" property="pageNo"/> 
	<logic:greaterThan name="objPageInfo" property="noOfPages" value="1">
           	<td valign="bottom" align="right" class="Text">
<%
		for(int i=1, j=((java.lang.Integer)selPageNo).intValue(); i<=objPageInfo.getNoOfPages(); i++) {
			if(i != j) {
%>
	            <a href="#" onClick="javascript:fnSearchSubmitPage('<%= i %>')"><%= i %></a>&nbsp;
<%
			} else {
%>
				<%= i %>&nbsp;
<%		
			}
		}
%>
				</td>              
	</logic:greaterThan >
</logic:present>
      </tr>
    </table>
    </td></tr>
    <tr>
          <td align="left" valign="top">&nbsp;</td>
          <td align="left" valign="top">&nbsp;</td>
          <td align="left" valign="top">&nbsp;</td>
        </tr>
</table>