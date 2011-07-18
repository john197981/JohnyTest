<!--
Displaying Demographic Detail Results for consultation paper
Created by Edmund Choo
on 22 NOV 2006

-->
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>


<%@ page import="com.oifm.common.OIApplConstants" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.NumberFormat" %>

<%
ArrayList alList = new ArrayList();
ArrayList alTemp = new ArrayList();

Hashtable htQuestionRespondents = null;

String strPaperName = (String)request.getAttribute("paperTitle");
String strPaperId = (String)request.getAttribute("PaperId");
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

int i =0;
int k =0;
int x =0;
double iTotal = Double.parseDouble(strTotalUser);
double iSubTotal = 0.00;
double dPercent = 0.00;

NumberFormat nf = NumberFormat.getNumberInstance();	
nf.setMaximumFractionDigits(2);


if(request.getAttribute("ConsultDetail")!= null){
	alList = (ArrayList)request.getAttribute("ConsultDetail");
}

htQuestionRespondents = (Hashtable)request.getAttribute("QuestionRespondents");

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
//System.out.println("Start of Details page");
%>

<%@page import="java.util.Hashtable"%>
<html>
<head>
		<title>My Forum, Ministry Of Education - Consultation Report</title>

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

<table width="98%" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td class="Table_head">
		<table>
			<tr class="Table_head">
				<td>Paper title </td>
				<td><b>: <%=strPaperName%></b></td>
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
						if(alTemp.get(0)!=null && (String)htQuestionRespondents.get(alTemp.get(0))!=null)
						{
						iSubTotal = Double.parseDouble((String)htQuestionRespondents.get(alTemp.get(0)));
						}
						else
						{
							iSubTotal =0;
						}
							
						
						//dPercent = 0.00;

							//for(x = 8; x < 13; x++)
							//	{
							//		if(alTemp.get(x-6) != null)
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
					<td colspan="1" class="Sub_head"><b><%=alTemp.get(1)%></b></td>
					<td class="Sub_head"><font color='red'><%=iSubTotal%>&nbsp;&nbsp;&nbsp;(<%=strPercent%>%)</font></td>
				</tr>
				<tr bgcolor="white">
					<td>&nbsp;</td>
					<td>
						<table border="0" cellpadding="0"	cellspacing="0">
							<%
								for(k = 2; k<7; k++)
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
								String strOther = "";
								if(alTemp.get(7) != null)
								{
								strOther = (String)alTemp.get(7);
								}
							
								if(strOther.equals("1"))
								{
									String strQuestionId = (String)alTemp.get(0);
							%>
										<tr>
											<td  class=body_detail_text vAlign=top>&nbsp;</td>
											<td class=body_detail_text vAlign=top><a href='#' onClick="window.open('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<%=strPaperId%>&hiddenAction=otherQuestion&strAge=<%=strAge%> &strLevel=<%=strLevel%>&strYear=<%=strYear%>&strDesignation=<%=strDesignation%>&paperTitle=<%=strPaperName%>&questionId=<%=strQuestionId%>&question=<%=strQuestion%>')" > Others (Please Specify)</a>
											</td>
										</tr> 
							<%
								}
							%>
						</table>
					</td>
					<td>
						<table border="0" cellpadding="0"	cellspacing="0">
							<%
								for(x = 8; x < 13; x++)
								{
									//if(alTemp.get(x-6) != null)
									//{
							%>
										<tr>
											<td class=body_detail_text vAlign=top><%=alTemp.get(x)%>&nbsp;&nbsp;&nbsp;(
											<% 
												if(iSubTotal!=0)
													dPercent = (Double.parseDouble((String)alTemp.get(x))/iSubTotal)*100.00;
												else
													dPercent =0;
												//System.out.println("dPercent "+dPercent+"iSubTotal*********"+iSubTotal);
												strPercent = nf.format(dPercent);
												//System.out.println("strPercent "+strPercent);
												out.println(strPercent);
											%>%)
											</td>
										</tr>
							<%		//}
									//else
									//{
									//	break;
									//}
								}

								if(strOther.equals("1"))
								{
							%>
										<tr>
											<td class=body_detail_text vAlign=top><%=alTemp.get(13)%>&nbsp;&nbsp;&nbsp;(
											<% 
												/*dPercent = (Double.parseDouble((String)alTemp.get(13))/iTotal)*100.00;
												strPercent = nf.format(dPercent);
												out.println(strPercent);*/
												if(iSubTotal!=0)
													dPercent = (Double.parseDouble((String)alTemp.get(13))/iSubTotal)*100.00;
												else
													dPercent =0;
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
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td align="left">
					<a href='<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<%=strPaperId%>&hiddenAction=selection&strAge=<%=strAge%> &strLevel=<%=strLevel%>&strYear=<%=strYear%>&strDesignation=<%=strDesignation%>&paperTitle=<%=strPaperName%>&export=yes&tDesignation=<%=tDesignation%>&tLevel=<%=tLevel%>' >
					<img src='<bean:message key="OIFM.docroot" />/images/btn_export.gif' alt="Export the Result" border="0" ></a>
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
</html>