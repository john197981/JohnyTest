
package com.oifm.survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.StringTokenizer;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.siteRanking.OIBAWebSiteRanking;
import com.oifm.siteRanking.OIDAOWebSiteRanking;
import com.oifm.utility.OISQLUtilities;
import com.oifm.utility.StringUtility;
import com.oifm.utility.OIUtilities;

public class OIDAOSurveyUser extends OIBaseDao {

	private static final Logger logger = Logger.getLogger(OIDAOSurveyUser.class);

	public OIDAOSurveyUser()	{	}

	public OIBASurvey fetchSurveyInfo(Connection con, OIBASurveySection objSurveySectionVo) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		String strSurveyId= objSurveySectionVo.getStrSurveyId();
		try {
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_DETAILS);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSurvey = new OIBASurvey(); 
				objSurvey.setStrSurveyId(strSurveyId);
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
		        if (objSurvey.getStrDescription()!=null)
		        	objSurvey.setStrDescription(objSurvey.getStrDescription().replaceAll("\n","<br>"));
				objSurvey.setStrInstruction(rs.getString("INSTRUCTIONS"));
		        if (objSurvey.getStrInstruction()!=null)
		        	objSurvey.setStrInstruction(objSurvey.getStrInstruction().replaceAll("\n","<br>"));
				objSurvey.setStrFromDate(rs.getString("START_ON"));
				objSurvey.setStrToDate(rs.getString("EXPIRY_ON"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				objSurvey.setStrSummary(rs.getString("SUMMARY"));
				objSurvey.setStrAttachedFile(rs.getString("ATTACHED_FILE"));
				objSurvey.setStrPublishedStatus(rs.getString("PUBLISHED_ST"));
				objSurvey.setStrMailStatus(rs.getString("MAIL_STATUS"));
				objSurvey.setStrReminderMode(rs.getString("REMINDER_MODE"));
				objSurvey.setStrAcknowledgeMode(rs.getString("ACK_MODE"));
				objSurvey.setStrIsActive(rs.getString("ACTIVE"));
				objSurvey.setStrDivisionCode(rs.getString("DIVISIONCODE"));
				objSurvey.setStrTargetAudience(rs.getString("TARGETAUDIENCE"));
				objSurvey.setStrContactPerson(rs.getString("CONTACTPERSON"));
				objSurvey.setStrPhone(rs.getString("PHONE")); 
				objSurvey.setStrEmailAddress(rs.getString("EMAILADDRESS"));

				objSurvey.setStrCompletionTime(rs.getString("COMPLETIONTIME"));
				objSurvey.setStrFindingsPlannedDt(rs.getString("FINDING_PUBLISHED_PLANNED_DATE"));
				objSurvey.setStrViewFindingType(rs.getString("FINDINGS_VIEW_TYPE"));
				objSurvey.setStrOpenTag(rs.getString("OPENTAG"));
				objSurvey.setStrTagWords(rs.getString("TAG_WORDS"));
				objSurvey.setStrEmailDate(rs.getString("EMAILDATE"));
				objSurvey.setStrEmailMessage(OIUtilities.clobToString(rs.getClob("EMAILMESSAGE")));
				objSurvey.setOutsideMOEChecked(rs.getString("IS_OUTSIDE_MOE"));	// added by K.K.Kumaresan
				logger.error("ddddd");
			}
//			 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
			// End : Added by deva on Sep 26, 2007
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_GP_IDS);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			String gpIds="";
			while(rs.next())
			{
				gpIds+=rs.getString("GROUPID")+",";
			}
			logger.error("gpIds==="+gpIds);
			objSurvey.setStrTargetGpIds(gpIds);
			try{
				if(objSurveySectionVo.getStrUserId()!= null){
					String pUserId = objSurveySectionVo.getStrUserId();
					OIBAWebSiteRanking aOIBAWebSiteRanking = new OIBAWebSiteRanking();
			        aOIBAWebSiteRanking.setActionId("VS");
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
			
		} catch(SQLException se) {
logger.error(" fetchSurveyInfo() "+se.getMessage());
			throw se;
		}
		 catch(IOException ie) {
logger.error(" fetchSurveyInfo() "+ie.getMessage());
			//throw ie;
		}
		finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objSurvey;
	}

