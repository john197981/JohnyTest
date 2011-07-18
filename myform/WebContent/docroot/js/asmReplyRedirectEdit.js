var objName;
function fnSave(){
	try{
		if(!fnValidation()){
			return false;
		}
		document.ASMFormReplyRedirectEdit.hiddenAction.value ="Save"
		document.ASMFormReplyRedirectEdit.action ="ASMReplyRedirectEdit.do"
		document.ASMFormReplyRedirectEdit.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSave() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}
function fnDelete()
		{
		
		if (window.confirm('Are you sure you want to delete the Letter')==false)
			{
				return;
			} 
		else{	
				document.ASMFormReplyRedirectEdit.hiddenAction.value="Delete";
				document.ASMFormReplyRedirectEdit.action="ASMReplyRedirectEdit.do";
				document.ASMFormReplyRedirectEdit.submit();
			}	
		}	
		
function fnCancel(){
	try{
		document.ASMFormReplyRedirectEdit.hiddenAction.value ="AdminPage"
		document.ASMFormReplyRedirectEdit.action ="ASMReplyRedirect.do"
		document.ASMFormReplyRedirectEdit.submit();
	}
	catch(err)
	{
		alert('Function Name : fnCancel() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnSelectUser(inputObj){
	try{
		
		if(inputObj =="TO" && document.ASMFormReplyRedirectEdit.cboDivision.value==""){
			alert("Please Enter Division");
			document.ASMFormReplyRedirectEdit.cboDivision.focus();
			return false;
		}
		objName = inputObj;

		if(objName=="RepliedBy"){
			help_window = window.open("ASMUserDetails.do?hiddenAction=SeniorManagementUserList&cboDivision="+document.ASMFormReplyRedirectEdit.cboDivision.value,'popUpWin','left=1,top=1,width=650,height=600,scrollbars=yes');
		}else{
			help_window = window.open("ASMUserDetails.do?hiddenAction=DivisionUserList&cboDivision="+document.ASMFormReplyRedirectEdit.cboDivision.value,'popUpWin','left=1,top=1,width=650,height=600,scrollbars=yes');
		}
	}
	catch(err)
	{
		alert('Function Name : fnSelectUser() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}


function fnSendReminder(){
	try{
		if(document.ASMFormReplyRedirectEdit.txtTo.value==""){
				alert("Please Enter Email Address in the To Address");
				document.ASMFormReplyRedirectEdit.txtTo.focus();
				return false;
		}
		document.ASMFormReplyRedirectEdit.action="ASMReplyRedirectEdit.do"
		document.ASMFormReplyRedirectEdit.hiddenAction.value="SendRemainder"
		document.ASMFormReplyRedirectEdit.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSendReminder() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnRedirect(){
	try{
		if(document.ASMFormReplyRedirectEdit.cboDivision.value==""){
				alert("Please Enter Division");
				document.ASMFormReplyRedirectEdit.cboDivision.focus();
				return false;
		}
		else if(document.ASMFormReplyRedirectEdit.txtTo.value==""){
				alert("Please Enter Email Address in the To Address");
				document.ASMFormReplyRedirectEdit.txtTo.focus();
				return false;
		}
		
		document.ASMFormReplyRedirectEdit.action="ASMReplyRedirectEdit.do"
		document.ASMFormReplyRedirectEdit.hiddenAction.value="Redirect"
		document.ASMFormReplyRedirectEdit.submit();
		//window.open("ASMReplyRedirectEdit.do?hiddenAction=Redirect",'Redirect','left=1,top=1,width=650,height=600');
	}
	catch(err)
	{
		alert('Function Name : fnRedirect() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnRadioRemainder(){
	try{

		//if(document.ASMFormReplyRedirectEdit.hidStatus.value=="S" || document.ASMFormReplyRedirectEdit.hidStatus.value=="T"){
		if(document.ASMFormReplyRedirectEdit.hidStatus.value=="T"){
			document.getElementById(11).style.display = "";
			document.getElementById(12).style.display = "";

			// If the lettser is submitted or redirected then show the remainder status else disable
			if(document.ASMFormReplyRedirectEdit.radRemainder[1].checked){
				document.getElementById(10).style.display = "";
				document.getElementById(20).style.display = "none";
				document.getElementById(21).style.display = "";
			}else{
				document.getElementById(10).style.display = "none";
				document.getElementById(20).style.display = "";
				document.getElementById(21).style.display = "none";
			}

		}else{
			document.getElementById(10).style.display = "none";
			document.getElementById(11).style.display = "none";
			document.getElementById(12).style.display = "none";
			document.getElementById(20).style.display = "none";
			document.getElementById(21).style.display = "none";
		}

	}
	catch(err)
	{
		alert('Function Name : fnRadioRemainder() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnClickPublish(){
	try{
		if(document.ASMFormReplyRedirectEdit.hidStatus.value!="S"){
			if(document.ASMFormReplyRedirectEdit.chkPublishWebSite.checked){
				document.getElementById(13).style.display = "";
				document.getElementById(14).style.display = "";
			}else{
				document.getElementById(13).style.display = "none";
				document.getElementById(14).style.display = "none";

				document.ASMFormReplyRedirectEdit.txtPublishOn.value="";
				document.ASMFormReplyRedirectEdit.txtShowFrom.value="";
				document.ASMFormReplyRedirectEdit.txtShowTo.value="";
			}
		}
	}
	catch(err)
	{
		alert('Function Name : fnClickPublish() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnDivision(){
	try{
		//document.ASMFormReplyRedirectEdit.txtTo.value ="";
	}
	catch(err)
	{
		alert('Function Name : fnClickPublish() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}
function fnModalWindow(){
	try{
	help_window.focus();
	}
	catch(err){
	}
}


function fnValidation(){
	try{
		if(document.ASMFormReplyRedirectEdit.cboDivision.value==""){
				alert("Please Enter Division");
				document.ASMFormReplyRedirectEdit.cboDivision.focus();
				return false;
		}

		//Check Topic 
		else if(document.ASMFormReplyRedirectEdit.txtTopic.value==""){
			alert("Please enter Topic");
			document.ASMFormReplyRedirectEdit.txtTopic.focus();
			return false;
		}
		//Replied on Date
		//else if(document.ASMFormReplyRedirectEdit.txtRepliedOn.value==""){
		//	alert("Please enter Replied on date");
		//	return false;
		//}

		//Publish on date should be present if publish on checkbox is checked
		if(document.ASMFormReplyRedirectEdit.chkPublishWebSite.checked){
			if(document.ASMFormReplyRedirectEdit.txtPublishOn.value==""){
				alert("Please enter Published on date");
				return false;
			}
			else if(document.ASMFormReplyRedirectEdit.txtShowFrom.value=="" && document.ASMFormReplyRedirectEdit.txtShowTo.value!=""){
				alert("Please Enter Show on website From Date");
				return false;
			}
			else if(document.ASMFormReplyRedirectEdit.txtShowFrom.value!="" && document.ASMFormReplyRedirectEdit.txtShowTo.value==""){
				alert("Please Enter Show on website To Date");
				return false;
			}
			else if (! compareTwoDates(document.ASMFormReplyRedirectEdit.txtShowFrom.value,document.ASMFormReplyRedirectEdit.txtShowTo.value,"Show on website From Date", "Show on website To Date", false)){
				return false;
			}
			// Publish Date
			if(publishOnDate==""){
				//If the publish on date is entered newly then check whether it is later than or equal to current date
				if (! compareTwoDates(currentDate,document.ASMFormReplyRedirectEdit.txtPublishOn.value,"Today's Date", "Publish On Date", false)){
					return false;
				}
			}
			//If the publish on date is already present then check whether it has been modified, if it is modified then check the
			//publish on date is later than or equal to current date
			else{
				if(publishOnDate != document.ASMFormReplyRedirectEdit.txtPublishOn.value){
					if (! compareTwoDates(currentDate,document.ASMFormReplyRedirectEdit.txtPublishOn.value,"Today's Date", "Publish On Date", false)){
						return false;
					}
				}
			}

			if(showFrom==""){
				//If the Show From date is entered newly then check whether it is later than or equal to current date
				if (! compareTwoDates(currentDate,document.ASMFormReplyRedirectEdit.txtShowFrom.value,"Today's Date", "Show on webiste From Date", false)){
					return false;
				}
			}
			//If the Show From date is already present then check whether it has been modified, if it is modified then check the
			//Show From date is later than or equal to current date
			else{
				if(showFrom!= document.ASMFormReplyRedirectEdit.txtShowFrom.value){
					if (! compareTwoDates(currentDate,document.ASMFormReplyRedirectEdit.txtShowFrom.value,"Today's Date", "Show on webiste From Date", false)){
						return false;
					}
				}
			}

			if(showTo==""){
				//If the Show To date is entered newly then check whether it is later than or equal to current date
				if (! compareTwoDates(currentDate,document.ASMFormReplyRedirectEdit.txtShowTo.value,"Today's Date", "Show on webiste To Date", false)){
					return false;
				}
			}
			//If the Show To date is already present then check whether it has been modified, if it is modified then check the
			//Show To date is later than or equal to current date
			else{
				if(showTo!= document.ASMFormReplyRedirectEdit.txtShowTo.value){
					if (! compareTwoDates(currentDate,document.ASMFormReplyRedirectEdit.txtShowTo.value,"Today's Date", "Show on webiste To Date", false)){
						return false;
					}
				}
			}

		}


		return true;
	}
	catch(err)
	{
		alert('Function Name : fnValidation() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
		return false;
	}
}
