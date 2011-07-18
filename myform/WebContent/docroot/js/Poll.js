/* 
 * Name 		: Poll.js
 * Created 		: 26-Jul-2005
 * Description 	: This javascript File is handled Create,Modify,Delete,Publish,View  operations in Poll Module
 *
 */
 
 
 var strDeleteInfo ="The poll and all its results will be deleted.\n Are you Sure ? ";
var rtmErr001 = 'Invalid day.';
var rtmErr002 = 'Invalid year.';
var rtmErr003 = 'Invalid days for month.';
var rtmErr004 = ' ,invalid days for February.';
var rtmErr005 = 'Invalid month.';
var ErrPoll = " with results published already exists. Please disable publishing for that poll first";
var view = false;
 
 /** This function is call the module of poll to add new poll**/
 	 function fnLoad(){
  		document.PollForm.hiddenAction.value = "load";
 		document.PollForm.submit();
 	} 
 
 
  /** This function is call the view module of poll **/
	function fnView(pollid){
		document.PollForm.pollId.value=pollid;
		document.PollForm.hiddenAction.value = "view";
 		document.PollForm.submit();
	}
	
	 /** This function is call the create module of poll **/
 	 function fnSave(){
 	 
 	 	if(fnChkValues("Save")){
	 	 	document.PollForm.startDt.value = document.PollForm.TempStartDt.value
	  		document.PollForm.expDt.value =document.PollForm.TempExpDt.value
	 	 	document.PollForm.hiddenAction.value="create";
	 		document.PollForm.submit();
	 	}	
 	} 

	 /** This function is call the update module of poll **/
 	 function fnUpdate(){
	 	 if(fnChkValues("Update")){
	  		document.PollForm.startDt.value = document.PollForm.TempStartDt.value
	  		document.PollForm.expDt.value =document.PollForm.TempExpDt.value
	  		document.PollForm.hiddenAction.value = "modify";
	 		document.PollForm.submit();
	 	}	
 	} 
 
 
	 function fnDelete(){
		
		//if(fnChkValues()){
			if(confirm(strDeleteInfo)){	 	
			  	document.PollForm.startDt.value = document.PollForm.TempStartDt.value
	  			document.PollForm.expDt.value =document.PollForm.TempExpDt.value
				document.PollForm.hiddenAction.value = "delete";
	 			document.PollForm.submit();
	 		}	
	 	//}	
 	} 
 
 
	 /** This function is clear all the fields **/
 	 function fnClear(){
 	 
 	 document.PollForm.reset();
	 fn_PolltextCounter(document.PollForm.question,document.PollForm.numleft)
 	 /*	var nLen =  document.PollForm.elements.length;
		for(i = 0;i< nLen ;i++){
			if (document.PollForm.elements[i].type=="text"){
				document.PollForm.elements[i].value="";
			}	
		}	
		document.PollForm.showRes.checked=false;
		document.PollForm.mutAns.checked=false;
		document.PollForm.published.checked=false;*/
 		
 	} 
 	
 	function fnCancel(){
 		document.PollForm.hiddenAction.value ="";
 		document.PollForm.submit();
 	}
 

  	function fnExport(){
 		 var strUrl = '/Poll.do?hiddenAction=export&pollId='+document.PollForm.pollId.value
		 help_window  = window.open(strDocRoot+strUrl,'export');
  		 help_window.focus();
 	}

	 function compareDate(fromDt,toDt){
		FromDate = new Date(fromDt);
		ToDate = new Date(toDt);
		//alert(ToDate-FromDate);
		if ((ToDate-FromDate)>=0){
			return true;
		}else{
			alert("From date should be less than To date");
			return false;
		}
	}
	

	function fnDisableStDt(){
			var stDate = document.PollForm.TempStartDt.value
			var startDate = new Date((stDate.replace('-',' ')).replace('-',' '));
			var endDate =  new Date((currDate.replace('-',' ')).replace('-',' '));
		if(trimSpaces(document.PollForm.hiddenAction.value)=="view"){
			if ((startDate >= endDate)) {
					cal.select(document.forms['PollForm'].TempStartDt,'TempStartDt','dd-NNN-yyyy');
					view = true;
					return false;
			}else{
					var tempDt = document.PollForm.startDt.value
					tempDt = new Date((tempDt.replace('-',' ')).replace('-',' '));
					if(startDate >= tempDt)	{			
						alert("From Date should not be changed");
						return false;
					}else{
						cal.select(document.forms['PollForm'].TempStartDt,'TempStartDt','dd-NNN-yyyy');
						view = true;
						return false;
					}
			}
  	  }else{
					cal.select(document.forms['PollForm'].TempStartDt,'TempStartDt','dd-NNN-yyyy');
					return false;
	  }

	}
	

	function fnChkValues(flag){
 
			var question =  trimSpaces(document.PollForm.question.value);
		   if(trimSpaces(document.PollForm.title.value)==""){
	 	 		alert("Please Enter Title");
	 	 		document.PollForm.title.focus();
	 	 		return false;
	 	 	}
			
	 	 	
	 	 	if(trimSpaces(document.PollForm.TempStartDt.value)==""){
	 	 		alert("Please Enter From Date");
	 	 		document.PollForm.TempStartDt.focus();
	 	 		return false;
	 	 	}
	 	 	if(trimSpaces(document.PollForm.TempExpDt.value)==""){
	 	 		alert("Please Enter To Date");   
	 	 		document.PollForm.TempExpDt.focus();
	 	 		return false;
	 	 	}
		if(flag=="Save" || view==true){
			if (! compareTwoDates(currDate,document.PollForm.TempStartDt.value,"Current Date", "From Date", false))
			{
				document.PollForm.TempStartDt.focus();  
				return false;
			}

			if (! compareTwoDates(currDate,document.PollForm.TempExpDt.value,"Current Date","To Date", false))
			{
				document.PollForm.TempExpDt.focus();
				return false; 
			}
		  }/*else{	
				if (! compareTwoDates(currDate,document.PollForm.TempStartDt.value,"Current Date", "From Date", false)){
								document.PollForm.TempStartDt.focus();  
								return false;
				}*/

		
			if(!compareTwoDates(document.PollForm.TempStartDt.value,document.PollForm.TempExpDt.value,"From Date", "To Date", false)){
				return false;
			}	
		
			if(question==""){
	 	 		alert("Please Enter Question");
	 	 		document.PollForm.question.focus(); 	 		
	 	 		return false;
	 	 	}
			if(question.length>500){
				alert("Question exceeding  500 Characters");
	 	 		document.PollForm.question.focus(); 	 		
	 	 		return false;

			}

			if(trimSpaces(document.PollForm.answer1.value)=="" && trimSpaces(document.PollForm.answer2.value)=="" &&
				trimSpaces(document.PollForm.answer3.value)=="" && trimSpaces(document.PollForm.answer4.value)=="" &&
				trimSpaces(document.PollForm.answer5.value)=="" ){
					alert("Please Enter atleast one answer");
		 	 		document.PollForm.answer1.focus(); 	 		
		 	 		return false;
			}

			return true;

 	} 
 
 
 
 	/** This function is to uncheck if the poll is already published **/
	 function fnPublish(){
	 	var pubTitle =  trimSpaces(document.PollForm.pubTitle.value)
	 	var Title = trimSpaces(document.PollForm.title.value)

		if(!fnComPublish(document.PollForm.TempStartDt.value,currDate)){
		 	return false;
		}
			 	
	 	if(pubTitle!=""&& eval(document.PollForm.pollId.value) != eval(document.PollForm.pubId.value)){
		 	document.PollForm.published.checked = false;
	 		alert( "Poll " + document.PollForm.pubTitle.value +ErrPoll);
		 	return false;
	 	} 
	 }	
  



  function fnComPublish(strDate1, strDate2) {
	var flag = false;
	var startDate = new Date((strDate1.replace('-',' ')).replace('-',' '));
	var endDate =  new Date((strDate2.replace('-',' ')).replace('-',' '));
	if ((startDate > endDate)) {
		document.PollForm.published.checked = false;
		alert("Poll cannot Publish for future date");
		return false;
	}if ((startDate >= endDate)) {
		document.PollForm.published.checked = false;
		alert("Poll cannot Publish for Today's date");
		return false;
	} 

	return true;
	/*else if (!isGreater && (startDate > endDate)) {
		alert(label2+" later than or equal to the "+label1);
	} else flag = true; 
	return flag;*/
}
 

 /** This function is used to load select users window */
	function fnPrintPublish(){
		var strUrl = '/Poll.do?hiddenAction=print&pollId='+document.PollForm.pollId.value

		 help_window  = window.open(strDocRoot+strUrl,'poll','width=760,height=300,left=0,top=0,resizable=no,scrollbars=NO');
  		 help_window.focus();
	}

 
	function compareCurrDate(fromDt)
	{
		FromDate = new Date(fromDt);
		ToDate = new Date();
/*		alert(fromDt);
		alert(ToDate);
		alert(FromDate);*/
		if ((ToDate < FromDate))
		{
			alert("The From date should be later than Current date");
			return false;
		}//else{
//			alert("From date should be less than To date");
			return true;
		//}
	}


