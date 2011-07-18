package com.oifm.forum;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.DateUtility;


public class OIDAOThreadsSearch extends OIBaseDao 
{
	private static final Logger logger = Logger.getLogger(OIDAOThreadsSearch.class);

	public ArrayList readUpToTopicListing(Connection connection,
			ArrayList arOIBVUpToTopicListing) throws Exception
    {   
        
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        ArrayList arOIBVUpToTopicListingResult = new ArrayList();
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_UPTO_TOPIC);
            rs = preparedStatement.executeQuery();
            while (rs.next())
            {
            	for (int i=0;i<arOIBVUpToTopicListing.size();i++)
                {
            		OIBVThreadsSearch aOIBVThreadsSearch = (OIBVThreadsSearch) arOIBVUpToTopicListing.get(i);
            		
            		 if (aOIBVThreadsSearch.getTopicId()!=null &&  
                    		aOIBVThreadsSearch.getTopicId().equalsIgnoreCase(rs.getString("TOPICID")))
                    {
            		 	aOIBVThreadsSearch.setLevel(rs.getString("HRNO"));
            		 	
    	                if (rs.getInt("HRNO")==1)
    	                {
    	                	OIBVThreadsSearch aOIBVThreadsSearch1 = new OIBVThreadsSearch();
    	                	aOIBVThreadsSearch1.setCategory(rs.getString("NAME"));
    	                	aOIBVThreadsSearch1.setCategoryId(rs.getString("TOPICID"));
    	                	logger.info(" aOIBVThreadsSearch1.getCategoryId() "+aOIBVThreadsSearch1.getCategoryId());
    	                    arOIBVUpToTopicListingResult.add(aOIBVThreadsSearch1);
    	                }	
    	                if (rs.getInt("HRNO")==2)
    	                {
    	                	OIBVThreadsSearch aOIBVThreadsSearch2 = new OIBVThreadsSearch();
    	                	aOIBVThreadsSearch2.setBoardName(rs.getString("NAME"));
    	                	aOIBVThreadsSearch2.setBoardId(rs.getString("TOPICID"));
    	                	logger.info(" aOIBVThreadsSearch2.getCategoryId() "+aOIBVThreadsSearch2.getCategoryId());
    	                	arOIBVUpToTopicListingResult.add(aOIBVThreadsSearch2);
    	                }
    	                if (rs.getInt("HRNO")==3)
    	                {
    	                    aOIBVThreadsSearch.setTopicName(rs.getString("NAME"));
    	                    aOIBVThreadsSearch.setTopicId(rs.getString("TOPICID"));
    	                    arOIBVUpToTopicListingResult.add(aOIBVThreadsSearch);
    	                }
            		 	
                    }
	                
                }
            }
        }
        catch(Exception e)
        {
            logger.error("readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVUpToTopicListing.size()==0)
            arOIBVUpToTopicListing=null;
        return arOIBVUpToTopicListing;
    }

    public ArrayList readPostListing(String pUserId,Connection connection,int iHours) throws Exception
    {
    	ArrayList arOIBVUpToTopicListing = new ArrayList();
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_POST_LIST_SEARCH);            
            logger.info(" iHours "+iHours);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setInt(2,iHours);
            preparedStatement.setString(3,pUserId);
            preparedStatement.setString(4,pUserId);            
            rs = preparedStatement.executeQuery();
            int topicId=0;
            logger.info(" rs "+rs);
            while (rs.next())
            {   
            		
                	OIBVThreadsSearch aOIBVThreadsSearch 
						= new OIBVThreadsSearch();
                	logger.info(" aOIBVThreadsSearch "+aOIBVThreadsSearch);
                    topicId = rs.getInt("topicid");
                    aOIBVThreadsSearch.setThreadName(rs.getString("TITLE"));
                    aOIBVThreadsSearch.setThreadId(rs.getString("THREADID"));
                    aOIBVThreadsSearch.setNickName(rs.getString("NICKNAME"));
                    aOIBVThreadsSearch.setPostedBy(rs.getString("POSTED_BY"));
                    aOIBVThreadsSearch.setNoThreads(rs.getString("COUNT"));
                    aOIBVThreadsSearch.setLastPostOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
                    aOIBVThreadsSearch.setReplies(rs.getString("REP"));
                    aOIBVThreadsSearch.setHits(rs.getString("HIT"));
                    aOIBVThreadsSearch.setTopicId(String.valueOf(topicId));
                    arOIBVUpToTopicListing.add(aOIBVThreadsSearch);
                
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            logger.error("readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVUpToTopicListing.size()==0)
            arOIBVUpToTopicListing=null;
        return arOIBVUpToTopicListing;
    }
    public ArrayList readBMPostListing(String pUserId,Connection connection) throws Exception
    {   
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        ArrayList arOIBVUpToTopicListing = new ArrayList();
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_POST_LIST_SEARCH_BM);
            preparedStatement.setString(1,pUserId);            
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);            
            rs = preparedStatement.executeQuery();
            int topicId=0;

            while (rs.next())
            {   
            		
                	OIBVThreadsSearch aOIBVThreadsSearch 
						= new OIBVThreadsSearch();
                	logger.info(" aOIBVThreadsSearch "+aOIBVThreadsSearch);
                    topicId = rs.getInt("topicid");
                    aOIBVThreadsSearch.setThreadName(rs.getString("TITLE"));
                    aOIBVThreadsSearch.setThreadId(rs.getString("THREADID"));
                    aOIBVThreadsSearch.setNickName(rs.getString("NICKNAME"));
                    aOIBVThreadsSearch.setPostedBy(rs.getString("POSTED_BY"));
                    aOIBVThreadsSearch.setNoThreads(rs.getString("COUNT"));
                    aOIBVThreadsSearch.setLastPostOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
                    aOIBVThreadsSearch.setReplies(rs.getString("REP"));
                    aOIBVThreadsSearch.setHits(rs.getString("HIT"));
                    aOIBVThreadsSearch.setTopicId(String.valueOf(topicId));
                    arOIBVUpToTopicListing.add(aOIBVThreadsSearch);
                
            }
        }
        catch(Exception e)
        {
            logger.error("readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVUpToTopicListing.size()==0)
            arOIBVUpToTopicListing=null;
        return arOIBVUpToTopicListing;
    }
    public ArrayList readPOPostListing(String pUserId,Connection connection) throws Exception
    {
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        ArrayList arOIBVUpToTopicListing = new ArrayList();
        try
        {
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_POST_LIST_SEARCH_PO);           
            
            preparedStatement.setString(1,pUserId);            
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);            
            rs = preparedStatement.executeQuery();
            int topicId=0;

            while (rs.next())
            {		
                	OIBVThreadsSearch aOIBVThreadsSearch 
						= new OIBVThreadsSearch();
                	logger.info(" aOIBVThreadsSearch "+aOIBVThreadsSearch);
                    topicId = rs.getInt("topicid");
                    aOIBVThreadsSearch.setThreadName(rs.getString("TITLE"));
                    aOIBVThreadsSearch.setThreadId(rs.getString("THREADID"));
                    aOIBVThreadsSearch.setNickName(rs.getString("NICKNAME"));
                    aOIBVThreadsSearch.setPostedBy(rs.getString("POSTED_BY"));
                    aOIBVThreadsSearch.setNoThreads(rs.getString("COUNT"));
                    aOIBVThreadsSearch.setLastPostOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
                    aOIBVThreadsSearch.setReplies(rs.getString("REP"));
                    aOIBVThreadsSearch.setHits(rs.getString("HIT"));
                    aOIBVThreadsSearch.setTopicId(String.valueOf(topicId));
                    arOIBVUpToTopicListing.add(aOIBVThreadsSearch);
                
            }
        }
        catch(Exception e)
        {
        	e.printStackTrace();
            logger.error("readUpToTopicListing -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBVUpToTopicListing.size()==0)
            arOIBVUpToTopicListing=null;
        return arOIBVUpToTopicListing;
    }
}