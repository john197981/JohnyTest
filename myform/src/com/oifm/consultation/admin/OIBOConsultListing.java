package com.oifm.consultation.admin;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			30/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseBo;
import com.oifm.common.OIResponseObject;
import com.oifm.consultation.OIConsultConstant;

public class OIBOConsultListing extends OIBaseBo 
{
    Logger logger = Logger.getLogger(OIBOConsultListing.class.getName());
    
    public OIResponseObject readConsultation()
    {
        try
        {
            getConnection();
            OIDAOConsultListing aOIDAOConsultListing = new OIDAOConsultListing();
            ArrayList arOIBVConsultListing = aOIDAOConsultListing.readListing(connection);
            ArrayList arOIBVConsultListingFeedBack = aOIDAOConsultListing.readFeedbackForListing(connection);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultListing,arOIBVConsultListing);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultListingFeedBack,arOIBVConsultListingFeedBack);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
    
    
    public OIResponseObject getConsultDivision()	
	{
    	OIDAOConsultListing aOIDAOConsultListing = new OIDAOConsultListing();
		ArrayList alPaperList = null;
		boolean CategoryFlag = false;
		
		try 
		{
			getConnection();
			ArrayList arOIBVConsultDivision = aOIDAOConsultListing.getDivisionList(connection);
			
			CategoryFlag = aOIDAOConsultListing.CheckCategoty(connection);
			responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultDivision,arOIBVConsultDivision);
			
			if(CategoryFlag)
				responseObject.addResponseObject("CategoryFlag","true");
			else
				responseObject.addResponseObject("CategoryFlag","false");
		} 
		catch(SQLException se) 
		{
			error = ""+se.getErrorCode();
            logger.error(se);
		} 
		catch(Exception be)	
		{
			error = "OIDEFAULT";
			logger.error(" getConsultDivision => "+be);
		} 
		finally 
		{
			freeConnection();
			addToResponseObject();
		}
		return responseObject;
	}

    
    public OIResponseObject readConsultationPaper(String divisionCode,String sortKey, String ascdec)
    {
        try
        {
			//System.out.println("OIBOConsultListing::readConsultationPaper");
            getConnection();
            OIDAOConsultListing aOIDAOConsultListing = new OIDAOConsultListing();
            ArrayList arOIBVConsultListing = aOIDAOConsultListing.readDivisionPaper(connection,divisionCode,sortKey,ascdec);
			//ArrayList arOIBVConsultDivision = aOIDAOConsultListing.readDivisionListing(connection);
			//ArrayList arOIBVConsultPaper = aOIDAOConsultListing.readDivisionPaper(connection,divisionCode);
			ArrayList arOIBVConsultListingFeedBack = aOIDAOConsultListing.readFeedbackForListing(connection);
            responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultListing,arOIBVConsultListing);
			//responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultDivision,arOIBVConsultDivision);
			//responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultPaper ,arOIBVConsultPaper);
		    responseObject.addResponseObject(OIConsultConstant.K_arOIBVConsultListingFeedBack,arOIBVConsultListingFeedBack);
        }
        catch(Exception e)
        {
            error = e.getMessage();
            logger.error(e);
        }
        finally
        {
            freeConnection();
        }
        addToResponseObject();
        return responseObject;
    }
}
