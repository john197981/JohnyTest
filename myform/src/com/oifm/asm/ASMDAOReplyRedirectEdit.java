/*********************************ASMDAOReplyRedirectEdit.java******************* 
 * Title 		: ASMDAOReplyRedirectEdit
 * Description 	: This is the DA0 Class for ASM Admin Reply/Redirect Edit Page. 
 * Author 		: Anbalagan Varadharajan 
 * Version No 	: 1.0 
 * Date Created : 21 - Dec -2005
 * Copyright 	: Scandent Group
 ******************************************************************************/

package com.oifm.asm;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import oracle.sql.CLOB;

import org.apache.log4j.Logger;

import com.oifm.common.OIComboVO;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class ASMDAOReplyRedirectEdit {
    private static final Logger objLogger = Logger.getLogger(ASMDAOReplyRedirectEdit.class);
    
    //This method is used to get All the Recent Letters
    
    public void getLetterDetails(Connection conn,ASMBVReplyRedirectEdit objBV) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        objLogger.debug("getLetterDetails() ");
        try{
            StringBuffer sbQry =new StringBuffer();
            System.out.println("get hid id :::::" + objBV.getHidLetterID());
            System.out.println("get letter id:::::" + objBV.getLetterId());
            System.out.println("get from Category:::::" + objBV.getFromCategory());
            if(null != objBV.getFromCategory() && objBV.getFromCategory().equals("Y")){
            	System.out.println("inside if >>>>>>>>"+objBV.getLetterId());
            	sbQry.append(ASMSqls.ASM_REPLY_REDIRECT_EDIT)
               	.append(objBV.getLetterId());
            }else{
            	System.out.println("inside else >>>>>>>>"+objBV.getHidLetterID());
            	sbQry.append(ASMSqls.ASM_REPLY_REDIRECT_EDIT)
            	.append(objBV.getHidLetterID());
            }
           	System.out.println("query " + sbQry.toString());
        	pst = conn.prepareStatement(sbQry.toString());
            
            rs = pst.executeQuery();
            if (rs.next()) {
            	objBV.setHidLetterID(OIUtilities.replaceNull(rs.getString("LETTERID")));
            	objBV.setTxtTopic(OIUtilities.replaceNull(rs.getString("TOPIC")));
            	objBV.setTxtLetter(OIUtilities.replaceNull(OIUtilities.clobToString(rs.getClob("LETTER"))));
            	objBV.setHidStatus(OIUtilities.replaceNull(rs.getString("STATUS")));
            	objBV.setHidPostedOn(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("SUBMITTEDON")));
            	objBV.setStrRedirectedOn(DateUtility.getDDMMYYYYStringFromDate(rs.getDate("REDIRECTEDON")));
            	objBV.setTxtTo(OIUtilities.replaceNull(rs.getString("REDIRECTEDTO")));
            	objBV.setTxtCc(OIUtilities.replaceNull(rs.getString("REDIRECTEDCC")));
            	objBV.setTxtBcc(OIUtilities.replaceNull(rs.getString("REDIRECTEDBCC")));
            	objBV.setTxtMessage(OIUtilities.replaceNull(rs.getString("REDIRECTMESSAGE")));
            	objBV.setTxtSubject(OIUtilities.replaceNull(rs.getString("SUBJECT")));
            	//If subject is null then assign the topic in subject
            	if(objBV.getTxtSubject().equalsIgnoreCase("")){
            		objBV.setTxtSubject(objBV.getTxtTopic());
            	}
            	objBV.setTxtRepliedBy(OIUtilities.replaceNull(rs.getString("REPLIEDBY")));
            	objBV.setTxtRepliedOn(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("REPLIEDON")));
            	
            	objBV.setTxtReply(OIUtilities.replaceNull(OIUtilities.clobToString(rs.getClob("REPLY"))));
            	
            	if(OIUtilities.replaceNull(rs.getString("PUBLISHONSITE")).equalsIgnoreCase("Y")){
            		objBV.setChkPublishWebSite(true);
            	}else{
            		objBV.setChkPublishWebSite(false);
            	}
            	objBV.setTxtPublishOn(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("PUBLISHEDON")));
            	objBV.setTxtShowFrom(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("PUBLISHEDFROM")));
            	objBV.setTxtShowTo(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("PUBLISHEDTO")));
            	objBV.setStrUsrId(OIUtilities.replaceNull(rs.getString("CREATEDBY")));
            	objBV.setRadRemainder(OIUtilities.replaceNull(rs.getString("REMINDERMODE")));
            	objBV.setCboDivision(OIUtilities.replaceNull(rs.getString("DIVISION_CODE")));
            	
            	objBV.setHidPostedBy(OIUtilities.replaceNull(rs.getString("NAME")));
            	objBV.setHidEmail(OIUtilities.replaceNull(rs.getString("EMAILID")));
            	objBV.setTxtContactNo(OIUtilities.replaceNull(rs.getString("CONTACTNO")));
            	objBV.setCboCategory(OIUtilities.replaceNull(rs.getString("CATEGORYID")));
            }
            //objLogger.info("userID - " + objBV.getHidUserID());
            pst = conn.prepareStatement(ASMSqls.ASM_USER_DETAIL);
            pst.setString(1, objBV.getStrUsrId());
            rs = pst.executeQuery();
            if (rs.next()) {
            	//objLogger.info("inside RS");
            	objBV.setStrUsrDesig(OIUtilities.replaceNull(rs.getString("DESIGNATION")));
            	objBV.setStrUsrDivSch(OIUtilities.replaceNull(rs.getString("AUTHOR_DIVISION_SCHOOL_DESC")));
            }
            
        } catch(SQLException e) {
        	objLogger.error("getLetterDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }
    
    public ArrayList getDivisionDetails(Connection conn) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        objLogger.debug("getDivisionDetails() ");
        try{
            StringBuffer sbQry =new StringBuffer();
            sbQry.append(ASMSqls.ASM_DIVISION);
           	
        	pst = conn.prepareStatement(sbQry.toString());
            
            rs = pst.executeQuery();
            ArrayList alDivisionList= new ArrayList();
            while (rs.next()) {
				alDivisionList.add(new OIComboVO((String) OIUtilities.replaceNull(rs.getString("value")),
						(String)OIUtilities.replaceNull(rs.getString("description"))));
            }
            return alDivisionList;
            
        } catch(SQLException e) {
        	objLogger.error("getDivisionDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }
    
    //Edited by Rakesh for CategoryList
    //This method returns Category List.
    public ArrayList getCategoryList(Connection conn) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        objLogger.debug("getCategoryList() ");
        try{
            StringBuffer sbQry =new StringBuffer();
            sbQry.append(ASMSqls.ASM_CATEGORY);
           	
        	pst = conn.prepareStatement(sbQry.toString());
            
            rs = pst.executeQuery();
            ArrayList alCategoryList= new ArrayList();
            while (rs.next()) {
            	alCategoryList.add(new OIComboVO((String) OIUtilities.replaceNull(rs.getString("CATEGORYID")),
						(String)OIUtilities.replaceNull(rs.getString("NAME"))));
            }
            return alCategoryList;
            
        } catch(SQLException e) {
        	objLogger.error("getCategoryList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }
    
    public void saveLetterDetails(Connection conn,ASMBVReplyRedirectEdit objBV) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        CLOB clob1 = null;
        CLOB clob2 = null;
        objLogger.debug("saveLetterDetails() ");
        try{
            String strPublishWebSiteFlag ="N";
        	if(objBV.getChkPublishWebSite()){
        		strPublishWebSiteFlag ="Y";
            }
        	String strPublishOn=OIUtilities.replaceNull(objBV.getTxtPublishOn());
        	
        	StringBuffer sbQry =new StringBuffer();
            sbQry.append("UPDATE OI_AM_LETTERS SET ")
			.append("DIVISION_CODE ='").append(objBV.getCboDivision()).append("', ")
			.append("CATEGORYID ='").append(objBV.getCboCategory()).append("', ")
			.append("REDIRECTEDTO ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtTo())).append("', ")
			.append("REDIRECTEDCC ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtCc())).append("', ")
			.append("REDIRECTEDBCC ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtBcc())).append("', ")
			.append("SUBJECT ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtSubject())).append("', ")
			.append("REDIRECTMESSAGE =?, ")
			.append("CONTACTNO ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtContactNo())).append("', ")
			.append("TOPIC ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtTopic())).append("', ")
			//.append("LETTER ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtLetter())).append("', ")
			.append("LETTER = EMPTY_CLOB(), ")
			//.append("REPLY ='").append(OIUtilities.addSingleQuoteDB(objBV.getTxtReply())).append("', ")
			.append("REPLY = EMPTY_CLOB(), ")
			.append("REMINDERMODE ='").append(objBV.getRadRemainder()).append("', ")
			.append("PUBLISHONSITE ='").append(strPublishWebSiteFlag).append("', ")
			.append("PUBLISHEDON ='").append(objBV.getTxtPublishOn()).append("', ")
			.append("PUBLISHEDFROM ='").append(objBV.getTxtShowFrom()).append("', ")
			.append("PUBLISHEDTO ='").append(objBV.getTxtShowTo()).append("', ")
            .append("REPLIEDBY ='").append(objBV.getTxtRepliedBy()).append("', ")
			.append("REPLIEDON ='").append(objBV.getTxtRepliedOn()).append("' ");
            
            if(OIUtilities.replaceNull(objBV.getHiddenAction()).equalsIgnoreCase("Redirect")){
            	sbQry.append(",STATUS ='T'");
            	sbQry.append(",REDIRECTEDON =TO_DATE(SYSDATE,'dd/mm/yyy')");
            	sbQry.append(",REDIRECTEDBY ='");
            	sbQry.append(objBV.getHidUserID());
            	sbQry.append("' ");
            }else if(OIUtilities.replaceNull(objBV.getHiddenAction()).equalsIgnoreCase("Save")){
            	if(objBV.getChkPublishWebSite()){
            		//System.out.println("ASMDAOReplyRedirectEdit-saveLetterDetails 1 publish web site");
            		sbQry.append(",STATUS ='P' ");
            		
            	}
            	else if(!(objBV.getTxtRepliedOn()).equals("")){
            	//	System.out.println("ASMDAOReplyRedirectEdit-saveLetterDetails 2 replied on is not null" );
            		sbQry.append(",STATUS ='R' ");
            	}
            }else {
            //	System.out.println("ASMDAOReplyRedirectEdit-saveLetterDetails  3 replied is null");
            	sbQry.append(",STATUS ='S' ");
            }
            
            sbQry.append("WHERE LETTERID=")
            .append(objBV.getHidLetterID());
            pst = conn.prepareStatement(sbQry.toString());
            pst.setString(1, objBV.getTxtMessage());
            //pst.setString(2, objBV.getTxtLetter());
			//pst.setString(3, objBV.getTxtReply());
            pst.executeUpdate();
            
            // WRITING CLOB
            objLogger.info("before CLOB");
				conn.setAutoCommit(false);
				pst=conn.prepareStatement(ASMSqls.ASM_REPLY_REDIRECT_EDIT + objBV.getHidLetterID() + " FOR UPDATE");
				//pst.setString(1,aASMBALetters.getLetterId());
        
				rs = pst.executeQuery();
			
				if (rs.next())  {
					clob1 = (CLOB) rs.getClob("LETTER");
					clob2 = (CLOB) rs.getClob("REPLY");
				}
				if (clob1 != null) OIUtilities.writeClob(clob1, objBV.getTxtLetter());
				if (clob2 != null) OIUtilities.writeClob(clob2, objBV.getTxtReply());
			
				conn.commit();
				//conn.setAutoCommit(true);
            
        } catch(SQLException e) {
        	objLogger.error("saveLetterDetails() : " + e);
            throw e;
        } finally {
        	conn.setAutoCommit(true);
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    	
    }
    //
    public void deleteLetter(Connection conn,ASMBVReplyRedirectEdit objBV) throws Exception {
		PreparedStatement pst = null;
        try {
			pst = conn.prepareStatement(ASMSqls.ASM_REPLY_REDIRECT_DELETE);
			pst.setString(1, objBV.getHidLetterID());
			pst.execute();
			// preparedStatement.close();
		} catch(SQLException e) {
        	objLogger.error("deleteLetterDetails() : " + e);
            throw e;
        } finally {
        	conn.setAutoCommit(true);
            OISQLUtilities.closePstatement(pst);
        }

	}
    
    
    
    public int getReminderDays (Connection conn) throws SQLException{
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	int ret = 0;
    	
    	try {
    		pst = conn.prepareStatement(ASMSqls.ASM_REMINDER_DAYS);
    		rs = pst.executeQuery();
    		
    		if (rs.next()) ret = rs.getInt("DESCRIPTION");
    		
    	} catch (SQLException e) {
    		objLogger.error("getReminderDays() - SQLException - " + e);
    		throw e;
    	} finally {
    		OISQLUtilities.closeRsetPstatement(rs, pst);
    	}
    	
    	return ret;
    }
}
