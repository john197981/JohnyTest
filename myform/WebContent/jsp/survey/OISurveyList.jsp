<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>

<script language="javascript" >
function submitSurveyListForm(submitUrl, actionName, surveyId) {
	var frm = document.UserSurveyForm;
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.strSurveyId.value = surveyId;
	frm.submit();
}

</script>
<logic:notPresent name="error" scope="request" >
	<logic:present name="CurrSurveyList" scope="request" >
		<bean:size id="currSize" name="CurrSurveyList" />
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">What's Up</td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
 Welcome to the<b> Survey </b>module. <br><br>To start, click on a 'New' survey below and start submitting your responses now! Do visit the <a href='<bean:message key="OIFM.contextroot"/>/jsp/common/iofmCommonSearchTemplate.jsp?pageName=faq' class="Poll_body" alt="Read the FAQs for My Forum"><font color\="red">My Forum</font> FAQ </a>or Help pages to find out more about how to participate in our surveys.				  
				  <br>
                  <br>
                  </p> 
                </blockquote>
            </div></td>
            </tr>
          <tr>
            <td class="Grey_fade">&nbsp;</td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="Box"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                  <tr>
                    <td colspan="3" class="Table_head">Current Survey(s) </td>
                    <td width="16%" class="Table_head">Closing on </td>
                  </tr>
					<logic:equal name="currSize" value="0">
					 <tr>
					 <td align="center" class="body_detail_text" colspan="2"><bean:message key="OI.SURVEY.CURRENT_NOT.AVAIL" /></td>
					 </tr>
					</logic:equal>
				  <!-- start loop -->
			<logic:greaterThan name="currSize" value="0">
			<logic:iterate id="objSurvey" name="CurrSurveyList" scope="request" type="com.oifm.survey.OIBASurvey">
                  <tr>
                    <td width="4%">
                    <img src='<bean:message key="OIFM.docroot" /><bean:write name="objSurvey" property="strStatusIcon" />' border="0">
                    <logic:equal name="objSurvey" property="strSurveyType" value="Y">
							<img src='<bean:message key="OIFM.docroot" />/images/icon_private.gif' border="0" alt = "private">
					</logic:equal>
                    </td>
                    <td colspan="2"> 	<a href="#" onClick='javascript:submitSurveyListForm("<%= OISurveyConstants.SURVEY_USER_DO %>","<%= OISurveyConstants.SURVEY_DETAILS %>","<bean:write name="objSurvey" property="strSurveyId" />")'  class="Table_Sub_head">
							<bean:write name="objSurvey" property="strSurveyName" />
						</a> </td>
                    <td class="user_online"><bean:write name="objSurvey" property="strToDate" /> </td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td width="4%">&nbsp;</td>
                    <td width="76%" class="body_extract_text"><bean:write name="objSurvey" property="strDescription" filter="false" /> <br></td>
                    <td>&nbsp;</td>
                  </tr>
                  <tr>
                    <td>&nbsp;</td>
                    <td colspan="2">&nbsp;</td>
                    <td>&nbsp;</td>
                  </tr>
               	</logic:iterate>
			</logic:greaterThan>
            <!-- end loop -->
 	</logic:present>
