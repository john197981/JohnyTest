<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="com.oifm.login.OILoginConstants" %>

<script language="javascript" >
function submitSurveyListForm(submitUrl, actionName, surveyId, nPageNo) {
 	var frm = document.SurveyForm;
	frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>&viewMode=all';
	frm.hiddenAction.value = actionName;
	frm.strSurveyId.value = surveyId;
	frm.pageNo.value = nPageNo;
	frm.submit();
}

function submitSurveyListForm2(submitUrl, actionName, divCode, nPageNo) {
 		
		//alert(nPageNo);
		document.SurveyForm.pageNo.value = nPageNo;
		document.SurveyForm.hiddenAction.value = '<%= OISurveyConstants.DIVISION_SURVEY_VIEW_ALL %>';
		document.SurveyForm.action = '<bean:message key="OIFM.contextroot" />'+'<%= OISurveyConstants.SURVEY_ADMIN_DO %>';
		document.SurveyForm.target="_self";
		document.SurveyForm.submit();
}

/*function fnSort(sortKey,sortOrder,surveyId,divCode){
	alert(sortKey + "," + sortOrder + "," + surveyId + "," + divCode + "," + '<%= OISurveyConstants.SURVEY_ADMIN_DO %>');

	//if(divCode=='')
		//alert("SURVEY_VIEW_ALL");
	
	var frm = document.SurveyForm;
	frm.hidSortKey.value = sortKey;
	frm.hidOrder.value = sortOrder;
	//frm.strSurveyId.value = surveyId;
	frm.strDivisionCode.value=divCode;
	alert(frm.strDivisionCode.value);

	if(divCode=='')
	{
		frm.hiddenAction.value='<%= OISurveyConstants.SURVEY_VIEW_ALL %>';
	}
	else
	{
		frm.hiddenAction.value='<%= OISurveyConstants.DIVISION_SURVEY_VIEW_ALL %>';
	}

	frm.action= '<bean:message key="OIFM.contextroot" />'+'<%= OISurveyConstants.SURVEY_ADMIN_DO %>'+'?id=<%= Math.random() %>';
	frm.target="_self";
	frm.submit();
	
}*/

function fnSort(sortKey,sortOrder,surveyId,divCode){
	//alert(divCode);
	if(divCode !='')
	{
		document.SurveyForm.hiddenAction.value = '<%= OISurveyConstants.DIVISION_SURVEY_VIEW_ALL %>';
	}
	else
	{
		document.SurveyForm.hiddenAction.value = '<%= OISurveyConstants.SURVEY_ADMIN_LIST %>';
	}
	//alert(document.SurveyForm.hiddenAction.value);

		document.SurveyForm.hidSortKey.value = sortKey;
		document.SurveyForm.hidOrder.value = sortOrder;
		document.SurveyForm.action = '<bean:message key="OIFM.contextroot" />'+'<%= OISurveyConstants.SURVEY_ADMIN_DO %>?sort=Sort';
		document.SurveyForm.target="_self";
		document.SurveyForm.submit();
		
	}

