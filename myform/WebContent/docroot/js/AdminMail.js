/* 
 * Name 		: AdminMail.js
 * Created 		: 12- Aug -2005
 * Description 	: This javascript file contains Admin mail functions
 *
 */



var Err_Msg     = "Please Enter Message"; 
var MsgPersonal_1 = " Personal Message from ";
var MsgPersonal_2 = ' on your "My Forum" posting ';
var MsgAdmin = ' Message from "My Forum" posting ';

/******************* Start of Admin SendMail  Javascript Functions *************************/

	function fnMailLoad(){
			if(trim(document.SendMailForm.flag.value)=="P"){
				document.SendMailForm.hiddenAction.value ="pmmail";
				document.SendMailForm.subject.value = MsgPersonal_1 +document.SendMailForm.name.value + MsgPersonal_2;
			}else if(trim(document.SendMailForm.flag.value)=="A"){
				document.SendMailForm.hiddenAction.value ="adminmail";
				document.SendMailForm.subject.value = MsgAdmin;
			}
	}
	function fnMailClose(){
		
		window.close();
	}
	function fnMailSubmit(){
	 var message = trim(document.SendMailForm.message.value);
	 if(message.length==0){
	 	alert(Err_Msg);
	 	document.SendMailForm.message.focus();
	 	return false;
	 }
		document.SendMailForm.submit();
	}

/******************* Start of Admin SendMail  Javascript Functions *************************/
	/* This function is to trim the values */
	function trim(string1){
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