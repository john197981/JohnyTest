
package com.oifm.survey.admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.oifm.survey.OIBASection;
import com.oifm.survey.OIDAOSectionUser;
import com.oifm.survey.OISurveySqls;
import com.oifm.utility.OISQLUtilities;

public class OIDAOSectionAdmin extends OIDAOSectionUser {
	
	private static final Logger logger = Logger.getLogger(OIDAOSectionAdmin.class);

	public OIDAOSectionAdmin()	{	}

	public ArrayList getSectionParentsList(Connection con, String strSurveyId, String strSectionId) throws SQLException {
		ArrayList alSectionList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASection objSection = null;
		
		try { 
			pstmt = con.prepareStatement(OISurveySqls.SECTION_PARENTS_LIST);
			pstmt.setString(1, strSurveyId);
			pstmt.setString(2, strSectionId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSection = new OIBASection();
				objSection.setStrSectionId(rs.getString("SECTIONID"));
				objSection.setStrSectionName(rs.getString("NAME"));
				alSectionList.add(objSection);
			}
		} catch(SQLException se) {
logger.error(" getSectionParentsList() "+se.getMessage());
			throw se;
		} finally {
			OISQLUtilities.closeRsetPstatement(rs, pstmt);
		}
		return alSectionList;
	}

	public ArrayList getSectionHierarchy(Connection con, String strSurveyId) throws SQLException {
		ArrayList alSectionList = new ArrayList();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		OIBASection objSection = null;
		
		try {
			pstmt = con.prepareStatement(OISurveySqls.SURVEY_SECTIONS_HIERARCHY);
			pstmt.setString(1, strSurveyId);
			rs = pstmt.executeQuery();
			while(rs.next())	{
				objSection = new OIBASection();
				objSection.setStrSectionId(rs.getString("SECTIONID"));
				objSection.setLevel(rs.getInt("LEVEL"));
				objSection.setStrSectionName(rs.getString("NAME"));
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

	public boolean createNewSection(Connection con, OIBASection objSection) throws SQLException {
		boolean insertFlag = false;
		ArrayList alSectionValues = new ArrayList();
		if(objSection != null) {
			alSectionValues.add(objSection.getStrSurveyId());
			alSectionValues.add(objSection.getStrParentId());
			alSectionValues.add(objSection.getStrSectionName());
			alSectionValues.add(objSection.getStrInstruction());
			alSectionValues.add(objSection.getStrDescription());
			insertFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.CREATE_SECTION, alSectionValues, "createNewSection", "OIDAOSectionAdmin") == 1);
		}
		return insertFlag;
	}

	public boolean modifySection(Connection con, OIBASection objSection) throws SQLException {
		boolean modifyFlag = false;
		ArrayList alSectionValues = new ArrayList();
		if(objSection != null) {
			alSectionValues.add(objSection.getStrParentId());
			alSectionValues.add(objSection.getStrSectionName());
			alSectionValues.add(objSection.getStrInstruction());
			alSectionValues.add(objSection.getStrDescription());
			alSectionValues.add(objSection.getStrSectionId());
			modifyFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.UPDATE_SECTION, alSectionValues, "modifySection", "OIDAOSectionAdmin") == 1);
		}
		return modifyFlag;
	}

	public boolean deleteSection(Connection con, String strSectionId) throws SQLException {
		boolean deleteFlag = false;
		ArrayList alSectionValues = new ArrayList();
		if(strSectionId != null) {
			alSectionValues.add(strSectionId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_SECTION, alSectionValues, "deleteSection", "OIDAOSectionAdmin") == 1);
		}
		return deleteFlag;
	}

	public boolean delteSurveySections(Connection con, String strSurveyId) throws SQLException {
		boolean deleteFlag = false;
		ArrayList alSectionValues = new ArrayList();
		if(strSurveyId != null) {
			alSectionValues.add(strSurveyId);
			deleteFlag = (OISQLUtilities.executeSingle(con, OISurveySqls.DELETE_ALL_SECTIONS, alSectionValues, "deleteSurveySections", "OIDAOSectionAdmin")  >= 0);
		}
		return deleteFlag;
	}

}
