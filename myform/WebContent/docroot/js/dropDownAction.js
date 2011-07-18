
var ie=document.all
var dom=document.getElementById
var ns4=document.layers

var bouncelimit=32 //(must be divisible by 8)
var direction="left"

function initbox(){
if (!dom&&!ie&&!ns4)
return
cen=(ie)? document.body.clientWidth/2-155 : (dom)?window.innerWidth/2-155  : window.innerWidth/2-155
crossobj=(dom)?document.getElementById("dropin").style : ie? document.all.dropin : document.dropin
scroll_top=(ie)? document.body.scrollTop : window.pageYOffset
crossobj.top=scroll_top-250
crossobj.width=310
crossobj.left=cen

crossobj.visibility=(dom||ie)? "visible" : "show"
dropstart=setInterval("dropin()",50)
}

function dropin(){
scroll_top=(ie)? document.body.scrollTop : window.pageYOffset
if (parseInt(crossobj.top)<125+scroll_top)
crossobj.top=parseInt(crossobj.top)+40
else{
clearInterval(dropstart)
bouncestart=setInterval("bouncein()",50)
}
}

function bouncein(){
crossobj.top=parseInt(crossobj.top)-bouncelimit
if (bouncelimit<0)
bouncelimit+=8
bouncelimit=bouncelimit*-1
if (bouncelimit==0){
clearInterval(bouncestart)
}
}

 


function doPopup() {


var info='<TABLE CELLPADDING=0 CELLSPACING=0 border=1 bgcolor="#d1e1f8"><TR><TD>'
+ '<a href="iofm_email_friend.htm"><img src="images/button/btn_EmailToFriend.gif" width="93" height="19" border="0"></a><hr>'
+'<a href="iofm_Postings_List1.htm"><img src="images/button/btn_LockThread.gif" width="93" height="19" border="0"></a><hr>'
+'<a href="iofm_ThreadPrint.htm" target="_new"><img src="images/button/btn_print.gif" width="46" height="19" border="0"></a><hr>'
+ '</TD></TR></TABLE>';
if (ie) {
	document.all.dropin.innerHTML = info;
	document.all.dropin.left='200';
  }	
  	else if (ns4) {
		document.layers['dropin'].document.open();
		document.layers['dropin'].document.write(info);
		document.layers['dropin'].document.close();
	}
	else if (dom) {
		document.getElementById('dropin').innerHTML= info;
	}


initbox();
}