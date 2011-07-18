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

public class OIDAOForumMoveThread extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOForumMoveThread.class.getName());
    
    public ArrayList readTopic(int boardId,Connection connection) throws Exception
    {
        ArrayList arOIBATopic = new ArrayList();
        OIBAForumTopic aOIBATopic = null;

        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.READ_TopicList);
            preparedStatement.setInt(1,boardId);
            rs = preparedStatement.executeQuery();
            while (rs.next())
            {
            	aOIBATopic = new OIBAForumTopic();
            	aOIBATopic.setTopicId(rs.getInt("TOPICID"));
            	aOIBATopic.setTopicName(rs.getString("TOPICNAME"));
            	arOIBATopic.add(aOIBATopic);
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
        if (arOIBATopic.size()==0)
        	arOIBATopic=null;
        return arOIBATopic;
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

    public boolean saveThread(OIBAForumMoveThread pOIBAMoveThread,Connection connection) throws Exception
    {
        boolean flag = false;
        int i=0;
        PreparedStatement preparedStatement = null;
        try
        {
        	String[] arThreadId = pOIBAMoveThread.getThreadId();
        	
        	if(arThreadId!=null && arThreadId.length>0){
        		if(arThreadId[0].equalsIgnoreCase("All")){
        			preparedStatement = connection.prepareStatement(OIForumSqls.MOVE_ALL_THREAD);
        			
                	preparedStatement.setString(1,pOIBAMoveThread.getDesTopicId());
                	preparedStatement.setString(2,pOIBAMoveThread.getTopicId());
                	i = preparedStatement.executeUpdate();
        		}else{
        			preparedStatement = connection.prepareStatement(OIForumSqls.MOVE_THREAD);
        			
        			for(int k=0;k<arThreadId.length;k++){
                    	preparedStatement.setString(1,pOIBAMoveThread.getDesTopicId());
                    	preparedStatement.setString(2,arThreadId[k]);
                    	i += preparedStatement.executeUpdate();
                    }
        		}
        	}
            
        }catch(SQLException sqle)
        {
            logger.error("update - " + sqle.getMessage());
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
            logger.error("Update Failed");
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

    public ArrayList readThread(int threadId,Connection connection) throws Exception
    {
        ArrayList arOIBAThread = new ArrayList();
        OIBAThread aOIBAThread = null;
        //int i=0;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.READ_THREADLIST);
            preparedStatement.setInt(1,threadId);
            rs = preparedStatement.executeQuery();
            while (rs.next())
            {
                aOIBAThread = new OIBAThread();
                aOIBAThread.setStrThreadId(rs.getString("threadId"));
                aOIBAThread.setStrTitle(rs.getString("TITLE"));
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
