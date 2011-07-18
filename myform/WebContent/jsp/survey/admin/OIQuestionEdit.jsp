<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ include file="/jsp/common/iofmSimpleTop.jsp" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.utility.OIUtilities" %>

<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
<%
com.oifm.survey.OIBAQuestion objPrevQuestion = (com.oifm.survey.OIBAQuestion) request.getAttribute("objPrevQuestion");
String strSectionId = (String)request.getAttribute("strSectionId");
%>

<SCRIPT LANGUAGE="JavaScript">
<!--
function enableAll()
{
	document.QuestionForm.strAnswer1.disabled = false;
		document.QuestionForm.strAnswer2.disabled = false;
		document.QuestionForm.strAnswer3.disabled = false;
		document.QuestionForm.strAnswer4.disabled = false;
		document.QuestionForm.strAnswer5.disabled = false;
		document.QuestionForm.strAnswer6.disabled = false;
		document.QuestionForm.strAnswer7.disabled = false;
		document.QuestionForm.strAnswer8.disabled = false;
		document.QuestionForm.strAnswer9.disabled = false;
		document.QuestionForm.strAnswer10.disabled = false;
		document.QuestionForm.strQuestionType.disabled = false;
		document.QuestionForm.strOtherRemarks.disabled = false;
}
function disableAll()
{
	document.QuestionForm.strAnswer1.disabled = true;
		document.QuestionForm.strAnswer2.disabled = true;
		document.QuestionForm.strAnswer3.disabled = true;
		document.QuestionForm.strAnswer4.disabled = true;
		document.QuestionForm.strAnswer5.disabled = true;
		document.QuestionForm.strAnswer6.disabled = true;
		document.QuestionForm.strAnswer7.disabled = true;
		document.QuestionForm.strAnswer8.disabled = true;
		document.QuestionForm.strAnswer9.disabled = true;
		document.QuestionForm.strAnswer10.disabled = true;
		document.QuestionForm.strQuestionType.disabled = true;
		document.QuestionForm.strOtherRemarks.disabled = true;
}
function submitQuestionForm(submitUrl, actionName) {
	var frm = document.QuestionForm;
	var flag = true;
	frm.action = '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
	frm.hiddenAction.value = actionName;
	if(actionName == "<%= OISurveyConstants.QUESTION_SAVE %>") {
		if(flag) flag = checkSelectedIndex(frm.strSectionId, "Section");
		if(flag) flag = checkBlank(frm.strQuestion, "Question");
		if(flag) flag = txtAreaMaxLen(frm.strQuestion, 200, "Question");
		trim(frm.strInstruction);
		if(flag) flag = txtAreaMaxLen(frm.strInstruction, 200, "Instruction");
		trim(frm.strAnswer1);
		trim(frm.strAnswer2);
		trim(frm.strAnswer3);
		trim(frm.strAnswer4);
		trim(frm.strAnswer5);
		trim(frm.strAnswer6);
		trim(frm.strAnswer7);
		trim(frm.strAnswer8);
		trim(frm.strAnswer9);
		trim(frm.strAnswer10);
		if(flag) {
			flag = (frm.strAnswer1.value != "" || frm.strAnswer2.value != "" || frm.strAnswer3.value != "" || frm.strAnswer4.value != "" || frm.strAnswer5.value != "" || frm.strAnswer6.value != "" || frm.strAnswer7.value != "" || frm.strAnswer8.value != "" || frm.strAnswer9.value != "" || frm.strAnswer10.value != "");
			if(!flag) alert('<%= OIDBRegistry.getValues("OI.SU.QES.ANS.CHECK") %>');
		}
	} 
	if(flag){
		enableAll();
		frm.submit();
	}
	else return;
}

function deleteQuestionForm(submitUrl, actionName) {
	var flag = confirm('<%= OIDBRegistry.getValues("OI.SU.QES.DELETE.CONFIRM") %>');
	if(flag) submitQuestionForm(submitUrl, actionName);
	else return false;
}

function refreshParentScreenClose() {
	var frm = document.QuestionForm;
//	window.opener.refresh();
	window.opener.location.href = "<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_QUESTION_DO %>?strSurveyId="+frm.strSurveyId.value;
	self.close();
}

function refreshParentScreen() {
	var frm = document.QuestionForm;
//	window.opener.refresh();
	window.opener.location.href = "<bean:message key="OIFM.contextroot" /><%= OISurveyConstants.SURVEY_QUESTION_DO %>?strSurveyId="+frm.strSurveyId.value;
}

function clearFormData() {
	document.QuestionForm.reset();
}

