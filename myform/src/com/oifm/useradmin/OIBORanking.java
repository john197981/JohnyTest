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


public class OIBORanking extends OIBaseBo 
{
	Logger logger = Logger.getLogger(OIBORanking.class);
	
	public OIBORanking() {}
	
	public OIResponseObject getPostModuleRanking(OIBARanking objBARanking) 
	{
    	OIDAORanking objRanking = new OIDAORanking();
    	ArrayList alResult = new ArrayList();
    	
    	try 
    	{
			getConnection();
    		alResult = objRanking.getPostModuleRanking(connection, Integer.parseInt(objBARanking.getStrNumToShow()), Integer.parseInt(objBARanking.getStrPeriod()));
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getPostModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getPostModuleRanking()->" + e);
        } 
    	finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Result", alResult);
        }
        
        return responseObject;
    }
	
	public OIResponseObject getThreadModuleRanking(OIBARanking objBARanking) 
	{
		OIDAORanking objRanking = new OIDAORanking();
    	ArrayList alResult = new ArrayList();
    	
    	try 
    	{
			getConnection();
			alResult = objRanking.getThreadModuleRanking(connection, Integer.parseInt(objBARanking.getStrNumToShow()), Integer.parseInt(objBARanking.getStrPeriod()));
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getThreadModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getThreadModuleRanking()->" + e);
        } 
    	finally 
    	{
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Result", alResult);
        }
        
        return responseObject;
    }
	
	public OIResponseObject getSurveyModuleRanking(OIBARanking objBARanking) 
	{
		OIDAORanking objRanking = new OIDAORanking();
    	ArrayList alResult = new ArrayList();
    	
    	try 
    	{
			getConnection();
			alResult = objRanking.getSurveyModuleRanking(connection, Integer.parseInt(objBARanking.getStrNumToShow()), Integer.parseInt(objBARanking.getStrPeriod()));
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getSurveyModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getSurveyModuleRanking()->" + e);
        } 
    	finally 
    	{
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Result", alResult);
        }
        
        return responseObject;
    }
	
	public OIResponseObject getPaperModuleRanking(OIBARanking objBARanking) 
	{
		OIDAORanking objRanking = new OIDAORanking();
    	ArrayList alResult = new ArrayList();
    	
    	try 
    	{
			getConnection();
			alResult = objRanking.getPaperModuleRanking(connection, Integer.parseInt(objBARanking.getStrNumToShow()), Integer.parseInt(objBARanking.getStrPeriod()));
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getPaperModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getPaperModuleRanking()->" + e);
        } 
    	finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Result", alResult);
        }
        
        return responseObject;
    }
	
	public OIResponseObject getWebsiteRanking(OIBAWebRanking objBAWebRanking) 
	{
		OIDAORanking objRanking = new OIDAORanking();
    	ArrayList alResult = new ArrayList();
    	ArrayList alType = new ArrayList();
    	ArrayList alList  = new ArrayList();
    	OIBARanking objBARanking = new OIBARanking();
    	ArrayList alResultDetails = new ArrayList();
    	try 
    	{
			getConnection();
			alResult = objRanking.getWebsiteRanking(connection,objBAWebRanking);
			if(objBAWebRanking.getHidAction() !=null && 
					objBAWebRanking.getHidAction().equals("WebRankDetails")){
				alResultDetails = objRanking.getWebsiteDetails(connection,objBAWebRanking);
			}
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getWebsiteModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getWebsiteModuleRanking()->" + e);
        } 
    	finally 
        {
            freeConnection();
            addToResponseObject();
            responseObject.addResponseObject("Result", alResult);
            responseObject.addResponseObject("ResultDetails", alResultDetails);
        }
        
        return responseObject;
    }
	public OIResponseObject getWebsiteRankingList() 
	{
		OIDAORanking objRanking = new OIDAORanking();    	
    	ArrayList alType = new ArrayList();
    	ArrayList alList  = new ArrayList();
    	OIBARanking objBARanking = new OIBARanking();
    	try 
    	{
    		getConnection();
    		alType = objRanking.getTypeList(connection);
			for(int i = 0; i < alType.size();i++)
			{
				ArrayList alRow = (ArrayList)alType.get(i);
				alList.add(new org.apache.struts.util.LabelValueBean(String.valueOf(alRow.get(1)),String.valueOf(alRow.get(0))));
			}
    	} 
		catch (SQLException se) 
		{
		    error = ""+se.getErrorCode();
		    logger.error("getWebsiteRankingList() - SQLException->" + se);
		} 
		catch (Exception e) 
		{
		    error = "OIDEFAULT";
		    logger.error("getWebsiteRankingList()->" + e);
		} 
		finally 
		{
		    freeConnection();
		    addToResponseObject();
		    responseObject.addResponseObject("type", alType); 
		    responseObject.addResponseObject("typeList", alList);
		    
		}
		
		return responseObject;
	}
	public void clearLog() 
	{
		OIDAORanking objRanking = new OIDAORanking();
    	try 
    	{
			getConnection();
			objRanking.clearLog(connection);
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getThreadModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getThreadModuleRanking()->" + e);
        } 
    	finally 
    	{
            freeConnection();
        }
    }
	public double getCount() 
	{
		OIDAORanking objRanking = new OIDAORanking();
		double iCount = 0;
    	try 
    	{
			getConnection();
			iCount = objRanking.getCount(connection);
        } 
    	catch (SQLException se) 
    	{
            error = ""+se.getErrorCode();
            logger.error("getThreadModuleRanking() - SQLException->" + se);
        } 
    	catch (Exception e) 
        {
            error = "OIDEFAULT";
            logger.error("getThreadModuleRanking()->" + e);
        } 
    	finally 
    	{
            freeConnection();
        }
        return iCount;
        
    }
	
}