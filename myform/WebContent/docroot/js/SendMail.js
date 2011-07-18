/* 
 * Name 		: SendMail.js
 * Created 		: 12-Jul-2005
 * Description 	: This javascript is contains sendmail,clear,cancel,delete etc., operation in SendMail Module
 *
 */
  
var Err_Subject = "Please Enter Subject";
var Err_Msg     = "Please Enter Message"; 
var Err_ChkBox  = "Please Select User to Remove";
var Err_ChkBox_Add  = "Please Select the Users";
var Err_Email   = "Please enter a valid e-mail address"
var Err_ChkBox_Grp   = "Please Select the Group(s)"
var Err_Age ="From value of Age must be less than To value"
var Err_YrJoin ="From value of Year of Joining must be less than To value"

var objWin;
var strSubject; 
var nPagNum;
var eMailIds = new Array();
var xWin=window.dialogArguments;

/** The Array declaration for field mapping with heading in select user screen **/
	var caption  = new Array()
	caption['USERID']="NRIC"  
	caption['NAME']="Name"  
	caption['ROLEID']="Role";  
	caption['NICKNAME']="Nick Name" 
	caption['GRADE']="Grade"
	caption['SCHOOL_TYPE']="School Type";  
	caption['BRANCH_ZONE']="Zone/Branch";  
	caption['SCHOOL_CLUSTER']="Cluster";  
	caption['AGE']="Age";  
	caption['EMAILID']="Email ID";  
	caption['DESIGNATION']="Designation";  
	caption['INTEREST']="Area of Interest";  
	caption['DIVISION']="Division";  
	caption['CCA_1']="CCA";  
	caption['L_TECH1']="Level Teaching";  
	caption['S_TECH1']="Subject Teaching";  
	caption['HOBBIES']="Hobbies";  
	caption['JOINDT']="Year of Joining Service";  
	caption['DIV_STATUS']="Divisional Status";  
	caption['SCHOOL']="School";  

 	 


/******************* Start of SendMail Javascript Functions ***********************/

/** This Function is Used to Send the Mail to all the users */
 function fnSendMail(){
	 var subject = trim(document.SendMailForm.subject.value);
	 var message = trim(document.SendMailForm.message.value);
	 
	 if(subject.length==0){
	 	alert(Err_Subject);
	 	document.SendMailForm.subject.focus();
	 	return false;
	 }
	 if(message.length==0){
	 	alert(Err_Msg);
	 	document.SendMailForm.message.focus();
	 	return false;
	 }
	 if (trim(document.SendMailForm.email.value)!=""){
	     document.SendMailForm.subject.disabled=false
		 document.SendMailForm.hiddenAction.value="SendMail";
		 document.SendMailForm.submit();
	}else{
		alert("Users not available to Send the mail ");
	}		 
 } 
 
 
 
 /** This  Function is used to clear Message, Subject Textboxes */
	 function fnClear(){
 		//document.SendMailForm.message.value
 		document.SendMailForm.reset();
		fn_SndMailtextCounter(document.SendMailForm.message,document.SendMailForm.numleft);
	}
 
 
 /** This Function is used to revert the page where it is called **/
	 function fnCancel(){
	
		var strFlag =  trim(document.SendMailForm.surveyOrCons.value);
		if(strFlag=="C"){
			document.SendMailForm.action = "consultListingAction.do?hiddenAction=populate";
		}else if(strFlag=="S"){
			document.SendMailForm.action = "SurveyAdmin.do?hiddenAction=SurveyAdminList";
		}
	
		document.SendMailForm.submit();
	 }		


/** This function is used remove the users form the database  **/
	 function fnRemove(){
	
	if(document.SendMailForm.removeId.length!=undefined){
		 var nLen = document.SendMailForm.removeId.length;
		 var nTemp = 0;
		 var i = 0;
		 for(i = 0 ;i < nLen ;i++){
		 	 if (document.SendMailForm.removeId[i].checked==true){
					break;
		 	 }else{
		 	 	nTemp++;	
		 	 }	
		 }	 		
		 if(nTemp == nLen){
		 	alert(Err_ChkBox);
		 	document.SendMailForm.removeId[0].focus();
		 	return;
		 }
		 document.SendMailForm.subject.disabled=false;
 		 document.SendMailForm.hiddenAction.value="Remove";
		 document.SendMailForm.submit();
 	   
 	 }else{
 	 	alert("Users not available to Remove");
 	 } 
 	 
  }

