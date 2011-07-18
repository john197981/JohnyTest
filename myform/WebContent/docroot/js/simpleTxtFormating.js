	function format_sel(v,obj) 
	{
		var str = document.selection.createRange().text;
		//document.my_form.my_textarea.focus();
		obj.focus();
		var sel = document.selection.createRange();
		sel.text = "<" + v + ">" + str + "</" + v + ">";
		return;
	}

	function insert_link(obj) 
	{
		var str = document.selection.createRange().text;
		//document.my_form.my_textarea.focus();
		obj.focus();
		var my_link = prompt("Enter URL:","http://");
		if (my_link != null) 
		{
			var sel = document.selection.createRange();
			sel.text = "<a href=\"" + my_link + "\">" + str + "</a>";
		}
		return;
	}
	function mouseover(el) 
	{
		el.className = "raised";
	}
	function mouseout(el) 
	{
		el.className = "button";
	}
	function mousedown(el) 
	{
		el.className = "pressed";
	}
	function mouseup(el) 
	{
		el.className = "raised";
	}

