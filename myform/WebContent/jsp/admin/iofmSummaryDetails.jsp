<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ page language="java" contentType="text/html" import="com.oifm.useradmin.OIRankingBean,java.util.ArrayList,com.oifm.utility.OIUtilities"%>
  
<html:form action="/SummaryDetails.do" method="post">
 
	<html:hidden property="hiddenAction" />

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<%response.setContentType("application/vnd.ms-excel");
%>

<table width="100%" border="0" cellpadding="0"
		cellspacing="0">

<tr>
	<td class="TableHead">
	<table width="100%" border="0" cellspacing="1" cellpadding="1">
		<tr>
		<td>
			<%	int count1=0;
				int count2=0;
			%>
			<%if(request.getAttribute("TotalList")!=null){ 
				ArrayList alTotalList =(ArrayList)request.getAttribute("TotalList");
				if(alTotalList!=null && alTotalList.size()>0){
					for(int i=0;i<alTotalList.size();i++){
				%>

				<!-- Discussion Forum Age Start-->
				<table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="white" class="Box">					 
						<%if(i==0){%>
							<tr class="Blue"><td colspan="3" class="Poll">Forum Statistics</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr class="Table_head" >
								<td colspan="3">Discussion Forum: By Age Group
						<%}else if(i==1){%>
							<tr class="Table_head" >
								<td colspan="3">Discussion Forum: By Designation
						<%}else if(i==2){%>
							<tr class="Table_head" >
								<td colspan="3">Discussion Forum: By School Levels (Applicable to school staffs only) 
						<%}else if(i==3){%>
							<tr class="Blue"><td colspan="3" class="Poll">Consultation Paper Statistics</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr class="Table_head" >
								<td colspan="3">Consultation Paper (Only Private Papers): By Age Group 
						<%}else if(i==4){%>
							<tr class="Table_head" >
								<td colspan="3">Consultation Paper (Only Private Papers): By Designation
						<%}else if(i==5){%>
							<tr class="Table_head" >
								<td colspan="3">Consultation Paper (Only Private Papers):By School Levels (Applicable to school staffs only) 
						<%}else if(i==6){%>
							<tr class="Blue"><td colspan="3" class="Poll">Survey Statistics</td></tr>
							<tr><td>&nbsp;</td></tr>
							<tr class="Table_head" >
								<td colspan="3">Survey (Only Private Papers): By Age Group 
						<%}else if(i==7){%>
							<tr class="Table_head" >
								<td colspan="3">Survey (Only Private Papers): By Designation
						<%}else if(i==8){%>
							<tr class="Table_head" >
								<td colspan="3">Survey (Only Private Papers):By School Levels (Applicable to School staffs only)
						<%}%>
						</td>
 					</tr>
					
					<tr bgcolor="#F0F8FF" class="Sub_head">
						<td width="300">
						<%if(i==0 || i==3 || i==6 ){%>
						Age Group
						<%}else if(i==1 || i==4 || i==7 ){%>
						Designation
						<%}else if(i==2 || i==5 || i==8 ){%>
						School Level
						<%}%>
						</td>
						<td width="120" align="center">No. Of Participants</td>
						<td width="120" align="center">
						<%if(i==0 || i==1 || i==2 ){%>
						No. Of Posts
						<%}else if(i==3 || i==4 || i==5 ){%>
						No. Of Papers
						<%}else if(i==6 || i==7 || i==8 ){%>
						No. Of Surveys
						<%}%>						
						</td>
 					</tr>

					<%if(alTotalList.get(i)!=null){
						ArrayList alList =(ArrayList)alTotalList.get(i);
						if(alList!=null && alList.size()>0){
							OIRankingBean[] objVO= (OIRankingBean[])alList.get(0);
							
							count1 = 0;
							count2 = 0;

							for(int j=0;j<objVO.length;j++){ %>
								<tr>
								<td class="heading_attributes"><%=objVO[j].getStrName()%></td>
								<td class="heading_attributes" align="right"><%=objVO[j].getStrPostCount()%></td>
								<td class="heading_attributes" align="right"><%=objVO[j].getStrTotalCount()%></td>

								<%
									count1 = count1+OIUtilities.replaceNulltoZero(objVO[j].getStrPostCount());
									
									count2 = count2+OIUtilities.replaceNulltoZero(objVO[j].getStrTotalCount());
								%>
								</tr>
							
							<%}%>
							<!-- Total-->

								<tr bgcolor="#F0F8FF" class="Sub_head" >
									<td align="right">Total</td>
									<td align="right"><%=count1%></td>
									<td align="right"><%=count2%></td>
								</tr>
 					
						<%}
					}
					%>
			</table>
								<br>

			<%}
			}
			
			}%>

			<table width="40%">
			<tr>
			<td align="left">
			<a href="#" onclick="exportExcel()"><img src="<bean:message key="OIFM.docroot"/>/images/btn_export.gif" border="0" alt="Website Rank"></a>
			
			</td>
			</tr>
			</table>
				
				<!-- Discussion Forum Age End-->

			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</html:form>

<script>
function exportExcel() {

  	document.RankingForm.target =""
	document.RankingForm.reset();
	document.RankingForm.action="SummaryDetails.do"+'?id=<%= Math.random() %>';
	document.RankingForm.hiddenAction.value = "SummaryDetails_Excel";
	document.RankingForm.submit();
	return;
 
 }
</script>