/** This function checks all the list of users **/
	function fnCheckAll(){

		if(document.SendMailForm.removeId.length!=undefined){
			var nLen = document.SendMailForm.removeId.length;	
			if(document.SendMailForm.removeId[0].checked){
				 for(i = 1 ;i < nLen ;i++){
						document.SendMailForm.removeId[i].checked = true;
				 }		
			}else{
				 for(i = 1 ;i < nLen ;i++){
						document.SendMailForm.removeId[i].checked = false;
				 }		
			}
		}else{
			document.SendMailForm.removeId.checked = false;
		}	
	
   }

 /** This function is used to load select users window */
	function fnSelectUsers(){
		 var strUrl = '/SelectUsers.do?hiddenAction=populate&id='+document.SendMailForm.id.value+'&flag='+document.SendMailForm.surveyOrCons.value+'&hidPage=addusers'
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=920,height=650,left=0,top=0,resizable=yes,scrollbars=yes');
		 document.SendMailForm.winObj.value =  help_window;
		// 	window.showModalDialog(strDocRoot+strUrl,'SelectUsers',"dialogWidth:920px;dialogHeight:550px;dialogLeft:0;dialogRight:0;resizable:yes,scrollbars:yes;help:no");
	//	window.location = window.dialogArguments;
  		 help_window.focus();
	}

 /** This function is used to load select users window */    
	function fnSelectGroups(){
		var strUrl = '/SelectGroups.do?hiddenAction=searchgroup&id='+document.SendMailForm.id.value+'&flag='+document.SendMailForm.surveyOrCons.value
		help_window  = window.open(strDocRoot+strUrl,'SelectGroups','width=520,height=300,left=0,top=0,resizable=yes,scrollbars=yes');
		help_window.focus();
	}


/* This function is used close all popup windows */   
	function fnCloseWin(){
		if (objWin != null){
			objWin.close();
		}
	}
	
 /* This function is used the List of users  */
	function fnSort(sortKey,sortOrder){
	    document.SendMailForm.subject.disabled=false
		document.SendMailForm.hidSortKey.value = sortKey;
		document.SendMailForm.hidOrder.value = sortOrder;
   	    document.SendMailForm.hiddenAction.value="Sort";
		document.SendMailForm.submit();
		
	}
	
  /* This function is used to set the default values */
     function fnSendMailLoad(){
   
   		var objAction =  trim(document.SendMailForm.hiddenAction.value);

   		if(objAction=="populate"){

	   		document.SendMailForm.nricOrder.value = "ASC";
	   		document.SendMailForm.nameOrder.value = "ASC";
	   		document.SendMailForm.responseOrder.value = "ASC";
	   		document.SendMailForm.papersOrder.value = "ASC";
	   		document.SendMailForm.surveysOrder.value = "ASC";
	   	}	

   }	
   
   function fnRemoveIdChk(){
		if(document.SendMailForm.removeId[0].checked){
			document.SendMailForm.removeId[0].checked=false;
		}
	}
	
	
  function 	fnPaperList(userid){
   		var strUrl = '/SendMail.do?hiddenAction=papersurvery&id='+userid+'&surveyOrCons=C'
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=890,height=500,left=0,top=0,resizable=no,scrollbars=yes');
  		 help_window.focus();
  }

  function 	fnSurveyList(userid){
   		var strUrl = '/SendMail.do?hiddenAction=papersurvery&id='+userid+'&surveyOrCons=S'
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=890,height=500,left=0,top=0,resizable=no,scrollbars=yes');
  		 help_window.focus();
  }
  function 	fnMember(userid){
	  	 var strUrl = '/MemberProfileAction.do?nric='+userid+'&hiddenAction=populate'
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=890,height=280,left=0,top=0,resizable=no,scrollbars=yes');
  		 help_window.focus();
  }

	
	
/******************* End of SendMail Javascript Functions *************************/	
	
	
/******************* Start of oifmMessage.jsp Javascript Functions *************************/	
	function fnMsgCancel(){
		document.SendMailForm.hiddenAction.value="populate"; 
		document.SendMailForm.submit();
	}
	
	
	
