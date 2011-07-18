/*
 * File name	= ASMBOAbout.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;


public class ASMBOAbout extends OIBaseBo {
	private static final Logger logger = Logger.getLogger(ASMBOAbout.class);
	
	public ASMBOAbout() {}
	
	public OIResponseObject getList() {
		ASMDAOAbout objDAO = new ASMDAOAbout();
		ArrayList temp = null;
		
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
		}
		return responseObject;
	}
	
	public OIResponseObject getDetail(ASMBAAbout objBA) {
		ASMDAOAbout objDAO = new ASMDAOAbout();
		ASMBAAbout ret = new ASMBAAbout();
		
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
	
	public OIResponseObject create(ASMBAAbout objBA, String userID) {
		ASMDAOAbout objDAO= new ASMDAOAbout();
		try {
			getConnection();
			if (!objDAO.create(connection, objBA.getQuestion(), objBA.getAnswer(), userID)) throw new Exception();
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("create() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("create() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject modify(ASMBAAbout objBA, String userID) {
		ASMDAOAbout objDAO= new ASMDAOAbout();
		try {
			getConnection();
			if (!objDAO.modify(connection, objBA.getId(), objBA.getQuestion(), objBA.getAnswer(), userID)) throw new Exception();
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("modify() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("modify() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	
	public OIResponseObject delete(ASMBAAbout objBA) {
		ASMDAOAbout objDAO= new ASMDAOAbout();
		try {
			getConnection();
			if (!objDAO.delete(connection, objBA.getId())) throw new Exception();
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("delete() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("delete() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}
	public OIResponseObject getModuleDescription() {
		//get the short description
		String strModDesc = null;
		try {
			getConnection();
			
    		//get the short description
    		ASMDAOCommon objCommonDAO =new ASMDAOCommon(); 
    		strModDesc = objCommonDAO.getModuleDescription(connection,"ASMABOUTWELCOME");
			
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getModuleDescription() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getModuleDescription() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("ModuleDesc", strModDesc);
		}
		return responseObject;
	}

	
}
