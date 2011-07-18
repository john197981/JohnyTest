
package com.oifm.survey.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;

public class OIDAOTempUserAdmin extends OIBaseDao {
	
	private static final Logger logger = Logger.getLogger(OIDAOTempUserAdmin.class);

	public OIDAOTempUserAdmin()	{	}

	public ArrayList getSurveyBatchList(Connection con, String strSurveyId) throws SQLException {
		ArrayList alBatchList =  new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
 		
		try {
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_BATCHS_LIST);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				alBatchList.add(rs.getString("BATCHNO"));
			}
		} catch(SQLException se) {
logger.error(" getSurveyBatchList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alBatchList;
	}

	public ArrayList getSurveyBatchTempUsersList(Connection con, String strSurveyId, String strBatchNo) throws SQLException {
		ArrayList alTempUserValues = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBATempUser objTempUser = null;
		try {
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_TEMP_USERS_LIST);
			pstmt.setString(1, strSurveyId);
			pstmt.setString(2, strBatchNo);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objTempUser = new OIBATempUser();
				objTempUser.setStrTempUserId(rs.getString("TEMPSURVEYUSERID"));
				objTempUser.setStrPassword(rs.getString("PASSWORD"));
				objTempUser.setStrObsolete(rs.getString("OBSOLETE"));
				alTempUserValues.add(objTempUser);
			}
		} catch(SQLException se) {
logger.error(" getSurveyBatchTempUsersList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alTempUserValues;
	}

	public String getNewSurveyBatchNumber(Connection con, String strSurveyId) throws SQLException {
		ArrayList alSurveyValues = new ArrayList();
		alSurveyValues.add(strSurveyId);
		String strBatchNo = OISQLUtilities.getString(con, OISurveySqls.SURVEY_NEW_BATCH, alSurveyValues, "getNewSurveyBatchNumber", "OIDAOTempUserAdmin");
		return strBatchNo;
	}
	
	public boolean insertTempUsers(Connection con, String strSurveyId, String strBatchNo, ArrayList alTempUserIds, ArrayList alPasswords) throws SQLException {
		boolean insertFlag = false;
		ArrayList alSurveyTempUserValues = new ArrayList();
		ArrayList alTempUserValues = null;

		for(int i=0; i<alTempUserIds.size(); i++) {
			alTempUserValues = new ArrayList();
			alTempUserValues.add(strSurveyId + strBatchNo + alTempUserIds.get(i));
			alTempUserValues.add(strSurveyId);
			alTempUserValues.add(strBatchNo);
			alTempUserValues.add(alPasswords.get(i));
			alSurveyTempUserValues.add(alTempUserValues);
		}
		insertFlag = (OISQLUtilities.executeMultiple(con, OISurveySqls.SURVEY_INSERT_TEMP_USERS, alSurveyTempUserValues, "insertTempUsers", "OIDAOTempUserAdmin") == alSurveyTempUserValues.size());
		return insertFlag;
	}

	public boolean deleteSurveyBatchTempUsers(Connection con, String strSurveyId, String strBatchNo) throws SQLException {
		boolean deleteFlag = false;
		ArrayList alTempUserValues = new ArrayList();
		alTempUserValues.add(strSurveyId);
		alTempUserValues.add(strBatchNo);
		deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.SURVEY_DELETE_TEMP_USERS, alTempUserValues, "deleteSurveyBatchTempUsers", "OIDAOTempUserAdmin") > 0);
		return deleteFlag;
	}
	
	
}