/******************* End of oifmMessage.jsp Javascript Functions *************************/	
	
	
	
	 
		
/******************* Start of SelectUsers Javascript Functions ***********************/
	
	 function fnSltUsrLoad(){
		 var xWin=window.dialogArguments;
//	 	document.SelectForm.id.value = opener.document.SendMailForm.id.value;
	// 	document.SelectForm.flag.value = opener.document.SendMailForm.surveyOrCons.value;
	    document.SelectForm.colName[0].checked = true;
	  	if(document.SelectForm.hiddenAction.value=="adduser" && document.SelectForm.hidPage.value=="addBoards"){
			 opener.document.ForumBoardForm.action="adminForumViewModifyBoardAction.do?hiddenAction=populate&boardId="+document.SelectForm.id.value
			 opener.document.ForumBoardForm.submit();
	    }else if(document.SelectForm.hiddenAction.value=="adduser" && document.SelectForm.hidPage.value=="addGroups"){
			opener.document.GroupsForm.action="Groups.do?hiddenAction=EditGroup&nextAction=EditGroup"
			opener.document.GroupsForm.submit();
	    }else if(document.SelectForm.hiddenAction.value=="adduser"){
			opener.document.SendMailForm.hiddenAction.value="addusers"; 
			opener.document.SendMailForm.submit();
			//window.opener.location.href = strDocRoot+'/SendMail.do?hiddenAction=addusers';
			
		}	
		if(trim(document.SelectForm.mailId.value)!=""){
				document.SelectForm.colName[2].checked = false;
		}	
		fnLoadEnableChk();
				
	}	
	  
	function fnEnableCheck(){
	   document.SelectForm.colName[0].checked = true;
	} 
