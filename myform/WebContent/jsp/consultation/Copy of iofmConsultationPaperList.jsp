<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<script language="javascript">
	function fnSubmit(actionName,paperId,hiddenAction)
	{
		document.tempForm.paperId.value=paperId;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}
</script>

<logic:present name="error" scope="request">
	<br><br>
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable" align="center">
		<tr>
    		<td width="75%" align="center" valign="top" class="Mainheader">
				<bean:write name="error" scope="request" />
			</td>
		</tr>
	</table>    
</logic:present>

<logic:present name="message" scope="request">
	<br><br>
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable" align="center">
		<logic:iterate id="mList" name="message" scope="request">
			<tr>
    			<td width="75%" align="center" valign="top" class="Mainheader">
					<bean:write name="mList"/>
				</td>
			</tr>
		</logic:iterate>
	</table>    
</logic:present>

<link href="css/MySay.css" rel="stylesheet" type="text/css">
<style type="text/css"> 
<!-- .style1 {color: #FF0000}
.style2 {font-size: 11px}
	--> 
	</style>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">Whats Up</td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br><logic:present name="aOIBAConfiguration" scope="request">
						<bean:define id="homeAnnouncement" name="aOIBAConfiguration" scope="request" type="com.oifm.common.OIBAConfiguration"></bean:define>
						<bean:write name="homeAnnouncement" property="value" filter="false" />
					</logic:present>
                  </p>
              </blockquote>
            </div></td>
            </tr>
          <tr>
            <td class="Grey_fade">&nbsp;</td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
			<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td  class="Box">
				<table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="3" class="Table_head">Current Consultation Paper(s) </td>
                    <td width="16%" class="Table_head">Closing on </td>
                  </tr>
				<!-- START LOOP -->
			<logic:iterate id="categoryListing" name="ConsultListingFormWeb" property="arOIBVPaperPresent" type="com.oifm.consultation.OIFormConsultListingWebHelper1">
				<logic:present name="categoryListing" property="arOIFormConsultListingWebHelper2">
					<logic:iterate id="paperListing" name="categoryListing" property="arOIFormConsultListingWebHelper2" type="com.oifm.consultation.OIFormConsultListingWebHelper2">
                  <tr>
                  <td><logic:present name="paperListing" property="status">
						<logic:equal name="paperListing" property="status" value="D">
							<img src='<bean:message key="OIFM.docroot" />/images/icon_draft.gif' border="0" alt="Draft">
						</logic:equal>
						<logic:equal name="paperListing" property="status" value="S">
							<img src='<bean:message key="OIFM.docroot" />/images/icon_submited.gif' border="0" alt="Submitted">
						</logic:equal>
					</logic:present>
					<logic:notPresent name="paperListing" property="status">
						<img src='<bean:message key="OIFM.docroot" />/images/latest_thread.gif' border="0" alt="New">
					</logic:notPresent></td>
                    <td colspan="2"> 
                    	<a href='#' onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do','<bean:write name="paperListing" property="paperId" />','populate');" class="Table_Sub_head">
							<bean:write name="paperListing" property="paperName" />
						</a>
                    </td>
                    <td class="user_online"><bean:write name="paperListing" property="expireDate" /> </td>
                  </tr>
                  <tr>
                    <td align="left" valign="top">&nbsp;</td>
                    <td width="4%">&nbsp;</td>
                    <td width="76%" class="body_extract_text">
                    <bean:write name="paperListing" property="description" filter="false" />
					</td>
                    <td>&nbsp;</td>
                  </tr>
				  <!-- END LOOP -->
				  	</logic:iterate>
				</logic:present>
			</logic:iterate>
            </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">&nbsp;</td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">
			<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="Box"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td colspan="3" class="Table_head">Past Consultation Paper(s)</td>
                      <td width="16%" class="Table_head">Published on </td>
                    </tr>
					<!-- START LOOP -->
			<logic:iterate id="categoryListing" name="ConsultListingFormWeb" property="arOIBVPaperPast" type="com.oifm.consultation.OIFormConsultListingWebHelper1">
				<logic:present name="categoryListing" property="arOIFormConsultListingWebHelper2">
					<logic:iterate id="paperListing" name="categoryListing" property="arOIFormConsultListingWebHelper2" type="com.oifm.consultation.OIFormConsultListingWebHelper2">
                  
                    <tr>
                      <td><img src="<bean:message key="OIFM.docroot" />/images/icon_private.gif" width="18" height="17"></td>
                      <td colspan="2">
                      <a href='#' onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/webConsultPastPaperAction.do','<bean:write name="paperListing" property="paperId" />','populate');" class="Table_Sub_head">
						<bean:write name="paperListing" property="paperName" />
					  </a> </td>
                      <td class="user_online"><bean:write name="paperListing" property="expireDate" /></td>
                    </tr>
                    <tr>
                      <td width="4%">&nbsp;</td>
                      <td width="4%">&nbsp;</td>
                      <td width="76%" class="body_extract_text"><bean:write name="paperListing" property="description" filter="false" /><br></td>
                      <td>&nbsp;</td>
                    </tr>
				<!-- END LOOP -->
			  	</logic:iterate>
				</logic:present>
			</logic:iterate>
                </table></td>
              </tr>
			  <tr><td>&nbsp;</td></tr>
		<logic:notPresent name="ConsultListingFormWeb" property="arOIBVPaperPresent" scope="request">
		<td colspan="2" align="left" valign="top">
			<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
				<tr>
				    <td class="BodyText">
						<bean:message key="OI.CONS.NOT.AVAIL" />
					</td>
				</tr>
			</table>
		</td>
		</logic:notPresent>
			  <tr>
			    <td><TABLE width="200" cellpadding="0" cellspacing="0">
                  <TBODY>
                    <TR>
                      <TD><img src="<bean:message key="OIFM.docroot" />/images/latest_thread.gif" width="20" height="20"></TD>
                      <TD class="body_extract_text">New</TD>
                    </TR>
                    <TR>
                      <TD><IMG src="<bean:message key="OIFM.docroot" />/images/icon_draft.gif" alt="Drafted" width="19" height="19"></TD>
                      <TD class="body_extract_text">Drafted</TD>
                    </TR>
                    <TR>
                      <TD width="30"><IMG src="<bean:message key="OIFM.docroot" />/images/icon_submited.gif" alt="Submit" width="19" height="19"></TD>
                      <TD class="body_extract_text">Submitted</TD>
                    </TR>
                    <TR>
                      <TD><img src="<bean:message key="OIFM.docroot" />/images/icon_private.gif" width="18" height="17"></TD>
                      <TD class="body_extract_text">Private</TD>
                    </TR>
                  </TBODY>
                </TABLE></td>
			    </tr>
            </table>
            </td>
          </tr>
        </table>
        </div></td>
    </tr>
    <tr align = "center"><td colspan="2">
	    <jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
    </td></tr>
  </table>

</HTML>
<form name="tempForm" method="post">
	<input type="hidden" name="paperId">
	<input type="hidden" name="hiddenAction">
</form>
