<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js' ></SCRIPT>
<script language="javascript">
<!--
function showAll() {
	
	var right = "TR";
	var down = "TD";
	var expand = "TE";
	var collapse = "TC";

	for(var i=0; i < <bean:write name="total" scope="request" />;i++){
		show(i);
	}
	
	document.getElementById(right).style.display = "none";
	document.getElementById(down).style.display = "";
	document.getElementById(expand).style.display = "none";
	document.getElementById(collapse).style.display = "";
}

function hideAll() {
	
	var right = "TR";
	var down = "TD";
	var expand = "TE";
	var collapse = "TC";

	for(var i=0 ; i < <bean:write name="total" scope="request" />;i++){
		hide(i);
	}
	
	document.getElementById(right).style.display = "";
	document.getElementById(down).style.display = "none";
	document.getElementById(expand).style.display = "";
	document.getElementById(collapse).style.display = "none";
}

function show(sectionId) {

	var right = sectionId + "R";
	var down = sectionId + "D";
	var profile = sectionId + "P";
 	document.getElementById(right).style.display = "none";
 	document.getElementById(down).style.display = "";
 	document.getElementById(profile).style.display = "";
 
}

function hide(sectionId) {

	var right = sectionId + "R";
	var down = sectionId + "D";
	var profile = sectionId + "P";
 	document.getElementById(right).style.display = "";
 	document.getElementById(down).style.display = "none";
	document.getElementById(profile).style.display = "none";
}
//-->
</script>

 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Our Senior Management</td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
				
					<logic:present name="welcomeMessage" scope="request">
						<bean:write name="welcomeMessage" scope="request" filter="false" />
					</logic:present>
                 <br>
                </p>
                 
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
 			<table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="648" class="Grey_fade" >&nbsp;</td>
                <td width="16" class="Blue" >&nbsp;</td>
                <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				  

<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="Senior" />
</jsp:include>
					   

					   <p class="Address_body">&nbsp;</p></td>
              </tr>

              <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc">
				<table width="98%"  border="0" valign="top"  align="center" cellpadding="0" cellspacing="0">
				<tr>
						<td class="Box" valign="top" >
							<table width="100%" valign="top"  border="0" cellspacing="0" cellpadding="0">
								<tr>
								  <td width="3%" align="left" class="Table_head">
								  
								  
					<a href="javascript:showAll()" class="BodyText_profile">
						<div id='TR'>
							<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' alt="Expand"  border="0" width="15" height="15" >
						</div>
					</a>
					<a href="javascript:hideAll()" class="BodyText_profile">
						<div id='TD' style="display:none">
							<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif'  border="0" alt="Collapse" border="0" width="15" height="15" >
						</div>
					</a>
								  
								  </td>
								  <td width="97%" class="Table_head">
									<div id='TE'>Expand All</div>
									<div id='TC' style="display:none">Collapse All</div>
								  </td>
								</tr>
							</table>
						</td>
				  </tr>

			<% String currHeading = null; %>
			

                  <tr>
                    <td class="Box">

<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">

<logic:iterate id="management" name="list" indexId="idx" type="com.oifm.asm.ASMBAManagement">                       
			<% if (currHeading == null || !currHeading.equals(management.getHeading())) { %>
			<% 		currHeading = management.getHeading(); %>						
						<tr>
                          <td colspan="4" bgcolor="#F0F8FF" class="Sub_head">
								<bean:write name="management" property="heading" />
							</td>
                        </tr>
						<% } %>

                        <tr>

                          <td colspan="4" bgcolor="#F0F8FF" class="Table_Sub_Topic">
						  <bean:write name="management" property="subheading" />
						  </td>
                        </tr>
                        <tr>
                          <td align="right">

					<a href="javascript:show('<bean:write name="idx" />')" class="BodyText_profile">
						<div id='<bean:write name="idx" />R'>
							<img src='<bean:message key="OIFM.docroot" />/images/btn_rightarrow.gif' ALT="Expand"   BORDER="0" width="15" height="15" >
						</div>
					</a>
					<a href="javascript:hide('<bean:write name="idx" />')" class="BodyText_profile">
						<div id='<bean:write name="idx" />D' style="display:none">
							<img src='<bean:message key="OIFM.docroot" />/images/DownArrow.gif'   BORDER="0" ALT="Collapse" width="15" height="15" >
						</div>
					</a>
						   
						  </td>
                          <td colspan="3" align="justify" class="body_detail_text"><p><strong>
						  <bean:write name="management" property="name" />
						  </strong> 
						  <bean:write name="management" property="designation" />, <bean:write name="management" property="division" />
						<br>
                          </p></td>
                        </tr>
                        <tr class="BodyText" id='<bean:write name="idx" />P' style="display:none">
					
				<td align="left" valign="top">&nbsp;</td>
				<td>
					<table width="100%" border="0" cellpadding="0" cellspacing="0">

						<tr valign="top">
							<td width="105">
						  
								<logic:notEmpty name="management" property="photograph">
									<img src='<bean:message key="OIFM.contextroot" />/ASMManagement.do?hiddenAction=PHOTO&photograph=<bean:write name="management" property="photograph" />' alt='<bean:write name="management" property="name" />'>
								</logic:notEmpty>
								<logic:empty name="management" property="photograph">
									&nbsp;
								</logic:empty>
						  
							</td>
                          <td width="84%" colspan="1" align="justify" class="body_extract_text">
						  
						  <bean:write name="management" property="profile" filter="false"/>

                                  <br>
                                  
								<logic:notEmpty name="management" property="divisionurl">
								<a href='http://<bean:write name="management" property="divisionurl" />' target="_blank">
									<bean:write name="management" property="division" /> <span class="Menu_text">Division Homepage</a></span> (available only in MOE intranet)
								</logic:notEmpty>
								  
                          </td>
                        </tr>
                        <tr>
                          <td align="right">&nbsp;</td>
                          <td colspan="3">&nbsp;</td>
                        </tr>
					</table>
				</td>
			</tr>
			</logic:iterate>
                    </table>				
					</td>
                  </tr>
                </table>
                  </td>
                <td class="Blue">&nbsp;</td>
              </tr>
            </table>
			</td>
 		  </tr>
        </table>
<script language="javascript">
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
 		document.ASMFormHome.hiddenAction.value=hiddenAction;
		document.ASMFormHome.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormHome.submit();
	}
</script>