/* This function is to submit the Select user functionality	 */
	 function fnSltUsrSearch(){  
		 var strIndex="";	

		var fromAge = eval(document.SelectForm.fromAge.value);   
		var toAge =  eval(document.SelectForm.toAge.value);

		var fromYr = eval(document.SelectForm.fromYrJoin.value);
		var toYr = eval(document.SelectForm.toYrJoin.value);

        if(eval(fromAge)==0){
			document.SelectForm.fromAge.value="";
		}
        if(eval(toAge)==0){
			document.SelectForm.toAge.value="";
		}
		 if(eval(fromYr)==0){
			document.SelectForm.fromYrJoin.value="";
		}
        if(eval(toYr)==0){
			document.SelectForm.toYrJoin.value="";
		}

		if(fromAge >= toAge){
			alert(Err_Age);
			document.SelectForm.fromAge.select();
			document.SelectForm.fromAge.focus();
		}
		if(fromYr >= toYr){
			alert(Err_YrJoin);
			document.SelectForm.fromYrJoin.select();
			document.SelectForm.fromYrJoin.focus();
			return false;
		}

		for( i = 1 ;i < document.SelectForm.colName.length ;i++) {
			if(document.SelectForm.colName[i].checked){
				strIndex=strIndex+i+",";
			}
		}
		strIndex=strIndex+"0";	
		document.SelectForm.chk.value=strIndex; 
		// document.SelectForm.target="self";
		 //alert(document.SelectForm.target);
		document.SelectForm.hiddenAction.value='search'; 
	 	document.SelectForm.rowId.value="0";
 		document.SelectForm.hidSortKey.value=document.SelectForm.colName[0].value
		document.SelectForm.hidOrder.value= "ASC";
	 	document.SelectForm.submit();

	 }
	 	
	/* This function is used to enable all users to add  */
	function fnEnableAllChk()	{ 
	
		if(document.SelectForm.userId.length!=undefined){
			var nLen = document.SelectForm.userId.length;	
				if(document.SelectForm.userId[0].checked){
					 for(i = 1 ;i < nLen ;i++){
							document.SelectForm.userId[i].checked = true;
					 }		
				
				}else{
					 for(i = 1 ;i < nLen ;i++){
							document.SelectForm.userId[i].checked = false;
					 }		
				}
		}else{
			document.SelectForm.userId.checked=false;
		}		
	}
	



	
	function fnAdd(){

	var page = trim(document.SelectForm.hidPage.value);
		if(page=="addusers" || page=="addBoards" || page=="addGroups"){
				fnAddUsers();
		}else if(page=="addemails"){
				fnUsrEmailChk();
		}
		
	}
	
	
	/** This function is used to add users **/
	function fnAddUsers(){

	if(document.SelectForm.userId.length!=undefined){
		 var nLen = document.SelectForm.userId.length;
		 var nTemp = 0;
		 var i = 0;
		 for(i = 0 ;i < nLen ;i++){
		 	 if (document.SelectForm.userId[i].checked==true){
					break;
		 	 }else{
		 	 	nTemp++;	
		 	 }	
		 }	 		
		 if(nTemp == nLen){
		 	alert(Err_ChkBox_Add);
		 	document.SelectForm.userId[0].focus();
		 	return;
		 }
			try{
				var right1 = "TR";
				document.getElementById(right1).style.display = "none";
			}catch(err){
			}
		 document.SelectForm.pageNo.value = nPagNum
 		 document.SelectForm.hiddenAction.value="adduser";
		 document.SelectForm.submit();
		}else{
			alert("Users not available to add");
		} 
	}
	
	function fnClrSrh(){
		var nLen =  document.SelectForm.elements.length;
		for(i = 0;i< nLen ;i++){
			if (document.SelectForm.elements[i].type=="text"){
				document.SelectForm.elements[i].value="";
			}	
		}	
		for( i = 1 ;i < document.SelectForm.colName.length ;i++) {
			document.SelectForm.colName[i].checked = false;
		}	
		document.SelectForm.role.value="";
		document.SelectForm.school.value="";
		document.SelectForm.schoolType.value="";
		document.SelectForm.design.value="";
		document.SelectForm.division.value="";
		document.SelectForm.zone.value="";
		document.SelectForm.cluster.value="";
		document.SelectForm.divStatus.value="";

	}
	
	
	function fnEnCluster(){
		if(trim(document.SelectForm.cluster.value)!=""){
			document.SelectForm.colName[3].checked = true;

		}else{
			document.SelectForm.colName[3].checked =false;
		}	
	}
	function fnEnSchTyp(){
		if(trim(document.SelectForm.schoolType.value)!=""){
			document.SelectForm.colName[5].checked = true;
		}else{
			document.SelectForm.colName[5].checked = false;
		}	
	
	}
	function fnEnSchool(){
		if(trim(document.SelectForm.school.value)!=""){
			document.SelectForm.colName[6].checked = true;		
		}else{
			document.SelectForm.colName[6].checked =false;
		}	
	
	}

	function fnLoadEnableChk(){
		var	strIndex =document.SelectForm.chk.value;
		nIndex = 0;
		if(strIndex.length!=0){
			while(nIndex!=-1){
				nIndex =  strIndex.indexOf(",");
				if(trim(strIndex.substr(0,nIndex))!="" && trim(strIndex.substr(0,nIndex))!=","){
					k = eval(strIndex.substr(0,nIndex));
					strIndex = strIndex.substr(nIndex+1,strIndex.length);
					document.SelectForm.colName[k].checked = true;
				}
				
			}
		}
	}



	
	/** Function for Pagniation **/

	function fnPagination(pageNum){
		var strIndex="";	
		for( i = 1 ;i < document.SelectForm.colName.length ;i++) {
			if(document.SelectForm.colName[i].checked){
				strIndex=strIndex+i+",";
			}
		}
		strIndex=strIndex+"0";	
		document.SelectForm.chk.value=strIndex; 

	     document.SelectForm.pageNo.value = pageNum;
		 document.SelectForm.hiddenAction.value="pagination";
		 document.SelectForm.submit();
	}

	/** Function to Sort the USers **/
	
	function fnSortUsers(sortKey,sortOrder){
	    var strIndex="";	
		for( i = 1 ;i < document.SelectForm.colName.length ;i++) {
			if(document.SelectForm.colName[i].checked){
				strIndex=strIndex+i+",";
			}
		}
		strIndex=strIndex+"0";	
		document.SelectForm.chk.value=strIndex; 
	
		document.SelectForm.hidSortKey.value = sortKey;
		document.SelectForm.hidOrder.value = sortOrder;
   	    document.SelectForm.hiddenAction.value="sort";
  	    document.SelectForm.pageNo.value = "null";
		document.SelectForm.submit();
	
	}
		
	function fnTitle(title){
		return caption[title];
	}	
	
	function fnUsrIdChk(){
		if(document.SelectForm.userId[0].checked){
			document.SelectForm.userId[0].checked=false;
		}
	}
	
	
	function fnUsrEmailChk(){
		 var nLen = document.SelectForm.userId.length;
		 var nTemp = 0;
		 var i = 0;
		 var j = 0;
		 for(i = 0 ;i < nLen ;i++){
		 	 if (document.SelectForm.userId[i].checked==true){
					break;
		 	 }else{
		 	 	nTemp++;	
		 	 }	
		 }	 		
		 if(nTemp == nLen){
		 	alert(Err_ChkBox_Add);
		 	document.SelectForm.userId[0].focus();
		 	return;
		 }
		  for(i = 1 ;i < nLen ;i++){
		 	 if (document.SelectForm.userId[i].checked==true){
					eMailIds[j]=document.SelectForm.userId[i].value
					j++;
		 	}
		 }
		 if (trim(opener.document.AlertFriendForm.to.value)=="")
			 opener.document.AlertFriendForm.to.value = opener.document.AlertFriendForm.to.value+eMailIds.toString();
		else
			opener.document.AlertFriendForm.to.value = opener.document.AlertFriendForm.to.value+","+eMailIds.toString();
//		 alert(eMailIds.toString());	
			
	}
	
	function fnBlur(frmObj){
		var strValue = eval(frmObj.value);
        if(eval(strValue)==0){
			frmObj.value="";
		}
	}
	
	
