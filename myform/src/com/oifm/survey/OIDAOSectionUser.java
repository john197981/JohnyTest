
package com.oifm.survey;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;

import org.apache.log4j.Logger;

import com.oifm.base.OIBaseDao;
import com.oifm.utility.OISQLUtilities;

public class OIDAOSectionUser extends OIBaseDao {

	private static final Logger logger = Logger.getLogger(OIDAOSectionUser.class);
	private static final String HRCY_DELIMETER = ".";

	public OIDAOSectionUser() {	}

	public ArrayList getSectionHierarchy(Connection con, String strSurveyId) throws SQLException {
		ArrayList alSectionList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASection objSection = null;
		
		try {
			pstmt = con.prepareStatement(OISurveySqls.PUBLISH_SECTION_HIERARCHY);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSection = new OIBASection();
				objSection.setStrSectionId(rs.getString("SECTIONID"));
				objSection.setLevel(rs.getInt("LEV"));
				objSection.setStrSectionName(rs.getString("NAME"));
				objSection.setStrDescription(rs.getString("DESCRIPTION"));
				objSection.setStrInstruction(rs.getString("INSTRUCTIONS"));
				alSectionList.add(objSection);
			}
		} catch(SQLException se) {
logger.error(" getSectionHierarchy() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSectionList;
	}

	public OIBASection fetchSectionInfo(Connection con, String strSectionId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASection objSection = null;
		try {
			pstmt = con.prepareStatement(OISurveySqls.SECTION_DETAILS);
			pstmt.setString(1, strSectionId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSection = new OIBASection(); 
				objSection.setStrSurveyId(rs.getString("SURVEYID"));
				objSection.setStrSectionId(strSectionId);
				objSection.setStrParentId(rs.getString("PARENTID"));
				objSection.setStrSectionName(rs.getString("NAME"));
				objSection.setStrInstruction(rs.getString("INSTRUCTIONS"));
				objSection.setStrDescription(rs.getString("DESCRIPTION"));
			}
		} catch(SQLException se) {
logger.error(" fetchSectionInfo() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return objSection;
	}


	public void setLevelsHirarchy(ArrayList alSectionList) {
		OIBASection objSection = null;
		byte[] HRCY_SET = new byte[100];
		StringBuffer strTemp = null;
		int tempLvl=0, i=0, j=0, level=-1;

		if(alSectionList != null && alSectionList.size() > 0) 	{
			for (i=0; i<alSectionList.size(); i++) {
				objSection = (OIBASection) alSectionList.get(i);
				strTemp = new StringBuffer();
				level = objSection.getLevel();
				if(tempLvl < level) HRCY_SET[level-1] = 1;
				else HRCY_SET[level-1]++;
				tempLvl = level;
				for (j=0; j < level; j++) { 
					strTemp.append(HRCY_DELIMETER + HRCY_SET[j]);
				}
				objSection.setStrLevelLabel(strTemp.toString().substring(1));
			}
		}
	}
	
	public void setSurveySectionStatistics(Connection con, String strSurveyId, String strUserId, ArrayList alSectionList, String strQuery) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Hashtable hmpSecQst = new Hashtable();
		Hashtable hmpSecRsp = new Hashtable();
		OIBASection objSection = null;
		try {
			pstmt = con.prepareStatement(strQuery);
			pstmt.setString(1, strUserId);
			pstmt.setString(2, strSurveyId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				hmpSecQst.put(rs.getString("SECTIONID"), rs.getString("QST_CNT"));
				hmpSecRsp.put(rs.getString("SECTIONID"), rs.getString("RES_CNT"));
			}
			for(int i=0; i<alSectionList.size(); i++) {
				objSection = (OIBASection) alSectionList.get(i);
				if(hmpSecQst.containsKey(objSection.getStrSectionId())) {
					objSection.setNoOfQuestions(Integer.parseInt((String)hmpSecQst.get(objSection.getStrSectionId())));
					objSection.setNoOfResponses(Integer.parseInt((String)hmpSecRsp.get(objSection.getStrSectionId())));
				}
			}
		} catch(SQLException se) {
logger.error(" setSurveySectionStatistics() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
	}

	public void setPrevNextSectionInfo(Connection con, OIBASection objSection) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String strTempSec = null;
		boolean isSetPrevSec = false;
		boolean isSetNextSec = false;
		try {
			pstmt = con.prepareStatement(OISurveySqls.PUBLISH_SEC_PREV_NEXT);
			pstmt.setString(1, objSection.getStrSurveyId());
			rs = pstmt.executeQuery();
			while(!isSetNextSec && rs.next())	{
				if(!isSetPrevSec && rs.getString("SECTIONID").equals(objSection.getStrSectionId())) {
					objSection.setStrPrevSecId(strTempSec);
					isSetPrevSec = true;
				} else if(isSetPrevSec) {
					objSection.setStrNextSecId(rs.getString("SECTIONID"));
					isSetNextSec = true;
				}
				strTempSec = rs.getString("SECTIONID");
			}
		} catch(SQLException se) {
logger.error(" setPrevNextSectionInfo() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
	}

	public ArrayList getSections(Connection con, String surveyId) throws SQLException {
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList arySections = new ArrayList();
		try {
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_SECTIONS_LIST);
			pstmt.setString(1, surveyId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				arySections.add(rs.getString("SECTIONID")); 
				
			}
		} catch(SQLException se) {
logger.error(" fetchSectionInfo() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return arySections;
	}
}
