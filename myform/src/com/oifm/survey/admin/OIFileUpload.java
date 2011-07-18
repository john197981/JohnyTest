
package com.oifm.survey.admin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.oifm.survey.OISurveyConstants;
import com.oifm.utility.OIUtilities;

public class OIFileUpload {

	private static final Logger logger = Logger.getLogger(OIFileUpload.class);	
	private static final int	BUFFER_SIZE = 8192;

	public boolean uploadFile(FormFile file, String strNewFileName) throws IOException	{
		boolean uploadFlag = false;
logger.debug(" upload FileName : "+file.getFileName() +" New FileName : "+strNewFileName+" Size : "+ file.getFileSize() + " bytes");
		int bytesRead = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		
		InputStream stream = null;
		OutputStream bos = null;
		try {
			stream = file.getInputStream();
			bos = new FileOutputStream(OIUtilities.getUploadFilesDir(OISurveyConstants.SURVEY_UPLOAD_DIR)+strNewFileName);

			while ((bytesRead = stream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				bos.write(buffer, 0, bytesRead);
			}
			uploadFlag = true;
		}catch (IOException ie) {
logger.error(" uploadFile() : "+ie.getMessage());			
			throw ie;
		}finally {
			bos.close();
			stream.close();
		}	
		return uploadFlag;
	}
	
	public boolean removeFile(String strFileName) {
		boolean removeFlag = false;
		try {
			File file = new File(OIUtilities.getUploadFilesDir("SurveyUploadDirPath")+strFileName);
			removeFlag = file.delete();
		}catch(SecurityException se) {
			logger.error(" removeFile() : "+se.getMessage());			
			throw se;
		}			
		return removeFlag;
	}
	
	public boolean readFile(OutputStream out, String strFileName) throws IOException	{
		boolean readFlag = false;
		int bytesRead = 0;
		byte[] buffer = new byte[BUFFER_SIZE];
		FileInputStream fis = null;
		try {
			File file = new File(OIUtilities.getUploadFilesDir("SurveyUploadDirPath"), strFileName);
			if(file.exists()) {
				fis = new FileInputStream(file);
				while ((bytesRead = fis.read(buffer, 0, BUFFER_SIZE)) != -1) {
					out.write(buffer, 0, bytesRead);
				}
				readFlag = true;
			}
		}catch (IOException ie) {
logger.error(" readFile() : "+ie.getMessage());
			throw ie;
		} finally {
			out.close();
			fis.close();
		}
		return readFlag;
	}
		
}