	public ArrayList getCurrentSurveysList(Connection con, String strUserId) throws SQLException {
		ArrayList alCurrSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		try {
			pstmt = con.prepareStatement(OISurveySqls.USER_CURRENT_SURVEYS);
			pstmt.setString(1, strUserId);
			pstmt.setString(2, strUserId);
			//pstmt.setString(3, strUserId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSurvey = new OIBASurvey(); 
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
		        if (objSurvey.getStrDescription()!=null)
		        	objSurvey.setStrDescription(objSurvey.getStrDescription().replaceAll("\n","<br>"));
		        else
		        	objSurvey.setStrDescription(objSurvey.getStrDescription());
				objSurvey.setStrToDate(rs.getString("EXPIRY_DATE"));
				objSurvey.setStrPublishedStatus(rs.getString("STATUS"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				alCurrSurveyList.add(objSurvey);
			}
		} catch(SQLException se) {
logger.error(" getCurrentSurveysList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alCurrSurveyList;
	}

	public ArrayList getPastSurveysList(Connection con, String strUserId) throws SQLException {
		ArrayList alPastSurveyList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurvey objSurvey = null;
		try {
			pstmt = con.prepareStatement(OISurveySqls.USER_PAST_SURVEYS);
			pstmt.setString(1, strUserId);
			//pstmt.setString(2, strUserId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSurvey = new OIBASurvey(); 
				objSurvey.setStrSurveyId(rs.getString("SURVEYID"));
				objSurvey.setStrSurveyName(rs.getString("NAME"));
				objSurvey.setStrDescription(rs.getString("DESCRIPTION"));
				if (objSurvey.getStrDescription()!=null)
					objSurvey.setStrDescription(StringUtility.getNNoWords(objSurvey.getStrDescription(),15).concat("..."));
				
				objSurvey.setStrToDate(rs.getString("PUBLISHED_DATE"));
				objSurvey.setStrSurveyType(rs.getString("SECURITY"));
				alPastSurveyList.add(objSurvey);
			}
		} catch(SQLException se) {
logger.error(" getPastSurveysList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alPastSurveyList;
	}

	public int getUserSurveyPercentageCompletion(Connection con, String strSurveyId, String strUserId, String strQuery) throws SQLException {
		int percentage = 0;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null && strUserId != null) {
			alValues.add(strSurveyId);
			alValues.add(strSurveyId);
			alValues.add(strUserId);
			percentage = OISQLUtilities.getNumber(con, strQuery, alValues, "getUserSurveyPercentageCompletion", "OIDAOSurveyUser");
		}
		return percentage;
	}

	public boolean checkUserResponsesForMandatoryQsts(Connection con, String strSurveyId, String strUserId, String strQuery) throws SQLException {
		boolean checkFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		if(strSurveyId != null && strUserId != null) {
			alSurveyValues.add(strSurveyId);
			alSurveyValues.add(strSurveyId);
			alSurveyValues.add(strUserId);
			checkFlag = OISQLUtilities.checkExists(con, strQuery, alSurveyValues, "checkUserResponsesForMandatoryQsts", "OIDAOSurveyUser");
		}
		return checkFlag;
	}

	public boolean checkUserSubmittedSurvey(Connection con, String strSurveyId, String strUserId) throws SQLException {
		boolean checkFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		if(strSurveyId != null && strUserId != null) {
			alSurveyValues.add(strSurveyId);
			alSurveyValues.add(strUserId);
			checkFlag = OISQLUtilities.checkExists(con, OISurveySqls.SURVEY_USER_SUBMITTED, alSurveyValues, "checkUserSubmittedSurvey", "OIDAOSurveyUser");
		}
		return checkFlag;
	}
	
	public boolean checkUserCanAccessSurvey(Connection con, String strSurveyId, String strUserId) throws SQLException {
		boolean checkFlag = false;
		ArrayList alSurveyValues = new ArrayList();
		if(strSurveyId != null && strUserId != null) {
//			alSurveyValues.add(strUserId);
//			alSurveyValues.add(strUserId);
			alSurveyValues.add(strSurveyId);
			alSurveyValues.add(strUserId);
			checkFlag = OISQLUtilities.checkExists(con, OISurveySqls.USER_CAN_ACCESS_SURVEY, alSurveyValues, "checkUserCanAccessSurvey", "OIDAOSurveyUser");
		}
		return checkFlag;
	}
	
	public String getSurveyAttachedFile(Connection con, String strSurveyId) throws SQLException {
		String strFile = null;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null) {
			alValues.add(strSurveyId);
			strFile = OISQLUtilities.getString(con, OISurveySqls.SURVEY_ATTACHED_INFO, alValues, "getSurveyAttachedFile", "OIDAOSurveyUser");
		}
		return strFile;
	}
	
	private LinkedHashMap getSurveyMembers (Connection connection, String strSurveyId) throws Exception
	{
		LinkedHashMap hmMap = new LinkedHashMap();
		
		ResultSet objRs = null;
		PreparedStatement objPs = null;
		ResultSet objRs2 = null;
		PreparedStatement objPs2 = null;
		
		try
		{
			// Survey private group members
			objPs = connection.prepareStatement(OISurveySqls.QRY_SU_MEMBERS);
			objPs.setInt(1, Integer.parseInt(strSurveyId));
			objRs = objPs.executeQuery();
			
			if (objRs != null)
			{
				while (objRs.next())
				{
					hmMap.put(objRs.getString(1), objRs.getString(1));
				}
			}
			
//			 Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(objRs, objPs);
			// End : Added by deva on Sep 26, 2007
			// Survey fixed group members
			objPs = connection.prepareStatement(OISurveySqls.QRY_SU_FIXED_GROUP_CONDITION);
			objPs.setInt(1, Integer.parseInt(strSurveyId));
			objRs = objPs.executeQuery();
			
			if (objRs != null)
			{
				while (objRs.next())
				{
					String condition = objRs.getString(1);
					
					objPs2 = connection.prepareStatement(OISurveySqls.QRY_USERIDS_ON_CONDITION + condition);
					objRs2 = objPs2.executeQuery();
					
					if (objRs2 != null)
					{
						while (objRs2.next())
						{
							hmMap.put(objRs2.getString(1), objRs2.getString(1));
						}
					}
//					 Start : Added by deva on Sep 26, 2007
					OISQLUtilities.closeRsetPstatement(objRs2, objPs2);
					// End : Added by deva on Sep 26, 2007
				}
			}
			
			// Survey interest group members
//			objPs = connection.prepareStatement(OISurveySqls.QRY_SU_TAG_WORDS);
//			objPs.setInt(1, Integer.parseInt(strSurveyId));
//			objRs = objPs.executeQuery();
//			
//			if (objRs != null)
//			{
//				while (objRs.next())
//				{
//					String tagWord = objRs.getString(1);
//					objPs2 = connection.prepareStatement(OISurveySqls.QRY_USERIDS_ON_CONDITION);
//					objPs2.setString(1, tagWord);
//					objRs2 = objPs2.executeQuery();
//					
//					if (objRs2 != null)
//					{
//						while (objRs2.next())
//						{
//							hmMap.put(objRs2.getString(1), objRs2.getString(1));
//						}
//					}
//				}
//			}
		}
		catch (Exception sqle)
		{
			logger.error("getSurveyMembers - " + sqle.getMessage());
			throw sqle;
		}
		finally
		{
			if (objRs != null)
				objRs.close();
			if (objRs2 != null)
				objRs2.close();
			if (objPs != null)
				objPs.close();
			if (objPs2 != null)
				objPs2.close();
			
		}
		
		return hmMap;
	}

	public String getSurveyDefaultMessage(Connection connection) throws SQLException
	{
		PreparedStatement pst = null;
		ResultSet rs = null;
		String defaultMessage = "";
		
		try
		{	
			pst = connection.prepareStatement(OISurveySqls.QRY_SU_DEFAULT_MESSAGE);
			rs = pst.executeQuery();
			
			if (rs.next())
				defaultMessage = rs.getString("DESCRIPTION");
		}
		catch (SQLException se)
		{
			logger.error("get default message " + se.getMessage());
			throw se;
		}
		finally
		{
			// Start : Added by deva on Sep 26, 2007
			OISQLUtilities.closeRsetPstatement(rs, pst);
			// End : Added by deva on Sep 26, 2007	
		}
		return defaultMessage;
	}
	
	
	//	 added by K.K.Kumaresan on Jan 19,2008
	/**
	 * fetch External Email Address
	 */
	public String fetchExternalEmailAddress(Connection con, String strSurveyId) throws SQLException 
	{
		logger.info("Start of fetching externalEmailAddress");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String externalEmailAddress= "";
		try
		{
			pstmt = con.prepareStatement(OISurveySqls.SELECT_EXTERNAL_MAIL);
			logger.info("SELECT_EXTERNAL_MAIL is"+OISurveySqls.SELECT_EXTERNAL_MAIL);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while(rs!=null && rs.next())
			{
				//externalEmailAddress+=rs.getString("EMAILADDRESS")+";";
				externalEmailAddress+=rs.getString("EMAILADDRESS");
			}
						
		} 
		catch(SQLException se) {
			logger.error(" getPastSurveysList() "+se.getMessage());
			throw se;
		} 
		finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		logger.info("End of fetching externalEmailAddress");
		return externalEmailAddress;
	}
	
//	 added by K.K.Kumaresan on Jan 21,2008
	/**
	 * check Eligible User
	 */
	public boolean checkEligibleUser(Connection con, String userId,String surveyId) throws SQLException 
	{
		logger.info("Start of checkEligibleUser");
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		boolean eligible=false;
		try
		{
			userId="%"+userId+"%";
			pstmt = con.prepareStatement(OISurveySqls.CHECK_ELIGIBLE_USER);
			logger.info("checkEligibleUser query is"+OISurveySqls.CHECK_ELIGIBLE_USER);
			pstmt.setString(1, surveyId);
			pstmt.setString(2, userId);
			rs = pstmt.executeQuery();
			if(rs.next())
			{
				// if the mail id is binded with the surveyid, it is a valid user.other wise invalid user.
				eligible=true;
			}
						
		} 
		catch(SQLException se) {
			logger.error(" getPastSurveysList() "+se.getMessage());
			throw se;
		} 
		finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		logger.info("End of checkEligibleUser");
		return eligible;
	}
} 
