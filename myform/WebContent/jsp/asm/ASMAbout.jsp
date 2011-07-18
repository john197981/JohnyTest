<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js' ></SCRIPT>


 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade"><a name="top">About ASM</a></td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
				<logic:present name="ModuleDesc" scope="request">
					<bean:write name="ModuleDesc" scope="request" filter="false" />
					</logic:present>
					<br>            
				<br>
                </p>
                 
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
 			<table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="648" class="Grey_fade">&nbsp;</td>
                <td width="16" class="Blue">&nbsp;</td>
                <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				  

<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="About" /> </jsp:include>
					   

<p class="Address_body">&nbsp;</p></td>
              </tr>
              <tr>
                <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="Box">
					

<table width="100%" cellpadding="0" cellspacing="0" bgcolor="white">
                      <tr>

                        <td ><table width="100%" cellpadding="5" cellspacing="0" bgcolor="white">

							<tr>
                              <td  colspan="2" valign="top" class="Table_head">About Ask Senior Management </td>
                            </tr>

					<logic:iterate id="about" name="list" indexId="idx" type="com.oifm.asm.ASMBAAbout">
                            <tr class="BodyText"  >
                              <td class="BodyText" colspan="2">
							  
							  <a class="Menu_text" href='#id<bean:write name="about" property="id" />'><bean:write name="about" property="question" /></a>							  
							  </td>
                            </tr>

					</logic:iterate>
					 <tr height="50" class="BodyText">
					 <td colspan="2"></td>
					 </tr>

 
                             <logic:iterate id="about" name="list" indexId="idx" type="com.oifm.asm.ASMBAAbout">

							<tr>
                              <td class="BodyText1" colspan="2">
							  
					<a class="Menu_highlight" name='id<bean:write name="about" property="id" />'><STRONG><bean:write name="about" property="question" /></STRONG></a>
							  
								</td>
                            </tr>

                            <tr>
                              <td class="body_detail_text" width="3%" valign="top"> A. </td>
                              <td class="body_detail_text" width="97%">
							  <bean:write name="about" property="answer" filter="false" />
								</td>
                            </tr>

                            <tr align="right">
                              <td class="body_detail_text" width="3%" valign="top">   </td>
                              <td class="body_detail_text" width="97%" valign="right" align="right">
							  <table  border="0" width="100%"><tr><td valign="right" align="right" class="body_detail_text">
							 
  
							  <a href="#top"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_jump_up.gif' alt="#top" width="13" height="9" border="0"></a> <a href="#bottom"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_jump_down.gif' alt="#bottom" width="13" height="9" border="0"></a>
 </td></tr></table>

							  </td>
                            </tr>

						</logic:iterate>
                     </table></td>
                      </tr>
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
  <a name="bottom"></a>
<script language="javascript">
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
 		document.ASMFormHome.hiddenAction.value=hiddenAction;
		document.ASMFormHome.action=actionName+"?hiddenAction="+hiddenAction;
		document.ASMFormHome.submit();
	}
</script>