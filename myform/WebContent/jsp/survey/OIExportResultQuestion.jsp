<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.oifm.survey.OIBASurveyResponse" %>
<%@ page import="java.text.NumberFormat" %>

<%
response.setHeader("Content-Disposition", "attachment;filename=SurveyDetailReport.xls");
response.setContentType("application/vnd.ms-excel");


ArrayList alList = new ArrayList();
ArrayList alTemp = new ArrayList();
Hashtable htQuestionRespondents = null;
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

htQuestionRespondents = (Hashtable)request.getAttribute("QuestionRespondents");

strAge = strAge.trim();
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
		</table>
		</td>
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
					<td colspan="3" class="Table_head">No Record Found</td>
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
						iSubTotal = Double.parseDouble((String)htQuestionRespondents.get(alTemp.get(0))) ;
						//dPercent = 0.00;

							//for(x = 13; x < 23; x++)
							//	{
							//		if(alTemp.get(x-11) != null)
							//		{
							//			//iSubTotal += Double.parseDouble((String)alTemp.get(x));
							//		}
							//	}
						
						dPercent = (int)(iSubTotal/iTotal)*100;
						strPercent = nf.format(dPercent);
				%>
				<tr bgcolor="white">
					<td class="Sub_head">Q<%=i+1%></td>
					<td colspan="1" class="Sub_head"><b><%=alTemp.get(1)%></b></td>
					<td class="Sub_head"><font color='red'><%=iSubTotal%>&nbsp;(<%=strPercent%>%)</font></td>
				</tr>
				<tr bgcolor="white">
					<td></td>
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
											<td width="100" align="left"><%=k-1%></td>
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
							%>
										<tr>
											<td width="100" align="left">&nbsp;</td>
											<td class=body_detail_text vAlign=top align="left">Others (Please Specify)</td>
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
											<td class=body_detail_text vAlign=top align="left"><%=alTemp.get(x)%>(
											<% 
												dPercent = (Double.parseDouble((String)alTemp.get(x))/iSubTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent);
											%>%)</td>
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
											<td class=body_detail_text vAlign=top align="left"><%=alTemp.get(23)%>(
											<% 
												dPercent = (Double.parseDouble((String)alTemp.get(23))/iTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent);
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