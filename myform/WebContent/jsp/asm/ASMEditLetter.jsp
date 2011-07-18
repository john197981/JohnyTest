<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%
try
{
%>
<html:html>
<head>
<style type="text/css"> 
<!-- .style2 {font-size: 11px}
a:link {
	text-decoration: none;
}
.style3 {color: #FFFFCC}
	--> 
	</style>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/MySay.css");
 			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->

   		</style>

	<title>Ask Senior Management</title>
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorASM.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript">
	
	function fnSubmit(actionName,hiddenAction)
	{
		if(!fnValidate()){
			return false;
		}
		if(Trim(document.ASMFormLetter.topic.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("ASM.ALERT.LETTER.TOPIC") %>');
			document.ASMFormLetter.topic.focus();
			return;
		}
		flag = checkBlank(document.ASMFormLetter.letter, "letter");
		if (! flag)
		{
			return;
		}

		document.ASMFormLetter.hiddenAction.value=hiddenAction;
		document.ASMFormLetter.action=actionName;
		document.ASMFormLetter.submit();
	}
	function fnSubmitSimple(actionName,hiddenAction)
	{
		if(hiddenAction=="delete"){
			if(!confirm("Are you sure you want to delete this draft?")){
				return false
			}
		}
		document.ASMFormLetter.hiddenAction.value=hiddenAction;
		//document.ASMFormLetter.action=actionName;
		document.ASMFormLetter.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormLetter.submit();
	}

	function fnClear()
	{
		document.ASMFormLetter.reset();
		document.ASMFormLetter.letter.value=letterContent;
	}

	function fnAlertEmail(strUrl)
	{
		window.showModalDialog(strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
	}
	function fnPrintPreview()
	{
		window.open("asmHome.do?hiddenAction=PrintPreview&hidLetterID="+document.ASMFormLetter.letterId.value,'printPreview','left=1,top=1,width=750,height=500,scrollbars=yes');
	}
	function fnValidate(){
		if(document.ASMFormLetter.contactNo.value!="" && isNaN(document.ASMFormLetter.contactNo.value)){
			alert("Use only numbers for your Contact Number.");
			document.ASMFormLetter.contactNo.focus();
			return false;
		}
		return true;
	}


</script>

</head>

<jsp:include page="/jsp/common/iofm_Top.jsp" flush="true" >	
	<jsp:param name="pageName" value="ASM" />
</jsp:include>

<script language="JavaScript" type="text/JavaScript">
	var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write("<scr"+"ipt>function editor_generate() { return false; }</scr"+"ipt>"); 
	}
</script>

<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr><td valign="top" align="center">

<html:form action="/ASMView" method="post">

		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">What Up in &quot;Ask Senior Management&quot; </td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>

				<%if(request.getParameter("hidPageDesc")!=null && 
					request.getParameter("hidPageDesc").equals("RecentLetters")){
					}else{ %>
				
 						<logic:present name="announcement" scope="request">
							<bean:write name="announcement" scope="request" filter="false" />
						</logic:present>
				<%}%>

                 <br>
                </p>
                 
              </blockquote>
            </div></td>
            </tr>

          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
 			
			<table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td  class="Grey_fade">&nbsp;</td>
  				</tr>
               
			   <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="Box">

<table width="100%"  border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr class="Table_head">
                        <td class="Table_head"> 
							<logic:present name="ASMFormLetter" property="letterId">
									<logic:notEqual name="ASMFormLetter" property="letterId" value="">
										Draft Letter
									</logic:notEqual>
									<logic:equal name="ASMFormLetter" property="letterId" value="">
										New Letter
									</logic:equal>
							</logic:present>
							<logic:notPresent name="ASMFormLetter" property="letterId">
									New Letter
							</logic:notPresent>						
						</td>
						<td width="20%">
                    	
						<div align="right">
                    		<a class="Table_head" href="#" onclick="javascript:fnSubmitSimple('<bean:message key="OIFM.contextroot" />/asmHome.do','populate');">
                    			Back to ASM</a> 
                            <a class="Table_head"  href="#" onclick="javascript:fnSubmitSimple('<bean:message key="OIFM.contextroot" />/ASMView.do','populate');">
							Back to Submit/View Your Letter
                            	</a>
						</div>

						</td>

                      </tr>
                      <tr>
                        <td nowrap bgcolor="#F0F8FF" class="Sub_head">Full Name </td>
                        <td width="82%" class="Heading_Thread">
						<logic:present name="username" scope="session">
							<bean:write name="username" scope="session"/>
						</logic:present>
						</td>
                        </tr>
                      <tr>
                        <td width="18%" align="left" valign="top" nowrap bgcolor="#F0F8FF" class="Sub_head">Email</td>
                        <td align="left" valign="top" class="Heading_Thread">
						
						<logic:present name="email" scope="session">
							<bean:write name="email" scope="session"/>
						</logic:present>
						<logic:notPresent name="email" scope="session">
						"Please update your email from "My Profile" link on top menu."
						</logic:notPresent>
						<br>
				</td>
                      </tr>
                      <tr>
                        <td nowrap bgcolor="#F0F8FF" class="Sub_head">Contact No </td>
                        <td align="left"><html:text name="ASMFormLetter" property="contactNo" styleClass="text_box" size="15" maxlength="15"></html:text>
 						</td>
                      </tr>
                      <tr>
                        <td nowrap bgcolor="#F0F8FF" class="Sub_head">Title *</td>
                        <td align="left" ><html:text name="ASMFormLetter" property="topic" styleClass="text_box" size="100" maxlength="256"></html:text>
 						</td>
                      </tr>
                      <tr>
                        <td align="left" valign="top" nowrap bgcolor="#F0F8FF" class="Sub_head">My Letter * </td>
                        <td align="left" valign="top" > 
                          
						<html:textarea name="ASMFormLetter" property="letter" styleId="taContentId" cols="80" rows="10" styleClass="text_area"/>
						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId',config);
						</script>

	</td></tr>
                      
                      <tr>
                        <td bgcolor="#F0F8FF">&nbsp;</td>
                        <td><span class="Menu_text">
						<a href="#" class="Menu_text" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','submit');"><img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif' border="0" alt="Submit" /></a>
						<a href="#" class="Menu_text" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','draft');"><img src='<bean:message key="OIFM.docroot" />/images/but_Save_Draft.gif' border="0" alt="Save As Draft"/></a>  
						<logic:present name="ASMFormLetter" property="letterId">
				<logic:notEqual name="ASMFormLetter" property="letterId" value="">
					<a href="#" class="Menu_text" onclick="javascript:fnSubmitSimple('<bean:message key="OIFM.contextroot" />/ASMView.do','delete');"><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif' border="0" alt="Delete letter" /></a>
				</logic:notEqual>
			</logic:present> 
			<logic:present name="ASMFormLetter" property="letterId">
				<logic:notEqual name="ASMFormLetter" property="letterId" value="">
					<a href="#" class="Menu_text" onclick="javascript:fnAlertEmail('<bean:message key="OIFM.contextroot" />/AlertFriendAction.do?hiddenAction=populate&module=ASMDRAFT&iD='+<bean:write name="ASMFormLetter" property="letterId" />);"><img src='<bean:message key="OIFM.docroot" />/images/but_email_friend.gif' border="0" alt="Email a friend" /></a>
				</logic:notEqual>
			</logic:present> 			
			<logic:present name="ASMFormLetter" property="letterId">
				<logic:notEqual name="ASMFormLetter" property="letterId" value="">
					<a href="#" class="Menu_text" onclick="javascript:fnPrintPreview();"><img src='<bean:message key="OIFM.docroot" />/images/but_Preview.gif' border="0" alt="Print Preview" /></a>
				</logic:notEqual>
			</logic:present>

			</span> 
						<div class="Sub_head" align = "right"> * - Fields are compulsory to fill.</div>
						</td>
						
                      </tr>
                    </table>

					
					</td>
                  </tr>
                </table>
                  <br></td>
                <!-- <td class="Blue">&nbsp;</td> -->
              </tr>
            </table>
			

			</td>
 		  </tr>
        </table>

			</td>
 		  </tr>
        </table>

<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />

<html:hidden name="ASMFormLetter" property="hiddenAction" />
<html:hidden name="ASMFormLetter" property="letterId" />
</html:form>
</html:html>


<script>
var letterContent= document.ASMFormLetter.letter.value
	
</script>
<%
}catch(Exception e)
{
out.println(e.getMessage());
}
%>