<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript">
	var strDocroot ='<bean:message key="OIFM.contextroot" />';
	var maxlimit = 4000;
	function fn_Add_Comment()
	{
		cmdObj=document.getElementById("dropin");
		//window.alert(cmdObj);
		if(cmdObj.style.visibility=="hidden")
			cmdObj.style.visibility="visible";
		else
			cmdObj.style.visibility="hidden"	
	}
	
	
	
	function fnClear()
	{
		document.ConsultPresentPaperForm.reset();
		fn_textCounter(this.ConsultPresentPaperForm.feedBack1,this.ConsultPresentPaperForm.numleft);
	}

	function fnSubmit(actionName,ac)
	{
			try
				{
					for(var i=0;i<document.ConsultPresentPaperForm.elements.length;i++){
						var elementName= document.ConsultPresentPaperForm.elements[i].id;
						var lastName = elementName.substr(elementName.length-2,elementName.length)
						if(lastName=="A5"){
							if(document.ConsultPresentPaperForm.elements[i].checked &&
							document.ConsultPresentPaperForm.elements[i+1].value==""){
							alert("Please enter the Other remarks");
							document.ConsultPresentPaperForm.elements[i+1].focus();
							return false;

							}
			
					}
			}

			}catch(err){
			alert("error");
			}
	
	
		if (document.ConsultPresentPaperForm.feedBack1.value.length>4000)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.WEB.OPEN.FEEDBACK.CHECK") %>');
			return;
		}
		document.ConsultPresentPaperForm.hiddenAction.value=ac;
		document.ConsultPresentPaperForm.action=actionName;
		document.ConsultPresentPaperForm.submit();
	}
	function fnSubmit1(actionName,ac)
	{
			try
				{
					for(var i=0;i<document.ConsultPresentPaperForm.elements.length;i++){
						var elementName= document.ConsultPresentPaperForm.elements[i].id;
						var lastName = elementName.substr(elementName.length-2,elementName.length)
						if(lastName=="A5"){
							if(document.ConsultPresentPaperForm.elements[i].checked &&
							document.ConsultPresentPaperForm.elements[i+1].value==""){
							alert("Please enter the Other remarks");
							document.ConsultPresentPaperForm.elements[i+1].focus();
							return false;

							}
			
					}
			}

			}catch(err){
			alert("error");
			}
		if (document.ConsultPresentPaperForm.feedBack1.value.length>4000)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.WEB.OPEN.FEEDBACK.CHECK") %>');
			document.ConsultPresentPaperForm.feedBack1.focus();
			return;
		}
		//var re = /\n/;
		//document.ConsultPresentPaperForm.feedBack1.value = document.ConsultPresentPaperForm.feedBack1.value.replace(/\s+/,"");
		fn_textCounter(this.ConsultPresentPaperForm.feedBack1,this.ConsultPresentPaperForm.numleft);
		if (Trim(document.ConsultPresentPaperForm.feedBack1.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.WEB.OPEN.FEEDBACK.EMPTY") %>');
			document.ConsultPresentPaperForm.feedBack1.focus();
			return;
		}
		if (window.confirm('<%= OIDBRegistry.getValues("OI.CONS.WEB.OPEN.FEEDBACK.CONFIRM") %>')==false)
		{
			return;
		}
		else
		{
			document.ConsultPresentPaperForm.hiddenAction.value=ac;
			document.ConsultPresentPaperForm.action=actionName;
			document.ConsultPresentPaperForm.submit();
		}
	}
	function fnSubmit2(actionName,ac)
	{

				try
				{
					for(var i=0;i<document.ConsultPresentPaperForm.elements.length;i++){
						var elementName= document.ConsultPresentPaperForm.elements[i].id;
						var lastName = elementName.substr(elementName.length-2,elementName.length)
						if(lastName=="A5"){
							if(document.ConsultPresentPaperForm.elements[i].checked &&
							document.ConsultPresentPaperForm.elements[i+1].value==""){
							alert("Please enter the Other remarks");
							document.ConsultPresentPaperForm.elements[i+1].focus();
							return false;

							}
			
					}
			}

			}catch(err){
			alert("error");
			}

		document.ConsultPresentPaperForm.hiddenAction.value=ac;
		document.ConsultPresentPaperForm.action=actionName;
		document.ConsultPresentPaperForm.submit();
	}

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
	function fnAlertSubmit(actionName,hiddenAction,id,module)
	{
		document.tempForm.iD.value=id;
		document.tempForm.module.value=module;
		document.tempForm.hiddenAction.value=hiddenAction;
		document.tempForm.action=actionName;
		document.tempForm.submit();
	}

	function fnAlertEmail(){
		 var strUrl = "/AlertFriendAction.do?hiddenAction=populate&module=C&iD="+<bean:write name="ConsultPresentPaperForm" property="paperId"/>
		 //help_window  = window.open(strDocroot+strUrl,'AlertFriend','width=900,height=260,left=0,top=0,resizable=no,scrollbars=yes');
		 window.showModalDialog(strDocroot+strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
		 //help_window.focus();
	}
</script>


	<table width="857" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td class="Orange_fade">View Consultation Paper  </td>
          </tr>
        </table>
        <table width="857" border="0" cellspacing="0" cellpadding="0">
		<html:form action="/webConsultOpenPaperAction.do">
          <tr>
            <td valign="top" class="orange"><div align="justify" class="Highlight_body"> 
              <blockquote>
                <p><br>
                  <bean:write name="ConsultPresentPaperForm" property="categoryName"/></a>
					<html:hidden name="ConsultPresentPaperForm" property="paperId"/>
                  <br>
                  <br>
                  </p>
                </blockquote>
            </div></td>
            </tr>
          <tr>
            <td class="Grey_fade">&nbsp;</td>
          </tr>
	      <tr>
            <td align="left" valign="top" bgcolor="#f7f8fc"><table width="98%"  border="0" align="center" cellpadding="0" cellspacing="0">
              <logic:equal name="ConsultPresentPaperForm" property="status" value="S">
          <tr>
	          <td width="98%" class="Heading_Thread">
		          <bean:message key="OI.CONS.SUMBITED"/>
		       </td>
	      </tr>
      	</logic:equal>
              <tr>
                <td >
                <table width="98%"  border="0" cellspacing="0" cellpadding="0" align = "center" class="Box">
                  <tr>
                    <td colspan="2" valign="top" class="Table_head">
                     Current Consultation Paper </td>
                    <td width="5%" class="Table_head"><a href="#" onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');" class="Table_head">Back</a></td>
                    <td width="3%" class="Table_head">
                    	<logic:present name="ConsultPresentPaperForm" property="security">
							<logic:equal name="ConsultPresentPaperForm" property="security" value="U">
								<a href="#" onclick="javascript:fnAlertEmail();"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Alert a friend on this paper" border="0"></a>
							</logic:equal>
							<logic:equal name="ConsultPresentPaperForm" property="security" value="R">
								<a href="#" onclick="javascript:alert('<%= OIDBRegistry.getValues("OI.CONS.OPEN.ALERTFRIEND") %>');" ><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Alert a friend on this paper" border="0"></a>
							</logic:equal>
						</logic:present>
						<logic:notPresent name="ConsultPresentPaperForm" property="security">
							<a href="#" onclick="javascript:fnAlertEmail();"><img src='<bean:message key="OIFM.docroot" />/images/Icons/icon_email.gif' alt="Alert a friend on this paper" border="0"></a>
						</logic:notPresent>
					</td>
                    <td width="3%" class="Table_head"><a href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?hiddenAction=print&paperId=<bean:write name="ConsultPresentPaperForm" property="paperId"/>','mywindow','menubar=no,toolbar=no,copyhistory=no,location=no,directories=no,resizable=1,scrollbars=yes');"><img src="<bean:message key="OIFM.docroot" />/images/Icons/icon_printer.gif"  border="0" alt="Print"></a></td>
                  </tr>
            <tr>
                <td width="16%" valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Paper Title</td>
                <td colspan="4" valign="top" class="body_extract_text">
					<bean:write name="ConsultPresentPaperForm" property="title"/>
					<html:hidden name="ConsultPresentPaperForm" property="title"/>
                </td>
                </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" valign="top" class="Heading_Topic"><hr style="height:1px; color=#E4E4E9"></td>
                </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Target Audience </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPresentPaperForm" property="targetAudiance"/>
				 </td>
                </tr>
			  <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Contact Person </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPresentPaperForm" property="contactPerson"/>
				 </td>
                </tr>
			  <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Phone </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPresentPaperForm" property="phone"/>
				 </td>
                </tr>
			  <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Email Address </td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPresentPaperForm" property="emailAddress"/>
				 </td>
                </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" valign="top" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Period</td>
                <td colspan="4" valign="top" class="body_extract_text">
				<bean:write name="ConsultPresentPaperForm" property="fromDt"/>
				 to 
				<bean:write name="ConsultPresentPaperForm" property="toDt"/>
                </td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" valign="top" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Description </td>
                <td colspan="4" valign="top" class="body_extract_text"><p align="justify"><bean:write name="ConsultPresentPaperForm" property="description" filter="false" /> </p></td>
              </tr>
              <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              	<logic:present name="ConsultPresentPaperForm" property="attachedFile">
              	<tr>
	              <td bgcolor="#DEEAF3" class="Heading_Topic">View Attachment </td>
	              <td colspan="4" class="body_extract_text">	<a href='<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?attachedFileName=<bean:write name="ConsultPresentPaperForm" property="attachedFile"/>&paperId=<bean:write name="ConsultPresentPaperForm" property="paperId"/>&hiddenAction=downLoad' />
					Consultation Paper Attachment
				</a></td>
				</tr>
				 <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
	           </logic:present>
        <% 
			boolean likertBlock=false;
			int likertCount=0;
		%>     
              <tr>
                <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Questions </td>
                <td colspan="4" valign="top" class="Heading_Thread" align = "left">
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
							<td align = "left" height="25" width="1%" class="Heading_Thread"><bean:write name="questionListing" property="questionNo" /></td>
							<td align = "left" height="25" colspan="2"  class="Heading_Thread">
								<strong>
									<bean:write name="questionListing" property="question" />
								</strong>
							</td>
						</tr>
						<logic:present name="questionListing" property="answerType">
							<logic:equal name="questionListing" property="answerType" value="1">
								<%@ include file="QuestionType1.incl" %>
							</logic:equal>
							<logic:notEqual name="questionListing" property="answerType" value="1">
								<logic:notEqual name="questionListing" property="likertScale" value="1">
									<%@ include file="QuestionType2.incl" %>
								</logic:notEqual>
								<logic:equal name="questionListing" property="likertScale" value="1">
									<logic:notEqual name="questionListing" property="useSameLikert" value="1">
										<%@ include file="QuestionTypeLik.incl" %>
									</logic:notEqual>
									
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
              <tr>
                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
	            <td valign="top" bgcolor="#DEEAF3" class="Heading_Topic">Feedback </td>
	            <td colspan="4" valign="top" class="body_extract_text">
	         <logic:equal name="ConsultPresentPaperForm" property="status" value="S">
				<html:textarea name="ConsultPresentPaperForm" property="feedBack1" cols="85" rows="15" readonly="true" ></html:textarea >
			</logic:equal>
			<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
				<html:textarea name="ConsultPresentPaperForm" property="feedBack1" cols="85" rows="15" onkeydown="fn_textCounter(this.form.feedBack1,this.form.numleft);" onkeyup="fn_textCounter(this.form.feedBack1,this.form.numleft);" onmouseover="fn_textCounter(this.form.feedBack1,this.form.numleft);" onmouseout="fn_textCounter(this.form.feedBack1,this.form.numleft);" ></html:textarea>
				<br>
				<div align="left">
					<font color="#005BCC">
						No. of characters remaining: 
						<input name="numleft" type="text" size="10" size="5" maxlength="3" value="4000" disabled style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 1px solid #7F9DB9;border-right: 1px solid #7F9DB9;border-left: 1px solid #7F9DB9;border-top:1px solid #7F9DB9; color:#018BAB;text-decoration:none" class="Text_box">
						<input name="exceed" id="exceed" type="hidden" size="85" value="" style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 0px solid #7F9DB9;border-right: 0px solid #7F9DB9;border-left: 0px solid #7F9DB9;border-top:0px solid #7F9DB9; color:red;text-decoration:none" class="Text_box">
					</font>
				</div>
				<em><b>Note: </b>Please give your feedback on the consultation papers in the space provided (max 4000 characters).</em> 
			</logic:notEqual>
	                </td>
	            </tr>
	            <tr>
	                <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
	                <td colspan="4" class="body_extract_text"><hr style="height:1px; color=#E4E4E9"></td>
              </tr>
              <tr>
              <td bgcolor="#DEEAF3" class="Heading_Topic">&nbsp;</td>
			<td >
				<logic:notEqual name="ConsultPresentPaperForm" property="status" value="S">
					<a href="#" onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do','submit');">
						<img src='<bean:message key="OIFM.docroot" />/images/but_submit.gif'  border="0" alt="Submit"></a>
					<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do','saveAsDraft');">
						<img src='<bean:message key="OIFM.docroot" />/images/but_Save_Draft.gif' border="0" alt="Save"></a>
					<a href="#" onclick="javascript:fnClear()">
						<img src='<bean:message key="OIFM.docroot" />/images/but_reset.gif' border="0" alt="Reset"></a>
					<a href="#" onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');">
						<img src='<bean:message key="OIFM.docroot" />/images/but_Cancel.gif' border="0" alt="Cancel"></a>
				</logic:notEqual>
				<logic:equal name="ConsultPresentPaperForm" property="status" value="S">
				<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/webConsultListingAction.do','populate');" >
						<img src='<bean:message key="OIFM.docroot" />/images/but_back.gif' border="0" alt="Back"></a>
				</logic:equal>
			</td>
<html:hidden name="ConsultPresentPaperForm" property="hiddenAction"/>
</html:form>
<jsp:include page="/jsp/common/oifm_footer.jsp" flush="true" />
<form name="tempForm" method="post">
	<input type="hidden" name="iD">
	<input type="hidden" name="module">
	<input type="hidden" name="hiddenAction">
</form>
<script language="javascript">
function fnchkOthers(obj){
try{ 	 
	obj.value="";
	}
	catch(err){
	 
	}
}

function fnCheckBoxOthers(thisObj,obj){
try{ 	 
	if(!thisObj.checked){
		obj.value="";
	}
	}
	catch(err){
	 
	}
}

</script>