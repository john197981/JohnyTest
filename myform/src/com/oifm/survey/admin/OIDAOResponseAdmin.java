package com.oifm.survey.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.oifm.survey.OIBAResponse;
import com.oifm.survey.OIBASurveyResponse;
import com.oifm.survey.OIDAOResponseUser;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;

public class OIDAOResponseAdmin extends OIDAOResponseUser 
{
	private static final Logger logger = Logger.getLogger(OIDAOResponseAdmin.class);

	public OIDAOResponseAdmin()	{	}

	public boolean delteSurveyResponses(Connection con, String strSurveyId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alResponseValues = new ArrayList();
		if(strSurveyId != null) 
		{
			alResponseValues.add(strSurveyId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_SURVEY_RESPONSES, alResponseValues, "deleteSurveyResponses", "OIDAOResponseAdmin") >= 0);
		} 
		return deleteFlag;
	}

	public boolean delteSectionResponses(Connection con, String strSectionId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alResponseValues = new ArrayList();
		if(strSectionId != null) 
		{
			alResponseValues.add(strSectionId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_SECTION_RESPONSES, alResponseValues, "delteSectionResponses", "OIDAOResponseAdmin") >= 0);
		}
		return deleteFlag;
	}

	public boolean delteQuestionResponses(Connection con, String strQuestionId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alResponseValues = new ArrayList();
		if(strQuestionId != null) 
		{
			alResponseValues.add(strQuestionId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_QUESTION_RESPONSES, alResponseValues, "delteQuestionResponses", "OIDAOResponseAdmin") >= 0);
		}
		return deleteFlag;
	}

	public boolean delteDraftInfo(Connection con, String strSurveyId) throws SQLException 
	{
		boolean deleteFlag = false;
		ArrayList alValues = new ArrayList();
		if(strSurveyId != null) 
		{
			alValues.add(strSurveyId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_DRAFT_INFO, alValues, "delteDraftInfo", "OIDAOResponseAdmin") >= 0);
		}
		return deleteFlag;
	}
	
	public boolean deleteMembers(Connection connection, String strSurveyId) throws SQLException
	{
		boolean deleteFlag = false;
		ArrayList alValues = new ArrayList();
		if (strSurveyId != null)
		{
			alValues.add(strSurveyId);
			deleteFlag = (OISQLUtilities.executeSingle(connection, OISurveySqls.DELETE_MEMBERS, alValues, "deleteMembers", "OIDAOResponseAdmin") >= 0);
		}
		return deleteFlag;
	}

	public ArrayList getSurveyResponses(Connection con, String strSurveyId, int startRec, String strQuery) throws SQLException 
	{
		ArrayList alSurveyResponses = new ArrayList();
		Hashtable htbUserResponses =  null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurveyResponse objSurveyResponse = null;
		OIBAResponse objResponse = null;
		String strQuestionId = null;
		String strUserId = "";
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strSurveyId);
			//commented by edmund
			//pstmt.setString(2, strSurveyId);
			rs = pstmt.executeQuery();
			//logger.debug("strQuery = "+strQuery);
			//System.out.println("strQuery = "+strQuery);
			while(rs.next())	
			{
			    logger.debug("With in while loop Response  strUserId : "+strUserId+" NEW strUserId "+rs.getString("USERID"));
				if(!strUserId.equals(rs.getString("USERID"))) 
				{
				    logger.debug("Entered into if loop Response");
					if(htbUserResponses != null) 
					{
						objSurveyResponse.setHtbUserResponse(htbUserResponses);
						alSurveyResponses.add(objSurveyResponse);
					}
					objSurveyResponse = new OIBASurveyResponse();
					htbUserResponses =  new Hashtable();
					strUserId = rs.getString("USERID");
					objSurveyResponse.setStrNickName(rs.getString("NICKNAME"));
					objSurveyResponse.setStrDivisioncode(rs.getString("DIVISIONCODE"));
					objSurveyResponse.setStrSchoolCode(rs.getString("SCHOOLCODE"));
					objSurveyResponse.setAge(rs.getString("age"));
					objSurveyResponse.setService(rs.getString("service"));
					objSurveyResponse.setDesignation(rs.getString("Designation"));
					objSurveyResponse.setEmail(rs.getString("emailid"));
					objSurveyResponse.setSchoolLevel(rs.getString("SCHOOL_TYPE"));
				}
				objResponse = new OIBAResponse();
				strQuestionId = rs.getString("QUESTIONID");
				logger.debug("Entered into normal loop => strQuestionId : "+strQuestionId );
				objResponse.setStrQuestionId(strQuestionId);
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
				htbUserResponses.put(strQuestionId, objResponse);
			}
			if(htbUserResponses != null) 
			{
				objSurveyResponse.setHtbUserResponse(htbUserResponses);
				alSurveyResponses.add(objSurveyResponse);
			}
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyResponses() "+se.getMessage());
			throw se;
		} finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSurveyResponses;
	}	
	
	//added by edmund to get data for survey respondent report
	public ArrayList getSurveyRespondentsList(Connection con, String strSurveyId, String strQuery) throws SQLException 
	{
		ArrayList alRespondents = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASurveyResponse objSurveyResponse = null;
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				objSurveyResponse = new OIBASurveyResponse();
				objSurveyResponse.setStrNickName(rs.getString("NAME"));
				objSurveyResponse.setEmail(rs.getString("emailaddress"));
				alRespondents.add(objSurveyResponse);
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyRespondentsList() "+se.getMessage());
			throw se;
		} finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alRespondents;
	}
	
	//	added by edmund to get data for survey demographic summary report
	public ArrayList getSurveyRespondentsData(Connection con, String strSurveyId, String strQuery) throws SQLException 
	{
		ArrayList alRespondentsData = new ArrayList();
		ArrayList alTempData = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				alTempData = new ArrayList();
				alTempData.add(rs.getString("DESCRIPTION"));
				alTempData.add(rs.getString("TOTAL_RESPONSES"));
				alRespondentsData.add(alTempData);
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyRespondentsData() "+se.getMessage());
			throw se;
		} finally 
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alRespondentsData;
	}
	
