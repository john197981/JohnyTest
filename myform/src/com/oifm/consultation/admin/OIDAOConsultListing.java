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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIConsultationSqls;
import com.oifm.survey.OIBASurvey;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;

public class OIDAOConsultListing extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultListing.class.getName());
    
    public ArrayList readListing(Connection connection) throws Exception
    {
        OIBVConsultListing aOIBVConsultListing=null;
        ArrayList arOIBVConsultListing=new ArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try
        {
            //getConnection();
        	String query = OIConsultationSqls.CONSULT_LISTING;
        	query +=  " order by aCategoryId ASC, aPaperId DESC";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
                aOIBVConsultListing = new OIBVConsultListing();
                aOIBVConsultListing.setCategoryId(rs.getInt(OIConsultConstant.Q_aCategoryId));
                aOIBVConsultListing.setCategoryName(rs.getString(OIConsultConstant.Q_aName));
                aOIBVConsultListing.setDescription(rs.getString(OIConsultConstant.Q_aDescription));
                aOIBVConsultListing.setFromDt((java.util.Date) rs.getDate(OIConsultConstant.Q_aFromDt));
                aOIBVConsultListing.setToDt((java.util.Date) rs.getDate(OIConsultConstant.Q_aToDt));
				aOIBVConsultListing.setMailStatus(rs.getString(OIConsultConstant.Q_aMailStatus));
                aOIBVConsultListing.setPaperId(rs.getInt(OIConsultConstant.Q_aPaperId));
                aOIBVConsultListing.setPaperTitle(rs.getString(OIConsultConstant.Q_aTitle));
                aOIBVConsultListing.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                aOIBVConsultListing.setDivision(rs.getString("DESCRIPTION"));
				aOIBVConsultListing.setTargetAudiance( rs.getString("TARGETAUDIENCE"));
                arOIBVConsultListing.add(aOIBVConsultListing);
            }

            if (arOIBVConsultListing.size()==0)
                arOIBVConsultListing = null;
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return arOIBVConsultListing;
    }


    public ArrayList readFeedbackForListing(Connection connection) throws Exception
    {
        OIBVConsultListingFeedBack aOIBVConsultListingFeedBack=null;
        ArrayList arOIBVConsultListingFeedBack=new ArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet rs =null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_LISTING_FEEDBACK);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
                aOIBVConsultListingFeedBack = new OIBVConsultListingFeedBack();
                aOIBVConsultListingFeedBack.setPaperID(rs.getInt(OIConsultConstant.Q_aPaperId));
                aOIBVConsultListingFeedBack.setNoOfFeedbacks(rs.getInt(OIConsultConstant.Q_aNoFeedBacks));
                arOIBVConsultListingFeedBack.add(aOIBVConsultListingFeedBack);
            }

            if (arOIBVConsultListingFeedBack.size()==0)
                arOIBVConsultListingFeedBack = null;
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return arOIBVConsultListingFeedBack;
    }

    public ArrayList readDivisionPaper(Connection connection,String divisionCode,String sortKey, String ascdec) throws Exception
	{
		//System.out.println("DAO::readDivisionPaper:divisionCode::" + divisionCode);
		OIBVConsultListing aOIBVConsultListing=null;
		ArrayList arOIBVConsultListing=new ArrayList();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String query = "";
		
		
		
		if((sortKey == null) || sortKey.equals(""))
			sortKey = "a.aPaperId";
		
		if((ascdec == null) || ascdec.equals(""))
			ascdec = "desc";
		
		/*if(sortKey.equalsIgnoreCase("a.aMailStatus")){
			sortKey = " aToDt "+ascdec+" ,security "+ascdec+", "+sortKey;
		}*/
		//System.out.println("OIDAOConsultListing: readDivisionPaper - divisionCode : "+divisionCode);
		if(divisionCode.equals("-1")){
			query = OIConsultationSqls.CONSULT_PAPER_LISTING_ALL;
		}
		else{
			query = OIConsultationSqls.CONSULT_PAPER_LISTING;
		}
		
		if(sortKey.equalsIgnoreCase("aNoFeedBacks") || sortKey.equalsIgnoreCase("aPaperId"))
			query +=  " order by aCategoryId ASC, " +sortKey  + " "+ascdec + "";
		else if (!sortKey.equalsIgnoreCase("AFROMDT") && !sortKey.equalsIgnoreCase("a.aPublishedOn"))
			query += " order by aCategoryId ASC, UPPER(" + sortKey + ") " + ascdec + "";
		else
			query += " order by aCategoryId ASC, TO_DATE(" + sortKey + ") " + ascdec + "";
		
		try
		{
			//getConnection();
			//System.out.println("readDivisionPaper::Query..." + query);
			preparedStatement = connection.prepareStatement(query);
			
			if(!divisionCode.equals("-1"))
			{
				preparedStatement.setString(1,divisionCode);
			}
			
			rs = preparedStatement.executeQuery();
			
			while (rs.next()) 
			{
				//System.out.println("readDivisionPaper:" + rs.getString(OIConsultConstant.Q_aName));
				aOIBVConsultListing = new OIBVConsultListing();
				aOIBVConsultListing.setCategoryId(rs.getInt(OIConsultConstant.Q_aCategoryId));
				aOIBVConsultListing.setCategoryName(rs.getString(OIConsultConstant.Q_aName));
				aOIBVConsultListing.setDescription(rs.getString(OIConsultConstant.Q_aDescription));
				aOIBVConsultListing.setFromDt((java.util.Date) rs.getDate(OIConsultConstant.Q_aFromDt));
				aOIBVConsultListing.setToDt((java.util.Date) rs.getDate(OIConsultConstant.Q_aToDt));
				aOIBVConsultListing.setMailStatus(rs.getString(OIConsultConstant.Q_aMailStatus));
				aOIBVConsultListing.setPublishedOn(rs.getString(OIConsultConstant.Q_aPublishedOn));
				aOIBVConsultListing.setPaperId(rs.getInt(OIConsultConstant.Q_aPaperId));
				aOIBVConsultListing.setPaperTitle(rs.getString(OIConsultConstant.Q_aTitle));
				aOIBVConsultListing.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
				aOIBVConsultListing.setDivision(rs.getString("DESCRIPTION"));
				aOIBVConsultListing.setTargetAudiance( rs.getString("TARGETAUDIENCE"));
				aOIBVConsultListing.setNoFeedBacks(rs.getString("anoFeedBacks"));
				arOIBVConsultListing.add(aOIBVConsultListing);
			}

			if (arOIBVConsultListing.size()==0)
				arOIBVConsultListing = null;
			//preparedStatement.close();
		}catch(SQLException sqle)
		{
			logger.error(sqle.getMessage());
			//connection.rollback();
			throw sqle;
		}
		finally
		{
			//freeConnection();
			if (preparedStatement!=null)
				preparedStatement.close();
			if (rs!=null)
				rs.close();
		}

		return arOIBVConsultListing;
	}
    
    public ArrayList getDivisionList(Connection con) throws SQLException 
	{
		ArrayList alDivList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBVConsultListing aOIBVConsultListing=null;
		
		try 
		{
			pstmt = con.prepareStatement(OIConsultationSqls.CONSULT_DIVISION_LISTING);
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				aOIBVConsultListing = new OIBVConsultListing();
				
				aOIBVConsultListing.setDivision(rs.getString("DIVISIONNAME"));
				aOIBVConsultListing.setDivisionCode(rs.getString("DIVISIONCODE"));
				//System.out.println("OIDAOConsultListing: getDivisionList - DIVISIONCODE : "+rs.getString("DIVISIONCODE"));
				alDivList.add(aOIBVConsultListing);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getDivisionList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alDivList;
	}
    
    public boolean CheckCategoty(Connection con) throws SQLException 
	{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int count = 0;
		try 
		{
			pstmt = con.prepareStatement("SELECT COUNT(CATEGORYID) FROM OI_CP_CATEGORY");
			rs = pstmt.executeQuery();
			while(rs.next())
			{
				count = rs.getInt(1);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" getDivisionList() "+se.getMessage());
			throw se;
		} 
		finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return (count>0);
	}
}
