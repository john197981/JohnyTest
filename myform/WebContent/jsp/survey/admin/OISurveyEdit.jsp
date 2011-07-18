<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>
<%@ page import="com.oifm.utility.OIUtilities" %>
<%@ page import="com.oifm.survey.OISurveyConstants" %>
<%@ page import="com.oifm.common.OIApplConstants" %>
 
<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
<script language="Javascript" src='<bean:message key="OIFM.docroot" />/js/RTFEditorSurvey.js'></script>

<script language="JavaScript" type="text/JavaScript">
	var docRoot = '<bean:message key="OIFM.docroot" />';
	var win_ie_ver = parseFloat(navigator.appVersion.split("MSIE")[1]);
	var aryCodes = new Array();
	var aryIcons = new Array();

	if (win_ie_ver < 5.5)
	{
		document.write("<scr"+"ipt>function editor_generate() { return false; }</scr"+"ipt>"); 
	}
</script>

 	<SCRIPT LANGUAGE="JavaScript">
		<!--
		var	currDate = "<%= com.oifm.utility.OIUtilities.getCurrentDateTime("dd-MMM-yyyy")%>";
		
		function MOEMailCheck(mailAddress)
		{
			var moe="moe.gov.sg"
			if (mailAddress.indexOf(moe)>=0){
			   alert("E-mail ID under MOE domain is not allowed")
			   return false;
			}
			else
			{		// Not MOE E-mail ID
				return true;
			}
		}

		/** This function is used to load select users window */    
		function fnSelectGroups(){
			var strUrl = '/SelectGroups.do?hiddenAction=searchallgroup&module=S&id='+document.SurveyForm.id.value;
			help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectGroups','width=640,height=600,left=0,top=0,resizable=yes,scrollbars=yes');
			help_window.focus();
		}

		function validateForm()
		{
			var frm = document.SurveyForm;
			var flag = true;
			
				 
				
				if(flag) {
					flag = checkBlank(frm.strSurveyName, "Survey Name");
					if (!flag) frm.strSurveyName.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strDescription, "Description");
					if (!flag) frm.strDescription.focus();
				}
				//trim(frm.strDescription);
				//editor_setHTML('taContentId',trim(editor_getHTML('taContentId')));
				if(flag) {
					flag = txtAreaMaxLen(frm.strDescription, 2000, "Description");
					//if (!flag) frm.strDescription.focus();
				}
				//trim(frm.strInstruction);
				//editor_setHTML('taContentId1',trim(editor_getHTML('taContentId1')));
				if(flag) {
					flag = txtAreaMaxLen(frm.strInstruction, 1000, "Instruction");
					//if (!flag) frm.strInstruction.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strCompletionTime, "Time Taken");
					if (!flag) frm.strCompletionTime.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strContactPerson, "Contact Person");
					if (!flag) frm.strContactPerson.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strPhone, "Telephone");
					if (!flag) frm.strPhone.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strEmailAddress, "Email Address");
					if (!flag) frm.strEmailAddress.focus();
				}
				if(flag) {
					flag = echeck(frm.strEmailAddress.value)
					if (!flag) frm.strEmailAddress.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strFromDate, "From Date");
					if (!flag) frm.strFromDate.focus();
				}
				if(flag) {
					flag = checkBlank(frm.strToDate, "To Date");
					if (!flag) frm.strToDate.focus();
				}
				if(frm.strSurveyId.value == "") 
				{
					if(flag) {
						flag = compareTwoDates(currDate, frm.strFromDate.value, "Current Date", "From Date", false);
						if (!flag) frm.strFromDate.focus();
					}
					if(flag) {
						flag = compareTwoDates(currDate, frm.strToDate.value, "Current Date", "To Date", false);
						if (!flag) frm.strToDate.focus();
					}
				} 
				else 
				{
					if(flag) {
						flag = compareTwoDates(currDate, frm.strFromDate.value, currDate+" (earlier survey start date)", "From Date", false);
						if (!flag) frm.strFromDate.focus();
					}
				}
				if(flag) {
					flag = compareTwoDates(frm.strFromDate.value, frm.strToDate.value, "From Date", "To Date", true);
					if (!flag) frm.strFromDate.focus();
				}
				if(flag)
				{
					var temp = trim(frm.strEmailDate);
					var temp2 = frm.strEmailDate.value
					if (temp2.length > 0)
					{
						flag = compareTwoDates2(frm.strFromDate.value, frm.strEmailDate.value, "From Date", "Email invitation date", false);
						flag = flag && compareTwoDates2(frm.strEmailDate.value, frm.strToDate.value, "Email invitation date", "To date", false);
					}
					if (!flag) {
						alert ('Email invitation date should be between start date and end date');
						frm.strEmailDate.focus();
					}
				}
				
				
				// Place holder..
				// Added by K.K.Kumaresan.
				 if(flag) 
				 {
				 	if(document.SurveyForm.outsideMOEChecked[0].checked)	// if 'Outside MOE' is clicked
				 	{
				 		flag = checkBlank(frm.externalMailAddress, "valid E-mail address");
				 		if (!flag) 
						{
							frm.externalMailAddress.focus();		// mail address is empty..
						}
						else if(txtAreaMaxLen(frm.externalMailAddress, 500, " E-mail address ")) // if mail address is less than 500
						{										// mail address is not empty..
							var mailArray=new Array();
							var temp=frm.externalMailAddress.value;
							mailArray=temp.split(';');
							var total=mailArray.length;
							var lastAddress=mailArray[total-1];
							
							for (i = 0; i < total; i++)
							{
								//alert("i is"+i+"mailArray  "+mailArray[i]);
								if(i==(total-1))  // for the last entry
								{	
									if(lastAddress=="") // if last address is empty, pass the validation.
									{
										flag=true;		
									}	
									else 
									{
										flag=echeck(lastAddress);
										if(flag) flag=MOEMailCheck(lastAddress);
									}	
								}
								else
								{
									flag=echeck(mailArray[i]);
									if(flag) flag=MOEMailCheck(mailArray[i]);
								}
								
								if (!flag) break;		// Invalid address
							} // for loop
							
						} // if mail address is less than 500
						else
						{
							flag=false;
						}
				 	} // if 'Outside MOE' is clicked
				 	if (!flag) frm.externalMailAddress.focus();	// Invalid address
				 }
				// ends..	
				
			    if(flag) {
					flag = checkBlank(frm.strTargetAudience, "Target Group");
					if (!flag) frm.strTargetAudience.focus();
				}
				if(flag)
				{
					tgtAud = frm.strTargetAudience.value;
					if(tgtAud.length>200)
					{
					   frm.strTargetAudience.value=tgtAud.substr(0,199);
					}
				}
				if(flag) {
					flag = checkBlank(frm.strFindingsPlannedDt, "Publish Results date");
					if (!flag) frm.strFindingsPlannedDt.focus();
				}
				if(flag) {
					flag = compareTwoDates(frm.strToDate.value, frm.strFindingsPlannedDt.value, "To Date", "Publish Results date", false);
					if (!flag) frm.strToDate.focus();
				}
				return flag;
		}

		function submitSurveyListForm(submitUrl, actionName) 
		{
			var flag = true;
			var frm = document.SurveyForm;
			frm.action = '<bean:message key="OIFM.contextroot" />'+submitUrl+'?id=<%= Math.random() %>';
			frm.hiddenAction.value = actionName;
			if(actionName == "<%= OISurveyConstants.SURVEY_SAVE %>" || actionName == "<%= OISurveyConstants.SURVEY_SAVE_NEXT %>") 
			{
				flag = validateForm();
				
			}
			else if(actionName == "<%= OISurveyConstants.SURVEY_EXPORT_HTML %>" || actionName == "<%= OISurveyConstants.SURVEY_PRINT_PREVIEW %>")
			{
				frm.target='_new';
				frm.submit();
				frm.target='_self';
				return;

			}
			
			if(flag) 
				frm.submit();
			else 
				return;
		}

		function deleteSurveyForm(submitUrl, actionName) 
		{
			var flag = confirm('<%= OIDBRegistry.getValues("OI.SU.SURVEY.DELETE.CONFIRM") %>');
			if(flag) 
				submitSurveyListForm(submitUrl, actionName);
			else 
				return false; 
		}

		function ImportSurveyForm(submitUrl, actionName) 
		{
			if(!checkNumber(document.SurveyForm.importFromId, "Survey ID to import from"))
				return;
			var flag = confirm('Would you like to import from the given survey ?');
			if(flag) 
				submitSurveyListForm(submitUrl, actionName);
			else 
				return false;
		}

		function clearFormData()
		{
			editor_setHTML('taContentId',hidStrDescription);
			editor_setHTML('taContentId1',hidStrInstruction);
			document.getElementById('spanTagName').innerHTML = hidTags;
			document.SurveyForm.reset();
		}
		function openNewWindow(str)
		{
			document.SurveyForm.previewDescription.value = str;
			window.open('<bean:message key="OIFM.docroot" />/html/preview.html','mywindow','left=20,top=20,width=400,height=275,toolbar=0,resizable=0');
		}

		function fnEditEmail(){
		
		if(validateForm()) {
		if (document.SurveyForm.strEmailMessage.value=='')
		{
			var tempMessage = document.SurveyForm.strDefaultEmailMessage.value;
			tempMessage = tempMessage.replace(/\[TITLE\]/g, document.SurveyForm.strSurveyName.value);
			tempMessage = tempMessage.replace(/\[TARGET_AUDIENCE\]/g, document.SurveyForm.strTargetAudience.value);
			tempMessage = tempMessage.replace(/\[DESCRIPTION\]/g, document.SurveyForm.strDescription.value);
			tempMessage = tempMessage.replace(/\[COMPLETION_TIME\]/g, document.SurveyForm.strCompletionTime.value);
			tempMessage = tempMessage.replace(/\[CONTACT_NAME\]/g, document.SurveyForm.strContactPerson.value);
			tempMessage = tempMessage.replace(/\[CONTACT_PHONE\]/g, document.SurveyForm.strPhone.value);
			tempMessage = tempMessage.replace(/\[CONTACT_EMAIL\]/g, document.SurveyForm.strEmailAddress.value);
			tempMessage = tempMessage.replace(/\[TO_DATE\]/g, document.SurveyForm.strToDate.value);
			tempMessage = tempMessage.replace(/\[PLANNED_PUBLISH_DATE\]/g, document.SurveyForm.strFindingsPlannedDt.value);
			document.SurveyForm.strEmailMessage.value = tempMessage;
		}
//		if(document.SurveyForm.strSurveyId.value=='')
//			{
//			  // do validations to check required fields
//				
//				    if(document.SurveyForm.strEmailMessage.value=='')
//				    {
//						document.SurveyForm.strEmailMessage.value="<B><SPAN style='FONT-SIZE:10pt;FONT-FAMILY:Arial;'>Survey: <U>"+document.SurveyForm.strSurveyName.value+"</U></B><P style='MARGIN:0'><B></B>&nbsp;<P style='MARGIN:0'><B><SPAN style='COLOR:red;'>YOU HAVE BEEN INVITED:</SPAN></B><BR>Calling for views from<U> "+document.SurveyForm.strTargetAudience.value+"!</U><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>We are seeking your feedback on <U>"+document.SurveyForm.strSurveyName.value+".</U> The findings we get from you will <U>"+document.SurveyForm.strDescription.value+".</U><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>Responding to this survey will take no more than <U>"+document.SurveyForm.strCompletionTime.value+" minutes</U> of your time. Please be assured that your response will be kept in the strictest of confidence.<P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'> Simply click on <U><SPAN style='COLOR:#333399'>http://myforum.gov.sg/oi/login.jsp?module=S&amp;id=<<<ID>>></SPAN></U> and log into MyForum with your GDS ID and password<I>(the same you use to access TRAISI and VITAL).</I><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>For enquiries about this survey, please contact:<P style='MARGIN:0'><I>Name:"+document.SurveyForm.strContactPerson.value+"</I><P style='MARGIN:0'><I>Tel:<SPAN style='mso-tab-count:1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+document.SurveyForm.strPhone.value+"</I><P style='MARGIN:0'><I>Email: "+document.SurveyForm.strEmailAddress.value+"</I><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>We look forward to your response by <B><U>"+document.SurveyForm.strToDate.value+"</U></B>. We will be publishing the findings in MyForum by <U>"+document.SurveyForm.strFindingsPlannedDt.value+". </U></SPAN>";
//					
//					}
//			}
//			else if (document.SurveyForm.strEmailMessage.value=='') //for old surveys before revamp
//			{
//				document.SurveyForm.strEmailMessage.value="Dear Colleagues, Have you got the chance to take part in the (name) e-survey yet? Visit myforum today and have your voice heard! (http://myforum.gov.sg/oi/login.jsp?module=S&id="+document.SurveyForm.strSurveyId.value+") Myforum Administrator, Corporate Communication Division, MOE HQ. ";
//			}
		 var strUrl = '<bean:message key="OIFM.contextroot" />/OIEmailEdit.do?module=S&id='+document.SurveyForm.strSurveyId.value;
		 help_window1  = window.open(strUrl,'EditEmail','width=650,height=400,left=0,top=0,resizable=yes,scrollbars=yes');
		
  		 help_window1.focus();
		}
	}

	function fnSelectTags(){
		objActiveElem = document.getElementById('spanTagName');
			var strUrl = '/AddTags.do?hiddenAction=addTags&module=S';
			help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectTagss','width=500,height=620,left=0,top=0,resizable=yes,scrollbars=yes');
			help_window.focus();
		}

	function fnDisable(mode){
		var frm = document.SurveyForm;
		if(mode=='Y'){
			frm.strViewFindingType[0].disabled = false;
			frm.strAcknowledgeMode[0].disabled = false;
		}
		if(mode=='N'){
			frm.strViewFindingType[0].disabled = true;
			frm.strViewFindingType[0].checked = false;
			frm.strAcknowledgeMode[0].checked = false;
			frm.strAcknowledgeMode[0].disabled = true;
		}
	}

	//check email
	function echeck(str) {

		var at="@"
		var dot="."
		var lat=str.indexOf(at)
		var lstr=str.length
		var ldot=str.indexOf(dot)
		if (str.indexOf(at)==-1){
		   alert("Invalid E-mail ID")
		   return false
		}

		if (str.indexOf(at)==-1 || str.indexOf(at)==0 || str.indexOf(at)==lstr){
		   alert("Invalid E-mail ID")
		   return false
		}

		if (str.indexOf(dot)==-1 || str.indexOf(dot)==0 || str.indexOf(dot)==lstr){
		    alert("Invalid E-mail ID")
		    return false
		}

		 if (str.indexOf(at,(lat+1))!=-1){
		    alert("Invalid E-mail ID")
		    return false
		 }

		 if (str.substring(lat-1,lat)==dot || str.substring(lat+1,lat+2)==dot){
		    alert("Invalid E-mail ID")
		    return false
		 }

		 if (str.indexOf(dot,(lat+2))==-1){
		    alert("Invalid E-mail ID")
		    return false
		 }
		
		 if (str.indexOf(" ")!=-1){
		    alert("Invalid E-mail ID")
		    return false
		 }

 		 return true					
	}
