<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.useradmin.OIBAWebRanking" %>
<%@ page import="com.oifm.useradmin.OIBARankingDetails" %>
<%@ page import="java.util.ArrayList" %>
<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<style type="text/css">
	<!--
	.style1 {font-size: 9pt}
	.style3 {font-size: 10}
	-->
</style>

<script language = "javascript">
function submitWebsiteRank() {
	
		document.forms[0].action= '<bean:message key="OIFM.contextroot" />'+'/WebRanking.do';
		document.forms[0].submit();
	
	}
</script>
<%
OIBAWebRanking objRanking = new OIBAWebRanking();
String strDescription = "";
ArrayList alList = new ArrayList();
if(request.getAttribute("WebRanking")!= null){
	objRanking= (OIBAWebRanking)request.getAttribute("WebRanking");
};
if(request.getAttribute("Description")!= null){
	strDescription = (String)request.getAttribute("Description");
};
if(request.getAttribute("ResultDetails")!= null){
	alList = (ArrayList)request.getAttribute("ResultDetails");
};
 %>
<BODY>
<FORM>
<TABLE cellSpacing=0 cellPadding=0 width="98%" align=center border=0>
  <TBODY>
  <TR>
    <TD class=small_text>&nbsp;</TD></TR>
  <TR>
    <TD class=Box>
      <TABLE cellSpacing=0 cellPadding=0 width="100%" align=center 
      bgColor=#ffffff border=0>
        <TBODY>
        <TR>
          <TD class=Table_head colSpan=3>User Access Log for <%=objRanking.getHidUserName()%> </TD></TR>
        <TR>
          <TD vAlign=top width="1%" bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=Table_Sub_head vAlign=top noWrap width="11%" 
            bgColor=#f0f8ff>Duration</TD>
          <TD vAlign=top width="88%">
            <TABLE cellSpacing=0 cellPadding=0 border=0>
              <TBODY>
              <TR>
                <TD class=Heading_Thread vAlign=top noWrap align=left>From</TD>
                <TD vAlign=top noWrap align=left>&nbsp;</TD>
                <TD class=body_detail_text vAlign=center noWrap 
                  align=left><%=objRanking.getFromDate()%></TD>
                <TD vAlign=top noWrap align=left>&nbsp;</TD>
                <TD class=Heading_Thread vAlign=top noWrap align=left>To</TD>
                <TD vAlign=top noWrap align=left>&nbsp;</TD>
                <TD class=body_detail_text vAlign=center noWrap 
                  align=left><%=objRanking.getToDate()%></TD></TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD vAlign=top bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=Table_Sub_head vAlign=top noWrap align=left 
            bgColor=#f0f8ff>Activity</TD>
          <TD class=Heading_Thread vAlign=top><%=strDescription%></TD></TR>
        <TR>
          <TD vAlign=top bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=Table_Sub_head vAlign=top noWrap align=left 
            bgColor=#f0f8ff>Acces Detail </TD>
          <TD class=Heading_Thread vAlign=top>
            <TABLE class=Box cellSpacing=0 cellPadding=0 border=0>
              <TBODY>
              <TR>
                <TD class=Heading_Category noWrap>Sl No. </TD>
                <TD class=Heading_Category noWrap>Item Name </TD>
                <TD class=Heading_Category noWrap>Date Accessed </TD></TR>
                
           <% for (int i = 0; i < alList.size() ; i++){
				OIBARankingDetails objDetails = (OIBARankingDetails)alList.get(i);
				String strActionName = objDetails.getActionName();
				String strActionTime = objDetails.getActionTime();
				strActionName = strActionName==null?"":strActionName;
				strActionTime = strActionTime==null?"":strActionTime;
           %>
              <TR>
                <TD class=body_detail_text vAlign=top><%=String.valueOf(i+1)%></TD>
                <TD class=body_detail_text vAlign=top><%=strActionName%></TD>
                <TD class=body_detail_text vAlign=top><%=strActionTime%></TD></TR>
           <%}%>
           
         </TBODY></TABLE></TD></TR>
        <TR>
          <TD vAlign=top bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=Table_Sub_head vAlign=top noWrap align=left 
          bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=body_detail_text vAlign=top>&nbsp;</TD></TR>
        <TR>
          <TD vAlign=top bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=Table_Sub_head vAlign=top noWrap align=left 
          bgColor=#f0f8ff>&nbsp;</TD>
          <TD class=body_detail_text vAlign=top>
			  <a href="#" onclick="window.close()"><img src="<bean:message key="OIFM.docroot"/>/images/but_ok.gif" border="0" alt="OK"></a>
		  </TD></TR>
        <TR>
          <TD bgColor=#f0f8ff>&nbsp;</TD>
          <TD noWrap bgColor=#f0f8ff>&nbsp;</TD>
          <TD vAlign=top align=left>&nbsp;</TD></TR>
        <TR>
          <TD class=Table_head>&nbsp;</TD>
          <TD class=Table_head>&nbsp;</TD>
          <TD class=Table_head vAlign=top align=left>
		  </TD>
		</TR>
	</TBODY>
	</TABLE>
	</TD></TR></TBODY></TABLE><BR></FORM></BODY></HTML>
