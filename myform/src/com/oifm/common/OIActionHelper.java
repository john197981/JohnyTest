
package com.oifm.common;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.SocketException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oifm.common.OIDownloadHelper;


public class OIActionHelper extends OIDownloadHelper {

	private static Logger logger = Logger.getLogger(OIActionHelper.class);

    public void writeContentToFile(HttpServletResponse res, String strOut, String fileName) throws IOException	{
    	PrintWriter out = null;
    	try{
    		res.setContentType("application/vnd.ms-excel");
    		res.setHeader("Content-disposition","attachment;filename="+fileName);
    		out = res.getWriter();
    		out.write(strOut);
    		out.flush();
		} catch(SocketException se) {

    	} catch(IOException ie) {
logger.error(" writeContentToFile => "+ie);
    		throw ie;
    	} finally {
    		if(out != null)	out.close();
    	}
    }

    public PrintWriter getWriter(HttpServletResponse res, String fileName) throws IOException	{
    	PrintWriter out = null;
    	try{
    		res.setContentType("application/vnd.ms-excel");
    		res.setHeader("Content-disposition","attachment;filename="+fileName);
    		out = res.getWriter();
    	} catch(IOException ie) {
logger.error(" getWriter() => "+ie);
    		throw ie;
    	} 
    	return out;
    }

}

