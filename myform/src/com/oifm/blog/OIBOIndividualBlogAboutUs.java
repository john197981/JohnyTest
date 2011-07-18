package com.oifm.blog;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.upload.FormFile;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.OIDBRegistry;

public class OIBOIndividualBlogAboutUs extends OIBaseBo
{
	private static final Logger logger = Logger.getLogger(OIBOIndividualBlogAboutUs.class);
	
	public OIBOIndividualBlogAboutUs() {super();}
	
	public OIResponseObject getAuthorDetailsList(OIBAIndividualBlog objBA)
	{
		ArrayList alList = null;
		OIDAOIndividualBlogAboutUs objDAO = new OIDAOIndividualBlogAboutUs();
		
		try
		{
			getConnection();
			alList = objDAO.getAuthorDetailsList(connection, objBA.getBlogId());
		} 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getAuthorDetailsList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAuthorDetailsList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("AuthorDetailsList", alList);
        }
		
		return responseObject;
	}
	
	public OIResponseObject getAuthorList(OIBAIndividualBlog objBA)
	{
		ArrayList alList = null;
		OIDAOIndividualBlogAboutUs objDAO = new OIDAOIndividualBlogAboutUs();
		
		try
		{
			getConnection();
			alList = objDAO.getAuthorList(connection, objBA.getBlogId());
		} 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getAuthorList - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAuthorList->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("AuthorList", alList);
        }
		
		return responseObject;
	}
	
	public OIResponseObject getAuthorDetails(OIBAIndividualBlog objBA)
	{
		OIBAIndividualBlog objBADetails = null;
		OIDAOIndividualBlogAboutUs objDAO = new OIDAOIndividualBlogAboutUs();
		
		try
		{
			getConnection();
			objBADetails = objDAO.getAuthorDetails(connection, objBA.getBlogId(), objBA.getAuthorUserId());
		} 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("getAuthorDetails - SQLException->" + se);
        } 
        catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getAuthorDetails->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("AuthorDetails", objBADetails);
        }
		
		return responseObject;
	}
	
	public OIResponseObject updateAuthorDetails (OIBAIndividualBlog objBA)
	{
		OIDAOIndividualBlogAboutUs objDAO = new OIDAOIndividualBlogAboutUs();
		FormFile file = objBA.getFileUpload();
		
		try
		{
			getConnection();
			
			if(file != null && !file.getFileName().trim().equals("") && file.getFileSize() > 0) {
				if (!checkFileType(file)) throw new Exception("BLOG.FileType");
				if (!checkFileSize(file)) throw new Exception("BLOG.FileSize");
				
				String fileName = contructFileName(file, objBA.getAuthorUserId(), objBA.getBlogId().intValue());
				if (!uploadFile(file, fileName)) throw new Exception("BLOG.Uploading");
				objBA.setAuthorImageFileName(fileName);
			}
			
			objDAO.updateAuthorDetails(connection, objBA.getBlogId(), objBA.getAuthorUserId(), objBA.getAuthorNotifyEmail(), objBA.getAuthorDescription(), objBA.getAuthorImageFileName());
		} 
        catch (SQLException se) 
        {
            error = "" + se.getErrorCode();
            logger.error("updateAuthorDetails - SQLException->" + se);
        } 
        catch (Exception e) 
        {
        	if (e.getMessage().equals("BLOG.FileType") || e.getMessage().equals("BLOG.FileSize") || e.getMessage().equals("BLOG.Uploading")) error = e.getMessage();
        	else error = "OIDEFAULT";
            logger.error("updateAuthorDetails->" + e);
        } 
        finally 
        {
            freeConnection();
            addToResponseObject();
        }
		
		return responseObject;
	}
	
	private boolean checkFileType(FormFile file)
	{
		if (file.getFileName().toLowerCase().endsWith("gif") || file.getFileName().toLowerCase().endsWith("jpg")
				|| file.getFileName().toLowerCase().endsWith("bmp") || file.getFileName().toLowerCase().endsWith("png"))
			return true;
		else
			return false;
	}
	
	private boolean checkFileSize(FormFile file)
	{
		if (file.getFileSize() <= (128 * 1024))
			return true;
		else
			return false;
	}
	
	private String contructFileName(FormFile file, String userId, int blogId)
	{
		String ext = file.getFileName().substring(file.getFileName().lastIndexOf("."));
		NumberFormat formatter = new DecimalFormat("00000");
		return "BLOGAUTHOR" + userId + formatter.format(blogId) + ext;
	}
	
	private boolean uploadFile(FormFile file, String fileName) throws Exception
	{
		InputStream iStream = null;
		OutputStream oStream = null;
		final int BUFFER_SIZE = 8192;
		int bytesRead = 0;
		boolean ret = false;
		
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			
			iStream = file.getInputStream();
			oStream = new FileOutputStream(OIDBRegistry.getValues("OI.BLOG.AUTHOR.PIC") + fileName);
			
			while ((bytesRead = iStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				oStream.write(buffer, 0, bytesRead);
			}
			
			ret = true;
			
		} catch (Exception e) {
			logger.error("uploadFile() - " + e);
			throw e;
		} finally {
			iStream.close();
			oStream.close();
		}
		return ret;
	}
	
	public OIResponseObject readPicture(HttpServletResponse response, String fileName) {
		try {
			response.setContentType(getContentType(fileName));
			readFile(response, fileName);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("readPicture() - " + e);
		}
		return responseObject;
	}
	
	private String getContentType(String fileName) {
		if (fileName.toLowerCase().endsWith("gif")) return "image/gif";
		else if (fileName.toLowerCase().endsWith("jpg")) return "image/jpeg";
		else if (fileName.toLowerCase().endsWith("bmp")) return "image/x-ms-bmp";
		else if (fileName.toLowerCase().endsWith("png")) return "image/x-png";
		else return null;
	}
	
	private boolean readFile(HttpServletResponse response, String fileName) throws Exception{
		InputStream iStream = null;
		OutputStream oStream = null;
		final int BUFFER_SIZE = 8192;
		int bytesRead = 0;
		boolean ret = false;
		
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			
			iStream = new FileInputStream(OIDBRegistry.getValues("OI.BLOG.AUTHOR.PIC") + fileName);
			oStream = response.getOutputStream();
			
			while ((bytesRead = iStream.read(buffer, 0, BUFFER_SIZE)) != -1) {
				oStream.write(buffer, 0, bytesRead);
			}
			
			ret = true;
			
		} catch (Exception e) {
			logger.error("readFile() - " + e);
			throw e;
		} finally {
			iStream.close();
			oStream.flush();
			oStream.close();
		}
		return ret;
	}
	
	public OIResponseObject removePhoto(OIBAIndividualBlog objBA, String userID) {
		OIDAOIndividualBlogAboutUs objDAO = new OIDAOIndividualBlogAboutUs();
		try {
			getConnection();
			if ((objBA.getAuthorImageFileName() != null || objBA.getAuthorImageFileName().length() > 0) && !deleteFile(objBA.getAuthorImageFileName())) throw new Exception();
			if (!objDAO.removePhoto(connection, objBA.getBlogId(), userID)) throw new Exception();
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("removePhoto() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("removePhoto() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	private boolean deleteFile (String fileName) {
		boolean ret = false;
		try {
			File file = new File(OIDBRegistry.getValues("OI.BLOG.AUTHOR.PIC") + fileName);
			ret = file.delete();
		} catch (Exception e) {
			logger.error("deleteFile() - " + e);
		}
		return ret;
	}
}