function setLikertScale(obj)
{
	if (obj.value=='L') {
		document.QuestionForm.strAnswer10.value = '';
		document.QuestionForm.strAnswer10.disabled = false;
		
		document.QuestionForm.strOtherRemarks.disabled = true;
		document.QuestionForm.strOtherRemarks.checked = false;
	}
	else {
		if (obj.value == 'T') {
			document.QuestionForm.strAnswer10.value = '';
			document.QuestionForm.strAnswer10.disabled = false;
		}
		else if (document.QuestionForm.strOtherRemarks.checked) {
			document.QuestionForm.strAnswer10.value = 'Others';
			document.QuestionForm.strAnswer10.disabled = true;
		}
			
		document.QuestionForm.strOtherRemarks.disabled = false;
	}
	
	
	if(obj.value=='L' && document.getElementById('prevScale')!=null)
	{
		document.getElementById('prevScale').style.display='block';
	}
	
	else if(this.value!='L' && document.getElementById('prevScale')!=null)
	{
		document.QuestionForm.strUseSameLikert.checked=false;document.getElementById('prevScale').style.display='none';
	}
}

function setLikertAnswers(obj)
{
	if(obj.checked)
	{
			
		document.QuestionForm.strAnswer1.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer1()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer1()):"") %>';
		document.QuestionForm.strAnswer2.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer2()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer2()):"") %>';
		document.QuestionForm.strAnswer3.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer3()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer3()):"") %>';
		document.QuestionForm.strAnswer4.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer4()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer4()):"") %>';
		document.QuestionForm.strAnswer5.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer5()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer5()):"") %>';
		document.QuestionForm.strAnswer6.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer6()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer6()):"") %>';
		document.QuestionForm.strAnswer7.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer7()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer7()):"") %>';
		document.QuestionForm.strAnswer7.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer8()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer8()):"") %>';
		document.QuestionForm.strAnswer9.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer9()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer9()):"") %>';
		document.QuestionForm.strAnswer10.value='<%= (objPrevQuestion!=null && objPrevQuestion.getStrAnswer10()!=null?OIUtilities.addSingleQuoteJS(objPrevQuestion.getStrAnswer10()):"") %>';
		disableAll();

	}
	else
	{
		document.QuestionForm.strAnswer1.value='';
		document.QuestionForm.strAnswer2.value='';
		document.QuestionForm.strAnswer3.value='';
		document.QuestionForm.strAnswer4.value='';
		document.QuestionForm.strAnswer5.value='';
		document.QuestionForm.strAnswer6.value='';
		document.QuestionForm.strAnswer7.value='';
		document.QuestionForm.strAnswer8.value='';
		document.QuestionForm.strAnswer9.value='';
		document.QuestionForm.strAnswer10.value='';

		enableAll();
	}
}

function checkOthers() {
	if (document.QuestionForm.strOtherRemarks.checked)
	{
		if (document.QuestionForm.strQuestionType.value != 'T') {
			document.QuestionForm.strAnswer10.value = 'Others';
			document.QuestionForm.strAnswer10.disabled = true;
		}
	}
	else
	{
		document.QuestionForm.strAnswer10.value = '';
		document.QuestionForm.strAnswer10.disabled = false;
	}
}
//-->
</script>


<html:form action="/SurveyQuestion.do" method="post" >

<html:hidden property="hiddenAction" />
<html:hidden property="strSurveyId" />
<html:hidden property="strQuestionId" />

<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />
<bean:define id="QuestionForm" name="QuestionForm" type="com.oifm.survey.OIFormQuestion" />

<table width="98%" border="0" cellpadding="2"
		cellspacing="2" bgcolor="white">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
					<td colspan="2" > Create / Modify Survey Question </td>
					</tr>
 
		<logic:present name="error" scope="request" >
				<tr>
					<td width="100%" class="body_detail_text" colspan="2">
					<b><bean:message name="error" scope="request"/></b>
					</td>
				</tr>
		</logic:present>

<logic:notPresent name="error" scope="request" >
	<logic:match name="QuestionForm" property="hiddenAction" value="<%= OISurveyConstants.QUESTION_SAVE %>">
		<logic:greaterThan name="QuestionForm" property="strQuestionId" value="0">
		<SCRIPT LANGUAGE="JavaScript">
			refreshParentScreenClose();
		</SCRIPT>
		</logic:greaterThan>
		<logic:lessThan name="QuestionForm" property="strQuestionId" value="1">
		<SCRIPT LANGUAGE="JavaScript">
			refreshParentScreen();
		</SCRIPT>
		</logic:lessThan>
	</logic:match>
	<logic:match name="QuestionForm" property="hiddenAction" value="<%= OISurveyConstants.QUESTION_DELETE %>">
		<SCRIPT LANGUAGE="JavaScript">
			refreshParentScreenClose();
		</SCRIPT>
	</logic:match>
