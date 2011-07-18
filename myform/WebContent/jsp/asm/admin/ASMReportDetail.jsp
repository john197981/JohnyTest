
<%--
/**
 * FileName			: ASMHomePage.jsp
 * Author      		: Anbalagan
 * Created Date 	: 09/12/2005
 * Description 		: This page used to display the ASM home page.
						This page is called from a. on click of right side menu topic link
												b. on click of ASM in the top menu
												c. on click of topic link in the ASM report search details
												d. on click of topic link in the Previous Replies search details
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>   
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></script>
 
 <html:form action="asmHome.do" method="post" >
 
 <jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

		<table width="98%"  border="0"  cellpadding="0" cellspacing="0" bgcolor="#f7f8fc">
			<tr>
			  <td class="Box"><table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
					<table width="100%"  border="0" cellpadding="3" cellspacing="0" bgcolor="#f7f8fc">
					<tr align="left" valign="top" class="Table_head">
					  <td >Latest Letter(s)</td>
 
                        <td width="20%" align="right"> 
						<a href="#" onClick="javascript:window.history.back()" scope="request" class="Table_head">Back </a>
						</td>
 					</tr>
                       <tr bgcolor="#E0EBFC">
                        <td class="Sub_head">  
 							<bean:write name="letter_topic" scope="request" filter="false" />
							 
						</td>
						<td class="Sub_head" >  

						<bean:write name="published_on" scope="request"/>
						 &nbsp;
						
						<a href="#" style="cursor:hand" onClick="javascript:fnAlertEmail()"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Email letter link to your friend" width="16" height="16" border="0"></a> 
                        
						&nbsp;
						<a href="#" style="cursor:hand" onclick="fnPrintPreview()"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' alt="Print the letter content as shown" width="16" height="16" border="0"></a>
						</td>
						</tr>


                       <tr >
                        <td colspan="2" class="body_detail_text">  
						<bean:write name="letter_content" scope="request" filter="false" />
						 
						  <br>
                          <span class="body_detail_text">
							<bean:write name="created_by" scope="request" filter="false" />
						  </span> <br></p>
						  </td>
						  </tr>


                      <tr bgcolor="#E0EBFC">
                        <td colspan="2" class="Sub_head">
 						Reply<br></td>
                      </tr>
                      <tr>
                        <td colspan="2" class="body_detail_text"> 
						<p >
						<bean:write name="reply_content" scope="request" filter="false" />
 						 <br><br>
						 <span class="body_detail_text">
                                 <bean:write name="replied_by" scope="request" filter="false" />
								 </span> 
								 <br></p>
						   </div>
					</td>
				</tr>				
			</table>
 
<!-- This hidden variable holds the letter id-->
<html:hidden property="hidLetterID"/>	
<!-- This hidden variable holds the Page Description (ie) ASM Home Page or Recent Replies-->
<html:hidden property="hidPageDesc"/>
<html:hidden property="hiddenAction"/>
<script language="javascript">
	var contextRoot ='<bean:message key="OIFM.contextroot" />'
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
 		document.ASMFormHome.hiddenAction.value=hiddenAction;
		document.ASMFormHome.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormHome.submit();
	}
</script>
<%
	if(request.getParameter("hidPageDesc")!=null && request.getParameter("hidPageDesc").equals("RecentLetters")
	&& !request.getParameter("hiddenAction").equals("PrevRepSearch") && !request.getParameter("hiddenAction").equals("ReportSearch"))
	{ 
%>
		<script>
			fnDisplayHidePrevNext(<%=request.getAttribute("TotRecSizRecLtr")%>)
		</script>
<%
	}
%>
</html:form>