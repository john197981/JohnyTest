<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>

<script language="javascript" >

function submitSurveyDetailsForm(submitUrl, actionName) {
 	var frm = document.UserSurveyForm;
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	frm.submit();
}


</script>

<logic:present name="objSurveyVo" scope="request">
<bean:define id="objSurvey" name="objSurveyVo" type="com.oifm.survey.OIBASurvey" />
<table width="857" border="0" cellspacing="0" cellpadding="0" >
  <tr>
    <td class="Orange_fade"><bean:write name="objSurvey" property="strSurveyName" /></td>
  </tr>
</table>
</logic:present>   
<table width="857" border="0" cellspacing="0" cellpadding="0" width = 100%>
  <tr>
    <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
      <blockquote>
        <p>
          <logic:present name="message" scope="request">
		<logic:iterate id="msg" name="message" scope="request">
		<b><bean:message key="msg"/></b>
		</logic:iterate>
		</logic:present>
          </p>
        </blockquote>
    </div></td>
    </tr>
  <table width="857" border="0" cellspacing="0" cellpadding="0" bgcolor="#f7f8fc" width = 100% height = 500>
  <tr>
    <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
      <blockquote>
        <p><br>
          <bean:write name="objSurvey" property="strDescription" filter="false" />
          <br>
          </p>
        </blockquote>
    </div></td>
    </tr>
    <tr><td class="Grey_fade">&nbsp;</td></tr>
  <html:form action="/UserSurvey.do" method="post" >
	<logic:present name="error" scope="request" >
		<tr>
			<td  colspan="3">
			<b><bean:message name="error" scope="request"/></b>
			</td>
		</tr>
	</logic:present>

	<logic:present name="message" scope="request">
		<logic:iterate id="msg" name="message" scope="request">
			<tr>
	    		<td width="100%" align="center" valign="top" class="Mainheader">
					<b><bean:message key="msg"/></b>
				</td>
			</tr>
		</logic:iterate>
	</logic:present>
	
		<tr>
			<td  align="center" valign="top">
				<logic:present name="objSurveyVo" scope="request">
					<bean:define id="objSurvey" name="objSurveyVo" type="com.oifm.survey.OIBASurvey" />
					<table width="98%"  border="0" cellpadding="5" cellspacing="0" class = "box">
					    <tr>
							<td class="Table_head" align="left" colspan = 2>
								Published Survey Detail
					        </td>
					        <td width="35%" align="right" valign="top" bgcolor="#DEEAF3" class="Table_head">
					        <a href="#" onClick="javascript:submitSurveyDetailsForm('<%= OISurveyConstants.SURVEY_USER_DO %>','<%= OISurveyConstants.SURVEY_USER_LIST %>')" class="Poll_body">Back To Survey List</a></td>
						</tr>
						
						<tr>
							<td bgcolor="#DEEAF3" valign="top" valign="TOP" width="9%" class="Heading_Topic"><strong>Period </strong> </td>
							<td  valign="TOP" class="body_extract_text">
								<bean:write name="objSurvey" property="strFromDate" 	/> To 
								<bean:write name="objSurvey" property="strToDate"	/>
							</td>
						</tr>
						 <tr>
			                <td bgcolor="#DEEAF3"  class="Heading_Topic">&nbsp;</td>
			                <td colspan="4" ><hr style="height:1px; color=#E4E4E9"></td>
			            </tr>
						<tr>
							<td bgcolor="#DEEAF3" valign="top" valign="TOP" width="9%" class="Heading_Topic"><strong>Contact Person Name </strong> </td>
							<td  valign="TOP" class="body_extract_text">
								<bean:write name="objSurvey" property="strContactPerson" 	/> 
							</td>
						</tr>
						 <tr>
			                <td bgcolor="#DEEAF3"  class="Heading_Topic">&nbsp;</td>
			                <td colspan="4" ><hr style="height:1px; color=#E4E4E9"></td>
			            </tr>
						<tr>
							<td bgcolor="#DEEAF3" valign="top" valign="TOP" width="9%" class="Heading_Topic"><strong>Telephone </strong> </td>
							<td  valign="TOP" class="body_extract_text">
								<bean:write name="objSurvey" property="strPhone" 	/> 
							</td>
						</tr>
						 <tr>
			                <td bgcolor="#DEEAF3"  class="Heading_Topic">&nbsp;</td>
			                <td colspan="4" ><hr style="height:1px; color=#E4E4E9"></td>
			            </tr>
						<tr>
							<td bgcolor="#DEEAF3" valign="top" valign="TOP" width="9%" class="Heading_Topic"><strong>Email Address </strong> </td>
							<td  valign="TOP" class="body_extract_text">
								<bean:write name="objSurvey" property="strEmailAddress" 	/> 
							</td>
						</tr>
						 <tr>
			                <td bgcolor="#DEEAF3"  class="Heading_Topic">&nbsp;</td>
			                <td colspan="4" ><hr style="height:1px; color=#E4E4E9"></td>
			            </tr>
						<tr>
							<td bgcolor="#DEEAF3" align="left"  valign="TOP" width="9%" class="Heading_Topic"><strong>Summary </strong></td>
							<td  align="left"  valign="TOP" class="body_extract_text">
								<bean:write name="objSurvey" property="strSummary" filter="false" /><BR><BR>
							</td>
						</tr>
						 <tr>
			                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
			                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
			            </tr>
						<tr>
							<logic:notEqual name="objSurveyVo" property="strAttachedFile" value="">
								<td bgcolor="#DEEAF3"  align="left"  valign="TOP" width="9%" class="Heading_Topic"><strong>Results</strong></td>
								<td  align="left"  valign="TOP" class="body_extract_text">
									<a href="#" onClick="javascript:submitSurveyDetailsForm('<%= OISurveyConstants.SURVEY_USER_DO %>','<%= OISurveyConstants.SURVEY_DOWNLOAD %>')" >
										Survey Results
									</a>
								</td>
							</logic:notEqual>
						</tr>
					</logic:present>
					 <tr>
			                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
			                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
			            </tr>
					<tr>
					<td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
			        <td colspan="3" >
						<a href="#" onClick="javascript:submitSurveyDetailsForm('<%= OISurveyConstants.SURVEY_USER_DO %>','<%= OISurveyConstants.SURVEY_USER_LIST %>')">
							<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' border="0" alt="Back"></a>
					</td>
				    </tr>
					</table>
			</td>
		</tr>
</table>
<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
</html:form>
