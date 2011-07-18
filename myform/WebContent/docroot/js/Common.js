/******************************/
/*	Author : - Sukumar Tunga  */
/******************************/

function checkBlank(obj, msg) {
	obj = trim(obj);
	var val = obj.value;
	var flag = true;
	if(val.length > 0) flag = true;
	else {
		alert(" Please enter "+msg);
		if(obj.type=='text')
		{
			obj.focus();
		}
	      	flag = false;
	}
	return flag;
}

function trim(obj) {
	var i=-1, StrIn = obj.value;
	while(StrIn.charAt(++i) == " "); 
	StrIn = StrIn.substr(i);
	i = StrIn.length;
	while(StrIn.charAt(--i) == " "); 
	obj.value = StrIn.substring(0, i+1);
	return obj;
} 

function checkNumber(obj, msg)	{
	var num = obj.value;
	var i=-1, flag= false;
	while(num.charAt(++i) == "0"); 
	num = obj.value = num.substring(i);

	if(num.length > 0) {
		for(var i=0; i<num.length; i++) {               
			if(num.charAt(i) >= "0" && num.charAt(i) <= "9") flag= true;
			else {
				alert("Please enter the valid "+ msg);
				obj.focus();
				obj.select();
				flag=false; 
				break;
			}
		}
		if(flag==true) {
			num = parseInt(num);
			if(num != 0)	obj.value = num;
			else { obj.value = ""; flag = false; }
		}
	}
	return flag;
}

function checkValidText(obj)	{
	var obj = trim(obj);
	var flag= false;
	var inStr = obj.value;

	if(inStr.length > 0)
	for(var i=0; i<inStr.length; i++) {               
		if((inStr.charAt(i) >= "0" && inStr.charAt(i) <= "9") || (inStr.charAt(i) >= "a" && inStr.charAt(i) <= "z") || (inStr.charAt(i) >= "A" && inStr.charAt(i) <= "Z") || inStr.charAt(i) == " ") 
			flag= true;
		else {
			alert("Please enter the alpha-numeric data only");
			obj.focus();
			obj.select();
			flag=false; break;
		}
	}
	return flag;
}

function compareTwoDates(strDate1, strDate2, label1, label2, isGreater) {
	var flag = false;
	var startDate = new Date((strDate1.replace('-',' ')).replace('-',' '));
	var endDate = new Date((strDate2.replace('-',' ')).replace('-',' '));
	if (isGreater && (startDate >= endDate)) {
		alert(label2+" should be later than the "+label1);
	} else if (!isGreater && (startDate > endDate)) {
		alert(label2+" must be later than or equal to the "+label1);
	} else flag = true; 
	return flag;
}

function compareTwoDates2(strDate1, strDate2, label1, label2, isGreater) {
	var flag = false;
	var startDate = new Date((strDate1.replace('-',' ')).replace('-',' '));
	var endDate = new Date((strDate2.replace('-',' ')).replace('-',' '));
	if (isGreater && (startDate >= endDate)) {
		//alert(label2+" should be later than the "+label1);
	} else if (!isGreater && (startDate > endDate)) {
		//alert(label2+" must be later than or equal to the "+label1);
	} else flag = true; 
	return flag;
}

function txtAreaMaxLen(obj, size, msg ) {
	var val = obj.value;
	var flag = true;
	if(val.length <= size) flag = true;
	else {
		alert("Please enter maximum ("+size+") characters for "+msg);
	      	flag = false;
	}
	return flag;
}

function fn_textCounter(field, countfield, maxlimit) {
	if (field.value.length > maxlimit)
		field.value = field.value.substring(0, maxlimit);
	else countfield.value = maxlimit - field.value.length;
}

function checkSelectedIndex(obj, msg)	{
	if(obj.options[obj.selectedIndex].value != "") flag = true;
	else	{ 
		alert("Please select "+msg);
		flag = false;
	}
	return flag;
}

function checkCompare(obj1, obj2, msg)	{
	flag = false;
	if(parseInt(obj1.value) > parseInt(obj2.value)){
		alert(msg);
		flag=false;
	}else
		flag=true;
	return flag;
}

function disableElements(frm) {
	var aryElements = frm.elements;
	var obj;
	for(var i=0; i < aryElements.length; i++) {
		obj = aryElements[i];
		if(obj.type == "text" || obj.type == "textarea")
			obj.readOnly = true;
		else if(obj.type == "select-one"  || obj.type == "radio"  || obj.type == "checkbox" || obj.type == "file")
			obj.disabled = true;
	}
	return true;
}

// To remove frameset and make to normal
function checkFrame()	{
	if(top.frames.length != 0) 
		top.location = self.document.location;
}

function keyNumber(e)
{
	isNav = (navigator.appName.indexOf("Netscape") != -1) ? true : false;
	var charCode=isNav ? e.which : e.keyCode;
	if( (charCode >= 48 && charCode <= 57)) 
	{
		return true;
	}		
	return false;
}
