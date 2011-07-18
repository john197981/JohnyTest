<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<html:html>
	<head>
		<title>Welcome   to IOFM Login Page</title>
		<meta name="GENERATOR" content="Microsoft Visual Studio.NET 7.0">
		<meta name="vs_targetSchema" content="http://schemas.microsoft.com/intellisense/ie5">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
		<script language="javascript" >
			function fn_login()
			{
				if(Trim(document.loginForm.userid.value)=="")
				{
					alert("Please enter User ID.");
					document.loginForm.userid.focus();
				}
				else if(Trim(loginForm.password.value)=="")
				{
					alert("Please enter password.");
					document.loginForm.password.focus();
				}
				else
				{
					if (document.loginForm.userid.value.length>9)
					{
						document.loginForm.action='<bean:message key="OIFM.contextroot" />/loginAction.do';
					}
					document.loginForm.submit();
				}
			}
			function fn_clear()
			{
				loginForm.reset();
			}
		</script>
		<style type="text/css">
			<!--
			body 
			{
				background-image: url(<bean:message key="OIFM.docroot" />/images/loginbk.gif);
				margin-left: 0px;
				margin-top: 0px;
				margin-right: 0px;
				margin-bottom: 0px;
			}
			.style1 
			{
				font-family: Arial, Helvetica, sans-serif;
				font-size: 10px;
				font-weight: bold;
			}
			-->
		</style>
	</head>
	<body>
		<html:form action="/loginAction.do">
		<input type="hidden" name="hiddenAction" value="valid">
			<P>&nbsp;</P>
			<table width="779" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td width="468" height="141">&nbsp;</td>
					<td width="311">&nbsp;</td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td>
						<TABLE id="Table1" cellSpacing="0" cellPadding="2" width="194" align=left border=0>
							<TR>
								<TD width="44">&nbsp;</TD>
								<TD width="142">
									<html:text property="userid" size="18" value="" />
								</TD>
							</TR>
							<TR>
								<TD>&nbsp;</TD>
								<TD>
									<html:password property="password" size="18"  value=""/>
								</TD>
							</TR>
							<TR>
								<TD align=right>
									<INPUT id="Checkbox1" type="checkbox" name="Checkbox1">
								</TD>
								<TD><span class="style1">Remember login info</span></TD>
							</TR>
							<TR>
								<TD colspan="2">
									<div align="center"><img src='<bean:message key="OIFM.docroot" />/images/button_login.gif' width="145" height="29" border="0" usemap="#Map"></div>
								</TD>
							</TR>
						</TABLE>
					</td>
				</tr>
			</table>
			<% 
				String module = (String) request.getParameter("module");
				String id = (String) request.getParameter("id");
				Cookie c = new Cookie("MyID", id);
				c.setMaxAge(0);
				c.setPath("/");
				response.addCookie(c);
				c = new Cookie("MyID", id);
				c.setMaxAge(60*60*24*365);
				c.setPath("/");
				response.addCookie(c);
//out.println(c.getValue());
				Cookie info = new Cookie("MyModule", module); 
				info.setMaxAge(0);
				info.setPath("/");  
				response.addCookie(info);
				info = new Cookie("MyModule", module); 
				info.setMaxAge(60*60*24*365);  
				info.setPath("/");  
				response.addCookie(info);
//out.println(info.getValue());

			%>
			<map name="Map">
				<area shape="rect" coords="7,2,66,22" href="javascript:fn_login()" target="_top"  alt="Click to Login">
				<area shape="rect" coords="82,1,136,23" href="javascript:fn_clear()" alt="Click to clear the fields">
			</map>
		</html:form>
	</body>
</html:html>