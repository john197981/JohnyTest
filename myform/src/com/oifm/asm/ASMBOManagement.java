/*
 * File name	= ASMBOManagement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 21, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

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


public class ASMBOManagement extends OIBaseBo {
	
	private static final Logger logger = Logger.getLogger(ASMBOManagement.class);
	
	public ASMBOManagement() {}
	
	public OIResponseObject getList() {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		ArrayList temp = null;
		ArrayList sub = null;
		String currHead = null;
		ASMBAManagement currProfile = null;
		
		try {
			getConnection();
			temp = objDAO.getList(connection);
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getList() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getList() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("List", temp);
			responseObject.addResponseObject("Total", new Integer(temp.size()));
		}
		return responseObject;
	}
	
	public OIResponseObject getDetail(ASMBAManagement objBA) {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		ASMBAManagement ret = new ASMBAManagement();
		try {
			getConnection();
			if (objBA.getId() != 0)
				ret = objDAO.getDetail(connection, objBA.getId());
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getDetail() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getDetail() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("Detail", ret);
		}
		return responseObject;
	}
	
	public OIResponseObject create(ASMBAManagement objBA, String userID, FormFile file) {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		try {
			getConnection();
			if(file != null && !file.getFileName().trim().equals("") && file.getFileSize() > 0) {
				if (!checkFileType(file)) throw new Exception("ASM.Manage.FileType");
				if (!checkFileSize(file)) throw new Exception("ASM.Manage.FileSize");
			}
			
			if (!objDAO.create(connection, objBA.getHeading(), objBA.getSubheading(), objBA.getName(), objBA.getDesignation(), objBA.getDivision(), objBA.getProfile(), objBA.getDivisionurl(), userID)) throw new Exception();
			
			int id = objDAO.getID(connection, objBA.getName());
			
			if(file != null && !file.getFileName().trim().equals("") && file.getFileSize() > 0) {
				String fileName = contructFileName(file, id);
				if (!uploadFile(file, fileName)) throw new Exception("ASM.Manage.Uploading");
				objDAO.setPhoto(connection, fileName, id);
			}
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("create() - SQLException - " + e);
		} catch (Exception e) {
			if (e.getMessage().equals("ASM.Manage.FileType") || e.getMessage().equals("ASM.Manage.FileSize") || e.getMessage().equals("ASM.Manage.Uploading")) error = e.getMessage();
			else error = "OIDEFAULT";
			logger.error("create() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject modify(ASMBAManagement objBA, String userID, FormFile file) {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		try {
			getConnection();
			
			if(file != null && !file.getFileName().trim().equals("") && file.getFileSize() > 0) {
				if (!checkFileType(file)) throw new Exception("ASM.Manage.FileType");
				if (!checkFileSize(file)) throw new Exception("ASM.Manage.FileSize");
				String fileName = contructFileName(file, objBA.getId());
				if (!uploadFile(file, fileName)) throw new Exception("ASM.Manage.Uploading");
				objBA.setPhotograph(fileName);
			}
			
			if (!objDAO.modify(connection, objBA.getId(), objBA.getHeading(), objBA.getSubheading(), objBA.getName(), objBA.getDesignation(), objBA.getDivision(), objBA.getProfile(), objBA.getDivisionurl(), objBA.getPhotograph(), userID)) throw new Exception();
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("modify() - SQLException - " + e);
		} catch (Exception e) {
			if (e.getMessage().equals("ASM.Manage.FileType") || e.getMessage().equals("ASM.Manage.FileSize") || e.getMessage().equals("ASM.Manage.Uploading")) error = e.getMessage();
			else error = "OIDEFAULT";
			logger.error("modify() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject delete(ASMBAManagement objBA) {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		try {
			getConnection();
			if (objBA.getPhotograph() != null && objBA.getPhotograph().length() > 0 && !deleteFile(objBA.getPhotograph())) throw new Exception("ASM.Manage.Deleting");
			if (!objDAO.delete(connection, objBA.getId())) throw new Exception("ASM.Manage.Deleting");
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("delete() - SQLException - " + e);
		} catch (Exception e) {
			if (e.getMessage().equals("ASM.Manage.Deleting")) error = e.getMessage();
			else error = "OIDEFAULT";
			logger.error("delete() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject getWelcomeMessage() {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		String ret = null;
		try {
			getConnection();
			ret = objDAO.getWelcomeMessage(connection);
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getWelcomeMessage() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getWelcomeMessage() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("WelcomeMessage", ret);
		}
		return responseObject;
	}
	
	public OIResponseObject removePhoto(ASMBAManagement objBA) {
		ASMDAOManagement objDAO = new ASMDAOManagement();
		try {
			getConnection();
			if ((objBA.getPhotograph() != null || objBA.getPhotograph().length() > 0) && !deleteFile(objBA.getPhotograph())) throw new Exception();
			if (!objDAO.removePhoto(connection, objBA.getId())) throw new Exception();
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
	
	public OIResponseObject readPhoto(HttpServletResponse response, String fileName) {
		try {
			response.setContentType(getContentType(fileName));
			readFile(response, fileName);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("readPhoto() - " + e);
		}
		return responseObject;
	}
	
	private boolean checkFileType (FormFile file) {
		if (file.getFileName().toLowerCase().endsWith("gif") || 
			file.getFileName().toLowerCase().endsWith("jpg") ||
			file.getFileName().toLowerCase().endsWith("bmp") ||
			file.getFileName().toLowerCase().endsWith("png")) return true;
		else return false;
	}
	
	private boolean checkFileSize (FormFile file) {
		if (file.getFileSize() <= (128 * 1024)) return true;
		else return false;
	}
	
	private String contructFileName (FormFile file, int id) {
		String ext = file.getFileName().substring(file.getFileName().lastIndexOf("."));
		NumberFormat formatter = new DecimalFormat("00");
		return "MGMNT" + formatter.format(id) + ext;
	}
	
	private String getContentType(String fileName) {
		if (fileName.toLowerCase().endsWith("gif")) return "image/gif";
		else if (fileName.toLowerCase().endsWith("jpg")) return "image/jpeg";
		else if (fileName.toLowerCase().endsWith("bmp")) return "image/x-ms-bmp";
		else if (fileName.toLowerCase().endsWith("png")) return "image/x-png";
		else return null;
	}
	
	private boolean uploadFile (FormFile file, String fileName) throws Exception {
		InputStream iStream = null;
		OutputStream oStream = null;
		final int BUFFER_SIZE = 8192;
		int bytesRead = 0;
		boolean ret = false;
		
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			
			iStream = file.getInputStream();
			oStream = new FileOutputStream(OIDBRegistry.getValues("ASM.MANAGEMENT.PHOTO") + fileName);
			
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
	
	private boolean readFile(HttpServletResponse response, String fileName) throws Exception{
		InputStream iStream = null;
		OutputStream oStream = null;
		final int BUFFER_SIZE = 8192;
		int bytesRead = 0;
		boolean ret = false;
		
		try {
			byte[] buffer = new byte[BUFFER_SIZE];
			
			iStream = new FileInputStream(OIDBRegistry.getValues("ASM.MANAGEMENT.PHOTO") + fileName);
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
	
	private boolean deleteFile (String fileName) {
		boolean ret = false;
		try {
			File file = new File(OIDBRegistry.getValues("ASM.MANAGEMENT.PHOTO") + fileName);
			ret = file.delete();
		} catch (Exception e) {
			logger.error("deleteFile() - " + e);
		}
		return ret;
	}

}
