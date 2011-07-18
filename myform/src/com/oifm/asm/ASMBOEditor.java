/*
 * File name	= ASMBOEditor.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 19, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.utility.DateUtility;


public class ASMBOEditor extends OIBaseBo {
	
	private static final Logger logger = Logger.getLogger(ASMBOEditor.class);
	
	public ASMBOEditor() {}
	
	public OIResponseObject getList() {
		ASMDAOEditor objDAO = new ASMDAOEditor();
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
	
	public OIResponseObject getListPage(int row1, int row2) {
		ASMDAOEditor objDAO = new ASMDAOEditor();
		ArrayList temp = null;
		
		try {
			getConnection();
			temp = objDAO.getListPage(connection, row1, row2);
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
			logger.error("getListPage() - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getListPage() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("List", temp);
		}
		return responseObject;
	}
	
	public OIResponseObject getDetail(ASMBAEditor objBA) {
		ASMDAOEditor objDAO = new ASMDAOEditor();
		ASMBAEditor ret = new ASMBAEditor();
		
		try {
			getConnection();
			if (objBA.getId() != 0)
				ret = objDAO.getDetail(connection, objBA.getId());
			else ret.setEditon(DateUtility.getMMDDYYYYStringFromDate(new Date()));
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
	
	public OIResponseObject create(ASMBAEditor objBA, String userID) {
		ASMDAOEditor objDAO= new ASMDAOEditor();
		try {
			getConnection();
			if (!objDAO.create(connection, objBA.getEditon(), objBA.getTopic(), objBA.getContent(), userID)) throw new Exception();
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
	
	public OIResponseObject modify(ASMBAEditor objBA, String userID) {
		ASMDAOEditor objDAO= new ASMDAOEditor();
		try {
			getConnection();
			if (!objDAO.modify(connection, objBA.getId(), objBA.getEditon(), objBA.getTopic(), objBA.getContent(), userID)) throw new Exception();
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
	
	public OIResponseObject delete(ASMBAEditor objBA) {
		ASMDAOEditor objDAO= new ASMDAOEditor();
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
	
	public OIResponseObject getPageSize () {
		int ret = 0;
        ASMDAOEditor objDAO = new ASMDAOEditor();
        try {
            getConnection();
            ret = objDAO.getNumPerPage(connection);		
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("getPageSize - SQLException - " + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("getPageSize" + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("PageSize", new Integer(ret));
        }
        return responseObject;
	}
	
	public OIResponseObject getTotalRec() {
		int ret = 0;
        ASMDAOEditor objDAO = new ASMDAOEditor();
        try {
            getConnection();
            ret = objDAO.getCount(connection);	
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("getPageSize - SQLException" + se);
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("getPageSize" + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("TotalRecord", new Integer(ret));
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
    		strModDesc = objCommonDAO.getModuleDescription(connection,"ASMEDITORWELCOME");
			
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
