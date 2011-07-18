<%--
/**
 * FileName			: ASMHomePage.jsp
 * Author      		: Anbalagan
 * Created Date 	: 09/12/2005
 * Description 		: This page used to display the ASM home page.
 * Version			: 1.0
 **/  
--%>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
   
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></script>

<style type="text/css">
	<!--
	.printPreview {
	FONT-FAMILY: arial;
	FONT-SIZE: 12px;
	}
	-->
</style>

<title>Latest Letter</title>

<html:form action="asmHome.do" method="post">
		<td >
			<table border="0" width="100%" cellpadding="6" >
				<tr>
					<td class="BodyText"> <img src='<bean:message key="OIFM.docroot" />/images/asm_top_banner.gif'>

					</td>
				</tr>

				<tr>
				<table width="100%" border="0" class="BoxTable"> 
					
					
					<tr>
						<td height="25" align="right"><div id="1"> <a href="#" style="cursor:hand" onclick="fnPrint()" ><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' alt="Print the letter content as shown" width="16" height="16" border="0"></a>
						</div>
						</td>
					</tr>
					
					<!-- Letter Start-->
					<tr>
						<td class="Subhead" height="25"><div class="printPreview"><strong>
							<%=""+request.getAttribute("letter_topic")%>	
							</strong>
						</div>
						</td>
					</tr>
					<tr>
						<td align="right"><div class="printPreview"><strong>
							<%=""+request.getAttribute("published_on")%>	
							</strong></div>
						</td>
					</tr>

					<tr>
						<td class="BodyText"> <div class="printPreview">
							<%=""+request.getAttribute("letter_content")%>
							<br><br><%=""+request.getAttribute("created_by")%>
							</div>						
						</td>
					</tr>
					<!-- Letter End -->

					<!-- Reply Start-->
					<tr height="30">
						<td></td>
					</tr>
					<tr>
						<td class="Subhead" height="25">
							<div class="printPreview"><strong>Reply	<strong></div>
						</td>
					</tr>
					<tr>
						<td class="BodyText"> <div class="printPreview">
							<%=""+request.getAttribute("reply_content")%>
							<br><br><%=""+request.getAttribute("replied_by")%>
							</div>
						</td>
					</tr>
					<!-- Reply End -->
					
					<tr>
						<td height="25" align="right"> <div id="2"><a href="#" style="cursor:hand" onclick="fnPrint()" ><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif' alt="Print the letter content as shown" width="16" height="16" border="0"></a>
						</td></div>
					</tr>
					
			</table>
			
		</td>
		


	
<html:hidden property="hiddenAction"/>

</html:form> 