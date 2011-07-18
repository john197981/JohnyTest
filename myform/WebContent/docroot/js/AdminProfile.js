/* 
 * Name 		: AdminProfile.js
 * Created 		: 26-Jul-2005
 * Description 	: This javascript functions is used to load,select,search the users profile
 *
 */

/**************Error Messages *************************************/

	var Err_Email   = "Please enter a valid e-mail address"
	var Err_SltRole = "Please select the role"
	var Err_User_Not_Avail = "Users not available to add"
	var Err_MailId =  "Please enter the EmailId"
	var Err_sign =  "Signature exceeding 200 characaters"
	var Err_Interest =  "Areas of Interest exceeding 200 characaters"
	var Err_Hobbies =  "Hobbies exceeding 200 characaters"
	var Err_NickName =  "Please enter the NickName"
	var Err_BookMark_Del =  "Please select BookMarks to Delete"
	var Err_Sticky_del =  "Please select Sticky Threads to Delete" 
	var Err_Age ="From value of Age must be less than To value"
	var Err_YrJoin ="From value of Year of Joining must be less than To value"


/*********************************************************************/



/******************* Start of AdminProfile Javascript Functions ***********************/

     var isMailChk = true;
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

    
	

		  
	function fnEnableCheck(){
	   document.UserProfileForm.colName[0].checked = true;
	   if(trimAdmin(document.UserProfileForm.hiddenAction.value)==""){
			document.UserProfileForm.mailN.value="";
			document.UserProfileForm.area.value="";
			document.UserProfileForm.role.value="";
			document.UserProfileForm.hobbies.value="";

	   }
	} 

	function fnProfileEnableCheck(){
	   document.UserProfileForm.colName[0].checked = true;
	   if(trimAdmin(document.UserProfileForm.hiddenAction.value)==""){
			document.UserProfileForm.mailId.value="";
			document.UserProfileForm.area.value="";
			document.UserProfileForm.role.value="";
			document.UserProfileForm.hobbies.value="";

	   }
	} 

