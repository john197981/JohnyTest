
function fnSubmitPage(nPageNo){
	try{
		document.ASMFormReplyRedirect.hidLink.value=nPageNo
		document.ASMFormReplyRedirect.action ="ASMReplyRedirect.do"
		document.ASMFormReplyRedirect.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSubmitPage(nPageNo) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}
function fnSortBy(param){
	try{
		document.ASMFormReplyRedirect.hidSortBy.value=param
		document.ASMFormReplyRedirect.action ="ASMReplyRedirect.do"
		document.ASMFormReplyRedirect.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSortBy(param) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnClickTopic(letterId){
	try{
		
		//document.ASMFormReplyRedirect.action ="ASMReplyRedirectEdit.do?hidLetterID="+letterId+"&hiddenAction=AdminPage"
		document.ASMFormReplyRedirect.hidLetterID.value=letterId
		document.ASMFormReplyRedirect.hiddenAction.value="AdminPage"
		document.ASMFormReplyRedirect.action ="ASMReplyRedirectEdit.do"
		document.ASMFormReplyRedirect.submit();
	}
	catch(err)
	{
		alert('Function Name : fnClickTopic \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}