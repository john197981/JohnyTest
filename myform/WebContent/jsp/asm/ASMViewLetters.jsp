<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
 
 <script language="javascript" src='<bean:message key="OIFM.docroot" />/js/asmHome.js'></script>
<script language="javascript">
	function fnSubmit(actionName,letterId,hiddenAction)
	{
		document.tempForm.letterId.value=letterId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}

	function fnSubmitNew(actionName,hiddenAction)
	{
		document.tempForm.letterId.value="";
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
</script>
<table border="0" cellspacing="0" cellpadding="0" width="100%" align="center">
<tr><td valign="top" align="center">

<form name="tempForm" method="post">
	<input type="hidden" name="letterId">
	<input type="hidden" name="hiddenAction">
</form>
<%try{%>

  		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Submit/view your Letter(s) </td>
          </tr>
        </table>
 		<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>

					<logic:present name="ModuleDesc" scope="request">
 							<bean:write name="ModuleDesc" scope="request" filter="false"/>
  					</logic:present>

                 <br>
                </p>
                 
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td align="left" valign="top" bgcolor="white">
 			<table width="857" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td width="648" class="Grey_fade">&nbsp;</td>
                <td width="16" class="Blue">&nbsp;</td>
                <td width="193" rowspan="2" align="left" valign="top" class="Blue">
				  

<jsp:include page="/jsp/asm/ASMRightMenu.jsp" flush="true"> <jsp:param name="pageName" value="SubmitView" /> </jsp:include>
					   

					   <p class="Address_body">&nbsp;</p></td>
              </tr>
              <tr>
                <td align="left" valign="top" bgcolor="white">
                <table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
                  <tr>
                    <td class="Box">
					<table border="0" cellpadding="0" cellspacing="0" width="98%">
					<logic:present name="arASMBALetters" scope="request">

                         <tr class="Table_head">
                          <td width="4%" class="Table_head">&nbsp;</td>
                          <td width="62%" class="Table_head">Letter(s)</td>
                          <td width="34%" align="right" class="Table_head">Status</td>
                        </tr>

						<logic:iterate id="letterListing" name="arASMBALetters" type="com.oifm.asm.ASMBALetters">
							<tr>
								<td  >
									<logic:equal name="letterListing" property="status" value="D">
										<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_draft.gif' alt="Drafted">
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="P">
										<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_published.gif' alt="Published">
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="S">
										<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_submited.gif' alt="Submitted">
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="R">
										<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_replied_to.gif' alt="Replied">
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="T">
										<img src='<bean:message key="OIFM.docroot" />/images/submited.gif' alt="Submitted">
									</logic:equal>
								</td>
								<td  >
									<logic:equal name="letterListing" property="status" value="D">
										<a href="#" class="Heading_Thread" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="letterListing" property="letterId" />','newOrEditLetter');">
											<bean:write name="letterListing" property="topic" />
										</a>
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="P">
										<a href="#" class="Heading_Thread" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="letterListing" property="letterId" />','detailLetter');">
											<bean:write name="letterListing" property="topic" />
										</a>
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="S">
										<a href="#" class="Heading_Thread" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="letterListing" property="letterId" />','detailLetter');">
											<bean:write name="letterListing" property="topic" />
										</a>
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="R">
										<a href="#" class="Heading_Thread" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="letterListing" property="letterId" />','detailLetter');">
											<bean:write name="letterListing" property="topic" />
										</a>
									</logic:equal>
									<logic:equal name="letterListing" property="status" value="T">
										<a href="#" class="Heading_Thread" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/ASMView.do','<bean:write name="letterListing" property="letterId" />','detailLetter');">
											<bean:write name="letterListing" property="topic" />
										</a>
									</logic:equal>
								</td>
 								 
								<td class="Heading_Attributes">
									<div align="right">
										<span class="Heading_Attributes">
											<logic:equal name="letterListing" property="status" value="D">
												Last Drafted on 
												<bean:write name="letterListing" property="draftedOn" />
											</logic:equal>
											<logic:equal name="letterListing" property="status" value="P">
												Published on 
												<bean:write name="letterListing" property="publishOn" />
											</logic:equal>
											<logic:equal name="letterListing" property="status" value="S">
												Submitted on 
												<bean:write name="letterListing" property="submittedOn" />
											</logic:equal>
											<logic:equal name="letterListing" property="status" value="T">
												Submitted on 
												<bean:write name="letterListing" property="submittedOn" />
											</logic:equal>
											<logic:equal name="letterListing" property="status" value="R">
												Replied on 
												<bean:write name="letterListing" property="repliedOn" />
											</logic:equal>
										</span>
									</div>
								</td>
							</tr>
						</logic:iterate>
					</logic:present>
					<logic:notPresent name="arASMBALetters" scope="request">
					<tr><td>&nbsp;</td></tr>
					<tr><td  align = "center" class="Menu_highlight">
					There is no letter posted so far</td></tr>
					<tr><td>&nbsp;</td></tr>
					</logic:notPresent>
					</table>
					</td>
                  </tr>
 

			</table>
			<br><br>
			<!-- Added by anbu % -->
			
			<table width="98%">
 			                <logic:present name="arASMBALetters" scope="request">
							<TR>
                  <TD colspan="4" class="heading_thread"><u>Legends</u></TD>
                 </TR>
						</logic:present>
			<tr>
					  <logic:present name="arASMBALetters" scope="request">
					  <td width="66%"><TABLE width="98%" cellpadding="0" cellspacing="0"  >
                            <TBODY>
                              <TR>
                                <TD><IMG src='<bean:message key="OIFM.docroot" />/images/Icons/icon_draft.gif' alt="Drafted" width="19" height="19"></TD>
                                <TD class="body_extract_text">Drafted</TD>
                              </TR>
                              <TR>
                                <TD width="30"><IMG src='<bean:message key="OIFM.docroot" />/images/Icons/icon_submited.gif' alt="Submit" width="19" height="19"></TD>
                                <TD class="body_extract_text">Submitted</TD>
                              </TR>
                              <TR>
                                <TD><IMG src='<bean:message key="OIFM.docroot" />/images/Icons/icon_replied_to.gif' alt="Reply" width="21" height="21"></TD>
                                <TD class="body_extract_text">Replied</TD>
                              </TR>
                              <TR>
                                <TD><IMG src='<bean:message key="OIFM.docroot" />/images/Icons/icon_published.gif' alt="Publish" width="19" height="19"></TD>
                                <TD class="body_extract_text">Published</TD>
                              </TR>
                            </TBODY>
                          </TABLE></td>
						</logic:present>
					  <td width="94%" align="right" valign="top">
					  <a href="#" onclick="javascript:fnSubmitNew('<bean:message key="OIFM.contextroot" />/ASMView.do','newOrEditLetter');"> <img src='<bean:message key="OIFM.docroot" />/images/btn_Newletter.gif' alt="Post a New Letter to Management"  border="0"></a></td>
					</tr>
					 

 
                </table>

                   <br></td>
                <td class="Blue">&nbsp;</td>
              </tr>
			   
             </table>
 
			</td>
 		  </tr>
        </table>
        <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
<script>
var contextRoot ='<bean:message key="OIFM.contextroot" />'
</script>


<script language="javascript">
	function fnRightPanelSubmit(actionName,hiddenAction)
	{
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName+"?hiddenAction="+hiddenAction;
		document.tempForm.submit();
	}
</script>

<%}catch(Exception e){
out.println(e);
}%>