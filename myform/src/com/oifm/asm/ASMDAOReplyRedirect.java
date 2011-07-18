/*********************************ASMDAOReplyRedirect.java******************* 
 * Title 		: ASMDAOReplyRedirect
 * Description 	: This is the DA0 Class for ASM Admin Reply/Redirect. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 20 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.utility.DateUtility;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class ASMDAOReplyRedirect {
    private static final Logger objLogger = Logger.getLogger(ASMDAOHomePage.class);
    
    //This method is used to get All the Recent Letters
    
    public void getLetterDetails(Connection conn,ASMBAReplyRedirect objBA,int iStart,int iEnd,int iTotalRecords) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr =0;
        
        try{
        	
 
           	StringBuffer sbQry = new StringBuffer(); 
           	
           
	        sbQry.append(ASMSqls.ASM_REPLY_REDIRECT1)
	        	 .append(objBA.getHidSortBy())
	        	 .append(ASMSqls.ASM_REPLY_REDIRECT2);
	        	System.out.println("ASMDAOReplyRedirect-getLetterDetails 1" + objBA.getHidSortBy());
        	pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	pst.setInt(1,iStart);
        	pst.setInt(2,iEnd);
            objRs = pst.executeQuery();
            if(objRs!=null){
            	
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
            	objBA.setTotRecLtr(iRows);

				ASMBVReplyRedirect[] objBV = new ASMBVReplyRedirect[iRows];
				
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVReplyRedirect();
	            	objBV[iIncr].setHidLetterID(OIUtilities.replaceNull(objRs.getString("LETTERID")));
	            	objBV[iIncr].setHidTopic(OIUtilities.replaceNull(objRs.getString("TOPIC")));
	            	objBV[iIncr].setHidSubmOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("SUBMITTEDON")));
	            	objBV[iIncr].setHidSubmBy(OIUtilities.replaceNull(objRs.getString("CREATEDBY")));
	            	objBV[iIncr].setHidPublOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("PUBLISHEDON")));
	            	objBV[iIncr].setHidRedtOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("REDIRECTEDON")));
	            	objBV[iIncr].setHidReplOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("REPLIEDON")));
	            	objBV[iIncr].setHidDivInCharge(OIUtilities.replaceNull(objRs.getString("DIVISION_INCHARGE")));
	            	objBV[iIncr].setHidCategoryName(OIUtilities.replaceNull(objRs.getString("CATEGORY")));
	            	objBV[iIncr].setHidStatus(OIUtilities.replaceNull(objRs.getString("STATUS")));
	            	
	            	iIncr = iIncr+1;
	            }
	            objBA.setObjBV(objBV);
            }
        } catch(SQLException e) {
            objLogger.error("getLetterDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        
    }
    
    public int getTotalReplyRedirect(Connection conn) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        
        try{
        	
 
           	StringBuffer sbQry = new StringBuffer(); 
        	sbQry.append(ASMSqls.ASM_TOTAL_REPLY_REDIRECT); 
        	 
        	pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	iRows = objRs.getInt("total");
	        }
        } catch(SQLException e) {
            objLogger.error("getTotalReplyRedirect() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return iRows;
    }

}
