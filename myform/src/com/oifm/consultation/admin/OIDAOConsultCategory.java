package com.oifm.consultation.admin;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Rajkumar			30/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.consultation.OIConsultConstant;
import com.oifm.consultation.OIConsultationSqls;
import com.oifm.utility.OISQLUtilities;

public class OIDAOConsultCategory extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConsultCategory.class.getName());
    /**
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value of the primary keys to the prepare statement, which will delete that record from the database
     * 4. execute the query 
     */
    public boolean delete(OIBAConsultCategory pOIBAConsultCategory,Connection connection) throws Exception
    {
        boolean flag=false;
        //int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            preparedStatement=connection.prepareStatement(OIConsultationSqls.DELETE_CATEGORY);
            preparedStatement.setInt(1,pOIBAConsultCategory.getCategoryID());
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

    /**
     * 1. gets connection from the base class
     * 2. create preparedStatement
     * 3. sets value to the prepare statement
     * 4. save it to the database 
     */
    public boolean save(OIBAConsultCategory pOIBAConsultCategory, Connection connection) throws SQLException, Exception
    {
        boolean flag=false;
        int i=0;
        PreparedStatement preparedStatement=null;
        try
        {
            //getConnection();
            logger.info(pOIBAConsultCategory.getCategoryID() + "");
            if (pOIBAConsultCategory.getCategoryID()==0)
            {
	            preparedStatement = connection.prepareStatement(OIConsultationSqls.SAVE_CATEGORY);
	            preparedStatement.setString(1,pOIBAConsultCategory.getName());
	            preparedStatement.setString(2,pOIBAConsultCategory.getCreatedBy());
            }
            else
            {
                preparedStatement = connection.prepareStatement(OIConsultationSqls.UPDATE_CATEGORY);
	            preparedStatement.setString(1,pOIBAConsultCategory.getName());
	            preparedStatement.setInt(2,pOIBAConsultCategory.getCategoryID());
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
    
    /**
     * This method reads the category of the passes CategoryID
     * 
     * It returns OIBAConsultCategory 
     */
    public OIBAConsultCategory read(int pcategoryID, Connection connection) throws Exception
    {
        OIBAConsultCategory aOIBAConsultCategory=null;
        ResultSet rs=null;
        PreparedStatement preparedStatement=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIConsultationSqls.READ_CATEGORY);
            preparedStatement.setInt(1,pcategoryID);
            rs = preparedStatement.executeQuery();
            
            if (rs.next())
            {
                aOIBAConsultCategory = new OIBAConsultCategory();
                aOIBAConsultCategory.setCategoryID(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBAConsultCategory.setName(rs.getString(OIConsultConstant.Q_NAME));
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
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return aOIBAConsultCategory;
    }
    /**
     * This method should return all the categories present in the table.
     * 
     * It returns an ArrayList of OIBAConsultCategory object 
     */
    public ArrayList readAll(Connection connection) throws Exception
    {
        ArrayList arOIBAConsultCategory = new ArrayList();
        OIBAConsultCategory aOIBAConsultCategory=null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIConsultationSqls.READ_ALL_CATEGORY);
            rs = preparedStatement.executeQuery();
            
            while (rs.next())
            {
                aOIBAConsultCategory = new OIBAConsultCategory();
                aOIBAConsultCategory.setCategoryID(rs.getInt(OIConsultConstant.Q_CATEGORYID));
                aOIBAConsultCategory.setName(rs.getString(OIConsultConstant.Q_NAME));
                arOIBAConsultCategory.add(aOIBAConsultCategory);
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
        	OISQLUtilities.closeRsetPstatement(rs, preparedStatement);
//            if (preparedStatement!=null)
//                preparedStatement.close();
//            if (rs!=null)
//                rs.close();
        }
        if (arOIBAConsultCategory.size()==0)
            arOIBAConsultCategory=null;
        
        return arOIBAConsultCategory;
    }
    
    public int checkCategory(int pcategoryID, Connection connection) throws Exception
    {
        int count=0;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            //getConnection();
            preparedStatement = connection.prepareStatement(OIConsultationSqls.CHECK_CATEGORY);
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
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return count;
    }
    
    public boolean checkDuplicateCategory(String pCategoryName,int pCategoryId,Connection connection) throws Exception
    {
        boolean flag=false;
        PreparedStatement preparedStatement = null;
        try
        {
            //getConnection();
            if (pCategoryId==0)
            {
	            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_CHECK_DUPLICATE_CATEGORY);
	            preparedStatement.setString(1,pCategoryName);
            }
            else
            {
	            preparedStatement = connection.prepareStatement(OIConsultationSqls.CONSULT_CHECK_DUPLICATE_CATEGORY1);
	            preparedStatement.setString(1,pCategoryName);
	            preparedStatement.setInt(2,pCategoryId);
            }
            ResultSet rs = preparedStatement.executeQuery();
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
            if (preparedStatement!=null)
                preparedStatement.close();
        }
        return flag;
    }
}
