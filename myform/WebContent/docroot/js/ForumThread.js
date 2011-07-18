var StatusWin;
var InterfaceWin;
var PrintWin;

function submitThreadPostsStatusForm(submitUrl, actionName) 
{
	var frm = document.ThreadForm;
	if(!(help_window && help_window.open && !help_window.closed))
	{
		help_window = window.open("", "help_window", "width=400,height=300,toolbar=no,location=no,status=yes, menubar=no,scrollbars=no,resizable=no, top=5, left=200" );
	}
	frm.target="help_window";
	frm.action= contextRoot+submitUrl;
	frm.hiddenAction.value = actionName;
	document.ThreadForm.winObj.value =  help_window;
	frm.submit();
	help_window.focus();
	return;
}


function submitNewThreadForm(submitUrl, actionName, strTopicId) 
{
	var frm = document.ThreadForm;
	frm.target="help_window";
	if(!(help_window && help_window.open && !help_window.closed))
	{
		help_window = window.open("", "help_window", "width=680,height=500,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5, left=50" );
	}
	frm.action= contextRoot+submitUrl;
	frm.hiddenAction.value = actionName;
	frm.strTopicId.value = strTopicId;
	frm.strThreadId.value ="";
	document.ThreadForm.winObj.value =  help_window;
	frm.submit();
	help_window.focus();
	return;
}


function submitThreadPostsPrintForm(submitUrl, actionName, strPostId) 
{
	var frm = document.ThreadForm;
	frm.target="help_window";
	if(!(help_window && help_window.open && !help_window.closed))
	{
		help_window = window.open("", "help_window", "width=680,height=500,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5, left=50" );
	}
	frm.action= contextRoot+submitUrl;
	frm.hiddenAction.value = actionName;
	frm.strPostId.value = strPostId;
	document.ThreadForm.winObj.value =  help_window;
	frm.submit();
	help_window.focus();
	return;
}

function submitCatBoardTopicHrcyForm(submitUrl, actionName) 
{
	var frm = document.ThreadForm;
	var flag = true;
	if(actionName == "MoveToTopicHrcy") 
	{
		flag = confirm("Do you like to move this thread to another topic?");
	}

	if(flag) 
	{
		if(!(help_window && help_window.open && !help_window.closed))
		{
			//help_window = window.open(contextRoot+submitUrl+'?hiddenAction='+actionName,"MoveTo","width=270,height=300,toolbar=no,location=no,status=no, menubar=no,scrollbars=yes,resizable=no,top=150,left=730");
			help_window = window.open("","help_window","width=270,height=300,toolbar=no,location=no,status=no, menubar=no,scrollbars=yes,resizable=no,top=150,left=730");
		}
		frm.target="help_window";
		frm.action= contextRoot+submitUrl;
		frm.hiddenAction.value = actionName;
		document.ThreadForm.winObj.value =  help_window;
		frm.submit();
		help_window.focus();
	}
	return;
}

function submitModThreadPostEditForm(submitUrl, actionName, postId, threadId) 
{
	var frm = document.ModerationForm;
	frm.target="PrintWin";
	if(!(PrintWin && PrintWin.open && !PrintWin.closed))
	{
		PrintWin = window.open("", "PrintWin", "width=680,height=500,toolbar=no,location=no,status=yes, menubar=no,scrollbars=yes,resizable=no, top=5, left=50" );
	}
	frm.action= contextRoot+submitUrl;
	frm.hiddenAction.value = actionName;
	frm.strPostId.value = postId;
	frm.strThreadId.value = threadId;
	frm.submit();
	return;
}
