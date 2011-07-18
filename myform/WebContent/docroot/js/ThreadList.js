/* 
 * Name 		: ThreadList.js
 * Created 		: 09-Aug-2005
 * Description 	: This javascript file contains for sorting the thread List.
 *
 */


  /* This function is used the List of users  */
	function fnSort(sortKey,sortOrder){
		document.ThreadForm.hidSortKey.value = sortKey;
		document.ThreadForm.hidOrder.value = sortOrder;
		document.ThreadForm.action = strDocRoot+"/ThreadList.do"
   	    document.ThreadForm.hiddenAction.value="Sort";
		document.ThreadForm.target="_self";
		document.ThreadForm.submit();
	}

	function fnMember(userid){
	  	 var strUrl = '/MemberProfileAction.do?nric='+userid+'&hiddenAction=populate'
		 help_window  = window.open(strDocRoot+strUrl,'SelectUsers','width=890,height=280,left=0,top=0,resizable=no,scrollbars=yes');
  		 help_window.focus();
  }

  function fnHits(threadid){
		document.ThreadForm.pageNo.value = null;
		document.ThreadForm.strThreadId.value = threadid;
		document.ThreadForm.action = strDocRoot+"/ThreadList.do"
	    document.ThreadForm.hiddenAction.value="hits";
		document.ThreadForm.target="_self";
		document.ThreadForm.submit();
  }

  /** Function for Pagniation **/

	function fnPagination(pageNum){

	     document.ThreadForm.pageNo.value = pageNum;
 		 document.ThreadForm.action = strDocRoot+"/ThreadList.do"
		 document.ThreadForm.hiddenAction.value="Pagniation";
		 document.ThreadForm.target="_self";
		 document.ThreadForm.submit();
	}

