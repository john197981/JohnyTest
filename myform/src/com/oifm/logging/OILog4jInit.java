package com.oifm.logging;

import org.apache.log4j.PropertyConfigurator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
 * Class Name:Log4jInit
 * Description:This servlet initialize the log4j property file
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			15/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
public class OILog4jInit extends HttpServlet
{
	public void init() 
	{ 
	    // recover the path and filename for the properties config file
	    // log4j-init-file is set in the web.xml file for this application
	    String prefix =  getServletContext().getRealPath("/");
	    String file = getInitParameter("log4j-init-file");
	
	    // if the log4j-init-file is not set, then no point in trying
	    if(file != null) 
	    {
	        PropertyConfigurator.configure(prefix+file);
	    }
	}

    public void doGet(HttpServletRequest req, HttpServletResponse res) 
    {
    }

}
