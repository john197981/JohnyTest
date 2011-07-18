/*
 * File name	= OIBORanking.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.sql.SQLException;
import java.util.ArrayList;


import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;


public class OIBOSummaryDetails extends OIBaseBo 
{
	Logger logger = Logger.getLogger(OIBOSummaryDetails.class);
	
	public OIBOSummaryDetails() {}
	
	public OIResponseObject getSummaryDetails() 
	{
    	OIDAOSummaryDetails objSumDt = new OIDAOSummaryDetails();
    	ArrayList alDiscAge = new ArrayList();
    	ArrayList alDiscDesg = new ArrayList();
    	ArrayList alDiscSchLvl = new ArrayList();

    	ArrayList alConsAge = new ArrayList();
    	ArrayList alConsDesg = new ArrayList();
    	ArrayList alConsSchLvl = new ArrayList();

    	ArrayList alSurveyAge = new ArrayList();
    	ArrayList alSurveyDesg = new ArrayList();
    	ArrayList alSurveySchLvl = new ArrayList();
    	
    	ArrayList alTotalList = new ArrayList();
    	
    	try 
    	{
			getConnection();
    		//Discussion Forum --Age
			alDiscAge = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_DF_AGE);
			//Discussion Forum --Designation
			alDiscDesg = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_DF_DESG);
			//Discussion Forum --SchoolLevel
			alDiscSchLvl = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_DF_SCHLVL);
    		//Consultation Paper--Age
			alConsAge = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_CP_AGE);
			//Consultation Paper--Designation
			alConsDesg = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_CP_DESG);
			//Consultation Paper--SchoolLevel
			alConsSchLvl = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_CP_SCHLVL);
    		//Survey--Age
			alSurveyAge = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_SV_AGE);
			//Survey--Designation
			alSurveyDesg = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_SV_DESG);
			//Survey--SchoolLevel
			alSurveySchLvl = objSumDt.getSummDts(connection, OISearchSqls.QRY_SMRY_DET_SV_SCHLVL);
			
			alTotalList.add(alDiscAge);
			alTotalList.add(alDiscDesg);
			alTotalList.add(alDiscSchLvl);

			alTotalList.add(alConsAge);
			alTotalList.add(alConsDesg);
			alTotalList.add(alConsSchLvl);

			alTotalList.add(alSurveyAge);
			alTotalList.add(alSurveyDesg);
			alTotalList.add(alSurveySchLvl);
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getSummaryDetails() - SQLException->" + se);
            System.out.println("bo------error"+se);
        } 
    	catch (Exception e) 
        {
    		System.out.println("bo------error"+e);
            error = "OIDEFAULT";
            logger.error("getSummaryDetails()->" + e);
        } 
    	finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("TotalList", alTotalList);
        }
        
        return responseObject;
    }
	
	
}