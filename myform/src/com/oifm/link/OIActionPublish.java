package com.oifm.link;

import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;

//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionServlet;
//import org.apache.struts.action.PlugIn;
//import org.apache.struts.config.ModuleConfig;

//import com.oifm.base.OIBaseAction;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OIDBRegistry;

public class OIActionPublish extends Action //implements PlugIn
{
    Logger logger = Logger.getLogger(OIActionPublish.class.getName());
    ActionServlet aServlet = null;
    
    /*public void init(ActionServlet servlet,ModuleConfig config) throws ServletException
    {
        logger.info("init");
        aServlet = getServlet();
    }

    public void destroy()
    {
        //aServlet = getServlet();
    }*/

    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
    {
        
    	try
        {
    		
            aServlet = getServlet();
            //logger.info("Rajkumar");
            boolean flag=false;
            //logger.info(aServlet);
            if (aServlet.getServletContext().getAttribute("XMLTIME")!=null)
            {
                Calendar cal = Calendar.getInstance();
                cal.setTime(DateUtility.getDateFromMMDDYYYYHHString((String) aServlet.getServletContext().getAttribute("XMLTIME")));
                Calendar currCal = Calendar.getInstance();
                currCal.setTime(DateUtility.getDateFromMMDDYYYYHHString(DateUtility.getMMDDYYYYHHStringFromDate(new Date())));
                //logger.info("Start time --" + cal.getTime());
                //logger.info("end time --" + currCal.getTime());
                long startTime = cal.getTime().getTime();
                long endTime = currCal.getTime().getTime();
                //logger.info(currCal.getTime().getTime() + "--Current Time in MilliSeconds");
                //logger.info(cal.getTime().getTime() + "--XML Time");
                //logger.info("Diff in Milliseconds--" + (endTime-startTime));
                long timeDiff = (endTime-startTime)/1000;
                //logger.info("timeDiff " + timeDiff);
                if (timeDiff<=Integer.parseInt(OIDBRegistry.getValues("OIFM_PUBLISHLINK_REFRESH"))*60)
                {
                    flag = true;
                }
            }
            if (flag && aServlet.getServletContext().getAttribute("XMLSTRING")!=null)
            {
                PrintWriter pr = response.getWriter();
                response.setContentType("text/plain");
                //logger.info(response.getBufferSize() + "");
                //logger.info("Rajkumar");
                response.setBufferSize(5000000);
                pr.flush();
                pr.println((String) aServlet.getServletContext().getAttribute("XMLSTRING"));
                pr.flush();
                pr.close();
                return null;
            }
            //logger.info("Raj---");
            //request.setAttribute("test","TEst Message");
            //RequestDispatcher requestDispatcher = request.getRequestDispatcher("test.jsp");
            //requestDispatcher.forward(request, response);
            //response.sendRedirect("test.jsp");
            OIResponseObject aOIResponseObject = new OIBOPublish().generateXML("");
            String XMLData = (String) aOIResponseObject.getResponseObject("XMLData");
            //logger.info("XMLData is "+XMLData);
            response.setContentType("text/plain");
            //logger.info(response.getBufferSize() + "");
            response.setBufferSize(5000000);
            StringBuffer out = new StringBuffer();
            //out.println("Rajkumar");
            //out.flush();
            out.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>");
	        out.append("<rss version=\"2.0\">");
	        out.append("<channel>");
	        out.append("<title>OIFM Links</title>");
            //out.flush();
	        out.append("<rootLink>" + OIDBRegistry.getValues("OI.PUBLISH.URL") +"</rootLink>");
            //out.flush();
	        out.append("<description>Link publishing to external applications</description>");
            //out.flush();
	        out.append("<language>en</language>");
            //out.flush();
	        out.append("<copyright>Copyright 2009</copyright>");
	        out.append("<pubDate>" + DateUtility.getMMDDYYYYStringFromDate(new Date()) + "</pubDate>");
	        out.append("<docs>http://www.rssboard.org/rss-specification</docs>");
            //out.flush();
            out.append(XMLData);
            //out.flush();
            //out.close();
            aServlet.getServletContext().setAttribute("XMLSTRING",out.toString());
            //logger.info(DateUtility.getMMDDYYYYStringFromDate(new Date()));
            aServlet.getServletContext().setAttribute("XMLTIME",DateUtility.getMMDDYYYYHHStringFromDate(new Date()));
            //logger.info(DateUtility.getMMDDYYYYHHStringFromDate(new Date()) + "--String Date");
            PrintWriter pr = response.getWriter();
            pr.flush();
            //logger.info(out.toString());
            pr.print(out.toString());
            pr.flush();
            pr.close();
            System.out.println("after  pr.close() " + out.toString());
        }
        catch(Exception e)
        {
            logger.error(e.getMessage());
        }
        finally{
        	
        	
    		//This code is added to reduce the session counter(by calling session invalidate method
    		HttpSession session = request.getSession(false);
    		if (session != null) 
    		{
    		    session.invalidate();
    		}
    		
    		//end
        	
        }
        return null;
    }
}
