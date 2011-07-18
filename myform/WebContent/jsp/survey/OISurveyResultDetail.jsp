<%--
/**
 * FileName			: OISurveyResultDetail.jsp
 * Author      		: Edmund Choo
 * Created Date 	: 21 Nov 2006
 * Description 		: This page allow user to Select Criteria for report data
 * Version			: 1.0
 **/ 
--%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oifm.survey.OIBASurveyResponse" %>

<%
ArrayList alList = new ArrayList();
ArrayList alLevelList = new ArrayList();
ArrayList alDesignationList = new ArrayList();

String strSurveyId = null;
String strSurveyName = null;
String strPaperId = null;
String strPaperTitle = null;

String pageTitle = null;

int i = 0;

if(request.getAttribute("SurveyDemographicSelection")!= null){
	alList = (ArrayList)request.getAttribute("SurveyDemographicSelection");
	alLevelList = (ArrayList)alList.get(0);
	alDesignationList = (ArrayList)alList.get(1);
}

if(request.getAttribute("SurveyId")!= null){
	
	strSurveyId = (String)request.getAttribute("SurveyId");
	strSurveyName = (String)request.getAttribute("SurveyTitle");
	pageTitle = "Survey";
}

if(request.getAttribute("PaperId")!= null){
	strPaperId = (String)request.getAttribute("PaperId");
	strPaperTitle = (String)request.getAttribute("paperTitle");

	pageTitle = "Consultation Paper";
}
%>
<SCRIPT LANGUAGE="JavaScript">
		//<!--
		function submitSurveyResultForm(submitUrl, actionName) {
		 	var frm = document.ResultForm;
			frm.target="_self";
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			frm.submit();
			return;
		}

		function fnOpen(paperId){
			var frm = document.ResultForm;

			lev = frm.strLevel.value;
			level = lev.substring((lev.indexOf("@@@")+3))
			tLevel = lev.substring(0,(lev.indexOf("@@@")))
				//alert(tLevel);

			Desi = frm.strDesignation.value;
			Desig = Desi.substring((Desi.indexOf("@@@")+3))
			tDesignation = Desi.substring(0,(Desi.indexOf("@@@")))
				//alert(tDesignation);

			if( paperId == "null" || paperId == ""){
				window.open('<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?surveyId=<%=strSurveyId%>&hiddenAction=selection&strAge='+frm.strAge.value+ '&strLevel='+level+'&strYear='+frm.strYear.value+'&strDesignation='+Desig+'&tDesignation='+tDesignation+'&tLevel='+tLevel);
			}
			else{
				window.open('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<%=strPaperId%>&hiddenAction=selection&strAge='+frm.strAge.value+ '&strLevel='+level+'&strYear='+frm.strYear.value+'&strDesignation='+Desig+'&tDesignation='+tDesignation+'&tLevel='+tLevel);
			}
		}

		function fnReset(){
		var frm = document.ResultForm;
		frm.strAge.value = "0";
		frm.strLevel.value = "@@@0";
		frm.strDesignation.value = "@@@0";
		frm.strYear.value = "0";
		}

		function submitSurveyListForm(submitUrl, actionName, surveyId, nPageNo) {
 			var frm = document.ResultForm;
			frm.action= '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=1234';
			frm.hiddenAction.value = actionName;
			frm.strSurveyId.value = surveyId;
			frm.submit();
		}



		//-->
</script>

<html:form action="/SurveyResult.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />
<html:hidden property="strResultType" />
<html:hidden property="strSurveyName" />

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />
<table width="100%" border="0" cellspacing="0" cellpadding="0">
<tr><td valign="top" class="Boxoutline">

