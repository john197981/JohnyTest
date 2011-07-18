
package com.oifm.survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAOQuestionUser extends OIBaseDao {

	private static final Logger logger = Logger.getLogger(OIDAOQuestionUser.class);

	public OIDAOQuestionUser() {	}

	public ArrayList fetchSectionQuestionList(Connection con, String strSectionId) throws SQLException {
		ArrayList alQuestionList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBAQuestion objQuestion = null;
		
		try {
			pstmt = con.prepareStatement(OISurveySqls.PUBLISH_SEC_QSTN_LIST);
			pstmt.setString(1, strSectionId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objQuestion = new OIBAQuestion();
				objQuestion.setStrSectionId(rs.getString("SECTIONID"));
				objQuestion.setStrQuestionId(rs.getString("QUESTIONID"));
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
				alQuestionList.add(objQuestion);
			}
		} catch(SQLException se) {
logger.error(" fetchSectionQuestionList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alQuestionList;
	}
	
}
