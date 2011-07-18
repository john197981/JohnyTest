<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>

<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
<head>
	<title>My Forum, Ministry Of Education</title>
</head>

<logic:present name="error" scope="request">
	<br><br>
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable" align="center">
		<tr>
    		<td width="75%" align="center" valign="top" class="Table_head">
				<bean:write name="error" scope="request" />
			</td>
		</tr>
	</table>    
</logic:present>

<logic:present name="message" scope="request">
	<br><br>
	<table width="80%"  border="0" cellspacing="0" cellpadding="0" class="BoxTable" align="center">
		<logic:iterate id="mList" name="message" scope="request">
			<tr>
    			<td width="75%" align="center" valign="top" class="Table_head">
					<bean:write name="mList"/>
				</td>
			</tr>
		</logic:iterate>
	</table>    
</logic:present>

<html:form action="/webConsultOpenPaperAction.do">
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" bgcolor="white">
<TR><TD>
<table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#FFFFFF" class="Box">
	<tr align="left" valign="top">
		<td height="20" colspan="2" class="Table_head">
			<bean:write name="ConsultPresentPaperForm" property="categoryName"/>
		</td>
	</tr>
	<tr align="left" valign="top">
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Paper Title 
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<bean:write name="ConsultPresentPaperForm" property="title"/>
			</span>
		</td>
	</tr>
	<tr>
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Target Audience
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<bean:write name="ConsultPresentPaperForm" property="targetAudiance"/>
			</span>
		</td>
	</tr>
	<tr>
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Contact Person
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<bean:write name="ConsultPresentPaperForm" property="contactPerson"/>
			</span>
		</td>
	</tr>
	<tr>
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Phone
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<bean:write name="ConsultPresentPaperForm" property="phone"/>
			</span>
		</td>
	</tr>
	<tr>
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Email Address
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<bean:write name="ConsultPresentPaperForm" property="emailAddress"/>
			</span>
		</td>
	</tr>
	</tr>
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Period
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<bean:write name="ConsultPresentPaperForm" property="fromDt"/>
				 to 
				<bean:write name="ConsultPresentPaperForm" property="toDt"/>
			</span>
		</td>
	</tr>
	</tr>
		<td width="14%" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>
				Status
			</strong>
		</td>
		<td width="86%" class="body_extract_text">
			<span class="style9">
				<logic:present name="ConsultPresentPaperForm" property="active">
					<logic:equal name="ConsultPresentPaperForm" property="active" value="1">
						Open
					</logic:equal>
					<logic:equal name="ConsultPresentPaperForm" property="active" value="0">
						Closed
					</logic:equal>
					<logic:notEqual name="ConsultPresentPaperForm" property="active" value="1">
						<logic:notEqual name="ConsultPresentPaperForm" property="active" value="0">
							Not Defined
						</logic:notEqual>
					</logic:notEqual>
				</logic:present>
			</span>
		</td>
	</tr>
	 <tr>
        <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
        <td colspan="4" valign="top" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
      </tr>
	<tr align="left" valign="top">
		<td height="73" bgcolor="#DEEAF3" class="Heading_Thread">
			<strong>Description</strong>
		</td>
		<td class="body_extract_text">
			<bean:write name="ConsultPresentPaperForm" property="description" filter="false" />
		</td>
	</tr>
	 <tr>
        <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
        <td colspan="4" valign="top" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
      </tr>
	  <% 
			boolean likertBlock=false;
			int likertCount=0;
		%>

	<tr align="left" valign="top">
		<td height="25" bgcolor="#DEEAF3" class="Heading_Thread"><strong>Questions</strong></td>
		<td height="25" class="body_extract_text">
			<table width="95%"  border="0" align="center" cellpadding="0" cellspacing="0">
				<logic:present name="ConsultPresentPaperForm" property="arOIFormConsultPresentPaperHelper">
					<logic:iterate id="questionListing" name="ConsultPresentPaperForm" property="arOIFormConsultPresentPaperHelper" type="com.oifm.consultation.OIFormConsultPresentPaperHelper">

					<logic:notEqual name="questionListing" property="useSameLikert" value="">
						<%@ include file="QuestionTypeLikBlock.incl" %>
							<%likertBlock=true;
							likertCount++;
							%>
					</logic:notEqual>

					<logic:equal name="questionListing" property="useSameLikert" value="">
						<%likertBlock=false;		
						 if(!likertBlock)	{ 
							if(likertCount>0)
							{%>
								</table>
								</td></tr>
							<%
							likertCount=0;
							}
						 }
								%>
						<tr>
							<td height="25" class="Question"><strong><bean:write name="questionListing" property="questionNo" /></strong></td>
							<td height="25" colspan="2"  class="body_extract_text">
								<strong>
									<bean:write name="questionListing" property="question" />
								</strong>
							</td>
						</tr>
						<logic:present name="questionListing" property="answerType">
							<logic:equal name="questionListing" property="answerType" value="1">
								<logic:present name="questionListing" property="answer1">
									<tr>
										<td class="body_extract_text">&nbsp;</td>
										<td class="body_extract_text">
											<logic:equal name="questionListing" property="response1" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer1" checked="checked" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer1" checked="checked" disabled="disabled">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="questionListing" property="response1" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer1" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer1">
												</logic:notEqual>
											</logic:notEqual>
										</td>
										<td class="body_extract_text"><bean:write name="questionListing" property="answer1" /></td>
									</tr>
								</logic:present>
								<logic:present name="questionListing" property="answer2">
									<tr>
										<td class="body_extract_text">&nbsp;</td>
										<td class="body_extract_text">
											<logic:equal name="questionListing" property="response2" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer2" checked="checked" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer2" checked="checked">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="questionListing" property="response2" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer2" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer2">
												</logic:notEqual>
											</logic:notEqual>
										</td>
										<td class="body_extract_text"><bean:write name="questionListing" property="answer2" /></td>
									</tr>
								</logic:present>
								<logic:present name="questionListing" property="answer3">
									<tr>
										<td class="body_extract_text">&nbsp;</td>
										<td class="body_extract_text">
											<logic:equal name="questionListing" property="response3" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer3" checked="checked" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer3" checked="checked">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="questionListing" property="response3" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer3" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer3">
												</logic:notEqual>
											</logic:notEqual>
										</td>
										<td class="body_extract_text"><bean:write name="questionListing" property="answer3" /></td>
									</tr>
								</logic:present>
								<logic:present name="questionListing" property="answer4">
									<tr>
										<td class="body_extract_text">&nbsp;</td>
										<td class="body_extract_text">
											<logic:equal name="questionListing" property="response4" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer4" checked="checked" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer4" checked="checked">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="questionListing" property="response4" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer4" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer4">
												</logic:notEqual>
											</logic:notEqual>
										</td>
										<td class="body_extract_text"><bean:write name="questionListing" property="answer4" /></td>
									</tr>
								</logic:present>
								<logic:present name="questionListing" property="answer5">
									<tr>
										<td class="body_extract_text">&nbsp;</td>
										<td class="body_extract_text">
											<logic:equal name="questionListing" property="response5" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer5" checked="checked" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer5" checked="checked">
												</logic:notEqual>
											</logic:equal>
											<logic:notEqual name="questionListing" property="response5" value="1">
												<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer5" disabled="disabled">
												</logic:equal>
												<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
													<input name='<bean:write name="questionListing" property="questionId" />' type="checkbox" value="answer5">
												</logic:notEqual>
											</logic:notEqual>
										</td>
										<td class="body_extract_text"><bean:write name="questionListing" property="answer5" /></td>
									</tr>
								</logic:present>
							</logic:equal>
							<logic:notEqual name="questionListing" property="answerType" value="1">
								<logic:notEqual name="questionListing" property="likertScale" value="1">
									<%@ include file="QuestionType2.incl" %>
								</logic:notEqual>
								<logic:equal name="questionListing" property="likertScale" value="1">
									
										<%@ include file="QuestionTypeLik.incl" %>
									
								</logic:equal>
							</logic:notEqual>
						</logic:present>
						</logic:equal>
					</logic:iterate>
					<%
					if(likertCount>0)
					{%>
						</table>
						</td></tr>
					<%
					likertCount=0;
					}
				%>
				</logic:present>
			</table>
		</td>
	</tr>
	<tr align="left" valign="top">
		<td height="73" bgcolor="#DEEAF3" class="Heading_Thread"><strong>Feedback</strong></td>
		<td class="body_extract_text" width="71%">
			<bean:write name="ConsultPresentPaperForm" property="feedBack1" filter="false" />
		</td>
	</tr>
	<tr align="left" valign="top" class="Table_head">
		<td height="25" class="body_extract_text">&nbsp;</td>
		<td >
			<a href="javascript:window.print()" >
				<img src='<bean:message key="OIFM.docroot" />/images/but_print.gif' alt="Print the consultation Paper"  border="0"></a>
		</td>
	</tr>
</table>
</td>
</tr>
</table>
</html:form>