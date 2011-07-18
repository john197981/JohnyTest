
package com.oifm.survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.OIUtilities;

public class OIDAOResponseUser  {
	
	private static final Logger logger = Logger.getLogger(OIDAOResponseUser.class);

	public OIDAOResponseUser()	{	}
	
	public ArrayList getPublishedResponses(Connection con, String strSectionId, String strUserId, String strQuery) throws SQLException {
		ArrayList aryResponses = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAResponse objResponse = null;

		try {
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strUserId);
			pstmt.setString(2, strSectionId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objResponse = new OIBAResponse();
				objResponse.setStrQuestionId(rs.getString("QUESTIONID"));
				objResponse.setStrQstType(rs.getString("TYPE"));
				objResponse.setStrResponseId(rs.getString("RESPONSEID"));
				objResponse.setStrResponse1(rs.getString("RESPONSE1"));
				objResponse.setStrResponse2(rs.getString("RESPONSE2"));
				objResponse.setStrResponse3(rs.getString("RESPONSE3"));
				objResponse.setStrResponse4(rs.getString("RESPONSE4"));
				objResponse.setStrResponse5(rs.getString("RESPONSE5"));
				objResponse.setStrResponse6(rs.getString("RESPONSE6"));
				objResponse.setStrResponse7(rs.getString("RESPONSE7"));
				objResponse.setStrResponse8(rs.getString("RESPONSE8"));
				objResponse.setStrResponse9(rs.getString("RESPONSE9"));
				objResponse.setStrResponse10(rs.getString("RESPONSE10"));
				objResponse.setStrOtherRemarks(rs.getString("OTHERREMARKS"));
				aryResponses.add(objResponse);
			}
		} catch(SQLException se) {
logger.error(" getPublishedResponses() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return aryResponses;
	}

	public boolean updateSurveyUserResponses(Connection con, boolean isGDSUser, ArrayList alInsertResponses, ArrayList alUpdateResponses, ArrayList alDeleteResponseIds, String strSurveyId, String strUserId) throws SQLException, RuntimeException {
		boolean saveFlag = true;
		
		try {
			con.setAutoCommit(false);
logger.debug(" before invoke alInsertResponses  => "+alInsertResponses+" saveFlag : "+saveFlag);			
			if(saveFlag && alInsertResponses.size() > 0)
				saveFlag = createResponses(con, alInsertResponses, strUserId,((isGDSUser)?OISurveySqls.INSERT_RESPONSE:OISurveySqls.INSERT_RESPONSE_TUSER));
logger.debug(" before invoke alUpdateResponses => "+alUpdateResponses+" saveFlag : "+saveFlag);			
			if(saveFlag && alUpdateResponses.size() > 0)
				saveFlag = modifyResponses(con, alUpdateResponses, strUserId, ((isGDSUser)?OISurveySqls.UPDATE_RESPONSE:OISurveySqls.UPDATE_RESPONSE_TUSER));
logger.debug(" before invoke alDeleteResponseIds  => "+alDeleteResponseIds+" saveFlag : "+saveFlag);
			if(saveFlag && alDeleteResponseIds.size() > 0)
				saveFlag = deleteResponses(con, alDeleteResponseIds, ((isGDSUser)?OISurveySqls.DELETE_RESPONSE:OISurveySqls.DELETE_RESPONSE_TUSER));
logger.debug(" before invoke updateDraftInfo  => saveFlag : "+saveFlag);
			if(saveFlag)
				saveFlag = updateDraftInfo(con, strSurveyId, strUserId);
		} catch(SQLException se) {
			saveFlag = false;
logger.error(" updateSurveyUserResponses() => "+se);
			throw se;
		} catch(RuntimeException re)	{
			saveFlag = false;
logger.error(" updateSurveyUserResponses() => "+re);
			throw re;
		} finally {
			if(saveFlag)
				con.commit();
			else 
				con.rollback();
			con.setAutoCommit(true);
		}
		return saveFlag;
	}
	
	public boolean createResponses(Connection con, ArrayList alResponses, String strUserId, String strQuery) throws SQLException {
		boolean insertFlag = false;
		ArrayList alSurveyResponseValues = new ArrayList();
		ArrayList alResponseValues = null;
		OIBAResponse objResponse = null;
		try {
			if(alResponses != null && alResponses.size() > 0) {
				for (int i=0; i<alResponses.size(); i++) {
					objResponse = (OIBAResponse)alResponses.get(i);
					alResponseValues = new ArrayList();
					alResponseValues.add(objResponse.getStrQuestionId());
					alResponseValues.add(strUserId);
					alResponseValues.add(objResponse.getStrResponse1());
					alResponseValues.add(objResponse.getStrResponse2());
					alResponseValues.add(objResponse.getStrResponse3());
					alResponseValues.add(objResponse.getStrResponse4());
					alResponseValues.add(objResponse.getStrResponse5());
					alResponseValues.add(objResponse.getStrResponse6());
					alResponseValues.add(objResponse.getStrResponse7());
					alResponseValues.add(objResponse.getStrResponse8());
					alResponseValues.add(objResponse.getStrResponse9());
					alResponseValues.add(objResponse.getStrResponse10());
					//alResponseValues.add((objResponse.getStrResponse10() == null || objResponse.getStrResponse10().trim().length() == 0)?"":objResponse.getStrOtherRemarks());
					alResponseValues.add(objResponse.getStrOtherRemarks());
					alSurveyResponseValues.add(alResponseValues);
				}
				insertFlag = (OISQLUtilities.executeMultiple(con, strQuery, alSurveyResponseValues, "createResponses", "OIDAOResponseUser") == alSurveyResponseValues.size());
			}
		}catch(SQLException se) {
			insertFlag = false;
			throw se;
		}
		return insertFlag;
	}
	
	public boolean modifyResponses(Connection con, ArrayList alResponses, String strUserId, String strQuery) throws SQLException {
		boolean modifyFlag = false;
		ArrayList alSurveyResponseValues = new ArrayList();
		ArrayList alResponseValues = null;
		OIBAResponse objResponse = null;
		if(alResponses != null && alResponses.size() > 0) {
			for (int i=0; i<alResponses.size(); i++) {
				objResponse = (OIBAResponse)alResponses.get(i);
				alResponseValues = new ArrayList();
				alResponseValues.add(objResponse.getStrResponse1());
				alResponseValues.add(objResponse.getStrResponse2());
				alResponseValues.add(objResponse.getStrResponse3());
				alResponseValues.add(objResponse.getStrResponse4());
				alResponseValues.add(objResponse.getStrResponse5());
				alResponseValues.add(objResponse.getStrResponse6());
				alResponseValues.add(objResponse.getStrResponse7());
				alResponseValues.add(objResponse.getStrResponse8());
				alResponseValues.add(objResponse.getStrResponse9());
				alResponseValues.add(objResponse.getStrResponse10());
				//alResponseValues.add((objResponse.getStrResponse10() == null || objResponse.getStrResponse10().trim().length() == 0)?"":objResponse.getStrOtherRemarks());
				alResponseValues.add(objResponse.getStrOtherRemarks());
				alResponseValues.add(objResponse.getStrQuestionId());
				alResponseValues.add(strUserId);
				alSurveyResponseValues.add(alResponseValues);
			}
			modifyFlag = (OISQLUtilities.executeMultiple(con, strQuery, alSurveyResponseValues, "modifyResponses", "OIDAOResponseUser") == alSurveyResponseValues.size());
		}
		return modifyFlag;
	}

	public boolean deleteResponses(Connection con, ArrayList alResponseIds, String strQuery) throws SQLException {
		boolean deleteFlag = false;
		String strRespIds = " ("+ (OIUtilities.arrayToSqlString((String[])alResponseIds.toArray(new String[alResponseIds.size()]))) +")";
		deleteFlag = (OISQLUtilities.execute(con, strQuery + strRespIds, "deleteResponses", "OIDAOResponseUser") == alResponseIds.size());
		return deleteFlag;
	}
	
	public boolean updateDraftInfo(Connection con, String strSurveyId, String strUserId) throws SQLException {
		boolean insertFlag = false;
		boolean isExists = false;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null && !strSurveyId.equals("")) {
			alValues.add(strSurveyId);
			alValues.add(strUserId);
			isExists = OISQLUtilities.checkExists(con, OISurveySqls.CHECK_SURVEY_DRAFT, alValues, "updateDraftInfo", "OIDAOResponseUser");
			if(!isExists) {
				insertFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.INSERT_SURVEY_DRAFT, alValues, "updateDraftInfo", "OIDAOResponseUser") == 1);
			} else insertFlag = true;
		}
		return insertFlag;
	}

	public boolean updateSubmitionInfo(Connection con, String strSurveyId, String strUserId) throws SQLException {
		boolean modifyFlag = false;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null && !strSurveyId.equals("")) {
			alValues.add(strSurveyId);
			alValues.add(strUserId);
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SURVEY_SUBMIT, alValues, "updateSubmitionInfo", "OIDAOResponseUser") == 1);
		}
		try{
			if(strSurveyId!= null){
				String pUserId = strUserId;
				OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
		        aOIBAWebSiteRanking.setActionId("SS");
		        aOIBAWebSiteRanking.setUserId(pUserId);
		        aOIBAWebSiteRanking.setItemId(Integer.parseInt(strSurveyId));
		        new OIDAOWebSiteRanking().save(aOIBAWebSiteRanking,con);
			}
		} 
		catch(Exception e)
	    {
	        String error = e.getMessage();
	        logger.error(e);
	        try
	        {
	        	con.rollback();
	        }catch (Exception ex){}
	    }
		return modifyFlag;
	}

	public ArrayList getUserRespQstIdsList(Connection con, String strSectionId, String strUserId, String strQuery) throws SQLException {
		ArrayList alIdsList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try { 
			pstmt = con.prepareStatement(strQuery);
			logger.info(strQuery);
			pstmt.setString(1, strSectionId);
			logger.info(strSectionId);
			pstmt.setString(2, strUserId);
			logger.info(strUserId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				logger.info("inside while - " + rs.getString("QUESTIONID"));
				alIdsList.add(rs.getString("QUESTIONID"));
			}
		} catch(SQLException se) {
logger.error(" getUserRespQstIdsList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alIdsList;
	}

	public boolean updateSurveyMembers(Connection con, String strSurveyId, String strUserId) throws SQLException {
		boolean modifyFlag = false;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null && !strSurveyId.equals("")) {
			alValues.add(strSurveyId);
			alValues.add(strUserId);
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SURVEY_MEMBERS, alValues, "updateSurveyMembers", "OIDAOResponseUser") >= 0);
		}
		return modifyFlag;
	}

	public boolean updateUserSurveyCount(Connection con, String strSurveyId) throws SQLException {
		boolean modifyFlag = false;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null && !strSurveyId.equals("")) {
			alValues.add(strSurveyId);
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_USER_SURVEY_CNT, alValues, "updateUserSurveyCount", "OIDAOResponseUser") == 1);
		}
		return modifyFlag;
	}


}
