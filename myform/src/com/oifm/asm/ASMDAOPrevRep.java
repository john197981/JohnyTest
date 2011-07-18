/*********************************ASMDAOPrevRep.java******************* 
 * Title 		: ASMDAOPrevRep
 * Description 	: This is the DA0 Class for ASM Previous Replies. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 19 - Dec -2005
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

public class ASMDAOPrevRep {
    private static final Logger objLogger = Logger.getLogger(ASMDAOHomePage.class);
    
    //This method is used to get All the Recent Letters
    
    public void getRecentLetters(Connection conn,ASMBAPrevRep objBA,int iStart,int iEnd,int iTotalRecords) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr =0;
        
        try{
        	
 
           	StringBuffer sbQry = new StringBuffer(); 
           	if(OIUtilities.replaceNull(objBA.getHiddenAction()).equals("populateNew")){
	        	sbQry.append(ASMSqls.ASM_PREVIOUS_REP1);
           	}else{
           		sbQry.append(ASMSqls.ASM_PREVIOUS_REP2);
           	}
        	
        	pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	pst.setInt(1,iStart);
        	pst.setInt(2,iEnd);
        	
            objRs = pst.executeQuery();
            if(objRs!=null){
            	
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
            	
				if(objBA.getHidLink()==0){
					//objBA.setHidLink(1);
				}            	
				objBA.setTotRecLtr(iRows);

				ASMBVPrevRep[] objBV = new ASMBVPrevRep[iRows];
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVPrevRep();
	            	objBV[iIncr].setHidLetterID(OIUtilities.replaceNull(objRs.getString("LETTERID")));
	            	objBV[iIncr].setHidLetterTopic(OIUtilities.replaceNull(objRs.getString("TOPIC")));
	            	objBV[iIncr].setHidSubmittedOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("SUBMITTEDON")));
	            	objBV[iIncr].setHidSubmittedBy(OIUtilities.replaceNull(objRs.getString("CREATEDBY")));
	            	objBV[iIncr].setHidPublishOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("PUBLISHEDON")));
	            	
	            	iIncr = iIncr+1;
	            }
	            objBA.setObjBV(objBV);
            }
        } catch(SQLException e) {
            objLogger.error("getRecentLetters() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        
    }
    
    public int getTotalPrevRep(Connection conn,String strHiddenAction) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        
        try{
        	
        	StringBuffer sbQry = new StringBuffer(); 
        	//sbQry.append("Select count(*) total from oi_am_letters where status='P'");
           	if(OIUtilities.replaceNull(strHiddenAction).equals("populateNew")){
	        	sbQry.append(ASMSqls.ASM_TOTAL_PREVIOUS_REP1);
           	}else{
           		sbQry.append(ASMSqls.ASM_TOTAL_PREVIOUS_REP2);
           	}
			 
        	pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	iRows = objRs.getInt("total");
	        }
        } catch(SQLException e) {
            objLogger.error("getRecentLetters() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return iRows;
    }

}
