<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
try
{
 %>
<head>
	<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">

	<base target=_self>
</head>
<title>My Forum, Ministry Of Education </title>
<script>
		var strContextRoot = '<bean:message key="OIFM.contextroot"/>'
function fnTopAdminSubmit(actionName,hiddenAction)
			{
	
				try{
					if(opener.basePageFlag){
 						self.close();
						try{
							opener.document.topForm.hiddenAction.value=hiddenAction;
						}catch(e){}
						opener.document.topForm.action=strContextRoot+actionName;
						opener.document.topForm.submit();

					}else{
 						document.topForm.hiddenAction.value=hiddenAction;
						document.topForm.action=strContextRoot+actionName;
						document.topForm.submit();
					}
				}catch(e){
 					//This is for modelss dialog window
 					try{
						if(window.dialogHeight=="260px"){
							self.close();
  						}else{
							document.topForm.hiddenAction.value=hiddenAction;
							document.topForm.action=strContextRoot+actionName;
							document.topForm.submit();
 						}
 					}catch(e){
						document.topForm.hiddenAction.value=hiddenAction;
						document.topForm.action=strContextRoot+actionName;
						document.topForm.submit();
 					}
				}
 			}

function fnBack(link){
	try{
 		if(opener.basePageFlag){
 			self.close();
  		}else{
 			document.topForm.action=link
			document.topForm.submit();
 		}
	}catch(e){
 		document.topForm.action=link
		document.topForm.submit();
 	}
 
 }

</script>
<html>
<body> 
<form name="topForm" method="post">
		<input type="hidden" name="hiddenAction">

<logic:notPresent name="TopFlag" scope="request">
	<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
	   <jsp:param name="pageName" value="" />
	</jsp:include>
</logic:notPresent>

	<table width="857" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td class="Orange_fade">Error</td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0" align="center">
          <tr>
            <td valign="top" class="orange"><div align="center" class="Highlight_body"> 
              <blockquote>
                <p><br>The following error has occured.
                  <br>
                  <br>
                  </p>
                </blockquote>
            </div></td>
            </tr>
</table>
	<table width="98%"  border="0" cellspacing="0" cellpadding="0" height = "400">
		<tr class="body_extract_text">
			<td align="center" valign = "top" >
				<br>
					<logic:notPresent name="error" scope="request">
						<bean:parameter id="errorId" name="error" />
						<logic:empty name="errorId" scope="request">
							<bean:message key="<%= errorId %>" />
						</logic:empty>
					</logic:notPresent>
					<logic:present name="error" scope="request">
						<bean:write name="error" scope="request" />
					</logic:present>
				<br><br>
					<logic:notPresent name="URL" scope="request">
						<a onclick="fnBack('<%= request.getHeader("Referer") %>')">
						<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' width="60" height="22" border="0" alt="Back"><a>
					</logic:notPresent>
				<br><br>
					<logic:present name="URL" scope="request">
						<a href="javascript:fnTopAdminSubmit('/loginAction.do','logout');">
						<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' width="60" height="22" border="0" alt="Back"><a>
					</logic:present>
				
			</td>
		</tr>
	</table>
</form>
</body>
</html>
<%
}catch (Exception e)
{
	out.println(e.getMessage());
}
 %>