//	added by edmund to get data for survey demographic detail report select box value
	public ArrayList getSurveySelectionData(Connection con, String strQuery) throws SQLException 
	{
		ArrayList alSelectionData = new ArrayList();
		ArrayList alTempData = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try 
		{
			stmt = con.prepareStatement(strQuery);
			rs = stmt.executeQuery(strQuery);
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				alTempData = new ArrayList();
				alTempData.add(rs.getString("DESCRIPTION"));
				alTempData.add(rs.getString("VALUE"));
				alSelectionData.add(alTempData);
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyRespondentsData() "+se.getMessage());
			throw se;
		} finally 
		{
			//OISQLUtilities.closeRsetPstatement(rs, stmt);
			OISQLUtilities.closeStatement(stmt);
		}
		return alSelectionData;
	}
	
//	added by edmund to get data for survey demographic detail report
	public ArrayList getSurveyDetailReport(Connection con, String strSurveyId, String strQuery, int iCount, String strDesignation, String strLevel) throws SQLException 
	{
		ArrayList alReportData = new ArrayList();
		ArrayList alTempData = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		//System.out.println("OIDAOResponseAdmin: getSurveyDetailReport - var : "+strQuery);
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strSurveyId);
			
				if(iCount == 1){
					pstmt.setString(2, strDesignation);
				}else if(iCount == 2){
					pstmt.setString(2, strLevel);
				}else if(iCount == 3){
					pstmt.setString(2, strDesignation);
					pstmt.setString(3, strLevel);
				}
				
			rs = pstmt.executeQuery();
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				alTempData = new ArrayList();
				alTempData.add(rs.getString("questionid"));
				alTempData.add(rs.getString("question"));
				alTempData.add(rs.getString("answer1"));
				alTempData.add(rs.getString("answer2"));
				alTempData.add(rs.getString("answer3"));
				alTempData.add(rs.getString("answer4"));
				alTempData.add(rs.getString("answer5"));
				alTempData.add(rs.getString("answer6"));
				alTempData.add(rs.getString("answer7"));
				alTempData.add(rs.getString("answer8"));
				alTempData.add(rs.getString("answer9"));
				alTempData.add(rs.getString("answer10"));
				alTempData.add(rs.getString("NEEDOTHERREMARK"));
				alTempData.add(rs.getString("response1Count"));
				alTempData.add(rs.getString("response2Count"));
				alTempData.add(rs.getString("response3Count"));
				alTempData.add(rs.getString("response4Count"));
				alTempData.add(rs.getString("response5Count"));
				alTempData.add(rs.getString("response6Count"));
				alTempData.add(rs.getString("response7Count"));
				alTempData.add(rs.getString("response8Count"));
				alTempData.add(rs.getString("response9Count"));
				alTempData.add(rs.getString("response10Count"));
				alTempData.add(rs.getString("oRemarksCount"));
				alReportData.add(alTempData);
			}
			//System.out.println("OIDAOResponseAdmin: getSurveyDetailReport - var : "+"DAO1");
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyRespondentsData() "+se.getMessage());
			throw se;
		} finally 
		{
			if(pstmt != null)
				OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alReportData;
	}
	
	// Added by Oscar @ 21 June 2007
	public Hashtable getNumberOfQuestionRespondent(Connection conn, String strSurveyId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Hashtable table = null;
		try 
		{
			table = new Hashtable();
			
			pstmt = conn.prepareStatement(OISurveySqls.NUMBER_OF_QUESTION_RESPONDENTS);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				table.put(rs.getString("QUESTIONID"), rs.getString("COUNT"));
			}
		} catch (SQLException se) {
		
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return table;
	}
	
	//added by edmund
	public ArrayList getOpenEndQuesiton(Connection con, String strQuestionId, String strQuery) throws SQLException 
	{
		ArrayList alReportData = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strQuestionId);
				
			rs = pstmt.executeQuery();
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				alReportData.add(rs.getString("OTHERREMARKS"));
				
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyRespondentsData() "+se.getMessage());
			throw se;
		} finally 
		{
			if(pstmt != null)
				OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alReportData;
	}
	//added by edmund
	