</logic:notPresent>
                </table></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc">&nbsp;</td>
          </tr>
          <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td class="Box"><table width="100%"  border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td colspan="3" class="Table_head">Past Survey(s) </td>
                      <td width="16%" class="Table_head">Published on</td>
                    </tr>
					<bean:size id="pastSize" name="PastSurveyList" />

					<logic:equal name="pastSize" value="0">
					 <tr>
					 <td align="center" class="body_detail_text" colspan="2"><bean:message key="OI.SURVEY.PAST_NOT.AVAIL" /></td>
					 </tr>
					</logic:equal>

					<logic:iterate id="objSurvey" name="PastSurveyList" scope="request" type="com.oifm.survey.OIBASurvey">
					<!-- start loop -->
                    <tr>
                      <td>&nbsp;
					  <logic:equal name="objSurvey" property="strSurveyType" value="Y">
							<img src='<bean:message key="OIFM.docroot" />/images/icon_private.gif' border="0" alt = "private">
						</logic:equal>
							</td>
                      <td colspan="2"><a href="#" onClick='javascript:submitSurveyListForm(
					            "<%= OISurveyConstants.SURVEY_USER_DO %>",
					            "<%= OISurveyConstants.SURVEY_DETAILS %>",
								"<bean:write name="objSurvey" property="strSurveyId" />"
								)'  class="Table_Sub_head"><bean:write name="objSurvey" property="strSurveyName" /></a> </td>
                      <td class="user_online"><bean:write name="objSurvey" property="strToDate" /></td>
                    </tr>
                    <tr>
                      <td width="4%">&nbsp;</td>
                      <td width="4%">&nbsp;</td>
                      <td width="76%" class="body_extract_text"><bean:write name="objSurvey" property="strDescription" filter="false" /> <br></td>
                      <td>&nbsp;</td>
                    </tr>
                    <tr>
                      <td>&nbsp;</td>
                      <td colspan="2">&nbsp;</td>
                      <td>&nbsp;</td>
                    </tr>
                 </logic:iterate>
				<!-- end loop -->
                </table></td>
              </tr>
			  <tr>
			    <td>&nbsp;</td>
			    </tr>
			  <tr><td>
			  

	<logic:notEqual name="currSize" value="0">
			  <TABLE width="200" cellpadding="0" cellspacing="0">
                <TBODY>
				<TR><TD colspan="2" class="heading_thread"><u>Legend</u></TD></TR>
                  <TR>
                    <TD><img src="<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif" width="20" height="20"></TD>
                    <TD class="smalltext">New</TD>
                  </TR>
                  <TR>
                    <TD><IMG src="<bean:message key="OIFM.docroot" />/images/Icons/icon_draft.gif" alt="Drafted" width="19" height="19"></TD>
                    <TD class="smalltext">Drafted</TD>
                  </TR>
                  <TR>
                    <TD width="30"><IMG src="<bean:message key="OIFM.docroot" />/images/Icons/icon_submited.gif" alt="Submit" width="19" height="19"></TD>
                    <TD class="smalltext">Submitted</TD>
                  </TR>
                  <TR>
                    <TD><img src="<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif" width="18" height="17"></TD>
                    <TD class="smalltext">Private</TD>
                  </TR>
                </TBODY>
              </TABLE>
	</logic:notEqual>

	<logic:equal name="currSize" value="0">
					<logic:notEqual name="pastSize" value="0">
					  <TABLE width="200" cellpadding="0" cellspacing="0">
						<TBODY>
						<TR><TD colspan="2" class="heading_thread"><u>Legend</u></TD></TR>
						  <TR>
							<TD><img src="<bean:message key="OIFM.docroot" />/images/Icons/latest_thread.gif" width="20" height="20"></TD>
							<TD class="smalltext">New</TD>
						  </TR>
						  <TR>
							<TD><IMG src="<bean:message key="OIFM.docroot" />/images/Icons/icon_draft.gif" alt="Drafted" width="19" height="19"></TD>
							<TD class="smalltext">Drafted</TD>
						  </TR>
						  <TR>
							<TD width="30"><IMG src="<bean:message key="OIFM.docroot" />/images/Icons/icon_submited.gif" alt="Submit" width="19" height="19"></TD>
							<TD class="smalltext">Submitted</TD>
						  </TR>
						  <TR>
							<TD><img src="<bean:message key="OIFM.docroot" />/images/Icons/icon_private.gif" width="18" height="17"></TD>
							<TD class="smalltext">Private</TD>
						  </TR>
						</TBODY>
					  </TABLE>
 					</logic:notEqual>

	</logic:equal>
			  
				</td></tr>
            </table>
              <br></td>
          </tr>
          <tr><td><jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" /></td>
          </tr>
        </table>
        </td>
          </tr>
        </table>

      </div>
      </td>
    </tr>
  </table>
</div>
<div align="center"></div></HTML>
</div></HTML>
<html:form action="/UserSurvey.do" method="post" >
<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
</html:form>