function fnBack(){
	document.PollForm.action=strDocRoot+"/home.do";
	document.PollForm.hiddenAction.value="";
	document.PollForm.submit();
}

 
function fnChkModifyDt(){

	var Stdt1 = document.PollForm.StartDt.value
	var Endt2 = document.PollForm.ExpDt.value

	var tmpStdt1 = document.PollForm.TempStartDt.value
	var tmpEndt2 = document.PollForm.TempExpDt.value


	 Stdt1 = new Date((Stdt1.replace('-',' ')).replace('-',' '));
	 Endt2 =  new Date((Endt2.replace('-',' ')).replace('-',' '));

	 tmpStdt1 = new Date((tmpStdt1.replace('-',' ')).replace('-',' '));
	 tmpEndt2 =  new Date((tmpEndt2.replace('-',' ')).replace('-',' '));


	if( Stdt1 < tmpStdt1){
		alert("The From date should be later than Current date");
	}


}


 /**********************Common Functions ***********************/
 
 
 	var maxlimit = 200;
	function fn_PolltextCounter(field, countfield){
	
		if (field.value.length > maxlimit){
			field.value = field.value.substring(0, maxlimit);
		}else{
			countfield.value = maxlimit - field.value.length;
		}
	} 
 
 
 
 
 
 
 
 /* This function is to trim the values */
	function trimSpaces(string1){
		var len=string1.length;
		var copystring;
		var out_printWriterputstring;
		var count=0;
		var count1=string1.length;
		var len1=string1.length;
		var lcount=0;
		var rcount=string1.length;
		for(var i=0;i<len1;i++){
			if(string1.charAt(i)==' ' || string1.charAt(i)==' '){
				lcount++;
			}else{
				break;
			}
		}
		for(var j=len1-1;j>0;j--){
			if(string1.charAt(j)==' ' || string1.charAt(j)==' '){
				rcount--
			}
			else{
				break;
			}
		}
		var trimstring='';
		if(rcount>lcount){
			trimstring=string1.substring(lcount,rcount)
		}
		return trimstring
 }//end of function trim
 
 
	 // function to restrict alphabets to be  entered into date field
	function fngetKeyCodeDate(e,obj)
	{
	   	chr=e.keyCode
	     //if ((chr<48 || chr>57) &&chr!=58 && chr!=0 && chr==46 & chr!=8)
		if ((chr<48 || chr>57))
			return false
	        
	     return true
	}
 
 
 
	 //function to validate date
	function fnCheckDateFormat(dateStr,fld)
	{
	if(bErrorFlag)
	{
		if (dateStr.length < 10)
		{
			bErrorFlag = false;
			alert(invalidmmddyyyy);
			fld.focus();
			fld.select();
			return false;
		}
		if( (dateStr.charAt(2) != "/") || (dateStr.charAt(5) != "/"))
		{	
			bErrorFlag = false;	
			alert(invalidmmddyyyy);
			fld.focus();
			fld.select();
			return false;		
		}
		mmstr = dateStr.charAt(0) + dateStr.charAt(1);
		ddstr = dateStr.charAt(3) + dateStr.charAt(4);
		yystr = dateStr.charAt(6) + dateStr.charAt(7);
		yystr = yystr + dateStr.charAt(8) + dateStr.charAt(9);
	    var dd, mm, yy;
		mm = parseInt(mmstr,10);
		dd = parseInt(ddstr,10) ;
		yy = parseInt(yystr,10);
	
		if( mm < 1 || mm > 12)
		{
			bErrorFlag = false;
			alert(rtmErr005);
			fld.focus();
			fld.select();
			return false;
		}
	
		if( dd < 1 || dd > 31 )
		{
			bErrorFlag = false;
			alert( rtmErr001 );
			fld.focus();
			fld.select();
			return false;
		}
	
		if( yy == 0 )
		{
			bErrorFlag = false;	
			alert( rtmErr002 );
			fld.focus();
			fld.select();
			return false;
		}
	
		if ( mm == 4 || mm == 6 || mm == 9 || mm == 11)
		{
			if( dd > 30)
			{
				bErrorFlag = false;		
				alert(rtmErr003);
				fld.focus();
				fld.select();
				return false
			}
		}
		if ( mm == 2) // check for leap year
		{
			if( dd > 29)
			{
				bErrorFlag = false;
				alert( rtmErr003);
				fld.focus();
				fld.select();
				return false
			}
			yy1 = yy % 4
			yy2 = yy % 100
			yy3 = yy % 400
		
			if ( dd == 29)
			{
				if(( yy1 == 0 ) && (( yy2 != 0 ) || (yy3 == 0 )))
				{
				}
				else
				{
					bErrorFlag = false;
					alert( fldName+rtmErr004);
					fld.focus();
					fld.select();
					return false
				}
			}
		}
		return true;
	  }
	}
 
 
	