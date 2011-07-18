/*
 * File name	= OIBOAddEmails.java
 * Package		= com.oifm.common
 * Created on 	= Aug 23, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.common;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;


public class OIBOAddEmails extends OIBaseBo {
	Logger logger = Logger.getLogger(OIBOAddEmails.class);
	
	public OIBOAddEmails () {}
	
	public OIResponseObject verifyEmails (OIBAAddEmails objEmails) {
		OIDAOAddEmails objAddEmails = new OIDAOAddEmails();
		ArrayList alTempMails = new ArrayList();
		ArrayList alTempRes = null;
		ArrayList alRetS = new ArrayList();
		ArrayList alRetF = new ArrayList();
		
		try {
    		getConnection();
    		alTempMails = objEmails.getAlEmails();
    		
    		for (int i = 0; i < alTempMails.size(); i++) {
    			alTempRes = objAddEmails.verifyEmail(connection, objEmails.getStrGroupID(), (String) alTempMails.get(i));
    			if (alTempRes.size() > 0) {
    				alRetS.addAll(alTempRes);
    			} else {
    				OIBAAddEmails temp = new OIBAAddEmails();
    				temp.setStrEmailID((String) alTempMails.get(i));
    				alRetF.add(temp);
    			}
    		}
    		
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("verifyEmails - SQLException");
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("verifyEmails - " + e);
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("SuccessMails", alRetS);
            responseObject.addResponseObject("FailedMails", alRetF);
        }
        return responseObject;
	}
	
	public OIResponseObject addGroupMembers (OIBAAddEmails objMembers) {
		OIDAOAddEmails objAddEmails = new OIDAOAddEmails();
		int intAdded = 0;
		
		try {
    		getConnection();
    		String[] arMembers = objMembers.getArMembers();
    		for (int i = 0; i < arMembers.length; i++) {
    			intAdded += objAddEmails.addGroupMember(connection, objMembers.getStrGroupID(), arMembers[i]);
    		}
    		
        } catch (SQLException se) {
            error = ""+se.getErrorCode();
            logger.error("verifyEmails - SQLException");
        } catch (Exception e) {
            error = "OIDEFAULT";
            logger.error("verifyEmails");
        } finally {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Added", new Integer(intAdded));
        }
        return responseObject;
	}
}