</logic:notPresent>

<logic:present name="message" scope="request">
		<logic:iterate id="msg" name="message" scope="request">
		<tr>
    		<td width="100%" align="center" valign="top" class="sub_head" colspan="2">
				<b><bean:message key="msg"/></b>
			</td>
		</tr>
		</logic:iterate>
</logic:present>
		<tr>
			<td class="heading_attributes">Section</td>
			<td width="82%" class="body_detail_text">
			<html:select property="strSectionId" styleClass="Text_box" >
<logic:present name="SectionList" scope="request" >
				<html:options collection="SectionList" property="strSectionId"
					labelProperty="strSectionName" />
</logic:present>
				</html:select>
			</td>
		</tr>
		<tr>
			<td class="heading_attributes"  valign="top">Question <B><font color="#FF0000">*</font></B></td>
			<td class="body_detail_text">
				<html:textarea styleClass="Text" property="strQuestion" cols="60" rows="4"  onkeydown="fn_textCounter(document.QuestionForm.strQuestion, document.QuestionForm.numleft, 200);" onkeyup="fn_textCounter(document.QuestionForm.strQuestion, document.QuestionForm.numleft, 200);" onmouseover="fn_textCounter(document.QuestionForm.strQuestion, document.QuestionForm.numleft, 200);" onmouseout="fn_textCounter(document.QuestionForm.strQuestion, document.QuestionForm.numleft, 200);"/>
				<div align="left">
					<font color="#005BCC" >
						No. of characters remaining: 
						<input name="numleft" type="text" class="Text_box" size="5" value="200" disabled style="taInfo">
					</font>
				</div>
			</td>
		</tr>
            <tr>
              <td class="heading_attributes"  valign="top">Instruction</td>
              <td class="body_detail_text">
				<html:textarea styleClass="Text" property="strInstruction" cols="60" rows="4" onkeydown="fn_textCounter(document.QuestionForm.strInstruction, document.QuestionForm.numleft1, 200);" onkeyup="fn_textCounter(document.QuestionForm.strInstruction, document.QuestionForm.numleft1, 200);" onmouseover="fn_textCounter(document.QuestionForm.strInstruction, document.QuestionForm.numleft1, 200);" onmouseout="fn_textCounter(document.QuestionForm.strInstruction, document.QuestionForm.numleft1, 200);"/>
				<div align="left">
					<font color="#005BCC" >
						No. of characters remaining: 
						<input name="numleft1" type="text" class="Text_box" size="5" value="200" disabled style="taInfo">
					</font>
				</div>
			  </td>
            </tr>
            <tr>
				<td width="18%" class="heading_attributes">Question Type </td>
				<td class="body_detail_text">
					<html:select property="strQuestionType" styleClass="Text_box" onchange="javascript:setLikertScale(this);">
					<html:options name="QuestionTypeIds" labelName="QuestionTypeText" />
					</html:select>
					
						<%
						if(objPrevQuestion!=null && "L".equals(objPrevQuestion.getStrQuestionType()) &&  objPrevQuestion.getStrSectionId().equals(strSectionId))
						{
						%>
							<div id=prevScale style="display:none" class="heading_attributes" style="margin-top:-20px;margin-left:120px">
					
							&nbsp;&nbsp;&nbsp;<input type=checkbox name="strUseSameLikert" value="<%= objPrevQuestion.getStrQuestionId() %>" onclick="javascript:setLikertAnswers(this);"/>Use same scale as previous question
							<logic:present name="QuestionForm" property="strUseSameLikert">
								<logic:notEqual name="QuestionForm" property="strUseSameLikert" value="">
									<logic:notEqual name="QuestionForm" property="strUseSameLikert" value="0">
										<script>
											document.QuestionForm.strUseSameLikert.checked=true;
										</script>
									</logic:notEqual>
								</logic:notEqual>
							</logic:present>
								
							</div>
						
						<%
						}
						else if ("L".equalsIgnoreCase(QuestionForm.getStrQuestionType())) //if (objPrevQuestion == null)
						{
							System.out.println();
						%>
							<html:hidden property="strUseSameLikert" />
						<%
						}
						%>
					
					<script>
					if(document.QuestionForm.strQuestionType.value=='L')
					{
						if(document.getElementById('prevScale')!=null)
						  document.getElementById('prevScale').style.display='block';
					}
					</script>
			  </td>
				</td>
            </tr>
            <tr>
				<td width="18%" class="heading_attributes">Answer 1 </td>
				<td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer1" size="60" maxlength="200"	/>
				</td>
            </tr>
            <tr>
				<td width="18%" class="heading_attributes">Answer 2 </td>
				<td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer2" size="60" maxlength="200"	/>
				</td>
            </tr>
            <tr>
				<td width="18%" class="heading_attributes">Answer 3 </td>
				<td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer3" size="60" maxlength="200"	/>
				</td>
            </tr>
            <tr>
				<td width="18%" class="heading_attributes">Answer 4 </td>
				<td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer4" size="60" maxlength="200"	/>
				</td>
            </tr>
            <tr>
				<td width="18%" class="heading_attributes">Answer 5 </td>
				<td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer5" size="60" maxlength="200"	/>
				</td>
            </tr>
            <tr>
              <td width="18%" class="heading_attributes">Answer 6 </td>
              <td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer6" size="60" maxlength="200"	/>
              </td>
            </tr>
            <tr>
              <td width="18%" class="heading_attributes">Answer 7 </td>
              <td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer7" size="60" maxlength="200"	/>
              </td>
            </tr>
            <tr>
              <td width="18%" class="heading_attributes">Answer 8 </td>
              <td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer8" size="60" maxlength="200"	/>
              </td>
            </tr>
            <tr>
              <td width="18%" class="heading_attributes">Answer 9 </td>
              <td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer9" size="60" maxlength="200"	/>
              </td>
            </tr>
            <tr>
              <td width="18%" class="heading_attributes">Answer 10 </td>
              <td class="body_detail_text">
                  <html:text styleClass="Text_box" property="strAnswer10" size="60" maxlength="200"	/>
              </td>
            </tr>
			<tr>
              <td class="body_detail_text">&nbsp;</td>
              <td class="heading_attributes">
			  <html:checkbox property="strOtherRemarks" value="Y" onclick="checkOthers()"/>Need other remarks
			  <html:checkbox property="strOptional" value="O"/>Optional
			  </td>
            </tr>
