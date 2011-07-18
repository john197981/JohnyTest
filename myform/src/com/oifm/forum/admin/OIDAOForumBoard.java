package com.oifm.forum.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.forum.OIForumConstants;
import com.oifm.forum.OIForumSqls;

public class OIDAOForumBoard extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOForumBoard.class.getName());
    
    public boolean save(OIBAForumBoard pOIBAForumBoard,Connection connection) throws Exception
    {
        boolean flag = false;
        int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            //getConnection();
            if (pOIBAForumBoard.getBoardId()==0)
            {
	            preparedStatement = connection.prepareStatement(OIForumSqls.SAVE_BOARD);
	            preparedStatement.setInt(1,pOIBAForumBoard.getCategoryId());
	            preparedStatement.setInt(2,pOIBAForumBoard.getDivisionId());
	            preparedStatement.setString(3,pOIBAForumBoard.getBoardName());
	            preparedStatement.setString(4,pOIBAForumBoard.getCreatedBy());
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.UPDATE_BOARD);
	            preparedStatement.setInt(1,pOIBAForumBoard.getCategoryId());
	            preparedStatement.setInt(2,pOIBAForumBoard.getDivisionId());
	            preparedStatement.setString(3,pOIBAForumBoard.getBoardName());
	            preparedStatement.setInt(4,pOIBAForumBoard.getBoardId());
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
    
    public boolean checkDuplicateBoard(String pBoardName,int pBoardId,int pCategoryId,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            if (pBoardId==0)
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.FORUM_CHECK_DUPLICATE_BOARD);
                preparedStatement.setInt(1,pCategoryId);
                preparedStatement.setString(2,pBoardName);
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.FORUM_CHECK_DUPLICATE_BOARD1);
                preparedStatement.setInt(1,pCategoryId);
                preparedStatement.setString(2,pBoardName);                
                preparedStatement.setInt(3,pBoardId);
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
            logger.error("checkDuplicateBoard- " + sqle.getMessage());
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

    public OIBAForumBoard read(int pBoardId, Connection connection) throws Exception
    {
        OIBAForumBoard aOIBAForumBoard=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_BOARD);
            preparedStatement.setInt(1,pBoardId);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                aOIBAForumBoard = new OIBAForumBoard();
                aOIBAForumBoard.setCategoryId(rs.getInt("CATEGORYID"));
                aOIBAForumBoard.setBoardId(rs.getInt("BOARDID"));
                aOIBAForumBoard.setDivisionId(rs.getInt("DIVISIONID"));
                aOIBAForumBoard.setBoardName(rs.getString("NAME"));
                aOIBAForumBoard.setCreatedBy(rs.getString("CREATED_BY"));
                aOIBAForumBoard.setCreatedOn(rs.getDate("CREATED_ON"));
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

        return aOIBAForumBoard;
    }

    public ArrayList readUsers(int pBoardId, Connection connection) throws Exception
    {
        ArrayList arOIBAForumMembers = new ArrayList();
        OIBAForumMembers aOIBAForumMembers = null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_BOARD_MOD_ADMIN);
            preparedStatement.setInt(1,pBoardId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                aOIBAForumMembers = new OIBAForumMembers();
                aOIBAForumMembers.setMemberId(rs.getInt("BOARDMODADMINID"));
                aOIBAForumMembers.setBoardId(rs.getInt("BOARDID"));
                aOIBAForumMembers.setUserId(rs.getString("USERID"));
                aOIBAForumMembers.setUserName(rs.getString("NAME"));
                aOIBAForumMembers.setRoleName(rs.getString("rName"));
                if (rs.getInt("FN_FLAG")>=2)
                    aOIBAForumMembers.setFnFlag("D");
                else
                    aOIBAForumMembers.setFnFlag("M");
                arOIBAForumMembers.add(aOIBAForumMembers);
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
        
        if (arOIBAForumMembers.size()==0)
            arOIBAForumMembers=null;
        return arOIBAForumMembers;
    }
    
    public int checkBoard(int pBoardId,Connection connection) throws Exception
    {
        int count=0;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.CHECK_BOARD);
            preparedStatement.setInt(1,pBoardId);
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
    
    public boolean delete(OIBAForumBoard pOIBAForumBoard,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.DELETE_BOARD);
            preparedStatement.setInt(1,pOIBAForumBoard.getBoardId());
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
    
    public void removeUser(OIBAForumBoard pOIBAForumBoard,String[] pUser,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement=null;
        //int i=0;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.REMOVE_USER);
            if (pUser!=null)
            {
	            for(int i=0;i<pUser.length;i++)
	            {
		            preparedStatement.setInt(1,pOIBAForumBoard.getBoardId());
		            preparedStatement.setString(2,pUser[i]);
		            flag = preparedStatement.execute();
	            }
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
            if (preparedStatement!=null)
                preparedStatement.close();
        }
    }
    
    public ArrayList readAll(String pUserId,int pCategoryId,Connection connection) throws Exception
    {
        ArrayList arOIBAForumBoard = new ArrayList();
        OIBAForumBoard aOIBAForumBoard=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            if (pUserId.equals(""))
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.READ_ALL_BOARD);
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.READ_ALL_BOARD_USER);
                preparedStatement.setString(2,pUserId);
            }
            preparedStatement.setInt(1,pCategoryId);
            rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                aOIBAForumBoard = new OIBAForumBoard();
                aOIBAForumBoard.setBoardId(rs.getInt("BOARDID"));
                aOIBAForumBoard.setBoardName(rs.getString(OIForumConstants.Q_NAME));
                arOIBAForumBoard.add(aOIBAForumBoard);
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
        if (arOIBAForumBoard.size()==0)
            arOIBAForumBoard=null;
        
        return arOIBAForumBoard;
    }
    
    public boolean deleteBoardAdmin(OIBAForumBoard pOIBAForumBoard,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.DELETE_BOARDADMIN);
            preparedStatement.setInt(1,pOIBAForumBoard.getBoardId());
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
}
