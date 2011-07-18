package com.oifm.base;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.common.OIResponseObject;
import com.oifm.login.OILoginConstants;
import com.oifm.utility.OIDBUtilities;

public class OIBaseBo extends OIBaseHandler
{
    public OIResponseObject responseObject=new OIResponseObject();
    public Connection connection;
    private static Logger logger = Logger.getLogger(OIBaseBo.class);    
    
    public OIBaseBo()
    {
        //responseObject=new OIResponseObject();
    }
    public void getConnection() throws SQLException
    {
        try
        {
            connection = OIDBUtilities.getConnection();
            if (connection==null)
            {
                System.out.println("OIBaseBo-getConnection is null");
                throw new SQLException("Connection is null");
            }
        }
        catch(SQLException se)
        {
			logger.error(se.getMessage());
			throw se;
        }
        //return connection;
    }
    
    public void freeConnection()
    {
        OIDBUtilities.freeConnection(connection);
    }

    public void addToResponseObject()
    {
        responseObject.addResponseObject(OILoginConstants.K_ERROR,getError());
        responseObject.addResponseObject(OILoginConstants.K_MESSAGE,getMessageList());
        responseObject.addResponseObject(OILoginConstants.K_CATEGORY,getCategoryList());
        
    }
}
