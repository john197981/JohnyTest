<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>

<%

response.setContentType("application/vnd.ms-excel");
response.setHeader("Content-Disposition", "attachment;filename=SummaryReport.xls");

ArrayList alList = new ArrayList();
ArrayList alAgeList = new ArrayList();
ArrayList alLevelList = new ArrayList();
ArrayList alYearList = new ArrayList();
ArrayList alDesignationList = new ArrayList();
String strTitle = "title";
String strTotalUser = "1";
String strPercent = "";


if(request.getAttribute("TotalUser") != null){
	strTotalUser = (String)request.getAttribute("TotalUser");
}
;

double dTotal = Double.parseDouble(strTotalUser);
double dPercent = 0.00;
double dSubTotal = 0;
NumberFormat nf = NumberFormat.getNumberInstance();	
nf.setMaximumFractionDigits(2);

NumberFormat nf2 = NumberFormat.getNumberInstance();	
nf2.setMaximumFractionDigits(0);

if(request.getAttribute("paperTitle") != null){
	strTitle = (String)request.getAttribute("paperTitle");
}

if(request.getAttribute("ConsultDemographicSummary")!= null){
	alList = (ArrayList)request.getAttribute("ConsultDemographicSummary");
	if(!alList.isEmpty() && alList.size()>3){
		alAgeList = (ArrayList)alList.get(0);
		alLevelList = (ArrayList)alList.get(1);
		alYearList = (ArrayList)alList.get(2);
		alDesignationList = (ArrayList)alList.get(3);
	}
}
%>

<html>
<head>
		<title>My Forum, Ministry Of Education</title>
</head>
<script language="javascript" >

</script>

<table width="98%" border="0" cellpadding="0"	cellspacing="0">
<tr>
	<td colspan="2">&nbsp;</td>
</tr>
<tr class="Blue">
	<td colspan="2" class="Poll">Profile of Respondents</td>
</tr>
<tr>
	<td colspan="2"><B>Paper Title : <%=strTitle%></B></td>
