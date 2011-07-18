package com.oifm.siteRanking;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.apache.log4j.Logger;



public class OIDAOWebSiteRanking 
{
	Logger logger = Logger.getLogger(OIDAOWebSiteRanking.class.getName());
	
	public boolean save(OIBAWebSiteRanking pOIBAWebSiteRanking,Connection connection) throws Exception
	{
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
        	preparedStatement = connection.prepareStatement(OIWebsiteRankingSqls.SAVE);
        	preparedStatement.setString(1,pOIBAWebSiteRanking.getUserId());
        	preparedStatement.setString(2,pOIBAWebSiteRanking.getActionId());
        	//preparedStatement.setString(3,pOIBAWebSiteRanking.getActionType());
        	preparedStatement.setInt(3,pOIBAWebSiteRanking.getItemId());
        	i = preparedStatement.executeUpdate();
        }catch(Exception sqle)
        {
            logger.error(sqle.getMessage());
            throw sqle;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        
        if (i==0)
        {
            logger.error("Save Failed");
            throw new Exception("Save Failed");
        }
        else
        {
            flag = true;
        }
        return flag;
	}
}
