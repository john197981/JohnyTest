<%--
/**
 * FileName			: iofmMail.jsp
 * Author      		: Suresh Kumar.R, Rakesh Chagallu
 * Created Date 		: 4 Aug 2005 
 * Modified Date		: 21 Jan 2008
 * Description 		: This page used to Send the Mail to Admin/Users.
 * Version			: 1.0
 **/ 
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
  

<html:html>
	<head>
		<base target=_self>
		<title>Send Mail</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
		<script>
			

			var strDocRoot ='<bean:message key="OIFM.contextroot" />';
			var strUserName="";
			
			strUserName = '<%=session.getAttribute("username")%>';
			strNickName = '<%=session.getAttribute("nickname")%>';

			function fnSetMsg()
			{
				if(document.SendMailForm.flag.value=="A")
				{
					document.SendMailForm.subject.value= 'My Forum Admin Alert Message from ' + strUserName;
				}
				else if(document.SendMailForm.flag.value=="P")
				{
					document.SendMailForm.subject.value= '<bean:message key="MsgPersonal_1"/>' + strNickName + ' <bean:message key="MsgPersonal_2"/>'
				}
			}
		</script>	  
		<script language="javascript" src='<bean:message key="OIFM.docroot"/>/js/AdminMail.js'></script>
	</head>
	
	<body onLoad="fnMailLoad();fnSetMsg()">
		<html:form action="/SendMail" method="post">		
		    <html:hidden property="email"/>
		  	<html:hidden property="id"/>
			<html:hidden property="hiddenAction"/>
			<html:hidden property="subject"/>
			<html:hidden property="name"/>
			<html:hidden property="flag"/>
	
			<%boolean bFlag=  false; %>

			<table width="98%"  border="0" cellpadding="0" cellspacing="0" bgcolor="white">
				<logic:present name="Mail" scope="request">
					<tr>
						<br><br>
			        	<td align="center" valign="top" class="body_detail_text" noWrap>
							<bean:message key="SndMail.success"/>
						</td>
					</tr>
					</table> 
					<%bFlag=true;%> 
				 </logic:present>	
	   	
				<!-- Send Mail Falied -->	

				<logic:present name="mailerror" scope="request">
					<tr>
			        	<td align="center" valign="top" class="body_detail_text">
							 <bean:message key="SndMail.failure"/><br>
						</td>
					</tr>	
					<%bFlag=true;%> 
				</logic:present>
		
				<!-- List of Invalid Email ids -->	

			    <logic:present name="invalid" scope="request">
					<tr>
		        	 	<td align="center" valign="top" class="body_detail_text">
							<bean:message key="SndMail.invalidIds"/>
								<br><br>
							<bean:write name="invalid" scope="request"/>
						</td>
					</tr>
					<%bFlag=true;%>
				</logic:present>
				
				<logic:present name="invalidalone" scope="request">
					<tr>
		    	   		<td align="center" valign="top" class="body_detail_text">
							<bean:message key="SndMail.invalidIdAlone"/><br>
							<bean:write name="invalidalone" scope="request"/>
						</td>
					</tr>	
					<%bFlag=true;%> 
				</logic:present>
				
				<logic:present name="noDomains" scope="request">
					<tr>
						<br><br>
			        	<td align="center" valign="top" class="body_detail_text" noWrap>
							<bean:message key="SndMail.NoDomains"/>
						</td>
					</tr>
					</table> 
					<%bFlag=true;%> 
				</logic:present>
	
	
			<%if(bFlag){ %>
				<tr>
					<td align="center" valign="top" class="body_detail_text" noWrap>
						 <br><br>
						 <div align="center">
							<a href="#" onClick="javascript:fnMailClose();"><img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif' border="0" alt="Close"></a> 
						 </div><br><br>
					</td>
				</tr>
				</table> 
			<%}%>	 	
		 		
		    <% if(!bFlag){%>
				<table width="100%"  border="0" cellpadding="0" cellspacing="0" bgcolor="white" class="Box">
					<tr>
						<td width="5%"   nowrap align="left" valign="top" > <font size="2"><b>From </b></font>
						</td><td class="body_detail_text">
							<logic:equal name="SendMailForm" property="flag" value="P">
								<% if(null!=request.getAttribute("NickName")){ %>
									<%=request.getAttribute("NickName") %>
								<%}else{ %>
									<%= session.getAttribute("nickname") %>
								<%} %>
							</logic:equal>	 
							<logic:equal name="SendMailForm" property="flag" value="A">
								<%= session.getAttribute("username") %>
							</logic:equal>	
						</td>
			       	</tr>
					
					<tr>
						<td width="5%"   nowrap align="left" valign="top" > <font size="2"><b>To </b></font>
						</td><td class="body_detail_text">
							<logic:equal name="SendMailForm" property="flag" value="P">
								<bean:write name="SendMailForm" property="name"/>
							</logic:equal>	 
							<logic:equal name="SendMailForm" property="flag" value="A">
								Admin
							</logic:equal>	
						</td>
			       	</tr>
				   	<tr>
						<td nowrap align="left" valign="top"><font size="2"><b>Sub</b></font>
						</td><td class="body_detail_text">
							<logic:equal name="SendMailForm" property="flag" value="A" scope="request">
								<bean:message key="MsgAdmin"/>
						 	</logic:equal>
		                	 <logic:equal name="SendMailForm" property="flag" value="P" scope="request">
		                	 	<bean:message key="MsgPersonal_1"/><% if(null!=request.getAttribute("NickName")){ %>
									<%=request.getAttribute("NickName") %>
								<%}else{ %>
									<%= session.getAttribute("nickname") %>
								<%} %><bean:message key="MsgPersonal_2"/>
						 	</logic:equal>
						<td>
					</tr>
					<tr>
						<td colspan="2" align="left" valign="top" noWrap><font size="2"><b>Message:</b></font></td>
					</tr>
					<tr>
						<td colspan="2" >   	
							<html:textarea  property="message" styleClass="Text" cols="40" rows="3">
					  		</html:textarea>
						</td>
					</tr>
					
				   	<logic:equal name="SendMailForm" property="flag" value="A">		
					<tr>
						<td width="5%"   nowrap align="left" valign="top" > <font size="2"><b>Note : </b></font>
						</td><td class="body_detail_text" wrap="true">
							This message would be sent to MyForum moderators.
						</td>
			       	</tr>
					</logic:equal>
					
					<tr>
						<td align="left" colspan="2">
	                  		<img src='<bean:message key="OIFM.docroot"/>/images/but_send.gif'  style="cursor:hand" onClick="fnMailSubmit();" alt="Send"> 
		                  	<img src='<bean:message key="OIFM.docroot"/>/images/but_Cancel.gif'  style="cursor:hand" onClick="fnMailClose();" alt="Cancel">
    		        	</td>
					</tr>
				</table>
			<%}%>
		</html:form>	
	</body>
</html:html>