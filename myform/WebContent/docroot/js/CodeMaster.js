/* 
 * Name 		: CodeMaster.js
 * Created 		: 15-Aug-2005
 * Description 	: This javascript file contains functions of Code Master 
 *
 */

/**************Error Messages *************************************/

var Err_Desc = "Please enter the description"
var Err_Desc_Exceeds = "Description exceeding 3000 Characters"
var Err_Code = "Please select the Code Type";

/********************************************************************/

function fnUpdate()
{
	var desc = trim(document.CodeMasterForm.description.value);
	if(desc=="")
	{
		alert(Err_Desc);
		document.CodeMasterForm.description.focus();
		return false;
    }
	if(desc.length > 3000)
	{
		alert(Err_Desc_Exceeds);
		document.CodeMasterForm.description.focus();
		return false;
	}	
	//fnHidDataAssign();
	document.CodeMasterForm.hiddenAction.value="update";
	document.CodeMasterForm.submit();
}

function fnCancel()
{
	//document.CodeMasterForm.reset();
	//fnTextCounter(document.CodeMasterForm.description,document.CodeMasterForm.numleft1);
	if(trim(document.CodeMasterForm.hiddenAction.value)!="")
	{
		document.CodeMasterForm.hiddenAction.value="";
		document.CodeMasterForm.type.value="";
		document.CodeMasterForm.value.value="";
		document.CodeMasterForm.obsolete.checked=false;
		document.CodeMasterForm.description.value='';
		document.CodeMasterForm.shortName.value="";
		document.CodeMasterForm.submit();
	}
}

function fnSearch()
{
	if(trim(document.CodeMasterForm.type.value)=="")
	{
		alert(Err_Code);
		document.CodeMasterForm.type.focus();
		return false;
	}
	
	if(document.CodeMasterForm.obsolete.checked==false)
	{
		document.CodeMasterForm.obsolete.value="N";
	}

	var sName = trim(document.CodeMasterForm.shortName.value);
	//fnHidDataAssign();
	document.CodeMasterForm.hiddenAction.value="search";
	document.CodeMasterForm.submit();
}


function fnLoad()
{
	var strAction = trim(document.CodeMasterForm.hiddenAction.value);
	if(strAction=="load")
	{
		document.CodeMasterForm.type.disabled=true;
		document.CodeMasterForm.value.disabled=true;
		document.CodeMasterForm.description.focus();
	}
	//else{
		/*if(document.CodeMasterForm.hidDesc.value==""){
			document.CodeMasterForm.description.value="";
		}
		if(document.CodeMasterForm.hidCode.value==""){
			document.CodeMasterForm.value.value="";
		}
		if(document.CodeMasterForm.obsolete.value==""){
			document.CodeMasterForm.obsolete.checked=false;
		}*/
	//}
	/*else if(strAction=="" || strAction=="update"){
		document.CodeMasterForm.type.value="";
		document.CodeMasterForm.value.value="";
		document.CodeMasterForm.obsolete.checked=false;
		document.CodeMasterForm.description.value='';
		document.CodeMasterForm.type.focus();
	}*/
}

function fnPopulate(codeId)
{
	document.CodeMasterForm.strCodeId.value = codeId;
	document.CodeMasterForm.hiddenAction.value="load";
	document.CodeMasterForm.submit();
}

function fnHidDataAssign()
{
	document.CodeMasterForm.hidType.value = document.CodeMasterForm.type.value
	document.CodeMasterForm.hidCode.value = document.CodeMasterForm.value.value
	document.CodeMasterForm.hidDesc.value = document.CodeMasterForm.description.value
	document.CodeMasterForm.hidName.value = document.CodeMasterForm.shortName.value
	document.CodeMasterForm.hidObs.value  = document.CodeMasterForm.obsolete.value
	alert(document.CodeMasterForm.hidObs.value);
}
var maxlimit = 3000;
function fnTextCounter(field, countfield)
{
	if (field.value.length > maxlimit)
	{
		field.value = field.value.substring(0, maxlimit);
	}
	else
	{
		countfield.value = maxlimit - field.value.length;
	}
}

 /* This function is to trim the values */
function trim(string1)
{
	var len=string1.length;
	var copystring;
	var out_printWriterputstring;
	var count=0;
	var count1=string1.length;
	var len1=string1.length;
	var lcount=0;
	var rcount=string1.length;
	for(var i=0;i<len1;i++)
	{
		if(string1.charAt(i)==' ' || string1.charAt(i)==' ')
		{
			lcount++;
		}
		else
		{
			break;
		}
	}
	for(var j=len1-1;j>0;j--)
	{
		if(string1.charAt(j)==' ' || string1.charAt(j)==' ')
		{
			rcount--;
		}
		else
		{
			break;
		}
	}
	var trimstring='';
	if(rcount>lcount)
	{
		trimstring=string1.substring(lcount,rcount)
	}
	return trimstring
 }//end of function trim