package com.oifm.forum.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.forum.OIForumSqls;

public class OIDAOForumListing extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOForumListing.class.getName());
    
    public ArrayList readForumList(String pUserId,Connection connection) throws Exception
    {
        ArrayList arOIBVForumList = new ArrayList();
        OIBVForumList aOIBVForumList=null;
        PreparedStatement preparedStatement = null;
        PreparedStatement preparedStatement1 = null;
        ResultSet rs=null;
        ResultSet  rs1 = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.FORUM_LISTING);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBVForumList = new OIBVForumList();
                aOIBVForumList.setBoardId(rs.getInt("bBOARDID"));
                aOIBVForumList.setBoardName(rs.getString("bName"));
                aOIBVForumList.setCategoryId(rs.getInt("cCATEGORYID"));
                aOIBVForumList.setCategoryName(rs.getString("cName"));
                aOIBVForumList.setCreatedOn(rs.getDate("CREATED_ON"));
                aOIBVForumList.setDivision(rs.getString("DESCRIPTION"));
                aOIBVForumList.setTopicId(rs.getInt("TOPICID"));
                aOIBVForumList.setTopicName(rs.getString("TOPICNAME"));
                aOIBVForumList.setLinkFlag("false");
                preparedStatement1=connection.prepareStatement(OIForumSqls.FORUM_USERLIST);
                rs1 = preparedStatement1.executeQuery();
                while(rs1.next())
                {
                    if (rs1.getString("USERID") != null && rs1.getString("BOARDID") != null)
                    {
                        if (pUserId.equalsIgnoreCase(rs1.getString("USERID")) && rs1.getInt("BOARDID")==rs.getInt("bBOARDID"))
                        {
                            aOIBVForumList.setLinkFlag("true");
                        }
                    }
                }
                if (rs1!=null)
                    rs1.close();
                if (preparedStatement1!=null)
                    preparedStatement1.close();
                arOIBVForumList.add(aOIBVForumList);
            }
        }
        catch(Exception e)
        {
            logger.error("readAllFeedBack -" + e.getMessage());
            throw e;
        }
        finally
        {
        	if (rs1!=null)
                rs1.close();
            if (preparedStatement1!=null)
                preparedStatement1.close();
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
            
        }

        if (arOIBVForumList.size()==0)
            arOIBVForumList=null;
        return arOIBVForumList;
    }
}
