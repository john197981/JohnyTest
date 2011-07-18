package com.oifm.forum.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.forum.OIBAThread;
import com.oifm.forum.OIForumSqls;

public class OIDAOForumTopic extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOForumTopic.class.getName());
    
    public OIBAForumTopic read(int pTopicId, Connection connection) throws Exception
    {
        OIBAForumTopic aOIBAForumTopic=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_Topic);
            preparedStatement.setInt(1,pTopicId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                aOIBAForumTopic = new OIBAForumTopic();
                aOIBAForumTopic.setTopicId(rs.getInt("TOPICID"));
                aOIBAForumTopic.setBoardId(rs.getInt("BOARDID"));
                aOIBAForumTopic.setTopicName(rs.getString("TOPICNAME"));
                aOIBAForumTopic.setModReq(rs.getString("MODERATION_REQ"));
                aOIBAForumTopic.setCreatedBy(rs.getString("CREATED_BY"));
                aOIBAForumTopic.setCreatedOn(rs.getDate("CREATED_ON"));
                aOIBAForumTopic.setTopicDesc(rs.getString("TOPICDESC"));
            }
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
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }

        return aOIBAForumTopic;
    }

    public boolean checkDuplicateTopic(String pTopicName,int pTopicId,int pBoardId,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            //PreparedStatement preparedStatement = null;
            if (pBoardId==0)
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.FORUM_CHECK_DUPLICATE_TOPIC);
                preparedStatement.setInt(1,pBoardId);
                preparedStatement.setString(2,pTopicName);
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.FORUM_CHECK_DUPLICATE_TOPIC1);
                preparedStatement.setInt(1,pBoardId);
                preparedStatement.setString(2,pTopicName);                
                preparedStatement.setInt(3,pTopicId);
            }
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                int count = rs.getInt("count");
                if (count==0)
                    flag=true;
            }
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("checkDuplicateTopic- " + sqle.getMessage());
            //connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
        }        
        return flag;
    }

    public boolean save(OIBAForumTopic pOIBAForumTopic,Connection connection) throws Exception
    {
        boolean flag = false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            //getConnection();
            //PreparedStatement preparedStatement=null;
            if (pOIBAForumTopic.getTopicId()==0)
            {
	            preparedStatement = connection.prepareStatement(OIForumSqls.SAVE_TOPIC);
	            preparedStatement.setInt(1,pOIBAForumTopic.getBoardId());
	            preparedStatement.setString(2,pOIBAForumTopic.getTopicName());
	            preparedStatement.setString(3,pOIBAForumTopic.getModReq());
	            preparedStatement.setString(4,pOIBAForumTopic.getCreatedBy());
	            preparedStatement.setString(5,pOIBAForumTopic.getTopicDesc());
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.UPDATE_TOPIC);
	            preparedStatement.setInt(1,pOIBAForumTopic.getBoardId());
	            preparedStatement.setString(2,pOIBAForumTopic.getTopicName());
	            preparedStatement.setString(3,pOIBAForumTopic.getModReq());
	            preparedStatement.setString(4,pOIBAForumTopic.getTopicDesc());
	            preparedStatement.setInt(5,pOIBAForumTopic.getTopicId());
	            
            }
            i = preparedStatement.executeUpdate();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error("save - " + sqle.getMessage());
            connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
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
    
    public boolean deleteTopic(OIBAForumTopic pOIBAForumTopic,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.DELETE_TOPIC);
            preparedStatement.setInt(1,pOIBAForumTopic.getTopicId());
            flag = preparedStatement.execute();
            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
            if (preparedStatement!=null)
                preparedStatement.close();
        }

        return flag;
    }

    public ArrayList readThread(OIBAForumTopic pOIBAForumTopic,Connection connection) throws Exception
    {
        ArrayList arOIBAThread = new ArrayList();
        OIBAThread aOIBAThread = null;
        //int i=0;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.READ_ARRAY_THREAD);
            preparedStatement.setInt(1,pOIBAForumTopic.getTopicId());
            rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                aOIBAThread = new OIBAThread();
                aOIBAThread.setStrThreadId(rs.getString("threadId"));
                arOIBAThread.add(aOIBAThread);
            }

            //preparedStatement.close();
        }catch(SQLException sqle)
        {
            logger.error(sqle.getMessage());
            connection.rollback();
            throw sqle;
        }
        finally
        {
            //freeConnection();
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }
        if (arOIBAThread.size()==0)
            arOIBAThread=null;
        return arOIBAThread;
    }

    public int checkTopic(int pTopicId,Connection connection) throws Exception
    {
        int count=0;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.CHECK_TOPIC);
            preparedStatement.setInt(1,pTopicId);
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                count = rs.getInt("count");
            }
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
        	if (rs!=null)
                rs.close();
            if (preparedStatement!=null)
                preparedStatement.close();
            
        }

        return count;
    }
}
