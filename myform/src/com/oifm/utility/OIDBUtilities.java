package com.oifm.utility;
/*
 * Class Name:
 * Description:
 * 
 * 	Created By			Created/Modified on			Revision				Remarks
 * -----------------------------------------------------------------------------------------------------
 * 	Sukumar			16/06/2005					Draft					Inital Version
 * 
 * -----------------------------------------------------------------------------------------------------
 */

import javax.naming.*;
import java.sql.*;
import javax.sql.*;
import org.apache.log4j.Logger;

public class OIDBUtilities	
{
	private static DataSource ds = null;
	private static Logger logger = Logger.getLogger(OIDBUtilities.class);
	private static OIDBUtilities instance = null;

	static 
	{
		Context ctx = null;
		try
		{
			logger.info("Before InitialContext");
			ctx = new InitialContext();
			//For MOE Deployment
			logger.info("Before SetValue");
			OIDBRegistry.setValues();
			ds = (DataSource)  ctx.lookup(OIDBRegistry.dataSourceName);
			logger.info("Retrieving DataSource - " + OIDBRegistry.dataSourceName);

			//For Scandent Deployment
			//ds = (DataSource)  ctx.lookup(OIDBRegistry.getValues("dataSourceName"));
			//logger.debug("DataSource	:	"+ds);
		} catch(Exception  ne)
		{ 
			logger.error(ne.getMessage());
		} finally 
		{
			try	
			{
				if (ctx != null) ctx.close();
			} catch(NamingException  ne)
			{ 
				logger.error(ne.getMessage());
			}
		}
	}

	public static Connection getConnection() throws SQLException 
	{
		Connection conn = null;
		try	
		{
			//logger.info("Before getting Connection");
			if(ds != null) 
			{
				
				if(conn==null){
					//conn = ds.getConnection();
					//logger.info("getting Connection");
					conn = ds.getConnection(OIDBRegistry.dbUserName,OIDBRegistry.dbPwd);
				}	
			} else logger.info("ds = null");

			if(conn != null)
				conn.setAutoCommit(true);
			else 
				new SQLException("System not able to establish Connection with database");
			//logger.debug("Connection	:	"+conn);
		} catch(SQLException se)
		{ 
			logger.error(se.getMessage());
			throw se;
		} 
		return conn;
	}

	public static void freeConnection(Connection conn){
		try{
			//logger.info("Before closing Connection");
			if(conn != null) { 
			    conn.close();
			}  
		}catch(SQLException se){ 
			logger.error(se.getMessage());
		} 
	}
}

 