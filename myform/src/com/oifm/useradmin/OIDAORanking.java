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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;
import java.util.Date;
import com.oifm.utility.DateUtility;


public class OIDAORanking extends OIBaseDao {
	Logger logger = Logger.getLogger(OIDAORanking.class);
	
	public OIDAORanking() {}
	
	public ArrayList getPostModuleRanking(Connection conn, int numToShow, int period) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		
		try{
            pst = conn.prepareStatement(OIRankingSqls.POST_RANKING);
            pst.setInt(1, period);
            pst.setInt(2, numToShow);
            rs = pst.executeQuery();
            
            while(rs.next()){
            	objRankingBean = new OIRankingBean();
            	objRankingBean.setStrRowNum(rs.getString("NUM"));
            	objRankingBean.setStrUserID(rs.getString("USERID"));
            	objRankingBean.setStrNickname(rs.getString("NAME"));
            	objRankingBean.setStrPostCount(rs.getString("POSTS"));
            	alResult.add(objRankingBean);
            }
        } catch(SQLException e) {
            logger.error("getPostModuleRanking() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("getPostModuleRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	
	public ArrayList getThreadModuleRanking(Connection conn, int numToShow, int period) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		
		try{
			pst = conn.prepareStatement(OIRankingSqls.THREAD_RANKING);
            pst.setInt(1, period);
            pst.setInt(2, numToShow);
            rs = pst.executeQuery();
            
            while(rs.next()){
            	objRankingBean = new OIRankingBean();
            	objRankingBean.setStrRowNum(rs.getString("NUM"));
            	objRankingBean.setStrUserID(rs.getString("USERID"));
            	objRankingBean.setStrNickname(rs.getString("NAME"));
            	objRankingBean.setStrThreadCount(rs.getString("THREADS"));
            	alResult.add(objRankingBean);
            }
        } catch(SQLException e) {
            logger.error("getThreadModuleRanking() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("getThreadModuleRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	
	public ArrayList getPaperModuleRanking(Connection conn, int numToShow, int period) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		
		try{
			pst = conn.prepareStatement(OIRankingSqls.PAPER_RANKING);
            pst.setInt(1, period);
            pst.setInt(2, numToShow);
            rs = pst.executeQuery();
            
            while(rs.next()){
            	objRankingBean = new OIRankingBean();
            	objRankingBean.setStrRowNum(rs.getString("NUM"));
            	objRankingBean.setStrUserID(rs.getString("USERID"));
            	objRankingBean.setStrNickname(rs.getString("NAME"));
            	objRankingBean.setStrPaperCount(rs.getString("PAPERS"));
            	alResult.add(objRankingBean);
            }
        } catch(SQLException e) {
            logger.error("getPaperModuleRanking() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("getPaperModuleRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	
	public ArrayList getSurveyModuleRanking(Connection conn, int numToShow, int period) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		
		try{
			pst = conn.prepareStatement(OIRankingSqls.SURVEY_RANKING);
            pst.setInt(1, period);
            pst.setInt(2, numToShow);
            rs = pst.executeQuery();
            
            while(rs.next()){
            	objRankingBean = new OIRankingBean();
            	objRankingBean.setStrRowNum(rs.getString("NUM"));
            	objRankingBean.setStrUserID(rs.getString("USERID"));
            	objRankingBean.setStrNickname(rs.getString("NAME"));
            	objRankingBean.setStrSurveyCount(rs.getString("SURVEYS"));
            	alResult.add(objRankingBean);
            }
        } catch(SQLException e) {
            logger.error("getSurveyModuleRanking() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("getSurveyModuleRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	
	public ArrayList getWebsiteRanking(Connection conn,OIBAWebRanking objBAWebRanking) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		
		try{
			
			String strQuery =  "SELECT * FROM ("
				+" SELECT NAME,Designation,Age,YrsInService,Hits,ACTIONID,School_Level,USERID FROM ( "
				+" Select B.Name NAME, C.Description Designation, ROUND(((SYSDATE-B.BIRTH_DT))/365) Age, "
				+" 	    D.DESCRIPTION School_Level, ROUND(((SYSDATE-B.JOINING_DT))/365) YrsInService, "
				+" 		Count(A.RANKID) Hits,ACTIONID,A.USERID"
				+" from OI_AD_SITERANK A,OI_AD_PROFILE B,OI_AD_CODE_MASTER C, OI_AD_CODE_MASTER D"
				+" Where A.USERID = B.USERID"
				+"   AND B.DESIGNATIONCODE = C.VALUE(+)"
				+"   AND B.SCHOOLLVLCODE = D.VALUE(+)"
				+"   AND (C.TYPE = 'DESIGNATION_CODE' OR C.TYPE IS NULL)"
				+"   AND (D.TYPE = 'SCHOOL_LEVEL' OR D.TYPE IS NULL)";
			
			if(objBAWebRanking.getFromDate()!=null && 
					objBAWebRanking.getFromDate().trim().length() > 0){
				//strQuery = strQuery+"   AND ACTIONTIME >= ? ";
				// changed by vasuki  - to get result when given date in search criteria
				strQuery = strQuery+" AND to_date(to_char(ACTIONTIME,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date(?,'yyyy-mm-dd') ";
			}
			if(objBAWebRanking.getToDate()!=null && 
					objBAWebRanking.getToDate().trim().length() > 0){
				//strQuery = strQuery+"   AND ACTIONTIME <= ? ";
				//changed by vasuki  - to get result when given date in search criteria
				strQuery = strQuery+" AND to_date(to_char(ACTIONTIME,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date(?,'yyyy-mm-dd') ";
			}
			
			strQuery = strQuery+" GROUP BY B.Name, C.Description , ROUND(((SYSDATE-B.BIRTH_DT))/365), ";
			strQuery = strQuery+" 	    D.DESCRIPTION, ROUND(((SYSDATE-B.JOINING_DT))/365),ACTIONID,A.USERID)";
			
			int iFlag = 0;
			
			if(objBAWebRanking.getTypeList()!=null && 
					objBAWebRanking.getTypeList().trim().length() > 0){
				strQuery = strQuery+" WHERE ACTIONID = ? ";
				iFlag =1;
				
			}
			strQuery = strQuery +" ORDER BY Hits DESC )";
			
			if(objBAWebRanking.getTitle()!=null && 
					objBAWebRanking.getTitle().trim().length() > 0){
				/*if(iFlag ==1){
					strQuery = strQuery+" AND  ROWNUM <=? ";
				}else{
					strQuery = strQuery+" WHERE  ROWNUM <=? ";
				}*/				
				strQuery = strQuery+" WHERE  ROWNUM <=? ";
			}		

			logger.info(" strQuery "+strQuery);
			pst = conn.prepareStatement(strQuery);
			
			int iIndex = 1;
			
			if(objBAWebRanking.getFromDate()!=null && 
					objBAWebRanking.getFromDate().trim().length() > 0){
	            String strFromDate = objBAWebRanking.getFromDate();
	            Date dtFromDate = DateUtility.getDateFromMMDDYYYYString(strFromDate);
	           // pst.setDate(iIndex,new java.sql.Date(dtFromDate.getTime()));
	            String strFrmDate =(new java.sql.Date(dtFromDate.getTime())).toString();
	            pst.setString(iIndex,strFrmDate);
	            iIndex++;
			}
			if(objBAWebRanking.getToDate()!=null && 
					objBAWebRanking.getToDate().trim().length() > 0){
	            String strToDate = objBAWebRanking.getToDate();
	            Date dtToDate = DateUtility.getDateFromMMDDYYYYString(strToDate);
	           // pst.setDate(iIndex,new java.sql.Date(dtToDate.getTime()));
	            String strDate =(new java.sql.Date(dtToDate.getTime())).toString();
	            pst.setString(iIndex,strDate);
	            iIndex++;
			}
			
			if(objBAWebRanking.getTypeList()!=null && 
					objBAWebRanking.getTypeList().trim().length() > 0){
				pst.setString(iIndex, OIUtilities.replaceNull(objBAWebRanking.getTypeList()));
				iIndex++;
			}
			if(objBAWebRanking.getTitle()!=null && 
					objBAWebRanking.getTitle().trim().length() > 0){
				int iNum = Integer.parseInt(objBAWebRanking.getTitle());
				pst.setInt(iIndex,iNum);
	            iIndex++;
	           
				
			}
            
			
            rs = pst.executeQuery();
            
            while(rs.next()){
            	objRankingBean = new OIRankingBean();            	            	
            	objRankingBean.setStrName(rs.getString("NAME"));
            	objRankingBean.setStrDesignation(rs.getString("DESIGNATION"));
            	objRankingBean.setStrAge(rs.getString("AGE"));
            	objRankingBean.setStrSchoolLevel(rs.getString("SCHOOL_LEVEL"));
            	objRankingBean.setStrYIS(rs.getString("YrsInService"));
            	objRankingBean.setStrHits(rs.getString("HITS"));
            	objRankingBean.setStrUserID(rs.getString("USERID"));
            	objRankingBean.setStrHidTypeList(rs.getString("ACTIONID"));
            	alResult.add(objRankingBean);
            }
        } catch(SQLException e) {
            logger.error("getWebsiteRanking() : " + e);
            e.printStackTrace();
            throw e;
        } catch(Exception e) {
        	e.printStackTrace();
        	logger.error("getWebsiteRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	public ArrayList getTypeList(Connection conn) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		
		try{
			pst = conn.prepareStatement(OIRankingSqls.WEBSITE_RANKING_TYPE);
			rs = pst.executeQuery();            
            while(rs.next()){
            	ArrayList alRow = new ArrayList();
            	alRow.add(rs.getString("VALUE"));
            	alRow.add(rs.getString("DESCRIPTION"));
            	alResult.add(alRow);
            	System.out.println("alResult size "+alResult.size());
            }
		 } catch(SQLException e) {
            logger.error("getWebsiteRanking() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("getWebsiteRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
			return alResult;
	}
	public ArrayList getWebsiteDetails(Connection conn,OIBAWebRanking objBAWebRanking) throws SQLException{
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		try{
			
			String strQuery =  " SELECT USERID,itemname , ACTIONTIME,ACTIONID FROM ( "
						+" Select A.USERID,ACTIONID,ACTIONTIME ";
			
			logger.info("objBAWebRanking.getTypeList()"+objBAWebRanking.getTypeList());
			
			String strType = objBAWebRanking.getHidType();
			
			logger.info("strType"+strType);
				
				if(strType!=null &&
						(strType.equals("PM"))){
						strQuery = strQuery+",POSTING  itemname";
					}else if(strType!=null &&
							(strType.equals("PT")||
							strType.equals("VT"))){
						strQuery = strQuery+",TITLE itemname";
					}else if(strType!=null &&
							(strType.equals("SC")||
							strType.equals("VC"))){
						strQuery = strQuery+",E.DESCRIPTION itemname";
					}else if(strType!=null &&
							(strType.equals("SS")||
							strType.equals("VS"))){
						strQuery = strQuery +",E.DESCRIPTION itemname";
					}else if(strType!=null &&
							(strType.equals("VL")||
							strType.equals("SL"))){
						strQuery = strQuery +",TOPIC itemname";
					}
					
				strQuery =  strQuery +" from OI_AD_SITERANK A,OI_AD_PROFILE B,OI_AD_CODE_MASTER C, " 
				+"OI_AD_CODE_MASTER D";
				
				if(strType!=null &&
					(strType.equals("PM"))){
					strQuery = strQuery +",OI_FM_POSTS E ";
				}else if(strType!=null &&
						(strType.equals("PT")||
						strType.equals("VT"))){
					strQuery = strQuery +",OI_FM_THREAD E ";
				}else if(strType!=null &&
						(strType.equals("SC")||
						strType.equals("VC"))){
					strQuery = strQuery +",OI_CP_PAPER E ";
				}else if(strType!=null &&
						(strType.equals("SS")||
						strType.equals("VS"))){
					strQuery = strQuery +",OI_SU_SURVEY E ";
				}else if(strType!=null &&
						(strType.equals("VL")||
						strType.equals("SL"))){
					strQuery = strQuery +",OI_AM_LETTERS E ";
				}
				
				strQuery = strQuery +" Where A.USERID = B.USERID"
				+"   AND B.DESIGNATIONCODE = C.VALUE(+)"
				+"   AND B.SCHOOLLVLCODE = D.VALUE(+)"
				+"   AND (C.TYPE = 'DESIGNATION_CODE' OR C.TYPE IS NULL)"
				+"   AND (D.TYPE = 'SCHOOL_LEVEL' OR D.TYPE IS NULL)";
				
				if(strType!=null &&
					(strType.equals("PM"))){
					strQuery = strQuery +" and a.itemid = e.postid  ";
				}else if(strType!=null &&
						(strType.equals("PT")||
						strType.equals("VT"))){
					strQuery = strQuery +" and a.itemid = e.threadid  ";
				}else if(strType!=null &&
						(strType.equals("SC")||
						strType.equals("VC"))){
					strQuery = strQuery +" and a.itemid = e.PAPERID  ";
				}else if(strType!=null &&
						(strType.equals("SS")||
						strType.equals("VS"))){
					strQuery = strQuery +" and a.itemid = e.SURVEYID  ";
				}else if(strType!=null &&
						(strType.equals("VL")||
						strType.equals("SL"))){
					strQuery = strQuery +" and a.itemid = e.LETTERID  ";
				}
			
			if(objBAWebRanking.getFromDate()!=null && 
					objBAWebRanking.getFromDate().trim().length() > 0){
				//strQuery = strQuery+"   AND ACTIONTIME >= ? ";
				strQuery = strQuery+" AND to_date(to_char(ACTIONTIME,'yyyy-mm-dd'),'yyyy-mm-dd') >= to_date(?,'yyyy-mm-dd') ";
			}
			if(objBAWebRanking.getToDate()!=null && 
					objBAWebRanking.getToDate().trim().length() > 0){
				//strQuery = strQuery+"   AND ACTIONTIME <= ? ";
				strQuery = strQuery+" AND to_date(to_char(ACTIONTIME,'yyyy-mm-dd'),'yyyy-mm-dd') <= to_date(?,'yyyy-mm-dd') ";
			}
			
			strQuery = strQuery+" GROUP BY B.Name, C.Description , ROUND(((SYSDATE-B.BIRTH_DT))/365),";
			
			if(strType!=null &&
				(strType.equals("PM"))){
				strQuery = strQuery+"POSTING, ";
			}else if(strType!=null &&
					(strType.equals("PT")||
					strType.equals("VT"))){
				strQuery = strQuery+"TITLE, ";
			}else if(strType!=null &&
					(strType.equals("SC")||
					strType.equals("VC"))){
				strQuery = strQuery+"E.DESCRIPTION, ";
			}else if(strType!=null &&
					(strType.equals("SS")||
					strType.equals("VS"))){
				strQuery = strQuery +"E.DESCRIPTION,";
			}else if(strType!=null &&
					(strType.equals("VL")||
					strType.equals("SL"))){
				strQuery = strQuery +"E.TOPIC, ";
			}
			strQuery = strQuery+" D.DESCRIPTION, ROUND(((SYSDATE-B.JOINING_DT))/365),ACTIONID,ACTIONTIME,A.USERID)";
			
			int iFlag = 0;
			
			if(strType!=null && 
					strType.trim().length() > 0){
				strQuery = strQuery+" WHERE ACTIONID = ? ";
				iFlag =1;
				
			}
			if(objBAWebRanking.getHidUserId()!=null && 
					objBAWebRanking.getHidUserId().trim().length() > 0){
				if(iFlag ==1){
					strQuery = strQuery+" AND  USERID =? ";
				}else{
					strQuery = strQuery+" WHERE  USERID =? ";
				}
			}
			
			logger.info(" strType "+strType);
			
			logger.info(" objBAWebRanking.getTitle() "+objBAWebRanking.getTitle());
			
			logger.info(" objBAWebRanking.getHidUserId "+objBAWebRanking.getHidUserId());
			
			logger.info(" strQuery "+strQuery);
			
			pst = conn.prepareStatement(strQuery);
			
			int iIndex = 1;
			
			if(objBAWebRanking.getFromDate()!=null && 
					objBAWebRanking.getFromDate().trim().length() > 0){
	            String strFromDate = objBAWebRanking.getFromDate();
	            Date dtFromDate = DateUtility.getDateFromMMDDYYYYString(strFromDate);
	            //pst.setDate(iIndex,new java.sql.Date(dtFromDate.getTime()));
	            String strFrmdate = (new java.sql.Date(dtFromDate.getTime())).toString();
	            System.out.println("from date in web rank details "+strFrmdate);
	            pst.setString(iIndex,strFrmdate);
	            iIndex++;
			}
			if(objBAWebRanking.getToDate()!=null && 
					objBAWebRanking.getToDate().trim().length() > 0){
	            String strToDate = objBAWebRanking.getToDate();
	            Date dtToDate = DateUtility.getDateFromMMDDYYYYString(strToDate);
	           // pst.setDate(iIndex,new java.sql.Date(dtToDate.getTime()));
	            String strDate = (new java.sql.Date(dtToDate.getTime())).toString();
	            System.out.println("strDate in web ranking details "+strDate);
	            pst.setString(iIndex,strDate);
	            iIndex++;
			}
			
			if(strType!=null && 
					strType.trim().length() > 0){
				pst.setString(iIndex, OIUtilities.replaceNull(strType));
				iIndex++;
			}			
			if(objBAWebRanking.getHidUserId()!=null && 
					objBAWebRanking.getHidUserId().trim().length() > 0){				
				pst.setString(iIndex,objBAWebRanking.getHidUserId());
	            iIndex++;
			}
            rs = pst.executeQuery();
            
            logger.info("objBAWebRanking.getHidUserId()"+objBAWebRanking.getHidUserId());
            
            while(rs.next()){
            	OIBARankingDetails objDetails = new OIBARankingDetails();            	            	
            	objDetails.setActionName(rs.getString("ITEMNAME"));
            	objDetails.setActionTime(DateUtility.getMMDDYYYYStringFromDate(rs.getDate("ACTIONTIME")));
            	alResult.add(objDetails);
            }
        } catch(SQLException e) {
            logger.error("getWebsiteRanking() : " + e);
            e.printStackTrace();
            throw e;
        } catch(Exception e) {
        	e.printStackTrace();
        	logger.error("getWebsiteRanking() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
		
		return alResult;
	}
	public void clearLog(Connection conn) throws SQLException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		String strQuery = "DELETE  FROM OI_AD_SITERANK";
		try{
            pst = conn.prepareStatement(strQuery);
            rs = pst.executeQuery();
            
        } catch(SQLException e) {
            logger.error("clearLog() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("clearLog() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
	}
	public double getCount(Connection conn) throws SQLException{
		
		PreparedStatement pst = null;
		ResultSet rs = null;
		ArrayList alResult = new ArrayList();
		OIRankingBean objRankingBean = null;
		String strQuery = "SELECT COUNT(1) totalCount FROM OI_AD_SITERANK";
		double dCount = 0;
		try{
            pst = conn.prepareStatement(strQuery);
            rs = pst.executeQuery();
            if(rs.next()){
            	dCount = rs.getDouble("totalCount");
            }
            logger.info(" dCount "+dCount);
        } catch(SQLException e) {
            logger.error("clearLog() : " + e);
            throw e;
        } catch(Exception e) {
        	logger.error("clearLog() : " + e);
        } finally {
            OISQLUtilities.closeRsetPstatement(rs, pst);
        }
        return dCount;
	}
}
