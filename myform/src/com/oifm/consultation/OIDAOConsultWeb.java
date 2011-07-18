package com.oifm.consultation;

/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			01/08/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
public class OIDAOConsultWeb extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultWeb.class.getName());
    /**
     * getConnection
     * Query
     * 
     * return ArrayList of OIBVPaper 
     */
    public ArrayList readAllActivePapers(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVPaper = new ArrayList();
        OIBVPaper aOIBVPaper = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_WEB_LISTING_CURRENT_PAPERS);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
                aOIBVPaper = new OIBVPaper();
                aOIBVPaper.setActive(rs.getString(OIConsultConstant.Q_ACTIVE));
                aOIBVPaper.setCategoryId(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBVPaper.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBVPaper.setCategoryName(rs.getString(OIConsultConstant.Q_NAME));
                aOIBVPaper.setDescription(rs.getString(OIConsultConstant.Q_DESCRIPTION));
                aOIBVPaper.setExpireDate(rs.getDate(OIConsultConstant.Q_TO_DT));
                aOIBVPaper.setStartDate(rs.getDate(OIConsultConstant.Q_FROM_DT));
                aOIBVPaper.setPaperName(rs.getString(OIConsultConstant.Q_TITLE));
                aOIBVPaper.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                aOIBVPaper.setStatus(rs.getString(OIConsultConstant.Q_STATUS));
                arOIBVPaper.add(aOIBVPaper);
            }
            if (arOIBVPaper.size()==0)
                arOIBVPaper = null;
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error("readAllActivePapers - " + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//      	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }

        return arOIBVPaper;
    }
    
    /**
     * getConnection
     * Query
     * 
     * return ArrayList of OIBVPaper 
     */
    public ArrayList readAllPastPapers(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVPaper = new ArrayList();
        OIBVPaper aOIBVPaper = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_WEB_LISTING_PAST_PAPERS);
            preparedStatement.setString(1,pUserId);
            //preparedStatement.setString(2,pUserId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
                aOIBVPaper = new OIBVPaper();
                aOIBVPaper.setActive(rs.getString(OIConsultConstant.Q_ACTIVE));
                aOIBVPaper.setCategoryId(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBVPaper.setPaperId(rs.getInt(OIConsultConstant.Q_PAPERID));
                aOIBVPaper.setCategoryName(rs.getString(OIConsultConstant.Q_NAME));
                aOIBVPaper.setDescription(rs.getString(OIConsultConstant.Q_DESCRIPTION));
                aOIBVPaper.setExpireDate(rs.getDate(OIConsultConstant.Q_TO_DT));
                aOIBVPaper.setStartDate(rs.getDate(OIConsultConstant.Q_FROM_DT));
                aOIBVPaper.setPaperName(rs.getString(OIConsultConstant.Q_TITLE));
                aOIBVPaper.setSecurity(rs.getString(OIConsultConstant.Q_SECURITY));
                //aOIBVPaper.setStatus(rs.getString(OIConsultConstant.Q_STATUS));

                arOIBVPaper.add(aOIBVPaper);
            }
            if (arOIBVPaper.size()==0)
                arOIBVPaper = null;
            //preparedStatement.close();
        }catch(Exception sqle)
        {
            logger.error("readAllPastPapers " + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
//      	 Start : Added by deva on Sep 26, 2007
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }

        return arOIBVPaper;
    }
}