</tr>
<tr>
	<td colspan="2" align="left" valign="top" class="Boxoutline">
		<table border="0" cellpadding="0"	cellspacing="0">
			<tr>
				<td>
					<table border="0" cellpadding="0"	cellspacing="0">
						<tr>
							<td align="left" valign="top" class="Boxoutline">
								<table border="0" cellpadding="0" cellspacing="0" width="350">
									<tr class="Table_head">
										<td colspan="2">By Age Group</td>
									</tr>
									<tr bgcolor="#F0F8FF" class="Sub_head">
										<td width="190">Age Group</td>
										<td align="right">No of Respondents(%)</td>
									</tr>
									<% if(alAgeList != null && !(alAgeList.size()<= 0))
										{
											dSubTotal = 0;
											for (int i = 0; i < alAgeList.size() ; i++){
												ArrayList alTemp =  null;
												alTemp = (ArrayList)alAgeList.get(i);
												String strDesc = (String)alTemp.get(0);
												String strNumber = (String)alTemp.get(1);
												strDesc = (strDesc==null)?"":strDesc;
												strNumber = (strNumber==null)?"":strNumber;

												dSubTotal = dSubTotal + Double.parseDouble(strNumber);
									%>
									<tr>
										<TD class=body_detail_text vAlign=top><%=strDesc %></td>
										<td class=body_detail_text vAlign=top align="right"><%=strNumber %></td>
									</tr>
									<% 		} 
										} else {%>
									<tr>
										<TD class=body_detail_text vAlign=top colspan="2">No Record</td>
									</tr>
									<%	} %>
									<tr bgcolor="#F0F8FF" class="Sub_head">
										<td align="right">Total</td>
										<td align="right"><%=nf2.format(dSubTotal) %>&nbsp;(
										<%
												dPercent = (dSubTotal/dTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent+"%");
										%>)
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left" valign="top" class="Boxoutline">
								<table border="0" cellpadding="0" cellspacing="0" width="350">
									<tr class="Table_head">
										<td colspan="2">By School Level</td>
									</tr>
									<tr bgcolor="#F0F8FF" class="Sub_head">
										<td width="190">School Level</td>
										<td align="right">No of Respondents(%)</td>
									</tr>
									<% if(alLevelList!=null && !(alLevelList.size()<= 0))
										{
											dSubTotal = 0;
											for (int i = 0; i < alLevelList.size() ; i++){
												ArrayList alTemp =  null;
												alTemp = (ArrayList)alLevelList.get(i);
												String strDesc = (String)alTemp.get(0);
												String strNumber = (String)alTemp.get(1);
												strDesc = (strDesc==null)?"":strDesc;
												strNumber = (strNumber==null)?"":strNumber;

												dSubTotal = dSubTotal + Double.parseDouble(strNumber);
									%>
									<tr>
										<TD class=body_detail_text vAlign=top><%=strDesc %></td>
										<td class=body_detail_text vAlign=top align="right"><%=strNumber %></td>
									</tr>
									<% 		} 
										} else {%>
									<tr>
										<TD class=body_detail_text vAlign=top colspan="2">No Record</td>
									</tr>
									<%	} %>
									<tr bgcolor="#F0F8FF" class="Sub_head">
										<td align="right">Total</td>
										<td align="right"><%=nf2.format(dSubTotal) %>&nbsp;(
										<%
												dPercent = (dSubTotal/dTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent+"%");
										%>)</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td align="left" valign="top" class="Boxoutline">
								<table border="0" cellpadding="0" cellspacing="0" width="350">
									<tr class="Table_head">
										<td colspan="2">By Years in Service</td>
									</tr>
									<tr bgcolor="#F0F8FF" class="Sub_head">
										<td width="190">Years in Service</td>
										<td align="right">No of Respondents(%)</td>
									</tr>
									<% if(alYearList!=null && !(alYearList.size()<= 0))
										{
											dSubTotal = 0;
											for (int i = 0; i < alYearList.size() ; i++){
												ArrayList alTemp =  null;
												alTemp = (ArrayList)alYearList.get(i);
												String strDesc = (String)alTemp.get(0);
												String strNumber = (String)alTemp.get(1);
												strDesc = (strDesc==null)?"":strDesc;
												strNumber = (strNumber==null)?"":strNumber;

												dSubTotal = dSubTotal + Double.parseDouble(strNumber);
									%>
									<tr>
										<TD class=body_detail_text vAlign=top><%=strDesc %></td>
										<td class=body_detail_text vAlign=top align="right"><%=strNumber %></td>
									</tr>
									<% 		} 
										} else {%>
									<tr>
										<TD class=body_detail_text vAlign=top colspan="2">No Record</td>
									</tr>
									<%	} %>
									<tr bgcolor="#F0F8FF" class="Sub_head">
										<td align="right">Total</td>
										<td align="right"><%=nf2.format(dSubTotal) %>&nbsp;(
										<%
												dPercent = (dSubTotal/dTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent+"%");
										%>)</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td align="left" valign="top" class="Boxoutline">
					<table border="0" cellpadding="0" cellspacing="0" width="375">
						<tr class="Table_head">
							<td colspan="2">By Designation</td>
						</tr>
						<tr bgcolor="#F0F8FF" class="Sub_head">
							<td width="210">Designation</td>
							<td>No of Respondents(%)</td>
						</tr>
						<% if(alDesignationList!=null && !(alDesignationList.size()<= 0))
							{
								dSubTotal = 0;
								for (int i = 0; i < alDesignationList.size() ; i++){
									ArrayList alTemp =  null;
									alTemp = (ArrayList)alDesignationList.get(i);
									String strDesc = (String)alTemp.get(0);
									String strNumber = (String)alTemp.get(1);
									strDesc = (strDesc==null)?"":strDesc;
									strNumber = (strNumber==null)?"":strNumber;

									dSubTotal = dSubTotal + Double.parseDouble(strNumber);
						%>
						<tr>
							<TD class=body_detail_text vAlign=top><%=strDesc %></td>
							<td class=body_detail_text vAlign=top align="right"><%=strNumber %></td>
						</tr>
						<% 		} 
							} else {%>
						<tr>
							<TD class=body_detail_text vAlign=top colspan="2">No Record</td>
						</tr>
						<%	} %>
						<tr bgcolor="#F0F8FF" class="Sub_head">
							<td align="right">Total</td>
							<td align="right"><%=nf2.format(dSubTotal) %>&nbsp;(
										<%
												dPercent = (dSubTotal/dTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent+"%");
										%>)</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</td>
</tr>
</table>


</html>