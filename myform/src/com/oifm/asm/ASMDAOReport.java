/*********************************ASMDAOReport.java******************* 
 * Title 		: ASMDAOReport
 * Description 	: This is the DA0 Class for ASM Report. 
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
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.oifm.utility.DateUtility;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class ASMDAOReport {
    private static final Logger objLogger = Logger.getLogger(ASMDAOReport.class);
    
    //This method is used to get All the Recent Letters
    
    public void searchDetails(Connection conn,ASMBAReport objBA,int iStart,int iEnd,int iTotalRecords,String strAction) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        int iIncr =0;
        
        try{
        	
        	StringBuffer sbQry = new StringBuffer();
        	
        	
        	if(strAction.equalsIgnoreCase("SearchResult")){
	        	//System.out.println("ASMDAOReport-searchDetails--bEGIN IF");
        		sbQry.append(ASMSqls.ASM_REPORT1);
	        	formatQuery(sbQry,objBA);
	        	sbQry.append("order by ")
				.append(objBA.getHidSortBy());
				sbQry.append(ASMSqls.ASM_REPORT2);
	        	//System.out.println("ASMDAOReport-searchDetails--QUERY EXE");
				pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	        	pst.setInt(1,iStart);
	        	//System.out.println("ASMDAOReport-searchDetails--iN BETWEEN QUERY");
	        	pst.setInt(2,iEnd);
	        	//System.out.println("ASMDAOReport-searchDetails--END QUERY");
        	}else{
	        	//System.out.println("ASMDAOReport-searchDetails--ELSE");
        		sbQry.append(ASMSqls.ASM_EXCEL_REPORT);
	        	formatQuery(sbQry,objBA);
	        	sbQry.append("order by ")
				.append(objBA.getHidSortBy());
	        	
	        	pst = conn.prepareStatement(sbQry.toString(),ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        	}
        	//System.out.println("SQL="+sbQry.toString());
        	
            objRs = pst.executeQuery();
            if(objRs!=null){
            	//System.out.println("ASMDAOReport-searchDetails--IF objrs = null");
            	objRs.last();
            	iRows = objRs.getRow();
            	objRs.beforeFirst();
            	
				if(objBA.getHidLink()==0){
					//objBA.setHidLink(1);
				}            	
				objBA.setTotRecLtr(iRows);
				//System.out.println("ASMDAOReport-searchDetails--set to rec");
				ASMBVReport[] objBV = new ASMBVReport[iRows];
	            while(objRs.next()) {
	            	objBV[iIncr] = new ASMBVReport();
	            	objBV[iIncr].setHidLetterID(OIUtilities.replaceNull(objRs.getString("LETTERID")));
	            	objBV[iIncr].setHidLetterTopic(OIUtilities.replaceNull(objRs.getString("TOPIC")));
	            	objBV[iIncr].setTxtSubOnFromDate(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("SUBMITTEDON")));
	            	objBV[iIncr].setTxtWrittenBy(OIUtilities.replaceNull(objRs.getString("AUTHOR_NAME")));
	            	objBV[iIncr].setTxtDesigLW(OIUtilities.replaceNull(objRs.getString("AUTHOR_DESIGNATION")));
	            	objBV[iIncr].setTxtDivisionLW(OIUtilities.replaceNull(objRs.getString("AUTHOR_DIVISION_SCHOOL_DESC")));
	            	//AUTHOR_SCHOOL_DESC
	            	objBV[iIncr].setTxtLetterContent(OIUtilities.replaceNull(OIUtilities.clobToString(objRs.getClob("LETTER"))));
	            	objBV[iIncr].setTxtYISFromDate(OIUtilities.replaceNull(objRs.getString("YIS")));
	            	objBV[iIncr].setTxtAgeFromDate(OIUtilities.replaceNull(objRs.getString("AGE")));
	            	objBV[iIncr].setCboDivInCharge(OIUtilities.replaceNull(objRs.getString("DIVISION_INCHARGE_DESCRIPTION")));
	            	//For Category List
	            	//System.out.println("ASMDAOReport-searchDetails--BEFORE CATEGORY");
	            	objBV[iIncr].setCboCategory(OIUtilities.replaceNull(objRs.getString("CATEGORY")));
	            	objBV[iIncr].setTxtRedirectTo(OIUtilities.replaceNull(objRs.getString("REDIRECTEDTO")));
	            	objBV[iIncr].setTxtRedirectFromDate(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("REDIRECTEDON")));
	            	objBV[iIncr].setTxtRepliedBy(OIUtilities.replaceNull(objRs.getString("REPLIEDBY")));
	            	objBV[iIncr].setTxtReplyFromDate(
	            			DateUtility.getMMDDYYYYStringFromDate(objRs.getDate("REPLIEDON")));
	            	objBV[iIncr].setTxtReplycontent(OIUtilities.replaceNull(OIUtilities.clobToString(objRs.getClob("REPLY"))));
	            	
	            	iIncr = iIncr+1;
	            	//System.out.println("ASMDAOReport-searchDetails--iIncr = " + iIncr);
	            }
	            objBA.setObjBV(objBV);
            }
        } catch(SQLException e) {
            objLogger.error("searchDetails() : " + e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        
    }
    
    public int getTotalDetails(Connection conn,ASMBAReport objBA) throws Exception {
        
        PreparedStatement pst = null;
        ResultSet objRs = null;
        int iRows =0;
        
        try{
        	StringBuffer sbQry= new StringBuffer();
        	sbQry.append(ASMSqls.ASM_TOTAL_REPORT);
        	
        	formatQuery(sbQry,objBA);
        	pst = conn.prepareStatement(sbQry.toString());
        	
        	
            objRs = pst.executeQuery();
            if(objRs.next())
            {
            	iRows = objRs.getInt("total");
	        }
        } catch(SQLException e) {
            objLogger.error("getTotalDetails() : " + e);
           // System.out.println("ASMDAOReport-getTotalDetails e="+e);
            throw e;
        } finally {
            OISQLUtilities.closeRsetPstatement(objRs, pst);
        }
        return iRows;
    }

    private void formatQuery(StringBuffer sbQry,ASMBAReport objBA)throws Exception{
    	boolean bEnteredFlg = false;
    	//System.out.println("ASMDAOReport-formatQuery--begin");
    	if(!OIUtilities.replaceNull(objBA.getTxtSubOnFromDate()).equals("")){
			bEnteredFlg = true;
			sbQry.append("where to_date(SUBMITTEDON,'dd/mm/yy') between to_date('"+objBA.getTxtSubOnFromDate()+"','dd/mm/yy') and to_date('"+objBA.getTxtSubOnToDate()+"','dd/mm/yy') ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtWrittenBy()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(AUTHOR_NAME) like UPPER('%"+objBA.getTxtWrittenBy()+"%') ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtDesigLW()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(AUTHOR_DESIGNATION) like UPPER('%"+objBA.getTxtDesigLW()+"%') ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtDivisionLW()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			StringTokenizer st=new StringTokenizer(objBA.getHidSchDiv(),",");
			String strData="";
			int incr=0;
			while(st.hasMoreTokens()){
				if(incr>0){
					strData=strData+",";
				}
				strData= strData+"'"+st.nextToken()+"'";
				incr=incr+1;
			}
			sbQry.append("AUTHOR_DIVISION_SCHOOL_CODE IN ("+strData+") ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtLetterContent()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(LETTER) like UPPER('%"+objBA.getTxtLetterContent()+"%') ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtYISFromDate()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("YIS between '"+objBA.getTxtYISFromDate()+"' and '"+objBA.getTxtYISToDate()+"' ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtAgeFromDate()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("AGE between '"+objBA.getTxtAgeFromDate()+"' and '"+objBA.getTxtAgeToDate()+"' ");
		}
		if(!OIUtilities.replaceNull(objBA.getCboDivInCharge()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			StringTokenizer st=new StringTokenizer(objBA.getHidDivIncharge(),",");
			String strData="";
			int incr=0;
			while(st.hasMoreTokens()){
				if(incr>0){
					strData=strData+",";
				}
				strData= strData+"'"+st.nextToken()+"'";
				incr=incr+1;
			}
			
			sbQry.append("DIVISION_INCHARGE_CODE 	IN ("+strData+") ");
		}
		// Added by Rakesh @$ 'For Category List'
		//System.out.println("ASMDAOReport-formatQuery b4 category");
		if(!OIUtilities.replaceNull(objBA.getCboCategory()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			StringTokenizer st=new StringTokenizer(objBA.getHidCategory(),",");
			String strData="";
			int incr=0;
			while(st.hasMoreTokens()){
				if(incr>0){
					strData=strData+",";
				}
				strData= strData+"'"+st.nextToken()+"'";
				incr=incr+1;
			}
			
			sbQry.append("CATEGORY_ID 	IN ("+strData+") ");
			//System.out.println("ASMDAOReport-formatQuery after");
		}
		
		
		if(!OIUtilities.replaceNull(objBA.getTxtRedirectTo()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(REDIRECTEDTO) like UPPER('%"+objBA.getTxtRedirectTo()+"%') ");
		}
		if(!OIUtilities.replaceNull(objBA.getHidLetterTopic()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(TOPIC) like UPPER('%"+objBA.getHidLetterTopic()+"%') ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtRedirectFromDate()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("TO_DATE(REDIRECTEDON,'dd/mm/yy') between to_date('"+objBA.getTxtRedirectFromDate()+"','dd/mm/yy') and to_date('"+objBA.getTxtRedirectToDate()+"','dd/mm/yy') ");
		}
		if(!OIUtilities.replaceNull(objBA.getTxtRepliedBy()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(REPLIEDBY) like UPPER('%"+objBA.getTxtRepliedBy()+"%') ");
		}
		//System.out.println("ASMDAOReport-formatQuery after2");
		//Added by Rakesh. $ for Replied on.
		if(!OIUtilities.replaceNull(objBA.getTxtReplyFromDate()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("TO_DATE(REPLIEDON,'dd/mm/yy') between to_date('"+objBA.getTxtReplyFromDate()+"','dd/mm/yy') and to_date('"+objBA.getTxtReplyToDate()+"','dd/mm/yy') ");
		}
		
		if(!OIUtilities.replaceNull(objBA.getTxtReplycontent()).equals("")){
			if(bEnteredFlg){
				sbQry.append(" and ");
			}else{
				sbQry.append(" where ");
				bEnteredFlg = true;
			}
			sbQry.append("UPPER(REPLY) like UPPER('%"+objBA.getTxtReplycontent()+"%') ");
		}
		//System.out.println("ASMDAOReport-formatQuery after3" + sbQry.toString());
	    	
    }
}