/* This function is to submit the Select user functionality	 */
	 function fnSltUsrSearch(){  
		var strIndex="";	
        
		var fromAge = eval(document.UserProfileForm.fromAge.value);   
		var toAge =  eval(document.UserProfileForm.toAge.value);

		var fromYr = eval(document.UserProfileForm.fromYrJoin.value);
		var toYr = eval(document.UserProfileForm.toYrJoin.value);

        if(eval(fromAge)==0){
			document.UserProfileForm.fromAge.value="";
		}
        if(eval(toAge)==0){
			document.UserProfileForm.toAge.value="";
		}
		 if(eval(fromYr)==0){
			document.UserProfileForm.fromYrJoin.value="";
		}
        if(eval(toYr)==0){
			document.UserProfileForm.toYrJoin.value="";
		}

		if(fromAge >= toAge){
			alert(Err_Age);
			document.UserProfileForm.fromAge.select();
			document.UserProfileForm.fromAge.focus();
			return false;
		}
		if(fromYr >= toYr){
			alert(Err_YrJoin);
			document.UserProfileForm.fromYrJoin.select();
			document.UserProfileForm.fromYrJoin.focus();
			return false;
		}


		for( i = 0 ;i < document.UserProfileForm.colName.length ;i++) {
			if(document.UserProfileForm.colName[i].checked){
				strIndex=strIndex+i+",";
			}
		}
		strIndex=strIndex+"0";	
		document.UserProfileForm.chk.value=strIndex; 
		document.UserProfileForm.hiddenAction.value='search'; 
	 	document.UserProfileForm.rowId.value="0";
 		document.UserProfileForm.hidSortKey.value=document.UserProfileForm.colName[0].value
		document.UserProfileForm.hidOrder.value= "ASC";
	 	document.UserProfileForm.submit();

	 }
	 	
	/* This function is used to enable all users to add  */
	function fnEnableAllChk()	{ 
	
		if(document.UserProfileForm.userId.length!=undefined){
			var nLen = document.UserProfileForm.userId.length;	
				if(document.UserProfileForm.userId[0].checked){
					 for(i = 1 ;i < nLen ;i++){
							document.UserProfileForm.userId[i].checked = true;
					 }		
				
				}else{
					 for(i = 1 ;i < nLen ;i++){
							document.UserProfileForm.userId[i].checked = false;
					 }		
				}
		}else{
			document.UserProfileForm.userId.checked=false;
		}		
	}
	
	
	function fnAdd(){

		if(trimAdmin(document.UserProfileForm.hidPage.value)=="addusers"){
				fnAddUsers();
		}else if(trimAdmin(document.UserProfileForm.hidPage.value)=="addemails"){
				fnUsrEmailChk();
		}
		
	}
	
	
	/** This function is used to add users **/
	function fnAddUsers(){

	if(document.UserProfileForm.userId.length!=undefined){
		 var nLen = document.UserProfileForm.userId.length;
		 var nTemp = 0;
		 var i = 0;
		 for(i = 0 ;i < nLen ;i++){
		 	 if (document.UserProfileForm.userId[i].checked==true){
					break;
		 	 }else{
		 	 	nTemp++;	
		 	 }	
		 }	 		
		 if(nTemp == nLen){
		 	alert(Err_ChkBox_Add);
		 	document.UserProfileForm.userId[0].focus();
		 	return;
		 }
		 document.UserProfileForm.pageNo.value = nPagNum
 		 document.UserProfileForm.hiddenAction.value="adduser";
		 document.UserProfileForm.submit();
		}else{
			alert(Err_User_Not_Avail); 
		} 
	}
	
	function fnClrSrh(){
		var nLen =  document.UserProfileForm.elements.length;
		for(i = 0;i< nLen ;i++){
			if (document.UserProfileForm.elements[i].type=="text"){
				document.UserProfileForm.elements[i].value="";
			}	
		}	
		for( i = 1 ;i < document.UserProfileForm.colName.length ;i++) {
			document.UserProfileForm.colName[i].checked = false;
		}	
		document.UserProfileForm.cluster.value="";
		document.UserProfileForm.schoolType.value="";
		document.UserProfileForm.school.value="";
		document.UserProfileForm.role.value="";
		document.UserProfileForm.design.value="";
		document.UserProfileForm.division.value="";
		document.UserProfileForm.divStatus.value="";
		document.UserProfileForm.zone.value="";

	}
	
	
	function fnEnCluster(){
		if(trimAdmin(document.UserProfileForm.cluster.value)!=""){
			document.UserProfileForm.colName[3].checked = true;

		}else{
			document.UserProfileForm.colName[3].checked =false;
		}	
	}
	function fnEnSchTyp(){
		if(trimAdmin(document.UserProfileForm.schoolType.value)!=""){
			document.UserProfileForm.colName[5].checked = true;
		}else{
			document.UserProfileForm.colName[5].checked = false;
		}	
	
	}
	function fnEnRole(){
		if(trimAdmin(document.UserProfileForm.role.value)!=""){
			document.UserProfileForm.colName[1].checked = true;		
		}else{
			document.UserProfileForm.colName[1].checked =false;
		}	

		if(trimAdmin(document.UserProfileForm.school.value)!=""){
			document.UserProfileForm.colName[3].checked = true;		
		}else{
			document.UserProfileForm.colName[3].checked =false;
		}	
	
		if(trimAdmin(document.UserProfileForm.design.value)!=""){
			document.UserProfileForm.colName[9].checked = true;		
		}else{
			document.UserProfileForm.colName[9].checked =false;
		}	
		if(trimAdmin(document.UserProfileForm.division.value)!=""){
			document.UserProfileForm.colName[11].checked = true;		
		}else{
			document.UserProfileForm.colName[11].checked =false;
		}	
	
		if(trimAdmin(document.UserProfileForm.zone.value)!=""){
			document.UserProfileForm.colName[13].checked = true;		
		}else{
			document.UserProfileForm.colName[13].checked =false;
		}	

		if(trimAdmin(document.UserProfileForm.divStatus.value)!=""){
			document.UserProfileForm.colName[19].checked = true;		
		}else{
			document.UserProfileForm.colName[19].checked =false;
		}	

	}
	
	function fnLoadEnableChk(){
		var	strIndex =document.UserProfileForm.chk.value;
		nIndex = 0;
		if(strIndex.length!=0){
			while(nIndex!=-1){
				nIndex =  strIndex.indexOf(",");
				//alert(strIndex.substr(0,nIndex));
				if(trimAdmin(strIndex.substr(0,nIndex))!="" && trimAdmin(strIndex.substr(0,nIndex))!=","){
					var k = eval(strIndex.substr(0,nIndex));
					//alert("K" +k);
					strIndex = strIndex.substr(nIndex+1,strIndex.length);
					document.UserProfileForm.colName[k].checked = true;
				}
				
			}
		}
	}

	
	/** Function for Pagniation **/

	function fnPagination(pageNum){
		var strIndex="";	
		for( i = 0 ;i < document.UserProfileForm.colName.length ;i++) {
			if(document.UserProfileForm.colName[i].checked){
				strIndex=strIndex+i+",";
			}
		}
		strIndex=strIndex+"0";	
		document.UserProfileForm.chk.value=strIndex; 

	     document.UserProfileForm.pageNo.value = pageNum;
		 document.UserProfileForm.hiddenAction.value="pagination";
		 document.UserProfileForm.submit();
	}

	/** Function to Sort the USers **/
	
	function fnSortUsers(sortKey,sortOrder){
		var strIndex="";	
		for( i = 0 ;i < document.UserProfileForm.colName.length ;i++) {
			if(document.UserProfileForm.colName[i].checked){
				strIndex=strIndex+i+",";
			}
		}
		strIndex=strIndex+"0";	
		document.UserProfileForm.chk.value=strIndex; 
	
		document.UserProfileForm.hidSortKey.value = sortKey;
		document.UserProfileForm.hidOrder.value = sortOrder;
   	    document.UserProfileForm.hiddenAction.value="sort";
  	    document.UserProfileForm.pageNo.value = "null";
		document.UserProfileForm.submit();
	
	}
		
	function fnTitle(title){
		return caption[title];
	}	
	
	function fnUsrIdChk(){
		if(document.UserProfileForm.userId[0].checked){
			document.UserProfileForm.userId[0].checked=false;
		}
	}
	
	
	function fnUsrEmailChk(){
		 var nLen = document.UserProfileForm.userId.length;
		 var nTemp = 0;
		 var i = 0;
		 var j = 0;
		 for(i = 0 ;i < nLen ;i++){
		 	 if (document.UserProfileForm.userId[i].checked==true){
					break;
		 	 }else{
		 	 	nTemp++;	
		 	 }	
		 }	 		
		 if(nTemp == nLen){
		 	alert(Err_ChkBox_Add);
		 	document.UserProfileForm.userId[0].focus();
		 	return;
		 }
		  for(i = 1 ;i < nLen ;i++){
		 	 if (document.UserProfileForm.userId[i].checked==true){
					eMailIds[j]=document.UserProfileForm.userId[i].value
					j++;
		 	}
		 }
		 if (trimAdmin(opener.document.AlertFriendForm.to.value)=="")
			 opener.document.AlertFriendForm.to.value = opener.document.AlertFriendForm.to.value+eMailIds.toString();
		else
			opener.document.AlertFriendForm.to.value = opener.document.AlertFriendForm.to.value+","+eMailIds.toString();
//		 alert(eMailIds.toString());	
			
	}
	

	function fnUserView(strUserId){

		 var strUrl = '/UserProfile.do?hiddenAction=view&id='+strUserId
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=850,height=550,left=0,top=0,resizable=yes,scrollbars=yes');
  		 help_window.focus();
		 
		/* document.UserProfileForm.id.value = strUserId;
		 document.UserProfileForm.hiddenAction.value="view";
		 document.UserProfileForm.submit();*/
	}
	
	function fnUpdate(){
		// document.UserProfileForm.id.value = strUserId;
		var sign = trimAdmin(document.UserProfileForm.sign.value);
		var area = trimAdmin(document.UserProfileForm.area.value);
		var hobbies = trimAdmin(document.UserProfileForm.hobbies.value);


		if(trimAdmin(document.UserProfileForm.role.value)==""){
				alert(Err_SltRole); 
				document.UserProfileForm.role.focus(); 
				return false;
		}
	
		
		if( isMailChk && trimAdmin(document.UserProfileForm.mailName.value)==""){
			alert(Err_MailId); 
			document.UserProfileForm.mailName.focus(); 
			return false;
		}

		if(sign.length>200){
			alert(Err_sign); 
			document.UserProfileForm.sign.focus();
			return false;
		}

		if(area.length>200){
			alert(Err_Interest); 
			document.UserProfileForm.area.focus();
			return false;
		}
		if(hobbies.length>200){
			alert(Err_Hobbies); 
			document.UserProfileForm.hobbies.focus();
			return false;
		}


		 document.UserProfileForm.hiddenAction.value="save";
		 document.UserProfileForm.submit();

	}

	function fnResetView(){

		document.UserProfileForm.reset();
		fnTextCounter(document.UserProfileForm.sign,document.UserProfileForm.numleft1);
		fnTextCounter(document.UserProfileForm.area,document.UserProfileForm.numleft2);
		fnTextCounter(document.UserProfileForm.hobbies,document.UserProfileForm.numleft3);
	}
	
	function fnSearchview(){
		
			self.close();
		/*document.UserProfileForm.hiddenAction.value="";
		document.UserProfileForm.action="UserProfile.do"
		document.UserProfileForm.submit();*/
	}
	function fnDisable(){
		// document.UserProfileForm.id.value = strUserId;
		flag = confirm("Are you sure you want to disable this user from MyForum ?");
		if(flag)
		{
			 document.UserProfileForm.obsolete.value ='Y';
			 document.UserProfileForm.hiddenAction.value="disable";
			 document.UserProfileForm.submit();
		}

	}
	function fnEnable(){
		// document.UserProfileForm.id.value = strUserId;
		flag = confirm("Are you sure you want to enable this user from MyForum ?");
		if(flag)
		{
			 document.UserProfileForm.obsolete.value ='';
			 document.UserProfileForm.hiddenAction.value="disable";
			 document.UserProfileForm.submit();
		}

	}


	
