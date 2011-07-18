<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="com.oifm.utility.OIDBRegistry" %>


<link href='<bean:message key="OIFM.docroot" />/css/oicalendar.css' rel="stylesheet" type="text/css">
<link href='<bean:message key="OIFM.docroot" />/css/simpleTxtFormating.css' rel="stylesheet" type="text/css">
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/validate.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/oicalendar.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/Common.js'></script>
<script language="javascript" src='<bean:message key="OIFM.docroot" />/js/simpleTxtFormating.js'></script>
<script language="javascript">
	var maxlimit = 1000;
	var currDate = "<%= com.oifm.utility.OIUtilities.getCurrentDateTime("dd-MMM-yyyy")%>";
	var objActiveElem;
	function fn_textCounter(field, countfield) 
	{
		/*if (field.value.length > maxlimit) 
		{
			field.value = field.value.substring(0, maxlimit);
		}
		else
		{
			countfield.value = maxlimit - field.value.length;
		}*/
	}
			/** This function is used to load select users window */    
	function fnSelectGroups(){
		
		var strUrl = '/SelectGroups.do?hiddenAction=searchallgroup&module=C&id='+document.ConsultPageForm.paperId.value;
		help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectGroups','width=640,height=600,left=0,top=0,resizable=yes,scrollbars=yes');
		help_window.focus();
	}
	function fnSubmit1(actionName,ac)
	{
		document.ConsultPageForm.hiddenAction.value=ac;
		document.ConsultPageForm.action=actionName;
		document.ConsultPageForm.submit();
	}
	//added by edmund
	function fnSubmit3(actionName,paperId,hiddenAction)
	{
		document.ConsultPageForm.paperId.value=paperId;
		document.ConsultPageForm.hiddenAction.value=hiddenAction;
		document.ConsultPageForm.action=actionName;
		document.ConsultPageForm.submit();
	}
	function fnSubmit2(actionName,ac)
	{
		//if (compareDate(document.ConsultPageForm.toDt.value,'<%= com.oifm.utility.OIUtilities.getCurrentDateTime("dd-MMM-yyyy")%>'))
		//{
			//return;
		//}
		document.ConsultPageForm.hiddenAction.value=ac;
		document.ConsultPageForm.action=actionName;
		document.ConsultPageForm.submit();
	}
	function  validateForm()
	{
		if(Trim(document.ConsultPageForm.title.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.TITLE") %>');
			document.ConsultPageForm.title.focus();
		}
		else if (document.ConsultPageForm.categoryID.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.CATEGORY") %>');
			document.ConsultPageForm.categoryID.focus();
		}
		else if(Trim(document.ConsultPageForm.description.value)=="")
		{
			alert('Please enter the Description');
			document.ConsultPageForm.description.focus();
		}
		else if (document.ConsultPageForm.description.value.length > 1000)
		{
			alert("Please enter maximum 1000 characters for Description");
			document.ConsultPageForm.description.focus();
		}
		else if (document.ConsultPageForm.strInstruction.value.length > 1000)
		{
			alert("Please enter maximum 1000 characters for Instruction");
			document.ConsultPageForm.strInstruction.focus();
		}
		else if(Trim(document.ConsultPageForm.strCompletionTime.value)=="")
		{
			alert('Please enter the Time Taken');
			document.ConsultPageForm.strCompletionTime.focus();
		}
		else if(Trim(document.ConsultPageForm.contactPerson.value)=="")
		{
			alert('Please enter the Contact Person');
			document.ConsultPageForm.contactPerson.focus();
		}
		else if(Trim(document.ConsultPageForm.phone.value)=="")
		{
			alert('Please enter the Telephone number');
			document.ConsultPageForm.phone.focus();
		}
		else if(Trim(document.ConsultPageForm.emailAddress.value)=="")
		{
			alert('Please enter the Email address');
			document.ConsultPageForm.emailAddress.focus();
		}
		else if (echeck(document.ConsultPageForm.emailAddress.value)==false){
				document.ConsultPageForm.emailAddress.value="";
				document.ConsultPageForm.emailAddress.focus();
		}
		else if(Trim(document.ConsultPageForm.fromDt.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.FROMDT") %>');
			document.ConsultPageForm.fromDt.focus();
		}
		else if(Trim(document.ConsultPageForm.toDt.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.TODT") %>');
			document.ConsultPageForm.toDt.focus();
		}
		else if(Trim(document.ConsultPageForm.targetAudiance.value)=="")
		{
			alert('Please select the target group');
			document.ConsultPageForm.targetAudiance.focus();
		}
		else if(Trim(document.ConsultPageForm.strFindingsPlannedDt.value)=="")
		{
			alert('Please enter the Publish Results date');
			document.ConsultPageForm.strCompletionTime.focus();
		}
		else if (! compareTwoDates(document.ConsultPageForm.fromDt.value,document.ConsultPageForm.toDt.value,"From Date", "To Date", true))
		{
			document.ConsultPageForm.fromDt.focus();
		}
		else if (Trim(document.ConsultPageForm.strEmailDate.value) != "" &&
				 (!compareTwoDates2(document.ConsultPageForm.fromDt.value, document.ConsultPageForm.strEmailDate.value, "From Date", "Email invitation date", false) ||
				  !compareTwoDates2(document.ConsultPageForm.strEmailDate.value, document.ConsultPageForm.toDt.value, "Email invitation date", "To date", false)))
		{
			alert ('Email invitation date should be between start date and end date');
		}
		else if (Trim(document.ConsultPageForm.paperId.value)=="")
		{
			if (! compareTwoDates(currDate,document.ConsultPageForm.toDt.value,"Current Date", "To Date", false))
			{
				document.ConsultPageForm.toDt.focus();
			}
			else if (! compareTwoDates(currDate,document.ConsultPageForm.fromDt.value,"Current Date","From Date", false))
			{
				document.ConsultPageForm.fromDt.focus();
			}
			else if (! compareTwoDates(document.ConsultPageForm.toDt.value,document.ConsultPageForm.strFindingsPlannedDt.value,"To Date","Publish Results date", false))
			{
				document.ConsultPageForm.strFindingsPlannedDt.focus();
			}
			else
			{
				if(document.ConsultPageForm.targetAudiance.value.length>200)
				{
					document.ConsultPageForm.targetAudiance.value=document.ConsultPageForm.targetAudiance.value.substr(0,199);
				}
				return true;
			}
		}
		else
		{
			if (Trim(document.ConsultPageForm.paperId.value)=="")
			{
			}
			else if (! compareTwoDates(currDate,document.ConsultPageForm.fromDt.value,currDate+" (earlier consultation paper start date)","From Date", false))
			{
				document.ConsultPageForm.fromDt.focus();
				return;
			}
			else if (! compareTwoDates(document.ConsultPageForm.toDt.value,document.ConsultPageForm.strFindingsPlannedDt.value,"To Date","Publish Results date", false))
			{
				document.ConsultPageForm.strFindingsPlannedDt.focus();
				return;
			}
			
			if(document.ConsultPageForm.targetAudiance.value.length>200)
			{
				document.ConsultPageForm.targetAudiance.value=document.ConsultPageForm.targetAudiance.value.substr(0,199);
			}
			return true;
		}
		
		return false;
	}
	function fnSubmit(actionName,ac)
	{
		if(validateForm())
		{
				document.ConsultPageForm.description.value=Trim(document.ConsultPageForm.description.value);
				document.ConsultPageForm.hiddenAction.value=ac;
				document.ConsultPageForm.action=actionName;
				document.ConsultPageForm.submit();
			
		}	
	}
	function fnClear()
	{
		document.getElementById('spanTagName').innerHTML = hidTags;
		document.ConsultPageForm.reset();
		fn_textCounter(this.ConsultPageForm.description,this.ConsultPageForm.numleft);
	}
	function compareDate(fromDt,toDt)
	{
		FromDate = new Date(fromDt);
		ToDate = new Date(toDt);
		if ((ToDate-FromDate)<0)
		{
			return false;
		}
		else
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.PUPLISH") %>');
			return true;
		}
	}
	function fnDelete(actionName,ac)
	{
		if (window.confirm('<%= OIDBRegistry.getValues("OI.CONS.PAPER.DELETE") %>')==false)
		{
			return;
		}
		else if (document.ConsultPageForm.categoryID.selectedIndex==0)
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.CATEGORY") %>');
			document.ConsultPageForm.categoryID.focus();
		}
		else if(Trim(document.ConsultPageForm.title.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.TITLE") %>');
			document.ConsultPageForm.title.focus();
		}
		else if(Trim(document.ConsultPageForm.fromDt.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.FROMDT") %>');
			document.ConsultPageForm.fromDt.focus();
		}
		else if(Trim(document.ConsultPageForm.toDt.value)=="")
		{
			alert('<%= OIDBRegistry.getValues("OI.CONS.PAPER.TODT") %>');
			document.ConsultPageForm.toDt.focus();
		}
		else if(Trim(document.ConsultPageForm.targetAudiance.value)=="")
		{
			alert('Please enter the target audience');
			document.ConsultPageForm.targetAudiance.focus();
		}
		else if(Trim(document.ConsultPageForm.contactPerson.value)=="")
		{
			alert('Please enter the Contact Person');
			document.ConsultPageForm.contactPerson.focus();
		}
		else if(Trim(document.ConsultPageForm.phone.value)=="")
		{
			alert('Please enter the Telephone number');
			document.ConsultPageForm.phone.focus();
		}
		else if(Trim(document.ConsultPageForm.emailAddress.value)=="")
		{
			alert('Please enter the Email address');
			document.ConsultPageForm.emailAddress.focus();
		}
		else if(Trim(document.ConsultPageForm.strCompletionTime.value)=="")
		{
			alert('Please enter the Time Taken');
			document.ConsultPageForm.strCompletionTime.focus();
		}
		else if(Trim(document.ConsultPageForm.strFindingsPlannedDt.value)=="")
		{
			alert('Please enter the Publish Results date');
			document.ConsultPageForm.strCompletionTime.focus();
		}
		else if(Trim(document.ConsultPageForm.description.value)=="")
		{
			alert('Please enter the Description');
			document.ConsultPageForm.description.focus();
		}
		else if (! compareTwoDates(document.ConsultPageForm.fromDt.value,document.ConsultPageForm.toDt.value,"From Date", "To Date", true))
		{
		}
		else
		{
			document.ConsultPageForm.action=actionName;
			document.ConsultPageForm.hiddenAction.value=ac;
			//document.loginForm.method="post";
			document.ConsultPageForm.submit();
		}	
	}
	function fnRemove(actionName,ac)
	{
		if (window.confirm('<%= OIDBRegistry.getValues("OI.CONS.PAPER.ATTACH") %>')==false)
		{
			return;
		}
		else
		{
			document.ConsultPageForm.hiddenAction.value=ac;
			document.ConsultPageForm.action=actionName;
			//document.loginForm.method="post";
			document.ConsultPageForm.submit();
		}	
	}
	function fnRemoveQuestion(actionName,qi,ac)
	{
		if (window.confirm('<%= OIDBRegistry.getValues("OI.CONS.PAPER.QUESTION") %>')==false)
		{
			return;
		}
		else
		{
			document.ConsultPageForm.questionId.value=qi;
			document.ConsultPageForm.hiddenAction.value=ac;
			document.ConsultPageForm.action=actionName;
			//document.loginForm.method="post";
			document.ConsultPageForm.submit();
		}	
	}
	function openNewWindow()
	{
		document.ConsultPageForm.previewDescription.value = document.ConsultPageForm.description.value;
		window.open('<bean:message key="OIFM.docroot" />/html/preview.html','mywindow','left=20,top=20,width=400,height=275,toolbar=0,resizable=0,scrollbars=yes');
	}

	function ImportPaperForm(submitUrl, actionName) 
		{
			if(!checkNumber(document.ConsultPageForm.importFromId, "Consultation Paper ID to import from"))
				return;
			var flag = confirm('Would you like to import from the given consultation paper ?');
			if(flag) 
				fnSubmit1(submitUrl, actionName);
			else 
				return false;
		}

	function submitReorderQuestion(actionName,ac,qi,canMoveUp,canMoveDown)
	{
		
		if(ac=='QUESTION_MOVE_UP' && (canMoveUp!='' && canMoveUp!='null'))
		{
			alert(canMoveUp);
			return;
		}
		else if(ac=='QUESTION_MOVE_DOWN' && (canMoveDown!='' && canMoveDown!='null'))
		{
			alert(canMoveDown);
			return;
		}
		
			document.ConsultPageForm.hiddenAction.value=ac;
			document.ConsultPageForm.questionId.value=qi;
			document.ConsultPageForm.action=actionName;
			//document.loginForm.method="post";
			document.ConsultPageForm.submit();
			
	}

	function fnEditEmail(){
		if(validateForm()){
		if (document.ConsultPageForm.strEmailMessage.value=='')
		{
			var tempMessage = document.ConsultPageForm.strDefaultEmailMessage.value;
			tempMessage = tempMessage.replace(/\[TITLE\]/g, document.ConsultPageForm.title.value);
			tempMessage = tempMessage.replace(/\[TARGET_AUDIENCE\]/g, document.ConsultPageForm.targetAudiance.value);
			tempMessage = tempMessage.replace(/\[DESCRIPTION\]/g, document.ConsultPageForm.description.value);
			tempMessage = tempMessage.replace(/\[COMPLETION_TIME\]/g, document.ConsultPageForm.strCompletionTime.value);
			tempMessage = tempMessage.replace(/\[CONTACT_NAME\]/g, document.ConsultPageForm.contactPerson.value);
			tempMessage = tempMessage.replace(/\[CONTACT_PHONE\]/g, document.ConsultPageForm.phone.value);
			tempMessage = tempMessage.replace(/\[CONTACT_EMAIL\]/g, document.ConsultPageForm.emailAddress.value);
			tempMessage = tempMessage.replace(/\[TO_DATE\]/g, document.ConsultPageForm.toDt.value);
			tempMessage = tempMessage.replace(/\[PLANNED_PUBLISH_DATE\]/g, document.ConsultPageForm.strFindingsPlannedDt.value);
			document.ConsultPageForm.strEmailMessage.value = tempMessage;
		}
//		if(document.ConsultPageForm.paperId.value=='')
//			{
//			  // do validations to check required fields
//				if (document.ConsultPageForm.strEmailMessage.value=='') {
//					document.ConsultPageForm.strEmailMessage.value="<B><SPAN style='FONT-SIZE:10pt;FONT-FAMILY:Arial;'>Consultation Paper: <U>"+document.ConsultPageForm.title.value+"</U></B><P style='MARGIN:0'><B></B>&nbsp;<P style='MARGIN:0'><B><SPAN style='COLOR:red;'>YOU HAVE BEEN INVITED:</SPAN></B><BR>Calling for views from<U> "+document.ConsultPageForm.targetAudiance.value+"!</U><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>We are seeking your feedback on <U>"+document.ConsultPageForm.title.value+".</U> The findings we get from you will <U>"+document.ConsultPageForm.description.value+".</U><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>Responding to this paper will take no more than <U>"+document.ConsultPageForm.strCompletionTime.value+" minutes</U> of your time. Please be assured that your response will be kept in the strictest of confidence.<P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'> Simply click on <U><SPAN style='COLOR:#333399'>http://myforum.gov.sg/oi/login.jsp?module=C&amp;id=<<<ID>>></SPAN></U> and log into MyForum with your GDS ID and password<I>(the same you use to access TRAISI and VITAL).</I><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>For enquiries about this paper, please contact:<P style='MARGIN:0'><I>Name:"+document.ConsultPageForm.contactPerson.value+"</I><P style='MARGIN:0'><I>Tel:<SPAN style='mso-tab-count:1'>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+document.ConsultPageForm.phone.value+"</I><P style='MARGIN:0'><I>Email: "+document.ConsultPageForm.emailAddress.value+"</I><P style='MARGIN:0'>&nbsp;<P style='MARGIN:0'>We look forward to your response by <B><U>"+document.ConsultPageForm.toDt.value+"</U></B>. We will be publishing the findings in MyForum by <U>"+document.ConsultPageForm.strFindingsPlannedDt.value+". </U></SPAN>";
//				}
//			  
//			}
//			else if (document.ConsultPageForm.strEmailMessage.value=='') //for old papers before revamp
//			{
//				document.ConsultPageForm.value="Dear Colleagues, Have you got the chance to take part in the (name) consultation paper yet? Visit myforum today and have your voice heard! (http://myforum.gov.sg/oi/login.jsp?module=C&id="+document.ConsultPageForm.paperId.value+") Myforum Administrator, Corporate Communication Division, MOE HQ. ";
//			}
		 var strUrl = '<bean:message key="OIFM.contextroot" />/OIEmailEdit.do?module=C&id='+document.ConsultPageForm.paperId.value;
		 help_window1  = window.open(strUrl,'EditEmail','width=650,height=400,left=0,top=0,resizable=yes,scrollbars=yes');
		
  		 help_window1.focus();
		}
	}

	function fnSelectTags(){
			var strUrl = '/AddTags.do?hiddenAction=addTags&module=C';
			help_window  = window.open('<bean:message key="OIFM.contextroot" />'+strUrl,'SelectTagss','width=520,height=600,left=0,top=0,resizable=yes,scrollbars=yes');
			help_window.focus();
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
function fnDisable(mode){
		var frm = document.ConsultPageForm;
		if(mode=='Y'){
			frm.strViewFindingType[0].disabled = false;
			frm.publishStatus[0].disabled = false;
		}
		if(mode=='N'){
			frm.strViewFindingType[0].disabled = true;
			frm.strViewFindingType[0].checked = false;
			frm.publishStatus[0].checked = false;
			frm.publishStatus[0].disabled = true;
		}
	}
</script>
<DIV id=divCalendar style="VISIBILITY: hidden; POSITION: absolute; BACKGROUND-COLOR: white; layer-background-color: white"></DIV>
<%
try
{
%>


<html:form action="/consultCreatePageAction.do" enctype="multipart/form-data">
<html:hidden property="strTagIdList" />

<jsp:include page="/jsp/common/oifm_admin_header.jsp" flush="true" />

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
						<td colspan="5">Consultation Paper</td>
 					</tr>
					<logic:present name="error" scope="request">
						<tr><td colspan="5">&nbsp;</td></tr>
						<tr>
							<td width="75%" align="center" valign="top" class="Mainheader" colspan="5">
								<font color="red"><b><bean:write name="error" scope="request" /></b></font>
							</td>
						</tr>
						<tr><td colspan="5">&nbsp;</td></tr>
					</logic:present>
					<logic:present name="message" scope="request">
						<tr><td colspan="5">&nbsp;</td></tr>
							<logic:iterate id="mList" name="message" scope="request">
								<tr>
									<td width="75%" align="center" valign="top" class="Mainheader" colspan="5">
										<bean:write name="mList"/>
									</td>
								</tr>
							</logic:iterate>
						<tr><td colspan="5">&nbsp;</td></tr>
					</logic:present>
					<tr>
						<td colspan="5" class="sub_head">
							<logic:notPresent name="ConsultPageForm" property="paperId">
								Create Consultation Paper 
							</logic:notPresent>
							<logic:present name="ConsultPageForm" property="paperId">
							<div align="left">
								<img src='<bean:message key="OIFM.docroot" />/images/tab_cp.gif' width="368" height="27" border="0" usemap="#MapMap">
									<map name="MapMap">
										<area shape="rect" coords="7,0,150,32" href="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','<bean:write name="ConsultPageForm" property="paperId" />','populate');" >
										<area shape="rect" coords="170,3,230,27" href="javascript:fnSubmit3('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','<bean:write name="ConsultPageForm" property="paperId" />','result');" >
									</map>
							</div>
								View / Modify Consultation Paper
								<SCRIPT LANGUAGE="JavaScript">
									<!--
										var fDate = "<bean:write  name="ConsultPageForm" property="fromDt" />";
										if (! compareTwoDates2(currDate,fDate,"Current Date","From Date", false))
										{
											currDate = "<bean:write  name="ConsultPageForm" property="fromDt" />";
										}
									//-->
								</SCRIPT>
							</logic:present>
						</td>
					</tr>
					<logic:notPresent name="ConsultPageForm" property="paperId">
					<tr>
						<td colspan=5 valign="top"  valign="top" class="heading_attributes">If you wish to import from a previous paper, please enter the <span title='Refer paper ID from listing screen' style="border-bottom:1px dotted;" onmouseover="this.style.color='red';" onmouseout="this.style.color='';">paper ID</span> and click 'Import' button
						<input type="text" name="importFromId" size="4" maxlength="10" onkeypress="return keyNumber(event)"><a href='#'  onClick='javascript:ImportPaperForm("<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do","import")' ><img src='<bean:message key="OIFM.docroot" />/images/btn_import.gif' alt="Import Survey"   border="0"></a> 
						</td>
					</tr>
					</logic:notPresent>
								<tr>
									<td class="heading_attributes" colspan="5">What is the title of your paper? <font color="red"> * </font>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:text  property="title" styleClass="Text_box" size="55" maxlength="70" />
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:text  property="title" styleClass="Text_box" size="55" maxlength="70" readonly="true" />
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td class="heading_attributes" colspan="5">Which Category would your paper belong to? <font color="red"> * </font>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<bean:define id="ar" name="ConsultPageForm" property="arCategoryID" /> 
											<html:select property="categoryID" styleClass="Text_box" >
												<html:options collection="ar" property="value" labelProperty="label"  />
											</html:select>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<bean:define id="ar" name="ConsultPageForm" property="arCategoryID" /> 
											<html:select property="categoryID" styleClass="Text_box" disabled="true">
												<html:options collection="ar" property="value" labelProperty="label"  />
											</html:select>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_attributes" colspan=5>What does your division/branch hope to achieve through running this paper?<font color="red"> * </font> <br><br>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:textarea  property="description" styleClass="Text" cols="54" rows="6" onkeydown="fn_textCounter(this.form.description,this.form.numleft);" onkeyup="fn_textCounter(this.form.description,this.form.numleft);" onmouseover="fn_textCounter(this.form.description,this.form.numleft);" onmouseout="fn_textCounter(this.form.description,this.form.numleft);"></html:textarea>
											<INPUT TYPE="hidden" name="previewDescription">
									
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:textarea  property="description" styleClass="Text" cols="54" rows="6" onkeydown="fn_textCounter(this.form.description,this.form.numleft);" onkeyup="fn_textCounter(this.form.description,this.form.numleft);" onmouseover="fn_textCounter(this.form.description,this.form.numleft);" onmouseout="fn_textCounter(this.form.description,this.form.numleft);" readonly="true"></html:textarea>
										</logic:equal>
										<br>
										<!--div align="left">
											<font color="#005BCC">
												No. of characters remaining: 
												<input name="numleft" class="text_box" type="text" size="10" size="5" maxlength="3" value="500" disabled >
												<input name="exceed" id="exceed" type="hidden" size="60" value="" style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 0px solid #7F9DB9;border-right: 0px solid #7F9DB9;border-left: 0px solid #7F9DB9;border-top:0px solid #7F9DB9; color:red;text-decoration:none">
											</font>
										</div-->
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_attributes" colspan=5>Please fill in the instructions for the consultation paper:<br><br>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:textarea  property="strInstruction" styleClass="Text" cols="54" rows="6" onkeydown="fn_textCounter(this.form.strInstruction,this.form.numleft);" onkeyup="fn_textCounter(this.form.strInstruction,this.form.numleft);" onmouseover="fn_textCounter(this.form.strInstruction,this.form.numleft);" onmouseout="fn_textCounter(this.form.strInstruction,this.form.numleft);"></html:textarea>
											<INPUT TYPE="hidden" name="previewDescription">
									
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:textarea  property="strInstruction" styleClass="Text" cols="54" rows="6" onkeydown="fn_textCounter(this.form.strInstruction,this.form.numleft);" onkeyup="fn_textCounter(this.form.strInstruction,this.form.numleft);" onmouseover="fn_textCounter(this.form.strInstruction,this.form.numleft);" onmouseout="fn_textCounter(this.form.strInstruction,this.form.numleft);" readonly="true"></html:textarea>
										</logic:equal>
										<br>
										<!--div align="left">
											<font color="#005BCC">
												No. of characters remaining: 
												<input name="numleft" class="text_box" type="text" size="10" size="5" maxlength="3" value="500" disabled >
												<input name="exceed" id="exceed" type="hidden" size="60" value="" style="font-family: Arial, Helvetica, sans-serif;font-size:11px; background: #FFFFFF;border-bottom: 0px solid #7F9DB9;border-right: 0px solid #7F9DB9;border-left: 0px solid #7F9DB9;border-top:0px solid #7F9DB9; color:red;text-decoration:none">
											</font>
										</div-->
									</td>
								</tr>
								<logic:notPresent name="ConsultPageForm" property="attachedFile" >
									<tr>
										<td class="heading_attributes"> Attach paper</td>
										<td colspan="2" class="heading_attributes">
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<html:file  property="fileName" styleClass="Text_box" ></html:file>
												<br />(Max 2 MB) 
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
												<html:file  property="fileName" styleClass="Text_box" disabled="true"></html:file>
												<br />(Max 2 MB) 
											</logic:equal>
										</td>
										<td width="26" align="right" valign="middle" class="body_detail_text">&nbsp;</td>
										<td class="body_detail_text">&nbsp;</td>
									</tr>
								</logic:notPresent>
								<logic:present name="ConsultPageForm" property="attachedFile" >
									<tr>
										<td align="left" valign="top" class="body_detail_text" colspan="5">
											<a class="special_body_link"  href='<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do?attachedFileName=<bean:write name="ConsultPageForm" property="attachedFile"/>&paperId=<bean:write name="ConsultPageForm" property="paperId"/>&hiddenAction=downLoad'>
												Consultation Paper Attachment
											</a>
											&nbsp;&nbsp;&nbsp;
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<a class="special_body_link" href="#" onclick="javascript:fnRemove('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','removeAttachment');">
													<img src='<bean:message key="OIFM.docroot" />/images/remove.gif' alt="Remove Attachments" border="0">
												</a>
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											</logic:equal>
											<html:hidden name="ConsultPageForm" property="attachedFile"/>
										</td>
									</tr>
								</logic:present>
								<tr>
									<td valign="top" class="heading_attributes" colspan=5>How long will it take to respond to this consultation paper ? <font color="red"> *</font>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:text  property="strCompletionTime" styleClass="Text_box" size="4" maxlength="3"     onkeypress="return keyNumber(event)"></html:text>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:text  property="strCompletionTime" styleClass="Text_box" size="4" maxlength="4" readonly="true"></html:text>
										</logic:equal> minutes
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_attributes" colspan=5>
									<i>For queries on the consultation paper, respondents may contact : </i>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_attributes" >&nbsp;&nbsp;&nbsp;&nbsp;<i>Name<font color="red"> *</td>
									<td colspan="4" class="body_detail_text">
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:text  property="contactPerson" styleClass="Text_box" size="55" maxlength="66"></html:text>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:text  property="contactPerson" styleClass="Text_box" size="55" maxlength="66" readonly="true"></html:text>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_attributes" >&nbsp;&nbsp;&nbsp;&nbsp;<i>Contact Tel<font color="red"> *</td>
									<td colspan="4" class="body_detail_text">
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:text  property="phone" styleClass="Text_box" size="55" maxlength="15"></html:text>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:text  property="phone" styleClass="Text_box" size="55" maxlength="15" readonly="true"></html:text>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td valign="top" class="heading_attributes" >&nbsp;&nbsp;&nbsp;&nbsp;<i>Email address<font color="red"> *</td>
									<td colspan="4" class="body_detail_text">
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<html:text  property="emailAddress" styleClass="Text_box" size="55" maxlength="60"></html:text>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<html:text  property="emailAddress" styleClass="Text_box" size="55" maxlength="60" readonly="true"></html:text>
										</logic:equal>
									</td>
								</tr>
								<tr>
									<td class="heading_attributes" colspan=5>The paper will run from <font color="red"> * </font>
										<html:text  property="fromDt" styleClass="Text_box" size="13" readonly="true" ></html:text>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<a href="#" onClick="cal.select(document.forms['ConsultPageForm'].fromDt,'fromDt','dd-NNN-yyyy');return false;" name="fromAnchor" id="fromAnchor">
												<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' width="25" height="20" border="0" alt = "Calendar"></a>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' width="25" height="20" border="0" alt = "Calendar">
										</logic:equal>
										&nbsp;&nbsp;&nbsp;&nbsp; 
										to <font color="red"> * </font>
										<html:text  property="toDt" styleClass="Text_box" size="13" readonly="true" ></html:text>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
											<a href="#" onClick="cal.select(document.forms['ConsultPageForm'].toDt,'toDt','dd-NNN-yyyy');return false;" name="toAnchor" id="toAnchor">
												<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' width="25" height="20" border="0" alt = "Calendar"></a>
										</logic:equal>
										<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											<img src='<bean:message key="OIFM.docroot" />/images/img_calendar.gif' width="25" height="20" border="0" alt = "Calendar"></a>
										</logic:equal>
									</td>
								</tr>
								
									<tr class="sub_head">
										<td colspan="5" >
										<br>Selecting Target Group
										</td>
									</tr>

									<tr>
										<td valign="top" class="Heading_attributes" colspan=5>Would this consultation paper be open to all users ?
											<!--public or private -->
											<html:radio  styleClass="Text" property="security" value="U" onclick="javascript:fnDisable('Y');">Yes</html:radio> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 
											<html:radio  styleClass="Text" property="security" value="R" onclick="javascript:fnDisable('N');">No</html:radio>
										</td>
									</tr>

									<tr>
										<td valign="top" class="Heading_attributes" colspan=5>Please click on 'Select Groups' button to select the target groups for the paper. <b><font color="#FF0000">*</font></b><br>
										<html:text styleClass="Text_box" property="targetAudiance" size="50" maxlength="70" readonly="true"/>

										<a href="javascript:;" onClick="javascript:fnSelectGroups();"><img src='<bean:message key="OIFM.docroot" />/images/but_select_groups.gif' border="0" alt="Select Group"></a> 
										</td>
									</tr>


									<tr>
										<td valign="top" class="Heading_attributes" colspan=5><br>Would you like to open the paper to other users who have expressed interest in the issues <br>relevant to this paper?
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
												<logic:notEqual name="ConsultPageForm" property="strTagWords" value="">
													<bean:write name="ConsultPageForm" property="strTagWords"/>
												</logic:notEqual>
												<logic:equal name="ConsultPageForm" property="strTagWords" value="">
													Please click on "Select Tags" to select the key tag words for the paper
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
									<tr>
										<td valign="top" class="Heading_attributes" colspan=5><br>Would you like to send an email invitation to the group/s selected above?
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<html:radio property="reminderMode" value="A">Yes</html:radio>
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
												<html:radio property="reminderMode" value="A" disabled="true">Yes</html:radio>
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<html:radio property="reminderMode" value="M">No</html:radio>
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
												<html:radio property="reminderMode" value="M" disabled="true">No</html:radio>
											</logic:equal>
											<a href='#'  onClick="javascript:fnEditEmail();">Click here to edit email</a>
										</td>
									</tr>
									<tr>
										<td valign="top" class="Heading_attributes" colspan=5><br>When would you like the email invitation to be sent ? 
										<html:text styleClass="Text_box" property="strEmailDate" size="15" maxlength="11"  readonly="true" />
											
												<a href="#" onClick="cal.select(document.ConsultPageForm.strEmailDate,'strEmailDate','dd-NNN-yyyy');return false;" ><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a>
											
											<br>
											
										</td>
									</tr>

								<!--closing the loop ends -->
								
								<!--closing the loop starts -->
								<tr class="sub_head">
										<td colspan="5" >
										<br>Closing the Loop
										</td>
									</tr>

									<tr>
										<td valign="top" class="Heading_attributes" colspan=5>We will finalize the findings of the paper and publish the results by <B><font color="#FF0000">*</font></B>
										<html:text styleClass="Text_box" property="strFindingsPlannedDt" size="15" maxlength="11"  readonly="true" />
												<a href="#" onClick="cal.select(document.ConsultPageForm.strFindingsPlannedDt,'strFindingsPlannedDt','dd-NNN-yyyy');return false;" ><img src='<bean:message key="OIFM.docroot" />/images/insert_table.gif' width="25" height="24" border="0" align="absmiddle"></a>
											<br>
											via the following platforms :
										</td>
									</tr>

									<tr>
										<td valign="top" colspan=5><font size=1><i><u><br>Viewing of findings <br> </u></i></td>
									</tr>
									<tr>
										<td class="Heading_attributes" colspan=5>
										<logic:equal name="ConsultPageForm" property="security" value="U">
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="A">In MyForum, viewed by all OR</html:radio> <br>
										</logic:equal>
										<logic:notEqual name="ConsultPageForm" property="security" value="U">
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="A" disabled="true">In MyForum, viewed by all OR</html:radio> <br>
										</logic:notEqual>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="I">In MyForum viewed only by Invitees (private consultation papers only) OR</html:radio> <br>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="strViewFindingType" value="P">In My Forum, views only by respondents</html:radio><br>
										</td>
									</tr>

										<tr>
										<td valign="top" colspan=5><font size=1><i><u><br>Publicizing of findings <br> </u></i></td>
									</tr>
									<tr>
										<td class="Heading_attributes" colspan=5>
										<logic:equal name="ConsultPageForm" property="security" value="U">
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="publishStatus" value="A"> Email link to all OR</html:radio> <br>
										</logic:equal>
										<logic:notEqual name="ConsultPageForm" property="security" value="U">
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="publishStatus" value="A" disabled="true"> Email link to all OR</html:radio> <br>
										</logic:notEqual>
											
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="publishStatus" value="I"> Email link to Invitees (private consultation papers only) OR</html:radio> <br>
											&nbsp;&nbsp;&nbsp;&nbsp;<html:radio  styleClass="Text" property="publishStatus" value="P"> Email link to all respondents</html:radio><br>
										</td>
									</tr>
								
								<logic:present name="ConsultPageForm" property="paperId">						
									<tr>
										<td align="left" valign="top" class="Heading_attributes" colspan="4">
											Publish on Web &nbsp;&nbsp;&nbsp;
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<html:checkbox property="active" />
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
												<html:checkbox property="active" disabled="true" />
											</logic:equal>
											&nbsp;
										</td>									
										<td class="body_detail_text">&nbsp;</td>
									</tr>
								</logic:present>
								<tr>
									<td align="left" valign="top" class="body_detail_text">&nbsp;</td>
									<td colspan="3" valign="top" class="body_detail_text">&nbsp;</td>
									<td class="body_detail_text">&nbsp;</td>
								</tr>
								<logic:present name="ConsultPageForm" property="paperId">
									<tr>
										<td align="left" valign="top" class="sub_head" colspan=5>Questions<br><BR>
											<table width="100%"  border="0" align="left" cellpadding="0" cellspacing="0" class="Box">
												<logic:present name="ConsultPageForm" property="arOIBAConsultQuestion" scope="request">
												
													<logic:iterate id="questionListing" name="ConsultPageForm" property="arOIBAConsultQuestion" type="com.oifm.consultation.admin.OIFormConsultPaperHelper">
														<% int i=0;
															%>
															
															
														<tr >
															<td colspan="2" class="table_sub_head">
																Q : <bean:write name="questionListing" property="questionNo" />
																	<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
																		<a class="table_sub_head" href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultViewModifyQuestionAction.do?questionId=<bean:write name="questionListing" property="questionId"/>&paperId=<bean:write name="questionListing" property="paperId"/>&hiddenAction=populate','mywindow','width=500,height=460,menubar=no,toolbar=no,copyhistory=no,location=no,directories=no' );" >
																		<bean:write name="questionListing" property="question" /></a> 

																		
																	</logic:equal>
																	<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
																		<bean:write name="questionListing" property="question" />
																	</logic:equal>
															</td>
															<td align =right>
																<a href="#" onClick="javascript:submitReorderQuestion(
																'<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do',
																'QUESTION_MOVE_UP',
																'<bean:write name="questionListing" property="questionId"/>','<bean:write name="questionListing" property="canMoveUp"/>','<bean:write name="questionListing" property="canMoveDown"/>')"
																><img src='<bean:message key="OIFM.docroot" />/images/up_arrow_3.gif' border=0></a> 

																<a href="#" onClick="javascript:submitReorderQuestion(
																'<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do',
																'QUESTION_MOVE_DOWN', 
																'<bean:write name="questionListing" property="questionId"/>','<bean:write name="questionListing" property="canMoveUp"/>','<bean:write name="questionListing" property="canMoveDown"/>')"
																><img src='<bean:message key="OIFM.docroot" />/images/down_arrow_3.gif' border=0></a>
															</td>
														</tr>
														<logic:notEqual name="questionListing" property="likertScale" value="1">
														<logic:present name="questionListing" property="answer1">
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer1"/>
																</td>
															</tr>
														</logic:present>
														<logic:present name="questionListing" property="answer2">
															<tr class="Text">
																<td width="9%">&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer2"/>
																</td>
															</tr>
														</logic:present>
														<logic:present name="questionListing" property="answer3">
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer3"/>
																</td>
															</tr>
														</logic:present>
														<logic:present name="questionListing" property="answer4">
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer4"/>
																</td>
															</tr>
														</logic:present>
														<logic:present name="questionListing" property="answer5">
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer5"/>
																</td>
															</tr>
														</logic:present>
														<!-- 
														<logic:present name="questionListing" property="needOtherRemark">
														<logic:equal name="questionListing" property="needOtherRemark" value="1">
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	Other Remarks
																</td>
															</tr>
														</logic:equal>
														</logic:present>
														-->
														</logic:notEqual>
														<logic:equal name="questionListing" property="likertScale" value="1">
														
															<%
																int max = 0;
																if (questionListing.getAnswer5() != null)
																	max = 5;
																else if (questionListing.getAnswer4() != null)
																	max = 4;
																else if (questionListing.getAnswer3() != null)
																	max = 3;
																else if (questionListing.getAnswer2() != null)
																	max = 2;
															%>
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer1"/>
																</td>
															</tr>
															<tr class="Text">
																<td width="9%">&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer2"/>
																</td>
															</tr>
															<% if (max >= 3){ %>
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer3"/>
																</td>
															</tr>
															<% } %>
															<% if (max >= 4) { %>
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer4"/>
																</td>
															</tr>
															<% } %>
															<% if (max == 5) { %>
															<tr class="Text">
																<td>&nbsp;</td>
																<td colspan="2">
																	<%= ++i %>.
																	<bean:write name="questionListing" property="answer5"/>
																</td>
															</tr>
															<% } %>
														</logic:equal>
														<tr>
															<td colspan="3">
																<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
																	<a class="special_body_link" href="#" onclick="javascript:fnRemoveQuestion('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do',<bean:write name="questionListing" property="questionId"/>,'removeQuestion');">
																		<img src='<bean:message key="OIFM.docroot" />/images/remove.gif' border="0" alt = "Remove"></a>
																</logic:equal>
																&nbsp;
															</td>
														</tr>
													</logic:iterate>
												</logic:present>
												<tr>
													<td colspan="3">&nbsp;</td>
												</tr>
											</table>
										</td>
										<td class="body_detail_text">&nbsp;</td>
									</tr>
									<tr>
										<td align="left" valign="top" class="body_detail_text">&nbsp;</td>
										<td colspan="3" valign="top" class="body_detail_text">&nbsp;</td>
										<td class="body_detail_text">&nbsp;</td>
									</tr>
								</logic:present>
								<logic:present name="ConsultPageForm" property="paperId">
									<tr>										
										<td align="left" colspan="4" valign="top" class="body_detail_text">
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<a href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultCreateQuestionAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId"/>&hiddenAction=populate','mywindow','width=500,height=460' ); " >
													<img src='<bean:message key="OIFM.docroot" />/images/btn_Addquestion.gif' alt="Add new question" border="0"></a>

												<a href='<bean:message key="OIFM.contextroot" />/webConsultOpenPaperAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId"/>&hiddenAction=print' target=_new >
													<img src='<bean:message key="OIFM.docroot" />/images/print-preview.gif' alt="Print preview" border="0"></a>
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											</logic:equal>
										</td>
										<td class="body_detail_text"></td>
									</tr>
								</logic:present>
								<logic:notPresent name="ConsultPageForm" property="paperId">
									<tr>										
										<td align="left" colspan="4" valign="top" class="body_detail_text">
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
												<a class="special_body_link" alt="Save Consultation Paper and add a new question" href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultCreatePageAction.do','save');">Save and Add Question</a>
													<input type=hidden name="flag" value="true">
											</logic:equal>
											<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
											</logic:equal>
										</td>
										<td class="body_detail_text"></td>
									</tr>
								</logic:notPresent>
 
 					<tr>
						<td height="33" align="left" valign="middle" colspan="5">
							<html:hidden name="ConsultPageForm" property="paperId"/>
							<logic:notPresent name="ConsultPageForm" property="paperId">
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
									<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultCreatePageAction.do','save');">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif'  border="0" alt = "Save"></a> 
								</logic:equal>
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
								</logic:equal>
							</logic:notPresent>
							<logic:present name="ConsultPageForm" property="paperId">
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
									<a href="#" onclick="javascript:fnSubmit('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','update');">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Save.gif'  border="0" alt = "Save"></a> 
								</logic:equal>
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
								</logic:equal>

								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
									<a href="javascript:fnDelete('<bean:message key="OIFM.contextroot" />/consultViewModifyPageAction.do','delete');">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Delete.gif' alt="#" border="0" alt = "Delete"></a>
								</logic:equal>
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
								</logic:equal>
							</logic:present>
							<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
								<a href="#" onclick="javascript:fnClear()">
									<img src='<bean:message key="OIFM.docroot" />/images/btn_Clear.gif' border="0" alt = "Reset"></a>
							</logic:equal>
							<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
							</logic:equal>

							<a href="#" onclick="javascript:fnSubmit1('<bean:message key="OIFM.contextroot" />/consultListingAction.do','populate');">
								<img src='<bean:message key="OIFM.docroot" />/images/btn_Cancel.gif' border="0" alt = "Cancel"></a>

							<logic:present name="ConsultPageForm" property="paperId">
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="false">
									<a href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId"/>&hiddenAction=export'); ">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Export_consultation_pap.gif' border="0" alt = "Export Consultation Paper"></a>
									<logic:equal name="publishFlag" value="true" scope="request">
										<a href="#" onclick="javascript:fnSubmit2('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do','populate');">
											<img src='<bean:message key="OIFM.docroot" />/images/publish_consultation_paper.gif'  border="0" alt = "Publish Consultation Paper"></a>
									</logic:equal>

									<logic:equal name="publishFlag" value="false" scope="request">
										<a href="#" onclick="javascript:alert('<bean:message key="OI.CONS.PAPER.ACTIVE.PUBLISHED" />');">
											<img src='<bean:message key="OIFM.docroot" />/images/publish_consultation_paper.gif' alt = "Publish Consultation Paper" border="0"></a>
									</logic:equal>
								</logic:equal>
								<logic:equal name="ConsultPageForm" property="readOnlyFlag" value="true">
									<a href="#" onclick="javascript:window.open('<bean:message key="OIFM.contextroot" />/consultPaperPublishAction.do?paperId=<bean:write name="ConsultPageForm" property="paperId"/>&hiddenAction=export'); ">
										<img src='<bean:message key="OIFM.docroot" />/images/btn_Export_consultation_pap.gif' border="0" alt = "Export Consultation Paper"></a>
								</logic:equal>
							</logic:present>
							<html:hidden name="ConsultPageForm" property="readOnlyFlag"/>
							<html:hidden name="ConsultPageForm" property="hiddenAction"/>
							<html:hidden name="ConsultPageForm" property="strTargetGpIds"/>
							<html:hidden name="ConsultPageForm" property="strEmailMessage"/>
							<html:hidden name="ConsultPageForm" property="strDefaultEmailMessage" />
							
							<input type=hidden name="questionId">
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
	<script language="javascript">
		//fn_textCounter(this.ConsultPageForm.description,this.ConsultPageForm.numleft);
	</script>
	<script language="javascript">
		var hidTags = document.getElementById('spanTagName').innerHTML;
	</script>
</html:form>
<%
}
catch(Exception e)
{
	out.println(e.getMessage());
}
%>
