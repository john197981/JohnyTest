/*********************************ASMDAOCommon.java******************* 
 * Title 		: ASMDAOCommon
 * Description 	: This class is the VO Class for ASM Right Side portal Page. 
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
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.common.OIComboVO;
import com.oifm.utility.DateUtility;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class ASMDAOCommon {
    private static final Logger objLogger = Logger.getLogger(ASMDAOCommon.class);
    
    //This method is used to get the Details of Editor's Note
    //14th March 2011, Added new parameter to the getEditorNote method for addressing the Editor note issue.
    public void getEditorsNote(Connection conn,ASMBACommon objBA,String contentId) throws Exception {
       // System.out.println("iam coming here...");
        PreparedStatement pst = null;
        ResultSet rs = null;
        try{
        	if(contentId==null){
        		pst = conn.prepareStatement(ASMSqls.ASM_EDITORS_NOTE);
        	}else{
        		pst = conn.prepareStatement(ASMSqls.ASM_EDITORS_NOTE_WITH_ID);
        		pst.setString(1,contentId);
        	}
            rs = pst.executeQuery();
            if (rs.next()) {
            	objBA.setHidEditorsNoteID(OIUtilities.replaceNull(rs.getString("CONTENTID")));
//				commented by Deva
            	//objBA.setHidEditorsNote(OIUtilities.truncateString(OIUtilities.clobToString(rs.getClob("CONTENT"))));
//            	Added by deva
				objBA.setHidEditorsNote(OIUtilities.truncateString(OIUtilities.removeHtmlTags(OIUtilities.clobToString(rs.getClob("CONTENT")))));

            	
            	
            	//System.out.println("content=="+OIUtilities.clobToString(rs.getClob("CONTENT")));
				//System.out.println("truncate=="+OIUtilities.truncateString(OIUtilities.clobToString(rs.getClob("CONTENT"))));
            	
            	objBA.setHidEditorsNotePostedOn(
            			DateUtility.getMMDDYYYYStringFromDate(rs.getDate("EDITON")));
            }
        } catch(SQLException e) {
        	objLogger.error("getEditorsNote() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }
 
    //This method is to get the Recent Letter Topic and Published on details
    public void getRecentLetters(Connection conn,ASMBACommon objBA) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr=0;
        
        try{
        	
        	pst = conn.prepareStatement(ASMSqls.ASM_RECENT_LETTERS,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	
            objRs = pst.executeQuery();
            
            if(objRs!=null){
            	
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
            	
            	ASMBVCommon[] objBV = new ASMBVCommon[iRows];
            	
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVCommon();
	            	objBV[iIncr].setHidRecLtrID(OIUtilities.replaceNull(objRs.getString("LETTERID")));
	            	objBV[iIncr].setHidRecLtrTopic(OIUtilities.replaceNull(objRs.getString("TOPIC")));
	            	objBV[iIncr].setHidRecLtrPubOn(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("PUBLISHEDON")));
	            			
	            	
	            	iIncr = iIncr+1;
	            }
	            objBA.setObjBV(objBV);
	            objBA.setTotRecLtr(iRows);
            }
        } catch(SQLException e) {
            objLogger.error("getRecentLetters() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
    }
    
    //This method is to get the Topic with the Letter ID
    public String getTopic(Connection conn,String strLetterID) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr=0;
        String strTopic="";
        try{
        	StringBuffer sbQry=new StringBuffer();
        	sbQry.append(ASMSqls.ASM_TOPIC);
        	sbQry.append(strLetterID);
        	
        	pst = conn.prepareStatement(sbQry.toString());
        	
            objRs = pst.executeQuery();
            
            if(objRs.next()) {
            	strTopic= OIUtilities.replaceNull(objRs.getString("topic"));
            }
        } catch(SQLException e) {
            objLogger.error("getTopic() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return strTopic;
    }

    //This method is used to get the Pagination Size
    public int getPaginationSize(Connection conn,String strModule) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int nRows =0;
        
        try{
        	StringBuffer sbQry = new StringBuffer(); 
        	sbQry.append(ASMSqls.ASM_PAGINATION);
        	sbQry.append(strModule);
        	sbQry.append("'");
        	pst = conn.prepareStatement(sbQry.toString());
        	
            objRs = pst.executeQuery();
            
            if(objRs.next()) {
            	nRows = objRs.getInt("DESCRIPTION");
            }
        } catch(SQLException e) {
            objLogger.error("getPaginationSize() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return nRows;
    }
//  This method gets all the user details with respect to the selected division list    
    public void getUserDetails(Connection conn,ASMBACommon objBA,int iStart,int iEnd,int iTotalRecords) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr =0;
        
        try{
 
           	StringBuffer sbQry = new StringBuffer(); 
	        	
	        sbQry.append(ASMSqls.ASM_USER_DETAILS1)
	        .append(objBA.getCboDivision())
			.append(ASMSqls.ASM_USER_DETAILS2);
        	pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	pst.setInt(1,iStart);
        	pst.setInt(2,iEnd);
            objRs = pst.executeQuery();
            if(objRs!=null){
            	
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
    			
            	objBA.setTotRecLtr(iRows);

				ASMBVCommon[] objBV = new ASMBVCommon[iRows];
				
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVCommon();
	            	objBV[iIncr].setHidName(OIUtilities.replaceNull(objRs.getString("NAME")));
	            	objBV[iIncr].setHidEmailID(OIUtilities.replaceNull(objRs.getString("EMAILID")));
	            	
	            	iIncr = iIncr+1;
	            }
	            objBA.setObjBV(objBV);
            }
        } catch(SQLException e) {
            objLogger.error("getUserDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        
    }
    //This method get total user mail list with respect to the selected division list
    public int getTotalUserMail(Connection conn,String strDivisionCode) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        
        try{
           	StringBuffer sbQry = new StringBuffer(); 
        	sbQry.append(ASMSqls.ASM_TOTAL_USER_DETAILS);
        	sbQry.append(strDivisionCode);
        	sbQry.append("'");
			
        	pst = conn.prepareStatement(sbQry.toString());
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	iRows = objRs.getInt("total");
	        }
        } catch(SQLException e) {
            objLogger.error("getTotalUserMail() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return iRows;
    }

    public ArrayList getDivisionDetails(Connection conn) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        objLogger.debug("getDivisionDetails() ");
        try{
            
        	pst = conn.prepareStatement(ASMSqls.ASM_DIVISION);
            
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
// This method is used for Category list.
    public ArrayList getCategoryList(Connection conn) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        objLogger.debug("getCategoryList() ");
        try{
            
        	pst = conn.prepareStatement(ASMSqls.ASM_CATEGORY);
            
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
    //This method is used to get the Pagination Size
    public String getMessage(Connection conn,String strInput) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        String strRtnMsg ="";
        
        try{
        	StringBuffer sbQry = new StringBuffer(); 
        	sbQry.append(ASMSqls.ASM_DESCRIPTION);
        	sbQry.append(strInput);
        	sbQry.append("'");
        	
        	pst = conn.prepareStatement(sbQry.toString());
        	
            objRs = pst.executeQuery();
            
            if(objRs.next()) {
            	strRtnMsg = objRs.getString("DESCRIPTION");
            }
        } catch(SQLException e) {
            objLogger.error("getPaginationSize() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return strRtnMsg;
    }
//This method gets all the user details
    public void getUserList(Connection conn,ASMBACommon objBA,int iStart,int iEnd,int iTotalRecords) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr =0;
        
        try{
 
        	pst = conn.prepareStatement(ASMSqls.ASM_USER_LIST,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	pst.setInt(1,iStart);
        	pst.setInt(2,iEnd);
        	
            objRs = pst.executeQuery();
            if(objRs!=null){
            	
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
    			
            	objBA.setTotRecLtr(iRows);

				ASMBVCommon[] objBV = new ASMBVCommon[iRows];
				
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVCommon();
	            	objBV[iIncr].setHidName(OIUtilities.replaceNull(objRs.getString("NAME")));
	            	objBV[iIncr].setHidEmailID(OIUtilities.replaceNull(objRs.getString("EMAILID")));
	            	
	            	iIncr = iIncr+1;
	            }
	            objBA.setObjBV(objBV);
            }
        } catch(SQLException e) {
            objLogger.error("getUserList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        
    }
    //This method is to get the total of user list
    public int getTotalUserList(Connection conn) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        
        try{
 
        	pst = conn.prepareStatement(ASMSqls.ASM_TOTAL_USER_LIST);
        	
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	iRows = objRs.getInt("total");
	        }
        } catch(SQLException e) {
            objLogger.error("getTotalUserList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return iRows;
    }
//This method is to get the school details    
    public ArrayList getSchoolDetails(Connection conn,ArrayList alList) throws Exception {
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        objLogger.debug("getSchoolDetails() ");
        try{
            
        	pst = conn.prepareStatement(ASMSqls.ASM_SCHOOL);
            
            rs = pst.executeQuery();
            while (rs.next()) {
            	alList.add(new OIComboVO((String) OIUtilities.replaceNull(rs.getString("schoolcode")),
						(String)OIUtilities.replaceNull(rs.getString("schoolname"))));
            }
            return alList;
            
        } catch(SQLException e) {
        	objLogger.error("getSchoolDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
    }
    //This method is used to get the short description of each module
    public String getModuleDescription(Connection conn,String strModule) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        String strDesc ="";
        
        try{
        	StringBuffer strQuery=new StringBuffer();
        	strQuery.append(ASMSqls.ASM_GET_DESC)
        	.append(strModule)
			.append("'");
 
        	pst = conn.prepareStatement(strQuery.toString());
        	
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	strDesc = objRs.getString("VALUE");
	        }
        } catch(SQLException e) {
            objLogger.error("getTotalUserList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return strDesc;
    }
 
//Get Senior Management User List
    
//  This method gets all the Senior Management user details
    public void getSnrMgmtUserList(Connection conn,ASMBACommon objBA,int iStart,int iEnd,int iTotalRecords) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr =0;
        
        try{
 
        	pst = conn.prepareStatement(ASMSqls.ASM_SNR_USER_DETAIL,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	pst.setInt(1,iStart);
        	pst.setInt(2,iEnd);
        	
            objRs = pst.executeQuery();
            if(objRs!=null){
            	
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
    			
            	objBA.setTotRecLtr(iRows);

				ASMBVCommon[] objBV = new ASMBVCommon[iRows];
				
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVCommon();
	            	objBV[iIncr].setHidName(OIUtilities.replaceNull(objRs.getString("NAME")));
	            	objBV[iIncr].setHidEmailID(OIUtilities.replaceNull(objRs.getString("EMAILID")));
	            	
	            	iIncr = iIncr+1;
	            }
	            objBA.setObjBV(objBV);
            }
        } catch(SQLException e) {
            objLogger.error("getUserList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        
    }
    //This method is to get the total of Senior Management list
    public int getTotalSnrMgmtUserList(Connection conn) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        
        try{
 
        	pst = conn.prepareStatement(ASMSqls.ASM_SNR_TOTAL_USER_DETAIL);
        	
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	iRows = objRs.getInt("total");
	        }
        } catch(SQLException e) {
            objLogger.error("getTotalSnrMgmtUserList() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return iRows;
    }
    
}
