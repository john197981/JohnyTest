package com.oifm.utility;

import org.apache.log4j.Logger;

import java.util.*;
import java.sql.*;

public class  OISQLUtilities	
{
	private static Logger logger = Logger.getLogger(OISQLUtilities.class);

	//	To execute single simple insert/update/Delete statement.
	public static int execute(Connection conn, String query, String method, String className) throws SQLException 
	{
		Statement stmt = null;
		int count = 0;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query: "+query);
		try
		{
			stmt = conn.createStatement();
			count = stmt.executeUpdate(query);
			if (count==0)
			{
			    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"-->Save Failed");
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			throw se;
		} 
		finally 
		{
			closeStatement(stmt);
		}
		return count;
	}

//	To execute insert/update/Delete statements for several records.
	public static int executeMultiple(Connection conn, String query, ArrayList alMultipleValues, String method, String className) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ArrayList alValues = null;
		int count = 0;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query: "+query+" \n  alMultipleValues : "+ alMultipleValues);
		try
		{
			pstmt = conn.prepareStatement(query);
			if(alMultipleValues != null && alMultipleValues.size() > 0) 
			{ 
				for(int i=0; i<alMultipleValues.size(); i++) 
				{
					alValues = (ArrayList) alMultipleValues.get(i);
					for(int j=0; j<alValues.size(); j++)
					{
						pstmt.setString(j+1, (String)alValues.get(j));
					}
					count += pstmt.executeUpdate();
				}
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+alMultipleValues+"\n"+se);
			throw se;
		} 
		finally 
		{
			closePstatement(pstmt);
		}
		return count;
	}

	//	To execute insert/update/Delete statements for single record.
	public static int executeSingle(Connection conn, String query, ArrayList alValues, String method, String className) throws SQLException 
	{
		PreparedStatement pstmt = null;
		int count = 0;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query: "+query+" \n  alValues : "+ alValues);
		try
		{
			pstmt = conn.prepareStatement(query);
			for(int j=0; j<alValues.size(); j++)
			{
				pstmt.setString(j+1, (String)alValues.get(j));
			}
			count = pstmt.executeUpdate();
			if (count==0)
			{
			    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"-->Save/Delete Failed");
			    logger.error(alValues.toString());
			}
		}
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			throw se;
		}
		finally 
		{
			closePstatement(pstmt);
		}
		return count;
	}

//	To get single string by executing simple query.
	public static String getString(Connection con, String query, String method, String className) throws SQLException 
	{
		Statement stmt = null;
		ResultSet rs = null;
		String retVal = null;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query: "+query);
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) 
			{
				retVal = rs.getString(1);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			retVal = null;
			throw se;
		} 
		finally 
		{
			closeRsetStatement(rs, stmt);
		}
		return retVal;
	}

//	To get single string by executing query.
	public static String getString(Connection con, String query, ArrayList alValues, String method, String className) throws SQLException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String retVal = null;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query : "+query+" alValues : "+alValues);
		try
		{
			pstmt = con.prepareStatement(query);
			if(alValues != null && alValues.size() > 0) 
			{ 
				for(int j=0; j<alValues.size(); j++)
				{
					pstmt.setString(j+1, (String)alValues.get(j));
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				retVal = rs.getString(1);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			retVal = null;
			throw se;
		} 
		finally 
		{
			closeRsetPstatement(rs, pstmt);
		}
		return retVal;
	}

	//	To get single number like count, qty, etc  by executing simple query.
	public static int getNumber(Connection con, String query, String method, String className) throws SQLException, NumberFormatException 
	{
		Statement stmt = null;
		ResultSet rs = null;
		int retCount = 0;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query: "+query);
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) 
			{
				retCount = rs.getInt(1);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			retCount = 0;
			throw se;
		} 
		finally 
		{
			closeRsetStatement(rs, stmt);
		}

		return retCount;
	}

	//	To get single number like count, qty, etc  by executing query.
	public static int getNumber(Connection con, String query, ArrayList alValues, String method, String className) throws SQLException, NumberFormatException 
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int retCount = 0;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query : "+query+" alValues : "+alValues);
		try
		{
			pstmt = con.prepareStatement(query);
			if(alValues != null && alValues.size() > 0) 
			{ 
				for(int j=0; j<alValues.size(); j++)
				{
					pstmt.setString(j+1, (String)alValues.get(j));
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next()) 
			{
				retCount = rs.getInt(1);
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			retCount = 0;
			throw se;
		} 
		finally 
		{
			closeRsetPstatement(rs, pstmt);
		}
		return retCount;
	}

	//	To check the true or false by executing simple query.
	public static boolean checkExists(Connection con, String query, String method, String className) throws SQLException,RuntimeException 
	{
		boolean isExists = false;
		Statement stmt = null;
		ResultSet rs = null;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query: "+query);
		try
		{
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);
			if (rs.next()) isExists = true;
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			isExists = false;
			throw se;
		} 
		finally 
		{
			closeRsetStatement(rs, stmt);
		}
		return isExists;
	}

	//	To check the true or false by executing simple query.
	public static boolean checkExists(Connection con, String query, ArrayList alValues, String method, String className) throws SQLException,RuntimeException 
	{
		boolean isExists = false;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		logger.debug("Ref Class : "+className+" Method : "+method+" Query : "+query+" alValues : "+alValues);
		try
		{
			pstmt = con.prepareStatement(query);
			if(alValues != null && alValues.size() > 0) 
			{ 
				for(int j=0; j<alValues.size(); j++)
				{
					pstmt.setString(j+1, (String)alValues.get(j));
				}
			}
			rs = pstmt.executeQuery();
			if (rs.next())
			{
			    isExists = true;
			}
		} 
		catch(SQLException se) 
		{
		    logger.error("Ref Class : "+className+" Method : "+method+" Query: "+query+"\n"+se);
			isExists = false;
			throw se;
		} 
		finally 
		{
			closeRsetPstatement(rs, pstmt);
		}
		return isExists;
	}

	public static void closePstatement(PreparedStatement pstmt)  throws SQLException 
	{
		closeRsetPstatement(null, pstmt);
	}

	public static void closeRsetPstatement(ResultSet rs, PreparedStatement pstmt)  throws SQLException 
	{
		try
		{
			if(rs != null)	
			{
				rs.close();
				rs = null;
			}
			if(pstmt != null)	
			{
				pstmt.close();
				pstmt = null;
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" closeRsetPstatement() : "+se);
			throw se;
		}
	}

	public static void  closeStatement(Statement stmt) throws SQLException 
	{
		closeRsetStatement(null, stmt);
	}

	public static void closeRsetStatement(ResultSet rs, Statement stmt) throws SQLException 
	{
		try
		{
			if(rs != null)	
			{
				rs.close();
				rs = null;
			}
			if(stmt != null)	
			{
				stmt.close();
				stmt = null;
			}
		} 
		catch(SQLException se) 
		{
		    logger.error(" closeRsetStatement() : "+se);
			throw se;
		}
	}
}