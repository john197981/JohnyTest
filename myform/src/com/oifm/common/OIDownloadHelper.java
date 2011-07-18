
package com.oifm.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.oifm.utility.OIUtilities;


public class OIDownloadHelper {

	private static final int BUFFER_SIZE = 8192;
	private static Logger logger = Logger.getLogger(OIDownloadHelper.class);

	private boolean writeFileContentOnStream(OutputStream out, String strAbsoluteFilePath) throws IOException	{
		boolean writeFlag = false;
		int bytesRead = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(strAbsoluteFilePath);
			if(file.exists() && file.canRead()) {
				fis = new FileInputStream(file);
				while ((bytesRead = fis.read(buffer, 0, BUFFER_SIZE)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				writeFlag = true;
			}
		}catch (IOException ie) {
logger.error(" writeFileContentOnStream() => File Name : "+strAbsoluteFilePath+" Exception : "+ie.getMessage());
			throw ie;
		} finally {
			out.flush();
			out.close();
			fis.close();
		}
		return writeFlag;
	}



    public boolean downloadFile(HttpServletResponse response, String fileDirKey, String fileName, String strTargetFileName) throws IOException	{
    	OutputStream os = null;
		boolean flag = false;

		try{

			if(fileName != null) {
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition","attachment;filename="+strTargetFileName);//+".pdf");
				os = response.getOutputStream();
				flag = writeFileContentOnStream(os, OIUtilities.getUploadFilesDir(fileDirKey) + fileName);
			}

		} catch(SocketException se) {

		} catch(IOException ie) {
logger.error(" downloadFile => "+ie);
    		throw ie;
    	} finally {
    		if(os != null)	{
				os.flush();
				os.close();
			}
    	}
		return flag;
    }



}
 
    

