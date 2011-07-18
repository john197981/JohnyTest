		<%
			int sessionTimeout=0;

			//Add however many minutes you want as a warning in terms of seconds:
			int minutesWarning = Integer.parseInt((String) session.getAttribute("sessionTimeout")) * 60;

			sessionTimeout = session.getMaxInactiveInterval() - minutesWarning;
			//express sessionTimeout in terms of milliseconds
			sessionTimeout *= 1000;
		%>
	
		<script language="javascript">
			function confirmLogoff() 
			{
				if (confirm("Your session will end in <%= (String) session.getAttribute("sessionTimeout") %> minute (s).\n\nPress OK to continue for another <%= session.getMaxInactiveInterval()/60 %> minute(s)."))
				{
					location.reload();
				}
			}
			setTimeout("confirmLogoff()", <%= sessionTimeout %> ); 
		</script> 
sfdfsdfdsfsd
sdfasdasdsa