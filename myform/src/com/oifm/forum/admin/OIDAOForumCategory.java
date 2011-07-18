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
import com.oifm.utility.OISQLUtilities;

public class OIDAOForumCategory extends OIBaseDao
{
    Logger logger = Logger.getLogger(OIDAOForumCategory.class.getName());
    
    public boolean checkDuplicateCategory(String pCategoryName,int pCategoryId,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            if (pCategoryId==0)
            {
	            preparedStatement = connection.prepareStatement(OIForumSqls.FORUM_CHECK_DUPLICATE_CATEGORY);
	            preparedStatement.setString(1,pCategoryName);
            }
            else
            {
	            preparedStatement = connection.prepareStatement(OIForumSqls.FORUM_CHECK_DUPLICATE_CATEGORY1);
	            preparedStatement.setString(1,pCategoryName);
	            preparedStatement.setInt(2,pCategoryId);
            }
            rs = preparedStatement.executeQuery();
            if (rs.next())
            {
                if (rs.getInt("count")==0)
                	flag=true;
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
        	OISQLUtilities.closeRsetPstatement(rs,preparedStatement);
            
        }
        return flag;
    }

    public boolean save(OIBAForumCategory pOIBAForumCategory, Connection connection) throws SQLException, Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            //getConnection();
            if (pOIBAForumCategory.getCategoryID()==0)
            {
	            preparedStatement = connection.prepareStatement(OIForumSqls.SAVE_CATEGORY);
	            preparedStatement.setString(1,pOIBAForumCategory.getName());
	            preparedStatement.setString(2,pOIBAForumCategory.getCreatedBy());
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIForumSqls.UPDATE_CATEGORY);
	            preparedStatement.setString(1,pOIBAForumCategory.getName());
	            preparedStatement.setInt(2,pOIBAForumCategory.getCategoryID());
            }
            i = preparedStatement.executeUpdate();
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

    public OIBAForumCategory read(int pcategoryID, Connection connection) throws Exception
    {
        OIBAForumCategory aOIBAForumCategory=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_CATEGORY);
            preparedStatement.setInt(1,pcategoryID);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                aOIBAForumCategory = new OIBAForumCategory();
                aOIBAForumCategory.setCategoryID(rs.getInt(OIForumConstants.Q_CATEGORYID));
                aOIBAForumCategory.setName(rs.getString(OIForumConstants.Q_NAME));
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

        return aOIBAForumCategory;
    }
    public boolean delete(OIBAForumCategory pOIBAForumCategory,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIForumSqls.DELETE_CATEGORY);
            preparedStatement.setInt(1,pOIBAForumCategory.getCategoryID());
            flag = preparedStatement.execute();
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
        }

        return flag;
    }

    public int checkCategory(int pcategoryID, Connection connection) throws Exception
    {
        int count=0;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.CHECK_CATEGORY);
            preparedStatement.setInt(1,pcategoryID);
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
        	OISQLUtilities.closeRsetPstatement(rs,preparedStatement);
        }

        return count;
    }

    public ArrayList readAll(Connection connection) throws Exception
    {
        ArrayList arOIBAForumCategory = new ArrayList();
        OIBAForumCategory aOIBAForumCategory=null;
        PreparedStatement preparedStatement=null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIForumSqls.READ_ALL_CATEGORY);
            rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                aOIBAForumCategory = new OIBAForumCategory();
                aOIBAForumCategory.setCategoryID(rs.getInt(OIForumConstants.Q_CATEGORYID));
                aOIBAForumCategory.setName(rs.getString(OIForumConstants.Q_NAME));
                arOIBAForumCategory.add(aOIBAForumCategory);
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
        	OISQLUtilities.closeRsetPstatement(rs,preparedStatement);
        }
        if (arOIBAForumCategory.size()==0)
            arOIBAForumCategory=null;
        
        return arOIBAForumCategory;
    }

}
