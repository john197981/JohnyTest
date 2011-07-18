

var Err_Msg     = "Please Enter Message"; 
var MsgPersonal_1 = " Personal Message from ";
var MsgPersonal_2 = ' on your "My Forum" posting ';
var MsgAdmin = ' Message from "My Forum" posting ';

/******************* Start of Admin SendMail  Javascript Functions *************************/



	function fnMailLoad(){
			if(trim(document.SendMailForm.flag.value)=="P"){
				document.SendMailForm.hiddenAction.value ="pmmail";
				document.SendMailForm.subject.value = MsgPersonal_1 +document.SendMailForm.name.value + MsgPersonal_2
			}else if(trim(document.SendMailForm.flag.value)=="A"){
				document.SendMailForm.hiddenAction.value ="adminmail";
				document.SendMailForm.subject.value = MsgAdmin;
			}
	}
	function fnMailClose(){
		self.close();
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
	
	var strEnable;
	var strUrl;
	var posX;
	var posY;
	var strType;
	var flag = false;
if (document.all){
	//document.onclick=handle;
}

winWidth=document.all?document.body.clientWidth:window.innerWidth; 
winHeight=document.all?document.body.clientHeight:window.innerHeight; 
function handle(e) {

	if(flag){
	  posX = window.event.clientX,   posY=window.event.clientY
	  flag = false;
		if(strType=="P"){
			posX=posX-30;
			posY=posY+130;
		}else{
			posX=posX-158;
			posY=posY+145;
		}
		var help_window  = window.open(contextRoot+strUrl,'Mail',"width =300,height=130,left="+posX+",top="+posY);
		help_window.focus();
	  return true; // i.e. follow the link 
	}
}

function getiFrame(strTopicId,strNickName,strFlag){
	strUrl='/SendMail.do?hiddenAction=mail&id='+strTopicId+'&name='+strNickName+'&flag='+strFlag
	strType = strFlag;
	//var help_window  = window.open(contextRoot+strUrl,'Mail',"width =300,height=130,left="+posX+",top="+posY);
//	help_window  = window.open(contextRoot+strUrl,'Mail',"width =300,height=130,left=30,top=30");  
	if( strFlag =="P") {
		window.showModalDialog(contextRoot+strUrl,'Mail',"dialogWidth:300px;dialogHeight:220px;dialogLeft:20px;dialogRight:20px;resizable:yes,scrollbars:yes;help:no;status:off;");
	} else  if(strFlag =="A") {
		window.showModalDialog(contextRoot+strUrl,'Mail',"dialogWidth:300px;dialogHeight:220px;dialogLeft:700px;dialogRight:20px;resizable:yes,scrollbars:yes;help:no;status:off;");
	}

//	help_window.focus();

//	flag = true;

}

function fnMember(userid){
	 var strUrl = '/MemberProfileAction.do?nric='+userid+'&hiddenAction=populate'
	 help_window  = window.open(contextRoot+strUrl,'SelectUsers','width=900,height=260,left=0,top=0,resizable=no,scrollbars=yes');
	 help_window.focus();
}

function fnAlertEmail(){
	 var strUrl = '/AlertFriendAction.do?hiddenAction=populate&module=F&iD='+document.ThreadForm.strThreadId.value
	 //help_window  = window.open(contextRoot+strUrl,'SelectUsers','width=900,height=260,left=0,top=0,resizable=no,scrollbars=yes');
	 window.showModalDialog(contextRoot+strUrl,'AlertFriend',"dialogWidth:900px;dialogHeight:260px;dialogLeft:0px;dialogRight:0px;resizable:yes,scrollbars:no;help:no;status:off;");
	 //help_window.focus();

}

/******************* Start of Admin SendMail  Javascript Functions *************************/
	
//	Ref from common.js
