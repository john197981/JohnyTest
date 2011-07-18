package com.oifm.search;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAOAdvancedSearch extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOAdvancedSearch.class.getName());

    public ArrayList getSearchResult(String query,String pUserId,Connection connection) throws Exception
    {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList arOISearchResultBean = new ArrayList();
		OISearchResultBean objResult = null;
		
		try
		{
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pUserId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
            	objResult = new OISearchResultBean();
            	objResult.setRowNum(rs.getString("NUM"));
            	objResult.setStrID(rs.getString("ID"));
            	objResult.setStrTitle(rs.getString("TITLE"));
            	objResult.setStrDescription(rs.getString("DESCRIPTION"));
            	objResult.setStrCreatedBy(rs.getString("CREATED_BY"));
            	objResult.setStrNickname(rs.getString("NICKNAME"));
            	objResult.setStrCreatedOn(rs.getString("CREATED_ON"));
            	arOISearchResultBean.add(objResult);
            }
        } catch(SQLException e) 
        {
            logger.error("getSearchResult() : " + e);
            throw e;
        } finally 
        {
            OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        if (arOISearchResultBean.size()==0)
            arOISearchResultBean=null;
        return arOISearchResultBean;
    }

    public int getTotalRecordCount(String query,String pUserId,Connection connection) throws Exception
    {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count=0;
		try
		{
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pUserId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next()) 
            {
                count = rs.getInt("COUNT");
            }
        } catch(SQLException e) 
        {
            logger.error("getSearchResult() : " + e);
            throw e;
        } finally 
        {
            OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        return count;
    }
    
    public int getTotalRecordCount(String query,Connection connection) throws Exception
    {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		int count=0;
		try
		{
			preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            
            if (rs.next()) 
            {
                count = rs.getInt("COUNT");
            }
        } catch(SQLException e) 
        {
            logger.error("getSearchResult() : " + e);
            throw e;
        } finally 
        {
            OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        return count;
    }

    public ArrayList getSearchResultWithinLimit(String query,String pUserId,int startRecNo,int endRecNo, Connection connection) throws Exception
    {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList arOISearchResultBean = new ArrayList();
		OISearchResultBean objResult = null;
		
		try
		{
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, pUserId);
            preparedStatement.setInt(2, startRecNo);
            preparedStatement.setInt(3, endRecNo);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
            	objResult = new OISearchResultBean();
            	objResult.setRowNum(rs.getString("NUM"));
            	objResult.setStrID(rs.getString("ID"));
            	objResult.setStrTitle(rs.getString("TITLE"));
            	objResult.setStrDescription(rs.getString("DESCRIPTION"));
            	objResult.setStrCreatedBy(rs.getString("CREATED_BY"));
            	objResult.setStrNickname(rs.getString("NICKNAME"));
            	objResult.setStrCreatedOn(rs.getString("CREATED_ON"));
            	arOISearchResultBean.add(objResult);
            }
        } catch(SQLException e) 
        {
            logger.error("getSearchResult() : " + e);
            throw e;
        } finally 
        {
            OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        if (arOISearchResultBean.size()==0)
            arOISearchResultBean=null;
        return arOISearchResultBean;
    }

//  Modified by K.K.Kumaresan on Aug 17,2009
    public ArrayList getSearchResultWithinLimit(String query,int startRecNo,int endRecNo, Connection connection) throws Exception
    {
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		ArrayList arOISearchResultBean = new ArrayList();
		OISearchResultBean objResult = null;
		
		try
		{
			preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, startRecNo);
            preparedStatement.setInt(2, endRecNo);
            rs = preparedStatement.executeQuery();
            
            while (rs.next()) 
            {
            	objResult = new OISearchResultBean();
            	/*objResult.setRowNum(rs.getString("NUM"));
            	objResult.setStrID(rs.getString("ID"));
            	objResult.setStrTitle(rs.getString("TITLE"));
            	objResult.setStrDescription(rs.getString("DESCRIPTION"));
            	objResult.setStrCreatedBy(rs.getString("CREATED_BY"));
            	objResult.setStrNickname(rs.getString("NICKNAME"));
            	objResult.setStrCreatedOn(rs.getString("CREATED_ON"));*/
            	objResult.setRowNum(rs.getString("NUM"));
            	objResult.setStrID(rs.getString("letterid"));
            	objResult.setStrTitle(rs.getString("topic"));
            	objResult.setStrDescription(rs.getString("SUBJECT"));
            	objResult.setStrCreatedBy(rs.getString("CREATEDBY"));
            	objResult.setStrNickname(rs.getString("NICKNAME"));
            	objResult.setStrCreatedOn(rs.getString("CREATED_ON"));
            	arOISearchResultBean.add(objResult);
            }
        } catch(SQLException e) 
        {
            logger.error("ASM getSearchResult() : " + e);
            throw e;
        } finally 
        {
            OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
        }
        if (arOISearchResultBean.size()==0)
            arOISearchResultBean=null;
        return arOISearchResultBean;
    }
}
