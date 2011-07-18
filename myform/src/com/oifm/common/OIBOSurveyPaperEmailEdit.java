/*
 * File name	= OIBOSurveyPaperEmailEdit.java
 * Package		= com.oifm.common
 * Created on 	= Dec 15, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;


public class OIBOSurveyPaperEmailEdit extends OIBaseBo {
	private static final Logger logger = Logger.getLogger(OIBOSurveyPaperEmailEdit.class);
	
	public OIBOSurveyPaperEmailEdit () {}
	
	public OIResponseObject getEmail(String module, String id) {
		OIBASurveyPaperEmailEdit objBA = new OIBASurveyPaperEmailEdit();
		OIDAOSurveyPaperEmailEdit objDAO = new OIDAOSurveyPaperEmailEdit();
		
		try{
			getConnection();
			objBA.setMessage(objDAO.getEmail(connection,module,id));
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
            logger.error("getEmail - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("getEmail() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
			responseObject.addResponseObject("EmailText", objBA);
		}
		return responseObject;
	}
	
	public OIResponseObject setEmail(OIBASurveyPaperEmailEdit objBA, String module, String id) {
		OIDAOSurveyPaperEmailEdit objDAO = new OIDAOSurveyPaperEmailEdit();
		
		try{
			getConnection();
			objDAO.setEmail(connection, objBA.getMessage(), module,id);
			
		} catch (SQLException e) {
			error = "" + e.getErrorCode();
            logger.error("setEmail - SQLException - " + e);
		} catch (Exception e) {
			error = "OIDEFAULT";
			logger.error("setEmail() - " + e);
		} finally {
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}

}
