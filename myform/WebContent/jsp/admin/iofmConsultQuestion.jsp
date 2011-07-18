<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%
  com.oifm.consultation.admin.OIBAConsultQuestion objPrevQuestion = (com.oifm.consultation.admin.OIBAConsultQuestion)request.getAttribute("objPreviousQuestion");
  
%>
<html>
	<head>
		<title>Consultation Paper</title>
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<link href='<bean:message key="OIFM.docroot" />/css/iofs.css' rel="stylesheet" type="text/css">
		<style type="text/css">
			<!--
			.style1 {font-size: 9pt}
			.style3 {font-size: 10}
			body 
			{
				background-image: url(<bean:message key="OIFM.docroot" />/images/Admin_bk.gif);
			}
			-->
		</style>
		<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
		<script language="javascript">

			function fnSubmit(actionName,ac)
			{
				if (document.ConsultQuestionForm.question.value.length>200)
				{
					alert('<%= OIDBRegistry.getValues("OI.CONS.QST.LEN") %>');
					document.ConsultQuestionForm.question.focus();
					return;
				}
				var re = /\n/;
				//document.ConsultQuestionForm.question.value = document.ConsultQuestionForm.question.value.replace(/\s+/,"");
				fn_textCounter(this.ConsultQuestionForm.question,this.ConsultQuestionForm.numleft);
				
				var ansCounter = 0;
				if(Trim(document.ConsultQuestionForm.answer1.value)!="") ansCounter++;
				if(Trim(document.ConsultQuestionForm.answer2.value)!="") ansCounter++;
				if(Trim(document.ConsultQuestionForm.answer3.value)!="") ansCounter++;
				if(Trim(document.ConsultQuestionForm.answer4.value)!="") ansCounter++;
				if(Trim(document.ConsultQuestionForm.answer5.value)!="") ansCounter++;

				if(Trim(document.ConsultQuestionForm.question.value)=="")
				{
					alert('<%= OIDBRegistry.getValues("OI.CONS.QST.EMPTY") %>');
					document.ConsultQuestionForm.question.focus();
				}
				else if(Trim(document.ConsultQuestionForm.answer1.value)=="")
				{
					alert('<%= OIDBRegistry.getValues("OI.CONS.QST.ANS1") %>');
					document.ConsultQuestionForm.answer1.focus();
				}
//				else if(Trim(document.ConsultQuestionForm.answer2.value)=="")
//				{
//					alert('<%= OIDBRegistry.getValues("OI.CONS.QST.ANS2") %>');
//					document.ConsultQuestionForm.answer2.focus();
//				}
				else if (ansCounter < 2)
				{
					alert('<%= OIDBRegistry.getValues("OI.CONS.QST.MIN2ANS") %>');
					document.ConsultQuestionForm.answer1.focus();
				}
				else
				{
					document.ConsultQuestionForm.question.value=Trim(document.ConsultQuestionForm.question.value);
					document.ConsultQuestionForm.hiddenAction.value=ac;
					document.ConsultQuestionForm.action=actionName;
					//document.loginForm.method="post";
					enableAll();
					document.ConsultQuestionForm.submit();
				}	
			}
			function fnClear()
			{
				document.ConsultQuestionForm.reset();
				fn_textCounter(this.ConsultQuestionForm.question,this.ConsultQuestionForm.numleft);
			}

			var maxlimit = 200;
			function fn_textCounter(field, countfield) 
			{
				if (field.value.length > maxlimit) 
				{
					field.value = field.value.substring(0, maxlimit);
					cmdObj=document.getElementById("exceed");
					cmdObj.value="You have exceeded the maximum allowed characters in this field";
				}
				else
				{
					countfield.value = maxlimit - field.value.length;
					cmdObj=document.getElementById("exceed");
					cmdObj.value="";
				}
			}
			function enableAll()
			{
					document.ConsultQuestionForm.answer1.disabled = false;
					document.ConsultQuestionForm.answer2.disabled = false;
					document.ConsultQuestionForm.answer3.disabled = false;
					document.ConsultQuestionForm.answer4.disabled = false;
					document.ConsultQuestionForm.answer5.disabled = false;

			}
			function disableAll()
			{
				document.ConsultQuestionForm.answer1.disabled = true;
					document.ConsultQuestionForm.answer2.disabled = true;
					document.ConsultQuestionForm.answer3.disabled = true;
					document.ConsultQuestionForm.answer4.disabled = true;
					document.ConsultQuestionForm.answer5.disabled = true;
					
			}
			function setLikertScale(obj)
			{
				if(obj.checked)
				{
					if (document.getElementById('prevScale')!=null)
						document.getElementById('prevScale').style.display='block'; 
					document.ConsultQuestionForm.answerType.checked=false;
					document.ConsultQuestionForm.answerType.disabled=true;
					
					document.ConsultQuestionForm.answer5.value = '';
					document.ConsultQuestionForm.answer5.disabled = false;
					
					document.ConsultQuestionForm.needOtherRemark.checked = false;
					document.ConsultQuestionForm.needOtherRemark.disabled = true;
				}
				
				else if(!obj.checked)
				{
					if (document.getElementById('prevScale')!=null)
					{
						document.getElementById('prevScale').style.display='none';
						document.ConsultQuestionForm.useSameLikert.checked=false;
					}
					document.ConsultQuestionForm.answerType.disabled=false;
					
					document.ConsultQuestionForm.needOtherRemark.checked = false;
					document.ConsultQuestionForm.needOtherRemark.disabled = false;
				}
			}
			
			function setMultiAnswer(obj)
			{
				if(obj.checked)
				{
					document.ConsultQuestionForm.likertScale.checked=false;
					document.ConsultQuestionForm.likertScale.disabled=true;
				}
				
				else if(!obj.checked)
				{
					document.ConsultQuestionForm.likertScale.disabled=false;
				}
			}

			function setLikertAnswers(obj)
			{
				if(obj.checked)
				{
					document.ConsultQuestionForm.answer1.value='<%= (objPrevQuestion!=null && objPrevQuestion.getAnswer1()!=null?objPrevQuestion.getAnswer1():"") %>';
					document.ConsultQuestionForm.answer2.value='<%= (objPrevQuestion!=null && objPrevQuestion.getAnswer2()!=null?objPrevQuestion.getAnswer2():"") %>';
					document.ConsultQuestionForm.answer3.value='<%= (objPrevQuestion!=null && objPrevQuestion.getAnswer3()!=null?objPrevQuestion.getAnswer3():"") %>';
					document.ConsultQuestionForm.answer4.value='<%= (objPrevQuestion!=null && objPrevQuestion.getAnswer4()!=null?objPrevQuestion.getAnswer4():"") %>';
					document.ConsultQuestionForm.answer5.value='<%= (objPrevQuestion!=null && objPrevQuestion.getAnswer5()!=null?objPrevQuestion.getAnswer5():"") %>';
					
					disableAll();

				}
				else
				{
					document.ConsultQuestionForm.answer1.value='';
					document.ConsultQuestionForm.answer2.value='';
					document.ConsultQuestionForm.answer3.value='';
					document.ConsultQuestionForm.answer4.value='';
					document.ConsultQuestionForm.answer5.value='';
					

					enableAll();
				}
			}
			
			function checkOthers()
			{
			
				if (document.ConsultQuestionForm.needOtherRemark.checked)
				{
					document.ConsultQuestionForm.answer5.value='Others';
					document.ConsultQuestionForm.answer5.disabled=true;
				}
				else
				{
					document.ConsultQuestionForm.answer5.value='';
					document.ConsultQuestionForm.answer5.disabled=false;
				}
			
			}

		</script>

	</head>
	<body>

		<html:form action="/consultCreateQuestionAction.do">

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="1"
					cellspacing="1" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="2" >New Question </td>
 					</tr>

	<logic:present name="error" scope="request">
		<br><br>
 			<tr>
	    		<td colspan="2" align="center" valign="top" class="Mainheader">
					<bean:write name="error" scope="request" />
				</td>
			</tr>
 	</logic:present>
	
	<logic:present name="message" scope="request">
		<br><br>
 			<logic:iterate id="mList" name="message" scope="request">
				<tr>
	    			<td colspan="2" align="center" valign="top" class="Mainheader">
						<bean:write name="mList"/>
					</td>
				</tr>
			</logic:iterate>
 	</logic:present>


 										<tr>
											<td valign="top" class="heading_thread">Question <font color="red"> * </font></td>
											<td class="body_detail_text">
												<html:textarea property="question" cols="54" rows="6" styleClass="Text" onkeydown="fn_textCounter(this.form.question,this.form.numleft);" onkeyup="fn_textCounter(this.form.question,this.form.numleft);" onmouseover="fn_textCounter(this.form.question,this.form.numleft);" onmouseout="fn_textCounter(this.form.question,this.form.numleft);">
												</html:textarea>
												<html:hidden property="questionId"/>
												<html:hidden property="paperId"/>
												<br>
												<div align="left">
													<font color="#005BCC">
														No. of characters remaining: 
														<input name="numleft" type="text" size="10" size="5" maxlength="3" value="200" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none">
														<input name="exceed" id="exceed" type="hidden" size="60" value="" style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 0px solid #7F9DB9;border-right: 0px solid #7F9DB9;border-left: 0px solid #7F9DB9;border-top:0px solid #7F9DB9; color:red;text-decoration:none">
													</font>
												</div>
											</td>
										</tr>
										<tr>
											<td class="heading_thread">Answers 1 <font color="red">*</font></td>
											<td class="body_detail_text">
												<html:text property="answer1" styleClass="Text_box" size="55" maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="heading_thread">Answers 2 </td>
											<td class="body_detail_text">
												<html:text property="answer2" styleClass="Text_box" size="55" maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="heading_thread">Answers 3 </td>
											<td class="body_detail_text">
												<html:text property="answer3" styleClass="Text_box" size="55" maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="heading_thread">Answers 4 </td>
											<td class="body_detail_text">
												<html:text property="answer4" styleClass="Text_box" size="55" maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="heading_thread">Answers 5</td>
											<td class="body_detail_text">
												<html:text property="answer5" styleClass="Text_box" size="55" maxlength="100"></html:text>
											</td>
										</tr>
										<tr>
											<td class="heading_thread"> Multiple Answers</td>
											<td class="body_detail_text">
												<html:checkbox property="answerType" onclick="setMultiAnswer(this);"></html:checkbox>
											</td>
										</tr>
										<tr>
											<td class="heading_thread"> Need other remarks</td>
											<td class="body_detail_text">
												<html:checkbox property="needOtherRemark" onclick="checkOthers();"></html:checkbox>
											</td>
										</tr>
										<tr>
											<td class="heading_thread"> Likert Scale</td>
											<td class="body_detail_text">
												<html:checkbox property="likertScale" onclick="javascript:setLikertScale(this);"></html:checkbox>
												&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
												<%if(objPrevQuestion!=null && "1".equals(objPrevQuestion.getLikertScale()) )
												{
												%>
											
													<div id=prevScale style="display:none" class="heading_attributes" style="margin-top:-20px;margin-left:120px">
												
														<i>Use same scale as previous question 
														<input type=checkbox name="useSameLikert" value="<%= objPrevQuestion.getQuestionId()+""%>" onclick="javascript:setLikertAnswers(this);"  />
														<logic:present name="ConsultQuestionForm" property="useSameLikert">
															<logic:notEqual name="ConsultQuestionForm" property="useSameLikert" value="">
																<logic:notEqual name="ConsultQuestionForm" property="useSameLikert" value="0">
																	<script>
																		document.ConsultQuestionForm.useSameLikert.checked=true;
																	</script>
																</logic:notEqual>
															</logic:notEqual>
														</logic:present>

													</div>
						
												<%
												}
												else if (objPrevQuestion == null)
												{
												%>
													<html:hidden name="ConsultQuestionForm" property="useSameLikert" />
												<%
												}
												%>
												<script>
												if(document.ConsultQuestionForm.likertScale.checked)
												{
													if(document.getElementById('prevScale')!=null)
													  document.getElementById('prevScale').style.display='block';
													  
													document.ConsultQuestionForm.answerType.disabled=true;
												}
												if(document.ConsultQuestionForm.useSameLikert!=null && document.ConsultQuestionForm.useSameLikert.checked)
												{
												   disableAll();
												}

												</script>
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td height="33" align="left" valign="middle">
									<logic:notPresent name="ConsultQuestionForm" property="questionId">
										<a href="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultCreateQuestionAction.do','save');">
											<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt = "Save" ></a> 
									</logic:notPresent>
									<logic:present name="ConsultQuestionForm" property="questionId">
										<a href="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultViewModifyQuestionAction.do','save');">
											<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt = "Save"></a> 
									</logic:present>
									<a href="javascript:window.close()">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a> 
									<a href="javascript:fnClear()">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>
								</td>
								<html:hidden property="hiddenAction"/>
							</tr>


				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>

			<script language="javascript">
				fn_textCounter(this.ConsultQuestionForm.question,this.ConsultQuestionForm.numleft);
				checkOthers();
			</script>
		<logic:present name="refresh" scope="request">
			<script language="JavaScript">
				refreshParentWindow('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?paperId=<bean:write name="ConsultQuestionForm" property="paperId"/>&amp;hiddenAction=populate',true,0500);
				function refreshParentWindow(url,closeIt,delay)
				{ 
					opener.location.href = url;
					//opener.location.reload(true);
					if (closeIt == true)
						setTimeout('self.close()',delay);
				}
			</script>
		</logic:present>
		</html:form>
	</body>
</html>


