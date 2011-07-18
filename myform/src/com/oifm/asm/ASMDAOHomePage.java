/*********************************ASMBVHomePage.java.java******************* 
 * Title 		: ASMBVHomePage
 * Description 	: This class is the VO Class for ASM Home Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 14 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class ASMDAOHomePage {
    private static final Logger logger = Logger.getLogger(ASMDAOHomePage.class);
    
    public void getAnnouncement(Connection conn,ASMBVHomePage objBV) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            pst = conn.prepareStatement(ASMSqls.ASM_ANNOUNCEMENT);
            rs = pst.executeQuery();
            if (rs.next()) {
            	objBV.setHidAnnouncemnet(OIUtilities.replaceNull(
            			OIUtilities.clobToString(rs.getClob("CONTENT"))));
            }
        } catch(SQLException e) {
            logger.error("getAnnouncement() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }

    public void getLetterReplyDetails(Connection conn,ASMBVHomePage objBV) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
            StringBuffer sbQry =new StringBuffer();
            
            if(OIUtilities.replaceNull(objBV.getHidLetterID()).equals("")){
            	sbQry.append(ASMSqls.ASM_LETTER_REPLY1);
            }else{
            	sbQry.append(ASMSqls.ASM_LETTER_REPLY2)
				.append(objBV.getHidLetterID());
            }
        	pst = conn.prepareStatement(sbQry.toString());
            
            rs = pst.executeQuery();
            if (rs.next()) {
            	objBV.setHidLetterID(OIUtilities.replaceNull(rs.getString("LETTERID")));
            	objBV.setHidLetterTopic(OIUtilities.replaceNull(rs.getString("TOPIC")));
            	objBV.setHidLetterContent(OIUtilities.replaceNull(
            			OIUtilities.clobToString(rs.getClob("LETTER"))));
            	objBV.setHidReplyContent(OIUtilities.replaceNull(
            			OIUtilities.clobToString(rs.getClob("REPLY"))));
            	objBV.setHidPublishOn(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("PUBLISHEDON")));
            	objBV.setHidCreatedBy(OIUtilities.replaceNull(rs.getString("CREATENAME")));
            	objBV.setHidRepliedBy(OIUtilities.replaceNull(rs.getString("REPLYNAME")));
            	
            	// Website rank - View Letters
            	if(!OIUtilities.replaceNull(objBV.getHidLetterID()).equals("")){
            		try{
            			if(objBV.getUserId()!= null){        				
            				OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
            		        aOIBAWebSiteRanking.setActionId("VL");
            		        aOIBAWebSiteRanking.setUserId(objBV.getUserId());
            		        aOIBAWebSiteRanking.setItemId(Integer.parseInt(objBV.getHidLetterID()));
            		        new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,conn);
            			}
            		} 
            		catch(Exception e)
            	    {
            	        String error = e.getMessage();
            	        logger.error(e);
            	        try
            	        {
            	        	conn.rollback();
            	        }catch (Exception ex){}
            	    }
            	}
            	// End website rank
           }
        } catch(SQLException e) {
            logger.error("getLetterReplyDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }

}
