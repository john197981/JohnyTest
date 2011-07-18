/*
 * File name	= OIDAORanking.java
 * Package		= com.oifm.useradmin
 * Created on 	= Aug 16, 2005
 * Created by	= Oscar
 * Copyright	= Scandent Group
 *
 */

package com.oifm.useradmin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;


public class OIDAOSummaryDetails extends OIBaseDao {
	Logger logger = Logger.getLogger(OIDAOSummaryDetails.class);
	
	public OIDAOSummaryDetails() {}
	
	public ArrayList getSummDts(Connection conn, String strQuery) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
        int iRows =0;
        int iIncr =0;
		
		try{
            pst = conn.prepareStatement(strQuery,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			
            rs = pst.executeQuery();
            if(rs!=null){
	            rs.last();
	        	iRows = rs.getRow();
	        	rs.beforeFirst();
	        	OIRankingBean[] objRankingBean = new OIRankingBean[iRows];
	            
	            while(rs.next()){
	            	objRankingBean[iIncr] = new OIRankingBean();
	            	objRankingBean[iIncr].setStrName(rs.getString(1));
	            	objRankingBean[iIncr].setStrPostCount(rs.getString(2));
	            	objRankingBean[iIncr].setStrTotalCount(rs.getString(3));
	            	
	            	iIncr = iIncr+1;
	            }
	            alResult.add(objRankingBean);
            }
        } catch(SQLException e) {
        	System.out.println("dao-------------error"+e);
            logger.error("getSummDts() : " + e);
            throw e;
        } catch(Exception e) {
        	System.out.println("dao-------------error"+e);
        	logger.error("getSummDts() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	
	
}
