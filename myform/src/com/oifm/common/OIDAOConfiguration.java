package com.oifm.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;

public class OIDAOConfiguration extends OIBaseDao 
{
    Logger logger = Logger.getLogger(OIDAOConfiguration.class.getName());
    public ArrayList read(Connection connection) throws Exception
    {
        ArrayList arOIBAConfiguration = new ArrayList();
        OIBAConfiguration aOIBAConfiguration = null;
        PreparedStatement preparedStatement = null;
        ResultSet  rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OICommonSqls.CONFIGURATION_READ_ALL);
            rs = preparedStatement.executeQuery();
            while(rs.next())
            {
                aOIBAConfiguration = new OIBAConfiguration();
                aOIBAConfiguration.setConfigId(rs.getInt("CONFIGID"));
                aOIBAConfiguration.setCaption(rs.getString("CAPTION"));
                aOIBAConfiguration.setTag(rs.getString("TAG"));
                aOIBAConfiguration.setValue(rs.getString("VALUE"));
                aOIBAConfiguration.setUnit(rs.getString("UNIT"));
                arOIBAConfiguration.add(aOIBAConfiguration);
            }
        }
        catch(Exception e)
        {
            logger.error("readConfiguartion_ALL -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        if (arOIBAConfiguration.size()==0)
            arOIBAConfiguration=null;
        return arOIBAConfiguration;
    }

    public boolean save(ArrayList arOIBAConfiguration,Connection connection) throws Exception
    {
        boolean flag=false;
        OIBAConfiguration aOIBAConfiguration = null;
        PreparedStatement preparedStatement = null;
        try
        {
            preparedStatement=connection.prepareStatement(OICommonSqls.CONFIGURATION_SAVE);
            for (int i=0;i<arOIBAConfiguration.size();i++)
            {
                aOIBAConfiguration = (OIBAConfiguration) arOIBAConfiguration.get(i);
	            preparedStatement.setString(1,aOIBAConfiguration.getValue());
	            preparedStatement.setString(2,aOIBAConfiguration.getTag());
	            int k = preparedStatement.executeUpdate();
	            if (k==0)
	            {
	                logger.error("Save Failed" + i + aOIBAConfiguration.getTag());
	                throw new Exception("Save Failed");
	            }
	            else
	            {
	                flag = true;
	            }
            }
        }
        catch(Exception e)
        {
            logger.error("readAllFeedBack -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
        }

        return flag;
    }
    
    public OIBAConfiguration readDt(String pTag,String pTagFrom,String pTagTo,Connection connection) throws Exception
    {
        OIBAConfiguration aOIBAConfiguration = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs=null;
        try
        {
            preparedStatement=connection.prepareStatement(OICommonSqls.CONFIGURATION_READ_DT);
            preparedStatement.setString(1,pTag);
            preparedStatement.setString(2,pTagFrom);
            preparedStatement.setString(3,pTagTo);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                aOIBAConfiguration = new OIBAConfiguration();
                aOIBAConfiguration.setConfigId(rs.getInt("CONFIGID"));
                aOIBAConfiguration.setCaption(rs.getString("CAPTION"));
                aOIBAConfiguration.setTag(rs.getString("TAG"));
                aOIBAConfiguration.setValue(rs.getString("VALUE"));
                aOIBAConfiguration.setUnit(rs.getString("UNIT"));
            }
        }
        catch(Exception e)
        {
            logger.error("readConfiguartion -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return aOIBAConfiguration;
    }

    public OIBAConfiguration read(String pTag,Connection connection) throws Exception
    {
        OIBAConfiguration aOIBAConfiguration = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try
        {
            preparedStatement=connection.prepareStatement(OICommonSqls.CONFIGURATION_READ);
            preparedStatement.setString(1,pTag);
            rs = preparedStatement.executeQuery();
            if(rs.next())
            {
                aOIBAConfiguration = new OIBAConfiguration();
                aOIBAConfiguration.setConfigId(rs.getInt("CONFIGID"));
                aOIBAConfiguration.setCaption(rs.getString("CAPTION"));
                aOIBAConfiguration.setTag(rs.getString("TAG"));
                aOIBAConfiguration.setValue(rs.getString("VALUE"));
                aOIBAConfiguration.setUnit(rs.getString("UNIT"));
            }
        }
        catch(Exception e)
        {
            logger.error("readConfiguartion -" + e.getMessage());
            throw e;
        }
        finally
        {
            if (preparedStatement!=null)
                preparedStatement.close();
            if (rs!=null)
                rs.close();
        }

        return aOIBAConfiguration;
    }

}