<table width="100%" border="0" cellspacing="0" cellpadding="0">
	<tr class="Blue">
		<td colspan="1" class="Poll"><%=pageTitle%></td>
	</tr>
	<tr>
		<td colspan="1">
		<%
			if(strSurveyId != null)
			{
		%>
			<div align="left">
				<img src='<bean:message key="OIFM.docroot" />/images/tab_survey_results.gif' height="27" border="0" usemap="#MapMap">
					<map name="MapMap">
						<area shape="rect" coords="7,0,62,32" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>','<%=strSurveyId%>', '')" >						
						<area shape="rect" coords="76,3,141,27" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_SECTION_DO %>','<%=OISurveyConstants.SECTION_EDIT%>','<%=strSurveyId%>','')" >
						<area shape="rect" coords="150,0,284,29" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_QUESTION_DO %>','<%= OISurveyConstants.QUESTION_LIST %>','<%=strSurveyId%>','')" >
						<!--<area shape="rect" coords="230,0,380,29" href="javascript:submitSurveyResultForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.RESULT_TYPE %>')" >-->
						<area shape="rect" coords="230,0,380,29" href="#" onClick="history.back()">
					</map>
			</div>
		<%
			}
			if(strPaperId != null)
			{

		%>
				<div align="left">
						<img src='<bean:message key="OIFM.docroot" />/images/tab_cp.gif' width="368" height="27" border="0" usemap="#MapMap">
							<map name="MapMap">
								<area shape="rect" coords="7,0,150,32" href="<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<%=strPaperId%>&paperTitle=<%=strPaperTitle%>&hiddenAction=populate" >
								<area shape="rect" coords="170,3,230,27" href="<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<%=strPaperId%>&paperTitle=<%=strPaperTitle%>&hiddenAction=result" >
							</map>
				</div>
		<%
			}
		%>
		</td>
	</tr>
	<tr>
		<td colspan="1">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="1" class=body_detail_text vAlign=top><b>Responses by Demographics</b></td>
	</tr>
	<tr>
		<td colspan="1">&nbsp;</td>
	</tr>
	<tr>
		<td colspan="1" class=body_detail_text vAlign=top>Please select the required criteria. A questionwise breadown of the results can be obtained by clicking the 'Submit' button. Leave the default 'ALL' to obtain the overall results</td>
	</tr>
	<tr>
		<td colspan="1">&nbsp;</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td class=body_detail_text vAlign=top>Age</td>
					<td><html:select property="strAge" styleClass="Text">
							<html:option value="0">All</html:option>
							<html:option value="1">Below 30 years</html:option>
							<html:option value="2">30 to 40 years</html:option>
							<html:option value="3">Above 40 years</html:option>
						</html:select>
					</td>
					<td class=body_detail_text vAlign=top>Designation</td>
					<td><html:select property="strDesignation" styleClass="Text">
							<html:option value="@@@0">All</html:option>
				<%		for(i=0; i<alDesignationList.size(); i++){
							ArrayList alTemp =  null;
							alTemp = (ArrayList)alDesignationList.get(i);
							String strDesc = (String)alTemp.get(0);
							String strValue = (String)alTemp.get(1);
				%>
							<html:option value="<%=strDesc+"@@@"+strValue%>"><%=strDesc%></html:option>
				<%		}
				%>
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td class=body_detail_text vAlign=top>School Level</td>
					<td><html:select property="strLevel" styleClass="Text">
							<html:option value="@@@0">All</html:option>
				<%		for(i=0; i<alLevelList.size(); i++){
							ArrayList alTemp =  null;
							alTemp = (ArrayList)alLevelList.get(i);
							String strDesc = (String)alTemp.get(0);
							String strValue = (String)alTemp.get(1);
				%>
							<html:option value="<%=strDesc+"@@@"+strValue%>"><%=strDesc%></html:option>
				<%		}
				%>
						</html:select>
					</td>
					<td class=body_detail_text vAlign=top>Years in Service</td>
					<td><html:select property="strYear" styleClass="Text">
							<html:option value="0">All</html:option>
							<html:option value="1">< 3 years</html:option>
							<html:option value="2">3 to 5 years</html:option>
							<html:option value="3">5 to 10 years</html:option>
							<html:option value="4">> 10 years</html:option>
						</html:select>
					</td>
				</tr>
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left">
					<a href="#" onClick='fnOpen("<%=strPaperId%>");'><img src='<bean:message key="OIFM.docroot"/>/images/but_submit.gif' border="0" tabindex="42" alt = "Submit"></a>&nbsp;
					<a href="#" onClick='fnReset();'><img src='<bean:message key="OIFM.docroot"/>/images/btn_Clear.gif' border="0" tabindex="42" alt = "Reset"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
</td></tr>
</table>

</html:form>
