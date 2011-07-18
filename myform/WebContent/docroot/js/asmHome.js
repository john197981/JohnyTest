function fnAlertEmail(){
	 var strUrl = '/AlertFriendAction.do?hiddenAction=populate&module=ASM&iD='+document.ASMFormHome.hidLetterID.value;
	 //help_window  = window.open(contextRoot+strUrl,'SelectUsers','width=900,height=260,left=0,top=0,resizable=no,scrollbars=yes');
	 window.showModalDialog(contextRoot+strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
	 //help_window.focus();

}

function fnEditorNotesAlertEmail(){
	 var strUrl = '/AlertFriendAction.do?hiddenAction=populate&module=ASMEditorNote&iD='+document.ASMFormHome.hidLetterID.value;
	 window.showModalDialog(contextRoot+strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
}

function fnPrintPreview(){
	try{
		help_window = window.open("asmHome.do?hiddenAction=PrintPreview&hidLetterID="+document.ASMFormHome.hidLetterID.value,'printPreview','left=1,top=1,width=750,height=500,scrollbars=yes');
	}
	catch(err)
	{
		alert('Function Name : fnPrintPreview() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}


function fnPrint(){
	try{
		document.getElementById(1).style.display = "none";
		document.getElementById(2).style.display = "none";
		window.print();
		document.getElementById(1).style.display = "";
		document.getElementById(2).style.display = "";
	}
	catch(err)
	{
		alert('Function Name : fnPrint() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}

//this function is called On click of recent letter link in the right side portal
function fnRecentLetter(inpVal){
	try{
		//document.ASMFormHome.action ="asmHome.do";
		document.ASMFormHome.hidPageDesc.value ="RecentLetters";
		document.ASMFormHome.hidLetterID.value =inpVal;
		document.ASMFormHome.hiddenAction.value ="populate";

		document.ASMFormHome.action ="asmHome.do?hidPageDesc=RecentLetters&hiddenAction=populate&hidLetterID="+inpVal;
		document.ASMFormHome.submit();
	}
	catch(err)
	{
		alert('Function Name : fnRecentLetter() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

//this function is called On click of Previous button in the recent letter
function fnPrevious(totalRec){
	try{
		//enable previous and next button
		for(var i=0;i<totalRec ;i++){
			if(document.ASMFormHome.hidLetterID.value == 
				document.ASMFormHome.hidRecLtrID[i].value){
				document.ASMFormHome.hidLetterID.value =document.ASMFormHome.hidRecLtrID[i-1].value;
				break;
			}
		}

		document.ASMFormHome.hidPageDesc.value ="RecentLetters";
		document.ASMFormHome.action ="asmHome.do?hidPageDesc=RecentLetters&hiddenAction=populate&hidLetterID="+document.ASMFormHome.hidLetterID.value;
		document.ASMFormHome.submit();
	}
	catch(err)
	{
		alert('Function Name : fnPrevious() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

//this function is called On click of Next button in the recent letter
function fnNext(totalRec){
	try{
		//enable previous and next button
		for(var i=0;i<totalRec ;i++){
			if(document.ASMFormHome.hidLetterID.value == 
				document.ASMFormHome.hidRecLtrID[i].value){
				document.ASMFormHome.hidLetterID.value =document.ASMFormHome.hidRecLtrID[i+1].value;
				break;
			}
		}

		document.ASMFormHome.hidPageDesc.value ="RecentLetters"
		document.ASMFormHome.action ="asmHome.do?hidPageDesc=RecentLetters&hiddenAction=populate&hidLetterID="+document.ASMFormHome.hidLetterID.value;
		document.ASMFormHome.submit();
	}
	catch(err)
	{
		alert('Function Name : fnNext() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnDisplayHidePrevNext(totalSize){
	try{
		var totalRec=0;
		if(totalSize!="" && totalSize!="null"){
			totalRec =totalSize
		}
		if(totalRec<=1){
			//disable previous and next button
			document.getElementById(5).style.display = "none";
			document.getElementById(6).style.display = "none";
		}
		else{
			//enable previous and next button
			for(var i=0;i<totalRec ;i++){
				if(document.ASMFormHome.hidLetterID.value == 
						document.ASMFormHome.hidRecLtrID[i].value){
					if(i==0){
						//disable previous and enable next
						document.getElementById(5).style.display = "none";
						document.getElementById(6).style.display = "";

					}
					else if (i==totalRec-1){
						//disable next and enable previous
							document.getElementById(5).style.display = "";
							document.getElementById(6).style.display = "none";
					}
					else{
						//enable both 
						document.getElementById(5).style.display = "";
						document.getElementById(6).style.display = "";

					}

				}

			}
		}
	}
	catch(err)
	{
		alert('Function Name : fnDisplayHidePrevNext() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

//This function is called when the Division is changed in the user list
function fnChangeDivision(){
	try{
		fnUnSelectAll();
		document.ASMFormCommon.hidLink.value=0;
		document.ASMFormCommon.submit();
	}
	catch(err)
	{
		alert('Function Name : fnChangeDivision() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnSubmitPage(nPageNo){
	try{
		
		fnUnSelectAll();
		document.ASMFormCommon.hidLink.value=nPageNo
		document.ASMFormCommon.action ="ASMUserDetails.do"
		document.ASMFormCommon.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSubmitPage(nPageNo) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}

function fnClosewindow(){
	try{
		window.close();
	}
	catch(err)
	{
		alert('Function Name : fnClosewindow() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}

function fnAddUser(){
	try{
		var totalMailID="";
		//If there is only one row then take as an individual element  or treat it as an array		
		if(totalRec<=1){
			if(document.ASMFormCommon.chkSelect.checked && document.ASMFormCommon.hidEmailID.value!=""){
					if(totalMailID!=""){
						totalMailID = totalMailID+",";
					}
					totalMailID =totalMailID + document.ASMFormCommon.hidEmailID.value + totalMailID ;
			}
		}else{
			for(var i=0;i<totalRec;i++){
				if(document.ASMFormCommon.chkSelect[i].checked && document.ASMFormCommon.hidEmailID[i].value!=""){

						if(totalMailID!=""){
							totalMailID = totalMailID+",";
						}
						totalMailID =totalMailID  + document.ASMFormCommon.hidEmailID[i].value ;
				}
			}
		}
		if(opener.objName=="CC"){
			if(window.opener.document.ASMFormReplyRedirectEdit.txtCc.value !="" && totalMailID!=""){
				window.opener.document.ASMFormReplyRedirectEdit.txtCc.value =
					window.opener.document.ASMFormReplyRedirectEdit.txtCc.value +","
				}
				window.opener.document.ASMFormReplyRedirectEdit.txtCc.value=
					window.opener.document.ASMFormReplyRedirectEdit.txtCc.value + totalMailID

		}
		else if(opener.objName=="BCC"){
			if(window.opener.document.ASMFormReplyRedirectEdit.txtBcc.value !="" && totalMailID!=""){
				window.opener.document.ASMFormReplyRedirectEdit.txtBcc.value =
					window.opener.document.ASMFormReplyRedirectEdit.txtBcc.value +","
				}
				window.opener.document.ASMFormReplyRedirectEdit.txtBcc.value=
					window.opener.document.ASMFormReplyRedirectEdit.txtBcc.value + totalMailID

		}
		else if(opener.objName=="TO"){
			if(window.opener.document.ASMFormReplyRedirectEdit.txtTo.value !="" && totalMailID!=""){
				window.opener.document.ASMFormReplyRedirectEdit.txtTo.value =
					window.opener.document.ASMFormReplyRedirectEdit.txtTo.value +","
				}
				window.opener.document.ASMFormReplyRedirectEdit.txtTo.value=
					window.opener.document.ASMFormReplyRedirectEdit.txtTo.value + totalMailID
		}
		else if(opener.objName=="RepliedBy"){
			try{
			if(window.opener.document.ASMFormReport.txtRepliedBy.value !="" && totalMailID!=""){
				window.opener.document.ASMFormReport.txtRepliedBy.value =
					window.opener.document.ASMFormReport.txtRepliedBy.value +","
				}
				window.opener.document.ASMFormReport.txtRepliedBy.value=
					window.opener.document.ASMFormReport.txtRepliedBy.value + totalMailID
			}catch(err){
			if(window.opener.document.ASMFormReplyRedirectEdit.txtRepliedBy.value !="" && totalMailID!=""){
				window.opener.document.ASMFormReplyRedirectEdit.txtRepliedBy.value =
					window.opener.document.ASMFormReplyRedirectEdit.txtRepliedBy.value +","
				}
				window.opener.document.ASMFormReplyRedirectEdit.txtRepliedBy.value=
					window.opener.document.ASMFormReplyRedirectEdit.txtRepliedBy.value + totalMailID

				}
		}
		else if(opener.objName=="RedirectTo"){
			if(window.opener.document.ASMFormReport.txtRedirectTo.value !="" && totalMailID!=""){
				window.opener.document.ASMFormReport.txtRedirectTo.value =
					window.opener.document.ASMFormReport.txtRedirectTo.value +","
				}
				window.opener.document.ASMFormReport.txtRedirectTo.value=
					window.opener.document.ASMFormReport.txtRedirectTo.value + totalMailID
		}

	}
	catch(err)
	{
		alert('Function Name : fnAddUser() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}

function fnSelectAll(){
	try{
		if(totalRec==1){
			if(document.ASMFormCommon.chkSelectAll.checked){
					document.ASMFormCommon.chkSelect.checked =true;
			}else{
				document.ASMFormCommon.chkSelect.checked =false;
			}
		}else if(totalRec>1){
			for(var i=0;i<totalRec;i++){
				if(document.ASMFormCommon.chkSelectAll.checked){
					document.ASMFormCommon.chkSelect[i].checked =true;
				}else{
					document.ASMFormCommon.chkSelect[i].checked =false;
				}
			}
		}
	}
	catch(err)
	{
		alert('Function Name : fnSelectAll() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnUnSelectAll(){
	try{
		if(totalRec>=1){
			document.ASMFormCommon.chkSelectAll.checked =false
			fnSelectAll();
		}
	}
	catch(err)
	{
		alert('Function Name : fnUnSelectAll() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnCallModal(){
	try{
	help_window.focus();
	}
	catch(err){
	}
}

