<!--
Displaying Demographic Detail Results report for survey
Created by Edmund Choo
on 22 NOV 2006

-->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oifm.survey.OIBASurveyResponse" %>
<%@ page import="java.text.NumberFormat" %>

<%
ArrayList alList = new ArrayList();
ArrayList alTemp = new ArrayList();
String strSurveyName = (String)request.getAttribute("SurveyTitle");
String strSurveyId = (String)request.getAttribute("SurveyId");
String strTotalUser = (String)request.getAttribute("TotalUser");
String strAge = (String)request.getAttribute("strAge");
String strLevel = (String)request.getAttribute("strLevel");
String strDesignation = (String)request.getAttribute("strDesignation");
String strYear = (String)request.getAttribute("strYear");
String tDesignation = (String)request.getAttribute("tDesignation");
String tLevel = (String)request.getAttribute("tLevel");
String tAge = "";
String tYear = "";
String strPercent = "";
String strQuestion = "";

Hashtable questionRespondentsTable = (Hashtable)request.getAttribute("QuestionRespondents");

int i =0;
int k =0;
int x =0;
double iTotal = Double.parseDouble(strTotalUser);
double iSubTotal = 0.00;
double dPercent = 0.00;

NumberFormat nf = NumberFormat.getNumberInstance();	
nf.setMaximumFractionDigits(2);


if(request.getAttribute("SurveyDemoDetail")!= null){
	alList = (ArrayList)request.getAttribute("SurveyDemoDetail");
}

if(strAge.equals("1")){
	tAge = "Below 30 years";
}else if(strAge.equals("2")){
	tAge = "30 to 40 years";
}else if(strAge.equals("3")){
	tAge = "Above 40 years";
}else{
	tAge = "All";
}

if(strYear.equals("1")){
	tYear = "< 3 years";
}else if(strYear.equals("2")){
	tYear = "3 to 5 years";
}else if(strYear.equals("3")){
	tYear = "5 to 10 years";
}else if(strYear.equals("4")){
	tYear = "> 10 years";
}else{
	tYear = "All";
}

%>

<%@page import="java.util.Hashtable"%>
<html>
<head>
		<title>My Forum, Ministry Of Education - Survey Report</title>

		<style type="text/css">
			<!--
			@import url("<bean:message key="OIFM.docroot" />/css/iofs.css");
			.style2 
			{
				color: #FFFFFF;
				font-weight: bold;
			}
			-->
		</style>
</head>

<script language="javascript" >

</script>

<html:form action="/SurveyResult.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strSectionId" />
<html:hidden property="strResultType" />
<html:hidden property="strSurveyName" />