/******************* End of SelectUsers Javascript Functions *************************/


 

	
	
/******************* Start of SelectGroups Javascript Functions ***********************/
	
	/** This Method is used to submit select group form **/
	function fnSltGrpSubmit(){
		

			 var nLen = document.SelectForm.groupId.length;
			 var nTemp = 0;
			 var i = 0;
			 for(i = 0 ;i < nLen ;i++){
			 	 if (document.SelectForm.groupId[i].checked==true){
						break;
			 	 }else{
			 	 	nTemp++;	
			 	 }	
			 }	 		
			 if(nTemp == nLen){
			 	alert(Err_ChkBox_Grp);
			 	document.SelectForm.groupId[0].focus();
			 	return;
			 }
			document.SelectForm.hiddenAction.value="addgroup";
			document.SelectForm.submit();

			
					
			
	}
	function fnSltGrp(){
	//	document.SelectForm.id.value = opener.document.SendMailForm.id.value;
	// 	document.SelectForm.flag.value = opener.document.SendMailForm.surveyOrCons.value;
		
	 if(document.SelectForm.hiddenAction.value=="addgroup"){
	    	opener.document.SendMailForm.hiddenAction.value="addusers";
	    	opener.document.SendMailForm.submit();
			opener.focus();
			self.close();
		}	
	}
	
	
/******************* End of SelectGroups Javascript Functions *************************/
	
	
/************************* Start of Common Functions *********************************/	
		
		
		
//This function allows to enter only Integer Values

function fngetKeyCodeInteger(e)
{
   	chr=e.keyCode     	     
     if ((chr<48 || chr>57)&& chr!=0)
        return false
        
     return true
}		
	
 /* This function is used to validate the Email */
 	function fnchkEmailAddress(field) {

		if(	trim(field.value)!= ""){
			var goodEmail =
			field.value.match(/\b(^(\S+@).+((\.com)|(\.net)|(\.edu)|(\.mil)|(\.gov)|(\.org)|(\..{2,2}))$)\b/gi);
			if (goodEmail) {
					good = true;
			}else {
				alert(Err_Email);
				field.focus();
				field.select();
				good = false;
	  		}
	  	}	
	}
 
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

	//var maxlimit = 500;
	var maxlimit = 4000;
	function fn_SndMailtextCounter(field, countfield){
	
		if (field.value.length > maxlimit){
			field.value = field.value.substring(0, maxlimit);
		//	cmdObj=document.getElementById("exceed");
		//	cmdObj.value="You have exceeded the maximum allowed characters in this field";
		

		}else{
			countfield.value = maxlimit - field.value.length;
			//cmdObj=document.getElementById("exceed");
			//cmdObj.value="";
		}
	}


/************************* End of Common Functions *********************************/	
	