/******************* End of AdminProfile Javascript Functions *************************/


 /******************* Start of Web AdminProfile Javascript Function ********************/


	function fnWebSave(){

		var sign = trimAdmin(document.UserProfileForm.sign.value);
		var area = trimAdmin(document.UserProfileForm.area.value);
		var hobbies = trimAdmin(document.UserProfileForm.hobbies.value);


		if(trimAdmin(document.UserProfileForm.nickName.value)==""){
				alert(Err_NickName); 
				document.UserProfileForm.nickName.focus(); 
				return false;
		}
	
		
		if(trimAdmin(document.UserProfileForm.mailName.value)==""){
			alert(Err_MailId);
			document.UserProfileForm.mailId.focus(); 
			return false;
		}

		if(sign.length>200){
			alert(Err_sign);
			document.UserProfileForm.sign.focus();
			return false;
		}

		if(area.length>200){
			alert(Err_Interest);
			document.UserProfileForm.area.focus();
			return false;
		}
		if(hobbies.length>200){
			alert(Err_Hobbies);
			document.UserProfileForm.hobbies.focus();
			return false;
		}


		 document.UserProfileForm.hiddenAction.value="userupdate";
		 document.UserProfileForm.submit();


	}

	function fnWebBookMarkdelete(){
		if(trimAdmin(document.UserProfileForm.bookIds.value)==""){
			alert(Err_BookMark_Del); 
			document.UserProfileForm.bookIds.focus();
			return false;
		}
			 document.UserProfileForm.hiddenAction.value="delbookmark";
			 document.UserProfileForm.submit();
	}
	function fnWebStickydelete(){
		if(trimAdmin(document.UserProfileForm.stickyIds.value)==""){ 
			alert(Err_Sticky_del); 
			document.UserProfileForm.stickyIds.focus();
			return false;
		}
			 document.UserProfileForm.hiddenAction.value="delsticky";
			 document.UserProfileForm.submit();
	}

	function fnWebReset(){

		document.UserProfileForm.reset();
		fnTextCounter(document.UserProfileForm.sign,document.UserProfileForm.numleft1);
		fnTextCounter(document.UserProfileForm.area,document.UserProfileForm.numleft2);
		fnTextCounter(document.UserProfileForm.hobbies,document.UserProfileForm.numleft3);
	}


	function fnBlur(frmObj){
		var strValue = eval(frmObj.value);
        if(eval(strValue)==0){
			frmObj.value="";
		}
	}

    function fnLoad(){


		if(document.UserProfileForm!=undefined && strRole=="DIVADMIN"){
			isMailChk = false;
			document.UserProfileForm.sign.readOnly = true;
			document.UserProfileForm.area.readOnly = true;
			document.UserProfileForm.hobbies.readOnly = true;
			document.UserProfileForm.mailName.readOnly = true;
			document.UserProfileForm.mailDomain.readOnly = true;
		}
	}

  

 /******************* End of Web AdminProfile Javascript Function ********************/




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
		var good= false;
		if(	trimAdmin(field.value)!= ""){
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
		return good;
	}
 
 /* This function is to trim the values */
	function trimAdmin(string1){
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

	var maxlimit = 200;
	function fnTextCounter(field, countfield){
	
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