<table width="98%" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td class="Table_head">
		<table>
			<tr class="Table_head">
				<td>Survey title </td>
				<td><b>: <%=strSurveyName%></b></td>
			</tr>
			<tr class="Table_head">
				<td>Age </td>
				<td>: <%=tAge%></td>
			</tr>
			<tr class="Table_head">
				<td>Years in Service </td>
				<td>: <%=tYear%></td>
			</tr>
			<tr class="Table_head">
				<td>Designation </td>
				<td>: <%=tDesignation%></td>
			</tr>
			<tr class="Table_head">
				<td>School Level </td>
				<td>: <%=tLevel%></td>
			</tr>
		</table></td>
	</tr>
	<tr>
		<td class="Boxoutline">
			<table width="98%" border="1" cellpadding="0"	cellspacing="0">
				<tr>
					<td colspan="3"></td>
				</tr>
				<%
					if(alList.size() <= 0 )
					{
				%>
				<tr>
					<td colspan="3" class="Sub_head" align="center" style="font-size: 14px">No Record Found</td>
				</tr>
				<%
					}
					else
					{
				%>
				<tr bgcolor="#F0F8FF">
					<td class="Table_head" width="50">Q NO.</td>
					<td class="Table_head">Question</td>
					<td class="Table_head" width="100">Result</td>
				</tr>
				<%
					for (i = 0; i < alList.size() ; i++)
					{
						alTemp = new ArrayList();
						alTemp = (ArrayList)alList.get(i);
						iSubTotal = Double.parseDouble((String)questionRespondentsTable.get(alTemp.get(0)));
						//iSubTotal = iTotal;
						//dPercent = 0.00;

							//for(x = 13; x < 23; x++)
							//	{
							//		if(alTemp.get(x-11) != null)
							//		{
							//			//iSubTotal += Double.parseDouble((String)alTemp.get(x));
							//		}
							//	}
						
						dPercent = (iSubTotal/iTotal)*100.00;
						strPercent = nf.format(dPercent);

						strQuestion = (String)alTemp.get(1);
				%>
				<tr bgcolor="white">
					<td class="Sub_head">Q<%=i+1%></td>
					<td colspan="1" class="Sub_head"><b><%=alTemp.get(1)%> </b></td>
					<td  class="Sub_head"><font color='red'>&nbsp;&nbsp;<%=(int)iSubTotal%>&nbsp;&nbsp;&nbsp;(<%=strPercent%>%)</font>&nbsp;&nbsp;&nbsp;&nbsp;</td>
				</tr>
				<tr bgcolor="white">
					<td>&nbsp;</td>
					<td>
						<table border="0" cellpadding="0"	cellspacing="0">
							<%
								if(alTemp.size() > 20)
								for(k = 2; k<12; k++)
								{
									//if(alTemp.get(k) != null)
									//{
							%>
										<tr>
											<td class=body_detail_text vAlign=top><%=k-1%>.&nbsp;&nbsp;&nbsp;</td>
											<td class=body_detail_text vAlign=top><%=(alTemp.get(k) == null)?"":alTemp.get(k)%></td>
										</tr>
							<%		//}
									//else
									//{
									//	break;
									//}
								}
							%>
							<%
								String strOther = (String)alTemp.get(12);
								if(strOther.equals("Y"))
								{
									String strQuestionId = (String)alTemp.get(0);
							%>
										<tr>
											<td class=body_detail_text vAlign=top>&nbsp;</td>
											<td class=body_detail_text vAlign=top><a href='#' onClick="window.open('<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?surveyId=<%=strSurveyId%>&hiddenAction=otherQuestion&strAge=<%=strAge%> &strLevel=<%=strLevel%>&strYear=<%=strYear%>&strDesignation=<%=strDesignation%>&surveyTitle=<%=strSurveyName%>&questionId=<%=strQuestionId%>&question=<%=strQuestion%>','Report','width=350,height=300,resizable=yes,scrollbars=yes')" > Others (Please Specify)</a>
											</td>
										</tr> 
							<%
								}
							%>
						</table>
					</td>
					<td>
						<table border="0" cellpadding="0"	cellspacing="0">
							<%	if(alTemp.size() > 20)
								for(x = 13; x < 23; x++)
								{
									//if(alTemp.get(x-11) != null)
									//{
							%>
										<tr>
											<td class=body_detail_text vAlign=top><%=alTemp.get(x)%>&nbsp;&nbsp;&nbsp;
											(<%
												dPercent = (Double.parseDouble((String)alTemp.get(x))/iSubTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.print(strPercent);
											%>%)
											</td>
										</tr>
							<%		//}
									//else
									//{
									//	break;
									//}
								}

								if(strOther.equals("Y"))
								{
							%>
										<tr>
											<td class=body_detail_text vAlign=top><%=alTemp.get(23)%>&nbsp;&nbsp;&nbsp;
											(<%
												dPercent = (Double.parseDouble((String)alTemp.get(23))/iSubTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.print(strPercent);
											%>%)
											</td>
										</tr>
							<%	}
							%>
						</table>
					</td>
				</tr>
				<%
					}
				%>
			</table>
		</td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left">
					<a href='<bean:message key="OIFM.contextroot" />/SurveyAdmin.do?surveyId=<%=strSurveyId%>&hiddenAction=selection&strAge=<%=strAge%> &strLevel=<%=strLevel%>&strYear=<%=strYear%>&strDesignation=<%=strDesignation%>&surveyTitle=<%=strSurveyName%>&export=yes&tDesignation=<%=tDesignation%>&tLevel=<%=tLevel%>' >
					<img src='<bean:message key="OIFM.docroot" />/images/btn_export.gif' alt="Export the Survey" border="0" ></a>
					&nbsp;&nbsp;
					<a href="#" onClick="window.print()"><img src='<bean:message key="OIFM.docroot"/>/images/btn_print.gif' border="0" alt = "Print Page"></a>
					</td>
				</tr>
			</table>
		</td>
	</tr>
	<%			}
		%>
	<tr>
		<td></td>
	</tr>
</table>
</html:form>
</html>