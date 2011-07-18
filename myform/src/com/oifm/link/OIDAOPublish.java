package com.oifm.link;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.forum.OIBVForumListing;
import com.oifm.forum.OIBVHottestThread;
import com.oifm.utility.DateUtility;

public class OIDAOPublish 
{
    Logger logger = Logger.getLogger(OIDAOPublish.class.getName());
    
    public ArrayList readPostListing(String pUserId,ArrayList arOIThreadListing,Connection connection) throws Exception
    {
        ArrayList arOIBVListing = new ArrayList();
        OIBVForumListing aOIBVForumListing = null;
        OIBVHottestThread aOIBVHottestThread = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement = connection.prepareStatement(OILinkSqls.POSTING_DETAILS);
            preparedStatement.setString(1,pUserId);
            preparedStatement.setString(2,pUserId);
            preparedStatement.setString(3,pUserId);
            rs = preparedStatement.executeQuery();
            int threadId=0;
            if (arOIThreadListing!=null)
            {
	            for (int i=0;i<arOIThreadListing.size();i++)
	            {
	                aOIBVHottestThread = (OIBVHottestThread) arOIThreadListing.get(i);
	                aOIBVForumListing = new OIBVForumListing();
	                aOIBVForumListing.setThreadName(aOIBVHottestThread.getTitle());
	                aOIBVForumListing.setThreadId(aOIBVHottestThread.getThreadId());
	                if (aOIBVHottestThread.getPostedOn()!=null)
	                    aOIBVForumListing.setLastPostOn(aOIBVHottestThread.getPostedOn());
	                else
	                    aOIBVForumListing.setLastPostOn("");
	                if (aOIBVHottestThread.getNumberOfPosts()!=null)
	                    aOIBVForumListing.setNumberOfPosts(aOIBVHottestThread.getNumberOfPosts());
	                else
	                    aOIBVForumListing.setNumberOfPosts("");
	                
	                arOIBVListing.add(aOIBVForumListing);
	            }
            }
            while (rs.next())
            {
                if (threadId!=rs.getInt("THREADID"))
                {
                    threadId = rs.getInt("THREADID");
	                for (int i=0;i<arOIBVListing.size();i++)
	                {
	                    aOIBVForumListing = (OIBVForumListing) arOIBVListing.get(i);
	                    if (aOIBVForumListing.getThreadId()!=null && aOIBVForumListing.getThreadId().equalsIgnoreCase(rs.getString("THREADID")))
	                    {
	                        if (rs.getString("NICKNAME")!=null)
	                            aOIBVForumListing.setNickName(rs.getString("NICKNAME"));
	                        else
	                            aOIBVForumListing.setNickName("");
	                        if (rs.getString("POSTED_BY")!=null)
	                            aOIBVForumListing.setPostedBy(rs.getString("POSTED_BY"));
	                        else
	                            aOIBVForumListing.setPostedBy("");
	                        if (rs.getString("CREATED_BY")!=null)
	                            aOIBVForumListing.setCreatedBy(rs.getString("CREATED_BY"));
	                        else
		                        aOIBVForumListing.setCreatedBy("");
	                        if (rs.getString("CREATED_ON")!=null)
	                            aOIBVForumListing.setCreatedOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("CREATED_ON")));
	                        else
	                            aOIBVForumListing.setCreatedOn("");
	                        if (rs.getString("COUNT")!=null)
		                        aOIBVForumListing.setNoThreads(rs.getString("COUNT"));
	                        else
		                        aOIBVForumListing.setNoThreads("");
	                        if (rs.getString("POSTED_ON")!=null)
		                        aOIBVForumListing.setLastPostOn(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("POSTED_ON")));
	                        else
		                        aOIBVForumListing.setLastPostOn("");
	                        
	                        arOIBVListing.set(i,aOIBVForumListing);
	                        break;
	                    }
	                }
                }
            }
        }
        catch(Exception e)
        {
            logger.error("readPostListing -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }
        if (arOIBVListing.size()==0)
            arOIBVListing=null;
        return arOIBVListing;
    }

}
