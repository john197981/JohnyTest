function fnSetLink(link,clicked)
{
	try{
		
		if(clicked=="next" || clicked=="previous"){
			document.ASMFormPrevRep.hidStartIndex.value= link
		}

		document.ASMFormPrevRep.hidLink.value= link
		//document.ASMFormPrevRep.hidAction.value="populate";
		document.ASMFormPrevRep.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSetLink(link,clicked) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}

function fnSubmitPage(nPageNo){
	try{
		document.ASMFormPrevRep.hidLink.value=nPageNo
		//document.ASMFormPrevRep.action ="asmPrevRep.do"
		document.ASMFormPrevRep.action ="asmPrevRep.do?hidLink="+nPageNo+"&hiddenAction=populateNew"
		document.ASMFormPrevRep.submit();
	}
	catch(err)
	{
		alert('Function Name : fnSubmitPage(link,clicked) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}

function fnClickLetter(mode){
	try{
		document.ASMFormPrevRep.hidLink.value=1
		document.ASMFormPrevRep.hiddenAction.value =mode
		document.ASMFormPrevRep.hidPageDesc.value =""
		document.ASMFormPrevRep.action ="asmPrevRep.do"
		document.ASMFormPrevRep.submit();
	}
	catch(err)
	{
		alert('Function Name : fnClickLetter(link,clicked) \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}

}

function fnCallDetails(letterid){
	try{
		document.ASMFormPrevRep.hidPageDesc.value ="RecentLetters";
		document.ASMFormPrevRep.hidLetterID.value =letterid;
		document.ASMFormPrevRep.hiddenAction.value ="PrevRepSearch";
		document.ASMFormPrevRep.action ="asmHome.do?hiddenAction=PrevRepSearch&hidPageDesc=RecentLetters&hidLetterID="+letterid;
		//document.ASMFormPrevRep.action ="asmHome.do";
		document.ASMFormPrevRep.submit();
	}
	catch(err)
	{
		alert('Function Name : fnCallDetails() \n Error Number: '+ err.number +'\n Error Name : ' + err.name +'\n Error Message : ' + err.message );
	}
}
