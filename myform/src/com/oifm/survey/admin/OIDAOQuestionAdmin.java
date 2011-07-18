package com.oifm.survey.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.oifm.survey.OIBAQuestion;
import com.oifm.survey.OIDAOQuestionUser;
import com.oifm.survey.OISurveySqls;
import com.oifm.survey.OISurveyConstants;
import com.oifm.utility.OISQLUtilities;

public class OIDAOQuestionAdmin extends OIDAOQuestionUser
{
	
	private static final Logger logger = Logger.getLogger(OIDAOQuestionAdmin.class);
	
	
	public OIDAOQuestionAdmin()
	{}
	
	public Hashtable getSectionsQuestionsList(Connection con, String strSurveyId) throws SQLException
	{
		Hashtable htbSections = new Hashtable();
		ArrayList alQuestionList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAQuestion objQuestion = null;
		String strSectionId = "";
		
		try
		{
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_QUESTIONS_LIST);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				objQuestion = new OIBAQuestion();
				strSectionId = rs.getString("SECTIONID");
				objQuestion.setStrQuestionId(rs.getString("QUESTIONID"));
				objQuestion.setStrQuestion(rs.getString("QUESTION"));
				objQuestion.setStrUseSameLikert(rs.getString("USE_LIKERT"));
				objQuestion.setCanMoveUp(rs.getString("CAN_MOVE_UP"));
				objQuestion.setCanMoveDown(rs.getString("CAN_MOVE_DOWN"));
				if (htbSections.containsKey(strSectionId))
					alQuestionList = (ArrayList) htbSections.get(strSectionId);
				else
					alQuestionList = new ArrayList();
				alQuestionList.add(objQuestion);
				htbSections.put(strSectionId, alQuestionList);
			}
		}
		catch (SQLException se)
		{
			logger.error(" getSectionList() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return htbSections;
	}
	
	public Hashtable getSectionsQuestionsAnswersList(Connection con, String strSurveyId) throws SQLException
	{
		Hashtable htbSections = new Hashtable();
		ArrayList alQuestionList = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAQuestion objQuestion = null;
		String strSectionId = "";
		
		try
		{
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_QUESTIONS_ANSWERS_LIST);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				objQuestion = new OIBAQuestion();
				strSectionId = rs.getString("SECTIONID");
				objQuestion.setStrQuestionId(rs.getString("QUESTIONID"));
				objQuestion.setStrQuestion(rs.getString("QUESTION"));
				objQuestion.setStrInstruction(rs.getString("INSTRUCTIONS"));
				objQuestion.setStrQuestionType(rs.getString("TYPE"));
				objQuestion.setStrOtherRemarks(rs.getString("NEEDOTHERREMARK"));
				objQuestion.setStrAnswer1(rs.getString("ANSWER1"));
				objQuestion.setStrAnswer2(rs.getString("ANSWER2"));
				objQuestion.setStrAnswer3(rs.getString("ANSWER3"));
				objQuestion.setStrAnswer4(rs.getString("ANSWER4"));
				objQuestion.setStrAnswer5(rs.getString("ANSWER5"));
				objQuestion.setStrAnswer6(rs.getString("ANSWER6"));
				objQuestion.setStrAnswer7(rs.getString("ANSWER7"));
				objQuestion.setStrAnswer8(rs.getString("ANSWER8"));
				objQuestion.setStrAnswer9(rs.getString("ANSWER9"));
				objQuestion.setStrAnswer10(rs.getString("ANSWER10"));
				objQuestion.setStrUseSameLikert(rs.getString("USE_LIKERT"));
				if (htbSections.containsKey(strSectionId))
					alQuestionList = (ArrayList) htbSections.get(strSectionId);
				else
					alQuestionList = new ArrayList();
				alQuestionList.add(objQuestion);
				htbSections.put(strSectionId, alQuestionList);
			}
		}
		catch (SQLException se)
		{
			logger.error(" getSectionsQuestionsAnswersList() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return htbSections;
	}
	
	public boolean createNewQuestion(Connection con, OIBAQuestion objQuestion) throws SQLException
	{
		boolean insertFlag = false;
		ArrayList alQuestionValues = new ArrayList();
		if (objQuestion != null)
		{
			alQuestionValues.add(objQuestion.getStrSurveyId());
			alQuestionValues.add(objQuestion.getStrSectionId());
			alQuestionValues.add(objQuestion.getStrQuestion());
			alQuestionValues.add(objQuestion.getStrInstruction());
			alQuestionValues.add(objQuestion.getStrQuestionType());
			alQuestionValues.add(objQuestion.getStrOtherRemarks());
			alQuestionValues.add(objQuestion.getStrOptional());
			alQuestionValues.add(objQuestion.getStrAnswer1());
			alQuestionValues.add(objQuestion.getStrAnswer2());
			alQuestionValues.add(objQuestion.getStrAnswer3());
			alQuestionValues.add(objQuestion.getStrAnswer4());
			alQuestionValues.add(objQuestion.getStrAnswer5());
			alQuestionValues.add(objQuestion.getStrAnswer6());
			alQuestionValues.add(objQuestion.getStrAnswer7());
			alQuestionValues.add(objQuestion.getStrAnswer8());
			alQuestionValues.add(objQuestion.getStrAnswer9());
			alQuestionValues.add(objQuestion.getStrAnswer10());
			alQuestionValues.add(objQuestion.getStrUseSameLikert());
			insertFlag = (OISQLUtilities
					.executeSingle(con, OISurveySqls.CREATE_QUESTION, alQuestionValues, "createNewQuestion", "OIDAOQuestionAdmin") == 1);
			if ("L".equals(objQuestion.getStrQuestionType()) && insertFlag)
			{
				alQuestionValues = new ArrayList();
				if (objQuestion.getStrUseSameLikert() != null && !"".equals(objQuestion.getStrUseSameLikert()))
				{
					alQuestionValues.add("0");
					alQuestionValues.add(objQuestion.getStrUseSameLikert());
					OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SRC_LIKERT_SCALE, alQuestionValues, "createNewQuestion",
							"OIDAOQuestionAdmin");
				}
				
			}
		}
		return insertFlag;
	}
	
	public OIBAQuestion getQuestionDetails(Connection con, String strQuestionId) throws SQLException
	{
		OIBAQuestion objQuestion = fetchQuestionDetails(con, OISurveySqls.QUESTION_DETAILS, strQuestionId);
		if (objQuestion != null)
			objQuestion.setStrQuestionId(strQuestionId);
		return objQuestion;
	}
	
	public OIBAQuestion getPrevQuestionDetails(Connection con, String strQuestionId, String strSectionId) throws SQLException
	{
		
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAQuestion objPrevQuestion = null;
		
		try
		{
			pstmt = con.prepareStatement(OISurveySqls.PREV_QUESTION_DETAILS);
			pstmt.setString(1, strQuestionId);
			pstmt.setString(2, strSectionId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				objPrevQuestion = new OIBAQuestion();
				objPrevQuestion.setStrQuestionId(rs.getString("QUESTIONID"));
				objPrevQuestion.setStrSurveyId(rs.getString("SURVEYID"));
				objPrevQuestion.setStrSectionId(rs.getString("SECTIONID"));
				objPrevQuestion.setStrQuestion(rs.getString("QUESTION"));
				objPrevQuestion.setStrInstruction(rs.getString("INSTRUCTIONS"));
				objPrevQuestion.setStrQuestionType(rs.getString("TYPE"));
				objPrevQuestion.setStrOtherRemarks(rs.getString("NEEDOTHERREMARK"));
				objPrevQuestion.setStrOptional(rs.getString("MANDATORY"));
				objPrevQuestion.setStrAnswer1(rs.getString("ANSWER1"));
				objPrevQuestion.setStrAnswer2(rs.getString("ANSWER2"));
				objPrevQuestion.setStrAnswer3(rs.getString("ANSWER3"));
				objPrevQuestion.setStrAnswer4(rs.getString("ANSWER4"));
				objPrevQuestion.setStrAnswer5(rs.getString("ANSWER5"));
				objPrevQuestion.setStrAnswer6(rs.getString("ANSWER6"));
				objPrevQuestion.setStrAnswer7(rs.getString("ANSWER7"));
				objPrevQuestion.setStrAnswer8(rs.getString("ANSWER8"));
				objPrevQuestion.setStrAnswer9(rs.getString("ANSWER9"));
				objPrevQuestion.setStrAnswer10(rs.getString("ANSWER10"));
				objPrevQuestion.setStrUseSameLikert(rs.getString("USE_LIKERT"));
			}
		}
		catch (SQLException se)
		{
			logger.error(" getPrevQuestionDetails() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objPrevQuestion;
	}
	
	public OIBAQuestion getTemplateQuestionDetails(Connection con, String strSectionId) throws SQLException
	{
		return fetchQuestionDetails(con, OISurveySqls.TEMPLATE_QUESTION_DETAILS, strSectionId);
	}
	
	private OIBAQuestion fetchQuestionDetails(Connection con, String strQry, String strId) throws SQLException
	{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAQuestion objQuestion = null;
		logger.debug(" Before invoke fetchQuestionDetails()	 => strQry : " + strQry + " \t strId : " + strId);
		
		try
		{
			pstmt = con.prepareStatement(strQry);
			pstmt.setString(1, strId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				
				objQuestion = new OIBAQuestion();
				objQuestion.setStrSurveyId(rs.getString("SURVEYID"));
				objQuestion.setStrSectionId(rs.getString("SECTIONID"));
				// objQuestion.setStrQuestionId(rs.getString("QUESTIONID"));
				objQuestion.setStrQuestion(rs.getString("QUESTION"));
				objQuestion.setStrInstruction(rs.getString("INSTRUCTIONS"));
				objQuestion.setStrQuestionType(rs.getString("TYPE"));
				objQuestion.setStrOtherRemarks(rs.getString("NEEDOTHERREMARK"));
				objQuestion.setStrOptional(rs.getString("MANDATORY"));
				objQuestion.setStrAnswer1(rs.getString("ANSWER1"));
				objQuestion.setStrAnswer2(rs.getString("ANSWER2"));
				objQuestion.setStrAnswer3(rs.getString("ANSWER3"));
				objQuestion.setStrAnswer4(rs.getString("ANSWER4"));
				objQuestion.setStrAnswer5(rs.getString("ANSWER5"));
				objQuestion.setStrAnswer6(rs.getString("ANSWER6"));
				objQuestion.setStrAnswer7(rs.getString("ANSWER7"));
				objQuestion.setStrAnswer8(rs.getString("ANSWER8"));
				objQuestion.setStrAnswer9(rs.getString("ANSWER9"));
				objQuestion.setStrAnswer10(rs.getString("ANSWER10"));
				objQuestion.setStrUseSameLikert(rs.getString("USE_LIKERT"));
			}
			
		}
		catch (SQLException se)
		{
			logger.error(" fetchQuestionDetails() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objQuestion;
	}
	
	public boolean modifyQuestion(Connection con, OIBAQuestion objQuestion) throws SQLException
	{
		boolean modifyFlag = false;
		ArrayList alQuestionValues = new ArrayList();
		if (objQuestion != null)
		{
			alQuestionValues.add(objQuestion.getStrSectionId());
			alQuestionValues.add(objQuestion.getStrQuestion());
			alQuestionValues.add(objQuestion.getStrInstruction());
			alQuestionValues.add(objQuestion.getStrQuestionType());
			alQuestionValues.add(objQuestion.getStrOtherRemarks());
			alQuestionValues.add(objQuestion.getStrOptional());
			alQuestionValues.add(objQuestion.getStrAnswer1());
			alQuestionValues.add(objQuestion.getStrAnswer2());
			alQuestionValues.add(objQuestion.getStrAnswer3());
			alQuestionValues.add(objQuestion.getStrAnswer4());
			alQuestionValues.add(objQuestion.getStrAnswer5());
			alQuestionValues.add(objQuestion.getStrAnswer6());
			alQuestionValues.add(objQuestion.getStrAnswer7());
			alQuestionValues.add(objQuestion.getStrAnswer8());
			alQuestionValues.add(objQuestion.getStrAnswer9());
			alQuestionValues.add(objQuestion.getStrAnswer10());
			alQuestionValues.add(objQuestion.getStrUseSameLikert());
			alQuestionValues.add(objQuestion.getStrQuestionId());
			
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_QUESTION, alQuestionValues, "modifyQuestion", "OIDAOQuestionAdmin") == 1);
			
			if ("L".equals(objQuestion.getStrQuestionType()))
			{
				alQuestionValues = new ArrayList();
				if (objQuestion.getStrUseSameLikert() != null && !"".equals(objQuestion.getStrUseSameLikert()))
				{
					alQuestionValues.add("0");
					alQuestionValues.add(objQuestion.getStrUseSameLikert());
					
					OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SRC_LIKERT_SCALE, alQuestionValues, "UPDATE_SRC_LIKERT_SCALE",
							"OIDAOQuestionAdmin");
				}
				// modified by divya
				
			}
		}
		return modifyFlag;
	}
	
	public boolean deleteQuestion(Connection con, String strQuestionId) throws SQLException
	{
		boolean deleteFlag = false;
		ArrayList alQuestionValues = new ArrayList();
		if (strQuestionId != null)
		{
			alQuestionValues.add(strQuestionId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_QUESTION, alQuestionValues, "deleteQuestion", "OIDAOQuestionAdmin") == 1);
		}
		return deleteFlag;
	}
	
	public boolean delteSectionQuestions(Connection con, String strSectionId) throws SQLException
	{
		boolean deleteFlag = false;
		ArrayList alQuestionValues = new ArrayList();
		if (strSectionId != null)
		{
			alQuestionValues.add(strSectionId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_SECTION_QUESTIONS, alQuestionValues, "deleteSectionQuestions",
					"OIDAOQuestionAdmin") >= 0);
		}
		return deleteFlag;
	}
	
	public boolean delteSurveyQuestions(Connection con, String strSurveyId) throws SQLException
	{
		boolean deleteFlag = false;
		ArrayList alQuestionValues = new ArrayList();
		if (strSurveyId != null)
		{
			alQuestionValues.add(strSurveyId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_SURVEY_QUESTIONS, alQuestionValues, "deleteSurveyQuestions",
					"OIDAOQuestionAdmin") >= 0);
		}
		return deleteFlag;
	}
	
	public ArrayList getQuestionIdsList(Connection con, String strSurveyId) throws SQLException
	{
		ArrayList alIdsList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_QUESTION_IDS);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while (rs.next())
			{
				alIdsList.add(rs.getString("QUESTIONID"));
			}
		}
		catch (SQLException se)
		{
			logger.error(" getQuestionIdsList() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alIdsList;
	}
	
	public boolean reorderQuestion(Connection con, String strQuestionId, String reorderType) throws SQLException
	{
		boolean reorderFlag = false;
		CallableStatement cstmt = null;
		try
		{
			if (strQuestionId != null)
			{
				
				logger.error("reorder query " + OISurveySqls.REORDER_QUERY);
				cstmt = con.prepareCall(OISurveySqls.REORDER_QUERY);
				cstmt.setInt(1, Integer.parseInt(strQuestionId));
				if (reorderType.equals(OISurveyConstants.QUESTION_MOVE_UP))
				{
					cstmt.setInt(2, -1);
				}
				else if (reorderType.equals(OISurveyConstants.QUESTION_MOVE_DOWN))
				{
					cstmt.setInt(2, 1);
				}
				
				cstmt.execute();
				
				reorderFlag = true;
			}
			
		}
		catch (SQLException se)
		{
			logger.error(" reorderQuestion() " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeStatement(cstmt);
		}
		return reorderFlag;
	}
	
	public void updateLikertBlockAnswers(Connection connection, String strQuestionId) throws SQLException
	{
		CallableStatement cst = null;
		try
		{
			cst = connection.prepareCall(OISurveySqls.UPDATE_LIKERT_BLOCK_ANSWERS);
			cst.setInt(1, Integer.parseInt(strQuestionId));
			cst.execute();
		}
		catch (SQLException se)
		{
			logger.error("updateLikertBlockAnswers() - " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeStatement(cst);
		}
	}
	
	public void updateLikertBlockQuestions(Connection connection, String strQuestionId, boolean moveQuestion) throws SQLException
	{
		CallableStatement cst = null;
		try
		{
			cst = connection.prepareCall(OISurveySqls.UPDATE_LIKERT_BLOCK_QUESTIONS);
			cst.setInt(1, Integer.parseInt(strQuestionId));
			cst.setString(2, (moveQuestion) ? "TRUE" : "FALSE");
			cst.execute();
		}
		catch (SQLException se)
		{
			logger.error("updateLikertBlockQuestions() - " + se.getMessage());
			throw se;
		}
		finally
		{
			OISQLUtilities.closeStatement(cst);
		}
	}
}
