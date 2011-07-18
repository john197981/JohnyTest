/*
 * File name	= ASMBOAnnouncement.java
 * Package		= com.oifm.asm
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.asm;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;


public class ASMBOAnnouncement extends OIBaseBo {
	private static final Logger logger = Logger.getLogger(ASMBOAnnouncement.class);
	
	public ASMBOAnnouncement () {}
	
	public OIResponseObject getAnnouncement() {
		ASMBAAnnouncement objBA = new ASMBAAnnouncement();
		ASMDAOAnnouncement objDAO = new ASMDAOAnnouncement();
		
		try{
			getConnection();
			objBA.setMessage(objDAO.getAnnouncement(connection));
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
            logger.error("getAnnouncement - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getAnnouncement() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("Announcement", objBA);
		}
		return responseObject;
	}
	
	public OIResponseObject setAnnouncement(ASMBAAnnouncement objBA, String userID) {
		ASMDAOAnnouncement objDAO = new ASMDAOAnnouncement();
		
		try{
			getConnection();
			if (!objDAO.setAnnouncement(connection, objBA.getMessage(), userID)) throw new Exception("Multiple announcement entry in database");
			
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
            logger.error("setAnnouncement - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("setAnnouncement() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}

}