<logic:lessThan name="QuestionForm" property="strQuestionId" value="0">
			<tr>
              <td class="body_detail_text">&nbsp;</td>
              <td class="heading_attributes">
			  <html:checkbox property="strMakeTemplate" value="Y"/>Use this format for next question
			  </td>
            </tr>
</logic:lessThan>
            <tr>
              <td class="body_detail_text">&nbsp;</td>
              <td class="body_detail_text">&nbsp;</td>
			</tr>
     <tr>
		<td height="35" align="left" colspan="2">
<logic:match name="isSurveyOwnerDivision" value="true" >
		<a href="JavaScript:submitQuestionForm('<%= OISurveyConstants.SURVEY_QUESTION_DO %>',
						'<%= OISurveyConstants.QUESTION_SAVE %>');"><img src='<bean:message key="OIFM.docroot" />/images/but_save.gif' border="0" alt = "Save"></a>
		<a href="#" onClick="javascript:clearFormData()" ><img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif'  border="0" alt = "Reset"></a>&nbsp;
	<logic:greaterThan name="QuestionForm" property="strQuestionId" value="0">
		<logic:match name="isCurrentlyValid" value="true" scope="request">
		<a href="#" onClick="alert('You should not delete question, when survey period is valid')" ><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif'  border="0" alt = "Delete"></a>&nbsp;
		</logic:match>
		<logic:match name="isCurrentlyValid" value="false" scope="request">
		<a href="#" onClick="javascript:deleteQuestionForm(
						'<%= OISurveyConstants.SURVEY_QUESTION_DO %>',
						'<%= OISurveyConstants.QUESTION_DELETE %>')" ><img src='<bean:message key="OIFM.docroot" />/images/but_delete.gif'  border="0" alt = "Delete"></a>&nbsp;
		</logic:match>
	</logic:greaterThan>
</logic:match>
		<a href="JavaScript:window.close();"><img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt = "Cancel"></a>&nbsp;

		</td>
	</tr>
    
				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
<script>
if(document.QuestionForm.strUseSameLikert!=null && document.QuestionForm.strUseSameLikert.checked)
{
   disableAll();
}

checkOthers();
</script>

<logic:match name="isSurveyOwnerDivision" value="false" >
<SCRIPT LANGUAGE="JavaScript">
<!--
	disableElements(document.QuestionForm);
//-->
</SCRIPT>
</logic:match>
<logic:notPresent name="error" scope="request" >
	<logic:match name="QuestionForm" property="hiddenAction" value="<%= OISurveyConstants.QUESTION_SAVE %>">
		<logic:lessThan name="QuestionForm" property="strQuestionId" value="1">
		<SCRIPT LANGUAGE="JavaScript">
			alert('<%= OIDBRegistry.getValues("OI.SU.QES.SAVE.CONFIRM") %>');
		</SCRIPT>
		</logic:lessThan>
	</logic:match>
</logic:notPresent>

</html:form>