//	added by anbu
	public String getOpenEndQuesitonName(Connection con, String strQuestionId, String strQuery) throws SQLException 
	{
		String strQuestion = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strQuestionId);
				
			rs = pstmt.executeQuery();
			logger.debug("strQuery = "+strQuery);
			if(rs.next())	
			{
				strQuestion= rs.getString("QUESTION");
				
			}
			
		} catch(SQLException se) 
		{
			logger.error(" getOpenEndQuesitonName() "+se.getMessage());
			throw se;
		} finally 
		{
			if(pstmt != null)
				OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return strQuestion;
	}
	//added by edmund
	
	public String getTotalUser(Connection con, String strId, String strQuery) throws SQLException 
	{
		String strTotal = "1";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(strId));
			rs = pstmt.executeQuery();
			
			logger.debug("strQuery = "+OISurveySqls.TOTAL_USER);
			while(rs.next())	
			{
				strTotal = rs.getString("total");
			}
			
			if ("0".equals(strTotal)) {
				strTotal = "1";
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyRespondentsData() "+se.getMessage());
			throw se;
		} finally 
		{
			if(pstmt != null)
				OISQLUtilities.closeStatement(pstmt);
		}
		return strTotal;
	}
	
	public String getSurveyTitle(Connection con, String strId, String strQuery) throws SQLException 
	{
		String strTitle = "";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try 
		{
			pstmt = con.prepareStatement(strQuery);
			pstmt.setInt(1, Integer.parseInt(strId));
			rs = pstmt.executeQuery();
			
			logger.debug("strQuery = "+strQuery);
			while(rs.next())	
			{
				strTitle = rs.getString("title");
			}
			
		} catch(SQLException se) 
		{
		    logger.error(" getSurveyTitle() "+se.getMessage());
			throw se;
		} finally 
		{
			if(pstmt != null)
				OISQLUtilities.closeStatement(pstmt);
		}
		return strTitle;
	}
}