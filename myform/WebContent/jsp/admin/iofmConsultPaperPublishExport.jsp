<!--
	Edited by edmund choo on 23 NOV 2006
-->

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@page import="java.util.ArrayList"%>
<%@ page import="com.oifm.consultation.admin.OIFormConsultPaperHelper" %>
<html>
<head>
<title>My Forum, Ministry Of Education</title>
</head>
<%@page contentType="text/html"%>
<%
try
{
%>
<%response.setContentType("application/vnd.ms-excel");
int k=0; 
%>
<%
	String strMulti = "N";	
	String strQuesNo = null;
	String printFlag = "N";
	String checkFlag = "Y";
	

%>


<logic:present name="error" scope="request">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="BoxTable">
		<tr>
			<td width="85%" align="center" valign="top" class="Mainheader">
			<bean:write name="error" scope="request" /></td>
		</tr>
	</table>
</logic:present>

<logic:present name="message" scope="request">
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="BoxTable">
		<logic:iterate id="mList" name="message" scope="request">
			<tr>
				<td width="85%" align="center" valign="top" class="Mainheader">
				<bean:write name="mList" /></td>
			</tr>
		</logic:iterate>
	</table>
</logic:present>


<table width="100%" border="0" align="left" cellpadding="0"
	cellspacing="0">
	<tr>
		<td width="85%" align="left" valign="top">
		<table width="100%" border="0" align="center" cellpadding="0"
			cellspacing="0">
			<tr>
				<td align="left" valign="top" class="Boxoutline">
				<table width="100%" border="0" align="left" cellpadding="0"
					cellspacing="0">
					<tr>
						<td width="130" align="left" valign="top" class="BodyText">Paper
						Title</td>
						<td width="490" class="BodyText"><bean:write
							name="ConsultPublishForm" property="title" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Duration</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="fromDt" /> to <bean:write name="ConsultPublishForm"
							property="toDt" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Target
						Audience</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="targetAudiance" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Contact Person
						</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="contactPerson" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Telephone
						Number</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="phone" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Email Address
						</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="emailAddress" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Description</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="description" filter="false" /></td>
					</tr>
					<tr>
						<td align="left" valign="top" class="BodyText">Summary</td>
						<td class="BodyText"><bean:write name="ConsultPublishForm"
							property="summary" filter="false" /></td>
					</tr>
					<tr>
						<td colspan="2" class="BodyText">&nbsp;</td>
					</tr>
				</table>
				</td>
			</tr>
		</table>
		<br>
		</td>
	</tr>
	<tr>
		<td align="center" valign="top">

		<table width="98%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td colspan=8></td>
				<logic:present name="ConsultPublishForm"
					property="arOIBAConsultQuestion" scope="request">
					<logic:iterate id="questionListing" name="ConsultPublishForm"
						property="arOIBAConsultQuestion"
						type="com.oifm.consultation.admin.OIFormConsultPaperHelper">
						<td colspan='2'>Q : <bean:write name="questionListing"
							property="questionNo" />.</td>
						
					</logic:iterate>
				</logic:present>
			</tr>
			<tr>
				<td align="right">Age</td>
				<td>Years in Service</td>
				<td>Designation</td>
				<td>School</td>
				<td>Division</td>
				<td>School Level</td>
				<td>Feedback Date</td>
				<td>Feedback</td>
				<!--added new -->
				<logic:present name="ConsultPublishForm"
					property="arOIBAConsultQuestion" scope="request">
					<logic:iterate id="questionListing" name="ConsultPublishForm"
						property="arOIBAConsultQuestion"
						type="com.oifm.consultation.admin.OIFormConsultPaperHelper">
						<%// k=0; %>
						<td>Ans</td>
						<td>Remarks</td>
					</logic:iterate>
				</logic:present>

				<!--added new -->
			</tr>
			<logic:present name="ConsultPublishForm"
				property="arOIFormConsultPublishHelper" scope="request">
				<logic:iterate id="feedBackListing" name="ConsultPublishForm"
					property="arOIFormConsultPublishHelper"
					type="com.oifm.consultation.admin.OIFormConsultPublishHelper">
					<tr>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="age" /></td>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="service" /></td>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="designation" /></td>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="school" /></td>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="division" /></td>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="schoolLevel" /></td>
						<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="feedBackDate" /></td>

						<!--added new -->
				<td class="BodyText" valign="top"><bean:write
							name="feedBackListing" property="feedBack" filter="false" /></td>
					
							<%
								//printFlag = "N";
								//checkFlag = "N";
								
							%>
											
						<logic:present name="feedBackListing" property="responses">
						
						 <%
								//printFlag = "N";
								//checkFlag = "Y";
								
							%>
						<logic:iterate id="responseListing" name="feedBackListing" property="responses" type="com.oifm.consultation.OIBAResponse">
								<bean:define id="compare" name="questionListing" property="questionNo" type="java.lang.String" /> 
									
									<%
										strMulti = "N";
										
									%>
									<logic:equal name="responseListing" property="questionNo" value="1" >
												<td>
												
												
												<logic:present name="responseListing" property="response1">
													1
													<%
														strMulti = "Y";
														%>
												</logic:present>
												
												<logic:present name="responseListing" property="response2">
												
													<%
														if(strMulti.equals("Y"))
														{
															out.println(",");
														}
														strMulti = "Y";
													%>
													2
												</logic:present>
												
												<logic:present name="responseListing" property="response3">
													<%
														if(strMulti.equals("Y"))
														{
															out.println(",");
														}
														strMulti = "Y";
													%>
													3
												</logic:present>
												
												<logic:present name="responseListing" property="response4">
													<%
														if(strMulti.equals("Y"))
														{
															out.println(",");
														}
														strMulti = "Y";
													%>
													4
												</logic:present>
												
												<logic:present name="responseListing" property="response5">
													<%
														if(strMulti.equals("Y"))
														{
															out.println(",");
														}
													%>
													5
												</logic:present>
												
												</td>
												
												<td>
													<bean:write name="responseListing" property="otherRemarks"/>
												</td>
												
											</logic:equal>			
												
												<logic:notEqual name="responseListing" property="questionNo" value="1">
												
												<td></td>
												<td></td>
												</logic:notEqual>
												
												</logic:iterate>
												
								</logic:present>
						

								<!--added new -->
							</tr>
							
						</logic:iterate>
					</logic:present> 
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table border="0" align="left" cellpadding="0" cellspacing="0">
			<logic:present name="ConsultPublishForm"
				property="arOIBAConsultQuestion" scope="request">
				<logic:iterate id="questionListing" name="ConsultPublishForm"
					property="arOIBAConsultQuestion"
					type="com.oifm.consultation.admin.OIFormConsultPaperHelper">
					<% int i=0; %>
					<tr>
						<td colspan="3">Q : <bean:write name="questionListing"
							property="questionNo" />. 
					</tr>

					<tr>
						<td>&nbsp;</td>
						<logic:present name="questionListing" property="answer1">
						<td colspan="2"><%= ++i %>. <bean:write
							name="questionListing" property="answer1" /></td>
						</logic:present>
						<logic:notPresent name="questionListing" property="answer1">
						<td colspan="2" align="left"><%= ++i %>. </td>
						</logic:notPresent>
					</tr>


					<tr>
						<td width="9%">&nbsp;</td>
						<logic:present name="questionListing" property="answer2">
						<td colspan="2"><%= ++i %>. <bean:write
							name="questionListing" property="answer2" /></td>
						</logic:present>
						<logic:notPresent name="questionListing" property="answer2">
						<td colspan="2" align="left"><%= ++i %>. </td>
						</logic:notPresent>

					</tr>


					<tr>
						<td>&nbsp;</td>
						<logic:present name="questionListing" property="answer3">
						<td colspan="2"><%= ++i %>. <bean:write
							name="questionListing" property="answer3" /></td>
						</logic:present>
						<logic:notPresent name="questionListing" property="answer3">
						<td colspan="2" align="left"><%= ++i %>. </td>
						</logic:notPresent>

					</tr>


					<tr>
						<td>&nbsp;</td>
						<logic:present name="questionListing" property="answer4">
						<td colspan="2"><%= ++i %>. <bean:write
							name="questionListing" property="answer4" /></td>
						</logic:present>
						<logic:notPresent name="questionListing" property="answer4">
						<td colspan="2" align="left"><%= ++i %>. </td>
						</logic:notPresent>

					</tr>


					<tr>
						<td>&nbsp;</td>
						<logic:present name="questionListing" property="answer5">
						<td colspan="2"><%= ++i %>. <bean:write
							name="questionListing" property="answer5" /></td>
						</logic:present>
						<logic:notPresent name="questionListing" property="answer5">
						<td colspan="2" align="left"><%= ++i %>. </td>
						</logic:notPresent>
						
					</tr>


					<tr>
						<td colspan="3"></td>
					</tr>
				</logic:iterate>
			</logic:present>
		</table>
		</td>
	</tr>
</table>
<br>
<br>


</html>
<%
}catch(Exception e)
{
out.println(e.getMessage());
}
%>