</script>
<%
try {
	String strDivision = "";
	if(request.getAttribute("all")==null){

		if (session.getAttribute(OILoginConstants.DIVCODE+"List")!=null)
			strDivision = (String) session.getAttribute(OILoginConstants.DIVCODE+"List");
	}
%>

<html:form action="/SurveyAdmin.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="hidSortKey"/>
<html:hidden property="hidOrder"/>
<html:hidden property="pageNo" />
<html:hidden property="strDivisionCode" />


<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"	cellspacing="1" bgcolor="white">					
 		  <logic:present name="error" scope="request" >
				  <tr>
					<td width="100%" class="body_detail_text" colspan="7">
					<b><bean:message name="error" scope="request"/></b>
					</td>
				  </tr>
		</logic:present>
		
<logic:notPresent name="error" scope="request" >
	<bean:define id="objPageInfo" name="PageInfo" scope="request" type="com.oifm.common.OIPageInfoBean" />
	<logic:present name="SurveyList" scope="request" >
          	 <tr>
              	<td valign="bottom" align="left" NOWRAP colspan="7">
					<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>', '', '')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_create_survey.gif'  border="0" alt = "Create Survey"></a>
					&nbsp;
					<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_LIST %>', '','')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_CreateUserID.gif' alt="Create Temporary User IDs"  border="0" alt = "Create User IDs"></a>
				</td>
            </tr>
		   <tr>
            <td class="Table_Head">Survey (<bean:write name="objPageInfo" property="totalRec" /> surveys)
				<a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="SURVEYID" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('SURVEYID','ASC','surveyId','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="SURVEYID" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('SURVEYID','DESC','surveyId','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('SURVEYID','ASC','surveyId','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a> 
			</td>
            <td width="170" class="Table_Head""> Division / Author 
				<a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="DIVISIONNAME" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('DIVISIONNAME','ASC','surveyId','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="DIVISIONNAME" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('DIVISIONNAME','DESC','surveyId','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('DIVISIONNAME','ASC','surveyId','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a>
			</td>
            <td width="100" class="Table_Head"" align="center">Period 
				<a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="START_ON" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('START_ON','ASC','surveyId','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="START_ON" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('START_ON','DESC','surveyId','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('START_ON','ASC','surveyId','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a>
			</td>
			<td width="100" class="Table_Head"" align="center">Target Audience 
				<a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="TARGETAUDIENCE" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('TARGETAUDIENCE','ASC','surveyId','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="TARGETAUDIENCE" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('TARGETAUDIENCE','DESC','surveyId','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('TARGETAUDIENCE','ASC','surveyId','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a>
			</td>
            <td width="100" class="Table_Head"" align="center">Resp 
				<a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="NOOFRES" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('NOOFRES','ASC','surveyId','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="NOOFRES" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('NOOFRES','DESC','surveyId','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('NOOFRES','ASC','surveyId','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a>
			</td>
			<td width="80" class="Table_Head"" align="center">Published Finding On
				<a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="PUBLISHED_ON" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('PUBLISHED_ON','ASC','1','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="PUBLISHED_ON" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('PUBLISHED_ON','DESC','1','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('PUBLISHED_ON','ASC','1','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a>
			</td>
            <td width="80" class="Table_Head"" nowrap>Mail <a href="#">
					<logic:notEqual name="SurveyForm" property="hidSortKey" value="MAIL_STATUS" scope="request"> 
						<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_no.GIF'  border="0" onClick="javascript:fnSort('MAIL_STATUS','ASC','1','<%=strDivision%>');" alt="No Order">
					</logic:notEqual>
					<logic:equal name="SurveyForm" property="hidSortKey" value="MAIL_STATUS" scope="request">
						<logic:equal name="SurveyForm" property="hidOrder" value="ASC" scope="request">
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_down.GIF' border="0" onClick="javascript:fnSort('MAIL_STATUS','DESC','1','<%=strDivision%>');" alt="Show in Ascending Order">
						</logic:equal>
						<logic:equal name="SurveyForm" property="hidOrder" value="DESC" scope="request" >
							<img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_sort_up.GIF' border="0" onClick="javascript:fnSort('MAIL_STATUS','ASC','1','<%=strDivision%>');" alt="Show in Descending Order">
						</logic:equal>
					</logic:equal>
				</a>
			</td>
          </tr>
		<logic:greaterThan name="objPageInfo" property="totalRec" value="0">
			<logic:iterate id="objSurvey" name="SurveyList" indexId="ind" type="com.oifm.survey.OIBASurvey">
          <tr>
            <td width="70%" class="heading_thread"  valign="top">
            <a href="#" class="heading_thread" onClick='javascript:submitSurveyListForm(
            "<%= OISurveyConstants.SURVEY_ADMIN_DO %>",
            "<%= OISurveyConstants.SURVEY_EDIT %>",
			"<bean:write name="objSurvey" property="strSurveyId" />", ""
			)'><bean:write name="objSurvey" property="strSurveyId" /> - <bean:write name="objSurvey" property="strSurveyName" /></a>
             </td>
            <td class="heading_attributes" valign="top">
      			<bean:write name="objSurvey" property="strDivisionName" filter="false" /></td>
            <td class="heading_attributes" valign="top" NOWRAP>
            	<CENTER><bean:write name="objSurvey" property="strFromDate" /><br>to<br>
      			<bean:write name="objSurvey" property="strToDate" /></CENTER></td>
			<td class="heading_attributes" valign="top">
            	<logic:present name="objSurvey" property="strTargetAudience">
	            	<CENTER><bean:write name="objSurvey" property="strTargetAudience" /></CENTER>
            	</logic:present>
      		</td>
            <td class="heading_attributes" valign="top" NOWRAP>
            	<logic:present name="objSurvey" property="strNoOfResponse">
	            	<CENTER><bean:write name="objSurvey" property="strNoOfResponse" /></CENTER>
            	</logic:present>
      		</td>
			<td class="heading_attributes" valign="top" NOWRAP>
            	<logic:present name="objSurvey" property="strNoOfResponse">
	            	<CENTER><bean:write name="objSurvey" property="strPublishedOn" /></CENTER>
            	</logic:present>
      		</td>
            <td class="body_detail_text"  valign="top">
            	<logic:present name="objSurvey" property="strSurveyType">
					<logic:match name="objSurvey" property="strSurveyType" value="Y">
		            	<logic:present name="objSurvey" property="strSurveyType">
							<logic:match name="objSurvey" property="strIsActive" value="Y">
								<logic:present name="objSurvey" property="strDivisionCode">
									<logic:match name="objSurvey" property="strDivisionCode" value="<%= strDivision %>">
										<logic:present name="objSurvey" property="strMailStatus">
											<logic:match name="objSurvey" property="strMailStatus" value="S">
												<a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_MAIL_DO %>?hiddenAction=populate&Id=<bean:write name="objSurvey" property="strSurveyId" />&module=S&flag=S' target="_self">Send Mail</a>
											</logic:match>
											<logic:match name="objSurvey" property="strMailStatus" value="R">
												<a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_MAIL_DO %>?hiddenAction=populate&Id=<bean:write name="objSurvey" property="strSurveyId" />&module=S&flag=R' target="_self">Send Reminder</a>
											</logic:match>
										</logic:present>
									</logic:match>
									<logic:notMatch name="objSurvey" property="strDivisionCode" value="<%= strDivision %>">
										<logic:present name="role" scope="session">
											<logic:match name="role" scope="session" value="ADMIN">
												<logic:present name="objSurvey" property="strMailStatus">
													<logic:match name="objSurvey" property="strMailStatus" value="S">
														<a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_MAIL_DO %>?hiddenAction=populate&Id=<bean:write name="objSurvey" property="strSurveyId" />&module=S&flag=S' target="_self">Send Mail</a>
													</logic:match>
													<logic:match name="objSurvey" property="strMailStatus" value="R">
														<a class="special_body_link" href='<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_MAIL_DO %>?hiddenAction=populate&Id=<bean:write name="objSurvey" property="strSurveyId" />&module=S&flag=R' target="_self">Send Reminder</a>
													</logic:match>
												</logic:present>
											</logic:match>
										</logic:present>
									</logic:notMatch>
								</logic:present>
							</logic:match>
						</logic:present>
					</logic:match>
				</logic:present>
			</td>
          </tr>
		  <td colspan="7" class="body_detail_text">
			<hr style="color:#CCCCCC;height:1px">
		  </td>
		  </tr>

			</logic:iterate>
		</logic:greaterThan>
		<!--<logic:lessEqual name="objPageInfo" property="totalRec" value="0">
          <tr>
            <td width="100%" class="BodyText" colspan="3">
			<b><bean:message key="NoRecordLoad"/></b>
			</td>
          </tr>
		</logic:lessEqual>-->
	</logic:present>
</logic:notPresent>
			 <tr>
              	<td valign="bottom" align="left" NOWRAP colspan="3">
	            <a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>', '', '')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_create_survey.gif'  border="0" alt = "Create Survey"></a>
				&nbsp;
	            <a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_TEMP_USER_DO %>','<%= OISurveyConstants.TEMP_USER_LIST %>', '','')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_CreateUserID.gif' alt="Create Temporary User IDs"  border="0" alt = "Create User IDs"></a>
				</td>
<%
	if((request.getAttribute("all")!=null))
	{
%>

<logic:present name="PageInfo" scope="request" >
	<bean:define id="objPageInfo" name="PageInfo" scope="request" type="com.oifm.common.OIPageInfoBean" />
	<bean:define id="selPageNo" name="objPageInfo" property="pageNo"/> 
	<logic:greaterThan name="objPageInfo" property="noOfPages" value="1">
           	<td valign="bottom" align="right" class="Text" colspan="4" nowrap>
<%
		for(int i=1, j=((java.lang.Integer)selPageNo).intValue(); i<=objPageInfo.getNoOfPages(); i++) {
			if(i != j) {
%>
	            <a href="#" onClick="javascript:submitSurveyListForm(
	            '<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
	            '<%= OISurveyConstants.SURVEY_ADMIN_LIST %>', '','<%= i %>')"><%= i %></a>&nbsp;
<%
			} else {
%>
				<font color = "red"><b><%= i %></b>&nbsp;
<%		
			}
		}
%>
				</td>              
	</logic:greaterThan >
</logic:present>
<%
	}else{
%>
<logic:present name="PageInfo" scope="request" >
	<bean:define id="objPageInfo" name="PageInfo" scope="request" type="com.oifm.common.OIPageInfoBean" />
	<bean:define id="selPageNo" name="objPageInfo" property="pageNo"/> 
	<logic:greaterThan name="objPageInfo" property="noOfPages" value="1">
           	<td valign="bottom" align="right" class="Text" colspan="4" nowrap>
<%
		for(int i=1, j=((java.lang.Integer)selPageNo).intValue(); i<=objPageInfo.getNoOfPages(); i++) {
			if(i != j) {
%>
	            <a href="#" onClick="javascript:submitSurveyListForm2(
	            '<%= OISurveyConstants.SURVEY_ADMIN_DO %>',
	            '<%= OISurveyConstants.DIVISION_SURVEY_VIEW_ALL %>', '','<%= i %>')"><%= i %></a>&nbsp;
<%
			} else {
%>
				<font color = "red"><b><%= i %></b>&nbsp;
<%		
			}
		}
%>
				</td>              
	</logic:greaterThan >
</logic:present>
<%	}//end else
%>
            </tr>

				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</html:form>
<%
} catch(Exception e)	{
	e.printStackTrace();
}		
%>