//end check email
//-->
	
	// Added by K.K.Kumaresan on Jan 16,2008.
	function onClickOutsideMOE(mode)
	{
		var frm = document.SurveyForm;
		//alert("mode is"+mode);
		if(mode=='Y')
		{
			document.getElementById("toggle").style.display="block";		// visible
		}
		else
		{
			document.getElementById("toggle").style.display="none";		// Invisible
			
		}
		
	}
	</script>

	<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/Common.js' ></SCRIPT>
	<SCRIPT LANGUAGE="JavaScript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js' ></SCRIPT>

	<DIV id="divCalendar" style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>

	<html:form action="/SurveyAdmin.do" method="post" >
		<bean:define name="SurveyForm" id="SurveyForm" scope="request" type="com.oifm.survey.OIFormSurvey" />
		
		<html:hidden property="hiddenAction" />
		<html:hidden property="strSurveyId" />
		<html:hidden property="strEmailMessage" />
		<html:hidden property="strDefaultEmailMessage" />
		<html:hidden property="strTargetGpIds" />
		<html:hidden property="strTagIdList" />
		<bean:define id="isSurveyOwnerDivision" name="isSurveyDivision" scope="request" />

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

<table width="98%" border="0" cellpadding="0"
		cellspacing="0">

	<tr>
		<td class="TableHead">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
			<td class="Box">
				<table width="100%" border="0" cellpadding="0"
					cellspacing="0" bgcolor="white">
					<tr class="Table_head" >
						<td colspan="2">Survey</td>
 					</tr>

					<logic:present name="error" scope="request" >
						<tr><td colspan="2">&nbsp;</td></tr>
						<tr>
							<td width="75%" align="center" valign="top" class="Mainheader" colspan="2">
								<bean:message name="error" scope="request"/>
							</td>
						</tr>
						<tr><td colspan="2">&nbsp;</td></tr>
					</logic:present>

					<logic:present name="message" scope="request">
						<logic:iterate id="msg" name="message" scope="request">
							<tr><td colspan="2">&nbsp;</td></tr>
							<tr>
								<td width="75%" align="center" valign="top" class="Mainheader" colspan="2">
									<bean:message key="msg"/>
								</td>
							</tr>
							<tr><td colspan="2">&nbsp;</td></tr>
						</logic:iterate>
					</logic:present>

					<tr>
						<td colspan="2">
							<div align="left">
								<img src='<bean:message key="OIFM.docroot" />/images/tab_survey.gif' height="27" border="0" usemap="#MapMap">
								<logic:greaterThan name="SurveyForm" property="strSurveyId" value="0">
									<map name="MapMap">
										<area shape="rect" coords="7,0,62,32" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_EDIT %>')" >
										<area shape="rect" coords="76,3,141,27" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_SECTION_DO %>','<%= OISurveyConstants.SECTION_EDIT %>')" >
										<area shape="rect" coords="150,0,284,29" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_QUESTION_DO %>','<%= OISurveyConstants.QUESTION_LIST %>')" >
										<area shape="rect" coords="290,0,380,29" href="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_RESULT_DO %>','<%= OISurveyConstants.RESULT_TYPE %>')" >
									</map>
								</logic:greaterThan>
							</div>
						</td>
					</tr>
 
					<tr class="sub_head">
						<td colspan="2" >
							<logic:greaterThan name="SurveyForm" property="strSurveyId" value="0">
								View / Modify Survey 
								<SCRIPT LANGUAGE="JavaScript">
									<!--
									var fDate = "<bean:write  name="SurveyForm" property="strFromDate" />";
									if (! compareTwoDates2(currDate,fDate,"Current Date","From Date", false))
									{
										currDate = "<bean:write  name="SurveyForm" property="strFromDate" />";
									}
									//-->
								</SCRIPT>
							</logic:greaterThan>
							<logic:lessThan name="SurveyForm" property="strSurveyId" value="1">
								Create Survey 
							</logic:lessThan>
						</td>
					</tr> 
					<logic:lessThan name="SurveyForm" property="strSurveyId" value="1">
								<tr>
										<td colspan=2 valign="top" class="Heading_attributes"  valign="top">If you wish to import from a previous survey, please enter the <span title='Refer survey ID from listing screen' style="border-bottom:1px dotted;" onmouseover="this.style.color='red';" onmouseout="this.style.color='';">survey ID</span> and click 'Import' button 
										<input type="text" name="importFromId" size="4" maxlength="10" onkeypress="return keyNumber(event)"><a href='#'  onClick="javascript:ImportSurveyForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_IMPORT %>')" ><img src='<bean:message key="OIFM.docroot" />/images/btn_import.gif' alt="Import Survey"   border="0"></a> 
										</td>
									</tr>
							</logic:lessThan>
									<tr>
										<td width="15%" valign="top" class="Heading_attributes"  valign="top" colspan=2>What is the title of your survey ?<B><font color="#FF0000">*</font></B>
											<html:text styleClass="Text_box" property="strSurveyName" size="82" maxlength="70"	/>
										</td>
									</tr>
									
									<tr>
										<td valign="top" class="Heading_attributes"  valign="top" colspan=2>What does your division/branch hope to achieve through  running this survey ?<B><font color="#FF0000">*</font></B>
											</td>
									</tr>
									<tr>
											<td  class="body_detail_text" colspan=2>
											
 											<INPUT TYPE="hidden" name="previewDescription">
						<html:textarea property="strDescription" styleId="taContentId" cols="70" rows="6" styleClass="text_area" onkeydown="fn_textCounter(document.SurveyForm.strDescription, document.SurveyForm.numleft, 1000);" onkeyup="fn_textCounter(document.SurveyForm.strDescription, document.SurveyForm.numleft, 1000);" onmouseover="fn_textCounter(document.SurveyForm.strDescription, document.SurveyForm.numleft, 1000);" onmouseout="fn_textCounter(document.SurveyForm.strDescription, document.SurveyForm.numleft, 1000);"/>

						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId',config);
						</script>
											
											<div align="left">
												<font color="#005BCC" >
													<!--No. of characters remaining: -->
													<input name="numleft" type="hidden" class="text_box" size="5" value="1000" disabled style="taInfo">
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes"  colspan=2>Please enter the instructions for this survey:</td>
									</tr>
									<tr>
										<td class="body_detail_text"  colspan=2>
										<html:textarea property="strInstruction" styleId="taContentId1" cols="70" rows="6" styleClass="text_area" onkeydown="fn_textCounter(document.SurveyForm.strInstruction, document.SurveyForm.numleft1, 1000);" onkeyup="fn_textCounter(document.SurveyForm.strInstruction, document.SurveyForm.numleft1, 1000);" onmouseover="fn_textCounter(document.SurveyForm.strInstruction, document.SurveyForm.numleft1, 1000);" onmouseout="fn_textCounter(document.SurveyForm.strInstruction, document.SurveyForm.numleft1, 1000);"/>

						<script language="javascript">
							var config = new Object();
							config.bodyStyle = 'background-color: white; font-family: "Verdana"; font-size: x-small;';
							config.debug = 0;
							editor_generate('taContentId1',config);
						</script>
 
										
											<div align="left">
												<font color="#005BCC" >
													<!--No. of characters remaining: -->
													<input name="numleft1" type="hidden" class="text_box" size="5" value="1000" disabled style="taInfo">
												</font>
											</div>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>How long will it take to complete this survey ? <font color="red"> * </font>
											<html:text styleClass="Text_box" property="strCompletionTime" size="4" maxlength="3"	 onkeypress="return keyNumber(event)"/> minutes
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>
										<i>For queries on the survey, respondents may contact : </i>
											
										</td>
									</tr>
									
									<tr>
										<td valign="top" class="Heading_attributes">&nbsp;&nbsp;&nbsp;&nbsp;<i>Name<font color="red"> *</td>
										<td class="body_detail_text">
											<html:text styleClass="Text_box" property="strContactPerson" size="50" maxlength="66"	/>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes">&nbsp;&nbsp;&nbsp;&nbsp;<i>Contact Tel<font color="red"> *</td>
										<td class="body_detail_text">
											<html:text styleClass="Text_box" property="strPhone" size="50" maxlength="15"	/>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes">&nbsp;&nbsp;&nbsp;&nbsp;<i>Email Address<font color="red"> *</td>
										<td class="body_detail_text">
											<html:text styleClass="Text_box" property="strEmailAddress" size="50" maxlength="60"	/>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>This survey will run <font color="#FF0000">*</font> from
											<html:text styleClass="Text_box" property="strFromDate" size="15" maxlength="11" readonly="true" />
											<logic:match name="isSurveyOwnerDivision" value="true" >
												<a href="#" onClick="cal.select(document.SurveyForm.strFromDate,'strFromDate','dd-NNN-yyyy'); return false;" ><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a> &nbsp;
											</logic:match>
											<logic:match name="isSurveyOwnerDivision" value="false" >
												<img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"> &nbsp;
											</logic:match>
											to
											<html:text styleClass="Text_box" property="strToDate" size="15" maxlength="11"  readonly="true" />
											<logic:match name="isSurveyOwnerDivision" value="true" >
												<a href="#" onClick="cal.select(document.SurveyForm.strToDate,'strToDate','dd-NNN-yyyy');return false;" ><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a>
											</logic:match>
											<logic:match name="isSurveyOwnerDivision" value="false" >
												<img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"> &nbsp;
											</logic:match>
										</td>
									</tr>
									
									<tr class="sub_head">
										<td colspan="2" >
										<br>Selecting Target Group
										</td>
									</tr>

									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>Would this survey be open to all users ?
											<!--public or private -->
											<html:radio  styleClass="Text" property="strSurveyType" value="N" onclick="javascript:fnDisable('Y');">Yes</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											<html:radio  styleClass="Text" property="strSurveyType" value="Y" onclick="javascript:fnDisable('N');">No</html:radio>
										</td>
									</tr>
									
													

									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>Please click on 'Select Groups' button to select the target groups for the survey. <b><font color="#FF0000">*</font></b><br>
										<html:text styleClass="Text_box" property="strTargetAudience" size="50" maxlength="70" readonly="true"/>

										<a href="javascript:;" onClick="javascript:fnSelectGroups();"><img src='<bean:message key="OIFM.docroot" />/images/but_select_groups.gif' border="0" alt="Select Group"></a> 
										</td>
									</tr>
							
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>Would you like to extend this survey to users outside MOE? <b><font color="#FF0000">*</font></b>
											<html:radio  styleClass="Text" property="outsideMOEChecked" value="Y" onclick="javascript:onClickOutsideMOE('Y');">Yes</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											<html:radio  styleClass="Text" property="outsideMOEChecked" value="N" onclick="javascript:onClickOutsideMOE('N');">No</html:radio>
										</td>
									</tr>
									
									
									<tr>
										
										<td valign="top" class="Heading_attributes" colspan=2>
											<div id="toggle">
											Email addresses(with ; as a seperator) of the users for survey <b><font color="#FF0000">*</font></b><br>
											<html:textarea property="externalMailAddress" cols="50" rows="5" ></html:textarea>
											</div>
										</td>
									</tr>
	
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2><br>Would you like to open the survey to other users who have expressed interest in the issues <br>relevant to this survey?
											<html:radio  styleClass="Text" property="strOpenTag" value="Y"> Yes&nbsp;&nbsp; </html:radio>
											<html:radio  styleClass="Text" property="strOpenTag" value="N"> No  </html:radio>
										</td>
									</tr>
									<tr>
										<td colspan=5>
										<table>
										<tr>
											<td valign="top" class="Heading_attributes" ><br>&nbsp;&nbsp;
												<span id="spanTagName" class="Boxoutline">
												<logic:notEqual name="SurveyForm" property="strTagWords" value="">
													<bean:write name="SurveyForm" property="strTagWords"/>
												</logic:notEqual>
												<logic:equal name="SurveyForm" property="strTagWords" value="">
													Please click on "Select Tags" to select the key tag words for the survey
												</logic:equal>
												</span>
											</td>
											<td valign="bottom" class="body_extract_text">
												<!--<html:text styleClass="Text_box" property="strTagWords" size="50" maxlength="250"	/>-->
												<a href="javascript:;" onClick="javascript:fnSelectTags();"><img src='<bean:message key="OIFM.docroot" />/images/select-tags.gif' border="0" alt="Add Tags" /></a>
											</td>
										</tr>
										</table>
										</td>
									</tr>
									<!--<tr>
										<td valign="top" class="Heading_attributes" ><br>Tag words
										</td>
										<td valign="bottom"  >
											<html:text styleClass="Text_box" property="strTagWords" size="50" maxlength="250" readonly="true"	/>
											<a href="javascript:;" onClick="javascript:fnSelectTags();"><img src='<bean:message key="OIFM.docroot" />/images/select-tags.gif' border="0" alt="Add Tags" /></a>
										</td>
									</tr>-->
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2><br>Would you like to send an email invitation to the group/s selected above?
											<html:radio  styleClass="Text" property="strReminderMode" value="A"> Yes&nbsp;&nbsp; </html:radio>
											<html:radio  styleClass="Text" property="strReminderMode" value="M"> No  </html:radio> 
											<a href='#'  onClick="javascript:fnEditEmail();">Click here to edit email</a>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes" colspan=2><br>When would you like the email invitation to be sent ? 
										<html:text styleClass="Text_box" property="strEmailDate" size="15" maxlength="11"  readonly="true" />
											<logic:match name="isSurveyOwnerDivision" value="true" >
												<a href="#" onClick="cal.select(document.SurveyForm.strEmailDate,'strEmailDate','dd-NNN-yyyy');return false;" ><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a>
											</logic:match>
											<logic:match name="isSurveyOwnerDivision" value="false" >
												<img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"> &nbsp;
											</logic:match>
											<br>
											
										</td>
									</tr>
									<logic:greaterThan name="SurveyForm" property="strSurveyId" value="0">
										<tr>
											<td valign="top" class="Heading_attributes" colspan=2><br>Publish on Web &nbsp;&nbsp;&nbsp;
												<html:checkbox  property="strIsActive" value="Y" />
											</td>
										</tr>
									</logic:greaterThan>
 									
 									<tr class="sub_head">
										<td colspan="2" >
										<br>Closing the Loop
										</td>
									</tr>

									<tr>
										<td valign="top" class="Heading_attributes" colspan=2>We will finalize the findings of the survey and publish the results by <B><font color="#FF0000">*</font></B>
										<html:text styleClass="Text_box" property="strFindingsPlannedDt" size="15" maxlength="11"  readonly="true" />
											<logic:match name="isSurveyOwnerDivision" value="true" >
												<a href="#" onClick="cal.select(document.SurveyForm.strFindingsPlannedDt,'strFindingsPlannedDt','dd-NNN-yyyy');return false;" ><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a>
											</logic:match>
											<logic:match name="isSurveyOwnerDivision" value="false" >
												<img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"> &nbsp;
											</logic:match>
											<br>
											via the following platforms :
										</td>
									</tr>

									<tr>
										<td valign="top" colspan=2><font size=1><i><u><br>Viewing of findings <br> </u></i></td>
									</tr>
									<tr>
										<td class="Heading_attributes" colspan=2>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="A">In MyForum, viewed by all OR</html:radio> <br>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="I">In MyForum viewed only by Invitees (private surveys only) OR</html:radio> <br>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="P">In My Forum, views only by respondents</html:radio><br>
										</td>
									</tr>

										<tr>
										<td valign="top" colspan=2><font size=1><i><u><br>Publicizing of findings <br> </u></i></td>
									</tr>
									<tr>
										<td class="Heading_attributes" colspan=2>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strAcknowledgeMode" value="A">Email link to all OR</html:radio> <br>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strAcknowledgeMode" value="I">Email link to Invitees (private surveys only) OR</html:radio> <br>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strAcknowledgeMode" value="P">Email link to all respondents</html:radio><br>
										</td>
									</tr>
									
									<tr>
										<td valign="top" class="body_detail_text" colspan="2">&nbsp;</td>
 									</tr>
 
						<tr>
							<td height="35" align="left" colspan="2">
								<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_ADMIN_LIST %>')" >
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a>&nbsp;
								<logic:match name="isSurveyOwnerDivision" value="true" >
									<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_SAVE %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif' border="0" alt = "Save"></a>&nbsp;
									<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_SAVE_NEXT %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/but_save_next.gif' border="0" alt = "Save & Next"></a>&nbsp;
									<a href="#" onClick="javascript:clearFormData()" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>&nbsp;

									<logic:greaterThan name="SurveyForm" property="strSurveyId" value="0">
										<logic:match name="isCurrentlyValid" value="true" scope="request">
											<a href="#" onClick="alert('You should not delete survey, when survey period is valid')" >
												<img src='<bean:message key="OIFM.docroot" />/images/btn_Delete.gif' border="0" alt = "Delete"></a>&nbsp;
										</logic:match>
										<logic:match name="isCurrentlyValid" value="false" scope="request">
											<a href="#" onClick="javascript:deleteSurveyForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_DELETE %>')" >
												<img src='<bean:message key="OIFM.docroot" />/images/btn_Delete.gif'  border="0" alt = "Delete"></a>
										</logic:match>

										<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_USER_RSP_DO %>','<%= OISurveyConstants.SURVEY_PRINT_PREVIEW %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/print-preview.gif' alt="Print the Survey" border="0" ></a>&nbsp;
									</logic:greaterThan>
								</logic:match>

								<logic:match name="isSurveyOwnerDivision" value="false" >
									<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_SECTION_DO %>','<%= OISurveyConstants.SECTION_EDIT %>')" >
										<img src='<bean:message key="OIFM.docroot" />/images/btn_next.gif' border="0" alt = "Next"></a>&nbsp;
								</logic:match>

								<logic:match name="isSurveyOwnerDivision" value="true" >
								
										&nbsp;
									<br>
								
										

									<logic:match name="SurveyForm" property="strIsActive" value="Y">
										<logic:match name="isSurveyPublish" value="true" scope="request">
											<logic:match name="isCurrentlyValid" value="true" scope="request">
												<a href="#" onClick="alert('Can not publish survey, when survey period is active')" >
													<img src='<bean:message key="OIFM.docroot" />/images/Publish_Survey_Result.gif' alt="Publish Survey"  border="0"></a>&nbsp;
											</logic:match>
											<logic:match name="isCurrentlyValid" value="false" scope="request">
												<a href="#" onClick="javascript:submitSurveyListForm('<%= OISurveyConstants.SURVEY_ADMIN_DO %>','<%= OISurveyConstants.SURVEY_PUBLISH_EDIT %>')" >
													<img src='<bean:message key="OIFM.docroot" />/images/Publish_Survey_Result.gif' alt="Publish Survey"   border="0"></a>&nbsp;
											</logic:match>
										</logic:match>
									</logic:match>
								</logic:match>
							</table>

			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
		<logic:match name="isSurveyOwnerDivision" value="false" >
			<SCRIPT LANGUAGE="JavaScript">
				<!--
				disableElements(document.SurveyForm);
				//-->
			</SCRIPT>
		</logic:match>
	</html:form >
<script language="javascript">
	var hidStrDescription = document.SurveyForm.strDescription.value;
	var hidStrInstruction = document.SurveyForm.strInstruction.value;
	var hidTags = document.getElementById('spanTagName').innerHTML;
</script>
<logic:lessThan name="SurveyForm" property="strSurveyId" value="1">
<script>
	document.SurveyForm.strSurveyType[0].checked=true
	
	// added by K.K.Kumaresan.
	document.SurveyForm.outsideMOEChecked[1].checked=true;	
	document.getElementById("toggle").style.display="none";		// Invisible
</script>
</logic:lessThan>