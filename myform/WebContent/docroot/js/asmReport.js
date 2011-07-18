function fnSubmitPageReport(nPageNo){
	try{
		document.ASMFormReport.hiddenAction.value= "SearchResult"
		document.ASMFormReport.hidLink.value=nPageNo
		document.ASMFormReport.action ="ASMReport.do"
		document.ASMFormReport.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSubmitPage(nPageNo) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnSearch(){
	try{
		if(!fnValidation()){
			return false;
		}
		var divincharge="";
		  for (var i = 0; i < document.ASMFormReport.cboDivInCharge.options.length; i++){
			if (document.ASMFormReport.cboDivInCharge.options[i].selected){
				if(divincharge==""){
					divincharge = document.ASMFormReport.cboDivInCharge.options[i].value
				}else{
					divincharge = divincharge +","+document.ASMFormReport.cboDivInCharge.options[i].value
				}
			  }
		  }
		 var category="";
		  for (var i = 0; i < document.ASMFormReport.cboCategory.options.length; i++){
			if (document.ASMFormReport.cboCategory.options[i].selected){
				if(category==""){
					category = document.ASMFormReport.cboCategory.options[i].value
				}else{
					category = category +","+document.ASMFormReport.cboCategory.options[i].value
				}
			  }
		  }
		  var schooldivision="";
		  for (var i = 0; i < document.ASMFormReport.txtDivisionLW.options.length; i++){
			if (document.ASMFormReport.txtDivisionLW.options[i].selected){
				if(schooldivision==""){
					schooldivision = document.ASMFormReport.txtDivisionLW.options[i].value
				}else{
					schooldivision = schooldivision +","+document.ASMFormReport.txtDivisionLW.options[i].value
				}
			  }
		  }


		document.ASMFormReport.hidDivIncharge.value =divincharge
		document.ASMFormReport.hidSchDiv.value =schooldivision
		document.ASMFormReport.hidCategory.value =category
		document.ASMFormReport.hidLink.value=0
		document.ASMFormReport.hiddenAction.value= "SearchResult"
		document.ASMFormReport.action ="ASMReport.do"
		document.ASMFormReport.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSearch() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnSortBy(param){
	try{
		document.ASMFormReport.hidSortBy.value=param
		document.ASMFormReport.hiddenAction.value= "SearchResult"
		document.ASMFormReport.action ="ASMReport.do"
		document.ASMFormReport.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSortBy(param) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}


function fnExport(){
	try{
		
		document.ASMFormReport.target ="ASMReport"
		document.ASMFormReport.hidLink.value=0
		document.ASMFormReport.hiddenAction.value= "ExportExcel"
		document.ASMFormReport.action ="ASMReport.do"
		document.ASMFormReport.submit();
		document.ASMFormReport.target ="_self"
	}
	catch(err)
	{
		alert('Function Name : fnSearch() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnSelectUser(inputObj){
	try{
		
		objName = inputObj;
		if(objName=="RepliedBy"){
			help_window = window.open("ASMUserDetails.do?hiddenAction=SeniorManagementUserList",'popUpWin','left=1,top=1,width=650,height=600,scrollbars=yes');
		}else{
			help_window = window.open("ASMUserDetails.do?hiddenAction=DivisionUserList",'popUpWin','left=1,top=1,width=650,height=600,scrollbars=yes');
		}
	}
	catch(err)
	{
		alert('Function Name : fnSelectUser() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

//This function is to clear all the fields
function fnCancel(){
	try{
		document.ASMFormReport.txtSubOnFromDate.value ="";
		document.ASMFormReport.txtSubOnToDate.value ="";
		document.ASMFormReport.txtWrittenBy.value ="";
		document.ASMFormReport.txtDesigLW.value ="";
		document.ASMFormReport.txtDivisionLW.value ="";
		document.ASMFormReport.hidLetterTopic.value ="";
		document.ASMFormReport.txtLetterContent.value ="";
		document.ASMFormReport.txtYISFromDate.value ="";
		document.ASMFormReport.txtYISToDate.value ="";
		document.ASMFormReport.txtAgeFromDate.value ="";
		document.ASMFormReport.txtAgeToDate.value ="";
		//document.ASMFormReport.cboDivInCharge.value ="";
		  for (var i = 0; i < document.ASMFormReport.txtDivisionLW.options.length; i++){
			document.ASMFormReport.txtDivisionLW.options[i].selected=false;
		  }
		  for (var i = 0; i < document.ASMFormReport.cboDivInCharge.options.length; i++){
			document.ASMFormReport.cboDivInCharge.options[i].selected=false;
		  }
		  for (var i = 0; i < document.ASMFormReport.cboCategory.options.length; i++){
			document.ASMFormReport.cboCategory.options[i].selected=false;
		  }
		document.ASMFormReport.txtRedirectTo.value ="";
		document.ASMFormReport.txtRedirectFromDate.value ="";
		document.ASMFormReport.txtRedirectToDate.value ="";
		document.ASMFormReport.txtRepliedBy.value ="";
		document.ASMFormReport.txtReplyFromDate.value ="";
		document.ASMFormReport.txtReplyToDate.value ="";
		document.ASMFormReport.txtReplycontent.value ="";
		
		document.ASMFormReport.hidDivIncharge.value ="";
		document.ASMFormReport.hidSchDiv.value ="";
		document.ASMFormReport.hidCategory.value ="";
		document.ASMFormReport.hidLink.value =0;
		document.ASMFormReport.hidSortBy.value ="";

		document.ASMFormReport.chkSubmitOn.checked= false;
		document.ASMFormReport.chkWrittenBy.checked= false;
		document.ASMFormReport.chkDesigLW.checked= false;

		document.ASMFormReport.chkDivisionLW.checked= false;
		document.ASMFormReport.chkLetterContent.checked= false;
		document.ASMFormReport.chkYIS.checked= false;
		document.ASMFormReport.chkAge.checked= false;
		document.ASMFormReport.chkDivInCharge.checked= false;
		document.ASMFormReport.chkCategory.checked= false;
		document.ASMFormReport.chkRedirectTo.checked= false;
		document.ASMFormReport.chkRedirectOn.checked= false;
		document.ASMFormReport.chkRepliedBy.checked= false;
		document.ASMFormReport.chkRepliedOn.checked= false;
		document.ASMFormReport.chkReplyContent.checked= false;


	}
	catch(err)
	{
		alert('Function Name : fnCancel() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

//This function is to highlight the multiple selected values of divinsion in charge and division&school combo box
function fnHighLightcombo(){
	try{
		// Division in charge
		if(document.ASMFormReport.hidDivIncharge.value!=""){
			var indElem= document.ASMFormReport.hidDivIncharge.value.split(",");
			for(var i=0;i<indElem.length;i++){
				  for (var j = 0; j < document.ASMFormReport.cboDivInCharge.options.length; j++){
					if(document.ASMFormReport.cboDivInCharge.options[j].value== indElem[i]){
						document.ASMFormReport.cboDivInCharge.options[j].selected = true;
					}
				  }
			}
		}
			
		// Category
		if(document.ASMFormReport.hidCategory.value!=""){
			var indElem= document.ASMFormReport.hidCategory.value.split(",");
			for(var i=0;i<indElem.length;i++){
				  for (var j = 0; j < document.ASMFormReport.cboCategory.options.length; j++){
					if(document.ASMFormReport.cboCategory.options[j].value== indElem[i]){
						document.ASMFormReport.cboCategory.options[j].selected = true;
					}
				  }
			}
		}
			
		//School/Division of Letter writer
		if(document.ASMFormReport.hidSchDiv.value!=""){
			var indElem= document.ASMFormReport.hidSchDiv.value.split(",");
			for(var i=0;i<indElem.length;i++){
				  for (var j = 0; j < document.ASMFormReport.txtDivisionLW.options.length; j++){
					if(document.ASMFormReport.txtDivisionLW.options[j].value== indElem[i]){
						document.ASMFormReport.txtDivisionLW.options[j].selected = true;
					}
				  }
			}
		}
	}catch(err)
	{
		alert('Function Name : fnHighLightcombo() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnCallDetails(letterid){
	try{
		
		document.ASMFormReport.hidPageDesc.value ="RecentLetters";
		document.ASMFormReport.hidLetterID.value =letterid;
		document.ASMFormReport.hiddenAction.value ="ReportSearch";
		document.ASMFormReport.action ="asmHome.do";
		document.ASMFormReport.submit();
	}
	catch(err)
	{
		alert('Function Name : fnCallDetails() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}


function fnValidation(){
	try{
		//Submitted on Validation
		if(document.ASMFormReport.txtSubOnFromDate.value=="" && document.ASMFormReport.txtSubOnToDate.value!=""){
			alert("Please Enter Submitted on From Date");
			return false;
		}
		else if(document.ASMFormReport.txtSubOnFromDate.value!="" && document.ASMFormReport.txtSubOnToDate.value==""){
			alert("Please Enter Submitted on To Date");
			return false;
		}
		else if (! compareTwoDates(document.ASMFormReport.txtSubOnFromDate.value,document.ASMFormReport.txtSubOnToDate.value,"Submitted on From Date", "Submitted on To Date", false)){
			return false;
		}
		//Years in service validation
		else if(document.ASMFormReport.txtYISFromDate.value!="" && isNaN(document.ASMFormReport.txtYISFromDate.value)){
			alert("Please Enter a valid years in service From field");
			document.ASMFormReport.txtYISFromDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtYISToDate.value!="" && isNaN(document.ASMFormReport.txtYISToDate.value)){
			alert("Please Enter a valid years in service To field");
			document.ASMFormReport.txtYISToDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtYISFromDate.value=="" && document.ASMFormReport.txtYISToDate.value!=""){
			alert("Please Enter years in service From field");
			document.ASMFormReport.txtYISFromDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtYISFromDate.value!="" && document.ASMFormReport.txtYISToDate.value==""){
			alert("Please Enter years in service To field");
			document.ASMFormReport.txtYISToDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtYISFromDate.value!="" && document.ASMFormReport.txtYISToDate.value!=""){
			if(parseInt(document.ASMFormReport.txtYISFromDate.value)>parseInt(document.ASMFormReport.txtYISToDate.value)){
				alert("Years in service From cannot be greater than To field");
				document.ASMFormReport.txtYISFromDate.focus();
				return false;
			}
		}
		//Age validation
		if(document.ASMFormReport.txtAgeFromDate.value!="" && isNaN(document.ASMFormReport.txtAgeFromDate.value)){
			alert("Please Enter a valid Age From field");
			document.ASMFormReport.txtAgeFromDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtAgeToDate.value!="" && isNaN(document.ASMFormReport.txtAgeToDate.value)){
			alert("Please Enter a valid Age To field");
			document.ASMFormReport.txtAgeToDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtAgeFromDate.value=="" && document.ASMFormReport.txtAgeToDate.value!=""){
			alert("Please Enter Age From field");
			document.ASMFormReport.txtAgeFromDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtAgeFromDate.value!="" && document.ASMFormReport.txtAgeToDate.value==""){
			alert("Please Enter Age To field");
			document.ASMFormReport.txtAgeToDate.focus();
			return false;
		}
		else if(document.ASMFormReport.txtAgeFromDate.value!="" && document.ASMFormReport.txtAgeToDate.value!=""){
			if(parseInt(document.ASMFormReport.txtAgeFromDate.value)>parseInt(document.ASMFormReport.txtAgeToDate.value)){
				alert("Age From cannot be greater than To field");
				document.ASMFormReport.txtAgeFromDate.focus();
				return false;
			}
		}
		//Redirected on validation
		if(document.ASMFormReport.txtRedirectFromDate.value=="" && document.ASMFormReport.txtRedirectToDate.value!=""){
			alert("Please Enter Redirected on From Date");
			return false;
		}
		else if(document.ASMFormReport.txtRedirectFromDate.value!="" && document.ASMFormReport.txtRedirectToDate.value==""){
			alert("Please Enter Redirected on To Date");
			return false;
		}
		else if (! compareTwoDates(document.ASMFormReport.txtRedirectFromDate.value,document.ASMFormReport.txtRedirectToDate.value,"Redirected on From Date", "Redirected on To Date", false)){
			return false;
		}
		//Replied on Validation
		if(document.ASMFormReport.txtReplyFromDate.value=="" && document.ASMFormReport.txtReplyToDate.value!=""){
			alert("Please Enter Replied on From Date");
			return false;
		}
		else if(document.ASMFormReport.txtReplyFromDate.value!="" && document.ASMFormReport.txtReplyToDate.value==""){
			alert("Please Enter Replied on To Date");
			return false;
		}
		else if (! compareTwoDates(document.ASMFormReport.txtReplyFromDate.value,document.ASMFormReport.txtReplyToDate.value,"Replied on From Date", "Replied on To Date", false)){
			return false;
		}

		return true;
	}
	catch(err)
	{
		alert('Function Name : fnValidation() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
		return false;
	}
}
