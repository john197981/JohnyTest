<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<head>
<base target=_self>
</head>
<body height=100%>
<title>Email to Friend</title>
<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade"><bean:write name="title" scope="request" filter="false" /></td>
          </tr>
        </table>
<table width="857" border="0" cellspacing="0" cellpadding="0"  height="100%">
<tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
				<logic:present name="title" scope="request"><div align="justify" class="Highlight_body"> 
					<blockquote>
					 <p><br>&nbsp;
					</p>
					</blockquote>
				</logic:present> 
			</div></td>
            </tr>
<tr><td>
<table width="100%"  border="0" cellspacing="0" cellpadding="0">
	<tr>
		<td width="81%" align="center" valign="top">
			<table width="100%"  border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td valign="bottom" >&nbsp;</td>
				</tr>
			</table>    
			<br>
			<table width="98%"  border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="center" valign="top" class="text">
						<logic:notPresent name="ASM" scope="request">
						<logic:notPresent name="invalid" scope="request">
								<logic:present name="content" scope="request">
									<bean:write name="content" scope="request" filter="false" />
								</logic:present> 
						</logic:notPresent> 	
						</logic:notPresent> 
						<logic:present name="ASM" scope="request">
						<logic:notPresent name="invalid" scope="request">
								<logic:present name="valid" scope="request">
									<bean:message key="ASM.MAIL.VALID"/>
									<br><br>
						  			<bean:write name="valid" scope="request" filter="false" />
						  		</logic:present>
						  		
						</logic:notPresent> 	
						</logic:present> 
					</td>
				</tr>
				

			<!-- Send Mail Falied -->	

			<logic:present name="mailerror" scope="request">
					<tr>
			        	<td align="center" valign="top" class="text">
							 <bean:message key="SndMail.failure"/><br>
						</td>
					</tr>	
			</logic:present>
		
		<!-- List of Invalid Email ids -->	
		<logic:notPresent name="ASM" scope="request">
		    <logic:present name="invalid" scope="request">
				<tr>
		        	<td align="center" valign="top" class="text">
						 <bean:message key="SndMail.invalidIds"/>
							<br><br>
						  <bean:write name="invalid" scope="request" filter="false" />
					</td>		 
				</tr>				
			</logic:present>
		    <logic:present name="invalidalone" scope="request">
				<tr>
		    	   	<td align="center" valign="top" class="text">
						 <bean:message key="SndMail.invalidIdAlone"/><br>
						 <bean:write name="invalidalone" scope="request" filter="false" />
					</td>
				</tr>	
		 </logic:present>
		 <logic:present name="noDomains" scope="request">
				<tr>
					<br><br>
		        	<td align="center" valign="top" class="text" noWrap>
							 <bean:message key="SndMail.NoDomains"/>
					</td>
				</tr>
				</table> 
		</logic:present>
	</logic:notPresent>
	<logic:present name="ASM" scope="request">
		    <logic:present name="invalid" scope="request">
				<tr>
		        	<td align="center" valign="top" class="text">
						 <bean:message key="ASM.MAIL.VALID"/>
							<br><br>
						  <bean:write name="valid" scope="request" filter="false" />
						  <br><br>
						  <bean:message key="ASM.MAIL.INVALID.MIDDLE"/>
							<br><br>
						  <bean:write name="invalid" scope="request" filter="false" />
						  <br><br>
						  <bean:message key="ASM.MAIL.INVALID.END"/>
					</td>		 
				</tr>				
			</logic:present>
		    <logic:present name="invalidalone" scope="request">
				<tr>
		    	   	<td align="center" valign="top" class="text">
						 <bean:message key="ASM.MAIL.INVALID.START"/><br><br>
						 <bean:write name="invalidalone" scope="request" filter="false" />
						 <br><br>
						  <bean:message key="ASM.MAIL.INVALID.END"/>
					</td>
				</tr>	
		 </logic:present>
		 <logic:present name="noDomains" scope="request">
				<tr>
					<br><br>
		        	<td align="center" valign="top" class="text" noWrap>
							 <bean:message key="SndMail.NoDomains"/>
					</td>
				</tr>
				</table> 
		</logic:present>
	</logic:present>
			</table>
			<br>
			<table width="98%"  border="0" cellspacing="0" cellpadding="0" height = "330">
				<tr>
					<td align="center" valign = "top">
						<br><br>
						<logic:notPresent name="URL" scope="request">
							<a href="javascript:window.close()" >
								<img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif'  border="0" alt="OK">
							</a>
						</logic:notPresent>
						<logic:present name="URL" scope="request">
							<a href='<bean:write name="URL" scope="request" />' >
								<img src='<bean:message key="OIFM.docroot" />/images/but_ok.gif' border="0" alt="OK">
							</a>
						</logic:present>
						
					</td>
				</tr>
			</table>
			<p>&nbsp;</p>
		</td>
	</tr>
</table></td>
	</tr>
</table>
</body>
</html>