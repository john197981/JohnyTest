
var ErrPoll ="Please select atleast one answer";



function fnShowResult(){
var bflag = false;
	if(document.HomeForm.response==undefined){
		if(document.HomeForm.res1!=undefined){
				if(document.HomeForm.res1.checked){
						bflag = true;							
				}
		}	
		if(document.HomeForm.res2!=undefined){
				if(document.HomeForm.res2.checked){
					bflag = true;
				}
		}	
		if(document.HomeForm.res3!=undefined){
				if(document.HomeForm.res3.checked){
					bflag = true;								
				}
		}	
		if(document.HomeForm.res4!=undefined){
				if(document.HomeForm.res4.checked){
					bflag = true;
				}
		}	
		if(document.HomeForm.res5!=undefined){
				if(document.HomeForm.res5.checked){
					bflag = true;								
				}  
		}	
	}else{
			var nLen = document.HomeForm.response.length;
			for(i =0;i<nLen;i++){
				if(document.HomeForm.response[i].checked){
					bflag = true;								
					break;
				}
			}	 			
	}
	
	if(bflag){
		document.HomeForm.hiddenAction.value="poll";
		document.HomeForm.submit();
	}else{
		alert(ErrPoll);
		return false;
	}
}
function fnPublish(){
	document.HomeForm.pollId.value = document.HomeForm.pubId.value;
	document.HomeForm.hiddenAction.value="publish";
	document.HomeForm.submit();
}
function fnPublishCurrent(){
	document.HomeForm.reset();
	document.HomeForm.hiddenAction.value="publish";
	document.HomeForm.submit();
}


